package com.basicemail.servelet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.basicemail.entity.Message;
import com.basicemail.service.SendingMessageService;

@WebServlet("/MessageServlet")
public class MessageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public MessageServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		int userid = 0;
		try {
			userid = (int) session.getAttribute("logedInUserId");
		} catch (Exception d) {
		}

		if (userid == 0) {
			getServletConfig().getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
			return;
		}

		Integer type = Integer.parseInt((String) request.getParameter("type"));

		String subject = (String) request.getParameter("subject");
		String body = (String) request.getParameter("body");

		//System.out.println(type + " " + subject + " " + body + " " + recivers);

		SendingMessageService sms = new SendingMessageService();
		String result = "";
		if (type.intValue() == 1) { // compose
			String recivers = (String) request.getParameter("recivers");
			result = sms.composeMessage(new Message(0, userid, 0, subject, body, null), recivers);
		} else if (type.intValue() == 2) { // replay
			String[] trecivers = request.getParameterValues("recivers");
			String recivers = "";
			for (int i = 0; i < trecivers.length; i++) {
				recivers += trecivers[i];
				if (i != trecivers.length - 1)
					recivers += ",";
			}
			int threadid = Integer.parseInt((String) request.getParameter("threadid"));
			result = sms.replayMessage(new Message(0, userid, 0, subject, body, null), recivers, threadid);
		} else if (type.intValue() == 3) { // forward
			String recivers = (String) request.getParameter("recivers");
			result = sms.forwardMessage(new Message(0, userid, 0, subject, body, null), recivers);
		} else {
			result = "Wrong Paramter";
			// getServletConfig().getServletContext().getRequestDispatcher("/index.jsp").forward(request,
			// response);
		}
		response.sendRedirect("home.jsp?msgresult=" + result);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
