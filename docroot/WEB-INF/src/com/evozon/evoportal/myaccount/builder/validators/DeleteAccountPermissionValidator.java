package com.evozon.evoportal.myaccount.builder.validators;

import com.evozon.evoportal.my_account.util.EvoportalUserUtil;
import com.evozon.evoportal.myaccount.builder.ActionValidationResult;
import com.evozon.evoportal.myaccount.builder.ValidationResult;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.User;

public class DeleteAccountPermissionValidator extends AbstractValidator {

	private static Log logger = LogFactoryUtil.getLog(DeleteAccountPermissionValidator.class);

	private final static String INVALID_DELETE_PERMISSION = "The user doesn't have permission to delete.";

	private final User user;

	public DeleteAccountPermissionValidator(User currentUser) {
		this.user = currentUser;
	}

	public ValidationResult validate() {
		final ActionValidationResult res = new ActionValidationResult();
		boolean isAdmin = false;
		try {
			isAdmin = EvoportalUserUtil.isAdmin(user);
		} catch (PortalException e) {
			isAdmin = false;
			logger.error(e);
		} catch (SystemException e) {
			isAdmin = false;
			logger.error(e);
		}

		if (!isAdmin) {
			res.addError(buildValidationMessage(INVALID_DELETE_PERMISSION, user.getFullName()));
		}

		return res;
	}

	protected String getCategory() {
		return StringPool.BLANK;
	}

}
