package com.evozon.evoportal.evozonfreedaysallocator;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.evozon.evoportal.evozonfreedaysallocator.service.BenefitDayLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.util.bridges.mvc.MVCPortlet;
import com.liferay.util.portlet.PortletProps;

public class AllocatorScheduler extends MVCPortlet {

	private static final Logger log = Logger.getLogger(AllocatorScheduler.class);

	private static final String ADMINISTRATOR_ROLE_NAME = "Administrator";

	private static final String ACCOUNTING_ROLE_NAME = "Accounting";

	private static final String MONTHLY_BONUSES_RECEIVER_ROLE_NAME = "Monthly Bonuses Receiver";

	protected List<User> getUsersToSimulate() {
		List<User> usersToSimulate = new ArrayList<User>();

		if (!BenefitDayLocalServiceUtil.hasSimulateMode()) {
			return usersToSimulate;
		}

		String usersIdStr = PortletProps.get("simulated-users-id");
		String[] userIdsStr = usersIdStr.split(",");

		for (String userIds : userIdsStr) {
			try {
				long userId = Long.parseLong(userIds);
				User u = UserLocalServiceUtil.getUser(userId);
				usersToSimulate.add(u);
			} catch (Exception e) {
				log.error("Failed getting user with id: " + userIds);
			}
		}

		if (BenefitDayLocalServiceUtil.hasSimulateMode()) {
			log.info("Simulated mode activated");
			if (!usersToSimulate.isEmpty()) {
				log.info("Simulating users: " + usersToSimulate);
			} else {
				log.info("No user selected, simulating all users with selected date.");
			}
		}

		try {
			usersToSimulate = usersToSimulate.isEmpty() ? UserLocalServiceUtil.getUsers(QueryUtil.ALL_POS, QueryUtil.ALL_POS) : usersToSimulate;
		} catch (SystemException e) {
			log.error("Failed to get all users, returning an empty list [" + usersToSimulate + "].", e);
		}

		return usersToSimulate;
	}

	protected List<User> getAccountingRegularUsers() throws SystemException, PortalException {
		List<User> result = new ArrayList<User>();
		for (User accounting : UserLocalServiceUtil.getUsers(QueryUtil.ALL_POS, QueryUtil.ALL_POS)) {
			if (isAccounting(accounting) && !isAdmin(accounting)) {
				// get all Accounting Users
				result.add(accounting);
			}
		}
		return result;
	}

	protected List<User> getMonthlyBonusesReceivers() throws SystemException, PortalException {
		List<User> result = new ArrayList<User>();
		for (User accounting : UserLocalServiceUtil.getUsers(QueryUtil.ALL_POS, QueryUtil.ALL_POS)) {
			if (isMonthlyBonusesReceiver(accounting)) {
				result.add(accounting);
			}
		}
		return result;
	}

	private boolean isMonthlyBonusesReceiver(User user) throws PortalException, SystemException {
		return hasRole(MONTHLY_BONUSES_RECEIVER_ROLE_NAME, user.getRoles());
	}

	private boolean isAdmin(User user) throws PortalException, SystemException {
		return hasRole(ADMINISTRATOR_ROLE_NAME, user.getRoles());
	}

	private boolean isAccounting(User user) throws PortalException, SystemException {
		return hasRole(ACCOUNTING_ROLE_NAME, user.getRoles());
	}

	private boolean hasRole(String roleName, List<Role> roles) throws PortalException, SystemException {
		for (Role role : roles) {
			if (roleName.equalsIgnoreCase(role.getName())) {
				return true;
			}
		}
		return false;
	}
}
