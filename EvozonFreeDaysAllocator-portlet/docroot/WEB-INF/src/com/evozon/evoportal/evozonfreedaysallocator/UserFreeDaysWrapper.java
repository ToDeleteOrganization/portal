package com.evozon.evoportal.evozonfreedaysallocator;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import org.apache.log4j.Logger;

import com.evozon.evoportal.evozonfreedaysallocator.service.BenefitDayLocalServiceUtil;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;

public class UserFreeDaysWrapper {

	private static final Logger log = Logger.getLogger(UserFreeDaysWrapper.class);

	private static final String FREE_DAYS_IN_CURRENT_YEAR = "Free Days In Current Year";

	private static final String EXTRACTED_FREE_DAYS_FROM_CURRENT_YEAR = "Extracted Free Days From Current Year";

	private static final String EXTRACTED_FREE_DAYS_FROM_LAST_YEAR = "Extracted Free Days From Last Year";

	private static final String FREE_DAYS_FROM_LAST_YEAR = "Free Days From Last Year";

	private static final String REMAINING_FREE_DAYS_LAST = "Remaining Free Days From Last Year";

	private static final String MONTHS_OF_EXPERIENCE_IN_EVOZON = "Months Of Experience In Evozon";

	private static final String VACATION_DAYS_TAKEN_HISTORY = "Vacation days taken history";

	private static final String INTERNSHIP_START_DATE = "Internship Start Date";

	private static final String DATE_HIRED = "Date Hired";

	private static final String CIM_START_DATE = "Cim Start Date";

	private static final String STARTING_BONUS_DAYS = "Starting Bonus Days";

	private static final String EVOZON_USER_STATUS = "Evozon User Status";

	private static final String LEGAL_VACATION_DAYS = "Legal Vacation Days";

	private static final String EXTRA_DAYS = "Extra Days";

	public static final String FUNCTIE_CIM = "Functie CIM";

	private User user;

	public UserFreeDaysWrapper(long userId) {
		try {
			user = UserLocalServiceUtil.getUser(userId);
		} catch (Exception e) {
			throw new NullPointerException("The user with the id: " + userId + ", couldn't be obtained.");
		}
	}

	public UserFreeDaysWrapper(User user) {
		if (user == null) {
			throw new NullPointerException("The user cannot be null.");
		}
		this.user = user;
	}

	public void setDateHired(Date dateHired) {
		setExpandoAttribute(DATE_HIRED, dateHired);
	}

	public void setCIMStartDate(Date cimStart) {
		setExpandoAttribute(CIM_START_DATE, cimStart);
	}

	// Free Days In Current Year
	public void setFreeDaysInCurrentYear(int freeDaysInCurrentYear) {
		setExpandoAttribute(FREE_DAYS_IN_CURRENT_YEAR, String.valueOf(freeDaysInCurrentYear));
	}

	public void setExtraDays(int extraDays) {
		setExpandoAttribute(EXTRA_DAYS, String.valueOf(extraDays));
	}

	public void setRemainingFreeDaysFromLastYear(int freeDaysFromLastYear) {
		int lastYear = BenefitDayLocalServiceUtil.getUserSelectedDate().get(Calendar.YEAR) - 1;
		setRemainingFreeDaysInYear(lastYear, freeDaysFromLastYear);
	}

	public void setRemainingFreeDaysInYear(int year, int freeDaysFromLastYear) {
		try {
			String remDaysFromLastStr = getExpandoAttribute(REMAINING_FREE_DAYS_LAST).toString();

			JSONObject yearsSituation = JSONFactoryUtil.createJSONObject(remDaysFromLastStr);

			JSONObject newYearJson = JSONFactoryUtil.createJSONObject().put("daysLeft", freeDaysFromLastYear);
			yearsSituation.put(String.valueOf(year), newYearJson);

			setExpandoAttribute(REMAINING_FREE_DAYS_LAST, yearsSituation.toString());
		} catch (JSONException e) {
			log.error("Couldn't retrieve remaining free days from last year for user: " + user.getFullName());
		}
	}

	public void setVacationDaysTakenInYear(int year, int vacationDaysTaken) {
		try {
			String daysTaken = getExpandoAttribute(VACATION_DAYS_TAKEN_HISTORY).toString();

			JSONObject yearsSituation = JSONFactoryUtil.createJSONObject(daysTaken);

			JSONObject newYearJson = JSONFactoryUtil.createJSONObject().put("daysTaken", vacationDaysTaken);
			yearsSituation.put(String.valueOf(year), newYearJson);

			setExpandoAttribute(VACATION_DAYS_TAKEN_HISTORY, yearsSituation.toString());
		} catch (JSONException e) {
			log.error("Couldn't retrieve remaining free days from last year for user: " + user.getFullName());
		}
	}

