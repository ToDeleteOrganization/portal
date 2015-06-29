package com.evozon.evoportal.myaccount.builder;

import javax.portlet.PortletRequest;

import com.evozon.evoportal.my_account.AccountModelHolder;

public class CreateAccountModelHolderBuilder extends AccountModelHolderStrategy {

	private final RequestAccountModelHolderBuilder fromRequestBuilder;

	public CreateAccountModelHolderBuilder(PortletRequest request) {
		fromRequestBuilder = new RequestAccountModelHolderBuilder(request);
	}

	public AccountModelHolder buildNewAccountModelHolder() {
		return fromRequestBuilder.buildUserDepartments().buildDetailsModel().buildFreeDaysModel().build();
	}

	public AccountModelHolder buildOldAccountModelHolder() {
		// this doesn't apply for this kind
		return null;
	}

}
