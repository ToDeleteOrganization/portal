package com.evozon.evoportal.myaccount.builder.validators;

import com.evozon.evoportal.myaccount.builder.ActionValidationResult;
import com.evozon.evoportal.myaccount.builder.ValidationResult;

public class FirstNameValidator extends AbstractValidator {

	private static final String FIRST_NAME_INVALID_LENGTH_ERROR = "first-name-invalid-length-error";

	private static final String FIRST_NAME_INVALID_CHARACTERS_ERROR = "first-name-invalid-characters-error";

	private final String firstNameToValidate;

	public FirstNameValidator(final String firstName) {
		this.firstNameToValidate = firstName;
	}

	public ValidationResult validate() {
		final ActionValidationResult res = new ActionValidationResult();

		if (!isInputLengthValid(firstNameToValidate)) {
			res.addError(buildValidationMessage(FIRST_NAME_INVALID_LENGTH_ERROR, FIRST_NAME_INVALID_LENGTH_ERROR));
		}

		final String trimedFirstName = getTrimedNameString(firstNameToValidate);
		if (!isAlphaSpaceStringInput(trimedFirstName)) {
			res.addError(buildValidationMessage(FIRST_NAME_INVALID_CHARACTERS_ERROR, FIRST_NAME_INVALID_CHARACTERS_ERROR));
		}

		return res;
	}

	protected String getCategory() {
		return AbstractValidator.PERSONAL_DETAILS_ERROR;
	}

}
