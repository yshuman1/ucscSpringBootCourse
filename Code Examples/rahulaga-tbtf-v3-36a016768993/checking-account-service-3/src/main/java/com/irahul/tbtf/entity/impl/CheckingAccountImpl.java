package com.irahul.tbtf.entity.impl;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.irahul.tbtf.entity.CheckingAccount;

@Entity
@Table(name="checking_account")
public class CheckingAccountImpl implements CheckingAccount {
	@Id
	@Column(name="id_account")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@Column(name="users_idusers")
	private long ownerId;
	
	@Column//eg: no need to specify name since matches column but I prefer to be explicit
	private int balance;
	
	@Column(name="available_balance")
	private int availableBalance;
	
	@Column(name="currency")
	private String currency;
	
	@Column(name="routing_number")
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
