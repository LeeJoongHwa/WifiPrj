<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="getterSetter.History"%>
<%@ page import="mission2prj.HistoryService"%>
<%@ page import="java.util.*"%>

<!DOCTYPE html>
<html>
<head>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<!-- Jquery를 사용하기 위한 라이브러리 추가 (ajax 사용위함)-->
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

tr:nth-child(2n+1) {
	background-color: ghostWhite;
}
</style>
</head>

<%
HistoryService service = new HistoryService();
List<History> historyList = service.historyShow();
%>

<body>
	<h1>와이파이 정보 구하기</h1>
	<div>
		<a href="Wifi.jsp">홈</a> | 
		<a href="LocationHistory.jsp">위치 히스토리 목록</a> | 
		<a href="OpenAPI.jsp">Open API 와이파이 정보 가져오기</a> | 
		<a href="BookMarkShow2.jsp">북마크 보기</a> | <a href="BookMarkGroup.jsp">북마크 그룹 관리</a>
	</div>
	<br>
	<table>
		<!-- 테이블 -->
		<thead>
			<!-- 테이블 머리 -->
			<tr>
				<th>ID</th>
				<th>X좌표</th>
				<th>Y좌표</th>
				<th>조회일자</th>
				<th>비고</th>
			</tr>
		</thead>
		<tbody>
			<%for (History history : historyList) {%>
			<tr>
				<td><%=history.getID()%></td>
				<td><%=history.getLAT()%></td>
				<td><%=history.getLNT()%></td>
				<td><%=history.getInquiryDate()%></td>
				<td align="center">
					<button onclick="historyDelete('<%=history.getID()%>')">삭제</button></td>
			</tr>
			<%}%>

		</tbody>
	</table>
</body>
</html>
<script>
	function historyDelete(ID) {
		if (confirm("정말로 삭제하시겠습니까?")) {
			$.ajax({
				type : 'POST',
				url : "LocationHistory.jsp",
				data : {
					ID : ID
				},
				success : function() {
					location.reload();
				}
			});
		}
	}
</script>

<%
String ID = request.getParameter("ID");
if (ID != null) {
	try {
		service.historyDelete(Integer.parseInt(ID));
		response.setStatus(204);
		return;
	} finally {
		return;
	}
}
%>
