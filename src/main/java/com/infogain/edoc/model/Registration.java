package com.infogain.edoc.model;

import java.util.List;

public class Registration
{
private String username;
private int applicationId;
private List<String> email;
private String firstName;
private String lastName;
private String password;
private String address;
private String contact;
private int zip;
private int preFlag;
private String fileLocation;


public String getUsername() {
	return username;
}
public void setUsername(String username) {
	this.username = username;
}
public int getApplicationId() {
	return applicationId;
}
public void setApplicationId(int applicationId) {
	this.applicationId = applicationId;
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
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public String getAddress() {
	return address;
}
public void setAddress(String address) {
	this.address = address;
}
public String getContact() {
	return contact;
}
public void setContact(String contact) {
	this.contact = contact;
}
public int getZip() {
	return zip;
}
public void setZip(int zip) {
	this.zip = zip;
}
public int getPreFlag() {
	return preFlag;
}
public void setPreFlag(int preFlag) {
	this.preFlag = preFlag;
}
public String getFileLocation() {
	return fileLocation;
}
public void setFileLocation(String fileLocation) {
	this.fileLocation = fileLocation;
}
public List<String> getEmail() {
	return email;
}
public void setEmail(List<String> email) {
	this.email = email;
}

}
