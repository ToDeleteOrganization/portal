package com.evozon.evoportal.evozonfreedaysallocator.mail;

import java.util.List;

import com.liferay.portal.model.User;

public interface AllocatorMailFormatFactory {

	public static final String NEW_LINE = "<br/>";

	public AllocatorMail getReceivingUserMailFormat();

	public void setReceivingUser(User user);

	public User getReceivingUser();
	
	// TODO: rethink this
	public List<AllocatorMail> getNotifiedUserAllocatorMailFormat();
}
