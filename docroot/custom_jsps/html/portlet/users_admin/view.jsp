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

<%@page import="com.liferay.portal.UserActiveException" %>

<%
String viewUsersRedirect = ParamUtil.getString(request, "viewUsersRedirect");
String backURL = ParamUtil.getString(request, "backURL", viewUsersRedirect);

String usersListView = ParamUtil.get(request, "usersListView", UserConstants.LIST_VIEW_TREE);

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("struts_action", "/users_admin/view_users");
portletURL.setParameter("usersListView", usersListView);

if (Validator.isNotNull(viewUsersRedirect)) {
	portletURL.setParameter("viewUsersRedirect", viewUsersRedirect);
}

pageContext.setAttribute("portletURL", portletURL);

String portletURLString = portletURL.toString();

Object deactUser = SessionErrors.get(renderRequest, UserActiveException.class);

String userDeactivationErrMsg = (deactUser != null) ? deactUser.toString() : "";
%>

<liferay-ui:error exception="<%= RequiredOrganizationException.class %>" message="you-cannot-delete-organizations-that-have-suborganizations-or-users" />
<liferay-ui:error exception="<%= RequiredUserException.class %>" message="you-cannot-delete-or-deactivate-yourself" />
<liferay-ui:error exception="<%= UserActiveException.class %>" message="<%= userDeactivationErrMsg %>" />

<aui:form action="<%= portletURLString %>" method="get" name="fm">
	<liferay-portlet:renderURLParams varImpl="portletURL" />
	<aui:input name="<%= Constants.CMD %>" type="hidden" />
	<aui:input name="redirect" type="hidden" value="<%= portletURLString %>" />

	<%
	long organizationGroupId = 0;
	%>

	<c:if test="<%= portletName.equals(PortletKeys.USERS_ADMIN) %>">
		<liferay-util:include page="/html/portlet/users_admin/toolbar.jsp">
			<liferay-util:param name="toolbarItem" value="view-all" />
		</liferay-util:include>

		<c:if test="<%= usersListView.equals(UserConstants.LIST_VIEW_FLAT_ORGANIZATIONS) || usersListView.equals(UserConstants.LIST_VIEW_FLAT_USERS) %>">
			<portlet:renderURL var="headerBackURL">
				<portlet:param name="struts_action" value="/users_admin/view_users" />
			</portlet:renderURL>

			<liferay-ui:header
				backLabel="users-and-organizations-home"
				backURL="<%= headerBackURL.toString() %>"
				title='<%= usersListView.equals(UserConstants.LIST_VIEW_FLAT_ORGANIZATIONS) ? "organizations" : "users" %>'
			/>
		</c:if>
	</c:if>

	<c:choose>
		<c:when test="<%= usersListView.equals(UserConstants.LIST_VIEW_FLAT_ORGANIZATIONS) %>">
			<%@ include file="/html/portlet/users_admin/view_flat_organizations.jspf" %>
		</c:when>
		<c:when test="<%= usersListView.equals(UserConstants.LIST_VIEW_FLAT_USERS) %>">

			<%
			boolean organizationContextView = false;
			%>

			<%@ include file="/html/portlet/users_admin/view_flat_users.jspf" %>
		</c:when>
		<c:otherwise>
			<%@ include file="/html/portlet/users_admin/view_tree.jspf" %>
		</c:otherwise>
	</c:choose>
</aui:form>

