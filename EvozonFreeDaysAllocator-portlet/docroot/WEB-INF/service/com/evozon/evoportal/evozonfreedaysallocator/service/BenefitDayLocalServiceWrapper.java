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

package com.evozon.evoportal.evozonfreedaysallocator.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link BenefitDayLocalService}.
 * </p>
 *
 * @author    Cristina Kiss
 * @see       BenefitDayLocalService
 * @generated
 */
public class BenefitDayLocalServiceWrapper implements BenefitDayLocalService,
	ServiceWrapper<BenefitDayLocalService> {
	public BenefitDayLocalServiceWrapper(
		BenefitDayLocalService benefitDayLocalService) {
		_benefitDayLocalService = benefitDayLocalService;
	}

	/**
	* Adds the benefit day to the database. Also notifies the appropriate model listeners.
	*
	* @param benefitDay the benefit day
	* @return the benefit day that was added
	* @throws SystemException if a system exception occurred
	*/
	public com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDay addBenefitDay(
		com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDay benefitDay)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _benefitDayLocalService.addBenefitDay(benefitDay);
	}

	/**
	* Creates a new benefit day with the primary key. Does not add the benefit day to the database.
	*
	* @param entryId the primary key for the new benefit day
	* @return the new benefit day
	*/
	public com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDay createBenefitDay(
		long entryId) {
		return _benefitDayLocalService.createBenefitDay(entryId);
	}

	/**
	* Deletes the benefit day with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param entryId the primary key of the benefit day
	* @return the benefit day that was removed
	* @throws PortalException if a benefit day with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDay deleteBenefitDay(
		long entryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _benefitDayLocalService.deleteBenefitDay(entryId);
	}

	/**
	* Deletes the benefit day from the database. Also notifies the appropriate model listeners.
	*
	* @param benefitDay the benefit day
	* @return the benefit day that was removed
	* @throws SystemException if a system exception occurred
	*/
	public com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDay deleteBenefitDay(
		com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDay benefitDay)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _benefitDayLocalService.deleteBenefitDay(benefitDay);
	}

	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _benefitDayLocalService.dynamicQuery();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _benefitDayLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return _benefitDayLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _benefitDayLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _benefitDayLocalService.dynamicQueryCount(dynamicQuery);
	}

	public com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDay fetchBenefitDay(
		long entryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _benefitDayLocalService.fetchBenefitDay(entryId);
	}

	/**
	* Returns the benefit day with the primary key.
	*
	* @param entryId the primary key of the benefit day
	* @return the benefit day
	* @throws PortalException if a benefit day with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDay getBenefitDay(
		long entryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _benefitDayLocalService.getBenefitDay(entryId);
	}

	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _benefitDayLocalService.getPersistedModel(primaryKeyObj);
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
	public java.util.List<com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDay> getBenefitDaies(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _benefitDayLocalService.getBenefitDaies(start, end);
	}

	/**
	* Returns the number of benefit daies.
	*
	* @return the number of benefit daies
	* @throws SystemException if a system exception occurred
	*/
	public int getBenefitDaiesCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _benefitDayLocalService.getBenefitDaiesCount();
	}

	/**
	* Updates the benefit day in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param benefitDay the benefit day
	* @return the benefit day that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDay updateBenefitDay(
		com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDay benefitDay)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _benefitDayLocalService.updateBenefitDay(benefitDay);
	}

	/**
	* Updates the benefit day in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param benefitDay the benefit day
	* @param merge whether to merge the benefit day with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the benefit day that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDay updateBenefitDay(
		com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDay benefitDay,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _benefitDayLocalService.updateBenefitDay(benefitDay, merge);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _benefitDayLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_benefitDayLocalService.setBeanIdentifier(beanIdentifier);
	}

	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _benefitDayLocalService.invokeMethod(name, parameterTypes,
			arguments);
	}

	public long saveBenefitDay(long userId, java.lang.String type, int daysNo) {
		return _benefitDayLocalService.saveBenefitDay(userId, type, daysNo);
	}

	public void addExtraValue(long userId, java.lang.String type, int bonus) {
		_benefitDayLocalService.addExtraValue(userId, type, bonus);
	}

	public long saveExtraDay(long userId, java.lang.String type, int daysNo,
		java.lang.String comment) {
		return _benefitDayLocalService.saveExtraDay(userId, type, daysNo,
			comment);
	}

	public java.util.List<com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDay> getBenefitDaysByTypeInYear(
		int year, long userId, java.lang.String benefitDayType) {
		return _benefitDayLocalService.getBenefitDaysByTypeInYear(year, userId,
			benefitDayType);
	}

	public int getTotalExtraDaysReceivedInYear(int year, long userId) {
		return _benefitDayLocalService.getTotalExtraDaysReceivedInYear(year,
			userId);
	}

	public int getTotalExtraDaysInCurrentYear(long userId) {
		return _benefitDayLocalService.getTotalExtraDaysInCurrentYear(userId);
	}

	public java.util.List<com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDay> getBenefitDays(
		long userId) throws com.liferay.portal.kernel.exception.SystemException {
		return _benefitDayLocalService.getBenefitDays(userId);
	}

	public java.util.List<com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDay> getBenefitDayOfType(
		long userId, java.lang.String type)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _benefitDayLocalService.getBenefitDayOfType(userId, type);
	}

	public java.util.Calendar getUserSelectedDate() {
		return _benefitDayLocalService.getUserSelectedDate();
	}

	public boolean hasSimulateMode() {
		return _benefitDayLocalService.hasSimulateMode();
	}

	public java.lang.String getProperty(java.lang.String key,
		java.lang.String defaultValue) {
		return _benefitDayLocalService.getProperty(key, defaultValue);
	}

	public void blankMethod() {
		_benefitDayLocalService.blankMethod();
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public BenefitDayLocalService getWrappedBenefitDayLocalService() {
		return _benefitDayLocalService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedBenefitDayLocalService(
		BenefitDayLocalService benefitDayLocalService) {
		_benefitDayLocalService = benefitDayLocalService;
	}

	public BenefitDayLocalService getWrappedService() {
		return _benefitDayLocalService;
	}

	public void setWrappedService(BenefitDayLocalService benefitDayLocalService) {
		_benefitDayLocalService = benefitDayLocalService;
	}

	private BenefitDayLocalService _benefitDayLocalService;
}