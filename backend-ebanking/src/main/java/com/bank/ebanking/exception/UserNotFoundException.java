package com.bank.ebanking.exception;

public class UserNotFoundException extends RuntimeException {
    
	public UserNotFoundException(String message) {
        super(message);
    }
}
