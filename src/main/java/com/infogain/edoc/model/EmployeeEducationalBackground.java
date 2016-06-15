package com.infogain.edoc.model;

public class EmployeeEducationalBackground {
private int applicationId;
private String examinationPassed;
private String fromToDate;
private String institution;
private String board;
private String marks;
public int getApplicationId() {
	return applicationId;
}
public void setApplicationId(int applicationId) {
	this.applicationId = applicationId;
}
public String getExaminationPassed() {
	return examinationPassed;
}
public void setExaminationPassed(String examinationPassed) {
	this.examinationPassed = examinationPassed;
}

public String getInstitution() {
	return institution;
}
public void setInstitution(String institution) {
	this.institution = institution;
}

public String getBoard() {
	return board;
}
public void setBoard(String board) {
	this.board = board;
}
public String getMarks() {
	return marks;
}
public void setMarks(String marks) {
	this.marks = marks;
}
public String getFromToDate() {
	return fromToDate;
}
public void setFromToDate(String fromToDate) {
	this.fromToDate = fromToDate;
}


}
