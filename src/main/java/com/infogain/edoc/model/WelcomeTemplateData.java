package com.infogain.edoc.model;

import java.util.List;


public class WelcomeTemplateData 
{
	private String name;
	private String primarySkill;
	private List<String> companyNames;
	private String maritalStatus;
	private String sex;	
	private String dob;
	private String examination;
	private String institute;	
	private int applicationId;
	
	public WelcomeTemplateData(int applicationId) {
		this.applicationId = applicationId;
	}
	public WelcomeTemplateData() {
		
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPrimarySkill() {
		return primarySkill;
	}
	public void setPrimarySkill(String primarySkill) {
		this.primarySkill = primarySkill;
	}
	public List<String> getCompanyNames() {
		return companyNames;
	}
	public void setCompanyNames(List<String> companyNames) {
		this.companyNames = companyNames;
	}
	public String getMaritalStatus() {
		return maritalStatus;
	}
	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public int getApplicationId() {
		return applicationId;
	}
	public void setApplicationId(int applicationId) {
		this.applicationId = applicationId;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getExamination() {
		return examination;
	}
	public void setExamination(String examination) {
		this.examination = examination;
	}
	public String getInstitute() {
		return institute;
	}
	public void setInstitute(String institute) {
		this.institute = institute;
	}
	
	
	
	
}
