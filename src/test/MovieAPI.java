package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
 
public class MovieAPI {
 
    // ��� ����
    //   - ��û(Request) ��û ����
    private final String REQUEST_URL = "http://www.kobis.or.kr/kobisopenapi/webservice/rest/movie/searchMovieList.json";
    private final String AUTH_KEY = "a72db72d7857cfff21838f2e711d0e80";
 
    // Map -> QueryString
    public String makeQueryString(Map<String, String> paramMap) {
        final StringBuilder sb = new StringBuilder();
 
        paramMap.entrySet().forEach(( entry )->{
            if( sb.length() > 0 ) {
                sb.append('&');
            }
            sb.append(entry.getKey()).append('=').append(entry.getValue());
        });
 
        return sb.toString();
    }
 
    // API��û
    public void requestAPI() {
        // ���� ����
        //   - ��û(Request) �������̽� Map
        //   - ������ �پ缺 �ѱ���ȭ 10�� ��ȸ
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("key"          , AUTH_KEY);                        // �߱޹��� ����Ű
        paramMap.put("itemPerPage"  , "20");                            // ��� ROW �� ����( �ִ� 10�� )
        paramMap.put("openStartDt", "2015");							// ��ȸ���� ��������
        paramMap.put("openEndDt", "2019");								// ��ȸ���� ��������
        paramMap.put("repNationCd" , "22041011");    	                // �����ڵ� �ѱ�
        paramMap.put("repNationCd" , "22042002");    	                // �����ڵ� �̱�
        paramMap.put("movieTypeCd"  , "220101");                        // ��ȭ�����ڵ� ����
 
        try {
            // Request URL ���� ��ü ����
            URL requestURL = new URL(REQUEST_URL+"?"+makeQueryString(paramMap));
            HttpURLConnection conn = (HttpURLConnection) requestURL.openConnection();
 
            // GET ������� ��û
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
 
            // ����(Response) ���� �ۼ�
            //   - Stream -> JSONObject
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            String readline = null;
            StringBuffer response = new StringBuffer();
            while ((readline = br.readLine()) != null) {
                response.append(readline);
            }
            //System.out.println(response);
            
            // JSON ��ü��  ��ȯ
            JSONObject responseBody = new JSONObject(response.toString());
 
            // ������ ����
            JSONObject movieResult = responseBody.getJSONObject("movieListResult");
            
            // ��� ���
            JSONArray movieList = movieResult.getJSONArray("movieList");
            Iterator<Object> iter = movieList.iterator();
            while(iter.hasNext()) {
                JSONObject movie = (JSONObject) iter.next();
                if(movie.get("repGenreNm").equals("�׼�")) {
                	System.out.printf(" ��ȭ�ڵ� :  %s, ��ȭ�� : %s, ��ǥ�帣 : %s\n", movie.get("movieCd"), movie.get("movieNm"), movie.get("repGenreNm"));
                }
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
 
    public static void main(String[] args) {
        // API ��ü ����
        MovieAPI api = new MovieAPI();
 
        // API ��û
        api.requestAPI();
    }
}