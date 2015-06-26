<%@page import="java.util.Calendar"%>

<%@ include file="/html/portlet/users_admin/init.jsp"%>

<%
	pageContext.setAttribute("maxPersonsPerRow", 3);
	Calendar today = Calendar.getInstance();
	pageContext.setAttribute("dayValue", today.get(Calendar.DAY_OF_MONTH));
	pageContext.setAttribute("monthValue", today.get(Calendar.MONTH));
	pageContext.setAttribute("yearValue", today.get(Calendar.YEAR));
%>

<portlet:actionURL name="userAction" var="userActionURL">
	<portlet:param name="struts_action" value="/users_admin/edit_user" />
	<portlet:param name="cmd" value="${cmd}" />
	<portlet:param name="deleteUserIds" value="${userIds}" />
</portlet:actionURL>

<aui:form id="deactivateActivateUsersForm"
	name="deactivateActivateUsersForm" method="post" action="">
	<table class="user-state-table">
		<c:set var="personIndex" scope="page" value="0" />
		<c:forEach var="user" items="${selectedUsers}">
			<c:if test="${personIndex % maxPersonsPerRow == 0 }">
				<tr>
			</c:if>

			<td>
				<div class="user-state-cell">
					<div class="user-state-item-arrange">
						<b>${user.fullName}</b>
					</div>
					<br />
					<div class="user-state-item-arrange">
						<img alt="${user.fullName}" class="user-logo" src="${user.getPortraitURL(themeDisplay)}" />
					</div>
					<br />
					<div class="user-state-item-arrange">
						<liferay-ui:input-date 
							dayValue="${dayValue}"
							dayParam="day_${user.userId}" 
							firstDayOfWeek="1"
							monthParam="month_${user.userId}" 
							monthValue="${monthValue}"
							yearParam="year_${user.userId}" 
							yearValue="${yearValue }"
							yearRangeStart="2006" 
							yearRangeEnd="2099" />
					</div>
				</div>

			</td>

			<c:if
				test="${personIndex % maxPersonsPerRow == maxPersonsPerRow - 1 }">
				</tr>
			</c:if>
			<c:set var="personIndex" scope="page" value="${personIndex + 1}" />
		</c:forEach>
		<tr>
			<td colspan="3">
				<div class="user-state-item-arrange user-state-button-arrange">
					<aui:button name="saveButton" value="save" type="submit" id="<portlet:namespace />saveButton" />
					<aui:button name="cancelButton" type="cancel" />
				</div>
			</td>
		</tr>
	</table>
</aui:form>

<aui:script>

AUI().ready('liferay-util-window','aui-base', 'aui-io-request', function(A) {
	A.one("#<portlet:namespace />saveButton").on('click', function(event) {
		event.preventDefault();

		A.io.request(
			'${userActionURL}',
			{
				method: 'post',
				form: {
					id: '<portlet:namespace />deactivateActivateUsersForm'
				},
				on: {
					success: function() {
						var popUpOpener = Liferay.Util.getOpener();
						popUpOpener.<portlet:namespace />closePopup('<portlet:namespace />userDeactivationReactivation');
				 		popUpOpener.<portlet:namespace />refreshPortlet();
					}
				}
			}
		);

	});

});

</aui:script>



