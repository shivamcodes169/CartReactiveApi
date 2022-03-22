package com.demo.exception;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@Setter
public class ErrorResponse  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Error> errors = new ArrayList<>();

}
