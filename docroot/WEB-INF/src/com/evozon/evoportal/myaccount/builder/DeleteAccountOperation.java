package com.evozon.evoportal.myaccount.builder;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

public class DeleteAccountOperation extends ManagementAccountActionOperation {

	private static Log logger = LogFactoryUtil.getLog(DeleteAccountOperation.class);

	public DeleteAccountOperation(ActionPhaseParameters app) {
		super(app);
	}

	protected void executeInternalAction() {
		try {
			executeDefaultLiferayProcess();
		} catch (Exception e) {
			logger.error("Error deleting account.");
		}
	}

}
