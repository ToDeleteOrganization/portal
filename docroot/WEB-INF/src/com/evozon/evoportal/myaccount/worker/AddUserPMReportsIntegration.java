package com.evozon.evoportal.myaccount.worker;

import org.springframework.util.MultiValueMap;

import com.evozon.evoportal.ws.pmreports.calls.PMReportsPersonCall;
import com.evozon.evoportal.ws.pmreports.model.PmResponseStatus;

public class AddUserPMReportsIntegration extends PMReportsIntegration {

	public PmResponseStatus executeIntegration(PMReportsPersonCall pmPersonCall, MultiValueMap<String, Object> pmParams) {
		return pmPersonCall.addUser(pmParams);
	}

	protected boolean hasPMReportsParamatersModified() {
		// when adding always call pm reports services.
		return true;
	}

}
