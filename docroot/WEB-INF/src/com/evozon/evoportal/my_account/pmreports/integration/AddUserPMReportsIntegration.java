package com.evozon.evoportal.my_account.pmreports.integration;

import org.springframework.util.MultiValueMap;

import com.evozon.evoportal.my_account.AccountModelHolder;
import com.evozon.evoportal.ws.pmreports.calls.PMReportsPersonCall;
import com.evozon.evoportal.ws.pmreports.model.PmResponseStatus;
import com.liferay.portal.model.User;

public class AddUserPMReportsIntegration extends PMReportsIntegration {

	public AddUserPMReportsIntegration(long companyId, AccountModelHolder holder, User addedUser) {
		super(companyId, addedUser);
		setNewAccountModelHolder(holder);
	}

	public PmResponseStatus executeIntegration() {
		PmResponseStatus pmResponse = null;

		if (hasPMReportsParamatersModified()) {
			MultiValueMap<String, Object> pmParams = getPMReportsParameters();

			PMReportsPersonCall pmPersonCall = new PMReportsPersonCall(companyId);
			pmResponse = pmPersonCall.addUser(pmParams);
		}

		return pmResponse;
	}

	protected boolean hasPMReportsParamatersModified() {
		// when adding always call pm reports services.
		return true;
	}

}
