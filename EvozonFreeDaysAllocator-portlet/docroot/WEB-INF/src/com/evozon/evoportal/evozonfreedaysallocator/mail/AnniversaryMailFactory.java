package com.evozon.evoportal.evozonfreedaysallocator.mail;

import com.liferay.portal.model.User;

public class AnniversaryMailFactory extends AbstractNotifiableMailFactory {

	private static final String ANNIVERSARY_MAIL_SUBJECT = "+1 year in Evozon";

	private int evozonYears = 0;

	private boolean firstOfMonth = true;

	public AnniversaryMailFactory(int evozonYears, boolean firstOfMonth) {
		this.evozonYears = evozonYears;
		this.firstOfMonth = firstOfMonth;
	}

	protected String getNotifiedUserMailContent(User toUser) {
		StringBuilder build = new StringBuilder();

		build.append("Dear " + toUser.getFirstName() + ",");
		build.append(NEW_LINE + NEW_LINE);

		build.append(getReceivingUser().getFullName());
		build.append(" has received a free day for ");
		build.append("being an Evozon employee for " + evozonYears + " year(s).<br/>");
		build.append(NEW_LINE);

		if (firstOfMonth) {
			build.append("The new bonus day will be allocated and usable starting from the beginning of this month.");
		} else {
			build.append("The new bonus day will be allocated and usable starting 1st of next month.");
		}

		return build.toString();
	}

	protected String getReceivedUserMailContent() {
		return null;
	}

	protected String getMailSubject() {
		return ANNIVERSARY_MAIL_SUBJECT;
	}

}
