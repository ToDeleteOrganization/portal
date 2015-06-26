package com.evozon.evoportal.myaccount.builder;

import java.util.Date;

import javax.portlet.PortletRequest;

import com.evozon.evoportal.my_account.util.MyAccountConstants;
import com.liferay.compat.portal.util.PortalUtil;

public class CreateCIMAccountModelHolderBuilder extends RequestAccountModelHolderBuilder {

	public CreateCIMAccountModelHolderBuilder(PortletRequest request) {
		super(request);
	}

	protected Date getInternshipStartDate() {
		long companyId = PortalUtil.getCompanyId(request);
		return getDefaultUserCustomAttribute(companyId, MyAccountConstants.INTERNSHIP_START_DATE);
	}

	protected Date getCIMStartDate() {
		return super.getDateFromRequest("hired");
	}

}
