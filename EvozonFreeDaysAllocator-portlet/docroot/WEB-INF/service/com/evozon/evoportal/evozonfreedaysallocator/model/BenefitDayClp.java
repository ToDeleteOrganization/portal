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

package com.evozon.evoportal.evozonfreedaysallocator.model;

import com.evozon.evoportal.evozonfreedaysallocator.service.BenefitDayLocalServiceUtil;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.impl.BaseModelImpl;
import com.liferay.portal.util.PortalUtil;

import java.io.Serializable;

import java.lang.reflect.Proxy;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Cristina Kiss
 */
public class BenefitDayClp extends BaseModelImpl<BenefitDay>
	implements BenefitDay {
	public BenefitDayClp() {
	}

	public Class<?> getModelClass() {
		return BenefitDay.class;
	}

	public String getModelClassName() {
		return BenefitDay.class.getName();
	}

	public long getPrimaryKey() {
		return _entryId;
	}

	public void setPrimaryKey(long primaryKey) {
		setEntryId(primaryKey);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_entryId);
	}

	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("entryId", getEntryId());
		attributes.put("type", getType());
		attributes.put("userId", getUserId());
		attributes.put("daysNo", getDaysNo());
		attributes.put("allocatedDate", getAllocatedDate());
		attributes.put("comment", getComment());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long entryId = (Long)attributes.get("entryId");

		if (entryId != null) {
			setEntryId(entryId);
		}

		String type = (String)attributes.get("type");

		if (type != null) {
			setType(type);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		Integer daysNo = (Integer)attributes.get("daysNo");

		if (daysNo != null) {
			setDaysNo(daysNo);
		}

		Date allocatedDate = (Date)attributes.get("allocatedDate");

		if (allocatedDate != null) {
			setAllocatedDate(allocatedDate);
		}

		String comment = (String)attributes.get("comment");

		if (comment != null) {
			setComment(comment);
		}
	}

	public long getEntryId() {
		return _entryId;
	}

	public void setEntryId(long entryId) {
		_entryId = entryId;
	}

	public String getType() {
		return _type;
	}

	public void setType(String type) {
		_type = type;
	}

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	public String getUserUuid() throws SystemException {
		return PortalUtil.getUserValue(getUserId(), "uuid", _userUuid);
	}

	public void setUserUuid(String userUuid) {
		_userUuid = userUuid;
	}

	public int getDaysNo() {
		return _daysNo;
	}

	public void setDaysNo(int daysNo) {
		_daysNo = daysNo;
	}

	public Date getAllocatedDate() {
		return _allocatedDate;
	}

	public void setAllocatedDate(Date allocatedDate) {
		_allocatedDate = allocatedDate;
	}

	public String getComment() {
		return _comment;
	}

	public void setComment(String comment) {
		_comment = comment;
	}

	public BaseModel<?> getBenefitDayRemoteModel() {
		return _benefitDayRemoteModel;
	}

	public void setBenefitDayRemoteModel(BaseModel<?> benefitDayRemoteModel) {
		_benefitDayRemoteModel = benefitDayRemoteModel;
	}

	public void persist() throws SystemException {
		if (this.isNew()) {
			BenefitDayLocalServiceUtil.addBenefitDay(this);
		}
		else {
			BenefitDayLocalServiceUtil.updateBenefitDay(this);
		}
	}

	@Override
	public BenefitDay toEscapedModel() {
		return (BenefitDay)Proxy.newProxyInstance(BenefitDay.class.getClassLoader(),
			new Class[] { BenefitDay.class }, new AutoEscapeBeanHandler(this));
	}

	@Override
	public Object clone() {
		BenefitDayClp clone = new BenefitDayClp();

		clone.setEntryId(getEntryId());
		clone.setType(getType());
		clone.setUserId(getUserId());
		clone.setDaysNo(getDaysNo());
		clone.setAllocatedDate(getAllocatedDate());
		clone.setComment(getComment());

		return clone;
	}

	public int compareTo(BenefitDay benefitDay) {
		int value = 0;

		value = getType().compareTo(benefitDay.getType());

		if (value != 0) {
			return value;
		}

		return 0;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}

		BenefitDayClp benefitDay = null;

		try {
			benefitDay = (BenefitDayClp)obj;
		}
		catch (ClassCastException cce) {
			return false;
		}

		long primaryKey = benefitDay.getPrimaryKey();

		if (getPrimaryKey() == primaryKey) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return (int)getPrimaryKey();
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(13);

		sb.append("{entryId=");
		sb.append(getEntryId());
		sb.append(", type=");
		sb.append(getType());
		sb.append(", userId=");
		sb.append(getUserId());
		sb.append(", daysNo=");
		sb.append(getDaysNo());
		sb.append(", allocatedDate=");
		sb.append(getAllocatedDate());
		sb.append(", comment=");
		sb.append(getComment());
		sb.append("}");

		return sb.toString();
	}

	public String toXmlString() {
		StringBundler sb = new StringBundler(22);

		sb.append("<model><model-name>");
		sb.append(
			"com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDay");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>entryId</column-name><column-value><![CDATA[");
		sb.append(getEntryId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>type</column-name><column-value><![CDATA[");
		sb.append(getType());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userId</column-name><column-value><![CDATA[");
		sb.append(getUserId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>daysNo</column-name><column-value><![CDATA[");
		sb.append(getDaysNo());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>allocatedDate</column-name><column-value><![CDATA[");
		sb.append(getAllocatedDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>comment</column-name><column-value><![CDATA[");
		sb.append(getComment());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _entryId;
	private String _type;
	private long _userId;
	private String _userUuid;
	private int _daysNo;
	private Date _allocatedDate;
	private String _comment;
	private BaseModel<?> _benefitDayRemoteModel;
}