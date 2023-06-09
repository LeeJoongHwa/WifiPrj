<%@ page import="java.sql.*" %>
<%@ page import="java.time.LocalDateTime" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    request.setCharacterEncoding("utf-8");
    String BookName = request.getParameter("bookName");
    String MrgNo = request.getParameter("MrgNo");
    PreparedStatement prepareStatement = null;
    Connection conn = null;
    request.setCharacterEncoding("utf-8");
    if (BookName != null && MrgNo != null) {
     
        try {
            Class.forName("org.sqlite.JDBC");
            String URL = "jdbc:sqlite:C:/SQLiteStudio/PublicWifi.sqlite3";
       
            conn = DriverManager.getConnection(URL);
            String sql = "INSERT INTO BookMark (X_SWIFI_MAIN_NM, CreateDate, BookMarkid, X_SWIFI_MGR_NO)"+
            		"VALUES ((SELECT X_SWIFI_MAIN_NM FROM Homepage WHERE X_SWIFI_MGR_NO = ?), ?, (SELECT ID FROM BookMarkList WHERE BookName = ?), ?)";
            
            prepareStatement = conn.prepareStatement(sql);
            prepareStatement.setString(1, MrgNo);
            prepareStatement.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));
            prepareStatement.setString(3, BookName);
            prepareStatement.setString(4, MrgNo);
            prepareStatement.executeUpdate();
      
            prepareStatement.close();
            conn.close();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
%>

<html>
<head>
    <title>Title</title>
</head>
<body>
<script>
    function bookmarkSaveOk(){
        alert("북마크 정보를 추가하였습니다.");
        window.location.href="BookMarkShow2.jsp";
    }
bookmarkSaveOk();
</script>
</body>
</html>