package com.evozon.evoportal.myaccount.builder.validators;

import org.apache.commons.lang.StringUtils;

import com.evozon.evoportal.myaccount.builder.ActionValidationResult;
import com.evozon.evoportal.myaccount.builder.ValidationResult;

public class CNPContentValidator extends AbstractValidator {

	private static final String CNP_INVALID_LENGTH_ERROR = "cnp-invalid-length-error";

	private static final String CNP_INVALID_CHARACTER_ERROR = "cnp-invalid-characters-error";

	private static final int CNP_LENGTH = 13;

	private final String cnp;

	public CNPContentValidator(final String cnp) {
		this.cnp = cnp;
	}

	public ValidationResult validate() {
		final ActionValidationResult res = new ActionValidationResult();

		if (StringUtils.length(cnp) != CNP_LENGTH) {
			res.addError(buildValidationMessage(CNP_INVALID_LENGTH_ERROR, CNP_INVALID_LENGTH_ERROR));
		}

		if (!isNumericInput(cnp)) {
			res.addError(buildValidationMessage(CNP_INVALID_CHARACTER_ERROR, CNP_INVALID_CHARACTER_ERROR));
		}

		return res;
	}

	protected String getCategory() {
		return AbstractValidator.PERSONAL_DETAILS_ERROR;
	}

}
