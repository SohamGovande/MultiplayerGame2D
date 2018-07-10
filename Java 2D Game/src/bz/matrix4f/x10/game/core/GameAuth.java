package bz.matrix4f.x10.game.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class GameAuth {
	
	public static String login(String username, String password) {
//		return sendPostRequest("http://matrix4f.x10.bz/mulgame/function.php?func=login", "username=" + username + "&password=" + password);
		return "{\"session_id\":\"none\"}";
	}
	
	public static String checkValidity(String sessionid) {
//		return sendPostRequest("http://matrix4f.x10.bz/mulgame/function.php?func=serverauth", "sessionid=" + sessionid);
		return "true";
	}
	
	public static String sendPostRequest(String url, String params) {
		try {
			URL obj = new URL(url);
			HttpURLConnection conn = (HttpURLConnection) obj.openConnection();
			conn.setRequestMethod("POST");
			conn.setRequestProperty("User-Agent", "Mozilla/5.0");
	        conn.setDoOutput(true);
	        OutputStream os = conn.getOutputStream();
	        os.write(params.getBytes());
	        os.flush();
	        os.close();
	        int responseCode = conn.getResponseCode();
	        if (responseCode == HttpURLConnection.HTTP_OK) {
	            BufferedReader in = new BufferedReader(new InputStreamReader(
	                    conn.getInputStream()));
	            String inputLine;
	            StringBuffer response = new StringBuffer();
	            while ((inputLine = in.readLine()) != null) {
	                response.append(inputLine);
	            }
	            in.close();
	            return response.toString();
	        }
		} catch(IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
