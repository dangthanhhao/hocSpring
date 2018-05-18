package com.dangthanhhao.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
@Entity
public class Account implements Serializable {
	@Id
	private String usersName;
	private String password;
	private int permission;
	
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "account", cascade = CascadeType.ALL)
	private User user;
	
	public Account(String usersName, String password, int permission) {
		super();
		this.usersName = usersName;
		this.password = password;
		this.permission = permission;
	}
	public String getUsersName() {
		return usersName;
	}
	public void setUsersName(String usersName) {
		this.usersName = usersName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getPermission() {
		return permission;
	}
	public void setPermission(int permission) {
		this.permission = permission;
	}
	public Account() {
		super();
	}
	
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	@Override
	public String toString() {
		return "Account [usersName=" + usersName + ", password=" + password + ", permission=" + permission + "]";
	}
	
}
