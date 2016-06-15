package com.infogain.edoc.aop;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
public class EmployeeLogging 
{
	private static final Logger logger = LoggerFactory.getLogger(EmployeeLogging.class);
	
	@Pointcut("execution(* com.infogain.edoc.controller.EmployeeController.makeEmployee(..))")
	private void makeEmployee(){}
	
	@Pointcut("execution(* com.infogain.edoc.controller.EmployeeController.submitData(..))")
	private void submitData(){}
	
	@Pointcut("execution(* com.infogain.edoc.controller.EmployeeController.approveBGC(..))")
	private void approveBGC(){}
	
	@Pointcut("execution(* com.infogain.edoc.controller.EmployeeController.rejectEmployee(..))")
	private void rejectEmployee(){}
	
	@Pointcut("execution(* com.infogain.edoc.controller.EmployeeController.initiateBGC(..))")
	private void initiateBGC(){}
	
	@Pointcut("execution(* com.infogain.edoc.controller.EmployeeController.approveRmg(..))")
	private void approveRmg(){}
	
	@Pointcut("execution(* com.infogain.edoc.controller.EmployeeController.approveRmgAdmin(..))")
	private void approveRmgAdmin(){}
	
	@Pointcut("execution(* com.infogain.edoc.controller.EmployeeController.rejectRmg(..))")
	private void rejectRmg(){}
	
	@Pointcut("execution(* com.infogain.edoc.controller.EmployeeController.rejectRmgAdmin(..))")
	private void rejectRmgAdmin(){}
	
	@Pointcut("execution(* com.infogain.edoc.controller.EmployeeController.enableUser(..))")
	private void enableUser(){}
	
	@Pointcut("execution(* com.infogain.edoc.controller.EmployeeController.disableUser(..))")
	private void disableUser(){}
	
	@Pointcut("execution(* com.infogain.edoc.controller.EmployeeController.saveEmployeePersonalDetail(..))")
	private void saveEmployeePersonalDetail(){}
	
	@Pointcut("execution(* com.infogain.edoc.controller.EmployeeController.saveEmployeeQualification(..))")
	private void saveEmployeeQualification(){}
	
	@Pointcut("execution(* com.infogain.edoc.controller.EmployeeController.saveEmploymentDetail(..))")
	private void saveEmploymentDetail(){}
	
	@Pointcut("execution(* com.infogain.edoc.controller.EmployeeController.saveEmployeeSalaryDetail(..))")
	private void saveEmployeeSalaryDetail(){}
	
	@Pointcut("execution(* com.infogain.edoc.controller.EmployeeController.saveOtherDetail(..))")
	private void saveOtherDetail(){}
	
	@Pointcut("execution(* com.infogain.edoc.controller.EmployeeController.saveSkillDetail(..))")
	private void saveSkillDetail(){}
	
	@Pointcut("execution(* com.infogain.edoc.controller.EmployeeController.saveBackgroundCheckForm(..))")
	private void saveBackgroundCheckForm(){}
	
	@Before("makeEmployee()")
	public void beforeMakeEmployee(){
		logger.info("Starting Make Employee operation");
	}	
	@After("makeEmployee()")
	public void afterMakeEmployee(){
		logger.info("Make Employee operation completed");
	}
	
	@Before("submitData()")
	public void beforeSubmitData(){
		logger.info("Starting Submit Data operation");
	}	
	@After("submitData()")
	public void afterSubmitData(){
		logger.info("Submit Data operation completed");
	}
	
	@Before("approveBGC()")
	public void beforeApproveBGC(){
		logger.info("Starting approve BGC operation");
	}	
	@After("approveBGC()")
	public void afterApproveBGC(){
		logger.info("approve BGC operation completed");
	}
	
	@Before("rejectEmployee()")
	public void beforeRejectEmployee(){
		logger.info("Starting employee rejection operation");
	}	
	@After("rejectEmployee()")
	public void afterRejectEmployee(){
		logger.info("employee rejection operation completed");
	}
	
