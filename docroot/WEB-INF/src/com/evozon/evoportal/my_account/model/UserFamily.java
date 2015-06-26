package com.evozon.evoportal.my_account.model;

import java.io.Serializable;
import java.util.List;

import com.evozon.evoportal.familytableaccess.slayer.model.FamilyMember;

public class UserFamily implements IAccountModel, Serializable {

	/**
	 * Serial version
	 */
	private static final long serialVersionUID = 1L;

	private FamilyMember spouse;

	private List<FamilyMember> childrens;

	public FamilyMember getSpouse() {
		return spouse;
	}

	public void setSpouse(FamilyMember spouse) {
		this.spouse = spouse;
	}

	public List<FamilyMember> getChildrens() {
		return childrens;
	}

	public void setChildrens(List<FamilyMember> childrens) {
		this.childrens = childrens;
	}

}
