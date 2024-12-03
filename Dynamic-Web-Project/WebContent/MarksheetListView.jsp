<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="com.rays.bean.MarksheetBean"%>
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

	<form action="MarksheetListCtl.do" method="post">

		<h1 align="center">Marksheet List</h1>

		<table>
			<tr>
				<th>Name :</th>
				<td><input type="text" name="name"
					placeholder="Enter Student's Name"></td>

				<th>Roll No. :</th>
				<td><input type="text" name="roll_no"
					placeholder="Enter Roll no."></td>


				<td><input type="submit" name="operation" value="search"></td>
			</tr>

		</table>

		<table width="100%" border="1px">
			<tr style="background-color: yellow">
				<th>Delete</th>
				<th>Name</th>
				<th>Roll NO.</th>
				<th>Physics</th>
				<th>Chemistry</th>
				<th>Maths</th>
				<th>Edit</th>
			</tr>
			<%
				Iterator it = list.iterator();
				while (it.hasNext()) {
					MarksheetBean bean = (MarksheetBean) it.next();
			%>

			<tr align="center">
				<td><input type="checkbox" name="ids"
					values="<%=bean.getId()%>"></td>
				<td><%=bean.getName()%></td>
				<td><%=bean.getRoll_no()%></td>
				<td><%=bean.getPhysics()%></td>
				<td><%=bean.getChemistry()%></td>
				<td><%=bean.getMaths()%></td>
				<td><a href="MarksheetCtl.do?id=<%=bean.getId()%>">Edit</a></td>
			</tr>

			<%
				}
			%>
		</table>
		<table>
			<tr>
				<td><input type="submit" name="operation" value="delete"></td>

			</tr>

		</table>


	</form>

	<%@ include file="Footer.jsp"%>
</body>
</html>


