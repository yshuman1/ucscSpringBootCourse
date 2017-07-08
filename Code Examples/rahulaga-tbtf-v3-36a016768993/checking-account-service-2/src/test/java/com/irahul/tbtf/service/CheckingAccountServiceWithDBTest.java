package com.irahul.tbtf.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.irahul.tbtf.entity.CheckingAccount;
import com.irahul.tbtf.entity.impl.CheckingAccountImpl;
import com.irahul.tbtf.service.exception.InvalidFieldException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CheckingAccountServiceWithDBTest{
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private CheckingAccountService checkingAccountService;
	
	@Test
	public void addAndGetAccount(){
		CheckingAccountImpl newAccount = new CheckingAccountImpl();
		newAccount.setCurrency("USD");
		newAccount.setRoutingNumber(123456789);
		newAccount.deposit(100, 50);
		newAccount.setOwnerId(1);//This MUST already exist otherwise there will be an error
		
		CheckingAccount added = checkingAccountService.addAccount(newAccount);
		logger.info("account added "+added);
		assertThat(0).isNotEqualTo(added.getId());//this should have been created so not zero anymore
		assertThat(newAccount.getAvailableBalance()).isEqualTo(added.getAvailableBalance());
		assertThat(newAccount.getBalance()).isEqualTo(added.getBalance());
		assertThat(newAccount.getRoutingNumber()).isEqualTo(added.getRoutingNumber());
		assertThat(newAccount.getOwnerId()).isEqualTo(added.getOwnerId());
		
		//use id from account just added
		CheckingAccount found = checkingAccountService.getAccount(added.getId());
		assertThat(found.getId()).isEqualTo(added.getId());
		assertThat(found.getAvailableBalance()).isEqualTo(added.getAvailableBalance());
		assertThat(found.getBalance()).isEqualTo(added.getBalance());
		assertThat(found.getRoutingNumber()).isEqualTo(added.getRoutingNumber());
		assertThat(found.getOwnerId()).isEqualTo(added.getOwnerId());
	}
	
	@Test
	public void addAccountInvalidUser(){
		CheckingAccountImpl newAccount = new CheckingAccountImpl();
		newAccount.setCurrency("USD");
		newAccount.setRoutingNumber(123456789);
		newAccount.deposit(100, 50);
		newAccount.setOwnerId(Long.MAX_VALUE);
		
		try{
			checkingAccountService.addAccount(newAccount);
		}
		catch(InvalidFieldException e){
			assertThat(e.getMessage()).isEqualTo("User not found");
		}
		
	}
}
