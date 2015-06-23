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

import com.liferay.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link BenefitDay}.
 * </p>
 *
 * @author    Cristina Kiss
 * @see       BenefitDay
 * @generated
 */
public class BenefitDayWrapper implements BenefitDay, ModelWrapper<BenefitDay> {
	public BenefitDayWrapper(BenefitDay benefitDay) {
		_benefitDay = benefitDay;
	}

	public Class<?> getModelClass() {
		return BenefitDay.class;
	}

	public String getModelClassName() {
		return BenefitDay.class.getName();
	}

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

	/**
	* Returns the primary key of this benefit day.
	*
	* @return the primary key of this benefit day
	*/
	public long getPrimaryKey() {
		return _benefitDay.getPrimaryKey();
	}

	/**
	* Sets the primary key of this benefit day.
	*
	* @param primaryKey the primary key of this benefit day
	*/
	public void setPrimaryKey(long primaryKey) {
		_benefitDay.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the entry ID of this benefit day.
	*
	* @return the entry ID of this benefit day
	*/
	public long getEntryId() {
		return _benefitDay.getEntryId();
	}

	/**
	* Sets the entry ID of this benefit day.
	*
	* @param entryId the entry ID of this benefit day
	*/
	public void setEntryId(long entryId) {
		_benefitDay.setEntryId(entryId);
	}

	/**
	* Returns the type of this benefit day.
	*
	* @return the type of this benefit day
	*/
	public java.lang.String getType() {
		return _benefitDay.getType();
	}

	/**
	* Sets the type of this benefit day.
	*
	* @param type the type of this benefit day
	*/
	public void setType(java.lang.String type) {
		_benefitDay.setType(type);
	}

	/**
	* Returns the user ID of this benefit day.
	*
	* @return the user ID of this benefit day
	*/
	public long getUserId() {
		return _benefitDay.getUserId();
	}

	/**
	* Sets the user ID of this benefit day.
	*
	* @param userId the user ID of this benefit day
	*/
	public void setUserId(long userId) {
		_benefitDay.setUserId(userId);
	}

	/**
	* Returns the user uuid of this benefit day.
	*
	* @return the user uuid of this benefit day
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _benefitDay.getUserUuid();
	}

	/**
	* Sets the user uuid of this benefit day.
	*
	* @param userUuid the user uuid of this benefit day
	*/
	public void setUserUuid(java.lang.String userUuid) {
		_benefitDay.setUserUuid(userUuid);
	}

	/**
	* Returns the days no of this benefit day.
	*
	* @return the days no of this benefit day
	*/
	public int getDaysNo() {
		return _benefitDay.getDaysNo();
	}

	/**
	* Sets the days no of this benefit day.
	*
	* @param daysNo the days no of this benefit day
	*/
	public void setDaysNo(int daysNo) {
		_benefitDay.setDaysNo(daysNo);
	}

	/**
	* Returns the allocated date of this benefit day.
	*
	* @return the allocated date of this benefit day
	*/
	public java.util.Date getAllocatedDate() {
		return _benefitDay.getAllocatedDate();
	}

	/**
	* Sets the allocated date of this benefit day.
	*
	* @param allocatedDate the allocated date of this benefit day
	*/
	public void setAllocatedDate(java.util.Date allocatedDate) {
		_benefitDay.setAllocatedDate(allocatedDate);
	}

	/**
	* Returns the comment of this benefit day.
	*
	* @return the comment of this benefit day
	*/
	public java.lang.String getComment() {
		return _benefitDay.getComment();
	}

	/**
	* Sets the comment of this benefit day.
	*
	* @param comment the comment of this benefit day
	*/
	public void setComment(java.lang.String comment) {
		_benefitDay.setComment(comment);
	}

	public boolean isNew() {
		return _benefitDay.isNew();
	}

	public void setNew(boolean n) {
		_benefitDay.setNew(n);
	}

	public boolean isCachedModel() {
		return _benefitDay.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_benefitDay.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _benefitDay.isEscapedModel();
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _benefitDay.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_benefitDay.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _benefitDay.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_benefitDay.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new BenefitDayWrapper((BenefitDay)_benefitDay.clone());
	}

	public int compareTo(
		com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDay benefitDay) {
		return _benefitDay.compareTo(benefitDay);
	}

	@Override
	public int hashCode() {
		return _benefitDay.hashCode();
	}

	public com.liferay.portal.model.CacheModel<com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDay> toCacheModel() {
		return _benefitDay.toCacheModel();
	}

	public com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDay toEscapedModel() {
		return new BenefitDayWrapper(_benefitDay.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _benefitDay.toString();
	}

	public java.lang.String toXmlString() {
		return _benefitDay.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_benefitDay.persist();
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedModel}
	 */
	public BenefitDay getWrappedBenefitDay() {
		return _benefitDay;
	}

	public BenefitDay getWrappedModel() {
		return _benefitDay;
	}

	public void resetOriginalValues() {
		_benefitDay.resetOriginalValues();
	}

	private BenefitDay _benefitDay;
}