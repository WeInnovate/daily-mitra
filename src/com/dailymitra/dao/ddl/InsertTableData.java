package com.dailymitra.dao.ddl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.tomcat.jni.File;

import com.dailymitra.dao.util.DbUtil;

public class InsertTableData {

	public void insertDataInTable(String path) {
		
		DbMetaData dbmd = new DbMetaData();
		
		FileInputStream fin;
		try {
			fin = new FileInputStream(path);
		
		byte data[] = new byte[fin.available()];
		fin.read(data);
		fin.close();
		String str = new String(data);
		StringTokenizer st = new StringTokenizer(str,"$");
		int count = 0;
		while(st.hasMoreTokens()) {
			String query = st.nextToken();
			if (query.trim().equals("stop")) {
				break;
			}
			
			try(Connection con = DbUtil.getConnection(); Statement stmt = con.createStatement()){
				stmt.execute(query);
			count++;
			System.out.println(count+" rows inserted");
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
