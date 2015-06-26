package com.evozon.evoportal.my_account.command;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.mail.internet.InternetAddress;
import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;

import org.springframework.util.MultiValueMap;

import com.evozon.evoportal.familytableaccess.slayer.model.FamilyMember;
import com.evozon.evoportal.familytableaccess.slayer.service.FamilyMemberLocalServiceUtil;
import com.evozon.evoportal.my_account.AccountModelHolder;
import com.evozon.evoportal.my_account.managers.UserFamilyHandler;
import com.evozon.evoportal.my_account.model.FreeDaysModel;
import com.evozon.evoportal.my_account.model.UserFamily;
import com.evozon.evoportal.my_account.util.EvoportalUserUtil;
import com.evozon.evoportal.my_account.util.MyAccountConstants;
import com.evozon.evoportal.my_account.util.MyAccountRequestUtil;
import com.evozon.evoportal.my_account.util.MyAccountUtil;
import com.evozon.evoportal.my_account.validator.UserAccountValidation;
import com.evozon.evoportal.my_account.wrapper.UserExpandoWrapper;
import com.evozon.evoportal.ws.pmreports.calls.PMReportsPersonCall;
import com.evozon.evoportal.ws.pmreports.model.PmResponseStatus;
import com.evozon.evoportal.ws.pmreports.util.PMReportsIntegrationUtil;
import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.mail.service.MailServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.mail.MailMessage;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.struts.StrutsPortletAction;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.Contact;
import com.liferay.portal.model.EmailAddress;
import com.liferay.portal.model.ListType;
import com.liferay.portal.model.Phone;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ContactLocalServiceUtil;
import com.liferay.portal.service.EmailAddressLocalServiceUtil;
import com.liferay.portal.service.ListTypeServiceUtil;
import com.liferay.portal.service.PhoneLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.util.PortalUtil;

public abstract class UserProfileAccountCommand extends AccountActionCommand {

	private static Log logger = LogFactoryUtil.getLog(UserProfileAccountCommand.class);

	protected AccountModelHolder newAccountModel = null; // taken from request

	protected AccountModelHolder oldAccountModel = null; // taken from user

	protected UserProfileAccountCommand(StrutsPortletAction originalStrutsPortletAction, PortletConfig portletConfig, ActionRequest actionRequest, ActionResponse actionResponse) {
		super(originalStrutsPortletAction, portletConfig, actionRequest, actionResponse);
		newAccountModel = MyAccountRequestUtil.getAccountModelFromRequest(actionRequest);

		String command = ParamUtil.get(actionRequest, Constants.CMD, StringPool.BLANK);
		if (Constants.UPDATE.equals(command)) {
			oldAccountModel = MyAccountRequestUtil.getAccountModelFromUser(getSelectedUser());
		}
	}

	protected void executeCustomActionBefore() {
		UserFamily newUserFamily = newAccountModel.getUserFamily();
		UserFamily oldUserFamily = (oldAccountModel != null) ? oldAccountModel.getUserFamily() : null;

		UserFamilyHandler familyManager = new UserFamilyHandler(oldUserFamily, newUserFamily);

		UserAccountValidation accountValidation = new UserAccountValidation();
		boolean isValid = accountValidation.validate(actionRequest, familyManager);

		if (!isValid) {
			logger.warn("The user properties aren't valid, abort user creation.");
		}
	}

	public void handleFamilyModificaions() {
		updateFamilyMembers();
		sendFamilyModificationNotifications();
	}

