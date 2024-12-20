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
		int pageNo = 1;

		if (request.getAttribute("pageNo") != null) {

			pageNo = (int) request.getAttribute("pageNo");

		}
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
				<th>S.No</th>
				<th>delete</th>
				<th>Name</th>
				<th>Roll NO.</th>
				<th>Physics</th>
				<th>Chemistry</th>
				<th>Maths</th>
				<th>Total</th>
				<th>Percentage</th>
				<th>Status</th>
				<th>Edit</th>
			</tr>
			<%
				Iterator it = list.iterator();
				while (it.hasNext()) {
					MarksheetBean bean = (MarksheetBean) it.next();
			%>

			<%
				int total = (bean.getPhysics() + bean.getChemistry() + bean.getMaths());
					double p = (total / 3);
			%>

			<%
				String status;
					if (bean.getPhysics() < 33 || bean.getChemistry() < 33 || bean.getMaths() < 33) {

						status = "Fail";

					} else {

						status = "Pass";

					}
			%>

			<tr align="center">
				<td><%=bean.getId()%></td>
				<td><input type="checkbox" name="ids" value="<%=bean.getId()%>"></td>
				<td><%=bean.getName()%></td>
				<td><%=bean.getRoll_no()%></td>
				<td><%=bean.getPhysics()%></td>
				<td><%=bean.getChemistry()%></td>
				<td><%=bean.getMaths()%></td>
				<td><%=total%></td>
				<td><%=p + "%"%></td>
				<td><%=status%></td>
				<td><a href="MarksheetCtl.do?id=<%=bean.getId()%>">Edit</a></td>
			</tr>

			<%
				}
			%>
		</table>
		<table width="100%">
			<tr>
				<td align="left"><input type="submit" name="operation"
					value="previous" <%=pageNo == 1 ? "disabled" : ""%>></td>
				<td><input type="submit" name="operation" value="delete"></td>
				<td align="right"><input type="submit" name="operation"
					value="next" <%=list.size() != 0 ? "" : "disabled"%>></td>
			</tr>

		</table>

		<input type="text" name="pageNo" value=<%=pageNo%>>

	</form>

	<%@ include file="Footer.jsp"%>
</body>
</html>


