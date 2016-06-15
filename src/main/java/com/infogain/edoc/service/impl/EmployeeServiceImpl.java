package com.infogain.edoc.service.impl;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.infogain.edoc.dao.UserDao;
import com.infogain.edoc.service.EmployeeService;

public class EmployeeServiceImpl implements EmployeeService 
{
	@Autowired
	private UserDao userDao;
	
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
}
