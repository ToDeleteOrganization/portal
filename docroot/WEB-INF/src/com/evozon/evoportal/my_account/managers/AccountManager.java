package com.evozon.evoportal.my_account.managers;

import com.evozon.evoportal.my_account.model.IAccountModel;

public abstract class AccountManager<T extends IAccountModel> {

	private T oldModel;
	
	private T newModel;
	
	public AccountManager(T oldModel, T newModel) {
		this.oldModel = oldModel;
		this.newModel = newModel;
	}

	public T getOldModel() {
		return oldModel;
	}
	
	public T getNewModel() {
		return newModel;
	}

}