	protected void updateFamilyMembers() {
		UserFamilyHandler familyManager = new UserFamilyHandler(oldAccountModel.getUserFamily(), newAccountModel.getUserFamily());

		for (FamilyMember addedChild : familyManager.getAddedChildren()) {
			try {
				addFamilyMember(addedChild);
			} catch (SystemException e) {
				logger.error("Error adding child: " + addedChild);
			}
		}

		for (FamilyMember updatedChild : familyManager.getUpdatedChildren()) {
			try {
				updateFamilyMember(updatedChild);
			} catch (SystemException se) {
				logger.error("Error updating child: " + updatedChild, se);

			} catch (PortalException pe) {
				logger.error("Error updating child: " + updatedChild, pe);
			}
		}

		for (FamilyMember removedChild : familyManager.getRemovedChildrens()) {
			try {
				FamilyMemberLocalServiceUtil.deleteFamilyMember(removedChild);
			} catch (SystemException e) {
				logger.error("Error deleting child: " + removedChild);
			}
		}

		try {
			if (familyManager.isSpouseAdded()) {
				addFamilyMember(familyManager.getNewSpouse());

			} else if (familyManager.isSpouseUpdated()) {
				updateFamilyMember(familyManager.getNewSpouse());

			} else if (familyManager.isSpouseRemoved()) {
				FamilyMemberLocalServiceUtil.deleteFamilyMember(familyManager.getOldSpouse());
			}

		} catch (SystemException se) {
			logger.error("Error performing spouse modifications: " + se.getMessage());

		} catch (PortalException pe) {
			logger.error("Error performing spouse modifications: " + pe.getMessage());
		}

	}

	private void updateFamilyMember(FamilyMember familyMember) throws PortalException, SystemException {
		FamilyMember existingFamilyMember = FamilyMemberLocalServiceUtil.getFamilyMember(familyMember.getMemberId());
		copyFamilyMembersFields(familyMember, existingFamilyMember);
		FamilyMemberLocalServiceUtil.updateFamilyMember(existingFamilyMember, false);
	}

	private void addFamilyMember(FamilyMember familyMember) throws SystemException {
		FamilyMember newMember = FamilyMemberLocalServiceUtil.createFamilyMember(CounterLocalServiceUtil.increment(FamilyMember.class.getSimpleName()));

		copyFamilyMembersFields(familyMember, newMember);
		newMember.setUserBelongsId(familyMember.getUserBelongsId());
		newMember.setType(familyMember.getType());

		FamilyMemberLocalServiceUtil.addFamilyMember(newMember);
	}

	protected void updateUserCIMStartDate(User selectedUser) {
		try {
			UserExpandoWrapper userExpando = new UserExpandoWrapper(selectedUser);

			String prevJobType = (oldAccountModel != null) ? oldAccountModel.getDetailsModel().getFunctieCIM() : StringPool.BLANK;
			String currentJobType = userExpando.getUserJob();

			// if the previous job type was 'Bursier'/'Bursier cu beneficii'
			boolean isChangedToCIM = prevJobType.contains(MyAccountConstants.SCHOLARSHIP);
			isChangedToCIM &= !currentJobType.contains(MyAccountConstants.SCHOLARSHIP);

			// if the Job Type has changed from 'Bursier'
			isChangedToCIM &= !currentJobType.equalsIgnoreCase(prevJobType);

			Calendar newCIMStartDate = Calendar.getInstance();
			if (!isChangedToCIM) {
				FreeDaysModel freeDaysModel = newAccountModel.getFreeDaysModel();
				newCIMStartDate.setTime(freeDaysModel.getCimStartDate());
			}
			userExpando.setCIMStartDate(newCIMStartDate.getTime());
		} catch (Exception e) {
			logger.warn("The CIM Start Date couldn't be updated. Message: " + e.getMessage());
		}
	}

	protected void updateUserInternshipChangeDate(User selectedUser) {
		try {
			UserExpandoWrapper userExpando = new UserExpandoWrapper(selectedUser);

			String prevJobType = oldAccountModel.getDetailsModel().getFunctieCIM();
			String currentJobType = userExpando.getUserJob();

			// if the previous job type was 'Intern'
			boolean isChangedFromIntern = prevJobType.contains("intern");
			isChangedFromIntern &= !currentJobType.contains("intern");

			Calendar newInternshipStart = Calendar.getInstance();
			if (!isChangedFromIntern) {
				Date internshipStart = MyAccountRequestUtil.getInternshipStartDateFromRequest(actionRequest);
				newInternshipStart.setTime(internshipStart);
			}
			userExpando.setInternshipStartDate(newInternshipStart.getTime());
		} catch (Exception e) {
			logger.warn("The Internship Start Date couldn't be updated. Message: " + e.getMessage());
		}
	}

