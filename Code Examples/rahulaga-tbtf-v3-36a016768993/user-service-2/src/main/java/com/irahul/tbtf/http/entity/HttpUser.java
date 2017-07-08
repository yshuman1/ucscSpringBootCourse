package com.irahul.tbtf.http.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.irahul.tbtf.entity.User;

/**
 * Select fields we want exposed to the REST layer. HTTP separation from business/data layer. 
 *  
 * @author rahul
 *
 */
@JsonInclude(value=Include.NON_NULL)
public class HttpUser {	
	public long id;
	
	public String firstName;
	
	public String lastName;
		
	public String pin;

	public HttpUser() {}

	public HttpUser(User user) {
		this.id=user.getId();
		this.firstName=user.getFirstName();
		this.lastName=user.getLastName();
		//not setting PIN
	}
}
