package com.irahul.tbtf.service;

import java.util.List;

import com.irahul.tbtf.entity.CheckingAccount;

public interface CheckingAccountService {

	CheckingAccount getAccount(long accountId);
	
	List<CheckingAccount> getAccountsForUser(long userId);
	
	void deposit(long accountId, int totalDeposit, int availableNow);
	
	void withdraw(long accountId, int amount);

	CheckingAccount addAccount(CheckingAccount accountToCreate);
}
