package mission2prj;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import getterSetter.BookMark;

import getterSetter.History;

public class BookMarkService {
	public void bookMarkService(String BookName, String Sequence) throws ClassNotFoundException {

		if (BookName != null && Sequence != null && !BookName.equals("") && !Sequence.equals("")) {
			Connection con = null; // 아래에서 finally 로 닫아줘야하기 때문에 미리 변수 선언.
			PreparedStatement preparedStatement = null;

			try {
				Class.forName("org.sqlite.JDBC");
				String URL = "jdbc:SQLite:C:\\SQLiteStudio\\PublicWifi.sqlite3";

				con = DriverManager.getConnection(URL);
				
				String check = "SELECT COUNT(*) FROM BookMarkList WHERE BookName = ? OR Sequence = ?";
				preparedStatement = con.prepareStatement(check);
				preparedStatement.setString(1, BookName);
				preparedStatement.setInt(2, Integer.parseInt(Sequence));
				ResultSet resultSet = preparedStatement.executeQuery();
				resultSet.next();
				int count = resultSet.getInt(1);
				
				if(count == 0) {
				String sqlite = "INSERT INTO BookMarkList(BookName, Sequence, CreateDate) VALUES(?,?,?)";
				preparedStatement = con.prepareStatement(sqlite);

				preparedStatement.setString(1, BookName);
				preparedStatement.setInt(2, Integer.parseInt(Sequence));
				preparedStatement.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
				
				preparedStatement.executeUpdate();
				} else {
					System.out.println("중복된 값이 있습니다. 다시 입력하세요.");
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

	public List<BookMark> bringBookMark() throws ClassNotFoundException {
		List<BookMark> BookMark = new ArrayList<>();
		Connection con = null; // 아래에서 finally 로 닫아줘야하기 때문에 미리 변수 선언.
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;

		try {
			Class.forName("org.sqlite.JDBC");
			String URL = "jdbc:SQLite:C:\\SQLiteStudio\\PublicWifi.sqlite3";

			con = DriverManager.getConnection(URL);
			System.out.println("데이터베이스 연결 완료");

			String sqlite = "SELECT * FROM BookMarkList order by ID DESC";
			preparedStatement = con.prepareStatement(sqlite);
			rs = preparedStatement.executeQuery();

			while (rs.next()) {
				BookMark bookMark = new BookMark();
				bookMark.setID(rs.getInt("ID"));
				bookMark.setBookName(rs.getString("BookName"));
				bookMark.setSequence(rs.getInt("Sequence"));
				bookMark.setCreateDate(rs.getTimestamp("CreateDate"));
				bookMark.setUpdateDate(rs.getTimestamp("UpdateDate"));
				System.out.println(rs.getString("BookName"));
				BookMark.add(bookMark);
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
		return BookMark;
	}
	public void bookMarkDelete(int Sequence) throws ClassNotFoundException{
		Connection con = null;
		PreparedStatement preparedStatement = null;
		try {
			Class.forName("org.sqlite.JDBC");
			String URL = "jdbc:SQLite:C:\\SQLiteStudio\\PublicWifi.sqlite3";

			con = DriverManager.getConnection(URL);
			System.out.println("데이터베이스 연결 완료");

			String sqlite = "DELETE FROM BookMarkList where Sequence = ?";

			preparedStatement = con.prepareStatement(sqlite);
			preparedStatement.setInt(1, Sequence);
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
	public void bookMarkUpdate(String BookName, int Sequence) throws ClassNotFoundException {
		Connection con = null;
		PreparedStatement updateStatement = null;
		
		try {
			Class.forName("org.sqlite.JDBC");
			String URL = "jdbc:SQLite:C:\\SQLiteStudio\\PublicWifi.sqlite3";

			con = DriverManager.getConnection(URL);
			System.out.println("데이터베이스 연결 완료");

			String update1 = "update BookMarkList set BookName = ?, UpdateDate = ? where Sequence = ?";
			
			updateStatement = con.prepareStatement(update1);
			updateStatement.setString(1, BookName);
			updateStatement.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
			updateStatement.setInt(3, Sequence);
			int affected = updateStatement.executeUpdate();

			if (affected > 0) {
				System.out.println("수정 성공");
			} else {
				System.out.println("수정 실패");
			}
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
	
			try {
				if (updateStatement != null && !updateStatement.isClosed()) { // 객체 연결 해제
					updateStatement.close();
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
	public List<BookMark> showBookMark() throws ClassNotFoundException {
		List<BookMark> bookMarks = new ArrayList();
		Connection con = null; // 아래에서 finally 로 닫아줘야하기 때문에 미리 변수 선언.
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;

		try {
			Class.forName("org.sqlite.JDBC");
			String URL = "jdbc:SQLite:C:\\SQLiteStudio\\PublicWifi.sqlite3";

			con = DriverManager.getConnection(URL);
			System.out.println("데이터베이스 연결 완료");

			String sqlite = "SELECT b.id, l.BookName, b.X_SWIFI_MAIN_NM, b.CreateDate, b.X_SWIFI_MGR_NO " +
                    "FROM BookMark b " +
                    "JOIN BookMarkList l ON b.id = l.ID " +
                    "ORDER BY b.id";
			preparedStatement = con.prepareStatement(sqlite);
			rs = preparedStatement.executeQuery();

			while (rs.next()) {
				BookMark bookMark = new BookMark();
				bookMark.setID(rs.getInt("id"));
				bookMark.setBookName(rs.getString("BookName"));
				bookMark.setX_SWIFI_MAIN_NM(rs.getString("X_SWIFI_MAIN_NM"));
				bookMark.setCreateDate(rs.getTimestamp("CreateDate"));
				bookMark.setX_SWIFI_MGR_NO(rs.getString("X_SWIFI_MGR_NO"));
				bookMarks.add(bookMark);
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
		return bookMarks;
	}
	
	public List<BookMark> showBookMarkDelete(int ID) throws ClassNotFoundException {
		List<BookMark> BookMark = new ArrayList<>();
		Connection con = null; // 아래에서 finally 로 닫아줘야하기 때문에 미리 변수 선언.
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;

		try {
			Class.forName("org.sqlite.JDBC");
			String URL = "jdbc:SQLite:C:\\SQLiteStudio\\PublicWifi.sqlite3";

			con = DriverManager.getConnection(URL);
			System.out.println("데이터베이스 연결 완료");

			String sqlite = "SELECT b.id, l.BookName, b.X_SWIFI_MAIN_NM, b.CreateDate, b.X_SWIFI_MGR_NO " +
                    "FROM BookMark b " +
                    "JOIN BookMarkList l ON b.id = l.ID " +
                    "ORDER BY b.id = ?";
			preparedStatement = con.prepareStatement(sqlite);
			preparedStatement.setInt(1,ID);
			rs = preparedStatement.executeQuery();

			while (rs.next()) {
				BookMark bookMark = new BookMark();
				bookMark.setBookName(rs.getString("BookName"));
				bookMark.setX_SWIFI_MAIN_NM(rs.getString("X_SWIFI_MAIN_NM"));
				bookMark.setCreateDate(rs.getTimestamp("CreateDate"));
				BookMark.add(bookMark);
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
		return BookMark;
	}
	public void bookMarkDelete2(int ID) throws ClassNotFoundException{
		Connection con = null;
		PreparedStatement preparedStatement = null;
		try {
			Class.forName("org.sqlite.JDBC");
			String URL = "jdbc:SQLite:C:\\SQLiteStudio\\PublicWifi.sqlite3";

			con = DriverManager.getConnection(URL);
			System.out.println("데이터베이스 연결 완료");

			String sqlite = "DELETE FROM BookMark where ID = ?";

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
