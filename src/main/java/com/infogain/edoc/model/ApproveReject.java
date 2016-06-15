package com.infogain.edoc.model;

public class ApproveReject
{
	private int fileId;
	private int applicationId;
	private int currentStatus;
	private String remark;


	public int getFileId() {
		return fileId;
	}
	public void setFileId(int fileId) {
		this.fileId = fileId;
	}
	public int getApplicationId() {
		return applicationId;
	}
	public void setApplicationId(int applicationId) {
		this.applicationId = applicationId;
	}


	public int getCurrentStatus() {
		return currentStatus;
	}


	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public void setCurrentStatus(int currentStatus) {
		this.currentStatus = currentStatus;
	}




}
