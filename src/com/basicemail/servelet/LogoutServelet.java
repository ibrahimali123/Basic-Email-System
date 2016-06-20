package com.basicemail.servelet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LogoutServelet
 */
@WebServlet("/LogoutServelet")
public class LogoutServelet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LogoutServelet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		HttpSession session = request.getSession();
		session.invalidate();

		Cookie[] cookies = request.getCookies();

		for (int i = 0; i < cookies.length; i++) {
			Cookie cookie1 = cookies[i];
			if (cookie1.getName().equals("user")) {
				Cookie ck = new Cookie("user", "");
				ck.setMaxAge(0);
				response.addCookie(ck);
			} else {
			}
		}
		response.sendRedirect("index.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
