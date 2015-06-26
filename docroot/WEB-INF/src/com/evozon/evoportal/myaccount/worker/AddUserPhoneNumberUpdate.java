package com.evozon.evoportal.myaccount.worker;

import java.util.List;

import com.evozon.evoportal.my_account.model.DetailsModel;
import com.evozon.evoportal.myaccount.builder.AbstractAccountActionOperation;
import com.evozon.evoportal.myaccount.builder.AddAccountOperation;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.Contact;
import com.liferay.portal.model.ListType;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ListTypeServiceUtil;
import com.liferay.portal.service.PhoneLocalServiceUtil;

public class AddUserPhoneNumberUpdate extends ActionWorker {

	private static final Log logger = LogFactoryUtil.getLog(AddAccountOperation.class);

	public void execute(AbstractAccountActionOperation operation) throws Exception {
		try {
			User changedUser = operation.getSelectedUser();

			DetailsModel detailsModel = operation.getNewAccountModelHolder().getDetailsModel();
			String newPhoneNumber = detailsModel.getPhoneNumber();

			if (!newPhoneNumber.isEmpty()) {
				// add new phone
				int listTypeId = getPersonalListTypeId();
				long userId = changedUser.getUserId();
				String contactName = Contact.class.getName();
				long contactId = changedUser.getContactId();

				PhoneLocalServiceUtil.addPhone(userId, contactName, contactId, newPhoneNumber, StringPool.BLANK, listTypeId, true);
			}
		} catch (Exception e) {
			logger.error(e);
		}

	}

	private int getPersonalListTypeId() throws SystemException {
		int listType = 0;

		List<ListType> listTypeList = ListTypeServiceUtil.getListTypes("com.liferay.portal.model.Contact.phone");
		for (ListType lType : listTypeList) {
			if (lType.getName().equals("personal")) {
				listType = lType.getListTypeId();
				break;
			}
		}
		return listType;
	}

}
