package com.mailorderpharmacy.drugservice.exception;

public class DrugNotFoundException extends RuntimeException
{
	private static final long serialVersionUID = 1L;

	public DrugNotFoundException(final String message) {
        super(message);
    }
}