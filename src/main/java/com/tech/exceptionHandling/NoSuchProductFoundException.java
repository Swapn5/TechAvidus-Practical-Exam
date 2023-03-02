package com.tech.exceptionHandling;

public class NoSuchProductFoundException extends RuntimeException{

	
	public NoSuchProductFoundException(String msg){
		super(msg);
	}
	
	
}
