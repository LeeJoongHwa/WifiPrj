package mission2prj;

import getterSetter.Homepage;
import java.sql.*;
import java.util.List;

import java.util.ArrayList;

public class DetailSelect {
	public Homepage selectWifiDistance(String X_SWIFI_MGR_NO, String Distance) throws ClassNotFoundException {
		Homepage homepage = null;
		Connection con = null; // 아래에서 finally 로 닫아줘야하기 때문에 미리 변수 선언.
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		System.out.println(X_SWIFI_MGR_NO);
		try {
			Class.forName("org.sqlite.JDBC");
			String URL = "jdbc:SQLite:C:\\SQLiteStudio\\PublicWifi.sqlite3";

			con = DriverManager.getConnection(URL);
			System.out.println("데이터베이스 연결 완료");

			String sqlite = "SELECT * FROM Homepage WHERE X_SWIFI_MGR_NO = ?";

			preparedStatement = con.prepareStatement(sqlite);
			preparedStatement.setString(1, X_SWIFI_MGR_NO);
			rs = preparedStatement.executeQuery();

			while (rs.next()) {
				System.out.println(1);
				homepage = new Homepage();
				homepage.setX_SWIFI_MGR_NO(rs.getString("X_SWIFI_MGR_NO"));
				homepage.setX_SWIFI_WRDOFC(rs.getString("X_SWIFI_WRDOFC"));
				homepage.setX_SWIFI_MAIN_NM(rs.getString("X_SWIFI_MAIN_NM"));
				homepage.setX_SWIFI_ADRES1(rs.getString("X_SWIFI_ADRES1"));
				homepage.setX_SWIFI_ADRES2(rs.getString("X_SWIFI_ADRES2"));
				homepage.setX_SWIFI_INSTL_FLOOR(rs.getString("X_SWIFI_INSTL_FLOOR"));
				homepage.setX_SWIFI_INSTL_TY(rs.getString("X_SWIFI_INSTL_TY"));
				homepage.setX_SWIFI_INSTL_MBY(rs.getString("X_SWIFI_INSTL_MBY"));
				homepage.setX_SWIFI_SVC_SE(rs.getString("X_SWIFI_SVC_SE"));
				homepage.setX_SWIFI_CMCWR(rs.getString("X_SWIFI_CMCWR"));
				homepage.setX_SWIFI_CNSTC_YEAR(rs.getString("X_SWIFI_CNSTC_YEAR"));
				homepage.setX_SWIFI_INOUT_DOOR(rs.getString("X_SWIFI_INOUT_DOOR"));
				homepage.setX_SWIFI_REMARS3(rs.getString("X_SWIFI_REMARS3"));
				homepage.setLNT(rs.getDouble("LNT"));
				homepage.setLAT(rs.getDouble("LAT"));
				homepage.setWORK_DTTM(rs.getTimestamp("WORK_DTTM"));
				homepage.setDistance(Double.parseDouble(Distance));
				System.out.println(rs.getString("X_SWIFI_MAIN_NM"));
				
			}

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if (preparedStatement != null && !preparedStatement.isClosed()) { // 객체 연결 해제
					preparedStatement.close();
				}
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}

			try {
				if (con != null && !con.isClosed()) { // 객체 연결 해제
					con.close();
				}
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}

		return homepage;
	}
}
