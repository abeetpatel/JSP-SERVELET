<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<form action="FirstServlet" method="post">

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
				<th></th>
				<td><input type="submit"></td>
			</tr>

		</table>

	</form>

</body>
</html>