package com.best.practice.exception;

public class DataFetchFailureException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public DataFetchFailureException(String msg) {
		super(msg);
	}
}
