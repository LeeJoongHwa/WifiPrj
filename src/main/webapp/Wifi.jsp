<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="mission2prj.selectWifiDistance"%>
<%@ page import="getterSetter.History"%>
<%@page import="getterSetter.Homepage"%>
<%@ page import="java.sql.Timestamp"%>
<%@ page import="mission2prj.HistoryService" %>
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

tr:nth-child(2n+1) {
	background-color: ghostWhite;
}
</style>
</head>
<body>
	<%
	String lat = request.getParameter("lat");
	String lnt = request.getParameter("lnt");
	%>
	<h1>와이파이 정보 구하기</h1>
	<div>
		<a href="Wifi.jsp">홈</a> | 
		<a href="LocationHistory.jsp">위치 히스토리 목록</a> | 
		<a href="OpenAPI.jsp">Open API 와이파이 정보 가져오기</a> | 
		<a href="BookMarkShow2.jsp">북마크 보기</a> | <a href="BookMarkGroup.jsp">북마크 그룹 관리</a>
	</div>
	<br>
	<form action="Wifi.jsp" method="get">
		<label for="lat"> LAT:</label>
		<input type="text" value="0.0" id="lat" name="lat"> 
		<label for="lnt"> LNT:</label> 
		<input type="text" value="0.0" id="lnt" name="lnt">
		<button type="button" onclick="getLocation();">내 위치 가져오기</button>
		<button type="submit" onclick="getWifi();">근처 WIFI 정보 보기</button>
	</form>
	<br>
	<script>
		let lat = document.getElementById("lat");
		let lnt = document.getElementById("lnt");

		function getLocation() {
			if (navigator.geolocation) {
				navigator.geolocation.getCurrentPosition(success);
			} else {
				window.alert("현재 위치를 가져올 수 없습니다.")
			}
			function success(position) {
				lat.value = position.coords.latitude;
				lnt.value = position.coords.longitude;
			}
		}

		function getWifi() {
			let lat = document.getElementById(lat).value;
			let lnt = document.getElementById(lnt).value;
			if (lat === "" || lnt === "") {
				window.alert("값이 비어있습니다.");
			}
		}
	</script>
	<table>
		<!-- 테이블 -->
		<thead>
			<!-- 테이블 머리 -->
			<tr>
				<!-- 테이블 주제 -->
				<th>거리(Km)</th>
				<th>관리번호</th>
				<th>자치구</th>
				<th>와이파이명</th>
				<th>도로명주소</th>
				<th>상세주소</th>
				<th>설치위치(층)</th>
				<th>설치유형</th>
				<th>설치기관</th>
				<th>서비스구분</th>
				<th>망종류</th>
				<th>설치년도</th>
				<th>실내외구분</th>
				<th>WIFI접속환경</th>
				<th>X좌표</th>
				<th>Y좌표</th>
				<th>작업일자</th>
			</tr>
		</thead>

		<tbody>
			
			<%if (lat == null || lnt == null || lat.equals("0.0") || lnt.equals("0.0")) {%>
			
			<tr>
				<td colspan="17"><b>위치 정보를 입력한 후에 조회하세요</b></td>
			</tr>
			<%} else {%>
				<%
				selectWifiDistance wifiDistance = new selectWifiDistance();
				List<Homepage> selectList = wifiDistance.selectWifiDistance(lat, lnt);
				HistoryService service = new HistoryService();
				service.historySave(lat, lnt);
				for (Homepage homepage : selectList) {
				%>
					<tr>
						<td><%=homepage.getDistance()%></td>
						<td><%=homepage.getX_SWIFI_MGR_NO()%></td>
						<td><%=homepage.getX_SWIFI_WRDOFC()%></td>
						<td><a href="detail.jsp?MrgNo=<%=homepage.getX_SWIFI_MGR_NO()%>
							&Distance=<%=homepage.getDistance()%>">
							<%=homepage.getX_SWIFI_MAIN_NM()%></a></td>
						<td><%=homepage.getX_SWIFI_ADRES1()%></td>
						<td><%=homepage.getX_SWIFI_ADRES2()%></td>
						<td><%=homepage.getX_SWIFI_INSTL_FLOOR()%></td>
						<td><%=homepage.getX_SWIFI_INSTL_TY()%></td>
						<td><%=homepage.getX_SWIFI_INSTL_MBY()%></td>
						<td><%=homepage.getX_SWIFI_SVC_SE()%></td>
						<td><%=homepage.getX_SWIFI_CMCWR()%></td>
						<td><%=homepage.getX_SWIFI_CNSTC_YEAR()%></td>
						<td><%=homepage.getX_SWIFI_INOUT_DOOR()%></td>
						<td><%=homepage.getX_SWIFI_REMARS3()%></td>
						<td><%=homepage.getLAT()%></td>
						<td><%=homepage.getLNT()%></td>
						<td><%=homepage.getWORK_DTTM()%></td>
				   </tr>
				<%}%>
			<%}%>
		</tbody>
	</table>
</body>
</html>