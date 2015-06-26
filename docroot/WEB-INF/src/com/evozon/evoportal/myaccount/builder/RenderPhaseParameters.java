package com.evozon.evoportal.myaccount.builder;

import javax.portlet.PortletConfig;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import com.liferay.portal.kernel.struts.StrutsPortletAction;

public class RenderPhaseParameters implements PhaseParameters<RenderRequest, RenderResponse, StrutsPortletAction, PortletConfig> {

	private final RenderRequest request;

	private final RenderResponse response;

	private final StrutsPortletAction strutsPortletAction;

	private final PortletConfig portletConfig;

	public RenderPhaseParameters(RenderRequest req, RenderResponse res, StrutsPortletAction spa, PortletConfig pc) {
		this.request = req;
		this.response = res;
		this.strutsPortletAction = spa;
		this.portletConfig = pc;
	}

	public RenderRequest getRequest() {
		return request;
	}

	public RenderResponse getResponse() {
		return response;
	}

	public StrutsPortletAction getStrutsPortletAction() {
		return strutsPortletAction;
	}

	public PortletConfig getPortletConfig() {
		return portletConfig;
	}

	public AccountPhaseType getAccountPhaseType() {
		return AccountPhaseType.RENDER;
	}

}
