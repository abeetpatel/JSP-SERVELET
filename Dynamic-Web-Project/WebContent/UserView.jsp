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
		String msg = (String) request.getAttribute("msg");
	%>

	<form action="UserCtl" method="post">

		<center>

			<h1>Add User</h1>

			<%
				if (msg != null) {
			%>
			<span><%=msg%></span>
			<%
				}
			%>

			<table>
				<tr>
					<th>First Name:</th>
					<td><input type="text" name="firstName"
						placeholder="Enter First Name"></td>
				</tr>
				<tr>
					<th>Last Name:</th>
					<td><input type="text" name="lastName"
						placeholder="Enter Last Name"></td>
				</tr>
				<tr>
					<th>LoginId:</th>
					<td><input type="email" name="loginId"
						placeholder="Enter Your LoginId"></td>
				</tr>
				<tr>
					<th>Password:</th>
					<td><input type="password" name="password"
						placeholder="Enter Your Password"></td>
				</tr>
				<tr>
					<th>DOB:</th>
					<td><input type="date" name="dob" placeholder="Enter Your dob"></td>
				</tr>
				<tr>
					<th>Address:</th>
					<td><input type="text" name="address"
						placeholder="Enter Your Address"></td>
				</tr>

				<tr>
					<th></th>
					<td><input type="submit" name="operation" value="save"></td>
				</tr>

			</table>

		</center>

	</form>

	<%@ include file="Footer.jsp"%>
</body>
</html>