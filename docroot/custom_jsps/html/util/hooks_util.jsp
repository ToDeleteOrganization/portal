
<%@page import="com.evozon.evoportal.familytableaccess.slayer.model.FamilyMember"%>
<%@page import="com.evozon.evoportal.familytableaccess.slayer.model.FamilyMemberClp"%>
<%@page import="com.liferay.portal.kernel.json.JSONArray"%>
<%@page import="com.liferay.portal.kernel.json.JSONException"%>
<%@page import="com.liferay.portal.kernel.json.JSONFactoryUtil"%>
<%@page import="com.liferay.portal.kernel.json.JSONObject"%>
<%@page import="javax.servlet.http.HttpServletRequest"%>

<%@page import="java.io.Serializable"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.util.Date"%>

<%!
private FamilyMember getFamilySpouseMember(HttpServletRequest request) {
	FamilyMember familyMember = null;
	Map<String, String> spouseMap = (Map<String, String>)request.getAttribute("invalidSpouseData");

	if ((spouseMap != null) && !spouseMap.isEmpty()) {
		familyMember = new FamilyMemberClp();
		familyMember.setFirstName(spouseMap.get("spouseFirstName"));
		familyMember.setLastName(spouseMap.get("spouseLastName"));
		familyMember.setSeries(spouseMap.get("spouseSerie"));
		familyMember.setNumar(spouseMap.get("spouseNumar"));
		familyMember.setCNP(spouseMap.get("spouseCNP"));
		familyMember.setNew(Boolean.getBoolean(spouseMap.get("isNew")));
	}

	return familyMember;
}

private FamilyMember getFamilyChildrenMember(long childId, HttpServletRequest request) {
	FamilyMember familyMember = null;
	Map<String, String> childMap = (Map<String, String>)request.getAttribute(childId + "invalidChildData");
	return extractChildFromMap(String.valueOf(childId), childMap);
}

private List<FamilyMember> getNewFamilyChildeMember(HttpServletRequest request) {
	List<FamilyMember> familyMembers = new ArrayList<FamilyMember>();
	int invalidNewChildrenCount = 0;
	Object objCount = request.getAttribute("invalidNewChildrenCount");
	if (objCount != null) {
		invalidNewChildrenCount = (Integer)request.getAttribute("invalidNewChildrenCount");
	}
	
	for (int i = 0; i < invalidNewChildrenCount; i++) {
		Map<String, String> childMap = (Map<String, String>)request.getAttribute(i + "invalidChildData");
		familyMembers.add(extractChildFromMap(String.valueOf(i), childMap));
	}

	return familyMembers;
}

private FamilyMember extractChildFromMap(String childId, Map<String, String> childMap) {
	FamilyMember familyMember = null;
	if ((childMap != null) && !childMap.isEmpty()) {
		familyMember = new FamilyMemberClp();
		familyMember.setFirstName(childMap.get(childId + "childFirstName"));
		familyMember.setLastName(childMap.get(childId + "childLastName"));
		familyMember.setSeries(childMap.get(childId + "childSerie"));
		familyMember.setNumar(childMap.get(childId + "childNumar"));
		familyMember.setCNP(childMap.get(childId + "childCNP"));
		familyMember.setNew(Boolean.getBoolean(childMap.get(childId + "isNew")));
	}
	return familyMember;
}

private Calendar getInvalidDateHired(HttpServletRequest request) {
	Calendar invalidDateHired = null;
	Object dhObj = request.getAttribute("invalidDateHired");
	if (dhObj != null) {
		invalidDateHired = (Calendar)dhObj;
	}
	return invalidDateHired;
}


private boolean hasDeactivationDateSet(User user) {
	boolean hasDeactivationDateSet = false;

	Date statusChangeDate = getStatusChangeDateForStatus(user, com.liferay.portal.kernel.workflow.WorkflowConstants.STATUS_INACTIVE);
	if (statusChangeDate != null) {
		hasDeactivationDateSet = (Calendar.getInstance().getTime().compareTo(statusChangeDate) < 0);
	}

	return hasDeactivationDateSet;
}

