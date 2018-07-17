package com.dailymitra.service;

public interface LoginService {

	String saveLogin(String userName, String password, String status);
	
	String getLoginStatus(String userName);
	
	boolean isExistingUser(String userName);
	
	String checkLogin(String userName, String password);
	
	String saveOTP (String userName,String OTP );
	
	boolean verifyOTP (String userName,String OTP );
	
	String deleteOTPrecord(String userName, String OTP);
}
