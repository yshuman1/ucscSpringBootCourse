package com.irahul.tbtf.http.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.irahul.tbtf.service.exception.TBTFException;

@JsonInclude(value=Include.NON_NULL)
public class HttpError {
	public int status;	
	public String code;	
	public String message;
	public String debug;

	protected HttpError(){}
	
	public HttpError(TBTFException ex) {
		status=409;
		code=ex.getErrorCode()==null?"":ex.getErrorCode().name();
		message=ex.getMessage();
		debug=ex.getCause()==null?"":"caused by"+ex.getCause().getMessage();		
	}
}
