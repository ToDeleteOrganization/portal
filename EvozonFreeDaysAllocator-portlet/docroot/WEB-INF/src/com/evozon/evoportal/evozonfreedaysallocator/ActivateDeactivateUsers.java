package com.evozon.evoportal.evozonfreedaysallocator;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.evozon.evoportal.evozonfreedaysallocator.service.BenefitDayLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageListener;
import com.liferay.portal.kernel.messaging.MessageListenerException;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;

public class ActivateDeactivateUsers extends AllocatorScheduler implements MessageListener {

	private static final Logger log = Logger.getLogger(EvozonFreeDaysAllocator.class);

	public void receive(Message arg0) throws MessageListenerException {
		List<User> users = getUsersToSimulate();
		if (users.isEmpty()) {
			try {
				users = UserLocalServiceUtil.getUsers(QueryUtil.ALL_POS, QueryUtil.ALL_POS);
			} catch (SystemException e) {
				log.error(e);
			}
		}

		Calendar today = BenefitDayLocalServiceUtil.getUserSelectedDate();
		today.set(Calendar.HOUR_OF_DAY, 23); // make sure day time won't rise any issues
		Date todayDate = today.getTime();

		for (User user : users) {
			try {
				UserFreeDaysWrapper wrapper = new UserFreeDaysWrapper(user);
				Date statusChangeDate = wrapper.getCurrentStatusChangeDate();

				if ((statusChangeDate == null) || (todayDate.compareTo(statusChangeDate) < 0)) {
					continue;
				}

				int status = wrapper.getStatusWorkflowStatus();
				if ((WorkflowConstants.STATUS_INACTIVE == status) && user.isActive()) {
					UserLocalServiceUtil.updateStatus(user.getUserId(), WorkflowConstants.STATUS_INACTIVE);
					log.info(user.getFullName() + " has been deactivated.");

				} else if ((WorkflowConstants.STATUS_APPROVED == status) && !user.isActive()) {
					User updatedUser = UserLocalServiceUtil.updateStatus(user.getUserId(), WorkflowConstants.STATUS_APPROVED);
					recalculateFreeDaysSituationOnActivation(updatedUser);
					log.info(user.getFullName() + " has been reactivated.");

				}

			} catch (Exception e) {
				log.error("Job failed! Free days were not allocated!", e);
			}
		}
		log.info("Finish checking for users to activate/deactivate.");
	}

	private void recalculateFreeDaysSituationOnActivation(User user) {
		UserFreeDaysWrapper userExpando = new UserFreeDaysWrapper(user);
		Date statusChangeDate = userExpando.getCurrentStatusChangeDate();

		userExpando.setDateHired(statusChangeDate);
		userExpando.setCIMStartDate(statusChangeDate);

		// this must be called after setDateHired, or use
		// getUserLegalFreeDaysInDate()
		int newLegalFreeDays = BonusDaysComputer.getUserLegalFreeDays(userExpando.getUser());
		userExpando.setFreeDaysInCurrentYear(newLegalFreeDays);
		userExpando.setLegalVacationDays(newLegalFreeDays);
		userExpando.setFreeDaysFromLastYear(0);
		userExpando.setRemainingFreeDaysFromLastYear(0);

		userExpando.setExtractedFreeDaysFromCurrentYear(0);
		userExpando.setExtractedFreeDaysFromLastYear(0);

		userExpando.setExtraDays(0);
		// TODO: ED from 'benefitday_benefitday' table, should be treated as
		// well
	}
}
