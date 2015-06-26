<%--
/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */
--%>

<%@ include file="/html/portlet/users_admin/init.jsp" %>
<%@ include file="../../util/hooks_util.jsp"%>
<%@page import="com.liferay.portal.kernel.servlet.SessionErrors"%>

<%
String CIM_USER_TYPE = "regular";
String INTERNSHIP_USER_TYPE = "internship";
String SCHOLARSHIP_USER_TYPE = "scholarship";

themeDisplay.setIncludeServiceJs(true);

String redirect = ParamUtil.getString(request, "redirect");
String backURL = ParamUtil.getString(request, "backURL", redirect);
String historyKey = ParamUtil.getString(request, "historyKey");

boolean hasContractDetailsErrors = SessionErrors.contains(renderRequest, "hasContractDetailsErrors");
boolean hasPersonalDetailsErrors = SessionErrors.contains(renderRequest, "hasPersonalDetailsErrors");
boolean hasFamilyErrors = SessionErrors.contains(renderRequest, "hasFamilyErrors");
boolean hasSitesErrors = SessionErrors.contains(renderRequest, "hasSitesErrors");
boolean hasFreeDaysErrors = SessionErrors.contains(renderRequest, "invalid-date-hired-error-key");

User selUser = PortalUtil.getSelectedUser(request);

Contact selContact = null;
if (selUser != null) {
	selContact = selUser.getContact();
}

PasswordPolicy passwordPolicy = null;
if (selUser == null) {
	passwordPolicy = PasswordPolicyLocalServiceUtil.getDefaultPasswordPolicy(company.getCompanyId());

} else {
	passwordPolicy = selUser.getPasswordPolicy();
}

String groupIds = ParamUtil.getString(request, "groupsSearchContainerPrimaryKeys");

List<Group> groups = Collections.emptyList();

if (Validator.isNotNull(groupIds)) {
	long[] groupIdsArray = StringUtil.split(groupIds, 0L);

	groups = GroupLocalServiceUtil.getGroups(groupIdsArray);
}
else if (selUser != null) {
	groups = selUser.getGroups();

	if (filterManageableGroups) {
		groups = UsersAdminUtil.filterGroups(permissionChecker, groups);
	}
} else {
	String newUserGroups = (String)request.getAttribute("newUserGroups");
	long[] newUserGroupIds = StringUtil.split(newUserGroups, 0L);

	groups = GroupLocalServiceUtil.getGroups(newUserGroupIds);
}

String organizationIds = ParamUtil.getString(request, "organizationsSearchContainerPrimaryKeys");

List<Organization> organizations = Collections.emptyList();

if (Validator.isNotNull(organizationIds)) {
	long[] organizationIdsArray = StringUtil.split(organizationIds, 0L);

	organizations = OrganizationLocalServiceUtil.getOrganizations(organizationIdsArray);
}
else {
	if (selUser != null) {
		organizations = selUser.getOrganizations();
	}

	if (filterManageableOrganizations) {
		organizations = UsersAdminUtil.filterOrganizations(permissionChecker, organizations);
	}
}

String roleIds = ParamUtil.getString(request, "rolesSearchContainerPrimaryKeys");

List<Role> roles = Collections.emptyList();

if (Validator.isNotNull(roleIds)) {
	long[] roleIdsArray = StringUtil.split(roleIds, 0L);

	roles = RoleLocalServiceUtil.getRoles(roleIdsArray);
}
else if (selUser != null) {
	roles = selUser.getRoles();

	if (filterManageableRoles) {
		roles = UsersAdminUtil.filterRoles(permissionChecker, roles);
	}
} else {
	String newUserRoles = (String)request.getAttribute("newUserRoles");
	long[] newUserRoleIds = StringUtil.split(newUserRoles, 0L);

	roles = RoleLocalServiceUtil.getRoles(newUserRoleIds);
}

List<UserGroupRole> userGroupRoles = UsersAdminUtil.getUserGroupRoles(renderRequest);

List<UserGroupRole> communityRoles = new ArrayList<UserGroupRole>();
List<UserGroupRole> organizationRoles = new ArrayList<UserGroupRole>();

