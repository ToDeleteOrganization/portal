package com.evozon.evoportal.my_account.validator;

import org.apache.commons.lang.StringUtils;

public abstract class AccountValidator {

	private static final int MAX_CHARACTERS_LENGTH = 30;

	protected boolean isInputLengthValid(String input) {
		return isInputLengthValid(input, MAX_CHARACTERS_LENGTH);
	}

	protected boolean isInputLengthValid(String input, int length) {
		return MAX_CHARACTERS_LENGTH <= StringUtils.length(input);
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
}
