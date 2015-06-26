package com.evozon.evoportal.myaccount.builder;

import java.util.Date;

import javax.portlet.PortletRequest;

import com.evozon.evoportal.my_account.model.FreeDaysModel;


public abstract class CreateAccountModelHolderBuilder extends RequestAccountModelHolderBuilder {

	public CreateAccountModelHolderBuilder(PortletRequest request) {
		super(request);
	}

	public CreateAccountModelHolderBuilder buildFreeDaysModel() {
		FreeDaysModel freeDaysModel = super.buildFreeDaysModelInternal();
		freeDaysModel.setCimStartDate(getCIMStartDate());
		freeDaysModel.setStartDate(getStartDate());
		freeDaysModel.setInternshipStartDate(getInternshipStartDate());
		accountModelHolder.setFreeDaysModel(freeDaysModel);
		return this;
	}

	public CreateAccountModelHolderBuilder buildDetailsModel() {
		accountModelHolder.setDetailsModel(super.buildDetailsModelInternal());
		return this;
	}

	public CreateAccountModelHolderBuilder buildAddresses() {
		return this;
	}

	public CreateAccountModelHolderBuilder buildUserFamily() {
		return this;
	}

	public CreateAccountModelHolderBuilder buildUserDepartments() {
		accountModelHolder.setUserDepartments(super.getUserDepartmentsFromRequest());
		return this;
	}

	protected abstract Date getCIMStartDate();

	protected abstract Date getInternshipStartDate();

	protected Date getStartDate() {
		return super.getDateFromRequest("hired");
	}
}
