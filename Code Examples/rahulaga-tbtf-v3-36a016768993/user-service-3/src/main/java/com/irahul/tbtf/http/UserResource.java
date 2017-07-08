package com.irahul.tbtf.http;

import java.util.ArrayList;
import java.util.List;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.irahul.tbtf.entity.User;
import com.irahul.tbtf.entity.impl.UserImpl;
import com.irahul.tbtf.http.entity.HttpUser;
import com.irahul.tbtf.service.UserService;

@RestController
@RequestMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserResource {
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private UserService userService;

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HttpUser> createUser(@RequestBody HttpUser newUser) {		
		User userToCreate = convert(newUser);
		logger.info("Create user:" + userToCreate);
		User addedUser = userService.addUser(userToCreate);
		return new ResponseEntity<>(new HttpUser(addedUser), HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{userId}", method = RequestMethod.GET)
	public ResponseEntity<HttpUser> getUserById(@PathVariable("userId") long userId) {
		logger.info("getting user by id:" + userId);
		User user = userService.getUser(userId);
		return new ResponseEntity<>(new HttpUser(user), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<HttpUser>> getUserSearch(@RequestParam(value="firstName", required=false) String firstName,
			@RequestParam(value="lastName", required=false) String lastName) {
		logger.info("user search firstName=" + firstName + " lastName=" + lastName);
		Iterable<UserImpl> found = userService.getUsers(firstName, lastName);
		List<HttpUser> returnList = new ArrayList<>();
		for (User user : found) {
			returnList.add(new HttpUser(user));
		}
		return new ResponseEntity<>(returnList, HttpStatus.OK);
	}

	/**
	 * Not pushing this into business layer. Could be a util as well
	 * 
	 * @param newUser
	 * @return
	 */
	private User convert(HttpUser httpUser) {
		UserImpl user = new UserImpl();
		user.setFirstName(httpUser.firstName);
		user.setLastName(httpUser.lastName);
		user.setPin(httpUser.pin);
		return user;
	}
}
