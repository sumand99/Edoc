package com.infogain.edoc.model;

public class EmployeeFamilyDetail {
private int applicationId;
private String memberName;
private String occupation;
private int age;
private String dependent;
private String presentLocation;

public String getMemberName() {
	return memberName;
}
public void setMemberName(String memberName) {
	this.memberName = memberName;
}
public int getAge() {
	return age;
}
public void setAge(int age) {
	this.age = age;
}
public String getDependent() {
	return dependent;
}
public void setDependent(String dependent) {
	this.dependent = dependent;
}
public String getPresentLocation() {
	return presentLocation;
}
public void setPresentLocation(String presentLocation) {
	this.presentLocation = presentLocation;
}
public int getApplicationId() {
	return applicationId;
}
public void setApplicationId(int applicationId) {
	this.applicationId = applicationId;
}
public String getOccupation() {
	return occupation;
}
public void setOccupation(String occupation) {
	this.occupation = occupation;
}

}
