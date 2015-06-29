package com.evozon.evoportal.myaccount.builder;

import com.evozon.evoportal.my_account.AccountModelHolder;

public abstract class AccountModelHolderStrategy {

	protected AccountModelHolderBuilder userAccountBuilder;

	protected AccountModelHolderBuilder requestAccountBuilder;

	public abstract AccountModelHolder buildNewAccountModelHolder();

	public abstract AccountModelHolder buildOldAccountModelHolder();

	public void setNewAccountModelHolderBuilder(AccountModelHolderBuilder newBuilder) {
		this.requestAccountBuilder = newBuilder;
	}

	public void setOldAccountModelHolderBuilder(AccountModelHolderBuilder oldBuilder) {
		this.userAccountBuilder = oldBuilder;
	}

}
