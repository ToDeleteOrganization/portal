package com.evozon.evoportal.evozonfreedaysallocator.model.impl;

public enum FreeDaysHistoryEventType {

	/**
	 * Represents the bonus for time worked in the company.
	 */
	BONUS_EVOZON,

	/**
	 * Represents the bonus for overall experience.
	 */
	BONUS_EXPERIENCE,

	/**
	 * Represents the extra days allocated for a special circumstances (days for overtime, days remaining from paternity
	 * leave etc.)
	 */
	EXTRA_DAYS,

	/**
	 * Represents changes at New Year
	 */
	NEW_YEAR,

	/**
	 * Represents a vacation request.
	 */
	CO,

	/**
	 * Represents a vacation withiut payment request.
	 */
	CF,

	/**
	 * Maternity Leave CIC request (concediu ingrijire copil).
	 */
	ML_CIC,

	/**
	 * Represents account changes.
	 */
	ACCOUNT,

	/**
	 * Represents legal days changes.
	 */
	LEGAL_DAYS,

	OTHER;
}
