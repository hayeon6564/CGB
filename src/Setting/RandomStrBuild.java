package Setting;

import java.util.ArrayList;
import java.util.Random;

public class RandomStrBuild {
     
	//생성할 랜덤문자열에 들어갈 문자들 저장(숫자, 소문자, 대문자)
    public static final String NUMBER = "0123456789";
    public static final String ALPHABET_LOWER = "abcdefghijkmnlopqrstuvwxyz";
    public static final String ALPHABET_UPPER = "ABCDEFGHIJKMNLOPQRSTUVWXYZ";
    public static final String ALPHABET = ALPHABET_LOWER + ALPHABET_UPPER;
     
    private ArrayList<Character> mLimitCharList = new ArrayList<Character>();
             
    int mLength = 6;
     
    public RandomStrBuild setLength(int length) { //만들 글자수 설정, 반환
        mLength = length;
        return this;
    }
     
    public int getLength() { //글자수 가져오기
        return mLength;
    }
    
    //String을 쪼개서 한 글자씩 char에 저장후에 list에 저장
    public RandomStrBuild putLimitedChar(String limited) {
        putLimitedChar(limited.toCharArray());
        return this;
    }
     
    public RandomStrBuild putLimitedChar(char[] limitedList) {
        for(char limited : limitedList) 
                putLimitedChar(limited);
        return this;
    }
    
    public RandomStrBuild putLimitedChar(char limited) {
        mLimitCharList.add(limited);
        return this;
    }
    
    //해당 문자 리스트에서 삭제
    public boolean removeLimitedChar(char limited) {
        return mLimitCharList.remove((Character)limited);
    }
    
    //리스트 비우기
    public void clearLimited() {
        mLimitCharList.clear();
    }

    public String build() { //글자수 만큼 랜덤문자열 생성
        return generateRandomString(mLength);
    }
    
    public String generateRandomString(int length) {
        StringBuffer strBuffer = new StringBuffer(length); //문자열을 추가하거나 변경할 때 사용하는 자료형 StringBuffer
        Random rand = new Random(System.nanoTime()); //랜덤함수
        for(int i = 0; i < length; ++i ) { //생성하려는 글자 수 만큼
            char randomChar = makeChar(rand); //랜덤으로 char 값을 만들어서
            strBuffer.append(randomChar); //모두 덧붙임
        }
        return strBuffer.toString(); //하나의 문자열로 만듦
    }
     
    private char makeChar(Random rand) {
        if(mLimitCharList.isEmpty()) // 리스트 비어있으면 아스키코드 랜덤
            return (char)(rand.nextInt(94) + 33); // 0~93에서 33~126 - 아스키코드 특수문자,숫자,영문자,대문자
        else
            return mLimitCharList.get(rand.nextInt(mLimitCharList.size())); //리스트의 문자를 랜덤으로 반환
    }
}
