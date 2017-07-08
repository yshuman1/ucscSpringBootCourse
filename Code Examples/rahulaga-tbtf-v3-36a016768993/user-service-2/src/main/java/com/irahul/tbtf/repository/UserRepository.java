package com.irahul.tbtf.repository;

import org.springframework.data.repository.CrudRepository;

import com.irahul.tbtf.entity.impl.UserImpl;

public interface UserRepository extends CrudRepository<UserImpl, Long> {
	
	Iterable<UserImpl> findByFirstNameContainingAndLastNameContaining(String firstName, String lastName);

	Iterable<UserImpl> findByFirstNameContaining(String firstName);

	Iterable<UserImpl> findByLastNameContaining(String firstName);
}
