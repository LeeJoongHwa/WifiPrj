package BookMark;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import getterSetter.BookMark;
import getterSetter.Homepage;
import mission2prj.DbDelete;
import mission2prj.totalCount;

public class bookMarkInsert {
	public void bookInsert(BookMark bookMark) throws ClassNotFoundException {
				
				Connection con = null; // 아래에서 finally 로 닫아줘야하기 때문에 미리 변수 선언.
				PreparedStatement preparedStatement = null;
				int affected = 0;
				try {
					Class.forName("org.sqlite.JDBC");
					String URL = "jdbc:SQLite:C:\\SQLiteStudio\\PublicWifi.sqlite3";

					con = DriverManager.getConnection(URL);
					System.out.println("데이터베이스 연결 완료");

					String sqlite = "INSERT INTO BookMark(BookName, Sequence, CreateDate VALUES(?,?,?)";
					preparedStatement = con.prepareStatement(sqlite);
					BookMark mark = new BookMark();
					preparedStatement.setString(1, mark.getBookName());
					preparedStatement.setInt(2, mark.getSequence());
					preparedStatement.setTimestamp(3, mark.getCreateDate());
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
	}


