package com.evozon.evoportal.my_account.command;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;

import com.evozon.evoportal.evozonfreedaysallocator.EvoportalEmailUtil;
import com.evozon.evoportal.my_account.managers.DetailsManager;
import com.evozon.evoportal.my_account.managers.FreeDaysManager;
import com.evozon.evoportal.my_account.model.DetailsModel;
import com.evozon.evoportal.my_account.model.FreeDaysModel;
import com.evozon.evoportal.my_account.model.UserAccountOperation;
import com.evozon.evoportal.my_account.util.EvoportalUserUtil;
import com.evozon.evoportal.my_account.wrapper.UserExpandoWrapper;
import com.evozon.evoportal.ws.pmreports.model.PmResponseStatus;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.struts.StrutsPortletAction;
import com.liferay.portal.model.User;

public class UpdateAccountCommand extends UserProfileAccountCommand {

	private static Log logger = LogFactoryUtil.getLog(UpdateAccountCommand.class);

	public UpdateAccountCommand(StrutsPortletAction originalStrutsPortletAction, PortletConfig portletConfig, ActionRequest actionRequest, ActionResponse actionResponse) {
		super(originalStrutsPortletAction, portletConfig, actionRequest, actionResponse);
		commandType = UserAccountOperation.MANAGEMENT_UPDATE;
	}

	public void executeCustomActionAfter() {
		User selectedUser = getSelectedUser();

		updatePersonalDetailsSection(selectedUser);

		updateContractDetailsAndFreeDays(selectedUser);

		handleFamilyModificaions();

		PmResponseStatus pmCall = executePMReportsUserIntegration(oldAccountModel, selectedUser);
		if (pmCall != null) {
			logger.info("Pm Reports management update user " + selectedUser.getFullName() + ", call finished with status: " + pmCall);
		}
	}

	private void updatePersonalDetailsSection(User selectedUser) {
		UserExpandoWrapper userExpando = new UserExpandoWrapper(selectedUser);

		DetailsModel oldDetailsModel = oldAccountModel.getDetailsModel();
		DetailsModel newDetailsModel = newAccountModel.getDetailsModel();
		DetailsManager detailsManager = new DetailsManager(oldDetailsModel, newDetailsModel);

		if (detailsManager.isCNPChanged()) {
			userExpando.setPersonalIdentificationNumber(newDetailsModel.getCNP());
		}

		if (detailsManager.isHideBirthdayChanged()) {
			userExpando.setBirthdayHidden(newDetailsModel.isBirthdayHidden());
		}

		if (detailsManager.isHidePhoneNumberChanged()) {
			userExpando.setPhoneNumberHidden(newDetailsModel.isPhoneNumberHidden());
		}

		if (detailsManager.isHideBirthdayChanged()) {
			userExpando.setBirthdayHidden(newDetailsModel.isBirthdayHidden());
		}

		if (detailsManager.isStudentChanged()) {
			userExpando.setIsStudentHidden(newDetailsModel.isStudent());
		}

		if (detailsManager.isPhoneNumberChanged()) {
			updateUserPhone(selectedUser);
		}

		if (detailsManager.isAdditionalEmailAddressChanged()) {
			updateUserAdditionalEmailAddress(selectedUser);
		}

		if (detailsManager.isBirthdayDateChanged()) {
			updateBirthday(selectedUser);
		}

	}

	private void updateContractDetailsAndFreeDays(User selectedUser) {
		FreeDaysManager freeDaysManager = new FreeDaysManager(oldAccountModel.getFreeDaysModel(), newAccountModel.getFreeDaysModel(), selectedUser);
		if (freeDaysManager.isFreeDaysFromLastYearChanged()) {
			freeDaysManager.executeFreeDaysFromLastYearChange();
		}

		if (freeDaysManager.isFreeDaysInCurrentYearChanged()) {
			freeDaysManager.executeFreeDaysInCurrentYearChange();
		}

		if (freeDaysManager.isDateHiredChanged()) {
			freeDaysManager.executeStartDateChange();
		}

		if (freeDaysManager.isStartingBonusDaysChanged()) {
			freeDaysManager.executeStartingBonusDaysChange();
		}

		if (freeDaysManager.isExtraDaysNumberChanged()) {
			freeDaysManager.executeExtraDaysChange();
			executeExtraDaysEmailNotification(freeDaysManager);
		}

		if (freeDaysManager.isCommentsChanged()) {
			freeDaysManager.executeCommentChange();
		}

		if (freeDaysManager.isCIMStartDateChanged()) {
			updateUserCIMStartDate(selectedUser);
		}

		if (freeDaysManager.isInternshipStartDateChanged()) {
			updateUserInternshipChangeDate(selectedUser);
		}

	}

	private void executeExtraDaysEmailNotification(FreeDaysManager freeDaysManager) {
		try {
			Set<User> notifiedUsers = new HashSet<User>();
			notifiedUsers.addAll(EvoportalUserUtil.getAccountingRegularUsers());
			notifiedUsers.addAll(EvoportalUserUtil.getHRRegularUsers());

			FreeDaysModel newFreeDaysModel = freeDaysManager.getNewModel();
			int totalFreeDays = newFreeDaysModel.getFreeDaysInCurrentYear();
			totalFreeDays += newFreeDaysModel.getFreeDaysFromLastYear();
			totalFreeDays += newFreeDaysModel.getExtraDaysCount();

			String extraDaysDescription = newAccountModel.getFreeDaysModel().getExtraDaysDescription();
			int extraDaysCount = newFreeDaysModel.getExtraDaysCount();
			// send mails to all intended users
			EvoportalEmailUtil.sendMailExtraDays(freeDaysManager.getUser(), notifiedUsers, extraDaysDescription, extraDaysCount);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
