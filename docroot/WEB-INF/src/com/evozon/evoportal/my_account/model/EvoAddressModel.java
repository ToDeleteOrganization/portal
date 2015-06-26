package com.evozon.evoportal.my_account.model;

import com.liferay.portal.kernel.util.StringPool;

public class EvoAddressModel implements IAccountModel {

	private String city = StringPool.BLANK;

	private String streetName = StringPool.BLANK;

	private String streetNumber = StringPool.BLANK;

	private String postalCode = StringPool.BLANK;

	private String countryCode = StringPool.BLANK;

	private long type;

	private long region;

	private boolean isPrimary;

	private boolean isMailing;

	public boolean isPrimary() {
		return isPrimary;
	}

	public void setPrimary(boolean isPrimary) {
		this.isPrimary = isPrimary;
	}

	public boolean isMailing() {
		return isMailing;
	}

	public void setMailing(boolean isMailing) {
		this.isMailing = isMailing;
	}

	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public long getType() {
		return type;
	}

	public void setType(long type) {
		this.type = type;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStreetNumber() {
		return streetNumber;
	}

	public void setStreetNumber(String streetNumber) {
		this.streetNumber = streetNumber;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public long getRegionId() {
		return region;
	}

	public void setRegion(long region) {
		this.region = region;
	}

}
