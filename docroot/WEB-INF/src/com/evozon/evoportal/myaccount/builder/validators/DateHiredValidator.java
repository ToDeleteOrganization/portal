package com.evozon.evoportal.myaccount.builder.validators;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.evozon.evoportal.evozonnationalfreedaystableaccess.slayer.model.NationalFreeDaysEntry;
import com.evozon.evoportal.evozonnationalfreedaystableaccess.slayer.service.NationalFreeDaysEntryLocalServiceUtil;
import com.evozon.evoportal.my_account.EditUserSaveButtonCustomAction;
import com.evozon.evoportal.my_account.util.MyAccountUtil;
import com.evozon.evoportal.myaccount.builder.ActionValidationResult;
import com.evozon.evoportal.myaccount.builder.ValidationResult;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

public class DateHiredValidator extends AbstractValidator {

	private static final String INVALID_DATE_HIRED_ERROR_KEY = "invalid-date-hired-error-key";

	private static final String INVALID_DATE_HIRED_MESSAGE = "invalidDateHired";

	private static Log logger = LogFactoryUtil.getLog(EditUserSaveButtonCustomAction.class);

	private final Date dateHired;

	public DateHiredValidator(final Date dateHired) {
		this.dateHired = dateHired;
	}

	public ValidationResult validate() {
		final ActionValidationResult res = new ActionValidationResult();
		final boolean isDateHiredInWeekendOrFreeDays = isDateHiredInWeekendOrFreeDays();

		if (isDateHiredInWeekendOrFreeDays) {
			res.addMessage(buildValidationMessage(INVALID_DATE_HIRED_MESSAGE, convertToCalendar(dateHired)));
			res.addError(buildValidationMessage(INVALID_DATE_HIRED_ERROR_KEY, INVALID_DATE_HIRED_ERROR_KEY));
		}

		return res;
	}

	private boolean isDateHiredInWeekendOrFreeDays() {
		final Calendar dateHiredCal = convertToCalendar(dateHired);

		boolean isDateHiredInvalid = false;
		if (isWeekendDay(dateHiredCal)) {
			isDateHiredInvalid = true;

			if (logger.isDebugEnabled()) {
				logger.debug(this.dateHired + " is in a weekend day.");
			}
		} else if (isNationalFreeDay(dateHiredCal)) {
			isDateHiredInvalid = true;

			if (logger.isDebugEnabled()) {
				logger.debug(this.dateHired + " is in a national free day.");
			}
		}

		return isDateHiredInvalid;
	}

	private boolean isWeekendDay(Calendar cal) {
		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);

		boolean isSaturday = (dayOfWeek == Calendar.SATURDAY);
		boolean isSunday = (dayOfWeek == Calendar.SUNDAY);

		return isSaturday || isSunday;
	}

	private boolean isNationalFreeDay(Calendar hiredDate) {
		try {
			List<NationalFreeDaysEntry> nationalFreeDaysEntries = NationalFreeDaysEntryLocalServiceUtil.getNationalFreeDaysEntries(QueryUtil.ALL_POS, QueryUtil.ALL_POS);

			for (NationalFreeDaysEntry freeDay : nationalFreeDaysEntries) {
				Calendar freeDayCal = MyAccountUtil.getCalendarFromDate(freeDay.getDate());

				boolean isNationalFreeDay = MyAccountUtil.isSameDayAndMonth(hiredDate, freeDayCal);

				if (!isNationalFreeDay) {
					continue;
				}

				int availableUntil = freeDay.getAvailableUntil();
				if ((availableUntil == -1) || (freeDay.getAvailableFrom() == availableUntil)) {
					// if this is a free day only in a past year, check the
					// year
					isNationalFreeDay &= (hiredDate.get(Calendar.YEAR) == freeDayCal.get(Calendar.YEAR));
				} else if (availableUntil == 0) {
					// if this a free day through all years, check the year it
					// was introduced as a free day
					isNationalFreeDay &= (hiredDate.get(Calendar.YEAR) >= freeDay.getAvailableFrom());
				}

				if (isNationalFreeDay) {
					return true;
				}
			}
		} catch (SystemException e) {
			logger.warn("Error comparing current hired date to national free days.", e);
		}

		return false;
	}

	protected String getCategory() {
		return AbstractValidator.CONTRACT_DETAILS_ERROR;
	}
}
