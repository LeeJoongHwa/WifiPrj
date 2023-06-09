package mission2prj;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.*;
import java.util.*;

import getterSetter.History;

public class HistoryService {
	public void historySave(String LAT, String LNT) throws ClassNotFoundException {

		Connection con = null; // 아래에서 finally 로 닫아줘야하기 때문에 미리 변수 선언.
		PreparedStatement preparedStatement = null;
		int affected = 0;
		try {
			Class.forName("org.sqlite.JDBC");
			String URL = "jdbc:SQLite:C:\\SQLiteStudio\\PublicWifi.sqlite3";

			con = DriverManager.getConnection(URL);
			System.out.println("데이터베이스 연결 완료");

			String sqlite = "INSERT INTO LocationHistory"
					+ "(LAT, LNT, InquiryDate)"
					+ "VALUES(?,?,?)";
			
			preparedStatement = con.prepareStatement(sqlite);
			preparedStatement.setString(1, LAT);
			preparedStatement.setString(2, LNT);
			preparedStatement.setTimestamp(3, new Timestamp(System.currentTimeMillis()));

			affected = preparedStatement.executeUpdate();

			if (affected > 0) {
				System.out.println("저장 성공");
			} else {
				System.out.println("저장 실패");
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
	
	
	public List<History> historyShow() throws ClassNotFoundException {
		
		List<History> bringHistory = new ArrayList();
		Connection con = null; // 아래에서 finally 로 닫아줘야하기 때문에 미리 변수 선언.
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		
		try {
			Class.forName("org.sqlite.JDBC");
			String URL = "jdbc:SQLite:C:\\SQLiteStudio\\PublicWifi.sqlite3";

			con = DriverManager.getConnection(URL);
			System.out.println("데이터베이스 연결 완료");

			String sqlite = "SELECT * FROM LocationHistory order by ID DESC";
			preparedStatement = con.prepareStatement(sqlite);
			rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				History history = new History();
				history.setID(rs.getInt("ID"));
				history.setLAT(rs.getDouble("LAT"));
				history.setLNT(rs.getDouble("LNT"));
				history.setInquiryDate(rs.getTimestamp("InquiryDate"));
				bringHistory.add(history);
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
		return bringHistory;
	}
	
	public void historyDelete(int ID) throws ClassNotFoundException {
		Connection con = null;
		PreparedStatement preparedStatement = null;
		try {
			Class.forName("org.sqlite.JDBC");
			String URL = "jdbc:SQLite:C:\\SQLiteStudio\\PublicWifi.sqlite3";

			con = DriverManager.getConnection(URL);
			System.out.println("데이터베이스 연결 완료");

			String sqlite = "DELETE FROM LocationHistory where ID = ?";

			preparedStatement = con.prepareStatement(sqlite);
			preparedStatement.setInt(1, ID);
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
