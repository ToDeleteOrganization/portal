package com.evozon.evoportal.myaccount.builder;

import com.evozon.evoportal.my_account.AccountModelHolder;

public abstract class AccountModelHolderStrategy {

	protected AccountModelHolderBuilder userAccountBuilder;

	protected AccountModelHolderBuilder requestAccountBuilder;

	public abstract AccountModelHolder buildNewAccountModelHolder();

	public abstract AccountModelHolder buildOldAccountModelHolder();

	public void setAccountModelHolderBuilder(AccountModelHolderBuilder builder) {
		this.requestAccountBuilder = builder;
	}
}
