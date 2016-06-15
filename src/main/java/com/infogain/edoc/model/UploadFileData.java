package com.infogain.edoc.model;

public class UploadFileData 
{
	private int fileId;
	private int parent_category_id;
	private String fileName;
	private int fileDescriptionId;
	private String remark;

	public UploadFileData(int fileId, 
			int parent_category_id, String fileName,
			int fileDescriptionId, String remark) {
		super();
		this.fileId = fileId;
		
		this.parent_category_id = parent_category_id;
	
		this.fileName = fileName;
		this.fileDescriptionId = fileDescriptionId;
		this.remark = remark;
	}

	public int getFileDescriptionId() {
		return fileDescriptionId;
	}

	public void setFileDescriptionId(int fileDescriptionId) {
		this.fileDescriptionId = fileDescriptionId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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
	
	public int getParent_category_id() {
		return parent_category_id;
	}
	public void setParent_category_id(int parent_category_id) {
		this.parent_category_id = parent_category_id;
	}

	
	
	
}
