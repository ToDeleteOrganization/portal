package com.evozon.evoportal.myaccount.builder.validators;

import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;

import javax.portlet.PortletRequest;

import org.apache.commons.lang.StringUtils;

import com.evozon.evoportal.myaccount.builder.AccountValidationResult;
import com.evozon.evoportal.myaccount.builder.Validator;

// Add toString method to all subclasses
public abstract class AbstractValidator implements Validator {

	private static final String PROPERTIES_FILE = "content/Language_en";

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

	protected AccountValidationResult buildValidationMessage(String msgKey, Object msgObj) {
		return new AccountValidationResult(msgKey, msgObj, getCategory());
	}

	protected String getBundleMessage(PortletRequest request, String key) {
		return getResourceBundle(request).getString(key);
	}

	private ResourceBundle getResourceBundle(PortletRequest request) {
		return ResourceBundle.getBundle(PROPERTIES_FILE, request.getLocale());
	}

	protected abstract String getCategory();
}
