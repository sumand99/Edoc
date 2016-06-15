package com.infogain.edoc.model;

/**
 * 
 * @author amant.kumar
 *
 */
public class DashboardData {
private int bgcDoneFlag;
private int applicationId;
private String email;
private String firstName;
private String lastName;
private int offset;
private int totalCount;
private String searchTerm;
private int preFlag;
private int rmgDoneFlag;
private int rmgAdminDoneFlag;
private String empId;

public DashboardData(int bgcDoneFlag, int applicationId,
		String firstName, String lastName,int preFlag, int rmgDoneFlag, int rmgAdminDoneFlag,String empId, String email) {
	super();
	this.bgcDoneFlag = bgcDoneFlag;
	this.applicationId = applicationId;
	this.email = email;
	this.firstName = firstName;
	this.lastName = lastName;
	this.preFlag = preFlag;
	this.rmgDoneFlag = rmgDoneFlag;
	this.rmgAdminDoneFlag = rmgAdminDoneFlag;
	this.empId = empId;
	
}
public DashboardData(int bgcDoneFlag, int applicationId, String email,
		String firstName, String lastName, int offset, int totalCount,
		String searchTerm) {
	super();
	this.bgcDoneFlag = bgcDoneFlag;
	this.applicationId = applicationId;
	this.email = email;
	this.firstName = firstName;
	this.lastName = lastName;
	this.offset = offset;
	this.totalCount = totalCount;
	this.searchTerm = searchTerm;
}
public int getBgcDoneFlag() {
	return bgcDoneFlag;
}

public int getPreFlag() {
	return preFlag;
}
public void setPreFlag(int preFlag) {
	this.preFlag = preFlag;
}
public void setBgcDoneFlag(int bgcDoneFlag) {
	this.bgcDoneFlag = bgcDoneFlag;
}
public int getApplicationId() {
	return applicationId;
}
public void setApplicationId(int applicationId) {
	this.applicationId = applicationId;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getFirstName() {
	return firstName;
}
public void setFirstName(String firstName) {
	this.firstName = firstName;
}
public String getLastName() {
	return lastName;
}
public void setLastName(String lastName) {
	this.lastName = lastName;
}
public int getOffset() {
	return offset;
}
public void setOffset(int offset) {
	this.offset = offset;
}
public int getTotalCount() {
	return totalCount;
}
public void setTotalCount(int totalCount) {
	this.totalCount = totalCount;
}
public String getSearchTerm() {
	return searchTerm;
}
public void setSearchTerm(String searchTerm) {
	this.searchTerm = searchTerm;
}
public int getRmgDoneFlag() {
	return rmgDoneFlag;
}
public void setRmgDoneFlag(int rmgDoneFlag) {
	this.rmgDoneFlag = rmgDoneFlag;
}
public int getRmgAdminDoneFlag() {
	return rmgAdminDoneFlag;
}
public void setRmgAdminDoneFlag(int rmgAdminDoneFlag) {
	this.rmgAdminDoneFlag = rmgAdminDoneFlag;
}
public String getEmpId() {
	return empId;
}
public void setEmpId(String empId) {
	this.empId = empId;
}


}
