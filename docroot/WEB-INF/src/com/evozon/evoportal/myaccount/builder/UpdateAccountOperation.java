package com.evozon.evoportal.myaccount.builder;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

public class UpdateAccountOperation extends AbstractAccountActionOperation {

	private static final Log logger = LogFactoryUtil.getLog(AddAccountOperation.class);

	public UpdateAccountOperation(ActionPhaseParameters app) {
		super(app);
	}

}
