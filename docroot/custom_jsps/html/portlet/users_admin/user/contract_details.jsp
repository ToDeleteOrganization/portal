
<%@ include file="/html/portlet/users_admin/init.jsp"%>

<%@ include file="../../../util/hooks_util.jsp" %>

<%
	User selUser = (User)request.getAttribute("user.selUser");
	Calendar invalidDH = getInvalidDateHired(request);
	String userType = (String)request.getAttribute("user.type");

	Calendar hired = Calendar.getInstance();
	if (selUser != null) {
		hired = getDateHiredAsCalendar(selUser);
	}

	boolean isMyAccountUpdate = !portletName.equals(PortletKeys.USERS_ADMIN);
%>

<label class="aui-field-label">
	<c:choose>
		<c:when test='<%= userType.equals("internship") %>'>
			<liferay-ui:message key="internship-start-date" />
		</c:when>
		<c:when test='<%= userType.equals("cim") %>'>
			<liferay-ui:message key="cim-start-date" />
		</c:when>
		<c:when test='<%= userType.equals("scholarship") %>'>
			<liferay-ui:message key="scholarship-start-date" />
		</c:when>
		<c:otherwise>
			<liferay-ui:message key="start-date" />
		</c:otherwise>
	</c:choose>
</label>

<liferay-ui:error key="invalid-date-hired-error-key" message="date-hired-cannot-be-set-on-weekends-or-on-national-free-days" />

<liferay-ui:input-date
	dayValue="<%= (invalidDH != null) ? invalidDH.get(Calendar.DAY_OF_MONTH) : hired.get(Calendar.DAY_OF_MONTH) %>"
    dayParam="hiredDay"
    disabled='<%= false %>'
    firstDayOfWeek="<%= 1 %>"
    monthParam="hiredMonth"
    monthValue="<%= (invalidDH != null) ? invalidDH.get(Calendar.MONTH) : hired.get(Calendar.MONTH) %>"
    yearParam="hiredYear"
    yearValue="<%= (invalidDH != null) ? invalidDH.get(Calendar.YEAR) : hired.get(Calendar.YEAR) %>"
    yearRangeStart="<%= 2006 %>"
    yearRangeEnd="<%= 2099 %>"
/>
<br />
<br />



<div class="ctrl-input" style="max-width:300px;">
	<liferay-ui:error key="please-specify-functiecim" message="please-specify-functiecim" />
	<liferay-ui:custom-attribute className="com.liferay.portal.model.User" classPK="<%=(selUser != null) ? selUser.getUserId() : 0%>"
		editable="<%=!isMyAccountUpdate%>" label="<%=true%>" name="Functie CIM" />
</div>

<c:if test="<%= selUser != null %>">
	<%@ include file="user_history.jsp" %> 
</c:if>

