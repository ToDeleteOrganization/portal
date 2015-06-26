package com.evozon.evoportal.myaccount.builder;

import com.evozon.evoportal.evozonfreedaysallocator.BonusDaysComputer;
import com.evozon.evoportal.my_account.wrapper.UserExpandoWrapper;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

public class DeactivateAccountOperation extends UserStatusAccountOperation {

	public DeactivateAccountOperation(ActionPhaseParameters app) {
		super(app);
	}

	protected void adjustFreeDaysSituation(UserExpandoWrapper userExpando, boolean isFutureChange) {
		int newLegalFreeDays = BonusDaysComputer.getUserLegalFreeDays(userExpando.getUser());
		int oldLegalVacationDays = userExpando.getLegalVacationDays();

		int extractedLegalVacationDays = oldLegalVacationDays - newLegalFreeDays;

		userExpando.setFreeDaysInCurrentYear(userExpando.getFreeDaysInCurrentYear() - extractedLegalVacationDays);
		userExpando.setLegalVacationDays(newLegalFreeDays);
	}

	protected void executeStatusCancel(UserExpandoWrapper userExpando) {
		userExpando.removeCurrentStatusLog();
		adjustFreeDaysSituation(userExpando, false);
	}

	protected int getWorkflowCommandStatus() {
		return WorkflowConstants.STATUS_INACTIVE;
	}

	protected void executeInternalAction() {
		// do nothing
	}
}
