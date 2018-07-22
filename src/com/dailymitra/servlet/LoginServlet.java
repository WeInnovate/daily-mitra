package com.dailymitra.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dailymitra.constant.LoginStatusConstant;
import com.dailymitra.service.LoginService;
import com.dailymitra.service.LoginServiceImpl;
import com.dailymitra.service.SendMailService;
import com.dailymitra.service.SendMailServiceImpl;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private LoginService loginService;
	private SendMailService sendMailService;

	public LoginServlet() {
		this.loginService = new LoginServiceImpl();
		this.sendMailService = new SendMailServiceImpl();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/forgotpwd.jsp");
		rd.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String userName = request.getParameter("username");
		String password = request.getParameter("password");

		String status = loginService.checkLogin(userName, password);

		if (status.equals(LoginStatusConstant.NON_EXISTING.getValue())) {
			request.setAttribute("msg", "No details. \n Please signup...");
			RequestDispatcher rd = request.getRequestDispatcher("/login.jsp");
			rd.forward(request, response);
		} else if (status.equals(LoginStatusConstant.EXISTING.getValue())) {
			request.setAttribute("msg", "Wrong details. \n Please try again...");
			RequestDispatcher rd = request.getRequestDispatcher("/login.jsp");
			rd.forward(request, response);
		} else if (status.equals(LoginStatusConstant.NOT_VERIFIED.getValue())) {
			request.setAttribute("msg", "Email address is not verified yet. \n Please verify...");
			RequestDispatcher rd = request.getRequestDispatcher("/verify.jsp");
			rd.forward(request, response);
		} else if (status.equals(LoginStatusConstant.VERIFIED.getValue())
				|| status.equals(LoginStatusConstant.ADMIN.getValue())) {
			HttpSession session = request.getSession(true);
			session.setAttribute("userName", userName);
			RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
			rd.forward(request, response);
		}
	}

}
