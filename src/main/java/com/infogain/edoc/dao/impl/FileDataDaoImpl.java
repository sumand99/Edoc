package com.infogain.edoc.dao.impl;

import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.transaction.annotation.Transactional;

import com.infogain.edoc.dao.FileCategoryDao;
import com.infogain.edoc.dao.FileDataDao;
import com.infogain.edoc.exception.FileAlreadyExistsException;
import com.infogain.edoc.model.ApproveReject;
import com.infogain.edoc.model.FileData;
import com.infogain.edoc.model.FileDelete;
import com.infogain.edoc.model.UploadFileData;
import com.infogain.edoc.utils.DbQueries.FileDataDaoQueries;

/**
 * The FileDataDaoImpl implements all the data access operation related to the
 * files uploaded by the employee or pre-employee. This class implements
 * com.infogain.edoc.dao.FileDataDao interface.
 * 
 * @see FileDataDao
 * @author amant.kumar
 * 
 */

@Configurable
@Transactional
public class FileDataDaoImpl implements FileDataDao {
	@Autowired
	private FileCategoryDao fileCategoryDao;
	@Autowired
	private ServletContext servletContext;
	
	private DataSource dataSource;
	private JdbcTemplate templateObj;
	private NamedParameterJdbcTemplate namedTemplateObj;

