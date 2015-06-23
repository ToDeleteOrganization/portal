package com.evozon.evoportal.evozonfreedaysallocator.mail;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.apache.log4j.Logger;

import com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDayQueue;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;

public class MonthlyUsersAnniversariesMailFactory implements AllocatorMailFormatFactory {

	private static final Logger log = Logger.getLogger(MonthlyUsersAnniversariesMailFactory.class);

	private static final String PERSONAL_IDENTIFICATION_NUMBER = "Personal Identification Number";

	private final List<BenefitDayQueue> queuedDays;

	private final List<User> usersToSend;

	public MonthlyUsersAnniversariesMailFactory(final List<BenefitDayQueue> queuedDays, final List<User> usersToSend) {
		this.queuedDays = queuedDays;
		this.usersToSend = usersToSend;
	}

	public List<AllocatorMail> getNotifiedUserAllocatorMailFormat() {
		final List<AllocatorMail> allocators = new ArrayList<AllocatorMail>();

		if (!usersToSend.isEmpty()) {
			final String notifiedUserMailContent = createReceivingUserMailContent();

			for (final User user : usersToSend) {
				final AllocatorMail sam = new SimpleAllocatorMail();
				sam.setMailSubject(getMailSubject());
				sam.setNotifiedUsers(user);
				sam.setMailContent(notifiedUserMailContent);

				allocators.add(sam);
			}
		}
		return allocators;
	}

	private String createReceivingUserMailContent() {
		sortAnniversariesByDate();

		StringBuilder mailContent = new StringBuilder();
		mailContent.append("The following users had/will have, an evozon anniversary: <br />");

		for (BenefitDayQueue queuedDay : queuedDays) {
			long userId = queuedDay.getUserId();
			try {
				User user = UserLocalServiceUtil.getUser(userId);

				Calendar addedDateCal = Calendar.getInstance();
				addedDateCal.setTime(queuedDay.getAddedDate());

				String addedDateStr = addedDateCal.get(Calendar.DAY_OF_MONTH) + "/";
				addedDateStr += addedDateCal.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault()) + "/";
				addedDateStr += addedDateCal.get(Calendar.YEAR);

				mailContent.append(" - <b>");
				mailContent.append(user.getLastName());
				mailContent.append(StringPool.SPACE);
				mailContent.append(user.getFirstName());
				mailContent.append(" (" + findUserCNP(user) + ")");
				mailContent.append("</b> in ");
				mailContent.append(addedDateStr);
				mailContent.append(AllocatorMailFormatFactory.NEW_LINE);
			} catch (Exception e) {
				log.error("Error getting user name for mail sending, for user: " + userId, e);
			}
		}
		return mailContent.toString();
	}

	private void sortAnniversariesByDate() {
		final Comparator<BenefitDayQueue> comparator = new Comparator<BenefitDayQueue>() {
			public int compare(BenefitDayQueue bDay1, BenefitDayQueue bDay2) {
				int result = 0;

				Date bDayAddedDay1 = bDay1.getAddedDate();
				Date bDayAddedDay2 = bDay2.getAddedDate();

				if ((bDayAddedDay1 != null) && (bDayAddedDay2 != null)) {
					result = bDayAddedDay1.compareTo(bDayAddedDay2);
				}

				return result;
			}
		};
		Collections.sort(queuedDays, comparator);
	}

	private String getMailSubject() {
		Calendar today = Calendar.getInstance();
		String monthName = today.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault());
		return "Users who had anniversay in " + monthName;
	}

	private static String findUserCNP(User user) {
		String userCNP = StringPool.BLANK;
		try {
			userCNP = user.getExpandoBridge().getAttribute(PERSONAL_IDENTIFICATION_NUMBER, false).toString();
		} catch (Exception e) {
			log.warn("Cannot get user CNP: " + user.getFullName());
		}
		return userCNP;
	}

	public AllocatorMail getReceivingUserMailFormat() {
		return null;
	}

	public void setReceivingUser(User toUser) {
		// nothing
	}

	public User getReceivingUser() {
		return null;
	}
}