if (userGroupRoles.isEmpty() && (selUser != null)) {
	userGroupRoles = UserGroupRoleLocalServiceUtil.getUserGroupRoles(selUser.getUserId());

	if (filterManageableUserGroupRoles) {
		userGroupRoles = UsersAdminUtil.filterUserGroupRoles(permissionChecker, userGroupRoles);
	}
}

for (UserGroupRole userGroupRole : userGroupRoles) {
	int roleType = userGroupRole.getRole().getType();

	if (roleType == RoleConstants.TYPE_ORGANIZATION) {
		organizationRoles.add(userGroupRole);
	}
	else if (roleType == RoleConstants.TYPE_SITE) {
		communityRoles.add(userGroupRole);
	}
}

String userGroupIds = ParamUtil.getString(request, "userGroupsSearchContainerPrimaryKeys");

List<UserGroup> userGroups = Collections.emptyList();

if (Validator.isNotNull(userGroupIds)) {
	long[] userGroupIdsArray = StringUtil.split(userGroupIds, 0L);

	userGroups = UserGroupLocalServiceUtil.getUserGroups(userGroupIdsArray);
}
else if (selUser != null) {
	userGroups = selUser.getUserGroups();

	if (filterManageableUserGroups) {
		userGroups = UsersAdminUtil.filterUserGroups(permissionChecker, userGroups);
	}
}

List<Group> allGroups = new ArrayList<Group>();

allGroups.addAll(groups);
allGroups.addAll(GroupLocalServiceUtil.getOrganizationsGroups(organizations));
allGroups.addAll(GroupLocalServiceUtil.getOrganizationsRelatedGroups(organizations));
allGroups.addAll(GroupLocalServiceUtil.getUserGroupsGroups(userGroups));
allGroups.addAll(GroupLocalServiceUtil.getUserGroupsRelatedGroups(userGroups));

String[] mainSections = PropsValues.USERS_FORM_ADD_MAIN;
String[] identificationSections = PropsValues.USERS_FORM_ADD_IDENTIFICATION;
String[] miscellaneousSections = PropsValues.USERS_FORM_ADD_MISCELLANEOUS;

if (selUser != null) {
	if (portletName.equals(PortletKeys.MY_ACCOUNT)) {
		mainSections = PropsValues.USERS_FORM_MY_ACCOUNT_MAIN;
		identificationSections = PropsValues.USERS_FORM_MY_ACCOUNT_IDENTIFICATION;
		miscellaneousSections = PropsValues.USERS_FORM_MY_ACCOUNT_MISCELLANEOUS;
	} else {
		mainSections = PropsValues.USERS_FORM_UPDATE_MAIN;
		identificationSections = PropsValues.USERS_FORM_UPDATE_IDENTIFICATION;
		miscellaneousSections = PropsValues.USERS_FORM_UPDATE_MISCELLANEOUS;
	}
} else {
	mainSections = PropsValues.USERS_FORM_ADD_MAIN;
	identificationSections = PropsValues.USERS_FORM_ADD_IDENTIFICATION;
	miscellaneousSections = PropsValues.USERS_FORM_ADD_MISCELLANEOUS;
}

String[][] categorySections = {mainSections, identificationSections, miscellaneousSections};

String userType = ParamUtil.getString(request, "user_type", CIM_USER_TYPE);

String headerTitle = "";
if (Validator.isNotNull(selUser)) {
	headerTitle = selUser.getFullName();

} else {
	if (userType.equals(INTERNSHIP_USER_TYPE)) {
		headerTitle = "new-internship-user";

	} else if (userType.equals(SCHOLARSHIP_USER_TYPE)) {
		headerTitle = "new-scholarship-user";

	} else {
		headerTitle = "new-cim-user";

	}
}
%>

<liferay-ui:error exception="<%= CompanyMaxUsersException.class %>" message="unable-to-create-user-account-because-the-maximum-number-of-users-has-been-reached" />

<c:if test="<%= !portletName.equals(PortletKeys.MY_ACCOUNT) %>">
	<liferay-util:include page="/html/portlet/users_admin/toolbar.jsp">
		<liferay-util:param name="toolbarItem" value='<%= (selUser == null) ? "add" : "view" %>' />
	</liferay-util:include>
</c:if>

