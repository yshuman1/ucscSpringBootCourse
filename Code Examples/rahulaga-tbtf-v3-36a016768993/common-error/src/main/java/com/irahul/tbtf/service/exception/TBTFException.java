package com.irahul.tbtf.service.exception;

/**
 * Root of our exception heirarchy
 * @author rahul
 *
 */
@SuppressWarnings("serial")
public class TBTFException extends RuntimeException {
	private ErrorCode errorCode;

	public TBTFException(ErrorCode code, String message, Throwable throwable) {
		super(message, throwable);
		this.errorCode = code;
	}
	
	public TBTFException(ErrorCode code, String message) {
		super(message);
		this.errorCode = code;
	}

	public ErrorCode getErrorCode() {
		return errorCode;
	}

}
