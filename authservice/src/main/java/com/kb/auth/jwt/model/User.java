package com.kb.auth.jwt.model;

import java.io.Serializable;

public class User implements Serializable{

	private static final long serialVersionUID = -8036219797322639507L;

	private String userName;
	private String password="";
	private Long userId;
	private Long roleId=1L;
	
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getRoleId() {
		return roleId;
	}
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}