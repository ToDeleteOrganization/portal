<%@page import="com.liferay.portal.service.RoleLocalServiceUtil"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.Calendar"%>
<%@page import="com.liferay.portal.theme.ThemeDisplay"%>

<%@ include file="/html/portlet/users_admin/init.jsp" %>
<%@ include file="../../../util/user_permission_util.jsp" %>
<%@ include file="../../../util/hooks_util.jsp" %>


<%
	User selUser = (User)request.getAttribute("user.selUser");
	ThemeDisplay td = (ThemeDisplay) request.getAttribute(com.liferay.portal.kernel.util.WebKeys.THEME_DISPLAY);
	User currentUser = td.getUser();

	// Temporary
	boolean hasRoleForModification = false;
	Role r = RoleLocalServiceUtil.getRole(td.getCompanyId(), "Temporary");
	if (r != null) {
		hasRoleForModification = RoleLocalServiceUtil.hasUserRole(currentUser.getUserId(), r.getRoleId());
	}
	hasRoleForModification = (Boolean)request.getAttribute("test");
	// Temporary End

	boolean isAdmin = (Boolean)request.getAttribute("isAdmin");
	boolean isHR = (Boolean)request.getAttribute("isHR");	

	boolean hasViewRights = isAdmin || isHR;
	boolean hasEditRights = isAdmin || isHR;

%>

<h3><liferay-ui:message key="Free Days" /></h3>

<aui:fieldset>

<c:if test="<%= selUser != null %>">
	<aui:input label="Free Days From Last Year" name="freeDaysLast" value='<%= getFreeDaysFromLastYear(selUser) %>' />
	<aui:input label="Free Days In Current Year" name="freeDaysCurrent" value='<%= getFreeDaysFromCurrentYear(selUser) %>' />
	
	<label class="aui-field-label">Total Free Days</label><%= getTotalFreeDays(selUser) %>
</c:if>

<aui:input label="Starting Bonus Days" name="startingBonusDays" type='<%= !hasEditRights ? "hidden" : "" %>' value='<%= (selUser != null) ? getStartingBonusDays(selUser) : 0 %>' />

<c:if test="<%= selUser != null %>" >
	<label class="aui-field-label">Evozon Bonus Days</label><%=request.getAttribute("evozonBonusDays")%>
</c:if>

<c:if test="<%= hasViewRights %>">
	<aui:input disabled="<%=!hasEditRights%>" type="textarea" label="Comments" name="comments" value='<%= (selUser != null) ? getComments(selUser) : "" %>' style="height:75px;width:350px"/>

	<%@ include file="extra_days_history.jsp" %> 

	<br/>
	<h4>Total allocated extra days:</h4>

	<c:if test="<%= selUser != null %>" >
		<liferay-ui:custom-attribute
	        className="com.liferay.portal.model.User"
	        classPK="<%= selUser.getUserId() %>"
	        editable="<%= hasRoleForModification ? true : false %>"
	        label="<%= false %>"
	        name="Extra Days"
	       />
	</c:if>

	<aui:input disabled="<%=!hasEditRights%>" 
		label="Number Of Extra Days"
		name="extraDays"
		value='' />

	<aui:input disabled="<%=!hasEditRights%>"
		type="textarea"
		label="Extra Days Description"
		name="extraDaysDescription"
		value='' 
		style="height:75px;width:350px"/>
	
</c:if>

<c:if test="<%= (selUser != null) && hasRoleForModification %>">

	<liferay-ui:custom-attribute
	        className="com.liferay.portal.model.User"
	        classPK="<%= selUser.getUserId() %>"
	        editable="<%= true %>"
	        label="<%= true %>"
	        name="Legal Vacation Days"
	 />

	<liferay-ui:custom-attribute
	        className="com.liferay.portal.model.User"
	        classPK="<%= selUser.getUserId() %>"
	        editable="<%= true %>"
	        label="<%= true %>"
	        name="Extracted Free Days From Current Year"
	        />
	
	<liferay-ui:custom-attribute
	        className="com.liferay.portal.model.User"
	        classPK="<%= selUser.getUserId() %>"
	        editable="<%= true %>"
	        label="<%= true %>"
	        name="Extracted Free Days From Last Year"
	        />
	        
	 <liferay-ui:custom-attribute
	        className="com.liferay.portal.model.User"
	        classPK="<%= selUser.getUserId() %>"
	        editable="<%= true %>"
	        label="<%= true %>"
	        name="Remaining Free Days From Last Year"
	        />
	        
	   	 <liferay-ui:custom-attribute
	        className="com.liferay.portal.model.User"
	        classPK="<%= selUser.getUserId() %>"
	        editable="<%= true %>"
	        label="<%= true %>"
	        name="Extra Days"
	        />

	<liferay-ui:custom-attribute
		className="com.liferay.portal.model.User"
		classPK="<%= (selUser != null) ? selUser.getUserId() : 0 %>"
		editable="true"
		label="<%= true %>"
		name="Evozon User Status"
		/>
		
	<liferay-ui:custom-attribute
	        className="com.liferay.portal.model.User"
	        classPK="<%= selUser.getUserId() %>"
	        editable="<%= true %>"
	        label="<%= true %>"
	        name="User Entry Type"
	        />
</c:if>


</aui:fieldset>
