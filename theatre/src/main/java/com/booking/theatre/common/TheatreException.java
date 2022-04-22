package com.booking.theatre.common;

import org.springframework.http.HttpStatus;

public class TheatreException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private final HttpStatus status;	
	private String errorMsg;
	public TheatreException(String errorMsg,
			final HttpStatus status) {
		this.errorMsg=errorMsg;
		this.status = status;
	}

	
	public HttpStatus getStatus() {
		return status;
	}


	@Override
	public String getMessage() {
		return errorMsg;
	}

}
