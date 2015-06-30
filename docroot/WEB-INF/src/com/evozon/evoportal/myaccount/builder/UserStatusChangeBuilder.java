package com.evozon.evoportal.myaccount.builder;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.portlet.PortletRequest;

import com.evozon.evoportal.my_account.util.MyAccountConstants;
import com.evozon.evoportal.my_account.util.MyAccountUtil;
import com.evozon.evoportal.myaccount.builder.validators.UserProjectValidator;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;

public class UserStatusChangeBuilder implements MyAccountBuilder<StatusChangeModel> {

	private static final Log logger = LogFactoryUtil.getLog(UserProjectValidator.class);

	private final PortletRequest request;

	public UserStatusChangeBuilder(final PortletRequest request) {
		this.request = request;
	}

	public Calendar buildStatusChangeDate(long userId) {
		Calendar statusChange = Calendar.getInstance();

		statusChange.set(Calendar.DAY_OF_MONTH, ParamUtil.getInteger(request, "day_" + userId));
		statusChange.set(Calendar.MONTH, ParamUtil.getInteger(request, "month_" + userId));
		statusChange.set(Calendar.YEAR, ParamUtil.getInteger(request, "year_" + userId));

		return MyAccountUtil.resetToMidnight(statusChange.getTime());
	}

	private List<User> getSelectedUsers() {
		String idsStr = ParamUtil.getString(request, MyAccountConstants.DELETED_IDS);
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

	public StatusChangeModel build() {
		StatusChangeModel statusChangeModel = new StatusChangeModel();

		Map<User, Calendar> userStatuses = new HashMap<User, Calendar>();
		List<User> selectedUsers = getSelectedUsers();
		for (User selUser : selectedUsers) {
			userStatuses.put(selUser, this.buildStatusChangeDate(selUser.getUserId()));
		}

		statusChangeModel.setSelectedUsers(userStatuses);
		return statusChangeModel;
	}
}
