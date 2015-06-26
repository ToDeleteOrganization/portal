package com.evozon.evoportal.my_account.managers;

import java.util.Calendar;
import java.util.Date;

import com.evozon.evoportal.my_account.util.MyAccountConstants;
import com.evozon.evoportal.my_account.util.MyAccountUtil;
import com.evozon.evoportal.my_account.wrapper.UserExpandoWrapper;
import com.evozon.evoportal.vacation.model.DateInterval;
import com.liferay.portal.model.User;

public class UserEmploymentPeriodsHandler {

	private UserExpandoWrapper userExpando;

	private Calendar startDateInternship;

	private Calendar dateHired;

	private Calendar startDateCIM;

	public UserEmploymentPeriodsHandler(UserExpandoWrapper userExpando) {
		if (userExpando == null) {
			throw new NullPointerException("The user expando must be specified.");
		}
		this.userExpando = userExpando;
		init(userExpando);
	}

	public UserEmploymentPeriodsHandler(User user) {
		this(new UserExpandoWrapper(user));
	}

	private void init(UserExpandoWrapper userExpando) {
		startDateInternship = MyAccountUtil.resetToMidnight(userExpando.getInternshipStartDate());
		dateHired = MyAccountUtil.resetToMidnight(userExpando.getDateHired());
		startDateCIM = MyAccountUtil.resetToMidnight(userExpando.getCIMStartDate());
	}


	public Calendar getInternshipStartDate() {
		return startDateInternship;
	}

	public Calendar getStartDate() {
		return dateHired;
	}

	public Calendar getStartDateCIM() {
		return startDateCIM;
	}

	public boolean hasInternshipStartDate() {
		return (startDateInternship != null);
	}

	public boolean hasDateHired() {
		return (dateHired != null);
	}

	public boolean hasStartCIMDateHired() {
		return (startDateCIM != null);
	}

	public boolean isCIMStartDateValid() {
		if (startDateCIM == null) {
			return false;
		}

		return (dateHired != null) ? (startDateCIM.compareTo(dateHired) > 0) : true;
	}

	public DateInterval getInternshipInterval() {
		Calendar internshipPeriodStartDate = getCustomFieldDateIfSet(getInternshipStartDate(), MyAccountConstants.INTERNSHIP_START_DATE);
		if (internshipPeriodStartDate == null) {
			return null;
		}

		Calendar internshipPeriodEndDate = getCustomFieldDateIfSet(getStartDate(), MyAccountConstants.DATE_HIRED_EXPANDO_ATR);
		if (internshipPeriodEndDate == null) {
			// if 'start Date isn't set(shouldn't happen), take the cim start date
			internshipPeriodEndDate = getCustomFieldDateIfSet(getStartDateCIM(), MyAccountConstants.CIM_START_DATE);
		}

		return new DateInterval(internshipPeriodStartDate, internshipPeriodEndDate);
	}

	public DateInterval getScholarshipInterval() {
		Calendar scholarshipPeriodStartDate = getCustomFieldDateIfSet(getStartDate(), MyAccountConstants.DATE_HIRED_EXPANDO_ATR);
		if (scholarshipPeriodStartDate == null) {
			return null;
		}
		Calendar scholarshipPeriodEndDate = getCustomFieldDateIfSet(getStartDateCIM(), MyAccountConstants.CIM_START_DATE);

		return new DateInterval(scholarshipPeriodStartDate, scholarshipPeriodEndDate);
	}

	public DateInterval getCIMInterval() {
		Calendar cimStartDate = getCustomFieldDateIfSet(getStartDateCIM(), MyAccountConstants.CIM_START_DATE);
		if (cimStartDate == null) {
			return null;
		}

		Calendar scholarshipPeriodEndDate = MyAccountUtil.resetToMidnight(userExpando.getCurrentStatusChangeDate());

		return new DateInterval(cimStartDate, scholarshipPeriodEndDate);
	}

	private Calendar getCustomFieldDateIfSet(Calendar calendar, String customFieldName) {
		Calendar customFieldDate = null;
		try {
			Date defaultValue = (Date) MyAccountUtil.getDefaultUserCustomFieldValue(userExpando.getUser().getCompanyId(), customFieldName);
			customFieldDate = !MyAccountUtil.isSameDate(defaultValue, calendar.getTime()) ? calendar : null;
		} catch (Exception e) {
		}
		return customFieldDate;
	}

}
