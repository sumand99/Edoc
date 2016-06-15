package com.infogain.edoc.exception;


public class MakeEmployeeException extends Exception
{
	private String message;

	public MakeEmployeeException(String message)
	{
		super();
		this.message = message;
	}

	@Override
	public String getMessage()
	{
		return message;
	}

	@Override
	public String toString() {
		return "MakeEmployeeException [message=" + message + "]";
	}

}
