package com.evozon.evoportal.my_account.util;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;


import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.evozon.evoportal.evozonprojects.model.ProjectGroup;
import com.evozon.evoportal.evozonprojects.service.ProjectGroupLocalServiceUtil;
import com.evozon.evoportal.my_account.AccountModelHolder;
import com.evozon.evoportal.my_account.managers.DetailsManager;
import com.evozon.evoportal.my_account.managers.EvoAddressManager;
import com.evozon.evoportal.my_account.model.EvoAddressModel;
import com.evozon.evoportal.my_account.model.UserAccountOperation;
import com.evozon.evoportal.ws.pmreports.util.PMReportsConstants;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.User;
import com.liferay.portlet.expando.model.ExpandoColumn;
import com.liferay.portlet.expando.model.ExpandoTable;
import com.liferay.portlet.expando.service.ExpandoColumnLocalServiceUtil;
import com.liferay.portlet.expando.service.ExpandoTableLocalServiceUtil;

public class MyAccountUtil {

	private static Log logger = LogFactoryUtil.getLog(MyAccountUtil.class);

	private static Map<String, String> pmReportsDepartmentsAssociations = new LinkedHashMap<String, String>();

	static {
		pmReportsDepartmentsAssociations.put("HR Department", "HR");
		pmReportsDepartmentsAssociations.put("QA Department", "QA");
		pmReportsDepartmentsAssociations.put("Java Department", "Java");
		pmReportsDepartmentsAssociations.put("IT Support Department", "IT");
		pmReportsDepartmentsAssociations.put("Perl Department", "Perl");
		pmReportsDepartmentsAssociations.put(".Net Department", ".Net");
		pmReportsDepartmentsAssociations.put("Mobile Department", "Mobile");
		pmReportsDepartmentsAssociations.put("Ixxus Department", "Ixxus");
		pmReportsDepartmentsAssociations.put("Accounting Department", "Accounting");
		pmReportsDepartmentsAssociations.put("Sales&Marketing", "Sales");
		pmReportsDepartmentsAssociations.put("Games Department", "Games");
		pmReportsDepartmentsAssociations.put("Web Department", "Web Development");

		// these doesn't exist as departments, but as projects
		pmReportsDepartmentsAssociations.put("Navision", "Navision");
	}

	private static final String HR_DEPARTMENT_NAME = "HR Department";

	private static final String DATE_FORMAT_MONTH_DAY_YEAR = "MM/dd/yyyy";

	public static int getIntValue(String field) {
		int result = 0;
		try {
			result = Integer.parseInt(field);
		} catch (NumberFormatException e) {
			result = -1;
			logger.warn(e.getMessage());
		}
		return result;
	}

	public static Calendar getCalendarFromDate(Date date) {
		Calendar cal = null;
		if (date != null) {
			cal = Calendar.getInstance();
			cal.setTime(date);
		}
		return cal;
	}

	/**
	 * Converts a calendar object to a string format with a given format.
	 * 
	 * @param cal
	 *            The calendar
	 * @param format
	 *            The format
	 * @return The date in a given string format or <i>null</i> in case of
	 *         error.
	 */
	public static String convertCalendarToString(Calendar cal, String format) {
		return convertDateToString(cal.getTime(), format);
	}

	public static String convertDateToString(Date date, String format) {
		String dateString = null;

		try {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			dateString = sdf.format(date);

		} catch (NullPointerException npe) {
			logger.warn("Can't parse date [" + date + "], with format [" + format + "].");

		} catch (IllegalArgumentException iae) {
			logger.warn("Can't parse date [" + date + "], with format [" + format + "].");

		}

		return dateString;
	}

	public static Date convertStringToDate(String dateInput, String pattern, Locale locale) throws ParseException {
		Date stringToDate = null;

		if (!dateInput.isEmpty() && !pattern.isEmpty()) {
			DateFormat df = new SimpleDateFormat(pattern, locale);
			stringToDate = df.parse(dateInput);
		}

		return stringToDate;
	}

	public static boolean isSameDayAndMonth(Calendar firstDate, Calendar secondDate) {
		int firstDateDay = firstDate.get(Calendar.DAY_OF_MONTH);
		int firstDateMonth = firstDate.get(Calendar.MONTH);

		int secondDateDay = secondDate.get(Calendar.DAY_OF_MONTH);
		int secondDateMonth = secondDate.get(Calendar.MONTH);

		return ((firstDateMonth == secondDateMonth) && (firstDateDay == secondDateDay));
	}

	public static boolean isSameCalendar(Calendar cal1, Calendar cal2) {
		boolean result = false;

		boolean ifSameDayAndMonth = isSameDayAndMonth(cal1, cal2);
		boolean sameYear = cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR);
		result = ifSameDayAndMonth && sameYear;

