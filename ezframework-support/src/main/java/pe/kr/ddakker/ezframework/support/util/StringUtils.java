package pe.kr.ddakker.ezframework.support.util;

import java.text.DecimalFormat;

/**
 * Apache StringUtils의 직접 사용을 제한하고, 추가적으로 필요한 기능을 추가한다.
 *
 * @author ddakker 2014. 6. 11.
 *
 */
public class StringUtils extends org.apache.commons.lang.StringUtils {

	/**
	 * NULL 또는 빈값일때(공백포함)를 체크함
	 * @param value 체크할 String값
	 * @return Null 이나 빈값(공백포함)일때 true, 그외에 false
	 * @author ljc727 2014. 7. 14.
	 */
	public static boolean isNull(String value) {
		if (value == null) return true;

		String v = value.trim();
		if ("".equals(v)) return true;

		return false;
	}

	/**
	 * NULL이 아니거나 빈값이 아닐때(공백은 빈값처리)를 체크함
	 * @param value 체크할 String값
	 * @return Null이 아니거나 빈값이 아닐때 true, 그외에 false
	 * @author ljc727 2014. 7. 14.
	 */
	public static boolean isNotNull(String value) {
		if (value == null) return false;

		String v = value.trim();
		if ("".equals(v)) return false;

		return true;
	}

	/**
	 *
	 * @param v1
	 * @param v2
	 * @return
	 * @author ljc727 2014. 7. 14.
	 */
	public static boolean isEquals(String v1, String v2) {
		if (v1 == null || v2 == null) return false;
		if (v1.equals(v2)) return true;
		return false;
	}

	/**
	 *
	 * @param v1
	 * @param v2
	 * @return
	 * @author ljc727 2014. 7. 14.
	 */
	public static boolean isNotEquals(String v1, String v2) {
		if (v1 == null || v2 == null) return false;
		if (v1.equals(v2)) return false;
		return true;
	}

	/**
	 * 문자열이 길이를 제한하여 초과시 삭제하고, 뒤에 표현식을 붙인다.
	 * @param value 원본 문자열값
	 * @param exp 짤르고 뒤에 표시할 문자열 (ex : ...)
	 * @param limit 길이 제한
	 * @return 가공된 문자열
	 * @author ljc727 2014. 8. 7.
	 */
	public static String strCut(String value, String exp, int limit) {
		if (value.length() <= limit) return value;
		return value.substring(0, limit) + exp;
	}
	
	public static String camelToDBStyle(String value) {
		String regex = "([a-z])([A-Z])";
		String replacement = "$1_$2";
		return value.replaceAll(regex, replacement).toUpperCase();
	}
	
	/**
	 * 알파뱃 키값을 증가시킴
	 * 대문자만 가능
	 * @param key 알파뱃 키값
	 * @return 1개 증가된 키값 반환
	 */
	public static String getAlphabatIncrease(String key) {
		final int CHAR_Z_ASCII = 90;

		String orignStr = key;
		int orignLength = orignStr.length();
		char[] charArr = new char[orignStr.length()];

		for (int i = 0; i < orignLength; i++) {
			charArr[i] = orignStr.charAt(i);
		}

		for (int i = orignLength - 1; i >= 0; i--) {
			if (charArr[i] == CHAR_Z_ASCII) {
				charArr[i] = 'A';
			} else {
				char c = charArr[i];
				charArr[i] = (char) (c + 1);
				break;
			}
		}
		return new String(charArr);
	}

	public static String nvl(String oriValue, String nvlValue) {
		if (isNotNull(oriValue)) {
			return oriValue;
		}
		return nvlValue;
	}

	/**
	 * 100000 -> 1,000,000 으로 변환
	 * @param num 금액
	 * @return 한국돈 , 기준으로 리턴
	 */
	public static String moneyFormat(long money) {
		DecimalFormat df = new DecimalFormat("#,###");
		return df.format(money);
	}
	
	/**
	 * 오라클 LPAD 함수 역할
	 * 값 7을 007로 표현하고 싶으면, value에 7, lpadValue 000
	 * @param value
	 * @param lpadValue
	 * @return
	 */
	public static String lpadFormat(String value, String lpadValue) {
		DecimalFormat nf = new DecimalFormat(lpadValue);
		return nf.format(Integer.parseInt(value)); 
	}
	
	/**
	 * 글의 마지막 콤마를 제거
	 * 가,나,다,  ---> 가,나,다
	 * @param value
	 * @return 예외 발생시 빈값 리턴, null은 리턴안함
	 */
	public static String lastCommaRemove(String value) {
		try {
			if (value != null && value.length() > 0) {
				if (value.lastIndexOf(",") > 0) {
					return value.substring(0, value.length() - 1);
				}
			}			
		} catch (Exception e) {}
		return "";
	}

}
