package com.evozon.evoportal.evozonfreedaysallocator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.evozon.evoportal.evozonfreedaysallocator.model.impl.AllocationShift;
import com.evozon.evoportal.evozonfreedaysallocator.service.BenefitDayLocalServiceUtil;
import com.evozon.evoportal.vacation.model.DateInterval;
import com.evozon.evoportal.vacation.model.VacationType;
import com.evozon.evoportal.vacation.service.VacationDAO;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.model.User;

/**
 * Utility class for computing the exact bonus days for the company benefits package.
 */
public final class BonusDaysComputer {

	private static final Logger log = Logger.getLogger(BonusDaysComputer.class);

	public static final int LEGAL_DAYS_PER_YEAR = 21;

	private static final int FACTOR_EVZ = 12;

	public static final int MAX_BONUS_DAYS = 10;

	// saved for future reference
	private static final int BONUS_VALUE = 1;

	public static int getTotalBonusDaysForUser(User user, boolean allocatedBonus) {
		int bonus = 0;

		// total due bonus for time worked in company, computed based on today
		int bonusEVZ = BonusDaysComputer.computeBonusEVZ(user, allocatedBonus);
		log.debug("computed bonusInEvozon = " + bonusEVZ);

		// total due bonus for overall experience, computed based on today
		int bonusEXP = BonusDaysComputer.computeBonusForExperience(user);
		log.debug("computed bonusForExperience = " + bonusEXP);

		bonus = bonusEVZ + bonusEXP;

		// maximum bonus days can reach 10
		if (bonus > MAX_BONUS_DAYS) {
			bonus = MAX_BONUS_DAYS;
		}

		return bonus;
	}

	/**
	 * Computes the value of bonus EVZ, i.e. the no. of bonus days corresponding to time worked in the company.<br/>
	 * Currently, 1 bonus day is allocated for each 12 months.</br></br> Bonus days are limited to a maximum of 5.
	 * 
	 * @param user
	 *        specific individual
	 * @param allocatedBonus
	 *        new bonus days will be available from the 1'st of next month
	 * @return no. of bonus days corresponding to time worked in the company
	 */
	public static int computeBonusEVZ(User user, boolean allocatedBonus) {
		Calendar today = BenefitDayLocalServiceUtil.getUserSelectedDate();
		return BonusDaysComputer.computeBonusEVZForUserInDate(user, allocatedBonus, today);
	}

	public static int computeBonusEVZForUserInDate(User user, boolean allocatedBonus, Calendar date) {

		Calendar startDate = getDateHired(user);
		Calendar shiftedStartDate = BonusDaysComputer.getShiftedStartDate(user.getUserId(), startDate, date);

		int hiredMonths = getMonthsBetweenDates(date, shiftedStartDate);
		hiredMonths = (hiredMonths < 0) ? 0 : hiredMonths;

		int bonus = hiredMonths / FACTOR_EVZ;

		if (allocatedBonus && (hiredMonths > 0)) {

			if ((hiredMonths % FACTOR_EVZ) == 0) {
				boolean isBonusDayAvailable = BonusDaysComputer.isBonusDayAvailable(shiftedStartDate, date);
				bonus -= !isBonusDayAvailable ? 1 : 0;
			}
		}

		// maxim evozon bonus can reach 10 days
		if (bonus > MAX_BONUS_DAYS) {
			bonus = MAX_BONUS_DAYS;
		}
		log.debug("computeBonusEVZ() for user " + user.getUserId() + " = " + bonus);

		return bonus;
	}

	private static boolean isBonusDayAvailable(Calendar startDate, Calendar endDate) {
		boolean isBonusDayAvailable = false;

		int startDateMonth = startDate.get(Calendar.MONTH);
		int endDateMonth = endDate.get(Calendar.MONTH);

		if ((startDate.get(Calendar.DAY_OF_MONTH) == 1)) {
			isBonusDayAvailable = true;

		} else if (startDateMonth != endDateMonth) {
			isBonusDayAvailable = true;

		}

		return isBonusDayAvailable;
	}

	/**
	 * Returns the 'Starting Bonus Days' which represents the total bonus for previous experience. <br/>
	 * 
	 * Usually the maximum value of this should be 5, but there might be exceptions.
	 * 
	 * @param user
	 *        specific individual
	 * @return no. of 'Starting Bonus Days' for previous experience.
	 */
	public static int computeBonusForExperience(User user) {
		return new UserFreeDaysWrapper(user).getStartingBonusDays();
	}

