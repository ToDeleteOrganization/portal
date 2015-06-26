package com.evozon.evoportal.myaccount.builder;

import java.util.Date;

import javax.portlet.PortletRequest;

import com.evozon.evoportal.my_account.util.MyAccountConstants;
import com.liferay.compat.portal.util.PortalUtil;

public class CreateInternshipAccountModelHolderBuilder extends CreateAccountModelHolderBuilder {

	public CreateInternshipAccountModelHolderBuilder(PortletRequest request) {
		super(request);
	}

	protected Date getInternshipStartDate() {
		return super.getStartDate();
	}

	protected Date getCIMStartDate() {
		long companyId = PortalUtil.getCompanyId(request);
		return getDefaultUserCustomAttribute(companyId, MyAccountConstants.CIM_START_DATE);
	}

}
