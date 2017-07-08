package com.irahul.tbtf.service;

import com.irahul.tbtf.entity.User;

public interface UserService {

	User addUser(User user);

	void updateUser(User user);

	User getUser(long userId);

	/**
	 * Search user by first or last name, partial searches also performed
	 * 
	 * @param firstName
	 * @param lastName
	 * @return Empty list is returned if none found
	 */
	Iterable<User> getUsers(String firstName, String lastName);

	boolean isATMPinValid(long userId, String pin);
}
