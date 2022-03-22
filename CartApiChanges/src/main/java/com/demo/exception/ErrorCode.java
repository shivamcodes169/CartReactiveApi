package com.demo.exception;
import lombok.AllArgsConstructor;
import lombok.Getter;

import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;

@Getter
@AllArgsConstructor
public enum ErrorCode {
	 CANNOT_BE_NULL(INTERNAL_SERVER_ERROR, "System error"),
	 PRODUCT_VALIDATION_ERROR(BAD_REQUEST, "Validation error"),
	 ATTRIBUTE_NAME_ALREADY_EXISTS(CONFLICT,"attribute name already exists"),
	 PRODUCTS_MEDIA_TYPE_NOT_SUPPORTED(UNSUPPORTED_MEDIA_TYPE, "Media type not supported"),
	 ATTRIBUTE_NOT_FOUND(INTERNAL_SERVER_ERROR,"System error"),
	 USER_NOT_FOUND(BAD_REQUEST,"user not exist");
	 
	 private final HttpStatus httpStatus;
    private final String message;
}
