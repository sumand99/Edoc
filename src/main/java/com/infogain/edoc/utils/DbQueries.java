package com.infogain.edoc.utils;

import java.io.IOException;
import java.util.Properties;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;



public class DbQueries 
{	
	//EmployeeDaoQueries
	public static class EmployeeDaoQueries
	{
		public static String getEmployeeApplicationId;
		public static String getEmployee;
		public static String makeEmployee;
		public static String makeEmployee_update1;
		public static String makeEmployee_update2;
		public static String makeEmployee_insert1;
		public static String makeEmployee_insert2;
		public static String makeEmployee_insert3;
		public static String makeEmployee_insert4;
		public static String rejectEmployee;
		public static String rejectEmployee_update1;
		public static String rejectEmployee_update2;
		public static String getEmployeeId;
		public static String saveEmployeePersonalDetail_update;
		public static String saveEmployeePersonalDetail_insert;
		public static String saveEmployeeEducationBackground;
		public static String saveEmployeeTrainingDetail;
		public static String saveEmployeeEmploymentDetail;
		public static String saveCurrentEmploymentDetail;
		public static String saveEmployeeSalaryDetail_update;
		public static String saveEmployeeSalaryDetail_insert;
		public static String savemployeeFamilyDetail;
		public static String savemployeeReference;
		public static String saveEmployeeOtherDetail;
		public static String getSkills;
		public static String saveEmployeeSkills_delete;
		public static String saveEmployeeSkills_update;
		public static String saveEmployeeSkills_insert;
		public static String ifAreadyExists;
		public static String deleteTable;
		public static String getEmployeePersonalDetail;
		public static String getEmployeeEducationalBackgroundList;
		public static String getEmployeeTrainingAttendedList;
		public static String getEmployeeEmploymentDetailList;
		public static String getCurrentEmploymentDetail;
		public static String getEmployeeSalaryDetail;
		public static String getEmployeeFamilyDetailList;
		public static String getEmployeeReferenceList;
		public static String getEmployeeOtherDetail;
		public static String getEmployeeSkillsList;
		public static String getEducationFormData;
		public static String getEducationFormData1;
		public static String getEducationFormData2;
		public static String saveBackgroundCheckDetail_insert;
		public static String saveBackgroundCheckDetail_insert1;
		public static String saveBackgroundCheckDetail_insert2;
		public static String backgroundCheckEmploymentDetail;
		public static String backgroundCheckEducationalDetail;
		public static String backgroundCheckPersonalDetail;
		public static String makeMultipleEmployee;
		public static String makeMultipleEmployee_update;
		public static String makeMultipleEmployee_insert1;
		public static String makeMultipleEmployee_insert2;
		public static String makeMultipleEmployee_insert3;
		public static String makeMultipleEmployee_insert4;
		public static String getEmployeeByApplicationId;
		public static String getEmployeeFromAspire;
		public static String WelcomeTemplateData1;
		public static String WelcomeTemplateData2;
		public static String WelcomeTemplateData3;
		public static String WelcomeTemplateData4;
		public static String addRole;
		public static String addRole_insert;
		public static String removeRole_delete;
		
