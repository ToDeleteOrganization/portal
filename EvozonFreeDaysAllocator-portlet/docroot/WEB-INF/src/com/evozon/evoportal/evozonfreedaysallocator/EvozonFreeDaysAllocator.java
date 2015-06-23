package com.evozon.evoportal.evozonfreedaysallocator;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.mail.internet.AddressException;

import org.apache.log4j.Logger;

import com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDayQueue;
import com.evozon.evoportal.evozonfreedaysallocator.model.impl.BenefitDayQueueImpl;
import com.evozon.evoportal.evozonfreedaysallocator.model.impl.BenefitDayType;
import com.evozon.evoportal.evozonfreedaysallocator.service.BenefitDayLocalServiceUtil;
import com.evozon.evoportal.evozonfreedaysallocator.service.BenefitDayQueueLocalServiceUtil;
import com.evozon.evoportal.vacation.model.DateInterval;
import com.evozon.evoportal.vacation.model.EvozonVacation;
import com.evozon.evoportal.vacation.model.Extraction;
import com.evozon.evoportal.vacation.model.ExtractionClp;
import com.evozon.evoportal.vacation.model.Inbox;
import com.evozon.evoportal.vacation.model.Status;
import com.evozon.evoportal.vacation.model.VacationStatus;
import com.evozon.evoportal.vacation.model.VacationType;
import com.evozon.evoportal.vacation.service.InboxLocalServiceUtil;
import com.evozon.evoportal.vacation.service.VacationDAO;
import com.evozon.evoportal.vacation.service.VacationStatusUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageListener;
import com.liferay.portal.kernel.messaging.MessageListenerException;
import com.liferay.portal.model.User;

/**
 * Portlet implementation class EvoFreeDays
 */
public class EvozonFreeDaysAllocator extends AllocatorScheduler implements MessageListener {

	private static final Logger log = Logger.getLogger(EvozonFreeDaysAllocator.class);

	public void receive(Message message) throws MessageListenerException {
		log.debug("************>> Job has been started! <<**************");
		try {
//			List<User> usersList = getUsersToSimulate();
//			if (usersList.isEmpty()) {
//				usersList = UserLocalServiceUtil.getUsers(QueryUtil.ALL_POS, QueryUtil.ALL_POS);
//			}
//
//			Calendar today = BenefitDayLocalServiceUtil.getUserSelectedDate();
//
//			int dayOfMonth = today.get(Calendar.DAY_OF_MONTH);
//			if (dayOfMonth == today.getActualMinimum(Calendar.DAY_OF_MONTH)) {
//				handleFirstDayOfMonth(usersList);
//
//			} else if (dayOfMonth == today.getActualMaximum(Calendar.DAY_OF_MONTH)) {
//				handleLastDayOfMonth(usersList);
//
//			} else {
//				handleMonthAllocation(usersList);
//			}

			List<BenefitDayQueue> monthBonuses = BenefitDayQueueLocalServiceUtil.findBenefitDaysAllocatedInYearAndMonth(Calendar.JUNE, 2015);
			EvoportalEmailUtil.sendMailWithAnniversaryUsers(this.getMonthlyBonusesReceivers(), monthBonuses);

			log.info("Finished checkin for users anniversary.");
		} catch (Exception e) {
			log.error("Job failed! Free days were not allocated!", e);
		}
	}

	private void handleFirstDayOfMonth(List<User> usersList) {
		Calendar today = BenefitDayLocalServiceUtil.getUserSelectedDate();
		for (User user : usersList) {
			if (!user.isActive()) {
				log.debug("Skipping inactive user " + user.getFullName());
				continue;
			}
			log.info("Handle first day of month for user: " + user.getFullName());
			try {
				boolean isFirstDayOfYear = today.get(Calendar.DAY_OF_YEAR) == today.getActualMinimum(Calendar.DAY_OF_YEAR);
				if (isFirstDayOfYear) {
					handleNewYearTransition(user);
				}

				checkForAnniversary(user, true);
				allocateDaysFromQueue(user, isFirstDayOfYear);
			} catch (Exception e) {
				log.error("Error handling monthly allocation for user: " + user.getFullName(), e);
			}
		}

	}

