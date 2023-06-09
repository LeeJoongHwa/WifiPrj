package mission2prj;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DbDelete {
	public void dbDelete() throws ClassNotFoundException {
		Connection con = null; // 아래에서 finally 로 닫아줘야하기 때문에 미리 변수 선언.
		PreparedStatement preparedStatement = null;
		try {
			Class.forName("org.sqlite.JDBC");
			String URL = "jdbc:SQLite:C:\\SQLiteStudio\\PublicWifi.sqlite3";

			con = DriverManager.getConnection(URL);
			System.out.println("데이터베이스 연결 완료");

			String sqlite = "DELETE FROM Homepage";

			preparedStatement = con.prepareStatement(sqlite);

			int affected = preparedStatement.executeUpdate();

			if (affected > 0) {
				System.out.println("삭제 성공");
			} else {
				System.out.println("삭제 실패");
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
	}
}
