package com.evozon.evoportal.my_account;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.apache.commons.lang.StringUtils;

import com.evozon.evoportal.evozonfreedaysallocator.BonusDaysComputer;
import com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDay;
import com.evozon.evoportal.evozonfreedaysallocator.model.FreeDaysHistoryEntry;
import com.evozon.evoportal.evozonfreedaysallocator.service.BenefitDayLocalServiceUtil;
import com.evozon.evoportal.evozonfreedaysallocator.service.FreeDaysHistoryEntryLocalServiceUtil;
import com.evozon.evoportal.my_account.managers.UserEmploymentPeriodsHandler;
import com.evozon.evoportal.my_account.util.EvoportalUserUtil;
import com.evozon.evoportal.my_account.util.MyAccountConstants;
import com.evozon.evoportal.my_account.util.MyAccountUtil;
import com.evozon.evoportal.my_account.wrapper.UserExpandoWrapper;
import com.evozon.evoportal.myaccount.builder.AccountActionPhase;
import com.evozon.evoportal.myaccount.builder.AccountPhaseFactory;
import com.evozon.evoportal.myaccount.builder.ActionPhaseParameters;
import com.evozon.evoportal.vacation.model.DateInterval;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.struts.StrutsPortletAction;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.GroupConstants;
import com.liferay.portal.model.RoleConstants;
import com.liferay.portal.model.User;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.RoleLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.util.PortalUtil;

public class EditUserSaveButtonCustomAction extends AccountCustomStrutsPortletAction {

	private static Log logger = LogFactoryUtil.getLog(EditUserSaveButtonCustomAction.class);

	public static final String INVALID_DATE_HIRED_ERROR_KEY = "invalid-date-hired-error-key";

	private static final String ACTIVATE_DEACTIVATE = "activateDeactivate";

	private static final String RENDER_ACTION = "renderAction";

	private static final String USER_STATE_POP_UP = "/portlet/users_admin/user/user_state.jsp";

	private static final String PRESENT = "present";