	protected void updateUserPhone(User user) {
		try {
			String oldPhoneNumber = oldAccountModel.getDetailsModel().getPhoneNumber();
			String newPhoneNumber = newAccountModel.getDetailsModel().getPhoneNumber();

			if (!newPhoneNumber.isEmpty() && !newPhoneNumber.equals(oldPhoneNumber)) {

				List<Phone> phones = user.getPhones();
				if (phones.isEmpty()) {
					// add new phone
					int listTypeId = getPersonalListTypeId();
					long userId = user.getUserId();
					String contactName = Contact.class.getName();
					long contactId = user.getContactId();

					PhoneLocalServiceUtil.addPhone(userId, contactName, contactId, newPhoneNumber, StringPool.BLANK, listTypeId, true);
				} else {
					// update existing phone
					Phone phone = phones.get(0);
					phone.setNumber(newPhoneNumber);
					PhoneLocalServiceUtil.updatePhone(phone);
				}
			}

			UserExpandoWrapper userExpando = new UserExpandoWrapper(user);
			userExpando.setPhoneNumberHidden(newAccountModel.getDetailsModel().isPhoneNumberHidden());
		} catch (Exception e) {
			logger.error(e);
		}
	}

	protected void updateUserAdditionalEmailAddress(User user) {

		try {
			String oldEmail = oldAccountModel.getDetailsModel().getAdditionalEmailAddress();
			String newEmail = newAccountModel.getDetailsModel().getAdditionalEmailAddress();

			if (!newEmail.isEmpty() && !newEmail.equals(oldEmail)) {

				List<EmailAddress> emailAddresses = user.getEmailAddresses();
				if (emailAddresses.isEmpty()) {
					// add new additional email address
					String className = Contact.class.getName();
					long classPK = user.getContactId();
					long userId = user.getUserId();

					EmailAddressLocalServiceUtil.addEmailAddress(userId, className, classPK, newEmail, 11003, true);
				} else {
					// update existing email address
					EmailAddress emailAddress = emailAddresses.get(0);
					emailAddress.setAddress(newEmail);
					EmailAddressLocalServiceUtil.updateEmailAddress(emailAddress);
				}

			}

		} catch (Exception e) {
			logger.error(e);
		}

	}

