<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="com.basicemail.service.UserServices"%>
<%@page import="com.basicemail.service.UserMessagesService"%>
<%@page import="com.basicemail.entity.ThreadMessageDto"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="resources/css/bootstrap.min.css" rel="stylesheet">
<link href="resources/style.css" rel="stylesheet">
<script src="resources/js/bootstrap.min.js"></script>
<script src="resources/js/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="resources/js/animation.js"></script>

<%
	Integer userId = (Integer) session.getAttribute("logedInUserId");
	String parm = (String) request.getParameter("type");
	int type = Integer.parseInt((parm == null ? "1" : parm));
	int threadid = 0;
	String subjectString = "";
	String bodyString = "";
	String toString = "";
	String[] lis = null;
	UserMessagesService service = new UserMessagesService();
	//threadid
	if (type != 1) {

		String parmThreadid = (String) request.getParameter("threadid");
		if (parmThreadid == null) {
			out.print("<script type='text/javascript'>alert('Error will redirect to home')</script>");
			response.sendRedirect("/home.jsp");
		} else
			threadid = Integer.parseInt(parmThreadid);
		if (type == 2) {
			toString = service.getAllThreadUsersEmails(userId, Integer.parseInt(parmThreadid));
			lis = toString.split(",");
		}
		if (type == 3) {

			String tmp = "";
			List<ThreadMessageDto> list = service.getAllMessagesOfThreadByThreadID(userId, threadid);
			for (int i = 0; i < list.size(); i++) {
				ThreadMessageDto tmd = list.get(i);
				bodyString += tmp + "From : " + tmd.getSender() + "\n";
				bodyString += tmp + "Subject : " + tmd.getMessage().getSubject() + "\n";
				bodyString += tmp + "Body : " + tmd.getMessage().getBody() + "\n";
				tmp += "  ";
			}
			bodyString += "-----------------------------------------------";
		}
	}
%>
<title>
	<%
		out.print(type == 1 ? "Send" : (type == 2 ? "Reply" : "Forward") + " Message");
	%>
</title>

</head>
<body class="login_body">
	<div class="container">
		<nav class="navbar navbar-inverse">
		<div class="container-fluid">
			<!-- Brand and toggle get grouped for better mobile display -->
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
					aria-expanded="false">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#">Basic Email</a>
			</div>

			<!-- Collect the nav links, forms, and other content for toggling -->
			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
				<div class="nav navbar-nav"></div>
				<form class="navbar-form navbar-left" role="search">
					<a href="#" id="flip" class="btn btn-default">Search</a>
				</form>
				<form class="navbar-form navbar-left " role="profile"
					action="profile.jsp">
					<button type="submit" class="btn btn-default profile">
						<%
							out.print(session.getAttribute("logedInUseremail"));
						%>
					</button>
				</form>
				<form class="navbar-form navbar-left " role="logout"
					action="LogoutServelet">
					<button type="submit" class="btn btn-default log_out">logout</button>
				</form>

			</div>
			<!-- /.navbar-collapse -->
		</div>
		<!-- /.container-fluid --> </nav>
		<div id="panel" style="display: none;">
			<form action="search.jsp">
				<table>
					<tr>
						<td><label class="control-label">To:</label></td>
						<td><input type="text" name="msgto" id="msgto"></td>
						<td><label>From:</label></td>
						<td><input type="text" name="msgfrom" id="msgfrom"></td>
					</tr>

					<tr>
						<td><label>Date from:</label></td>
						<td><input type="text" name="datefrom" id="datefrom"></td>
						<td><label>Date to:</label></td>
						<td><input type="text" name="dateto" id="dateto"></td>

					</tr>
					<tr>
						<td></td>
						<td></td>
						<td><button class="btn btn-info">
								<span class="glyphicon glyphicon-search" aria-hidden="true"></span>
							</button></td>
					</tr>
				</table>
			</form>
		</div>
		<div class="row">
			<div class="col-md-3">

				<br> <br>
				<div class="list-group">
					<a href="home.jsp" class="list-group-item "> Inbox </a> <a
						href="sent.jsp" class="list-group-item">Sent</a> <a
						href="archived.jsp" class="list-group-item">Archived</a> <a
						href="trash.jsp" class="list-group-item">Trash</a>
				</div>
			</div>
			<div class="col-md-9">
				<%
					// 					Integer userId = (Integer) session.getAttribute("logedInUserId");
					// 					String parm = (String) request.getParameter("type");
					// 					int type = Integer.parseInt((parm == null ? "1" : parm));
					// 					int threadid = 0;
					// 					String subjectString = "";
					// 					String bodyString = "";
					// 					String toString = "";
					// 					UserMessagesService service = new UserMessagesService();
					// 					//threadid
					// 					if (type != 1) {

					// 						String parmThreadid = (String) request.getParameter("threadid");
					// 						if (parmThreadid == null) {
					// 							out.print("<script type='text/javascript'>alert('Error will redirect to home')</script>");
					// 							response.sendRedirect("/home.jsp");
					// 						} else
					// 							threadid = Integer.parseInt(parmThreadid);
					// 						if(type ==2){
					// 							toString = service.getAllThreadUsersEmails(userId,Integer.parseInt(parmThreadid));
					// 						}
					// 						if (type == 3) {

					// 							String tmp = "";
					// 							List<ThreadMessageDto> list = service.getAllMessagesOfThreadByThreadID(userId, threadid);
					// 							for (int i = 0; i < list.size(); i++) {
					// 								ThreadMessageDto tmd = list.get(i);
					// 								bodyString += tmp + "From : " + tmd.getSender() + "\n";
					// 								bodyString += tmp + "Subject : " + tmd.getMessage().getSubject() + "\n";
					// 								bodyString += tmp + "Body : " + tmd.getMessage().getBody() + "\n";
					// 								tmp += "  ";
					// 							}
					// 							bodyString += "-----------------------------------------------";
					// 						}
					// 					}
				%>

				<form action="MessageServlet">
					<input type="hidden" value="<%=type%>" name="type"> <input
						type="hidden" value="<%=threadid%>" name="threadid">
					<table>




						<table>
							<%
								if (type == 2) {

									for (int i = 0; i < lis.length; i++) {
										String str = lis[i];
										//out.print(str);
							%>
							<tr>
								<td style="width: 100%;">
								<input type="checkbox"
									name="recivers" value="<%=str%>"
									 checked>
									 <label><%=str %></label>
								</td>
							</tr>
							<%
								}
								} else {
							%>

							<tr>
								<td style="width: 100%;"><input type="text" name="recivers"
									value="<%=toString%>" class="form-control" placeholder="TO"
									aria-describedby="basic-addon1"></td>
							</tr>
							<%
								}
							%>







							<tr>
								<td style="width: 100%;"><input type="text" name="subject"
									value="<%=subjectString%>" class="form-control"
									placeholder="Subject" aria-describedby="basic-addon1"></td>
							</tr>
							<tr>
								<td style="width: 100%;"><textarea rows="14" cols="110"
										name="body" placeholder="Message"><%=bodyString%></textarea></td>
							</tr>
							<tr>

							</tr>
						</table>
						<button type="submit" class="btn btn-primary" style="float: left;">
							<%
								out.print(type == 1 ? "Send" : (type == 2 ? "Reply" : "Forward"));
							%>
						</button>
				</form>
			</div>
		</div>
		<script src="resources/js/bootstrap.min.js"></script>
		<script src="resources/js/jquery-1.11.3.min.js"></script>
</body>
</html>