package com.dailymitra.dao.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbUtil {

	static Connection con;

	public static Connection getConnection() {
		try {
			if (con == null || con.isClosed()) {
				try {
					Class.forName("org.h2.Driver");
					con = DriverManager.getConnection("jdbc:h2:~/daily_mitra", "sa", "");
					return con;
				} catch (ClassNotFoundException | SQLException e) {
					e.printStackTrace();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}
}
