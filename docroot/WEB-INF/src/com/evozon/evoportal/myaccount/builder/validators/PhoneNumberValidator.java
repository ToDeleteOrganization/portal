package com.evozon.evoportal.myaccount.builder.validators;

import com.evozon.evoportal.myaccount.builder.ActionValidationResult;
import com.evozon.evoportal.myaccount.builder.ValidationResult;
import com.liferay.portal.kernel.util.StringPool;

public class PhoneNumberValidator extends AbstractValidator {

	private static final String INVALID_PHONE_NUMBER = "invalid-phone-number";

	private final String phoneNumber;

	public PhoneNumberValidator(final String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public ValidationResult validate() {
		final ActionValidationResult res = new ActionValidationResult();

		final String trimedPhoneNumber = trimPhoneNumber();
		if (!isNumericInput(trimedPhoneNumber)) {
			res.addError(buildValidationMessage(INVALID_PHONE_NUMBER, INVALID_PHONE_NUMBER));
		}

		return res;
	}

	private String trimPhoneNumber() {
		String trimedPhonenumber = phoneNumber.replace(",", StringPool.BLANK);
		trimedPhonenumber = trimedPhonenumber.replace("-", StringPool.BLANK);
		trimedPhonenumber = trimedPhonenumber.replace(";", StringPool.BLANK);
		trimedPhonenumber = trimedPhonenumber.replace("/", StringPool.BLANK);
		trimedPhonenumber = trimedPhonenumber.replace(".", StringPool.BLANK);
		trimedPhonenumber = trimedPhonenumber.replace(" ", StringPool.BLANK);
		return trimedPhonenumber;
	}

	protected String getCategory() {
		return AbstractValidator.PERSONAL_DETAILS_ERROR;
	}
}
