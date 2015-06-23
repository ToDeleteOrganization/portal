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

import com.evozon.evoportal.evozonfreedaysallocator.service.FreeDaysHistoryEntryLocalServiceUtil;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.DateUtil;
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
public class FreeDaysHistoryEntryClp extends BaseModelImpl<FreeDaysHistoryEntry>
	implements FreeDaysHistoryEntry {
	public FreeDaysHistoryEntryClp() {
	}

	public Class<?> getModelClass() {
		return FreeDaysHistoryEntry.class;
	}

	public String getModelClassName() {
		return FreeDaysHistoryEntry.class.getName();
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
		attributes.put("userId", getUserId());
		attributes.put("operation", getOperation());
		attributes.put("oldValue", getOldValue());
		attributes.put("daysNo", getDaysNo());
		attributes.put("newValue", getNewValue());
		attributes.put("createDate", getCreateDate());
		attributes.put("eventType", getEventType());
		attributes.put("description", getDescription());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long entryId = (Long)attributes.get("entryId");

		if (entryId != null) {
			setEntryId(entryId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		String operation = (String)attributes.get("operation");

		if (operation != null) {
			setOperation(operation);
		}

		Integer oldValue = (Integer)attributes.get("oldValue");

		if (oldValue != null) {
			setOldValue(oldValue);
		}

		Integer daysNo = (Integer)attributes.get("daysNo");

		if (daysNo != null) {
			setDaysNo(daysNo);
		}

		Integer newValue = (Integer)attributes.get("newValue");

		if (newValue != null) {
			setNewValue(newValue);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		String eventType = (String)attributes.get("eventType");

		if (eventType != null) {
			setEventType(eventType);
		}

		String description = (String)attributes.get("description");

		if (description != null) {
			setDescription(description);
		}
	}

	public long getEntryId() {
		return _entryId;
	}

	public void setEntryId(long entryId) {
		_entryId = entryId;
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

	public String getOperation() {
		return _operation;
	}

	public void setOperation(String operation) {
		_operation = operation;
	}

	public int getOldValue() {
		return _oldValue;
	}

	public void setOldValue(int oldValue) {
		_oldValue = oldValue;
	}

	public int getDaysNo() {
		return _daysNo;
	}

	public void setDaysNo(int daysNo) {
		_daysNo = daysNo;
	}

	public int getNewValue() {
		return _newValue;
	}

	public void setNewValue(int newValue) {
		_newValue = newValue;
	}

	public Date getCreateDate() {
		return _createDate;
	}

	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	public String getEventType() {
		return _eventType;
	}

	public void setEventType(String eventType) {
		_eventType = eventType;
	}

	public String getDescription() {
		return _description;
	}

	public void setDescription(String description) {
		_description = description;
	}

	public BaseModel<?> getFreeDaysHistoryEntryRemoteModel() {
		return _freeDaysHistoryEntryRemoteModel;
	}

	public void setFreeDaysHistoryEntryRemoteModel(
		BaseModel<?> freeDaysHistoryEntryRemoteModel) {
		_freeDaysHistoryEntryRemoteModel = freeDaysHistoryEntryRemoteModel;
	}

	public void persist() throws SystemException {
		if (this.isNew()) {
			FreeDaysHistoryEntryLocalServiceUtil.addFreeDaysHistoryEntry(this);
		}
		else {
			FreeDaysHistoryEntryLocalServiceUtil.updateFreeDaysHistoryEntry(this);
		}
	}

	@Override
	public FreeDaysHistoryEntry toEscapedModel() {
		return (FreeDaysHistoryEntry)Proxy.newProxyInstance(FreeDaysHistoryEntry.class.getClassLoader(),
			new Class[] { FreeDaysHistoryEntry.class },
			new AutoEscapeBeanHandler(this));
	}

	@Override
	public Object clone() {
		FreeDaysHistoryEntryClp clone = new FreeDaysHistoryEntryClp();

		clone.setEntryId(getEntryId());
		clone.setUserId(getUserId());
		clone.setOperation(getOperation());
		clone.setOldValue(getOldValue());
		clone.setDaysNo(getDaysNo());
		clone.setNewValue(getNewValue());
		clone.setCreateDate(getCreateDate());
		clone.setEventType(getEventType());
		clone.setDescription(getDescription());

		return clone;
	}

	public int compareTo(FreeDaysHistoryEntry freeDaysHistoryEntry) {
		int value = 0;

		value = DateUtil.compareTo(getCreateDate(),
				freeDaysHistoryEntry.getCreateDate());

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

		FreeDaysHistoryEntryClp freeDaysHistoryEntry = null;

		try {
			freeDaysHistoryEntry = (FreeDaysHistoryEntryClp)obj;
		}
		catch (ClassCastException cce) {
			return false;
		}

		long primaryKey = freeDaysHistoryEntry.getPrimaryKey();

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
		StringBundler sb = new StringBundler(19);

		sb.append("{entryId=");
		sb.append(getEntryId());
		sb.append(", userId=");
		sb.append(getUserId());
		sb.append(", operation=");
		sb.append(getOperation());
		sb.append(", oldValue=");
		sb.append(getOldValue());
		sb.append(", daysNo=");
		sb.append(getDaysNo());
		sb.append(", newValue=");
		sb.append(getNewValue());
		sb.append(", createDate=");
		sb.append(getCreateDate());
		sb.append(", eventType=");
		sb.append(getEventType());
		sb.append(", description=");
		sb.append(getDescription());
		sb.append("}");

		return sb.toString();
	}

	public String toXmlString() {
		StringBundler sb = new StringBundler(31);

		sb.append("<model><model-name>");
		sb.append(
			"com.evozon.evoportal.evozonfreedaysallocator.model.FreeDaysHistoryEntry");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>entryId</column-name><column-value><![CDATA[");
		sb.append(getEntryId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userId</column-name><column-value><![CDATA[");
		sb.append(getUserId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>operation</column-name><column-value><![CDATA[");
		sb.append(getOperation());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>oldValue</column-name><column-value><![CDATA[");
		sb.append(getOldValue());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>daysNo</column-name><column-value><![CDATA[");
		sb.append(getDaysNo());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>newValue</column-name><column-value><![CDATA[");
		sb.append(getNewValue());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>createDate</column-name><column-value><![CDATA[");
		sb.append(getCreateDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>eventType</column-name><column-value><![CDATA[");
		sb.append(getEventType());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>description</column-name><column-value><![CDATA[");
		sb.append(getDescription());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _entryId;
	private long _userId;
	private String _userUuid;
	private String _operation;
	private int _oldValue;
	private int _daysNo;
	private int _newValue;
	private Date _createDate;
	private String _eventType;
	private String _description;
	private BaseModel<?> _freeDaysHistoryEntryRemoteModel;
}