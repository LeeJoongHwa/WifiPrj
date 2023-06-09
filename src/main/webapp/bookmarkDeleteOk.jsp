<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="mission2prj.BookMarkService" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<%
    if (request.getMethod().equals("POST")) {
        int id = Integer.parseInt(request.getParameter("bookMarkId"));
        BookMarkService bookmarkService = new BookMarkService();
        bookmarkService.bookMarkDelete2(id);
        response.sendRedirect("BookMarkShow2.jsp");
    }
%>
<body>

</body>
</html>