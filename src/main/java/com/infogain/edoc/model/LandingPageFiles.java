package com.infogain.edoc.model;

import java.util.Date;

public class LandingPageFiles 
{
	private int fileDescription;
	private String descriptionId;
	private int fileId;
	private String fileStatus;
	private String fileName;
	private Date lastModifiedDate;
	private int parentCategoryId;
	private int noOfFilesUploaded;
	
	public LandingPageFiles(int fileDescription, String descriptionId,
			int fileId, String fileStatus, String fileName,
			Date lastModifiedDate, int parentCategoryId, int noOfFilesUploaded) {
		super();
		this.fileDescription = fileDescription;
		this.descriptionId = descriptionId;
		this.fileId = fileId;
		this.fileStatus = fileStatus;
		this.fileName = fileName;
		this.lastModifiedDate = lastModifiedDate;
		this.parentCategoryId = parentCategoryId;
		this.noOfFilesUploaded = noOfFilesUploaded;
	}
	public int getFileDescription() {
		return fileDescription;
	}
	public void setFileDescription(int fileDescription) {
		this.fileDescription = fileDescription;
	}
	public String getDescriptionId() {
		return descriptionId;
	}
	public void setDescriptionId(String descriptionId) {
		this.descriptionId = descriptionId;
	}
	public int getFileId() {
		return fileId;
	}
	public void setFileId(int fileId) {
		this.fileId = fileId;
	}
	public String getFileStatus() {
		return fileStatus;
	}
	public void setFileStatus(String fileStatus) {
		this.fileStatus = fileStatus;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}
	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}
	public int getParentCategoryId() {
		return parentCategoryId;
	}
	public void setParentCategoryId(int parentCategoryId) {
		this.parentCategoryId = parentCategoryId;
	}
	public int getNoOfFilesUploaded() {
		return noOfFilesUploaded;
	}
	public void setNoOfFilesUploaded(int noOfFilesUploaded) {
		this.noOfFilesUploaded = noOfFilesUploaded;
	}
	
	
	
}
