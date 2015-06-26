package com.evozon.evoportal.myaccount.builder;

import java.util.ArrayList;
import java.util.List;

public class ActionValidationResult implements ValidationResult {

	private List<AccountValidationResult> validationErrors = new ArrayList<AccountValidationResult>();

	private List<AccountValidationResult> validationMessages = new ArrayList<AccountValidationResult>();

	public boolean hasErrors() {
		return !this.validationErrors.isEmpty();
	}

	public List<AccountValidationResult> getValidationErrors() {
		return this.validationErrors;
	}

	public void addErrors(List<AccountValidationResult> errors) {
		if (!errors.isEmpty()) {
			this.validationErrors.addAll(errors);
		}
	}

	public boolean hasMessages() {
		return !this.validationMessages.isEmpty();
	}

	public List<AccountValidationResult> getValidationMessages() {
		return this.validationMessages;
	}

	public void addMessage(AccountValidationResult message) {
		this.validationMessages.add(message);
	}

	public void addError(AccountValidationResult errorMessage) {
		this.validationErrors.add(errorMessage);
	}

	public void addMessages(List<AccountValidationResult> messages) {
		if (!messages.isEmpty()) {
			this.validationMessages.addAll(messages);
		}
	}

}
