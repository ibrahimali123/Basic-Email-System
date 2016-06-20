package com.basicemail.servelet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.basicemail.service.UserServices;
import com.basicemail.util.ErrorUtil;

/**
 * Servlet implementation class EmailAlreadyExistServlet
 */
@WebServlet("/EmailAlreadyExistServlet")
public class EmailAlreadyExistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public EmailAlreadyExistServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UserServices user = new UserServices();
		
		String email = request.getParameter("Email");
		if(user.emailAlreadyExist(email)){
			response.setContentType("text/plain");
			response.getWriter().write(email + " already exist, please choose another");	
		}
		
		
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
