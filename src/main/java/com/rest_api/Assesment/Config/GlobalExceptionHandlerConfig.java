package com.rest_api.Assesment.Config;

import org.springframework.http.HttpStatusCode;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.rest_api.Assesment.Exception.InvalidIDException;
import com.rest_api.Assesment.Exception.InvalidUserNameException;


@RestControllerAdvice
public class GlobalExceptionHandlerConfig {

	@ExceptionHandler(InvalidIDException.class)
	public ErrorResponse invalidIDExceptionHandler(InvalidIDException e) {
		return ErrorResponse.create(e, HttpStatusCode.valueOf(400),e.getMessage());
	}
	@ExceptionHandler(InvalidUserNameException.class)
	public ErrorResponse invalidUserNameExceptionHandler(InvalidUserNameException e) {
		return ErrorResponse.create(e, HttpStatusCode.valueOf(400),e.getMessage());
	}
}
