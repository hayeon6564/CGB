package Setting;

public class RandomStr {
	private String randStr;

	public RandomStr() {
		// 알파벳으로 이루어진 길의 6의 랜덤 문자열 생성.
		randStr =  new RandomStrBuild().
				putLimitedChar(RandomStrBuild.ALPHABET + RandomStrBuild.NUMBER). //알파벳과 숫자로 랜덤문자열 생성
				setLength(6).build(); //6글자 생성
		
	}
	
	public String getStr() {
		return randStr; //생성돤 랜덤문자열 6글자 반환
	}
	
}

