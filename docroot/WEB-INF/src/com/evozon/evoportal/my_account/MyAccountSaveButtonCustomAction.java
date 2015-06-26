package com.evozon.evoportal.my_account;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import com.evozon.evoportal.my_account.command.AccountActionCommand;
import com.evozon.evoportal.my_account.command.DefaultCommand;
import com.evozon.evoportal.my_account.command.UserUpdateCommand;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.struts.StrutsPortletAction;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.ParamUtil;

public class MyAccountSaveButtonCustomAction extends AccountCustomStrutsPortletAction  {

	private static Log logger = LogFactoryUtil.getLog(MyAccountSaveButtonCustomAction.class);

	public void processAction(StrutsPortletAction originalStrutsPortletAction, PortletConfig portletConfig, ActionRequest actionRequest,
			ActionResponse actionResponse) throws Exception {
		AccountActionCommand accountCommand = null;

		String cmd = ParamUtil.getString(actionRequest, Constants.CMD);
		if (Constants.UPDATE.equals(cmd)) {
			accountCommand = new UserUpdateCommand(originalStrutsPortletAction, portletConfig, actionRequest, actionResponse);
		
		} else {
			accountCommand = new DefaultCommand(originalStrutsPortletAction, portletConfig, actionRequest, actionResponse);
		}

		logger.debug("Execute user command: " + accountCommand);
		accountCommand.executeCustomAction();
	}

	public String render(StrutsPortletAction originalStrutsPortletAction, PortletConfig portletConfig, RenderRequest renderRequest,
			RenderResponse renderResponse) throws Exception {

		handleInvalidFamilyMembersEntries(renderRequest);
		return originalStrutsPortletAction.render(originalStrutsPortletAction, portletConfig, renderRequest, renderResponse);
	}

}
