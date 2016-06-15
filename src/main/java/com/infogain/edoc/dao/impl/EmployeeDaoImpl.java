package com.infogain.edoc.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Transactional;

import com.infogain.edoc.dao.EmployeeDao;
import com.infogain.edoc.dao.UserDao;
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
import com.infogain.edoc.model.User;
import com.infogain.edoc.model.WelcomeTemplateData;
import com.infogain.edoc.utils.DbQueries.EmployeeDaoQueries;

/**
*This class handles all the operations 
*applicable to an Employee 
*
*@author Akshay.Verma
*/

@Transactional
public class EmployeeDaoImpl implements EmployeeDao
{
	@Autowired
	private UserDao userDao;
		
	private DataSource dataSource;
	private JdbcTemplate templateObj;
	
	/**
	 * This method sets the dataSource from the jdbcBean.xml,
	 * also providing the jdbcTemplate object for jdbc operations
	 * 
	 * @param dataSource
	 */
	public void setDataSource(DataSource dataSource)
	{
		this.dataSource = dataSource;
		templateObj = new JdbcTemplate(dataSource);
	}
	
	/**
	 * This method is used to retrieve the applicationId for
	 * a particular employee
	 * 
	 * @param empId This is the employee Id
	 * @return int This returns the applicationId for the employee
	 */
	@Override
	public int getEmployeeApplicationId(String empId) 
	{
		//String sql = "select application_id from emp where emp_id = ?";
		String sql = EmployeeDaoQueries.getEmployeeApplicationId;
		Integer applicationId =  templateObj.queryForObject(sql, Integer.class, empId);
		return (null == applicationId ? 0 : applicationId); 
	}

	
	/**
	 * This method is used to retrieve Employee Object for
	 * a particular employee username
	 * 
	 * @param empUsername This is the employee username
	 * @return Employee This returns the Employee object
	 */
	@Override
	public Employee getEmployee(String empUsername) 
	{
		//String sql = "select application_id, emp_id, first_name, last_name, email, contact, address, zip, file_location,emp_username from emp where emp_username = ?";
		String sql = EmployeeDaoQueries.getEmployee;
		List<Employee> emp =  templateObj.query(sql, new RowMapper<Employee>() {

			@Override
			public Employee mapRow(ResultSet rs, int rowNum) throws SQLException 
			{
				return new Employee(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),rs.getString(5), rs.getString(6), rs.getString(7),rs.getInt(8), rs.getString(9), rs.getString(10));
			}
		},empUsername);
		
