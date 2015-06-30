package com.evozon.evoportal.myaccount.builder;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.User;
import com.liferay.portal.util.PortalUtil;

public abstract class ManagementAccountActionOperation extends AbstractAccountActionOperation {

	public ManagementAccountActionOperation(ActionPhaseParameters app) {
		super(app);
	}

	public User getCurrentUser() throws PortalException, SystemException {
		return PortalUtil.getUser(getActionRequest());
	}

	public User getSelectedUser() throws PortalException, SystemException {
		return PortalUtil.getSelectedUser(getActionRequest());
	}

}
