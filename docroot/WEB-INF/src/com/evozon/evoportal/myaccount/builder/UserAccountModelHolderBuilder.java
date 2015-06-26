package com.evozon.evoportal.myaccount.builder;

import java.util.ArrayList;
import java.util.List;

import com.evozon.evoportal.my_account.AccountModelHolder;
import com.evozon.evoportal.my_account.model.DetailsModel;
import com.evozon.evoportal.my_account.model.EvoAddressModel;
import com.evozon.evoportal.my_account.model.FreeDaysModel;
import com.evozon.evoportal.my_account.wrapper.UserExpandoWrapper;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.Address;
import com.liferay.portal.model.Contact;
import com.liferay.portal.model.EmailAddress;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.Phone;
import com.liferay.portal.model.User;

public class UserAccountModelHolderBuilder implements AccountModelHolderBuilder {

	private static final Log logger = LogFactoryUtil.getLog(UserAccountModelHolderBuilder.class);

	private final User user;

	private final UserExpandoWrapper expando;

	private final AccountModelHolder accountModelHolder;

	public UserAccountModelHolderBuilder(User user) {
		this.user = user;
		this.expando = new UserExpandoWrapper(user);
		this.accountModelHolder = new AccountModelHolder();
	}

	public AccountModelHolder build() {
		return accountModelHolder;
	}

	// TODO: add extra days descr.
	public AccountModelHolderBuilder buildFreeDaysModel() {
		FreeDaysModel freeDaysModel = new FreeDaysModel();

		freeDaysModel.setUserType(expando.getUserEntryType());
		freeDaysModel.setStartingBonusDays(expando.getStartingBonusDays());
		freeDaysModel.setComments(expando.getComments());
		freeDaysModel.setExtraDaysCount(expando.getExtraDays());
		freeDaysModel.setExtraDaysDescription(StringPool.BLANK);
		freeDaysModel.setStartDate(expando.getDateHired());
		freeDaysModel.setCimStartDate(expando.getCIMStartDate());
		freeDaysModel.setInternshipStartDate(expando.getInternshipStartDate());

		freeDaysModel.setFreeDaysFromLastYear(expando.getFreeDaysFromLastYear());
		freeDaysModel.setFreeDaysInCurrentYear(expando.getFreeDaysInCurrentYear());
		freeDaysModel.setRemainingFreeDaysFromLastYear(expando.getRemainingFreeDaysFromLastYear());
		accountModelHolder.setFreeDaysModel(freeDaysModel);

		return this;
	}

	public AccountModelHolderBuilder buildDetailsModel() {
		DetailsModel detailsModel = new DetailsModel();
		try {
			Contact userContact = user.getContact();

			detailsModel.setScreenName(user.getScreenName());
			detailsModel.setEmailAddress(user.getEmailAddress());
			detailsModel.setFirstName(user.getFirstName());
			detailsModel.setLastName(user.getLastName());
			detailsModel.setMale(user.isMale());
			detailsModel.setBuilding(expando.getBuilding());
			detailsModel.setJobTitle(user.getJobTitle());
			detailsModel.setFunctieCIM(expando.getUserJob());
			detailsModel.setCnp(expando.getPersonalIdentificationNumber());
			detailsModel.setLicensePlate(expando.getLicensePlate());
			detailsModel.setBirthdayDate(user.getBirthday());
			detailsModel.setBirthdayHidden(expando.isBirthdayHidden());
			detailsModel.setSkype(userContact.getSkypeSn());
			detailsModel.setPhoneNumberHidden(expando.isPhoneNumberHidden());
			detailsModel.setMarried(expando.getMarried());
			detailsModel.setStudent(expando.isStudent());
			detailsModel.setUniversity(expando.getUniversity());
			detailsModel.setFaculty(expando.getFaculty());
			detailsModel.setDiplomaTitle(expando.getDiplomaTitle());

			// add phone number
			List<Phone> phones = user.getPhones();
			if (!phones.isEmpty()) {
				Phone phone = phones.get(0);
				detailsModel.setPhoneNumber(phone.getNumber());
			}

			// add additional email address
			List<EmailAddress> additionalEmails = user.getEmailAddresses();
			if (!additionalEmails.isEmpty()) {
				EmailAddress emailAddress = additionalEmails.get(0);
				detailsModel.setAdditionalEmailAddress(emailAddress.getAddress());
			}

		} catch (PortalException e) {
			logger.error("Can't get details model from user: " + user, e);
		} catch (SystemException e) {
			logger.error("Can't get details model from user: " + user, e);
		}

		this.accountModelHolder.setDetailsModel(detailsModel);
		return this;
	}

	public AccountModelHolderBuilder buildAddresses() {
		List<EvoAddressModel> adressModel = new ArrayList<EvoAddressModel>();

		try {
			for (Address address : user.getAddresses()) {
				EvoAddressModel addressModel = new EvoAddressModel();

				addressModel.setCity(address.getCity());
				addressModel.setStreetName(address.getStreet1());
				addressModel.setStreetNumber(address.getStreet2());
				addressModel.setPostalCode(address.getStreet3());
				addressModel.setCountryCode(address.getCountry().getA2());
				addressModel.setType(address.getTypeId());
				addressModel.setRegion(address.getRegionId());
				addressModel.setPrimary(address.isPrimary());
				addressModel.setMailing(address.getMailing());

				adressModel.add(addressModel);
			}
		} catch (SystemException e) {
			logger.error("Can't get evo addresses from user: " + user);
		}

		this.accountModelHolder.setAdresses(adressModel);
		return this;
	}

	public AccountModelHolderBuilder buildUserFamily() {
		return this;
	}

	public AccountModelHolderBuilder buildUserDepartments() {
		List<String> departments = new ArrayList<String>();

		try {
			List<Group> mySites = user.getMySites();

			for (Group site : mySites) {
				if (site.isSite() && !site.isGuest()) {
					departments.add(site.getName());
				}
			}
		} catch (PortalException e) {
			logger.error("Can't get departments for user: " + user);
		} catch (SystemException e) {
			logger.error("Can't get departments for user: " + user);
		}

		this.accountModelHolder.setUserDepartments(departments);
		return this;
	}
}
