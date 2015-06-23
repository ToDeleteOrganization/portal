<%@ include file="/html/portlet/users_admin/init.jsp"%>
<%@ include file="../../../util/hooks_util.jsp"%>

<%@page import="javax.portlet.RenderRequest"%>
<%@page import="com.liferay.portal.kernel.dao.orm.QueryUtil"%>
<%@page import="com.liferay.portal.kernel.util.Validator"%>
<%@page import="com.evozon.evoportal.familytableaccess.slayer.service.FamilyMemberLocalServiceUtil"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>

<%@page import="com.evozon.evoportal.familytableaccess.slayer.model.FamilyMember"%>

<%@page import="java.util.Enumeration"%>
<%@page import="com.liferay.portal.kernel.servlet.SessionMessages"%>
<%@page import="javax.portlet.PortletPreferences" %>


<!-- http://www.liferay.com/community/forums/-/message_boards/message/8103137

On click apeleaza my struts action -->

<%
User selUser = (User)request.getAttribute("user.selUser");
long userId = (selUser != null) ? selUser.getUserId() : 0;
%>

<div class="ctrl-input" style="max-width: 200px;">
	<liferay-ui:custom-attribute className="com.liferay.portal.model.User" classPK="<%= userId%>" editable="<%=true%>"
		label="<%=true%>" name="Married" />
</div>

