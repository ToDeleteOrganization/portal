package com.evozon.evoportal.evozonfreedaysallocator.mail;

import java.util.List;

import com.liferay.portal.model.User;

public interface NotifiableAllocatorMailFactory extends AllocatorMailFormatFactory {

	public void setNotifiedUsers(List<User> users);

	public List<User> getNotifiedUsers();

}
