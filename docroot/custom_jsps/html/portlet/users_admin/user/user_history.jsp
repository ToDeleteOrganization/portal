<%@page import="java.util.Calendar"%>
<%@page import="java.util.Set" %>
<%@page import="java.util.Map.Entry" %>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
Calendar internshipStart = getInternshipDateAsCalendar(selUser);
Calendar cimStart = getCIMStartDateAsCalendar(selUser);

Set<Entry<String, String>> employments = (Set<Entry<String, String>>)request.getAttribute("employments");
%>

<h3><liferay-ui:message key="user-history"/></h3>

<c:choose>
	<c:when test="<%= !employments.isEmpty() %>">
		<table class=cancelVacationTable border="5">
			<tr>
				<th><liferay-ui:message key="employment-type"/></th>
				<th><liferay-ui:message key="employment-period"/></th>
			</tr>

			<% for (Entry<String, String> employment : employments) {%>
				<tr>
					<td width="250"><%= employment.getKey() %></td>
					<td width="250"><%= employment.getValue() %></td>
				</tr>
			<%}%>

		</table>
	</c:when>
	<c:otherwise>
		<liferay-ui:message key="no-employment-period-exists"/>
	</c:otherwise>
</c:choose>

<span class="show-dates">
	<input id="showDatesCheckbox" type="checkbox" onclick='toggleVisibility(this, "<portlet:namespace />employments-dates")'/>
	<label for="showDatesCheckbox"><liferay-ui:message key="show.employment.dates" /></label>
</span>

<div id="<portlet:namespace />employments-dates" class="aui-helper-hidden" style="margin-top: 15px;">
	<label class="aui-field-label"><liferay-ui:message key="Internship start" /></label>
	<liferay-ui:input-date
		dayValue="<%= internshipStart.get(Calendar.DAY_OF_MONTH) %>"
	    dayParam="internshipDay"
	    disabled='<%= false %>'
	    firstDayOfWeek="<%= 1 %>"
	    monthParam="internshipMonth"
	    monthValue="<%= internshipStart.get(Calendar.MONTH) %>"
	    yearParam="internshipYear"
	    yearValue="<%= internshipStart.get(Calendar.YEAR) %>"
	    yearRangeStart="<%= 2006 %>"
	    yearRangeEnd="<%= 2099 %>"
	/>

<br />
<br />

	<label class="aui-field-label"><liferay-ui:message key="CIM Start" /></label>
	<liferay-ui:input-date
		dayValue="<%= cimStart.get(Calendar.DAY_OF_MONTH) %>"
	    dayParam="cmDay"
	    disabled='<%= false %>'
	    firstDayOfWeek="<%= 1 %>"
	    monthParam="cmMonth"
	    monthValue="<%= cimStart.get(Calendar.MONTH) %>"
	    yearParam="cmYear"
	    yearValue="<%= cimStart.get(Calendar.YEAR) %>"
	    yearRangeStart="<%= 2006 %>"
	    yearRangeEnd="<%= 2099 %>"
	/>
	
</div>

<br />
<br />
