package com.infogain.edoc.model;

import java.util.List;

public class EmploymentDetail {
private List<EmployeeEmploymentDetail> employeeEmploymentDetailList;
private CurrentEmploymentDetail currentEmploymentDetail;
public List<EmployeeEmploymentDetail> getEmployeeEmploymentDetailList() {
	return employeeEmploymentDetailList;
}
public void setEmployeeEmploymentDetailList(
		List<EmployeeEmploymentDetail> employeeEmploymentDetailList) {
	this.employeeEmploymentDetailList = employeeEmploymentDetailList;
}
public CurrentEmploymentDetail getCurrentEmploymentDetail() {
	return currentEmploymentDetail;
}
public void setCurrentEmploymentDetail(
		CurrentEmploymentDetail currentEmploymentDetail) {
	this.currentEmploymentDetail = currentEmploymentDetail;
}



}