	protected void updateBirthday(User user) {
		Date newBirthday = newAccountModel.getDetailsModel().getBirthdayDate();
		try {
			Contact contact = user.getContact();
			contact.setBirthday(newBirthday);
			ContactLocalServiceUtil.updateContact(contact);
		} catch (Exception e) {
			logger.error("Birthday could not be updated for user: " + user.getFirstName());
		}
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

	protected PmResponseStatus executePMReportsUserIntegration(AccountModelHolder userModel, User selectedUser) {
		if (!PMReportsIntegrationUtil.isPmReportsIntegrationActivated()) {
			return null;
		}

		MultiValueMap<String, Object> modifications = MyAccountUtil.getPMReportsParameters(userModel, newAccountModel, selectedUser, commandType);
		PmResponseStatus callStatus = null;

		if (!modifications.isEmpty()) {
			PMReportsPersonCall pmPersonCall = new PMReportsPersonCall(getCompanyId());

			switch (commandType) {
			case ADD_USER:
				callStatus = pmPersonCall.addUser(modifications);
				break;
			case MANAGEMENT_UPDATE:
			case USER_UPDATE:
				callStatus = pmPersonCall.updateUser(modifications);
				break;
			default:
				// do nothing
				break;
			}

			if ((callStatus != null) && !callStatus.isSuccess()) {
				// add custom error message
				SessionErrors.add(actionRequest, "not-added-to-pmreports-error");
				// hide default error message
				SessionMessages.add(actionRequest, portletConfig.getPortletName() + SessionMessages.KEY_SUFFIX_HIDE_DEFAULT_ERROR_MESSAGE);
				sendUnsuccessfullPmReportsIntegrationMail(currentUser);
			}
		} else {
			logger.info("No PM Reports parameter modified, abort PM Reports intergration.");
		}

		return callStatus;
	}

	private void sendUnsuccessfullPmReportsIntegrationMail(User currentUser) {
		// TODO:
	}

	protected void sendFamilyModificationNotifications() {
		UserFamily newFamily = newAccountModel.getUserFamily();
		UserFamily oldFamily = (oldAccountModel != null) ? oldAccountModel.getUserFamily() : null;

		UserFamilyHandler familyHandler = new UserFamilyHandler(oldFamily, newFamily);
		if (familyHandler.isSpouseAdded()) {
			sendNewSpouseAddedMail(familyHandler.getNewSpouse());
		}

		sendNewChildMail(familyHandler.getAddedChildren());
	}

	private void sendNewSpouseAddedMail(FamilyMember spouse) {
		try {
			List<User> allUsers = EvoportalUserUtil.getFamilyMemberResponsibles();
			User selectedUser = getSelectedUser();

			StringBuilder mail = new StringBuilder();
			mail.append("User " + selectedUser.getFullName() + " has added new spouse: <br />");
			mail.append("- <b>" + spouse.getFirstName() + " " + spouse.getLastName() + "</b>, cnp: " + spouse.getCNP() + "<br />");

			for (User user : allUsers) {
				InternetAddress from = new InternetAddress("evoportal@evozon.com");
				InternetAddress to = new InternetAddress(user.getEmailAddress());
				MailMessage mailMessage = new MailMessage(from, to, "New spouse has been added for " + selectedUser.getFullName(), mail.toString(), true);
				MailServiceUtil.sendEmail(mailMessage);
			}

		} catch (Exception e) {
			logger.error(e);
		}
	}

	private void sendNewChildMail(List<FamilyMember> addedChildren) {
		try {
			List<User> allUsers = EvoportalUserUtil.getFamilyMemberResponsibles();
			User selectedUser = getSelectedUser();

			StringBuilder mail = new StringBuilder();
			mail.append("User " + selectedUser.getFullName() + " has added new children: <br />");
			for (FamilyMember fm : addedChildren) {
				mail.append("- <b>" + fm.getFirstName() + " " + fm.getLastName() + "</b>, cnp: " + fm.getCNP() + "<br />");
			}

			InternetAddress from = new InternetAddress("evoportal@evozon.com");
			for (User user : allUsers) {
				InternetAddress to = new InternetAddress(user.getEmailAddress());
				MailMessage mailMessage = new MailMessage(from, to, "New child has been added for " + selectedUser.getFullName(), mail.toString(), true);
				MailServiceUtil.sendEmail(mailMessage);
			}

		} catch (Exception e) {
			logger.error(e);
		}
	}

	protected User getSelectedUser() {
		User selectedUser = null;

		String command = ParamUtil.getString(actionRequest, Constants.CMD);
		if (Constants.UPDATE.equals(command)) {
			selectedUser = getUpdateSelectedUser();

		} else if (Constants.ADD.equals(command)) {
			selectedUser = getAddedUser();

		}
		return selectedUser;
	}

	private User getAddedUser() {
		User user = null;

		try {
			long companyId = PortalUtil.getCompanyId(actionRequest);
			String emailAddress = actionRequest.getParameter(MyAccountConstants.EMAIL_ADDRESS);
			user = UserLocalServiceUtil.getUserByEmailAddress(companyId, emailAddress);
		} catch (Exception e) {
			logger.error("Error getting added user: " + e.getMessage(), e);
		}

		return user;
	}

	private User getUpdateSelectedUser() {
		User updatedUser = null;
		try {
			updatedUser = PortalUtil.getSelectedUser(actionRequest);
		} catch (PortalException e) {
			logger.error("Error getting updated user: " + e.getMessage(), e);
		} catch (SystemException e) {
			logger.error("Error getting updated user." + e.getMessage(), e);
		}
		return updatedUser;
	}

	private void copyFamilyMembersFields(FamilyMember from, FamilyMember to) {
		to.setFirstName(from.getFirstName());
		to.setLastName(from.getLastName());
		to.setCNP(from.getCNP());
	}
}
