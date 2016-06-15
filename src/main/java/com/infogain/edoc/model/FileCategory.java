package com.infogain.edoc.model;

public class FileCategory {
private int categoryId;
private String categoryName;
private int parentCategoryId;
private int applicationId;
private int visibility;


public FileCategory(int categoryId, String categoryName, int parentCategoryId,
		int applicationId, int visibility) {
	super();
	this.categoryId = categoryId;
	this.categoryName = categoryName;
	this.parentCategoryId = parentCategoryId;
	this.applicationId = applicationId;
	this.visibility = visibility;
}



@Override
public String toString() {
	return "FileCategory [categoryId=" + categoryId + ", categoryName="
			+ categoryName + ", parentCategoryId=" + parentCategoryId
			+ ", applicationId=" + applicationId + "]";
}

public FileCategory(int categoryId, String categoryName) {
	super();
	this.categoryId = categoryId;
	this.categoryName = categoryName;
}

public int getCategoryId() {
	return categoryId;
}
public void setCategoryId(int categoryId) {
	this.categoryId = categoryId;
}
public String getCategoryName() {
	return categoryName;
}
public void setCategoryName(String categoryName) {
	this.categoryName = categoryName;
}
public int getParentCategoryId() {
	return parentCategoryId;
}
public void setParentCategoryId(int parentCategoryId) {
	this.parentCategoryId = parentCategoryId;
}
public int getApplicationId() {
	return applicationId;
}
public void setApplicationId(int applicationId) {
	this.applicationId = applicationId;
}

public int getVisibility() {
	return visibility;
}

public void setVisibility(int visibility) {
	this.visibility = visibility;
}

public FileCategory( String categoryName, 
		int applicationId) {
	super();
	
	this.categoryName = categoryName;
	
	this.applicationId = applicationId;
}



public FileCategory(int categoryId, String categoryName, int visibility) {
	super();
	this.categoryId = categoryId;
	this.categoryName = categoryName;
	this.visibility = visibility;
}



public FileCategory(String categoryName, int applicationId, int visibility) {
	super();
	this.categoryName = categoryName;
	this.applicationId = applicationId;
	this.visibility = visibility;
}








}