<liferay-ui:header
	backURL="<%= backURL %>"
	localizeTitle="<%= (selUser == null) %>"
	title='<%= headerTitle %>'
/>

<%
String taglibOnSubmit = "event.preventDefault(); " + renderResponse.getNamespace() + "saveUser('" + ((selUser == null) ? Constants.ADD : Constants.UPDATE) + "');";
%>

<aui:form method="post" name="fm" onSubmit="<%= taglibOnSubmit %>">
	<aui:input name="<%= Constants.CMD %>" type="hidden" />
	<aui:input name="redirect" type="hidden" />
	<aui:input name="backURL" type="hidden" value="<%= backURL %>" />
	<aui:input name="p_u_i_d" type="hidden" value="<%= (selUser != null) ? selUser.getUserId() : 0 %>" />
	<aui:input name="user_type" type="hidden" value="<%= userType %>" />

	<%
	request.setAttribute("user.selUser", selUser);
	request.setAttribute("user.selContact", selContact);
	request.setAttribute("user.passwordPolicy", passwordPolicy);
	request.setAttribute("user.groups", groups);
	request.setAttribute("user.organizations", organizations);
	request.setAttribute("user.roles", roles);
	request.setAttribute("user.communityRoles", communityRoles);
	request.setAttribute("user.organizationRoles", organizationRoles);
	request.setAttribute("user.userGroups", userGroups);
	request.setAttribute("user.allGroups", allGroups);
	request.setAttribute("user.type", userType);

	request.setAttribute("addresses.className", Contact.class.getName());
	request.setAttribute("emailAddresses.className", Contact.class.getName());
	request.setAttribute("phones.className", Contact.class.getName());
	request.setAttribute("websites.className", Contact.class.getName());

	if (selContact != null) {
		request.setAttribute("addresses.classPK", selContact.getContactId());
		request.setAttribute("emailAddresses.classPK", selContact.getContactId());
		request.setAttribute("phones.classPK", selContact.getContactId());
		request.setAttribute("websites.classPK", selContact.getContactId());
	}
	else {
		request.setAttribute("addresses.classPK", 0L);
		request.setAttribute("emailAddresses.classPK", 0L);
		request.setAttribute("phones.classPK", 0L);
		request.setAttribute("websites.classPK", 0L);
	}
	%>

	<liferay-util:buffer var="htmlTop">
		<c:if test="<%= selUser != null %>">
			<div class="user-info">
				<div class="float-container">
					<img alt="<%= HtmlUtil.escape(selUser.getFullName()) %>" class="user-logo" src="<%= selUser.getPortraitURL(themeDisplay) %>" />

					<span class="user-name"><%= HtmlUtil.escape(selUser.getFullName()) %></span>
				</div>
			</div>
		</c:if>
	</liferay-util:buffer>

	<liferay-util:buffer var="htmlBottom">
		<c:if test="<%= (selUser != null) && (passwordPolicy != null) && selUser.getLockout() %>">
			<aui:button-row>
				<div class="portlet-msg-alert"><liferay-ui:message key="this-user-account-has-been-locked-due-to-excessive-failed-login-attempts" /></div>

				<%
				String taglibOnClick = renderResponse.getNamespace() + "saveUser('unlock');";
				%>

				<aui:button onClick="<%= taglibOnClick %>" value="unlock" />
			</aui:button-row>
		</c:if>
	</liferay-util:buffer>

	<liferay-ui:form-navigator
		backURL="<%= backURL %>"
		categoryNames="<%= _CATEGORY_NAMES %>"
		categorySections="<%= categorySections %>"
		htmlBottom="<%= htmlBottom %>"
		htmlTop="<%= htmlTop %>"
		jspPath="/html/portlet/users_admin/user/"
	/>
</aui:form>
<%
if (selUser != null) {
	PortalUtil.setPageSubtitle(selUser.getFullName(), request);
}
%>

