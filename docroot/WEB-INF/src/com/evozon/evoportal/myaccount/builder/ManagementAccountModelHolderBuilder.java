package com.evozon.evoportal.myaccount.builder;

import javax.portlet.PortletRequest;

import com.liferay.portal.model.User;

public class ManagementAccountModelHolderBuilder extends UserAccountModelHolderBuilder {

	public ManagementAccountModelHolderBuilder(User user, PortletRequest request) {
		super(user, request);
	}

	public AccountModelHolderBuilder buildFreeDaysModel() {
		return null;
	}

	public AccountModelHolderBuilder buildDetailsModel() {
		return null;
	}

	public AccountModelHolderBuilder buildAddresses() {
		return null;
	}

	public AccountModelHolderBuilder buildUserFamily() {
		return null;
	}

	public AccountModelHolderBuilder buildUserDepartments() {
		return null;
	}

}
