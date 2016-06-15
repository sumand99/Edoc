package com.infogain.edoc.dao.impl;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.infogain.edoc.model.FileCategory;
import com.infogain.edoc.model.FileData;

public class FileCategoryMapper implements RowMapper<FileCategory>
{
	public FileCategoryMapper()
	{
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public FileCategory mapRow(ResultSet rs, int row) throws SQLException
	{
		return new FileCategory(rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getInt(4),rs.getInt(5));
	}

}

