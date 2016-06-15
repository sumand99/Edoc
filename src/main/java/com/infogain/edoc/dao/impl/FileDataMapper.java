package com.infogain.edoc.dao.impl;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.infogain.edoc.model.FileData;

public class FileDataMapper implements RowMapper<FileData>
{
	public FileDataMapper()
	{
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public FileData mapRow(ResultSet rs, int row) throws SQLException
	{
		return new FileData(rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getInt(4),rs.getDate(5),rs.getInt(6),rs.getString(7),rs.getString(8));
	}

}

