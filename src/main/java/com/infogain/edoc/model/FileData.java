package com.infogain.edoc.model;

import java.util.Date;

public class FileData {
private int fileId;
private int fileDescriptionId;
private String fileLocation;
private Date lastModified;
private Date creationDate;
private int parentCategoryId;
private int applicationId;
private int flagId;
private String remark;
private String fileName;


public FileData() {
	super();
	// TODO Auto-generated constructor stub
}



@Override
public String toString() {
	return "FileData [fileId=" + fileId + ", fileDescriptionId="
			+ fileDescriptionId + ", fileLocation=" + fileLocation
			+ ", lastModified=" + lastModified + ", creationDate="
			+ creationDate + ", parentCategoryId=" + parentCategoryId
			+ ", applicationId=" + applicationId + ", flagId=" + flagId
			+ ", remark=" + remark + ", fileName=" + fileName + "]";
}



public FileData( int applicationId,int fileDescriptionId, String fileLocation,
		Date lastModified, Date creationDate, int parentCategoryId,
		int flagId, String remark, String fileName) {
	super();
	
	this.fileDescriptionId = fileDescriptionId;
	this.fileLocation = fileLocation;
	this.lastModified = lastModified;
	this.creationDate = creationDate;
	this.parentCategoryId = parentCategoryId;
	this.applicationId = applicationId;
	this.flagId = flagId;
	this.remark = remark;
	this.fileName = fileName;
}



public FileData(int fileDescriptionId,String fileName, int fileId, int flagId,  Date lastModified,
		int parentCategoryId,String fileLocation,String remark) {
	super();
	this.fileId = fileId;
	this.fileDescriptionId = fileDescriptionId;
	this.lastModified = lastModified;
	this.parentCategoryId = parentCategoryId;
	this.flagId = flagId;
	this.fileName = fileName;
	this.fileLocation = fileLocation;
	this.remark = remark;
}



public String getFileLocation() {
	return fileLocation;
}



public void setFileLocation(String fileLocation) {
	this.fileLocation = fileLocation;
}



public String getFileName() {
	return fileName;
}
public void setFileName(String fileName) {
	this.fileName = fileName;
}
public int getFileId() {
	return fileId;
}
public void setFileId(int fileId) {
	this.fileId = fileId;
}
public int getFileDescriptionId() {
	return fileDescriptionId;
}
public void setFileDescriptionId(int fileDescriptionId) {
	this.fileDescriptionId = fileDescriptionId;
}
public Date getLastModified() {
	return lastModified;
}
public void setLastModified(Date lastModified) {
	this.lastModified = lastModified;
}
public Date getCreationDate() {
	return creationDate;
}
public void setCreationDate(Date creationDate) {
	this.creationDate = creationDate;
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
public int getFlagId() {
	return flagId;
}
public void setFlagId(int flagId) {
	this.flagId = flagId;
}
public String getRemark() {
	return remark;
}
public void setRemark(String remark) {
	this.remark = remark;
}



public FileData(int fileId,int applicationId, int fileDescriptionId, String fileLocation,
		Date lastModified, Date creationDate, int parentCategoryId,
		 int flagId, String remark, String fileName) {
	super();
	this.fileId = fileId;
	this.fileDescriptionId = fileDescriptionId;
	this.fileLocation = fileLocation;
	this.lastModified = lastModified;
	this.creationDate = creationDate;
	this.parentCategoryId = parentCategoryId;
	this.applicationId = applicationId;
	this.flagId = flagId;
	this.remark = remark;
	this.fileName = fileName;
}

}
