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
		UserBean bean = (UserBean) request.getAttribute("bean");
	%>

	<form action="UserCtl.do" method="post">

		<center>

			<%
				if (bean != null) {
			%>
			<h1>Update User</h1>
			<%
				} else {
			%>
			<h1>Add User</h1>
			<%
				}
			%>
			<%
				if (msg != null) {
			%>
			<span><%=msg%></span>
			<%
				}
			%>

			<td><input type="hidden" name="id"
				value="<%=bean != null ? bean.getId() : ""%>"></td>

			<table>
				<tr>
					<th>First Name:</th>
					<td><input type="text" name="firstName"
						value="<%=bean != null ? bean.getFirstName() : ""%>"
						placeholder="Enter First Name"></td>
				</tr>
				<tr>
					<th>Last Name:</th>
					<td><input type="text" name="lastName"
						value="<%=bean != null ? bean.getLastName() : ""%>"
						placeholder="Enter Last Name"></td>
				</tr>
				<tr>
					<th>LoginId:</th>
					<td><input type="email" name="loginId"
						value="<%=bean != null ? bean.getLoginId() : ""%>"
						placeholder="Enter Your LoginId"></td>
				</tr>
				<tr>
					<th>Password:</th>
					<td><input type="password" name="password"
						value="<%=bean != null ? bean.getPassword() : ""%>"
						placeholder="Enter Your Password"></td>
				</tr>
				<tr>
					<th>DOB:</th>
					<td><input type="date" name="dob"
						value="<%=bean != null ? bean.getDob() : ""%>"
						placeholder="Enter Your dob"></td>
				</tr>
				<tr>
					<th>Address:</th>
					<td><input type="text" name="address"
						value="<%=bean != null ? bean.getAddress() : ""%>"
						placeholder="Enter Your Address"></td>
				</tr>

				<tr>
					<th></th>
					<td><input type="submit" name="operation"
						value=<%=bean != null ? "update" : "save"%>></td>
				</tr>

			</table>

		</center>

	</form>

	<%@ include file="Footer.jsp"%>
</body>
</html>