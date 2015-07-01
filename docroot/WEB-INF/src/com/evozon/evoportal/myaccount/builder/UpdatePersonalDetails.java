package com.evozon.evoportal.myaccount.builder;

import java.util.Date;
import java.util.List;

import com.evozon.evoportal.my_account.managers.DetailsManager;
import com.evozon.evoportal.my_account.model.DetailsModel;
import com.evozon.evoportal.my_account.wrapper.UserExpandoWrapper;
import com.evozon.evoportal.myaccount.worker.ActionWorker;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.Contact;
import com.liferay.portal.model.EmailAddress;
import com.liferay.portal.model.ListType;
import com.liferay.portal.model.Phone;
import com.liferay.portal.model.User;
import com.liferay.portal.service.EmailAddressLocalServiceUtil;
import com.liferay.portal.service.ListTypeServiceUtil;
import com.liferay.portal.service.PhoneLocalServiceUtil;

// TODO split this into multiple workers
public class UpdatePersonalDetails extends ActionWorker {

	private static final Log logger = LogFactoryUtil.getLog(UpdatePersonalDetails.class);

	public void execute(AbstractAccountActionOperation operation) throws Exception {
		User selectedUser = operation.getSelectedUser();
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

	protected void updateBirthday(User user) {
		Date newBirthday = newAccountModelHolder.getDetailsModel().getBirthdayDate();
		try {
			Contact contact = user.getContact();
			contact.setBirthday(newBirthday);
		} catch (Exception e) {
			logger.error("Birthday could not be updated for user: " + user.getFirstName());
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
