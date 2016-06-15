package com.infogain.edoc.dao;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.infogain.edoc.dao.impl.FileCategoryDaoImpl;
import com.infogain.edoc.model.ChangeVisibility;
import com.infogain.edoc.model.FileCategory;
import com.infogain.edoc.model.FolderApproveReject;
import com.infogain.edoc.model.FolderDelete;
import com.infogain.edoc.model.NewFolder;

/**
 * 
 * PreEmployeeDao.java
 * Interface class containing methods related to a directory.
 * 
 * @author amant.kumar
 * @see FileCategoryDaoImpl
 * 
 */
public interface FileCategoryDao 
{
	public List<FileCategory> rootFolderList(int applicationId, HttpSession session);
	public List<FileCategory> folderList(int applicationId, int parentCategoryId, HttpSession session);
	public FileCategory createNewFolder(NewFolder newFolder, int applicationId,String fileLocation,String rootLocation);
	public int getRootId(int applicationId);
	public List<FolderDelete> deleteFolders(List<FolderDelete> folderDeleteList, int applicationId);
	public List<String> getDefaultFolders();
	public String getCategoryName(int categoryId);
	public int approveFolder(FolderApproveReject folder, int applicationId);
	public int rejectFolder(FolderApproveReject folder, int applicationId);
	public void changeFolderVisibility(ChangeVisibility fileCategory,
			int applicationId);

}
