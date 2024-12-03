<%@page import="com.rays.bean.UserBean"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
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
		List list = (List) request.getAttribute("list");
	%>

	<form action="UserListCtl.do" method="post">

		<h1 align="center">User List</h1>

		<table>
			<tr>
				<th>First Name:</th>
				<td><input type="text" name="firstName"
					placeholder="Enter First Name"></td>

				<th>Last Name:</th>
				<td><input type="text" name="lastName"
					placeholder="Enter Last Name"></td>

				<th>Login Id:</th>
				<td><input type="text" name="loginId"
					placeholder="Enter Your Login Id"></td>

				<th>DOB:</th>
				<td><input type="date" name="dob" placeholder="Enter Your DOB"></td>

				<th>Address:</th>
				<td><input type="text" name="address"
					placeholder="Enter Your Address"></td>

				<td><input type="submit" name="operation" value="search"></td>
			</tr>
		</table>

		<table width="100%" border="1px">
			<tr style="background-color: skyblue">
				<th>Delete</th>
				<th>First Name</th>
				<th>Last Name</th>
				<th>LoginId</th>
				<th>DOB</th>
				<th>Address</th>
				<th>Edit</th>
			<tr>

				<%
					Iterator it = list.iterator();
					while (it.hasNext()) {
						UserBean bean = (UserBean) it.next();
				%>
			
			<tr align="center">
				<td><input type="checkbox" name="ids" value="<%=bean.getId()%>"></td>
				<td><%=bean.getFirstName()%></td>
				<td><%=bean.getLastName()%></td>
				<td><%=bean.getLoginId()%></td>
				<td><%=bean.getDob()%></td>
				<td><%=bean.getAddress()%></td>
				<td><a href="UserCtl.do?id=<%=bean.getId()%>">Edit</a></td>
			</tr>
			<%
				}
			%>
		</table>
		<table>
			<tr>
				<td><input type="submit" name="operation" value="delete">
				</td>
			</tr>
		</table>

	</form>
	<%@ include file="Footer.jsp"%>
</body>
</html>