package com.evozon.evoportal.evozonfreedaysallocator.mail;

import com.evozon.evoportal.evozonfreedaysallocator.UpdatedFieldMarker;
import com.liferay.portal.model.User;

public class AccountUpdateMailFactory extends AbstractNotifiableMailFactory {

	private static final String ACCOUNT_UPDATE_MAIL_SUBJECT = "Updated Contact Details";
	
	private final UpdatedFieldMarker marker;

	public AccountUpdateMailFactory(UpdatedFieldMarker marker) {
		this.marker = marker;
	}

	protected String getNotifiedUserMailContent(User toUser) {
		StringBuilder build = new StringBuilder();

		build.append("Dear " + toUser.getFirstName() + ",");
		build.append(NEW_LINE + NEW_LINE);

		build.append("User: " + getReceivingUser().getFullName() + " has updated his/her details:");
		build.append(NEW_LINE);
		build.append(marker.isSpouse() ? "Spouse" + NEW_LINE : "");
		build.append(marker.isChildren() ? "Children" + NEW_LINE : "");
		build.append(marker.isSkype() ? "Skype contact" + NEW_LINE : "");
		build.append(marker.isIdCard() ? "ID Card" + NEW_LINE : "");
		build.append(marker.isPhones() ? "Phone numbers" + NEW_LINE : "");
		build.append(marker.isBuilding() ? "Building" + NEW_LINE : "");
		build.append(marker.isAddresses() ? "Address" + NEW_LINE : "");

		return build.toString();
	}

	protected String getMailSubject() {
		return ACCOUNT_UPDATE_MAIL_SUBJECT;
	}

	public AllocatorMail getReceivingUserMailFormat() {
		// the 'updated' user won't receive a notification, yet
		return null;
	}
	
	protected String getReceivedUserMailContent() {
		// the 'updated' user won't receive a notification, yet
		return null;
	}

}
