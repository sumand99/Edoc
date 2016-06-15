package com.infogain.edoc.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="File Not Supported Exception") 
public class FileNotSupportedException extends Exception
{
	private String message;

	public FileNotSupportedException(String message)
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
		return "FileNotSupportedException [message=" + message + "]";
	}

}
