package com.irahul.tbtf.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.irahul.tbtf.entity.CheckingAccount;
import com.irahul.tbtf.entity.impl.CheckingAccountImpl;
import com.irahul.tbtf.externalresource.UserServiceClient;
import com.irahul.tbtf.repository.CheckingAccountRepository;
import com.irahul.tbtf.service.CheckingAccountService;
import com.irahul.tbtf.service.exception.InvalidFieldException;

@Service
public class CheckingAccountServiceImpl implements CheckingAccountService {
private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private CheckingAccountRepository checkingAccountRepository;
	
	@Autowired
	private UserServiceClient userServiceClient;

	@Override
	public CheckingAccount getAccount(long accountId) {		
		return checkingAccountRepository.findOne(accountId);
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
		if(accountToCreate.getOwnerId()<=0){
			throw new InvalidFieldException("Invalid owner id");
		}
		logger.info("Calling user service for ownerId="+accountToCreate.getOwnerId());
		if(!userServiceClient.userExists(accountToCreate.getOwnerId())){
			throw new InvalidFieldException("User not found");
		}
		
		CheckingAccountImpl impl = (CheckingAccountImpl)accountToCreate;		
		return checkingAccountRepository.save(impl);
	}
}
