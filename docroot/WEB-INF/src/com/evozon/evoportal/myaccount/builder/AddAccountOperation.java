package com.evozon.evoportal.myaccount.builder;

import javax.portlet.ActionRequest;

import com.evozon.evoportal.my_account.util.MyAccountConstants;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.model.Contact;
import com.liferay.portal.model.User;
import com.liferay.portal.service.EmailAddressLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;

public class AddAccountOperation extends AbstractAccountActionOperation {

	private static final Log logger = LogFactoryUtil.getLog(AddAccountOperation.class);

	public AddAccountOperation(ActionPhaseParameters app) {
		super(app);
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

	public User getSelectedUser() throws PortalException, SystemException {
		ActionRequest actionRequest = super.getActionRequest();
		String emailAddress = actionRequest.getParameter(MyAccountConstants.EMAIL_ADDRESS);
		return UserLocalServiceUtil.getUserByEmailAddress(getCompanyId(), emailAddress);
	}
}
