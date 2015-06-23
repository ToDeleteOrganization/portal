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
 * This class is a wrapper for {@link BenefitDayQueue}.
 * </p>
 *
 * @author    Cristina Kiss
 * @see       BenefitDayQueue
 * @generated
 */
public class BenefitDayQueueWrapper implements BenefitDayQueue,
	ModelWrapper<BenefitDayQueue> {
	public BenefitDayQueueWrapper(BenefitDayQueue benefitDayQueue) {
		_benefitDayQueue = benefitDayQueue;
	}

	public Class<?> getModelClass() {
		return BenefitDayQueue.class;
	}

	public String getModelClassName() {
		return BenefitDayQueue.class.getName();
	}

	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("entryId", getEntryId());
		attributes.put("type", getType());
		attributes.put("userId", getUserId());
		attributes.put("daysNo", getDaysNo());
		attributes.put("queued", getQueued());
		attributes.put("addedDate", getAddedDate());
		attributes.put("allocatedDate", getAllocatedDate());

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

		Boolean queued = (Boolean)attributes.get("queued");

		if (queued != null) {
			setQueued(queued);
		}

		Date addedDate = (Date)attributes.get("addedDate");

		if (addedDate != null) {
			setAddedDate(addedDate);
		}

		Date allocatedDate = (Date)attributes.get("allocatedDate");

		if (allocatedDate != null) {
			setAllocatedDate(allocatedDate);
		}
	}

	/**
	* Returns the primary key of this benefit day queue.
	*
	* @return the primary key of this benefit day queue
	*/
	public long getPrimaryKey() {
		return _benefitDayQueue.getPrimaryKey();
	}

	/**
	* Sets the primary key of this benefit day queue.
	*
	* @param primaryKey the primary key of this benefit day queue
	*/
	public void setPrimaryKey(long primaryKey) {
		_benefitDayQueue.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the entry ID of this benefit day queue.
	*
	* @return the entry ID of this benefit day queue
	*/
	public long getEntryId() {
		return _benefitDayQueue.getEntryId();
	}

	/**
	* Sets the entry ID of this benefit day queue.
	*
	* @param entryId the entry ID of this benefit day queue
	*/
	public void setEntryId(long entryId) {
		_benefitDayQueue.setEntryId(entryId);
	}

	/**
	* Returns the type of this benefit day queue.
	*
	* @return the type of this benefit day queue
	*/
	public java.lang.String getType() {
		return _benefitDayQueue.getType();
	}

	/**
	* Sets the type of this benefit day queue.
	*
	* @param type the type of this benefit day queue
	*/
	public void setType(java.lang.String type) {
		_benefitDayQueue.setType(type);
	}

	/**
	* Returns the user ID of this benefit day queue.
	*
	* @return the user ID of this benefit day queue
	*/
	public long getUserId() {
		return _benefitDayQueue.getUserId();
	}

	/**
	* Sets the user ID of this benefit day queue.
	*
	* @param userId the user ID of this benefit day queue
	*/
	public void setUserId(long userId) {
		_benefitDayQueue.setUserId(userId);
	}

	/**
	* Returns the user uuid of this benefit day queue.
	*
	* @return the user uuid of this benefit day queue
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _benefitDayQueue.getUserUuid();
	}

	/**
	* Sets the user uuid of this benefit day queue.
	*
	* @param userUuid the user uuid of this benefit day queue
	*/
	public void setUserUuid(java.lang.String userUuid) {
		_benefitDayQueue.setUserUuid(userUuid);
	}

	/**
	* Returns the days no of this benefit day queue.
	*
	* @return the days no of this benefit day queue
	*/
	public int getDaysNo() {
		return _benefitDayQueue.getDaysNo();
	}

	/**
	* Sets the days no of this benefit day queue.
	*
	* @param daysNo the days no of this benefit day queue
	*/
	public void setDaysNo(int daysNo) {
		_benefitDayQueue.setDaysNo(daysNo);
	}

	/**
	* Returns the queued of this benefit day queue.
	*
	* @return the queued of this benefit day queue
	*/
	public boolean getQueued() {
		return _benefitDayQueue.getQueued();
	}

	/**
	* Returns <code>true</code> if this benefit day queue is queued.
	*
	* @return <code>true</code> if this benefit day queue is queued; <code>false</code> otherwise
	*/
	public boolean isQueued() {
		return _benefitDayQueue.isQueued();
	}

	/**
	* Sets whether this benefit day queue is queued.
	*
	* @param queued the queued of this benefit day queue
	*/
	public void setQueued(boolean queued) {
		_benefitDayQueue.setQueued(queued);
	}

	/**
	* Returns the added date of this benefit day queue.
	*
	* @return the added date of this benefit day queue
	*/
	public java.util.Date getAddedDate() {
		return _benefitDayQueue.getAddedDate();
	}

	/**
	* Sets the added date of this benefit day queue.
	*
	* @param addedDate the added date of this benefit day queue
	*/
	public void setAddedDate(java.util.Date addedDate) {
		_benefitDayQueue.setAddedDate(addedDate);
	}

	/**
	* Returns the allocated date of this benefit day queue.
	*
	* @return the allocated date of this benefit day queue
	*/
	public java.util.Date getAllocatedDate() {
		return _benefitDayQueue.getAllocatedDate();
	}

	/**
	* Sets the allocated date of this benefit day queue.
	*
	* @param allocatedDate the allocated date of this benefit day queue
	*/
	public void setAllocatedDate(java.util.Date allocatedDate) {
		_benefitDayQueue.setAllocatedDate(allocatedDate);
	}

	public boolean isNew() {
		return _benefitDayQueue.isNew();
	}

	public void setNew(boolean n) {
		_benefitDayQueue.setNew(n);
	}

	public boolean isCachedModel() {
		return _benefitDayQueue.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_benefitDayQueue.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _benefitDayQueue.isEscapedModel();
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _benefitDayQueue.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_benefitDayQueue.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _benefitDayQueue.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_benefitDayQueue.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new BenefitDayQueueWrapper((BenefitDayQueue)_benefitDayQueue.clone());
	}

	public int compareTo(
		com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDayQueue benefitDayQueue) {
		return _benefitDayQueue.compareTo(benefitDayQueue);
	}

	@Override
	public int hashCode() {
		return _benefitDayQueue.hashCode();
	}

	public com.liferay.portal.model.CacheModel<com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDayQueue> toCacheModel() {
		return _benefitDayQueue.toCacheModel();
	}

	public com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDayQueue toEscapedModel() {
		return new BenefitDayQueueWrapper(_benefitDayQueue.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _benefitDayQueue.toString();
	}

	public java.lang.String toXmlString() {
		return _benefitDayQueue.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_benefitDayQueue.persist();
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedModel}
	 */
	public BenefitDayQueue getWrappedBenefitDayQueue() {
		return _benefitDayQueue;
	}

	public BenefitDayQueue getWrappedModel() {
		return _benefitDayQueue;
	}

	public void resetOriginalValues() {
		_benefitDayQueue.resetOriginalValues();
	}

	private BenefitDayQueue _benefitDayQueue;
}