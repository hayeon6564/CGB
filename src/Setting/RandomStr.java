package Setting;

public class RandomStr {
	private String randStr;

	public RandomStr() {
		// ���ĺ����� �̷���� ���� 6�� ���� ���ڿ� ����.
		randStr =  new RandomStrBuild().
				putLimitedChar(RandomStrBuild.ALPHABET + RandomStrBuild.NUMBER). //���ĺ��� ���ڷ� �������ڿ� ����
				setLength(6).build(); //6���� ����
		
	}
	
	public String getStr() {
		return randStr; //������ �������ڿ� 6���� ��ȯ
	}
	
}

