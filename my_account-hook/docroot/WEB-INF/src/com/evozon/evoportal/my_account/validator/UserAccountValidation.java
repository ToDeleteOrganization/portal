package com.evozon.evoportal.my_account.validator;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.portlet.ActionRequest;
import javax.portlet.PortletSession;

import org.apache.commons.lang.StringUtils;

import com.evozon.evoportal.evozonnationalfreedaystableaccess.slayer.model.NationalFreeDaysEntry;
import com.evozon.evoportal.evozonnationalfreedaystableaccess.slayer.service.NationalFreeDaysEntryLocalServiceUtil;
import com.evozon.evoportal.familytableaccess.slayer.model.FamilyMember;
import com.evozon.evoportal.my_account.managers.UserFamilyHandler;
import com.evozon.evoportal.my_account.util.MyAccountConstants;
import com.evozon.evoportal.my_account.util.MyAccountRequestUtil;
import com.evozon.evoportal.my_account.util.MyAccountUtil;
import com.evozon.evoportal.my_account.wrapper.UserExpandoWrapper;
import com.liferay.portal.UserEmailAddressException;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.GroupConstants;
import com.liferay.portal.model.User;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.expando.model.ExpandoColumn;
import com.liferay.portlet.expando.model.ExpandoTable;
import com.liferay.portlet.expando.service.ExpandoColumnLocalServiceUtil;
import com.liferay.portlet.expando.service.ExpandoTableLocalServiceUtil;

// TODO: use bundle messages in this class
// TODO: use AccountModel 
public class UserAccountValidation extends AccountValidator {

	private static Log logger = LogFactoryUtil.getLog(UserAccountValidation.class);

	public static final String INVALID_DATE_HIRED_ERROR_KEY = "invalid-date-hired-error-key";

	private static final String VALID_EMAIL_ADDRESS = "evozon.com";

	private static final String LIFERAY_NEW_LINE = "liferay:new-line";

	private static final String NO_DEPARTMENT_DETECTED = "no-department-detected";

	private static final int CNP_LENGTH = 13;

	private static final int MAX_LICENSE_PLATE_LENGTH = 50;

	public boolean validate(ActionRequest actionRequest, UserFamilyHandler familyManager) {
		boolean isValid = true;

		isValid &= hasUserUpdatableFieldsValid(actionRequest, familyManager);
		if (MyAccountRequestUtil.isUserAdministrationUpdate(actionRequest)) {
			isValid &= hasUserAdministrationFieldsValid(actionRequest);
		}

		return isValid;
	}

	private boolean hasUserAdministrationFieldsValid(ActionRequest actionRequest) {
		boolean isValid = true;

		boolean isContractDetailsValid = isContractDetailsValid(actionRequest);
		if (!isContractDetailsValid) {
			SessionErrors.add(actionRequest, "hasContractDetailsErrors");
			isValid = false;
			logger.error("Contract details are not valid, abort user action.");
		}

		boolean isSitesValid = isSitesValid(actionRequest);
		if (!isSitesValid) {
			SessionErrors.add(actionRequest, "hasSitesErrors");
			isValid = false;
			logger.error("User departments are not valid, abort user action.");
		}

		return isValid;
	}

	private boolean hasUserUpdatableFieldsValid(ActionRequest actionRequest, UserFamilyHandler familyManager) {
		boolean isValid = true;

		boolean isPersonalDetailsValid = isPersonalDetailsValid(actionRequest);
		if (!isPersonalDetailsValid) {
			SessionErrors.add(actionRequest, "hasPersonalDetailsErrors");
			isValid = false;
			logger.error("Personal details are not valid, abort user creation.");
		}

		boolean isFamilyValid = isFamilyValid(actionRequest, familyManager);
		if (!isFamilyValid) {
			isValid = false;

			SessionErrors.add(actionRequest, "hasFamilyErrors");

			PortletSession portletSession = actionRequest.getPortletSession();
			portletSession.setAttribute("oldFamily", familyManager.getOldModel());
			portletSession.setAttribute("newFamily", familyManager.getNewModel());

			logger.error("Family is not valid, abort user action.");
		}

		return isValid;
	}

