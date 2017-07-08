package com.irahul.tbtf.http;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * Test class is independent 
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
}
