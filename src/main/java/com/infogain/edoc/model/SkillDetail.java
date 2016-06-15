package com.infogain.edoc.model;

import java.util.List;

public class SkillDetail {
private String primarySkill;
private String secondarySkill;
private List<EmployeeSkills> skills;
public String getPrimarySkill() {
	return primarySkill;
}
public void setPrimarySkill(String primarySkill) {
	this.primarySkill = primarySkill;
}
public String getSecondarySkill() {
	return secondarySkill;
}
public void setSecondarySkill(String secondarySkill) {
	this.secondarySkill = secondarySkill;
}
public List<EmployeeSkills> getSkills() {
	return skills;
}
public void setSkills(List<EmployeeSkills> skills) {
	this.skills = skills;
}


}
