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

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the benefit day queue service. This utility wraps {@link BenefitDayQueuePersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Cristina Kiss
 * @see BenefitDayQueuePersistence
 * @see BenefitDayQueuePersistenceImpl
 * @generated
 */
public class BenefitDayQueueUtil {
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
	public static void clearCache(BenefitDayQueue benefitDayQueue) {
		getPersistence().clearCache(benefitDayQueue);
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
	public static List<BenefitDayQueue> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<BenefitDayQueue> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<BenefitDayQueue> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
	 */
	public static BenefitDayQueue update(BenefitDayQueue benefitDayQueue,
		boolean merge) throws SystemException {
		return getPersistence().update(benefitDayQueue, merge);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
	 */
	public static BenefitDayQueue update(BenefitDayQueue benefitDayQueue,
		boolean merge, ServiceContext serviceContext) throws SystemException {
		return getPersistence().update(benefitDayQueue, merge, serviceContext);
	}

	/**
	* Caches the benefit day queue in the entity cache if it is enabled.
	*
	* @param benefitDayQueue the benefit day queue
	*/
	public static void cacheResult(
		com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDayQueue benefitDayQueue) {
		getPersistence().cacheResult(benefitDayQueue);
	}

	/**
	* Caches the benefit day queues in the entity cache if it is enabled.
	*
	* @param benefitDayQueues the benefit day queues
	*/
	public static void cacheResult(
		java.util.List<com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDayQueue> benefitDayQueues) {
		getPersistence().cacheResult(benefitDayQueues);
	}

	/**
	* Creates a new benefit day queue with the primary key. Does not add the benefit day queue to the database.
	*
	* @param entryId the primary key for the new benefit day queue
	* @return the new benefit day queue
	*/
	public static com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDayQueue create(
		long entryId) {
		return getPersistence().create(entryId);
	}

	/**
	* Removes the benefit day queue with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param entryId the primary key of the benefit day queue
	* @return the benefit day queue that was removed
	* @throws com.evozon.evoportal.evozonfreedaysallocator.NoSuchQueueException if a benefit day queue with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDayQueue remove(
		long entryId)
		throws com.evozon.evoportal.evozonfreedaysallocator.NoSuchQueueException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().remove(entryId);
	}

	public static com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDayQueue updateImpl(
		com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDayQueue benefitDayQueue,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(benefitDayQueue, merge);
	}

	/**
	* Returns the benefit day queue with the primary key or throws a {@link com.evozon.evoportal.evozonfreedaysallocator.NoSuchQueueException} if it could not be found.
	*
	* @param entryId the primary key of the benefit day queue
	* @return the benefit day queue
	* @throws com.evozon.evoportal.evozonfreedaysallocator.NoSuchQueueException if a benefit day queue with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDayQueue findByPrimaryKey(
		long entryId)
		throws com.evozon.evoportal.evozonfreedaysallocator.NoSuchQueueException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByPrimaryKey(entryId);
	}

	/**
	* Returns the benefit day queue with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param entryId the primary key of the benefit day queue
	* @return the benefit day queue, or <code>null</code> if a benefit day queue with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDayQueue fetchByPrimaryKey(
		long entryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(entryId);
	}

	/**
	* Returns all the benefit day queues where userId = &#63;.
	*
	* @param userId the user ID
	* @return the matching benefit day queues
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDayQueue> findByUserId(
		long userId) throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUserId(userId);
	}

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
	public static java.util.List<com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDayQueue> findByUserId(
		long userId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUserId(userId, start, end);
	}

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
	public static java.util.List<com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDayQueue> findByUserId(
		long userId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByUserId(userId, start, end, orderByComparator);
	}

	/**
	* Returns the first benefit day queue in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching benefit day queue
	* @throws com.evozon.evoportal.evozonfreedaysallocator.NoSuchQueueException if a matching benefit day queue could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDayQueue findByUserId_First(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.evozon.evoportal.evozonfreedaysallocator.NoSuchQueueException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUserId_First(userId, orderByComparator);
	}

	/**
	* Returns the first benefit day queue in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching benefit day queue, or <code>null</code> if a matching benefit day queue could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDayQueue fetchByUserId_First(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByUserId_First(userId, orderByComparator);
	}

	/**
	* Returns the last benefit day queue in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching benefit day queue
	* @throws com.evozon.evoportal.evozonfreedaysallocator.NoSuchQueueException if a matching benefit day queue could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDayQueue findByUserId_Last(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.evozon.evoportal.evozonfreedaysallocator.NoSuchQueueException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUserId_Last(userId, orderByComparator);
	}

	/**
	* Returns the last benefit day queue in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching benefit day queue, or <code>null</code> if a matching benefit day queue could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDayQueue fetchByUserId_Last(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByUserId_Last(userId, orderByComparator);
	}

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
	public static com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDayQueue[] findByUserId_PrevAndNext(
		long entryId, long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.evozon.evoportal.evozonfreedaysallocator.NoSuchQueueException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByUserId_PrevAndNext(entryId, userId, orderByComparator);
	}

	/**
	* Returns all the benefit day queues where type = &#63;.
	*
	* @param type the type
	* @return the matching benefit day queues
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDayQueue> findByType(
		java.lang.String type)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByType(type);
	}

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
	public static java.util.List<com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDayQueue> findByType(
		java.lang.String type, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByType(type, start, end);
	}

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
	public static java.util.List<com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDayQueue> findByType(
		java.lang.String type, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByType(type, start, end, orderByComparator);
	}

	/**
	* Returns the first benefit day queue in the ordered set where type = &#63;.
	*
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching benefit day queue
	* @throws com.evozon.evoportal.evozonfreedaysallocator.NoSuchQueueException if a matching benefit day queue could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDayQueue findByType_First(
		java.lang.String type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.evozon.evoportal.evozonfreedaysallocator.NoSuchQueueException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByType_First(type, orderByComparator);
	}

	/**
	* Returns the first benefit day queue in the ordered set where type = &#63;.
	*
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching benefit day queue, or <code>null</code> if a matching benefit day queue could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDayQueue fetchByType_First(
		java.lang.String type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByType_First(type, orderByComparator);
	}

	/**
	* Returns the last benefit day queue in the ordered set where type = &#63;.
	*
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching benefit day queue
	* @throws com.evozon.evoportal.evozonfreedaysallocator.NoSuchQueueException if a matching benefit day queue could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDayQueue findByType_Last(
		java.lang.String type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.evozon.evoportal.evozonfreedaysallocator.NoSuchQueueException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByType_Last(type, orderByComparator);
	}

	/**
	* Returns the last benefit day queue in the ordered set where type = &#63;.
	*
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching benefit day queue, or <code>null</code> if a matching benefit day queue could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDayQueue fetchByType_Last(
		java.lang.String type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByType_Last(type, orderByComparator);
	}

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
	public static com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDayQueue[] findByType_PrevAndNext(
		long entryId, java.lang.String type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.evozon.evoportal.evozonfreedaysallocator.NoSuchQueueException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByType_PrevAndNext(entryId, type, orderByComparator);
	}

	/**
	* Returns all the benefit day queues where userId = &#63; and queued = &#63;.
	*
	* @param userId the user ID
	* @param queued the queued
	* @return the matching benefit day queues
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDayQueue> findByUserId_Queued(
		long userId, boolean queued)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUserId_Queued(userId, queued);
	}

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
	public static java.util.List<com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDayQueue> findByUserId_Queued(
		long userId, boolean queued, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUserId_Queued(userId, queued, start, end);
	}

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
	public static java.util.List<com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDayQueue> findByUserId_Queued(
		long userId, boolean queued, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByUserId_Queued(userId, queued, start, end,
			orderByComparator);
	}

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
	public static com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDayQueue findByUserId_Queued_First(
		long userId, boolean queued,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.evozon.evoportal.evozonfreedaysallocator.NoSuchQueueException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByUserId_Queued_First(userId, queued, orderByComparator);
	}

	/**
	* Returns the first benefit day queue in the ordered set where userId = &#63; and queued = &#63;.
	*
	* @param userId the user ID
	* @param queued the queued
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching benefit day queue, or <code>null</code> if a matching benefit day queue could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDayQueue fetchByUserId_Queued_First(
		long userId, boolean queued,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByUserId_Queued_First(userId, queued, orderByComparator);
	}

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
	public static com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDayQueue findByUserId_Queued_Last(
		long userId, boolean queued,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.evozon.evoportal.evozonfreedaysallocator.NoSuchQueueException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByUserId_Queued_Last(userId, queued, orderByComparator);
	}

	/**
	* Returns the last benefit day queue in the ordered set where userId = &#63; and queued = &#63;.
	*
	* @param userId the user ID
	* @param queued the queued
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching benefit day queue, or <code>null</code> if a matching benefit day queue could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDayQueue fetchByUserId_Queued_Last(
		long userId, boolean queued,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByUserId_Queued_Last(userId, queued, orderByComparator);
	}

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
	public static com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDayQueue[] findByUserId_Queued_PrevAndNext(
		long entryId, long userId, boolean queued,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.evozon.evoportal.evozonfreedaysallocator.NoSuchQueueException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByUserId_Queued_PrevAndNext(entryId, userId, queued,
			orderByComparator);
	}

	/**
	* Returns all the benefit day queues where userId = &#63; and allocatedDate = &#63;.
	*
	* @param userId the user ID
	* @param allocatedDate the allocated date
	* @return the matching benefit day queues
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDayQueue> findByUserId_Allocated(
		long userId, java.util.Date allocatedDate)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUserId_Allocated(userId, allocatedDate);
	}

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
	public static java.util.List<com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDayQueue> findByUserId_Allocated(
		long userId, java.util.Date allocatedDate, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByUserId_Allocated(userId, allocatedDate, start, end);
	}

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
	public static java.util.List<com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDayQueue> findByUserId_Allocated(
		long userId, java.util.Date allocatedDate, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByUserId_Allocated(userId, allocatedDate, start, end,
			orderByComparator);
	}

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
	public static com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDayQueue findByUserId_Allocated_First(
		long userId, java.util.Date allocatedDate,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.evozon.evoportal.evozonfreedaysallocator.NoSuchQueueException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByUserId_Allocated_First(userId, allocatedDate,
			orderByComparator);
	}

	/**
	* Returns the first benefit day queue in the ordered set where userId = &#63; and allocatedDate = &#63;.
	*
	* @param userId the user ID
	* @param allocatedDate the allocated date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching benefit day queue, or <code>null</code> if a matching benefit day queue could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDayQueue fetchByUserId_Allocated_First(
		long userId, java.util.Date allocatedDate,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByUserId_Allocated_First(userId, allocatedDate,
			orderByComparator);
	}

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
	public static com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDayQueue findByUserId_Allocated_Last(
		long userId, java.util.Date allocatedDate,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.evozon.evoportal.evozonfreedaysallocator.NoSuchQueueException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByUserId_Allocated_Last(userId, allocatedDate,
			orderByComparator);
	}

	/**
	* Returns the last benefit day queue in the ordered set where userId = &#63; and allocatedDate = &#63;.
	*
	* @param userId the user ID
	* @param allocatedDate the allocated date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching benefit day queue, or <code>null</code> if a matching benefit day queue could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDayQueue fetchByUserId_Allocated_Last(
		long userId, java.util.Date allocatedDate,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByUserId_Allocated_Last(userId, allocatedDate,
			orderByComparator);
	}

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
	public static com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDayQueue[] findByUserId_Allocated_PrevAndNext(
		long entryId, long userId, java.util.Date allocatedDate,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.evozon.evoportal.evozonfreedaysallocator.NoSuchQueueException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByUserId_Allocated_PrevAndNext(entryId, userId,
			allocatedDate, orderByComparator);
	}

	/**
	* Returns all the benefit day queues where userId = &#63; and type = &#63;.
	*
	* @param userId the user ID
	* @param type the type
	* @return the matching benefit day queues
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDayQueue> findByUserId_Type(
		long userId, java.lang.String type)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUserId_Type(userId, type);
	}

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
	public static java.util.List<com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDayQueue> findByUserId_Type(
		long userId, java.lang.String type, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUserId_Type(userId, type, start, end);
	}

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
	public static java.util.List<com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDayQueue> findByUserId_Type(
		long userId, java.lang.String type, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByUserId_Type(userId, type, start, end,
			orderByComparator);
	}

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
	public static com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDayQueue findByUserId_Type_First(
		long userId, java.lang.String type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.evozon.evoportal.evozonfreedaysallocator.NoSuchQueueException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByUserId_Type_First(userId, type, orderByComparator);
	}

	/**
	* Returns the first benefit day queue in the ordered set where userId = &#63; and type = &#63;.
	*
	* @param userId the user ID
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching benefit day queue, or <code>null</code> if a matching benefit day queue could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDayQueue fetchByUserId_Type_First(
		long userId, java.lang.String type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByUserId_Type_First(userId, type, orderByComparator);
	}

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
	public static com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDayQueue findByUserId_Type_Last(
		long userId, java.lang.String type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.evozon.evoportal.evozonfreedaysallocator.NoSuchQueueException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByUserId_Type_Last(userId, type, orderByComparator);
	}

	/**
	* Returns the last benefit day queue in the ordered set where userId = &#63; and type = &#63;.
	*
	* @param userId the user ID
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching benefit day queue, or <code>null</code> if a matching benefit day queue could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDayQueue fetchByUserId_Type_Last(
		long userId, java.lang.String type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByUserId_Type_Last(userId, type, orderByComparator);
	}

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
	public static com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDayQueue[] findByUserId_Type_PrevAndNext(
		long entryId, long userId, java.lang.String type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.evozon.evoportal.evozonfreedaysallocator.NoSuchQueueException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByUserId_Type_PrevAndNext(entryId, userId, type,
			orderByComparator);
	}

	/**
	* Returns all the benefit day queues.
	*
	* @return the benefit day queues
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDayQueue> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

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
	public static java.util.List<com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDayQueue> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

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
	public static java.util.List<com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDayQueue> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the benefit day queues where userId = &#63; from the database.
	*
	* @param userId the user ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByUserId(long userId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByUserId(userId);
	}

	/**
	* Removes all the benefit day queues where type = &#63; from the database.
	*
	* @param type the type
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByType(java.lang.String type)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByType(type);
	}

	/**
	* Removes all the benefit day queues where userId = &#63; and queued = &#63; from the database.
	*
	* @param userId the user ID
	* @param queued the queued
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByUserId_Queued(long userId, boolean queued)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByUserId_Queued(userId, queued);
	}

	/**
	* Removes all the benefit day queues where userId = &#63; and allocatedDate = &#63; from the database.
	*
	* @param userId the user ID
	* @param allocatedDate the allocated date
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByUserId_Allocated(long userId,
		java.util.Date allocatedDate)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByUserId_Allocated(userId, allocatedDate);
	}

	/**
	* Removes all the benefit day queues where userId = &#63; and type = &#63; from the database.
	*
	* @param userId the user ID
	* @param type the type
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByUserId_Type(long userId, java.lang.String type)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByUserId_Type(userId, type);
	}

	/**
	* Removes all the benefit day queues from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of benefit day queues where userId = &#63;.
	*
	* @param userId the user ID
	* @return the number of matching benefit day queues
	* @throws SystemException if a system exception occurred
	*/
	public static int countByUserId(long userId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByUserId(userId);
	}

	/**
	* Returns the number of benefit day queues where type = &#63;.
	*
	* @param type the type
	* @return the number of matching benefit day queues
	* @throws SystemException if a system exception occurred
	*/
	public static int countByType(java.lang.String type)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByType(type);
	}

	/**
	* Returns the number of benefit day queues where userId = &#63; and queued = &#63;.
	*
	* @param userId the user ID
	* @param queued the queued
	* @return the number of matching benefit day queues
	* @throws SystemException if a system exception occurred
	*/
	public static int countByUserId_Queued(long userId, boolean queued)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByUserId_Queued(userId, queued);
	}

	/**
	* Returns the number of benefit day queues where userId = &#63; and allocatedDate = &#63;.
	*
	* @param userId the user ID
	* @param allocatedDate the allocated date
	* @return the number of matching benefit day queues
	* @throws SystemException if a system exception occurred
	*/
	public static int countByUserId_Allocated(long userId,
		java.util.Date allocatedDate)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByUserId_Allocated(userId, allocatedDate);
	}

	/**
	* Returns the number of benefit day queues where userId = &#63; and type = &#63;.
	*
	* @param userId the user ID
	* @param type the type
	* @return the number of matching benefit day queues
	* @throws SystemException if a system exception occurred
	*/
	public static int countByUserId_Type(long userId, java.lang.String type)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByUserId_Type(userId, type);
	}

	/**
	* Returns the number of benefit day queues.
	*
	* @return the number of benefit day queues
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static BenefitDayQueuePersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (BenefitDayQueuePersistence)PortletBeanLocatorUtil.locate(com.evozon.evoportal.evozonfreedaysallocator.service.ClpSerializer.getServletContextName(),
					BenefitDayQueuePersistence.class.getName());

			ReferenceRegistry.registerReference(BenefitDayQueueUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	/**
	 * @deprecated
	 */
	public void setPersistence(BenefitDayQueuePersistence persistence) {
	}

	private static BenefitDayQueuePersistence _persistence;
}