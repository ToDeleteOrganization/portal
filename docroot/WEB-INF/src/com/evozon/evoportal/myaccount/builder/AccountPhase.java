package com.evozon.evoportal.myaccount.builder;

import javax.portlet.PortletConfig;
import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

import com.liferay.portal.kernel.struts.StrutsPortletAction;

public interface AccountPhase<T extends PhaseParameters<? extends PortletRequest, ? extends PortletResponse, ? extends StrutsPortletAction, ? extends PortletConfig>> {

	public void executePhase(T pp);
}
