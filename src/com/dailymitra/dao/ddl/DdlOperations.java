package com.dailymitra.dao.ddl;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import com.dailymitra.dao.util.DbUtil;

public class DdlOperations {

	public boolean createTable(String createTableQuery) {
		boolean retVal = true;

		try (Connection con = DbUtil.getConnection(); Statement stmt = con.createStatement()) {
			retVal = stmt.execute(createTableQuery);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return retVal;
	}

}
