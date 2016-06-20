<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="com.basicemail.entity.ThreadMessageDto"%>
<%@page import="java.util.List"%>
<%@page import="com.basicemail.service.UserMessagesService"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="resources/css/bootstrap.min.css" rel="stylesheet">
<link href="resources/style.css" rel="stylesheet">
<title>home</title>
<script src="resources/js/bootstrap.min.js"></script>
<script src="resources/js/jquery-1.11.3.min.js"></script>

<script type="text/javascript" src="resources/js/animation.js"></script>
</head>
<body class="login_body">
	<div class="container">
		<%
			Object use = session.getAttribute("logedInUserId");
			String id = use.toString();
			int userId = Integer.parseInt(id);
			//	out.print(use);
		%>
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
				<a href="compose.jsp">
					<button type="button" class="btn btn-primary" style="width: 100%">
						Compose</button>
				</a> <br> <br>
				<div class="list-group">
					<a href="home.jsp" class="list-group-item active"> Inbox </a> <a
						href="sent.jsp" class="list-group-item">Sent</a> <a
						href="archived.jsp" class="list-group-item">Archived</a> <a
						href="trash.jsp" class="list-group-item">Trash</a>
				</div>
			</div>
			<%
				UserMessagesService s = new UserMessagesService();
				String thredID = request.getParameter("thredId");
				List<ThreadMessageDto> msgs = s.getAllMessagesOfThreadByThreadID(userId, Integer.parseInt(thredID));
				s.MarkThreadASReaded(userId, Integer.parseInt(thredID));
			%>
			<div class="col-md-9">
				<div>
					<h3 style="color: #337ab7;">
						<%=msgs.get(0).getMessage().getSubject()%></h3>
				</div>
				<%
					for (int i = 0; i < msgs.size(); i++) {
				%>

				<div class="msg">
					<div class="row" style="height: auto;">
						<div class="col-md-9">
							<h5>
								<strong>From: <%=msgs.get(i).getSender()%></strong>
							</h5>
						</div>
						<div>
							<p style="color: red;"><%=msgs.get(i).getMessage().getTimestap()%></p>
						</div>
					</div>
					<h5>
						<strong>TO: </strong><%=msgs.get(i).getReceiver()%>
					</h5>
					<br>
					<textarea style="width: 100% ;" rows="7" readonly="readonly"><%=msgs.get(i).getMessage().getBody()%></textarea>
					

				</div>
				<%
					}
				%>

				<a href="<%="compose.jsp?type=2&threadid=" + thredID%>"><button
						class="btn btn-primary">Reply</button></a> <a
					href="<%="compose.jsp?type=3&threadid=" + thredID%>"><button
						class="btn btn-primary">Forward</button></a>
			</div>
		</div>
</body>
</html>