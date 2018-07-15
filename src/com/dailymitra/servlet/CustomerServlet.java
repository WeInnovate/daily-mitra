package com.dailymitra.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dailymitra.service.SendMailService;
import com.dailymitra.service.SendMailServiceImpl;

@WebServlet("/customers/*")
public class CustomerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CustomerServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String uri = request.getRequestURI();

		if (uri.contains("save")) {
			System.out.println("Save is happening");
//			fetch the data
//			check whether userName exists in login table or not
//			insert int Login, into Customer 
//			sned email with otp
			SendMailService sendMailService = new SendMailServiceImpl();
			sendMailService.sendMail(request.getParameter("email"), "DailyMitra - OTP", "1234");
//			insert into DM_OTP
//			set msg registration successful please verify yourself
//			show verify.jsp
			request.setAttribute("msg", "Registration Successfull. \n Please verify yourself...");
			request.getRequestDispatcher("/verify.jsp").forward(request, response);
		} else if (uri.contains("edit")) {

		} else if (uri.contains("delete")) {

		} else if (uri.contains("verify")) {
//			fetch the data from request
//			send the data to DAO layer to verify the OTP
//			if success delete OTP record
//			show index.jsp with userName
//			else
//			show again verify.jsp
//			say invalid OTP msg

		} else {
			response.sendRedirect("error.jsp");
		}
	}

}
