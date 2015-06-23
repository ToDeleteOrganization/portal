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

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.InvokableLocalService;

/**
 * The utility for the benefit day queue local service. This utility wraps {@link com.evozon.evoportal.evozonfreedaysallocator.service.impl.BenefitDayQueueLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Cristina Kiss
 * @see BenefitDayQueueLocalService
 * @see com.evozon.evoportal.evozonfreedaysallocator.service.base.BenefitDayQueueLocalServiceBaseImpl
 * @see com.evozon.evoportal.evozonfreedaysallocator.service.impl.BenefitDayQueueLocalServiceImpl
 * @generated
 */
public class BenefitDayQueueLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.evozon.evoportal.evozonfreedaysallocator.service.impl.BenefitDayQueueLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the benefit day queue to the database. Also notifies the appropriate model listeners.
	*
	* @param benefitDayQueue the benefit day queue
	* @return the benefit day queue that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDayQueue addBenefitDayQueue(
		com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDayQueue benefitDayQueue)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().addBenefitDayQueue(benefitDayQueue);
	}

	/**
	* Creates a new benefit day queue with the primary key. Does not add the benefit day queue to the database.
	*
	* @param entryId the primary key for the new benefit day queue
	* @return the new benefit day queue
	*/
	public static com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDayQueue createBenefitDayQueue(
		long entryId) {
		return getService().createBenefitDayQueue(entryId);
	}

	/**
	* Deletes the benefit day queue with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param entryId the primary key of the benefit day queue
	* @return the benefit day queue that was removed
	* @throws PortalException if a benefit day queue with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDayQueue deleteBenefitDayQueue(
		long entryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteBenefitDayQueue(entryId);
	}

	/**
	* Deletes the benefit day queue from the database. Also notifies the appropriate model listeners.
	*
	* @param benefitDayQueue the benefit day queue
	* @return the benefit day queue that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDayQueue deleteBenefitDayQueue(
		com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDayQueue benefitDayQueue)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteBenefitDayQueue(benefitDayQueue);
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public static java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQuery(dynamicQuery);
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
	public static java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQuery(dynamicQuery, start, end);
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
	public static java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .dynamicQuery(dynamicQuery, start, end, orderByComparator);
	}

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQueryCount(dynamicQuery);
	}

	public static com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDayQueue fetchBenefitDayQueue(
		long entryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().fetchBenefitDayQueue(entryId);
	}

	/**
	* Returns the benefit day queue with the primary key.
	*
	* @param entryId the primary key of the benefit day queue
	* @return the benefit day queue
	* @throws PortalException if a benefit day queue with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDayQueue getBenefitDayQueue(
		long entryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getBenefitDayQueue(entryId);
	}

	public static com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getPersistedModel(primaryKeyObj);
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
	public static java.util.List<com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDayQueue> getBenefitDayQueues(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getBenefitDayQueues(start, end);
	}

	/**
	* Returns the number of benefit day queues.
	*
	* @return the number of benefit day queues
	* @throws SystemException if a system exception occurred
	*/
	public static int getBenefitDayQueuesCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getBenefitDayQueuesCount();
	}

	/**
	* Updates the benefit day queue in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param benefitDayQueue the benefit day queue
	* @return the benefit day queue that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDayQueue updateBenefitDayQueue(
		com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDayQueue benefitDayQueue)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateBenefitDayQueue(benefitDayQueue);
	}

	/**
	* Updates the benefit day queue in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param benefitDayQueue the benefit day queue
	* @param merge whether to merge the benefit day queue with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the benefit day queue that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDayQueue updateBenefitDayQueue(
		com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDayQueue benefitDayQueue,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateBenefitDayQueue(benefitDayQueue, merge);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public static java.lang.String getBeanIdentifier() {
		return getService().getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public static void setBeanIdentifier(java.lang.String beanIdentifier) {
		getService().setBeanIdentifier(beanIdentifier);
	}

	public static java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return getService().invokeMethod(name, parameterTypes, arguments);
	}

	public static java.util.List<com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDayQueue> getBenefitDaysQueued(
		long userId) throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getBenefitDaysQueued(userId);
	}

	public static void saveBenefitDayQueue(long userId, java.lang.String type,
		int daysNo) {
		getService().saveBenefitDayQueue(userId, type, daysNo);
	}

	public static void saveBenefitDayQueue(long userId, java.lang.String type,
		int daysNo, java.util.Date allocatedDate) {
		getService().saveBenefitDayQueue(userId, type, daysNo, allocatedDate);
	}

	public static java.util.List<com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDayQueue> findBenefitDays(
		boolean allocatedDays) {
		return getService().findBenefitDays(allocatedDays);
	}

	public static java.util.List<com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDayQueue> findBenefitDaysAllocatedThisYear() {
		return getService().findBenefitDaysAllocatedThisYear();
	}

	public static java.util.List<com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDayQueue> findBenefitDaysAllocatedInYearAndMonth(
		int month, int year) {
		return getService().findBenefitDaysAllocatedInYearAndMonth(month, year);
	}

	/**
	* Marks as allocated the input benefit day in queue. Attention! Benefit
	* days from queue are not deleted from DB, they are marked as allocated by
	* setting "queued" to false and the allocated date. <br/>
	* Allocated date represent the date when the benefit day was "removed" from
	* the queue, i.e. queued set to false, as this information will be needed
	* in the future.
	*/
	public static void allocateBenefitDayFromQueue(
		com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDayQueue day) {
		getService().allocateBenefitDayFromQueue(day);
	}

	public static void clearService() {
		_service = null;
	}

	public static BenefitDayQueueLocalService getService() {
		if (_service == null) {
			InvokableLocalService invokableLocalService = (InvokableLocalService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					BenefitDayQueueLocalService.class.getName());

			if (invokableLocalService instanceof BenefitDayQueueLocalService) {
				_service = (BenefitDayQueueLocalService)invokableLocalService;
			}
			else {
				_service = new BenefitDayQueueLocalServiceClp(invokableLocalService);
			}

			ReferenceRegistry.registerReference(BenefitDayQueueLocalServiceUtil.class,
				"_service");
		}

		return _service;
	}

	/**
	 * @deprecated
	 */
	public void setService(BenefitDayQueueLocalService service) {
	}

	private static BenefitDayQueueLocalService _service;
}