		static
		{

			Resource resource = new ClassPathResource("/properties/sqlQueries/employeeDao.properties");
			try
			{
				Properties props = PropertiesLoaderUtils.loadProperties(resource);
				
				getEmployeeApplicationId = props.getProperty("getEmployeeApplicationId");
				getEmployee = props.getProperty("getEmployee");
				
				makeEmployee = props.getProperty("makeEmployee");
				makeEmployee_update1 = props.getProperty("makeEmployee_update1");
				makeEmployee_update2 = props.getProperty("makeEmployee_update2");
				makeEmployee_insert1 = props.getProperty("makeEmployee_insert1");
				makeEmployee_insert2 = props.getProperty("makeEmployee_insert2");
				makeEmployee_insert3 = props.getProperty("makeEmployee_insert3");
				makeEmployee_insert4 = props.getProperty("makeEmployee_insert4");
				
				rejectEmployee = props.getProperty("rejectEmployee");
				rejectEmployee_update1 = props.getProperty("rejectEmployee_update1");
				rejectEmployee_update2 = props.getProperty("rejectEmployee_update2");
				
				getEmployeeId = props.getProperty("getEmployeeId");
				
				saveEmployeePersonalDetail_update = props.getProperty("saveEmployeePersonalDetail_update");
				saveEmployeePersonalDetail_insert = props.getProperty("saveEmployeePersonalDetail_insert");
				saveEmployeeEducationBackground = props.getProperty("saveEmployeeEducationBackground");
				saveEmployeeTrainingDetail = props.getProperty("saveEmployeeTrainingDetail");
				saveEmployeeEmploymentDetail = props.getProperty("saveEmployeeEmploymentDetail");
				saveCurrentEmploymentDetail = props.getProperty("saveCurrentEmploymentDetail");
				saveEmployeeSalaryDetail_update = props.getProperty("saveEmployeeSalaryDetail_update");
				saveEmployeeSalaryDetail_insert = props.getProperty("saveEmployeeSalaryDetail_insert");
				savemployeeFamilyDetail = props.getProperty("savemployeeFamilyDetail");
				savemployeeReference = props.getProperty("savemployeeReference");
				saveEmployeeOtherDetail = props.getProperty("saveEmployeeOtherDetail");
				getSkills = props.getProperty("getSkills");
				saveEmployeeSkills_delete = props.getProperty("saveEmployeeSkills_delete");
				saveEmployeeSkills_update = props.getProperty("saveEmployeeSkills_update");
				saveEmployeeSkills_insert = props.getProperty("saveEmployeeSkills_insert");
				ifAreadyExists = props.getProperty("ifAreadyExists");
				deleteTable = props.getProperty("deleteTable");
				getEmployeePersonalDetail = props.getProperty("getEmployeePersonalDetail");
				getEmployeeEducationalBackgroundList = props.getProperty("getEmployeeEducationalBackgroundList");
				getEmployeeTrainingAttendedList = props.getProperty("getEmployeeTrainingAttendedList");
				getEmployeeEmploymentDetailList = props.getProperty("getEmployeeEmploymentDetailList");
				getCurrentEmploymentDetail = props.getProperty("getCurrentEmploymentDetail");
				getEmployeeSalaryDetail = props.getProperty("getEmployeeSalaryDetail");
				getEmployeeFamilyDetailList = props.getProperty("getEmployeeFamilyDetailList");
				getEmployeeReferenceList = props.getProperty("getEmployeeReferenceList");
				getEmployeeOtherDetail = props.getProperty("getEmployeeOtherDetail");
				getEmployeeSkillsList = props.getProperty("getEmployeeSkillsList");
				getEducationFormData = props.getProperty("getEducationFormData") ;
				getEducationFormData1 = props.getProperty("getEducationFormData1");
				getEducationFormData2 = props.getProperty("getEducationFormData2");
				saveBackgroundCheckDetail_insert = props.getProperty("saveBackgroundCheckDetail_insert");
				saveBackgroundCheckDetail_insert1 = props.getProperty("saveBackgroundCheckDetail_insert1");
				saveBackgroundCheckDetail_insert2 = props.getProperty("saveBackgroundCheckDetail_insert2");
				backgroundCheckEmploymentDetail = props.getProperty("backgroundCheckEmploymentDetail");
				backgroundCheckEducationalDetail = props.getProperty("backgroundCheckEducationalDetail");
				backgroundCheckPersonalDetail = props.getProperty("backgroundCheckPersonalDetail");
				makeMultipleEmployee = props.getProperty("makeMultipleEmployee");
				makeMultipleEmployee_update = props.getProperty("makeMultipleEmployee_update");
				makeMultipleEmployee_insert1 = props.getProperty("makeMultipleEmployee_insert1");
				makeMultipleEmployee_insert2 = props.getProperty("makeMultipleEmployee_insert2");
				makeMultipleEmployee_insert3 = props.getProperty("makeMultipleEmployee_insert3");
				makeMultipleEmployee_insert4 = props.getProperty("makeMultipleEmployee_insert4");
				getEmployeeByApplicationId = props.getProperty("getEmployeeByApplicationId");
				getEmployeeFromAspire = props.getProperty("getEmployeeFromAspire");
				WelcomeTemplateData1 = props.getProperty("WelcomeTemplateData1");
				WelcomeTemplateData2 = props.getProperty("WelcomeTemplateData2");
				WelcomeTemplateData3 = props.getProperty("WelcomeTemplateData3");
				WelcomeTemplateData4 = props.getProperty("WelcomeTemplateData4");
				addRole = props.getProperty("addRole");
				addRole_insert = props.getProperty("addRole_insert");
				
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
			
		}
		
	}
	
	//FileDataDaoQueries
	public static class FileDataDaoQueries
	{
		public static String uploadFile1;
		public static String uploadFile_update1;
		public static String uploadFile_insert1;
		public static String uploadFile_insert2;
		public static String uploadFile2;
		public static String getRootId;
		public static String listRootFiles1;
		public static String listRootFiles2;
		public static String listFolderFiles;
		public static String approveFile_update;
		public static String rejectFile_update1;
		public static String rejectFile_update2;
		public static String getNoOfUploadedFiles;
		public static String deleteFiles_delete;
		public static String ifFileExists;
		public static String getPhoto;
		
