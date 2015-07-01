package com.evozon.evoportal.myaccount.builder;

import com.liferay.compat.portal.util.PortalUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.model.User;

public class DeleteAccountOperation extends AbstractAccountActionOperation {

	private static Log logger = LogFactoryUtil.getLog(DeleteAccountOperation.class);

	public DeleteAccountOperation(ActionPhaseParameters app) {
		super(app);
	}

	protected void executeInternalAction() {
		try {
			logger.info("Deleted user: " + getSelectedUser().getFullName());
		} catch (PortalException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SystemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// TODO: add getSelectedUser from delete operation
	public User getSelectedUser() throws PortalException, SystemException {
		return PortalUtil.getSelectedUser(getActionRequest());
	}
}
