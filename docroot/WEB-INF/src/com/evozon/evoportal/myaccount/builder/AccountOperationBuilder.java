package com.evozon.evoportal.myaccount.builder;

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
import com.evozon.evoportal.myaccount.builder.validators.EmailAddressValidator;
import com.evozon.evoportal.myaccount.builder.validators.FirstNameValidator;
import com.evozon.evoportal.myaccount.builder.validators.FunctieCIMValidator;
import com.evozon.evoportal.myaccount.builder.validators.JobTitleValidator;
import com.evozon.evoportal.myaccount.builder.validators.LastNameValidator;
import com.evozon.evoportal.myaccount.builder.validators.LicensePlateValidator;
import com.evozon.evoportal.myaccount.builder.validators.PhoneNumberValidator;
import com.evozon.evoportal.myaccount.builder.validators.ScreenNameValidator;
import com.evozon.evoportal.myaccount.builder.validators.SiteValidator;
import com.liferay.portal.kernel.struts.StrutsPortletAction;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.ParamUtil;

public final class AccountOperationBuilder {

	private static final String INTERNSHIP_USER_TYPE = "internship";

	private static final String SCHOLARSHIP_USER_TYPE = "scholarship";

	private static final String DEFAULT = "DEFAULT";

	private static final String USER_TYPE = "user_type";

	public static ActionAccountOperation buildAccountActionOperation(ActionPhaseParameters app) {
		ActionAccountOperation operation = null;

		String command = ParamUtil.getString(app.getRequest(), Constants.CMD, AccountOperationBuilder.DEFAULT);
		if (Constants.ADD.equals(command)) {
			operation = buildAddAccountActionOperation(app);

		}

		return operation;
	}

	// TODO: add familyValidation for update
	private static ActionAccountOperation buildAddAccountActionOperation(ActionPhaseParameters app) {
		ManagementAccountActionOperation addAccountCommandOperation = new AddAccountOperation(app);

		AccountModelHolderDirectory accountModelFactory = AccountOperationBuilder.createAddAccountBuilder(app.getRequest());
		AccountModelHolder newAccountModel = accountModelFactory.buildNewAccountModelHolder(app.getRequest());
		addAccountCommandOperation.setNewAccountModelHolder(newAccountModel);

		DetailsModel detailsModel = newAccountModel.getDetailsModel();
		FreeDaysModel freeDaysModel = newAccountModel.getFreeDaysModel();

		addAccountCommandOperation.addValidationRule(new ScreenNameValidator(detailsModel.getScreenName()));
		addAccountCommandOperation.addValidationRule(new EmailAddressValidator(detailsModel.getEmailAddress()));
		addAccountCommandOperation.addValidationRule(new LastNameValidator(detailsModel.getLastName()));
		addAccountCommandOperation.addValidationRule(new FirstNameValidator(detailsModel.getFirstName()));
		addAccountCommandOperation.addValidationRule(new LicensePlateValidator(detailsModel.getLicensePlate()));
		addAccountCommandOperation.addValidationRule(new PhoneNumberValidator(detailsModel.getPhoneNumber()));
		addAccountCommandOperation.addValidationRule(new BirthdayValidator(freeDaysModel.getStartDate(), detailsModel.getBirthdayDate()));
		addAccountCommandOperation.addValidationRule(new CNPContentValidator(detailsModel.getCNP()));
		addAccountCommandOperation.addValidationRule(new CNPDuplicateValidator(detailsModel.getCNP()));
		addAccountCommandOperation.addValidationRule(new FunctieCIMValidator(detailsModel.getFunctieCIM()));
		addAccountCommandOperation.addValidationRule(new DateHiredValidator(freeDaysModel.getStartDate()));
		addAccountCommandOperation.addValidationRule(new JobTitleValidator(detailsModel.getJobTitle()));
		addAccountCommandOperation.addValidationRule(new SiteValidator(newAccountModel.getUserDepartments()));

		return addAccountCommandOperation;
	}

	private static AccountModelHolderDirectory createAddAccountBuilder(ActionRequest request) {
		AccountModelHolderDirectory accountFactory = new AccountModelHolderDirectory();

		String userType = ParamUtil.getString(request, AccountOperationBuilder.USER_TYPE);
		if (AccountOperationBuilder.INTERNSHIP_USER_TYPE.equals(userType)) {
			accountFactory.setAccountModelHolderBuilder(new CreateInternshipAccountModelHolderBuilder(request));

		} else if (AccountOperationBuilder.SCHOLARSHIP_USER_TYPE.equals(userType)) {
			accountFactory.setAccountModelHolderBuilder(new CreateScholarshipAccountModelHolderBuilder(request));

		} else {
			accountFactory.setAccountModelHolderBuilder(new CreateCIMAccountModelHolderBuilder(request));
		}

		return accountFactory;
	}

	private AccountActionCommand createCommand(StrutsPortletAction originalStrutsPortletAction, PortletConfig portletConfig, ActionRequest actionRequest, ActionResponse actionResponse) {
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
