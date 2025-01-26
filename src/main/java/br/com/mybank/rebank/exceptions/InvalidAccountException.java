package br.com.mybank.rebank.exceptions;

public class InvalidAccountException extends RuntimeException{
	
	public InvalidAccountException(String message) {
		super(message);
	}

}
