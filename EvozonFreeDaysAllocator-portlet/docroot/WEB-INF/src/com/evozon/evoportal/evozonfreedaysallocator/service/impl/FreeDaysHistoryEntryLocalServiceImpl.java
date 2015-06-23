/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.evozon.evoportal.evozonfreedaysallocator.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.evozon.evoportal.evozonfreedaysallocator.model.FreeDaysHistoryEntry;
import com.evozon.evoportal.evozonfreedaysallocator.service.base.FreeDaysHistoryEntryLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.SystemException;

/**
 * The implementation of the free days history entry local service.
 * 
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy
 * their definitions into the
 * {@link com.evozon.evoportal.evozonfreedaysallocator.service.FreeDaysHistoryEntryLocalService} interface.
 * 
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same VM.
 * </p>
 * 
 * @author Cristina Kiss
 * @see com.evozon.evoportal.evozonfreedaysallocator.service.base.FreeDaysHistoryEntryLocalServiceBaseImpl
 * @see com.evozon.evoportal.evozonfreedaysallocator.service.FreeDaysHistoryEntryLocalServiceUtil
 */
public class FreeDaysHistoryEntryLocalServiceImpl extends FreeDaysHistoryEntryLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 * 
	 * Never reference this interface directly. Always use {@link
	 * com.evozon.evoportal.evozonfreedaysallocator.service.FreeDaysHistoryEntryLocalServiceUtil} to access the free
	 * days history entry local service.
	 */

	private static final Logger log = Logger.getLogger(FreeDaysHistoryEntryLocalServiceImpl.class);

	public List<FreeDaysHistoryEntry> getAllHistory(long userId) throws SystemException {
		List<FreeDaysHistoryEntry> result = new ArrayList<FreeDaysHistoryEntry>();
		result = freeDaysHistoryEntryPersistence.findByUserId(userId);
		return result;
	}

	public String getMyString() {
		return "My String";
	}
	
	public long saveFreeDaysHistoryEntry(long userId, String operationType, int oldValue,
			int daysNo, int newValue, String eventType, String description) {
		long result = 0;
		try {
			FreeDaysHistoryEntry newEntry = createFreeDaysHistoryEntry(counterLocalService
					.increment(FreeDaysHistoryEntry.class.getSimpleName()));

			newEntry.setUserId(userId);

			newEntry.setOldValue(oldValue);
			newEntry.setOperation(operationType.toString());
			newEntry.setDaysNo(daysNo);
			newEntry.setNewValue(newValue);

			newEntry.setEventType(eventType.toString());
			newEntry.setDescription(description);
			newEntry.setCreateDate(new Date());

			newEntry.setNew(true);
			newEntry.persist();

			result = newEntry.getEntryId();
		} catch (SystemException e) {
			log.error(e.getMessage(), e);
		}
		return result;
	}
}