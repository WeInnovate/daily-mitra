package com.dailymitra.service;

import com.dailymitra.dao.LoginDao;
import com.dailymitra.dao.LoginDaoImpl;

public class LoginServiceImpl implements LoginService {
	
	private LoginDao loginDao;

	public LoginServiceImpl() {
		this.loginDao = new LoginDaoImpl();
	}

	@Override
	public String saveLogin(String userName, String password, String status) {
		return loginDao.saveLogin(userName, password, status);
	}

	@Override
	public String getLoginStatus(String userName) {
		return null;
	}

	@Override
	public boolean isExistingUser(String userName) {
		return loginDao.isExistingUser(userName);
	}

	@Override
	public String checkLogin(String userName, String password) {
		return loginDao.checkLogin(userName, password);
	}

	@Override
	public String saveOTP(String userName, String OTP) {
		return loginDao.saveOTP(userName, OTP);
	}

	@Override
	public boolean verifyOTP(String userName, String OTP) {
		return loginDao.verifyOTP(userName, OTP);
	}

	@Override
	public String deleteOTPrecord(String userName, String OTP) {
		return loginDao.deleteOTPrecord(userName,OTP);
	}

	@Override
	public int updateLoginStatus(String userName, String updatedStatus) {
		return loginDao.updateLoginStatus(userName, updatedStatus);
	}

	@Override
	public String recoverPassword(String userName) {
		return loginDao.recoverPassword(userName);
	}

	@Override
	public String findUserName(String email) {
	
		return loginDao.findUserName(email);
	}

}
