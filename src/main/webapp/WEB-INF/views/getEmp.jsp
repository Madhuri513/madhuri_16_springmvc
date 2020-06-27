<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<% int empIdVal = (int) request.getAttribute("empId"); %>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>Search results for <%=empIdVal %> </h1>
	<h1>Name = Madhuri</h1>
	<h1>Sal = 30000</h1>
</body>
</html>