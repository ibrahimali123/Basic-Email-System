package com.basicemail.util;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ErrorUtil {

	public static void errorHandler(HttpServletRequest request, HttpServletResponse response, String message)
			throws ServletException, IOException {
		request.setAttribute("message", "<h4>" + message + ". </h4>");

		request.getRequestDispatcher("index.jsp").forward(request, response);
	}
}
