package com.evozon.evoportal.myaccount.builder;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

public class AccountNullPhase implements AccountPhase {

	private static Log logger = LogFactoryUtil.getLog(AccountNullPhase.class);

	public void executePhase(PhaseParameters pp) {
		
		if (logger.isDebugEnabled()) {
			logger.debug("This phase isn't treated: ["  + pp.getAccountPhaseType() + "]");
		}
		
	}

}
