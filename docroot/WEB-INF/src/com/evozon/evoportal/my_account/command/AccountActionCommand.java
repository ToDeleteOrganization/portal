package com.evozon.evoportal.my_account.command;

import java.io.IOException;
import java.util.ResourceBundle;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;

import com.evozon.evoportal.my_account.model.UserAccountOperation;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.struts.StrutsPortletAction;
import com.liferay.portal.model.User;
import com.liferay.portal.util.PortalUtil;

public abstract class AccountActionCommand {

	private static Log logger = LogFactoryUtil.getLog(AccountActionCommand.class);

	private static final String PROPERTIES_FILE = "content/Language_en";

	protected StrutsPortletAction originalStrutsPortletAction;

	protected PortletConfig portletConfig;

	protected ActionRequest actionRequest;

	protected ActionResponse actionResponse;

	protected User currentUser;

	protected UserAccountOperation commandType = UserAccountOperation.NO_COMMAND;

	protected AccountActionCommand(StrutsPortletAction originalStrutsPortletAction, PortletConfig portletConfig, ActionRequest actionRequest, ActionResponse actionResponse) {
		this.originalStrutsPortletAction = originalStrutsPortletAction;
		this.portletConfig = portletConfig;
		this.actionRequest = actionRequest;
		this.actionResponse = actionResponse;

		try {
			currentUser = PortalUtil.getUser(actionRequest);
		} catch (Exception e) {
			logger.error("Error initializing account command.", e);
		}
	}

	/**
	 * The entry point of an action. <br />
	 * This will fire a before method (pre-execution), original struts action and then an after action (post-execution).
	 * 
	 * @throws Exception
	 */
	public void executeCustomAction() throws Exception {
		executeCustomActionBefore();

		if (SessionErrors.isEmpty(actionRequest)) {
			// if the 'executeCustomActionBefore' execution hasn't got any session errors
			originalStrutsPortletAction.processAction(originalStrutsPortletAction, portletConfig, actionRequest, actionResponse);
		}

		if (SessionErrors.isEmpty(actionRequest)) {
			// if the 'processAction' hasn't got any session errors
			executeCustomActionAfter();
		}
	}

	/**
	 * This should be executed before the liferay default process Action
	 */
	protected void executeCustomActionBefore() {
		// to be implemented by subclasses
	}

	/**
	 * This should be executed after the liferay default process Action
	 */
	protected void executeCustomActionAfter() {
		// to be implemented by subclasses
	}

	protected void sendRedirect(String redirectURL) {
		try {
			actionResponse.sendRedirect(redirectURL);
		} catch (IOException e) {
			logger.error("Failed to redirect action [" + getClass().getName() + "] to " + redirectURL, e);
		}
	}

	protected String getBundleMessage(String key) {
		return getResourceBundle().getString(key);
	}

	protected long getCompanyId() {
		return PortalUtil.getCompanyId(actionRequest);
	}

	private ResourceBundle getResourceBundle() {
		return ResourceBundle.getBundle(PROPERTIES_FILE, actionRequest.getLocale());
	}
}
