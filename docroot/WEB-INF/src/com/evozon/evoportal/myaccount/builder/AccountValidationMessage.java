package com.evozon.evoportal.myaccount.builder;

public class AccountValidationMessage {

	private final String messageKey;
	
	private final Object messageObject;

	private final String accountCategory;

	public AccountValidationMessage(final String msgKey,final Object msgObj, final String accCat) {
		this.messageKey = msgKey;
		this.messageObject = msgObj;
		this.accountCategory = accCat;
	}

	public String getMessageKey() {
		return messageKey;
	}

	public Object getMessageObject() {
		return messageObject;
	}

	public String getAccountCategory() {
		return accountCategory;
	}

	public String toString() {
		StringBuilder build = new StringBuilder();
		build.append("[messageKey = ").append(messageKey).append(", ");
		build.append(" messageObject = ").append(messageObject).append(", ");
		build.append(" accountCategory = ").append(accountCategory).append("]");
		return build.toString();
	}
}
