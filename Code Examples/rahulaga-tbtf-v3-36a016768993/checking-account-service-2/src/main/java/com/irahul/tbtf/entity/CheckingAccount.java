package com.irahul.tbtf.entity;

public interface CheckingAccount {
	long getId();

	long getOwnerId();

	String getCurrency();

	/**
	 * To keep things simple TBTF bank does not deal with fractional money and
	 * simply keeps the change for itself
	 * 
	 * Total money in account
	 * 
	 * @return
	 */
	int getBalance();

	/**
	 * Money available in account. This maybe lower than balance in case of
	 * holds
	 * 
	 * @return
	 */
	int getAvailableBalance();

	long getRoutingNumber();
}
