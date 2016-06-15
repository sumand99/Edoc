package com.infogain.edoc.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.infogain.edoc.dao.FileDataDao;
import com.infogain.edoc.dao.PreEmployeeDao;
import com.infogain.edoc.exception.FileAlreadyExistsException;
import com.infogain.edoc.exception.FileNotSupportedException;
import com.infogain.edoc.model.ApproveReject;
import com.infogain.edoc.model.FileDelete;
import com.infogain.edoc.model.PreEmployee;
import com.infogain.edoc.model.UploadFileData;
import com.infogain.edoc.service.FileService;

import fr.opensagres.xdocreport.converter.XDocConverterException;

/**
 * This class handles url requests related to a File.
 * 
 * @author amant.kumar
 *
 */
@Controller
public class FileController
{
	@Autowired
	private FileDataDao fileDataDao;

	@Autowired
	private ServletContext servletContext;

	@Autowired
	private PreEmployeeDao preEmpDao;
	
	@Autowired
	private FileService fileService;
	
	private static final Logger logger = LoggerFactory.getLogger(FileController.class);
	
	/**
	 * This method handles the request for the file upload.
	 * @param request
	 * @param session
	 * @param model
	 * @return
	 * @throws XDocConverterException
	 * @throws FileNotSupportedException
	 * @throws IOException
	 * @throws FileAlreadyExistsException 
	 * @throws MaxUploadSizeExceededException 
	 */
	@RequestMapping(value = {"/uploadFile"}, method = RequestMethod.POST)
	public @ResponseBody HashMap<String, Object> uploadFile(MultipartHttpServletRequest request,HttpSession session,ModelMap model) throws XDocConverterException, FileNotSupportedException, IOException, MaxUploadSizeExceededException, FileAlreadyExistsException
	{
		MultipartFile mpf = null;
		Iterator<String> itr = request.getFileNames();
		if(itr.hasNext()){
			mpf = request.getFile(itr.next());
		}
		String fileName = mpf.getOriginalFilename().replaceAll("\\s", "");
		int fileId = Integer.parseInt(request.getParameter("fileId"));
		int fileDescriptionId = Integer.parseInt(request.getParameter("fileDescriptionId"));
		int parentCategoryId =Integer.parseInt(request.getParameter("parentCategoryId"));
		UploadFileData ufd = new UploadFileData(fileId, parentCategoryId, fileName, fileDescriptionId, null);
		HashMap<String, Object> uploadData = fileService.uploadFileToServer(mpf,session, ufd);
		return uploadData;

	}
	
	/**
	 * This method handles the request for the file upload from an employee having role other than ROLE_USER.
	 * @param request
	 * @param session
	 * @param model
	 * @return
	 * @throws XDocConverterException
	 * @throws FileNotSupportedException
	 * @throws IOException
	 * @throws FileAlreadyExistsException 
	 * @throws MaxUploadSizeExceededException 
	 */
	@RequestMapping(value = {"/landingpage/uploadFile"}, method = RequestMethod.POST)
	public @ResponseBody HashMap<String, Object> uploadFileForAdmin(MultipartHttpServletRequest request,HttpSession session,ModelMap model) throws XDocConverterException, FileNotSupportedException, IOException, MaxUploadSizeExceededException, FileAlreadyExistsException
	{
		MultipartFile mpf = null;
		Iterator<String> itr = request.getFileNames();
		if(itr.hasNext()){
			mpf = request.getFile(itr.next());
		}
		String fileName = mpf.getOriginalFilename().replaceAll("\\s", "");
		int fileId = Integer.parseInt(request.getParameter("fileId"));
		int fileDescriptionId = Integer.parseInt(request.getParameter("fileDescriptionId"));
		int parentCategoryId =Integer.parseInt(request.getParameter("parentCategoryId"));
		System.out.println("file_id--- "+fileId +" fileDescriptionId--- "+ fileDescriptionId +" parentCategoryId--- "+ parentCategoryId);
		UploadFileData ufd = new UploadFileData(fileId, parentCategoryId, fileName, fileDescriptionId, null);
		HashMap<String, Object> uploadData = fileService.uploadFileToServer(mpf,session, ufd);
		return uploadData;

	}
	
