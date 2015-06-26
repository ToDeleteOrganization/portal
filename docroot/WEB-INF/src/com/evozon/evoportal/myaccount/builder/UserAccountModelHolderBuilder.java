package com.evozon.evoportal.myaccount.builder;

import com.evozon.evoportal.my_account.AccountModelHolder;
import com.liferay.portal.model.User;


public abstract class UserAccountModelHolderBuilder implements AccountModelHolderBuilder {

	protected User user;

	protected AccountModelHolder accountModelHolder;

	public AccountModelHolder build() {
		return accountModelHolder;
	}
}
