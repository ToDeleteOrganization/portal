package com.evozon.evoportal.myaccount.builder.validators;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.evozon.evoportal.my_account.util.MyAccountConstants;
import com.evozon.evoportal.myaccount.builder.ActionValidationResult;
import com.evozon.evoportal.myaccount.builder.ValidationResult;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.model.User;
import com.liferay.portlet.expando.model.ExpandoColumn;
import com.liferay.portlet.expando.model.ExpandoTable;
import com.liferay.portlet.expando.model.ExpandoValue;
import com.liferay.portlet.expando.service.ExpandoColumnLocalServiceUtil;
import com.liferay.portlet.expando.service.ExpandoTableLocalServiceUtil;
import com.liferay.portlet.expando.service.ExpandoValueLocalServiceUtil;

public class CNPDuplicateValidator extends AbstractValidator {

	private static Log logger = LogFactoryUtil.getLog(CNPDuplicateValidator.class);

	private static final String DUPLICATE_CNP_ERROR = "duplicate-cnp";

	private static final String ERROR_MESSAGE_START = "The following users have the same CNP: ";

	private final String newCNP;

	private final String oldCNP;

	public CNPDuplicateValidator(String newCNP) {
		this(newCNP, StringUtils.EMPTY);
	}

	public CNPDuplicateValidator(String newCNP, String oldCNP) {
		this.newCNP = newCNP;
		this.oldCNP = oldCNP;
	}

	public ValidationResult validate() {
		final ActionValidationResult res = new ActionValidationResult();

		if (!StringUtils.equals(newCNP, oldCNP)) {
			List<String> duplicateUsers = findUsersWithSameCNP(newCNP);

			if (!duplicateUsers.isEmpty()) {
				res.addError(buildValidationMessage(DUPLICATE_CNP_ERROR, buildErrorMessage(duplicateUsers)));
			}
		}

		return res;
	}

	private String buildErrorMessage(List<String> duplicateUsers) {
		StringBuffer buf = new StringBuffer();

		buf.append(ERROR_MESSAGE_START);
		buf.append(duplicateUsers.toString());

		return buf.toString();
	}

	private List<String> findUsersWithSameCNP(String cnp) {
		List<String> usersNamesWithSameCNP = new ArrayList<String>();;
		try {
			ExpandoTable userExpandoTable = ExpandoTableLocalServiceUtil.getDefaultTable(10154, User.class.getName());
			ExpandoColumn filedColumn = ExpandoColumnLocalServiceUtil.getColumn(userExpandoTable.getTableId(), MyAccountConstants.PERSONAL_IDENTIFICATION_NUMBER);
			
			List<ExpandoValue> columnValues = ExpandoValueLocalServiceUtil.getColumnValues(filedColumn.getColumnId(), -1, -1);
			for (ExpandoValue ev : columnValues) {
				if (StringUtils.equals(ev.getData(), cnp)) {
					usersNamesWithSameCNP.add(ev.getData());
				}
			}

		} catch (PortalException e) {
			logger.error(e);
		} catch (SystemException e) {
			logger.error(e);
		}

		return usersNamesWithSameCNP;
	}

	protected String getCategory() {
		return AbstractValidator.PERSONAL_DETAILS_ERROR;
	}

}
