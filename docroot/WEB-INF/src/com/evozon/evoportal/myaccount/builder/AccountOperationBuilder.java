package com.evozon.evoportal.myaccount.builder;

import java.util.ArrayList;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;

import com.evozon.evoportal.my_account.AccountModelHolder;
import com.evozon.evoportal.my_account.command.AccountActionCommand;
import com.evozon.evoportal.my_account.command.ActivateUsersCommand;
import com.evozon.evoportal.my_account.command.AddAccountCommand;
import com.evozon.evoportal.my_account.command.DeactivateUsersCommand;
import com.evozon.evoportal.my_account.command.DefaultCommand;
import com.evozon.evoportal.my_account.command.DeleteUserCommand;
import com.evozon.evoportal.my_account.command.UpdateAccountCommand;
import com.evozon.evoportal.my_account.model.DetailsModel;
import com.evozon.evoportal.my_account.model.FreeDaysModel;
import com.evozon.evoportal.my_account.util.MyAccountConstants;
import com.evozon.evoportal.myaccount.builder.validators.BirthdayValidator;
import com.evozon.evoportal.myaccount.builder.validators.CNPContentValidator;
import com.evozon.evoportal.myaccount.builder.validators.CNPDuplicateValidator;
import com.evozon.evoportal.myaccount.builder.validators.DateHiredValidator;
import com.evozon.evoportal.myaccount.builder.validators.DeleteAccountPermissionValidator;
import com.evozon.evoportal.myaccount.builder.validators.EmailAddressValidator;
import com.evozon.evoportal.myaccount.builder.validators.FirstNameValidator;
import com.evozon.evoportal.myaccount.builder.validators.FunctieCIMValidator;
import com.evozon.evoportal.myaccount.builder.validators.JobTitleValidator;
import com.evozon.evoportal.myaccount.builder.validators.LastNameValidator;
import com.evozon.evoportal.myaccount.builder.validators.LicensePlateValidator;
import com.evozon.evoportal.myaccount.builder.validators.PhoneNumberValidator;
import com.evozon.evoportal.myaccount.builder.validators.ScreenNameValidator;
import com.evozon.evoportal.myaccount.builder.validators.SiteValidator;
import com.evozon.evoportal.myaccount.builder.validators.UserProjectValidator;
import com.liferay.compat.portal.util.PortalUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.struts.StrutsPortletAction;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.model.User;

public final class AccountOperationBuilder {

	public static final String DEFAULT = "DEFAULT";

	private static final String INTERNSHIP_USER_TYPE = "internship";

	private static final String SCHOLARSHIP_USER_TYPE = "scholarship";

	private static final String USER_TYPE = "user_type";

	public static ActionAccountOperation buildAccountActionOperation(ActionPhaseParameters app) throws PortalException, SystemException {
		ActionAccountOperation operation = null;

		String command = ParamUtil.getString(app.getRequest(), Constants.CMD, AccountOperationBuilder.DEFAULT);
		if (Constants.ADD.equals(command)) {
			operation = AccountOperationBuilder.buildAddAccountActionOperation(app);

		} else if (Constants.UPDATE.equals(command)) {
			operation = AccountOperationBuilder.buildUpdateAccountActionOperation(app);

		} else if (Constants.DEACTIVATE.equals(command) || MyAccountConstants.CANCEL_DEACTIVATION.equals(command)) {
			operation = AccountOperationBuilder.buildDeactivateAccountOperation(app);

		} else if (Constants.RESTORE.equals(command) || MyAccountConstants.CANCEL_ACTIVATION.equals(command)) {
			operation = AccountOperationBuilder.buildActivateAccountOperation(app);

		} else if (Constants.DELETE.equals(command)) {
			operation = AccountOperationBuilder.buildDeleteAccountActionOperation(app);

		} else {
			operation = new DefaultAccountActionOperation(app);

		}

		return operation;
	}

	private static ActionAccountOperation buildActivateAccountOperation(ActionPhaseParameters app) {
		ManagementAccountActionOperation restoreAccountCommandOperation = new ActivateAccountOperation(app);
		return restoreAccountCommandOperation;
	}

	private static ActionAccountOperation buildDeactivateAccountOperation(ActionPhaseParameters app) throws PortalException, SystemException {
		ManagementAccountActionOperation deactivateAccountCommandOperation = new DeactivateAccountOperation(app);		
		deactivateAccountCommandOperation.addValidationRule(new UserProjectValidator(app.getRequest()));
		return deactivateAccountCommandOperation;
	}

	private static ActionAccountOperation buildDeleteAccountActionOperation(ActionPhaseParameters app) throws PortalException, SystemException {
		ManagementAccountActionOperation deleteAccountCommandOperation = new DeleteAccountOperation(app);
		User currentUser = PortalUtil.getUser(app.getRequest());
		deleteAccountCommandOperation.addValidationRule(new DeleteAccountPermissionValidator(currentUser));
		return deleteAccountCommandOperation;
	}

