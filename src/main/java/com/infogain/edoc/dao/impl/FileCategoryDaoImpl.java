package com.infogain.edoc.dao.impl;

import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

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
import com.infogain.edoc.model.ChangeVisibility;
import com.infogain.edoc.model.FileCategory;
import com.infogain.edoc.model.FolderApproveReject;
import com.infogain.edoc.model.FolderDelete;
import com.infogain.edoc.model.NewFolder;
import com.infogain.edoc.model.User;
import com.infogain.edoc.utils.DbQueries.FileCategoryDaoQueries;

/**
*This class handles all the operations 
*applicable to a folder
*
*@author Akshay.Verma
*/

@Configurable
@Transactional
public class FileCategoryDaoImpl implements FileCategoryDao 
{
	private DataSource dataSource;
	private JdbcTemplate templateObj;
	private NamedParameterJdbcTemplate namedTemplateObj;

	/**
	 * This method sets the dataSource from the jdbcBean.xml,
	 * also providing the jdbcTemplate object for jdbc operations
	 * 
	 * @param dataSource
	 */
	public void setDataSource(DataSource dataSource)
	{
		this.dataSource = dataSource;
		templateObj = new JdbcTemplate(dataSource);
		namedTemplateObj = new NamedParameterJdbcTemplate(dataSource);
	}
	
	/**
	 * This method is used for listing all folders within the Root 
	 * 
	 * @param applicationId
	 * @param session This is the HttpSession object
	 * 
	 * @return List This is the list of FileCategory object containing respective data
	 */
	@Override
	public List<FileCategory> rootFolderList(int applicationId, HttpSession session)
	{
		User user = (User) session.getAttribute("user");
		List roles = user.getRoles();
		String role = "";
		if (roles.isEmpty()) {
			System.out.println("Role is empty");
		} else {
			role = (String) roles.get(0);
		}
		
		int rootId = getRootId(applicationId);
		
		if(rootId == -1)
		{
			return null;
		}
		else if(role.equalsIgnoreCase("ROLE_USER"))
		{
			//String sql = "select * from file_category where parent_category_id = ? and application_id = ? and visibility = 1";
			String sql = FileCategoryDaoQueries.rootFolderList1;
			
			
			List<FileCategory> folderList = templateObj.query(sql, new FileCategoryMapper(), rootId,applicationId);
			return folderList;
		}
		else 
		{
			//String sql = "select * from file_category where parent_category_id = ? and application_id = ?";
			String sql = FileCategoryDaoQueries.rootFolderList2;
			
			
			List<FileCategory> folderList = templateObj.query(sql, new FileCategoryMapper(), rootId,applicationId);
			return folderList;
		}
	}
	
	/**
	 * This method is used for listing all folders within a folder
	 * 
	 * @param applicationId
	 * @param parentCategoryId 
	 * @param session This is the HttpSession object 
	 * @return List This is the list of FileCategory object containing respective data
	 */
	@Override
	public List<FileCategory> folderList(int applicationId, int parentCategoryId, HttpSession session)
	{	
		User user = (User) session.getAttribute("user");
		List roles = user.getRoles();
		String role = "";
		if (roles.isEmpty())
		{
			System.out.println("Role is empty");
		}
		else
		{
			role = (String) roles.get(0);
		}
		if(role.equalsIgnoreCase("ROLE_USER"))
		{
			//String sql = "select * from file_category where parent_category_id = ? and application_id = ? and visibility = 1";
			String sql = FileCategoryDaoQueries.folderList1;
			
			List<FileCategory> folderList = templateObj.query(sql, new FileCategoryMapper(),parentCategoryId, applicationId);
			return folderList;
		}
		else
		{
			//String sql = "select * from file_category where parent_category_id = ? and application_id = ?";
			String sql = FileCategoryDaoQueries.folderList2;
			
			List<FileCategory> folderList = templateObj.query(sql, new FileCategoryMapper(),parentCategoryId, applicationId);
			return folderList;
		}
	}
	
	/**
	 * This method is used for getting the root id of the user
	 * 
	 * @param applicationId
	 * @return int This is the rootId for the user
	 */
	@Override
	public int getRootId(int applicationId)
	{
		//String sql = "select category_id from file_category where parent_category_id is null and application_id = ?";
		String sql = FileCategoryDaoQueries.getRootId;
		
		Integer categoryId = templateObj.queryForObject(sql, Integer.class, applicationId);
		return (null == categoryId ? -1 : categoryId);
	}
	