	private void allocateDaysFromQueue(User user, boolean isFirstDayOfYear) throws SystemException {
		List<BenefitDayQueue> userBonus = BenefitDayQueueLocalServiceUtil.getBenefitDaysQueued(user.getUserId());
		UserFreeDaysWrapper userWrapper = new UserFreeDaysWrapper(user);

		for (BenefitDayQueue userBonusDay : userBonus) {
			long userId = userBonusDay.getUserId();
			int daysNo = userBonusDay.getDaysNo();
			String type = userBonusDay.getType();

			BenefitDayLocalServiceUtil.addExtraValue(userId, type, daysNo);

			BenefitDayQueueLocalServiceUtil.allocateBenefitDayFromQueue(userBonusDay);

			int legalFreeDays = BonusDaysComputer.getUserLegalFreeDays(userWrapper.getUser());
			userWrapper.setLegalVacationDays(legalFreeDays);

			if (!isFirstDayOfYear) {
				int oldFreeDaysInCurrentYear = userWrapper.getFreeDaysInCurrentYear();

				int newFreeDaysInCurrentYear = oldFreeDaysInCurrentYear + daysNo;
				userWrapper.setFreeDaysInCurrentYear(newFreeDaysInCurrentYear);

				EvozonFreeDaysHistoryUtil.saveHistory(userId, oldFreeDaysInCurrentYear, newFreeDaysInCurrentYear, type, FreeDaysHistoryDescription.BONUS_EVOZON);
			}

		}
	}

	private void handleLastDayOfMonth(List<User> usersList) {
		Calendar tomorrow = BenefitDayLocalServiceUtil.getUserSelectedDate();
		tomorrow.add(Calendar.DAY_OF_YEAR, 1);
		List<BenefitDayQueue> tomorrowAnniversary = new ArrayList<BenefitDayQueue>();
		for (User user : usersList) {
			if (!user.isActive()) {
				log.debug("Skipping inactive user " + user.getFullName());
				continue;
			}
			log.debug("Handle last day of month for user: " + user.getFullName());
			try {
				checkForAnniversary(user, false);

				boolean hasAnniversaryTomorrow = BonusDaysComputer.hasEVZAnniversayInDate(user, tomorrow);
				if (hasAnniversaryTomorrow) {
					log.debug(user.getFullName() + " has anniversay tomorrow.");
					BenefitDayQueue queue = new BenefitDayQueueImpl();
					queue.setAddedDate(tomorrow.getTime());
					queue.setUserId(user.getUserId());
					tomorrowAnniversary.add(queue);
				}
			} catch (Exception e) {
				log.error("Error handling monthly allocation for user: " + user.getFullName(), e);
			}
		}

		sendMonthBonusesSituation(tomorrowAnniversary);
	}

	private void sendMonthBonusesSituation(List<BenefitDayQueue> tomorrowQueue) {
		try {
			log.info("Sending month bonuses situation to accounting.");
			Calendar today = BenefitDayLocalServiceUtil.getUserSelectedDate();

			int month = today.get(Calendar.MONTH);
			int year = today.get(Calendar.YEAR);

			List<BenefitDayQueue> monthBonuses = BenefitDayQueueLocalServiceUtil.findBenefitDaysAllocatedInYearAndMonth(month, year);
			monthBonuses.addAll(tomorrowQueue);

			EvoportalEmailUtil.sendMailWithAnniversaryUsers(getMonthlyBonusesReceivers(), monthBonuses);
		} catch (Exception e) {
			log.error("Error sending monthly bonuses situation mail: ", e);
		}
	}

