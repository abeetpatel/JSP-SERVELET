<%@page import="com.rays.bean.UserBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<%
		UserBean user = (UserBean) session.getAttribute("user");
	%>

	<%
		if (user != null) {
	%>
	<h2><%="Hi, " + user.getFirstName()%></h2>
	<a href="UserCtl.do">Add User</a> |
	<a href="UserListCtl.do">User List</a> |
	<a href="MarksheetCtl.do">Add Marksheet</a> |
	<a href="MarksheetListCtl.do">MarksheetList</a> |
	<a href="ShopCtl.do">Add Product</a> |
	<a href="ShopListCtl.do">Product List</a> |
	<a href="WelcomeCtl">Welcome</a> |
	<a href="LoginCtl?operation=logout">Logout</a>
	<%
		} else {
	%>
	<h2>Hi, Guest</h2>
	<a href="UserRegistrationCtl">SignUp</a> |
	<a href="LoginCtl">Login</a> |
	<a href="WelcomeCtl">Welcome</a>
	<%
		}
	%>



	<hr>
</body>
</html>