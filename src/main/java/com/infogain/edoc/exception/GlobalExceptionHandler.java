package com.infogain.edoc.exception;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import fr.opensagres.xdocreport.converter.XDocConverterException;

@ControllerAdvice
public class GlobalExceptionHandler 
{
	@ResponseStatus(value=HttpStatus.UNSUPPORTED_MEDIA_TYPE, reason="File Not Supported Exception")
	@ExceptionHandler(FileNotSupportedException.class)
	public void fileNotSupportedException(HttpServletRequest request, Exception ex)
	{
		ex.printStackTrace();
	}
	
	@ResponseStatus(value=HttpStatus.NOT_ACCEPTABLE, reason="File Already Exists Exception")
	@ExceptionHandler(FileAlreadyExistsException.class)
	public void fileAlreadyExistsException(HttpServletRequest request, Exception ex)
	{
		ex.printStackTrace();
	} 
	
	@ResponseStatus(value=HttpStatus.NOT_ACCEPTABLE, reason="Same Username Or Email")
	@ExceptionHandler(RegistrationException.class)
	public void registrationException(HttpServletRequest request, Exception ex)
	{
		ex.printStackTrace();
	} 
	
	@ResponseStatus(value=HttpStatus.FORBIDDEN, reason="Entered Employee ID does not exist")
	@ExceptionHandler(MakeEmployeeException.class)
	public void makeEmployeeException(HttpServletRequest request, Exception ex)
	{
		ex.printStackTrace();
	} 
	
	
	@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="File Not Found")
	@ExceptionHandler(IOException.class)
	public void handleIOException(HttpServletRequest request, Exception ex)
	{

		ex.printStackTrace();
	} 
	
	@ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR, reason="Other Exception")
	@ExceptionHandler(Exception.class)
	public void handleOtherException(HttpServletRequest request, Exception ex)
	{

		ex.printStackTrace();
	} 
	
	@ResponseStatus(value=HttpStatus.UNPROCESSABLE_ENTITY, reason="Could not convert from dox to pdf")
	@ExceptionHandler(XDocConverterException.class)
	public void doxToPdfConversionException(HttpServletRequest request, Exception ex)
	{

		ex.printStackTrace();
	} 
	
	@ResponseStatus(value=HttpStatus.NOT_ACCEPTABLE, reason="File Size Not Supported Exception")
	@ExceptionHandler(MaxUploadSizeExceededException.class)
	public void maxUploadSizeExceededException(HttpServletRequest request, Exception ex)
	{
		ex.printStackTrace();
	} 
}
