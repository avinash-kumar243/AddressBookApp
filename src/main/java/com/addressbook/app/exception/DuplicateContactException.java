package com.addressbook.app.exception;

public class DuplicateContactException extends RuntimeException {
	
	public DuplicateContactException(String message) {
		super(message);
	}
}
