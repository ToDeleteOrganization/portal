package com.evozon.evoportal.my_account.model;

import java.util.Date;

import com.liferay.portal.kernel.util.StringPool;

public class DetailsModel implements IAccountModel {

	private String screenName = StringPool.BLANK;

	private String emailAddress = StringPool.BLANK;

	private String firstName = StringPool.BLANK;

	private String lastName = StringPool.BLANK;

	private String building = StringPool.BLANK;

	private String jobTitle = StringPool.BLANK;

	private String functieCIM = StringPool.BLANK;

	private String cnp = StringPool.BLANK;

	private String licensePlate = StringPool.BLANK;

	private String skype = StringPool.BLANK;

	private String phoneNumber = StringPool.BLANK;

	private String additionalEmailAddress = StringPool.BLANK;

	private String university = StringPool.BLANK;

	private String faculty = StringPool.BLANK;

	private String diplomaTitle = StringPool.BLANK;

	private Date birthdayDate;

	private boolean isMale;

	private boolean isHideBirthday;

	private boolean isHidePhoneNumber;

	private boolean isMarried;

	private boolean isStudent;

	public String getScreenName() {
		return screenName;
	}

	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public boolean isMale() {
		return isMale;
	}

	public void setMale(boolean isMale) {
		this.isMale = isMale;
	}

	public String getBuilding() {
		return building;
	}

	public void setBuilding(String building) {
		this.building = building;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public String getFunctieCIM() {
		return functieCIM;
	}

	public void setFunctieCIM(String functieCIM) {
		this.functieCIM = functieCIM;
	}

	public String getCNP() {
		return cnp;
	}

	public void setCnp(String cnp) {
		this.cnp = cnp;
	}

	public String getLicensePlate() {
		return licensePlate;
	}

	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
	}

	public Date getBirthdayDate() {
		return birthdayDate;
	}

	public void setBirthdayDate(Date birthdayDate) {
		this.birthdayDate = birthdayDate;
	}

	public boolean isBirthdayHidden() {
		return isHideBirthday;
	}

	public void setBirthdayHidden(boolean isHideBirthday) {
		this.isHideBirthday = isHideBirthday;
	}

	public String getSkype() {
		return skype;
	}

	public void setSkype(String skype) {
		this.skype = skype;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAdditionalEmailAddress() {
		return additionalEmailAddress;
	}

	public void setAdditionalEmailAddress(String additionalEmailAddress) {
		this.additionalEmailAddress = additionalEmailAddress;
	}

	public boolean isPhoneNumberHidden() {
		return isHidePhoneNumber;
	}

	public void setPhoneNumberHidden(boolean isHidePhoneNumber) {
		this.isHidePhoneNumber = isHidePhoneNumber;
	}

	public boolean isMarried() {
		return isMarried;
	}

	public void setMarried(boolean isMarried) {
		this.isMarried = isMarried;
	}

	public boolean isStudent() {
		return isStudent;
	}

	public void setStudent(boolean isStudent) {
		this.isStudent = isStudent;
	}

	public String getUniversity() {
		return university;
	}

	public void setUniversity(String university) {
		this.university = university;
	}

	public String getFaculty() {
		return faculty;
	}

	public void setFaculty(String faculty) {
		this.faculty = faculty;
	}

	public String getDiplomaTitle() {
		return diplomaTitle;
	}

	public void setDiplomaTitle(String diplomaTitle) {
		this.diplomaTitle = diplomaTitle;
	}

}
