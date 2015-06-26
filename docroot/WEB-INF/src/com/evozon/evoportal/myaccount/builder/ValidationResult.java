package com.evozon.evoportal.myaccount.builder;

import java.util.List;

public interface ValidationResult {

	public boolean hasErrors();
	
	public boolean hasMessages();

	public List<AccountValidationResult> getValidationErrors();

	public List<AccountValidationResult> getValidationMessages();

	public void addErrors(List<AccountValidationResult> errors);

	public void addMessages(List<AccountValidationResult> messages);
	
	public void addError(AccountValidationResult errorMessage);

	public void addMessage(AccountValidationResult message);

}
