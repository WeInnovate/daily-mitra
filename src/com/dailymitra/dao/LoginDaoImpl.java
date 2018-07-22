package com.dailymitra.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.dailymitra.constant.LoginStatusConstant;
import com.dailymitra.dao.util.DbUtil;

public class LoginDaoImpl implements LoginDao {

	String loginInsertQuery = "INSERT INTO DM_LOGIN VALUES(?, ?, ?)";
	String otpInsertQuery = "INSERT INTO DM_OTP VALUES(?, ?)";
	private static final String UPDATE_LOGIN_STATUS_QUERY = "UPDATE DM_LOGIN SET STATUS = ? WHERE USERNAME = ?";

	@Override
	public String saveLogin(String userName, String password, String status) {
		try (Connection con = DbUtil.getConnection();
				PreparedStatement pstmt = con.prepareStatement(loginInsertQuery)) {
			pstmt.setString(1, userName);
			pstmt.setString(2, password);
			pstmt.setString(3, status);

			int i = pstmt.executeUpdate();

			if (i > 0) {
				return getLoginStatus(userName);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String getLoginStatus(String userName) {
		try (Connection con = DbUtil.getConnection(); Statement stmt = con.createStatement()) {
			ResultSet rs = stmt.executeQuery("SELECT STATUS FROM DM_LOGIN WHERE USERNAME = '" + userName + "'");
			rs.next();
			return rs.getString(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean isExistingUser(String userName) {
		try (Connection con = DbUtil.getConnection(); Statement stmt = con.createStatement()) {
			ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM DM_LOGIN WHERE USERNAME = '" + userName + "'");
			rs.next();
			int i = rs.getInt(1);
			return (i > 0) ? true : false;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public String checkLogin(String userName, String password) {
		String retVal = LoginStatusConstant.NON_EXISTING.getValue();
		if (isExistingUser(userName)) {
			retVal = LoginStatusConstant.EXISTING.getValue();
			try (Connection con = DbUtil.getConnection(); Statement stmt = con.createStatement()) {
				ResultSet rs = stmt.executeQuery("SELECT STATUS FROM DM_LOGIN WHERE USERNAME = '" + userName
						+ "' AND PASSWORD = '" + password + "'");

				if (rs.next()) {
					retVal = rs.getString(1);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return retVal;
	}

	@Override
	public String saveOTP(String userName, String OTP) {
		try (Connection con = DbUtil.getConnection(); PreparedStatement pstmt = con.prepareStatement(otpInsertQuery)) {
			pstmt.setString(1, userName);
			pstmt.setString(2, OTP);
			int i = pstmt.executeUpdate();
			if (i > 0) {
				System.out.println("inside LoginDaoImpl.saveOTP :Success");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean verifyOTP(String userName, String OTP) {
		try (Connection con = DbUtil.getConnection(); Statement stmt = con.createStatement()) {
			ResultSet rs = stmt.executeQuery(
					"SELECT OTP FROM DM_OTP WHERE USERNAME = '" + userName + "' AND OTP = '" + OTP + "' ");
			if (rs.next()) {
				int i = rs.getInt(1);
				return (i > 0) ? true : false;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public String deleteOTPrecord(String userName, String OTP) {
		try (Connection con = DbUtil.getConnection(); Statement stmt = con.createStatement()) {
			int i = stmt.executeUpdate("DELETE FROM DM_OTP WHERE userName = '" + userName + "' ");
			if (i > 0) {
				System.out.println("OTP record deleted successfully.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public int updateLoginStatus(String userName, String updatedStatus) {
		try (Connection con = DbUtil.getConnection();
				PreparedStatement pstmt = con.prepareStatement(UPDATE_LOGIN_STATUS_QUERY)) {
			pstmt.setString(1, updatedStatus);
			pstmt.setString(2, userName);
			return pstmt.executeUpdate();
		} catch (SQLException e) {
		}
		return 0;
	}

	@Override
	public String recoverPassword(String userName) {
		try (Connection con = DbUtil.getConnection(); Statement stmt = con.createStatement()) {
			ResultSet rs = stmt.executeQuery("SELECT PASSWORD FROM DM_LOGIN WHERE USERNAME = '" + userName + "' ");
			rs.next();
			return rs.getString(1);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String findUserName(String email) {
		try (Connection con = DbUtil.getConnection(); Statement stmt = con.createStatement()) {
			ResultSet rs = stmt.executeQuery("SELECT USERNAME FROM DM_CUSTOMER WHERE EMAIL = '" + email + "' ");
			rs.next();
			return rs.getString(1);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}
}
