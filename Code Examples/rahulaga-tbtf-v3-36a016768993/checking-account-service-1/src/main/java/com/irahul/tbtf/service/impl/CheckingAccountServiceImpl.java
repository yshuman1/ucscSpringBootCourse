package com.irahul.tbtf.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.irahul.tbtf.entity.CheckingAccount;
import com.irahul.tbtf.entity.impl.CheckingAccountImpl;
import com.irahul.tbtf.service.CheckingAccountService;

@Service
public class CheckingAccountServiceImpl implements CheckingAccountService {

	@Override
	public CheckingAccount getAccount(long accountId) {
		CheckingAccountImpl account = new CheckingAccountImpl(accountId);
		// TODO Auto-generated method stub
		return account;
	}

	@Override
	public List<CheckingAccount> getAccountsForUser(long userId) {
		//make sure user is valid
		
				
		//get accounts
		
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deposit(long accountId, int totalDeposit, int availableNow) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void withdraw(long accountId, int amount) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public CheckingAccount addAccount(CheckingAccount accountToCreate) {
		//need to validate ownerId with User Service
		
		
		// TODO Auto-generated method stub
		return accountToCreate;
	}
}
