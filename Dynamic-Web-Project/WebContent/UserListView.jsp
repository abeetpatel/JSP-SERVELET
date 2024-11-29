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

	<form action="UserListCtl" method="post">

		<h1 align="center">User List</h1>

		<table width="100%" border="1px">
			<tr style="background-color: skyblue">
				<th>First Name</th>
				<th>Last Name</th>
				<th>LoginId</th>
				<th>DOB</th>
				<th>Address</th>
			<tr>

				<%
					Iterator it = list.iterator();
					while (it.hasNext()) {
						UserBean bean = (UserBean) it.next();
				%>
			
			<tr align="center">
				<td><%=bean.getFirstName()%></td>
				<td><%=bean.getLastName()%></td>
				<td><%=bean.getLoginId()%></td>
				<td><%=bean.getDob()%></td>
				<td><%=bean.getAddress()%></td>
			</tr>
			<%
				}
			%>
		</table>

	</form>
	<%@ include file="Footer.jsp"%>
</body>
</html>