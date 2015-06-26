package com.evozon.evoportal.my_account.managers;

import com.evozon.evoportal.my_account.model.EvoAddressModel;

public class EvoAddressManager extends AccountManager<EvoAddressModel> {

	public EvoAddressManager(EvoAddressModel oldModel, EvoAddressModel newModel) {
		super(oldModel, newModel);
	}

	public boolean isStreetNameChanged() {
		return !getOldModel().getStreetName().equals(getNewModel().getStreetName());
	}

	public boolean isStreetNumberChanged() {
		return !getOldModel().getStreetNumber().equals(getNewModel().getStreetNumber());
	}

	public boolean isTypeChanged() {
		return getOldModel().getType() != getNewModel().getType();
	}

	public boolean isPostalCodeChanged() {
		return !getOldModel().getPostalCode().equals(getNewModel().getPostalCode());
	}

	public boolean isCityChanged() {
		return !getOldModel().getCity().equals(getNewModel().getCity());
	}

	public boolean isCountryCodeChanged() {
		return !getOldModel().getCountryCode().equals(getNewModel().getCountryCode());
	}

	public boolean isRegionIdChanged() {
		return getOldModel().getRegionId() != getNewModel().getRegionId(); 
	}
}
