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

package com.evozon.evoportal.evozonfreedaysallocator.model.impl;

import com.evozon.evoportal.evozonfreedaysallocator.model.FreeDaysHistoryEntry;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Serializable;

import java.util.Date;

/**
 * The cache model class for representing FreeDaysHistoryEntry in entity cache.
 *
 * @author Cristina Kiss
 * @see FreeDaysHistoryEntry
 * @generated
 */
public class FreeDaysHistoryEntryCacheModel implements CacheModel<FreeDaysHistoryEntry>,
	Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(19);

		sb.append("{entryId=");
		sb.append(entryId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", operation=");
		sb.append(operation);
		sb.append(", oldValue=");
		sb.append(oldValue);
		sb.append(", daysNo=");
		sb.append(daysNo);
		sb.append(", newValue=");
		sb.append(newValue);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", eventType=");
		sb.append(eventType);
		sb.append(", description=");
		sb.append(description);
		sb.append("}");

		return sb.toString();
	}

	public FreeDaysHistoryEntry toEntityModel() {
		FreeDaysHistoryEntryImpl freeDaysHistoryEntryImpl = new FreeDaysHistoryEntryImpl();

		freeDaysHistoryEntryImpl.setEntryId(entryId);
		freeDaysHistoryEntryImpl.setUserId(userId);

		if (operation == null) {
			freeDaysHistoryEntryImpl.setOperation(StringPool.BLANK);
		}
		else {
			freeDaysHistoryEntryImpl.setOperation(operation);
		}

		freeDaysHistoryEntryImpl.setOldValue(oldValue);
		freeDaysHistoryEntryImpl.setDaysNo(daysNo);
		freeDaysHistoryEntryImpl.setNewValue(newValue);

		if (createDate == Long.MIN_VALUE) {
			freeDaysHistoryEntryImpl.setCreateDate(null);
		}
		else {
			freeDaysHistoryEntryImpl.setCreateDate(new Date(createDate));
		}

		if (eventType == null) {
			freeDaysHistoryEntryImpl.setEventType(StringPool.BLANK);
		}
		else {
			freeDaysHistoryEntryImpl.setEventType(eventType);
		}

		if (description == null) {
			freeDaysHistoryEntryImpl.setDescription(StringPool.BLANK);
		}
		else {
			freeDaysHistoryEntryImpl.setDescription(description);
		}

		freeDaysHistoryEntryImpl.resetOriginalValues();

		return freeDaysHistoryEntryImpl;
	}

	public long entryId;
	public long userId;
	public String operation;
	public int oldValue;
	public int daysNo;
	public int newValue;
	public long createDate;
	public String eventType;
	public String description;
}