	private void handleMonthAllocation(List<User> usersList) {
		for (User user : usersList) {
			if (!user.isActive()) {
				log.debug("Skipping inactive user " + user.getFullName());
				continue;
			}
			log.info("Handle month allocation for user: " + user.getFullName());
			try {
				checkForAnniversary(user, false);
			} catch (Exception e) {
				log.error("Error handling monthly allocation for user: " + user.getFullName(), e);
			}
		}
	}

	private void checkForAnniversary(User user, boolean firstOfMonth) throws AddressException, SystemException, PortalException {
		boolean isAnniversary = BonusDaysComputer.isAnniversaryEVZ(user);
		if (isAnniversary) {
			isAnniversary = BonusDaysComputer.isEntitledForEvoBonus(user);
			if (!isAnniversary) {
				log.debug("User " + user.getFullName() + " has max free days received.");
			}
		}

		if (isAnniversary) {
			log.debug("Today is anniversary EVZ for user: " + user.getUserId());
			boolean succesfullAllocation = allocateBonusDay(user, BenefitDayType.BONUS_EVOZON);

			if (succesfullAllocation) {
				sendMailForAnniversary(user, BenefitDayType.BONUS_EVOZON, firstOfMonth);
				log.info("Allocated a free day for user: " + user.getUserId());
			}
		}
	}

	private void sendMailForAnniversary(User user, BenefitDayType dayType, boolean firstOfMonth) throws AddressException, SystemException, PortalException {
		// Send email notification to user and Accounting
		int evozonYears = getTotalBonus(user, dayType); // total due bonus days

		EvoportalEmailUtil.sendMailAtAnniversary(user, getAccountingRegularUsers(), evozonYears, firstOfMonth);
		log.debug("Succesfully sent anniversary mail.");
	}

	private void handleNewYearTransition(User user) throws AddressException, SystemException, PortalException, ParseException {
		log.info("Handle new year passing for user: " + user.getFullName());

		UserFreeDaysWrapper userWrapper = new UserFreeDaysWrapper(user);
		// Days from the previous and current years
		int freeDaysFromLastYear = userWrapper.getFreeDaysFromLastYear();
		int initialFreeDaysFromLastYear = freeDaysFromLastYear;

		int freeDaysInCurrentYear = userWrapper.getFreeDaysInCurrentYear();
		int initialFreeDaysInCurrentYear = freeDaysInCurrentYear;

		int remainingExtraDays = userWrapper.getExtraDays();

		int newYearLegalDays = FreeDaysComputer.getCurrentTotalFreeDaysForUser(userWrapper.getUser());
		userWrapper.setLegalVacationDays(newYearLegalDays);

		// set dynamic attributes (bucket days)
		if (freeDaysInCurrentYear < 0) {
			newYearLegalDays = newYearLegalDays + freeDaysInCurrentYear;
			// => freeCurrent < newYearLegalDays
			freeDaysFromLastYear = 0;
		} else {
			freeDaysFromLastYear = freeDaysInCurrentYear + freeDaysFromLastYear;
		}
		freeDaysInCurrentYear = newYearLegalDays;

		if (remainingExtraDays > 0) {
			log.debug(userWrapper.getUser().getFullName() + " still has extra days: " + remainingExtraDays);
			BenefitDayLocalServiceUtil.saveExtraDay(userWrapper.getUserId(), BenefitDayType.EXTRA_DAYS.toString(), remainingExtraDays, "Reallocating extra days from last year.");
		}

		Extraction newYearExtraction = getVacationDaysTakenThisYear(BenefitDayLocalServiceUtil.getUserSelectedDate().get(Calendar.YEAR), userWrapper.getUser());
		int newYearDaysExtraction = newYearExtraction.getExtractedFromCurrentYear();
		int newYearExtraDaysExtraction = newYearExtraction.getExtractedFromExtraDays();

		userWrapper.setFreeDaysInCurrentYear(freeDaysInCurrentYear - newYearDaysExtraction);
		userWrapper.setFreeDaysFromLastYear(freeDaysFromLastYear);

		userWrapper.setRemainingFreeDaysFromLastYear(initialFreeDaysInCurrentYear + initialFreeDaysFromLastYear);

		userWrapper.setExtractedFreeDaysFromCurrentYear(newYearDaysExtraction);
		userWrapper.setExtractedFreeDaysFromLastYear(0);

		userWrapper.setExtraDays(remainingExtraDays - newYearExtraDaysExtraction);
	}