	/**
	 * Determines whether the given user is entitled to a bonus EVZ, in the current date <i>(if no simulation date is
	 * present)</i> .<br/>
	 * 
	 * @param user
	 *        specific individual
	 * @return true, if today <i>(or simulated date)</i> has a company anniversary.
	 */
	public static boolean isAnniversaryEVZ(User user) {
		Calendar today = BenefitDayLocalServiceUtil.getUserSelectedDate();
		return BonusDaysComputer.hasEVZAnniversayInDate(user, today);
	}

	/**
	 * Determines whether the given user is entitled to a bonus EVZ, in the specified date.<br/>
	 * 
	 * @param user
	 *        specific individual
	 * @return true, if the user has anniversary in the specified date.
	 */
	// TODO: check if will be 29 feb.
	public static boolean hasEVZAnniversayInDate(User user, Calendar date) {
		Calendar hired = BonusDaysComputer.getDateHired(user);
		Calendar newBonusHiredDate = BonusDaysComputer.getShiftedStartDate(user.getUserId(), hired, date);

		boolean sameMonth = (date.get(Calendar.MONTH) == newBonusHiredDate.get(Calendar.MONTH));
		boolean sameDay = BonusDaysComputer.daysMatch(date, newBonusHiredDate);

		boolean differentYear = (date.get(Calendar.YEAR) > newBonusHiredDate.get(Calendar.YEAR));
		differentYear &= (date.get(Calendar.YEAR) != newBonusHiredDate.get(Calendar.YEAR));

		log.error("isAnniversaryEVZ() for user " + user.getUserId() + " = " + (sameDay && sameMonth && differentYear));
		return sameDay && sameMonth && differentYear;
	}

	private static Calendar getShiftedStartDate(long userId, Calendar startDate, Calendar endDate) {
		Calendar shiftedStartDate = Calendar.getInstance();
		shiftedStartDate.setTime(startDate.getTime());

		DateInterval dateInterval = new DateInterval(startDate, endDate);
		List<AllocationShift> dayShift = BonusDaysComputer.findUserAllocationShift(userId, dateInterval);

		for (AllocationShift allocationShift : dayShift) {
			shiftedStartDate.add(allocationShift.getCalendarField(), allocationShift.getCalendarAmount());
		}

		if (!dayShift.isEmpty() && log.isDebugEnabled()) {
			String unpaidLeaveHiredStr = new SimpleDateFormat("dd/MM/YYYY").format(shiftedStartDate.getTime());
			log.debug("New bonus day for user [" + userId + "] will be in " + unpaidLeaveHiredStr);
		}

		return shiftedStartDate;
	}

	private static List<AllocationShift> findUserAllocationShift(long userId, DateInterval dateInterval) {
		List<AllocationShift> allocationsShift = new ArrayList<AllocationShift>();

		// shift start date with CF days count taken
		int nonWorkingDaysCount = BonusDaysComputer.getVacationTypeDaysForUser(userId, dateInterval, VacationType.CF, VacationType.ML_CIC);
		if (nonWorkingDaysCount > 0) {
			if (log.isDebugEnabled()) {
				log.debug("Skip anniversary bonus for user [" + userId + "] with [" + nonWorkingDaysCount + "] days.");
			}

			AllocationShift shift = new AllocationShift(userId);
			shift.setCalendarField(Calendar.DAY_OF_YEAR);
			shift.setCalendarAmount(nonWorkingDaysCount);
			allocationsShift.add(shift);
		}

		// shift start with mentioned time
		List<AllocationShift> dayShift = PropertiesInterpretor.getInstance().getShiftedBonusExceptionForUser(userId);
		if ((dayShift != null) && !dayShift.isEmpty()) {
			allocationsShift.addAll(dayShift);
		}

		return allocationsShift;
	}

	private static int getVacationTypeDaysForUser(long userId, DateInterval dateInterval, VacationType... types) {
		int nonWorkingDaysCount = 0;

		try {
			for (VacationType vacType : types) {
				nonWorkingDaysCount += VacationDAO.getNumberOfVacationDaysBetweenTwoDates(userId, vacType, dateInterval);
			}
		} catch (SystemException e) {
			log.error(e.getMessage(), e);
		}

		return nonWorkingDaysCount;
	}

