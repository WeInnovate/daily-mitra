package com.dailymitra.dao.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Locale;
import java.util.ResourceBundle;

import org.h2.bnf.context.DbContextRule;

public class DbUtil {

	private static Connection con;

	private static ResourceBundle dbResource;

	private static String url;

	private static String userName;

	private static String password;

	private DbUtil() {
	}

	static {
		dbResource = ResourceBundle.getBundle("com//dailymitra//dao//util//db", Locale.US);
		url = dbResource.getString("url");
		userName = dbResource.getString("username");
		password = dbResource.getString("password");
	}

	public static Connection getConnection() {
		try {
			if (con == null || con.isClosed()) {
				Class.forName("org.h2.Driver");
				return DriverManager.getConnection(url, userName, password);
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return con;
	}
}
