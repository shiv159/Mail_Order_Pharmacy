package com.mailorderpharmacy.drugservice.exception;

public class InvalidTokenException extends Exception
{
	private static final long serialVersionUID = 1L;

	public InvalidTokenException(final String message) {
        super(message);
    }
}