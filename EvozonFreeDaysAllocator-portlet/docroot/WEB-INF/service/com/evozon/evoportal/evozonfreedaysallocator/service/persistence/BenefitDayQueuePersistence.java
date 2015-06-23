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

import com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDayQueue;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the benefit day queue service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Cristina Kiss
 * @see BenefitDayQueuePersistenceImpl
 * @see BenefitDayQueueUtil
 * @generated
 */
public interface BenefitDayQueuePersistence extends BasePersistence<BenefitDayQueue> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link BenefitDayQueueUtil} to access the benefit day queue persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the benefit day queue in the entity cache if it is enabled.
	*
	* @param benefitDayQueue the benefit day queue
	*/
	public void cacheResult(
		com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDayQueue benefitDayQueue);

	/**
	* Caches the benefit day queues in the entity cache if it is enabled.
	*
	* @param benefitDayQueues the benefit day queues
	*/
	public void cacheResult(
		java.util.List<com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDayQueue> benefitDayQueues);

	/**
	* Creates a new benefit day queue with the primary key. Does not add the benefit day queue to the database.
	*
	* @param entryId the primary key for the new benefit day queue
	* @return the new benefit day queue
	*/
	public com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDayQueue create(
		long entryId);

	/**
	* Removes the benefit day queue with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param entryId the primary key of the benefit day queue
	* @return the benefit day queue that was removed
	* @throws com.evozon.evoportal.evozonfreedaysallocator.NoSuchQueueException if a benefit day queue with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDayQueue remove(
		long entryId)
		throws com.evozon.evoportal.evozonfreedaysallocator.NoSuchQueueException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDayQueue updateImpl(
		com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDayQueue benefitDayQueue,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the benefit day queue with the primary key or throws a {@link com.evozon.evoportal.evozonfreedaysallocator.NoSuchQueueException} if it could not be found.
	*
	* @param entryId the primary key of the benefit day queue
	* @return the benefit day queue
	* @throws com.evozon.evoportal.evozonfreedaysallocator.NoSuchQueueException if a benefit day queue with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDayQueue findByPrimaryKey(
		long entryId)
		throws com.evozon.evoportal.evozonfreedaysallocator.NoSuchQueueException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the benefit day queue with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param entryId the primary key of the benefit day queue
	* @return the benefit day queue, or <code>null</code> if a benefit day queue with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDayQueue fetchByPrimaryKey(
		long entryId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the benefit day queues where userId = &#63;.
	*
	* @param userId the user ID
	* @return the matching benefit day queues
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDayQueue> findByUserId(
		long userId) throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the benefit day queues where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userId the user ID
	* @param start the lower bound of the range of benefit day queues
	* @param end the upper bound of the range of benefit day queues (not inclusive)
	* @return the range of matching benefit day queues
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDayQueue> findByUserId(
		long userId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the benefit day queues where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userId the user ID
	* @param start the lower bound of the range of benefit day queues
	* @param end the upper bound of the range of benefit day queues (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching benefit day queues
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDayQueue> findByUserId(
		long userId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first benefit day queue in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching benefit day queue
	* @throws com.evozon.evoportal.evozonfreedaysallocator.NoSuchQueueException if a matching benefit day queue could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDayQueue findByUserId_First(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.evozon.evoportal.evozonfreedaysallocator.NoSuchQueueException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first benefit day queue in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching benefit day queue, or <code>null</code> if a matching benefit day queue could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDayQueue fetchByUserId_First(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last benefit day queue in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching benefit day queue
	* @throws com.evozon.evoportal.evozonfreedaysallocator.NoSuchQueueException if a matching benefit day queue could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDayQueue findByUserId_Last(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.evozon.evoportal.evozonfreedaysallocator.NoSuchQueueException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last benefit day queue in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching benefit day queue, or <code>null</code> if a matching benefit day queue could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDayQueue fetchByUserId_Last(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the benefit day queues before and after the current benefit day queue in the ordered set where userId = &#63;.
	*
	* @param entryId the primary key of the current benefit day queue
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next benefit day queue
	* @throws com.evozon.evoportal.evozonfreedaysallocator.NoSuchQueueException if a benefit day queue with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDayQueue[] findByUserId_PrevAndNext(
		long entryId, long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.evozon.evoportal.evozonfreedaysallocator.NoSuchQueueException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the benefit day queues where type = &#63;.
	*
	* @param type the type
	* @return the matching benefit day queues
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDayQueue> findByType(
		java.lang.String type)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the benefit day queues where type = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param type the type
	* @param start the lower bound of the range of benefit day queues
	* @param end the upper bound of the range of benefit day queues (not inclusive)
	* @return the range of matching benefit day queues
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDayQueue> findByType(
		java.lang.String type, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the benefit day queues where type = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param type the type
	* @param start the lower bound of the range of benefit day queues
	* @param end the upper bound of the range of benefit day queues (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching benefit day queues
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDayQueue> findByType(
		java.lang.String type, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first benefit day queue in the ordered set where type = &#63;.
	*
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching benefit day queue
	* @throws com.evozon.evoportal.evozonfreedaysallocator.NoSuchQueueException if a matching benefit day queue could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDayQueue findByType_First(
		java.lang.String type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.evozon.evoportal.evozonfreedaysallocator.NoSuchQueueException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first benefit day queue in the ordered set where type = &#63;.
	*
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching benefit day queue, or <code>null</code> if a matching benefit day queue could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDayQueue fetchByType_First(
		java.lang.String type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last benefit day queue in the ordered set where type = &#63;.
	*
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching benefit day queue
	* @throws com.evozon.evoportal.evozonfreedaysallocator.NoSuchQueueException if a matching benefit day queue could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDayQueue findByType_Last(
		java.lang.String type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.evozon.evoportal.evozonfreedaysallocator.NoSuchQueueException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last benefit day queue in the ordered set where type = &#63;.
	*
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching benefit day queue, or <code>null</code> if a matching benefit day queue could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDayQueue fetchByType_Last(
		java.lang.String type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the benefit day queues before and after the current benefit day queue in the ordered set where type = &#63;.
	*
	* @param entryId the primary key of the current benefit day queue
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next benefit day queue
	* @throws com.evozon.evoportal.evozonfreedaysallocator.NoSuchQueueException if a benefit day queue with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDayQueue[] findByType_PrevAndNext(
		long entryId, java.lang.String type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.evozon.evoportal.evozonfreedaysallocator.NoSuchQueueException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the benefit day queues where userId = &#63; and queued = &#63;.
	*
	* @param userId the user ID
	* @param queued the queued
	* @return the matching benefit day queues
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDayQueue> findByUserId_Queued(
		long userId, boolean queued)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the benefit day queues where userId = &#63; and queued = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userId the user ID
	* @param queued the queued
	* @param start the lower bound of the range of benefit day queues
	* @param end the upper bound of the range of benefit day queues (not inclusive)
	* @return the range of matching benefit day queues
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDayQueue> findByUserId_Queued(
		long userId, boolean queued, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the benefit day queues where userId = &#63; and queued = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userId the user ID
	* @param queued the queued
	* @param start the lower bound of the range of benefit day queues
	* @param end the upper bound of the range of benefit day queues (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching benefit day queues
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDayQueue> findByUserId_Queued(
		long userId, boolean queued, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first benefit day queue in the ordered set where userId = &#63; and queued = &#63;.
	*
	* @param userId the user ID
	* @param queued the queued
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching benefit day queue
	* @throws com.evozon.evoportal.evozonfreedaysallocator.NoSuchQueueException if a matching benefit day queue could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDayQueue findByUserId_Queued_First(
		long userId, boolean queued,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.evozon.evoportal.evozonfreedaysallocator.NoSuchQueueException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first benefit day queue in the ordered set where userId = &#63; and queued = &#63;.
	*
	* @param userId the user ID
	* @param queued the queued
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching benefit day queue, or <code>null</code> if a matching benefit day queue could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDayQueue fetchByUserId_Queued_First(
		long userId, boolean queued,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last benefit day queue in the ordered set where userId = &#63; and queued = &#63;.
	*
	* @param userId the user ID
	* @param queued the queued
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching benefit day queue
	* @throws com.evozon.evoportal.evozonfreedaysallocator.NoSuchQueueException if a matching benefit day queue could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDayQueue findByUserId_Queued_Last(
		long userId, boolean queued,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.evozon.evoportal.evozonfreedaysallocator.NoSuchQueueException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last benefit day queue in the ordered set where userId = &#63; and queued = &#63;.
	*
	* @param userId the user ID
	* @param queued the queued
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching benefit day queue, or <code>null</code> if a matching benefit day queue could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDayQueue fetchByUserId_Queued_Last(
		long userId, boolean queued,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the benefit day queues before and after the current benefit day queue in the ordered set where userId = &#63; and queued = &#63;.
	*
	* @param entryId the primary key of the current benefit day queue
	* @param userId the user ID
	* @param queued the queued
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next benefit day queue
	* @throws com.evozon.evoportal.evozonfreedaysallocator.NoSuchQueueException if a benefit day queue with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDayQueue[] findByUserId_Queued_PrevAndNext(
		long entryId, long userId, boolean queued,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.evozon.evoportal.evozonfreedaysallocator.NoSuchQueueException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the benefit day queues where userId = &#63; and allocatedDate = &#63;.
	*
	* @param userId the user ID
	* @param allocatedDate the allocated date
	* @return the matching benefit day queues
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDayQueue> findByUserId_Allocated(
		long userId, java.util.Date allocatedDate)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the benefit day queues where userId = &#63; and allocatedDate = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userId the user ID
	* @param allocatedDate the allocated date
	* @param start the lower bound of the range of benefit day queues
	* @param end the upper bound of the range of benefit day queues (not inclusive)
	* @return the range of matching benefit day queues
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDayQueue> findByUserId_Allocated(
		long userId, java.util.Date allocatedDate, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the benefit day queues where userId = &#63; and allocatedDate = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userId the user ID
	* @param allocatedDate the allocated date
	* @param start the lower bound of the range of benefit day queues
	* @param end the upper bound of the range of benefit day queues (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching benefit day queues
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDayQueue> findByUserId_Allocated(
		long userId, java.util.Date allocatedDate, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first benefit day queue in the ordered set where userId = &#63; and allocatedDate = &#63;.
	*
	* @param userId the user ID
	* @param allocatedDate the allocated date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching benefit day queue
	* @throws com.evozon.evoportal.evozonfreedaysallocator.NoSuchQueueException if a matching benefit day queue could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDayQueue findByUserId_Allocated_First(
		long userId, java.util.Date allocatedDate,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.evozon.evoportal.evozonfreedaysallocator.NoSuchQueueException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first benefit day queue in the ordered set where userId = &#63; and allocatedDate = &#63;.
	*
	* @param userId the user ID
	* @param allocatedDate the allocated date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching benefit day queue, or <code>null</code> if a matching benefit day queue could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDayQueue fetchByUserId_Allocated_First(
		long userId, java.util.Date allocatedDate,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last benefit day queue in the ordered set where userId = &#63; and allocatedDate = &#63;.
	*
	* @param userId the user ID
	* @param allocatedDate the allocated date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching benefit day queue
	* @throws com.evozon.evoportal.evozonfreedaysallocator.NoSuchQueueException if a matching benefit day queue could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDayQueue findByUserId_Allocated_Last(
		long userId, java.util.Date allocatedDate,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.evozon.evoportal.evozonfreedaysallocator.NoSuchQueueException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last benefit day queue in the ordered set where userId = &#63; and allocatedDate = &#63;.
	*
	* @param userId the user ID
	* @param allocatedDate the allocated date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching benefit day queue, or <code>null</code> if a matching benefit day queue could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDayQueue fetchByUserId_Allocated_Last(
		long userId, java.util.Date allocatedDate,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the benefit day queues before and after the current benefit day queue in the ordered set where userId = &#63; and allocatedDate = &#63;.
	*
	* @param entryId the primary key of the current benefit day queue
	* @param userId the user ID
	* @param allocatedDate the allocated date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next benefit day queue
	* @throws com.evozon.evoportal.evozonfreedaysallocator.NoSuchQueueException if a benefit day queue with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDayQueue[] findByUserId_Allocated_PrevAndNext(
		long entryId, long userId, java.util.Date allocatedDate,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.evozon.evoportal.evozonfreedaysallocator.NoSuchQueueException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the benefit day queues where userId = &#63; and type = &#63;.
	*
	* @param userId the user ID
	* @param type the type
	* @return the matching benefit day queues
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDayQueue> findByUserId_Type(
		long userId, java.lang.String type)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the benefit day queues where userId = &#63; and type = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userId the user ID
	* @param type the type
	* @param start the lower bound of the range of benefit day queues
	* @param end the upper bound of the range of benefit day queues (not inclusive)
	* @return the range of matching benefit day queues
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDayQueue> findByUserId_Type(
		long userId, java.lang.String type, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the benefit day queues where userId = &#63; and type = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userId the user ID
	* @param type the type
	* @param start the lower bound of the range of benefit day queues
	* @param end the upper bound of the range of benefit day queues (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching benefit day queues
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDayQueue> findByUserId_Type(
		long userId, java.lang.String type, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first benefit day queue in the ordered set where userId = &#63; and type = &#63;.
	*
	* @param userId the user ID
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching benefit day queue
	* @throws com.evozon.evoportal.evozonfreedaysallocator.NoSuchQueueException if a matching benefit day queue could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDayQueue findByUserId_Type_First(
		long userId, java.lang.String type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.evozon.evoportal.evozonfreedaysallocator.NoSuchQueueException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first benefit day queue in the ordered set where userId = &#63; and type = &#63;.
	*
	* @param userId the user ID
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching benefit day queue, or <code>null</code> if a matching benefit day queue could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDayQueue fetchByUserId_Type_First(
		long userId, java.lang.String type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last benefit day queue in the ordered set where userId = &#63; and type = &#63;.
	*
	* @param userId the user ID
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching benefit day queue
	* @throws com.evozon.evoportal.evozonfreedaysallocator.NoSuchQueueException if a matching benefit day queue could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDayQueue findByUserId_Type_Last(
		long userId, java.lang.String type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.evozon.evoportal.evozonfreedaysallocator.NoSuchQueueException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last benefit day queue in the ordered set where userId = &#63; and type = &#63;.
	*
	* @param userId the user ID
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching benefit day queue, or <code>null</code> if a matching benefit day queue could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDayQueue fetchByUserId_Type_Last(
		long userId, java.lang.String type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the benefit day queues before and after the current benefit day queue in the ordered set where userId = &#63; and type = &#63;.
	*
	* @param entryId the primary key of the current benefit day queue
	* @param userId the user ID
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next benefit day queue
	* @throws com.evozon.evoportal.evozonfreedaysallocator.NoSuchQueueException if a benefit day queue with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDayQueue[] findByUserId_Type_PrevAndNext(
		long entryId, long userId, java.lang.String type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.evozon.evoportal.evozonfreedaysallocator.NoSuchQueueException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the benefit day queues.
	*
	* @return the benefit day queues
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDayQueue> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the benefit day queues.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of benefit day queues
	* @param end the upper bound of the range of benefit day queues (not inclusive)
	* @return the range of benefit day queues
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDayQueue> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the benefit day queues.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of benefit day queues
	* @param end the upper bound of the range of benefit day queues (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of benefit day queues
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDayQueue> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the benefit day queues where userId = &#63; from the database.
	*
	* @param userId the user ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByUserId(long userId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the benefit day queues where type = &#63; from the database.
	*
	* @param type the type
	* @throws SystemException if a system exception occurred
	*/
	public void removeByType(java.lang.String type)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the benefit day queues where userId = &#63; and queued = &#63; from the database.
	*
	* @param userId the user ID
	* @param queued the queued
	* @throws SystemException if a system exception occurred
	*/
	public void removeByUserId_Queued(long userId, boolean queued)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the benefit day queues where userId = &#63; and allocatedDate = &#63; from the database.
	*
	* @param userId the user ID
	* @param allocatedDate the allocated date
	* @throws SystemException if a system exception occurred
	*/
	public void removeByUserId_Allocated(long userId,
		java.util.Date allocatedDate)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the benefit day queues where userId = &#63; and type = &#63; from the database.
	*
	* @param userId the user ID
	* @param type the type
	* @throws SystemException if a system exception occurred
	*/
	public void removeByUserId_Type(long userId, java.lang.String type)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the benefit day queues from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of benefit day queues where userId = &#63;.
	*
	* @param userId the user ID
	* @return the number of matching benefit day queues
	* @throws SystemException if a system exception occurred
	*/
	public int countByUserId(long userId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of benefit day queues where type = &#63;.
	*
	* @param type the type
	* @return the number of matching benefit day queues
	* @throws SystemException if a system exception occurred
	*/
	public int countByType(java.lang.String type)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of benefit day queues where userId = &#63; and queued = &#63;.
	*
	* @param userId the user ID
	* @param queued the queued
	* @return the number of matching benefit day queues
	* @throws SystemException if a system exception occurred
	*/
	public int countByUserId_Queued(long userId, boolean queued)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of benefit day queues where userId = &#63; and allocatedDate = &#63;.
	*
	* @param userId the user ID
	* @param allocatedDate the allocated date
	* @return the number of matching benefit day queues
	* @throws SystemException if a system exception occurred
	*/
	public int countByUserId_Allocated(long userId, java.util.Date allocatedDate)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of benefit day queues where userId = &#63; and type = &#63;.
	*
	* @param userId the user ID
	* @param type the type
	* @return the number of matching benefit day queues
	* @throws SystemException if a system exception occurred
	*/
	public int countByUserId_Type(long userId, java.lang.String type)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of benefit day queues.
	*
	* @return the number of benefit day queues
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}