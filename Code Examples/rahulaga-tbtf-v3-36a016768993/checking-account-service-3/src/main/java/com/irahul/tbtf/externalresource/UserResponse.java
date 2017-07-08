package com.irahul.tbtf.externalresource;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserResponse {
	public long id;	
	public String firstName;	
	public String lastName;	

	public UserResponse(){		
	}

	@Override
	public String toString() {
		return "UserResponse [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + "]";
	}
}
