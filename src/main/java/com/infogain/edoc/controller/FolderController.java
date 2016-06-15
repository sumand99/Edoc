package com.infogain.edoc.controller;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.infogain.edoc.dao.FileCategoryDao;
import com.infogain.edoc.model.ChangeVisibility;
import com.infogain.edoc.model.FileCategory;
import com.infogain.edoc.model.FolderApproveReject;
import com.infogain.edoc.model.FolderDelete;
import com.infogain.edoc.model.NewFolder;
import com.infogain.edoc.model.PreEmployee;
/**
 * This method handles requests related to a folder(category).
 */
@Controller
public class FolderController {

	@Autowired
	private ServletContext servletContext;

	@Autowired
	private FileCategoryDao fileCategoryDao;

	/**
	 * This methods handles request for folder creation.
	 * @param newFolder
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/createFolder", method = RequestMethod.POST)
	public @ResponseBody FileCategory createFolder(
			@RequestBody NewFolder newFolder, HttpSession session) {
		PreEmployee preEmp = (PreEmployee) session.getAttribute("preEmp");
		int applicationId = (Integer) session
				.getAttribute("selectedApplicationId");
		
		return fileCategoryDao.createNewFolder(newFolder, applicationId,
				preEmp.getFileLocation(), servletContext.getRealPath("/"));
	}

	/**
	 * This methods handles request for folder creation.
	 * @param newFolder
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/landingpage/createFolder", method = RequestMethod.POST)
	public @ResponseBody FileCategory createFolderForAdmin(
			@RequestBody NewFolder newFolder, HttpSession session) {
		PreEmployee preEmp = (PreEmployee) session.getAttribute("preEmp");
		int applicationId = (Integer) session
				.getAttribute("selectedApplicationId");

		return fileCategoryDao.createNewFolder(newFolder, applicationId,
				preEmp.getFileLocation(), servletContext.getRealPath("/"));
	}

	/**
	 * This methods handles request for folder deletion.
	 * @param folderDeleteList
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/deleteFolder", method = RequestMethod.POST)
	public @ResponseBody HashMap<String, Object> deleteFolder(
			@RequestBody List<FolderDelete> folderDeleteList,
			HttpSession session) {
		int applicationId = (Integer) session
				.getAttribute("selectedApplicationId");

		HashMap<String, Object> deletedFolder = new HashMap<String, Object>();
		for (FolderDelete deleteFolder : folderDeleteList) {
			String categoryName = fileCategoryDao.getCategoryName(deleteFolder
					.getCategoryId());
			File folder = new File(servletContext.getRealPath("/")
					+ "resources/edoc/" + applicationId + "/root/"
					+ categoryName);
			if (folder.isDirectory()) {
				File[] list = folder.listFiles();
				if (list != null) {
					for (int i = 0; i < list.length; i++) {
						File tmpF = list[i];
						tmpF.delete();
					}
				}
			}
			folder.delete();
		}

		deletedFolder.put("deletedFolder",
				fileCategoryDao.deleteFolders(folderDeleteList, applicationId));

		return deletedFolder;
	}

	/**
	 * This methods handles request for folder deletion.
	 * @param folderDeleteList
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/landingpage/deleteFolder", method = RequestMethod.POST)
	public @ResponseBody HashMap<String, Object> deleteFolderForAdmin(
			@RequestBody List<FolderDelete> folderDeleteList,
			HttpSession session) {
		int applicationId = (Integer) session
				.getAttribute("selectedApplicationId");

		HashMap<String, Object> deletedFolder = new HashMap<String, Object>();
		for (FolderDelete deleteFolder : folderDeleteList) {
			String categoryName = fileCategoryDao.getCategoryName(deleteFolder
					.getCategoryId());
			File folder = new File(servletContext.getRealPath("/")
					+ "resources/edoc/" + applicationId + "/root/"
					+ categoryName);
			if (folder.isDirectory()) {
				File[] list = folder.listFiles();
				if (list != null) {
					for (int i = 0; i < list.length; i++) {
						File tmpF = list[i];
						tmpF.delete();
					}
				}
			}
			folder.delete();
		}
		deletedFolder.put("deletedFolder",
				fileCategoryDao.deleteFolders(folderDeleteList, applicationId));

		return deletedFolder;
	}
	/**
	 * This methods handles request for folder approval.
	 * @param folderList
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/landingpage/approveFolder", method = RequestMethod.POST)
	public @ResponseBody Boolean approveFolderForAdmin(
			@RequestBody List<FolderApproveReject> folderList, HttpSession session) {
		int success = 0;
		PreEmployee preEmp = (PreEmployee) session.getAttribute("preEmp");
		int applicationId = (Integer) session
				.getAttribute("selectedApplicationId");
		for(FolderApproveReject folder:folderList){
		success += fileCategoryDao.approveFolder(folder, applicationId);
		}
		if(success == folderList.size()){
			return true;
		}
		else 
			return false;
	}
	
	/**
	 * This methods handles request for folder rejection.
	 * @param folderList
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/landingpage/rejectFolder", method = RequestMethod.POST)
	public @ResponseBody Boolean rejectFolderForAdmin(
			@RequestBody List<FolderApproveReject> folderList, HttpSession session) {
		int success = 0;
		PreEmployee preEmp = (PreEmployee) session.getAttribute("preEmp");
		int applicationId = (Integer) session
				.getAttribute("selectedApplicationId");
		for(FolderApproveReject folder:folderList){
		success += fileCategoryDao.rejectFolder(folder, applicationId);
		}
		if(success == folderList.size()){
			return true;
		}
		else 
			return false;
	}
	
	/**
	 * This methods handles request for changing folder visibility.
	 * @param fileCategory
	 * @param session
	 */
	@RequestMapping(value = {"/landingpage/changeFolderVisibility"}, method = RequestMethod.POST)
	public @ResponseBody void changeVisibility(@RequestBody ChangeVisibility fileCategory, HttpSession session) {
		
		PreEmployee preEmp = (PreEmployee) session.getAttribute("preEmp");
		int applicationId = (Integer) session
				.getAttribute("selectedApplicationId");
		fileCategoryDao.changeFolderVisibility(fileCategory,applicationId);
	}
}
