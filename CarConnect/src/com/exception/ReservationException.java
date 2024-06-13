package com.exception;

public class ReservationException extends Exception{

	private static final long serialVersionUID = 1L;
	
	String message;

	public ReservationException(String message) {
		super();
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}

}
