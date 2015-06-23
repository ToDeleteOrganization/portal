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

package com.evozon.evoportal.evozonfreedaysallocator.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDayQueue;
import com.evozon.evoportal.evozonfreedaysallocator.service.BenefitDayLocalServiceUtil;
import com.evozon.evoportal.evozonfreedaysallocator.service.base.BenefitDayQueueLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.SystemException;

/**
 * The implementation of the benefit day queue local service.
 * 
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * {@link com.evozon.evoportal.evozonfreedaysallocator.service.BenefitDayQueueLocalService}
 * interface.
 * 
 * <p>
 * This is a local service. Methods of this service will not have security
 * checks based on the propagated JAAS credentials because this service can only
 * be accessed from within the same VM.
 * </p>
 * 
 * @author Cristina Kiss
 * @see com.evozon.evoportal.evozonfreedaysallocator.service.base.BenefitDayQueueLocalServiceBaseImpl
 * @see com.evozon.evoportal.evozonfreedaysallocator.service.BenefitDayQueueLocalServiceUtil
 */
public class BenefitDayQueueLocalServiceImpl extends BenefitDayQueueLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 * 
	 * Never reference this interface directly. Always use {@link
	 * com.evozon.evoportal
	 * .evozonfreedaysallocator.service.BenefitDayQueueLocalServiceUtil} to
	 * access the benefit day queue local service.
	 */

	private static final Logger log = Logger.getLogger(BenefitDayQueueLocalServiceImpl.class);

	public List<BenefitDayQueue> getBenefitDaysQueued(long userId) throws SystemException {
		return benefitDayQueuePersistence.findByUserId_Queued(userId, true);
	}

	public void saveBenefitDayQueue(long userId, String type, int daysNo) {
		try {
			BenefitDayQueue bday = createBenefitDayQueue(counterLocalService.increment(BenefitDayQueue.class.getSimpleName()));
			bday.setUserId(userId);
			bday.setType(type);
			bday.setDaysNo(daysNo);
			bday.setQueued(true); // put day in queue
			bday.setAddedDate(BenefitDayLocalServiceUtil.getUserSelectedDate().getTime());
			bday.setAllocatedDate(null); // not allocated because it's in queue

			bday.setNew(true);
			bday.persist();

			log.debug("saveBenefitDayQueue: " + bday);
		} catch (SystemException e) {
			log.error(e.getMessage(), e);
		}
	}

	public void saveBenefitDayQueue(long userId, String type, int daysNo, Date allocatedDate) {
		try {
			BenefitDayQueue bday = createBenefitDayQueue(counterLocalService.increment(BenefitDayQueue.class.getSimpleName()));
			bday.setUserId(userId);
			bday.setType(type);
			bday.setDaysNo(daysNo);
			bday.setQueued(true); // put day in queue
			bday.setAddedDate(allocatedDate);
			bday.setAllocatedDate(null); // not allocated because it's in queue

			bday.setNew(true);
			bday.persist();

			log.debug("saveBenefitDayQueue: " + bday);
		} catch (SystemException e) {
			log.error(e.getMessage(), e);
		}
	}

	public List<BenefitDayQueue> findBenefitDays(boolean allocatedDays) {
		List<BenefitDayQueue> benefitDaysQueue = new ArrayList<BenefitDayQueue>();

		try {
			List<BenefitDayQueue> allBenefitDaysQueue = benefitDayQueuePersistence.findAll();
			for (BenefitDayQueue benefitDayQueue : allBenefitDaysQueue) {
				boolean isQueued = benefitDayQueue.isQueued();

				if (allocatedDays && !isQueued) {
					benefitDaysQueue.add(benefitDayQueue);
				} else if (!allocatedDays && isQueued) {
					benefitDaysQueue.add(benefitDayQueue);
				}
			}
		} catch (SystemException e) {
			log.error(e.getMessage(), e);
		}
		return benefitDaysQueue;
	}

	public List<BenefitDayQueue> findBenefitDaysAllocatedThisYear() {
		List<BenefitDayQueue> benefitDaysQueue = new ArrayList<BenefitDayQueue>();
		Calendar today = Calendar.getInstance();

		for (BenefitDayQueue benefitDay : findBenefitDays(true)) {
			Date allocatedDate = benefitDay.getAllocatedDate();

			Calendar allocatedCal = Calendar.getInstance();
			allocatedCal.setTime(allocatedDate);
			if (today.get(Calendar.YEAR) == allocatedCal.get(Calendar.YEAR)) {
				benefitDaysQueue.add(benefitDay);
			}
		}

		return benefitDaysQueue;
	}

	public List<BenefitDayQueue> findBenefitDaysAllocatedInYearAndMonth(int month, int year) {
		List<BenefitDayQueue> benefitDaysQueue = new ArrayList<BenefitDayQueue>();

		try {
			List<BenefitDayQueue> allBenefitDaysQueue = benefitDayQueuePersistence.findAll();

			for (BenefitDayQueue benefitDayQueue : allBenefitDaysQueue) {
				Calendar allocatedCal = Calendar.getInstance();
				Date addedDate = benefitDayQueue.getAddedDate();
				if (addedDate != null) {
					allocatedCal.setTime(addedDate);

					if ((allocatedCal.get(Calendar.YEAR) == year) && (allocatedCal.get(Calendar.MONTH) == month)) {
						benefitDaysQueue.add(benefitDayQueue);
					}
				}
			}
		} catch (SystemException e) {
			log.error(e.getMessage(), e);
		}

		return benefitDaysQueue;
	}

	/**
	 * Marks as allocated the input benefit day in queue. Attention! Benefit
	 * days from queue are not deleted from DB, they are marked as allocated by
	 * setting "queued" to false and the allocated date. <br/>
	 * Allocated date represent the date when the benefit day was "removed" from
	 * the queue, i.e. queued set to false, as this information will be needed
	 * in the future.
	 */
	public void allocateBenefitDayFromQueue(BenefitDayQueue day) {
		try {
			day.setQueued(false); // remove from queue
			day.setAllocatedDate(new Date());// allocate it now
			day.persist();
		} catch (SystemException e) {
			log.error(e.getMessage(), e);
		}
	}

}