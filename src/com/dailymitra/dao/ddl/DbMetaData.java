package com.dailymitra.dao.ddl;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import com.dailymitra.dao.util.DbUtil;

public class DbMetaData {
	public List<String> getTables() {
		List<String> tables = new ArrayList<String>();
		try (Connection con = DbUtil.getConnection();) {
			DatabaseMetaData dbmd = con.getMetaData();
			ResultSet rs = dbmd.getTables(null, null, "%", null);
			while (rs.next()) {
				tables.add(rs.getString(3));
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("Database Tables: " + tables);
		return tables;
	}

	public List<String> getApplicationTables(String path) {
		List<String> tables = new ArrayList<String>();
		try {
			FileInputStream fin = new FileInputStream(path);
			byte data[] = new byte[fin.available()];
			fin.read(data);
			fin.close();
			String str = new String(data);
			StringTokenizer st = new StringTokenizer(str, "/");
			while (st.hasMoreTokens()) {
				String query = st.nextToken();
				if (query.trim().equals("stop")) {
					break;
				}
				tables.add(query.split(" ")[2]);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Application Tables: " + tables);
		return tables;
	}
}