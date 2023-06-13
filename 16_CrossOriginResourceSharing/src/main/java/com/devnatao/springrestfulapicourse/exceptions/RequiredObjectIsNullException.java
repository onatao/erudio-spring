package com.devnatao.springrestfulapicourse.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class RequiredObjectIsNullException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	/*
	 * Setting one custom exception message
	 */
	public RequiredObjectIsNullException() {
		super("Isnt allowed to persisted an null object!");
	}	
	
	public RequiredObjectIsNullException(String msg) {
		super(msg);
	}
}
