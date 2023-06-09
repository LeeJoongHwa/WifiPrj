
<%
request.setCharacterEncoding("utf-8");
%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="getterSetter.BookMark"%>
<%@ page import="mission2prj.BookMarkService"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<title>Insert title here</title>
<style>
table {
	width: 100%;
}

th {
	padding: 10px 0px;
	background-color: #5f9ea0;
	color: white;
}

td {
	border: solid 1px lightgray;
	padding: 10px 0px;
	font-weight: bold;
}

tr:nth-child(2n) {
	background-color: ghostWhite;
}

.ipt {
	margin-left: 10px;
}
</style>
</head>
<%
    request.setCharacterEncoding("utf-8");
    int bookMarkId = Integer.parseInt(request.getParameter("ID"));
    BookMarkService service = new BookMarkService();
    List<BookMark> bookmarkList = service.showBookMarkDelete(bookMarkId);
  %>
<body>
	<h1>북마크 삭제</h1>
	<div>
		<a href="Wifi.jsp">홈</a> | 
		<a href="LocationHistory.jsp">위치 히스토리 목록</a> | 
		<a href="OpenAPI.jsp">Open API 와이파이 정보 가져오기</a> | 
		<a href="BookMarkShow2.jsp">북마크 보기</a> | 
		<a href="BookMarkGroup.jsp">북마크 그룹 관리</a>
	</div>
	<br> 북마크를 삭제하시겠습니까?
	<table>

		<thead>
		<tbody>
		<% for(BookMark bookMark : bookmarkList) { %>
			<tr><th>북마크 이름</th><td><%=bookMark.getBookName() %></td></tr>
			<tr><th>와이파이명</th><td><%=bookMark.getX_SWIFI_MAIN_NM() %></td></tr>
			<tr><th>등록일자</th><td><%=bookMark.getCreateDate() %></td></tr>
			<tr>
			<form action="bookmarkDeleteOk.jsp?bookMarkId=<%=bookMarkId%>" method="post" %>
			<td align="center" colspan=2>
			<a href="BookMarkShow2.jsp">돌아가기</a>
			<input type="submit" onclick="deleteOK()" value="삭제"></td>
			</form>
			</tr>
			<%} %>
		</tbody>
		</thead>

	</table>

</body>
<script>
  function deleteOK() {
    alert("북마크 정보를 삭제하였습니다.");
    location.reload();
  }
</script>
</html>
