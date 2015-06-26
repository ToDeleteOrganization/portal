package com.evozon.evoportal.my_account.managers;

import java.util.Date;

import com.evozon.evoportal.my_account.model.DetailsModel;

public class DetailsManager extends AccountManager<DetailsModel> {

	public DetailsManager(DetailsModel oldModel, DetailsModel newModel) {
		super(oldModel, newModel);
	}

	public boolean isBirthdayDateChanged() {
		Date oldBirthday= getOldModel().getBirthdayDate();
		Date newBirthday = getNewModel().getBirthdayDate();
		return oldBirthday.compareTo(newBirthday) != 0;
	}

	public boolean isBuildingChanged() {
		return !getOldModel().getBuilding().equals(getNewModel().getBuilding());
	}

	public boolean isCNPChanged() {
		return !getOldModel().getCNP().equals(getNewModel().getCNP());
	}

	public boolean isDiplomaTitleChanged() {
		return !getOldModel().getDiplomaTitle().equals(getNewModel().getDiplomaTitle());
	}

	public boolean isEmailAddressChanged() {
		return !getOldModel().getEmailAddress().equals(getNewModel().getEmailAddress());
	}

	public boolean isFacultyChanged() {
		return !getOldModel().getFaculty().equals(getNewModel().getFaculty());
	}

	public boolean isFirstNameChanged() {
		return !getOldModel().getFirstName().equals(getNewModel().getFirstName());
	}
	
	public boolean isLastNameChanged() {
		return !getOldModel().getLastName().equals(getNewModel().getLastName());
	}
	
	public boolean isFunctieCIMChanged() {
		return !getOldModel().getFunctieCIM().equals(getNewModel().getFunctieCIM());
	}
	
	public boolean isJobTitleChanged() {
		return !getOldModel().getJobTitle().equals(getNewModel().getJobTitle());
	}
	
	public boolean isLicensePlateChanged() {
		return !getOldModel().getLicensePlate().equals(getNewModel().getLicensePlate());
	}
	
	public boolean isPhoneNumberChanged() {
		return !getOldModel().getPhoneNumber().equals(getNewModel().getPhoneNumber());
	}

	public boolean isAdditionalEmailAddressChanged() {
		return !getOldModel().getAdditionalEmailAddress().equals(getNewModel().getAdditionalEmailAddress());
	}

	public boolean isHideBirthdayChanged() {
		return !(getOldModel().isBirthdayHidden() == getNewModel().isBirthdayHidden());
	}

	public boolean isHidePhoneNumberChanged() {
		return !(getOldModel().isPhoneNumberHidden() == getNewModel().isPhoneNumberHidden());
	}

	public boolean isScreenNameChanged() {
		return !getOldModel().getScreenName().equals(getNewModel().getScreenName());
	}

	public boolean isSkypeChanged() {
		return !getOldModel().getSkype().equals(getNewModel().getSkype());
	}
	
	public boolean isUniversityChanged() {
		return !getOldModel().getUniversity().equals(getNewModel().getUniversity());
	}

	public boolean isStudentChanged() {
		return !(getOldModel().isStudent() == getNewModel().isStudent());
	}

}
