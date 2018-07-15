package com.dailymitra.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dailymitra.constant.LoginStatusConstant;
import com.dailymitra.service.LoginService;
import com.dailymitra.service.LoginServieImpl;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private LoginService loginService;

	public LoginServlet() {
		this.loginService = new LoginServieImpl();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String userName = request.getParameter("username");
		String password = request.getParameter("password");

		String status = loginService.checkLogin(userName, password);

		if (status.equals(LoginStatusConstant.NON_EXISTING.getValue())) {
			request.setAttribute("msg", "No details. \n Please signup...");
			RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
			rd.forward(request, response);
		} else if (status.equals(LoginStatusConstant.EXISTING.getValue())) {
			request.setAttribute("msg", "Wrong details. \n Please try again...");
			RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
			rd.forward(request, response);
		}
		else {
			request.setAttribute("userName", userName);
			RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
			rd.forward(request, response);
		}
	}

}
