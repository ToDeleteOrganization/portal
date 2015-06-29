package com.evozon.evoportal.myaccount.builder;

import com.evozon.evoportal.my_account.util.MyAccountConstants;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.ParamUtil;

public class ActivateAccountOperation extends ManagementAccountActionOperation {

	private boolean isCancelActivation;

	public ActivateAccountOperation(ActionPhaseParameters app) {
		super(app);
		String command = ParamUtil.getString(app.getRequest(), Constants.CMD, AccountOperationBuilder.DEFAULT);
		isCancelActivation = MyAccountConstants.CANCEL_ACTIVATION.equals(command);
	}

	@Override
	protected void executeInternalAction() {
		// TODO Auto-generated method stub

	}

}