	public int getVacationDaysTakenInYear(int year) {
		int daysTakenInYear = 0;
		try {
			String yearStr = String.valueOf(year);
			String daysTaken = getExpandoAttribute(VACATION_DAYS_TAKEN_HISTORY).toString();

			JSONObject yearsSituation = JSONFactoryUtil.createJSONObject(daysTaken);

			if (yearsSituation.has(yearStr)) {
				JSONObject yearJSON = yearsSituation.getJSONObject(yearStr);
				daysTakenInYear = yearJSON.getInt("daysTaken");
			}

		} catch (JSONException e) {
			log.error("Couldn't retrieve remaining free days from last year for user: " + user.getFullName());
		}
		return daysTakenInYear;
	}

	public int getMonthsOfExperienceInEvozon() {
		int monthsOfExperienceInEvozon = 0;

		try {
			String currExperienceInEvozon = getExpandoAttribute(MONTHS_OF_EXPERIENCE_IN_EVOZON).toString();
			monthsOfExperienceInEvozon = Integer.parseInt(currExperienceInEvozon);
		} catch (ClassCastException cce) {
			log.error("Couldn't retrieve months of experince in evozon for user: " + user.getFullName());
		}

		return monthsOfExperienceInEvozon;
	}

	public Date getInternshipStartDate() {
		return getExpandoDate(INTERNSHIP_START_DATE);
	}

	public Date getDateHired() {
		return getExpandoDate(DATE_HIRED);
	}

	public Date getCIMStartDate() {
		return getExpandoDate(CIM_START_DATE);
	}

