package com.dailymitra.dao;

public interface LoginDao {

	String saveLogin(String userName, String password, String status);
	
	String getLoginStatus(String userName);
	
	boolean isExistingUser(String userName);
	
	String checkLogin(String userName, String password);
	
}
