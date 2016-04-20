package org.ezdevgroup.ezframework.support.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils extends org.apache.commons.lang.time.DateUtils {

	public static final int KOR_WEEK = 1;

	public static final int ENG_WEEK = 2;


	/**
	 * 날짜 포맷을 반환  <br>
	 * ex) 20130806
	 * @param fmt 년도 yyyy(소문자) <br> 월 MM(대문자 ) <br> 일 dd(소문자) <br> 시분초 HHmmss
	 * @return 포맷에 맞추어 날짜 반환
	 */
	public static String getDateFormat(String fmt) {
		return new SimpleDateFormat(fmt).format(Calendar.getInstance().getTime());
	}

	/**
	 * 무슨요일인지 반환
	 * @param day null이면 현재날짜 요일을 반환
	 * @param locale  DateUtils.KOR_WEEK -> 한글반환 <br/>
	 *                DateUtils.ENG_WEEK -> 영문반환 <br/>
	 * @return 요일반환
	 * @author ljc727 2014. 7. 21.
	 */
	public static String getDayOfWeek(Calendar day, int locale) {
		if (day == null) day = Calendar.getInstance();

		String[] week = null;
		if (locale == KOR_WEEK) {
			week = new String[]{ "일", "월", "화", "수", "목", "금", "토" };
		} else if (locale == ENG_WEEK) {
			week = new String[]{ "SUN", "MON", "TUE", "WED", "THU", "FRI", "SAT" };
		}
		return week[day.get(Calendar.DAY_OF_WEEK) - 1];
	}

	/**
	 * 현재 시간 요일 반환 국문스타일
	 * @return
	 * @auther ddakker 2014. 7. 24.
	 */
	public static String getDayOfWeekKor() {
		return getDayOfWeek(Calendar.getInstance(), DateUtils.KOR_WEEK);
	}

	/**
	 * 현재 시간 요일 반환 영문 스타일
	 * @return
	 * @auther ddakker 2014. 7. 24.
	 */
	public static String getDayOfWeekEng() {
		return getDayOfWeek(Calendar.getInstance(), DateUtils.ENG_WEEK);
	}

	/**
	 * 날짜 표현식을 변경 함. <br />
	 * @param date 형식은 yyyyMMdd, yyyyMMddHHmm, yyyyMMddHHmmss로만 가능함. 형식이 다르면 Exception발생.
	 * @param regex yyyy-MM-dd, yyyy.MM.dd, yyyy MM dd, yyyy-MM-dd HH:mm:ss 등
	 * @return 20140808 -> 2014-08-08 (regex yyyy-MM-dd)로 리턴
	 * @author ljc727 2014. 8. 8.
	 */
	public static String toDateString(String date, String regex) {
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

		return toDateString(cal.getTime(), regex);
	}

	public static String toDateString(Date date, String regex) {
		return new SimpleDateFormat(regex).format(date);
	}

	public static String toDateString(String date) {
		return toDateString(date, "yyyy-MM-dd HH:mm:ss");
	}

	public static String toDateString() {
		return toDateString(new Date(), "yyyy-MM-dd HH:mm:ss");
	}

	public static String toDateStringYMD() {
		return toDateString(new Date(), "yyyyMMdd");
	}

	public static String toDateStringYMDHMS() {
		return toDateString(new Date(), "yyyyMMddHHmmss");
	}

	/**
	 * 두 날짜의 일수 차이
	 * @param curr		날짜형
	 * @param next		날짜형
	 * @param format	포멧(yyyyMMddHHmmss 활용)
	 * @return
	 * @throws ParseException
	 * @auther ddakker 2014. 9. 1.
	 */
	public static long getDiffDays(Date curr, Date next, String format) {
		long diff = curr.getTime() - next.getTime();

        return diff / (24 * 60 * 60 * 1000);
	}

	/**
	 * 두 날짜의 일수 차이
	 * @param curr		문자형
	 * @param next		문자형
	 * @param format	포멧(yyyyMMddHHmmss 활용)
	 * @return
	 * @throws ParseException
	 * @auther ddakker 2014. 9. 1.
	 */
	public static long getDiffDays(String curr, String next, String format) {
		try {
	        SimpleDateFormat formatter = new SimpleDateFormat(format);

	        Date currDate = formatter.parse(curr);
	        Date nextDate = formatter.parse(next);

	        return getDiffDays(currDate, nextDate, format);
		} catch (ParseException e) {
			return -1;
		}
	}
}
