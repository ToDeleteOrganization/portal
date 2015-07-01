package com.evozon.evoportal.myaccount.worker;

import com.evozon.evoportal.evozonfreedaysallocator.BonusDaysComputer;
import com.evozon.evoportal.evozonfreedaysallocator.model.impl.BenefitDayType;
import com.evozon.evoportal.evozonfreedaysallocator.model.impl.FreeDaysHistoryEventType;
import com.evozon.evoportal.evozonfreedaysallocator.model.impl.FreeDaysHistoryOperationType;
import com.evozon.evoportal.evozonfreedaysallocator.service.BenefitDayLocalServiceUtil;
import com.evozon.evoportal.evozonfreedaysallocator.service.FreeDaysHistoryEntryLocalServiceUtil;
import com.evozon.evoportal.my_account.AccountModelHolder;
import com.evozon.evoportal.my_account.model.DetailsModel;
import com.evozon.evoportal.my_account.model.FreeDaysModel;
import com.evozon.evoportal.my_account.wrapper.UserExpandoWrapper;
import com.evozon.evoportal.myaccount.builder.AbstractAccountActionOperation;
import com.evozon.evoportal.myaccount.builder.AddAccountOperation;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.model.User;

public class AddUserExpandoPropertiesUpdate extends ActionWorker {

	private static final Log logger = LogFactoryUtil.getLog(AddUserExpandoPropertiesUpdate.class);

	public void execute(AbstractAccountActionOperation operation) throws Exception {
		try {
			User changedUser = operation.getSelectedUser();
			AccountModelHolder newAccountModelHolder = operation.getNewAccountModelHolder();

			UserExpandoWrapper userExpando = new UserExpandoWrapper(changedUser);
			FreeDaysModel freeDaysModel = newAccountModelHolder.getFreeDaysModel();
			DetailsModel detailsModel = newAccountModelHolder.getDetailsModel();

			userExpando.setDateHired(freeDaysModel.getStartDate());
			userExpando.setCIMStartDate(freeDaysModel.getCimStartDate());
			userExpando.setInternshipStartDate(freeDaysModel.getInternshipStartDate());

			int newUserFreeDays = BonusDaysComputer.getUserLegalFreeDays(changedUser);
			userExpando.setFreeDaysInCurrentYear(newUserFreeDays);
			userExpando.setLegalVacationDays(newUserFreeDays);

			executeExtraDaysRecord(freeDaysModel.getExtraDaysCount(), freeDaysModel.getExtraDaysDescription(), userExpando);

			userExpando.setComments(freeDaysModel.getComments());

			userExpando.setBuilding(detailsModel.getBuilding());
			userExpando.setPersonalIdentificationNumber(detailsModel.getCNP());

			userExpando.setUserTypeCreation(newAccountModelHolder.getUserType());

			// updating official name
			String officialName = detailsModel.getOfficialName().isEmpty() ? detailsModel.getLastName() : detailsModel.getOfficialName();
			userExpando.setOfficialName(officialName);

			// update 'hide' phone number
			userExpando.setPhoneNumberHidden(detailsModel.isPhoneNumberHidden());
		} catch (PortalException e) {
			logger.error(e);
		} catch (SystemException e) {
			logger.error(e);
		}
	}

	private void executeExtraDaysRecord(int extraDaysReceived, String extraDaysDescr, UserExpandoWrapper userExpando) {
		userExpando.setExtraDays(extraDaysReceived);

		if (!extraDaysDescr.isEmpty()) {
			long userId = userExpando.getUserId();

			BenefitDayLocalServiceUtil.saveExtraDay(userId, BenefitDayType.EXTRA_DAYS.name(), extraDaysReceived, extraDaysDescr);

			FreeDaysHistoryEntryLocalServiceUtil.saveFreeDaysHistoryEntry(userId, FreeDaysHistoryOperationType.ADD.name(), 0, extraDaysReceived, extraDaysReceived,
					FreeDaysHistoryEventType.EXTRA_DAYS.name(), extraDaysDescr);
		}

	}

}
