package com.evozon.evoportal.my_account.validator;

import javax.portlet.ActionRequest;

import com.evozon.evoportal.familytableaccess.slayer.model.FamilyMember;
import com.evozon.evoportal.my_account.util.MyAccountConstants;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.StringPool;

public class FamilyMemberValidator extends AccountValidator {

	private static final String EMPTY_FIRST_NAME_ERROR_KEY = "empty-first-name-error";

	private static final String EMPTY_LAST_NAME_ERROR_KEY = "empty-last-name-error";

	private static final String INVALID_FIRST_NAME_ERROR_KEY = "invalid-first-name-error";

	private static final String INVALID_LAST_NAME_ERROR_KEY = "invalid-last-name-error";

	private static final String INVALID_CNP_ERROR_KEY = "invalid-personal-identification-number-error";

	private static final String INVALID_CNP_SIZE_ERROR_KEY = "invalid-personal-identification-number-size-error";

	private ActionRequest actionRequest;

	private String existingMemberId = "";

	private int memberType;

	public FamilyMemberValidator(ActionRequest actionRequest) {
		this.actionRequest = actionRequest;
	}

	public void setExistingMemberId(String existingMemberId) {
		this.existingMemberId = existingMemberId;
	}

	public boolean validate(FamilyMember familyMember) {
		boolean isValid = true;
		memberType = familyMember.getType();

		boolean isFirstNameValid = checkFirstName(familyMember.getFirstName().trim());
		isValid &= isFirstNameValid;

		boolean isLastNameValid = checkLastName(familyMember.getLastName().trim());
		isValid &= isLastNameValid;

		boolean isCNPValid = checkCNP(familyMember.getCNP().trim());
		isValid &= isCNPValid;

		return isValid;
	}

	private boolean checkCNP(String cnp) {
		boolean isCNPValid = true;

		if (!isNumericInput(cnp)) {
			String errMsg = getMemberKey() + INVALID_CNP_ERROR_KEY;
			SessionErrors.add(actionRequest, errMsg);
			isCNPValid = false;
		}

		if (cnp.length() != 13) {
			String errMsg = getMemberKey() + INVALID_CNP_SIZE_ERROR_KEY;
			SessionErrors.add(actionRequest, errMsg);
			isCNPValid = false;
		}
		return isCNPValid;
	}

	private boolean checkLastName(String lastName) {
		boolean isLastNameValid = true;
		lastName = lastName.replace("-", "");

		if (isEmptyInput(lastName)) {
			String errMsg = getMemberKey() + EMPTY_LAST_NAME_ERROR_KEY;
			SessionErrors.add(actionRequest, errMsg);
			isLastNameValid = false;

		} else if (!isAlphaSpaceStringInput(lastName)) {
			String errMsg = getMemberKey() + INVALID_LAST_NAME_ERROR_KEY;
			SessionErrors.add(actionRequest, errMsg);
			isLastNameValid = false;
		}

		return isLastNameValid;
	}

	private boolean checkFirstName(String firstName) {
		boolean isFirstNameValid = true;
		firstName = firstName.replace("-", "");
		firstName = firstName.replace(" ", StringPool.BLANK);
		
		if (isEmptyInput(firstName)) {
			String errMsg = getMemberKey() + EMPTY_FIRST_NAME_ERROR_KEY;
			SessionErrors.add(actionRequest, errMsg);
			isFirstNameValid = false;

		} else if (!isAlphaSpaceStringInput(firstName)) {
			String errMsg = getMemberKey() + INVALID_FIRST_NAME_ERROR_KEY;
			SessionErrors.add(actionRequest, errMsg);
			isFirstNameValid = false;
		}

		return isFirstNameValid;
	}

	// TODO: remove 'existingMemberId', use a counter
	private String getMemberKey() {
		String memberKey = (memberType == MyAccountConstants.SPOUSE) ? "spouse-" : "child-";
		if (!existingMemberId.isEmpty()) {
			memberKey = existingMemberId + memberKey;
		}
		return memberKey;
	}
}
