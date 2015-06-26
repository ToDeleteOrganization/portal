package com.evozon.evoportal.myaccount.builder;

import javax.portlet.PortletRequest;

import com.evozon.evoportal.my_account.AccountModelHolder;
import com.liferay.portal.model.User;

public class UpdateAccountModelHolderFactory extends AccountModelHolderDirectory {

	public AccountModelHolder buildNewAccountModelHolder(PortletRequest request) {
		return null;
	}

	public AccountModelHolder buildOldAccountModelHolder(User user) {
		return null;
	}

}