	public boolean isFamilyValid(ActionRequest actionRequest, UserFamilyHandler familyManager) {
		boolean isValid = true;

		FamilyMemberValidator familyValidator = new FamilyMemberValidator(actionRequest);

		// check new children
		for (FamilyMember addedChild : familyManager.getAddedChildren()) {
			familyValidator.setExistingMemberId(String.valueOf(addedChild.getMemberId()));

			boolean isChildValid = familyValidator.validate(addedChild);
			if (!isChildValid) {
				isValid = false;
			}
		}

		// check updated children
		List<FamilyMember> invalidUpdates = new ArrayList<FamilyMember>();
		for (FamilyMember updatedChild : familyManager.getUpdatedChildren()) {
			familyValidator.setExistingMemberId(String.valueOf(updatedChild.getMemberId()));

			boolean isChildValid = familyValidator.validate(updatedChild);
			if (!isChildValid) {
				isValid = false;
				invalidUpdates.add(updatedChild);
			}
		}

		// check spouse modification
		if (familyManager.isSpouseAdded() || familyManager.isSpouseUpdated()) {
			FamilyMember newSpouse = familyManager.getNewSpouse();

			boolean isSpouseValid = familyValidator.validate(newSpouse);
			if (!isSpouseValid) {
				isValid = false;
			}
		}

		return isValid;
	}

	public boolean isContractDetailsValid(ActionRequest actionRequest) {
		boolean isValid = true;

		isValid &= verifyCNP(actionRequest);
		isValid &= verifyFunctieCIM(actionRequest);
		isValid &= verifyDateHired(actionRequest);
		isValid &= verifyJobTitle(actionRequest);

		return isValid;
	}

	public boolean isPersonalDetailsValid(ActionRequest actionRequest) {
		boolean isValid = true;

		// Verify both user and management fields.
		isValid &= verifyScreenName(actionRequest);
		isValid &= verifyEmailAddress(actionRequest);
		isValid &= verifyFirstName(actionRequest);
		isValid &= verifyLastName(actionRequest);
		isValid &= verifyLicensePlate(actionRequest);
		isValid &= verifyPhoneNumber(actionRequest);
		isValid &= verifyAdditionalEmailAddress(actionRequest);
		isValid &= isBirthdayAfterDateHired(actionRequest);

		return isValid;
	}

	private boolean isBirthdayAfterDateHired(ActionRequest actionRequest) {
		boolean isBirthdayAfterDateHired = true;

		Calendar birthdayCal = MyAccountUtil.getCalendarFromDate(MyAccountRequestUtil.getBirthdayFromRequest(actionRequest));
		Calendar newHiredCal = MyAccountUtil.getCalendarFromDate(MyAccountRequestUtil.getDateHired(actionRequest));

		if ((birthdayCal != null) && (newHiredCal != null) && (birthdayCal.compareTo(newHiredCal) >= 0)) {
			String userId = ParamUtil.getString(actionRequest, "userId");
			SessionErrors.add(actionRequest, "birthday-after-hire");
			isBirthdayAfterDateHired = false;

			logger.error("Date hired cannot be BEFORE user's birthday: " + userId);
		}

		return isBirthdayAfterDateHired;
	}

	private boolean verifyAdditionalEmailAddress(ActionRequest actionRequest) {
		// TODO
		// Use default in details.
		// <liferay-ui:error exception="<%= EmailAddressException.class %>"
		// message="please-enter-a-valid-email-address"
		// />
		// <liferay-ui:error
		// key="<%= NoSuchListTypeException.class.getName() + className + ListTypeConstants.EMAIL_ADDRESS %>"
		// message="please-select-a-type" />

		return true;
	}

	private boolean verifyPhoneNumber(ActionRequest actionRequest) {
		String phoneNumber = ParamUtil.getString(actionRequest, "phoneNumber", StringPool.BLANK);

		phoneNumber = phoneNumber.replace(",", StringPool.BLANK);
		phoneNumber = phoneNumber.replace("-", StringPool.BLANK);
		phoneNumber = phoneNumber.replace(";", StringPool.BLANK);
		phoneNumber = phoneNumber.replace("/", StringPool.BLANK);
		phoneNumber = phoneNumber.replace(".", StringPool.BLANK);
		phoneNumber = phoneNumber.replace(" ", StringPool.BLANK);

		if (!isNumericInput(phoneNumber)) {
			SessionErrors.add(actionRequest, "invalid-phone-number");
			return false;
		}

		return true;
	}

