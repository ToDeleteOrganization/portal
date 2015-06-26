package com.evozon.evoportal.my_account.command;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;

import com.evozon.evoportal.evozonfreedaysallocator.BonusDaysComputer;
import com.evozon.evoportal.evozonprojects.model.ProjectGroup;
import com.evozon.evoportal.evozonprojects.service.ProjectGroupLocalServiceUtil;
import com.evozon.evoportal.my_account.CustomActionRequest;
import com.evozon.evoportal.my_account.model.UserAccountOperation;
import com.evozon.evoportal.my_account.wrapper.UserExpandoWrapper;
import com.liferay.portal.UserActiveException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.struts.StrutsPortletAction;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.model.User;

public class DeactivateUsersCommand extends UserStatusAccountCommand {

	private static Log logger = LogFactoryUtil.getLog(DeactivateUsersCommand.class);

	private static final String CANNOT_DEACTIVATE_PROJECT_MEMBERS_KEY = "cannot-delete-or-deactivate-a-project-member";

	public DeactivateUsersCommand(StrutsPortletAction originalStrutsPortletAction, PortletConfig portletConfig, ActionRequest actionRequest, ActionResponse actionResponse,
			boolean isCancelDeactivation) {
		super(originalStrutsPortletAction, portletConfig, actionRequest, actionResponse);
		commandType = UserAccountOperation.MANAGEMENT_UPDATE;
		this.isCancelCommand = isCancelDeactivation;
	}

	public void executeCustomActionBefore() {
		checkForProjectGroupUsersDeactivation();
	}

	private boolean checkForProjectGroupUsersDeactivation() {
		boolean isProjectMembersDeactivated = false;
		Map<Long, String> projectUsers = new HashMap<Long, String>();

		long currentUserId = currentUser.getUserId();
		for (User user : getSelectedUsers()) {
			long userId = user.getUserId();
			if (currentUserId != userId) {
				List<ProjectGroup> userProjects = ProjectGroupLocalServiceUtil.findProjectsByUser(userId);
				if (!userProjects.isEmpty()) {
					logger.warn("The user with the id " + userId + " is part of prjects: " + userProjects + ", and cannot be deactivated.");
					projectUsers.put(userId, user.getFullName());
					continue;
				}
			} else {
				logger.warn("The user cannot deactivate himself.");
			}
		}

		if (!projectUsers.isEmpty()) {
			isProjectMembersDeactivated = true;
			logger.debug("Project members can't be deactivated: " + projectUsers.values());

			String errMsg = getBundleMessage(CANNOT_DEACTIVATE_PROJECT_MEMBERS_KEY) + projectUsers.values();
			SessionErrors.add(actionRequest, UserActiveException.class, errMsg);
			actionRequest = new CustomActionRequest(actionRequest, Constants.DEACTIVATE, projectUsers.keySet());
		}

		return isProjectMembersDeactivated;
	}

	protected void adjustFreeDaysSituation(UserExpandoWrapper userExpando, boolean isFutureChange) {
		int newLegalFreeDays = BonusDaysComputer.getUserLegalFreeDays(userExpando.getUser());
		int oldLegalVacationDays = userExpando.getLegalVacationDays();

		int extractedLegalVacationDays = oldLegalVacationDays - newLegalFreeDays;

		userExpando.setFreeDaysInCurrentYear(userExpando.getFreeDaysInCurrentYear() - extractedLegalVacationDays);
		userExpando.setLegalVacationDays(newLegalFreeDays);
	}

	protected int getWorkflowCommandStatus() {
		return WorkflowConstants.STATUS_INACTIVE;
	}

	protected void executeStatusCancel(UserExpandoWrapper userExpando) {
		userExpando.removeCurrentStatusLog();
		adjustFreeDaysSituation(userExpando, false);
	}

}
