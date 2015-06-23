package com.evozon.evoportal.evozonfreedaysallocator.model.impl;

/**
 * Enum representing the possible values for benefit days. Currently, only bonus days are part of the benefit package.
 */
public enum BenefitDayType {

	/**
	 * Represents the bonus for time worked in the company.
	 */
	BONUS_EVOZON,

	/**
	 * Represents the bonus for overall experience.
	 */
	BONUS_EXPERIENCE,
	
	/**
	 * Represents the extra days allocated for a special circumstances (days for overtime, days remaining from paternity leave etc.)
	 */
	EXTRA_DAYS;

}
