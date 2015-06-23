package com.evozon.evoportal.evozonfreedaysallocator.mail;

import com.liferay.portal.model.User;

public interface AllocatorMail {

	public User getReceivingUser();

	public void setReceivingUser(User user);

	public User getNotifiedUser();

	public void setNotifiedUsers(User notifiedUser);

	public String getMailSubject();

	public String getMailContent();

	public boolean getHTMLFormat();

	public void setMailSubject(String mailSubject);

	public void setMailContent(String mailContent);

	public void setHTMLFormat(boolean htmlFormat);
}
