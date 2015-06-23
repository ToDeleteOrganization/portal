package com.evozon.evoportal.my_account.command;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;

import org.apache.log4j.Logger;

import com.evozon.evoportal.evozonfreedaysallocator.EvoportalEmailUtil;
import com.evozon.evoportal.evozonfreedaysallocator.UpdatedFieldMarker;
import com.evozon.evoportal.my_account.managers.DetailsManager;
import com.evozon.evoportal.my_account.managers.EvoAddressManager;
import com.evozon.evoportal.my_account.model.DetailsModel;
import com.evozon.evoportal.my_account.model.EvoAddressModel;
import com.evozon.evoportal.my_account.model.UserAccountOperation;
import com.evozon.evoportal.my_account.util.EvoportalUserUtil;
import com.evozon.evoportal.my_account.wrapper.UserExpandoWrapper;
import com.evozon.evoportal.ws.pmreports.model.PmResponseStatus;
import com.liferay.portal.kernel.struts.StrutsPortletAction;
import com.liferay.portal.model.User;

public class UserUpdateCommand extends UserProfileAccountCommand {

	private static Logger logger = Logger.getLogger(UserUpdateCommand.class);

	public UserUpdateCommand(StrutsPortletAction originalStrutsPortletAction, PortletConfig portletConfig, ActionRequest actionRequest, ActionResponse actionResponse) {
		super(originalStrutsPortletAction, portletConfig, actionRequest, actionResponse);
		commandType = UserAccountOperation.USER_UPDATE;
	}

	public void executeCustomActionAfter() {
		try {
			UpdatedFieldMarker marker = new UpdatedFieldMarker();

			DetailsModel oldDetails = oldAccountModel.getDetailsModel();
			DetailsModel newDetails = newAccountModel.getDetailsModel();

			DetailsManager detailsManager = new DetailsManager(oldDetails, newDetails);
			UserExpandoWrapper userExpandoWrapper = new UserExpandoWrapper(currentUser);

			boolean updatedSkype = detailsManager.isSkypeChanged();
			marker.setSkype(updatedSkype);

			boolean updatedBuilding = detailsManager.isBuildingChanged();
			marker.setBuilding(updatedBuilding);

			boolean updatedPhones = detailsManager.isPhoneNumberChanged();
			marker.setPhones(updatedPhones);

			boolean updatedAddress = handleAddresses(oldAccountModel.getPrimaryAddress(), newAccountModel.getPrimaryAddress());
			marker.setAddresses(updatedAddress);

			boolean updatedFamily = false;
			boolean updatedContacts = updatedSkype || updatedPhones || updatedAddress;
			boolean notificationNeeded = updatedFamily || updatedContacts || updatedBuilding;

			boolean isBirthdayChanged = detailsManager.isBirthdayDateChanged();
			if (isBirthdayChanged) {
				updateBirthday(currentUser);
			}

			boolean isHidePhoneNunmber = detailsManager.isHidePhoneNumberChanged();
			if (updatedPhones || isHidePhoneNunmber) {
				updateUserPhone(currentUser);
			}

			if (detailsManager.isHideBirthdayChanged()) {
				userExpandoWrapper.setBirthdayHidden(newDetails.isBirthdayHidden());
			}

			if (detailsManager.isStudentChanged()) {
				userExpandoWrapper.setIsStudentHidden(newDetails.isStudent());
			}

			if (notificationNeeded) {
				// TODO: change this, the user that made the modification shouldn't be in the same list as the notified users.
				Set<User> notifiedUsers = new HashSet<User>();
				notifiedUsers.addAll(EvoportalUserUtil.getAccountingRegularUsers());
				notifiedUsers.addAll(EvoportalUserUtil.getHRRegularUsers());

				ArrayList<User> notifiedUsersList = new ArrayList<User>(notifiedUsers);
				notifiedUsersList.add(0, currentUser);
				EvoportalEmailUtil.sendMailAccountUpdate(notifiedUsersList, marker);
			}
			handleFamilyModificaions();

			integrateWithPmReports(getSelectedUser());
		} catch (Exception e) {
			logger.error(e);
		}
	}

	private void integrateWithPmReports(User user) {
		PmResponseStatus pmCall = executePMReportsUserIntegration(oldAccountModel, user);

		if (pmCall != null) {
			logger.info("Pm Reports (own) updates for user " + user.getFullName() + ", call finished with status: " + pmCall);
		}
	}

	private boolean handleAddresses(EvoAddressModel oldPrimaryAddress, EvoAddressModel newPrimaryAddress) {
		if ((oldPrimaryAddress == null) && (newPrimaryAddress == null)) {
			return false;

		} else if ((oldPrimaryAddress != null) && (newPrimaryAddress == null)) {
			return true;

		} else if ((oldPrimaryAddress == null) && (newPrimaryAddress != null)) {
			return true;
		}

		EvoAddressManager addressManager = new EvoAddressManager(oldPrimaryAddress, newPrimaryAddress);
		if (addressManager.isStreetNameChanged()) {
			return true;
		}

		if (addressManager.isStreetNumberChanged()) {
			return true;
		}

		if (addressManager.isCityChanged()) {
			return true;
		}

		if (addressManager.isCountryCodeChanged()) {
			return true;
		}

		if (addressManager.isTypeChanged()) {
			return true;
		}

		if (addressManager.isRegionIdChanged()) {
			return true;
		}
		return false;
	}

}
