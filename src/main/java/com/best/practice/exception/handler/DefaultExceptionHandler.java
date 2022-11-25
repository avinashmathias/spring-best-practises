package com.best.practice.exception.handler;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.best.practice.exception.DataFetchFailureException;

@RestControllerAdvice
public class DefaultExceptionHandler {

	@Autowired
	MessageSource messageSource;

	@ExceptionHandler(DataFetchFailureException.class)
	public ResponseEntity<HttpErrorResponse> dataFetchFailureException(DataFetchFailureException ex,
			WebRequest request) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST.value())
				.body(new HttpErrorResponse(HttpStatus.BAD_REQUEST.value(), new Date(), ex.getMessage(),
						request.getDescription(false)));
	}

	@ExceptionHandler(Exception.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public HttpErrorResponse globalExceptionHandler(Exception ex, WebRequest request) {
		return new HttpErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), new Date(), ex.getMessage(),
				request.getDescription(false));
	}

}
