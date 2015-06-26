package com.evozon.evoportal.my_account.command;

import java.util.Date;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;

import com.evozon.evoportal.evozonfreedaysallocator.BonusDaysComputer;
import com.evozon.evoportal.evozonfreedaysallocator.model.impl.BenefitDayType;
import com.evozon.evoportal.evozonfreedaysallocator.model.impl.FreeDaysHistoryEventType;
import com.evozon.evoportal.evozonfreedaysallocator.model.impl.FreeDaysHistoryOperationType;
import com.evozon.evoportal.evozonfreedaysallocator.service.BenefitDayLocalServiceUtil;
import com.evozon.evoportal.evozonfreedaysallocator.service.FreeDaysHistoryEntryLocalServiceUtil;
import com.evozon.evoportal.my_account.model.UserAccountOperation;
import com.evozon.evoportal.my_account.util.MyAccountConstants;
import com.evozon.evoportal.my_account.util.MyAccountRequestUtil;
import com.evozon.evoportal.my_account.wrapper.UserExpandoWrapper;
import com.evozon.evoportal.ws.pmreports.model.PmResponseStatus;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.struts.StrutsPortletAction;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.User;

public class AddAccountCommand extends UserProfileAccountCommand {

	private static Log logger = LogFactoryUtil.getLog(AddAccountCommand.class);

	private static final int MAXIMUM_STARTING_BONUS_DAYS = 5;

	public AddAccountCommand(StrutsPortletAction originalStrutsPortletAction, PortletConfig portletConfig, ActionRequest actionRequest, ActionResponse actionResponse) {
		super(originalStrutsPortletAction, portletConfig, actionRequest, actionResponse);
		commandType = UserAccountOperation.ADD_USER;
	}

	public void executeCustomActionAfter() {
		User createdUser = getSelectedUser();

		if (createdUser != null) {
			integrateWithPmReports(createdUser);
			updateExpandoProperties(createdUser);
			updateUserPhone(createdUser);
			updateUserAdditionalEmailAddress(createdUser);

		} else {
			logger.error("Error creating user.");
		}

	}

	private void integrateWithPmReports(User user) {
		oldAccountModel = MyAccountRequestUtil.getAccountModelFromUser(user);
		PmResponseStatus pmCall = executePMReportsUserIntegration(oldAccountModel, user);

		if (pmCall != null) {
			logger.info("Pm Reports add user " + user.getFullName() + ", call finished with status: " + pmCall);
		}
	}

	private void updateExpandoProperties(User createdUser) {
		UserExpandoWrapper userExpando = new UserExpandoWrapper(createdUser);

		Date hiredDate = newAccountModel.getFreeDaysModel().getStartDate();
		userExpando.setDateHired(hiredDate);
		userExpando.setCIMStartDate(hiredDate);
		userExpando.setInternshipStartDate(hiredDate);

		int startingBonusDays = newAccountModel.getFreeDaysModel().getStartingBonusDays();
		startingBonusDays = (startingBonusDays > MAXIMUM_STARTING_BONUS_DAYS) ? MAXIMUM_STARTING_BONUS_DAYS : startingBonusDays;
		userExpando.setStartingBonusDays(startingBonusDays);

		int newUserFreeDays = BonusDaysComputer.getUserLegalFreeDays(createdUser);
		userExpando.setFreeDaysInCurrentYear(newUserFreeDays);
		userExpando.setLegalVacationDays(newUserFreeDays);

		executeExtraDaysRecord(userExpando);

		userExpando.setComments(newAccountModel.getFreeDaysModel().getComments());

		userExpando.setBuilding(newAccountModel.getDetailsModel().getBuilding());
		userExpando.setPersonalIdentificationNumber(newAccountModel.getDetailsModel().getCNP());

		userExpando.setUserTypeCreation(ParamUtil.getString(actionRequest, "user_type"));

		// updating official name
		String officialName = ParamUtil.getString(actionRequest, MyAccountConstants.EXPANDO_OFFICIAL_NAME_ATTRIBUTE, StringPool.BLANK);
		if (officialName.isEmpty()) {
			officialName = userExpando.getUser().getLastName();
		}

		userExpando.setOfficialName(officialName);
	}

	private void executeExtraDaysRecord(UserExpandoWrapper userExpando) {
		int extraDaysReceived = newAccountModel.getFreeDaysModel().getExtraDaysCount();
		userExpando.setExtraDays(extraDaysReceived);

		String extraDaysDescr = newAccountModel.getFreeDaysModel().getExtraDaysDescription();
		if (!extraDaysDescr.isEmpty()) {
			long userId = userExpando.getUserId();

			BenefitDayLocalServiceUtil.saveExtraDay(userId, BenefitDayType.EXTRA_DAYS.name(), extraDaysReceived, extraDaysDescr);

			FreeDaysHistoryEntryLocalServiceUtil.saveFreeDaysHistoryEntry(userId, FreeDaysHistoryOperationType.ADD.name(), 0, extraDaysReceived, extraDaysReceived,
					FreeDaysHistoryEventType.EXTRA_DAYS.name(), extraDaysDescr);
		}

	}
}
