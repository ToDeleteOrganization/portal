package com.evozon.evoportal.evozonfreedaysallocator;

import java.util.List;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import org.apache.log4j.Logger;

import com.evozon.evoportal.evozonfreedaysallocator.mail.AccountUpdateMailFactory;
import com.evozon.evoportal.evozonfreedaysallocator.mail.AllocatorMailFormatFactory;
import com.evozon.evoportal.evozonfreedaysallocator.mail.AllocatorMailSender;
import com.evozon.evoportal.evozonfreedaysallocator.mail.AnniversaryMailFactory;
import com.evozon.evoportal.evozonfreedaysallocator.mail.ExtraDayAllocatorMailFactory;
import com.evozon.evoportal.evozonfreedaysallocator.mail.MonthlyUsersAnniversariesMailFactory;
import com.evozon.evoportal.evozonfreedaysallocator.mail.NotifiableAllocatorMailFactory;
import com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDayQueue;
import com.liferay.mail.service.MailServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.mail.MailMessage;
import com.liferay.portal.model.User;

/**
 * Utility class for sending emails.
 */
public final class EvoportalEmailUtil {

	private static final Logger log = Logger.getLogger(EvoportalEmailUtil.class);

	private static final String EVOPORTAL_EMAIL_ADDRESS = "evoportal@evozon.com";

	private static final String NEW_LINE = "<br/>";

	private static final AllocatorMailSender sender = new AllocatorMailSender();

	public static void sendMailAtNewYear(List<User> users, int currentFreeDays, int bonusEVZ, int bonusEXP, int last) throws AddressException, SystemException, PortalException {
		String mailSubject = "New Year free days allocation!";
		int totalFreeDays = currentFreeDays + last;

		User user = users.get(0);

		String userFullName = user.getFirstName() + " " + user.getLastName();
		String userTitle = "Dear " + userFullName + "," + NEW_LINE + NEW_LINE;

		String userContent = getEmailIntro(true, userFullName, totalFreeDays);
		String execContent = getEmailIntro(false, userFullName, totalFreeDays);

		String emailDays = getEmailDays(last, bonusEVZ, bonusEXP);
		userContent += emailDays;
		execContent += emailDays;

		String signature = NEW_LINE + NEW_LINE + "Sincerely," + NEW_LINE + "EvoPortal Team";

		String userMail = userTitle + userContent + signature;
		sendMail(user, mailSubject, userMail);
		log.debug(userContent);

		for (int i = 1; i < users.size(); i++) {
			User userExec = users.get(i);

			String execTitle = "Dear " + userExec.getFirstName() + " " + userExec.getLastName() + "," + NEW_LINE + NEW_LINE;
			String execMail = execTitle + execContent + signature;

			sendMail(userExec, mailSubject, execMail);
		}
	}

	private static String getEmailIntro(boolean user, String userName, int totalFreeDays) {
		String legalDays = "received 21 free days from legal behalf." + NEW_LINE;

		StringBuffer result = new StringBuffer();
		result.append(user ? "You have " : userName + " has ");
		result.append(legalDays);
		result.append("Currently ");
		result.append(user ? "you have " : userName + " has ");
		result.append(totalFreeDays + " free days." + NEW_LINE);

		return result.toString();
	}

	private static String getEmailDays(int last, int bonusEVZ, int bonusEXP) {
		StringBuffer result = new StringBuffer();
		if (last > 0 || bonusEVZ > 0 || bonusEXP > 0) { // we have some content to show
			result.append("<ul>");
			if (last > 0) {
				result.append("<li>");
				result.append(last + " of which are from last year");
				result.append("</li>");
			}
			if (bonusEVZ > 0) {
				result.append("<li>");
				result.append(bonusEVZ + " of which are for being an Evozon employee for " + bonusEVZ + " years");
				result.append("</li>");
			}
			if (bonusEXP > 0) {
				result.append("<li>");
				result.append(bonusEXP + " days for having " + bonusEXP * 24 + " months of experience");
				result.append("</li>");
			}
			result.append("</ul>");
		}
		return result.toString();
	}