<aui:script>
	AUI().ready('event', 'node', 'aui-loading-mask', 'aui-tabs', function(A) {
		MyAccountPageManager.showFirstErrorSection(A);
		MyAccountPageManager.addSectionErrors(A);
	});

	function <portlet:namespace />createURL(href, value, onclick) {
		return '<a href="' + href + '"' + (onclick ? ' onclick="' + onclick + '" ' : '') + '>' + value + '</a>';
	};

	function <portlet:namespace />saveUser(cmd) {
		document.<portlet:namespace />fm.<portlet:namespace /><%= Constants.CMD %>.value = cmd;

		var redirect = "<portlet:renderURL><portlet:param name="struts_action" value="/users_admin/edit_user" /><portlet:param name="backURL" value="<%= backURL %>"></portlet:param></portlet:renderURL>";

		redirect += Liferay.Util.getHistoryParam('<portlet:namespace />');

		document.<portlet:namespace />fm.<portlet:namespace />redirect.value = redirect;

		var url = "<portlet:actionURL><portlet:param name="struts_action" value="/my_account/edit_user" /></portlet:actionURL>";

		submitForm(document.<portlet:namespace />fm, url);
	}

	function toggleVisibility(eventElement, toggledElementId) {
		var toggledElement = AUI().one("#" + toggledElementId);

		if(eventElement.checked) {
			toggledElement.show();

		} else {
			toggledElement.hide();

		}
	}

	var MyAccountPageManager = {

		showFirstErrorSection: function(A) {
			var my = this;
			if (<%= hasPersonalDetailsErrors %>) {
				my.showSelection('details', A);
				return;
			}

			if (<%= hasContractDetailsErrors %>) {
				my.showSelection('contract_details', A);
				return;
			}

			if (<%= hasSitesErrors %>) {
				my.showSelection('sites_and_roles', A);
				return;
			}

			if (<%= hasFamilyErrors %>) {
				my.showSelection('family', A);
				return;
			}

			if (<%= hasFreeDaysErrors %>) {
				my.showSelection('free_days_history', A);
				return;
			}

		},

		addSectionErrors: function(A) {
			var my = this;
			if (<%= hasPersonalDetailsErrors %>) {
				my.addAnchorError('details', A);
			}

			if (<%= hasContractDetailsErrors %>) {
				my.addAnchorError('contract_details', A);
			}

			if (<%= hasSitesErrors %>) {
				my.addAnchorError('sites_and_roles', A);
			}

			if (<%= hasFamilyErrors %>) {
				my.addAnchorError('family', A);
			}

			if (<%= hasFreeDaysErrors %>) {
				my.addAnchorError('free_days_history', A);
			}
		},

		addAnchorError: function(anchorName, A) {
			var anchor = A.one("a[id$=" + anchorName + "Link]");

			if (anchor) {
				var ali = anchor.ancestor("li");
				ali.addClass("section-error");
			}
		},

		showSelection: function(sectionName, A) {
			this.deselectCurrentSelectedAnchor(A);

			// add the selected class to the family section
			A.one("div[id$=" + sectionName + "]").replaceClass('aui-helper-hidden-accessible', 'selected');
			A.one("a[id$=" + sectionName + "Link]").get('parentNode').addClass('selected');

			location.href = '#_LFR_FN__125_' + sectionName;
		},

		deselectCurrentSelectedAnchor: function(A) {
			var currentSelected = A.one("div.form-section.selected");
			if (currentSelected) {
				currentSelected.replaceClass('selected', 'aui-helper-hidden-accessible');
			}
				
			var selectedLink = A.one("li.selected");
			if (selectedLink) {
				selectedLink.removeClass('selected');
				selectedLink.removeClass('section-error');
			}
		}
	};

	<c:if test="<%= windowState.equals(WindowState.MAXIMIZED) %>">
		Liferay.Util.focusFormField(document.<portlet:namespace />fm.<portlet:namespace />screenName);
	</c:if>
</aui:script>

<%
if (selUser != null) {
	if (!portletName.equals(PortletKeys.MY_ACCOUNT)) {
		PortalUtil.addPortletBreadcrumbEntry(request, selUser.getFullName(), null);
	}

	PortalUtil.addPortletBreadcrumbEntry(request, LanguageUtil.get(pageContext, "edit"), currentURL);
}
else {
	PortalUtil.addPortletBreadcrumbEntry(request, LanguageUtil.get(pageContext, "add-user"), currentURL);
}
%>

<%!
private static String[] _CATEGORY_NAMES = {"user-information", "identification", "miscellaneous"};
%>