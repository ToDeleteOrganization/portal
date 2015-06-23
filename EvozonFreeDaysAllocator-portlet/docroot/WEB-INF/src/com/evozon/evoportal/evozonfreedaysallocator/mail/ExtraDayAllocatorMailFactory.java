package com.evozon.evoportal.evozonfreedaysallocator.mail;

import com.liferay.portal.model.User;

public class ExtraDayAllocatorMailFactory extends AbstractNotifiableMailFactory {

	private static final String EXTRA_DAYS_MAIL_SUBJECT = "Extra free days";

	private final int extraDays;

	private final String extraDayDescrition;

	public ExtraDayAllocatorMailFactory(int extraDays, String extraDayDescrition) {
		this.extraDays = extraDays;
		this.extraDayDescrition = extraDayDescrition;
	}

	protected String getReceivedUserMailContent() {
		StringBuilder build = new StringBuilder();

		build.append("Dear " + getReceivingUser().getFirstName() + ",");
		build.append(NEW_LINE + NEW_LINE);

		build.append("You have received " + extraDays + " extra day(s)");
		if ((extraDayDescrition) != null && (extraDayDescrition.isEmpty())) {
			build.append("for '" + extraDayDescrition + "'");
		}

		build.append(".");
		build.append(NEW_LINE);
		build.append("The new gifted day(s) will be allocated and usable immediately.");
		build.append(NEW_LINE);

		return build.toString();
	}

	protected String getNotifiedUserMailContent(User toUser) {
		StringBuilder build = new StringBuilder();

		build.append("Dear " + toUser.getFirstName() + ",");
		build.append(NEW_LINE + NEW_LINE);

		build.append(getReceivingUser().getFullName());
		build.append(" has received ");
		build.append(extraDays);
		build.append(" extra free day(s)");
		if ((extraDayDescrition) != null && (extraDayDescrition.isEmpty())) {
			build.append("for '" + extraDayDescrition + "'");
		}

		build.append(".");
		build.append(NEW_LINE);
		build.append("The new gifted day(s) will be allocated and usable immediately.");
		build.append(NEW_LINE);

		return build.toString();
	}

	protected String getMailSubject() {
		return EXTRA_DAYS_MAIL_SUBJECT;
	}

}
