package com.basicemail.servelet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.basicemail.entity.User;
import com.basicemail.service.UserServices;

/**
 * Servlet implementation class LoginServelet
 */
@WebServlet("/LoginServelet")
public class LoginServelet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LoginServelet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UserServices userSer = new UserServices();
		response.getWriter().append("Served at: ").append(request.getContextPath());
		String email = request.getParameter("inputEmail3");
		String pass = request.getParameter("inputPassword3");

		System.out.println(email + " " + pass);
		User currentUser = userSer.getUser(email, pass);
		if (currentUser == null) {
			getServletConfig().getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
		} else {
			HttpSession session = request.getSession();
			session.setAttribute("logedInUserId", currentUser.getId());
			session.setAttribute("logedInUseremail", currentUser.getEmail());
			session.setAttribute("logedInUserfirstname", currentUser.getFirstname());
			session.setAttribute("logedInUserlastname", currentUser.getLastname());
			session.setAttribute("logedInUserPassword", currentUser.getPassword());
			session.setAttribute("logedInUserPhoto", currentUser.getPhotourl());
			session.setAttribute("logedInUserPhone", currentUser.getPhone());
			try {
				String rem = request.getParameter("rem");
				if (rem.equals("on")) {
					System.out.println("===========");
					System.out.println(currentUser.getId());
					Cookie cookie1 = new Cookie("user", Integer.toString(currentUser.getId()));
					cookie1.setMaxAge(60 * 1000);
					response.addCookie(cookie1);
				}
			} catch (Exception e) {
			}
			response.sendRedirect("home.jsp");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
