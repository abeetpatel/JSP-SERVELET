<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<%@ include file="Header.jsp"%>

	<%
		String err = (String) request.getAttribute("err");
	%>

	<form action="LoginCtl" method="post">

		<br> <br> <br> <br> <br>

		<center>
			<h1>Login Page</h1>

			<%
				if (err != null) {
			%>
			<span><%=err%></span>
			<%
				}
			%>

			<table>

				<tr>
					<th>LoginId:</th>
					<td><input type="text" name="loginId"
						placeholder="enter your user id"></td>
				</tr>

				<tr>
					<th>Password:</th>
					<td><input type="password" name="password"
						placeholder="enter your password"></td>
				</tr>

				<tr>
					<th></th>
					<td><input type="submit" name="operation" value="SignIn"></td>
				</tr>

			</table>
	</form>
	</center>
	<%@ include file="Footer.jsp"%>

</body>
</html>