package org.ezdevgroup.ezframework.support.util;

import java.text.DecimalFormat;
import java.util.regex.Pattern;

import org.apache.commons.lang.math.NumberUtils;


public class FormatUtils {

	/**
	 * 금액 단위당 콤마 붙이기
	 * @param value
	 * @return
	 * @auther ddakker 2015. 12. 15.
	 */
	public static String toAmount(String value) {
		if (value.contains(",")) 	value = value.replaceAll(",", "");

        DecimalFormat df = new DecimalFormat("#,##0");
        return df.format(NumberUtils.toLong(StringUtils.trimToEmpty(value), 0L));
    }

	/**
     * 전화번호를 자리수에 맞게 분리하고, 구분자를 붙인다.
     * @param phoneNumber	전화번호
     * @param delim	구분자
     * @return
     * @auther ddakker 2015. 12. 15.
     */
    public static String convertorPhone(String phoneNumber) {
    	return convertorPhone(phoneNumber, "-");
    }

    /**
     * 전화번호를 자리수에 맞게 분리하고, 구분자를 붙인다.
     * @param phoneNumber
     * @param delim
     * @return
     * @auther ddakker 2015. 12. 15.
     */
	public static String convertorPhone(String phoneNumber, String delim) {
		if (StringUtils.isNotEmpty(phoneNumber)) {
			if (phoneNumber.contains("-")) 		phoneNumber = phoneNumber.replaceAll("-", "");
	    	if (phoneNumber.contains(delim)) 	phoneNumber = phoneNumber.replaceAll(delim, "");

			String regEx = "(02)(\\d{3,4})(\\d{4})";

			if (Pattern.matches(regEx, phoneNumber)) {
				return phoneNumber.replaceAll(regEx, "$1-$2-$3");
			} else {
				regEx = "(\\d{3})(\\d{3,4})(\\d{4})";
				if (Pattern.matches(regEx, phoneNumber)) {
					return phoneNumber.replaceAll(regEx, "$1-$2-$3");
				} else {
					return "";
				}
			}
		} else {
			return "";
		}

	}
}
