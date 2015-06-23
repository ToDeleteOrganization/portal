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

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the benefit day service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Cristina Kiss
 * @see BenefitDayPersistenceImpl
 * @see BenefitDayUtil
 * @generated
 */
public interface BenefitDayPersistence extends BasePersistence<BenefitDay> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link BenefitDayUtil} to access the benefit day persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the benefit day in the entity cache if it is enabled.
	*
	* @param benefitDay the benefit day
	*/
	public void cacheResult(
		com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDay benefitDay);

	/**
	* Caches the benefit daies in the entity cache if it is enabled.
	*
	* @param benefitDaies the benefit daies
	*/
	public void cacheResult(
		java.util.List<com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDay> benefitDaies);

	/**
	* Creates a new benefit day with the primary key. Does not add the benefit day to the database.
	*
	* @param entryId the primary key for the new benefit day
	* @return the new benefit day
	*/
	public com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDay create(
		long entryId);

	/**
	* Removes the benefit day with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param entryId the primary key of the benefit day
	* @return the benefit day that was removed
	* @throws com.evozon.evoportal.evozonfreedaysallocator.NoSuchBenefitDayException if a benefit day with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDay remove(
		long entryId)
		throws com.evozon.evoportal.evozonfreedaysallocator.NoSuchBenefitDayException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDay updateImpl(
		com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDay benefitDay,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the benefit day with the primary key or throws a {@link com.evozon.evoportal.evozonfreedaysallocator.NoSuchBenefitDayException} if it could not be found.
	*
	* @param entryId the primary key of the benefit day
	* @return the benefit day
	* @throws com.evozon.evoportal.evozonfreedaysallocator.NoSuchBenefitDayException if a benefit day with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDay findByPrimaryKey(
		long entryId)
		throws com.evozon.evoportal.evozonfreedaysallocator.NoSuchBenefitDayException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the benefit day with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param entryId the primary key of the benefit day
	* @return the benefit day, or <code>null</code> if a benefit day with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDay fetchByPrimaryKey(
		long entryId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the benefit daies where userId = &#63;.
	*
	* @param userId the user ID
	* @return the matching benefit daies
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDay> findByUserId(
		long userId) throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDay> findByUserId(
		long userId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDay> findByUserId(
		long userId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first benefit day in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching benefit day
	* @throws com.evozon.evoportal.evozonfreedaysallocator.NoSuchBenefitDayException if a matching benefit day could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDay findByUserId_First(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.evozon.evoportal.evozonfreedaysallocator.NoSuchBenefitDayException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first benefit day in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching benefit day, or <code>null</code> if a matching benefit day could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDay fetchByUserId_First(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last benefit day in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching benefit day
	* @throws com.evozon.evoportal.evozonfreedaysallocator.NoSuchBenefitDayException if a matching benefit day could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDay findByUserId_Last(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.evozon.evoportal.evozonfreedaysallocator.NoSuchBenefitDayException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last benefit day in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching benefit day, or <code>null</code> if a matching benefit day could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDay fetchByUserId_Last(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDay[] findByUserId_PrevAndNext(
		long entryId, long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.evozon.evoportal.evozonfreedaysallocator.NoSuchBenefitDayException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the benefit daies where type = &#63;.
	*
	* @param type the type
	* @return the matching benefit daies
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDay> findByType(
		java.lang.String type)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDay> findByType(
		java.lang.String type, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDay> findByType(
		java.lang.String type, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first benefit day in the ordered set where type = &#63;.
	*
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching benefit day
	* @throws com.evozon.evoportal.evozonfreedaysallocator.NoSuchBenefitDayException if a matching benefit day could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDay findByType_First(
		java.lang.String type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.evozon.evoportal.evozonfreedaysallocator.NoSuchBenefitDayException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first benefit day in the ordered set where type = &#63;.
	*
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching benefit day, or <code>null</code> if a matching benefit day could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDay fetchByType_First(
		java.lang.String type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last benefit day in the ordered set where type = &#63;.
	*
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching benefit day
	* @throws com.evozon.evoportal.evozonfreedaysallocator.NoSuchBenefitDayException if a matching benefit day could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDay findByType_Last(
		java.lang.String type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.evozon.evoportal.evozonfreedaysallocator.NoSuchBenefitDayException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last benefit day in the ordered set where type = &#63;.
	*
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching benefit day, or <code>null</code> if a matching benefit day could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDay fetchByType_Last(
		java.lang.String type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDay[] findByType_PrevAndNext(
		long entryId, java.lang.String type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.evozon.evoportal.evozonfreedaysallocator.NoSuchBenefitDayException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the benefit daies where userId = &#63; and type = &#63;.
	*
	* @param userId the user ID
	* @param type the type
	* @return the matching benefit daies
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDay> findByUserId_Type(
		long userId, java.lang.String type)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDay> findByUserId_Type(
		long userId, java.lang.String type, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDay> findByUserId_Type(
		long userId, java.lang.String type, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDay findByUserId_Type_First(
		long userId, java.lang.String type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.evozon.evoportal.evozonfreedaysallocator.NoSuchBenefitDayException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first benefit day in the ordered set where userId = &#63; and type = &#63;.
	*
	* @param userId the user ID
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching benefit day, or <code>null</code> if a matching benefit day could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDay fetchByUserId_Type_First(
		long userId, java.lang.String type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDay findByUserId_Type_Last(
		long userId, java.lang.String type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.evozon.evoportal.evozonfreedaysallocator.NoSuchBenefitDayException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last benefit day in the ordered set where userId = &#63; and type = &#63;.
	*
	* @param userId the user ID
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching benefit day, or <code>null</code> if a matching benefit day could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDay fetchByUserId_Type_Last(
		long userId, java.lang.String type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDay[] findByUserId_Type_PrevAndNext(
		long entryId, long userId, java.lang.String type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.evozon.evoportal.evozonfreedaysallocator.NoSuchBenefitDayException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the benefit daies.
	*
	* @return the benefit daies
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDay> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDay> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDay> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the benefit daies where userId = &#63; from the database.
	*
	* @param userId the user ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByUserId(long userId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the benefit daies where type = &#63; from the database.
	*
	* @param type the type
	* @throws SystemException if a system exception occurred
	*/
	public void removeByType(java.lang.String type)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the benefit daies where userId = &#63; and type = &#63; from the database.
	*
	* @param userId the user ID
	* @param type the type
	* @throws SystemException if a system exception occurred
	*/
	public void removeByUserId_Type(long userId, java.lang.String type)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the benefit daies from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of benefit daies where userId = &#63;.
	*
	* @param userId the user ID
	* @return the number of matching benefit daies
	* @throws SystemException if a system exception occurred
	*/
	public int countByUserId(long userId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of benefit daies where type = &#63;.
	*
	* @param type the type
	* @return the number of matching benefit daies
	* @throws SystemException if a system exception occurred
	*/
	public int countByType(java.lang.String type)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of benefit daies where userId = &#63; and type = &#63;.
	*
	* @param userId the user ID
	* @param type the type
	* @return the number of matching benefit daies
	* @throws SystemException if a system exception occurred
	*/
	public int countByUserId_Type(long userId, java.lang.String type)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of benefit daies.
	*
	* @return the number of benefit daies
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}