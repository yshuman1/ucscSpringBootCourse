package com.irahul.tbtf.repository;

import org.springframework.data.repository.CrudRepository;

import com.irahul.tbtf.entity.impl.CheckingAccountImpl;

public interface CheckingAccountRepository extends CrudRepository<CheckingAccountImpl, Long> {
	
}
