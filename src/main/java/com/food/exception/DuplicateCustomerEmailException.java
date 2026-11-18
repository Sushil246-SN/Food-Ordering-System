package com.food.exception;

public class DuplicateCustomerEmailException extends RuntimeException {

	public DuplicateCustomerEmailException(String message)
	{
		super(message);
	}
}
