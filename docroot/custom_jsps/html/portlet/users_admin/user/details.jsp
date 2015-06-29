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
<%@page import="javax.portlet.RenderRequest"%>
<%@ include file="/html/portlet/users_admin/init.jsp"%>
<%@ include file="../../../util/user_permission_util.jsp"%>
<%@ include file="../../../util/hooks_util.jsp"%>

<%@page import="com.liferay.portal.kernel.dao.orm.QueryUtil"%>
<%@page import="com.liferay.portal.kernel.servlet.SessionMessages"%>
<%@page import="com.liferay.portal.service.UserLocalServiceUtil"%>
<%@page import="com.liferay.portal.security.permission.PermissionCheckerFactoryUtil"%>
<%@page import="com.liferay.portal.security.permission.PermissionThreadLocal"%>

<%
	User selUser = (User) request.getAttribute("user.selUser");
	Contact selContact = (Contact) request.getAttribute("user.selContact");

	Calendar birthday = CalendarFactoryUtil.getCalendar(locale);

	birthday.set(Calendar.MONTH, Calendar.JANUARY);
	birthday.set(Calendar.DATE, 1);
	birthday.set(Calendar.YEAR, 1970);
	birthday.setFirstDayOfWeek(Calendar.MONDAY);
	if (selContact != null) {
		birthday.setTime(selContact.getBirthday());
	}

	Object cnpErrMsgObj = SessionErrors.get(renderRequest, "duplicate-cnp");

	String cnpErrMsg = (cnpErrMsgObj != null) ? cnpErrMsgObj.toString() : "";

	boolean deletePortrait = ParamUtil.getBoolean(request, "deletePortrait");

	String oldFunctieValue = "";
	String userCNP = "";

	Phone userPhone = null;
	boolean hidePhones = false;
	boolean hideBirthday = false;
	boolean isStudent = false;

	if (Validator.isNotNull(selUser)) {
		List<Phone> userPhones = selUser.getPhones();

		if (!userPhones.isEmpty()) {
			userPhone = userPhones.get(0);
		}

		oldFunctieValue = getFunctieCIM(selUser);
		userCNP = getUserCNP(selUser);

		hidePhones = isPhoneHidden(selUser);
		hideBirthday = isBirthdayHidden(selUser);
		isStudent= isStudent(selUser);
	} else if (SessionMessages.contains(renderRequest, "previousFunctieCIM")) {
		oldFunctieValue = (String)SessionMessages.get(renderRequest, "previousFunctieCIM");
		request.setAttribute("functie.cim.custom", new String[] {oldFunctieValue});
	}

	boolean isMyAccountUpdate = !portletName.equals(PortletKeys.USERS_ADMIN);

	boolean isAdmin = false;
	boolean isHR = false;
	boolean isUserManagement = false;

	if (!isMyAccountUpdate) {
		isAdmin = (Boolean)request.getAttribute("isAdmin");
		isHR = (Boolean)request.getAttribute("isHR");
		isUserManagement = (Boolean)request.getAttribute("isUserManagement");
	}
%>

<liferay-ui:error-marker key="errorSection" value="details" />
<liferay-ui:error key="not-added-to-pmreports-error" message="not-added-to-pmreports-error" />

<aui:model-context bean="<%=selUser%>" model="<%=User.class%>" />

<h3>
	<liferay-ui:message key="details" />
</h3>

