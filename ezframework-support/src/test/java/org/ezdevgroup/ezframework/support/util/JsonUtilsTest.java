package org.ezdevgroup.ezframework.support.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.junit.Test;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class JsonUtilsTest {

	@Test
	public void jsonTest() {
		try {
			ObjectMapper om = new ObjectMapper();

			List<String> list = new ArrayList<String>();
			list.add("a");
			list.add("b");
			list.add("c");

			Map<String, Object> oMap = new HashMap<String, Object>();
			oMap.put("list", list);
			oMap.put("a", "1");

			String jsonStr = om.writeValueAsString(oMap);
			System.out.println("object to json : " + jsonStr);

			Map<String, Object> m = om.readValue(jsonStr, new TypeReference<Map<String, Object>>() {});
			System.out.println("json to object : " + m);

			assertThat((String) m.get("a"), is("1"));


			Map<String, String> sMap = new HashMap<>();
			sMap.put("a", "1");
			sMap.put("b", "2");

			jsonStr = om.writeValueAsString(sMap);
			Map<String, String> rsMap = om.readValue(jsonStr, new TypeReference<Map<String, Object>>() {});
			System.out.println("rsMap: " + rsMap);

			assertThat(rsMap.get("b"), is("2"));

		} catch (IOException e) {
			System.err.println(e);
		}

	}
}
