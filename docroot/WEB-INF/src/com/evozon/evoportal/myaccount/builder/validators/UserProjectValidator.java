package com.evozon.evoportal.myaccount.builder.validators;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.PortletRequest;

import com.evozon.evoportal.evozonprojects.model.ProjectGroup;
import com.evozon.evoportal.evozonprojects.service.ProjectGroupLocalServiceUtil;
import com.evozon.evoportal.my_account.CustomActionRequest;
import com.evozon.evoportal.my_account.command.DefaultCommand;
import com.evozon.evoportal.my_account.util.MyAccountConstants;
import com.evozon.evoportal.myaccount.builder.ActionValidationResult;
import com.evozon.evoportal.myaccount.builder.ValidationResult;
import com.evozon.evoportal.myaccount.builder.Validator;
import com.liferay.portal.UserActiveException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.util.PortalUtil;
import com.sun.corba.se.impl.orbutil.closure.Constant;

public class UserProjectValidator extends AbstractValidator {

	private static final Log logger = LogFactoryUtil.getLog(UserProjectValidator.class);

	private static final String CANNOT_DEACTIVATE_PROJECT_MEMBERS_KEY = "cannot-delete-or-deactivate-a-project-member";

	private final PortletRequest request;

	public UserProjectValidator(ActionRequest request) {
		this.request = request;
	}

	public ValidationResult validate() {
		final ActionValidationResult res = new ActionValidationResult();

		try {
			final Map<Long, String> projectUsers = new HashMap<Long, String>();

			User currentUser = PortalUtil.getUser(request);
			long currentUserId = currentUser.getUserId();

			for (User user : getSelectedUsers()) {
				long userId = user.getUserId();
				if (currentUserId != userId) {
					List<ProjectGroup> userProjects = ProjectGroupLocalServiceUtil.findProjectsByUser(userId);
					if (!userProjects.isEmpty()) {
						logger.warn("The user with the id " + userId + " is part of prjects: " + userProjects + ", and cannot be deactivated.");
						projectUsers.put(userId, user.getFullName());
					}
				} else {
					logger.warn("The user cannot deactivate himself.");
				}
			}

			if (!projectUsers.isEmpty()) {
				logger.debug("Project members can't be deactivated: " + projectUsers.values());

				String errMsg = getBundleMessage(request, CANNOT_DEACTIVATE_PROJECT_MEMBERS_KEY) + projectUsers.values();
				res.addError(buildValidationMessage(errMsg, UserActiveException.class));
				// actionRequest = new CustomActionRequest(actionRequest,
				// Constants.DEACTIVATE, projectUsers.keySet());
			}
		} catch (PortalException e) {
			logger.error(e);
		} catch (SystemException e) {
			logger.error(e);
		}

		return res;
	}

	private List<User> getSelectedUsers() {
		String idsStr = ParamUtil.getString(request, MyAccountConstants.DELETED_IDS);
		long[] usersId = StringUtil.split(idsStr, 0L);

		List<User> selectedUsers = new ArrayList<User>();
		for (long userId : usersId) {
			try {
				User user = UserLocalServiceUtil.getUser(userId);
				selectedUsers.add(user);
			} catch (Exception e) {
				logger.error(e);
			}
		}

		return selectedUsers;
	}

	protected String getCategory() {
		return Constants.DEACTIVATE;
	}

}
