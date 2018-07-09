package com.arun.spring.springrest.springrestfirst.helpers;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllException(Exception ex, WebRequest request) throws Exception {
	
	ExceptionResponse response = new ExceptionResponse(ex.getMessage(),request.getDescription(false),new Date());
	return new ResponseEntity<Object>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	
	}
	@ExceptionHandler(UserNotFoundException.class)
	public final ResponseEntity<Object> handleAllException(UserNotFoundException ex, WebRequest request) throws Exception {
	
	ExceptionResponse response = new ExceptionResponse(ex.getMessage(),request.getDescription(false),new Date());
	return new ResponseEntity<Object>(response, HttpStatus.NOT_FOUND);
	
	}
	
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		ExceptionResponse response = new ExceptionResponse("Validation failed",ex.getBindingResult().toString(),new Date());
		
		return new ResponseEntity<Object>(response, HttpStatus.BAD_REQUEST);
	}
}