		boolean equals = DateUtil.equals(cal1.getTime(), cal2.getTime(), true);
		System.out.println("DateUtil = " + equals + "  >>>>>>>  result = " + result);

		return result;
	}

	// DateUtil.equals(date1, date2, true) - try using this
	public static boolean isSameDate(Date date1, Date date2) {
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(date1);

		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(date2);

		return isSameCalendar(cal1, cal2);
	}

	public static Serializable getDefaultUserCustomFieldValue(long companyId, String customFieldName) throws PortalException, SystemException {
		ExpandoTable userExpandoTable = ExpandoTableLocalServiceUtil.getDefaultTable(companyId, User.class.getName());
		ExpandoColumn filedColumn = ExpandoColumnLocalServiceUtil.getColumn(userExpandoTable.getTableId(), customFieldName);
		return filedColumn.getDefaultValue();
	}

	public static boolean isPartOfHR(User user) {
		return isPartOfGroup(user, HR_DEPARTMENT_NAME);
	}

	public static boolean hasPMReportsDataModified(AccountModelHolder oldModel, AccountModelHolder newModel) {
		DetailsManager detailsManager = new DetailsManager(oldModel.getDetailsModel(), newModel.getDetailsModel());
		if (detailsManager.isEmailAddressChanged()) {
			return true;
		}
		if (detailsManager.isLastNameChanged()) {
			return true;
		}
		if (detailsManager.isFirstNameChanged()) {
			return true;
		}
		if (detailsManager.isLicensePlateChanged()) {
			return true;
		}
		if (detailsManager.isPhoneNumberChanged()) {
			return true;
		}
		Calendar oldStartDate = resetToMidnight(oldModel.getFreeDaysModel().getStartDate());
		Calendar newStartDate = resetToMidnight(newModel.getFreeDaysModel().getStartDate());
		if (oldStartDate.compareTo(newStartDate) != 0) {
			return true;
		}

		EvoAddressModel oldPrimaryAddress = oldModel.getPrimaryAddress();
		EvoAddressModel newPrimaryAddress = newModel.getPrimaryAddress();
		if ((oldPrimaryAddress == null) && (newPrimaryAddress == null)) {
			return false;

		} else if ((oldPrimaryAddress != null) && (newPrimaryAddress == null)) {
			return true;

		} else if ((oldPrimaryAddress == null) && (newPrimaryAddress != null)) {
			return true;
		}

		EvoAddressManager addressManager = new EvoAddressManager(oldPrimaryAddress, newPrimaryAddress);
		if (addressManager.isStreetNameChanged()) {
			return true;
		}

		if (addressManager.isStreetNumberChanged()) {
			return true;
		}

		if (addressManager.isCityChanged()) {
			return true;
		}

		if (addressManager.isCountryCodeChanged()) {
			return true;
		}

		return false;
	}

	// TODO: use DateUtil.truncate
	public static Calendar resetToMidnight(Date date) {
		Calendar cal = null;

		if (date != null) {
			cal = Calendar.getInstance();
			cal.setTime(date);
			cal.set(Calendar.MILLISECOND, 0);
			cal.set(Calendar.SECOND, 0);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.HOUR_OF_DAY, 0);
		}

		return cal;
	}

	public static boolean isPrimaryAddressChanged(EvoAddressModel oldPrimaryAddress, EvoAddressModel newPrimaryAddress) {
		if ((oldPrimaryAddress == null) && (newPrimaryAddress == null)) {
			return false;

		} else if ((oldPrimaryAddress != null) && (newPrimaryAddress == null)) {
			return true;

		} else if ((oldPrimaryAddress == null) && (newPrimaryAddress != null)) {
			return true;
		}

		EvoAddressManager addressManager = new EvoAddressManager(oldPrimaryAddress, newPrimaryAddress);
		if (addressManager.isStreetNameChanged()) {
			return true;
		}

		if (addressManager.isStreetNumberChanged()) {
			return true;
		}

		if (addressManager.isCityChanged()) {
			return true;
		}

		if (addressManager.isCountryCodeChanged()) {
			return true;
		}
		return false;
	}

	public static MultiValueMap<String, Object> getPMReportsParameters(AccountModelHolder oldModel, AccountModelHolder newModel, User selectedUser, UserAccountOperation commandType) {
		MultiValueMap<String, Object> mapModifications = new LinkedMultiValueMap<String, Object>();

		if (!UserAccountOperation.ADD_USER.equals(commandType) && !hasPMReportsDataModified(oldModel, newModel)) {
			return mapModifications;
		}

		String userCNP = UserAccountOperation.USER_UPDATE.equals(commandType) ? oldModel.getDetailsModel().getCNP() : newModel.getDetailsModel().getCNP();
		mapModifications.add(PMReportsConstants.USER_CNP, userCNP);
		mapModifications.add(PMReportsConstants.USER_EMAIL, newModel.getDetailsModel().getEmailAddress());
		mapModifications.add(PMReportsConstants.USER_LAST_NAME, newModel.getDetailsModel().getLastName());
		mapModifications.add(PMReportsConstants.USER_FIRST_NAME, newModel.getDetailsModel().getFirstName());
		mapModifications.add(PMReportsConstants.USER_DATE_HIRED, convertDateToString(newModel.getFreeDaysModel().getStartDate(), DATE_FORMAT_MONTH_DAY_YEAR));

		String pmDep = findPMReportsAssociatedDepartment(newModel, selectedUser);
		if (!pmDep.isEmpty()) {
			mapModifications.add(PMReportsConstants.DEPARTMENT_NAME, pmDep);
		}

		String countryCode = null;
		EvoAddressModel primaryAddress = newModel.getPrimaryAddress();
		if (primaryAddress != null) {
			mapModifications.add(PMReportsConstants.USER_ZIP_CODE, primaryAddress.getPostalCode());
			mapModifications.add(PMReportsConstants.USER_CITY_NAME, primaryAddress.getCity());

			countryCode = primaryAddress.getCountryCode();
			if (countryCode.isEmpty()) {
				countryCode = PMReportsConstants.USER_DEFAULT_COUNTRY_CODE;
			}

			mapModifications.add(PMReportsConstants.USER_STREET_NAME, primaryAddress.getStreetName());
			mapModifications.add(PMReportsConstants.USER_STREET_NUMBER, primaryAddress.getStreetNumber());
		}
		// The 'country code' is mandatory
		mapModifications.add(PMReportsConstants.USER_COUNTRY_CODE, (countryCode == null) ? PMReportsConstants.USER_DEFAULT_COUNTRY_CODE : countryCode);

		mapModifications.add(PMReportsConstants.USER_MOBILE_NUMBER, newModel.getDetailsModel().getPhoneNumber());
		mapModifications.add(PMReportsConstants.USER_PLATE_NUMBER, newModel.getDetailsModel().getLicensePlate());

		return mapModifications;
	}

	private static String findPMReportsAssociatedDepartment(AccountModelHolder newModel, User selectedUser) {
		List<String> userDepartments = newModel.getUserDepartments();

		// if the user is part of the HR Department
		if (userDepartments.contains(HR_DEPARTMENT_NAME)) {
			return pmReportsDepartmentsAssociations.get(HR_DEPARTMENT_NAME);
		}

		// check user projects, ex. 'Navision' is a dep. in PM and a project in
		// EvoPortal.
		String pmReportsDepartmentName = StringPool.BLANK;
		if (selectedUser != null) {

			List<ProjectGroup> userProjects = ProjectGroupLocalServiceUtil.findProjectsByUser(selectedUser.getUserId());
			for (ProjectGroup pGroup : userProjects) {
				String name = pGroup.getProjectGroupName();
				if (pmReportsDepartmentsAssociations.containsKey(name)) {
					pmReportsDepartmentName = name;
					break;
				}
			}

		}

		if (!pmReportsDepartmentName.isEmpty()) {
			return pmReportsDepartmentName;
		}

		// last option check department names
		for (String departmentName : userDepartments) {
			if (pmReportsDepartmentsAssociations.containsKey(departmentName)) {
				pmReportsDepartmentName = pmReportsDepartmentsAssociations.get(departmentName);
				break;
			}
		}

		return pmReportsDepartmentName;
	}

	// TODO: check if this can't be optimized using code in comments
	private static boolean isPartOfGroup(User user, String groupName) {
		// try {
		// Group group = GroupLocalServiceUtil.getGroup(user.getCompanyId(),
		// HR_GROUP_NAME);
		// if (group != null) {
		// UserLocalServiceUtil.hasGroupUser(group.getGroupId(),
		// user.getUserId());
		// }
		// } catch (Exception e1) {
		// e1.printStackTrace();
		// }

		boolean isPartOfGroup = false;
		try {
			for (Group group : user.getGroups()) {
				if (group.getName().equalsIgnoreCase(groupName)) {
					isPartOfGroup = true;
					break;
				}
			}
		} catch (PortalException e) {
			logger.error(e.getMessage(), e);
		} catch (SystemException e) {
			logger.error(e.getMessage(), e);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

		return isPartOfGroup;
	}

	private MyAccountUtil() {
	}

}
