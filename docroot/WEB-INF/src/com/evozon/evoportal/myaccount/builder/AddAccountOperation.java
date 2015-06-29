package com.evozon.evoportal.myaccount.builder;

import java.util.List;

import javax.portlet.ActionRequest;

import com.evozon.evoportal.evozonfreedaysallocator.BonusDaysComputer;
import com.evozon.evoportal.evozonfreedaysallocator.model.impl.BenefitDayType;
import com.evozon.evoportal.evozonfreedaysallocator.model.impl.FreeDaysHistoryEventType;
import com.evozon.evoportal.evozonfreedaysallocator.model.impl.FreeDaysHistoryOperationType;
import com.evozon.evoportal.evozonfreedaysallocator.service.BenefitDayLocalServiceUtil;
import com.evozon.evoportal.evozonfreedaysallocator.service.FreeDaysHistoryEntryLocalServiceUtil;
import com.evozon.evoportal.my_account.model.DetailsModel;
import com.evozon.evoportal.my_account.model.FreeDaysModel;
import com.evozon.evoportal.my_account.pmreports.integration.AddUserPMReportsIntegration;
import com.evozon.evoportal.my_account.pmreports.integration.PMReportsIntegration;
import com.evozon.evoportal.my_account.util.MyAccountConstants;
import com.evozon.evoportal.my_account.wrapper.UserExpandoWrapper;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.Contact;
import com.liferay.portal.model.ListType;
import com.liferay.portal.model.User;
import com.liferay.portal.service.EmailAddressLocalServiceUtil;
import com.liferay.portal.service.ListTypeServiceUtil;
import com.liferay.portal.service.PhoneLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;

public class AddAccountOperation extends ManagementAccountActionOperation {

	private static Log logger = LogFactoryUtil.getLog(AddAccountOperation.class);

	public AddAccountOperation(ActionPhaseParameters app) {
		super(app);
	}

	protected void executeInternalAction() {
		try {
			// if the pm reports 
			executePMReportsIntegration();
			executeExpandoPropertiesUpdate();
			executePhoneNumbersUpdate();
		} catch (Exception e) {
			logger.error("Error updating created user.");
		}
	}

	private void executePMReportsIntegration() throws PortalException, SystemException {
		long companyId = getCompanyId();
		User user = getUser();
		
		PMReportsIntegration addPMUser = new AddUserPMReportsIntegration(newAccountModelHolder, companyId, user);
		super.executePMReportsIntegration(addPMUser);
	}

	protected void updateUserAdditionalEmailAddress(User user) {
		try {
			String email = newAccountModelHolder.getDetailsModel().getAdditionalEmailAddress();
			if (!email.isEmpty()) {
				String className = Contact.class.getName();
				long classPK = user.getContactId();
				long userId = user.getUserId();

				EmailAddressLocalServiceUtil.addEmailAddress(userId, className, classPK, email, 11003, true);
			}
		} catch (PortalException e) {
			logger.error(e);
		} catch (SystemException e) {
			logger.error(e);
		}

	}

	protected void executePhoneNumbersUpdate() {
		try {
			User changedUser = getUser();

			DetailsModel detailsModel = newAccountModelHolder.getDetailsModel();
			String newPhoneNumber = detailsModel.getPhoneNumber();

			if (!newPhoneNumber.isEmpty()) {
				// add new phone
				int listTypeId = getPersonalListTypeId();
				long userId = changedUser.getUserId();
				String contactName = Contact.class.getName();
				long contactId = changedUser.getContactId();

				PhoneLocalServiceUtil.addPhone(userId, contactName, contactId, newPhoneNumber, StringPool.BLANK, listTypeId, true);
			}
		} catch (Exception e) {
			logger.error(e);
		}
	}

	private void executeExpandoPropertiesUpdate() {
		User changedUser;
		try {
			changedUser = getUser();
			UserExpandoWrapper userExpando = new UserExpandoWrapper(changedUser);

			FreeDaysModel freeDaysModel = newAccountModelHolder.getFreeDaysModel();
			DetailsModel detailsModel = newAccountModelHolder.getDetailsModel();

			userExpando.setDateHired(freeDaysModel.getStartDate());
			userExpando.setCIMStartDate(freeDaysModel.getCimStartDate());
			userExpando.setInternshipStartDate(freeDaysModel.getInternshipStartDate());

			int newUserFreeDays = BonusDaysComputer.getUserLegalFreeDays(changedUser);
			userExpando.setFreeDaysInCurrentYear(newUserFreeDays);
			userExpando.setLegalVacationDays(newUserFreeDays);

			executeExtraDaysRecord(userExpando);

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

	private void executeExtraDaysRecord(UserExpandoWrapper userExpando) {
		FreeDaysModel freeDaysModel = newAccountModelHolder.getFreeDaysModel();
		int extraDaysReceived = freeDaysModel.getExtraDaysCount();
		userExpando.setExtraDays(extraDaysReceived);

		String extraDaysDescr = freeDaysModel.getExtraDaysDescription();
		if (!extraDaysDescr.isEmpty()) {
			long userId = userExpando.getUserId();

			BenefitDayLocalServiceUtil.saveExtraDay(userId, BenefitDayType.EXTRA_DAYS.name(), extraDaysReceived, extraDaysDescr);

			FreeDaysHistoryEntryLocalServiceUtil.saveFreeDaysHistoryEntry(userId, FreeDaysHistoryOperationType.ADD.name(), 0, extraDaysReceived, extraDaysReceived,
					FreeDaysHistoryEventType.EXTRA_DAYS.name(), extraDaysDescr);
		}

	}

	public User getUser() throws PortalException, SystemException {
		ActionRequest actionRequest = super.getActionRequest();
		String emailAddress = actionRequest.getParameter(MyAccountConstants.EMAIL_ADDRESS);
		return UserLocalServiceUtil.getUserByEmailAddress(getCompanyId(), emailAddress);
	}

	private int getPersonalListTypeId() throws SystemException {
		int listType = 0;

		List<ListType> listTypeList = ListTypeServiceUtil.getListTypes("com.liferay.portal.model.Contact.phone");
		for (ListType lType : listTypeList) {
			if (lType.getName().equals("personal")) {
				listType = lType.getListTypeId();
				break;
			}
		}
		return listType;
	}
}
