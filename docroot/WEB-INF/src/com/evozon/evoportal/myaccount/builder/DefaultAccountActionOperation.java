package com.evozon.evoportal.myaccount.builder;

import com.evozon.evoportal.my_account.command.UserProfileAccountCommand;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

public class DefaultAccountActionOperation implements ActionAccountOperation {

	private static Log logger = LogFactoryUtil.getLog(UserProfileAccountCommand.class);

	private final ActionPhaseParameters pp;

	public DefaultAccountActionOperation(ActionPhaseParameters pp) {
		this.pp = pp;
	}

	public void execute() throws Exception {
		logger.info("Executing default action processing.");
		pp.getStrutsPortletAction().processAction(pp.getStrutsPortletAction(), pp.getPortletConfig(), pp.getRequest(), pp.getResponse());
	}

	public ValidationResult isActionValid() {
		return new ActionValidationResult();
	}

}
