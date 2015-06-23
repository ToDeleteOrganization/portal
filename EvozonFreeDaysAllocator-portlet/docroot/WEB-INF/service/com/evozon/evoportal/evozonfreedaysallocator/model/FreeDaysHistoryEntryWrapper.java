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
 * This class is a wrapper for {@link FreeDaysHistoryEntry}.
 * </p>
 *
 * @author    Cristina Kiss
 * @see       FreeDaysHistoryEntry
 * @generated
 */
public class FreeDaysHistoryEntryWrapper implements FreeDaysHistoryEntry,
	ModelWrapper<FreeDaysHistoryEntry> {
	public FreeDaysHistoryEntryWrapper(
		FreeDaysHistoryEntry freeDaysHistoryEntry) {
		_freeDaysHistoryEntry = freeDaysHistoryEntry;
	}

	public Class<?> getModelClass() {
		return FreeDaysHistoryEntry.class;
	}

	public String getModelClassName() {
		return FreeDaysHistoryEntry.class.getName();
	}

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

	/**
	* Returns the primary key of this free days history entry.
	*
	* @return the primary key of this free days history entry
	*/
	public long getPrimaryKey() {
		return _freeDaysHistoryEntry.getPrimaryKey();
	}

	/**
	* Sets the primary key of this free days history entry.
	*
	* @param primaryKey the primary key of this free days history entry
	*/
	public void setPrimaryKey(long primaryKey) {
		_freeDaysHistoryEntry.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the entry ID of this free days history entry.
	*
	* @return the entry ID of this free days history entry
	*/
	public long getEntryId() {
		return _freeDaysHistoryEntry.getEntryId();
	}

	/**
	* Sets the entry ID of this free days history entry.
	*
	* @param entryId the entry ID of this free days history entry
	*/
	public void setEntryId(long entryId) {
		_freeDaysHistoryEntry.setEntryId(entryId);
	}

	/**
	* Returns the user ID of this free days history entry.
	*
	* @return the user ID of this free days history entry
	*/
	public long getUserId() {
		return _freeDaysHistoryEntry.getUserId();
	}

	/**
	* Sets the user ID of this free days history entry.
	*
	* @param userId the user ID of this free days history entry
	*/
	public void setUserId(long userId) {
		_freeDaysHistoryEntry.setUserId(userId);
	}

	/**
	* Returns the user uuid of this free days history entry.
	*
	* @return the user uuid of this free days history entry
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _freeDaysHistoryEntry.getUserUuid();
	}

	/**
	* Sets the user uuid of this free days history entry.
	*
	* @param userUuid the user uuid of this free days history entry
	*/
	public void setUserUuid(java.lang.String userUuid) {
		_freeDaysHistoryEntry.setUserUuid(userUuid);
	}

	/**
	* Returns the operation of this free days history entry.
	*
	* @return the operation of this free days history entry
	*/
	public java.lang.String getOperation() {
		return _freeDaysHistoryEntry.getOperation();
	}

	/**
	* Sets the operation of this free days history entry.
	*
	* @param operation the operation of this free days history entry
	*/
	public void setOperation(java.lang.String operation) {
		_freeDaysHistoryEntry.setOperation(operation);
	}

	/**
	* Returns the old value of this free days history entry.
	*
	* @return the old value of this free days history entry
	*/
	public int getOldValue() {
		return _freeDaysHistoryEntry.getOldValue();
	}

	/**
	* Sets the old value of this free days history entry.
	*
	* @param oldValue the old value of this free days history entry
	*/
	public void setOldValue(int oldValue) {
		_freeDaysHistoryEntry.setOldValue(oldValue);
	}

	/**
	* Returns the days no of this free days history entry.
	*
	* @return the days no of this free days history entry
	*/
	public int getDaysNo() {
		return _freeDaysHistoryEntry.getDaysNo();
	}

	/**
	* Sets the days no of this free days history entry.
	*
	* @param daysNo the days no of this free days history entry
	*/
	public void setDaysNo(int daysNo) {
		_freeDaysHistoryEntry.setDaysNo(daysNo);
	}

	/**
	* Returns the new value of this free days history entry.
	*
	* @return the new value of this free days history entry
	*/
	public int getNewValue() {
		return _freeDaysHistoryEntry.getNewValue();
	}

	/**
	* Sets the new value of this free days history entry.
	*
	* @param newValue the new value of this free days history entry
	*/
	public void setNewValue(int newValue) {
		_freeDaysHistoryEntry.setNewValue(newValue);
	}

	/**
	* Returns the create date of this free days history entry.
	*
	* @return the create date of this free days history entry
	*/
	public java.util.Date getCreateDate() {
		return _freeDaysHistoryEntry.getCreateDate();
	}

	/**
	* Sets the create date of this free days history entry.
	*
	* @param createDate the create date of this free days history entry
	*/
	public void setCreateDate(java.util.Date createDate) {
		_freeDaysHistoryEntry.setCreateDate(createDate);
	}

	/**
	* Returns the event type of this free days history entry.
	*
	* @return the event type of this free days history entry
	*/
	public java.lang.String getEventType() {
		return _freeDaysHistoryEntry.getEventType();
	}

	/**
	* Sets the event type of this free days history entry.
	*
	* @param eventType the event type of this free days history entry
	*/
	public void setEventType(java.lang.String eventType) {
		_freeDaysHistoryEntry.setEventType(eventType);
	}

	/**
	* Returns the description of this free days history entry.
	*
	* @return the description of this free days history entry
	*/
	public java.lang.String getDescription() {
		return _freeDaysHistoryEntry.getDescription();
	}

	/**
	* Sets the description of this free days history entry.
	*
	* @param description the description of this free days history entry
	*/
	public void setDescription(java.lang.String description) {
		_freeDaysHistoryEntry.setDescription(description);
	}

	public boolean isNew() {
		return _freeDaysHistoryEntry.isNew();
	}

	public void setNew(boolean n) {
		_freeDaysHistoryEntry.setNew(n);
	}

	public boolean isCachedModel() {
		return _freeDaysHistoryEntry.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_freeDaysHistoryEntry.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _freeDaysHistoryEntry.isEscapedModel();
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _freeDaysHistoryEntry.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_freeDaysHistoryEntry.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _freeDaysHistoryEntry.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_freeDaysHistoryEntry.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new FreeDaysHistoryEntryWrapper((FreeDaysHistoryEntry)_freeDaysHistoryEntry.clone());
	}

	public int compareTo(
		com.evozon.evoportal.evozonfreedaysallocator.model.FreeDaysHistoryEntry freeDaysHistoryEntry) {
		return _freeDaysHistoryEntry.compareTo(freeDaysHistoryEntry);
	}

	@Override
	public int hashCode() {
		return _freeDaysHistoryEntry.hashCode();
	}

	public com.liferay.portal.model.CacheModel<com.evozon.evoportal.evozonfreedaysallocator.model.FreeDaysHistoryEntry> toCacheModel() {
		return _freeDaysHistoryEntry.toCacheModel();
	}

	public com.evozon.evoportal.evozonfreedaysallocator.model.FreeDaysHistoryEntry toEscapedModel() {
		return new FreeDaysHistoryEntryWrapper(_freeDaysHistoryEntry.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _freeDaysHistoryEntry.toString();
	}

	public java.lang.String toXmlString() {
		return _freeDaysHistoryEntry.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_freeDaysHistoryEntry.persist();
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedModel}
	 */
	public FreeDaysHistoryEntry getWrappedFreeDaysHistoryEntry() {
		return _freeDaysHistoryEntry;
	}

	public FreeDaysHistoryEntry getWrappedModel() {
		return _freeDaysHistoryEntry;
	}

	public void resetOriginalValues() {
		_freeDaysHistoryEntry.resetOriginalValues();
	}

	private FreeDaysHistoryEntry _freeDaysHistoryEntry;
}