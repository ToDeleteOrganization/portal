package com.evozon.evoportal.my_account.command;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;

import com.evozon.evoportal.my_account.model.UserAccountOperation;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.struts.StrutsPortletAction;

public class DefaultCommand extends AccountActionCommand {

	private static Log logger = LogFactoryUtil.getLog(DefaultCommand.class);

	public DefaultCommand(StrutsPortletAction originalStrutsPortletAction, PortletConfig portletConfig, ActionRequest actionRequest, ActionResponse actionResponse) {
		super(originalStrutsPortletAction, portletConfig, actionRequest, actionResponse);
		commandType = UserAccountOperation.NO_COMMAND;
	}

	public void executeCustomAction() throws Exception {
		logger.info("Executing default command.");
		originalStrutsPortletAction.processAction(originalStrutsPortletAction, portletConfig, actionRequest, actionResponse);
	}
}
