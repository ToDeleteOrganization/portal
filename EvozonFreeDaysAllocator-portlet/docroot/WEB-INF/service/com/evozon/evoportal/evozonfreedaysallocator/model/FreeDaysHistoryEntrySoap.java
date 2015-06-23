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

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author    Cristina Kiss
 * @generated
 */
public class FreeDaysHistoryEntrySoap implements Serializable {
	public static FreeDaysHistoryEntrySoap toSoapModel(
		FreeDaysHistoryEntry model) {
		FreeDaysHistoryEntrySoap soapModel = new FreeDaysHistoryEntrySoap();

		soapModel.setEntryId(model.getEntryId());
		soapModel.setUserId(model.getUserId());
		soapModel.setOperation(model.getOperation());
		soapModel.setOldValue(model.getOldValue());
		soapModel.setDaysNo(model.getDaysNo());
		soapModel.setNewValue(model.getNewValue());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setEventType(model.getEventType());
		soapModel.setDescription(model.getDescription());

		return soapModel;
	}

	public static FreeDaysHistoryEntrySoap[] toSoapModels(
		FreeDaysHistoryEntry[] models) {
		FreeDaysHistoryEntrySoap[] soapModels = new FreeDaysHistoryEntrySoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static FreeDaysHistoryEntrySoap[][] toSoapModels(
		FreeDaysHistoryEntry[][] models) {
		FreeDaysHistoryEntrySoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new FreeDaysHistoryEntrySoap[models.length][models[0].length];
		}
		else {
			soapModels = new FreeDaysHistoryEntrySoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static FreeDaysHistoryEntrySoap[] toSoapModels(
		List<FreeDaysHistoryEntry> models) {
		List<FreeDaysHistoryEntrySoap> soapModels = new ArrayList<FreeDaysHistoryEntrySoap>(models.size());

		for (FreeDaysHistoryEntry model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new FreeDaysHistoryEntrySoap[soapModels.size()]);
	}

	public FreeDaysHistoryEntrySoap() {
	}

	public long getPrimaryKey() {
		return _entryId;
	}

	public void setPrimaryKey(long pk) {
		setEntryId(pk);
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

	private long _entryId;
	private long _userId;
	private String _operation;
	private int _oldValue;
	private int _daysNo;
	private int _newValue;
	private Date _createDate;
	private String _eventType;
	private String _description;
}