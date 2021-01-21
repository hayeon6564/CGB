package Setting;

import java.util.ArrayList;
import java.util.Random;

public class RandomStrBuild {
     
	//������ �������ڿ��� �� ���ڵ� ����(����, �ҹ���, �빮��)
    public static final String NUMBER = "0123456789";
    public static final String ALPHABET_LOWER = "abcdefghijkmnlopqrstuvwxyz";
    public static final String ALPHABET_UPPER = "ABCDEFGHIJKMNLOPQRSTUVWXYZ";
    public static final String ALPHABET = ALPHABET_LOWER + ALPHABET_UPPER;
     
    private ArrayList<Character> mLimitCharList = new ArrayList<Character>();
             
    int mLength = 6;
     
    public RandomStrBuild setLength(int length) { //���� ���ڼ� ����, ��ȯ
        mLength = length;
        return this;
    }
     
    public int getLength() { //���ڼ� ��������
        return mLength;
    }
    
    //String�� �ɰ��� �� ���ھ� char�� �����Ŀ� list�� ����
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
    
    //�ش� ���� ����Ʈ���� ����
    public boolean removeLimitedChar(char limited) {
        return mLimitCharList.remove((Character)limited);
    }
    
    //����Ʈ ����
    public void clearLimited() {
        mLimitCharList.clear();
    }

    public String build() { //���ڼ� ��ŭ �������ڿ� ����
        return generateRandomString(mLength);
    }
    
    public String generateRandomString(int length) {
        StringBuffer strBuffer = new StringBuffer(length); //���ڿ��� �߰��ϰų� ������ �� ����ϴ� �ڷ��� StringBuffer
        Random rand = new Random(System.nanoTime()); //�����Լ�
        for(int i = 0; i < length; ++i ) { //�����Ϸ��� ���� �� ��ŭ
            char randomChar = makeChar(rand); //�������� char ���� ����
            strBuffer.append(randomChar); //��� ������
        }
        return strBuffer.toString(); //�ϳ��� ���ڿ��� ����
    }
     
    private char makeChar(Random rand) {
        if(mLimitCharList.isEmpty()) // ����Ʈ ��������� �ƽ�Ű�ڵ� ����
            return (char)(rand.nextInt(94) + 33); // 0~93���� 33~126 - �ƽ�Ű�ڵ� Ư������,����,������,�빮��
        else
            return mLimitCharList.get(rand.nextInt(mLimitCharList.size())); //����Ʈ�� ���ڸ� �������� ��ȯ
    }
}