<aui:script>
	function <portlet:namespace />deleteOrganization(organizationId) {
		<portlet:namespace />doDeleteOrganization('<%= Organization.class.getName() %>', organizationId);
	}

	function <portlet:namespace />doDeleteOrganization(className, id) {
		var ids = id;

		var status = <%= WorkflowConstants.STATUS_INACTIVE %>;

		<portlet:namespace />getUsersCount(
			className, ids, status,
			function(event, id, obj) {
				var responseData = this.get('responseData');
				var count = parseInt(responseData);

				if (count > 0) {
					status = <%= WorkflowConstants.STATUS_APPROVED %>

					<portlet:namespace />getUsersCount(
						className, ids, status,
						function(event, id, obj) {
							responseData = this.get('responseData')
							count = parseInt(responseData);

							if (count > 0) {
								if (confirm('<%= UnicodeLanguageUtil.get(pageContext, "are-you-sure-you-want-to-delete-this") %>')) {
									<portlet:namespace />doDeleteOrganizations(ids);
								}
							}
							else {
								var message = null;

								if (id && (id.toString().split(",").length > 1)) {
									message = '<%= UnicodeLanguageUtil.get(pageContext, "one-or-more-organizations-are-associated-with-deactivated-users.-do-you-want-to-proceed-with-deleting-the-selected-organizations-by-automatically-unassociating-the-deactivated-users") %>';
								}
								else {
									message = '<%= UnicodeLanguageUtil.get(pageContext, "the-selected-organization-is-associated-with-deactivated-users.-do-you-want-to-proceed-with-deleting-the-selected-organization-by-automatically-unassociating-the-deactivated-users") %>';
								}

								if (confirm(message)) {
									<portlet:namespace />doDeleteOrganizations(ids);
								}
							}
						}
					);
				}
				else {
					if (confirm('<%= UnicodeLanguageUtil.get(pageContext, "are-you-sure-you-want-to-delete-this") %>')) {
						<portlet:namespace />doDeleteOrganizations(ids);
					}
				}
			}
		);
	}

	function <portlet:namespace />doDeleteOrganizations(organizationIds) {
		document.<portlet:namespace />fm.method = "post";
		document.<portlet:namespace />fm.<portlet:namespace /><%= Constants.CMD %>.value = "<%= Constants.DELETE %>";
		document.<portlet:namespace />fm.<portlet:namespace />redirect.value = document.<portlet:namespace />fm.<portlet:namespace />organizationsRedirect.value;
		document.<portlet:namespace />fm.<portlet:namespace />deleteOrganizationIds.value = organizationIds;
		submitForm(document.<portlet:namespace />fm, "<portlet:actionURL><portlet:param name="struts_action" value="/users_admin/edit_organization" /></portlet:actionURL>");
	}

	function <portlet:namespace />search() {
		document.<portlet:namespace />fm.method = "get";
		document.<portlet:namespace />fm.<portlet:namespace /><%= Constants.CMD %>.value = "";
		submitForm(document.<portlet:namespace />fm, '<%= portletURLString %>');
	}

	Liferay.provide(
		window,
		'<portlet:namespace />deleteOrganizations',
		function() {
			var organizationIds = Liferay.Util.listCheckedExcept(document.<portlet:namespace />fm, "<portlet:namespace />allRowIds");

			if (!organizationIds) {
				return;
			}

			<portlet:namespace />doDeleteOrganization('<%= Organization.class.getName() %>', organizationIds);
		},
		['liferay-util-list-fields']
	);

	Liferay.provide(
		window,
		'<portlet:namespace />deleteUsers',
		function(cmd, actionDate) {
			var deleteUsers = true;

			var deleteUserIds = Liferay.Util.listCheckedExcept(document.<portlet:namespace />fm, "<portlet:namespace />allRowIds");

			if (!deleteUserIds) {
				deleteUsers = false;
			}
			else if (cmd == "<%= Constants.DEACTIVATE %>") {
				if (!confirm('<%= UnicodeLanguageUtil.get(pageContext, "are-you-sure-you-want-to-deactivate-the-selected-users") %>')) {
					deleteUsers = false;
				}
			}
			else if (cmd == "<%= Constants.DELETE %>") {
				if (!confirm('<%= UnicodeLanguageUtil.get(pageContext, "are-you-sure-you-want-to-permanently-delete-the-selected-users") %>')) {
					deleteUsers = false;
				}
			}

			if (deleteUsers) {
				document.<portlet:namespace />fm.method = "post";
				document.<portlet:namespace />fm.<portlet:namespace /><%= Constants.CMD %>.value = cmd;
				document.<portlet:namespace />fm.<portlet:namespace />redirect.value = document.<portlet:namespace />fm.<portlet:namespace />usersRedirect.value;
				document.<portlet:namespace />fm.<portlet:namespace />deleteUserIds.value = deleteUserIds;
				if (!actionDate && (actionDate.length != 0)) {
					document.<portlet:namespace />fm.<portlet:namespace />actionDate = actionDate;
				}
				submitForm(document.<portlet:namespace />fm, "<portlet:actionURL><portlet:param name="struts_action" value="/users_admin/edit_user" /></portlet:actionURL>");
			}
		},
		['liferay-util-list-fields']
	);

	Liferay.provide(
		window,
		'<portlet:namespace />customPreUserAction',
		function(cmd, userId) {
			var instance = this;

			if (!userId) {
				userId = Liferay.Util.listCheckedExcept(document.<portlet:namespace />fm, "<portlet:namespace />allRowIds");
			}

			var url = Liferay.PortletURL.createRenderURL();
      		url.setPortletId("<%=themeDisplay.getPortletDisplay().getId() %>");
      		url.setWindowState('pop_up');
      		url.setLifecycle(0);

      		url.setParameter('struts_action', '/users_admin/edit_user');
      		url.setParameter('renderAction', 'activateDeactivate');
      		url.setParameter('cmd', cmd);
      		url.setParameter('userIds', userId);

			Liferay.Util.openWindow(
				{
					destroyOnClose : true,
					cache: false,
					id: '<portlet:namespace />userDeactivationReactivation',
					dialog: {
						align: Liferay.Util.Window.ALIGN_CENTER,
						after: {
							render: function(event) {
								this.set('y', this.get('y') + 50);
							}
						},
						modal: true,
						width: 820,
					},
					dialogIframe: {
						uri: url.toString()
					},
					title: 'User Deactivation/Reactivation',
					uri: url.toString()
				}
			);
		},
		['liferay-util-list-fields', 'liferay-util-window', 'aui-base', 'liferay-portlet-url','aui-node', 'liferay-portlet-url']
	);

	Liferay.provide(
		window,
    	'<portlet:namespace/>closePopup',
        function(popupIdToClose) {
			AUI().DialogManager.closeByChild('#' + popupIdToClose);
        },
        ['aui-base','aui-dialog','aui-dialog-iframe']
    );

	Liferay.provide(
        window,
        '<portlet:namespace />refreshPortlet',
        function() {
            Liferay.Portlet.refresh('#p_p_id<portlet:namespace />');
        },
        ['aui-dialog','aui-dialog-iframe']
    );

	Liferay.provide(
		window,
		'<portlet:namespace />getUsersCount',
		function(className, ids, status, callback) {
			var A = AUI();

			A.io.request(
				'<%= themeDisplay.getPathMain() %>/users_admin/get_users_count',
				{
					data: {
						className: className,
						ids: ids,
						status: status
					},
					on: {
						success: callback
					}
				}
			);
		},
		['aui-io']
	);
	
</aui:script>