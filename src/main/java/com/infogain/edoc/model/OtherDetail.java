package com.infogain.edoc.model;

import java.util.List;

public class OtherDetail {
private List<EmployeeFamilyDetail> employeeFamilyDetailList;
private List<EmployeeReference> employeeReferenceList;
private List<EmployeeOtherDetail> employeeOtherDetail;
public List<EmployeeFamilyDetail> getEmployeeFamilyDetailList() {
	return employeeFamilyDetailList;
}
public void setEmployeeFamilyDetailList(
		List<EmployeeFamilyDetail> employeeFamilyDetailList) {
	this.employeeFamilyDetailList = employeeFamilyDetailList;
}
public List<EmployeeReference> getEmployeeReferenceList() {
	return employeeReferenceList;
}
public void setEmployeeReferenceList(
		List<EmployeeReference> employeeReferenceList) {
	this.employeeReferenceList = employeeReferenceList;
}
public List<EmployeeOtherDetail> getEmployeeOtherDetail() {
	return employeeOtherDetail;
}
public void setEmployeeOtherDetail(List<EmployeeOtherDetail> employeeOtherDetail) {
	this.employeeOtherDetail = employeeOtherDetail;
}



}
