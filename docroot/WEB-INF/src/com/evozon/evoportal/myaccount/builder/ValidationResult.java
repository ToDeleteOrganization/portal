package com.evozon.evoportal.myaccount.builder;

import java.util.List;

public interface ValidationResult {

	public boolean hasErrors();
	
	public boolean hasMessages();

	public List<AccountValidationMessage> getValidationErrors();

	public List<AccountValidationMessage> getValidationMessages();

	public void addErrors(List<AccountValidationMessage> errors);

	public void addMessages(List<AccountValidationMessage> messages);
	
	public void addError(AccountValidationMessage errorMessage);

	public void addMessage(AccountValidationMessage message);

}
