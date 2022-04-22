package com.booking.movie.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


@ControllerAdvice
public class GlobalExceptionHandler {
	
	private static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@ExceptionHandler(MovieException.class)
	  @ResponseBody
	  public ResponseEntity<String> handleMovieException(
	      final MovieException error) {
		logger.error("MovieException while processing request: ", error);
	    final HttpStatus status =
	        error.getStatus() == null ? HttpStatus.INTERNAL_SERVER_ERROR : error.getStatus();
	    return ResponseEntity.status(status).body(error.getMessage());

	  }
}
