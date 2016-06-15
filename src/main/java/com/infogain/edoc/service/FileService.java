package com.infogain.edoc.service;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;

import com.infogain.edoc.exception.FileAlreadyExistsException;
import com.infogain.edoc.exception.FileNotSupportedException;
import com.infogain.edoc.model.PreEmployee;
import com.infogain.edoc.model.UploadFileData;

import fr.opensagres.xdocreport.converter.XDocConverterException;

@Service
public interface FileService {
	public HashMap<String, Object> uploadFileToServer(MultipartFile file, HttpSession session,UploadFileData ufd)throws MaxUploadSizeExceededException,FileNotSupportedException, IOException, XDocConverterException, FileAlreadyExistsException;
 	public void downloadZip(PreEmployee preEmp, String folderLocation,HttpServletResponse response) throws IOException;
}
