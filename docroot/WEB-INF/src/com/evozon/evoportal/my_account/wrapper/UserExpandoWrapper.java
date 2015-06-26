package com.evozon.evoportal.my_account.wrapper;

import java.util.Calendar;
import java.util.Date;

import org.apache.log4j.Logger;

import com.evozon.evoportal.my_account.util.MyAccountConstants;
import com.evozon.evoportal.my_account.util.MyAccountUtil;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;

public class UserExpandoWrapper {

	private static final Logger log = Logger.getLogger(UserExpandoWrapper.class);

	private User user;

	public UserExpandoWrapper(User user) {
		if (user == null) {
			throw new NullPointerException("The user expando must be specified.");
		}
		this.user = user;
	}

	public UserExpandoWrapper(long userId) {
		try {
			this.user = UserLocalServiceUtil.getUser(userId);
		} catch (Exception e) {
			// TODO: check if the throw is necessary
			throw new RuntimeException("Can't retrieve expando bridge for userId: " + userId, e);
		}
	}

	public String getPersonalIdentificationNumber() {
		return user.getExpandoBridge().getAttribute(MyAccountConstants.PERSONAL_IDENTIFICATION_NUMBER, false).toString();
	}

	public void setPersonalIdentificationNumber(String cnp) {
		user.getExpandoBridge().setAttribute(MyAccountConstants.PERSONAL_IDENTIFICATION_NUMBER, cnp, false);
	}

	public Date getDateHired() {
		return (Date) user.getExpandoBridge().getAttribute(MyAccountConstants.DATE_HIRED_EXPANDO_ATR, false);
	}

	public void setDateHired(Date dateHired) {
		user.getExpandoBridge().setAttribute(MyAccountConstants.DATE_HIRED_EXPANDO_ATR, dateHired, false);
	}

	public String getLicensePlate() {
		return user.getExpandoBridge().getAttribute(MyAccountConstants.LICENSE_PLATE, false).toString();
	}

	public void setCIMStartDate(Date cimStart) {
		user.getExpandoBridge().setAttribute(MyAccountConstants.CIM_START_DATE, cimStart, false);
	}

	public void setInternshipStartDate(Date internshipDate) {
		user.getExpandoBridge().setAttribute(MyAccountConstants.INTERNSHIP_START_DATE, internshipDate, false);
	}

	public void setStartingBonusDays(int startingBonusDays) {
		user.getExpandoBridge().setAttribute(MyAccountConstants.STARTING_BONUS_DAYS, String.valueOf(startingBonusDays), false);
	}

	public void setFreeDaysInCurrentYear(int freeDaysInCurrentYear) {
		user.getExpandoBridge().setAttribute(MyAccountConstants.FREE_DAYS_CURRENT_EXPANDO_ATR, String.valueOf(freeDaysInCurrentYear), false);
	}

	public void setExtractedFreeDaysFromCurrentYear(int extractedFreeDays) {
		user.getExpandoBridge().setAttribute(MyAccountConstants.EXTRACTED_FREE_DAYS_FROM_CURRENT_YEAR, String.valueOf(extractedFreeDays), false);
	}

	public void setExtractedFreeDaysFromLastYear(int extractedFreeDays) {
		user.getExpandoBridge().setAttribute(MyAccountConstants.EXTRACTED_FREE_DAYS_FROM_LAST_YEAR, String.valueOf(extractedFreeDays), false);
	}

	public void addNewStatusLog(int status, long createdDate, long statusChangeDate) {
		try {
			JSONArray statusArray = getStatusArrayLog();
			if (statusArray == null) {
				statusArray = JSONFactoryUtil.createJSONArray();
			}

			JSONObject newStatus = JSONFactoryUtil.createJSONObject();
			newStatus.put("status", status);
			newStatus.put("createdDate", createdDate);
			newStatus.put("statusChangeDate", statusChangeDate);

			statusArray.put(newStatus);

			user.getExpandoBridge().setAttribute(MyAccountConstants.EVOZON_USER_STATUS, statusArray.toString(), false);
		} catch (JSONException e) {
			log.error(e);
		}
	}