	/**
	 * This method handles the request for file approval
	 * @param approveList
	 * @param session
	 * @return
	 */
	@RequestMapping(value = {"/approveFile"}, method = RequestMethod.POST)
	public @ResponseBody HashMap<String, Object> approveFile(@RequestBody List<ApproveReject> approveList,HttpSession session)
	{
		int applicationId = (Integer) session.getAttribute("selectedApplicationId");
		HashMap<String, Object> approvedList = new HashMap<String, Object>();
		approvedList.put("approvedList", fileDataDao.approveFile(approveList,applicationId));
		return approvedList;
	}
	
	/**
	 * This method handles the request for file approval
	 * @param approveList
	 * @param session
	 * @return
	 */
	@RequestMapping(value = {"/landingpage/approveFile"}, method = RequestMethod.POST)
	public @ResponseBody HashMap<String, Object> approveFileForAdmin(@RequestBody List<ApproveReject> approveList,HttpSession session)
	{
		int applicationId = (Integer) session.getAttribute("selectedApplicationId");
		HashMap<String, Object> approvedList = new HashMap<String, Object>();
		approvedList.put("approvedList", fileDataDao.approveFile(approveList,applicationId));
		return approvedList;
	}

	/**
	 * This method handles the request for file rejection.
	 * @param rejectList
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/rejectFile", method = RequestMethod.POST)
	public @ResponseBody HashMap<String, Object> rejectFile(@RequestBody List<ApproveReject> rejectList,HttpSession session)
	{
		int applicationId = (Integer) session.getAttribute("selectedApplicationId");
		HashMap<String, Object> rejectedList = new HashMap<String, Object>();
		rejectedList.put("rejectedList", fileDataDao.rejectFile(rejectList, applicationId));
		return rejectedList;
	}
	
	/**
	 * This method handles the request for file rejection.
	 * @param rejectList
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/landingpage/rejectFile", method = RequestMethod.POST)
	public @ResponseBody HashMap<String, Object> rejectFileForAdmin(@RequestBody List<ApproveReject> rejectList,HttpSession session)
	{
		int applicationId = (Integer) session.getAttribute("selectedApplicationId");
		HashMap<String, Object> rejectedList = new HashMap<String, Object>();
		rejectedList.put("rejectedList", fileDataDao.rejectFile(rejectList, applicationId));
		return rejectedList;
	}
	
	/**
	 * This method handles the request for file delete
	 * @param fileDeleteList
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/deleteFile", method = RequestMethod.POST)
	public @ResponseBody HashMap<String, Object> deleteFile(@RequestBody List<FileDelete> fileDeleteList,HttpSession session)
	{
		int applicationId = (Integer) session.getAttribute("selectedApplicationId");
		HashMap<String, Object> removedList = new HashMap<String, Object>();
		removedList.put("removedList", fileDataDao.deleteFiles(fileDeleteList, applicationId));
		return removedList;
	}
	
	/**
	 * This method handles the request for file delete
	 * @param fileDeleteList
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/landingpage/deleteFile", method = RequestMethod.POST)
	public @ResponseBody HashMap<String, Object> deleteFileForAdmin(@RequestBody List<FileDelete> fileDeleteList,HttpSession session)
	{
		int applicationId = (Integer) session.getAttribute("selectedApplicationId");
		HashMap<String, Object> removedList = new HashMap<String, Object>();
		removedList.put("removedList", fileDataDao.deleteFiles(fileDeleteList, applicationId));
		for(FileDelete deleteFile:fileDeleteList){
			if(deleteFile.getFileLocation().indexOf("docx") != -1){
		File file1 = new File(servletContext.getRealPath("/") + deleteFile.getFileLocation()+".pdf");
		file1.delete();
		File file2 = new File(servletContext.getRealPath("/") + deleteFile.getFileLocation()+".docx");
		file2.delete();
			}
			else{
				File file = new File(servletContext.getRealPath("/") + deleteFile.getFileLocation());
				file.delete();
			}
		}
		return removedList;
	}
	/**
	 * This methods handles the request to download zip.
	 * @param applicationId
	 * @param response
	 * @param session
	 * @throws IOException
	 */
	@RequestMapping(value="/downloadZip/{applicationId}",method = RequestMethod.GET)
    public void doDownload(@PathVariable("applicationId") int applicationId,HttpServletResponse response, HttpSession session) throws IOException 
	{
		PreEmployee preEmp =  preEmpDao.getPreEmployee(applicationId);
		String folderLocation = servletContext.getRealPath("/") + preEmp.getFileLocation() + "/" + applicationId;
       fileService.downloadZip(preEmp, folderLocation, response);
 
    }
	
}