<aui:fieldset column="<%=true%>" cssClass="aui-w50">
	<liferay-ui:success key="verificationEmailSent"
		message="your-email-verification-code-has-been-sent-and-the-new-email-address-will-be-applied-to-your-account-once-it-has-been-verified" />

	<liferay-ui:error exception="<%=DuplicateUserScreenNameException.class%>" message="the-screen-name-you-requested-is-already-taken" />

	<liferay-ui:error exception="<%=GroupFriendlyURLException.class%>">

		<%
			GroupFriendlyURLException gfurle = (GroupFriendlyURLException) errorException;
		%>

		<c:if test="<%=gfurle.getType() == GroupFriendlyURLException.DUPLICATE%>">
			<liferay-ui:message key="the-screen-name-you-requested-is-associated-with-an-existing-friendly-url" />
		</c:if>
	</liferay-ui:error>

	<liferay-ui:error exception="<%=ReservedUserScreenNameException.class%>" message="the-screen-name-you-requested-is-reserved" />
	<liferay-ui:error exception="<%=UserScreenNameException.class%>" message="please-enter-a-valid-screen-name" />

	<c:if test="<%=!PrefsPropsUtil.getBoolean(company.getCompanyId(), PropsKeys.USERS_SCREEN_NAME_ALWAYS_AUTOGENERATE) || (selUser != null)%>">
		<c:choose>
			<c:when
				test="<%=PrefsPropsUtil.getBoolean(company.getCompanyId(), PropsKeys.USERS_SCREEN_NAME_ALWAYS_AUTOGENERATE)
									|| ((selUser != null) && !UsersAdminUtil.hasUpdateScreenName(permissionChecker, selUser))%>">
				<aui:field-wrapper name="screenName">
					<%=selUser.getScreenName()%>

					<aui:input name="screenName" type="hidden" value="<%=selUser.getScreenName()%>" />
				</aui:field-wrapper>
			</c:when>
			<c:otherwise>
				<liferay-ui:error key="screen-name-invalid-length-error" message="screen-name-invalid-length-error" />
				<liferay-ui:error key="screen-name-must-contain-at-least-one-letter-error" message="screen-name-must-contain-at-least-one-letter-error" />
				<liferay-ui:error key="screen-name-invalid-characters-error" message="screen-name-invalid-characters-error" />
				<aui:input cssClass="ctrl-input" name="screenName" />
			</c:otherwise>
		</c:choose>
	</c:if>

	<liferay-ui:error exception="<%=DuplicateUserEmailAddressException.class%>" message="the-email-address-you-requested-is-already-taken" />
	<liferay-ui:error exception="<%=ReservedUserEmailAddressException.class%>" message="the-email-address-you-requested-is-reserved" />
	<liferay-ui:error exception="<%=UserEmailAddressException.class%>" message="please-enter-a-valid-company-email-address" />

	<c:choose>
		<c:when test="<%=(selUser != null) && !UsersAdminUtil.hasUpdateEmailAddress(permissionChecker, selUser)%>">
			<aui:field-wrapper name="emailAddress">
				<%=selUser.getDisplayEmailAddress()%>

				<aui:input name="emailAddress" type="hidden" value="<%=selUser.getEmailAddress()%>" />
			</aui:field-wrapper>
		</c:when>
		<c:otherwise>

			<%
			User displayEmailAddressUser = null;

			if (selUser != null) {
				displayEmailAddressUser = (User) selUser.clone();
				displayEmailAddressUser.setEmailAddress(displayEmailAddressUser.getDisplayEmailAddress());
			}
			%>

			<aui:input cssClass="ctrl-input" bean="<%=displayEmailAddressUser%>" model="<%=User.class%>" name="emailAddress">
				<c:if test="<%=PrefsPropsUtil.getBoolean(company.getCompanyId(), PropsKeys.USERS_EMAIL_ADDRESS_REQUIRED)%>">
					<aui:validator name="required" />
				</c:if>
			</aui:input>
		</c:otherwise>
	</c:choose>

	<liferay-ui:error exception="<%=ContactFirstNameException.class%>" message="please-enter-a-valid-first-name" />
	<liferay-ui:error exception="<%=ContactFullNameException.class%>" message="please-enter-a-valid-first-middle-and-last-name" />

	<liferay-ui:error key="first-name-invalid-length-error" message="first-name-invalid-length-error" />
	<liferay-ui:error key="first-name-invalid-characters-error" message="first-name-invalid-characters-error" />
	<aui:input cssClass="ctrl-input" name="firstName" />

	<liferay-ui:error exception="<%=ContactLastNameException.class%>" message="please-enter-a-valid-last-name" />

	<liferay-ui:error key="last-name-invalid-length-error" message="last-name-invalid-length-error" />
	<liferay-ui:error key="last-name-invalid-characters-error" message="last-name-invalid-characters-error" />
	<aui:input cssClass="ctrl-input" name="lastName">
		<c:if test="<%=PrefsPropsUtil.getBoolean(company.getCompanyId(), PropsKeys.USERS_LAST_NAME_REQUIRED, PropsValues.USERS_LAST_NAME_REQUIRED)%>">
			<aui:validator name="required" />
		</c:if>
	</aui:input>

	<c:if test="<%=!isMyAccountUpdate%>">
		<div class="ctrl-input">
			<liferay-ui:custom-attribute className="com.liferay.portal.model.User" classPK="<%=(selUser != null) ? selUser.getUserId() : 0%>" editable="<%=true%>"
				label="<%=true%>" name="Official Name" />
		</div>
	</c:if>

	<c:if test="<%=PrefsPropsUtil.getBoolean(company.getCompanyId(), PropsKeys.FIELD_ENABLE_COM_LIFERAY_PORTAL_MODEL_CONTACT_MALE)%>">
		<aui:select cssClass="ctrl-select" bean="<%=selContact%>" label="gender" model="<%=Contact.class%>" name="male">
			<aui:option label="male" value="true" />
			<aui:option label="female" value="false" />
		</aui:select>
	</c:if>


	<div class="ctrl-input">
		<liferay-ui:custom-attribute className="com.liferay.portal.model.User" classPK="<%=(selUser != null) ? selUser.getUserId() : 0%>" editable="<%=true%>"
			label="<%=true%>" name="Building" />
	</div>

	<liferay-ui:error key="job-title-invalid-length-error" message="job-title-invalid-length-error" />
	<liferay-ui:error key="job-title-invalid-characters-error" message="job-title-invalid-characters-error" />
	<aui:input cssClass="ctrl-input" name="jobTitle" />

	<c:if test="<%=!oldFunctieValue.isEmpty()%>">
		<aui:input type="hidden" name="prevFunctieType" value="<%=oldFunctieValue%>" />
	</c:if>

	<c:if test="<%=isUserManagement || isAdmin && !isMyAccountUpdate%>">
		<div class="ctrl-input">
			<liferay-ui:error key="cnp-invalid-length-error" message="cnp-invalid-length-error" />
			<liferay-ui:error key="cnp-invalid-characters-error" message="cnp-invalid-characters-error" />
			<liferay-ui:error key="duplicate-cnp" message="<%=cnpErrMsg%>" />

			<aui:input cssClass="ctrl-input" id="userCNP" name="userCNP" type="text" label="personal.identification.number" value="<%=userCNP%>">
				<aui:validator name="required" />
			</aui:input>
		</div>
	</c:if>

