package com.demo.exception;



import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;

import com.demo.constants.MessageConstants;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class SystemExceptionHandler {
	
	
	 @ExceptionHandler(MethodArgumentNotValidException.class)
	    public final ResponseEntity<ErrorResponse> handleValidationException(MethodArgumentNotValidException exception){
		 System.out.println("error 1");
	        List<Error> errors = new ArrayList<>();
	        exception.getBindingResult().getFieldErrors()
	                .forEach(error -> errors.add(
	                        Error.builder()
	                                .errorCode(ErrorCode.PRODUCT_VALIDATION_ERROR)
	                                .property(error.getField())
	                                .message(error.getDefaultMessage())
	                                .build())
	                );
	        ErrorResponse errorResponse = ErrorResponse.builder()
	                        .errors(errors)
	                        .build();
	        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	    }
	 
	 @ExceptionHandler(WebExchangeBindException.class)
	    public final ResponseEntity<ErrorResponse> handleValidationException(WebExchangeBindException exception){
         System.out.println("error 2");
	        List<Error> errors = new ArrayList<>();
	        exception.getBindingResult().getFieldErrors()
	                .forEach(error -> errors.add(
	                        Error.builder()
	                                .errorCode(ErrorCode.PRODUCT_VALIDATION_ERROR)
	                                .property(error.getField())
	                                .message(error.getDefaultMessage())
	                                .build())
	                );
	        ErrorResponse errorResponse = ErrorResponse.builder()
	                        .errors(errors)
	                        .build();
	        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	    }

	    @ExceptionHandler(SystemException.class)
	    public final ResponseEntity<ErrorResponse> handleSystemException(SystemException exception){
	        List<Error> errors = new ArrayList<>();
	        errors.add(Error.builder()
	                .message(exception.getMessage())
	                .errorCode(exception.getErrorCode())
	                .build());
	        ErrorResponse errorResponse = ErrorResponse.builder()
	                .errors(errors)
	                .build();
	        return new ResponseEntity<>(errorResponse, exception.getErrorCode().getHttpStatus());
	    }

	    @ExceptionHandler(Exception.class)
	    public ResponseEntity<ErrorResponse> handleException(Exception ex) {
      ex.printStackTrace();
	        List<Error> errors = new ArrayList<>();
	        errors.add(
	                Error.builder()
	                        .errorCode(ErrorCode.PRODUCTS_INTERNAL_ERROR)
	                        .message(MessageConstants.INTERNAL_SERVER_ERROR)
	                        .build()
	        );
	        ErrorResponse errorResponse = ErrorResponse.builder()
	                .errors(errors)
	                .build();
	        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	    }

	    @ExceptionHandler(JsonProcessingException.class)
	    public ResponseEntity<ErrorResponse> handleInvalidRequestDataType(
	            JsonProcessingException exception) {


	        List<Error> errors = new ArrayList<>();
	        errors.add(Error.builder()
	                .errorCode(ErrorCode.PRODUCT_VALIDATION_ERROR)
	                .message(MessageConstants.MALFORMED_REQUEST)
	                .build()
	        );

	        ErrorResponse errorResponse = ErrorResponse.builder()
	                        .errors(errors)
	                        .build();
	        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	    }

//	    @ExceptionHandler(UnsupportedMediaTypeStatusException.class)
//	    public ResponseEntity<ErrorResponse> handleException(UnsupportedMediaTypeStatusException ex) {
//	        List<Error> errors = new ArrayList<>();
//	        errors.add(
//	                Error.builder()
//	                        .errorCode(ErrorCode.PRODUCTS_MEDIA_TYPE_NOT_SUPPORTED)
//	                        .message(MessageConstants.MEDIA_TYPE_NOT_SUPPORTED)
//	                        .build()
//	        );
//	        ErrorResponse errorResponse = ErrorResponse.builder()
//	                        .errors(errors)
//	                        .build();
//	        return new ResponseEntity<>(errorResponse, HttpStatus.UNSUPPORTED_MEDIA_TYPE);
//	    }

//	    @ExceptionHandler(NotAcceptableStatusException.class)
//	    public ResponseEntity<ErrorResponse> handleException(NotAcceptableStatusException ex) {
//	        List<Error> errors = new ArrayList<>();
//	        errors.add(
//	                Error.builder()
//	                        .errorCode(ErrorCode.PRODUCTS_MEDIA_TYPE_NOT_SUPPORTED)
//	                        .message(MessageConstants.MEDIA_TYPE_NOT_SUPPORTED)
//	                        .build()
//	        );
//	        ErrorResponse errorResponse = ErrorResponse.builder()
//	                .errors(errors)
//	                .build();
//	        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_ACCEPTABLE);
//	    }
}
