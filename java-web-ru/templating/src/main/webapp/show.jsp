<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>User</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css"
	      rel="stylesheet"
	      integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We"
	      crossorigin="anonymous">
</head>
<body>
<table>
		<tr>
			<td>${user.get("id")}</td>
			<td><a href='/users/show?id=${user.get("id")}'>${user.get("firstName")} ${user.get("lastName")}</a></td>
		</tr>
		<tr>
			<td>${user.get("email")}</td>
		</tr>
		<tr>
		<td>
			<a href='/users/delete?id=${user.get("id")}'>Delete</a>
		</td>
	</tr>
</table>
</body>
</html>