package com.irahul.tbtf.http;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
/**
 * Test class is independent
 * @author rahul
 *
 */
@JsonInclude(value=Include.NON_NULL)
public class HttpError {
	public int status;	
	public String code;	
	public String message;
	public String debug;
}
