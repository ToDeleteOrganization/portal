package com.evozon.evoportal.evozonfreedaysallocator.mail;

import java.util.List;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import org.apache.log4j.Logger;

import com.liferay.mail.service.MailServiceUtil;
import com.liferay.portal.kernel.mail.MailMessage;
import com.liferay.portal.model.User;

public final class AllocatorMailSender {

	private static final Logger log = Logger.getLogger(AllocatorMailSender.class);

	private static final String EVOPORTAL_EMAIL_ADDRESS = "evoportal@evozon.com";

	private final InternetAddress fromAddress;

	private AllocatorMailFormatFactory allocatorMailFactory;

	public AllocatorMailSender() {
		this(AllocatorMailSender.EVOPORTAL_EMAIL_ADDRESS);
	}

	public AllocatorMailSender(final String fromMail) {
		fromAddress = createMailInternetAddress(fromMail);
		if (fromAddress == null) {
			throw new RuntimeException("Cannoe initialzie Allcoator Mail Sending for: " + fromMail);
		}
	}

	public void setAllocatorMail(final AllocatorMailFormatFactory allocatorMailFactory) {
		this.allocatorMailFactory = allocatorMailFactory;
	}

	public void sendMails() {
		if (this.allocatorMailFactory == null) {
			log.error("No mail allocator waas found!");
			throw new RuntimeException("No allocator mail factory was found");
		}

		final InternetAddress evoportalTeamAddress = createMailInternetAddress(AllocatorMailSender.EVOPORTAL_EMAIL_ADDRESS);
		if (evoportalTeamAddress == null) {
			throw new RuntimeException("Cannot create evoportal mail internet address.");
		}

		sendMailToReceivingUser(evoportalTeamAddress);
		sendMailToNotifiedUsers(evoportalTeamAddress);
	}

	private void sendMailToReceivingUser(InternetAddress fromAddress) {
		final AllocatorMail recUserMail = allocatorMailFactory.getReceivingUserMailFormat();

		if (recUserMail != null) {
			final User receivingUser = allocatorMailFactory.getReceivingUser();
			final InternetAddress toUserAddress = createMailInternetAddress(receivingUser.getEmailAddress());

			if (toUserAddress != null) {
				sendMailFromTo(fromAddress, toUserAddress, recUserMail);

			} else if (log.isDebugEnabled()) {
				log.debug("Could not create internet address for user: " + receivingUser.getFullName());
			}
		}
	}

	private void sendMailToNotifiedUsers(final InternetAddress fromAddress) {
		final List<AllocatorMail> notificationMails = allocatorMailFactory.getNotifiedUserAllocatorMailFormat();

		for (final AllocatorMail notificationMail : notificationMails) {
			final User toUser = notificationMail.getNotifiedUser();
			final InternetAddress toUserAddress = createMailInternetAddress(toUser.getEmailAddress());

			if (toUserAddress != null) {
				sendMailFromTo(fromAddress, toUserAddress, notificationMail);

			} else if (log.isDebugEnabled()) {
				log.debug("Could not create internet address for user: " + toUser.getFullName());
			}
		}
	}

	private void sendMailFromTo(final InternetAddress from, final InternetAddress to, final AllocatorMail allocatorMail) {
		final String mailSubject = allocatorMail.getMailSubject();
		final String mailContent = allocatorMail.getMailContent();
		final boolean htmlFormat = allocatorMail.getHTMLFormat();
		final MailMessage mailMessage = new MailMessage(from, to, mailSubject, mailContent, htmlFormat);

		MailServiceUtil.sendEmail(mailMessage);
	}

	private InternetAddress createMailInternetAddress(final String emailAddress) {
		InternetAddress address = null;
		try {
			address = new InternetAddress(emailAddress);
		} catch (AddressException e) {
			log.error(e);
		}

		return address;
	}
}
