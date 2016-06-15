package com.infogain.edoc.model;

import java.util.List;

public class EmployeeQualification {
private List<EmployeeEducationalBackground> employeeEducationalBackgroundList;
private List<EmployeeTrainingAttended> employeeTrainingAttendedList;
public List<EmployeeEducationalBackground> getEmployeeEducationalBackgroundList() {
	return employeeEducationalBackgroundList;
}
public void setEmployeeEducationalBackgroundList(
		List<EmployeeEducationalBackground> employeeEducationalBackgroundList) {
	this.employeeEducationalBackgroundList = employeeEducationalBackgroundList;
}
public List<EmployeeTrainingAttended> getEmployeeTrainingAttendedList() {
	return employeeTrainingAttendedList;
}
public void setEmployeeTrainingAttendedList(
		List<EmployeeTrainingAttended> employeeTrainingAttendedList) {
	this.employeeTrainingAttendedList = employeeTrainingAttendedList;
}



}
