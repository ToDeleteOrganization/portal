package com.evozon.evoportal.myaccount.builder;

import javax.portlet.PortletRequest;

import com.evozon.evoportal.my_account.AccountModelHolder;
import com.liferay.portal.model.User;

public class AccountModelHolderDirectory {

	private AccountModelHolderBuilder builder;

	public AccountModelHolder buildNewAccountModelHolder(PortletRequest request) {
		return builder.buildFreeDaysModel().buildDetailsModel().buildUserDepartments().build();
	}

	public AccountModelHolder buildOldAccountModelHolder(User user) {
		return builder.build();
	}

	public void setAccountModelHolderBuilder(AccountModelHolderBuilder builder) {
		this.builder = builder;
	}
}
