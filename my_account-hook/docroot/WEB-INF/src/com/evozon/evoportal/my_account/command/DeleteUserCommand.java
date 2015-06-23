package com.evozon.evoportal.my_account.command;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;

import com.evozon.evoportal.my_account.model.UserAccountOperation;
import com.evozon.evoportal.my_account.wrapper.UserExpandoWrapper;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.struts.StrutsPortletAction;
import com.liferay.portal.model.User;

public class DeleteUserCommand extends UserStatusAccountCommand {

	private static Log logger = LogFactoryUtil.getLog(AccountActionCommand.class);

	public DeleteUserCommand(StrutsPortletAction originalStrutsPortletAction, PortletConfig portletConfig, ActionRequest actionRequest, ActionResponse actionResponse) {
		super(originalStrutsPortletAction, portletConfig, actionRequest, actionResponse);
		commandType = UserAccountOperation.MANAGEMENT_UPDATE;
	}

	public void executeCustomAction() throws Exception {
		logger.warn("Cannot delete users. All users should only be deactivated.");
	}

	protected boolean executeStatusChange(User user) {
		// nothing do to
		return false;
	}

	protected boolean executeStatusCancel(User user) {
		// nothing do to
		return false;
	}

	protected void executeStatusCancel(UserExpandoWrapper userExpando) {
		
	}

	protected int getWorkflowCommandStatus() {
		return 0;
	}
}
