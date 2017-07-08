package com.irahul.tbtf.entity.impl;

import com.irahul.tbtf.entity.CheckingAccount;

public class CheckingAccountImpl implements CheckingAccount {
	private long id;
	private long ownerId;
	private int balance;
	private int availableBalance;
	private String currency;
	private long routingNumber;

	public CheckingAccountImpl(long id) {
		super();
		this.id = id;
	}

	public CheckingAccountImpl() {
	}

	public long getId() {
		return id;
	}

	public long getOwnerId() {
		return ownerId;
	}

	public int getBalance() {
		return balance;
	}

	public int getAvailableBalance() {
		return availableBalance;
	}

	public String getCurrency() {
		return currency;
	}

	public void deposit(int totalDeposit, int availableNow) {
		balance += totalDeposit;
		availableBalance += availableNow;
	}

	public void withdraw(int amount) {
		balance -= amount;
	}

	@Override
	public long getRoutingNumber() {
		return this.routingNumber;
	}

	public void setRoutingNumber(long routingNumber) {
		this.routingNumber = routingNumber;
	}

	public void setOwnerId(long ownerId) {
		this.ownerId = ownerId;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	@Override
	public String toString() {
		return "CheckingAccountImpl [id=" + id + ", ownerId=" + ownerId + ", balance=" + balance + ", availableBalance="
				+ availableBalance + ", currency=" + currency + ", routingNumber=" + routingNumber + "]";
	}
	
}
