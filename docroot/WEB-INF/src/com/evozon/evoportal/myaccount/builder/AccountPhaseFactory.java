package com.evozon.evoportal.myaccount.builder;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;

import com.liferay.portal.kernel.struts.StrutsPortletAction;

public final class AccountPhaseFactory {

	public static AccountPhase<?> getAccountPhase(AccountPhaseType type) {
		return null;
	}

	public static AccountActionPhase getActionAccountPhase() {
		return new AccountActionPhase();
	}

	public static ActionPhaseParameters getActionAccountPhase(StrutsPortletAction strutsAction, PortletConfig portletConfig, ActionRequest request, ActionResponse response) {
		return new ActionPhaseParameters(request, response, strutsAction, portletConfig);
	}
}
