package com.evozon.evoportal.myaccount.builder;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.portlet.ActionRequest;

import com.evozon.evoportal.evozonfreedaysallocator.EvoportalEmailUtil;
import com.evozon.evoportal.my_account.managers.FreeDaysManager;
import com.evozon.evoportal.my_account.model.FreeDaysModel;
import com.evozon.evoportal.my_account.util.EvoportalUserUtil;
import com.evozon.evoportal.my_account.util.MyAccountConstants;
import com.evozon.evoportal.my_account.util.MyAccountRequestUtil;
import com.evozon.evoportal.my_account.wrapper.UserExpandoWrapper;
import com.evozon.evoportal.myaccount.worker.ActionWorker;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.model.User;

public class UpdateUserExpandoProperties extends ActionWorker {

	private static final Log logger = LogFactoryUtil.getLog(UpdateUserExpandoProperties.class);

	public void execute(AbstractAccountActionOperation operation) throws Exception {
		User selectedUser = operation.getSelectedUser();

		FreeDaysManager freeDaysManager = new FreeDaysManager(oldAccountModelHolder.getFreeDaysModel(), newAccountModelHolder.getFreeDaysModel(), selectedUser);
		if (freeDaysManager.isFreeDaysFromLastYearChanged()) {
			freeDaysManager.executeFreeDaysFromLastYearChange();
		}

		if (freeDaysManager.isFreeDaysInCurrentYearChanged()) {
			freeDaysManager.executeFreeDaysInCurrentYearChange();
		}

		if (freeDaysManager.isDateHiredChanged()) {
			freeDaysManager.executeStartDateChange();
		}

		if (freeDaysManager.isStartingBonusDaysChanged()) {
			freeDaysManager.executeStartingBonusDaysChange();
		}

		if (freeDaysManager.isExtraDaysNumberChanged()) {
			freeDaysManager.executeExtraDaysChange();
			executeExtraDaysEmailNotification(freeDaysManager);
		}

		if (freeDaysManager.isCommentsChanged()) {
			freeDaysManager.executeCommentChange();
		}

		if (freeDaysManager.isCIMStartDateChanged()) {
			updateUserCIMStartDate(selectedUser);
		}

		if (freeDaysManager.isInternshipStartDateChanged()) {
			updateUserInternshipChangeDate(selectedUser, operation.getActionRequest());
		}
	}

	protected void updateUserInternshipChangeDate(User selectedUser, ActionRequest request) {
		try {
			UserExpandoWrapper userExpando = new UserExpandoWrapper(selectedUser);

			String prevJobType = oldAccountModelHolder.getDetailsModel().getFunctieCIM();
			String currentJobType = userExpando.getUserJob();

			// if the previous job type was 'Intern'
			boolean isChangedFromIntern = prevJobType.contains("intern");
			isChangedFromIntern &= !currentJobType.contains("intern");

			Calendar newInternshipStart = Calendar.getInstance();
			if (!isChangedFromIntern) {
				Date internshipStart = MyAccountRequestUtil.getInternshipStartDateFromRequest(request);
				newInternshipStart.setTime(internshipStart);
			}
			userExpando.setInternshipStartDate(newInternshipStart.getTime());
		} catch (Exception e) {
			logger.warn("The Internship Start Date couldn't be updated. Message: " + e.getMessage());
		}
	}

	protected void updateUserCIMStartDate(User selectedUser) {
		try {
			UserExpandoWrapper userExpando = new UserExpandoWrapper(selectedUser);

			String prevJobType = oldAccountModelHolder.getDetailsModel().getFunctieCIM();
			String currentJobType = userExpando.getUserJob();

			// if the previous job type was 'Bursier'/'Bursier cu beneficii'
			boolean isChangedToCIM = prevJobType.contains(MyAccountConstants.SCHOLARSHIP);
			isChangedToCIM &= !currentJobType.contains(MyAccountConstants.SCHOLARSHIP);

			// if the Job Type has changed from 'Bursier'
			isChangedToCIM &= !currentJobType.equalsIgnoreCase(prevJobType);

			Calendar newCIMStartDate = Calendar.getInstance();
			if (!isChangedToCIM) {
				FreeDaysModel freeDaysModel = newAccountModelHolder.getFreeDaysModel();
				newCIMStartDate.setTime(freeDaysModel.getCimStartDate());
			}
			userExpando.setCIMStartDate(newCIMStartDate.getTime());
		} catch (Exception e) {
			logger.warn("The CIM Start Date couldn't be updated. Message: " + e.getMessage());
		}
	}

	private void executeExtraDaysEmailNotification(FreeDaysManager freeDaysManager) {
		try {
			Set<User> notifiedUsers = new HashSet<User>();
			notifiedUsers.addAll(EvoportalUserUtil.getAccountingRegularUsers());
			notifiedUsers.addAll(EvoportalUserUtil.getHRRegularUsers());

			String extraDaysDescription = newAccountModelHolder.getFreeDaysModel().getExtraDaysDescription();
			int extraDaysCount = freeDaysManager.getNewModel().getExtraDaysCount();
			// send mails to all intended users
			EvoportalEmailUtil.sendMailExtraDays(freeDaysManager.getUser(), new ArrayList<User>(notifiedUsers), extraDaysDescription, extraDaysCount);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
