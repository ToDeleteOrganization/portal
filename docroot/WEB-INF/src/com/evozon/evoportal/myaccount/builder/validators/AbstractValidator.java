package com.evozon.evoportal.myaccount.builder.validators;

import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

import com.evozon.evoportal.myaccount.builder.AccountValidationMessage;
import com.evozon.evoportal.myaccount.builder.Validator;

// Add toString method to all subclasses
public abstract class AbstractValidator implements Validator {

	protected static final String CONTRACT_DETAILS_ERROR = "hasContractDetailsErrors";

	protected static final String PERSONAL_DETAILS_ERROR = "hasPersonalDetailsErrors";

	protected static final String FAMILY_ERROR = "hasFamilyErrors";

	protected static final String SITE_ERROR = "hasSitesErrors";

	private static final int MAX_CHARACTERS_LENGTH = 30;

	protected boolean isInputLengthValid(String input) {
		return isInputLengthValid(input, MAX_CHARACTERS_LENGTH);
	}

	protected boolean isInputLengthValid(String input, int length) {
		return StringUtils.length(input) <= length;
	}

	protected boolean isAlphaSpaceStringInput(String input) {
		return StringUtils.isAlphaSpace(input);
	}

	protected boolean isNumericInput(String input) {
		return StringUtils.isNumeric(input);
	}

	protected boolean isEmptyInput(String input) {
		return StringUtils.isEmpty(input);
	}

	protected boolean isAlphaNumericInput(String input) {
		return StringUtils.isAlphanumeric(input);
	}

	protected Calendar convertToCalendar(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal;
	}

	protected String getTrimedNameString(String stringToTrim) {
		String newString = stringToTrim.replaceAll("-", StringUtils.EMPTY);
		newString = newString.replaceAll("\\(", StringUtils.EMPTY);
		newString = newString.replaceAll("\\)", StringUtils.EMPTY);
		return newString;
	}

	protected AccountValidationMessage buildValidationMessage(String msgKey, Object msgObj) {
		return new AccountValidationMessage(msgKey, msgObj, getCategory());
	}

	protected abstract String getCategory();
}