		return emp.isEmpty() ? null : emp.get(0);
	}
	
	/**
	 * This method is used to perform the operations
	 * needed to convert the preEmployee to an Infogain Employee
	 * 
	 * @param makeEmp This is the MakeEmployee object
	 * @param applicationId 
	 * @return String This returns the message success or failure
	 */
	@Override
	public String makeEmployee(MakeEmployee makeEmp, int applicationId) throws MakeEmployeeException
	{	
		//String sql = "select emp_domainId from Aspire.dbo.ERM_EMPLOYEE_MASTER where emp_staffid = ?";
		String sql = EmployeeDaoQueries.makeEmployee;
		
		String empUsername = templateObj.queryForObject(sql, String.class, makeEmp.getEmpId());
		int rows = 0;
		if(empUsername != null)
		{
			empUsername = empUsername.split("\\\\")[1];
			
			//String sql1 = "update emp set emp_id = ?, email = ?, emp_username = ? where application_id = ?";
			String sql1 = EmployeeDaoQueries.makeEmployee_update1;
			
			int rows1 = templateObj.update(sql1, makeEmp.getEmpId(), makeEmp.getEmail(), empUsername, applicationId);
			
			//String sql2 = "update pre_emp set pre_flag = 0 where application_id = ?";
			String sql2 = EmployeeDaoQueries.makeEmployee_update2;
			
			int rows2 = templateObj.update(sql2, applicationId);
			
			List<EmployeeFamilyDetail> employeeFamilyDetailList = getEmployeeFamilyDetailList(applicationId);
			List<EmployeeEmploymentDetail> employeeEmploymentDetailList = getEmployeeEmploymentDetailList(applicationId);
			EmployeePersonalDetail employeePersonalDetail = getEmployeePersonalDetail(applicationId);
			List<EmployeeReference> employeeReferenceList = getEmployeeReferenceList(applicationId);
			
			if(employeeFamilyDetailList != null)
			{
				for(EmployeeFamilyDetail employeeFamilyDetail:employeeFamilyDetailList){
					//sql = "insert into pim_family_details (family_emp_id,family_name, family_dependent,family_Address) values (?,?,?,?)";
					sql = EmployeeDaoQueries.makeEmployee_insert1;
					rows += templateObj.update(sql,makeEmp.getEmpId(), employeeFamilyDetail.getMemberName(), employeeFamilyDetail.getDependent(), employeeFamilyDetail.getPresentLocation());
				}
			}
			
			if(employeeReferenceList != null)
			{
				for(EmployeeReference employeeReference:employeeReferenceList){
					//sql = "insert into pim_references (REFERENCE_EMP_ID,reference_personname,REFERENCE_ORGANIZATION,reference_phoneno) values (?,?,?,?)";
					sql = EmployeeDaoQueries.makeEmployee_insert2;
					rows += templateObj.update(sql,makeEmp.getEmpId(), employeeReference.getReferenceName(), employeeReference.getOrganisationName(), employeeReference.getContact() );
				}
			}
			
			if(employeePersonalDetail != null)
			{
				//sql = "insert into pim_employee_details (emp_det_emp_id,emp_det_maritalstatus, emp_det_permanentadd, emp_det_currentadd) values (?,?,?,?)";
				sql = EmployeeDaoQueries.makeEmployee_insert3;
				rows += templateObj.update(sql,makeEmp.getEmpId(),employeePersonalDetail.getMaritalStatus(), employeePersonalDetail.getPermanentAddress(), employeePersonalDetail.getPresentAddress() );
				//sql = "insert into pim_contact_details(contact_emp_id,contact_homephone) values (?,?)";
				sql = EmployeeDaoQueries.makeEmployee_insert4;
				rows += templateObj.update(sql,makeEmp.getEmpId(),employeePersonalDetail.getPermanentTelephone());
			}	
			return (rows1 >= 1 && rows2 >= 1 && userDao.insertUser(new User(empUsername)) ? "success" : "failure");
		}
		else
		{
			throw new MakeEmployeeException("Entered Employee ID does not exist");
		}
		
		
		
		
	}
	
	
	/**
	 * This method is used to perform the operations
	 * needed to revert the makeEmployee operation
	 * 
	 * @param applicationId 
	 * @return String This returns the message success or failure
	 */
	@Override
	public String rejectEmployee(int applicationId)
	{
		//String sql3 = "select emp_id from emp where application_id = ?";
		String sql3 = EmployeeDaoQueries.rejectEmployee;
		
		String empId = templateObj.queryForObject(sql3, String.class, applicationId);
		
		//String sql1 = "update emp set emp_id = null, email = null where application_id = ?";
		String sql1 = EmployeeDaoQueries.rejectEmployee_update1;
		
		int rows1 = templateObj.update(sql1, applicationId);
		
		//String sql2 = "update pre_emp set pre_flag = 1 where application_id = ?";
		String sql2 = EmployeeDaoQueries.rejectEmployee_update2;
		
		int rows2 = templateObj.update(sql2, applicationId);
		
		
		return (rows1 >= 1 && rows2 >= 1 && userDao.deleteUser(new User(empId)) ? "success" : "failure");
	}
	
	
	/**
	 * This method is used get the Employee Id for an employee
	 * 
	 * @param applicationId 
	 * @return String This returns the employee Id
	 */
	@Override
	public String getEmployeeId(int applicationId)
	{
		//String sql3 = "select emp_id from emp where application_id = ?";
		String sql3 = EmployeeDaoQueries.getEmployeeId;
		
		String empId = templateObj.queryForObject(sql3, String.class, applicationId);
		
		return (null == empId ? null : empId);		
	}

	/**
	 * This method is used save Employee Personal Details into the database
	 * 
	 * @param EmployeePersonalDetail This is the EmployeePersonalDetail object containing respective data  
	 * @param applicationId 
	 * @return boolean true is operation completed else false
	 */
	@Override
	public boolean saveEmployeePersonalDetail(
			EmployeePersonalDetail employeepersonalDetail, int applicationId) 
	{
		String sql = "";
		int rows = 0;
		
		if(ifAlreadyExists(applicationId, "emp_personal_detail"))
		{
			/*sql = "update emp_personal_detail set employee_id=?, name=?, date=?, marital_status=?, date_of_birth=?, permanent_address=?"
					+ ",permanent_telephone=?, present_address=?, present_address_telephone=?, present_address_mobile=?,  passport=?, passport_expiry=?,"
					+ " visa=?, visa_expiry=?, it_experience=?, relevant_experience=?, onsite_experience=? where application_id=?";*/
			sql = EmployeeDaoQueries.saveEmployeePersonalDetail_update;
			rows = templateObj.update(sql, null,employeepersonalDetail.getName(),employeepersonalDetail.getDate() , employeepersonalDetail.getMaritalStatus(), employeepersonalDetail.getDob(), employeepersonalDetail.getPermanentAddress(), employeepersonalDetail.getPermanentTelephone(), employeepersonalDetail.getPresentAddress(), employeepersonalDetail.getPresentAddressTelephone(),
					employeepersonalDetail.getPresentAddressMobile(),  employeepersonalDetail.getPassport(), employeepersonalDetail.getPassportExpiry(), employeepersonalDetail.getVisa(), employeepersonalDetail.getVisaExpiry(), employeepersonalDetail.getItExperience(), employeepersonalDetail.getRelevantExperience(),employeepersonalDetail.getOnsiteExperience(), applicationId);
		}
		else
		{	
			/*sql = "insert into emp_personal_detail(application_id, employee_id, name, date, marital_status, date_of_birth, permanent_address, permanent_telephone, present_address, present_address_telephone,"
				+ "present_address_mobile,  passport, passport_expiry, visa, visa_expiry, it_experience, relevant_experience, onsite_experience) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		*/
			sql = EmployeeDaoQueries.saveEmployeePersonalDetail_insert;
			rows = templateObj.update(sql, applicationId, null,employeepersonalDetail.getName(),employeepersonalDetail.getDate() , employeepersonalDetail.getMaritalStatus(), employeepersonalDetail.getDob(), employeepersonalDetail.getPermanentAddress(), employeepersonalDetail.getPermanentTelephone(), employeepersonalDetail.getPresentAddress(), employeepersonalDetail.getPresentAddressTelephone(),
					employeepersonalDetail.getPresentAddressMobile(),  employeepersonalDetail.getPassport(), employeepersonalDetail.getPassportExpiry(), employeepersonalDetail.getVisa(), employeepersonalDetail.getVisaExpiry(), employeepersonalDetail.getItExperience(), employeepersonalDetail.getRelevantExperience(),employeepersonalDetail.getOnsiteExperience() );
		}
		
		return (rows >= 1 ? Boolean.TRUE : Boolean.FALSE);
	}

	/**
	 * This method is used save Employee Educational Background Details into the database
	 * 
	 * @param EmployeeEducationalBackground This is the object containing respective data  
	 * @param applicationId 
	 * @return nothing
	 */
	@Override
	public void saveEmployeeEducationalBackground(EmployeeEducationalBackground employeeEducationalBackground, int applicationId) 
	{
		/*String sql2 = "insert into emp_education_background(application_id, examination_passed, from_to_date, institution, board, marks) values (?,?,?,?,?,?)";*/
		String sql2 = EmployeeDaoQueries.saveEmployeeEducationBackground;
		int row2 =  templateObj.update(sql2, applicationId, employeeEducationalBackground.getExaminationPassed(),employeeEducationalBackground.getFromToDate(),
				employeeEducationalBackground.getInstitution(),employeeEducationalBackground.getBoard(),employeeEducationalBackground.getMarks());
	}

	/**
	 * This method is used save Employee Training Details into the database
	 * 
	 * @param EmployeeTrainingAttended This is the object containing respective data  
	 * @param applicationId 
	 * @return nothing
	 */
	@Override
	public void saveEmployeeTrainingDetail(EmployeeTrainingAttended employeeTrainingDetail, int applicationId)
	{
		
/*		String sql4 = "insert into emp_training_attended(application_id, course_name, from_to_date, institution, certificate_awarded) values (?,?,?,?,?)";
*/
		String sql4 = EmployeeDaoQueries.saveEmployeeTrainingDetail;
		int row4 = templateObj.update(sql4, applicationId, employeeTrainingDetail.getCourseName(),employeeTrainingDetail.getFromToDate(), employeeTrainingDetail.getInstitution(), employeeTrainingDetail.getCertificateAwarded());
	}

	/**
	 * This method is used save Employee Employment Details into the database
	 * 
	 * @param EmployeeEmploymentDetail This is the object containing respective data  
	 * @param applicationId 
	 * @return nothing
	 */
	@Override
	public void saveEmployeeEmploymentDetail(EmployeeEmploymentDetail employeeEmploymentDetail, int applicationId)
	{
/*		String sql6 = "insert into emp_employment_detail(application_id,employer_name, location, number_of_employee, months, years, experience, designation_held, reporting_to, reason_for_leaving) values (?,?,?,?,?,?,?,?,?,?)";
*/
		String sql6 = EmployeeDaoQueries.saveEmployeeEmploymentDetail;
		int row6 = templateObj.update(sql6, applicationId, employeeEmploymentDetail.getEmployerName(), employeeEmploymentDetail.getLocation(), employeeEmploymentDetail.getNumberOfEmployee(), employeeEmploymentDetail.getMonths(), employeeEmploymentDetail.getYears(),
				employeeEmploymentDetail.getExperience(), employeeEmploymentDetail.getDesignationHeld(), employeeEmploymentDetail.getReportingTo(), employeeEmploymentDetail.getReasonForLeaving());
	}

	/**
	 * This method is used save Current Employment Details into the database
	 * 
	 * @param CurrentEmploymentDetail This is the object containing respective data  
	 * @param applicationId 
	 * @return nothing
	 */
	@Override
	public void saveCurrentEmploymentDetail(
			CurrentEmploymentDetail currentEmploymentDetail, int applicationId) 
	{
/*		String sql6 = "insert into emp_current_employment_detail(application_id,current_project_name, team_size, skills_used, roles_and_responsibilities, organisation_description) values (?,?,?,?,?,?)";
*/		
		String sql6 = EmployeeDaoQueries.saveCurrentEmploymentDetail;
		int row6 = templateObj.update(sql6, applicationId, currentEmploymentDetail.getCurrentProjectName(), currentEmploymentDetail.getTeamSize(), currentEmploymentDetail.getSkillsUsed(),
				currentEmploymentDetail.getRolesAndResponsibilities(), currentEmploymentDetail.getOrganisationDescription()); 
	}

	/**
	 * This method is used save Employee Salary Details into the database
	 * 
	 * @param EmployeeSalaryDetail This is the object containing respective data  
	 * @param applicationId 
	 * @return boolean
	 */
	@Override
	public boolean saveEmployeeSalaryDetail(
			EmployeeSalaryDetail employeeSalaryDetail, int applicationId)
	{
		String sql = "";
		int rows = 0;
		
		if(ifAlreadyExists(applicationId, "emp_salary_detail"))
		{
			/*sql = "update emp_salary_detail set current_ctc_monthly=?, current_ctc_annual=?,current_salary_monthly=?, current_salary_annual=?"
					+ ", current_salary_fixed_monthly=?, current_salary_fixed_annual=?, current_salary_variable_monthly=?, current_salary_variable_annual=?,"
					+ "incentive_Monthly=?, incentive_annual=?, monthly_in_hand=?, expected_salary=?, expected_joining_date=?, notice_period=?, achievements=? where application_id=?";
			*/
			sql = EmployeeDaoQueries.saveEmployeeSalaryDetail_update;
			rows = templateObj.update(sql, employeeSalaryDetail.getCurrentCtcMonthly(), employeeSalaryDetail.getCurrentCtcAnnual(),
					employeeSalaryDetail.getCurrentSalaryMonthly(), employeeSalaryDetail.getCurrentSalaryAnnual(), employeeSalaryDetail.getCurrentSalaryFixedMonthly(),
					employeeSalaryDetail.getCurrentSalaryFixedAnnual(), employeeSalaryDetail.getCurrentSalaryVariableMonthly(), employeeSalaryDetail.getCurrentSalaryVariableAnnual(),
					employeeSalaryDetail.getIncentiveMonthly(),employeeSalaryDetail.getIncentiveAnnual(),employeeSalaryDetail.getMonthlyInHand(),
					employeeSalaryDetail.getExpectedSalary(), employeeSalaryDetail.getExpectedJoiningDate(), employeeSalaryDetail.getNoticePeriod(),employeeSalaryDetail.getAchievements(), applicationId);
		}
		else
		{
			/*sql = "insert into emp_salary_detail(application_id, current_ctc_monthly, current_ctc_annual,current_salary_monthly, current_salary_annual"
					+ ", current_salary_fixed_monthly, current_salary_fixed_annual, current_salary_variable_monthly, current_salary_variable_annual,"
					+ "incentive_Monthly, incentive_annual, monthly_in_hand, expected_salary, expected_joining_date, notice_period, achievements) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			*/
			sql = EmployeeDaoQueries.saveEmployeeSalaryDetail_insert;
			rows = templateObj.update(sql, applicationId, employeeSalaryDetail.getCurrentCtcMonthly(), employeeSalaryDetail.getCurrentCtcAnnual(),
					employeeSalaryDetail.getCurrentSalaryMonthly(), employeeSalaryDetail.getCurrentSalaryAnnual(), employeeSalaryDetail.getCurrentSalaryFixedMonthly(),
					employeeSalaryDetail.getCurrentSalaryFixedAnnual(), employeeSalaryDetail.getCurrentSalaryVariableMonthly(), employeeSalaryDetail.getCurrentSalaryVariableAnnual(),
					employeeSalaryDetail.getIncentiveMonthly(),employeeSalaryDetail.getIncentiveAnnual(),employeeSalaryDetail.getMonthlyInHand(),
					employeeSalaryDetail.getExpectedSalary(), employeeSalaryDetail.getExpectedJoiningDate(), employeeSalaryDetail.getNoticePeriod(),employeeSalaryDetail.getAchievements());
		}
		
		return (rows >= 1 ? Boolean.TRUE : Boolean.FALSE);
	}
	
	
	/**
	 * This method is used save Employee Family Details into the database
	 * 
	 * @param EmployeeFamilyDetail This is the object containing respective data  
	 * @param applicationId 
	 * @return nothing
	 */
	@Override
	public void saveEmployeeFamilyDetail(EmployeeFamilyDetail employeeFamilyDetail, int applicationId)
	{
		/*String sql3 = "insert into emp_family_detail values (?,?,?,?,?,?)";*/
		String sql3 = EmployeeDaoQueries.savemployeeFamilyDetail;
		int row3 =  templateObj.update(sql3, applicationId, employeeFamilyDetail.getMemberName(),employeeFamilyDetail.getOccupation(), employeeFamilyDetail.getAge(), employeeFamilyDetail.getDependent(), employeeFamilyDetail.getPresentLocation());
	}

	
	/**
	 * This method is used save Employee Reference Details into the database
	 * 
	 * @param EmployeeReference This is the object containing respective data  
	 * @param applicationId 
	 * @return nothing
	 */
	@Override
	public void saveEmployeeReference(EmployeeReference employeeReference,int applicationId)
	{
		/*String sql5 = "insert into emp_reference values (?,?,?,?,?)";*/
		String sql5 = EmployeeDaoQueries.savemployeeReference;
		int row5 = templateObj.update(sql5, applicationId, employeeReference.getReferenceName(), employeeReference.getOrganisationName(), employeeReference.getDesignation(), employeeReference.getContact());
		 
	}

	/**
	 * This method is used save Employee Other Details into the database
	 * 
	 * @param EmployeeOtherDetail This is the object containing respective data  
	 * @param applicationId 
	 * 
	 * @return nothing
	 */
	@Override
	public void saveEmployeeOtherDetail(EmployeeOtherDetail employeeOtherDetail, int applicationId) 
	{		
		/*String sql5 = "insert into emp_other_detail values (?,?,?,?,?,?,?,?,?)";*/
		String sql5 = EmployeeDaoQueries.saveEmployeeOtherDetail;
		int row5 = templateObj.update(sql5, applicationId, employeeOtherDetail.getIfReferred(), employeeOtherDetail.getIfContract(),
				employeeOtherDetail.getIfObjection(), employeeOtherDetail.getIfAppliedBefore(), employeeOtherDetail.getIfAppliedForVisa(),
				employeeOtherDetail.getLocationPreference(), employeeOtherDetail.getStrength(), employeeOtherDetail.getToImprove());
	}

	/**
	 * This method is used get the Skills listed from the database
	 * to display it to the employee
	 *
	 * @return List This is the list of Skills object
	 */
	@Override
	public List<Skills> getSkills() 
	{
		/*String sql1 = "select * from skills";*/
		String sql1 = EmployeeDaoQueries.getSkills;

		List<Skills> skills = templateObj.query(sql1, new SkillsMapper());
		return skills;
	}

	/**
	 * This method is used save Employee Skills Details into the database
	 * 
	 * @param SkillDetail This is the object containing respective data  
	 * @param applicationId 
	 * @return nothing
	 */
	@Override
	public void saveEmployeeSkills(SkillDetail skillDetail, int applicationId) 
	{
		
		/*String sql1 = "delete from emp_skills where application_id = ?";*/
		String sql1 = EmployeeDaoQueries.saveEmployeeSkills_delete;
		int row1 = templateObj.update(sql1,applicationId);
		/*String sql4 = "update emp_personal_detail set primary_skill =?, secondary_skill = ? where application_id = ?";*/
		String sql4 = EmployeeDaoQueries.saveEmployeeSkills_update;
		int row4 = templateObj.update(sql4, skillDetail.getPrimarySkill(), skillDetail.getSecondarySkill(), applicationId);
		
		for(EmployeeSkills skills:skillDetail.getSkills())
		{
			/*String sql5 = "insert into emp_skills values (?,?,?)";*/
			String sql5 = EmployeeDaoQueries.saveEmployeeSkills_insert;
			int row5 = templateObj.update(sql5, applicationId, skills.getSkillId(), skills.getRating());
		}
		
	}

	/**
	 * This method is used check whether applicationId already exists
	 * for a particular table in the database
	 * 
	 * @param applicationId 
	 * @param tableName
	 * @return boolean
	 */
	public boolean ifAlreadyExists(int applicationId, String tableName)
	{
		String sql = "select count(*) from "+tableName+" where application_id = ?";
		/*String sql = EmployeeDaoQueries.ifAreadyExists;
		*/Integer count = templateObj.queryForObject(sql, Integer.class, applicationId);
		return (count >= 1 ? Boolean.TRUE : Boolean.FALSE);
	}
	
	/**
	 * This method is used for deleting a specific table
	 * in the database
	 * 
	 * @param applicationId 
	 * @param tableName
	 * @return boolean
	 */
	@Override
	public boolean deleteTable(int applicationId, String tableName)
	{
		String sql = "delete from " + tableName + " where application_id = ?";
		/*String sql = EmployeeDaoQueries.deleteTable;
		*/int count = templateObj.update(sql, applicationId);
		return (count >= 1 ? Boolean.TRUE : Boolean.FALSE);
	}
	
	/**
	 * This method is used get Employee Personal details from the database
	 * 
	 * @param applicationId 
	 * @return EmployeePersonalDetail This is the object containing respective data  
	 */
	@Override
	public EmployeePersonalDetail getEmployeePersonalDetail(int applicationId)
	{
		/*String sql = "select * from emp_personal_detail where application_id = ?";*/
		String sql = EmployeeDaoQueries.getEmployeePersonalDetail;
		
		List<EmployeePersonalDetail> emp =  templateObj.query(sql, new RowMapper<EmployeePersonalDetail>() {

			@Override
			public EmployeePersonalDetail mapRow(ResultSet rs, int rowNum) throws SQLException 
			{
				EmployeePersonalDetail empPersonalDetail = new EmployeePersonalDetail();
				empPersonalDetail.setName(rs.getString(3));
				empPersonalDetail.setDate(rs.getString(4));
				empPersonalDetail.setDob(rs.getString(5));
				empPersonalDetail.setMaritalStatus(rs.getString(6));
				empPersonalDetail.setPermanentAddress(rs.getString(7));
				empPersonalDetail.setPermanentTelephone(rs.getString(8));
				empPersonalDetail.setPresentAddress(rs.getString(9));
				empPersonalDetail.setPresentAddressTelephone(rs.getString(10));
				empPersonalDetail.setPresentAddressMobile(rs.getString(11));
				empPersonalDetail.setPassport(rs.getString(12));
				empPersonalDetail.setPassportExpiry(rs.getString(13));				
				empPersonalDetail.setRelevantExperience(rs.getString(14));
				empPersonalDetail.setVisa(rs.getString(15));
				empPersonalDetail.setVisaExpiry(rs.getString(16));
				empPersonalDetail.setOnsiteExperience(rs.getString(17));
				empPersonalDetail.setItExperience(rs.getString(18));
				empPersonalDetail.setPrimarySkill(rs.getString(19));
				empPersonalDetail.setSecondarySkill(rs.getString(20));
				
				return empPersonalDetail;
			}
		},applicationId);
		
		return (emp.isEmpty() ? null : emp.get(0)); 
		
	}
	
	/**
	 * This method is used get a list of Employee Educational Background details
	 *  from the database
	 * 
	 * @param applicationId 
	 * @return List This is the list of  EmployeeEducationalBackground object containing respective data  
	 */
	@Override
	public List<EmployeeEducationalBackground> getEmployeeEducationalBackgroundList(int applicationId)
	{
		/*String sql = "select * from emp_education_background where application_id = ?";*/
		String sql = EmployeeDaoQueries.getEmployeeEducationalBackgroundList;
		List<EmployeeEducationalBackground> empEducationalList =  templateObj.query(sql, new RowMapper<EmployeeEducationalBackground>() {

			@Override
			public EmployeeEducationalBackground mapRow(ResultSet rs, int rowNum) throws SQLException 
			{
				EmployeeEducationalBackground empEduBack = new EmployeeEducationalBackground();
				empEduBack.setExaminationPassed(rs.getString(2));
				empEduBack.setFromToDate(rs.getString(3));
				empEduBack.setInstitution(rs.getString(4));
				empEduBack.setBoard(rs.getString(5));
				empEduBack.setMarks(rs.getString(6));
				
				return empEduBack;
			}
		}, applicationId);
		
		return (empEducationalList.isEmpty() ? null : empEducationalList);
	}
	
	/**
	 * This method is used get a list of Employee Training Attended details
	 *  from the database
	 * 
	 * @param applicationId 
	 * @return List This is the list of  EmployeeTrainingAttended object containing respective data  
	 */
	@Override
	public List<EmployeeTrainingAttended> getEmployeeTrainingAttendedList(int applicationId)
	{
		/*String sql = "select * from emp_training_attended where application_id = ?";*/
		String sql = EmployeeDaoQueries.getEmployeeTrainingAttendedList;
		List<EmployeeTrainingAttended> empTrainingList =  templateObj.query(sql, new RowMapper<EmployeeTrainingAttended>() {

			@Override
			public EmployeeTrainingAttended mapRow(ResultSet rs, int rowNum) throws SQLException 
			{
				EmployeeTrainingAttended emp = new EmployeeTrainingAttended();
				emp.setCourseName(rs.getString(2));
				emp.setFromToDate(rs.getString(3));
				emp.setInstitution(rs.getString(4));
				emp.setCertificateAwarded(rs.getString(5));
				
				return emp;
			}
		}, applicationId);
		
		
		return (empTrainingList.isEmpty() ? null : empTrainingList);
	}
	
	
	/**
	 * This method is used get a list of Employee Employment details
	 *  from the database
	 * 
	 * @param applicationId 
	 * @return List This is the list of  EmployeeEmploymentDetail object containing respective data  
	 */
	@Override
	public List<EmployeeEmploymentDetail> getEmployeeEmploymentDetailList(int applicationId)
	{
		/*String sql = "select * from emp_employment_detail where application_id = ?";*/
		String sql = EmployeeDaoQueries.getEmployeeEmploymentDetailList;
		List<EmployeeEmploymentDetail> empemploymentList =  templateObj.query(sql, new RowMapper<EmployeeEmploymentDetail>() {

			@Override
			public EmployeeEmploymentDetail mapRow(ResultSet rs, int rowNum) throws SQLException 
			{
				EmployeeEmploymentDetail emp = new EmployeeEmploymentDetail();
				emp.setEmployerName(rs.getString(2));
				emp.setLocation(rs.getString(3));
				emp.setNumberOfEmployee(rs.getInt(4));
				emp.setMonths(rs.getString(5));
				emp.setYears(rs.getString(6));
				emp.setExperience(rs.getString(7));
				emp.setDesignationHeld(rs.getString(8));
				emp.setReportingTo(rs.getString(9));
				emp.setReasonForLeaving(rs.getString(10));
				
				
				return emp;
			}
		}, applicationId);
		
		return (empemploymentList.isEmpty() ? null : empemploymentList);
	}
	
	/**
	 * This method is used get Current Employment details
	 *  from the database
	 * 
	 * @param applicationId 
	 * @return CurrentEmploymentDetail This is the object containing respective data  
	 */
	@Override
	public CurrentEmploymentDetail getCurrentEmploymentDetail(int applicationId)
	{
		/*String sql = "select * from emp_current_employment_detail where application_id = ?";*/
		String sql = EmployeeDaoQueries.getCurrentEmploymentDetail;
		
		List<CurrentEmploymentDetail> curEmpDetail =  templateObj.query(sql, new RowMapper<CurrentEmploymentDetail>() {

			@Override
			public CurrentEmploymentDetail mapRow(ResultSet rs, int rowNum) throws SQLException 
			{
				CurrentEmploymentDetail emp = new CurrentEmploymentDetail();
				emp.setCurrentProjectName(rs.getString(2));
				emp.setTeamSize(rs.getString(3));
				emp.setSkillsUsed(rs.getString(4));
				emp.setRoleAndResponsibilities(rs.getString(5));
				emp.setOrganisationDescription(rs.getString(6));
				
				return emp;
			}
		},applicationId);
		
		return (curEmpDetail.isEmpty() ? null : curEmpDetail.get(0));
	}
	
	/**
	 * This method is used get Employee Salary details
	 *  from the database
	 * 
	 * @param applicationId 
	 * @return EmployeeSalaryDetail This is the object containing respective data  
	 */
	@Override
	public EmployeeSalaryDetail getEmployeeSalaryDetail(int applicationId)
	{
		/*String sql = "select * from emp_salary_detail where application_id = ?";*/
		String sql = EmployeeDaoQueries.getEmployeeSalaryDetail;
		
		List<EmployeeSalaryDetail> empSalaryDetail =  templateObj.query(sql, new RowMapper<EmployeeSalaryDetail>() {

			@Override
			public EmployeeSalaryDetail mapRow(ResultSet rs, int rowNum) throws SQLException 
			{
				EmployeeSalaryDetail emp = new EmployeeSalaryDetail();
				emp.setCurrentCtcMonthly(rs.getString(2));
				emp.setCurrentCtcAnnual(rs.getString(3));
				emp.setCurrentSalaryMonthly(rs.getString(4));
				emp.setCurrentSalaryAnnual(rs.getString(5));
				emp.setCurrentSalaryFixedMonthly(rs.getString(6));
				emp.setCurrentSalaryFixedAnnual(rs.getString(7));
				emp.setCurrentSalaryVariableMonthly(rs.getString(8));
				emp.setCurrentSalaryVariableAnnual(rs.getString(9));
				emp.setIncentiveMonthly(rs.getString(10));
				emp.setIncentiveAnnual(rs.getString(11));
				emp.setNoticePeriod(rs.getString(12));
				emp.setExpectedSalary(rs.getString(13));
				emp.setExpectedJoiningDate(rs.getString(14));
				emp.setMonthlyInHand(rs.getString(15));
				emp.setAchievements(rs.getString(16));
				
				return emp;
			}
		},applicationId);
		
		return (empSalaryDetail.isEmpty() ? null : empSalaryDetail.get(0));
		
	}
	
	/**
	 * This method is used get a list of Employee Family details
	 *  from the database
	 * 
	 * @param applicationId 
	 * @return List This is the list of  EmployeeFamilyDetail object containing respective data  
	 */
	@Override
	public List<EmployeeFamilyDetail> getEmployeeFamilyDetailList(int applicationId)
	{
		/*String sql = "select * from emp_family_detail where application_id = ?";*/
		String sql = EmployeeDaoQueries.getEmployeeFamilyDetailList;
		List<EmployeeFamilyDetail> empFamilyList =  templateObj.query(sql, new RowMapper<EmployeeFamilyDetail>() {

			@Override
			public EmployeeFamilyDetail mapRow(ResultSet rs, int rowNum) throws SQLException 
			{
				EmployeeFamilyDetail emp = new EmployeeFamilyDetail();
				emp.setMemberName(rs.getString(2));
				emp.setOccupation(rs.getString(3));
				emp.setAge(rs.getInt(4));
				emp.setDependent(rs.getString(5));
				emp.setPresentLocation(rs.getString(6));
				return emp;
			}
		}, applicationId);
		
		return (empFamilyList.isEmpty() ? null : empFamilyList);
	}
	
	/**
	 * This method is used get a list of Employee Family details
	 *  from the database
	 * 
	 * @param applicationId 
	 * @return List This is the list of  EmployeeReference object containing respective data  
	 */
	@Override
	public List<EmployeeReference> getEmployeeReferenceList(int applicationId)
	{
		/*String sql = "select * from emp_reference where application_id = ?";*/
		String sql = EmployeeDaoQueries.getEmployeeReferenceList;
		List<EmployeeReference> empReferenceList =  templateObj.query(sql, new RowMapper<EmployeeReference>() {

			@Override
			public EmployeeReference mapRow(ResultSet rs, int rowNum) throws SQLException 
			{
				EmployeeReference emp = new EmployeeReference();
				emp.setReferenceName(rs.getString(2));
				emp.setOrganisationName(rs.getString(3));
				emp.setDesignation(rs.getString(4));
				emp.setContact(rs.getString(5));
				
				return emp;
			}
		}, applicationId);
		
		return (empReferenceList.isEmpty() ? null : empReferenceList);
	}
	
	/**
	 * This method is used get Employee Other details
	 *  from the database
	 * 
	 * @param applicationId 
	 * @return EmployeeOtherDetail This is the object containing respective data  
	 */
	@Override
	public EmployeeOtherDetail getEmployeeOtherDetail(int applicationId)
	{
		/*String sql = "select * from emp_other_detail where application_id = ?";*/
		String sql = EmployeeDaoQueries.getEmployeeOtherDetail;
		List<EmployeeOtherDetail> empOtherDetail =  templateObj.query(sql, new RowMapper<EmployeeOtherDetail>() {

			@Override
			public EmployeeOtherDetail mapRow(ResultSet rs, int rowNum) throws SQLException 
			{
				EmployeeOtherDetail emp = new EmployeeOtherDetail();
				emp.setIfReferred(rs.getString(2));
				emp.setIfContract(rs.getString(3));
				emp.setIfObjection(rs.getString(4));
				emp.setIfAppliedBefore(rs.getString(5));
				emp.setIfAppliedForVisa(rs.getString(6));
				emp.setLocationPreference(rs.getString(7));
				emp.setStrength(rs.getString(8));
				emp.setToImprove(rs.getString(9));

				return emp;
			}
		},applicationId);
		
		return (empOtherDetail.isEmpty() ? null : empOtherDetail.get(0));
	}
	
	/**
	 * This method is used get a list of Employee Skills details
	 *  from the database
	 * 
	 * @param applicationId 
	 * @return List This is the list of  EmployeeSkills object containing respective data  
	 */
	@Override
	public List<EmployeeSkills> getEmployeeSkillsList(int applicationId)
	{
		/*String sql = "select * from emp_skills where application_id = ?";*/
		String sql = EmployeeDaoQueries.getEmployeeSkillsList;
		List<EmployeeSkills> empSkillList =  templateObj.query(sql, new RowMapper<EmployeeSkills>() {

			@Override
			public EmployeeSkills mapRow(ResultSet rs, int rowNum) throws SQLException 
			{
				EmployeeSkills emp = new EmployeeSkills();
				emp.setSkillId(rs.getInt(2));
				emp.setRating(rs.getInt(3));

				return emp;
			}
		}, applicationId);
		
		return (empSkillList.isEmpty() ? null : empSkillList);
	}

	/**
	 * This method is used get Education Form Data
	 *  from the database
	 * 
	 * @param applicationId 
	 * @return EducationFormData This is the object containing respective data  
	 */
	@Override
	public EducationFormData getEducationFormData() {
		/*String sql = "select college_name from PIM_College_master";*/
		String sql = EmployeeDaoQueries.getEducationFormData;
		
		List<String> collegeList =  templateObj.query(sql, new RowMapper<String>() {

			@Override
			public String mapRow(ResultSet rs, int rowNum) throws SQLException 
			{
				return rs.getString(1);
			}
		});	
		/*sql = "select degree_name from PIM_degree_master";*/
		sql = EmployeeDaoQueries.getEducationFormData1;
		List<String> degreeList =  templateObj.query(sql, new RowMapper<String>() {

			@Override
			public String mapRow(ResultSet rs, int rowNum) throws SQLException 
			{
				return rs.getString(1);
			}
		});	
		/*sql = "select unv_name from PIM_university_master";*/
		sql = EmployeeDaoQueries.getEducationFormData2;
		List<String> universityList =  templateObj.query(sql, new RowMapper<String>() {

			@Override
			public String mapRow(ResultSet rs, int rowNum) throws SQLException 
			{
				return rs.getString(1);
			}
		});	
		
		EducationFormData efd = new EducationFormData();
		efd.setCollegeList(collegeList);
		efd.setDegreeList(degreeList);
		efd.setUniversityList(universityList);
		
		return efd;
	}

	@Override
	public void saveBackgroundCheckDetail(
			BackgroundCheckFormData backgroundCheckFormData, int applicationId) {
		String sql = null;
		BackgroundCheckPersonalDetail bgpd = backgroundCheckFormData.getBackgroundCheckPersonalDetail();
		
		
		/*sql = "INSERT INTO [background_check_personal_detail]([application_id],[name_of_applicant],[date_of_birth],[place_of_birth]"+
          " ,[Sex] ,[Nationality] ,[Father_name] ,[passport_number]"+
           ",[home_phone]  ,[office_phone],[mobile],[permanent_address]"+
           ",[permanent_city] ,[permanent_state] ,[permanent_pin_code] ,[permanent_telephone]"+
           ",[permanent_duration_from] ,[permanent_duration_to],[permanent_nature_of_location]"+
           ",[present_address] ,[present_city] ,[present_state],[present_pin_code]"+
           ",[present_telephone] ,[present_duration_from],[present_duration_to] ,[present_nature_of_location],[i_agree])"+
    " VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";*/
		sql = EmployeeDaoQueries.saveBackgroundCheckDetail_insert;
         
      templateObj.update(sql, applicationId,bgpd.getNameOfApplicant(), bgpd.getDateOfBirth(), bgpd.getPlaceOfBirth(),
    		   bgpd.getSex(), bgpd.getNationality(), bgpd.getFatherName(), bgpd.getPassportNumber(), bgpd.getHomePhone(),
    		   bgpd.getOfficePhone(), bgpd.getMobile(), bgpd.getPermanentAddress(), bgpd.getPermanentCity(), bgpd.getPermanentState(), bgpd.getPermanentPinCode(),
    		   bgpd.getPermanentTelephone(), bgpd.getPermanentDurationFrom(), bgpd.getPermanentDurationTo(), bgpd.getPermanentNatureOfLocation(),
    		   bgpd.getPresentAddress(), bgpd.getPresentCity(), bgpd.getPresentState(), bgpd.getPresentPinCode(),
    		   bgpd.getPresentTelephone(),bgpd.getPresentDurationFrom(), bgpd.getPresentDurationTo(), bgpd.getPresentNatureOfLocation(), bgpd.getiAgree());
		
      for(BackgroundCheckEmploymentDetail bged : backgroundCheckFormData.getBackgroundCheckEmploymentDetailList()){
    	 /* sql = "INSERT INTO [background_check_employment_detail] ([application_id] ,[employer_name] ,"
    	  		+ "[employee_id] ,[employment_from] ,[employment_to] ,[street_address] ,[employer_telephone] ,"
    	  		+ "[remuneration] ,[employer_city] ,[employer_state] ,[employer_country] ,[employer_postal_code] ,"
    	  		+ "[designation_held] ,[reason_for_leaving] ,[employment_status] ,[outsourcing_agency_name] ,"
    	  		+ "[outsourcing_agency_address] ,[outsourcing_agency_telephone] ,[supervisor_name] ,"
    	  		+ "[supervisor_title] ,[supervisor_telephone] ,[supervisor_email] ,[hr_manager_name] ,"
    	  		+ "[hr_manager_telephone] ,[hr_manager_email] ,[employment_description] ,[current_employment_authority]) "
    	  		+ "VALUES "
    	  		+ "(? ,? ,? ,? ,? ,? ,? ,?,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,?)";*/
    	  sql = EmployeeDaoQueries.saveBackgroundCheckDetail_insert1;
    	  templateObj.update(sql, applicationId, bged.getEmployerName(), 
    			  bged.getEmployeeId(), bged.getEmploymentFrom(), bged.getEmploymentTo(), bged.getStreetAddress(), bged.getEmployerTelephone(),
    			  bged.getRemuneration(), bged.getEmployerCity(), bged.getEmployerState(), bged.getEmployerCountry(), bged.getEmployerPostalCode(),
    			  bged.getDesignationHeld(), bged.getReasonForLeaving(), bged.getEmploymentStatus(), bged.getOutsourcingAgencyName(),
    			  bged.getOutsourcingAgencyAddress(), bged.getOutsourcingAgencyTelephone(), bged.getSupervisorName(),
    			  bged.getSupervisorTitle(), bged.getSupervisorTelephone(), bged.getSupervisorEmail(), bged.getHrManagerName(),
    			  bged.getHrManagerTelephone(), bged.getHrManagerEmail(), bged.getEmploymentDescription(), bged.getCurrentEmploymentAuthority());
      }
      
      for(BackgroundCheckEducationalDetail educationDetail:backgroundCheckFormData.getBackgroundCheckEducationalDetailList()){
    	/*  sql = "INSERT INTO [background_check_educational_detail] ([application_id] ,[examination_passed] ,"
    	  		+ "[institution] ,[board] ,[course_attended] ,[marks] ,[from_to_date] ,[roll_number] ,[discipline])"
    	  		+ " VALUES (? ,? ,? , ?, ?, ?, ?,? ,?)";*/
    	  sql = EmployeeDaoQueries.saveBackgroundCheckDetail_insert2;
    	  
    	 templateObj.update(sql, applicationId,educationDetail.getExaminationPassed(), educationDetail.getInstitution(),
    			 educationDetail.getBoard(), educationDetail.getCourseAttended(), educationDetail.getMarks(), educationDetail.getFromToDate(),
    			 educationDetail.getRollNumber(), educationDetail.getDiscipline());
      }
	}

	@Override
	public List<BackgroundCheckEmploymentDetail> backgroundCheckEmploymentDetail(
			int applicationId) {
		/*String sql = "SELECT [application_id] ,[employer_name] ,[employee_id] ,[employment_from] "
				+ ",[employment_to] ,[street_address] ,[employer_telephone] ,[remuneration] "
				+ ",[employer_city] ,[employer_state] ,[employer_country] ,[employer_postal_code] "
				+ ",[designation_held] ,[reason_for_leaving] ,[employment_status] "
				+ ",[outsourcing_agency_name] ,[outsourcing_agency_address] ,[outsourcing_agency_telephone] "
				+ ",[supervisor_name] ,[supervisor_title] ,[supervisor_telephone] ,[supervisor_email] "
				+ ",[hr_manager_name] ,[hr_manager_telephone] ,[hr_manager_email] ,[employment_description] "
				+ ",[current_employment_authority] FROM [background_check_employment_detail] where application_id = ?";*/
		String sql = EmployeeDaoQueries.backgroundCheckEmploymentDetail;
		List<BackgroundCheckEmploymentDetail> backgroundCheckEmploymentDetailList = templateObj.query(sql, new RowMapper<BackgroundCheckEmploymentDetail>() {

			@Override
			public BackgroundCheckEmploymentDetail mapRow(ResultSet rs, int rowNum) throws SQLException 
			{
				BackgroundCheckEmploymentDetail bged = new BackgroundCheckEmploymentDetail();
				bged.setApplicationId(rs.getInt(1));
				bged.setCurrentEmploymentAuthority(rs.getString(27));
				bged.setDesignationHeld(rs.getString(13));
				bged.setEmployeeId(rs.getString(3));
				bged.setEmployerCity(rs.getString(9));
				bged.setEmployerCountry(rs.getString(11));
				bged.setEmployerName(rs.getString(2));
				bged.setEmployerPostalCode(rs.getString(12));
				bged.setEmployerState(rs.getString(10));
				bged.setEmployerTelephone(rs.getString(7));
				bged.setEmploymentDescription(rs.getString(26));
				bged.setEmploymentFrom(rs.getString(4));
				bged.setEmploymentStatus(rs.getString(15));
				bged.setEmploymentTo(rs.getString(5));
				bged.setHrManagerEmail(rs.getString(25));
				bged.setHrManagerName(rs.getString(23));
				bged.setHrManagerTelephone(rs.getString(24));
				bged.setOutsourcingAgencyAddress(rs.getString(17));
				bged.setOutsourcingAgencyName(rs.getString(16));
				bged.setOutsourcingAgencyTelephone(rs.getString(18));
				bged.setReasonForLeaving(rs.getString(14));
				bged.setRemuneration(rs.getString(8));
				bged.setStreetAddress(rs.getString(6));
				bged.setSupervisorEmail(rs.getString(22));
				bged.setSupervisorName(rs.getString(19));
				bged.setSupervisorTelephone(rs.getString(21));
				bged.setSupervisorTitle(rs.getString(20));
				
				return bged;
			}
		}, applicationId);	
		return backgroundCheckEmploymentDetailList;
	}

	@Override
	public List<BackgroundCheckEducationalDetail> backgroundCheckEducationalDetail(
			int applicationId) {
		/*String sql = "SELECT [application_id] ,[examination_passed] ,[institution] "
				+ ",[board] ,[course_attended] ,[marks] ,[from_to_date] ,[roll_number] "
				+ ",[discipline] FROM [background_check_educational_detail] where application_id = ?";*/
		String sql = EmployeeDaoQueries.backgroundCheckEducationalDetail;
		List<BackgroundCheckEducationalDetail> backgroundCheckEducationalDetailList = templateObj.query(sql, new RowMapper<BackgroundCheckEducationalDetail>() {

			@Override
			public BackgroundCheckEducationalDetail mapRow(ResultSet rs, int rowNum) throws SQLException 
			{
				BackgroundCheckEducationalDetail educationalDetail = new BackgroundCheckEducationalDetail();
				educationalDetail.setApplicationId(rs.getInt(1));
				educationalDetail.setBoard(rs.getString(4));
				educationalDetail.setCourseAttended(rs.getString(5));
				educationalDetail.setDiscipline(rs.getString(9));
				educationalDetail.setExaminationPassed(rs.getString(2));
				educationalDetail.setFromToDate(rs.getString(7));
				educationalDetail.setInstitution(rs.getString(3));
				educationalDetail.setMarks(rs.getString(6));
				educationalDetail.setRollNumber(rs.getString(8));
				
				return educationalDetail;
			}
		}, applicationId);	
		return backgroundCheckEducationalDetailList;
	}

	@Override
	public BackgroundCheckPersonalDetail backgroundCheckPersonalDetail(
			int applicationId) {
		/*String sql = "SELECT [application_id] " 
				+ ",[name_of_applicant] " 
				+ ",[date_of_birth] " 
				+ ",[place_of_birth] " 
				+ ",[Sex] " 
				+ ",[Nationality] " 
				+ ",[Father_name] " 
				+ ",[passport_number] " 
				+ ",[home_phone] " 
				+ ",[office_phone] " 
				+ ",[mobile] " 
				+ ",[permanent_address] " 
				+ ",[permanent_city] " 
				+ ",[permanent_state] " 
				+ ",[permanent_pin_code] " 
				+ ",[permanent_telephone] " 
				+ ",[permanent_duration_from] " 
				+ ",[permanent_duration_to] " 
				+ ",[permanent_nature_of_location] " 
				+ ",[present_address] " 
				+ ",[present_city] " 
				+ ",[present_state] " 
				+ ",[present_pin_code] " 
				+ ",[present_telephone] " 
				+ ",[present_duration_from] " 
				+ ",[present_duration_to] " 
				+ ",[present_nature_of_location] " 
				+ ",[i_agree] FROM [background_check_personal_detail] where application_id = ?"; */
		String sql = EmployeeDaoQueries.backgroundCheckPersonalDetail;
		List<BackgroundCheckPersonalDetail> backgroundCheckPersonalDetailList = templateObj.query(sql, new RowMapper<BackgroundCheckPersonalDetail>() {

			@Override
			public BackgroundCheckPersonalDetail mapRow(ResultSet rs, int rowNum) throws SQLException 
			{
				BackgroundCheckPersonalDetail bgpd = new BackgroundCheckPersonalDetail();
				bgpd.setApplicationId(rs.getInt(1));
				bgpd.setDateOfBirth(rs.getString(3));
				bgpd.setFatherName(rs.getString(7));
				bgpd.setHomePhone(rs.getString(9));
				bgpd.setiAgree(rs.getString(28));
				bgpd.setMobile(rs.getString(11));
				bgpd.setNameOfApplicant(rs.getString(2));
				bgpd.setNationality(rs.getString(6));
				bgpd.setOfficePhone(rs.getString(10));
				bgpd.setPassportNumber(rs.getString(8));
				bgpd.setPermanentAddress(rs.getString(12));
				bgpd.setPermanentCity(rs.getString(13));
				bgpd.setPermanentDurationFrom(rs.getString(17));
				bgpd.setPermanentDurationTo(rs.getString(18));
				bgpd.setPermanentNatureOfLocation(rs.getString(26));
				bgpd.setPermanentPinCode(rs.getString(15));
				bgpd.setPermanentState(rs.getString(14));
				bgpd.setPermanentTelephone(rs.getString(16));
				bgpd.setPlaceOfBirth(rs.getString(4));
				bgpd.setPresentAddress(rs.getString(20));
				bgpd.setPresentCity(rs.getString(21));
				bgpd.setPresentDurationFrom(rs.getString(25));
				bgpd.setPresentDurationTo(rs.getString(26));
				bgpd.setPresentNatureOfLocation(rs.getString(27));
				bgpd.setPresentPinCode(rs.getString(23));
				bgpd.setPresentState(rs.getString(22));
				bgpd.setPresentTelephone(rs.getString(24));
				bgpd.setSex(rs.getString(5));
				
				return bgpd;
			}
		}, applicationId);	
		
		if(backgroundCheckPersonalDetailList.isEmpty())
			return null;
		else
			return backgroundCheckPersonalDetailList.get(0);
		
		
	}

	@Override
	public Integer makeMultipleEmployee(MakeEmployee makeEmp) throws MakeEmployeeException
	{	
		//String sql = "select emp_domainId from Aspire.dbo.ERM_EMPLOYEE_MASTER where emp_staffid = ?";
		String sql = EmployeeDaoQueries.makeMultipleEmployee;
		String empUsername = templateObj.queryForObject(sql, String.class, makeEmp.getEmpId());
		int rows = 0;
		if(empUsername != null)
		{
			empUsername = empUsername.split("\\\\")[1];
			
			//String sql1 = "update emp set emp_id = ?, email = ?, emp_username = ? where application_id = ?";
			String sql1 = EmployeeDaoQueries.makeEmployee_update1;
			
			int rows1 = templateObj.update(sql1, makeEmp.getEmpId(), makeEmp.getEmail(), empUsername, makeEmp.getApplicationId());
			
			//String sql2 = "update pre_emp set pre_flag = 0 where application_id = ?";
			String sql2 = EmployeeDaoQueries.makeMultipleEmployee_update; 
			
			int rows2 = templateObj.update(sql2, makeEmp.getApplicationId());
			
			List<EmployeeFamilyDetail> employeeFamilyDetailList = getEmployeeFamilyDetailList(makeEmp.getApplicationId());
			List<EmployeeEmploymentDetail> employeeEmploymentDetailList = getEmployeeEmploymentDetailList(makeEmp.getApplicationId());
			EmployeePersonalDetail employeePersonalDetail = getEmployeePersonalDetail(makeEmp.getApplicationId());
			List<EmployeeReference> employeeReferenceList = getEmployeeReferenceList(makeEmp.getApplicationId());
			
			if(employeeFamilyDetailList != null)
			{
				for(EmployeeFamilyDetail employeeFamilyDetail:employeeFamilyDetailList){
					//sql = "insert into pim_family_details (family_emp_id,family_name, family_dependent,family_Address) values (?,?,?,?)";
					sql =  EmployeeDaoQueries.makeMultipleEmployee_insert1;
					rows += templateObj.update(sql,makeEmp.getEmpId(), employeeFamilyDetail.getMemberName(), employeeFamilyDetail.getDependent(), employeeFamilyDetail.getPresentLocation());
				}
			}
			
			if(employeeReferenceList != null)
			{
				for(EmployeeReference employeeReference:employeeReferenceList){
					//sql = "insert into pim_references (REFERENCE_EMP_ID,reference_personname,REFERENCE_ORGANIZATION,reference_phoneno) values (?,?,?,?)";
					sql = EmployeeDaoQueries.makeMultipleEmployee_insert2;
					rows += templateObj.update(sql,makeEmp.getEmpId(), employeeReference.getReferenceName(), employeeReference.getOrganisationName(), employeeReference.getContact() );
				}
			}
			
			if(employeePersonalDetail != null)
			{
				//sql = "insert into pim_employee_details (emp_det_emp_id,emp_det_maritalstatus, emp_det_permanentadd, emp_det_currentadd) values (?,?,?,?)";
				sql =  EmployeeDaoQueries.makeMultipleEmployee_insert3;
				rows += templateObj.update(sql,makeEmp.getEmpId(),employeePersonalDetail.getMaritalStatus(), employeePersonalDetail.getPermanentAddress(), employeePersonalDetail.getPresentAddress() );
				//sql = "insert into pim_contact_details(contact_emp_id,contact_homephone) values (?,?)";
				sql =  EmployeeDaoQueries.makeMultipleEmployee_insert4;
				rows += templateObj.update(sql,makeEmp.getEmpId(),employeePersonalDetail.getPermanentTelephone());
			}	
			//return (userDao.insertUser(new User(empUsername)) ? makeEmp.getEmail() : "failure");
			return (userDao.insertUser(new User(empUsername)) ? makeEmp.getApplicationId() : 0);
			
		}
		else
		{
			throw new MakeEmployeeException("Entered Employee ID does not exist");
		}
		
		
		
		
	}	

	@Override
	public Employee getEmployeeByApplicationId(int applicationId) {
		/*String sql = "select * from emp where application_id = ?";*/
		String sql = EmployeeDaoQueries.getEmployeeByApplicationId;
		List<Employee> emp =  templateObj.query(sql, new RowMapper<Employee>() {

			@Override
			public Employee mapRow(ResultSet rs, int rowNum) throws SQLException 
			{
				return new Employee(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),rs.getString(5), rs.getString(6), rs.getString(7),rs.getInt(8), rs.getString(9), rs.getString(10));
			}
		},applicationId);
		
		return emp.isEmpty() ? null : emp.get(0);
		
	}
	
	@Override
	public WelcomeTemplateData getWelcomeTemplateData(int applicationId)
	{
		//String sql = "select application_id, name, date_of_birth, marital_status, primary_skill from emp_personal_detail where application_id = ?";
		String sql = EmployeeDaoQueries.WelcomeTemplateData1;
		List<WelcomeTemplateData> welcomeTemplateDataList = templateObj.query(sql, new RowMapper<WelcomeTemplateData>() {

			@Override
			public WelcomeTemplateData mapRow(ResultSet rs, int rowNum) throws SQLException 
			{
				WelcomeTemplateData data = new WelcomeTemplateData();
				data.setApplicationId(rs.getInt(1));
				data.setName(rs.getString(2));
				data.setDob(rs.getString(3));
				data.setMaritalStatus(rs.getString(4));
				data.setPrimarySkill(rs.getString(5));
				return data;
			}
		}, applicationId);	
		
		WelcomeTemplateData data;
		
		if(!welcomeTemplateDataList.isEmpty())
			data = welcomeTemplateDataList.get(0);
		else
			return new WelcomeTemplateData(applicationId);
		
		//sql = "select employer_name from emp_employment_detail where application_id = ?";
		sql = EmployeeDaoQueries.WelcomeTemplateData2;
		List<String> companyNames = templateObj.query(sql, new RowMapper<String>() {

			@Override
			public String mapRow(ResultSet rs, int rowNum) throws SQLException 
			{
				return rs.getString(1);
			}
		}, applicationId); 
		if(!companyNames.isEmpty())
			data.setCompanyNames(companyNames);
		
		//sql = "select sex from background_check_personal_detail where application_id = ?";
		sql = EmployeeDaoQueries.WelcomeTemplateData3;
		List<String> sex = templateObj.query(sql, new RowMapper<String>() {

			@Override
			public String mapRow(ResultSet rs, int rowNum) throws SQLException 
			{
				return rs.getString(1);
			}
		}, applicationId); 
		
		if(!sex.isEmpty())
			data.setSex(sex.get(0));
		
		//sql = "select examination_passed, institution from emp_education_background where application_id = ?";
		sql = EmployeeDaoQueries.WelcomeTemplateData4;
		List<WelcomeTemplateData> welcomeDataList = templateObj.query(sql, new RowMapper<WelcomeTemplateData>() {

			@Override
			public WelcomeTemplateData mapRow(ResultSet rs, int rowNum) throws SQLException 
			{
				WelcomeTemplateData data = new WelcomeTemplateData();
				data.setExamination(rs.getString(1));
				data.setInstitute(rs.getString(2));
				return data;
			}
		}, applicationId);	
		
		if(!welcomeDataList.isEmpty()){
			data.setExamination(welcomeDataList.get(0).getExamination());
			data.setInstitute(welcomeDataList.get(0).getInstitute());
		}
			
		return data;
	}
	

	@Override
	public Employee getEmployeeFromAspire(String employeeDomainId) {
		String empDomainId = "igglobal\\"+ employeeDomainId;
/*		String sql = "select emp_firstName, emp_lastName, emp_mailid,emp_staffid from Aspire.dbo.ERM_EMPLOYEE_MASTER where emp_domainId = ?";
*/	
		String sql = EmployeeDaoQueries.getEmployeeFromAspire;
		List<Employee> emp =  templateObj.query(sql, new RowMapper<Employee>() {

			@Override
			public Employee mapRow(ResultSet rs, int rowNum) throws SQLException 
			{
				return new Employee(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4));
			}
		},empDomainId);
		return (!emp.isEmpty())?emp.get(0):null;
	}

	@Override
	public void addRole(AddRole addRole) {
		//String sql = "select username from user_role where username = ? and role_id = ?";
		String sql = EmployeeDaoQueries.addRole;
		List<String> userList =  templateObj.query(sql, new RowMapper<String>() {

			@Override
			public String mapRow(ResultSet rs, int rowNum) throws SQLException 
			{
				return rs.getString(1);
			}
		},addRole.getEmployeeId(), addRole.getRole());
		if(userList.isEmpty()){
			//sql = "insert into user_role(role_id,username) values(?,?)";
			sql = EmployeeDaoQueries.addRole_insert;
			templateObj.update(sql, addRole.getRole(),addRole.getEmployeeId());
		}
	}

	@Override
	public void removeRole(AddRole addRole) {
		//String sql = "delete from user_role where username = ? and role_id = ?";
		String sql = EmployeeDaoQueries.removeRole_delete;
		templateObj.update(sql, addRole.getEmployeeId(), addRole.getRole());
		
	}
	
	
}
