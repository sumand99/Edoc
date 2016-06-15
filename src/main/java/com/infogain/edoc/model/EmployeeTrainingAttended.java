package com.infogain.edoc.model;

public class EmployeeTrainingAttended {
private String courseName;
private String fromToDate;
private String institution;
private String certificateAwarded;
public String getCourseName() {
	return courseName;
}
public void setCourseName(String courseName) {
	this.courseName = courseName;
}

public String getInstitution() {
	return institution;
}
public void setInstitution(String institution) {
	this.institution = institution;
}
public String getCertificateAwarded() {
	return certificateAwarded;
}
public void setCertificateAwarded(String certificateAwarded) {
	this.certificateAwarded = certificateAwarded;
}
public String getFromToDate() {
	return fromToDate;
}
public void setFromToDate(String fromToDate) {
	this.fromToDate = fromToDate;
}

}
