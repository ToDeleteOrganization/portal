package com.evozon.evoportal.my_account.pmreports.integration;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.evozon.evoportal.my_account.AccountModelHolder;
import com.evozon.evoportal.my_account.model.DetailsModel;
import com.evozon.evoportal.my_account.model.EvoAddressModel;
import com.evozon.evoportal.my_account.util.MyAccountUtil;
import com.evozon.evoportal.ws.pmreports.calls.PMReportsPersonCall;
import com.evozon.evoportal.ws.pmreports.model.PmResponseStatus;
import com.evozon.evoportal.ws.pmreports.util.PMReportsConstants;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.model.User;

public class AddUserPMReportsIntegration extends PMReportsIntegration {

	private static Log logger = LogFactoryUtil.getLog(AddUserPMReportsIntegration.class);

	public AddUserPMReportsIntegration(AccountModelHolder holder, long companyId, User addedUser) {
		super(companyId, addedUser);
		setNewAccountModelHolder(holder);
	}

	public PmResponseStatus executeIntegration() {
		PmResponseStatus pmresponse = null;

		if (hasPMReportsParamatersModified()) {
			MultiValueMap<String, Object> pmParams = getPMReportsParameters();

			try {
				// TODO: change this block
				PMReportsPersonCall pmPersonCall = new PMReportsPersonCall(companyId);
				pmresponse = pmPersonCall.addUser(pmParams);
			} catch (Exception e) {
				logger.error(e);
			}
		}

		return pmresponse;
	}

	protected boolean hasPMReportsParamatersModified() {
		// when adding always call pm reports services.
		return true;
	}

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
