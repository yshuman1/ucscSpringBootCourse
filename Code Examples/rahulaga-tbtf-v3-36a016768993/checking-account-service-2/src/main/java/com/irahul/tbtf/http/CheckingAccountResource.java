package com.irahul.tbtf.http;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.irahul.tbtf.entity.CheckingAccount;
import com.irahul.tbtf.entity.impl.CheckingAccountImpl;
import com.irahul.tbtf.http.entity.HttpCheckingAccount;
import com.irahul.tbtf.service.CheckingAccountService;

@RestController
public class CheckingAccountResource {
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private CheckingAccountService checkingAccountService;

	@RequestMapping(method = RequestMethod.POST, value = "/checking", 
			produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HttpCheckingAccount> createUser(@RequestBody HttpCheckingAccount newAccount) {
		CheckingAccount accountToCreate = convert(newAccount);
		logger.info("Create account:" + accountToCreate);
		CheckingAccount addedAccount = checkingAccountService.addAccount(accountToCreate);
		return new ResponseEntity<>(new HttpCheckingAccount(addedAccount), HttpStatus.CREATED);
	}

	@RequestMapping(value = "/checking/{accountId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HttpCheckingAccount> getAccountById(@PathVariable("accountId") long accountId) {
		logger.info("getting account by id:" + accountId);
		CheckingAccount account = checkingAccountService.getAccount(accountId);
		return new ResponseEntity<>(new HttpCheckingAccount(account), HttpStatus.OK);
	}

	/**
	 * Not pushing this into business layer. Could be a util as well
	 * 
	 * @param httpAccount
	 * @return
	 */
	private CheckingAccount convert(HttpCheckingAccount httpAccount) {
		CheckingAccountImpl account = new CheckingAccountImpl();
		account.setCurrency(httpAccount.currency);
		account.setOwnerId(httpAccount.ownerId);		
		return account;
	}
}
