package com.evozon.evoportal.my_account.util;

public class MyAccountConstants {

	public static final String EMAIL_ADDRESS = "emailAddress";

	public static final String DATE_HIRED_EXPANDO_ATR = "Date Hired";

	public static final String BUILDING = "Building";

	public static final String EXPANDO_BUILDING = getCustomFieldIdVersion(BUILDING);

	public static final String PERSONAL_IDENTIFICATION_NUMBER = "Personal Identification Number";

	public static final String USER_CNP = "userCNP";

//	public static String USER_CNP_OLD = "oldUserCNP";

	public static final String LICENSE_PLATE = "License Plate";

	public static final String EXPANDO_LICENSE_PLATE = getCustomFieldIdVersion(LICENSE_PLATE);

	public static final String CIM_START_DATE = "Cim Start Date";

	public static final String INTERNSHIP_START_DATE = "Internship Start Date";

	public static final String EVOZON_USER_STATUS = "Evozon User Status";

	public static final String STARTING_BONUS_DAYS = "Starting Bonus Days";

	public static final String EXTRA_DAYS_CURRENT = "Extra Days";

	public static final String JOB_ATTRIBUTE = "Functie CIM";

	public static final String EXPANDO_JOB_ATTRIBUTE = getCustomFieldIdVersion(JOB_ATTRIBUTE);

	public static final String OFFICIAL_NAME_ATTRIBUTE = "Official Name";

	public static final String EXPANDO_OFFICIAL_NAME_ATTRIBUTE = getCustomFieldIdVersion(OFFICIAL_NAME_ATTRIBUTE);

	public static final String SCHOLARSHIP = "Bursier";

	public static final String USER_ENTRY_TYPE = "User Entry Type";

	public static final String FREE_DAYS_CURRENT_EXPANDO_ATR = "Free Days In Current Year";

	public static final String FREE_DAYS_LAST_YEAR_EXPANDO_ATR = "Free Days From Last Year";
	
	public static final String EXTRACTED_FREE_DAYS_FROM_CURRENT_YEAR = "Extracted Free Days From Current Year";

	public static final String EXTRACTED_FREE_DAYS_FROM_LAST_YEAR = "Extracted Free Days From Last Year";

	public static final String LEGAL_VACATION_DAYS = "Legal Vacation Days";
	
	public static final String REMAINING_FREE_DAYS_LAST = "Remaining Free Days From Last Year";

	public static final String COMMENTS_EXPANDO_ATR = "Comments";

	public static final String EVOZON_EXPERIENCE_EXPANDO_ATR = "Months Of Experience In Evozon";

	public static final String EVOPORTAL_EMAIL_ADDRESS = "evoportal@evozon.com";

	private static final String EXPANDO_ATTRIBUTE_REQ = "ExpandoAttribute";

	public static final String DELETED_IDS = "deleteUserIds";

	private static final String EXPANDO_ATTRIBUTE_REQ_DELIMITER = "--";

	public static final String FACULTY = "Faculty";

	public static final String UNIVERSITY = "University";

	public static final String HIDE_PHONES = "Hide phones";

	public static final String HIDE_BIRTHDAY = "Hide Birthday";

	public static final String MARRIED = "Married";

	public static final String STUDENT = "Student";

	public static final String DIPLOMA_TITLE = "Diploma title (In progress)";

	public static final String DAYS_LEFT = "daysLeft";

	public static final String DATE_FORMAT_DD_MM_YYYY = "dd/MM/yyyy";

	public static final String ZEBRA_DATE_PICKER_DATE_FORMAT = "dd - MMM - yyyy";

	public static final String CANCEL_DEACTIVATION = "CANCEL_DEACTIVATION";

	public static final String CANCEL_ACTIVATION = "CANCEL_ACTIVATION";

	public static final String USERS_IDS_PARAMETER = "userIds";

	public static final String SELECTED_USERS = "selectedUsers";

	public static final String EDITOR_PORTAL = "Editor Portal";

	public static final String EVOPORTAL_SITE = "EvoPortal";

	public static final int SPOUSE = 0;

	public static final int CHILD = 1;

	private static final String getCustomFieldIdVersion(String attribute) {
		return EXPANDO_ATTRIBUTE_REQ + EXPANDO_ATTRIBUTE_REQ_DELIMITER + attribute + EXPANDO_ATTRIBUTE_REQ_DELIMITER;
	}

	private MyAccountConstants() {
	}
}
