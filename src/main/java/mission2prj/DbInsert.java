package mission2prj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import getterSetter.Homepage;
import mission2prj.totalCount;

public class DbInsert {
    private static final int BATCH_SIZE = 1000;

    public int dbInsert(Homepage homepage) throws ClassNotFoundException, IOException {
        DbDelete delete = new DbDelete();
        delete.dbDelete();

        int startIndex = 1;
        totalCount total = new totalCount();
        int count = total.total();

        try (Connection con = DriverManager.getConnection("jdbc:SQLite:C:\\SQLiteStudio\\PublicWifi.sqlite3")) {
            System.out.println("데이터베이스 연결 완료");
            con.setAutoCommit(false);

            String insertSql = "INSERT INTO Homepage(X_SWIFI_MGR_NO, X_SWIFI_WRDOFC, X_SWIFI_MAIN_NM, X_SWIFI_ADRES1, "
            		+ "X_SWIFI_ADRES2, X_SWIFI_INSTL_FLOOR, X_SWIFI_INSTL_TY, X_SWIFI_INSTL_MBY, X_SWIFI_SVC_SE, X_SWIFI_CMCWR, X_SWIFI_CNSTC_YEAR, X_SWIFI_INOUT_DOOR, X_SWIFI_REMARS3, LNT, LAT, WORK_DTTM) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

            try (PreparedStatement preparedStatement = con.prepareStatement(insertSql)) {
                for (int i = 0; i <= count / BATCH_SIZE; i++) {
                    StringBuilder urlBuilder = new StringBuilder("http://openapi.seoul.go.kr:8088");
                    urlBuilder.append("/" + URLEncoder.encode("486e796367776e643436694c704c57", "UTF-8"));
                    urlBuilder.append("/" + URLEncoder.encode("json", "UTF-8"));
                    urlBuilder.append("/" + URLEncoder.encode("TbPublicWifiInfo", "UTF-8"));
                    urlBuilder.append("/" + URLEncoder.encode(String.valueOf(startIndex), "UTF-8"));
                    urlBuilder.append("/" + URLEncoder.encode(String.valueOf(startIndex + BATCH_SIZE - 1), "UTF-8"));

                    URL url = new URL(urlBuilder.toString());
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");
                    conn.setRequestProperty("Content-type", "application/xml");
                    System.out.println("Response code: " + conn.getResponseCode());

                    try (BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
                        StringBuilder sb = new StringBuilder();
                        String line;
                        while ((line = rd.readLine()) != null) {
                            sb.append(line);
                        }
                        String publicWifi = sb.toString();
                        System.out.println(publicWifi);

                        JsonParser parser = new JsonParser();
                        JsonObject jsonObj = parser.parse(publicWifi).getAsJsonObject();
                        JsonArray wifiArray = jsonObj.getAsJsonObject("TbPublicWifiInfo").getAsJsonArray("row");

                        for (int j = 0; j < wifiArray.size(); j++) {
                            JsonObject object = wifiArray.get(j).getAsJsonObject();

                            preparedStatement.setString(1, object.get("X_SWIFI_MGR_NO").getAsString());
                            preparedStatement.setString(2, object.get("X_SWIFI_WRDOFC").getAsString());
                            preparedStatement.setString(3, object.get("X_SWIFI_MAIN_NM").getAsString());
                            preparedStatement.setString(4, object.get("X_SWIFI_ADRES1").getAsString());
                            preparedStatement.setString(5, object.get("X_SWIFI_ADRES2").getAsString());
                            preparedStatement.setString(6, object.get("X_SWIFI_INSTL_FLOOR").getAsString());
                            preparedStatement.setString(7, object.get("X_SWIFI_INSTL_TY").getAsString());
                            preparedStatement.setString(8, object.get("X_SWIFI_INSTL_MBY").getAsString());
                            preparedStatement.setString(9, object.get("X_SWIFI_SVC_SE").getAsString());
                            preparedStatement.setString(10, object.get("X_SWIFI_CMCWR").getAsString());
                            preparedStatement.setString(11, object.get("X_SWIFI_CNSTC_YEAR").getAsString());
                            preparedStatement.setString(12, object.get("X_SWIFI_INOUT_DOOR").getAsString());
                            preparedStatement.setString(13, object.get("X_SWIFI_REMARS3").getAsString());
                            preparedStatement.setString(14, object.get("LNT").getAsString());
                            preparedStatement.setString(15, object.get("LAT").getAsString());
                            preparedStatement.setString(16, object.get("WORK_DTTM").getAsString());
                            preparedStatement.addBatch();
                        }

                        preparedStatement.executeBatch();
                        con.commit();
                    }
                    startIndex += BATCH_SIZE;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return count;
    }
}