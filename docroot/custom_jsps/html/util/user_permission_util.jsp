
<%@ page import="java.util.List"%>

<%@ page import="com.liferay.portal.kernel.exception.PortalException"%>
<%@ page import="com.liferay.portal.kernel.exception.SystemException"%>

<%@ page import="com.liferay.portal.model.Group"%>
<%@ page import="com.liferay.portal.model.Role"%>
<%@ page import="com.liferay.portal.model.User"%>
<%@ page import="com.liferay.portal.service.GroupLocalServiceUtil"%>

<%!
private final String ADMINISTRATOR_ROLE = "Administrator";

private final String USER_MANAGEMENT_ROLE = "UserManagement";

private final String HR_DEPARTMENT_GROUP = "HR Department";

private boolean isAdmin(User user) throws SystemException {
	return hasRole(ADMINISTRATOR_ROLE, user.getRoles());
}

private boolean isHR(User user) throws PortalException, SystemException {
	if (isAdmin(user)) {
		return false;
	}

	List<Group> userGroups = GroupLocalServiceUtil.getUserGroups(user.getUserId());
	return isPartOfGroup(HR_DEPARTMENT_GROUP, userGroups);
}

private boolean isUserManagement(User user) throws PortalException, SystemException  {
	return hasRole(USER_MANAGEMENT_ROLE, user.getRoles());
}

private boolean isPartOfGroup(String groupName, List<Group> groups) {
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

private boolean hasRole(String roleName, List<Role> roles) {
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
%>