package com.evozon.evoportal.myaccount.builder.validators;

import java.util.Date;

import com.evozon.evoportal.myaccount.builder.ActionValidationResult;
import com.evozon.evoportal.myaccount.builder.ValidationResult;

public class BirthdayValidator extends AbstractValidator {

	private static final String BIRTHDAY_AFTER_DATE_HIRED = "birthday-after-hire";

	private final Date dateHired;
	
	private final Date birthdayDate;

	public BirthdayValidator(final Date dateHired, final Date birthdayDate) {
		this.dateHired = dateHired;
		this.birthdayDate = birthdayDate;
	}

	public ValidationResult validate() {
		final ValidationResult res = new ActionValidationResult();

		if (birthdayDate.compareTo(dateHired) >= 0) {
			res.addError(buildValidationMessage(BIRTHDAY_AFTER_DATE_HIRED, BIRTHDAY_AFTER_DATE_HIRED));
		}

		return res;
	}

	protected String getCategory() {
		return AbstractValidator.PERSONAL_DETAILS_ERROR;
	}

}
