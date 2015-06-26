package com.evozon.evoportal.myaccount.builder.validators;

import com.evozon.evoportal.my_account.util.MyAccountConstants;
import com.evozon.evoportal.myaccount.builder.ActionValidationResult;
import com.evozon.evoportal.myaccount.builder.ValidationResult;

public class FunctieCIMValidator extends AbstractValidator {

	private static final String PLEASE_SPECIFY_FUNCTIE_CIM = "please-specify-functiecim";

	private static final String PREVIOUS_FUNCTIE_CIM = "please-specify-functiecim";

	private final String functieCIM;

	public FunctieCIMValidator(final String functieCIM) {
		this.functieCIM = functieCIM;
	}

	public ValidationResult validate() {
		final ActionValidationResult res = new ActionValidationResult();

		// if present (valid), add: SessionMessages.add(actionRequest, "previousFunctieCIM", functieCIM);
		if (functieCIM.equals(MyAccountConstants.LIFERAY_NEW_LINE)) {
			res.addError(buildValidationMessage(PLEASE_SPECIFY_FUNCTIE_CIM, PLEASE_SPECIFY_FUNCTIE_CIM));

		} else {
			res.addMessage(buildValidationMessage(PREVIOUS_FUNCTIE_CIM, this.functieCIM));
		}

		return res;
	}

	protected String getCategory() {
		return AbstractValidator.CONTRACT_DETAILS_ERROR;
	}

}
