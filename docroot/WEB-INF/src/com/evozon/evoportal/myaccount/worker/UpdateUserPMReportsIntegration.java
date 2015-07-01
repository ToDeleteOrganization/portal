package com.evozon.evoportal.myaccount.worker;

import java.util.Calendar;

import org.springframework.util.MultiValueMap;

import com.evozon.evoportal.my_account.managers.DetailsManager;
import com.evozon.evoportal.my_account.managers.EvoAddressManager;
import com.evozon.evoportal.my_account.model.EvoAddressModel;
import com.evozon.evoportal.my_account.util.MyAccountUtil;
import com.evozon.evoportal.ws.pmreports.calls.PMReportsPersonCall;
import com.evozon.evoportal.ws.pmreports.model.PmResponseStatus;

public class UpdateUserPMReportsIntegration extends PMReportsIntegration {

	public PmResponseStatus  executeIntegration(PMReportsPersonCall pmPersonCall, MultiValueMap<String, Object> pmParams) {
		return pmPersonCall.updateUser(pmParams);
	}

	protected boolean hasPMReportsParamatersModified() {
		DetailsManager detailsManager = new DetailsManager(oldAccountModelHolder.getDetailsModel(), newAccountModelHolder.getDetailsModel());
		if (detailsManager.isEmailAddressChanged()) {
			return true;
		}
		if (detailsManager.isLastNameChanged()) {
			return true;
		}
		if (detailsManager.isFirstNameChanged()) {
			return true;
		}
		if (detailsManager.isLicensePlateChanged()) {
			return true;
		}
		if (detailsManager.isPhoneNumberChanged()) {
			return true;
		}

		Calendar oldStartDate = MyAccountUtil.resetToMidnight(oldAccountModelHolder.getFreeDaysModel().getStartDate());
		Calendar newStartDate = MyAccountUtil.resetToMidnight(newAccountModelHolder.getFreeDaysModel().getStartDate());
		if (oldStartDate.compareTo(newStartDate) != 0) {
			return true;
		}

		EvoAddressModel oldPrimaryAddress = oldAccountModelHolder.getPrimaryAddress();
		EvoAddressModel newPrimaryAddress = newAccountModelHolder.getPrimaryAddress();
		if ((oldPrimaryAddress == null) && (newPrimaryAddress == null)) {
			return false;

		} else if ((oldPrimaryAddress != null) && (newPrimaryAddress == null)) {
			return true;

		} else if ((oldPrimaryAddress == null) && (newPrimaryAddress != null)) {
			return true;
		}

		EvoAddressManager addressManager = new EvoAddressManager(oldPrimaryAddress, newPrimaryAddress);
		if (addressManager.isStreetNameChanged()) {
			return true;
		}

		if (addressManager.isStreetNumberChanged()) {
			return true;
		}

		if (addressManager.isCityChanged()) {
			return true;
		}

		if (addressManager.isCountryCodeChanged()) {
			return true;
		}

		return false;
	}

}
