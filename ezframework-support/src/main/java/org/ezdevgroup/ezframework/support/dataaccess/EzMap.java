package org.ezdevgroup.ezframework.support.dataaccess;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.collections.map.ListOrderedMap;
import org.ezdevgroup.ezframework.support.util.FormatUtils;

/**
 *
 * ----------------------------------------------------------------------
 * 날짜 작업자 수정내역
 * ----------------------------------------------------------------------
 * 2015. 12. 21. ljc727 신규생성
 * 2014. 04. 20. ddakker ListOrderedMap 으로 수정
 * 					- https://github.com/eGovFrame/egovframework.rte.root/blob/master/Persistence/egovframework.rte.psl.dataaccess/src/main/java/egovframework/rte/psl/dataaccess/util/EgovMap.java
 */
public class EzMap extends ListOrderedMap {
    private static final long serialVersionUID = 1L;


    /**
     * 일반적인 데이터 put 시 Camel 변환되는것을 막기 위해..
     * 		- 일반적으로 key값을 String으로 하니...
     * @param key
     * @param value
     * @return
     * @auther ddakker 2016. 4. 20.
     */
    public Object put(String key, Object value) {
    	return super.put(key, value);
    }

    /**
     * Key 값을 Camel 형태로 변환하여 저장한다.
     * 		- MyBatis 측에서는 (Object, Object) 로 들어 온다.
     * @param key
     * @param value
     * @return
     * @auther ddakker 2015. 12. 15.
     * 			https://github.com/eGovFrame/egovframework.rte.root/blob/master/Persistence/egovframework.rte.psl.dataaccess/src/main/java/egovframework/rte/psl/dataaccess/util/CamelUtil.java
     */
    @Override
    public Object put(Object key, Object value) {
    	String underScore = (String) key;

    	StringBuilder result = new StringBuilder();

    	// '_' 가 나타나지 않으면 이미 camel case 로 가정함.
        // 단 첫째문자가 대문자이면 camel case 변환 (전체를 소문자로) 처리가
        // 필요하다고 가정함. --> 아래 로직을 수행하면 바뀜
        if (underScore.indexOf('_') < 0 && Character.isLowerCase(underScore.charAt(0))) {
        	result.append(underScore);
        } else {
	        boolean nextUpper = false;
	        int len = underScore.length();

	        for (int i = 0; i < len; i++) {
	            char currentChar = underScore.charAt(i);
	            if (currentChar == '_') {
	                nextUpper = true;
	            } else {
	                if (nextUpper) {
	                    result.append(Character.toUpperCase(currentChar));
	                    nextUpper = false;
	                } else {
	                    result.append(Character.toLowerCase(currentChar));
	                }
	            }
	        }
        }
    	return super.put(result.toString(), value);
    }


    /**
     * value를 스트링 객체로 변환하여 반환한다.
     */
    public String getString(String key) {
    	Object value = get(key);
    	if (value == null) value = "";
		return (value instanceof String)?(String) value:String.valueOf(value);
    }


    /**
     * value를 int로 변환하여 변환한다.
     */
    public int getInteger(String key) {
    	Object value = get(key);
    	return (value instanceof Integer)?(Integer) value:Integer.parseInt(String.valueOf(value));
    }


    /**
     * value를 long로 변환하여 변환한다.
     */
    public long getLong(String key) {
    	Object value = get(key);
    	return (value instanceof Long)?(Long) value:Long.parseLong(String.valueOf(value));
    }

    /**
     * value를 float로 변환하여 변환한다.
     */
    public float getFloat(String key) {
    	Object value = get(key);
    	return (value instanceof Float)?(Float) value:Float.parseFloat(String.valueOf(value));
    }

    /**
     * value를 dulble로 변환하여 변환한다.
     */
    public double getDouble(String key) {
    	Object value = get(key);
    	return (value instanceof Double)?(Double) value:Double.parseDouble(String.valueOf(value));
    }


    /**
     * 복수 필드  SUM
     * @param keys
     * @return
     * @auther ddakker 2015. 12. 30.
     */
    public int getSum(String ... keys) {
    	int sum = 0;
    	if (keys != null) {
    		for (String key : keys) {
    			sum += getInteger(key);
    		}
    	}
    	return sum;
    }


    /**
     * 날짜 형식
     * @param key
     * @param regex yyyy-MM-dd
     * @return
     */
    public String getDate(String key, String regex) {
    	try {
    		Object obj = get(key);
    		if (obj != null) {
    			if (obj instanceof String) {
    				String date = getString(key);
    				Calendar cal = Calendar.getInstance();

    				cal.set(Calendar.YEAR, Integer.parseInt(date.substring(0,  4)));
    				cal.set(Calendar.MONTH, Integer.parseInt(date.substring(4, 6)) - 1);
    				cal.set(Calendar.DATE, Integer.parseInt(date.substring(6, 8)));

    				if (date.length() >= 12) {
    					cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(date.substring(8, 10)));
    					cal.set(Calendar.MINUTE, Integer.parseInt(date.substring(10, 12)));
    				}
    				if (date.length() >= 14) {
    					cal.set(Calendar.MILLISECOND, Integer.parseInt(date.substring(12, 14)));
    				}

    				return new SimpleDateFormat(regex).format(cal.getTime());
    			} else if (obj instanceof Date) {
    				return new SimpleDateFormat(regex).format((Date) obj);
    			} else {
    				return "";
    			}
    		} else {
    			return "";
    		}
    	} catch (Exception e) {
    		return "";
    	}
    }

    /**
     * 날짜 형식
     * @param key
     * @return
     */
    public String getDate(String key) {
    	return getDate(key, "yyyy-MM-dd");
    }

    /**
     * 숫자 단위 구분처리
     * @param value
     * @return
     * @auther ddakker 2015. 12. 15.
     */
    public String getNumberDot(String value) {
    	String val = getString(value);
		return FormatUtils.toAmount(val);
    }

    /**
     * 숫자 단위 구분처리
     * @param value
     * @return
     * @auther ddakker 2015. 12. 15.
     */
    public String getNumberDot(Integer value) {
    	return getNumberDot(String.valueOf(value));
    }

    /**
     * 전화번호 구분자 추가
     * @param key
     * @return
     * @auther ddakker 2015. 12. 15.
     */
    public String getPhone(String value) {
    	return getPhone(value, "-");
    }

    /**
     * 전화번호 구분자 추가
     * @param key
     * @return
     * @auther ddakker 2015. 12. 15.
     */
    public String getPhone(String value, String delim) {
    	String val = getString(value);
		return FormatUtils.convertorPhone(val, delim);
    }
}