package com.evozon.evoportal.evozonfreedaysallocator;

import java.text.ParseException;
import java.util.Calendar;

import org.apache.log4j.Logger;

import com.evozon.evoportal.evozonfreedaysallocator.service.BenefitDayLocalServiceUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.User;

public class FreeDaysComputer {

	private static final Logger log = Logger.getLogger(EvozonFreeDaysAllocator.class);

	public static int getCurrentTotalFreeDaysForUser(User user) throws SystemException, ParseException {
		return getCurrentTotalFreeDaysForUserWithFutureCF(user, 0);
	}

	public static int getCurrentTotalFreeDaysForUserWithFutureCF(User user, int futureCF) throws SystemException, ParseException {

		int totalBonus = BonusDaysComputer.getTotalBonusDaysForUser(user, true);
		int daysLegal = getCurrentTotalFreeDays(user, totalBonus, futureCF);

		return daysLegal;
	}

	/**
	 * Computes the current legal no. of free days for the ongoing year
	 * 
	 * @throws ParseException
	 */
	public static int getCurrentTotalFreeDays(User user, int totalBonus) throws SystemException, ParseException {
		return getCurrentTotalFreeDays(user, totalBonus, 0);
	}

	public static int getCurrentTotalFreeDays(User user, int totalBonus, int futureCF) throws SystemException, ParseException {
		// hired Date
		Calendar hiredDate = BonusDaysComputer.getDateHired(user);

		// 01 January current year
		Calendar startYear = BusinessDaysManager.getCurrentStartYear();

		// 31 December current year
		Calendar endYear = BusinessDaysManager.getCurrentEndYear();

		// from the hire date until the end of the year (the max no. of months
		// expected to be worked for the current year)
		int workingMonths = BonusDaysComputer.getMonthsBetweenDates(endYear, hiredDate);

		int dueFreeDays = 0;
		if (workingMonths < 12) {
			// if a user got hired after 1 January current year
			// get the total free days staring from the hire date until end of
			// the year
			dueFreeDays = computeLEGALDays(user, hiredDate, endYear, totalBonus, futureCF);
		} else {
			// else get current total free days for the entire year
			dueFreeDays = computeLEGALDays(user, startYear, endYear, totalBonus, futureCF);
		}

		return dueFreeDays;
	}

	/**
	 * Computes the due free days for a user, between two given dates.
	 * 
	 * @throws ParseException
	 * 
	 */
	// TODO: check if this is used somewhere
	public static int computeLEGALDays(User user, Calendar startDate, Calendar endDate, int totalBonusDays) throws SystemException, ParseException {
		return computeLEGALDays(user, startDate, endDate, totalBonusDays, 0);
	}

	private static int computeLEGALDays(User user, Calendar startDate, Calendar endDate, int totalBonusDays, int futureCF) throws SystemException, ParseException {
		// the total of business days for current year (not including official
		// holidays)
		int businessDays = BusinessDaysManager.getBusinessDaysInCurrentYear();

		// the total number of days expected to be worked by a user in the
		// current year
		int userSelectedYear = BenefitDayLocalServiceUtil.getUserSelectedDate().get(Calendar.YEAR);
		int workedDays = BonusDaysComputer.getUserWorkedDaysInYear(user, userSelectedYear) - futureCF;
		// BusinessDaysManager.getBusinessDaysBetweenTwoDates(startDate,
		// endDate);

		// get the no. of unpaid leave days(CF) currently existing
		// int unpaidLeave = VacationDAO.getNumberOfVacationDays(user,
		// VacationType.CF, userSelectedYear);

		// add the future CF days
		// unpaidLeave += futureCF;

		// subtract the no. of unpaid leave days(CF) from expected worked days
		// workedDays = workedDays - unpaidLeave;

		// apply the formula and compute the current legal no. of free days for
		// the ongoing year
		float dueFreeDays = BonusDaysComputer.applyFormula(totalBonusDays, businessDays, workedDays);
		log.info("For User: " + user.getFullName() + " || hired at:" + BonusDaysComputer.getDateHired(user).getTime() + " || workingDays: " + businessDays + " || workedDays: "
				+ workedDays + " || totalBonusDays: " + totalBonusDays + " || legalFreeDays: " + dueFreeDays);

		// round the resulting dueFreeDays
		return (int) Math.round(dueFreeDays);
	}

	public static int getNumberOfExtractedDaysDueToCFVacations(User user, int futureCFDays) throws SystemException, ParseException {
		int totalBonus = BonusDaysComputer.getTotalBonusDaysForUser(user, true);
		int dueDays = getCurrentTotalFreeDays(user, totalBonus, futureCFDays);
		int freeDaysNoCF = BonusDaysComputer.LEGAL_DAYS_PER_YEAR + totalBonus;
		return freeDaysNoCF - dueDays;
	}

}