	@Before("initiateBGC()")
	public void beforeInitiateBGC(){
		logger.info("Starting initiate BGC operation");
	}	
	@After("initiateBGC()")
	public void afterInitiateBGC(){
		logger.info("initiate BGC operation completed");
	}
	
	@Before("approveRmg()")
	public void beforeApproveRmg(){
		logger.info("Starting approve Rmg operation");
	}	
	@After("approveRmg()")
	public void afterApproveRmg(){
		logger.info("approve Rmg operation completed");
	}
	
	@Before("approveRmgAdmin()")
	public void beforeApproveRmgAdmin(){
		logger.info("Starting approve Rmg Admin operation");
	}	
	@After("approveRmgAdmin()")
	public void afterApproveRmgAdmin(){
		logger.info("approve Rmg Admin operation completed");
	}
	
	@Before("rejectRmg()")
	public void beforeRejectRmg(){
		logger.info("Starting reject Rmg operation");
	}	
	@After("rejectRmg()")
	public void afterRejectRmg(){
		logger.info("reject Rmg operation completed");
	}
	
	@Before("rejectRmgAdmin()")
	public void beforeRejectRmgAdmin(){
		logger.info("Starting reject Rmg Admin operation");
	}	
	@After("rejectRmgAdmin()")
	public void afterRejectRmgAdmin(){
		logger.info("reject Rmg Admin operation completed");
	}
	
	@Before("enableUser()")
	public void beforeEnableUser(){
		logger.info("Starting enable user operation");
	}	
	@After("enableUser()")
	public void afterEnableUser(){
		logger.info("enable user operation completed");
	}
	
	
	@Before("disableUser()")
	public void beforeDisableUser(){
		logger.info("Starting disable user operation");
	}	
	@After("disableUser()")
	public void afterDisableUser(){
		logger.info("disable user operation completed");
	}
	
	@Before("saveEmployeePersonalDetail()")
	public void beforeSaveEmployeePersonalDetail(){
		logger.info("Starting save Employee Personal Detail operation");
	}	
	@After("saveEmployeePersonalDetail()")
	public void afterSaveEmployeePersonalDetail(){
		logger.info("save Employee Personal Detail operation completed");
	}
	
	@Before("saveEmployeeQualification()")
	public void beforeSaveEmployeeQualification(){
		logger.info("Starting save Employee Qualification operation");
	}	
	@After("saveEmployeeQualification()")
	public void afterSaveEmployeeQualification(){
		logger.info("save Employee Qualification operation completed");
	}
	
	@Before("saveEmploymentDetail()")
	public void beforeSaveEmploymentDetail(){
		logger.info("Starting save Employment Detail operation");
	}	
	@After("saveEmploymentDetail()")
	public void afterSaveEmploymentDetail(){
		logger.info("save Employment Detail operation completed");
	}
	
	@Before("saveEmployeeSalaryDetail()")
	public void beforeSaveEmployeeSalaryDetail(){
		logger.info("Starting save Employee Salary Detail operation");
	}	
	@After("saveEmployeeSalaryDetail()")
	public void afterSaveEmployeeSalaryDetail(){
		logger.info("save Employee Salary Detail operation completed");
	}
		
	@Before("saveOtherDetail()")
	public void beforeSaveOtherDetail(){
		logger.info("Starting save Other Detail operation");
	}	
	@After("saveOtherDetail()")
	public void afterSaveOtherDetail(){
		logger.info("save Other Detail operation completed");
	}
	
	@Before("saveSkillDetail()")
	public void beforeSaveSkillDetail(){
		logger.info("Starting save Skill Detail operation");
	}	
	@After("saveSkillDetail()")
	public void afterSaveSkillDetail(){
		logger.info("save Skill Detail operation completed");
	}
	
	@Before("saveBackgroundCheckForm()")
	public void beforeSaveBackgroundCheckForm(){
		logger.info("Starting Save Background Check Form operation");
	}	
	@After("saveBackgroundCheckForm()")
	public void afterSaveBackgroundCheckForm(){
		logger.info("Save Background Check Form operation completed");
	}
}
