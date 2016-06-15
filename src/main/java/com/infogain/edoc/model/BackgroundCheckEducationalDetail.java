package com.infogain.edoc.model;

public class BackgroundCheckEducationalDetail {
private int applicationId;
private String examinationPassed;
private String institution;
private String board;
private String courseAttended;
private String marks;
private String fromToDate;
private String rollNumber;
private String discipline;

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

public String getCourseAttended() {
	return courseAttended;
}
public void setCourseAttended(String courseAttended) {
	this.courseAttended = courseAttended;
}
public String getMarks() {
	return marks;
}
public void setMarks(String marks) {
	this.marks = marks;
}

public String getRollNumber() {
	return rollNumber;
}
public void setRollNumber(String rollNumber) {
	this.rollNumber = rollNumber;
}
public String getDiscipline() {
	return discipline;
}
public void setDiscipline(String discipline) {
	this.discipline = discipline;
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
public String getFromToDate() {
	return fromToDate;
}
public void setFromToDate(String fromToDate) {
	this.fromToDate = fromToDate;
}


}
