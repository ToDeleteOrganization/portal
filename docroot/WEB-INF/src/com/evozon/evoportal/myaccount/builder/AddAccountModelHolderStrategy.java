package com.evozon.evoportal.myaccount.builder;

import com.evozon.evoportal.my_account.AccountModelHolder;


public class AddAccountModelHolderStrategy extends AccountModelHolderStrategy {

	public AccountModelHolder buildNewAccountModelHolder() {
		return requestAccountBuilder.buildFreeDaysModel().buildDetailsModel().buildUserDepartments().build();
	}

	public AccountModelHolder buildOldAccountModelHolder() {
		return requestAccountBuilder.build();
	}

}
