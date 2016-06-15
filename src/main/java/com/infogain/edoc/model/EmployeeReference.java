package com.infogain.edoc.model;

public class EmployeeReference {
private int applicationId;
private String referenceName;
private String designation;
private String organisationName;
private String contact;
public String getReferenceName() {
	return referenceName;
}
public void setReferenceName(String referenceName) {
	this.referenceName = referenceName;
}
public String getDesignation() {
	return designation;
}
public void setDesignation(String designation) {
	this.designation = designation;
}
public String getOrganisationName() {
	return organisationName;
}
public void setOrganisationName(String organisationName) {
	this.organisationName = organisationName;
}
public String getContact() {
	return contact;
}
public void setContact(String contact) {
	this.contact = contact;
}
public int getApplicationId() {
	return applicationId;
}
public void setApplicationId(int applicationId) {
	this.applicationId = applicationId;
}

}
