package com.infogain.edoc.dao.impl;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.infogain.edoc.model.Skills;



public class SkillsMapper implements RowMapper<Skills>
{
	public SkillsMapper()
	{
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public Skills mapRow(ResultSet rs, int row) throws SQLException
	{
		return new Skills(rs.getInt(1),rs.getString(2));
	}

}