package com.evozon.evoportal.evozonfreedaysallocator.mail;

import java.util.ArrayList;
import java.util.List;

import com.liferay.portal.model.User;

public abstract class AbstractNotifiableMailFactory implements NotifiableAllocatorMailFactory {

	private List<User> notifiedUsers;

	private User receivingUser;

	public void setReceivingUser(User user) {
		this.receivingUser = user;
	}

	public User getReceivingUser() {
		return this.receivingUser;
	}

	public void setNotifiedUsers(List<User> users) {
		this.notifiedUsers = users;
	}

	public List<User> getNotifiedUsers() {
		return notifiedUsers;
	}

	public List<AllocatorMail> getNotifiedUserAllocatorMailFormat() {
		final List<AllocatorMail> allocatorMails = new ArrayList<AllocatorMail>();
		final List<User> usersToNotify = getNotifiedUsers();

		if (usersToNotify != null) {
			for (User user : usersToNotify) {
				final AllocatorMail samf = new SimpleAllocatorMail();
				samf.setMailSubject(getMailSubject());
				samf.setMailContent(getNotifiedUserMailContent(user));
				samf.setNotifiedUsers(user);
				allocatorMails.add(samf);
			}
		}

		return allocatorMails;
	}

	public AllocatorMail getReceivingUserMailFormat() {
		AllocatorMail samf = null;
		final User recUser = getReceivingUser();

		if (recUser != null) {
			samf = new SimpleAllocatorMail();
			samf.setMailSubject(getMailSubject());
			samf.setMailContent(getReceivedUserMailContent());
			samf.setReceivingUser(getReceivingUser());
		}

		return samf;
	}

	protected abstract String getNotifiedUserMailContent(User toUser);
	
	protected abstract String getReceivedUserMailContent();

	protected abstract String getMailSubject();
}