	private static ActionAccountOperation buildUpdateAccountActionOperation(final ActionPhaseParameters app) throws PortalException, SystemException {
		ManagementAccountActionOperation updateAccountCommandOperation = new UpdateAccountOperation(app);

		User selectedUser = PortalUtil.getSelectedUser(app.getRequest());

		AccountModelHolderStrategy accountFactory = new UpdateAccountModelHolderStrategy();
		accountFactory.setNewAccountModelHolderBuilder(new RequestAccountModelHolderBuilder(app.getRequest()));
		accountFactory.setOldAccountModelHolderBuilder(new UserAccountModelHolderBuilder(selectedUser));

		AccountModelHolder newAccountHolder = accountFactory.buildNewAccountModelHolder();
		updateAccountCommandOperation.setOldAccountModelHolder(accountFactory.buildOldAccountModelHolder());
		updateAccountCommandOperation.setNewAccountModelHolder(newAccountHolder);

		// TODO: customize validators for update operation
		updateAccountCommandOperation.addValidationRules(AccountOperationBuilder.getAddAccountValidators(newAccountHolder));

		return updateAccountCommandOperation;
	}

	// TODO: add familyValidation for update
	private static ActionAccountOperation buildAddAccountActionOperation(final ActionPhaseParameters app) {
		ManagementAccountActionOperation addAccountCommandOperation = new AddAccountOperation(app);

		AccountModelHolderStrategy accountModelFactory = AccountOperationBuilder.createAddAccountBuilder(app.getRequest());
		AccountModelHolder newAccountModel = accountModelFactory.buildNewAccountModelHolder();

		addAccountCommandOperation.setNewAccountModelHolder(newAccountModel);
		addAccountCommandOperation.addValidationRules(AccountOperationBuilder.getAddAccountValidators(newAccountModel));

		return addAccountCommandOperation;
	}

	private static List<Validator> getAddAccountValidators(final AccountModelHolder newAccountModel) {
		List<Validator> validators = new ArrayList<Validator>();

		DetailsModel detailsModel = newAccountModel.getDetailsModel();
		FreeDaysModel freeDaysModel = newAccountModel.getFreeDaysModel();

		validators.add(new ScreenNameValidator(detailsModel.getScreenName()));
		validators.add(new EmailAddressValidator(detailsModel.getEmailAddress()));
		validators.add(new LastNameValidator(detailsModel.getLastName()));
		validators.add(new FirstNameValidator(detailsModel.getFirstName()));
		validators.add(new LicensePlateValidator(detailsModel.getLicensePlate()));
		validators.add(new PhoneNumberValidator(detailsModel.getPhoneNumber()));
		validators.add(new BirthdayValidator(freeDaysModel.getStartDate(), detailsModel.getBirthdayDate()));
		validators.add(new CNPContentValidator(detailsModel.getCNP()));
		validators.add(new CNPDuplicateValidator(detailsModel.getCNP()));
		validators.add(new FunctieCIMValidator(detailsModel.getFunctieCIM()));
		validators.add(new DateHiredValidator(freeDaysModel.getStartDate()));
		validators.add(new JobTitleValidator(detailsModel.getJobTitle()));
		validators.add(new SiteValidator(newAccountModel.getUserDepartments()));

		return validators;
	}

	private static AccountModelHolderStrategy createAddAccountBuilder(ActionRequest request) {
		AccountModelHolderStrategy accountFactory = new AddAccountModelHolderStrategy();

		String userType = ParamUtil.getString(request, AccountOperationBuilder.USER_TYPE);
		if (AccountOperationBuilder.INTERNSHIP_USER_TYPE.equals(userType)) {
			accountFactory.setNewAccountModelHolderBuilder(new CreateInternshipAccountModelHolderBuilder(request));

		} else if (AccountOperationBuilder.SCHOLARSHIP_USER_TYPE.equals(userType)) {
			accountFactory.setNewAccountModelHolderBuilder(new CreateScholarshipAccountModelHolderBuilder(request));

		} else {
			accountFactory.setNewAccountModelHolderBuilder(new CreateCIMAccountModelHolderBuilder(request));
		}

		return accountFactory;
	}

	private AccountActionCommand createCommand(StrutsPortletAction originalStrutsPortletAction, PortletConfig portletConfig, ActionRequest actionRequest,
			ActionResponse actionResponse) {
		String command = ParamUtil.getString(actionRequest, Constants.CMD);

		AccountActionCommand accountCommand = null;
		if (Constants.ADD.equals(command)) {
			accountCommand = new AddAccountCommand(originalStrutsPortletAction, portletConfig, actionRequest, actionResponse);

		} else if (Constants.UPDATE.equals(command)) {
			accountCommand = new UpdateAccountCommand(originalStrutsPortletAction, portletConfig, actionRequest, actionResponse);

		} else if (Constants.DEACTIVATE.equals(command) || MyAccountConstants.CANCEL_DEACTIVATION.equals(command)) {
			boolean isCancelCommand = MyAccountConstants.CANCEL_DEACTIVATION.equals(command);
			accountCommand = new DeactivateUsersCommand(originalStrutsPortletAction, portletConfig, actionRequest, actionResponse, isCancelCommand);

		} else if (Constants.RESTORE.equals(command) || MyAccountConstants.CANCEL_ACTIVATION.equals(command)) {
			boolean isCancelCommand = MyAccountConstants.CANCEL_ACTIVATION.equals(command);
			accountCommand = new ActivateUsersCommand(originalStrutsPortletAction, portletConfig, actionRequest, actionResponse, isCancelCommand);

		} else if (Constants.DELETE.equals(command)) {
			accountCommand = new DeleteUserCommand(originalStrutsPortletAction, portletConfig, actionRequest, actionResponse);

		} else {
			accountCommand = new DefaultCommand(originalStrutsPortletAction, portletConfig, actionRequest, actionResponse);

		}

		return accountCommand;
	}
}