	/**
	 * This method is used to set the data source to the JdbcTemplate
	 * initialisation.
	 * 
	 * @param dataSource
	 *            This contains database information and credentials required to
	 *            connect to the database.
	 * @return nothing
	 */
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		templateObj = new JdbcTemplate(dataSource);
		namedTemplateObj = new NamedParameterJdbcTemplate(dataSource);
	}

	/**
	 * This method is used to store file information of an uploaded file int the
	 * database.
	 * 
	 * @param ufd
	 *            contains information of uploaded file.
	 * @param fileLocation
	 *            provides the physical address of the file.
	 * @param rootLocation
	 *            provides the home directory of a pre-employee.
	 * @return fileData This returns the file information back to the requesting
	 *         node.
	 * @throws FileAlreadyExistsException 
	 * @see UploadFileData
	 */
	@Override
	public FileData uploadFile(UploadFileData ufd, int applicationId,
			String fileLocation, String rootLocation) throws FileAlreadyExistsException {
		FileData fileData;
		int descriptionId = ufd.getFileDescriptionId();
		String sql;
		int rows;
		int fileId = ufd.getFileId();
		String location = "";
		String folderLocation = "";

		if(ifFileExists(ufd.getFileName(), applicationId)){
			throw new FileAlreadyExistsException("file Already Exists");
		};
		
		int rootId = getRootId(applicationId);
		String categoryName = fileCategoryDao.getCategoryName(ufd
				.getParent_category_id());

		if (rootId == ufd.getParent_category_id()) {
			location = fileLocation + "/" + applicationId + "/" + "root" + "/"
					+ ufd.getFileName();
			folderLocation = fileLocation + "/" + applicationId + "/" + "root";
		} else {
			location = fileLocation + "/" + applicationId + "/" + "root" + "/"
					+ categoryName + "/" + ufd.getFileName();
			folderLocation = fileLocation + "/" + applicationId + "/" + "root"
					+ "/" + categoryName;
		}
		if (fileId > 0) {
			//sql = "select file_location from file_data where file_id = ?";
			sql = FileDataDaoQueries.uploadFile1;
			
			String file_location = templateObj.queryForObject(sql,
					String.class, fileId);
			if (file_location.indexOf("docx") != -1) {
				File file1 = new File(servletContext.getRealPath("/")
						+ file_location.split(".docx")[0] + ".pdf");
				file1.delete();
				File file2 = new File(servletContext.getRealPath("/")
						+ file_location );
				file2.delete();
			} else {
				File file = new File(servletContext.getRealPath("/")
						+ file_location);
				file.delete();
			}
			//sql = "update file_data set file_location =?,flag_id= 2, remark = ? where file_id = ? and application_id = ?";
			sql = FileDataDaoQueries.uploadFile_update1;
			
			rows = templateObj.update(sql, location, null, ufd.getFileId(),
					applicationId);
		} else {
			SqlParameterSource fileParameters;
			KeyHolder keyHolder = new GeneratedKeyHolder();
			if (descriptionId != 0) {
				//sql = "insert into file_data(application_id, file_description_id, file_location, last_modified_date, creation_date, parent_category_id, flag_id, remark, filename) values (:applicationId,:fileDescriptionId,:fileLocation,:lastModified,:creationDate,:parentCategoryId,:flagId,:remark,:fileName)";
				sql = FileDataDaoQueries.uploadFile_insert1;
				
				fileData = new FileData(applicationId, descriptionId, location,
						new Date(), new Date(), ufd.getParent_category_id(), 2,
						ufd.getRemark(), ufd.getFileName());
				fileParameters = new BeanPropertySqlParameterSource(fileData);
				rows = namedTemplateObj.update(sql, fileParameters, keyHolder);
			} else {
				//sql = "insert into file_data(application_id, file_description_id, file_location, last_modified_date, creation_date, parent_category_id, flag_id, remark, filename) values (:applicationId,null,:fileLocation,:lastModified,:creationDate,:parentCategoryId,:flagId,:remark,:fileName)";
				sql = FileDataDaoQueries.uploadFile_insert2;
				
				fileData = new FileData(applicationId, 0, location, new Date(),
						new Date(), ufd.getParent_category_id(), 2,
						ufd.getRemark(), ufd.getFileName());
				fileParameters = new BeanPropertySqlParameterSource(fileData);
				rows = namedTemplateObj.update(sql, fileParameters, keyHolder);
			}
			fileId = keyHolder.getKey().intValue();
		}
		if (rows >= 1) {
			createDirectory(folderLocation, rootLocation);
			//String sql2 = "select * from file_data where file_id = ?";
			String sql2 = FileDataDaoQueries.uploadFile2;
			
			List<FileData> file = templateObj.query(sql2,
					new RowMapper<FileData>() {
						@Override
						public FileData mapRow(ResultSet rs, int rowNum)
								throws SQLException {
							return new FileData(rs.getInt(1), rs.getInt(2), rs
									.getInt(3), rs.getString(4), rs.getDate(5),
									rs.getDate(6), rs.getInt(7), rs.getInt(8),
									rs.getString(9), rs.getString(10));
						}
					}, fileId);
			return (file.isEmpty()) ? null : file.get(0);
		} else {
			return null;
		}
	}

	private boolean ifFileExists(String fileName, int applicationId) {
		//String sql = "select count(*) from file_data where fileName = ? and application_id = ?";
		String sql = FileDataDaoQueries.ifFileExists;
		Integer count = templateObj.queryForObject(sql, Integer.class, fileName, applicationId);
		if(count > 0){
			return Boolean.TRUE;
		}
		else
		{
			return Boolean.FALSE;
		}
	}

	/**
	 * This method is used to get home directory id of a pre-employee or
	 * employee based on his/her application id.
	 * 
	 * @param applicationId
	 *            application id of a pre-employee or employee.
	 * @return int This returns category id if exists or else null.
	 * 
	 */
	@Override
	public int getRootId(int applicationId) {
		//String sql = "select category_id from file_category where parent_category_id is null and application_id = ?";
		String sql = FileDataDaoQueries.getRootId;
		
		Integer categoryId = templateObj.queryForObject(sql, Integer.class,
				applicationId);
		return (categoryId == null) ? -1 : categoryId;
	}

	/**
	 * This method is used to get files in the home directory of a pre-employee
	 * or employee based on his/her application id.
	 * 
	 * @param applicationId
	 *            application id of a pre-employee or employee.
	 * @return List It contains file information of all the files present in the
	 *         home directory.
	 * @see FileData
	 */
	@Override
	public List<FileData> listRootFiles(int applicationId) {
		int categoryId = getRootId(applicationId);
		//String sql1 = "select fi.file_description_id, fi.format as fileName,fd.file_id, fd.flag_id, fd.last_modified_date, fd.parent_category_id,fd.file_location,fd.remark  from default_files_and_folders fi left join file_data fd on fi.file_description_id = fd.file_description_id and fd.application_id = ? where fi.document_type_id=1";
		String sql1 = FileDataDaoQueries.listRootFiles1;
		
		//String sql2 = "select file_description_id, fileName,file_id, flag_id,last_modified_date,parent_category_id,file_location ,remark from file_data where application_id= ? and file_description_id is null and parent_category_id=?";
		String sql2 = FileDataDaoQueries.listRootFiles2;
		
		
		List<FileData> fileList = templateObj.query(sql1, new FileDataMapper(),
				applicationId);
		List<FileData> newfileList = templateObj.query(sql2,
				new FileDataMapper(), applicationId, categoryId);
		fileList.addAll(newfileList);
		return fileList;
	}

	/**
	 * This method is used to get files in the specified folder of a
	 * pre-employee or employee based on his/her application id.
	 * 
	 * @param parentCategoryId
	 *            The id of parent directory of the specified folder..
	 * @param applicationId
	 *            application id of a pre-employee or employee.
	 * @return fileList list contains information of all the the files inside
	 *         the specified folder.
	 * @see FileData
	 */
	@Override
	public List<FileData> listFolderFiles(int applicationId,
			int parentCategoryId) {
		//String sql2 = "select file_description_id, fileName,file_id, flag_id,last_modified_date,parent_category_id,file_location,remark from file_data where application_id= ? and file_description_id is null and parent_category_id = ?";
		String sql2 = FileDataDaoQueries.listFolderFiles;
		
		
		List<FileData> fileList = templateObj.query(sql2, new FileDataMapper(),
				applicationId, parentCategoryId);
		return fileList;
	}

	/**
	 * This method is used to approve files of a pre-employee or employee based
	 * on his/her application id.
	 * 
	 * @param approveList
	 *            list of all the files to be approved.
	 * @param applicationId
	 *            application id of a pre-employee or employee.
	 * @return updatedApprovedList list contains information of all the the
	 *         approved files.
	 * @see ApproveReject
	 */
	@Override
	public List<ApproveReject> approveFile(List<ApproveReject> approveList,
			int applicationId) {
		List<ApproveReject> updatedApprovedList = new ArrayList<ApproveReject>();
		//String sql = "update file_data set flag_id = 3 where file_id = ? and application_id = ?";
		String sql = FileDataDaoQueries.approveFile_update;
		
		
		for (ApproveReject approve : approveList) {
			int rows = templateObj.update(sql, approve.getFileId(),
					applicationId);
			ApproveReject approveReject = new ApproveReject();
			approveReject.setFileId(approve.getFileId());
			approveReject.setCurrentStatus(3);
			updatedApprovedList.add(approveReject);
		}
		return updatedApprovedList;
	}

	/**
	 * This method is used to rejects files of a pre-employee or employee based
	 * on his/her application id.
	 * 
	 * @param rejectList
	 *            list of all the files to be rejected.
	 * @param applicationId
	 *            application id of a pre-employee or employee.
	 * @return updatedRejectedList list contains information of all the the
	 *         rejected files.
	 * @see ApproveReject
	 */
	@Override
	public List<ApproveReject> rejectFile(List<ApproveReject> rejectList,
			int applicationId) {
		List<ApproveReject> updatedRejectedList = new ArrayList<ApproveReject>();
		//String sql = "update file_data set flag_id = 1, remark = ? where file_id = ? and application_id = ?";
		String sql = FileDataDaoQueries.rejectFile_update1;
		
		//String sql2 = "update pre_emp set edit_flag = 1 where application_id = ?";
		String sql2 = FileDataDaoQueries.rejectFile_update2;
		
		
		for (ApproveReject approve : rejectList) {
			int rows = templateObj.update(sql, approve.getRemark(),
					approve.getFileId(), applicationId);
			ApproveReject approveReject = new ApproveReject();
			approveReject.setRemark(approve.getRemark());
			approveReject.setFileId(approve.getFileId());
			approveReject.setCurrentStatus(1);
			updatedRejectedList.add(approveReject);
		}
		int rows2 = templateObj.update(sql2, applicationId);
		return updatedRejectedList;
	}

	/**
	 * This method is used to get total number of uploaded files by a
	 * pre-employee or employee based on his/her application id.
	 * 
	 * @param rejectList
	 *            list of all the files to be rejected.
	 * @param applicationId
	 *            application id of a pre-employee or employee.
	 * @return count total number of uploaded documents.
	 */
	@Override
	public int getNoOfUploadedFiles(int applicationId) {
		//String sql = "select count(*) from file_data where application_id= ? and flag_id != 4";
		String sql = FileDataDaoQueries.getNoOfUploadedFiles;
		
		
		int count = templateObj.queryForObject(sql, Integer.class,
				applicationId);
		return count;
	}

	/**
	 * This method is used to create a new folder in the physical location.
	 * 
	 * @param directoryName
	 *            name of the the directory.
	 * @param rootLocation
	 *            location of the home directory.
	 * @return boolean true if directory is created else false.
	 */
	public boolean createDirectory(String directoryName, String rootLocation) {
		File file = new File(rootLocation + directoryName);
		if (!file.exists()) {
			if (file.mkdirs()) {
				System.out.println("Directory is created!");
				return true;
			} else {
				System.out.println("Failed to create directory!");
				return false;
			}
		}
		return false;
	}

	/**
	 * This method is used to delete files and folders from the database and
	 * physical location.
	 * 
	 * @param fileDeleteList
	 *            list of all the files to be deleted.
	 * @param applicationId
	 *            application id of the pre-employee to employee whose files are
	 *            to be deleted.
	 * @return fileDeletedList list containing information of all the deleted
	 *         files and folders.
	 */
	@Override
	public List<FileDelete> deleteFiles(List<FileDelete> fileDeleteList,
			int applicationId) {
		List<FileDelete> fileDeletedList = new ArrayList<FileDelete>();
		FileDelete fd;
		String sql = "";
		int rows = 0;
		for (FileDelete fileDelete : fileDeleteList) {
			//sql = "delete from file_data where application_id = ? and file_id = ?";
			sql = FileDataDaoQueries.deleteFiles_delete;
			
			
			rows = templateObj.update(sql, applicationId,
					fileDelete.getFileId());
			fd = new FileDelete();
			fd.setFileId(fileDelete.getFileId());
			fd.setFileDescriptionId(fileDelete.getFileDescriptionId());
			fileDeletedList.add(fd);
		}
		for (FileDelete deleteFile : fileDeleteList) {
			if (deleteFile.getFileLocation().indexOf("docx") != -1) {
				File file1 = new File(servletContext.getRealPath("/")
						+ deleteFile.getFileLocation().split(".docx")[0] + ".pdf");
				file1.delete();
				File file2 = new File(servletContext.getRealPath("/")
						+ deleteFile.getFileLocation() );
				file2.delete();
			} else {
				File file = new File(servletContext.getRealPath("/")
						+ deleteFile.getFileLocation());
				file.delete();
			}
		}
		return fileDeletedList;
	}

	@Override
	public String getPhoto(int applicationId) {
		//String sql = "select file_location from file_data where application_id = ? and file_description_id = ?";
		String sql = FileDataDaoQueries.getPhoto;
		String fileLocation = templateObj.queryForObject(sql, String.class, applicationId, 10);
		return (fileLocation != null)?fileLocation:null;
	}

}