<aui:fieldset id="family-section">

	<h3><liferay-ui:message key="spouse.details" /></h3>
	
	<aui:fieldset id="whife-fields">
		<div class="lfr-form-row lfr-form-row-inline">
	
			<liferay-ui:error key="spouse-empty-first-name-error" message="empty.first.name" />
			<liferay-ui:error key="spouse-invalid-first-name-error" message="invalid.first.name" />
			
			<liferay-ui:error key="spouse-empty-last-name-error" message="empty.last.name" />
			<liferay-ui:error key="spouse-invalid-last-name-error" message="invalid.last.name" />
			
			<liferay-ui:error key="spouse-invalid-personal-identification-number-error" message="invalid.personal.identification.number" />
			<liferay-ui:error key="spouse-invalid-personal-identification-number-size-error" message="invalid.personal.identification.number.size" />
	
			<liferay-ui:error key="spouse-new-empty-first-name-error" message="new.empty.first.name.error" />
			<liferay-ui:error key="spouse-new-empty-last-name-error" message="new.empty.last.name.error" />
	<%
	FamilyMember spouse = FamilyMemberLocalServiceUtil.getSpouse(userId);
	
	// can't put a FamilyMember in request, a class cast exception appears
	// different classloaders
	FamilyMember invalidSpouse = getFamilySpouseMember(request);
	
	if (spouse!=null) {
		long spouseId = spouse.getMemberId();
	
		String firstName = (invalidSpouse != null) ? invalidSpouse.getFirstName() : spouse.getFirstName();
		if (firstName.isEmpty()) {
			firstName = spouse.getFirstName();
		}
	
		String lastName = (invalidSpouse != null) ? invalidSpouse.getLastName() : spouse.getLastName();
		if (lastName.isEmpty()) {
			lastName = spouse.getLastName();
		}
	%>
			<aui:input name="spouseId" type="hidden" label="memberId" size="40" value="<%= spouse.getMemberId() %>"/>
			
			<aui:input name="spouseFirstName" label="first.name" size="40" value='<%= firstName %>'/>
			<aui:input name="spouseLastName" label="last.name" size="40" value='<%= lastName %>'/>
			<aui:input name="spouseCNP" label="personal.identification.number" size="15" value='<%= (invalidSpouse != null) ? invalidSpouse.getCNP() : spouse.getCNP() %>'/>
	<%
	} else {
	%>
			<aui:input name="spouseId" type="hidden" label="memberId" size="40"/>
			<aui:input name="spouseFirstName" label="first.name.required" size="40" value='<%= (invalidSpouse != null) ? invalidSpouse.getFirstName() : "" %>'/>
			<aui:input name="spouseLastName" label="last.name.required" size="40" value='<%= (invalidSpouse != null) ? invalidSpouse.getLastName() : "" %>'/>
			<aui:input name="spouseCNP" label="personal.identification.number" size="15" value='<%= (invalidSpouse != null) ? invalidSpouse.getCNP() : "" %>'/>
	<%
	}
	%>
		</div>
	</aui:fieldset>
	
	<br/>
	
	<h3><liferay-ui:message key="children.details" /></h3>
	
	<aui:fieldset id="children-fields">
	<%
	int countChildren = FamilyMemberLocalServiceUtil.getChildren(userId).size();
	
	int childrenIndexes[] = new int[countChildren];
	for (int i=0; i<countChildren; i++){
		childrenIndexes[i] = i;
	}
	
	// for the existing children that have been invalid updated
	int childIndex = 0;
	for (FamilyMember child : FamilyMemberLocalServiceUtil.getChildren(userId)) {
			childIndex++;
			long memberId = child.getMemberId();
			FamilyMember invalidChild = getFamilyChildrenMember(memberId, request);
	
			String firstName = (invalidChild != null) ? invalidChild.getFirstName() : child.getFirstName();
			if (firstName.isEmpty()) {
				firstName = child.getFirstName();
			}
	
			String lastName = (invalidChild != null) ? invalidChild.getLastName() : child.getLastName();
			if (lastName.isEmpty()) {
				lastName = child.getLastName();
			}
	%>
		
			<liferay-ui:error key='<%=memberId + "child-empty-first-name-error" %>' message="empty.first.name" />
			<liferay-ui:error key='<%=memberId + "child-invalid-first-name-error"%>' message="invalid.first.name" />
			
			<liferay-ui:error key='<%=memberId + "child-empty-last-name-error"%>' message="empty.last.name" />
			<liferay-ui:error key='<%=memberId + "child-invalid-last-name-error"%>' message="invalid.last.name" />
			
			<liferay-ui:error key='<%=memberId + "child-invalid-personal-identification-number-error"%>' message="invalid.personal.identification.number" />
			<liferay-ui:error key='<%=memberId + "child-invalid-personal-identification-number-size-error"%>' message="invalid.personal.identification.number.size" />
		
		<div class="lfr-form-row lfr-form-row-inline">
			<aui:input name='<%= "childId" + childIndex %>' type="hidden" label="memberId" size="30" value="<%= child.getMemberId() %>"/>
			<aui:input name='<%= "childFirstName" + childIndex %>' label="first.name" size="30" value="<%= firstName %>"/>
			<aui:input name='<%= "childLastName" + childIndex %>' label="last.name" size="30" value="<%= lastName %>"/>
			<aui:input name='<%= "childCNP" + childIndex %>' label="personal.identification.number" size="15" value="<%= (invalidChild != null) ? invalidChild.getCNP() : child.getCNP() %>"/>
		</div>
	<%
	}
	
	// for the new children that have been invalid created
	int i = 0;
	List<FamilyMember> newInvalidChildren = getNewFamilyChildeMember(request);
	for (FamilyMember invalidChild : newInvalidChildren) {
			childIndex++;
	%>
	
			<liferay-ui:error key='<%= i + "child-empty-first-name-error"%>' message="empty.first.name" />
			<liferay-ui:error key='<%= i + "child-invalid-first-name-error"%>' message="invalid.first.name" />
	
			<liferay-ui:error key='<%= i + "child-empty-last-name-error"%>' message="empty.last.name" />
			<liferay-ui:error key='<%= i + "child-invalid-last-name-error"%>' message="invalid.last.name" />
	
			<liferay-ui:error key='<%= i + "child-invalid-personal-identification-number-error"%>' message="invalid.personal.identification.number" />
			<liferay-ui:error key='<%= i + "child-invalid-personal-identification-number-size-error"%>' message="invalid.personal.identification.number.size" />
	
		<div class="lfr-form-row lfr-form-row-inline">
			<aui:input name='<%= "childId" + childIndex %>' type="hidden" label="memberId" size="30" />
			<aui:input name='<%= "childFirstName" + childIndex %>' label="first.name.required" size="30" value='<%= invalidChild.getFirstName() %>'/>
			<aui:input name='<%= "childLastName" + childIndex %>' label="last.name.required" size="30" value='<%= invalidChild.getLastName() %>'/>
			<aui:input name='<%= "childCNP" + childIndex %>' label="personal.identification.number" size="15" value='<%= invalidChild.getCNP() %>'/>
		</div>
	
	<%
			i++;
	}
	
	childIndex++;
	%>
		<liferay-ui:error key="child-new-empty-first-name-error" message="new.empty.first.name.error" />
		<liferay-ui:error key="child-new-empty-last-name-error" message="new.empty.last.name.error" />	
	
		<div class="lfr-form-row lfr-form-row-inline">
			<aui:input name='<%= "childId" + childIndex %>' type="hidden" label="memberId" size="30"/>
			<aui:input name='<%= "childFirstName" + childIndex %>' label="first.name.required" size="30"/>
			<aui:input name='<%= "childLastName" + childIndex %>' label="last.name.required" size="30"/>
			<aui:input name='<%= "childCNP" + childIndex %>' label="personal.identification.number" size="15"/>
		</div>
	</aui:fieldset>
</aui:fieldset>

<aui:script use="liferay-auto-fields">
AUI().ready('aui-form-validator', 'aui-overlay-context-panel', function(A) {
   new Liferay.AutoFields(
        {
            contentBox: '#<portlet:namespace />children-fields',
            fieldIndexes: '<portlet:namespace />childrenIndexes'
        }
    ).render();
});
</aui:script>