package com.evozon.evoportal.my_account;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.portlet.PortletSession;
import javax.portlet.RenderRequest;

import com.evozon.evoportal.familytableaccess.slayer.model.FamilyMember;
import com.evozon.evoportal.my_account.managers.UserFamilyHandler;
import com.evozon.evoportal.my_account.model.UserFamily;
import com.evozon.evoportal.my_account.util.MyAccountConstants;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.struts.BaseStrutsPortletAction;

public class AccountCustomStrutsPortletAction extends BaseStrutsPortletAction {

	protected void handleInvalidFamilyMembersEntries(RenderRequest renderRequest) {
		if (SessionErrors.contains(renderRequest, "hasFamilyErrors")) {
			PortletSession portletSession = renderRequest.getPortletSession();

			UserFamily oldFamily = (UserFamily) portletSession.getAttribute("oldFamily");
			UserFamily newFamily = (UserFamily) portletSession.getAttribute("newFamily");

			portletSession.removeAttribute("oldFamily");
			portletSession.removeAttribute("newFamily");

			handleSpouseErrors(newFamily.getSpouse(), renderRequest);
			handleChildrenErrors(oldFamily, newFamily, renderRequest);
		}
	}

	private void handleSpouseErrors(FamilyMember newSpouse, RenderRequest renderRequest) {
		renderRequest.setAttribute("spouseData", memberToMap(newSpouse));
	}

	private void handleChildrenErrors(UserFamily oldFamily, UserFamily newFamily, RenderRequest renderRequest) {

		UserFamilyHandler familyManager = new UserFamilyHandler(oldFamily, newFamily);
		// TODO: this is extremely ugly!!!
		List<Map<String, String>> addedMap = new ArrayList<Map<String, String>>();
		for (FamilyMember child : familyManager.getAddedChildren()) {
			addedMap.add(memberToMap(child));
		}
		if (!addedMap.isEmpty()) {
			renderRequest.setAttribute("newChildrenMap", addedMap);
		}

		for (FamilyMember child : familyManager.getUpdatedChildren()) {
			renderRequest.setAttribute(child.getMemberId() + "updatedChild", memberToMap(child));
		}
	}

	private Map<String, String> memberToMap(FamilyMember member) {
		Map<String, String> spouseToMap = new HashMap<String, String>();
		boolean isChild = (member.getType() == MyAccountConstants.CHILD);
		String preffix = isChild ? "child" : "spouse";

		spouseToMap.put(preffix + "FirstName", member.getFirstName());
		spouseToMap.put(preffix + "LastName", member.getLastName());
		spouseToMap.put(preffix + "CNP", member.getCNP());
		spouseToMap.put(preffix + "Id", String.valueOf(member.getMemberId()));

		return spouseToMap;
	}

	// private Map<String, String> createChildMemberMap(FamilyMember member) {
	// Map<String, String> childToMap = new HashMap<String, String>();
	// String key = String.valueOf(member.getMemberId());
	//
	// childToMap.put("childId", key);
	// childToMap.put("childFirstName", member.getFirstName());
	// childToMap.put("childLastName", member.getLastName());
	// childToMap.put("childCNP", member.getCNP());
	//
	// return childToMap;
	// }
	//
	// private Map<String, String> createSpouseMemberMap(FamilyMember member) {
	// Map<String, String> spouseToMap = new HashMap<String, String>();
	//
	// spouseToMap.put("spouseFirstName", member.getFirstName());
	// spouseToMap.put("spouseLastName", member.getLastName());
	// spouseToMap.put("spouseCNP", member.getCNP());
	//
	// return spouseToMap;
	// }

}
