package com.infogain.edoc.model;

public class Employee
{
	private int applicationId;
	private String empId;
	private String email;
	private String firstName;
	private String lastName;
	private String password;
	private String address;
	private String contact;
	private int zip;
	private String fileLocation;
	private String empUsername;
	
	public Employee(int applicationId, String empId, 
			String firstName, String lastName,String email, String address,
			String contact, int zip, String fileLocation, String empUsername) {
		super();
		this.applicationId = applicationId;
		this.empId = empId;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.contact = contact;
		this.zip = zip;
		this.fileLocation = fileLocation;
		this.empUsername = empUsername;
	}
	public Employee(int applicationId) {
		super();
		this.applicationId = applicationId;
	}
	
	public Employee(String firstName, String lastName, String email, String empId) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.empId = empId;
	}
	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
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

	public String getFileLocation() {
		return fileLocation;
	}
	public void setFileLocation(String fileLocation) {
		this.fileLocation = fileLocation;
	}
	public String getEmpUsername() {
		return empUsername;
	}
	public void setEmpUsername(String empUsername) {
		this.empUsername = empUsername;
	}
	
}
