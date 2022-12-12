package com.exception;

public class MyException extends RuntimeException
{
	String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public MyException(String message) {
		super(message);
		this.message = message;
	}

}
