package com.evozon.evoportal.my_account.managers;

import java.util.ArrayList;
import java.util.List;

import com.evozon.evoportal.familytableaccess.slayer.model.FamilyMember;
import com.evozon.evoportal.my_account.model.UserFamily;

public final class UserFamilyHandler extends AccountManager<UserFamily> {

	public UserFamilyHandler(UserFamily oldModel, UserFamily newModel) {
		super(oldModel, newModel);
	}

	public boolean isSpouseAdded() {
		FamilyMember oldSpouse = getOldSpouse();
		FamilyMember newSpouse = getNewSpouse();

		return (oldSpouse == null) && (newSpouse != null);
	}

	public boolean isSpouseUpdated() {
		boolean isUpdated = false;
		FamilyMember oldSpouse = getOldSpouse();
		FamilyMember newSpouse = getNewSpouse();

		if ((oldSpouse != null) && (newSpouse != null)) {
			isUpdated = !areSameFamilyMembers(oldSpouse, newSpouse);
		}

		return isUpdated;
	}

	public boolean isSpouseRemoved() {
		FamilyMember oldSpouse = getOldSpouse();
		FamilyMember newSpouse = getNewSpouse();

		return (oldSpouse != null) && (newSpouse == null);
	}

	public List<FamilyMember> getAddedChildren() {
		List<FamilyMember> newChildrens = getNewChildren();
		List<FamilyMember> oldChildrens = getOldChildren();

		List<FamilyMember> addedChildren = new ArrayList<FamilyMember>(newChildrens);
		if (oldChildrens != null) {
			addedChildren.removeAll(oldChildrens);
		}

		return addedChildren;
	}

	public List<FamilyMember> getRemovedChildrens() {
		List<FamilyMember> removedChildren = new ArrayList<FamilyMember>();

		List<FamilyMember> oldChildrens = getOldChildren();
		List<FamilyMember> newChildrens = getNewChildren();

		if (oldChildrens != null) {
			removedChildren.addAll(oldChildrens);
			removedChildren.removeAll(newChildrens);
		}

		return removedChildren;
	}

	public List<FamilyMember> getUpdatedChildren() {
		List<FamilyMember> updatedChildren = new ArrayList<FamilyMember>();

		List<FamilyMember> newChildrens = getNewChildren();

		for (FamilyMember newChild : newChildrens) {
			boolean isChildUpdated = isChildUpdated(newChild);
			if (isChildUpdated) {
				updatedChildren.add(newChild);
			}
		}

		return updatedChildren;
	}

	private boolean isChildUpdated(FamilyMember childMember) {
		boolean isUpdated = false;
		List<FamilyMember> oldChildren = getOldChildren();

		if ((oldChildren != null) && oldChildren.contains(childMember)) {

			int index = oldChildren.indexOf(childMember);
			if (index != -1) {
				FamilyMember oldChild = oldChildren.get(index);
				isUpdated = !areSameFamilyMembers(oldChild, childMember);
			}

		}
		return isUpdated;
	}

	private boolean areSameFamilyMembers(FamilyMember firstMember, FamilyMember secondMember) {
		boolean equals = true;

		equals &= firstMember.getFirstName().equalsIgnoreCase(secondMember.getFirstName());
		equals &= firstMember.getLastName().equalsIgnoreCase(secondMember.getLastName());
		equals &= firstMember.getCNP().equalsIgnoreCase(secondMember.getCNP());

		return equals;
	}

	public static boolean isFamilyMemberEmpty(FamilyMember member) {
		boolean isEmpty = true;

		isEmpty &= member.getFirstName().isEmpty();
		isEmpty &= member.getLastName().isEmpty();
		isEmpty &= member.getCNP().isEmpty();

		return isEmpty;
	}

	public FamilyMember getOldSpouse() {
		UserFamily oldFamily = getOldModel();
		return (oldFamily != null) ? getOldModel().getSpouse() : null;
	}

	public FamilyMember getNewSpouse() {
		return getNewModel().getSpouse();
	}

	private List<FamilyMember> getOldChildren() {
		UserFamily oldFamily = getOldModel();
		return (oldFamily != null) ? oldFamily.getChildrens() : null;
	}

	private List<FamilyMember> getNewChildren() {
		return getNewModel().getChildrens();
	}

}
