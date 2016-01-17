package pe.kr.ddakker.ezframework.web.vo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

/**
 * 
 * ---------------------------------------------------------------------- 
 * 날짜 작업자 수정내역 
 * ---------------------------------------------------------------------- 
 * 2015. 12. 21. ljc727 신규생성
 */
public class HashEntity extends HashMap<String, Object> {
    private static final long serialVersionUID = 1L;
    
    public HashEntity() {
        this(1);
    }

    public HashEntity(int size) {
    	super(size);
    }

    
    /**
     * Entity Value를 찾아 먼저 클리어 시킨다.
     */
    public void clear() {
		Iterator<String> itr = this.keySet().iterator();
		while (itr.hasNext()) {
			Object key = itr.next();
			Object value = this.get(key);
			if (value instanceof ArrayList) {
				for (int i = 0; i < ((ArrayList<?>) value).size(); i++) {
					if (((ArrayList<?>) value).get(i) instanceof HashEntity) {
						((HashEntity) ((ArrayList<?>) value).get(i)).clear();
					}
				}
			} else if (value instanceof HashEntity) {
				((HashEntity) value).clear();
			}
		}
		super.clear();
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
    
    
}
