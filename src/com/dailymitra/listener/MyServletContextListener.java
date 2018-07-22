package com.dailymitra.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.dailymitra.dao.LoginDao;
import com.dailymitra.dao.LoginDaoImpl;
import com.dailymitra.dao.ddl.InsertTableData;
import com.dailymitra.dao.ddl.TableCreator;

@WebListener
public class MyServletContextListener implements ServletContextListener {

	public MyServletContextListener() {
	}

	public void contextDestroyed(ServletContextEvent sce) {
	}

	public void contextInitialized(ServletContextEvent sce) {
		ServletContext context = sce.getServletContext();

		String createTablesFilPath = context.getRealPath("WEB-INF//sql//create-tables.sql");
		(new TableCreator()).createTable(createTablesFilPath);

		String createBaseData = context.getInitParameter("createBaseData");
		if (createBaseData != null && createBaseData.equalsIgnoreCase("Yes")) {
			String insertTableDateFilePath = context.getRealPath("WEB-INF//sql//create-base-data.sql");
			new InsertTableData().insertDataInTable(insertTableDateFilePath);
		}

	}

}
