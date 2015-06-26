package com.evozon.evoportal.myaccount.builder;

import com.liferay.portal.model.User;

public abstract class ManagementAccountActionOperation extends AbstractAccountActionOperation {

	// TODO: change this
	protected User changedUser;

	public ManagementAccountActionOperation(ActionPhaseParameters app) {
		super(app);
	}

	// TODO:
	public User getCurrentUser() {
		return null;
	}

	public User getUser() {
		return null;
	}

}
