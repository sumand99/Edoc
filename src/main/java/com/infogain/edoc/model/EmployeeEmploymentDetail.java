package com.infogain.edoc.model;

public class EmployeeEmploymentDetail {
private int applicationId;
private String employerName;
private String location;
private int numberOfEmployee;
private String months;
private String years;
private String experience;
private String designationHeld;
private String reportingTo;

private String reasonForLeaving;

public String getEmployerName() {
	return employerName;
}
public void setEmployerName(String employerName) {
	this.employerName = employerName;
}
public String getLocation() {
	return location;
}
public void setLocation(String location) {
	this.location = location;
}
public int getNumberOfEmployee() {
	return numberOfEmployee;
}
public void setNumberOfEmployee(int numberOfEmployee) {
	this.numberOfEmployee = numberOfEmployee;
}

public String getExperience() {
	return experience;
}
public void setExperience(String experience) {
	this.experience = experience;
}
public String getDesignationHeld() {
	return designationHeld;
}
public void setDesignationHeld(String designationHeld) {
	this.designationHeld = designationHeld;
}

public String getReasonForLeaving() {
	return reasonForLeaving;
}
public void setReasonForLeaving(String reasonForLeaving) {
	this.reasonForLeaving = reasonForLeaving;
}
public int getApplicationId() {
	return applicationId;
}
public void setApplicationId(int applicationId) {
	this.applicationId = applicationId;
}
public String getMonths() {
	return months;
}
public void setMonths(String months) {
	this.months = months;
}
public String getYears() {
	return years;
}
public void setYears(String years) {
	this.years = years;
}
public String getReportingTo() {
	return reportingTo;
}
public void setReportingTo(String reportingTo) {
	this.reportingTo = reportingTo;
}


}
