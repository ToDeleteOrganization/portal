package com.evozon.evoportal.myaccount.builder;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.evozon.evoportal.evozonfreedaysallocator.EvoportalEmailUtil;
import com.evozon.evoportal.my_account.managers.DetailsManager;
import com.evozon.evoportal.my_account.managers.FreeDaysManager;
import com.evozon.evoportal.my_account.model.DetailsModel;
import com.evozon.evoportal.my_account.model.FreeDaysModel;
import com.evozon.evoportal.my_account.pmreports.integration.PMReportsIntegration;
import com.evozon.evoportal.my_account.pmreports.integration.UpdateUserPMReportsIntegration;
import com.evozon.evoportal.my_account.util.EvoportalUserUtil;
import com.evozon.evoportal.my_account.util.MyAccountConstants;
import com.evozon.evoportal.my_account.util.MyAccountRequestUtil;
import com.evozon.evoportal.my_account.wrapper.UserExpandoWrapper;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
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

public class UpdateAccountOperation extends ManagementAccountActionOperation {

	private static final Log logger = LogFactoryUtil.getLog(AddAccountOperation.class);

	public UpdateAccountOperation(ActionPhaseParameters app) {
		super(app);
	}

	protected void executeInternalAction() {
		try {
			User selectedUser = getSelectedUser();
			executePMReportsIntegration();
			updatePersonalDetailsSection(selectedUser);
			updateContractDetailsAndFreeDays(selectedUser);
		} catch (Exception e) {
			logger.error(e);
		}
	}

	private void executePMReportsIntegration() throws PortalException, SystemException {
		long companyId = getCompanyId();
		User user = getSelectedUser();

		PMReportsIntegration updatePMUser = new UpdateUserPMReportsIntegration(companyId, oldAccountModelHolder, newAccountModelHolder, user);
		super.executePMReportsIntegration(updatePMUser);
	}

	private void updateContractDetailsAndFreeDays(User selectedUser) {
		FreeDaysManager freeDaysManager = new FreeDaysManager(oldAccountModelHolder.getFreeDaysModel(), newAccountModelHolder.getFreeDaysModel(), selectedUser);
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

	private void updatePersonalDetailsSection(User selectedUser) {
		UserExpandoWrapper userExpando = new UserExpandoWrapper(selectedUser);

		DetailsModel oldDetailsModel = oldAccountModelHolder.getDetailsModel();
		DetailsModel newDetailsModel = newAccountModelHolder.getDetailsModel();
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

	protected void updateUserAdditionalEmailAddress(User user) {

		try {
			String oldEmail = oldAccountModelHolder.getDetailsModel().getAdditionalEmailAddress();
			String newEmail = newAccountModelHolder.getDetailsModel().getAdditionalEmailAddress();

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

	protected void updateUserCIMStartDate(User selectedUser) {
		try {
			UserExpandoWrapper userExpando = new UserExpandoWrapper(selectedUser);

			String prevJobType = oldAccountModelHolder.getDetailsModel().getFunctieCIM();
			String currentJobType = userExpando.getUserJob();

			// if the previous job type was 'Bursier'/'Bursier cu beneficii'
			boolean isChangedToCIM = prevJobType.contains(MyAccountConstants.SCHOLARSHIP);
			isChangedToCIM &= !currentJobType.contains(MyAccountConstants.SCHOLARSHIP);

			// if the Job Type has changed from 'Bursier'
			isChangedToCIM &= !currentJobType.equalsIgnoreCase(prevJobType);

			Calendar newCIMStartDate = Calendar.getInstance();
			if (!isChangedToCIM) {
				FreeDaysModel freeDaysModel = newAccountModelHolder.getFreeDaysModel();
				newCIMStartDate.setTime(freeDaysModel.getCimStartDate());
			}
			userExpando.setCIMStartDate(newCIMStartDate.getTime());
		} catch (Exception e) {
			logger.warn("The CIM Start Date couldn't be updated. Message: " + e.getMessage());
		}
	}

	protected void updateBirthday(User user) {
		Date newBirthday = newAccountModelHolder.getDetailsModel().getBirthdayDate();
		try {
			Contact contact = user.getContact();
			contact.setBirthday(newBirthday);
			ContactLocalServiceUtil.updateContact(contact);
		} catch (Exception e) {
			logger.error("Birthday could not be updated for user: " + user.getFirstName());
		}
	}

	private void executeExtraDaysEmailNotification(FreeDaysManager freeDaysManager) {
		try {
			Set<User> notifiedUsers = new HashSet<User>();
			notifiedUsers.addAll(EvoportalUserUtil.getAccountingRegularUsers());
			notifiedUsers.addAll(EvoportalUserUtil.getHRRegularUsers());

			String extraDaysDescription = newAccountModelHolder.getFreeDaysModel().getExtraDaysDescription();
			int extraDaysCount = freeDaysManager.getNewModel().getExtraDaysCount();
			// send mails to all intended users
			EvoportalEmailUtil.sendMailExtraDays(freeDaysManager.getUser(), new ArrayList<User>(notifiedUsers), extraDaysDescription, extraDaysCount);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void updateUserPhone(User user) {
		try {
			String oldPhoneNumber = oldAccountModelHolder.getDetailsModel().getPhoneNumber();
			String newPhoneNumber = newAccountModelHolder.getDetailsModel().getPhoneNumber();

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
			userExpando.setPhoneNumberHidden(newAccountModelHolder.getDetailsModel().isPhoneNumberHidden());
		} catch (Exception e) {
			logger.error(e);
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

	protected void updateUserInternshipChangeDate(User selectedUser) {
		try {
			UserExpandoWrapper userExpando = new UserExpandoWrapper(selectedUser);

			String prevJobType = oldAccountModelHolder.getDetailsModel().getFunctieCIM();
			String currentJobType = userExpando.getUserJob();

			// if the previous job type was 'Intern'
			boolean isChangedFromIntern = prevJobType.contains("intern");
			isChangedFromIntern &= !currentJobType.contains("intern");

			Calendar newInternshipStart = Calendar.getInstance();
			if (!isChangedFromIntern) {
				Date internshipStart = MyAccountRequestUtil.getInternshipStartDateFromRequest(getActionRequest());
				newInternshipStart.setTime(internshipStart);
			}
			userExpando.setInternshipStartDate(newInternshipStart.getTime());
		} catch (Exception e) {
			logger.warn("The Internship Start Date couldn't be updated. Message: " + e.getMessage());
		}
	}
}
