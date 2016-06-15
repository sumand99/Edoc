package com.infogain.edoc.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(value=HttpStatus.NOT_ACCEPTABLE, reason="Same Username Or Email")
public class RegistrationException extends Exception
{
	private String message;

	public RegistrationException(String message)
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
		return "RegistrationException [message=" + message + "]";
	}


}
