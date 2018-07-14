package com.dailymitra.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.dailymitra.dao.LoginDao;
import com.dailymitra.dao.LoginDaoImpl;
import com.dailymitra.dao.ddl.DdlOperations;

@WebListener
public class MyServletContextListener implements ServletContextListener {

	public MyServletContextListener() {
	}

	public void contextDestroyed(ServletContextEvent sce) {
	}

	public void contextInitialized(ServletContextEvent sce) {
		ServletContext context = sce.getServletContext();
		String isTableCreationRequired = context.getInitParameter("createTable");
		if (isTableCreationRequired != null && isTableCreationRequired.equalsIgnoreCase("Yes")) {
			DdlOperations ddlOperations = new DdlOperations();
			boolean retVal = ddlOperations.createTable(
					"CREATE TABLE DM_LOGIN( USERNAME VARCHAR PRIMARY KEY, PASSWORD VARCHAR, STATUS VARCHAR(50))");
			String result = null;
			result = retVal == true ? "Tables not created" : "Tables created";
			System.out.println(result);

			LoginDao loginDao = new LoginDaoImpl();
			if (!loginDao.isExistingUser("admin")) {
				loginDao.saveLogin("admin", "admin", "ADMIN");
			}
		}
	}

}
