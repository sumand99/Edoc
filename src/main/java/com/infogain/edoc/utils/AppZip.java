package com.infogain.edoc.utils;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
 
public class AppZip
{
    public static List<String> fileList = new ArrayList<String>();
    private static String outputZipFile;
    private static String sourceFolder;
 
    
    /**
     * Zip it
     * @param zipFile output ZIP file location
     */
    public static void zipIt(String zipFile){
 
     byte[] buffer = new byte[4096];
 
     try{
 
    	FileOutputStream fos = new FileOutputStream(zipFile);
    	ZipOutputStream zos = new ZipOutputStream(fos);
 
    	System.out.println("Output to Zip : " + zipFile);
 
    	for(String file : fileList){
 
    		System.out.println("File Added : " + file);
    		ZipEntry ze= new ZipEntry(file);
        	zos.putNextEntry(ze);
 
        	FileInputStream in = 
                       new FileInputStream(sourceFolder + File.separator + file);
 
        	int len;
        	while ((len = in.read(buffer)) > 0) {
        		zos.write(buffer, 0, len);
        	}
 
        	in.close();
    	}
 
    	zos.closeEntry();
    	//remember close it
    	zos.close();
 
    	System.out.println("Done");
    }catch(IOException ex){
       ex.printStackTrace();   
    }
   }
 
    /**
     * Traverse a directory and get all files,
     * and add the file into fileList  
     * @param node file or directory
     */
    public static void generateFileList(File node){
 
    	//add file only
	if(node.isFile()){
		fileList.add(generateZipEntry(node.getAbsoluteFile().toString()));
	}
 
	if(node.isDirectory()){
		String[] subNote = node.list();
		for(String filename : subNote){
			generateFileList(new File(node, filename));
		}
	}
 
    }
 
    /**
     * Format the file path for zip
     * @param file file path
     * @return Formatted file path
     */
    private static String generateZipEntry(String file){
    	return file.substring(sourceFolder.length()+1, file.length());
    }

	public static String getOutputZipFile() {
		return outputZipFile;
	}

	public static void setOutputZipFile(String outputZipFile) {
		AppZip.outputZipFile = outputZipFile;
	}

	public static String getSourceFolder() {
		return sourceFolder;
	}

	public static void setSourceFolder(String sourceFolder) {
		AppZip.sourceFolder = sourceFolder;
	}

	
}