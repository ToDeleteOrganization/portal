package com.evozon.evoportal.myaccount.builder;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;

import com.liferay.portal.kernel.struts.StrutsPortletAction;

public class ActionPhaseParameters implements PhaseParameters<ActionRequest, ActionResponse, StrutsPortletAction, PortletConfig> {

	private final ActionRequest request;

	private final ActionResponse response;

	private final StrutsPortletAction strutsPortletAction;

	private final PortletConfig portletConfig;

	public ActionPhaseParameters(ActionRequest req, ActionResponse res, StrutsPortletAction spa, PortletConfig pc) {
		this.request = req;
		this.response = res;
		this.strutsPortletAction = spa;
		this.portletConfig = pc;
	}

	public ActionRequest getRequest() {
		return request;
	}

	public ActionResponse getResponse() {
		return response;
	}

	public StrutsPortletAction getStrutsPortletAction() {
		return strutsPortletAction;
	}

	public PortletConfig getPortletConfig() {
		return portletConfig;
	}

	public AccountPhaseType getAccountPhaseType() {
		return AccountPhaseType.ACTION;
	}

}
