package mission2prj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.*;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class totalCount {
		public Integer total() throws IOException{
			int startIndex = 1;
			int totalCount = 0;
		
				StringBuilder urlBuilder = new StringBuilder("http://openapi.seoul.go.kr:8088"); /* URL */
				urlBuilder.append("/" + URLEncoder.encode("486e796367776e643436694c704c57", "UTF-8")); /* 인증키 (sample사용시에는 호출시 제한됩니다.) */
				urlBuilder.append("/" + URLEncoder.encode("json", "UTF-8")); /* 요청파일타입 (xml,xmlf,xls,json) */
				urlBuilder.append("/" + URLEncoder.encode("TbPublicWifiInfo", "UTF-8")); /* 서비스명 (대소문자 구분 필수입니다.) */
				urlBuilder.append("/" + URLEncoder.encode(String.valueOf(startIndex), "UTF-8")); /* 요청시작위치 (sample인증키 사용시 5이내 숫자) */
				urlBuilder.append("/" + URLEncoder.encode(String.valueOf(startIndex),"UTF-8")); 
				
				URL url = new URL(urlBuilder.toString());
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				conn.setRequestMethod("GET");
				conn.setRequestProperty("Content-type", "application/xml");
				BufferedReader rd;

				if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
					rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
				} else {
					rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
				}
				StringBuilder sb = new StringBuilder();
				String line;
				while ((line = rd.readLine()) != null) {
					sb.append(line);
				}
				rd.close();
				conn.disconnect();

				String publicWifi = sb.toString();
				JsonParser Parser = new JsonParser();
				JsonObject jsonObject = (JsonObject) Parser.parse(publicWifi);
				JsonObject tbPublicWifiInfo = jsonObject.getAsJsonObject("TbPublicWifiInfo");
				totalCount = tbPublicWifiInfo.get("list_total_count").getAsInt();
				
				return totalCount;
		}
	}
