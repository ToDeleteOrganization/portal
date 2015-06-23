package com.evozon.evoportal.evozonfreedaysallocator.model.impl;


public class AllocationShift {

	private Long userId;

	private int calendarField;

	private int calendarAmount;

	public AllocationShift() {
		this(0L);
	}

	public AllocationShift(Long userId) {
		this.userId = userId;
	}

	public int getCalendarField() {
		return calendarField;
	}

	public int getCalendarAmount() {
		return calendarAmount;
	}

	public void setCalendarField(int calendarField) {
		this.calendarField = calendarField;
	}

	public void setCalendarAmount(int calendarAmount) {
		this.calendarAmount = calendarAmount;
	}

	public Long getUserId() {
		return userId;
	}

	public String toString() {
		StringBuilder build = new StringBuilder();
		build.append("[userId = ");
		build.append(userId);
		build.append(", field = ");
		build.append(calendarField);
		build.append(", amount = " + calendarAmount);
		build.append("]");
		return build.toString();
	}
}
