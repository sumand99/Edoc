package com.infogain.edoc.aop;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
public class FileLogging 
{
	private static final Logger logger = LoggerFactory.getLogger(FileLogging.class);
	
	@Pointcut("execution(* com.infogain.edoc.controller.FileController.uploadFile*(..))")
	private void uploadFile(){}
	
	@Pointcut("execution(* com.infogain.edoc.controller.FileController.approveFile*(..))")
	private void approveFile(){}
	
	@Pointcut("execution(* com.infogain.edoc.controller.FileController.rejectFile*(..))")
	private void rejectFile(){}
	
	@Pointcut("execution(* com.infogain.edoc.controller.FileController.deleteFile*(..))")
	private void deleteFile(){}
	
	@Pointcut("execution(* com.infogain.edoc.controller.FileController.doDownload*(..))")
	private void downloadZip(){}
	
	@Before("uploadFile()")
	public void beforeFileUpload(){
		logger.info("Starting file upload operation");
	}	
	@After("uploadFile()")
	public void afterFileUpload(){
		logger.info("File upload operation completed");
	}
	
	@Before("approveFile()")
	public void beforeApproveFile(){
		logger.info("Starting file approve operation");
	}
	@After("approveFile()")
	public void afterApproveFile(){
		logger.info("File approve operation completed");
	}
	
	@Before("rejectFile()")
	public void beforeRejectFile(){
		logger.info("Starting file rejection operation");
	}
	@After("rejectFile()")
	public void afterRejectFile(){
		logger.info("File rejection operation completed");
	}
	
	@Before("deleteFile()")
	public void beforeDeleteFile(){
		logger.info("Starting file deletion operation");
	}
	@After("deleteFile()")
	public void afterDeleteFile(){
		logger.info("File deletion operation completed");
	}
	
	@Before("downloadZip()")
	public void beforeDownloadZip(){
		logger.info("Starting Download Zip operation");
	}
	@After("downloadZip()")
	public void afterDownloadZip(){
		logger.info("Download Zip operation completed");
	}
}