</aui:fieldset>

<aui:fieldset column="<%=true%>" cssClass="aui-w50">
	<div class="myAccount">
		<c:if test="<%=selUser != null%>">
			<portlet:renderURL var="editUserPortraitURL" windowState="<%=LiferayWindowState.POP_UP.toString()%>">
				<portlet:param name="struts_action" value="/users_admin/edit_user_portrait" />
				<portlet:param name="redirect" value="<%=currentURL%>" />
				<portlet:param name="p_u_i_d" value="<%=String.valueOf(selUser.getUserId())%>" />
				<portlet:param name="portrait_id" value="<%=String.valueOf(selUser.getPortraitId())%>" />
			</portlet:renderURL>

			<liferay-ui:logo-selector defaultLogoURL="<%=UserConstants.getPortraitURL(themeDisplay.getPathImage(), selUser.isMale(), 0)%>"
				editLogoURL="<%=editUserPortraitURL%>" imageId="<%=selUser.getPortraitId()%>" logoDisplaySelector=".user-logo" showBackground="<%=false%>" />
		</c:if>
	</div>

	<c:if test="<%=(selUser != null) && isAdmin%>">
		<liferay-ui:error exception="<%=DuplicateUserIdException.class%>" message="the-user-id-you-requested-is-already-taken" />

		<liferay-ui:error exception="<%=ReservedUserIdException.class%>" message="the-user-id-you-requested-is-reserved" />
		<liferay-ui:error exception="<%=UserIdException.class%>" message="please-enter-a-valid-user-id" />

		<aui:field-wrapper name="userId">
			<%=selUser.getUserId()%>
		</aui:field-wrapper>
		<aui:input name="userId" type="hidden" value="<%=selUser.getUserId()%>" />
	</c:if>

	<div class="ctrl-input">
		<liferay-ui:error key="license-plate-invalid-length-error" message="license-plate-invalid-length-error" />
		<liferay-ui:error key="license-plate-invalid-characters-error" message="license-plate-invalid-characters-error" />

		<liferay-ui:custom-attribute className="com.liferay.portal.model.User" classPK="<%=(selUser != null) ? selUser.getUserId() : 0%>" editable="<%=true%>"
			label="<%=true%>" name="License Plate" />
	</div>

	<div class="control-select">
		<c:choose>
			<c:when test="<%=PrefsPropsUtil.getBoolean(company.getCompanyId(), PropsKeys.FIELD_ENABLE_COM_LIFERAY_PORTAL_MODEL_CONTACT_BIRTHDAY)%>">
				<liferay-ui:error exception="<%=ContactBirthdayException.class%>" message="please-enter-a-valid-date" />
				<liferay-ui:error key="birthday-after-hire" message="Invalid value: Birthday is after Date Hired" />

				<aui:input bean="<%=selContact%>" model="<%=Contact.class%>" name="birthday" value="<%=birthday%>" />
			</c:when>
			<c:otherwise>
				<aui:input name="birthdayMonth" type="hidden" value="<%=Calendar.JANUARY%>" />
				<aui:input name="birthdayDay" type="hidden" value="1" />
				<aui:input name="birthdayYear" type="hidden" value="1970" />
			</c:otherwise>
		</c:choose>

	<%
		// TODO: is this necessary??? check it out
		User admin = null;
			for (Role r : RoleLocalServiceUtil.getRoles(QueryUtil.ALL_POS, QueryUtil.ALL_POS)) {
				if (r.getName().equalsIgnoreCase("Administrator")) {
					admin = UserLocalServiceUtil.getRoleUsers(r.getRoleId()).get(0);
					break;
				}
			}

			PermissionThreadLocal.setPermissionChecker(PermissionCheckerFactoryUtil.create(admin));
	%>
		<span>
			<input class="aui-field-input aui-field-input-choice" id="hideBirthday" name="hideBirthday" onclick='toggleVisibility(this, "birthdayVisibility");' type="checkbox" <%= hideBirthday ? "checked" : "" %>>
			<label class="aui-choice-label" for="hideBirthday"><liferay-ui:message key="hide.birthday" /></label>
		</span>
		<span id="birthdayVisibility" class='access-label <%= !hideBirthday ? "aui-helper-hidden" : "" %>'>
			<liferay-ui:message key="hide.birthday.can.only.be.seen" />
		</span>
	
	</div>

	<div class="ctrl-input">
		<aui:input label="skype" type="text" name="skypeSn" value='<%=(selContact != null) ? selContact.getSkypeSn() : ""%>' />
	</div>

	<div>
		<span>
			<liferay-ui:error key="invalid-phone-number" message="invalid-phone-number" />

			<b><liferay-ui:message key="phone.number" /></b>
			<input type="text" id="phoneNumber" name="phoneNumber" value='<%=(userPhone != null) ? userPhone.getNumber() : ""%>' maxlength="75" />

			<input class="aui-field-input aui-field-input-choice" id="hidePhones" name="hidePhones" onclick='toggleVisibility(this, "phoneNumberVisibility");' type="checkbox" <%= hidePhones ? "checked" : "" %>>
			<label class="aui-choice-label" for="hidePhones"><liferay-ui:message key="hide.phones" /></label>
		</span>
		<span id="phoneNumberVisibility" class='access-label <%= !hidePhones ? "aui-helper-hidden" : "" %>'>
			<liferay-ui:message key="phone.number.can.only.be.seen" />
		</span>
	</div>

	<br />

	<span>
		<input class="aui-field-input aui-field-input-choice" id="isStudent" name="isStudent" onclick='toggleVisibility(this, "studentFields");' type="checkbox" <%= isStudent ? "checked" : "" %>>
		<label class="aui-choice-label" for="isStudent"><liferay-ui:message key="student" /></label>
	</span>

	<div id="studentFields" class='<%= !isStudent ? "aui-helper-hidden" : "" %>'>
		<div class="ctrl-input">
			<liferay-ui:custom-attribute className="com.liferay.portal.model.User" classPK="<%=(selUser != null) ? selUser.getUserId() : 0%>" editable="<%=true%>"
				label="<%=true%>" name="University" />
		</div>
		
		<div class="ctrl-input">
			<liferay-ui:custom-attribute className="com.liferay.portal.model.User" classPK="<%=(selUser != null) ? selUser.getUserId() : 0%>" editable="<%=true%>"
				label="<%=true%>" name="Faculty" />
		</div>
		
		<div class="ctrl-input">
			<liferay-ui:custom-attribute className="com.liferay.portal.model.User" classPK="<%=(selUser != null) ? selUser.getUserId() : 0%>" editable="<%=true%>"
				label="<%=true%>" name="Diploma title (In progress)" />
		</div>
	</div>

	<br />
</aui:fieldset>

