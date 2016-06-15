package com.infogain.edoc.dao;

import java.util.List;

import com.infogain.edoc.dao.impl.EmployeeDaoImpl;
import com.infogain.edoc.exception.MakeEmployeeException;
import com.infogain.edoc.model.AddRole;
import com.infogain.edoc.model.BackgroundCheckEducationalDetail;
import com.infogain.edoc.model.BackgroundCheckEmploymentDetail;
import com.infogain.edoc.model.BackgroundCheckFormData;
import com.infogain.edoc.model.BackgroundCheckPersonalDetail;
import com.infogain.edoc.model.CurrentEmploymentDetail;
import com.infogain.edoc.model.EducationFormData;
import com.infogain.edoc.model.Employee;
import com.infogain.edoc.model.EmployeeEducationalBackground;
import com.infogain.edoc.model.EmployeeEmploymentDetail;
import com.infogain.edoc.model.EmployeeFamilyDetail;
import com.infogain.edoc.model.EmployeeOtherDetail;
import com.infogain.edoc.model.EmployeePersonalDetail;
import com.infogain.edoc.model.EmployeeReference;
import com.infogain.edoc.model.EmployeeSalaryDetail;
import com.infogain.edoc.model.EmployeeSkills;
import com.infogain.edoc.model.EmployeeTrainingAttended;
import com.infogain.edoc.model.MakeEmployee;
import com.infogain.edoc.model.SkillDetail;
import com.infogain.edoc.model.Skills;
import com.infogain.edoc.model.WelcomeTemplateData;
/**
 * 
 * EmployeeDao.java
 * Interface class containing methods related to an employee.
 * 
 * @author amant.kumar
 * @see EmployeeDaoImpl
 */
public interface EmployeeDao
{
	public int getEmployeeApplicationId(String username);
	public Employee getEmployee(String empId);
	public String makeEmployee(MakeEmployee makeEmp, int applicationId) throws MakeEmployeeException;
	public String rejectEmployee(int applicationId);
	public boolean saveEmployeePersonalDetail(EmployeePersonalDetail employeepersonalDetail, int applicationId);
	public void saveEmployeeEducationalBackground(EmployeeEducationalBackground employeeEducationalBackground, int applicationId);
	public void saveEmployeeTrainingDetail(EmployeeTrainingAttended employeeTrainingDetail, int applicationId);
	public void saveEmployeeEmploymentDetail(
			EmployeeEmploymentDetail employeeEmploymentDetail, int applicationId);
	public void saveCurrentEmploymentDetail(
			CurrentEmploymentDetail currentEmploymentDetail, int applicationId);
	public boolean saveEmployeeSalaryDetail(EmployeeSalaryDetail employeeSalaryDetail, int applicationId);
	public void saveEmployeeFamilyDetail(
			EmployeeFamilyDetail employeeFamilyDetail, int applicationId);
	public void saveEmployeeReference(EmployeeReference employeeReference,
			int applicationId);
	public void saveEmployeeOtherDetail(
			EmployeeOtherDetail employeeOtherDetail, int applicationId);
	public List<Skills> getSkills();
	public void saveEmployeeSkills(SkillDetail skillDetail, int applicationId);
	public boolean deleteTable(int applicationId, String tableName);
	public EmployeePersonalDetail getEmployeePersonalDetail(int applicationId);
	public List<EmployeeEducationalBackground> getEmployeeEducationalBackgroundList(int applicationId);
	public List<EmployeeTrainingAttended> getEmployeeTrainingAttendedList(int applicationId);
	public List<EmployeeEmploymentDetail> getEmployeeEmploymentDetailList(int applicationId);
	public CurrentEmploymentDetail getCurrentEmploymentDetail(int applicationId);
	public EmployeeSalaryDetail getEmployeeSalaryDetail(int applicationId);
	public List<EmployeeFamilyDetail> getEmployeeFamilyDetailList(int applicationId);
	public List<EmployeeReference> getEmployeeReferenceList(int applicationId);
	public EmployeeOtherDetail getEmployeeOtherDetail(int applicationId);
	public List<EmployeeSkills> getEmployeeSkillsList(int applicationId);
	public String getEmployeeId(int applicationId);
	public EducationFormData getEducationFormData();
	public void saveBackgroundCheckDetail(
			BackgroundCheckFormData backgroundCheckFormData, int applicationId);
	public List<BackgroundCheckEmploymentDetail> backgroundCheckEmploymentDetail(int applicationId);
	public List<BackgroundCheckEducationalDetail> backgroundCheckEducationalDetail(int applicationId);
	public BackgroundCheckPersonalDetail backgroundCheckPersonalDetail(int applicationId);
	public Integer makeMultipleEmployee(MakeEmployee makeEmp) throws MakeEmployeeException;
	public Employee getEmployeeByApplicationId(int applicationId);
	public WelcomeTemplateData getWelcomeTemplateData(int applicationId);
	public Employee getEmployeeFromAspire(String employeeDomainId);
	public void addRole(AddRole addRole);
	public void removeRole(AddRole addRole);
}
