package com.evozon.evoportal.my_account.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.portlet.ActionRequest;
import javax.portlet.PortletRequest;

import com.evozon.evoportal.familytableaccess.slayer.model.FamilyMember;
import com.evozon.evoportal.familytableaccess.slayer.model.FamilyMemberClp;
import com.evozon.evoportal.familytableaccess.slayer.service.FamilyMemberLocalServiceUtil;
import com.evozon.evoportal.my_account.AccountModelHolder;
import com.evozon.evoportal.my_account.managers.UserFamilyHandler;
import com.evozon.evoportal.my_account.model.DetailsModel;
import com.evozon.evoportal.my_account.model.EvoAddressModel;
import com.evozon.evoportal.my_account.model.FreeDaysModel;
import com.evozon.evoportal.my_account.model.UserFamily;
import com.evozon.evoportal.my_account.wrapper.UserExpandoWrapper;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.Address;
import com.liferay.portal.model.Country;
import com.liferay.portal.model.EmailAddress;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.Phone;
import com.liferay.portal.model.User;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.persistence.CountryUtil;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portal.util.PortletKeys;

public class MyAccountRequestUtil {

	private static Log logger = LogFactoryUtil.getLog(MyAccountRequestUtil.class);

	private static final String ADDRESSES_INDEXES = "addressesIndexes";

	public static Date getCIMStartDateFromRequest(ActionRequest actionRequest) {
		Date cimStartDate = null;
		String cimStart = getDateFromRequest(actionRequest, "cm"); // cimStart
//				ParamUtil.getString(actionRequest, "cimStart");

		if (cimStart.isEmpty()) {
			long companyId = PortalUtil.getCompanyId(actionRequest);
			cimStartDate = getDefaultUserCustomAttribute(companyId, MyAccountConstants.CIM_START_DATE);

		} else {
			cimStartDate = getDateFromString(cimStart, MyAccountConstants.ZEBRA_DATE_PICKER_DATE_FORMAT, actionRequest.getLocale(), true);

		}
		return cimStartDate;
	}

	public static Date getBirthdayFromRequest(ActionRequest actionRequest) {
		 String birthday = getDateFromRequest(actionRequest, "birthday");
		return getDateFromString(birthday, MyAccountConstants.ZEBRA_DATE_PICKER_DATE_FORMAT, actionRequest.getLocale(), true);
	}

	public static Date getInternshipStartDateFromRequest(ActionRequest actionRequest) {
		Date internshipStartdate = null;
		String internshipStart = getDateFromRequest(actionRequest, "internship"); // internshipStart
//				ParamUtil.getString(actionRequest, "internshipStart");

		if (internshipStart.isEmpty()) {
			long companyId = PortalUtil.getCompanyId(actionRequest);
			internshipStartdate = getDefaultUserCustomAttribute(companyId, MyAccountConstants.INTERNSHIP_START_DATE);

		} else {
			internshipStartdate = getDateFromString(internshipStart, MyAccountConstants.ZEBRA_DATE_PICKER_DATE_FORMAT, actionRequest.getLocale(), true);
		}

		return internshipStartdate;
	}

	// TODO: this should be refactored, may cause problems
	public static Date getDateHired(ActionRequest actionRequest) {
		Date hiredDate = null;

		if (!MyAccountRequestUtil.isUserAdministrationUpdate(actionRequest)) {
			// this is the case when a user changes his details
			try {
				User selectedUser = PortalUtil.getSelectedUser(actionRequest);
				UserExpandoWrapper userExpando = new UserExpandoWrapper(selectedUser.getUserId());
				hiredDate = userExpando.getDateHired();
			} catch (Exception pe) {
				logger.error("Error getting exception from request.", pe);
			}
		} else {
			String dateHiredStr = getDateFromRequest(actionRequest, "hired"); // dateHired
//					ParamUtil.getString(actionRequest, "dateHired", StringPool.BLANK);
			if (dateHiredStr.isEmpty()) {
				dateHiredStr = getDateFromRequest(actionRequest, "internship"); 
//						ParamUtil.getString(actionRequest, "internshipStart", StringPool.BLANK);
			}
			hiredDate = getDateFromString(dateHiredStr, MyAccountConstants.ZEBRA_DATE_PICKER_DATE_FORMAT, actionRequest.getLocale(), true);
		}

		return hiredDate;
	}

