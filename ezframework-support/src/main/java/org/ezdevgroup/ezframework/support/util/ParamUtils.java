package org.ezdevgroup.ezframework.support.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;


public class ParamUtils {
	/**
	 * Controller 로 넘어온 @RequestParam <String, String> -> <String, Object> 로 변환한다.
	 * @param params
	 * @return
	 * @auther ddakker 2016. 1. 3.
	 */
	public static Map<String, Object> getObjectMap(Map<String, String> params) {
		Map<String, Object> objectMap = new HashMap<>();
		
		for (Entry<String, String> entry : params.entrySet()) {
			objectMap.put(entry.getKey(), entry.getValue());
		}
		return objectMap;
	}

	/*public static Map<String, Object> getHashMap(MultiValueMap<String, String> multiValueMap) {
		for (Entry<String, List<String>> entry : multiValueMap.entrySet()) {
			List<String> valueList = entry.getValue();
			//params.put(entry.getKey(), entry.getKey().equals("orderStatuses")?valueList:valueList.get(0));
		}
		multiValueMap.get
		return null;
	}*/
}
