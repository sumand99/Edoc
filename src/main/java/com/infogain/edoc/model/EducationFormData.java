package com.infogain.edoc.model;

import java.util.ArrayList;
import java.util.List;

public class EducationFormData {
List<String> collegeList = new ArrayList<String>();
List<String> degreeList = new ArrayList<String>();
List<String> universityList = new ArrayList<String>();
public List<String> getCollegeList() {
	return collegeList;
}
public void setCollegeList(List<String> collegeList) {
	this.collegeList = collegeList;
}
public List<String> getDegreeList() {
	return degreeList;
}
public void setDegreeList(List<String> degreeList) {
	this.degreeList = degreeList;
}
public List<String> getUniversityList() {
	return universityList;
}
public void setUniversityList(List<String> universityList) {
	this.universityList = universityList;
}

}
