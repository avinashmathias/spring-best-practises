package com.best.practice.exception.handler;

import java.util.Date;

import lombok.Data;

@Data
public class HttpErrorResponse {

	private int statusCode;
	private Date timestamp;
	private String message;
	private String description;

	public HttpErrorResponse(int statusCode, Date timestamp, String message, String description) {
		this.statusCode = statusCode;
		this.timestamp = timestamp;
		this.message = message;
		this.description = description;
	}
}
