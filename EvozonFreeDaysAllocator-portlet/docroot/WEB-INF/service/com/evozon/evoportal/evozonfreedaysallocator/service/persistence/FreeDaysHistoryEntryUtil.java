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

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the free days history entry service. This utility wraps {@link FreeDaysHistoryEntryPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Cristina Kiss
 * @see FreeDaysHistoryEntryPersistence
 * @see FreeDaysHistoryEntryPersistenceImpl
 * @generated
 */
public class FreeDaysHistoryEntryUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#clearCache(com.liferay.portal.model.BaseModel)
	 */
	public static void clearCache(FreeDaysHistoryEntry freeDaysHistoryEntry) {
		getPersistence().clearCache(freeDaysHistoryEntry);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public long countWithDynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<FreeDaysHistoryEntry> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<FreeDaysHistoryEntry> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<FreeDaysHistoryEntry> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
	 */
	public static FreeDaysHistoryEntry update(
		FreeDaysHistoryEntry freeDaysHistoryEntry, boolean merge)
		throws SystemException {
		return getPersistence().update(freeDaysHistoryEntry, merge);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
	 */
	public static FreeDaysHistoryEntry update(
		FreeDaysHistoryEntry freeDaysHistoryEntry, boolean merge,
		ServiceContext serviceContext) throws SystemException {
		return getPersistence()
				   .update(freeDaysHistoryEntry, merge, serviceContext);
	}

	/**
	* Caches the free days history entry in the entity cache if it is enabled.
	*
	* @param freeDaysHistoryEntry the free days history entry
	*/
	public static void cacheResult(
		com.evozon.evoportal.evozonfreedaysallocator.model.FreeDaysHistoryEntry freeDaysHistoryEntry) {
		getPersistence().cacheResult(freeDaysHistoryEntry);
	}

	/**
	* Caches the free days history entries in the entity cache if it is enabled.
	*
	* @param freeDaysHistoryEntries the free days history entries
	*/
	public static void cacheResult(
		java.util.List<com.evozon.evoportal.evozonfreedaysallocator.model.FreeDaysHistoryEntry> freeDaysHistoryEntries) {
		getPersistence().cacheResult(freeDaysHistoryEntries);
	}

	/**
	* Creates a new free days history entry with the primary key. Does not add the free days history entry to the database.
	*
	* @param entryId the primary key for the new free days history entry
	* @return the new free days history entry
	*/
	public static com.evozon.evoportal.evozonfreedaysallocator.model.FreeDaysHistoryEntry create(
		long entryId) {
		return getPersistence().create(entryId);
	}

	/**
	* Removes the free days history entry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param entryId the primary key of the free days history entry
	* @return the free days history entry that was removed
	* @throws com.evozon.evoportal.evozonfreedaysallocator.NoSuchFreeDaysHistoryEntryException if a free days history entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.evozon.evoportal.evozonfreedaysallocator.model.FreeDaysHistoryEntry remove(
		long entryId)
		throws com.evozon.evoportal.evozonfreedaysallocator.NoSuchFreeDaysHistoryEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().remove(entryId);
	}

	public static com.evozon.evoportal.evozonfreedaysallocator.model.FreeDaysHistoryEntry updateImpl(
		com.evozon.evoportal.evozonfreedaysallocator.model.FreeDaysHistoryEntry freeDaysHistoryEntry,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(freeDaysHistoryEntry, merge);
	}

	/**
	* Returns the free days history entry with the primary key or throws a {@link com.evozon.evoportal.evozonfreedaysallocator.NoSuchFreeDaysHistoryEntryException} if it could not be found.
	*
	* @param entryId the primary key of the free days history entry
	* @return the free days history entry
	* @throws com.evozon.evoportal.evozonfreedaysallocator.NoSuchFreeDaysHistoryEntryException if a free days history entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.evozon.evoportal.evozonfreedaysallocator.model.FreeDaysHistoryEntry findByPrimaryKey(
		long entryId)
		throws com.evozon.evoportal.evozonfreedaysallocator.NoSuchFreeDaysHistoryEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByPrimaryKey(entryId);
	}

	/**
	* Returns the free days history entry with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param entryId the primary key of the free days history entry
	* @return the free days history entry, or <code>null</code> if a free days history entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.evozon.evoportal.evozonfreedaysallocator.model.FreeDaysHistoryEntry fetchByPrimaryKey(
		long entryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(entryId);
	}

	/**
	* Returns all the free days history entries where userId = &#63;.
	*
	* @param userId the user ID
	* @return the matching free days history entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.evozon.evoportal.evozonfreedaysallocator.model.FreeDaysHistoryEntry> findByUserId(
		long userId) throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUserId(userId);
	}

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
	public static java.util.List<com.evozon.evoportal.evozonfreedaysallocator.model.FreeDaysHistoryEntry> findByUserId(
		long userId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUserId(userId, start, end);
	}

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
	public static java.util.List<com.evozon.evoportal.evozonfreedaysallocator.model.FreeDaysHistoryEntry> findByUserId(
		long userId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByUserId(userId, start, end, orderByComparator);
	}

	/**
	* Returns the first free days history entry in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching free days history entry
	* @throws com.evozon.evoportal.evozonfreedaysallocator.NoSuchFreeDaysHistoryEntryException if a matching free days history entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.evozon.evoportal.evozonfreedaysallocator.model.FreeDaysHistoryEntry findByUserId_First(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.evozon.evoportal.evozonfreedaysallocator.NoSuchFreeDaysHistoryEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUserId_First(userId, orderByComparator);
	}

	/**
	* Returns the first free days history entry in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching free days history entry, or <code>null</code> if a matching free days history entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.evozon.evoportal.evozonfreedaysallocator.model.FreeDaysHistoryEntry fetchByUserId_First(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByUserId_First(userId, orderByComparator);
	}

	/**
	* Returns the last free days history entry in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching free days history entry
	* @throws com.evozon.evoportal.evozonfreedaysallocator.NoSuchFreeDaysHistoryEntryException if a matching free days history entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.evozon.evoportal.evozonfreedaysallocator.model.FreeDaysHistoryEntry findByUserId_Last(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.evozon.evoportal.evozonfreedaysallocator.NoSuchFreeDaysHistoryEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUserId_Last(userId, orderByComparator);
	}

	/**
	* Returns the last free days history entry in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching free days history entry, or <code>null</code> if a matching free days history entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.evozon.evoportal.evozonfreedaysallocator.model.FreeDaysHistoryEntry fetchByUserId_Last(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByUserId_Last(userId, orderByComparator);
	}

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
	public static com.evozon.evoportal.evozonfreedaysallocator.model.FreeDaysHistoryEntry[] findByUserId_PrevAndNext(
		long entryId, long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.evozon.evoportal.evozonfreedaysallocator.NoSuchFreeDaysHistoryEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByUserId_PrevAndNext(entryId, userId, orderByComparator);
	}

	/**
	* Returns all the free days history entries where userId = &#63; and operation = &#63;.
	*
	* @param userId the user ID
	* @param operation the operation
	* @return the matching free days history entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.evozon.evoportal.evozonfreedaysallocator.model.FreeDaysHistoryEntry> findByUserId_Operation(
		long userId, java.lang.String operation)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUserId_Operation(userId, operation);
	}

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
	public static java.util.List<com.evozon.evoportal.evozonfreedaysallocator.model.FreeDaysHistoryEntry> findByUserId_Operation(
		long userId, java.lang.String operation, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByUserId_Operation(userId, operation, start, end);
	}

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
	public static java.util.List<com.evozon.evoportal.evozonfreedaysallocator.model.FreeDaysHistoryEntry> findByUserId_Operation(
		long userId, java.lang.String operation, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByUserId_Operation(userId, operation, start, end,
			orderByComparator);
	}

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
	public static com.evozon.evoportal.evozonfreedaysallocator.model.FreeDaysHistoryEntry findByUserId_Operation_First(
		long userId, java.lang.String operation,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.evozon.evoportal.evozonfreedaysallocator.NoSuchFreeDaysHistoryEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByUserId_Operation_First(userId, operation,
			orderByComparator);
	}

	/**
	* Returns the first free days history entry in the ordered set where userId = &#63; and operation = &#63;.
	*
	* @param userId the user ID
	* @param operation the operation
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching free days history entry, or <code>null</code> if a matching free days history entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.evozon.evoportal.evozonfreedaysallocator.model.FreeDaysHistoryEntry fetchByUserId_Operation_First(
		long userId, java.lang.String operation,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByUserId_Operation_First(userId, operation,
			orderByComparator);
	}

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
	public static com.evozon.evoportal.evozonfreedaysallocator.model.FreeDaysHistoryEntry findByUserId_Operation_Last(
		long userId, java.lang.String operation,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.evozon.evoportal.evozonfreedaysallocator.NoSuchFreeDaysHistoryEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByUserId_Operation_Last(userId, operation,
			orderByComparator);
	}

	/**
	* Returns the last free days history entry in the ordered set where userId = &#63; and operation = &#63;.
	*
	* @param userId the user ID
	* @param operation the operation
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching free days history entry, or <code>null</code> if a matching free days history entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.evozon.evoportal.evozonfreedaysallocator.model.FreeDaysHistoryEntry fetchByUserId_Operation_Last(
		long userId, java.lang.String operation,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByUserId_Operation_Last(userId, operation,
			orderByComparator);
	}

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
	public static com.evozon.evoportal.evozonfreedaysallocator.model.FreeDaysHistoryEntry[] findByUserId_Operation_PrevAndNext(
		long entryId, long userId, java.lang.String operation,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.evozon.evoportal.evozonfreedaysallocator.NoSuchFreeDaysHistoryEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByUserId_Operation_PrevAndNext(entryId, userId,
			operation, orderByComparator);
	}

	/**
	* Returns all the free days history entries where userId = &#63; and eventType = &#63;.
	*
	* @param userId the user ID
	* @param eventType the event type
	* @return the matching free days history entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.evozon.evoportal.evozonfreedaysallocator.model.FreeDaysHistoryEntry> findByUserId_EventType(
		long userId, java.lang.String eventType)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUserId_EventType(userId, eventType);
	}

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
	public static java.util.List<com.evozon.evoportal.evozonfreedaysallocator.model.FreeDaysHistoryEntry> findByUserId_EventType(
		long userId, java.lang.String eventType, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByUserId_EventType(userId, eventType, start, end);
	}

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
	public static java.util.List<com.evozon.evoportal.evozonfreedaysallocator.model.FreeDaysHistoryEntry> findByUserId_EventType(
		long userId, java.lang.String eventType, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByUserId_EventType(userId, eventType, start, end,
			orderByComparator);
	}

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
	public static com.evozon.evoportal.evozonfreedaysallocator.model.FreeDaysHistoryEntry findByUserId_EventType_First(
		long userId, java.lang.String eventType,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.evozon.evoportal.evozonfreedaysallocator.NoSuchFreeDaysHistoryEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByUserId_EventType_First(userId, eventType,
			orderByComparator);
	}

	/**
	* Returns the first free days history entry in the ordered set where userId = &#63; and eventType = &#63;.
	*
	* @param userId the user ID
	* @param eventType the event type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching free days history entry, or <code>null</code> if a matching free days history entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.evozon.evoportal.evozonfreedaysallocator.model.FreeDaysHistoryEntry fetchByUserId_EventType_First(
		long userId, java.lang.String eventType,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByUserId_EventType_First(userId, eventType,
			orderByComparator);
	}

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
	public static com.evozon.evoportal.evozonfreedaysallocator.model.FreeDaysHistoryEntry findByUserId_EventType_Last(
		long userId, java.lang.String eventType,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.evozon.evoportal.evozonfreedaysallocator.NoSuchFreeDaysHistoryEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByUserId_EventType_Last(userId, eventType,
			orderByComparator);
	}

	/**
	* Returns the last free days history entry in the ordered set where userId = &#63; and eventType = &#63;.
	*
	* @param userId the user ID
	* @param eventType the event type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching free days history entry, or <code>null</code> if a matching free days history entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.evozon.evoportal.evozonfreedaysallocator.model.FreeDaysHistoryEntry fetchByUserId_EventType_Last(
		long userId, java.lang.String eventType,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByUserId_EventType_Last(userId, eventType,
			orderByComparator);
	}

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
	public static com.evozon.evoportal.evozonfreedaysallocator.model.FreeDaysHistoryEntry[] findByUserId_EventType_PrevAndNext(
		long entryId, long userId, java.lang.String eventType,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.evozon.evoportal.evozonfreedaysallocator.NoSuchFreeDaysHistoryEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByUserId_EventType_PrevAndNext(entryId, userId,
			eventType, orderByComparator);
	}

	/**
	* Returns all the free days history entries where userId = &#63; and createDate = &#63;.
	*
	* @param userId the user ID
	* @param createDate the create date
	* @return the matching free days history entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.evozon.evoportal.evozonfreedaysallocator.model.FreeDaysHistoryEntry> findByUserId_CreateDate(
		long userId, java.util.Date createDate)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUserId_CreateDate(userId, createDate);
	}

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
	public static java.util.List<com.evozon.evoportal.evozonfreedaysallocator.model.FreeDaysHistoryEntry> findByUserId_CreateDate(
		long userId, java.util.Date createDate, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByUserId_CreateDate(userId, createDate, start, end);
	}

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
	public static java.util.List<com.evozon.evoportal.evozonfreedaysallocator.model.FreeDaysHistoryEntry> findByUserId_CreateDate(
		long userId, java.util.Date createDate, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByUserId_CreateDate(userId, createDate, start, end,
			orderByComparator);
	}

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
	public static com.evozon.evoportal.evozonfreedaysallocator.model.FreeDaysHistoryEntry findByUserId_CreateDate_First(
		long userId, java.util.Date createDate,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.evozon.evoportal.evozonfreedaysallocator.NoSuchFreeDaysHistoryEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByUserId_CreateDate_First(userId, createDate,
			orderByComparator);
	}

	/**
	* Returns the first free days history entry in the ordered set where userId = &#63; and createDate = &#63;.
	*
	* @param userId the user ID
	* @param createDate the create date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching free days history entry, or <code>null</code> if a matching free days history entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.evozon.evoportal.evozonfreedaysallocator.model.FreeDaysHistoryEntry fetchByUserId_CreateDate_First(
		long userId, java.util.Date createDate,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByUserId_CreateDate_First(userId, createDate,
			orderByComparator);
	}

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
	public static com.evozon.evoportal.evozonfreedaysallocator.model.FreeDaysHistoryEntry findByUserId_CreateDate_Last(
		long userId, java.util.Date createDate,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.evozon.evoportal.evozonfreedaysallocator.NoSuchFreeDaysHistoryEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByUserId_CreateDate_Last(userId, createDate,
			orderByComparator);
	}

	/**
	* Returns the last free days history entry in the ordered set where userId = &#63; and createDate = &#63;.
	*
	* @param userId the user ID
	* @param createDate the create date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching free days history entry, or <code>null</code> if a matching free days history entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.evozon.evoportal.evozonfreedaysallocator.model.FreeDaysHistoryEntry fetchByUserId_CreateDate_Last(
		long userId, java.util.Date createDate,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByUserId_CreateDate_Last(userId, createDate,
			orderByComparator);
	}

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
	public static com.evozon.evoportal.evozonfreedaysallocator.model.FreeDaysHistoryEntry[] findByUserId_CreateDate_PrevAndNext(
		long entryId, long userId, java.util.Date createDate,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.evozon.evoportal.evozonfreedaysallocator.NoSuchFreeDaysHistoryEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByUserId_CreateDate_PrevAndNext(entryId, userId,
			createDate, orderByComparator);
	}

	/**
	* Returns all the free days history entries where createDate = &#63;.
	*
	* @param createDate the create date
	* @return the matching free days history entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.evozon.evoportal.evozonfreedaysallocator.model.FreeDaysHistoryEntry> findByCreateDate(
		java.util.Date createDate)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByCreateDate(createDate);
	}

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
	public static java.util.List<com.evozon.evoportal.evozonfreedaysallocator.model.FreeDaysHistoryEntry> findByCreateDate(
		java.util.Date createDate, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByCreateDate(createDate, start, end);
	}

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
	public static java.util.List<com.evozon.evoportal.evozonfreedaysallocator.model.FreeDaysHistoryEntry> findByCreateDate(
		java.util.Date createDate, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByCreateDate(createDate, start, end, orderByComparator);
	}

	/**
	* Returns the first free days history entry in the ordered set where createDate = &#63;.
	*
	* @param createDate the create date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching free days history entry
	* @throws com.evozon.evoportal.evozonfreedaysallocator.NoSuchFreeDaysHistoryEntryException if a matching free days history entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.evozon.evoportal.evozonfreedaysallocator.model.FreeDaysHistoryEntry findByCreateDate_First(
		java.util.Date createDate,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.evozon.evoportal.evozonfreedaysallocator.NoSuchFreeDaysHistoryEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByCreateDate_First(createDate, orderByComparator);
	}

	/**
	* Returns the first free days history entry in the ordered set where createDate = &#63;.
	*
	* @param createDate the create date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching free days history entry, or <code>null</code> if a matching free days history entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.evozon.evoportal.evozonfreedaysallocator.model.FreeDaysHistoryEntry fetchByCreateDate_First(
		java.util.Date createDate,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByCreateDate_First(createDate, orderByComparator);
	}

	/**
	* Returns the last free days history entry in the ordered set where createDate = &#63;.
	*
	* @param createDate the create date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching free days history entry
	* @throws com.evozon.evoportal.evozonfreedaysallocator.NoSuchFreeDaysHistoryEntryException if a matching free days history entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.evozon.evoportal.evozonfreedaysallocator.model.FreeDaysHistoryEntry findByCreateDate_Last(
		java.util.Date createDate,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.evozon.evoportal.evozonfreedaysallocator.NoSuchFreeDaysHistoryEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByCreateDate_Last(createDate, orderByComparator);
	}

	/**
	* Returns the last free days history entry in the ordered set where createDate = &#63;.
	*
	* @param createDate the create date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching free days history entry, or <code>null</code> if a matching free days history entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.evozon.evoportal.evozonfreedaysallocator.model.FreeDaysHistoryEntry fetchByCreateDate_Last(
		java.util.Date createDate,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByCreateDate_Last(createDate, orderByComparator);
	}

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
	public static com.evozon.evoportal.evozonfreedaysallocator.model.FreeDaysHistoryEntry[] findByCreateDate_PrevAndNext(
		long entryId, java.util.Date createDate,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.evozon.evoportal.evozonfreedaysallocator.NoSuchFreeDaysHistoryEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByCreateDate_PrevAndNext(entryId, createDate,
			orderByComparator);
	}

	/**
	* Returns all the free days history entries.
	*
	* @return the free days history entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.evozon.evoportal.evozonfreedaysallocator.model.FreeDaysHistoryEntry> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

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
	public static java.util.List<com.evozon.evoportal.evozonfreedaysallocator.model.FreeDaysHistoryEntry> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

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
	public static java.util.List<com.evozon.evoportal.evozonfreedaysallocator.model.FreeDaysHistoryEntry> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the free days history entries where userId = &#63; from the database.
	*
	* @param userId the user ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByUserId(long userId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByUserId(userId);
	}

	/**
	* Removes all the free days history entries where userId = &#63; and operation = &#63; from the database.
	*
	* @param userId the user ID
	* @param operation the operation
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByUserId_Operation(long userId,
		java.lang.String operation)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByUserId_Operation(userId, operation);
	}

	/**
	* Removes all the free days history entries where userId = &#63; and eventType = &#63; from the database.
	*
	* @param userId the user ID
	* @param eventType the event type
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByUserId_EventType(long userId,
		java.lang.String eventType)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByUserId_EventType(userId, eventType);
	}

	/**
	* Removes all the free days history entries where userId = &#63; and createDate = &#63; from the database.
	*
	* @param userId the user ID
	* @param createDate the create date
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByUserId_CreateDate(long userId,
		java.util.Date createDate)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByUserId_CreateDate(userId, createDate);
	}

	/**
	* Removes all the free days history entries where createDate = &#63; from the database.
	*
	* @param createDate the create date
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByCreateDate(java.util.Date createDate)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByCreateDate(createDate);
	}

	/**
	* Removes all the free days history entries from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of free days history entries where userId = &#63;.
	*
	* @param userId the user ID
	* @return the number of matching free days history entries
	* @throws SystemException if a system exception occurred
	*/
	public static int countByUserId(long userId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByUserId(userId);
	}

	/**
	* Returns the number of free days history entries where userId = &#63; and operation = &#63;.
	*
	* @param userId the user ID
	* @param operation the operation
	* @return the number of matching free days history entries
	* @throws SystemException if a system exception occurred
	*/
	public static int countByUserId_Operation(long userId,
		java.lang.String operation)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByUserId_Operation(userId, operation);
	}

	/**
	* Returns the number of free days history entries where userId = &#63; and eventType = &#63;.
	*
	* @param userId the user ID
	* @param eventType the event type
	* @return the number of matching free days history entries
	* @throws SystemException if a system exception occurred
	*/
	public static int countByUserId_EventType(long userId,
		java.lang.String eventType)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByUserId_EventType(userId, eventType);
	}

	/**
	* Returns the number of free days history entries where userId = &#63; and createDate = &#63;.
	*
	* @param userId the user ID
	* @param createDate the create date
	* @return the number of matching free days history entries
	* @throws SystemException if a system exception occurred
	*/
	public static int countByUserId_CreateDate(long userId,
		java.util.Date createDate)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByUserId_CreateDate(userId, createDate);
	}

	/**
	* Returns the number of free days history entries where createDate = &#63;.
	*
	* @param createDate the create date
	* @return the number of matching free days history entries
	* @throws SystemException if a system exception occurred
	*/
	public static int countByCreateDate(java.util.Date createDate)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByCreateDate(createDate);
	}

	/**
	* Returns the number of free days history entries.
	*
	* @return the number of free days history entries
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static FreeDaysHistoryEntryPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (FreeDaysHistoryEntryPersistence)PortletBeanLocatorUtil.locate(com.evozon.evoportal.evozonfreedaysallocator.service.ClpSerializer.getServletContextName(),
					FreeDaysHistoryEntryPersistence.class.getName());

			ReferenceRegistry.registerReference(FreeDaysHistoryEntryUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	/**
	 * @deprecated
	 */
	public void setPersistence(FreeDaysHistoryEntryPersistence persistence) {
	}

	private static FreeDaysHistoryEntryPersistence _persistence;
}