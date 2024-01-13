package com.sourav.blog.exceptions;

import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class ApiException extends RuntimeException{
	
	public ApiException(String message) {
		super(message);
	}
	public ApiException() {
		// TODO Auto-generated constructor stub
	}
}
