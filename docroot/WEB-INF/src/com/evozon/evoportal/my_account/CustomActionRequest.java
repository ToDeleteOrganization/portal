package com.evozon.evoportal.my_account;

import java.util.HashSet;
import java.util.Set;

import javax.portlet.ActionRequest;
import javax.portlet.filter.ActionRequestWrapper;

import com.evozon.evoportal.my_account.util.MyAccountConstants;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.StringUtil;

public class CustomActionRequest extends ActionRequestWrapper {

	private static Log log = LogFactoryUtil.getLog(CustomActionRequest.class);
	
	private static final String DELIMITER = ",";
	
	private String cmd;
	
	private Set<Long> invalidUserDeactivatedIds = new HashSet<Long>();

	public CustomActionRequest(ActionRequest request) {
		super(request);
	}
	
	public CustomActionRequest(ActionRequest request, String cmd, Set<Long> invalidUsersDeactivatedIds) {
		this(request);
		this.cmd = cmd;
		this.invalidUserDeactivatedIds = invalidUsersDeactivatedIds;
	}

	public String getParameter(String paramName) {
		String parameter = super.getParameter(paramName);
		if (Constants.DEACTIVATE.equals(cmd) && MyAccountConstants.DELETED_IDS.equals(paramName)) {
			log.debug("Filter deactivated users [" + parameter + "] against: " + invalidUserDeactivatedIds);
			parameter = filterInvalidUserDeactivation(parameter);
		}
		return parameter;
	}

	private String filterInvalidUserDeactivation(String deletedUserParameters) {
		StringBuilder deactivatedUsers = new StringBuilder();
		long[] usersId = StringUtil.split(deletedUserParameters, 0L);

		for (long userId : usersId) {
			if (!invalidUserDeactivatedIds.contains(userId)) {
				if (!deactivatedUsers.toString().isEmpty()) {
					deactivatedUsers.append(DELIMITER);
				}
				deactivatedUsers.append(userId);
			}
		}
		log.debug("Returning filtered users: " + deactivatedUsers.toString());
		return deactivatedUsers.toString();
	}
	
}
