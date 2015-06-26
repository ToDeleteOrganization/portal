package com.evozon.evoportal.myaccount.builder;

import java.util.Date;

import javax.portlet.PortletRequest;

import com.evozon.evoportal.my_account.util.MyAccountConstants;
import com.liferay.compat.portal.util.PortalUtil;

public class CreateScholarshipAccountModelHolderBuilder extends RequestAccountModelHolderBuilder {

	public CreateScholarshipAccountModelHolderBuilder(PortletRequest request) {
		super(request);
	}

	protected Date getCIMStartDate() {
		long companyId = PortalUtil.getCompanyId(request);
		return getDefaultUserCustomAttribute(companyId, MyAccountConstants.CIM_START_DATE);
	}

	protected Date getInternshipStartDate() {
		long companyId = PortalUtil.getCompanyId(request);
		return getDefaultUserCustomAttribute(companyId, MyAccountConstants.INTERNSHIP_START_DATE);
	}

}
