package com.evozon.evoportal.my_account.command;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;

import com.evozon.evoportal.my_account.util.MyAccountConstants;
import com.evozon.evoportal.my_account.util.MyAccountUtil;
import com.evozon.evoportal.my_account.wrapper.UserExpandoWrapper;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.struts.StrutsPortletAction;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;

public abstract class UserStatusAccountCommand extends AccountActionCommand {

	private static Log logger = LogFactoryUtil.getLog(UserStatusAccountCommand.class);

	protected boolean isCancelCommand = false;

	protected UserStatusAccountCommand(StrutsPortletAction originalStrutsPortletAction, PortletConfig portletConfig, ActionRequest actionRequest, ActionResponse actionResponse) {
		super(originalStrutsPortletAction, portletConfig, actionRequest, actionResponse);
	}

	public void executeCustomAction() throws Exception {
		executeCustomActionBefore();

		for (User user : getSelectedUsers()) {
			UserExpandoWrapper userExpando = new UserExpandoWrapper(user);

			if (!isCancelCommand) {
				executeStatusChange(userExpando);

			} else {
				executeStatusCancel(userExpando);

			}
		}
		executeCustomActionAfter();
	}

	protected void executeCustomActionAfter() {
		if (isCancelCommand) {
			String redirectTo = ParamUtil.getString(actionRequest, "redirect", StringPool.BLANK);
			sendRedirect(redirectTo);
		}
	}

	protected abstract void executeStatusCancel(UserExpandoWrapper userExpando);

	protected abstract int getWorkflowCommandStatus();

	protected void adjustFreeDaysSituation(UserExpandoWrapper userExpando, boolean isFutureChange) {
		// to be implemented by subclasses
	}

	protected List<User> getSelectedUsers() {
		String idsStr = ParamUtil.getString(actionRequest, MyAccountConstants.DELETED_IDS);
		long[] usersId = StringUtil.split(idsStr, 0L);

		List<User> selectedUsers = new ArrayList<User>();
		for (long userId : usersId) {
			try {
				User user = UserLocalServiceUtil.getUser(userId);
				selectedUsers.add(user);
			} catch (Exception e) {
				logger.error(e);
			}
		}

		return selectedUsers;
	}

	protected Calendar getUserStatusChangeDate(long userId) {
		Calendar statusChange = Calendar.getInstance();

		statusChange.set(Calendar.DAY_OF_MONTH, ParamUtil.getInteger(actionRequest, "day_" + userId));
		statusChange.set(Calendar.MONTH, ParamUtil.getInteger(actionRequest, "month_" + userId));
		statusChange.set(Calendar.YEAR, ParamUtil.getInteger(actionRequest, "year_" + userId));

		return MyAccountUtil.resetToMidnight(statusChange.getTime());
	}

	protected void executeStatusChange(UserExpandoWrapper userExpando) {
		int command = getWorkflowCommandStatus();
		Calendar today = Calendar.getInstance();
		Calendar statusChangeDate = getUserStatusChangeDate(userExpando.getUserId());
		boolean isFutureChange = (statusChangeDate.compareTo(today) > 0);

		try {
			if (isFutureChange) {
				String dateStr = new SimpleDateFormat("dd/MM/yyyy").format(statusChangeDate.getTime());
				logger.info("User " + userExpando.getUser().getFullName() + " status was set to change in " + dateStr + " using: " + getClass().getCanonicalName());

			} else {
				User updatedUser = UserLocalServiceUtil.updateStatus(userExpando.getUserId(), command);
				userExpando = new UserExpandoWrapper(updatedUser);
				logger.info("The user: " + userExpando.getUser().getFullName() + " status changed using: " + getClass().getName());
			}

			userExpando.addNewStatusLog(command, today.getTimeInMillis(), statusChangeDate.getTimeInMillis());
			adjustFreeDaysSituation(userExpando, isFutureChange);

		} catch (PortalException e) {
			logger.error(e);
		} catch (SystemException e) {
			logger.error(e);
		}
	}

}
