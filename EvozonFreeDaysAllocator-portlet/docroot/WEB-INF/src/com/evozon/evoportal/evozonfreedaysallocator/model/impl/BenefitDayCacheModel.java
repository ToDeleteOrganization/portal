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

import com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDay;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Serializable;

import java.util.Date;

/**
 * The cache model class for representing BenefitDay in entity cache.
 *
 * @author Cristina Kiss
 * @see BenefitDay
 * @generated
 */
public class BenefitDayCacheModel implements CacheModel<BenefitDay>,
	Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(13);

		sb.append("{entryId=");
		sb.append(entryId);
		sb.append(", type=");
		sb.append(type);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", daysNo=");
		sb.append(daysNo);
		sb.append(", allocatedDate=");
		sb.append(allocatedDate);
		sb.append(", comment=");
		sb.append(comment);
		sb.append("}");

		return sb.toString();
	}

	public BenefitDay toEntityModel() {
		BenefitDayImpl benefitDayImpl = new BenefitDayImpl();

		benefitDayImpl.setEntryId(entryId);

		if (type == null) {
			benefitDayImpl.setType(StringPool.BLANK);
		}
		else {
			benefitDayImpl.setType(type);
		}

		benefitDayImpl.setUserId(userId);
		benefitDayImpl.setDaysNo(daysNo);

		if (allocatedDate == Long.MIN_VALUE) {
			benefitDayImpl.setAllocatedDate(null);
		}
		else {
			benefitDayImpl.setAllocatedDate(new Date(allocatedDate));
		}

		if (comment == null) {
			benefitDayImpl.setComment(StringPool.BLANK);
		}
		else {
			benefitDayImpl.setComment(comment);
		}

		benefitDayImpl.resetOriginalValues();

		return benefitDayImpl;
	}

	public long entryId;
	public String type;
	public long userId;
	public int daysNo;
	public long allocatedDate;
	public String comment;
}