package com.basicemail.servelet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.basicemail.entity.User;
import com.basicemail.service.UserServices;

/**
 * Servlet implementation class Signup
 */
@WebServlet("/Signup")
public class Signup extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Signup() {
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("secondName");
		String email = request.getParameter("inputEmail3");
		String pass = request.getParameter("inputPassword3");
		System.out.println(firstName + " " + lastName + " " + email + " " + pass);
		User reg = new User();
		reg.setFirstname(firstName);
		reg.setLastname(lastName);
		reg.setPassword(pass);
		reg.setEmail(email);
		UserServices service = new UserServices();
		service.addUser(reg);
		getServletConfig().getServletContext().getRequestDispatcher(
		        "/index.jsp").forward(request,response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
