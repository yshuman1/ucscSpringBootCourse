package com.irahul.tbtf.externalresource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * Connect with User Service using Rest Template
 * @author rahul
 *
 */
@Component
public class UserServiceClient {
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private RestTemplate userServiceRestTemplate;
	
	@Value("${userService.url}")
	private String userServiceUrl;

	public boolean userExists(long ownerId) {
		
		ResponseEntity<UserResponse> response = userServiceRestTemplate.exchange(userServiceUrl+"/users/{id}", 
				HttpMethod.GET, null, UserResponse.class, ownerId);
		
		logger.info("User response:"+response.getBody());
		
		if(response.getStatusCode()!=HttpStatus.OK){
			//some failure
			return false;
		}
		return true;
	}
}