	private Extraction getVacationDaysTakenThisYear(int year, User user) throws SystemException {
		int extractedFromYear = 0;
		int extractedFromExtraDays = 0;
		int extractedFromLastYear = 0;

		Extraction yearExtraction = new ExtractionClp();
		yearExtraction.setExtractedFromYear(year);
		DateInterval yearInterval = new DateInterval(BusinessDaysManager.getStartYear(year), BusinessDaysManager.getEndYear(year));

		List<Inbox> newYearInboxes = InboxLocalServiceUtil.getInboxBetweenIntervalAndUser(yearInterval.getStartDate(), yearInterval.getEndDate(), user.getUserId());
		List<EvozonVacation> vacationsInYear = VacationDAO.convertToEvozonVacations(newYearInboxes);
		for (EvozonVacation evoVac : vacationsInYear) {
			if (!hasExtracted(evoVac)) {
				continue;
			}

			List<Extraction> extractions = evoVac.getExtractions();
			for (Extraction extraction : extractions) {
				if (extraction.getExtractedFromYear() == year) {
					extractedFromYear += extraction.getExtractedFromCurrentYear();
					extractedFromExtraDays += extraction.getExtractedFromExtraDays();
					extractedFromLastYear += extraction.getExtractedFromLastYear();
				}
			}
		}

		yearExtraction.setExtractedFromCurrentYear(extractedFromYear);
		yearExtraction.setExtractedFromExtraDays(extractedFromExtraDays);
		yearExtraction.setExtractedFromLastYear(extractedFromLastYear);

		return yearExtraction;
	}

	// TODO: this is present in VacationDaysWorkerAsWell and
	// EvozonFreeDaysAllocator, find a common place
	private boolean hasExtracted(EvozonVacation evoVac) {
		boolean hasDaysToExtract = false;
		if (VacationType.CO.name().equals(evoVac.getVacationType())) {
			Status lastStatus = VacationStatusUtil.getVacationStatus(evoVac, false);
			String newStatus = lastStatus.getNewStatus();

			hasDaysToExtract = VacationStatus.APPROVED.name().equals(newStatus);
			hasDaysToExtract |= VacationStatus.PENDING.name().equals(newStatus);
		}

		return hasDaysToExtract;
	}

	private boolean allocateBonusDay(User user, BenefitDayType type) throws AddressException, SystemException, PortalException {
		boolean success = true;
		log.debug("Allocationg bonus days for user: " + user.getFullName() + " in queue.");
		// Allocate only from next month! = put it in queue
		try {
			int bonusValue = BonusDaysComputer.getBonusValue();
			BenefitDayQueueLocalServiceUtil.saveBenefitDayQueue(user.getUserId(), type.toString(), bonusValue);
		} catch (Exception e) {
			log.error("error allocationg bonus day.", e);
			success = false;
		}
		return success;
	}

	private int getTotalBonus(User user, BenefitDayType type) {
		int value = 0;
		switch (type) {
		case BONUS_EVOZON:
			value = BonusDaysComputer.computeBonusEVZ(user, false);
			break;
		case BONUS_EXPERIENCE:
			value = BonusDaysComputer.computeBonusForExperience(user);
			break;
		case EXTRA_DAYS:
			break;
		default:
			break;
		}
		return value;
	}

}
