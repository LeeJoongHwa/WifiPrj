<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@page import="getterSetter.BookMark"%>
<%@page import="java.util.List"%>
<%@ page import="mission2prj.BookMarkService" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
table {
	width: 100%;
	border-collapse: collapse;
}

th {
	padding: 10px 0px;
	background-color: #5f9ea0;
	color: white;
	border: 1px solid
}

td {
	border: solid 1px lightgray;
	padding: 10px 0px;
	text-align: center;
	font-weight: bold;
}

tr:nth-child(2n) {
	background-color: ghostWhite;
}
</style>
</head>
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
	<%
	request.setCharacterEncoding("UTF-8");
	BookMarkService service = new BookMarkService();
	List<BookMark> mark = service.showBookMark();
	
	%>
	<div>
		<table>
			<!-- 테이블 -->
			<thead>
				<!-- 테이블 머리 -->
				<tr>
					<th>ID</th>
					<th>북마크 이름</th>
					<th>와이파이명</th>
					<th>등록일자</th>
					<th>비고</th>
				</tr>
			</thead>
			<tbody>
				<%
				if(mark.isEmpty()){
				%>
				<tr>
					<td colspan="5" align="center">정보가 존재하지 않습니다.</td>
				</tr>
				<%}else{ 
				for(BookMark bookMark : mark){
				%>
				<tr>
					<td><%= bookMark.getID() %></td>
					<td><%= bookMark.getBookName() %></td>
					<td><a href="detail.jsp?MrgNo=<%=bookMark.getX_SWIFI_MGR_NO()%>"><%= bookMark.getX_SWIFI_MAIN_NM()%></a></td>
					<td><%= bookMark.getCreateDate()%></td>
					<td align="center"><a href="BookMarkDelete2.jsp?ID=<%=bookMark.getID()%>">삭제</a></td>
				</tr>
				<%}} %>
			</tbody>
		</table>
</body>
</html>