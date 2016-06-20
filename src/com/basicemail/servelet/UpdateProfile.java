package com.basicemail.servelet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.basicemail.service.UserServices;

/**
 * Servlet implementation class UpdateProfile
 */
@WebServlet("/UpdateProfile")
public class UpdateProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UpdateProfile() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		UserServices ser = new UserServices();
		HttpSession session = request.getSession();
		int id = (int) session.getAttribute("logedInUserId");
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("secondName");
		String phone = request.getParameter("inputPhone");
		System.out.println(id + " " + firstName + " " + lastName + " " + phone);
		ser.updateProfile(id, firstName, lastName, phone);
		getServletConfig().getServletContext().getRequestDispatcher("/profile.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