	private boolean verifyFunctieCIM(ActionRequest actionRequest) {
		boolean isValid = true;

		String functieCIM = ParamUtil.getString(actionRequest, MyAccountConstants.EXPANDO_JOB_ATTRIBUTE);
		if (functieCIM.equals(LIFERAY_NEW_LINE)) {
			isValid = false;
			SessionErrors.add(actionRequest, "please-specify-functiecim");
		} else {
			SessionMessages.add(actionRequest, "previousFunctieCIM", functieCIM);
		}

		return isValid;
	}

	private boolean verifyLicensePlate(ActionRequest actionRequest) {
		boolean isValid = true;

		String licensePlate = ParamUtil.getString(actionRequest, MyAccountConstants.EXPANDO_LICENSE_PLATE);
		if (isInputLengthValid(licensePlate, MAX_LICENSE_PLATE_LENGTH)) {
			SessionErrors.add(actionRequest, "license-plate-invalid-length-error");
			isValid = false;
		}

		// accepted characters
		licensePlate = licensePlate.replaceAll("-", StringUtils.EMPTY);
		licensePlate = licensePlate.replaceAll(",", StringUtils.EMPTY);
		licensePlate = licensePlate.replaceAll(" ", StringUtils.EMPTY);

		if (!isAlphaNumericInput(licensePlate)) {
			SessionErrors.add(actionRequest, "license-plate-invalid-characters-error");
			isValid = false;
		}

		return isValid;
	}

	public boolean verifyCNP(ActionRequest actionRequest) {
		boolean isValid = true;

		isValid &= isCNPContentValid(actionRequest);
		isValid &= isCNPDuplicate(actionRequest);

		return isValid;
	}

	private boolean isCNPContentValid(ActionRequest actionRequest) {
		boolean isValid = true;
		String cnp = ParamUtil.getString(actionRequest, MyAccountConstants.USER_CNP);

		if (StringUtils.length(cnp) != CNP_LENGTH) {
			SessionErrors.add(actionRequest, "cnp-invalid-length-error");
			isValid = false;
		}

		if (!isNumericInput(cnp)) {
			SessionErrors.add(actionRequest, "cnp-invalid-characters-error");
			isValid = false;
		}

		return isValid;
	}

	private boolean isCNPDuplicate(ActionRequest actionRequest) {
		boolean isValid = true;
		try {
			User selectedUser = PortalUtil.getSelectedUser(actionRequest);

			if (selectedUser != null) {

				String oldCNP = new UserExpandoWrapper(selectedUser).getPersonalIdentificationNumber();
				String newCNP = ParamUtil.getString(actionRequest, MyAccountConstants.USER_CNP);

				if (!oldCNP.equals(newCNP)) {
					// only if CNP was changed
					long userIdCNP = PortalUtil.getUserId(actionRequest);
					List<String> usersWithSameCNP = getUsersWithCNP(newCNP, userIdCNP);
					if (!usersWithSameCNP.isEmpty()) {
						String errMsg = "The following users have the same CNP: " + usersWithSameCNP.toString();

						SessionErrors.add(actionRequest, "duplicate-cnp", errMsg);
						isValid = false;
					}

				}
			}
		} catch (PortalException e) {
			logger.error(e.getMessage(), e);
		} catch (SystemException e) {
			logger.error(e.getMessage(), e);
		}

		return isValid;
	}

	public boolean isSitesValid(ActionRequest actionRequest) {
		boolean hasValidSites = true;
		try {
			long[] groupIdSplit = ParamUtil.getLongValues(actionRequest, "groupsSearchContainerPrimaryKeys", new long[] {});

			if (groupIdSplit.length > 0) {
				Set<Long> asSet = SetUtil.fromArray(groupIdSplit);
				long guestGroupId = GroupLocalServiceUtil.getGroup(PortalUtil.getCompanyId(actionRequest), GroupConstants.GUEST).getGroupId();

				if (asSet.contains(guestGroupId)) {
					asSet.remove(guestGroupId);
				}

				if (asSet.isEmpty()) {
					// the user has to be on EvoPortal and at least another
					// department
					logger.warn("At least one department has to be specified besides Evoportal.");
					hasValidSites = false;
				}
			} else {
				logger.debug("No departments are specified.");
				hasValidSites = false;
			}

		} catch (Exception e) {
			logger.error("Error getting new user sites.", e);
		}

		if (!hasValidSites) {
			SessionErrors.add(actionRequest, NO_DEPARTMENT_DETECTED);
		}

		return hasValidSites;
	}

