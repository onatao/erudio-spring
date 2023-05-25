package com.devnatao.springrestfulapicourse.handlers;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.devnatao.springrestfulapicourse.exceptions.ExceptionResponse;
import com.devnatao.springrestfulapicourse.exceptions.RequiredObjectIsNullException;
import com.devnatao.springrestfulapicourse.exceptions.ResourceNotFoundException;

@RestController
@ControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ExceptionResponse> handlerAllExceptions(Exception e, WebRequest request) {
		ExceptionResponse exception = new ExceptionResponse(new Date(), e.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(exception, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(ResourceNotFoundException.class)
	public final ResponseEntity<ExceptionResponse> handleNotFoundExceptions(Exception e, WebRequest request) {
		ExceptionResponse exception = new ExceptionResponse(new Date(), e.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(exception, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(RequiredObjectIsNullException.class)
	public final ResponseEntity<ExceptionResponse> handleBadRequestException(Exception e, WebRequest request) {
		ExceptionResponse exception = new ExceptionResponse(new Date(), e.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(exception, HttpStatus.BAD_REQUEST);
	}	
}
