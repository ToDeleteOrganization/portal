package com.evozon.evoportal.my_account;

import java.util.Collections;
import java.util.List;

import com.evozon.evoportal.my_account.model.DetailsModel;
import com.evozon.evoportal.my_account.model.EvoAddressModel;
import com.evozon.evoportal.my_account.model.FreeDaysModel;
import com.evozon.evoportal.my_account.model.UserFamily;

public class AccountModelHolder {

	private FreeDaysModel freeDaysModel;

	private DetailsModel detailsModel;

	private UserFamily userFamily;

	private List<EvoAddressModel> evoAdresses = Collections.emptyList();

	private List<String> userDepartments = Collections.emptyList();

	private String userType;

	public FreeDaysModel getFreeDaysModel() {
		return freeDaysModel;
	}

	public void setFreeDaysModel(FreeDaysModel freeDaysmodel) {
		this.freeDaysModel = freeDaysmodel;
	}

	public DetailsModel getDetailsModel() {
		return detailsModel;
	}

	public void setDetailsModel(DetailsModel detailsModel) {
		this.detailsModel = detailsModel;
	}

	public List<EvoAddressModel> getAddresses() {
		return evoAdresses;
	}

	public void setAdresses(List<EvoAddressModel> adresses) {
		this.evoAdresses = adresses;
	}

	public List<String> getUserDepartments() {
		return userDepartments;
	}

	public void setUserDepartments(List<String> userDepartments) {
		this.userDepartments = userDepartments;
	}

	public UserFamily getUserFamily() {
		return userFamily;
	}

	public void setUserFamily(UserFamily userFamily) {
		this.userFamily = userFamily;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public EvoAddressModel getPrimaryAddress() {
		EvoAddressModel primaryAddress = null;
		for (EvoAddressModel evoAddress : evoAdresses) {
			if (evoAddress.isPrimary()) {
				primaryAddress = evoAddress;
				break;
			}
		}
		return primaryAddress;
	}
}
