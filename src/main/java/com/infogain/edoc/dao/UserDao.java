package com.infogain.edoc.dao;

import java.util.List;

import com.infogain.edoc.dao.impl.UserDaoImpl;
import com.infogain.edoc.model.User;
/**
 * 
 * PreEmployeeDao.java
 * Interface class containing methods related to a user of this application.
 * 
 * @author amant.kumar
 * @see UserDaoImpl
 * 
 */
public interface UserDao {
	public boolean insertUser(User user);
	public boolean deleteUser(User user);
	//public int generateNewUserId();
	public User searchUser(String username);
	public List<String> getRoles(String username);
	public List<String> getUserList();
	public List<String> getRMGList();
	public List<String> getRMGAdminList();
	public List<String> getAdminList();
}
