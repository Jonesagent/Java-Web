package org.sg.sgg.string;

import java.io.UnsupportedEncodingException;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

public class StringUtils {

	private static final Logger logger = Logger.getLogger(StringUtils.class);

	/**
	 * 判断字符串是否为空
	 * 
	 * @param str
	 *            null、“ ”、“null”都返回true
	 * @return
	 */
	public static boolean isNullString(String str) {
		return (null == str
				|| org.apache.commons.lang.StringUtils.isBlank(str.trim())
				|| "null".equals(str.trim().toLowerCase()) ? true : false);
	}

	/**
	 * 格式化字符串 如果为null，返回“”
	 * 
	 * @param str
	 * @return
	 */
	public static String formatString(String str) {
		if (isNullString(str)) {
			return "";
		} else {
			return str;
		}
	}

	/**
	 * 截取字符串，字母、汉字都可以，汉字不会截取半
	 * 
	 * @param str
	 *            - 字符串
	 * @param n
	 *            - 截取的长度，字母数，如果为汉字，一个汉字等于两个字母数
	 * @return
	 */
	public static String subStringByByte(String str, int n) {
		int num = 0;

		try {
			byte[] buf = str.getBytes("GBK");
			if (n >= buf.length) {
				return str;
			}
			boolean bChineseFirstHalf = false;
			for (int i = 0; i < n; i++) {
				if (buf[i] < 0 && !bChineseFirstHalf) {
					bChineseFirstHalf = true;
				} else {
					num++;
					bChineseFirstHalf = false;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return str.substring(0, num);
	}

	/**
	 * 判断一个字符串是否是一个 Integer
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isInteger(String str) {
		Pattern pattern = Pattern.compile("^[-\\+]?[\\d]+$");
		return pattern.matcher(str).matches();
	}

	/**
	 * 判断一个字符串是否是一个 Double
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isDouble(String str) {
		Pattern pattern = Pattern.compile("^[-\\+]?\\d+\\.\\d+$");
		return pattern.matcher(str).matches();
	}
	/**
	 * 判断一个字符串是否是由字母 组成
	 * @param str
	 * @return
	 */
	public static boolean isLetter(String str) {
		if (str == null || str.length() < 0) {
			return false;
		}
		Pattern pattern = Pattern.compile("[\\w\\.-_]*");
		return pattern.matcher(str).matches();
	}
	
}
