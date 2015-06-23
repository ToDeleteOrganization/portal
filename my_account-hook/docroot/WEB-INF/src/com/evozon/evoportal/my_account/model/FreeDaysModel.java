package com.evozon.evoportal.my_account.model;

import java.util.Calendar;
import java.util.Date;

import com.liferay.portal.kernel.util.StringPool;

public class FreeDaysModel implements IAccountModel {

	private Date internshipStartDate;

	private Date startDate;

	private Date cimStartDate;

	private int freeDaysFromLastYear;

	private int freeDaysInCurrentYear;

	private int remainingFreeDaysFromLastYear;

	private int startingBonusDays;

	private int extraDaysCount;

	private String comments = StringPool.BLANK;

	private String userType = StringPool.BLANK;

	private String extraDaysDescription = new String();

	public FreeDaysModel() {
	}

	public FreeDaysModel(FreeDaysModel current) {
		setCimStartDate(current.getCimStartDate());
		setStartDate(current.getStartDate());
		setComments(current.getComments().toString());
		setExtraDaysCount(current.getExtraDaysCount());
		setFreeDaysFromLastYear(current.getFreeDaysFromLastYear());
		setFreeDaysInCurrentYear(current.getFreeDaysInCurrentYear());
		setRemainingFreeDaysFromLastYear(current.getRemainingFreeDaysFromLastYear());
		setStartingBonusDays(current.getStartingBonusDays());
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public void setCimStartDate(Date cimStartDate) {
		this.cimStartDate = cimStartDate;
	}

	public void setFreeDaysFromLastYear(int freeDaysFromLastYear) {
		this.freeDaysFromLastYear = freeDaysFromLastYear;
	}

	public void setFreeDaysInCurrentYear(int frreeDaysIncurrentYear) {
		this.freeDaysInCurrentYear = frreeDaysIncurrentYear;
	}

	public int getRemainingFreeDaysFromLastYear() {
		return remainingFreeDaysFromLastYear;
	}

	public void setRemainingFreeDaysFromLastYear(int remainingFreedaysFromLastYear) {
		this.remainingFreeDaysFromLastYear = remainingFreedaysFromLastYear;
	}

	public void setStartingBonusDays(int startingBonusDays) {
		this.startingBonusDays = startingBonusDays;
	}

	public void setComments(String newComments) {
		this.comments = newComments;
	}
	
	public void setExtraDaysDescription(String extraDaysDescription) {
		this.extraDaysDescription = extraDaysDescription;
	}

	public void setExtraDaysCount(int extraDaysCount) {
		this.extraDaysCount = extraDaysCount;
	}

	public int getFreeDaysInCurrentYear() {
		return freeDaysInCurrentYear;
	}

	public String getComments() {
		return comments;
	}
	
	public String getExtraDaysDescription() {
		return extraDaysDescription;
	}

	public Date getStartDate() {
		return startDate;
	}
	
	public Calendar getStartDateAsCalendar() {
		Calendar startDateCal = Calendar.getInstance();
		startDateCal.setTime(getStartDate());
		return startDateCal;
	}

	public Date getCimStartDate() {
		return cimStartDate;
	}
	
	public Calendar getCimStartDateAsCalendar() {
		Calendar cimStartDateCal = Calendar.getInstance();
		cimStartDateCal.setTime(getCimStartDate());
		return cimStartDateCal;
	}

	public int getFreeDaysFromLastYear() {
		return freeDaysFromLastYear;
	}

	public int getStartingBonusDays() {
		return startingBonusDays;
	}

	public int getExtraDaysCount() {
		return extraDaysCount;
	}

	public Date getInternshipStartDate() {
		return internshipStartDate;
	}

	public void setInternshipStartDate(Date internshipStartDate) {
		this.internshipStartDate = internshipStartDate;
	}

	public Calendar getInternshipStartDateAsCalendar() {
		Calendar cimStartDateCal = Calendar.getInstance();
		cimStartDateCal.setTime(getInternshipStartDate());
		return cimStartDateCal;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

}
