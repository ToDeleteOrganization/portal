package com.evozon.evoportal.my_account.util;

import java.util.ArrayList;
import java.util.List;

import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.User;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;

public class EvoportalUserUtil {

	private static final String ROLE_ADMIN = "Administrator";

	private static final String ROLE_ACCOUNTING = "Accounting";

	private static final String HR_DEPARTMENT_GROUP = "HR Department";

	private static final String ROLE_USER_MANAGEMENT = "UserManagement";

	private static final String DEP_HR = "HR Department";

	public static boolean isHR(long userId) throws PortalException, SystemException {
		boolean isHR = false;

		if (userId != 0) {
			User user = getUser(userId);
			if ((user == null) || isAdmin(user)) {
				return false;
			}
			List<Group> userGroups = GroupLocalServiceUtil.getUserGroups(userId);
			isHR = isPartOfGroup(HR_DEPARTMENT_GROUP, userGroups);
		}

		return isHR;
	}

	public static boolean isAdmin(long userId) throws PortalException, SystemException {
		return (userId != 0) ? isAdmin(getUser(userId)) : false;
	}

	public static boolean isAdmin(User user) throws PortalException, SystemException {
		return hasRole(ROLE_ADMIN, user.getRoles());
	}

	public static boolean isUserManagement(User user) throws PortalException, SystemException {
		return hasRole(ROLE_USER_MANAGEMENT, user.getRoles());
	}

	private static boolean isAccounting(User user) throws PortalException, SystemException {
		for (Role role : user.getRoles()) {
			if (role.getName().equalsIgnoreCase(ROLE_ACCOUNTING)) {
				return true;
			}
		}

		return false;
	}

	public static List<User> getAccountingRegularUsers() throws SystemException, PortalException {
		List<User> result = new ArrayList<User>();
		for (User accounting : UserLocalServiceUtil.getUsers(QueryUtil.ALL_POS, QueryUtil.ALL_POS)) {
			if (accounting.isActive() && isAccounting(accounting) && !isAdmin(accounting)) {
				result.add(accounting);
			}
		}
		return result;
	}

	public static List<User> getFamilyMemberResponsibles() throws SystemException, PortalException {
		List<User> result = new ArrayList<User>();
		for (User accounting : UserLocalServiceUtil.getUsers(QueryUtil.ALL_POS, QueryUtil.ALL_POS)) {
			if (accounting.isActive() && isFamilyMemberResponsible(accounting)) {
				result.add(accounting);
			}
		}
		return result;
	}

	private static boolean isFamilyMemberResponsible(User accounting) throws SystemException {
		for (Role role : accounting.getRoles()) {
			if (role.getName().equalsIgnoreCase("Family Member Responsibles")) {
				return true;
			}
		}
		return false;
	}

	public static List<User> getHRRegularUsers() throws SystemException, PortalException {
		List<User> result = new ArrayList<User>();

		long hrGroupId = 0;
		for (Group group : GroupLocalServiceUtil.getGroups(QueryUtil.ALL_POS, QueryUtil.ALL_POS)) {
			if (group.getName().equalsIgnoreCase(DEP_HR)) {
				hrGroupId = group.getGroupId();
			}
		}

		for (User user : UserLocalServiceUtil.getGroupUsers(hrGroupId)) { // get
																			// all
																			// HR
																			// Users
			if (user.isActive() && !isAdmin(user)) {
				result.add(user);
			}
		}
		return result;
	}

	private static User getUser(long userId) throws PortalException, SystemException {
		return (userId != 0) ? UserLocalServiceUtil.getUser(userId) : null;
	}

	private static boolean isPartOfGroup(String groupName, List<Group> groups) {
		boolean isPartOfGroup = false;

		for (Group userGroup : groups) {
			String userGroupName = userGroup.getName();
			if (groupName.equalsIgnoreCase(userGroupName)) {
				isPartOfGroup = true;
				break;
			}
		}

		return isPartOfGroup;
	}

	private static boolean hasRole(String roleName, List<Role> roles) {
		boolean hasRole = false;

		for (Role userRole : roles) {
			String userRoleName = userRole.getName();
			if (roleName.equalsIgnoreCase(userRoleName)) {
				hasRole = true;
				break;
			}
		}

		return hasRole;
	}
}
