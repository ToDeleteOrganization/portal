package com.evozon.evoportal.myaccount.builder;

import com.evozon.evoportal.my_account.util.MyAccountConstants;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.ParamUtil;

public class DeactivateAccountOperation extends ManagementAccountActionOperation {

	private boolean isCancelCommand;

	public DeactivateAccountOperation(ActionPhaseParameters app) {
		super(app);
		String command = ParamUtil.getString(app.getRequest(), Constants.CMD, AccountOperationBuilder.DEFAULT);
		isCancelCommand = MyAccountConstants.CANCEL_DEACTIVATION.equals(command);
		
	}

	protected void executeInternalAction() {
		
	}

}
