package com.ming.soap.reflect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.ming.soap.util.StringUtils;
/**
 * @author jianggm
 * @version 0.0.1
 * Class 的辅助类，
 * 对简单对象的get , set 方法的反射调用
 * */
public class ClassUtils {
	/**
	 * 序列字段
	 * */
	public final static String CONSTANT_SERIALVERSIONUID = "serialVersionUID";
	/**
	 * @param obj 操作的对象
	 * @param field 操作的属性
	 * @throws ClassSetterOrGetterException 
	 * */
	public static <T> Object getter(T t, String field) throws ClassSetterOrGetterException {
		Object obj = null;
		Class<?> clzz = t.getClass();
		try {
			Method method = clzz.getMethod("get" + StringUtils.firstCharToUpperCase(field));
			obj = method.invoke(t);
		} catch (NoSuchMethodException e) {
			throw new ClassSetterOrGetterException("[Class Getter Exception]", e);
		} catch (SecurityException e) {
			throw new ClassSetterOrGetterException("[Class Getter Exception]", e);
		} catch (IllegalAccessException e) {
			throw new ClassSetterOrGetterException("[Class Getter Exception]", e);
		} catch (IllegalArgumentException e) {
			throw new ClassSetterOrGetterException("[Class Getter Exception]", e);
		} catch (InvocationTargetException e) {
			throw new ClassSetterOrGetterException("[Class Getter Exception]", e);
		}
		return obj;
	}

	/**
	 * @param obj 操作的对象
	 * @param field 操作的属性
	 * @param value 设置的值
	 * @param type 参数的属性
	 * @throws ClassSetterOrGetterException 
	 * */
	public static <T> void setter(T t, String field, String value) throws ClassSetterOrGetterException {
		Class<?> clzz = t.getClass();
		Class<?> fieldClass;
		try {
			fieldClass = clzz.getDeclaredField(field).getType();
			Method method = clzz.getMethod("set" + StringUtils.firstCharToUpperCase(field), fieldClass);
			method.invoke(t, StringUtils.parseValueForClass(value, fieldClass));
		} catch (NoSuchFieldException e) {
			throw new ClassSetterOrGetterException("[Class Setter Exception]", e);
		} catch (SecurityException e) {
			throw new ClassSetterOrGetterException("[Class Setter Exception]", e);
		} catch (NoSuchMethodException e) {
			throw new ClassSetterOrGetterException("[Class Setter Exception]", e);
		} catch (IllegalAccessException e) {
			throw new ClassSetterOrGetterException("[Class Setter Exception]", e);
		} catch (IllegalArgumentException e) {
			throw new ClassSetterOrGetterException("[Class Setter Exception]", e);
		} catch (InvocationTargetException e) {
			throw new ClassSetterOrGetterException("[Class Setter Exception]", e);
		}
		
	}
}
