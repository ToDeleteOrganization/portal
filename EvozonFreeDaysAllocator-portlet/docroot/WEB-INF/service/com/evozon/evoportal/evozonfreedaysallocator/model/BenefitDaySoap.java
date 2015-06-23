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
public class BenefitDaySoap implements Serializable {
	public static BenefitDaySoap toSoapModel(BenefitDay model) {
		BenefitDaySoap soapModel = new BenefitDaySoap();

		soapModel.setEntryId(model.getEntryId());
		soapModel.setType(model.getType());
		soapModel.setUserId(model.getUserId());
		soapModel.setDaysNo(model.getDaysNo());
		soapModel.setAllocatedDate(model.getAllocatedDate());
		soapModel.setComment(model.getComment());

		return soapModel;
	}

	public static BenefitDaySoap[] toSoapModels(BenefitDay[] models) {
		BenefitDaySoap[] soapModels = new BenefitDaySoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static BenefitDaySoap[][] toSoapModels(BenefitDay[][] models) {
		BenefitDaySoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new BenefitDaySoap[models.length][models[0].length];
		}
		else {
			soapModels = new BenefitDaySoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static BenefitDaySoap[] toSoapModels(List<BenefitDay> models) {
		List<BenefitDaySoap> soapModels = new ArrayList<BenefitDaySoap>(models.size());

		for (BenefitDay model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new BenefitDaySoap[soapModels.size()]);
	}

	public BenefitDaySoap() {
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

	private long _entryId;
	private String _type;
	private long _userId;
	private int _daysNo;
	private Date _allocatedDate;
	private String _comment;
}