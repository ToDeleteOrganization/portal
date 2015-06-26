package com.evozon.evoportal.myaccount.builder;

import javax.portlet.PortletConfig;
import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

import com.liferay.portal.kernel.struts.StrutsPortletAction;

public interface PhaseParameters<REQ extends PortletRequest, RES extends PortletResponse, SPA extends StrutsPortletAction, PC extends PortletConfig> {

	public REQ getRequest();

	public RES getResponse();

	public SPA getStrutsPortletAction();

	public PC getPortletConfig();

	public AccountPhaseType getAccountPhaseType();
}
