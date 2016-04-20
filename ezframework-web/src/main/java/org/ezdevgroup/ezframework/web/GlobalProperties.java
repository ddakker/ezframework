package org.ezdevgroup.ezframework.web;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Properties;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

/**
 * 편리한 Properties - Properties 클래스를 상속하여 Properties에서 문자열,숫자,boolean 등의 값을 직접 뽑는다
 *
 * @author ddakker 2014. 6. 10.
 *
 */
public class GlobalProperties extends Properties {
	private Logger log = LoggerFactory.getLogger(GlobalProperties.class);

	private static Pattern SYSTEM_PROPERTY_NAME_PATTERN = Pattern.compile("\\$\\{([\\w\\.]+)\\}");

	public static final String REAL 	= "real";
	public static final String STAGE 	= "stage";
	public static final String DEV 		= "dev";
	public static final String LOCAL 	= "local";


	public GlobalProperties(Properties properties) {
		super(properties);
		Assert.notNull(properties, "properties should not be null");
		processSystemProperties();
		logProperties();
	}

	private void processSystemProperties() {
		for (String key : stringPropertyNames()) {
			String value = getProperty(key);

			if (System.getProperty(key) == null) {
				System.setProperty(key, value);
			} else {
				log.warn("System Properties Key 존재 {}={}", key, System.getProperty(key));
			}
		}
	}

	public int getInt(String key) {
		return Integer.parseInt(getProperty(key));
	}

	public int getInt(String key, int defaultValue) {
		String value = getProperty(key);
		if (value == null) {
			return defaultValue;
		}
		return Integer.parseInt(value);
	}

	public long getLong(String key) {
		return Long.parseLong(getProperty(key));
	}

	public long getLong(String key, long defaultValue) {
		String value = getProperty(key);

		if (value == null) {
			return defaultValue;
		}
		return Long.parseLong(value);
	}

	public double getDouble(String key) {
		return Double.parseDouble(getProperty(key));
	}

	public double getDouble(String key, double defaultValue) {
		String value = getProperty(key);

		if (value == null) {
			return defaultValue;
		}

		return Double.parseDouble(value);
	}

	public boolean getBoolean(String key) {
		return Boolean.valueOf(getProperty(key));
	}

	public void logProperties() {
		if (log.isDebugEnabled()) {
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			this.list(pw);

			log.debug("Convinient Properties loaded : {}", sw.toString());
			pw.close();
		}
	}
}