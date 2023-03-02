package com.tech.exceptionHandling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GolbalExceptionHandler {

	@ExceptionHandler(value = NullPointerException.class)
	public ResponseEntity<String> handleNPE(NullPointerException ne){
		String msg = "Operation on null is not allowed";
		return new ResponseEntity<String>(msg,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(value = NoSuchProductFoundException.class)
	public ResponseEntity<String> handleNoSuchProductFoundException(NoSuchProductFoundException nspf){	
		String msg = nspf.getMessage();
		return new ResponseEntity<String>(msg, HttpStatus.BAD_REQUEST);
	}

}
