<%@page import="com.rays.bean.ShopBean"%>
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
		int pageNo = 1;
	%>

	<%
		if (request.getAttribute("pageNo") != null) {

			pageNo = (int) request.getAttribute("pageNo");

		}
	%>

	<form action="ShopListCtl.do" method="post">

		<h1 align="center">Product List</h1>

		<table>
			<tr>
				<th>Product Name:</th>
				<td><input type="text" name="productName"
					placeholder="Enter Product Name"></td>

				<th>Product Price:</th>
				<td><input type="number" name="productPrice"
					placeholder="Enter Product Price"></td>

				<th>Purchase Date:</th>
				<td><input type="date" name="purchaseDate"
					placeholder="Enter Purchase Date"></td>

				<th>Product Category:</th>
				<td><input type="text" name="productCategory"
					placeholder="Enter Product Category"></td>

				<td><input type="submit" name="operation" value="search"></td>
			</tr>

		</table>

		<table width="100%" border="1px">
			<tr style="background-color: lightgreen">
				<th>S.No.</th>
				<th>Delete</th>
				<th>Product Name</th>
				<th>Product Price</th>
				<th>Purchase Date</th>
				<th>Product Category</th>
				<th>Edit</th>
			</tr>

			<%
				Iterator it = list.iterator();
				while (it.hasNext()) {
					ShopBean bean = (ShopBean) it.next();
			%>

			<tr align="center">
				<td><%=bean.getId()%></td>
				<td><input type="checkbox" name="ids" value="<%=bean.getId()%>"></td>
				<td><%=bean.getProductName()%></td>
				<td><%=bean.getProductPrice()%></td>
				<td><%=bean.getPurchaseDate()%></td>
				<td><%=bean.getProductCategory()%></td>
				<td><a href="ShopCtl.do?id=<%=bean.getId()%>">Edit</a></td>
			</tr>
			<%
				}
			%>
		</table>
		<br>
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