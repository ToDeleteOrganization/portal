package com.evozon.evoportal.myaccount.builder;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.portlet.PortletRequest;

import com.evozon.evoportal.my_account.AccountModelHolder;
import com.evozon.evoportal.my_account.model.DetailsModel;
import com.evozon.evoportal.my_account.model.FreeDaysModel;
import com.evozon.evoportal.my_account.util.MyAccountUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.Group;
import com.liferay.portal.service.GroupLocalServiceUtil;

// TODO: remove hardcoded string in constants
public abstract class RequestAccountModelHolderBuilder implements AccountModelHolderBuilder {

	private static Log logger = LogFactoryUtil.getLog(RequestAccountModelHolderBuilder.class);

	private static final String GROUP_SEARCH_CONTAINER_PRIMARY_KEYS = "groupsSearchContainerPrimaryKeys";

	protected final PortletRequest request;

	protected final AccountModelHolder accountModelHolder;

	public RequestAccountModelHolderBuilder(PortletRequest request) {
		this.request = request;
		this.accountModelHolder = new AccountModelHolder();
	}

	public AccountModelHolder build() {
		return accountModelHolder;
	}

	protected FreeDaysModel buildFreeDaysModelInternal() {
		FreeDaysModel freeDaysModel = new FreeDaysModel();

		freeDaysModel.setUserType(ParamUtil.getString(request, "user_type"));
		freeDaysModel.setStartingBonusDays(ParamUtil.getInteger(request, "startingBonusDays", 0));
		freeDaysModel.setComments(ParamUtil.getString(request, "comments", StringPool.BLANK));
		freeDaysModel.setExtraDaysCount(ParamUtil.getInteger(request, "extraDays", 0));
		freeDaysModel.setExtraDaysDescription(ParamUtil.getString(request, "extraDaysDescription", StringPool.BLANK));

		addFreeDaysToFreeDaysModel(freeDaysModel);
		return freeDaysModel;
	}

	protected DetailsModel buildDetailsModelInternal() {
		DetailsModel detailsModel = new DetailsModel();

		detailsModel.setScreenName(ParamUtil.get(request, "screenName", StringPool.BLANK));
		detailsModel.setEmailAddress(ParamUtil.get(request, "emailAddress", StringPool.BLANK));
		detailsModel.setFirstName(ParamUtil.get(request, "firstName", StringPool.BLANK));
		detailsModel.setLastName(ParamUtil.get(request, "lastName", StringPool.BLANK));
		detailsModel.setMale(ParamUtil.getBoolean(request, "male", Boolean.FALSE));
		detailsModel.setBuilding(ParamUtil.get(request, "ExpandoAttribute--Building--", StringPool.BLANK));
		detailsModel.setJobTitle(ParamUtil.get(request, "jobTitle", StringPool.BLANK));
		detailsModel.setFunctieCIM(ParamUtil.get(request, "ExpandoAttribute--Functie CIM--", StringPool.BLANK));
		detailsModel.setCnp(ParamUtil.get(request, "userCNP", StringPool.BLANK));
		detailsModel.setLicensePlate(ParamUtil.get(request, "ExpandoAttribute--License Plate--", StringPool.BLANK));
		detailsModel.setBirthdayDate(getDateFromRequest("birthday"));
		detailsModel.setBirthdayHidden(ParamUtil.getBoolean(request, "hideBirthday", Boolean.FALSE));
		detailsModel.setSkype(ParamUtil.get(request, "skypeSn", StringPool.BLANK));
		detailsModel.setPhoneNumber(ParamUtil.get(request, "phoneNumber", StringPool.BLANK));
		detailsModel.setPhoneNumberHidden(ParamUtil.getBoolean(request, "hidePhones", Boolean.FALSE));
		detailsModel.setMarried(ParamUtil.getBoolean(request, "ExpandoAttribute--Married--", Boolean.FALSE));
		detailsModel.setStudent(ParamUtil.getBoolean(request, "isStudent", Boolean.FALSE));
		detailsModel.setUniversity(ParamUtil.get(request, "ExpandoAttribute--University--", StringPool.BLANK));
		detailsModel.setFaculty(ParamUtil.get(request, "ExpandoAttribute--Faculty--", StringPool.BLANK));
		detailsModel.setDiplomaTitle(ParamUtil.get(request, "ExpandoAttribute--Diploma title (In progress)--", StringPool.BLANK));
		detailsModel.setAdditionalEmailAddress(ParamUtil.get(request, "additionalEmailAddress", StringPool.BLANK));

		return detailsModel;
	}

