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
 
    // 상수 설정
    //   - 요청(Request) 요청 변수
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
 
    // API요청
    public void requestAPI() {
        // 변수 설정
        //   - 요청(Request) 인터페이스 Map
        //   - 어제자 다양성 한국영화 10개 조회
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("key"          , AUTH_KEY);                        // 발급받은 인증키
        paramMap.put("itemPerPage"  , "20");                            // 결과 ROW 의 개수( 최대 10개 )
        paramMap.put("openStartDt", "2015");							// 조회시작 개봉연도
        paramMap.put("openEndDt", "2019");								// 조회종료 개봉연도
        paramMap.put("repNationCd" , "22041011");    	                // 국적코드 한국
        paramMap.put("repNationCd" , "22042002");    	                // 국적코드 미국
        paramMap.put("movieTypeCd"  , "220101");                        // 영화유형코드 장편
 
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
            //System.out.println(response);
            
            // JSON 객체로  변환
            JSONObject responseBody = new JSONObject(response.toString());
 
            // 데이터 추출
            JSONObject movieResult = responseBody.getJSONObject("movieListResult");
            
            // 목록 출력
            JSONArray movieList = movieResult.getJSONArray("movieList");
            Iterator<Object> iter = movieList.iterator();
            while(iter.hasNext()) {
                JSONObject movie = (JSONObject) iter.next();
                if(movie.get("repGenreNm").equals("액션")) {
                	System.out.printf(" 영화코드 :  %s, 영화명 : %s, 대표장르 : %s\n", movie.get("movieCd"), movie.get("movieNm"), movie.get("repGenreNm"));
                }
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
 
    public static void main(String[] args) {
        // API 객체 생성
        MovieAPI api = new MovieAPI();
 
        // API 요청
        api.requestAPI();
    }
}