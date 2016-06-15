package com.infogain.edoc.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Transactional;

import com.infogain.edoc.dao.UserDao;
import com.infogain.edoc.model.User;
import com.infogain.edoc.utils.DbQueries.UserDaoQueries;

/**
*This class handles all the operations 
*applicable to an User 
*
*@author Akshay.Verma
*/

@Configurable
@Transactional
public class UserDaoImpl implements UserDao
{
	private DataSource dataSource;
	private JdbcTemplate templateObj;

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
	}
	
	
	/**
	 * This method is used to perform the operations for
	 * inserting a user in the users and user_roles table of
	 * the database
	 * 
	 * @param user This is a User object 
	 * @return boolean
	 */
	
	@Override
	public boolean insertUser(User user)
	{
		//String sql1 = "insert into users(username) values(?)";
		String sql1 = UserDaoQueries.insertUser_insert1;
		
		//String sql2 = "insert into user_role values(?,?)";
		String sql2 = UserDaoQueries.insertUser_insert2;
		
		int rows1 = templateObj.update(sql1, user.getUsername());
		int rows2 = templateObj.update(sql2, user.getUsername(), 2);
		
		return ((rows1 >= 1 && rows2 >= 1) ? Boolean.TRUE : Boolean.FALSE);
		
	}
	
	/**
	 * This method is used to perform the operations for
	 * deleting a user
	 * the database
	 * 
	 * @param user This is a User object 
	 * @return boolean
	 */
	@Override
	public boolean deleteUser(User user)
	{	
		//String sql1 = "delete from user_role where username = ?";
		String sql1 = UserDaoQueries.deleteUser;
		
		int rows1 = templateObj.update(sql1, user.getUsername());
		return (rows1 >= 1 ? Boolean.TRUE : Boolean.FALSE);
	}

	/**
	 * This method is used to perform the operations for
	 * searching a user on the basis of the username
	 * 
	 * @param username
	 * @return User This is a object containing the user related data
	 */
	@Override
	public User searchUser(String username)
	{
		//String sql = "select * from users where username = ?";
		String sql = UserDaoQueries.searchUser;
		
		List<User> user = templateObj.query(sql, new RowMapper<User>() {

			@Override
			public User mapRow(ResultSet rs, int rowNum) throws SQLException 
			{
				return new User(rs.getInt(1), rs.getString(2));
			}
		}, username);
		return (user.isEmpty() ? null : user.get(0));
	}
	
	/**
	 * This method is used to perform the operations for
	 * retrieving a list of roles associated to a user on the basis of the username
	 * 
	 * @param username
	 * @return List This is a list of names of the roles
	 */
	@Override
	public List<String> getRoles(String username)
	{
		//String sql = "select r.role from roles r,user_role ur,users u where u.username=ur.username and ur.role_id = r.role_id and ur.username=?";
		String sql = UserDaoQueries.getRoles;
		
		List<String> roleList = templateObj.query(sql, new RowMapper<String>() {

			@Override
			public String mapRow(ResultSet rs, int rowNum) throws SQLException
			{
				return rs.getString(1);
			}
		},username);
		return roleList;
	}


	@Override
	public List<String> getUserList() {
		//String sql = "select distinct u.username from users u, emp p where u.username = p.emp_username";
		String sql = UserDaoQueries.getUserList;
		List<String> userList = templateObj.query(sql, new RowMapper<String>() {

			@Override
			public String mapRow(ResultSet rs, int rowNum) throws SQLException
			{
				return rs.getString(1);
			}
		});
		return userList;
	}


	@Override
	public List<String> getRMGList() {
		//String sql = "select username from user_role where role_id = 4";
		String sql = UserDaoQueries.getRMGList;
		List<String> rmgList =  templateObj.query(sql, new RowMapper<String>() {

			@Override
			public String mapRow(ResultSet rs, int rowNum) throws SQLException 
			{
				return rs.getString(1);
			}
		});
		return rmgList;
	}


	@Override
	public List<String> getRMGAdminList() {
		//String sql = "select username from user_role where role_id = 5";
		String sql = UserDaoQueries.getRMGAdminList;
		List<String> rmgAdminList =  templateObj.query(sql, new RowMapper<String>() {

			@Override
			public String mapRow(ResultSet rs, int rowNum) throws SQLException 
			{
				return rs.getString(1);
			}
		});
		return rmgAdminList;
	}


	@Override
	public List<String> getAdminList() {
		//String sql = "select username from user_role where role_id = 1";
		String sql = UserDaoQueries.getAdminList;
		List<String> hrList =  templateObj.query(sql, new RowMapper<String>() {

			@Override
			public String mapRow(ResultSet rs, int rowNum) throws SQLException 
			{
				return rs.getString(1);
			}
		});
		return hrList;
	}
}
