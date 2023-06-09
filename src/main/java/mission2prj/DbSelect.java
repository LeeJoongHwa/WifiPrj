/*
 * package mission2prj;
 * 
 * import java.sql.Connection; import java.sql.DriverManager; import
 * java.sql.PreparedStatement; import java.sql.ResultSet; import
 * java.sql.SQLException;
 * 
 * import getterSetter.Homepage;
 * 
 * public class DbSelect { public void dbSelect(Homepage homepage) throws
 * ClassNotFoundException { Connection con = null; // 아래에서 finally 로 닫아줘야하기 때문에
 * 미리 변수 선언. PreparedStatement preparedStatement = null; ResultSet rs = null;
 * try { Class.forName("org.sqlite.JDBC"); String URL =
 * "jdbc:SQLite:C:\\SQLiteStudio\\PublicWifi.sqlite3";
 * 
 * con = DriverManager.getConnection(URL); System.out.println("데이터베이스 연결 완료");
 * 
 * String sqlite =
 * "SELECT * FROM Homepage WHERE MRG_NO = ? AND JACHI = ? AND WIFI_NAME = ?";
 * 
 * preparedStatement = con.prepareStatement(sqlite);
 * preparedStatement.setString(1, homepage.getX_SWIFI_MGR_NO());
 * preparedStatement.setString(2, homepage.getX_SWIFI_WRDOFC());
 * preparedStatement.setString(3, homepage.getX_SWIFI_MAIN_NM());
 * 
 * rs = preparedStatement.executeQuery();
 * 
 * while (rs.next()) { String MrgNo = rs.getString("MRG_NO"); String Jachi =
 * rs.getString("JACHI"); String WifiName = rs.getString("WIFI_NAME");
 * 
 * }
 * 
 * } catch (SQLException e) { throw new RuntimeException(e); } finally { try {
 * if (rs != null && !rs.isClosed()) {
 * 
 * rs.close(); } } catch (SQLException e) { throw new RuntimeException(e); }
 * 
 * try { if (preparedStatement != null && !preparedStatement.isClosed()) { // 객체
 * 연결 해제 preparedStatement.close(); } } catch (SQLException e) { throw new
 * RuntimeException(e); }
 * 
 * try { if (con != null && !con.isClosed()) { // 객체 연결 해제 con.close(); } }
 * catch (SQLException e) { throw new RuntimeException(e); } } } }
 */