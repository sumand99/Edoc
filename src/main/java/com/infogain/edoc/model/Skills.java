package com.infogain.edoc.model;

public class Skills {
private int skillId;
private String skillName;
public int getSkillId() {
	return skillId;
}
public void setSkillId(int skillId) {
	this.skillId = skillId;
}
public String getSkillName() {
	return skillName;
}
public void setSkillName(String skillName) {
	this.skillName = skillName;
}
public Skills(int skillId, String skillName) {
	super();
	this.skillId = skillId;
	this.skillName = skillName;
}


}
