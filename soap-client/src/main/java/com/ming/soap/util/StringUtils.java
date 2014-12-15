package com.ming.soap.util;

import java.util.Locale;

public class StringUtils {
	/**
	 * 判断字符串是否是空字符串
	 * 空返回true，非空返回false
	 * @param value 待判定的字符串
	 * @author ming
	 * */
	public static boolean isEmptyTrim (String value) {
		if (value == null || value.trim().length() == 0) {
			return true;
		} else {
			return false;
		}
	}
	/**
	 *使用给定 Locale 的规则将此 String 中的首字母转换为大写。
	 * @author jianggm
	 * @param value 待转换的字符串
	 * @param locale 使用此语言环境转换规则 
	 * */
	public static String firstCharToUpperCase (String value, Locale locale) {
		StringBuilder sb = new StringBuilder();
		sb.append(value.substring(0, 1).toUpperCase(locale)).append(value.substring(1, value.length()));
		return sb.toString();
	}
	/**
	 * 使用默认语言环境的规则将此 String 中的首字母转换为大写。
	 * @author jianggm
	 * @param value 待转换的字符串
	 * */
	public static String firstCharToUpperCase (String value) {
		StringBuilder sb = new StringBuilder();
		sb.append(value.substring(0, 1).toUpperCase()).append(value.substring(1, value.length()));
		return sb.toString();
	}
	/**
	 * 根据clzz，value，将value转换成对应clzz类型的值，
	 * 只限 基本类型
	 * @author jianggm
	 * @param value 需要转换的值
	 * @param clzz	
	 * */
	public static Object parseValueForClass (String value, Class<?> clzz) {
		if (clzz.equals(String.class)) {
			return value;
		} else if (clzz.equals(int.class) || clzz.equals(short.class)) {
			return new Integer(value);
		} else if (clzz.equals(byte.class)) {
			return value.getBytes();
		} else if (clzz.equals(long.class)) {
			return new Long(value);
		} else if (clzz.equals(boolean.class) || clzz.equals(Boolean.class)) {
			return new Boolean(value);
		} else if (clzz.equals(float.class) || clzz.equals(Float.class)) {
			return new Float(value);
		} else if (clzz.equals(double.class) || clzz.equals(Double.class)) {
			return new Double(value);
		}
		return null;
	}
	/**
	 * 将args去掉两边空格连接起来
	 * @author jianggm
	 * @param  args 待连接的字符串
	 * */
	public static String append (String ...args) {
		StringBuilder sb = new StringBuilder();
		for (String arg : args) {
			sb.append(arg.trim());
		}
		return sb.toString();
	}
}