	public void setUserTypeCreation(String userType) {
		user.getExpandoBridge().setAttribute(MyAccountConstants.USER_ENTRY_TYPE, userType, false);
	}

	public void getuserEntryType(String userType) {
		user.getExpandoBridge().setAttribute(MyAccountConstants.USER_ENTRY_TYPE, userType, false);
	}

	public Date getCurrentStatusChangeDate() {
		Date statusChangeDate = null;

		JSONObject currentStatus = getCurrentStatus();
		if (currentStatus != null) {
			long statusChangeDateInMillis = currentStatus.getLong("statusChangeDate");
			statusChangeDate = new Date(statusChangeDateInMillis);
		}

		return statusChangeDate;
	}

	public int getStatusWorkflowStatus() {
		int workflowStatus = -1;

		JSONObject currentStatus = getCurrentStatus();
		if (currentStatus != null) {
			workflowStatus = currentStatus.getInt("status");
		}

		return workflowStatus;
	}

	private JSONObject getCurrentStatus() {
		JSONObject currentStatus = null;
		try {
			JSONArray statusArray = getStatusArrayLog();
			if (statusArray != null) {

				int statusCount = statusArray.length();
				if (statusCount > 0) {
					currentStatus = statusArray.getJSONObject(statusCount - 1);
				}
			}
		} catch (JSONException e) {
			log.error(e);
		}

		return currentStatus;
	}

	public void removeCurrentStatusLog() {
		try {
			JSONArray statusArray = getStatusArrayLog();
			JSONArray newStatusArray = JSONFactoryUtil.createJSONArray();

			int statusCount = statusArray.length();
			if (statusCount > 1) {
				for (int i = 0; i < statusCount - 1; i++) {
					JSONObject oldStatus = statusArray.getJSONObject(i);
					newStatusArray.put(oldStatus);
				}
			}
			user.getExpandoBridge().setAttribute(MyAccountConstants.EVOZON_USER_STATUS, newStatusArray.toString());
		} catch (Exception e) {
			log.error("Error getting current status for user: " + user.getFullName() + " in removeCurrentStatus()", e);
		}
	}

	private JSONArray getStatusArrayLog() throws JSONException {
		String jsonStatusStr = (String) user.getExpandoBridge().getAttribute(MyAccountConstants.EVOZON_USER_STATUS, false);

		// creating hole status object
		JSONArray statusArray = null;
		if (!jsonStatusStr.isEmpty()) {
			statusArray = JSONFactoryUtil.createJSONArray(jsonStatusStr);
		}

		return statusArray;
	}

	public void setRemainingFreeDaysFromLastYear(int remainingFreeDaysFromLastYear) {
		int lastYear = Calendar.getInstance().get(Calendar.YEAR) - 1;
		setRemainingFreeDaysFromYear(lastYear, remainingFreeDaysFromLastYear);
	}

	public void setRemainingFreeDaysFromYear(int year, int remainingFreeDaysFromYear) {
		try {
			String remDaysFromLastStr = user.getExpandoBridge().getAttribute(MyAccountConstants.REMAINING_FREE_DAYS_LAST, false).toString();

			JSONObject yearsSituation = JSONFactoryUtil.createJSONObject(remDaysFromLastStr);

			JSONObject newYearJson = JSONFactoryUtil.createJSONObject().put(MyAccountConstants.DAYS_LEFT, remainingFreeDaysFromYear);
			yearsSituation.put(String.valueOf(year), newYearJson);

			user.getExpandoBridge().setAttribute(MyAccountConstants.REMAINING_FREE_DAYS_LAST, yearsSituation.toString());

		} catch (JSONException e) {
			log.error("Couldn't retrieve remaining free days from last year for user: " + user.getFullName());
		}
	}

