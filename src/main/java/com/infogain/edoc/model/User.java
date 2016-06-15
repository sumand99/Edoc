package com.infogain.edoc.model;

import java.util.List;

public class User
{	
	private int id;
	private String username;
	private String age;
	private String address;
	private List<String> roles;
	
	public User(String username, String age, String address) {
		super();
		this.username = username;
		this.age = age;
		this.address = address;
	}
	
	
	public User(String username) {
		super();
		this.username = username;
	}


	public User(int id, String username) {
		super();
		this.id = id;
		this.username = username;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	
	public List<String> getRoles() {
		return roles;
	}
	public void setRoles(List<String> roles) {
		this.roles = roles;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", age=" + age
				+ ", address=" + address + ", roles=" + roles + "]";
	}
	

}
