package br.com.mybank.rebank.exceptions;

public class NotEnoughBalanceException extends RuntimeException{
	
	public NotEnoughBalanceException(String message) {
		super(message);
	}

}
