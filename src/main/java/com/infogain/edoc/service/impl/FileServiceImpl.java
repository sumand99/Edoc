package com.infogain.edoc.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;

import com.infogain.edoc.controller.FileController;
import com.infogain.edoc.dao.FileDataDao;
import com.infogain.edoc.exception.FileAlreadyExistsException;
import com.infogain.edoc.exception.FileNotSupportedException;
import com.infogain.edoc.model.FileData;
import com.infogain.edoc.model.PreEmployee;
import com.infogain.edoc.model.UploadFileData;
import com.infogain.edoc.service.FileService;
import com.infogain.edoc.utils.AppZip;
import com.infogain.edoc.utils.MainApp;

import fr.opensagres.xdocreport.converter.XDocConverterException;
@Service
public class FileServiceImpl implements FileService {
	
	@Autowired
	private FileDataDao fileDataDao;

	@Autowired
	private ServletContext servletContext;
	
	private static final Logger logger = LoggerFactory.getLogger(FileServiceImpl.class);
	
	public HashMap<String, Object> uploadFileToServer(MultipartFile file, HttpSession session,UploadFileData ufd)throws MaxUploadSizeExceededException,FileNotSupportedException, IOException, XDocConverterException, FileAlreadyExistsException
	{	
		PreEmployee preEmp = (PreEmployee)session.getAttribute("preEmp");
		int applicationId = (Integer) session.getAttribute("selectedApplicationId");
		HashMap<String, Object> uploadData = new HashMap<String, Object>();
		
		String originalName = file.getOriginalFilename();
		String ext = originalName.split("\\.")[1];
		
		//preEmp = null;
		
		logger.info(preEmp.getFileLocation());
		
		if(!ext.equalsIgnoreCase("docx")&& !ext.equalsIgnoreCase("jpeg") && !ext.equalsIgnoreCase("jpg") && !ext.equalsIgnoreCase("pdf") && !ext.equalsIgnoreCase("gif"))
		{
			System.out.println("Failed to upload name because the file was empty or not supported.");
			throw new FileNotSupportedException("Failed to upload name because the file was empty or not supported.");
		}
		
		FileData fileData = fileDataDao.uploadFile(ufd, applicationId, preEmp.getFileLocation(),servletContext.getRealPath("/"));
		System.out.println(fileData.getFileLocation()+"-------------");

		String fileLocation = servletContext.getRealPath("/") + fileData.getFileLocation();
		System.out.println(fileLocation);
		
		if (!file.isEmpty() && ext.equalsIgnoreCase("docx"))
		{

			System.out.println(file.getOriginalFilename());
			
			File file2 = new File(fileLocation);
			
			FileOutputStream fileOuputStream =
					new FileOutputStream(file2);
			fileOuputStream.write(file.getBytes());
			fileOuputStream.close();
			
			MainApp.ConvertToPdf(fileLocation, fileLocation.split(".docx")[0]+".pdf");
				

		}
		else if(!file.isEmpty() && ext.equalsIgnoreCase("pdf"))
		{
			File file2 = new File(fileLocation);
			FileOutputStream fileOuputStream =
					new FileOutputStream(file2);
			fileOuputStream.write(file.getBytes());
			fileOuputStream.close();
			
		}
		else if(!file.isEmpty() && (ext.equalsIgnoreCase("jpg") || ext.equalsIgnoreCase("jpeg") || ext.equalsIgnoreCase("gif")))
		{
			File file2 = new File(fileLocation);
			FileOutputStream fileOuputStream =
					new FileOutputStream(file2);
			fileOuputStream.write(file.getBytes());
			fileOuputStream.close();
			
		}
			
		uploadData.put("uploadData", fileData);
		return uploadData;

	}

	@Override
	public void downloadZip(PreEmployee preEmp, String folderLocation,HttpServletResponse response) throws IOException {
		//convert files to zip
	       AppZip.setSourceFolder(folderLocation);
	       AppZip.generateFileList(new File(AppZip.getSourceFolder()));
	       AppZip.setOutputZipFile(preEmp.getFirstName() + preEmp.getLastName()+".zip");
	       File file = new File(preEmp.getFirstName() + preEmp.getLastName()+".zip");
	       if(file.exists()){
	    	   file.delete();
	       }
	       AppZip.zipIt(AppZip.getOutputZipFile());
	       AppZip.fileList.clear();
	       
	       // construct the complete absolute path of the file
	       String fullPath = AppZip.getOutputZipFile();    
	       File downloadFile = new File(fullPath);
	       FileInputStream inputStream = new FileInputStream(downloadFile);
	       
	       // get MIME type of the file
	        String mimeType = servletContext.getMimeType(fullPath);
	        if (mimeType == null) {
	            // set to binary type if MIME mapping not found
	            mimeType = "application/octet-stream";
	        }
	        logger.info("MIME type: " + mimeType);
	 
	        // set content attributes for the response
	        response.setContentType(mimeType);
	        response.setContentLength((int) downloadFile.length());
	 
	        // set headers for the response
	        String headerKey = "Content-Disposition";
	        String headerValue = String.format("attachment; filename=\"%s\"",
	                downloadFile.getName());
	        response.setHeader(headerKey, headerValue);
	 
	        // get output stream of the response
	        OutputStream outStream = response.getOutputStream();
	 
	        byte[] buffer = new byte[4096];
	        int bytesRead = -1;
	 
	        // write bytes read from the input stream into the output stream
	        while ((bytesRead = inputStream.read(buffer)) != -1) {
	            outStream.write(buffer, 0, bytesRead);
	        }
	 
	        inputStream.close();
	        outStream.close();
		
	}
}
