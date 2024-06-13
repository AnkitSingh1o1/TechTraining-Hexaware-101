package com.exception;

public class InvalidCredentialException extends Exception {
	private static final long serialVersionUID = -3686831835634438320L;
	private String message;

	public InvalidCredentialException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
