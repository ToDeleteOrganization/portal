package com.evozon.evoportal.myaccount.builder;

import java.util.ArrayList;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import com.evozon.evoportal.my_account.AccountModelHolder;
import com.evozon.evoportal.ws.pmreports.util.PMReportsIntegrationUtil;
import com.liferay.compat.portal.util.PortalUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.SessionErrors;

public abstract class AbstractAccountActionOperation implements ActionAccountOperation {

	private static Log logger = LogFactoryUtil.getLog(AbstractAccountActionOperation.class);

	protected final List<Validator> validationRules = new ArrayList<Validator>();

	private final ActionPhaseParameters actionPhaseParameters;

	protected AccountModelHolder newAccountModelHolder;

	protected AccountModelHolder oldAccountModelHolder;

	public AbstractAccountActionOperation(final ActionPhaseParameters pp) {
		this.actionPhaseParameters = pp;
	}

	public void execute() throws Exception {
		// TODO: add general behavior before executing the actual action
		executeDefaultLiferayProcess();
		if (SessionErrors.isEmpty(actionPhaseParameters.getRequest())) {
			executeInternalAction();
		}
	}

	public ValidationResult isActionValid() {
		ValidationResult validationResult = new ActionValidationResult();

		for (Validator validator : validationRules) {
			ValidationResult res = validator.validate();

			if (res.hasMessages()) {
				validationResult.addMessages(res.getValidationMessages());
			}

			if (res.hasErrors()) {
				validationResult.addErrors(res.getValidationErrors());

				if (logger.isDebugEnabled()) {
					logger.debug("Validation failure [" + validator + "].");
				}
			}

		}

		return validationResult;
	}

	public void addValidationRule(Validator validator) {
		this.validationRules.add(validator);
	}

	protected void executeDefaultLiferayProcess() throws Exception {
		ActionAccountOperation defaultActionOp = new DefaultAccountActionOperation(actionPhaseParameters);
		defaultActionOp.execute();
	}

	protected void executePMReportsIntegration() {
		if (PMReportsIntegrationUtil.isPmReportsIntegrationActivated()) {
			
		}
	}

	protected ActionRequest getActionRequest() {
		return actionPhaseParameters.getRequest();
	}

	protected ActionResponse getActionResponse() {
		return actionPhaseParameters.getResponse();
	}

	protected Long getCompanyId() {
		return PortalUtil.getCompanyId(getActionRequest());
	}

	public void setNewAccountModelHolder(AccountModelHolder newAccountHolder) {
		this.newAccountModelHolder = newAccountHolder;
	}

	public void setOldAccountModelHolder(AccountModelHolder oldAccountHolder) {
		this.oldAccountModelHolder = oldAccountHolder;
	}

	protected abstract void executeInternalAction();

}
