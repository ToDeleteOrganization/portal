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

import com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDay;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the benefit day service. This utility wraps {@link BenefitDayPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Cristina Kiss
 * @see BenefitDayPersistence
 * @see BenefitDayPersistenceImpl
 * @generated
 */
public class BenefitDayUtil {
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
	public static void clearCache(BenefitDay benefitDay) {
		getPersistence().clearCache(benefitDay);
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
	public static List<BenefitDay> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<BenefitDay> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<BenefitDay> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
	 */
	public static BenefitDay update(BenefitDay benefitDay, boolean merge)
		throws SystemException {
		return getPersistence().update(benefitDay, merge);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
	 */
	public static BenefitDay update(BenefitDay benefitDay, boolean merge,
		ServiceContext serviceContext) throws SystemException {
		return getPersistence().update(benefitDay, merge, serviceContext);
	}

	/**
	* Caches the benefit day in the entity cache if it is enabled.
	*
	* @param benefitDay the benefit day
	*/
	public static void cacheResult(
		com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDay benefitDay) {
		getPersistence().cacheResult(benefitDay);
	}

	/**
	* Caches the benefit daies in the entity cache if it is enabled.
	*
	* @param benefitDaies the benefit daies
	*/
	public static void cacheResult(
		java.util.List<com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDay> benefitDaies) {
		getPersistence().cacheResult(benefitDaies);
	}

	/**
	* Creates a new benefit day with the primary key. Does not add the benefit day to the database.
	*
	* @param entryId the primary key for the new benefit day
	* @return the new benefit day
	*/
	public static com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDay create(
		long entryId) {
		return getPersistence().create(entryId);
	}

	/**
	* Removes the benefit day with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param entryId the primary key of the benefit day
	* @return the benefit day that was removed
	* @throws com.evozon.evoportal.evozonfreedaysallocator.NoSuchBenefitDayException if a benefit day with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDay remove(
		long entryId)
		throws com.evozon.evoportal.evozonfreedaysallocator.NoSuchBenefitDayException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().remove(entryId);
	}

	public static com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDay updateImpl(
		com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDay benefitDay,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(benefitDay, merge);
	}

	/**
	* Returns the benefit day with the primary key or throws a {@link com.evozon.evoportal.evozonfreedaysallocator.NoSuchBenefitDayException} if it could not be found.
	*
	* @param entryId the primary key of the benefit day
	* @return the benefit day
	* @throws com.evozon.evoportal.evozonfreedaysallocator.NoSuchBenefitDayException if a benefit day with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDay findByPrimaryKey(
		long entryId)
		throws com.evozon.evoportal.evozonfreedaysallocator.NoSuchBenefitDayException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByPrimaryKey(entryId);
	}

	/**
	* Returns the benefit day with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param entryId the primary key of the benefit day
	* @return the benefit day, or <code>null</code> if a benefit day with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDay fetchByPrimaryKey(
		long entryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(entryId);
	}

	/**
	* Returns all the benefit daies where userId = &#63;.
	*
	* @param userId the user ID
	* @return the matching benefit daies
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDay> findByUserId(
		long userId) throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUserId(userId);
	}

	/**
	* Returns a range of all the benefit daies where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userId the user ID
	* @param start the lower bound of the range of benefit daies
	* @param end the upper bound of the range of benefit daies (not inclusive)
	* @return the range of matching benefit daies
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDay> findByUserId(
		long userId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUserId(userId, start, end);
	}

	/**
	* Returns an ordered range of all the benefit daies where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userId the user ID
	* @param start the lower bound of the range of benefit daies
	* @param end the upper bound of the range of benefit daies (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching benefit daies
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDay> findByUserId(
		long userId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByUserId(userId, start, end, orderByComparator);
	}

	/**
	* Returns the first benefit day in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching benefit day
	* @throws com.evozon.evoportal.evozonfreedaysallocator.NoSuchBenefitDayException if a matching benefit day could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDay findByUserId_First(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.evozon.evoportal.evozonfreedaysallocator.NoSuchBenefitDayException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUserId_First(userId, orderByComparator);
	}

	/**
	* Returns the first benefit day in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching benefit day, or <code>null</code> if a matching benefit day could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDay fetchByUserId_First(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByUserId_First(userId, orderByComparator);
	}

	/**
	* Returns the last benefit day in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching benefit day
	* @throws com.evozon.evoportal.evozonfreedaysallocator.NoSuchBenefitDayException if a matching benefit day could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDay findByUserId_Last(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.evozon.evoportal.evozonfreedaysallocator.NoSuchBenefitDayException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUserId_Last(userId, orderByComparator);
	}

	/**
	* Returns the last benefit day in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching benefit day, or <code>null</code> if a matching benefit day could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDay fetchByUserId_Last(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByUserId_Last(userId, orderByComparator);
	}

	/**
	* Returns the benefit daies before and after the current benefit day in the ordered set where userId = &#63;.
	*
	* @param entryId the primary key of the current benefit day
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next benefit day
	* @throws com.evozon.evoportal.evozonfreedaysallocator.NoSuchBenefitDayException if a benefit day with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDay[] findByUserId_PrevAndNext(
		long entryId, long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.evozon.evoportal.evozonfreedaysallocator.NoSuchBenefitDayException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByUserId_PrevAndNext(entryId, userId, orderByComparator);
	}

	/**
	* Returns all the benefit daies where type = &#63;.
	*
	* @param type the type
	* @return the matching benefit daies
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDay> findByType(
		java.lang.String type)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByType(type);
	}

	/**
	* Returns a range of all the benefit daies where type = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param type the type
	* @param start the lower bound of the range of benefit daies
	* @param end the upper bound of the range of benefit daies (not inclusive)
	* @return the range of matching benefit daies
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDay> findByType(
		java.lang.String type, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByType(type, start, end);
	}

	/**
	* Returns an ordered range of all the benefit daies where type = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param type the type
	* @param start the lower bound of the range of benefit daies
	* @param end the upper bound of the range of benefit daies (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching benefit daies
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDay> findByType(
		java.lang.String type, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByType(type, start, end, orderByComparator);
	}

	/**
	* Returns the first benefit day in the ordered set where type = &#63;.
	*
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching benefit day
	* @throws com.evozon.evoportal.evozonfreedaysallocator.NoSuchBenefitDayException if a matching benefit day could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDay findByType_First(
		java.lang.String type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.evozon.evoportal.evozonfreedaysallocator.NoSuchBenefitDayException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByType_First(type, orderByComparator);
	}

	/**
	* Returns the first benefit day in the ordered set where type = &#63;.
	*
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching benefit day, or <code>null</code> if a matching benefit day could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDay fetchByType_First(
		java.lang.String type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByType_First(type, orderByComparator);
	}

	/**
	* Returns the last benefit day in the ordered set where type = &#63;.
	*
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching benefit day
	* @throws com.evozon.evoportal.evozonfreedaysallocator.NoSuchBenefitDayException if a matching benefit day could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDay findByType_Last(
		java.lang.String type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.evozon.evoportal.evozonfreedaysallocator.NoSuchBenefitDayException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByType_Last(type, orderByComparator);
	}

	/**
	* Returns the last benefit day in the ordered set where type = &#63;.
	*
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching benefit day, or <code>null</code> if a matching benefit day could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDay fetchByType_Last(
		java.lang.String type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByType_Last(type, orderByComparator);
	}

	/**
	* Returns the benefit daies before and after the current benefit day in the ordered set where type = &#63;.
	*
	* @param entryId the primary key of the current benefit day
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next benefit day
	* @throws com.evozon.evoportal.evozonfreedaysallocator.NoSuchBenefitDayException if a benefit day with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDay[] findByType_PrevAndNext(
		long entryId, java.lang.String type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.evozon.evoportal.evozonfreedaysallocator.NoSuchBenefitDayException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByType_PrevAndNext(entryId, type, orderByComparator);
	}

	/**
	* Returns all the benefit daies where userId = &#63; and type = &#63;.
	*
	* @param userId the user ID
	* @param type the type
	* @return the matching benefit daies
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDay> findByUserId_Type(
		long userId, java.lang.String type)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUserId_Type(userId, type);
	}

	/**
	* Returns a range of all the benefit daies where userId = &#63; and type = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userId the user ID
	* @param type the type
	* @param start the lower bound of the range of benefit daies
	* @param end the upper bound of the range of benefit daies (not inclusive)
	* @return the range of matching benefit daies
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDay> findByUserId_Type(
		long userId, java.lang.String type, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUserId_Type(userId, type, start, end);
	}

	/**
	* Returns an ordered range of all the benefit daies where userId = &#63; and type = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userId the user ID
	* @param type the type
	* @param start the lower bound of the range of benefit daies
	* @param end the upper bound of the range of benefit daies (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching benefit daies
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDay> findByUserId_Type(
		long userId, java.lang.String type, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByUserId_Type(userId, type, start, end,
			orderByComparator);
	}

	/**
	* Returns the first benefit day in the ordered set where userId = &#63; and type = &#63;.
	*
	* @param userId the user ID
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching benefit day
	* @throws com.evozon.evoportal.evozonfreedaysallocator.NoSuchBenefitDayException if a matching benefit day could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDay findByUserId_Type_First(
		long userId, java.lang.String type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.evozon.evoportal.evozonfreedaysallocator.NoSuchBenefitDayException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByUserId_Type_First(userId, type, orderByComparator);
	}

	/**
	* Returns the first benefit day in the ordered set where userId = &#63; and type = &#63;.
	*
	* @param userId the user ID
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching benefit day, or <code>null</code> if a matching benefit day could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDay fetchByUserId_Type_First(
		long userId, java.lang.String type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByUserId_Type_First(userId, type, orderByComparator);
	}

	/**
	* Returns the last benefit day in the ordered set where userId = &#63; and type = &#63;.
	*
	* @param userId the user ID
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching benefit day
	* @throws com.evozon.evoportal.evozonfreedaysallocator.NoSuchBenefitDayException if a matching benefit day could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDay findByUserId_Type_Last(
		long userId, java.lang.String type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.evozon.evoportal.evozonfreedaysallocator.NoSuchBenefitDayException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByUserId_Type_Last(userId, type, orderByComparator);
	}

	/**
	* Returns the last benefit day in the ordered set where userId = &#63; and type = &#63;.
	*
	* @param userId the user ID
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching benefit day, or <code>null</code> if a matching benefit day could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDay fetchByUserId_Type_Last(
		long userId, java.lang.String type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByUserId_Type_Last(userId, type, orderByComparator);
	}

	/**
	* Returns the benefit daies before and after the current benefit day in the ordered set where userId = &#63; and type = &#63;.
	*
	* @param entryId the primary key of the current benefit day
	* @param userId the user ID
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next benefit day
	* @throws com.evozon.evoportal.evozonfreedaysallocator.NoSuchBenefitDayException if a benefit day with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDay[] findByUserId_Type_PrevAndNext(
		long entryId, long userId, java.lang.String type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.evozon.evoportal.evozonfreedaysallocator.NoSuchBenefitDayException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByUserId_Type_PrevAndNext(entryId, userId, type,
			orderByComparator);
	}

	/**
	* Returns all the benefit daies.
	*
	* @return the benefit daies
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDay> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the benefit daies.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of benefit daies
	* @param end the upper bound of the range of benefit daies (not inclusive)
	* @return the range of benefit daies
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDay> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the benefit daies.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of benefit daies
	* @param end the upper bound of the range of benefit daies (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of benefit daies
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDay> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the benefit daies where userId = &#63; from the database.
	*
	* @param userId the user ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByUserId(long userId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByUserId(userId);
	}

	/**
	* Removes all the benefit daies where type = &#63; from the database.
	*
	* @param type the type
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByType(java.lang.String type)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByType(type);
	}

	/**
	* Removes all the benefit daies where userId = &#63; and type = &#63; from the database.
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
	* Removes all the benefit daies from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of benefit daies where userId = &#63;.
	*
	* @param userId the user ID
	* @return the number of matching benefit daies
	* @throws SystemException if a system exception occurred
	*/
	public static int countByUserId(long userId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByUserId(userId);
	}

	/**
	* Returns the number of benefit daies where type = &#63;.
	*
	* @param type the type
	* @return the number of matching benefit daies
	* @throws SystemException if a system exception occurred
	*/
	public static int countByType(java.lang.String type)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByType(type);
	}

	/**
	* Returns the number of benefit daies where userId = &#63; and type = &#63;.
	*
	* @param userId the user ID
	* @param type the type
	* @return the number of matching benefit daies
	* @throws SystemException if a system exception occurred
	*/
	public static int countByUserId_Type(long userId, java.lang.String type)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByUserId_Type(userId, type);
	}

	/**
	* Returns the number of benefit daies.
	*
	* @return the number of benefit daies
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static BenefitDayPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (BenefitDayPersistence)PortletBeanLocatorUtil.locate(com.evozon.evoportal.evozonfreedaysallocator.service.ClpSerializer.getServletContextName(),
					BenefitDayPersistence.class.getName());

			ReferenceRegistry.registerReference(BenefitDayUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	/**
	 * @deprecated
	 */
	public void setPersistence(BenefitDayPersistence persistence) {
	}

	private static BenefitDayPersistence _persistence;
}