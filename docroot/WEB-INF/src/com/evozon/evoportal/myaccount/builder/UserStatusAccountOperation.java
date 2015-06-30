package com.evozon.evoportal.myaccount.builder;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Set;

import javax.portlet.ActionRequest;

import com.evozon.evoportal.my_account.util.MyAccountConstants;
import com.evozon.evoportal.my_account.wrapper.UserExpandoWrapper;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;

public abstract class UserStatusAccountOperation extends ManagementAccountActionOperation {

	private static Log logger = LogFactoryUtil.getLog(UserStatusAccountOperation.class);

	protected boolean isCancelCommand = false;

	private StatusChangeModel statusChangeModel;

	public UserStatusAccountOperation(ActionPhaseParameters app) {
		super(app);
		init();
	}

	private void init() {
		ActionRequest actionRequest = getActionRequest();
		String command = ParamUtil.getString(actionRequest, Constants.CMD, AccountOperationBuilder.DEFAULT);
		isCancelCommand = MyAccountConstants.CANCEL_DEACTIVATION.equals(command);
		statusChangeModel = new UserStatusChangeBuilder(actionRequest).build();
	}

	public void execute() throws Exception {
		Set<User> selectedUsers = statusChangeModel.getSelectedUsers();
		for (User user : selectedUsers) {
			UserExpandoWrapper userExpando = new UserExpandoWrapper(user);

			if (!isCancelCommand) {
				executeStatusChange(userExpando);

			} else {
				executeStatusCancel(userExpando);

			}
		}
		executeInternalAction();
	}

	protected void executeCustomActionAfter() {
		if (isCancelCommand) {
			String redirectTo = ParamUtil.getString(getActionRequest(), "redirect", StringPool.BLANK);
			sendRedirect(redirectTo);
		}
	}

	protected void sendRedirect(String redirectURL) {
		try {
			getActionResponse().sendRedirect(redirectURL);
		} catch (IOException e) {
			logger.error("Failed to redirect action [" + getClass().getName() + "] to " + redirectURL, e);
		}
	}

	protected abstract void executeStatusCancel(UserExpandoWrapper userExpando);

	protected abstract int getWorkflowCommandStatus();

	protected void adjustFreeDaysSituation(UserExpandoWrapper userExpando, boolean isFutureChange) {
		// to be implemented by subclasses
	}

	protected void executeStatusChange(UserExpandoWrapper userExpando) {
		int command = getWorkflowCommandStatus();
		Calendar today = Calendar.getInstance();
		Calendar statusChangeDate = statusChangeModel.getStatusChangeCalendarForUser(userExpando.getUser());
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
