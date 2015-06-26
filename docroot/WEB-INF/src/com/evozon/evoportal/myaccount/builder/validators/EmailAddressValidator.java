package com.evozon.evoportal.myaccount.builder.validators;

import com.evozon.evoportal.myaccount.builder.ActionValidationResult;
import com.evozon.evoportal.myaccount.builder.ValidationResult;
import com.liferay.portal.UserEmailAddressException;

public class EmailAddressValidator extends AbstractValidator {

	private static final String VALID_EMAIL_ADDRESS = "evozon.com";

	private static final String EMAIL_ADDRESS_DELIMITER = "@";

	private static final String NOT_A_COMPANY_EMAIL_ADDRESS = "Not a company email address!";

	private final String emailAddressToValidate;

	public EmailAddressValidator(final String emailAddress) {
		this.emailAddressToValidate = emailAddress;
	}

	public ValidationResult validate() {
		final ActionValidationResult res = new ActionValidationResult();

		final String emailDomain = emailAddressToValidate.split(EMAIL_ADDRESS_DELIMITER)[1];
		if (!VALID_EMAIL_ADDRESS.equalsIgnoreCase(emailDomain)) {
			res.addError(buildValidationMessage(NOT_A_COMPANY_EMAIL_ADDRESS, new UserEmailAddressException(NOT_A_COMPANY_EMAIL_ADDRESS)));
		}

		return res;
	}

	protected String getCategory() {
		return AbstractValidator.PERSONAL_DETAILS_ERROR;
	}

}
