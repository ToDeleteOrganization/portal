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

package com.evozon.evoportal.evozonfreedaysallocator.service.persistence;

import com.evozon.evoportal.evozonfreedaysallocator.model.FreeDaysHistoryEntry;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the free days history entry service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Cristina Kiss
 * @see FreeDaysHistoryEntryPersistenceImpl
 * @see FreeDaysHistoryEntryUtil
 * @generated
 */
public interface FreeDaysHistoryEntryPersistence extends BasePersistence<FreeDaysHistoryEntry> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link FreeDaysHistoryEntryUtil} to access the free days history entry persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the free days history entry in the entity cache if it is enabled.
	*
	* @param freeDaysHistoryEntry the free days history entry
	*/
	public void cacheResult(
		com.evozon.evoportal.evozonfreedaysallocator.model.FreeDaysHistoryEntry freeDaysHistoryEntry);

	/**
	* Caches the free days history entries in the entity cache if it is enabled.
	*
	* @param freeDaysHistoryEntries the free days history entries
	*/
	public void cacheResult(
		java.util.List<com.evozon.evoportal.evozonfreedaysallocator.model.FreeDaysHistoryEntry> freeDaysHistoryEntries);

	/**
	* Creates a new free days history entry with the primary key. Does not add the free days history entry to the database.
	*
	* @param entryId the primary key for the new free days history entry
	* @return the new free days history entry
	*/
	public com.evozon.evoportal.evozonfreedaysallocator.model.FreeDaysHistoryEntry create(
		long entryId);

	/**
	* Removes the free days history entry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param entryId the primary key of the free days history entry
	* @return the free days history entry that was removed
	* @throws com.evozon.evoportal.evozonfreedaysallocator.NoSuchFreeDaysHistoryEntryException if a free days history entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.evozon.evoportal.evozonfreedaysallocator.model.FreeDaysHistoryEntry remove(
		long entryId)
		throws com.evozon.evoportal.evozonfreedaysallocator.NoSuchFreeDaysHistoryEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.evozon.evoportal.evozonfreedaysallocator.model.FreeDaysHistoryEntry updateImpl(
		com.evozon.evoportal.evozonfreedaysallocator.model.FreeDaysHistoryEntry freeDaysHistoryEntry,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the free days history entry with the primary key or throws a {@link com.evozon.evoportal.evozonfreedaysallocator.NoSuchFreeDaysHistoryEntryException} if it could not be found.
	*
	* @param entryId the primary key of the free days history entry
	* @return the free days history entry
	* @throws com.evozon.evoportal.evozonfreedaysallocator.NoSuchFreeDaysHistoryEntryException if a free days history entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.evozon.evoportal.evozonfreedaysallocator.model.FreeDaysHistoryEntry findByPrimaryKey(
		long entryId)
		throws com.evozon.evoportal.evozonfreedaysallocator.NoSuchFreeDaysHistoryEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the free days history entry with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param entryId the primary key of the free days history entry
	* @return the free days history entry, or <code>null</code> if a free days history entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.evozon.evoportal.evozonfreedaysallocator.model.FreeDaysHistoryEntry fetchByPrimaryKey(
		long entryId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the free days history entries where userId = &#63;.
	*
	* @param userId the user ID
	* @return the matching free days history entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.evozon.evoportal.evozonfreedaysallocator.model.FreeDaysHistoryEntry> findByUserId(
		long userId) throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the free days history entries where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userId the user ID
	* @param start the lower bound of the range of free days history entries
	* @param end the upper bound of the range of free days history entries (not inclusive)
	* @return the range of matching free days history entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.evozon.evoportal.evozonfreedaysallocator.model.FreeDaysHistoryEntry> findByUserId(
		long userId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the free days history entries where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userId the user ID
	* @param start the lower bound of the range of free days history entries
	* @param end the upper bound of the range of free days history entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching free days history entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.evozon.evoportal.evozonfreedaysallocator.model.FreeDaysHistoryEntry> findByUserId(
		long userId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first free days history entry in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching free days history entry
	* @throws com.evozon.evoportal.evozonfreedaysallocator.NoSuchFreeDaysHistoryEntryException if a matching free days history entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.evozon.evoportal.evozonfreedaysallocator.model.FreeDaysHistoryEntry findByUserId_First(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.evozon.evoportal.evozonfreedaysallocator.NoSuchFreeDaysHistoryEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first free days history entry in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching free days history entry, or <code>null</code> if a matching free days history entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.evozon.evoportal.evozonfreedaysallocator.model.FreeDaysHistoryEntry fetchByUserId_First(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last free days history entry in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching free days history entry
	* @throws com.evozon.evoportal.evozonfreedaysallocator.NoSuchFreeDaysHistoryEntryException if a matching free days history entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.evozon.evoportal.evozonfreedaysallocator.model.FreeDaysHistoryEntry findByUserId_Last(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.evozon.evoportal.evozonfreedaysallocator.NoSuchFreeDaysHistoryEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last free days history entry in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching free days history entry, or <code>null</code> if a matching free days history entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.evozon.evoportal.evozonfreedaysallocator.model.FreeDaysHistoryEntry fetchByUserId_Last(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the free days history entries before and after the current free days history entry in the ordered set where userId = &#63;.
	*
	* @param entryId the primary key of the current free days history entry
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next free days history entry
	* @throws com.evozon.evoportal.evozonfreedaysallocator.NoSuchFreeDaysHistoryEntryException if a free days history entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.evozon.evoportal.evozonfreedaysallocator.model.FreeDaysHistoryEntry[] findByUserId_PrevAndNext(
		long entryId, long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.evozon.evoportal.evozonfreedaysallocator.NoSuchFreeDaysHistoryEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the free days history entries where userId = &#63; and operation = &#63;.
	*
	* @param userId the user ID
	* @param operation the operation
	* @return the matching free days history entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.evozon.evoportal.evozonfreedaysallocator.model.FreeDaysHistoryEntry> findByUserId_Operation(
		long userId, java.lang.String operation)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the free days history entries where userId = &#63; and operation = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userId the user ID
	* @param operation the operation
	* @param start the lower bound of the range of free days history entries
	* @param end the upper bound of the range of free days history entries (not inclusive)
	* @return the range of matching free days history entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.evozon.evoportal.evozonfreedaysallocator.model.FreeDaysHistoryEntry> findByUserId_Operation(
		long userId, java.lang.String operation, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the free days history entries where userId = &#63; and operation = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userId the user ID
	* @param operation the operation
	* @param start the lower bound of the range of free days history entries
	* @param end the upper bound of the range of free days history entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching free days history entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.evozon.evoportal.evozonfreedaysallocator.model.FreeDaysHistoryEntry> findByUserId_Operation(
		long userId, java.lang.String operation, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first free days history entry in the ordered set where userId = &#63; and operation = &#63;.
	*
	* @param userId the user ID
	* @param operation the operation
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching free days history entry
	* @throws com.evozon.evoportal.evozonfreedaysallocator.NoSuchFreeDaysHistoryEntryException if a matching free days history entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.evozon.evoportal.evozonfreedaysallocator.model.FreeDaysHistoryEntry findByUserId_Operation_First(
		long userId, java.lang.String operation,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.evozon.evoportal.evozonfreedaysallocator.NoSuchFreeDaysHistoryEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first free days history entry in the ordered set where userId = &#63; and operation = &#63;.
	*
	* @param userId the user ID
	* @param operation the operation
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching free days history entry, or <code>null</code> if a matching free days history entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.evozon.evoportal.evozonfreedaysallocator.model.FreeDaysHistoryEntry fetchByUserId_Operation_First(
		long userId, java.lang.String operation,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last free days history entry in the ordered set where userId = &#63; and operation = &#63;.
	*
	* @param userId the user ID
	* @param operation the operation
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching free days history entry
	* @throws com.evozon.evoportal.evozonfreedaysallocator.NoSuchFreeDaysHistoryEntryException if a matching free days history entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.evozon.evoportal.evozonfreedaysallocator.model.FreeDaysHistoryEntry findByUserId_Operation_Last(
		long userId, java.lang.String operation,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.evozon.evoportal.evozonfreedaysallocator.NoSuchFreeDaysHistoryEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last free days history entry in the ordered set where userId = &#63; and operation = &#63;.
	*
	* @param userId the user ID
	* @param operation the operation
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching free days history entry, or <code>null</code> if a matching free days history entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.evozon.evoportal.evozonfreedaysallocator.model.FreeDaysHistoryEntry fetchByUserId_Operation_Last(
		long userId, java.lang.String operation,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the free days history entries before and after the current free days history entry in the ordered set where userId = &#63; and operation = &#63;.
	*
	* @param entryId the primary key of the current free days history entry
	* @param userId the user ID
	* @param operation the operation
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next free days history entry
	* @throws com.evozon.evoportal.evozonfreedaysallocator.NoSuchFreeDaysHistoryEntryException if a free days history entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.evozon.evoportal.evozonfreedaysallocator.model.FreeDaysHistoryEntry[] findByUserId_Operation_PrevAndNext(
		long entryId, long userId, java.lang.String operation,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.evozon.evoportal.evozonfreedaysallocator.NoSuchFreeDaysHistoryEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the free days history entries where userId = &#63; and eventType = &#63;.
	*
	* @param userId the user ID
	* @param eventType the event type
	* @return the matching free days history entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.evozon.evoportal.evozonfreedaysallocator.model.FreeDaysHistoryEntry> findByUserId_EventType(
		long userId, java.lang.String eventType)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the free days history entries where userId = &#63; and eventType = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userId the user ID
	* @param eventType the event type
	* @param start the lower bound of the range of free days history entries
	* @param end the upper bound of the range of free days history entries (not inclusive)
	* @return the range of matching free days history entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.evozon.evoportal.evozonfreedaysallocator.model.FreeDaysHistoryEntry> findByUserId_EventType(
		long userId, java.lang.String eventType, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the free days history entries where userId = &#63; and eventType = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userId the user ID
	* @param eventType the event type
	* @param start the lower bound of the range of free days history entries
	* @param end the upper bound of the range of free days history entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching free days history entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.evozon.evoportal.evozonfreedaysallocator.model.FreeDaysHistoryEntry> findByUserId_EventType(
		long userId, java.lang.String eventType, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first free days history entry in the ordered set where userId = &#63; and eventType = &#63;.
	*
	* @param userId the user ID
	* @param eventType the event type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching free days history entry
	* @throws com.evozon.evoportal.evozonfreedaysallocator.NoSuchFreeDaysHistoryEntryException if a matching free days history entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.evozon.evoportal.evozonfreedaysallocator.model.FreeDaysHistoryEntry findByUserId_EventType_First(
		long userId, java.lang.String eventType,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.evozon.evoportal.evozonfreedaysallocator.NoSuchFreeDaysHistoryEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first free days history entry in the ordered set where userId = &#63; and eventType = &#63;.
	*
	* @param userId the user ID
	* @param eventType the event type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching free days history entry, or <code>null</code> if a matching free days history entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.evozon.evoportal.evozonfreedaysallocator.model.FreeDaysHistoryEntry fetchByUserId_EventType_First(
		long userId, java.lang.String eventType,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last free days history entry in the ordered set where userId = &#63; and eventType = &#63;.
	*
	* @param userId the user ID
	* @param eventType the event type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching free days history entry
	* @throws com.evozon.evoportal.evozonfreedaysallocator.NoSuchFreeDaysHistoryEntryException if a matching free days history entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.evozon.evoportal.evozonfreedaysallocator.model.FreeDaysHistoryEntry findByUserId_EventType_Last(
		long userId, java.lang.String eventType,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.evozon.evoportal.evozonfreedaysallocator.NoSuchFreeDaysHistoryEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last free days history entry in the ordered set where userId = &#63; and eventType = &#63;.
	*
	* @param userId the user ID
	* @param eventType the event type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching free days history entry, or <code>null</code> if a matching free days history entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.evozon.evoportal.evozonfreedaysallocator.model.FreeDaysHistoryEntry fetchByUserId_EventType_Last(
		long userId, java.lang.String eventType,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the free days history entries before and after the current free days history entry in the ordered set where userId = &#63; and eventType = &#63;.
	*
	* @param entryId the primary key of the current free days history entry
	* @param userId the user ID
	* @param eventType the event type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next free days history entry
	* @throws com.evozon.evoportal.evozonfreedaysallocator.NoSuchFreeDaysHistoryEntryException if a free days history entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.evozon.evoportal.evozonfreedaysallocator.model.FreeDaysHistoryEntry[] findByUserId_EventType_PrevAndNext(
		long entryId, long userId, java.lang.String eventType,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.evozon.evoportal.evozonfreedaysallocator.NoSuchFreeDaysHistoryEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the free days history entries where userId = &#63; and createDate = &#63;.
	*
	* @param userId the user ID
	* @param createDate the create date
	* @return the matching free days history entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.evozon.evoportal.evozonfreedaysallocator.model.FreeDaysHistoryEntry> findByUserId_CreateDate(
		long userId, java.util.Date createDate)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the free days history entries where userId = &#63; and createDate = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userId the user ID
	* @param createDate the create date
	* @param start the lower bound of the range of free days history entries
	* @param end the upper bound of the range of free days history entries (not inclusive)
	* @return the range of matching free days history entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.evozon.evoportal.evozonfreedaysallocator.model.FreeDaysHistoryEntry> findByUserId_CreateDate(
		long userId, java.util.Date createDate, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the free days history entries where userId = &#63; and createDate = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userId the user ID
	* @param createDate the create date
	* @param start the lower bound of the range of free days history entries
	* @param end the upper bound of the range of free days history entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching free days history entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.evozon.evoportal.evozonfreedaysallocator.model.FreeDaysHistoryEntry> findByUserId_CreateDate(
		long userId, java.util.Date createDate, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first free days history entry in the ordered set where userId = &#63; and createDate = &#63;.
	*
	* @param userId the user ID
	* @param createDate the create date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching free days history entry
	* @throws com.evozon.evoportal.evozonfreedaysallocator.NoSuchFreeDaysHistoryEntryException if a matching free days history entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.evozon.evoportal.evozonfreedaysallocator.model.FreeDaysHistoryEntry findByUserId_CreateDate_First(
		long userId, java.util.Date createDate,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.evozon.evoportal.evozonfreedaysallocator.NoSuchFreeDaysHistoryEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first free days history entry in the ordered set where userId = &#63; and createDate = &#63;.
	*
	* @param userId the user ID
	* @param createDate the create date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching free days history entry, or <code>null</code> if a matching free days history entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.evozon.evoportal.evozonfreedaysallocator.model.FreeDaysHistoryEntry fetchByUserId_CreateDate_First(
		long userId, java.util.Date createDate,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last free days history entry in the ordered set where userId = &#63; and createDate = &#63;.
	*
	* @param userId the user ID
	* @param createDate the create date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching free days history entry
	* @throws com.evozon.evoportal.evozonfreedaysallocator.NoSuchFreeDaysHistoryEntryException if a matching free days history entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.evozon.evoportal.evozonfreedaysallocator.model.FreeDaysHistoryEntry findByUserId_CreateDate_Last(
		long userId, java.util.Date createDate,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.evozon.evoportal.evozonfreedaysallocator.NoSuchFreeDaysHistoryEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last free days history entry in the ordered set where userId = &#63; and createDate = &#63;.
	*
	* @param userId the user ID
	* @param createDate the create date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching free days history entry, or <code>null</code> if a matching free days history entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.evozon.evoportal.evozonfreedaysallocator.model.FreeDaysHistoryEntry fetchByUserId_CreateDate_Last(
		long userId, java.util.Date createDate,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the free days history entries before and after the current free days history entry in the ordered set where userId = &#63; and createDate = &#63;.
	*
	* @param entryId the primary key of the current free days history entry
	* @param userId the user ID
	* @param createDate the create date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next free days history entry
	* @throws com.evozon.evoportal.evozonfreedaysallocator.NoSuchFreeDaysHistoryEntryException if a free days history entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.evozon.evoportal.evozonfreedaysallocator.model.FreeDaysHistoryEntry[] findByUserId_CreateDate_PrevAndNext(
		long entryId, long userId, java.util.Date createDate,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.evozon.evoportal.evozonfreedaysallocator.NoSuchFreeDaysHistoryEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the free days history entries where createDate = &#63;.
	*
	* @param createDate the create date
	* @return the matching free days history entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.evozon.evoportal.evozonfreedaysallocator.model.FreeDaysHistoryEntry> findByCreateDate(
		java.util.Date createDate)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the free days history entries where createDate = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param createDate the create date
	* @param start the lower bound of the range of free days history entries
	* @param end the upper bound of the range of free days history entries (not inclusive)
	* @return the range of matching free days history entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.evozon.evoportal.evozonfreedaysallocator.model.FreeDaysHistoryEntry> findByCreateDate(
		java.util.Date createDate, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the free days history entries where createDate = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param createDate the create date
	* @param start the lower bound of the range of free days history entries
	* @param end the upper bound of the range of free days history entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching free days history entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.evozon.evoportal.evozonfreedaysallocator.model.FreeDaysHistoryEntry> findByCreateDate(
		java.util.Date createDate, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first free days history entry in the ordered set where createDate = &#63;.
	*
	* @param createDate the create date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching free days history entry
	* @throws com.evozon.evoportal.evozonfreedaysallocator.NoSuchFreeDaysHistoryEntryException if a matching free days history entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.evozon.evoportal.evozonfreedaysallocator.model.FreeDaysHistoryEntry findByCreateDate_First(
		java.util.Date createDate,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.evozon.evoportal.evozonfreedaysallocator.NoSuchFreeDaysHistoryEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first free days history entry in the ordered set where createDate = &#63;.
	*
	* @param createDate the create date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching free days history entry, or <code>null</code> if a matching free days history entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.evozon.evoportal.evozonfreedaysallocator.model.FreeDaysHistoryEntry fetchByCreateDate_First(
		java.util.Date createDate,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last free days history entry in the ordered set where createDate = &#63;.
	*
	* @param createDate the create date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching free days history entry
	* @throws com.evozon.evoportal.evozonfreedaysallocator.NoSuchFreeDaysHistoryEntryException if a matching free days history entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.evozon.evoportal.evozonfreedaysallocator.model.FreeDaysHistoryEntry findByCreateDate_Last(
		java.util.Date createDate,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.evozon.evoportal.evozonfreedaysallocator.NoSuchFreeDaysHistoryEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last free days history entry in the ordered set where createDate = &#63;.
	*
	* @param createDate the create date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching free days history entry, or <code>null</code> if a matching free days history entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.evozon.evoportal.evozonfreedaysallocator.model.FreeDaysHistoryEntry fetchByCreateDate_Last(
		java.util.Date createDate,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the free days history entries before and after the current free days history entry in the ordered set where createDate = &#63;.
	*
	* @param entryId the primary key of the current free days history entry
	* @param createDate the create date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next free days history entry
	* @throws com.evozon.evoportal.evozonfreedaysallocator.NoSuchFreeDaysHistoryEntryException if a free days history entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.evozon.evoportal.evozonfreedaysallocator.model.FreeDaysHistoryEntry[] findByCreateDate_PrevAndNext(
		long entryId, java.util.Date createDate,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.evozon.evoportal.evozonfreedaysallocator.NoSuchFreeDaysHistoryEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the free days history entries.
	*
	* @return the free days history entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.evozon.evoportal.evozonfreedaysallocator.model.FreeDaysHistoryEntry> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the free days history entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of free days history entries
	* @param end the upper bound of the range of free days history entries (not inclusive)
	* @return the range of free days history entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.evozon.evoportal.evozonfreedaysallocator.model.FreeDaysHistoryEntry> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the free days history entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of free days history entries
	* @param end the upper bound of the range of free days history entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of free days history entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.evozon.evoportal.evozonfreedaysallocator.model.FreeDaysHistoryEntry> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the free days history entries where userId = &#63; from the database.
	*
	* @param userId the user ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByUserId(long userId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the free days history entries where userId = &#63; and operation = &#63; from the database.
	*
	* @param userId the user ID
	* @param operation the operation
	* @throws SystemException if a system exception occurred
	*/
	public void removeByUserId_Operation(long userId, java.lang.String operation)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the free days history entries where userId = &#63; and eventType = &#63; from the database.
	*
	* @param userId the user ID
	* @param eventType the event type
	* @throws SystemException if a system exception occurred
	*/
	public void removeByUserId_EventType(long userId, java.lang.String eventType)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the free days history entries where userId = &#63; and createDate = &#63; from the database.
	*
	* @param userId the user ID
	* @param createDate the create date
	* @throws SystemException if a system exception occurred
	*/
	public void removeByUserId_CreateDate(long userId, java.util.Date createDate)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the free days history entries where createDate = &#63; from the database.
	*
	* @param createDate the create date
	* @throws SystemException if a system exception occurred
	*/
	public void removeByCreateDate(java.util.Date createDate)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the free days history entries from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of free days history entries where userId = &#63;.
	*
	* @param userId the user ID
	* @return the number of matching free days history entries
	* @throws SystemException if a system exception occurred
	*/
	public int countByUserId(long userId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of free days history entries where userId = &#63; and operation = &#63;.
	*
	* @param userId the user ID
	* @param operation the operation
	* @return the number of matching free days history entries
	* @throws SystemException if a system exception occurred
	*/
	public int countByUserId_Operation(long userId, java.lang.String operation)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of free days history entries where userId = &#63; and eventType = &#63;.
	*
	* @param userId the user ID
	* @param eventType the event type
	* @return the number of matching free days history entries
	* @throws SystemException if a system exception occurred
	*/
	public int countByUserId_EventType(long userId, java.lang.String eventType)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of free days history entries where userId = &#63; and createDate = &#63;.
	*
	* @param userId the user ID
	* @param createDate the create date
	* @return the number of matching free days history entries
	* @throws SystemException if a system exception occurred
	*/
	public int countByUserId_CreateDate(long userId, java.util.Date createDate)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of free days history entries where createDate = &#63;.
	*
	* @param createDate the create date
	* @return the number of matching free days history entries
	* @throws SystemException if a system exception occurred
	*/
	public int countByCreateDate(java.util.Date createDate)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of free days history entries.
	*
	* @return the number of free days history entries
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}