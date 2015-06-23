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
public class BenefitDayQueueSoap implements Serializable {
	public static BenefitDayQueueSoap toSoapModel(BenefitDayQueue model) {
		BenefitDayQueueSoap soapModel = new BenefitDayQueueSoap();

		soapModel.setEntryId(model.getEntryId());
		soapModel.setType(model.getType());
		soapModel.setUserId(model.getUserId());
		soapModel.setDaysNo(model.getDaysNo());
		soapModel.setQueued(model.getQueued());
		soapModel.setAddedDate(model.getAddedDate());
		soapModel.setAllocatedDate(model.getAllocatedDate());

		return soapModel;
	}

	public static BenefitDayQueueSoap[] toSoapModels(BenefitDayQueue[] models) {
		BenefitDayQueueSoap[] soapModels = new BenefitDayQueueSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static BenefitDayQueueSoap[][] toSoapModels(
		BenefitDayQueue[][] models) {
		BenefitDayQueueSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new BenefitDayQueueSoap[models.length][models[0].length];
		}
		else {
			soapModels = new BenefitDayQueueSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static BenefitDayQueueSoap[] toSoapModels(
		List<BenefitDayQueue> models) {
		List<BenefitDayQueueSoap> soapModels = new ArrayList<BenefitDayQueueSoap>(models.size());

		for (BenefitDayQueue model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new BenefitDayQueueSoap[soapModels.size()]);
	}

	public BenefitDayQueueSoap() {
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

	public int getDaysNo() {
		return _daysNo;
	}

	public void setDaysNo(int daysNo) {
		_daysNo = daysNo;
	}

	public boolean getQueued() {
		return _queued;
	}

	public boolean isQueued() {
		return _queued;
	}

	public void setQueued(boolean queued) {
		_queued = queued;
	}

	public Date getAddedDate() {
		return _addedDate;
	}

	public void setAddedDate(Date addedDate) {
		_addedDate = addedDate;
	}

	public Date getAllocatedDate() {
		return _allocatedDate;
	}

	public void setAllocatedDate(Date allocatedDate) {
		_allocatedDate = allocatedDate;
	}

	private long _entryId;
	private String _type;
	private long _userId;
	private int _daysNo;
	private boolean _queued;
	private Date _addedDate;
	private Date _allocatedDate;
}