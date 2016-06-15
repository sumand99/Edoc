package com.infogain.edoc.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.infogain.edoc.model.User;

public class UserMapper implements RowMapper<User> {

	public UserMapper() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public User mapRow(ResultSet rs, int row) throws SQLException {
		return new User(rs.getString(2), rs.getString(3), rs.getString(4));
	}

}
