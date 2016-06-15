package com.infogain.edoc.model;

public class PreEmployee
{
private String username;
private int applicationId;
private String email;
private String firstName;
private String lastName;
private String password;
private String address;
private String contact;
private int zip;
private int preFlag;
private String fileLocation;
private int editFlag;
private int rmgDoneFlag;
private int rmgAdminDoneFlag;
private int enableFlag;
private int totalEditFlag;

public PreEmployee(String username, int applicationId, String firstName, String lastName, String email,
		String contact, String address, String fileLocation, int preFlag, int editFlag, int enableFlag, int rmgDoneFlag, int rmgAdminDoneFlag, int totalEditFlag) {
	super();
	this.username = username;
	this.applicationId = applicationId;
	this.email = email;
	this.firstName = firstName;
	this.lastName = lastName;
	this.address = address;
	this.contact = contact;
	this.preFlag = preFlag;
	this.fileLocation = fileLocation;
	this.editFlag = editFlag;
	this.rmgDoneFlag = rmgDoneFlag;
	this.rmgAdminDoneFlag = rmgAdminDoneFlag;
	this.enableFlag = enableFlag;
	this.totalEditFlag = totalEditFlag;
}


public PreEmployee(String username,  String password, String firstName, String lastName, String email,
		String contact, String address, int zip, String fileLocation, int preFlag, int editFlag, int enableFlag, int rmgDoneFlag, int rmgAdminDoneFlag) {
	super();
	this.username = username;
	
	this.password = password;
	this.email = email;
	this.firstName = firstName;
	this.lastName = lastName;
	this.address = address;
	this.zip = zip;
	this.contact = contact;
	this.preFlag = preFlag;
	this.fileLocation = fileLocation;
	this.editFlag = editFlag;
	this.enableFlag = enableFlag;
	this.rmgDoneFlag = rmgDoneFlag;
	this.rmgAdminDoneFlag = rmgAdminDoneFlag;
}





public PreEmployee(int applicationId, String email, String firstName,
		String lastName) {
	super();
	this.applicationId = applicationId;
	this.email = email;
	this.firstName = firstName;
	this.lastName = lastName;
}




@Override
public String toString() {
	return "PreEmployee [username=" + username + ", applicationId="
			+ applicationId + ", email=" + email + ", firstName=" + firstName
			+ ", lastName=" + lastName + ", password=" + password
			+ ", address=" + address + ", contact=" + contact + ", zip=" + zip
			+ ", preFlag=" + preFlag + ", fileLocation=" + fileLocation + "]";
}

public PreEmployee(int applicationId) {
	super();
	this.applicationId = applicationId;
}
public String getUsername() {
	return username;
}

public int getEnableFlag() {
	return enableFlag;
}


public void setEnableFlag(int enableFlag) {
	this.enableFlag = enableFlag;
}

public int getEditFlag() {
	return editFlag;
}

public void setEditFlag(int editFlag) {
	this.editFlag = editFlag;
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
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
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


public int getRmgDoneFlag() {
	return rmgDoneFlag;
}


public void setRmgDoneFlag(int rmgDoneFlag) {
	this.rmgDoneFlag = rmgDoneFlag;
}


public int getRmgAdminDoneFlag() {
	return rmgAdminDoneFlag;
}


public void setRmgAdminDoneFlag(int rmgAdminDoneFlag) {
	this.rmgAdminDoneFlag = rmgAdminDoneFlag;
}


public int getTotalEditFlag() {
	return totalEditFlag;
}


public void setTotalEditFlag(int totalEditFlag) {
	this.totalEditFlag = totalEditFlag;
}

}
