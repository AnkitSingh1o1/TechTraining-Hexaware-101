package com.exception;

public class AdminNotFoundException extends Exception{

	private static final long serialVersionUID = 1L;
	private String message;

	public String getMessage() {
		return message;
	}

	public AdminNotFoundException(String message) {
		this.message = message;
	}
	
}