	protected List<String> getUserDepartmentsFromRequest() {
		List<String> userDepartments = new ArrayList<String>();

		try {
			long[] depIdArray = ParamUtil.getLongValues(request, RequestAccountModelHolderBuilder.GROUP_SEARCH_CONTAINER_PRIMARY_KEYS, new long[] {});
			List<Group> groups = GroupLocalServiceUtil.getGroups(depIdArray);
			for (Group group : groups) {
				if (!group.isGuest()) {
					userDepartments.add(group.getName());
				}
			}
		} catch (PortalException e) {
			logger.error("Could not get departments from request.", e);
		} catch (SystemException e) {
			logger.error("Could not get departments from request.", e);
		}

		return userDepartments;
	}

	// TODO: refactor this
	protected void addFreeDaysToFreeDaysModel(FreeDaysModel daysModel) {
		int freeDaysFromLastYear = ParamUtil.getInteger(request, "freeDaysLast", 0);
		int freeDaysInCurrentYear = ParamUtil.getInteger(request, "freeDaysCurrent", 0);
		int initialFreeDaysLast = ParamUtil.getInteger(request, "initialFreeDaysLast", 0);

		int adjustValue = ParamUtil.getInteger(request, "adjustValue", 0);
		if (adjustValue != 0) {
			String adjustment = ParamUtil.getString(request, "adjustSelection", StringPool.BLANK);

			if (adjustment.contains("freeDaysCurrentOption")) {
				freeDaysInCurrentYear += adjustValue;

			} else if (adjustment.contains("freeDaysLastOption")) {
				freeDaysFromLastYear += adjustValue;

			} else if (adjustment.contains("initialFreeDaysLastOption")) {
				initialFreeDaysLast += adjustValue;
			}
		}

		daysModel.setFreeDaysFromLastYear(freeDaysFromLastYear);
		daysModel.setFreeDaysInCurrentYear(freeDaysInCurrentYear);
		daysModel.setRemainingFreeDaysFromLastYear(initialFreeDaysLast);
	}

	protected static Date getDefaultUserCustomAttribute(long companyId, String attributeName) {
		Date defaultDate = null;

		try {
			defaultDate = (Date) MyAccountUtil.getDefaultUserCustomFieldValue(companyId, attributeName);
		} catch (Exception e) {
			logger.warn("Cannot get default custom attribute value for: " + attributeName);
		}

		return defaultDate;
	}

	protected Date getDateFromRequest(String dateName) {
		Date dateObject = null;

		String strDate = ParamUtil.getString(request, dateName, StringPool.BLANK);
		if (strDate.isEmpty()) {
			int day = ParamUtil.getInteger(request, dateName + "Day");
			int month = ParamUtil.getInteger(request, dateName + "Month");
			int year = ParamUtil.getInteger(request, dateName + "Year");

			Calendar cal = Calendar.getInstance();
			cal.set(Calendar.DAY_OF_MONTH, day);
			cal.set(Calendar.MONTH, month);
			cal.set(Calendar.YEAR, year);
			cal.set(Calendar.HOUR_OF_DAY, 0);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);
			cal.set(Calendar.MILLISECOND, 0);

			dateObject = cal.getTime();
		}

		return dateObject;
	}
}
