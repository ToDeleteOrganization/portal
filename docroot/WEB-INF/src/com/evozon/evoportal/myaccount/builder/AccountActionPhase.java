package com.evozon.evoportal.myaccount.builder;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.portlet.ActionRequest;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;

public class AccountActionPhase implements AccountPhase<ActionPhaseParameters> {

	private static Log logger = LogFactoryUtil.getLog(AccountActionPhase.class);

	public void executePhase(ActionPhaseParameters pp) {
		try {
			final ActionAccountOperation actionOperation = AccountOperationBuilder.buildAccountActionOperation(pp);
			final ValidationResult validationResult = actionOperation.isActionValid();

			if (validationResult.hasMessages()) {
				addMessagesToSession(validationResult.getValidationMessages(), pp.getRequest());
			}

			if (validationResult.hasErrors()) {
				addErrorsToSession(validationResult.getValidationErrors(), pp.getRequest());

			} else {
				actionOperation.execute();
			}
		} catch (Exception e) {
			logger.error(e);
		}

	}

	private void addErrorsToSession(final List<AccountValidationMessage> validationErrors, final ActionRequest actionRequest) {
		Set<String> categories = new HashSet<String>();

		// Add error messages to session for UI rendering
		for (AccountValidationMessage err : validationErrors) {
			Object errorObj = err.getMessageObject();

			if (errorObj instanceof Exception) {
				SessionErrors.add(actionRequest, errorObj.getClass());
			} else {
				SessionErrors.add(actionRequest, err.getMessageKey(), errorObj);
			}
			categories.add(err.getAccountCategory());
		}

		// Add error categories to session for menu highlighting
		for (String category : categories) {
			SessionErrors.add(actionRequest, category);
		}
	}

	private void addMessagesToSession(final List<AccountValidationMessage> validationMessages, final ActionRequest actionRequest) {

		for (AccountValidationMessage msg : validationMessages) {
			SessionMessages.add(actionRequest, msg.getMessageKey(), msg.getMessageObject());
		}
	}
}
