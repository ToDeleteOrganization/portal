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

import com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDayQueue;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Serializable;

import java.util.Date;

/**
 * The cache model class for representing BenefitDayQueue in entity cache.
 *
 * @author Cristina Kiss
 * @see BenefitDayQueue
 * @generated
 */
public class BenefitDayQueueCacheModel implements CacheModel<BenefitDayQueue>,
	Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(15);

		sb.append("{entryId=");
		sb.append(entryId);
		sb.append(", type=");
		sb.append(type);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", daysNo=");
		sb.append(daysNo);
		sb.append(", queued=");
		sb.append(queued);
		sb.append(", addedDate=");
		sb.append(addedDate);
		sb.append(", allocatedDate=");
		sb.append(allocatedDate);
		sb.append("}");

		return sb.toString();
	}

	public BenefitDayQueue toEntityModel() {
		BenefitDayQueueImpl benefitDayQueueImpl = new BenefitDayQueueImpl();

		benefitDayQueueImpl.setEntryId(entryId);

		if (type == null) {
			benefitDayQueueImpl.setType(StringPool.BLANK);
		}
		else {
			benefitDayQueueImpl.setType(type);
		}

		benefitDayQueueImpl.setUserId(userId);
		benefitDayQueueImpl.setDaysNo(daysNo);
		benefitDayQueueImpl.setQueued(queued);

		if (addedDate == Long.MIN_VALUE) {
			benefitDayQueueImpl.setAddedDate(null);
		}
		else {
			benefitDayQueueImpl.setAddedDate(new Date(addedDate));
		}

		if (allocatedDate == Long.MIN_VALUE) {
			benefitDayQueueImpl.setAllocatedDate(null);
		}
		else {
			benefitDayQueueImpl.setAllocatedDate(new Date(allocatedDate));
		}

		benefitDayQueueImpl.resetOriginalValues();

		return benefitDayQueueImpl;
	}

	public long entryId;
	public String type;
	public long userId;
	public int daysNo;
	public boolean queued;
	public long addedDate;
	public long allocatedDate;
}