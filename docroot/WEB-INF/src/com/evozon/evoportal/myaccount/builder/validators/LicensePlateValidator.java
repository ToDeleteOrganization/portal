package com.evozon.evoportal.myaccount.builder.validators;

import org.apache.commons.lang.StringUtils;

import com.evozon.evoportal.myaccount.builder.ActionValidationResult;
import com.evozon.evoportal.myaccount.builder.ValidationResult;

public class LicensePlateValidator extends AbstractValidator {

	private static final String LICENSE_PLATE_INVALID_LENGTH_ERROR = "license-plate-invalid-length-error";
	private static final String LICENSE_PLATE_INVALID_CHARACTERS_ERROR = "license-plate-invalid-characters-error";

	private static final int MAX_LICENSE_PLATE_LENGTH = 50;

	private final String licensePlateToValidate;

	public LicensePlateValidator(final String licensePlate) {
		this.licensePlateToValidate = licensePlate;
	}

	public ValidationResult validate() {
		final ValidationResult res = new ActionValidationResult();

		if (!isInputLengthValid(licensePlateToValidate, MAX_LICENSE_PLATE_LENGTH)) {
			res.addError(buildValidationMessage(LICENSE_PLATE_INVALID_LENGTH_ERROR, LICENSE_PLATE_INVALID_LENGTH_ERROR));
		}

		final String trimLicensePlate = trimLicensePlate();
		if (!isAlphaNumericInput(trimLicensePlate)) {
			res.addError(buildValidationMessage(LICENSE_PLATE_INVALID_CHARACTERS_ERROR, LICENSE_PLATE_INVALID_CHARACTERS_ERROR));
		}

		return res;
	}

	private String trimLicensePlate() {
		String trimedLicensePalte = licensePlateToValidate.replaceAll("-", StringUtils.EMPTY);
		trimedLicensePalte = trimedLicensePalte.replaceAll(",", StringUtils.EMPTY);
		trimedLicensePalte = trimedLicensePalte.replaceAll(" ", StringUtils.EMPTY);
		return trimedLicensePalte;
	}

	protected String getCategory() {
		return AbstractValidator.PERSONAL_DETAILS_ERROR;
	}
}
