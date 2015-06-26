package com.evozon.evoportal.my_account.managers;

import java.util.Calendar;

import org.apache.log4j.Logger;

import com.evozon.evoportal.evozonfreedaysallocator.BonusDaysComputer;
import com.evozon.evoportal.evozonfreedaysallocator.EvoportalEmailUtil;
import com.evozon.evoportal.evozonfreedaysallocator.FreeDaysHistoryDescription;
import com.evozon.evoportal.evozonfreedaysallocator.model.impl.BenefitDayType;
import com.evozon.evoportal.evozonfreedaysallocator.model.impl.FreeDaysHistoryEventType;
import com.evozon.evoportal.evozonfreedaysallocator.model.impl.FreeDaysHistoryOperationType;
import com.evozon.evoportal.evozonfreedaysallocator.service.BenefitDayLocalServiceUtil;
import com.evozon.evoportal.evozonfreedaysallocator.service.FreeDaysHistoryEntryLocalServiceUtil;
import com.evozon.evoportal.my_account.model.FreeDaysModel;
import com.evozon.evoportal.my_account.util.MyAccountUtil;
import com.evozon.evoportal.my_account.wrapper.UserExpandoWrapper;
import com.liferay.portal.model.User;

public class FreeDaysManager extends AccountManager<FreeDaysModel> {

	private static final Logger logger = Logger.getLogger(EvoportalEmailUtil.class);

	private UserExpandoWrapper userExpando;

	private User user;

	public FreeDaysManager(FreeDaysModel initial, FreeDaysModel current, User user) {
		super(initial, current);
		userExpando = new UserExpandoWrapper(user);
		this.user = user;
	}

	public User getUser() {
		return user;
	}

	public boolean isFreeDaysFromLastYearChanged() {
		return getOldModel().getFreeDaysFromLastYear() != getNewModel().getFreeDaysFromLastYear();
	}

	public boolean isFreeDaysInCurrentYearChanged() {
		return getOldModel().getFreeDaysInCurrentYear() != getNewModel().getFreeDaysInCurrentYear();
	}

	public boolean isStartingBonusDaysChanged() {
		return getOldModel().getStartingBonusDays() != getNewModel().getStartingBonusDays();
	}

	public boolean isCommentsChanged() {
		String initialComments = getOldModel().getComments().toString();
		String currentComments = getNewModel().getComments().toString();
		return !initialComments.equals(currentComments);
	}

	public boolean isExtraDaysNumberChanged() {
		return getNewModel().getExtraDaysCount() != 0;
	}

	public boolean isDateHiredChanged() {
		Calendar initStDate = getOldModel().getStartDateAsCalendar();
		Calendar currStDate = getNewModel().getStartDateAsCalendar();
		return !MyAccountUtil.isSameCalendar(initStDate, currStDate);
	}

	public boolean isCIMStartDateChanged() {
		Calendar initCIMDate = getOldModel().getCimStartDateAsCalendar();
		Calendar currCIMDate = getNewModel().getCimStartDateAsCalendar();
		return !MyAccountUtil.isSameCalendar(initCIMDate, currCIMDate);
	}

	public boolean isInternshipStartDateChanged() {
		Calendar internshipStart = getOldModel().getInternshipStartDateAsCalendar();
		Calendar internshipEnd = getNewModel().getInternshipStartDateAsCalendar();
		return !MyAccountUtil.isSameCalendar(internshipStart, internshipEnd);
	}

	public boolean isRemainingFreeDaysFromLastYearChanged() {
		return getOldModel().getRemainingFreeDaysFromLastYear() != getNewModel().getRemainingFreeDaysFromLastYear();
	}

	public void executeFreeDaysFromLastYearChange() {
		logger.info("'Free days from last year' was changed for user: " + user.getFullName());
		String updateDescription = "'Free Days From Last Year' account update";

		int oldFreeDaysFromLastYear = getOldModel().getFreeDaysFromLastYear();
		int newFreeDaysFromLastYear = getNewModel().getFreeDaysFromLastYear();

		userExpando.setFreeDaysFromLastYear(newFreeDaysFromLastYear);
		recordHistory(oldFreeDaysFromLastYear, newFreeDaysFromLastYear, FreeDaysHistoryEventType.ACCOUNT.name(), updateDescription);
	}

	public void executeRemainingFreeDaysFromLastYearChange() {
		logger.info("'Free days from last year' was changed for user: " + user.getFullName());
		int oldRemainingFreeDaysFromLastYear = getOldModel().getRemainingFreeDaysFromLastYear();
		int newRemainingFreeDaysFromLastYear = getNewModel().getRemainingFreeDaysFromLastYear();
		String updateDescription = "'Remaining Free Days From Last Year' account update";

		userExpando.setFreeDaysFromLastYear(newRemainingFreeDaysFromLastYear);
		recordHistory(oldRemainingFreeDaysFromLastYear, newRemainingFreeDaysFromLastYear, FreeDaysHistoryEventType.ACCOUNT.name(),
				updateDescription);

		 userExpando.setRemainingFreeDaysFromLastYear(newRemainingFreeDaysFromLastYear);
	}

	public void executeFreeDaysInCurrentYearChange() {
		logger.info("'Free Days In current Year' was changed for user: " + user.getFullName());
		int oldFreeDaysInCurrentYear = getOldModel().getFreeDaysInCurrentYear();
		int newFreeDaysInCurrentYear = getNewModel().getFreeDaysInCurrentYear();
		String updateDescription = "'Free Days In Current Year' account update";

		userExpando.setFreeDaysInCurrentYear(newFreeDaysInCurrentYear);

		recordHistory(oldFreeDaysInCurrentYear, newFreeDaysInCurrentYear, FreeDaysHistoryEventType.ACCOUNT.name(), updateDescription);
	}

