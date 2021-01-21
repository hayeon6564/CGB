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
 
    // 상수 설정
    //   - 요청(Request) 요청 변수
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
 
    // API요청
    public void requestAPI() {
        // 변수 설정
        //   - 요청(Request) 인터페이스 Map
        //   - 어제자 다양성 한국영화 10개 조회
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("key"          , AUTH_KEY);                        // 발급받은 인증키
        //paramMap.put("comCode", "2204");								//국적 코드 조회
        paramMap.put("comCode", "2201");								//영화 유형 코드 조회
        
 
        try {
            // Request URL 연결 객체 생성
            URL requestURL = new URL(REQUEST_URL+"?"+makeQueryString(paramMap));
            HttpURLConnection conn = (HttpURLConnection) requestURL.openConnection();
 
            // GET 방식으로 요청
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
 
            // 응답(Response) 구조 작성
            //   - Stream -> JSONObject
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            String readline = null;
            StringBuffer response = new StringBuffer();
            while ((readline = br.readLine()) != null) {
                response.append(readline);
            }
 
            // JSON 객체로  변환
            //JSONObject responseBody = new JSONObject(response.toString());
 
            // 데이터 추출
            //JSONObject boxOfficeResult = responseBody.getJSONObject("boxOfficeResult");
 
            // 박스오피스 주제 출력
            //String boxofficeType = boxOfficeResult.getString("boxofficeType");
            System.out.println(response);
 
            // 박스오피스 목록 출력
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
        // API 객체 생성
        SearchCodeAPI api = new SearchCodeAPI();
 
        // API 요청
        api.requestAPI();
    }
}