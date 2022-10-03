package com.mailorderpharmacy.drugservice.exception;

public class StockNotFoundException extends Exception
{
	private static final long serialVersionUID = 1L;

	public StockNotFoundException(final String message) {
        super(message);
    }
}