	public void executeStartDateChange() {
		Calendar newStartDate = getNewModel().getStartDateAsCalendar();
		logger.info("Start Date was changed for user: " + user.getFullName() + " to: " + newStartDate);

		int currentYear = Calendar.getInstance().get(Calendar.YEAR);
		int newStartDateYear = newStartDate.get(Calendar.YEAR);

		userExpando.setDateHired(newStartDate.getTime());

		int freeDaysInCurrentYear = getNewModel().getFreeDaysInCurrentYear();
		int legalVacationDays = 0;

		int extractedThisYear = userExpando.getExtractedFreeDaysFromCurrentYear();
		int remainingFromLastYear = userExpando.getRemainingFreeDaysFromLastYear();

		if (newStartDateYear > currentYear) {
			// if date hired is changed in the future
			logger.debug("Future year was set: " + newStartDateYear);
			freeDaysInCurrentYear = 0;
			legalVacationDays = 0;
		} else {
			legalVacationDays = BonusDaysComputer.getUserLegalFreeDays(user);
			freeDaysInCurrentYear = legalVacationDays - extractedThisYear;
			if (remainingFromLastYear < 0) {
				freeDaysInCurrentYear += remainingFromLastYear;
			}
			logger.info("New 'Legal Vacation Days': " + legalVacationDays + ", new free days in current year: " + freeDaysInCurrentYear);
		}

		userExpando.setFreeDaysInCurrentYear(freeDaysInCurrentYear);
		userExpando.setLegalVacationDays(legalVacationDays);
		getNewModel().setFreeDaysInCurrentYear(freeDaysInCurrentYear);
		adaptMonthsOfExperienceInEvozon();
	}

	public void executeCIMStartDateChange() {
		Calendar currCIMDate = getNewModel().getCimStartDateAsCalendar();
		userExpando.setCIMStartDate(currCIMDate.getTime());
	}

	public void executeCommentChange() {
		userExpando.setComments(getNewModel().getComments());
	}

	public void executeStartingBonusDaysChange() {
		logger.info("'starting bonus Days' changed for user: " + user.getFullName());
		int newStartingBonusDays = getNewModel().getStartingBonusDays();
		int freeDaysInCurrentYear = getNewModel().getFreeDaysInCurrentYear();

		int oldLegalFreeDays = BonusDaysComputer.getUserLegalFreeDays(user);
		userExpando.setStartingBonusDays(newStartingBonusDays);
		// the 'newLegalFreeDays' will be computed with the
		// newStartingBonusDays.
		int newLegalFreeDays = BonusDaysComputer.getUserLegalFreeDays(user);

		int legalFreeDaysDiff = newLegalFreeDays - oldLegalFreeDays;
		if (legalFreeDaysDiff != 0) {
			freeDaysInCurrentYear += legalFreeDaysDiff;

			userExpando.setFreeDaysInCurrentYear(freeDaysInCurrentYear);
			getNewModel().setFreeDaysInCurrentYear(freeDaysInCurrentYear);

			userExpando.setLegalVacationDays(newLegalFreeDays);
		}
	}

	public void executeExtraDaysChange() {
		int newEnteredExtraDays = getNewModel().getExtraDaysCount();

		if (newEnteredExtraDays > 0) {
			logger.info("'Extra Days' were added  for user: " + user.getFullName());
			int oldExtraDays = getOldModel().getExtraDaysCount();

			int newExtraDays = oldExtraDays + newEnteredExtraDays;
			String extraDaysDescription = getNewModel().getExtraDaysDescription();
			if (extraDaysDescription.isEmpty()) {
				extraDaysDescription = FreeDaysHistoryDescription.EXTRA_DAYS;
			}

			// add new entered extra days in benefit day
			BenefitDayLocalServiceUtil.saveExtraDay(user.getUserId(), BenefitDayType.EXTRA_DAYS.toString(), newEnteredExtraDays, extraDaysDescription);

			FreeDaysHistoryEntryLocalServiceUtil.saveFreeDaysHistoryEntry(user.getUserId(), FreeDaysHistoryOperationType.ADD.name(), oldExtraDays, newEnteredExtraDays,
					newExtraDays, FreeDaysHistoryEventType.EXTRA_DAYS.name(), extraDaysDescription);
			userExpando.setExtraDays(newExtraDays);
		}
	}

	private void adaptMonthsOfExperienceInEvozon() {
		logger.info("Adapting new start date, month of evozon experience.");
		Calendar today = Calendar.getInstance();

		Calendar newHiredCal = Calendar.getInstance();
		newHiredCal.setTime(getNewModel().getStartDate());

		int numberOfMonthsInEvozon = BonusDaysComputer.getMonthsBetweenDates(today, newHiredCal);
		userExpando.setMonthsOfExperienceInEvozon(numberOfMonthsInEvozon);
	}

	private void recordHistory(int oldValue, int newValue, String modEventType, String description) {
		int diff = newValue - oldValue;
		String operation = (diff > 0) ? FreeDaysHistoryOperationType.ADD.name() : FreeDaysHistoryOperationType.REMOVE.name();

		FreeDaysHistoryEntryLocalServiceUtil.saveFreeDaysHistoryEntry(user.getUserId(), operation, oldValue, Math.abs(diff), newValue, modEventType, description);
	}

}