	private Date getExpandoDate(String dateAttributeName) {
		Date hiredDate = null;
		try {
			hiredDate = (Date) getExpandoAttribute(dateAttributeName);
		} catch (ClassCastException cce) {
			log.error("Can't get date [" + dateAttributeName + "] from expando for user: " + user.getFullName());
		}
		return hiredDate;
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

	public Date getPenultimateStatusChangeDate() {
		Date statusChangeDate = null;

		JSONObject currentStatus = getPenultimateStatus();
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

	public int getPenultimateStatusWorkflowStatus() {
		int workflowStatus = -1;

		JSONObject currentStatus = getPenultimateStatus();
		if (currentStatus != null) {
			workflowStatus = currentStatus.getInt("status");
		}

		return workflowStatus;
	}

	private JSONObject getCurrentStatus() {
		JSONObject currentStatus = null;
		try {
			JSONArray statusArray = getStatusArray();
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

	private JSONObject getPenultimateStatus() {
		JSONObject currentStatus = null;
		try {
			JSONArray statusArray = getStatusArray();
			if (statusArray != null) {

				int statusCount = statusArray.length();
				if (statusCount > 1) {
					currentStatus = statusArray.getJSONObject(statusCount - 2);
				}
			}
		} catch (JSONException e) {
			log.error(e);
		}

		return currentStatus;
	}

	public JSONArray getStatusArray() throws JSONException {
		String jsonStatusStr = (String) user.getExpandoBridge().getAttribute(EVOZON_USER_STATUS, false);

		// creating hole status object
		JSONArray statusArray = null;
		if (!jsonStatusStr.isEmpty()) {
			statusArray = JSONFactoryUtil.createJSONArray(jsonStatusStr);
		}

		return statusArray;
	}

	public int getStatusCount() {
		int statusCount = 0;
		try {
			statusCount = getStatusArray().length();
		} catch (JSONException e) {
			log.error(e);
		}
		return statusCount;
	}

	public void setLegalVacationDays(int legalVacationDays) {
		setExpandoAttribute(LEGAL_VACATION_DAYS, String.valueOf(legalVacationDays));
	}

	public int getLegalVacationDays() {
		int legalVacationDays = 0;
		try {
			String legalVacDaysStr = getExpandoAttribute(LEGAL_VACATION_DAYS).toString();
			legalVacationDays = Integer.parseInt(legalVacDaysStr);
			System.out.println("legalVacationDays = " + legalVacationDays);
		} catch (ClassCastException cce) {
			log.error("Could not retrieve Legal Vacation Days for: " + user.getFullName());
		}

		return legalVacationDays;
	}

	public int getExtraDays() {
		int extraDays = 0;

		try {
			String extraDaysStr = getExpandoAttribute(EXTRA_DAYS).toString();
			extraDays = Integer.parseInt(extraDaysStr);
		} catch (ClassCastException cce) {
			log.error("Could not retrieve Extra Days for: " + user.getFullName());
		}
		return extraDays;
	}

	public int getFreeDaysInCurrentYear() {
		int freeDaysInCurrentYear = 0;
		try {
			String freeDays = getExpandoAttribute(FREE_DAYS_IN_CURRENT_YEAR).toString();
			freeDaysInCurrentYear = Integer.parseInt(freeDays);
		} catch (ClassCastException cce) {
			log.error("Couldn't retrieve the free days in current year for user: " + user.getFullName());
		}
		return freeDaysInCurrentYear;
	}

	public int getStartingBonusDays() {
		int freeDaysInCurrentYear = 0;
		try {
			String freeDays = getExpandoAttribute(STARTING_BONUS_DAYS).toString();
			freeDaysInCurrentYear = Integer.parseInt(freeDays);
		} catch (ClassCastException cce) {
			log.error("Couldn't retrieve the free days in current year for user: " + user.getFullName());
		}
		return freeDaysInCurrentYear;
	}

	public String getUserJob() {
		String userJob = StringPool.BLANK;

		String[] jobs = (String[]) user.getExpandoBridge().getAttribute(FUNCTIE_CIM);
		if ((jobs != null) && (jobs.length > 0)) {
			userJob = jobs[0];
		}

		return userJob;
	}

	public int getRemainingFreeDaysFromLastYear() {
		int lastYear = Calendar.getInstance().get(Calendar.YEAR) - 1;
		return getRemainingDaysFromYear(lastYear);
	}

	public int getRemainingDaysFromYear(int year) {
		int remainingDaysFromPastYear = 0;

		try {
			String remDaysFromLastStr = getExpandoAttribute(REMAINING_FREE_DAYS_LAST).toString();
			if (!remDaysFromLastStr.isEmpty()) {
				JSONObject yearsSituation = JSONFactoryUtil.createJSONObject(remDaysFromLastStr);

				String pastYearStr = String.valueOf(year);
				if (yearsSituation.has(pastYearStr)) {
					JSONObject yearJSON = yearsSituation.getJSONObject(pastYearStr);
					remainingDaysFromPastYear = yearJSON.getInt("daysLeft");
				}
			}

		} catch (JSONException e) {
			log.error("Couldn't retrieve remaining free days from last year for user: " + user.getFullName());
		}
		return remainingDaysFromPastYear;
	}

	// Free Days From Last Year
	public void setFreeDaysFromLastYear(int freeDaysFromLastYear) {
		setExpandoAttribute(FREE_DAYS_FROM_LAST_YEAR, String.valueOf(freeDaysFromLastYear));
	}

	public int getFreeDaysFromLastYear() {
		int freeDaysFromLastYear = 0;
		try {
			Serializable expandoAttribute = getExpandoAttribute(FREE_DAYS_FROM_LAST_YEAR);
			String remainigDays = expandoAttribute.toString();
			freeDaysFromLastYear = Integer.parseInt(remainigDays);
		} catch (ClassCastException cce) {
			log.error("couldn't retrieve the free days in current year for user: " + user.getFullName());
		}
		return freeDaysFromLastYear;
	}

	public int getTotalFreeDaysForUser() {
		int freeDaysInCurrentYear = getFreeDaysInCurrentYear();
		int freeDaysFromLastYear = getFreeDaysFromLastYear();
		int extraDays = getExtraDays();

		int totalFreeDays = freeDaysInCurrentYear + freeDaysFromLastYear + extraDays;

		return totalFreeDays;
	}

	// Extracted Free Days From Current Year
	public void setExtractedFreeDaysFromCurrentYear(int extractedFreeDays) {
		setExpandoAttribute(EXTRACTED_FREE_DAYS_FROM_CURRENT_YEAR, String.valueOf(extractedFreeDays));
	}

	public int getExtractedFreeDaysFromCurrentYear() {
		int extractedFreeDaysFromCurrentYear = 0;
		try {
			String extractedDays = getExpandoAttribute(EXTRACTED_FREE_DAYS_FROM_CURRENT_YEAR).toString();
			extractedFreeDaysFromCurrentYear = Integer.parseInt(extractedDays);
		} catch (ClassCastException cce) {
			log.error("couldn't retrieve the free days in current year for user: " + user.getFullName());
		}
		return extractedFreeDaysFromCurrentYear;
	}

	// Extracted Free Days From Last Year
	public int getExtractedDaysFromLastYear() {
		int extractedFreeDaysFromLastYear = 0;
		try {
			String extractedDays = getExpandoAttribute(EXTRACTED_FREE_DAYS_FROM_LAST_YEAR).toString();
			extractedFreeDaysFromLastYear = Integer.parseInt(extractedDays);
		} catch (ClassCastException cce) {
			log.error("couldn't retrieve the free days in current year for user: " + user.getFullName());
		}
		return extractedFreeDaysFromLastYear;
	}

	public void setExtractedFreeDaysFromLastYear(int extractedFreeDays) {
		setExpandoAttribute(EXTRACTED_FREE_DAYS_FROM_LAST_YEAR, String.valueOf(extractedFreeDays));
	}

	// Set/Get Expando attributes
	public void setExpandoAttribute(String attributeName, Serializable attributeValue) {
		user.getExpandoBridge().setAttribute(attributeName, attributeValue, false);
	}

	private Serializable getExpandoAttribute(String attributeName) {
		return user.getExpandoBridge().getAttribute(attributeName, false);
	}

	public User getUser() {
		return user;
	}

	public long getUserId() {
		return user.getUserId();
	}

//	TODO: these two methods don't belong here
	public boolean isInInternship() {
		return getUserJob().equalsIgnoreCase("intern");
	}

	public boolean isInScholarship() {
		return getUserJob().toLowerCase().contains("bursier");
	}
}
