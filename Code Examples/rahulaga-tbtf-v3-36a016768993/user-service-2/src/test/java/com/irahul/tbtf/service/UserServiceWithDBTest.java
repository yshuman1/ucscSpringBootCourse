package com.irahul.tbtf.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Random;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.irahul.tbtf.entity.User;
import com.irahul.tbtf.entity.impl.UserImpl;
import com.irahul.tbtf.service.exception.ErrorCode;
import com.irahul.tbtf.service.exception.TBTFException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceWithDBTest{
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private UserService userService;
	
	@Test
	public void testPin(){
		UserImpl newUser = new UserImpl();
		newUser.setFirstName("new test"+new Random().nextInt(99999));
		newUser.setLastName("lastName");
		newUser.setPin("1234");
		
		User added = userService.addUser(newUser);
		assertThat(userService.isATMPinValid(added.getId(), "12345")).isEqualTo(false);
		assertThat(userService.isATMPinValid(added.getId(), null)).isEqualTo(false);
		assertThat(userService.isATMPinValid(added.getId(), "1234")).isEqualTo(true);
	}
	
	@Test
	public void addAndGetUser(){
		UserImpl newUser = new UserImpl();
		newUser.setFirstName("test"+new Random().nextInt(99999));
		newUser.setLastName("lastName");
		newUser.setPin("1234");
		
		User added = userService.addUser(newUser);
		logger.info("user added "+added);
		assertThat(0).isNotEqualTo(added.getId());//this should have been created so not zero anymore
		assertThat(newUser.getFirstName()).isEqualTo(added.getFirstName());
		assertThat(newUser.getLastName()).isEqualTo(added.getLastName());
		assertThat(newUser.getPin()).isEqualTo(added.getPin());
		
		User found = userService.getUser(added.getId());
		assertThat(found.getId()).isEqualTo(added.getId());
		assertThat(found.getFirstName()).isEqualTo(added.getFirstName());
		assertThat(found.getLastName()).isEqualTo(added.getLastName());
		assertThat(found.getPin()).isEqualTo(added.getPin());
	}
	
	@Test
	public void getNonExistantUser(){
		try{
			userService.getUser(Long.MAX_VALUE);
		}
		catch(TBTFException e){
			assertThat(e.getErrorCode()).isEqualTo(ErrorCode.NOT_FOUND);
		}
	}
}