	public static Calendar getDateHired(User user) {
		Calendar hired = Calendar.getInstance();
		hired.setTime(new UserFreeDaysWrapper(user).getDateHired());
		return hired;
	}

	/**
	 * Compute the no. of months between the first calendar's date and the second.
	 * 
	 * @param today
	 * @param hired
	 * @return no. of months between the two dates
	 */
	public static int getMonthsBetweenDates(Calendar today, Calendar hired) {

		int yearMonths = today.get(Calendar.YEAR) - hired.get(Calendar.YEAR);
		int monthMonths = today.get(Calendar.MONTH) - hired.get(Calendar.MONTH);

		int result = yearMonths * 12 + monthMonths;
		if (today.get(Calendar.DAY_OF_MONTH) < hired.get(Calendar.DAY_OF_MONTH)) {
			result--;
		}

		return result;
	}

	private static boolean daysMatch(Calendar today, Calendar hired) {
		if (today.get(Calendar.DAY_OF_MONTH) == hired.get(Calendar.DAY_OF_MONTH)) {
			return true;
		} else if ((today.get(Calendar.DAY_OF_MONTH) == today.getActualMaximum(Calendar.DAY_OF_MONTH)) && (hired.get(Calendar.DAY_OF_MONTH) == hired.getActualMaximum(Calendar.DAY_OF_MONTH))) {
			return true;
		}

		return false;
	}

	/**
	 * Return the value of a bonus benefit, i.e. the number of free days corresponding to time worked in the company.
	 * 
	 * @return bonus value
	 */
	public static int getBonusValue() {
		return BONUS_VALUE;
	}

	/**
	 * Determines whether the user with the given ID is entitled to the input benefit day type. Rule is that the benefit
	 * days number should be limited by the maximum decided value.
	 * 
	 * @param user
	 * @param type
	 * @return true if the user with the given id should receive a benefit day of input type, false otherwise
	 */
	public static boolean isEntitledForEvoBonus(User user) {
		int bonusEVZ = BonusDaysComputer.computeBonusEVZ(user, false);
		if (bonusEVZ > MAX_BONUS_DAYS) {
			return false;
		}

		int bonusEXP = BonusDaysComputer.computeBonusForExperience(user);
		if ((bonusEVZ + bonusEXP) > MAX_BONUS_DAYS) {
			return false;
		}

		return true;
	}

	// TODO: bellow methods don't belong here
	public static float applyFormula(int totalBonusDays, int businessDays, int workedDays) {
		float yearWork = ((float) workedDays / businessDays);
		float dueFreeDays = yearWork * (LEGAL_DAYS_PER_YEAR + totalBonusDays);
		return dueFreeDays;
	}

	public static int getUserLegalFreeDays(User user) {
		Calendar today = BenefitDayLocalServiceUtil.getUserSelectedDate();
		return BonusDaysComputer.getUserLegalFreeDaysInDate(user, today);
	}

