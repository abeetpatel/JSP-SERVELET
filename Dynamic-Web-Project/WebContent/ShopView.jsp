<%@page import="com.rays.bean.ShopBean"%>
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
		ShopBean bean = (ShopBean) request.getAttribute("bean");
	%>

	<form action="ShopCtl.do" method="post">

		<center>

			<%
				if (bean != null) {
			%>
			<h1>Update Product</h1>
			<%
				} else {
			%>
			<h1>Add Product</h1>
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
					<th>Product Name:</th>
					<td><input type="text" name="productName"
						value="<%=bean != null ? bean.getProductName() : ""%>"
						placeholder="Enter Product Name"></td>
				</tr>

				<tr>
					<th>Product Price:</th>
					<td><input type="number" name="productPrice"
						value="<%=bean != null ? bean.getProductPrice() : ""%>"
						placeholder="Enter Product Price"></td>
				</tr>

				<tr>
					<th>Purchase Date:</th>
					<td><input type="date" name="purchaseDate"
						value="<%=bean != null ? bean.getPurchaseDate() : ""%>"
						placeholder="Enter Purchase Date"></td>
				</tr>

				<tr>
					<th>Product Category:</th>
					<td><input type="text" name="productCategory"
						value="<%=bean != null ? bean.getProductCategory() : ""%>"
						placeholder="Enter Product Name"></td>
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