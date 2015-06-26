package com.evozon.evoportal.myaccount.builder.validators;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.evozon.evoportal.my_account.util.MyAccountConstants;
import com.evozon.evoportal.myaccount.builder.ActionValidationResult;
import com.evozon.evoportal.myaccount.builder.ValidationResult;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portlet.expando.model.ExpandoColumn;
import com.liferay.portlet.expando.model.ExpandoTable;
import com.liferay.portlet.expando.model.ExpandoValue;
import com.liferay.portlet.expando.service.ExpandoColumnLocalServiceUtil;
import com.liferay.portlet.expando.service.ExpandoTableLocalServiceUtil;
import com.liferay.portlet.expando.service.ExpandoValueLocalServiceUtil;

public class CNPDuplicateValidator extends AbstractValidator {

	private static final Log logger = LogFactoryUtil.getLog(CNPDuplicateValidator.class);

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
			final List<String> duplicateUsers = findUsersWithSameCNP(newCNP);

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
		final List<String> usersNamesWithSameCNP = new ArrayList<String>();

		try {
			final List<ExpandoValue> columnValues = getExpandoValues(10154, User.class.getName(), MyAccountConstants.PERSONAL_IDENTIFICATION_NUMBER);
			for (ExpandoValue ev : columnValues) {
				if (StringUtils.equals(ev.getData(), cnp)) {
					String userFullName = findUserFullNameById(ev.getClassPK());
					usersNamesWithSameCNP.add(userFullName);
				}
			}

		} catch (PortalException e) {
			logger.error(e);
		} catch (SystemException e) {
			logger.error(e);
		} catch (Exception e) {
			logger.error(e);
		}

		return usersNamesWithSameCNP;
	}

	private List<ExpandoValue> getExpandoValues(final long companyId, final String exntityClassName, final String entityName) throws PortalException, SystemException {
		ExpandoTable userExpandoTable = ExpandoTableLocalServiceUtil.getDefaultTable(companyId, exntityClassName);
		ExpandoColumn filedColumn = ExpandoColumnLocalServiceUtil.getColumn(userExpandoTable.getTableId(), entityName);

		return ExpandoValueLocalServiceUtil.getColumnValues(filedColumn.getColumnId(), QueryUtil.ALL_POS, QueryUtil.ALL_POS);
	}

	private String findUserFullNameById(long userId) throws PortalException, SystemException {
		return UserLocalServiceUtil.getUser(userId).getFullName();
	}

	protected String getCategory() {
		return AbstractValidator.PERSONAL_DETAILS_ERROR;
	}

}
