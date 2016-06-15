package com.infogain.edoc.dao.impl;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import com.infogain.edoc.dao.HrBgcDao;

public class HrBgcDaoImpl implements HrBgcDao
{
	private DataSource dataSource;
	private JdbcTemplate templateObj;
	
	public void setDataSource(DataSource dataSource)
	{
		this.dataSource = dataSource;
		templateObj = new JdbcTemplate(dataSource);
	}

}
