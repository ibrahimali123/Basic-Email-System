<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="resources/css/bootstrap.min.css" rel="stylesheet">
<link href="resources/style.css" rel="stylesheet">
<title>Registration</title>
<script src="resources/js/bootstrap.min.js"></script>
<script src="resources/js/jquery-1.11.3.min.js"></script>
</head>
<body class="login_body">
	<div class="container">
		<h1 style="margin-left: 28%; margin-top: 11%;">
			<strong>Basic</strong> Email
		</h1>
		<div class="login_form">
			<form class="form-horizontal" method="post" action="Signup"
				onsubmit="return validate()">
				<br></br>
				<div class="form-group ">
					<label for="firstName"
						class="col-md-3 .col-md-offset-3 control-label">First Name</label>
					<div class="col-md-3 .col-md-offset-3">
						<input type="text" class="form-control" name="firstName"
							id="firstName" placeholder="First Name" style="width: 300px"
							required>
					</div>
				</div>
				<div class="form-group ">
					<label for="secondName"
						class="col-md-3 .col-md-offset-3 control-label">Last Name</label>
					<div class="col-md-3 .col-md-offset-3">
						<input type="text" class="form-control" name="secondName"
							id="secondName" placeholder="Last Name" style="width: 300px"
							required>
					</div>
				</div>

				<div class="form-group ">
					<label for="inputEmail3"
						class="col-md-3 .col-md-offset-3 control-label">Email</label>
					<div class="col-md-3 .col-md-offset-3">
						<input type="email" class="form-control" name="inputEmail3"
							id="inputEmail3" placeholder="Email" style="width: 300px"
							onfocus="create()" required>
					</div>
				</div>
				<div class="form-group">
					<label for="inputPassword3"
						class="col-md-3 .col-md-offset-3 control-label">Password</label>
					<div class="col-md-3 .col-md-offset-3">
						<input type="password" class="form-control" name="inputPassword3"
							id="inputPassword3" placeholder="Password" style="width: 300px"
							 required>
					</div>
				</div>
				<div class="form-group">
					<label for="inputPassword4"
						class="col-md-3 .col-md-offset-3 control-label">re
						Password</label>
					<div class="col-md-3 .col-md-offset-3">
						<input type="password" class="form-control" name="inputPassword4"
							id="inputPassword4" placeholder="Retype Password"
							style="width: 300px" required>
					</div>
				</div>
				<p id="ajaxGetUserServletResponse" style="background-color: red;"></p>

				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-10">
						<button type="submit" class="btn btn-primary">Register</button>
					</div>
				</div>
				<button type="button" class="btn btn-link">
					<a href="index.jsp">I already have a membership</a>
				</button>
				<br></br>
			</form>
		</div>
	</div>
	<script type="text/javascript">
		function create() {
			
			document.getElementById("inputEmail3").value = document
					.getElementById("firstName").value
					+ document.getElementById("secondName").value+"@BasicEmail.com";
		}

		function validate() {
			var pass = document.getElementById("inputPassword3").value;
			var repass = document.getElementById("inputPassword4").value;

			var re = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,}$/;
			var res = re.test(pass);
			var x = document.getElementById("ajaxGetUserServletResponse").innerHTML;
			if (x.length > 0)
				return false;

			if (res == false) {
				alert("your password should contain at least 6 charachter one upper case and one lower case and one number");
				return false;
			} else {
				if (pass != repass) {
					alert("the password and repassword should be the same");
					return false;
				} else {
					alert("your account added successfully ;)");
					return true;
				}

			}

		}

		$(document).ready(function() {
			$('#inputEmail3').blur(function(event) {
				var name = $('#inputEmail3').val();
				$.get('EmailAlreadyExistServlet', {
					Email : name
				}, function(responseText) {
					$('#ajaxGetUserServletResponse').text(responseText);
				});
			});
		});
	</script>
</body>
</html>