	public void processAction(StrutsPortletAction originalStrutsPortletAction, PortletConfig portletConfig, ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {
//		AccountActionCommand accountCommand = createCommand(originalStrutsPortletAction, portletConfig, actionRequest, actionResponse);

//		logger.debug("Executing command: " + accountCommand);
//		accountCommand.executeCustomAction();

		ActionPhaseParameters param = AccountPhaseFactory.getActionAccountPhase(originalStrutsPortletAction, portletConfig, actionRequest, actionResponse);
		AccountActionPhase accountPhase = AccountPhaseFactory.getActionAccountPhase();

		accountPhase.executePhase(param);
	}

	public String render(StrutsPortletAction originalStrutsPortletAction, PortletConfig portletConfig, RenderRequest renderRequest, RenderResponse renderResponse) throws Exception {
		String renderTo = StringPool.BLANK;

		String action = ParamUtil.getString(renderRequest, RENDER_ACTION, StringPool.BLANK);
		if (ACTIVATE_DEACTIVATE.equals(action)) {
			renderTo = executeActivateDeactivateRender(renderRequest);

		} else {
			renderTo = executeRenderAction(originalStrutsPortletAction, portletConfig, renderRequest, renderResponse);
		}

		return renderTo;
	}

	private String executeActivateDeactivateRender(RenderRequest renderRequest) throws PortalException, SystemException {
		String userIds = ParamUtil.getString(renderRequest, MyAccountConstants.USERS_IDS_PARAMETER);
		String[] ids = StringUtils.split(userIds, ",");
		List<User> selectedUsers = new ArrayList<User>();

		for (String userIdStr : ids) {
			long userId = Long.parseLong(userIdStr);
			User user = UserLocalServiceUtil.getUser(userId);
			selectedUsers.add(user);
		}

		renderRequest.setAttribute(MyAccountConstants.USERS_IDS_PARAMETER, userIds);
		renderRequest.setAttribute(MyAccountConstants.SELECTED_USERS, selectedUsers);
		renderRequest.setAttribute(Constants.CMD, ParamUtil.getString(renderRequest, Constants.CMD));

		return USER_STATE_POP_UP;
	}

	private String executeRenderAction(StrutsPortletAction originalStrutsPortletAction, PortletConfig portletConfig, RenderRequest renderRequest, RenderResponse renderResponse) throws Exception {

		// TODO: is this necessary at activate/deactivate???
		addExtraDaysSituation(renderRequest);

		// TODO: is this necessary at activate/deactivate???
		addStartDates(renderRequest);

		// TODO: is this necessary at activate/deactivate???
		addEmploymentPeriods(renderRequest);

		// TODO: is this necessary at activate/deactivate???
		addNewUserMandatoryFields(renderRequest);

		// TODO: is this necessary at activate/deactivate???
		addUserPosition(renderRequest);

		// TODO: is this necessary at activate/deactivate???
		handleInvalidFamilyMembersEntries(renderRequest);

		// for testing purposes
		renderRequest.setAttribute("test", ParamUtil.getBoolean(renderRequest, "test", false));

		return originalStrutsPortletAction.render(originalStrutsPortletAction, portletConfig, renderRequest, renderResponse);
	}

	private void addUserPosition(RenderRequest renderRequest) {
		boolean isHR = false;
		boolean isAdmin = false;
		boolean isUserManagement = false;

		try {
			User currentUser = PortalUtil.getUser(renderRequest);

			isHR = EvoportalUserUtil.isHR(currentUser.getUserId());
			isAdmin = EvoportalUserUtil.isAdmin(currentUser);
			isUserManagement = EvoportalUserUtil.isUserManagement(currentUser);
		} catch (PortalException e) {
			logger.error(e);
		} catch (SystemException e) {
			logger.error(e);
		}

		renderRequest.setAttribute("isHR", isHR);
		renderRequest.setAttribute("isAdmin", isAdmin);
		renderRequest.setAttribute("isUserManagement", isUserManagement);
	}

	private void addEmploymentPeriods(RenderRequest renderRequest) throws PortalException, SystemException {
		Map<String, String> employmentMap = new LinkedHashMap<String, String>();
		User selectedUser = PortalUtil.getSelectedUser(renderRequest);

		if (selectedUser != null) {
			UserEmploymentPeriodsHandler employment = new UserEmploymentPeriodsHandler(selectedUser);

			DateInterval internshipInterval = employment.getInternshipInterval();
			String internship = formEmploymentIntervalMessage(internshipInterval);
			if (!internship.isEmpty()) {
				employmentMap.put("Internship Period", internship);
			}

			DateInterval scholarshipInterval = employment.getScholarshipInterval();
			String scholarship = formEmploymentIntervalMessage(scholarshipInterval);
			if (!scholarship.isEmpty()) {
				employmentMap.put("Scholarship Period", scholarship);
			}

			DateInterval cimInterval = employment.getCIMInterval();
			String cim = formEmploymentIntervalMessage(cimInterval);
			if (!cim.isEmpty()) {
				employmentMap.put("CIM Period", cim);
			}
		}

		renderRequest.setAttribute("employments", employmentMap.entrySet());
	}

	private String formEmploymentIntervalMessage(DateInterval interval) {
		StringBuffer employmentMessage = new StringBuffer();

		if (interval == null) {
			return employmentMessage.toString();
		}

		if (!interval.hasEndDate()) {
			employmentMessage.append(new SimpleDateFormat(MyAccountConstants.DATE_FORMAT_DD_MM_YYYY).format(interval.getStartDate()));
			employmentMessage.append(StringPool.SPACE);
			employmentMessage.append(StringPool.MINUS);
			employmentMessage.append(StringPool.SPACE);
			employmentMessage.append(PRESENT);
			return employmentMessage.toString();
		}

		if (interval.isValid() && !interval.isOneDayInterval()) {
			employmentMessage.append(interval.toString());
		}

		return employmentMessage.toString();
	}

	private void addNewUserMandatoryFields(RenderRequest renderRequest) throws PortalException, SystemException {
		User selectedUser = PortalUtil.getSelectedUser(renderRequest);

		if (selectedUser == null) {
			long companyId = PortalUtil.getCompanyId(renderRequest);
			long powerUserRoleId = RoleLocalServiceUtil.getRole(companyId, RoleConstants.POWER_USER).getRoleId();
			long editorPortalRoleId = RoleLocalServiceUtil.getRole(companyId, MyAccountConstants.EDITOR_PORTAL).getRoleId();
			renderRequest.setAttribute("newUserRoles", powerUserRoleId + "," + editorPortalRoleId);

			long groupId = GroupLocalServiceUtil.getGroup(companyId, GroupConstants.GUEST).getGroupId();
			renderRequest.setAttribute("newUserGroups", groupId + ",");
		}
	}

	private void addExtraDaysSituation(RenderRequest renderRequest) throws PortalException, SystemException {
		int evozonBonusDays = 0;
		User selectedUser = PortalUtil.getSelectedUser(renderRequest);

		if (selectedUser != null) {
			List<BenefitDay> list = BenefitDayLocalServiceUtil.getBenefitDayOfType(selectedUser.getUserId(), "EXTRA_DAYS");
			renderRequest.setAttribute("extraDaysList", list);

			renderRequest.setAttribute("freeDaysHistory", getHistory(selectedUser));
			evozonBonusDays = BonusDaysComputer.computeBonusEVZ(selectedUser, true);
			if (evozonBonusDays < 0) {
				evozonBonusDays = 0;
			}

		}

		renderRequest.setAttribute("evozonBonusDays", evozonBonusDays);
	}

	private void addStartDates(RenderRequest renderRequest) {
		// Add the internship start date if available
		addStartDateInternship(renderRequest);

		// Add the start date. This must be available
		addDateHired(renderRequest);

		// Add the CIM start date if available
		addStartDateCIM(renderRequest);
	}

	private void addStartDateCIM(RenderRequest renderRequest) {
		String startDateCIM = StringPool.BLANK;

		try {
			String cmd = ParamUtil.getString(renderRequest, Constants.CMD, StringPool.BLANK);
			if (Constants.ADD.equals(cmd)) {
				String userType = ParamUtil.getString(renderRequest, "user_type", StringPool.BLANK);
				if (userType.equals("cim")) {
					startDateCIM = MyAccountUtil.convertCalendarToString(Calendar.getInstance(), MyAccountConstants.ZEBRA_DATE_PICKER_DATE_FORMAT);
				}

			} else if (Constants.UPDATE.equals(cmd) || cmd.isEmpty()) {
				User selectedUser = PortalUtil.getSelectedUser(renderRequest);

				if (selectedUser != null) {
					UserEmploymentPeriodsHandler periods = new UserEmploymentPeriodsHandler(selectedUser);
					if (periods.isCIMStartDateValid()) {
						startDateCIM = MyAccountUtil.convertCalendarToString(periods.getStartDateCIM(), MyAccountConstants.ZEBRA_DATE_PICKER_DATE_FORMAT);
					}
				}
			}

		} catch (PortalException e) {
			logger.warn(e);
		} catch (SystemException e) {
			logger.warn(e);
		}

		renderRequest.setAttribute("user.cim.start", startDateCIM);

	}

	private void addStartDateInternship(RenderRequest renderRequest) {
		String startDateInternship = StringPool.BLANK;

		try {
			String cmd = ParamUtil.getString(renderRequest, Constants.CMD, StringPool.BLANK);
			if (Constants.ADD.equals(cmd)) {
				String userType = ParamUtil.getString(renderRequest, "user_type", StringPool.BLANK);
				if (userType.equals("internship")) {
					startDateInternship = MyAccountUtil.convertCalendarToString(Calendar.getInstance(), MyAccountConstants.ZEBRA_DATE_PICKER_DATE_FORMAT);
				}

			} else if (Constants.UPDATE.equals(cmd) || cmd.isEmpty()) {
				User selectedUser = PortalUtil.getSelectedUser(renderRequest);

				if (selectedUser != null) {
					UserEmploymentPeriodsHandler periods = new UserEmploymentPeriodsHandler(selectedUser);

					if (periods.hasInternshipStartDate()) {
						startDateInternship = MyAccountUtil.convertCalendarToString(periods.getInternshipStartDate(), MyAccountConstants.ZEBRA_DATE_PICKER_DATE_FORMAT);
					}
				}
			}

		} catch (PortalException e) {
			logger.warn(e);
		} catch (SystemException e) {
			logger.warn(e);
		}

		renderRequest.setAttribute("user.internship.start", startDateInternship);
	}

	private void addDateHired(RenderRequest renderRequest) {
		String dateHired = StringPool.BLANK;

		try {
			String cmd = ParamUtil.getString(renderRequest, Constants.CMD, StringPool.BLANK);
			if (Constants.ADD.equals(cmd)) {
				dateHired = MyAccountUtil.convertCalendarToString(Calendar.getInstance(), MyAccountConstants.ZEBRA_DATE_PICKER_DATE_FORMAT);

			} else if (Constants.UPDATE.equals(cmd) || cmd.isEmpty()) {

				if (SessionErrors.contains(renderRequest, INVALID_DATE_HIRED_ERROR_KEY)) {
					Calendar invalidDate = (Calendar) SessionMessages.get(renderRequest, "invalidDateHired");
					dateHired = MyAccountUtil.convertCalendarToString(invalidDate, MyAccountConstants.ZEBRA_DATE_PICKER_DATE_FORMAT);

				} else {
					User selectedUser = PortalUtil.getSelectedUser(renderRequest);

					if (selectedUser != null) {
						Date hiredDate = new UserExpandoWrapper(selectedUser).getDateHired();
						dateHired = MyAccountUtil.convertDateToString(hiredDate, MyAccountConstants.ZEBRA_DATE_PICKER_DATE_FORMAT);
					}

				}
			}
		} catch (PortalException e) {
			logger.error(e);

		} catch (SystemException e) {
			logger.error(e);

		} catch (Exception e) {
			logger.error(e);
		}

		renderRequest.setAttribute("user.date.hired", dateHired);
	}

	public static List<FreeDaysHistoryEntry> getHistory(User user) {
		List<FreeDaysHistoryEntry> result = Collections.emptyList();
		try {
			result = FreeDaysHistoryEntryLocalServiceUtil.getAllHistory(user.getUserId());
		} catch (SystemException e) {
			logger.error(e.getMessage(), e);
		}

		return result;
	}

}
