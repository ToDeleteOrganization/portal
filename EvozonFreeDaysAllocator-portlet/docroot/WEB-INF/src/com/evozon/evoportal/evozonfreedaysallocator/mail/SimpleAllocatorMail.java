package com.evozon.evoportal.evozonfreedaysallocator.mail;

import com.liferay.portal.model.User;

public class SimpleAllocatorMail implements AllocatorMail {

	private String mailSubject;

	private String mailContent;

	private boolean htmlFormat = true;

	private User receivingUser;

	private User notifiedUser;

	public String getMailSubject() {
		return mailSubject;
	}

	public String getMailContent() {
		return mailContent;
	}

	public boolean getHTMLFormat() {
		return htmlFormat;
	}

	public void setMailSubject(String mailSubject) {
		this.mailSubject = mailSubject;
	}

	public void setMailContent(String mailContent) {
		this.mailContent = mailContent;
	}

	public void setHTMLFormat(boolean htmlFormat) {
		this.htmlFormat = htmlFormat;
	}

	public User getReceivingUser() {
		return receivingUser;
	}

	public void setReceivingUser(User user) {
		this.receivingUser = user;
	}

	public User getNotifiedUser() {
		return notifiedUser;
	}

	public void setNotifiedUsers(User notifiedUser) {
		this.notifiedUser = notifiedUser;
	}

}
