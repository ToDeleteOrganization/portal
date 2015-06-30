package com.evozon.evoportal.myaccount.builder;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.liferay.portal.model.User;

public class StatusChangeModel {

	private final Map<User, Calendar> statusChangeDate = new HashMap<User, Calendar>();

	public Calendar getStatusChangeCalendarForUser(final User u) {
		Calendar cal = null;
		if (statusChangeDate.containsKey(u)) {
			cal = statusChangeDate.get(u);
		}
		return cal;
	}

	public Set<User> getSelectedUsers() {
		return statusChangeDate.keySet();
	}

	public void setSelectedUsers(Map<User, Calendar> selectedUsers) {
		this.statusChangeDate.putAll(selectedUsers);
	}

}
