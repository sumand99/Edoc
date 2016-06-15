package com.infogain.edoc.dao;

import java.util.List;

import com.infogain.edoc.dao.impl.FileDataDaoImpl;
import com.infogain.edoc.exception.FileAlreadyExistsException;
import com.infogain.edoc.model.ApproveReject;
import com.infogain.edoc.model.FileData;
import com.infogain.edoc.model.FileDelete;
import com.infogain.edoc.model.UploadFileData;

/**
 * 
 * FileDataDao.java
 * Interface class containing methods related to a file.
 * 
 * @author amant.kumar
 * @see FileDataDaoImpl 
 * 
 */
public interface FileDataDao
{
	public FileData uploadFile(UploadFileData ufd,int applicationId, String fileLocation,String rootLocation) throws FileAlreadyExistsException;
	public List<FileData> listRootFiles(int applicationId);
	public List<FileData> listFolderFiles(int applicationId,int parentCategoryId);
	public  List<ApproveReject> approveFile(List<ApproveReject> approveList, int applicationId);
	public  List<ApproveReject> rejectFile(List<ApproveReject> rejectList, int applicationId);
	public int getNoOfUploadedFiles(int applicationId);
	public List<FileDelete> deleteFiles(List<FileDelete> fileDeleteList, int applicationId);
	public int getRootId(int applicationId);
	public String getPhoto(int applicationId);
}
