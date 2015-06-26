package com.evozon.evoportal.myaccount.builder;

import java.util.ArrayList;
import java.util.List;

public class ActionValidationResult implements ValidationResult {

	private List<AccountValidationMessage> validationErrors = new ArrayList<AccountValidationMessage>();

	private List<AccountValidationMessage> validationMessages = new ArrayList<AccountValidationMessage>();

	public boolean hasErrors() {
		return !this.validationErrors.isEmpty();
	}

	public List<AccountValidationMessage> getValidationErrors() {
		return this.validationErrors;
	}

	public void addErrors(List<AccountValidationMessage> errors) {
		if (!errors.isEmpty()) {
			this.validationErrors.addAll(errors);
		}
	}

	public boolean hasMessages() {
		return !this.validationMessages.isEmpty();
	}

	public List<AccountValidationMessage> getValidationMessages() {
		return this.validationMessages;
	}

	public void addMessage(AccountValidationMessage message) {
		this.validationMessages.add(message);
	}

	public void addError(AccountValidationMessage errorMessage) {
		this.validationErrors.add(errorMessage);
	}

	public void addMessages(List<AccountValidationMessage> messages) {
		if (!messages.isEmpty()) {
			this.validationMessages.addAll(messages);
		}
	}

}
