package com.springmvc.common;

import com.fasterxml.jackson.annotation.JsonView;
import com.springmvc.common.User.BaseView;

public class Role { 
	
	private String username;
	
	private String roleId;
	
	private String roleDescription;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@JsonView(BaseView.class)
	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	@JsonView(BaseView.class)
	public String getRoleDescription() {
		return roleDescription;
	}

	public void setRoleDescription(String roleDescription) {
		this.roleDescription = roleDescription;
	}
}