	public static int getUserLegalFreeDaysInDate(User user, Calendar date) {
		int freeDays = 0;
		try {
			int dateYear = date.get(Calendar.YEAR);
			Calendar startDate = BonusDaysComputer.getDateHired(user);

			if (startDate.get(Calendar.YEAR) <= dateYear) {
				int currentYearWorkingDays = BonusDaysComputer.getUserWorkedDaysInYear(user, dateYear);

				int businessDays = BusinessDaysManager.getBusinessDaysInYear(dateYear);

				int totalBonus = BonusDaysComputer.computeBonusForExperience(user);
				totalBonus += BonusDaysComputer.computeBonusEVZForUserInDate(user, true, date);

				totalBonus = (totalBonus > MAX_BONUS_DAYS) ? MAX_BONUS_DAYS : totalBonus;

				float userFreeDays = BonusDaysComputer.applyFormula(totalBonus, businessDays, currentYearWorkingDays);
				freeDays = Math.round(userFreeDays);
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}

		return freeDays;
	}

	public static int getUserWorkedDaysInYear(User user, int year) throws SystemException, ParseException {
		UserFreeDaysWrapper userWrapper = new UserFreeDaysWrapper(user);
		if (userWrapper.isInInternship()) {
			return 0;
		}

		int userWorkingDays = 0;
		DateInterval workingPeriod = findUserWorkedPerioadInYear(user, year);

		if (workingPeriod != null) {
			int workingPeriodBusinessDays = BusinessDaysManager.getBusinessDaysBetweenTwoDates(workingPeriod.getStartDateAsCalendar(), workingPeriod.getEndDateAsCalendar());
			int nonWorkingDaysInYear = BonusDaysComputer.getVacationTypeDaysForUser(user.getUserId(), workingPeriod, VacationType.CF, VacationType.ML_CIC);
			userWorkingDays = workingPeriodBusinessDays - nonWorkingDaysInYear;
		}

		return userWorkingDays;
	}

	private static DateInterval findUserWorkedPerioadInYear(User user, int year) {
		DateInterval dateInterval = null;

		if (user.isActive()) {
			Calendar firstWorkingDayInYear = getUserFirstWorkingDayInYear(user, year);
			Calendar lastWorkingDayInYear = getUserLastWorkingDayInYear(user, year);
			dateInterval = new DateInterval(firstWorkingDayInYear, lastWorkingDayInYear);
		} else {
			dateInterval = findInactiveUserWorkedPeriodInYear(user, year);
		}

		return dateInterval;
	}

	private static DateInterval findInactiveUserWorkedPeriodInYear(User user, int year) {
		DateInterval lastWorkedPeriodInYear = null;
		UserFreeDaysWrapper userWrapper = new UserFreeDaysWrapper(user);

		Date lastStatusChange = userWrapper.getCurrentStatusChangeDate();
		if (lastStatusChange != null) {

			DateInterval yearInterval = new DateInterval(BusinessDaysManager.getStartYear(year), BusinessDaysManager.getEndYear(year));

			DateInterval lastWorkedPeriod = null;
			int lastWorkflowStatus = userWrapper.getStatusWorkflowStatus();

			if (lastWorkflowStatus == WorkflowConstants.STATUS_INACTIVE) {
				lastWorkedPeriod = new DateInterval(yearInterval.getStartDate(), lastStatusChange);

			} else if (lastWorkflowStatus == WorkflowConstants.STATUS_APPROVED) {
				lastWorkedPeriod = new DateInterval(lastStatusChange, yearInterval.getEndDate());

			}

			if ((lastWorkedPeriod != null) && lastWorkedPeriod.isValid()) {
				lastWorkedPeriodInYear = yearInterval.getCommonInterval(lastWorkedPeriod);
			}
		}

		return lastWorkedPeriodInYear;
	}

	private static Calendar getUserFirstWorkingDayInYear(User user, int year) {
		Calendar startDate = getDateHired(user);

		if (startDate.get(Calendar.YEAR) < year) {
			startDate = BusinessDaysManager.getStartYear(year);
		}

		Calendar statusChangeCal = getCurrentStatusChangeTime(user, WorkflowConstants.STATUS_APPROVED);
		if ((statusChangeCal != null) && (statusChangeCal.get(Calendar.YEAR) == year)) {
			startDate = statusChangeCal;
		}

		return startDate;
	}

	private static Calendar getUserLastWorkingDayInYear(User user, int year) {
		Calendar lastWorkingDay = BusinessDaysManager.getEndYear(year);

		Calendar statusChangeCal = getCurrentStatusChangeTime(user, WorkflowConstants.STATUS_INACTIVE);
		if ((statusChangeCal != null) && (statusChangeCal.get(Calendar.YEAR) == year)) {
			// if the change date is in given year
			lastWorkingDay.setTime(statusChangeCal.getTime());
			lastWorkingDay.add(Calendar.DAY_OF_YEAR, -1);// -1 for last day of
															// work
		}

		return lastWorkingDay;
	}

	private static Calendar getCurrentStatusChangeTime(User user, int status) {
		Calendar statusChangeCal = null;

		UserFreeDaysWrapper userWrapper = new UserFreeDaysWrapper(user);
		if (userWrapper.getStatusWorkflowStatus() == status) {
			// if he has a change date set
			statusChangeCal = Calendar.getInstance();
			statusChangeCal.setTime(userWrapper.getCurrentStatusChangeDate());

		}

		return statusChangeCal;
	}

}
