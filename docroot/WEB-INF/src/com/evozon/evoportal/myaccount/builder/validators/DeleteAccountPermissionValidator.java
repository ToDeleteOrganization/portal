package com.evozon.evoportal.myaccount.builder.validators;

import com.evozon.evoportal.myaccount.builder.ValidationResult;
import com.evozon.evoportal.myaccount.builder.Validator;
import com.liferay.portal.model.User;

public class DeleteAccountPermissionValidator implements Validator {

	private final User user;

	public DeleteAccountPermissionValidator(User currentUser) {
		this.user = currentUser;
	}

	public ValidationResult validate() {
		return null;
	}

}
