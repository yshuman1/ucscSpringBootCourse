package com.irahul.tbtf.http.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.irahul.tbtf.entity.CheckingAccount;

/**
 * Select fields we want exposed to the REST layer. HTTP separation from business/data layer. 
 * 
 * @author rahul
 *
 */
@JsonInclude(value=Include.NON_NULL)
public class HttpCheckingAccount {
	public long id;
	public long ownerId;
	public String currency;
	public int balance;
	public int availableBalance;
	
	public HttpCheckingAccount() {}

	public HttpCheckingAccount(CheckingAccount account) {
		this.id=account.getId();
		this.ownerId=account.getOwnerId();
		this.currency=account.getCurrency();
		this.balance=account.getBalance();
		this.availableBalance=account.getAvailableBalance();
	}

	@Override
	public String toString() {
		return "HttpCheckingAccount [id=" + id + ", ownerId=" + ownerId + ", currency=" + currency + ", balance="
				+ balance + ", availableBalance=" + availableBalance + "]";
	}
	
}
