package com.irahul.tbtf.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringRunner;

import com.irahul.tbtf.entity.User;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest extends AbstractJUnit4SpringContextTests {
	@Autowired
	private UserService userService;

	@Test
	public void testPin() {
		assertThat(userService.isATMPinValid(1, "12345")).isEqualTo(false);
		assertThat(userService.isATMPinValid(1, null)).isEqualTo(false);
		assertThat(userService.isATMPinValid(1, "1234")).isEqualTo(true);
	}

	@Test
	public void testGetUser() {
		User userGet = userService.getUser(123);

		assertThat(userGet instanceof User).isTrue();
		assertThat(userGet.getId()).isEqualTo(123);
		assertThat(userGet.getFirstName()).isNull();
		assertThat(userGet.getLastName()).isNull();
		System.out.println(userGet);
	}
}