	/**
	 * This method is used for Creating a new folder
	 * 
	 * @param newFolder This is the object containing the data for creating new folder
	 * @param applicationId
	 * @param fileLocation holds the default file location
	 * @param rootLocation holds the default root location
	 * @return FileCategory This is the object containing the respective data
	 */
	@Override
	public FileCategory createNewFolder(NewFolder newFolder, int applicationId,String fileLocation,String rootLocation)
	{
		FileCategory fileCategory;
		SqlParameterSource fileParameters;
		KeyHolder keyHolder = new GeneratedKeyHolder();
		
		//String sql = "insert into file_category(category_name, parent_category_id, application_id, visibility) values(:categoryName,:parentCategoryId,:applicationId,:visibility)";
		String sql = FileCategoryDaoQueries.createNewFolder_insert;
		
		String location = "";
		int rootId = getRootId(applicationId);
		
		if(rootId == newFolder.getParentCategoryId())
		{
			location = fileLocation+ "/" + applicationId + "/" + newFolder.getParentCategoryId() + "/" + newFolder.getCategoryName();
		}
		else
		{
			location = fileLocation+ "/" + applicationId + "/" + getRootId(applicationId)+ "/" + newFolder.getParentCategoryId() + "/" + newFolder.getCategoryName();
		}
		
		fileCategory = new FileCategory(0, newFolder.getCategoryName(), newFolder.getParentCategoryId(), applicationId, newFolder.getVisibility());
		fileParameters = new BeanPropertySqlParameterSource(fileCategory);
		
		int rows = namedTemplateObj.update(sql, fileParameters, keyHolder);
		
		if(rows >= 1)
		{
			int categoryId = keyHolder.getKey().intValue();
			//String sql2 = "select category_id,category_name,visibility from file_category where category_id = ?";
			String sql2 = FileCategoryDaoQueries.createNewFolder;
			
			List<FileCategory> folderList =  templateObj.query(sql2, new RowMapper<FileCategory>() {

				@Override
				public FileCategory mapRow(ResultSet rs, int rowNum)throws SQLException {
					
					return new FileCategory(rs.getInt(1), rs.getString(2), rs.getInt(3));
				}
			}, categoryId);
			
			return (folderList.isEmpty() ? null : folderList.get(0));
		}
		else
		{
			return null;
		}
	}
	
	/**
	 * This method is used for creating a directory
	 * 
	 * @param directoryName 
	 * @param rootLocation holds the folder creation location
	 * @return boolean if created true else false
	 */
	public boolean createDirectory(String directoryName,String rootLocation)
	{
		File file = new File(rootLocation+directoryName);
		
		if (!file.exists()) 
		{
			if (file.mkdirs()) 
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		return false;
	}
	
	/**
	 * This method is used for Deletion of a folder
	 * 
	 * @param folderDeleteList This is a list of FolderDelete object
	 * @param applicationId
	 * @return List This is a list of FolderDelete object
	 */
	@Override
	public List<FolderDelete> deleteFolders(List<FolderDelete> folderDeleteList, int applicationId)
	{
		List<FolderDelete> folderDeletedList = new ArrayList<FolderDelete>();
		String sql="";
		int rows = 0;

		for(FolderDelete folderDelete : folderDeleteList)
		{
			//sql = "delete from file_category where application_id = ? and category_id = ?";
			sql = FileCategoryDaoQueries.deleteFolders;
			
			rows = templateObj.update(sql, applicationId, folderDelete.getCategoryId());
			folderDeletedList.add(folderDelete);
		}
		return folderDeletedList;
	}
	
	/**
	 * This method is used for Getting default folders
	 * 
	 * @return List This is a list of default folder names
	 */
	@Override
	public List<String> getDefaultFolders()
	{
		//String sql = "select format from default_files_and_folders where document_type_id = 2";
		String sql = FileCategoryDaoQueries.getDefaultFolders;
		
		List<String> folderList = templateObj.query(sql, new RowMapper<String>() {

			@Override
			public String mapRow(ResultSet rs, int rowNum) throws SQLException 
			{
				return rs.getString(1);
			}
		});
		
		return (folderList.isEmpty() ? null : folderList);
	}
	
	/**
	 * This method is used for Getting Category Name
	 * @param categoryId
	 * @return String This is the name of the category
	 */
	@Override
	public String getCategoryName(int categoryId)
	{
		//String sql = "select category_name from file_category where category_id = ?";
		String sql = FileCategoryDaoQueries.getCategoryName;
		
		String categoryName = templateObj.queryForObject(sql, String.class, categoryId);
		return categoryName;	
	}

	/**
	 * This method is used for approving the files inside a folder
	 * @param folder This is a FolderApproveReject object 
	 * @param applicationId 
	 * @return int 
	 */
	@Override
	public int approveFolder(FolderApproveReject folder, int applicationId) 
	{
		//String sql = "update file_data set flag_id = 3 where parent_category_id = ? and flag_id != 0 and application_id = ?";
		String sql = FileCategoryDaoQueries.approveFolder_update;
		
		int rows = templateObj.update(sql, folder.getCategoryId(), applicationId);
		return rows;
	}

	/**
	 * This method is used for rejecting the files inside a folder
	 * @param folder This is a FolderApproveReject object 
	 * @param applicationId 
	 * @return int 
	 */
	@Override
	public int rejectFolder(FolderApproveReject folder, int applicationId) 
	{
		//String sql = "update file_data set flag_id = 1 where parent_category_id = ? and flag_id != 0 and application_id = ?";
		String sql = FileCategoryDaoQueries.rejectFolder_update;
		
		int rows = templateObj.update(sql, folder.getCategoryId(), applicationId);
		return rows;
	}

	/**
	 * This method is used for changing the folder visibility
	 * @param folder This is a FolderApproveReject object 
	 * @param applicationId 
	 * @return nothing 
	 */
	@Override
	public void changeFolderVisibility(ChangeVisibility fileCategory,int applicationId)
	{
		//String sql = "update file_category set visibility =? where category_id = ? and application_id =?";
		String sql = FileCategoryDaoQueries.changeFolderVisibility;
		
		int rows = templateObj.update(sql,fileCategory.getVisibility(), fileCategory.getCategoryId(), applicationId);
	}
}