	public static AccountModelHolder getAccountModelFromUser(User user) {
		AccountModelHolder accountModel = new AccountModelHolder();

		try {
			accountModel.setFreeDaysModel(getFreeDaysModelFromUser(user));
			accountModel.setDetailsModel(getDetailsModelFromUser(user));
			accountModel.setAdresses(getEvoAddressFromUser(user));
			accountModel.setUserDepartments(getUserDepartmentsFromUser(user));
			accountModel.setUserFamily(getFamilyFromUser(user));
		} catch (PortalException e) {
			logger.error("Could not get account model users from request", e);
		} catch (SystemException e) {
			logger.error("Could not get account model users from request", e);
		} catch (Exception e) {
			logger.error("Could not get account model users from request", e);
		}

		return accountModel;
	}

	private static List<EvoAddressModel> getEvoAddressFromUser(User user) throws SystemException {
		List<EvoAddressModel> adressModel = new ArrayList<EvoAddressModel>();
		for (Address address : user.getAddresses()) {
			EvoAddressModel addressModel = new EvoAddressModel();

			addressModel.setCity(address.getCity());
			addressModel.setStreetName(address.getStreet1());
			addressModel.setStreetNumber(address.getStreet2());
			addressModel.setPostalCode(address.getStreet3());
			addressModel.setCountryCode(address.getCountry().getA2());
			addressModel.setType(address.getTypeId());
			addressModel.setRegion(address.getRegionId());
			addressModel.setPrimary(address.isPrimary());
			addressModel.setMailing(address.getMailing());

			adressModel.add(addressModel);
		}
		return adressModel;
	}

