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
		String msg = (String) request.getAttribute("msg");
		MarksheetBean bean = (MarksheetBean) request.getAttribute("bean");
	%>

	<form action="MarksheetCtl.do" method="post">
		<center>

			<%
				if (bean != null) {
			%>
			<h1>Update Marksheet</h1>
			<%
				} else {
			%>
			<h1>Add Marksheet</h1>
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
					<th>Name :</th>
					<td><input type="text" name="name"
						value="<%=bean != null ? bean.getName() : ""%>"
						placeholder="Enter Student's Name"></td>
				</tr>

				<tr>
					<th>Roll No. :</th>
					<td><input type="text" name="roll_no"
						value="<%=bean != null ? bean.getRoll_no() : ""%>"
						placeholder="Enter Student's Roll No."></td>
				</tr>

				<tr>
					<th>Physics :</th>
					<td><input type="number" name="physics"
						value="<%=bean != null ? bean.getPhysics() : ""%>"
						placeholder="Enter Physics Number"></td>
				</tr>

				<tr>
					<th>Chemistry :</th>
					<td><input type="number" name="chemistry"
						value="<%=bean != null ? bean.getChemistry() : ""%>"
						placeholder="Enter Chemistry Number"></td>
				</tr>

				<tr>
					<th>Maths :</th>
					<td><input type="number" name="maths"
						value="<%=bean != null ? bean.getMaths() : ""%>"
						placeholder="Enter Maths Number"></td>
				</tr>

				<tr>
					<th></th>
					<td><input type="submit" name="operation"
						value="<%=bean != null ? "update" : "save"%>"></td>
				</tr>

			</table>
		</center>
	</form>

	<%@ include file="Footer.jsp"%>
</body>
</html>