package com.evozon.evoportal.myaccount.worker;

import com.evozon.evoportal.my_account.AccountModelHolder;
import com.evozon.evoportal.myaccount.builder.AbstractAccountActionOperation;


public abstract class ActionWorker {
	
	protected AccountModelHolder newAccountModelHolder;

	protected AccountModelHolder oldAccountModelHolder;

	public void setNewAccountModelHolder(AccountModelHolder newAccountModelHolder) {
		this.newAccountModelHolder = newAccountModelHolder;
	}

	public void setOldAccountModelHolder(AccountModelHolder oldAccountModelHolder) {
		this.oldAccountModelHolder = oldAccountModelHolder;
	}

	public abstract void execute(AbstractAccountActionOperation operation) throws Exception;

}