	private static DetailsModel getDetailsModelFromUser(User user) {
		DetailsModel detailsModel = new DetailsModel();

		try {
			UserExpandoWrapper userExpando = new UserExpandoWrapper(user);

			detailsModel.setScreenName(user.getScreenName());
			detailsModel.setEmailAddress(user.getEmailAddress());
			detailsModel.setFirstName(user.getFirstName());
			detailsModel.setLastName(user.getLastName());
			detailsModel.setJobTitle(user.getJobTitle());

			detailsModel.setBuilding(userExpando.getBuilding());
			detailsModel.setFunctieCIM(userExpando.getUserJob());
			detailsModel.setCnp(userExpando.getPersonalIdentificationNumber());
			detailsModel.setLicensePlate(userExpando.getLicensePlate());
			detailsModel.setPhoneNumberHidden(userExpando.isPhoneNumberHidden());
			detailsModel.setBirthdayHidden(userExpando.isBirthdayHidden());
			detailsModel.setMarried(userExpando.getMarried());
			detailsModel.setStudent(userExpando.isStudent());
			detailsModel.setUniversity(userExpando.getUniversity());
			detailsModel.setFaculty(userExpando.getFaculty());
			detailsModel.setDiplomaTitle(userExpando.getDiplomaTitle());
			detailsModel.setBirthdayDate(user.getBirthday());

			// add phone number
			List<Phone> phones = user.getPhones();
			if (!phones.isEmpty()) {
				Phone phone = phones.get(0);
				detailsModel.setPhoneNumber(phone.getNumber());
			}

			// add additional email address
			List<EmailAddress> additionalEmails = user.getEmailAddresses();
			if (!additionalEmails.isEmpty()) {
				EmailAddress emailAddress = additionalEmails.get(0);
				detailsModel.setAdditionalEmailAddress(emailAddress.getAddress());
			}

			detailsModel.setMale(user.isMale());
			detailsModel.setSkype(user.getContact().getSkypeSn());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

		return detailsModel;
	}

	// TODO: this should be removed from here
	public static FreeDaysModel getFreeDaysModelFromUser(User user) {
		FreeDaysModel freeDaysModel = new FreeDaysModel();

		UserExpandoWrapper userExpando = new UserExpandoWrapper(user);

		freeDaysModel.setStartDate(userExpando.getDateHired());
		freeDaysModel.setCimStartDate(userExpando.getCIMStartDate());
		freeDaysModel.setInternshipStartDate(userExpando.getInternshipStartDate());
		freeDaysModel.setFreeDaysFromLastYear(userExpando.getFreeDaysFromLastYear());
		freeDaysModel.setFreeDaysInCurrentYear(userExpando.getFreeDaysInCurrentYear());
		freeDaysModel.setStartingBonusDays(userExpando.getStartingBonusDays());
		freeDaysModel.setComments(userExpando.getComments());
		freeDaysModel.setExtraDaysCount(userExpando.getExtraDays());

		return freeDaysModel;
	}

	public static AccountModelHolder getAccountModelFromRequest(ActionRequest actionRequest) {
		AccountModelHolder accountModel = new AccountModelHolder();

		try {
			accountModel.setFreeDaysModel(getFreeDaysModelFromRequest(actionRequest));
			accountModel.setDetailsModel(getDetailsModelFromRequest(actionRequest));
			accountModel.setAdresses(getAddressesFromRequest(actionRequest));
			accountModel.setUserFamily(getUserFamilyFromRequest(actionRequest));
			accountModel.setUserType(ParamUtil.getString(actionRequest, "user_type"));
			
			List<String> departments = null;
			if (MyAccountRequestUtil.isUserAdministrationUpdate(actionRequest)) {
				departments = getUserDepartmentsFromRequest(actionRequest);
			} else {
				User user = PortalUtil.getSelectedUser(actionRequest);
				departments = getUserDepartmentsFromUser(user);
			}
			accountModel.setUserDepartments(departments);

		} catch (PortalException e) {
			logger.error("Could not get account model users from request", e);
		} catch (SystemException e) {
			logger.error("Could not get account model users from request", e);
		} catch (Exception e) {
			logger.error("Could not get account model users from request", e);
		}

		return accountModel;
	}

	private static UserFamily getFamilyFromUser(User user) throws SystemException {
		long userId = user.getUserId();
		UserFamily userFamily = new UserFamily();
		userFamily.setSpouse(FamilyMemberLocalServiceUtil.getSpouse(userId));
		userFamily.setChildrens(FamilyMemberLocalServiceUtil.getChildren(userId));
		return userFamily;
	}

	public static UserFamily getUserFamilyFromRequest(PortletRequest actionRequest) throws PortalException, SystemException {
		UserFamily userFamily = new UserFamily();
		userFamily.setSpouse(getSpouseFromRequest(actionRequest));
		userFamily.setChildrens(getChildrenFromRequest(actionRequest));
		return userFamily;
	}

	private static List<FamilyMember> getChildrenFromRequest(PortletRequest actionRequest) throws PortalException, SystemException {
		List<FamilyMember> children = new ArrayList<FamilyMember>();

		String[] indexesArray = ParamUtil.getString(actionRequest, "childrenIndexes").split(StringPool.COMMA);
		List<String> indexes = Arrays.asList(indexesArray);
		long userBelongsToId = PortalUtil.getUserId(actionRequest);

		int memberIndex = 0;
		for (int i = 0; i < indexes.size(); i++) {
			String index = indexes.get(i);

			FamilyMember child = new FamilyMemberClp();
			child.setFirstName(ParamUtil.getString(actionRequest, "childFirstName" + index, StringPool.BLANK).trim());
			child.setLastName(ParamUtil.getString(actionRequest, "childLastName" + index, StringPool.BLANK).trim());
			child.setCNP(ParamUtil.getString(actionRequest, "childCNP" + index, StringPool.BLANK));
			child.setType(MyAccountConstants.CHILD);
			child.setUserBelongsId(userBelongsToId);
			child.setSeries(StringPool.BLANK);
			child.setNumar(StringPool.BLANK);

			if (!UserFamilyHandler.isFamilyMemberEmpty(child)) {
				String idStr = ParamUtil.getString(actionRequest, "childId" + index, StringPool.BLANK);
				long memberId = (StringPool.BLANK.equals(idStr)) ? Long.valueOf(memberIndex++) : Long.valueOf(idStr);

				child.setMemberId(memberId);
				children.add(child);
			}
		}
		return children;
	}

	private static FamilyMember getSpouseFromRequest(PortletRequest actionRequest) throws PortalException, SystemException {
		FamilyMember spouse = new FamilyMemberClp();

		spouse.setFirstName(ParamUtil.getString(actionRequest, "spouseFirstName", StringPool.BLANK));
		spouse.setLastName(ParamUtil.getString(actionRequest, "spouseLastName", StringPool.BLANK).trim());
		spouse.setCNP(ParamUtil.getString(actionRequest, "spouseCNP", StringPool.BLANK));
		spouse.setType(MyAccountConstants.SPOUSE);
		spouse.setMemberId(ParamUtil.getLong(actionRequest, "spouseId", 0l));
		spouse.setUserBelongsId(PortalUtil.getUserId(actionRequest));
		spouse.setNumar(StringPool.BLANK);
		spouse.setSeries(StringPool.BLANK);

		return !UserFamilyHandler.isFamilyMemberEmpty(spouse) ? spouse : null;
	}

	private static List<String> getUserDepartmentsFromUser(User user) throws PortalException, SystemException {
		List<String> departments = new ArrayList<String>();
		List<Group> mySites = user.getMySites();

		for (Group site : mySites) {
			if (site.isSite() && !site.isGuest()) {
				departments.add(site.getName());
			}
		}

		return departments;
	}

	private static List<String> getUserDepartmentsFromRequest(ActionRequest actionRequest) throws PortalException, SystemException {
		List<String> userDepartments = null;

		long[] depIdArray = ParamUtil.getLongValues(actionRequest, "groupsSearchContainerPrimaryKeys", new long[] {});
		userDepartments = new ArrayList<String>();
		List<Group> groups = GroupLocalServiceUtil.getGroups(depIdArray);
		for (Group group : groups) {
			if (!group.isGuest()) {
				userDepartments.add(group.getName());
			}
		}

		return userDepartments;
	}

	private static List<EvoAddressModel> getAddressesFromRequest(ActionRequest actionRequest) {
		List<EvoAddressModel> addresses = new ArrayList<EvoAddressModel>();

		String primaryIndexAddress = ParamUtil.getString(actionRequest, "addressPrimary", "0");

		String addressesIndexes = ParamUtil.getString(actionRequest, ADDRESSES_INDEXES);
		String[] indexes = addressesIndexes.split(StringPool.COMMA);

		for (String idx : indexes) {
			EvoAddressModel addressModel = new EvoAddressModel();

			String streetName = ParamUtil.getString(actionRequest, "addressStreet1_" + idx, StringPool.BLANK);
			String streetNo = ParamUtil.getString(actionRequest, "addressStreet2_" + idx, StringPool.BLANK);
			String city = ParamUtil.getString(actionRequest, "addressCity" + idx, StringPool.BLANK);

			if (streetName.isEmpty() && streetNo.isEmpty() && city.isEmpty()) {
				continue;
			}

			String zip = ParamUtil.getString(actionRequest, "addressZip" + idx);
			long regionId = ParamUtil.getLong(actionRequest, "addressRegionId" + idx, 0L);
			long typeId = ParamUtil.getLong(actionRequest, "addressTypeId" + idx, 0L);
			boolean mailing = ParamUtil.getBoolean(actionRequest, "addressMailing" + idx, false);

			addressModel.setStreetName(streetName);
			addressModel.setStreetNumber(streetNo);
			addressModel.setPostalCode(zip);
			addressModel.setRegion(regionId);
			addressModel.setType(typeId);
			addressModel.setCity(city);
			addressModel.setMailing(mailing);
			addressModel.setPrimary(primaryIndexAddress.equals(idx));

			try {
				int countryId = ParamUtil.getInteger(actionRequest, "addressCountryId" + idx);
				if (countryId != 0) {
					Country country = CountryUtil.fetchByPrimaryKey(countryId);
					addressModel.setCountryCode(country.getA2());
				}
			} catch (SystemException e) {
				logger.error("Error fetching address model from request: " + e.getMessage(), e);
			}
			addresses.add(addressModel);
		}

		return addresses;
	}

	public static DetailsModel getDetailsModelFromRequest(ActionRequest actionRequest) {
		DetailsModel detailsModel = new DetailsModel();

		detailsModel.setScreenName(ParamUtil.get(actionRequest, "screenName", StringPool.BLANK));
		detailsModel.setEmailAddress(ParamUtil.get(actionRequest, "emailAddress", StringPool.BLANK));
		detailsModel.setFirstName(ParamUtil.get(actionRequest, "firstName", StringPool.BLANK));
		detailsModel.setLastName(ParamUtil.get(actionRequest, "lastName", StringPool.BLANK));
		detailsModel.setMale(ParamUtil.getBoolean(actionRequest, "male", Boolean.FALSE));
		detailsModel.setBuilding(ParamUtil.get(actionRequest, "ExpandoAttribute--Building--", StringPool.BLANK));
		detailsModel.setJobTitle(ParamUtil.get(actionRequest, "jobTitle", StringPool.BLANK));
		detailsModel.setFunctieCIM(ParamUtil.get(actionRequest, "ExpandoAttribute--Functie CIM--", StringPool.BLANK));
		detailsModel.setCnp(ParamUtil.get(actionRequest, "userCNP", StringPool.BLANK));
		detailsModel.setLicensePlate(ParamUtil.get(actionRequest, "ExpandoAttribute--License Plate--", StringPool.BLANK));
		detailsModel.setBirthdayDate(getBirthdayFromRequest(actionRequest));
		detailsModel.setBirthdayHidden(ParamUtil.getBoolean(actionRequest, "hideBirthday", Boolean.FALSE));
		detailsModel.setSkype(ParamUtil.get(actionRequest, "skypeSn", StringPool.BLANK));
		detailsModel.setPhoneNumber(ParamUtil.get(actionRequest, "phoneNumber", StringPool.BLANK));
		detailsModel.setPhoneNumberHidden(ParamUtil.getBoolean(actionRequest, "hidePhones", Boolean.FALSE));
		detailsModel.setMarried(ParamUtil.getBoolean(actionRequest, "ExpandoAttribute--Married--", Boolean.FALSE));
		detailsModel.setStudent(ParamUtil.getBoolean(actionRequest, "isStudent", Boolean.FALSE));
		detailsModel.setUniversity(ParamUtil.get(actionRequest, "ExpandoAttribute--University--", StringPool.BLANK));
		detailsModel.setFaculty(ParamUtil.get(actionRequest, "ExpandoAttribute--Faculty--", StringPool.BLANK));
		detailsModel.setDiplomaTitle(ParamUtil.get(actionRequest, "ExpandoAttribute--Diploma title (In progress)--", StringPool.BLANK));
		detailsModel.setAdditionalEmailAddress(ParamUtil.get(actionRequest, "additionalEmailAddress", StringPool.BLANK));
		detailsModel.setOfficialName(ParamUtil.getString(actionRequest, MyAccountConstants.EXPANDO_OFFICIAL_NAME_ATTRIBUTE, StringPool.BLANK));

		return detailsModel;
	}

	public static FreeDaysModel getFreeDaysModelFromRequest(ActionRequest actionRequest) {
		FreeDaysModel freeDaysModel = new FreeDaysModel();

		freeDaysModel.setStartDate(getDateHired(actionRequest));
		freeDaysModel.setInternshipStartDate(getInternshipStartDateFromRequest(actionRequest));
		freeDaysModel.setCimStartDate(getCIMStartDateFromRequest(actionRequest));

		freeDaysModel.setUserType(ParamUtil.getString(actionRequest, "user_type"));

		int freeDaysFromLastYear = ParamUtil.getInteger(actionRequest, "freeDaysLast", 0);
		int freeDaysInCurrentYear = ParamUtil.getInteger(actionRequest, "freeDaysCurrent", 0);
		int initialFreeDaysLast = ParamUtil.getInteger(actionRequest, "initialFreeDaysLast", 0);

		int adjustValue = ParamUtil.getInteger(actionRequest, "adjustValue", 0);
		if (adjustValue != 0) {
			String adjustment = ParamUtil.getString(actionRequest, "adjustSelection", StringPool.BLANK);

			if (adjustment.contains("freeDaysCurrentOption")) {
				freeDaysInCurrentYear += adjustValue;

			} else if (adjustment.contains("freeDaysLastOption")) {
				freeDaysFromLastYear += adjustValue;

			} else if (adjustment.contains("initialFreeDaysLastOption")) {
				initialFreeDaysLast += adjustValue;
			}
		}

		freeDaysModel.setFreeDaysFromLastYear(freeDaysFromLastYear);
		freeDaysModel.setFreeDaysInCurrentYear(freeDaysInCurrentYear);
		freeDaysModel.setRemainingFreeDaysFromLastYear(initialFreeDaysLast);

		freeDaysModel.setStartingBonusDays(ParamUtil.getInteger(actionRequest, "startingBonusDays", 0));

		freeDaysModel.setComments(ParamUtil.getString(actionRequest, "comments", StringPool.BLANK));
		freeDaysModel.setExtraDaysCount(ParamUtil.getInteger(actionRequest, "extraDays", 0));
		freeDaysModel.setExtraDaysDescription(ParamUtil.getString(actionRequest, "extraDaysDescription", StringPool.BLANK));

		return freeDaysModel;
	}

	public static boolean isUserAdministrationUpdate(ActionRequest actionRequest) {
		String portletId = (String) actionRequest.getAttribute("PORTLET_ID");
		return PortletKeys.USERS_ADMIN.equals(portletId);
	}

	private static Date getDateFromString(String dateInput, String format, Locale locale, boolean resetToMidnight) {
		Date date = null;
		try {
			date = MyAccountUtil.convertStringToDate(dateInput, format, locale);
			if (resetToMidnight && (date != null)) {
				date = MyAccountUtil.resetToMidnight(date).getTime();
			}
		} catch (ParseException e) {
			logger.error("Eror parsing ");
		}
		return date;
	}

	private static Date getDefaultUserCustomAttribute(long companyId, String attributeName) {
		Date defaultDate = null;

		try {
			defaultDate = (Date) MyAccountUtil.getDefaultUserCustomFieldValue(companyId, MyAccountConstants.CIM_START_DATE);
		} catch (Exception e) {
			logger.warn("Cannot get default custom attribute value for: " + attributeName);
		}

		return defaultDate;
	}

	// Temporary, until zbra date picker is added
	private static String getDateFromRequest(ActionRequest actionRequest, String dateName) {
		String strDate = ParamUtil.getString(actionRequest, dateName, StringPool.BLANK);
		if (strDate.isEmpty()) {
			int day = ParamUtil.getInteger(actionRequest, dateName + "Day");
			int month = ParamUtil.getInteger(actionRequest, dateName + "Month");
			int year = ParamUtil.getInteger(actionRequest, dateName + "Year");
			
			Calendar cal = Calendar.getInstance();
			cal.set(Calendar.DAY_OF_MONTH, day);
			cal.set(Calendar.MONTH, month);
			cal.set(Calendar.YEAR, year);
			
			strDate = new SimpleDateFormat(MyAccountConstants.ZEBRA_DATE_PICKER_DATE_FORMAT).format(cal.getTime());
		}
		return strDate;
	}

	private MyAccountRequestUtil() {
	}

}
