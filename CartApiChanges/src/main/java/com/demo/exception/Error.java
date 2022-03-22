package com.demo.exception;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Error implements Serializable{

	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ErrorCode errorCode;
	    private String property;
	    private String message;
}
