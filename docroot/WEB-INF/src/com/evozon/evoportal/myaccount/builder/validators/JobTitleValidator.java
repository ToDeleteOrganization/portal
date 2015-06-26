package com.evozon.evoportal.myaccount.builder.validators;

import com.evozon.evoportal.myaccount.builder.ActionValidationResult;
import com.evozon.evoportal.myaccount.builder.ValidationResult;
import com.liferay.portal.kernel.util.StringPool;

public class JobTitleValidator extends AbstractValidator {

	private static final String JOB_TITLE_INVALID_LENGTH_ERROR = "job-title-invalid-length-error";

	private static final String JOB_TITLE_INVALID_CHARACTERS_ERROR = "job-title-invalid-characters-error";

	private final String jobTitle;

	public JobTitleValidator(final String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public ValidationResult validate() {
		final ActionValidationResult res = new ActionValidationResult();
		final String trimedJobTitle = trimJobTitle();

		if (!isInputLengthValid(trimedJobTitle)) {
			res.addError(buildValidationMessage(JOB_TITLE_INVALID_LENGTH_ERROR, JOB_TITLE_INVALID_LENGTH_ERROR));
		}

		if (!isAlphaSpaceStringInput(trimedJobTitle)) {
			res.addError(buildValidationMessage(JOB_TITLE_INVALID_CHARACTERS_ERROR, JOB_TITLE_INVALID_CHARACTERS_ERROR));
		}

		return res;
	}

	private String trimJobTitle() {
		return jobTitle.replace(StringPool.PERIOD, StringPool.BLANK).replace(StringPool.MINUS, StringPool.BLANK);
	}

	protected String getCategory() {
		return AbstractValidator.PERSONAL_DETAILS_ERROR;
	}

}
