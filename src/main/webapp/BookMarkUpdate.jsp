<% request.setCharacterEncoding("utf-8"); %>
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
BookMarkService service = new BookMarkService();
BookMark book = new BookMark();
%>

<body>
	<h1>북마크 그룹 수정</h1>
	<div>
		<a href="Wifi.jsp">홈</a> | 
		<a href="LocationHistory.jsp">위치 히스토리 목록</a> | 
		<a href="OpenAPI.jsp">Open API 와이파이 정보 가져오기</a> | 
		<a href="BookMarkShow2.jsp">북마크 보기</a> | 
		<a href="BookMarkGroup.jsp">북마크 그룹 관리</a>
	</div>
	<br> 북마크를 수정하시겠습니까?
	<table>

		<thead>
		<tbody>
			<form action="BookMarkUpdate.jsp" method="post">
			<tr>
				<th>북마크 이름</th>
				<td><input class="ipt" type="text" id="BookName"
					name="BookName"></input></td>
			</tr>
			<tr>
				<th>순서</th>
				<td><input class="ipt" type="text" id="Sequence"
					name="Sequence"></input></td>
			</tr>
			<tr>
				<td align="center" colspan=2><a href="BookMarkGroup.jsp">돌아가기</a>
					<button onclick="bookMarkUpdate('<%=book.getSequence()%>')">수정</button></td>
			</tr>
			</form>
		</tbody>
		</thead>

	</table>

</body>
</html>

<script>
	function bookMarkUpdate() {
		let BookName = document.getElementById("BookName");
		let Sequence = document.getElementById("Sequence");
		
		if (BookName === "" || Sequence === "") {
			alert("비어있는 값이 있습니다.");
		} else {
			alert("북마크 그룹 정보를 수정하였습니다.");
		}
	}
</script>
<%
String BookName = request.getParameter("BookName");
String ID = request.getParameter("Sequence");
if (ID != null) {
	service.bookMarkUpdate(BookName, Integer.parseInt(ID));
	
}
%>