package com.dailymitra.service;

import com.dailymitra.dao.LoginDao;
import com.dailymitra.dao.LoginDaoImpl;

public class LoginServieImpl implements LoginService {
	
	private LoginDao loginDao;

	public LoginServieImpl() {
		this.loginDao = new LoginDaoImpl();
	}

	@Override
	public String saveLogin(String userName, String password, String status) {
		return null;
	}

	@Override
	public String getLoginStatus(String userName) {
		return null;
	}

	@Override
	public boolean isExistingUser(String userName) {
		return false;
	}

	@Override
	public String checkLogin(String userName, String password) {
		return loginDao.checkLogin(userName, password);
	}

}