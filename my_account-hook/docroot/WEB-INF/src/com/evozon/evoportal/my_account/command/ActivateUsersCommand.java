package com.evozon.evoportal.my_account.command;

import java.util.Date;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;

import com.evozon.evoportal.evozonfreedaysallocator.BonusDaysComputer;
import com.evozon.evoportal.my_account.model.UserAccountOperation;
import com.evozon.evoportal.my_account.wrapper.UserExpandoWrapper;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.struts.StrutsPortletAction;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

public class ActivateUsersCommand extends UserStatusAccountCommand {

	 private static Log logger = LogFactoryUtil.getLog(ActivateUsersCommand.class);

	public ActivateUsersCommand(StrutsPortletAction originalStrutsPortletAction, PortletConfig portletConfig, ActionRequest actionRequest, ActionResponse actionResponse,
			boolean isCancelActivation) {
		super(originalStrutsPortletAction, portletConfig, actionRequest, actionResponse);
		commandType = UserAccountOperation.MANAGEMENT_UPDATE;
		this.isCancelCommand = isCancelActivation;
	}

	protected void adjustFreeDaysSituation(UserExpandoWrapper userExpando, boolean isFutureChange) {

		if (!isFutureChange) {
			// if this is set to be change in the past or present
			Date statusChangeDate = userExpando.getCurrentStatusChangeDate();

			userExpando.setDateHired(statusChangeDate);
			userExpando.setCIMStartDate(statusChangeDate);

			// this must be called after setDateHired, or use getUserLegalFreeDaysInDate()
			int newLegalFreeDays = BonusDaysComputer.getUserLegalFreeDays(userExpando.getUser());
			userExpando.setFreeDaysInCurrentYear(newLegalFreeDays);
			userExpando.setFreeDaysFromLastYear(0);
			userExpando.setLegalVacationDays(0);
			userExpando.setRemainingFreeDaysFromLastYear(0);

			userExpando.setExtractedFreeDaysFromCurrentYear(0);
			userExpando.setExtractedFreeDaysFromLastYear(0);

			userExpando.setExtraDays(0);
			logger.info(userExpando.getUser().getFullName() + " was reactivated with date " + statusChangeDate);
		}

	}

	protected int getWorkflowCommandStatus() {
		return WorkflowConstants.STATUS_APPROVED;
	}

	protected void executeStatusCancel(UserExpandoWrapper userExpando) {
		userExpando.removeCurrentStatusLog();
	}
}
