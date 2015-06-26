package com.evozon.evoportal.myaccount.builder.validators;

import com.evozon.evoportal.myaccount.builder.ActionValidationResult;
import com.evozon.evoportal.myaccount.builder.ValidationResult;

public class LastNameValidator extends AbstractValidator {

	private static final String LAST_NAME_INVALID_LENGTH_ERROR = "last-name-invalid-length-error";

	private static final String LAST_NAME_INVALID_CHARACTERS_ERROR = "last-name-invalid-characters-error";

	private final String lastNameToValidate;

	public LastNameValidator(final String lastName) {
		this.lastNameToValidate = lastName;
	}

	public ValidationResult validate() {
		final ActionValidationResult res = new ActionValidationResult();

		if (!isInputLengthValid(lastNameToValidate)) {
			res.addError(buildValidationMessage(LAST_NAME_INVALID_LENGTH_ERROR, LAST_NAME_INVALID_LENGTH_ERROR));
		}

		final String trimedLastName = getTrimedNameString(lastNameToValidate);
		if (!isAlphaSpaceStringInput(trimedLastName)) {
			res.addError(buildValidationMessage(LAST_NAME_INVALID_CHARACTERS_ERROR, LAST_NAME_INVALID_CHARACTERS_ERROR));
		}

		return res;
	}

	protected String getCategory() {
		return AbstractValidator.PERSONAL_DETAILS_ERROR;
	}

}
