package mission2prj;

import getterSetter.Homepage;
import java.sql.*;
import java.util.List;

import java.util.ArrayList;

public class selectWifiDistance {
	public List<Homepage> selectWifiDistance(String lat, String lnt) throws ClassNotFoundException {
		List<Homepage> selectList = new ArrayList<>();
		Connection con = null; // 아래에서 finally 로 닫아줘야하기 때문에 미리 변수 선언.
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		try {
			Class.forName("org.sqlite.JDBC");
			String URL = "jdbc:SQLite:C:\\SQLiteStudio\\PublicWifi.sqlite3";

			con = DriverManager.getConnection(URL);
			System.out.println("데이터베이스 연결 완료");

			String sqlite = "SELECT *, 6371 * 2 * ASIN(SQRT(POWER(SIN(((LAT - "
					+ lnt
					+ ") "
					+ "* PI() / 180) / 2), 2) + COS("
					+ lnt
					+ "* PI() / 180) * COS((LAT * PI() / 180)) * "
					+ "POWER(SIN(((LNT - "
					+ lat
					+ ") * PI() / 180) / 2), 2))) "
					+ "AS Distance FROM Homepage ORDER BY Distance LIMIT 0, 20";

			preparedStatement = con.prepareStatement(sqlite);

			rs = preparedStatement.executeQuery();

			while (rs.next()) {
				Homepage homepage = new Homepage();
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
				homepage.setLNT(rs.getDouble("LAT"));
				homepage.setLAT(rs.getDouble("LNT"));
				homepage.setWORK_DTTM(rs.getTimestamp("WORK_DTTM"));
				homepage.setDistance(Math.round(Double.parseDouble(rs.getString("Distance")) * 10000.0) / 10000.0);
				selectList.add(homepage);
				
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

		return selectList;
	}
}
