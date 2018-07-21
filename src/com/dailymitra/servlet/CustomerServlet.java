package com.dailymitra.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dailymitra.constant.LoginStatusConstant;
import com.dailymitra.model.Customer;
import com.dailymitra.service.CustomerService;
import com.dailymitra.service.CustomerServiceImpl;
import com.dailymitra.service.LoginService;
import com.dailymitra.service.LoginServiceImpl;
import com.dailymitra.service.SendMailService;
import com.dailymitra.service.SendMailServiceImpl;
import com.dailymitra.util.IdGenerator;

@WebServlet("/customers/*")
public class CustomerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private LoginService loginService;

	private CustomerService customerService;

	private SendMailService sendMailService;

	public CustomerServlet() {
		loginService = new LoginServiceImpl();
		customerService = new CustomerServiceImpl();
		sendMailService = new SendMailServiceImpl();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String uri = request.getRequestURI();

		LoginService loginService = new LoginServiceImpl();

		if (uri.contains("save")) {
			String userName = request.getParameter("username");
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			String mobile = request.getParameter("mobile");

			boolean isExistingUser = loginService.isExistingUser(userName);
			if (isExistingUser) {
				request.setAttribute("msg", "Username already exists, please try with different username...");
				request.getRequestDispatcher("/login.jsp").forward(request, response);
			} else {
				loginService.saveLogin(userName, password, LoginStatusConstant.NOT_VERIFIED.getValue());
				Customer customer = new Customer(userName, name, email, mobile);
				customerService.saveCustomer(customer);

				String OTP = IdGenerator.getOtp();
				sendMailService.sendMail(email, "DailyMitra - OTP", OTP);
				loginService.saveOTP(userName, OTP);

				request.setAttribute("msg", "Registration Successfull. \n Please verify OTP sent in your email...");
				request.getRequestDispatcher("/verify.jsp").forward(request, response);
			}

		} else if (uri.contains("edit")) {

		} else if (uri.contains("delete")) {

		} else if (uri.contains("verify")) {
			String OTP = request.getParameter("otp");
			String userName = request.getParameter("username");
			if (loginService.verifyOTP(userName, OTP)) {
				System.out.println("Verify OTP success !");
				loginService.deleteOTPrecord(userName, OTP);
				loginService.updateLoginStatus(userName, LoginStatusConstant.VERIFIED.getValue());
				request.setAttribute("userName", userName);
				request.getRequestDispatcher("/index.jsp").forward(request, response);
			} else {
				request.setAttribute("msg", "Invalid OTP,\n Please Enter correct userName and OTP");
				request.getRequestDispatcher("/verify.jsp").forward(request, response);
			}

		} else {
			response.sendRedirect("error.jsp");
		}
	}

}
