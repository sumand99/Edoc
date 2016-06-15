package com.infogain.edoc.dao;

import java.util.List;

import com.infogain.edoc.dao.impl.PreEmployeeDaoImpl;
import com.infogain.edoc.exception.RegistrationException;
import com.infogain.edoc.model.DashboardData;
import com.infogain.edoc.model.Employee;
import com.infogain.edoc.model.HrBgcApprove;
import com.infogain.edoc.model.PreEmployee;
import com.infogain.edoc.model.Registration;
import com.infogain.edoc.model.SubmitData;

/**
 * 
 * PreEmployeeDao.java
 * Interface class containing methods related to a pre-employee.
 * 
 * @author amant.kumar
 * @see PreEmployeeDaoImpl
 * 
 */
public interface PreEmployeeDao 
{
	public int getPreEmployeeApplicationId(String username);
	public PreEmployee getPreEmployee(String username);
	public List<DashboardData> getPreEmployeeList(String role);
	public List<DashboardData> getPreEmployeeList(int offset, String searchTerm, String role);
	public PreEmployee getPreEmployee(int applicationId);
	public int getCount(String searchTerm, String role);
	public Registration registration(Registration register, String fileLocation) throws RegistrationException;
	public String submitData(int applicationId);
	public List<HrBgcApprove> approveBgc(List<HrBgcApprove> hrBgcApproveList, boolean approve);
	public List<SubmitData> approveRmg(List<SubmitData> approveRmgList);
	public List<SubmitData> approveRmgAdmin(List<SubmitData> approveRmgAdminList);
	public boolean isEmployeeEnabled(int applicationId);
	public boolean enableDisableEmployee(int applicationId, boolean enable);
	public List<SubmitData> rejectRmgAdmin(List<SubmitData> rejectRmgAdminList);
	public List<SubmitData> rejectRmg(List<SubmitData> rejectRmgList);
	public boolean enableDisableEdit(int applicationId, boolean enable);
	public Registration existingEmployeeRegistration(Employee emp,Registration register, String fileLocation)
			throws RegistrationException;
	
}
