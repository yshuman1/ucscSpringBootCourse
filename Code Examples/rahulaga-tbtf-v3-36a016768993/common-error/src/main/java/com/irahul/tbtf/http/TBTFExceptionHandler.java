package com.irahul.tbtf.http;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.irahul.tbtf.http.entity.HttpError;
import com.irahul.tbtf.service.exception.TBTFException;

/**
 * Exception handling advice - for all controllers
 * @author rahul
 *
 */
@ControllerAdvice
public class TBTFExceptionHandler {
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@ExceptionHandler({TBTFException.class})
	@ResponseBody
	public ResponseEntity<HttpError> handleIllegalArgumentException(TBTFException e){
		logger.info("TBTF Exception handler",e);		
		return new ResponseEntity<>(new HttpError(e), HttpStatus.CONFLICT); 
	}
}
