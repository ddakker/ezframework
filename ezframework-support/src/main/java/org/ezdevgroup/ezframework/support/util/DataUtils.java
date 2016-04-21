package org.ezdevgroup.ezframework.support.util;

import java.lang.reflect.Array;
import java.util.List;
import java.util.Map;

/**
 * 데이터 체크 등 처리
 * @author ddakker 2016. 4. 19.
 */
public class DataUtils {
	public static boolean isNull(Object obj) {
		if (obj == null)
			return true;

		if (obj instanceof String) {
			return ((String) obj).equals("");
		} else if (obj instanceof List) {
			return ((List) obj).isEmpty();
		} else if (obj instanceof Map) {
			return ((Map) obj).isEmpty();
		} else if (obj instanceof Object[]) {
			return Array.getLength(obj) == 0;
		} else {
			return obj == null;
		}
	}

	public static boolean isNotNull(Object obj) {
		if (obj == null)
			return false;

		if (obj instanceof String) {
			return !((String) obj).equals("");
		} else if (obj instanceof List) {
			return !((List) obj).isEmpty();
		} else if (obj instanceof Map) {
			return !((Map) obj).isEmpty();
		} else if (obj instanceof Object[]) {
			return Array.getLength(obj) != 0;
		} else {
			return obj != null;
		}
	}
}
