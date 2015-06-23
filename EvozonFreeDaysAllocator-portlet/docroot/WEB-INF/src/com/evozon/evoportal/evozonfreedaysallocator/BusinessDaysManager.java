package com.evozon.evoportal.evozonfreedaysallocator;

import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.evozon.evoportal.evozonfreedaysallocator.service.BenefitDayLocalServiceUtil;
import com.evozon.evoportal.evozonnationalfreedaystableaccess.slayer.model.NationalFreeDaysEntry;
import com.evozon.evoportal.evozonnationalfreedaystableaccess.slayer.service.NationalFreeDaysEntryLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.SystemException;

public class BusinessDaysManager {

	public static int getBusinessDaysBetweenTwoDates(Calendar startDate, Calendar endDate) throws SystemException {
		// we don't want to change the initial start date
		Calendar startDateAux = Calendar.getInstance();
		startDateAux.set(Calendar.DAY_OF_MONTH, startDate.get(Calendar.DAY_OF_MONTH));
		startDateAux.set(Calendar.MONTH, startDate.get(Calendar.MONTH));
		startDateAux.set(Calendar.YEAR, startDate.get(Calendar.YEAR));
		// set to 00:00:00:00, if not will be a problem when comparing dates
		startDateAux.set(Calendar.HOUR_OF_DAY, 0);
		startDateAux.set(Calendar.MINUTE, 0);
		startDateAux.set(Calendar.SECOND, 0);
		startDateAux.set(Calendar.MILLISECOND, 0);

		int workDays = 0;

		// Return -1 if start > end date
		if (startDateAux.compareTo(endDate) > 0) {
			return -1;
		}
		// get the declared National Free Days
		List<NationalFreeDaysEntry> nationalFreeDays = NationalFreeDaysEntryLocalServiceUtil.getActiveNationalFreeDaysEntries(QueryUtil.ALL_POS, QueryUtil.ALL_POS);

		Set<Integer> holidays = new HashSet<Integer>();
		for (NationalFreeDaysEntry freeDay : nationalFreeDays) {
			Calendar day = Calendar.getInstance();
			day.setTime(freeDay.getDate());
			holidays.add((Integer) day.get(Calendar.DAY_OF_YEAR));
		}

		while (startDateAux.compareTo(endDate) <= 0) {
			// if week day(M-F) and not a national free day
			if (startDateAux.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY && startDateAux.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY
					&& !holidays.contains((Integer) startDateAux.get(Calendar.DAY_OF_YEAR))) {
				++workDays;
			}
			// go to the next day
			startDateAux.add(Calendar.DAY_OF_YEAR, 1);
		}
		return workDays;
	}

	public static int getBusinessDaysInCurrentYear() throws SystemException {
		return getBusinessDaysInYear(Calendar.getInstance().get(Calendar.YEAR));
	}

	public static int getBusinessDaysInYear(int year) throws SystemException {
		Calendar startYear = getStartYear(year);
		Calendar endYear = getEndYear(year);
		return getBusinessDaysBetweenTwoDates(startYear, endYear);
	}

	public static Calendar getCurrentStartYear() {
		// 01 January current year
		Calendar startYear = BenefitDayLocalServiceUtil.getUserSelectedDate();
		return getStartYear(startYear.get(Calendar.YEAR));
	}

	public static Calendar getCurrentEndYear() {
		// 31 December given year
		Calendar endYear = BenefitDayLocalServiceUtil.getUserSelectedDate();
		return getEndYear(endYear.get(Calendar.YEAR));
	}

	public static Calendar getStartYear(int year) {
		return dateToCalendar(year, 1, Calendar.JANUARY, true);
	}

	public static Calendar getEndYear(int year) {
		return dateToCalendar(year, 31, Calendar.DECEMBER, true);
	}

	private static Calendar dateToCalendar(int year, int month, int dayOfMonth, boolean dayStart) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.DAY_OF_MONTH, month);
		calendar.set(Calendar.MONTH, dayOfMonth);

		if (dayStart) {
			calendar.set(Calendar.MILLISECOND, 0);
			calendar.set(Calendar.SECOND, 0);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.HOUR_OF_DAY, 0);
		}
		
		return calendar;
	}
}