	public static void sendMailAtAnniversary(User receivingUser, List<User> allUsers, int evozonYears, boolean firstOfMonth) throws AddressException, SystemException, PortalException {
		NotifiableAllocatorMailFactory amf = new AnniversaryMailFactory(evozonYears, firstOfMonth);
		amf.setReceivingUser(receivingUser);
		amf.setNotifiedUsers(allUsers);

		sendMail(amf);
	}

	// TODO: check if this is used somewhere
	public static void sendMailAtAnniversary(List<User> users, int totalFreeDays, int lastYearDays, int bonusEVZ, int bonusEXP) throws AddressException, SystemException, PortalException {
		User receivingUser = users.remove(0);

		String title = (bonusEVZ > 0) ? "+1" : "+2";
		String mailSubject = title + " year in Evozon";

		String mailContent = receivingUser.getFirstName() + " " + receivingUser.getLastName() + " has received a free day for ";
		if (bonusEVZ > 0) {
			mailContent += "being an Evozon employee for " + bonusEVZ + " year(s).<br/>";
		} else if (bonusEXP > 0) {
			mailContent += "having " + bonusEXP * 24 + " months of experience.<br/>";
		}
		mailContent += "<br/>";
		mailContent += "The new bonus day will be allocated and usable starting 1st of next month.";

		mailContent += "<br/>";
		mailContent += "<br/>";
		mailContent += receivingUser.getFirstName() + " currently has " + totalFreeDays + " free days.";
		if (lastYearDays > 0) {
			mailContent = mailContent.substring(0, mailContent.length() - 1) + " (" + lastYearDays + " of which are from last year).";
		}
		mailContent += "<br/>";
		mailContent += "The total of free days will be updated on the 1st of next month.";

		sendMail(receivingUser, mailSubject, mailContent);
		log.debug(mailContent);

		for (int i = 1; i < users.size(); i++) {
			User userExec = users.get(i);
			sendMail(userExec, mailSubject, mailContent);
		}
	}

	public static void sendMailExtraDays(User user, List<User> notifiedUsers, String extraDayDescrition, int extraDays) throws AddressException, SystemException, PortalException {
		NotifiableAllocatorMailFactory edMailFactory = new ExtraDayAllocatorMailFactory(extraDays, extraDayDescrition);
		edMailFactory.setReceivingUser(user);
		edMailFactory.setNotifiedUsers(notifiedUsers);

		sendMail(edMailFactory);		
	}

	public static void sendMailAccountUpdate(List<User> users, UpdatedFieldMarker marker) throws AddressException, SystemException, PortalException {
		User updatedUser = users.remove(0); // remove the user form the list

		NotifiableAllocatorMailFactory accountUpdateFactory = new AccountUpdateMailFactory(marker);
		accountUpdateFactory.setReceivingUser(updatedUser);
		accountUpdateFactory.setNotifiedUsers(users);

		sendMail(accountUpdateFactory);
	}

	public static void sendMailWithAnniversaryUsers(List<User> toUsers, List<BenefitDayQueue> queuedDays) throws AddressException, SystemException, PortalException {
		final AllocatorMailFormatFactory mamf = new MonthlyUsersAnniversariesMailFactory(queuedDays, toUsers);
		sendMail(mamf);
	}

	private static void sendMail(AllocatorMailFormatFactory amff) {
		sender.setAllocatorMail(amff);
		sender.sendMails();		
	}

	private static void sendMail(User user, String mailSubject, String mailContent) throws AddressException, SystemException, PortalException {
		InternetAddress from = new InternetAddress(EVOPORTAL_EMAIL_ADDRESS);
		InternetAddress to = new InternetAddress(user.getEmailAddress());

		MailMessage mailMessage = new MailMessage(from, to, mailSubject, mailContent, true);

		MailServiceUtil.sendEmail(mailMessage);
	}

}