	private List<String> getUsersWithCNP(String newCNP, long userIdCNP) {
		List<String> userWithSameCNP = new ArrayList<String>();

		try {

			ExpandoTable userExpandoTable = ExpandoTableLocalServiceUtil.getDefaultTable(10154, User.class.getName());
			ExpandoColumn filedColumn = ExpandoColumnLocalServiceUtil.getColumn(userExpandoTable.getTableId(), "Personal Identification Number");

			Map<String, Object> modelAttributes = filedColumn.getModelAttributes();
			System.out.println(modelAttributes);

			List<User> users = UserLocalServiceUtil.getUsers(QueryUtil.ALL_POS, QueryUtil.ALL_POS);

			for (User user : users) {

				if (user.getUserId() != userIdCNP) {
					String userCNP = new UserExpandoWrapper(user).getPersonalIdentificationNumber();
					if (newCNP.equals(userCNP)) {
						userWithSameCNP.add(user.getFullName());
					}
				}

			}
		} catch (Exception e) {
			logger.error(e);
		}
		return userWithSameCNP;
	}

	private boolean verifyJobTitle(ActionRequest actionRequest) {
		boolean isValid = true;

		String jobTitle = ParamUtil.getString(actionRequest, "jobTitle", StringPool.BLANK);
		jobTitle = jobTitle.replace(StringPool.PERIOD, StringPool.BLANK).replace(StringPool.MINUS, StringPool.BLANK);

		if (isInputLengthValid(jobTitle)) {
			SessionErrors.add(actionRequest, "job-title-invalid-length-error");
			isValid = false;
		}

		if (!isAlphaSpaceStringInput(jobTitle)) {
			SessionErrors.add(actionRequest, "job-title-invalid-characters-error");
			isValid = false;
		}

		return isValid;
	}

	private boolean verifyFirstName(ActionRequest actionRequest) {
		boolean isValid = true;

		String firstName = ParamUtil.getString(actionRequest, "firstName");
		// accepted characters

		if (isInputLengthValid(firstName)) {
			SessionErrors.add(actionRequest, "first-name-invalid-length-error");
			isValid = false;
		}

		firstName = firstName.replaceAll("-", StringUtils.EMPTY);
		firstName = firstName.replaceAll("\\(", StringUtils.EMPTY);
		firstName = firstName.replaceAll("\\)", StringUtils.EMPTY);
		if (!isAlphaSpaceStringInput(firstName)) {
			SessionErrors.add(actionRequest, "first-name-invalid-characters-error");
			isValid = false;
		}

		return isValid;
	}

	private boolean verifyLastName(ActionRequest actionRequest) {
		boolean isValid = true;

		String lastName = ParamUtil.getString(actionRequest, "lastName", StringPool.BLANK);
		// accepted characters

		if (isInputLengthValid(lastName)) {
			SessionErrors.add(actionRequest, "last-name-invalid-length-error");
			isValid = false;
		}

		lastName = lastName.replaceAll("-", StringUtils.EMPTY);
		lastName = lastName.replaceAll("\\(", StringUtils.EMPTY);
		lastName = lastName.replaceAll("\\)", StringUtils.EMPTY);
		if (!isAlphaSpaceStringInput(lastName)) {
			SessionErrors.add(actionRequest, "last-name-invalid-characters-error");
			isValid = false;
		}

		return isValid;
	}

	private boolean verifyScreenName(ActionRequest actionRequest) {
		String screenName = ParamUtil.getString(actionRequest, "screenName");
		boolean isValid = true;
		// screen name should contain only letters, digits or dot '.'

		if (isInputLengthValid(screenName)) {
			SessionErrors.add(actionRequest, "screen-name-invalid-length-error");
			isValid = false;
		}

		screenName = screenName.replace(".", "");
		if (isEmptyInput(screenName) || isNumericInput(screenName)) {
			// screen name must contain at least one letter
			SessionErrors.add(actionRequest, "screen-name-must-contain-at-least-one-letter-error");
			isValid = false;
		}

		if (!isAlphaNumericInput(screenName)) {
			// screen name should contain only letters, numbers or dots.
			SessionErrors.add(actionRequest, "screen-name-invalid-characters-error");
			isValid = false;
		}

		return isValid;
	}

