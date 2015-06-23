package com.evozon.evoportal.evozonfreedaysallocator;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.log4j.Logger;

import com.evozon.evoportal.evozonfreedaysallocator.model.impl.AllocationShift;
import com.evozon.evoportal.evozonfreedaysallocator.service.BenefitDayLocalServiceUtil;
import com.evozon.evoportal.evozonfreedaysallocator.service.impl.BenefitDayLocalServiceImpl;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.util.portlet.PortletProps;

public final class PropertiesInterpretor {

	private static final Logger log = Logger.getLogger(BenefitDayLocalServiceImpl.class);

	private static final String YEARS = "years";
	
	private static final String MONTHS = "months";
	
	private static final String DAYS = "days";

	private static PropertiesInterpretor propertiesInterpretor = null;

	public static PropertiesInterpretor getInstance() {
		if (propertiesInterpretor == null) {
			propertiesInterpretor = new PropertiesInterpretor();
		}

		return propertiesInterpretor;
	}

	public List<AllocationShift> getShiftedBonusExceptionForUser(long userId) {
		List<AllocationShift> shifts = new ArrayList<AllocationShift>();

		try {
			String allocationShiftStr = BenefitDayLocalServiceUtil.getProperty(String.valueOf(userId), StringPool.BLANK);
			if (!allocationShiftStr.isEmpty()) {
				JSONObject shift = JSONFactoryUtil.createJSONObject(allocationShiftStr);

				AllocationShift yearsShift = formAllocationShift(Calendar.YEAR, shift.getInt(YEARS));
				if (yearsShift != null) {
					shifts.add(yearsShift);
				}

				AllocationShift monthShift = formAllocationShift(Calendar.MONTH, shift.getInt(MONTHS));
				if (monthShift != null) {
					shifts.add(monthShift);
				}

				AllocationShift daysShift = formAllocationShift(Calendar.DAY_OF_MONTH, shift.getInt(DAYS));
				if (daysShift != null) {
					shifts.add(daysShift);
				}
			}
		} catch (NumberFormatException e) {
			log.warn("getShiftedBonusExceptionForUser method error:" + e.getMessage());

		} catch (JSONException e) {
			log.warn("getShiftedBonusExceptionForUser method error:" + e.getMessage());
		}

		return shifts;
	}

	private AllocationShift formAllocationShift(int calendarField, int calendarAmount) {
		AllocationShift shift = null;

		if (calendarAmount != 0) {
			shift = new AllocationShift();
			shift.setCalendarField(calendarField);
			shift.setCalendarAmount(calendarAmount);
		}
		
		return shift;
	}

	private PropertiesInterpretor(){
	}
}
