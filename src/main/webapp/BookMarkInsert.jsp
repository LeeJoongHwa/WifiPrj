<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="getterSetter.BookMark"%>
<%@page import="java.util.List"%>
<%@page import="java.sql.*"%>
<%@page import="BookMark.bookMarkInsert"%>
<%@ page import="java.time.LocalDateTime"%>
<%@ page import="mission2prj.BookMarkService"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://kit.fontawesome.com/f0a6d22eff.js"
	crossorigin="anonymous"></script>
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
}

.btn:hover {
	background-color: dodgerblue;
	border: solid 4px darkgray;
	border-radius: 8%;
	color: white;
}

.btn .icon {
	display: none;
}

.btn:hover .icon {
	display: inline;
}

tr:nth-child(2n+1) {
	background-color: ghostWhite;
}

.ipt {
	margin-left: 10px;
}
</style>
</head>
<%
BookMarkService service = new BookMarkService();
%>
<body>
	<h1>북마크 그룹 이름 추가</h1>
	<div>
		<a href="Wifi.jsp">홈</a> | 
		<a href="LocationHistory.jsp">위치 히스토리 목록</a> | 
		<a href="OpenAPI.jsp">Open API 와이파이 정보 가져오기</a> | 
		<a href="BookMarkShow2.jsp">북마크 보기</a> | 
		<a href="BookMarkGroup.jsp">북마크 그룹 관리</a>
	</div>
	<br>
	<%
	request.setCharacterEncoding("utf-8");
	String BookName = request.getParameter("BookName");
	String Sequence = request.getParameter("Sequence");

	if (BookName != null || Sequence != null) {
		service.bookMarkService(BookName, Sequence);
	}
	%>
	<form action="BookMarkInsert.jsp" method="POST">
		<table>
			<!-- 테이블 -->
			<colgroup>
				<!-- 테이블 하나 이상의 열에 스타일을 적용할 때 사용 -->
				<col style="width: 20%;" />
				<col style="width: 80%;" />
			</colgroup>
			<tbody>

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
					<td align="center" colspan=2>
						<button type="submit" onclick="getNameSequence();">추가</button>
					</td>
				</tr>
			</tbody>
		</table>
	</form>
<script>
    function getNameSequence() {
        let BookName = document.getElementById("BookName").value;
        let Sequence = document.getElementById("Sequence").value;

        if (BookName === "" || Sequence === "") {
            alert("비어있는 값이 있습니다.");
        } else {
            alert("북마크 그룹 정보를 추가하였습니다.");
        }
    }
</script>
</body>
</html>