	private boolean verifyEmailAddress(ActionRequest actionRequest) {
		boolean valid = true;
		String emailAddress = actionRequest.getParameter(MyAccountConstants.EMAIL_ADDRESS);

		String delimiter = "@";
		String emailDomain = emailAddress.split(delimiter)[1];
		if (!emailDomain.equalsIgnoreCase(VALID_EMAIL_ADDRESS)) {
			valid = false;
			UserEmailAddressException exc = new UserEmailAddressException("Not a company email address!");
			SessionErrors.add(actionRequest, exc.getClass());
		}

		return valid;
	}

	private boolean verifyDateHired(ActionRequest actionRequest) {
		// add more date hired validation
		return isDateHiredInBusinessDay(actionRequest);
	}

	private boolean isDateHiredInBusinessDay(ActionRequest actionRequest) {
		boolean isDateHiredInWorkingDay = true;
		Calendar hiredDateCal = MyAccountUtil.getCalendarFromDate(MyAccountRequestUtil.getDateHired(actionRequest));

		if (hiredDateCal != null) {
			if (isWeekendDay(hiredDateCal)) {
				isDateHiredInWorkingDay = false;
			}

			if (isNationalFreeDay(hiredDateCal)) {
				isDateHiredInWorkingDay = false;
			}

			if (!isDateHiredInWorkingDay) {
				long selectedUserId = ParamUtil.getLong(actionRequest, "userId");
				prepareInvalidDateHiredRender(actionRequest, selectedUserId);
			}
		}

		return isDateHiredInWorkingDay;
	}

	public Date getDateHired(ActionRequest actionRequest, long selectedUserId) {
		Date hiredDate = null;

		int requestDay = ParamUtil.getInteger(actionRequest, "hiredDay", -1);
		if (requestDay == -1) {
			UserExpandoWrapper userExpando = new UserExpandoWrapper(selectedUserId);
			hiredDate = userExpando.getDateHired();

		} else {
			hiredDate = MyAccountRequestUtil.getDateHired(actionRequest);
		}

		return hiredDate;
	}

	private void prepareInvalidDateHiredRender(ActionRequest actionRequest, long selectedUserId) {
		Date hiredDate = MyAccountRequestUtil.getDateHired(actionRequest);
		Calendar hiredDateCal = Calendar.getInstance();
		hiredDateCal.setTime(hiredDate);
		SessionMessages.add(actionRequest, "invalidDateHired", hiredDateCal);
		SessionErrors.add(actionRequest, INVALID_DATE_HIRED_ERROR_KEY);
	}

	private boolean isNationalFreeDay(Calendar hiredDate) {
		try {
			List<NationalFreeDaysEntry> nationalFreeDaysEntries = NationalFreeDaysEntryLocalServiceUtil.getNationalFreeDaysEntries(QueryUtil.ALL_POS, QueryUtil.ALL_POS);

			for (NationalFreeDaysEntry freeDay : nationalFreeDaysEntries) {
				Calendar freeDayCal = MyAccountUtil.getCalendarFromDate(freeDay.getDate());

				boolean isNationalFreeDay = MyAccountUtil.isSameDayAndMonth(hiredDate, freeDayCal);

				if (!isNationalFreeDay) {
					continue;
				}

				int availableUntil = freeDay.getAvailableUntil();
				if ((availableUntil == -1) || (freeDay.getAvailableFrom() == availableUntil)) {
					// if this is a free day only in a past year, check the
					// year
					isNationalFreeDay &= (hiredDate.get(Calendar.YEAR) == freeDayCal.get(Calendar.YEAR));
				} else if (availableUntil == 0) {
					// if this a free day through all years, check the year it
					// was introduced as a free day
					isNationalFreeDay &= (hiredDate.get(Calendar.YEAR) >= freeDay.getAvailableFrom());
				}

				if (isNationalFreeDay) {
					return true;
				}
			}
		} catch (SystemException e) {
			logger.warn("Error comparing current hired date to national free days.", e);
		}

		return false;
	}

	private boolean isWeekendDay(Calendar cal) {
		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);

		boolean isSaturday = (dayOfWeek == Calendar.SATURDAY);
		boolean isSunday = (dayOfWeek == Calendar.SUNDAY);

		return isSaturday || isSunday;
	}

}