		static
		{
			Resource resource = new ClassPathResource("/properties/sqlQueries/fileDataDao.properties");
			try
			{
				Properties props = PropertiesLoaderUtils.loadProperties(resource);
				
				uploadFile1 = props.getProperty("uploadFile1");
				uploadFile_update1 = props.getProperty("uploadFile_update1");
				uploadFile_insert1 = props.getProperty("uploadFile_insert1");
				uploadFile_insert2 = props.getProperty("uploadFile_insert2");
				uploadFile2 = props.getProperty("uploadFile2");
				getRootId = props.getProperty("getRootId");
				listRootFiles1 = props.getProperty("listRootFiles1");
				listRootFiles2 = props.getProperty("listRootFiles2");
				listFolderFiles = props.getProperty("listFolderFiles");
				approveFile_update = props.getProperty("approveFile_update");
				rejectFile_update1 = props.getProperty("rejectFile_update1");
				rejectFile_update2 = props.getProperty("rejectFile_update2");
				getNoOfUploadedFiles = props.getProperty("getNoOfUploadedFiles");
				deleteFiles_delete = props.getProperty("deleteFiles_delete");
				ifFileExists = props.getProperty("ifFileExists");
				getPhoto = props.getProperty("getPhoto");
				
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
		}
	}
	
	//PreEmployeeDaoQueries
	public static class PreEmployeeDaoQueries
	{
		public static String getPreEmployeeApplicationId;
		public static String getPreEmployee_1;
		public static String getPreEmployeeList_1_1;
		public static String getPreEmployeeList_1_2;
		public static String getPreEmployeeList_1_3;
		public static String getPreEmployeeList_1_4;
		
		public static String getPreEmployeeList_2_1;
		public static String getPreEmployeeList_2_2;
		public static String getPreEmployeeList_2_3;
		public static String getPreEmployeeList_2_4;
		public static String getPreEmployeeList_2_5;
		public static String getPreEmployeeList_2_6;
		public static String getPreEmployeeList_2_7;
		public static String getPreEmployeeList_2_8;
		
		public static String getCount1;
		public static String getCount2;
		public static String getCount3;
		public static String getCount4;
		public static String getCount5;
		public static String getCount6;
		public static String getPreEmployee_2;
		public static String registration_insert1;
		public static String registration_insert2;
		public static String registration_insert3;
		public static String registration_insert4;
		public static String registration_insert5;
		public static String isSameUsernameOrEmail;
		public static String isSameEmail;
		public static String submitData_update;
		public static String approveBgc_update1;
		public static String approveBgc_update2;
		public static String approveRmg_update;
		public static String approveRmgAdmin_update;
		public static String rejectRmgAdmin_update;
		public static String rejectRmg_update;
		public static String isEmployeeEnabled;
		public static String enableDisableEmployee_update1;
		public static String enableDisableEmployee_update2;
		public static String enableDisableEdit_update1;
		public static String enableDisableEdit_update2;
		public static String existingEmployeeRegistration_insert1;
		public static String existingEmployeeRegistration_insert2;
		public static String existingEmployeeRegistration_insert3;
		public static String existingEmployeeRegistration_insert4;
		public static String existingEmployeeRegistration_insert5;
		
		
		static
		{
			Resource resource = new ClassPathResource("/properties/sqlQueries/preEmployeeDao.properties");
			try
			{
				Properties props = PropertiesLoaderUtils.loadProperties(resource);
				
				getPreEmployeeApplicationId = props.getProperty("getPreEmployeeApplicationId");
				getPreEmployee_1 = props.getProperty("getPreEmployee_1");
				getPreEmployeeList_1_1 = props.getProperty("getPreEmployeeList_1_1");
				getPreEmployeeList_1_2 = props.getProperty("getPreEmployeeList_1_2");
				getPreEmployeeList_1_3 = props.getProperty("getPreEmployeeList_1_3");
				getPreEmployeeList_1_4 = props.getProperty("getPreEmployeeList_1_4");
				
				getPreEmployeeList_2_1 = props.getProperty("getPreEmployeeList_2_1");
				getPreEmployeeList_2_2 = props.getProperty("getPreEmployeeList_2_2");
				getPreEmployeeList_2_3 = props.getProperty("getPreEmployeeList_2_3");
				getPreEmployeeList_2_4 = props.getProperty("getPreEmployeeList_2_4");
				getPreEmployeeList_2_5 = props.getProperty("getPreEmployeeList_2_5");
				getPreEmployeeList_2_6 = props.getProperty("getPreEmployeeList_2_6");
				getPreEmployeeList_2_7 = props.getProperty("getPreEmployeeList_2_7");
				getPreEmployeeList_2_8 = props.getProperty("getPreEmployeeList_2_8");
				
				getCount1 = props.getProperty("getCount1");
				getCount2 = props.getProperty("getCount2");
				getCount3 = props.getProperty("getCount3");
				getCount4 = props.getProperty("getCount4");
				getCount5 = props.getProperty("getCount5");
				getCount6 = props.getProperty("getCount6");
				getPreEmployee_2 = props.getProperty("getPreEmployee_2");
				registration_insert1 = props.getProperty("registration_insert1");
				registration_insert2 = props.getProperty("registration_insert2");
				registration_insert3 = props.getProperty("registration_insert3");
				registration_insert4 = props.getProperty("registration_insert4");
				registration_insert5 = props.getProperty("registration_insert5");
				isSameUsernameOrEmail = props.getProperty("isSameUsernameOrEmail");
				isSameEmail = props.getProperty("isSameEmail");
				submitData_update = props.getProperty("submitData_update");
				approveBgc_update1 = props.getProperty("approveBgc_update1");
				approveBgc_update2 = props.getProperty("approveBgc_update2");
				approveRmg_update = props.getProperty("approveRmg_update");
				approveRmgAdmin_update = props.getProperty("approveRmgAdmin_update");
				rejectRmgAdmin_update = props.getProperty("rejectRmgAdmin_update");
				rejectRmg_update = props.getProperty("rejectRmg_update");
				isEmployeeEnabled = props.getProperty("isEmployeeEnabled");
				enableDisableEmployee_update1 = props.getProperty("enableDisableEmployee_update1");
				enableDisableEmployee_update2 = props.getProperty("enableDisableEmployee_update2");	
				enableDisableEdit_update1 = props.getProperty("enableDisableEdit_update1");	
				enableDisableEdit_update2 = props.getProperty("enableDisableEdit_update2");	
				existingEmployeeRegistration_insert1 = props.getProperty("existingEmployeeRegistration_insert1");	
				existingEmployeeRegistration_insert2 = props.getProperty("existingEmployeeRegistration_insert2");	
				existingEmployeeRegistration_insert3 = props.getProperty("existingEmployeeRegistration_insert3");	
				existingEmployeeRegistration_insert4 = props.getProperty("existingEmployeeRegistration_insert4");	
				existingEmployeeRegistration_insert5 = props.getProperty("existingEmployeeRegistration_insert5");	
				
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
		}
	}
	
