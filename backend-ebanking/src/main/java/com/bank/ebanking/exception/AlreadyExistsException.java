package com.bank.ebanking.exception;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class AlreadyExistsException extends RuntimeException {


	private static final long serialVersionUID = 1L;

	public AlreadyExistsException(String message) {
		super(message);
	}

}
