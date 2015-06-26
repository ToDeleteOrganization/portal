package com.evozon.evoportal.myaccount.builder.validators;

import com.evozon.evoportal.myaccount.builder.ActionValidationResult;
import com.evozon.evoportal.myaccount.builder.ValidationResult;
import com.liferay.portal.kernel.util.StringPool;

public class ScreenNameValidator extends AbstractValidator {

	private static final String INVALID_LENGTH_ERROR = "screen-name-invalid-length-error";

	private static final String INVALID_CHARACTERS_ERROR = "screen-name-invalid-characters-error";
	
	private static final String INSUFFICIENT_LETTERS_ERROR = "screen-name-must-contain-at-least-one-letter-error";

	private final String screenNameToValidate;

	public ScreenNameValidator(final String screenName) {
		this.screenNameToValidate = screenName;
	}

	public ValidationResult validate() {
		final ActionValidationResult res = new ActionValidationResult();
		String trimedScreenName = getTrimedScreenName();

		if (!isInputLengthValid(trimedScreenName)) {
			res.addError(buildValidationMessage(INVALID_LENGTH_ERROR, INVALID_LENGTH_ERROR));
		}

		if (!isAlphaNumericInput(trimedScreenName)) {
			// screen name should contain only letters, numbers or dots.
			res.addError(buildValidationMessage(INVALID_CHARACTERS_ERROR, INVALID_CHARACTERS_ERROR));
		}

		if (isEmptyInput(trimedScreenName) || isNumericInput(trimedScreenName)) {
			res.addError(buildValidationMessage(INSUFFICIENT_LETTERS_ERROR, INSUFFICIENT_LETTERS_ERROR));
		}

		return res;
	}

	private String getTrimedScreenName() {
		return screenNameToValidate.replace(StringPool.PERIOD, StringPool.BLANK);
	}

	protected String getCategory() {
		return AbstractValidator.PERSONAL_DETAILS_ERROR;
	}

}
