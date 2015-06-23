
<%@ include file="/html/portlet/users_admin/init.jsp" %>

<%
User selUser = (User)request.getAttribute("user.selUser");

List<Group> groups = (List<Group>)request.getAttribute("user.groups");
%>


<%@ include file="/html/portlet/users_admin/user/sites.jsp" %>

<br />
<br />

<%@ include file="/html/portlet/users_admin/user/roles.jsp" %>