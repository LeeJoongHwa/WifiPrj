<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="mission2prj.BookMarkService" %>
<%@ page import="getterSetter.BookMark" %>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
table {
	width: 100%;
}

th {
	border: solid 0px white;
	padding: 10px;
	background-color: #5f9ea0;
	color: white;
}

td {
	border: solid 1px lightgray;
	padding: 10px 0px;
	text-align: center;
	font-weight: bold;
}
<!--
padding과
 
height차이
???????
-->
</style>
</head>
<%
BookMarkService service = new BookMarkService();
List<BookMark> bookList = service.bringBookMark();

String BookName = request.getParameter("BookName");
String ID = request.getParameter("ID");
%>
<body>
	<h1>와이파이 정보 구하기</h1>
	<div>
		<a href="Wifi.jsp">홈</a> | 
		<a href="LocationHistory.jsp">위치 히스토리 목록</a> | 
		<a href="OpenAPI.jsp">Open API 와이파이 정보 가져오기</a> | 
		<a href="BookMarkShow2.jsp">북마크 보기</a> | 
		<a href="BookMarkGroup.jsp">북마크 그룹 관리</a>
	</div>
	<br>
	<div><button type="button" onclick="location.href='BookMarkInsert.jsp'">북마크 그룹 이름 추가</button></div>
		<table>
			<!-- 테이블 -->
			<thead>
				<!-- 테이블 머리 -->
				<tr>
					<th>ID</th>
					<th>북마크 이름</th>
					<th>순서</th>
					<th>등록일자</th>
					<th>수정일자</th>
					<th>비고</th>
				</tr>
			</thead>
			<tbody>
				
				<%if(bookList.isEmpty()){ %>
				<tr>
					<td colspan=6 align="center">정보가 존재하지 않습니다.</td>
				</tr>
				<%} else {for (BookMark bookMark : bookList) {%>
				<tr>
					<td><%=bookMark.getID() %></td>
					<td><%=bookMark.getBookName() %></td>
					<td><%=bookMark.getSequence() %></td>
					<td><%=bookMark.getCreateDate() %></td>
					<% if(bookMark.getUpdateDate()== null){
						%>
					<td></td>
					<%}else{ %>
					<td><%=bookMark.getUpdateDate() %></td>
					<%} %>
					<td align="center">
					<a href="BookMarkUpdate.jsp">수정</a> 
					<a href="BookMarkDelete.jsp">삭제</a>
					</td>
				</tr>
				<!-- 테이블 몸통 -->
				<%}} %>
			</tbody>
		</table>
</body>
</html>