package com.dailymitra.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dailymitra.service.LoginService;
import com.dailymitra.service.LoginServiceImpl;
import com.dailymitra.service.SendMailService;
import com.dailymitra.service.SendMailServiceImpl;

@WebServlet("/forgotpwd")
public class ForgotPasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private LoginService loginService;
	private SendMailService sendMailService;

	public ForgotPasswordServlet() {
		this.loginService = new LoginServiceImpl();
		this.sendMailService = new SendMailServiceImpl();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String userName = request.getParameter("username");
		String email = request.getParameter("email");
		String password = null;
		if (loginService.isExistingUser(userName)) {
			password = loginService.recoverPassword(userName);
			System.out.println("password is :" + password);
			request.setAttribute("msg", "Password is Sent in your email...");
			sendMailService.sendMail(email, "DailyMitra - Forgot Password", password);
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		} else {
			System.out.println("No user Found");
			request.setAttribute("msg", "User Name Not Found.Please Try again..");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}

	}

}