	//FileCategoryDaoQueries
	public static class FileCategoryDaoQueries
	{
		public static String rootFolderList1;
		public static String rootFolderList2;
		public static String folderList1;
		public static String folderList2;
		public static String getRootId;
		public static String createNewFolder_insert;
		public static String createNewFolder;
		public static String deleteFolders;
		public static String getDefaultFolders;
		public static String getCategoryName;
		public static String approveFolder_update;
		public static String rejectFolder_update;
		public static String changeFolderVisibility;
		
		static
		{
			Resource resource = new ClassPathResource("/properties/sqlQueries/fileCategoryDao.properties");
			try
			{
				Properties props = PropertiesLoaderUtils.loadProperties(resource);
				
				rootFolderList1 = props.getProperty("rootFolderList1");
				rootFolderList2 = props.getProperty("rootFolderList2");
				folderList1 = props.getProperty("folderList1");
				folderList2 = props.getProperty("folderList2");
				getRootId = props.getProperty("getRootId");
				createNewFolder_insert = props.getProperty("createNewFolder_insert");
				createNewFolder = props.getProperty("createNewFolder");
				deleteFolders = props.getProperty("deleteFolders");
				getDefaultFolders = props.getProperty("getDefaultFolders");
				getCategoryName = props.getProperty("getCategoryName");
				approveFolder_update = props.getProperty("approveFolder_update");
				rejectFolder_update = props.getProperty("rejectFolder_update");
				changeFolderVisibility = props.getProperty("changeFolderVisibility");
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
		}
	}
	
	//UserDaoQueries
	public static class UserDaoQueries
	{
		public static String insertUser_insert1;
		public static String insertUser_insert2;
		public static String deleteUser;
		public static String searchUser;
		public static String getRoles;
		public static String getUserList;
		public static String getRMGList;
		public static String getRMGAdminList;
		public static String getAdminList;
		
		static
		{
			Resource resource = new ClassPathResource("/properties/sqlQueries/userDao.properties");
			try
			{
				Properties props = PropertiesLoaderUtils.loadProperties(resource);
				
				insertUser_insert1 = props.getProperty("insertUser_insert1");
				insertUser_insert2 = props.getProperty("insertUser_insert2");
				deleteUser = props.getProperty("deleteUser");
				searchUser = props.getProperty("searchUser");
				getRoles = props.getProperty("getRoles");
				getUserList = props.getProperty("getUserList");
				getRMGList = props.getProperty("getRMGList");
				getRMGAdminList = props.getProperty("getRMGAdminList");
				getAdminList = props.getProperty("getAdminList");
				
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
		}
	}
}
