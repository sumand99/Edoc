package com.infogain.edoc.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(value=HttpStatus.NOT_ACCEPTABLE, reason="File Already Exists Exception") 
public class FileAlreadyExistsException extends Exception
{
	private String message;

	public FileAlreadyExistsException(String message)
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
