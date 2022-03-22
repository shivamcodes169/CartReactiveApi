package com.demo.exception;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class SystemException extends RuntimeException{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	 private final ErrorCode errorCode;
	    private  String message;

	    public SystemException(ErrorCode errorCode) {
	        super(errorCode.getMessage());
	        this.errorCode = errorCode;
	    }

	    public SystemException(ErrorCode errorCode, String message) {
	        super(message);
	        this.errorCode = errorCode;
	        this.message = message;
	    }
}