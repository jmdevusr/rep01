package com.usuarios.demo.model;

import com.usuarios.demo.api.UserResponse;

public class UserResponseToQuery {

	private String msg;
	private UserResponse user;
	
	

	public UserResponseToQuery(String msg, UserResponse user) {
		super();
		this.msg = msg;
		this.user = user;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public UserResponse getUser() {
		return user;
	}

	public void setUser(UserResponse user) {
		this.user = user;
	}

}