	public void setFreeDaysFromLastYear(int freeDaysFromLastYear) {
		user.getExpandoBridge().setAttribute(MyAccountConstants.FREE_DAYS_LAST_YEAR_EXPANDO_ATR, String.valueOf(freeDaysFromLastYear));
	}

	public void setLegalVacationDays(int legalVacationDays) {
		user.getExpandoBridge().setAttribute(MyAccountConstants.LEGAL_VACATION_DAYS, String.valueOf(legalVacationDays));
	}

	public void setBuilding(String building) {
		user.getExpandoBridge().setAttribute(MyAccountConstants.BUILDING, new String[] { building });
	}

	public void setExtraDays(int extraDays) {
		user.getExpandoBridge().setAttribute(MyAccountConstants.EXTRA_DAYS_CURRENT, String.valueOf(extraDays));
	}

	public Date getCIMStartDate() {
		return (Date) user.getExpandoBridge().getAttribute(MyAccountConstants.CIM_START_DATE, false);
	}

	public Date getInternshipStartDate() {
		return (Date) user.getExpandoBridge().getAttribute(MyAccountConstants.INTERNSHIP_START_DATE, false);
	}

	public int getFreeDaysFromLastYear() {
		String lastYear = user.getExpandoBridge().getAttribute(MyAccountConstants.FREE_DAYS_LAST_YEAR_EXPANDO_ATR, false).toString();
		return MyAccountUtil.getIntValue(lastYear);
	}

	public int getFreeDaysInCurrentYear() {
		String currentYear = user.getExpandoBridge().getAttribute(MyAccountConstants.FREE_DAYS_CURRENT_EXPANDO_ATR, false).toString();
		return MyAccountUtil.getIntValue(currentYear);
	}

	public int getLegalVacationDays() {
		int legalVacationDays = 0;
		try {
			String legalVacDaysStr = user.getExpandoBridge().getAttribute(MyAccountConstants.LEGAL_VACATION_DAYS, false).toString();
			legalVacationDays = Integer.parseInt(legalVacDaysStr);
		} catch (ClassCastException cce) {
			log.error("Could not retrieve Legal Vacation Days for: " + user.getFullName());
		}

		return legalVacationDays;
	}

	public int getStartingBonusDays() {
		String startingBonus = user.getExpandoBridge().getAttribute(MyAccountConstants.STARTING_BONUS_DAYS, false).toString();
		return MyAccountUtil.getIntValue(startingBonus);
	}

	public int getRemainingFreeDaysFromLastYear() {
		int lastYear = Calendar.getInstance().get(Calendar.YEAR) - 1;
		return getRemainingFreeDaysFromYear(lastYear);
	}

	public int getRemainingFreeDaysFromYear(int year) {
		int remainingFromYear = 0;

		try {
			String remDaysFromLastStr = user.getExpandoBridge().getAttribute(MyAccountConstants.REMAINING_FREE_DAYS_LAST, false).toString();
			if (!remDaysFromLastStr.isEmpty()) {
				JSONObject yearsSituation = JSONFactoryUtil.createJSONObject(remDaysFromLastStr);

				String pastYearStr = String.valueOf(year);
				if (yearsSituation.has(pastYearStr)) {
					JSONObject yearJSON = yearsSituation.getJSONObject(pastYearStr);
					remainingFromYear = yearJSON.getInt(MyAccountConstants.DAYS_LEFT);
				}
			}

		} catch (JSONException e) {
			log.error("Couldn't retrieve remaining free days from last year for user: " + user.getFullName());
		}

		return remainingFromYear;
	}

	public String getComments() {
		return user.getExpandoBridge().getAttribute(MyAccountConstants.COMMENTS_EXPANDO_ATR, false).toString();
	}

	public int getExtraDays() {
		String startingBonus = user.getExpandoBridge().getAttribute(MyAccountConstants.EXTRA_DAYS_CURRENT, false).toString();
		return MyAccountUtil.getIntValue(startingBonus);
	}

