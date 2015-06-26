package com.evozon.evoportal.myaccount.builder;
import com.evozon.evoportal.my_account.AccountModelHolder;

public class UpdateAccountModelHolderStrategy extends AccountModelHolderStrategy {

	public AccountModelHolder buildNewAccountModelHolder() {
		return createAccountModelHolderFromBuilder(requestAccountBuilder);
	}

	public AccountModelHolder buildOldAccountModelHolder() {
		return createAccountModelHolderFromBuilder(userAccountBuilder);
	}

	private AccountModelHolder createAccountModelHolderFromBuilder(AccountModelHolderBuilder build) {
		AccountModelHolder accountModelHolder = null;
		if (build != null) {
			AccountModelHolderBuilder accBuild = build.buildAddresses().buildDetailsModel();
			accBuild = accBuild.buildFreeDaysModel().buildUserDepartments().buildUserFamily();
			accountModelHolder = accBuild.build();
		}
		return accountModelHolder;
	}
}
