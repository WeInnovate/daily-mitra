package com.dailymitra.dao.ddl;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.Statement;
import java.util.List;
import java.util.StringTokenizer;

import com.dailymitra.dao.util.DbUtil;

public class TableCreator {
	public void createTable(String path) {

		DbMetaData dbmd = new DbMetaData();
		List<String> dataBaseTables = dbmd.getTables();
		List<String> applicationTables = dbmd.getApplicationTables(path);
		applicationTables.removeAll(dataBaseTables);
		System.out.println("New Tables: " + applicationTables);
		if (!applicationTables.isEmpty()) {
			try (Connection con = DbUtil.getConnection(); Statement stm = con.createStatement();) {
				FileInputStream fin = new FileInputStream(path);
				byte data[] = new byte[fin.available()];
				fin.read(data);
				fin.close();
				String str = new String(data);
				StringTokenizer st = new StringTokenizer(str, "/");
				System.out.println(">> Table creation started.");
				while (st.hasMoreTokens()) {
					String query = st.nextToken();
					if (query.trim().equals("stop")) {
						break;
					}
					for (String newTable : applicationTables) {
						String tableName = query.split(" ")[2];
						if (tableName.equalsIgnoreCase(newTable)) {
							stm.execute(query);
							System.out.println(tableName + " table is successfully created.");
						}
					}
				}
				System.out.println(">> Table creation started.");
			} catch (Exception e) {
				System.out.println(e);
			}
		} else {
			System.out.println("No new table to create.");
		}
	}
}