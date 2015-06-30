package com.evozon.evoportal.my_account.pmreports.integration;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.evozon.evoportal.evozonprojects.model.ProjectGroup;
import com.evozon.evoportal.evozonprojects.service.ProjectGroupLocalServiceUtil;
import com.evozon.evoportal.my_account.AccountModelHolder;
import com.evozon.evoportal.my_account.model.DetailsModel;
import com.evozon.evoportal.my_account.model.EvoAddressModel;
import com.evozon.evoportal.my_account.util.MyAccountUtil;
import com.evozon.evoportal.ws.pmreports.model.PmResponseStatus;
import com.evozon.evoportal.ws.pmreports.util.PMReportsConstants;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.User;

public abstract class PMReportsIntegration {

	protected static final String DATE_FORMAT_MONTH_DAY_YEAR = "MM/dd/yyyy";

	private static Map<String, String> pmReportsDepartmentsAssociations = new LinkedHashMap<String, String>();

	private static final String HR_DEPARTMENT_NAME = "HR Department";

	static {
		pmReportsDepartmentsAssociations.put("HR Department", "HR");
		pmReportsDepartmentsAssociations.put("QA Department", "QA");
		pmReportsDepartmentsAssociations.put("Java Department", "Java");
		pmReportsDepartmentsAssociations.put("IT Support Department", "IT");
		pmReportsDepartmentsAssociations.put("Perl Department", "Perl");
		pmReportsDepartmentsAssociations.put(".Net Department", ".Net");
		pmReportsDepartmentsAssociations.put("Mobile Department", "Mobile");
		pmReportsDepartmentsAssociations.put("Ixxus Department", "Ixxus");
		pmReportsDepartmentsAssociations.put("Accounting Department", "Accounting");
		pmReportsDepartmentsAssociations.put("Sales&Marketing", "Sales");
		pmReportsDepartmentsAssociations.put("Games Department", "Games");
		pmReportsDepartmentsAssociations.put("Web Department", "Web Development");

		// these doesn't exist as departments, but as projects
		pmReportsDepartmentsAssociations.put("Navision", "Navision");
	}

	protected final Long companyId;

	protected final User integratedUser;

	protected AccountModelHolder newAccountModelHolder;

	protected AccountModelHolder oldAccountModelHolder;

	protected PMReportsIntegration(final Long companyId, final User integratedUser) {
		this.companyId = companyId;
		this.integratedUser = integratedUser;
	}

	public void setNewAccountModelHolder(AccountModelHolder newAccountModelHolder) {
		this.newAccountModelHolder = newAccountModelHolder;
	}

	public void setOldAccountModelHolder(AccountModelHolder oldAccountModelHolder) {
		this.oldAccountModelHolder = oldAccountModelHolder;
	}

	protected String findPMReportsAssociatedDepartment() {
		List<String> userDepartments = newAccountModelHolder.getUserDepartments();

		// if the user is part of the HR Department
		if (userDepartments.contains(HR_DEPARTMENT_NAME)) {
			return pmReportsDepartmentsAssociations.get(HR_DEPARTMENT_NAME);
		}

		// check user projects, ex. 'Navision' is a dep. in PM and a project in
		// EvoPortal.
		String pmReportsDepartmentName = StringPool.BLANK;

		List<ProjectGroup> userProjects = ProjectGroupLocalServiceUtil.findProjectsByUser(integratedUser.getUserId());
		for (ProjectGroup pGroup : userProjects) {
			String name = pGroup.getProjectGroupName();
			if (pmReportsDepartmentsAssociations.containsKey(name)) {
				pmReportsDepartmentName = name;
				break;
			}
		}

		if (!pmReportsDepartmentName.isEmpty()) {
			return pmReportsDepartmentName;
		}

		// last option check department names
		for (String departmentName : userDepartments) {
			if (pmReportsDepartmentsAssociations.containsKey(departmentName)) {
				pmReportsDepartmentName = pmReportsDepartmentsAssociations.get(departmentName);
				break;
			}
		}

		return pmReportsDepartmentName;
	}

	public abstract PmResponseStatus executeIntegration();

	protected abstract boolean hasPMReportsParamatersModified();

	protected MultiValueMap<String, Object> getPMReportsParameters() {
		MultiValueMap<String, Object> mapModifications = new LinkedMultiValueMap<String, Object>();

		DetailsModel detailsModel = newAccountModelHolder.getDetailsModel();

		mapModifications.add(PMReportsConstants.USER_CNP, detailsModel.getCNP());
		mapModifications.add(PMReportsConstants.USER_EMAIL, detailsModel.getEmailAddress());
		mapModifications.add(PMReportsConstants.USER_LAST_NAME, detailsModel.getLastName());
		mapModifications.add(PMReportsConstants.USER_FIRST_NAME, detailsModel.getFirstName());

		String startDate = MyAccountUtil.convertDateToString(newAccountModelHolder.getFreeDaysModel().getStartDate(), DATE_FORMAT_MONTH_DAY_YEAR);
		mapModifications.add(PMReportsConstants.USER_DATE_HIRED, startDate);

		String pmDep = findPMReportsAssociatedDepartment();
		if (!pmDep.isEmpty()) {
			mapModifications.add(PMReportsConstants.DEPARTMENT_NAME, pmDep);
		}

		String countryCode = null;
		EvoAddressModel primaryAddress = newAccountModelHolder.getPrimaryAddress();
		if (primaryAddress != null) {
			mapModifications.add(PMReportsConstants.USER_ZIP_CODE, primaryAddress.getPostalCode());
			mapModifications.add(PMReportsConstants.USER_CITY_NAME, primaryAddress.getCity());

			countryCode = primaryAddress.getCountryCode();
			if (countryCode.isEmpty()) {
				countryCode = PMReportsConstants.USER_DEFAULT_COUNTRY_CODE;
			}

			mapModifications.add(PMReportsConstants.USER_STREET_NAME, primaryAddress.getStreetName());
			mapModifications.add(PMReportsConstants.USER_STREET_NUMBER, primaryAddress.getStreetNumber());
		}
		// The 'country code' is mandatory
		mapModifications.add(PMReportsConstants.USER_COUNTRY_CODE, (countryCode == null) ? PMReportsConstants.USER_DEFAULT_COUNTRY_CODE : countryCode);

		mapModifications.add(PMReportsConstants.USER_MOBILE_NUMBER, detailsModel.getPhoneNumber());
		mapModifications.add(PMReportsConstants.USER_PLATE_NUMBER, detailsModel.getLicensePlate());

		return mapModifications;
	}

}