private boolean hasActivationDateSet(User user) {
	boolean hasActivationDateSet = false;

	Date statusChangeDate = getStatusChangeDateForStatus(user, com.liferay.portal.kernel.workflow.WorkflowConstants.STATUS_APPROVED);
	if (statusChangeDate != null) {
		hasActivationDateSet = (Calendar.getInstance().getTime().compareTo(statusChangeDate) < 0);
	}

	return hasActivationDateSet;
}

private Date getStatusChangeDateForStatus(User user, int status) {
	Date statusChangeDate = null;

	JSONObject currentStatus = getCurrentStatus(user);
	if ((currentStatus != null) && (currentStatus.getInt("status") == status)) {
		long statusChangeDateInMillis = currentStatus.getLong("statusChangeDate");
		statusChangeDate = new Date(statusChangeDateInMillis);
	}

	return statusChangeDate;
}

private JSONObject getCurrentStatus(User user) {
	JSONObject currentStatus = null;
	String dateStr = (String) user.getExpandoBridge().getAttribute("Evozon User Status", false);

	try {
		if (!dateStr.isEmpty()) {
			JSONArray statusArray = JSONFactoryUtil.createJSONArray(dateStr);
			int length = statusArray.length();
			if (length > 0) {
				currentStatus = statusArray.getJSONObject(length - 1);
			}
		}
	} catch (JSONException json) {
		System.out.println(user.getFullName() + "dateStr = " + dateStr + ", " + json.getMessage());
	}

	return currentStatus;
}

private int getFreeDaysFromLastYear(User user) {
	return Integer.parseInt(getExpandoAttribute(user, "Free Days From Last Year").toString());
}

private int getFreeDaysFromCurrentYear(User user) {
	return Integer.parseInt(getExpandoAttribute(user, "Free Days In Current Year").toString());
}

private int getExtraDays(User user) {
	return Integer.parseInt(getExpandoAttribute(user, "Extra Days").toString());
}

private int getTotalFreeDays(User user) {
	return getFreeDaysFromLastYear(user) + getFreeDaysFromCurrentYear(user) + getExtraDays(user);
}

private int getStartingBonusDays(User user) {
	return Integer.parseInt(getExpandoAttribute(user, "Starting Bonus Days").toString());
}

private String getComments(User user) {
	return getExpandoAttribute(user, "Comments").toString();
}

private Date getDateHired(User user) {
	return (Date)getExpandoAttribute(user, "Date Hired");
}

private Calendar getDateHiredAsCalendar(User user) {
	return toCalendar(getDateHired(user));
}

private Date getInternshipDate(User user) {
	return (Date)getExpandoAttribute(user, "Internship Start Date");
}

private Calendar getInternshipDateAsCalendar(User user) {
	return toCalendar(getInternshipDate(user));
}

private Date getCIMStartDate(User user) {
	return (Date)getExpandoAttribute(user, "Cim Start Date");
}

private Calendar getCIMStartDateAsCalendar(User user) {
	return toCalendar(getCIMStartDate(user));
}

private String getFunctieCIM(User user) {
	String functieCIM = "";

	String[] function = (String[])getExpandoAttribute(user, "Functie Cim");
	if (function.length != 0) {
		functieCIM = function[0];
	}

	return functieCIM;
}

private String getUserCNP(User user) {
	return getExpandoAttribute(user, "Personal Identification Number").toString();
}

private boolean isPhoneHidden(User user) {
	return (Boolean)getExpandoAttribute(user, "Hide Phones");
}

private boolean isBirthdayHidden(User user) {
	return (Boolean)getExpandoAttribute(user, "Hide Birthday");
}

private boolean isStudent(User user) {
	return (Boolean)getExpandoAttribute(user, "Student");
}

private Calendar toCalendar(Date date) {
	Calendar dateToCalendar = Calendar.getInstance();
	dateToCalendar.setTime(date);
	return dateToCalendar;
}

private Serializable getExpandoAttribute(User user, String attributeName) {
	return user.getExpandoBridge().getAttribute(attributeName, false);
}
%>