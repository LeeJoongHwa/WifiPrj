<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@page import="mission2prj.DetailSelect" %>
	<%@page import="getterSetter.Homepage" %>
	<%@page import="getterSetter.BookMark" %>
	<%@page import="mission2prj.BookMarkService" %>
	<%@page import="java.util.List"%>
	<%@page import="java.sql.Timestamp"%>
	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://kit.fontawesome.com/f0a6d22eff.js" crossorigin="anonymous"></script>
<title>Insert title here</title>
<style>
table {
	width: 100%;
}

th {
	padding : 10px 0px;
	background-color: #5f9ea0;
	color: white;
}
td {
	border : solid 1px lightgray;
	padding : 10px 0px;
	font-weight: bold;
}
 .btn:hover{
    background-color : lightgray;
    border-radius : 8%;
    font-color : white;
  }
  
  .btn .icon{
    display : none;
  }
  
  .btn:hover .icon{
    display : inline;
  }
 tr:nth-child(2n) {
background-color: ghostWhite;
}
 
  
</style>
</head>
<body>
	<%
	request.setCharacterEncoding("UTF-8");
	String MrgNo = request.getParameter("MrgNo");
	String Distance = request.getParameter("Distance");
	
	DetailSelect selectDetail = new DetailSelect();
	Homepage homepage = null;
	
	BookMarkService service = new BookMarkService();
	List<BookMark> bookMarkList = service.bringBookMark();
	%>
	<%
	if(request.getParameter("Distance") != null){
		homepage = selectDetail.selectWifiDistance(MrgNo,Distance);
	}
	%>

	<h1>와이파이 정보 구하기</h1>
	<div>
		<a href="Wifi.jsp">홈</a> | 
		<a href="LocationHistory.jsp">위치 히스토리 목록</a> | 
		<a href="OpenAPI.jsp">Open API 와이파이 정보 가져오기</a> | 
		<a href="BookMarkShow2.jsp">북마크 보기</a> | 
		<a href="BookMarkGroup.jsp">북마크 그룹 관리</a>
	</div>
	<br>
    
  <select class="btn" name="bookName" id ="bookName">
  	<span class="icon"><i class="fa-solid fa-check"></i></span>
  	<option value="">북마크 그룹이름 선택</option>
  	<%for(BookMark mark : bookMarkList){%>
  		<option name="bookName" id = "bookName" value="<%= mark.getBookName()%>"><%= mark.getBookName() %></option>
  	<%}%>
  </select>
	<button onclick="addBookMark('<%=homepage.getX_SWIFI_MGR_NO()%>', document.querySelector('#bookName').value)">즐겨찾기 추가하기</button>
	<table>
		<!-- 테이블 -->
		<colgroup>
			<!-- 테이블 하나 이상의 열에 스타일을 적용할 때 사용 -->
			<col style="width: 20%;" />
			<col style="width: 80%;" />
		</colgroup>
		<tbody>
			<tr><th>거리(Km)</th><td><%=homepage.getDistance()%></td></tr>
			<tr><th>관리번호</th><td><%=homepage.getX_SWIFI_MGR_NO()%></td></tr>
			<tr><th>자치구</th><td><%=homepage.getX_SWIFI_WRDOFC()%></td></tr>
			<tr><th>와이파이명</th><td><%=homepage.getX_SWIFI_MAIN_NM()%></td></tr>
			<tr><th>도로명주소</th><td><%=homepage.getX_SWIFI_ADRES1()%></td></tr>
			<tr><th>상세주소</th><td><%=homepage.getX_SWIFI_ADRES2()%></td></tr>
			<tr><th>설치위치(층)</th><td><%=homepage.getX_SWIFI_INSTL_FLOOR()%></td></tr>
			<tr><th>설치유형</th><td><%=homepage.getX_SWIFI_INSTL_TY()%></td></tr>
			<tr><th>설치기관</th><td><%=homepage.getX_SWIFI_INSTL_MBY()%></td></tr>
			<tr><th>서비스구분</th><td><%=homepage.getX_SWIFI_SVC_SE()%></td></tr>
			<tr><th>망종류</th><td><%=homepage.getX_SWIFI_CMCWR()%></td></tr>
			<tr><th>설치년도</th><td><%=homepage.getX_SWIFI_CNSTC_YEAR()%></td></tr>
			<tr><th>실내외구분</th><td><%=homepage.getX_SWIFI_INOUT_DOOR()%></td></tr>
			<tr><th>WIFI접속환경</th><td><%=homepage.getX_SWIFI_REMARS3()%></td></tr>
			<tr><th>X좌표</th><td><%=homepage.getLAT()%></td></tr>
			<tr><th>Y좌표</th><td><%=homepage.getLNT()%></td></tr>
			<tr><th>작업일자</th><td><%=homepage.getWORK_DTTM()%></td></tr>
		</tbody>
	</table>
	<script type="text/javascript">

	function addBookMark(MrgNo){
		let bookName = document.getElementById('bookName').value;
		
		console.log('Data check' + MrgNo,bookName)
		
		let form = document.createElement('form');
		form.setAttribute("charset","UTF-8");
		form.setAttribute("method","POST");
		form.setAttribute('action','<%=request.getContextPath()%>/BookMarkInsert2.jsp');
		
		let mrgNoInput = document.createElement("input");
		mrgNoInput.setAttribute("type","hidden");
		mrgNoInput.setAttribute("name","MrgNo");
		mrgNoInput.setAttribute("value",MrgNo);
		
		let bookNameInput = document.createElement("input");
		bookNameInput.setAttribute("type","hidden");
		bookNameInput.setAttribute("name","bookName");
		bookNameInput.setAttribute("value",bookName);
		
		form.appendChild(mrgNoInput);
		form.appendChild(bookNameInput);
		
		document.body.appendChild(form);
		
		form.submit();
	}
</script>
</body>
</html>