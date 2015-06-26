package com.evozon.evoportal.myaccount.builder.validators;

import java.util.ArrayList;
import java.util.List;

import com.evozon.evoportal.my_account.EditUserSaveButtonCustomAction;
import com.evozon.evoportal.myaccount.builder.ActionValidationResult;
import com.evozon.evoportal.myaccount.builder.ValidationResult;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.model.GroupConstants;

public class SiteValidator extends AbstractValidator {

	private static Log logger = LogFactoryUtil.getLog(EditUserSaveButtonCustomAction.class);

	private static final String NO_DEPARTMENT_DETECTED_ERROR = "no-department-detected";

	private final List<String> departments;

	public SiteValidator(final List<String> departments) {
		this.departments = departments;
	}

	public ValidationResult validate() {
		final ActionValidationResult res = new ActionValidationResult();

		final List<String> validDepartments = getValidDepartments(); 
		if (validDepartments.isEmpty()) {
			// the user has to be on EvoPortal and at least another
			// department
			res.addError(super.buildValidationMessage(NO_DEPARTMENT_DETECTED_ERROR, NO_DEPARTMENT_DETECTED_ERROR));

			if (logger.isWarnEnabled()) {
				logger.warn("At least one department has to be specified besides Evoportal.");
			}
		}

		return res;
	}

	private List<String> getValidDepartments() {
		final List<String> validDepartments = new ArrayList<String>();

		if ((this.departments != null) && (!this.departments.isEmpty())) {
			validDepartments.addAll(departments);

			if (validDepartments.contains(GroupConstants.GUEST)) {
				validDepartments.remove(GroupConstants.GUEST);
			}
		}

		return validDepartments;
	}

	protected String getCategory() {
		return AbstractValidator.SITE_ERROR;
	}

}
