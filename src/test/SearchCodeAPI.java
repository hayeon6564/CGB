package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

 
public class SearchCodeAPI {
 
    // ��� ����
    //   - ��û(Request) ��û ����
    private final String REQUEST_URL = "http://www.kobis.or.kr/kobisopenapi/webservice/rest/code/searchCodeList.json";
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
        //paramMap.put("comCode", "2204");								//���� �ڵ� ��ȸ
        paramMap.put("comCode", "2201");								//��ȭ ���� �ڵ� ��ȸ
        
 
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
 
            // JSON ��ü��  ��ȯ
            //JSONObject responseBody = new JSONObject(response.toString());
 
            // ������ ����
            //JSONObject boxOfficeResult = responseBody.getJSONObject("boxOfficeResult");
 
            // �ڽ����ǽ� ���� ���
            //String boxofficeType = boxOfficeResult.getString("boxofficeType");
            System.out.println(response);
 
            // �ڽ����ǽ� ��� ���
            //JSONArray dailyBoxOfficeList = boxOfficeResult.getJSONArray("dailyBoxOfficeList");
            //Iterator<Object> iter = dailyBoxOfficeList.iterator();
            //while(iter.hasNext()) {
            //    JSONObject boxOffice = (JSONObject) iter.next();
            //    System.out.printf("  %s - %s\n", boxOffice.get("rnum"), boxOffice.get("movieNm"));
            //}
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
 
    public static void main(String[] args) {
        // API ��ü ����
        SearchCodeAPI api = new SearchCodeAPI();
 
        // API ��û
        api.requestAPI();
    }
}