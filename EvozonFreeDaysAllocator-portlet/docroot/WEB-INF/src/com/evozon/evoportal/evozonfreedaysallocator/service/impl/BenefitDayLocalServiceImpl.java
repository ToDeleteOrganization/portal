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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDay;
import com.evozon.evoportal.evozonfreedaysallocator.model.impl.BenefitDayType;
import com.evozon.evoportal.evozonfreedaysallocator.service.base.BenefitDayLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.util.portlet.PortletProps;

/**
 * The implementation of the benefit day local service.
 * 
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * {@link com.evozon.evoportal.evozonfreedaysallocator.service.BenefitDayLocalService}
 * interface.
 * 
 * <p>
 * This is a local service. Methods of this service will not have security
 * checks based on the propagated JAAS credentials because this service can only
 * be accessed from within the same VM.
 * </p>
 * 
 * @author Cristina Kiss
 * @see com.evozon.evoportal.evozonfreedaysallocator.service.base.BenefitDayLocalServiceBaseImpl
 * @see com.evozon.evoportal.evozonfreedaysallocator.service.BenefitDayLocalServiceUtil
 */
public class BenefitDayLocalServiceImpl extends BenefitDayLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 * 
	 * Never reference this interface directly. Always use {@link
	 * com.evozon.evoportal
	 * .evozonfreedaysallocator.service.BenefitDayLocalServiceUtil} to access
	 * the benefit day local service.
	 */

	private static final Logger log = Logger.getLogger(BenefitDayLocalServiceImpl.class);

	private static final String SIMULATED_DATE = "simulated-date";

	private static final String SIMULATE = "simulate";

	public long saveBenefitDay(long userId, String type, int daysNo) {
		long savedId = 0l;
		try {
			// check if user has already some data in DB
			List<BenefitDay> days = getBenefitDayOfType(userId, type);
			boolean isNew = (days == null) || (days.size() == 0);

			BenefitDay bday = null;
			if (isNew) {
				bday = createBenefitDay(counterLocalService.increment(BenefitDay.class.getSimpleName()));
			} else {
				bday = getBenefitDay(days.get(0).getEntryId());
			}
			bday.setUserId(userId);
			bday.setType(type);
			bday.setDaysNo(daysNo);
			bday.setAllocatedDate(getUserSelectedDate().getTime());

			bday.setNew(isNew);
			bday.persist();

			savedId = bday.getEntryId();
			log.debug("saveBenefitDay: " + bday);
		} catch (SystemException e) {
			log.error(e.getMessage(), e);
		} catch (PortalException e) {
			log.error(e.getMessage(), e);
		}
		return savedId;
	}

	public void addExtraValue(long userId, String type, int bonus) {
		try {
			List<BenefitDay> benefitDays = benefitDayPersistence.findByUserId_Type(userId, type);

			if (benefitDays != null && benefitDays.size() > 0) {
				BenefitDay benefitDay = benefitDays.get(0);// 1 entry/benefit
															// type
				int currentValue = benefitDay.getDaysNo();
				benefitDay.setDaysNo(currentValue + bonus);
				benefitDay.persist();
			} else {
				// Save bonus value for user with no DB history!
				saveBenefitDay(userId, type, bonus);
			}
		} catch (SystemException e) {
			log.error(e.getMessage(), e);
		}
	}

	public long saveExtraDay(long userId, java.lang.String type, int daysNo, java.lang.String comment) {
		long savedId = 0l;
		try {

			BenefitDay bday = null;

			bday = createBenefitDay(counterLocalService.increment(BenefitDay.class.getSimpleName()));

			bday.setUserId(userId);
			bday.setType(type);
			bday.setDaysNo(daysNo);
			bday.setAllocatedDate(getUserSelectedDate().getTime());
			bday.setComment(comment);

			bday.setNew(true);
			bday.persist();

			savedId = bday.getEntryId();
			log.debug("saveExtraDay: " + bday);
		} catch (SystemException e) {
			log.error(e.getMessage(), e);
		}
		return savedId;
	}

	public List<BenefitDay> getBenefitDaysByTypeInYear(int year, long userId, String benefitDayType) {
		List<BenefitDay> extraDaysInCurrentYear = new ArrayList<BenefitDay>();
		try {
			List<BenefitDay> benefitDaysType = benefitDayPersistence.findByUserId_Type(userId, benefitDayType);

			for (BenefitDay benefitDay : benefitDaysType) {
				Date dateAllocated = benefitDay.getAllocatedDate();
				if (dateAllocated == null) {
					continue;
				}
				Calendar allocatedDate = Calendar.getInstance();
				allocatedDate.setTime(benefitDay.getAllocatedDate());

				if (allocatedDate.get(Calendar.YEAR) == year) {
					extraDaysInCurrentYear.add(benefitDay);
				}
			}
		} catch (SystemException e) {
			log.error(e);
		}

		return extraDaysInCurrentYear;
	}

	public int getTotalExtraDaysReceivedInYear(int year, long userId) {
		int totalExtraDays = 0;

		List<BenefitDay> extraDays = getBenefitDaysByTypeInYear(year, userId, BenefitDayType.EXTRA_DAYS.name());
		for (BenefitDay bDay : extraDays) {
			totalExtraDays += bDay.getDaysNo();
		}

		return totalExtraDays;
	}

	public int getTotalExtraDaysInCurrentYear(long userId) {
		return getTotalExtraDaysReceivedInYear(Calendar.getInstance().get(Calendar.YEAR), userId);
	}

	public List<BenefitDay> getBenefitDays(long userId) throws SystemException {
		return benefitDayPersistence.findByUserId(userId);
	}

	public List<BenefitDay> getBenefitDayOfType(long userId, String type) throws SystemException {
		return benefitDayPersistence.findByUserId_Type(userId, type);
	}

	public Calendar getUserSelectedDate() {
		Calendar cal = Calendar.getInstance();

		if (!hasSimulateMode()) {
			return cal;
		}

		try {
			String dateStr = getProperty(SIMULATED_DATE, StringPool.BLANK);

			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			Date parse = sdf.parse(dateStr);
			cal.setTime(parse);
		} catch (Exception e) {
			log.error(e);
		}
		return cal;
	}

	public boolean hasSimulateMode() {
		boolean isSimulation = false;
		try {
			String simultionStr = getProperty(SIMULATE, Boolean.FALSE.toString());
			isSimulation = Boolean.valueOf(simultionStr);
		} catch (Exception e) {
			if (isSimulation) {
				isSimulation = false;
			}
		}
		return isSimulation;
	}

	public String getProperty(String key, String defaultValue) {
		String value = PortletProps.get(key);

		if ((value == null) && (defaultValue != null)) {
			value = defaultValue;
		}

		return value;
	}

	public void blankMethod() {
	}

}