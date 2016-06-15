package com.infogain.edoc.aop;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
public class FolderLogging 
{
	private static final Logger logger = LoggerFactory.getLogger(FolderLogging.class);
	
	@Pointcut("execution(* com.infogain.edoc.controller.FolderController.createFolder*(..))")
	private void createFolder(){}
	
	@Pointcut("execution(* com.infogain.edoc.controller.FolderController.deleteFolder*(..))")
	private void deleteFolder(){}
	
	@Pointcut("execution(* com.infogain.edoc.controller.FolderController.approveFolderForAdmin(..))")
	private void approveFolderForAdmin(){}
	
	@Pointcut("execution(* com.infogain.edoc.controller.FolderController.rejectFolderForAdmin(..))")
	private void rejectFolderForAdmin(){}
	
	@Pointcut("execution(* com.infogain.edoc.controller.FolderController.changeVisibility(..))")
	private void changeVisibility(){}
	
	
	@Before("createFolder()")
	public void beforeCreateFolder(){
		logger.info("Starting create Folder operation");
	}	
	@After("createFolder()")
	public void afterCreateFolder(){
		logger.info("create Folder operation completed");
	}
	
	@Before("deleteFolder()")
	public void beforeDeleteFolder(){
		logger.info("Starting delete Folder operation");
	}	
	@After("deleteFolder()")
	public void afterDeleteFolder(){
		logger.info("delete Folder operation completed");
	}
	
	@Before("approveFolderForAdmin()")
	public void beforeApproveFolderForAdmin(){
		logger.info("Starting approve Folder For Admin operation");
	}	
	@After("approveFolderForAdmin()")
	public void afterApproveFolderForAdmin(){
		logger.info("approve Folder For Admin operation completed");
	}
	
	@Before("rejectFolderForAdmin()")
	public void beforeRejectFolderForAdmin(){
		logger.info("Starting reject Folder For Admin operation");
	}	
	@After("rejectFolderForAdmin()")
	public void afterRejectFolderForAdmin(){
		logger.info("reject Folder For Admin operation completed");
	}
	
	@Before("changeVisibility()")
	public void beforeChangeVisibility(){
		logger.info("Starting change Visibility operation");
	}	
	@After("changeVisibility()")
	public void afterChangeVisibility(){
		logger.info("change Visibility operation completed");
	}
}
