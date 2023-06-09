<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="mission2prj.DbInsert"%>
<%@page import="getterSetter.Homepage" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
	DbInsert total = new DbInsert();
	Homepage homepage = new Homepage();
	int totalData = total.dbInsert(homepage);
	%>
	<div style="text-align: center">
		<h1><%=totalData%>개의 WIFI 정보를 정상적으로 저장하였습니다.</h1>
	</div>
	<div style="text-align: center">
		<a href="Wifi.jsp">홈으로 가기</a>
	</div>
</body>
</html>