	public int getExtractedFreeDaysFromCurrentYear() {
		String extractedStr = user.getExpandoBridge().getAttribute(MyAccountConstants.EXTRACTED_FREE_DAYS_FROM_CURRENT_YEAR, false).toString();
		return MyAccountUtil.getIntValue(extractedStr);
	}

	public String getUserJob() {
		String userJob = StringPool.BLANK;

		String[] jobs = (String[]) user.getExpandoBridge().getAttribute(MyAccountConstants.JOB_ATTRIBUTE);
		if ((jobs != null) && (jobs.length > 0)) {
			userJob = jobs[0];
		}

		return userJob;
	}

	public void setMonthsOfExperienceInEvozon(int monthsOfExperienceInEvozon) {
		user.getExpandoBridge().setAttribute(MyAccountConstants.EVOZON_EXPERIENCE_EXPANDO_ATR, String.valueOf(monthsOfExperienceInEvozon));
	}

	public void setComments(String comments) {
		user.getExpandoBridge().setAttribute(MyAccountConstants.COMMENTS_EXPANDO_ATR, comments);
	}

	public String getUserEntryType() {
		return user.getExpandoBridge().getAttribute(MyAccountConstants.USER_ENTRY_TYPE, false).toString();
	}

	public String getBuilding() {
		String userBuilding = StringPool.BLANK;

		String[] buildings = (String[]) user.getExpandoBridge().getAttribute(MyAccountConstants.BUILDING);
		if ((buildings != null) && (buildings.length > 0)) {
			userBuilding = buildings[0];
		}

		return userBuilding;
	}

	public String getFaculty() {
		return user.getExpandoBridge().getAttribute(MyAccountConstants.FACULTY, false).toString();
	}

	public String getUniversity() {
		return user.getExpandoBridge().getAttribute(MyAccountConstants.UNIVERSITY, false).toString();
	}

	public boolean isPhoneNumberHidden() {
		return (Boolean) user.getExpandoBridge().getAttribute(MyAccountConstants.HIDE_PHONES, false);
	}

	public void setPhoneNumberHidden(boolean isHidePhones) {
		user.getExpandoBridge().setAttribute(MyAccountConstants.HIDE_PHONES, isHidePhones, false);
	}

	public void setBirthdayHidden(boolean isHideBirthday) {
		user.getExpandoBridge().setAttribute(MyAccountConstants.HIDE_BIRTHDAY, isHideBirthday, false);
	}

	public boolean isBirthdayHidden() {
		return (Boolean) user.getExpandoBridge().getAttribute(MyAccountConstants.HIDE_BIRTHDAY, false);
	}

	public boolean getMarried() {
		return (Boolean) user.getExpandoBridge().getAttribute(MyAccountConstants.MARRIED, false);
	}

	public boolean isStudent() {
		return (Boolean) user.getExpandoBridge().getAttribute(MyAccountConstants.STUDENT, false);
	}

	public void setIsStudentHidden(boolean isStudent) {
		user.getExpandoBridge().setAttribute(MyAccountConstants.STUDENT, isStudent, false);
	}

	public String getDiplomaTitle() {
		return user.getExpandoBridge().getAttribute(MyAccountConstants.DIPLOMA_TITLE, false).toString();
	}

	public void setOfficialName(String officialName) {
		user.getExpandoBridge().setAttribute(MyAccountConstants.OFFICIAL_NAME_ATTRIBUTE, officialName, false);
	}

	public long getUserId() {
		return user.getUserId();
	}

	public User getUser() {
		return user;
	}

	// TODO: these two methods don't belong here
	public boolean isInInternship() {
		return getUserJob().equalsIgnoreCase("intern");
	}

	public boolean isInScholarship() {
		return getUserJob().toLowerCase().contains("bursier");
	}

}
