package com.sms.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ExceptionResponse> handleMyCustomException(ResourceNotFoundException exception) {
		ExceptionResponse response = new ExceptionResponse();
		response.setErrorCode("404");
		response.setErrorMessage(exception.getLocalizedMessage());
		response.setTimestamp(LocalDateTime.now());

		return new ResponseEntity<ExceptionResponse>(response, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(BadRequestException.class)
	public ResponseEntity<ExceptionResponse> handleBadRequestException(BadRequestException exception) {
		ExceptionResponse response = new ExceptionResponse();
		response.setErrorCode("400");
		response.setErrorMessage(exception.getLocalizedMessage());
		response.setTimestamp(LocalDateTime.now());

		return new ResponseEntity<ExceptionResponse>(response, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(AuthenticationFailedException.class)
	public ResponseEntity<ExceptionResponse> handleAuthenticationFailedException(
			AuthenticationFailedException exception) {
		ExceptionResponse response = new ExceptionResponse();
		response.setErrorCode("401");
		response.setErrorMessage(exception.getLocalizedMessage());
		response.setTimestamp(LocalDateTime.now());

		return new ResponseEntity<ExceptionResponse>(response, HttpStatus.UNAUTHORIZED);
	}

	@ExceptionHandler(UnAuthorizedException.class)
	public ResponseEntity<ExceptionResponse> handleUnAuthorizedException(UnAuthorizedException exception) {
		ExceptionResponse response = new ExceptionResponse();
		response.setErrorCode("403");
		response.setErrorMessage(exception.getLocalizedMessage());
		response.setTimestamp(LocalDateTime.now());

		return new ResponseEntity<ExceptionResponse>(response, HttpStatus.FORBIDDEN);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ExceptionResponse> handleAllException(Exception exception) {
		ExceptionResponse response = new ExceptionResponse();
		response.setErrorCode("500");
		response.setErrorMessage(exception.getLocalizedMessage());
		response.setTimestamp(LocalDateTime.now());

		return new ResponseEntity<ExceptionResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
