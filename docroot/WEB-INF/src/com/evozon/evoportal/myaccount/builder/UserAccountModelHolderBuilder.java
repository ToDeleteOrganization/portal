package com.evozon.evoportal.myaccount.builder;

import javax.portlet.PortletRequest;

import com.evozon.evoportal.my_account.AccountModelHolder;
import com.liferay.portal.model.User;


public abstract class UserAccountModelHolderBuilder implements AccountModelHolderBuilder {

	protected final User user;

	protected final PortletRequest portletRequest;

	protected AccountModelHolder accountModelHolder;

	CreateAccountModelHolderBuilder s;

	public UserAccountModelHolderBuilder(User user, PortletRequest request) {
		this.portletRequest = request;
		this.user = user;
	}

	public AccountModelHolder build() {
		return accountModelHolder;
	}
}
