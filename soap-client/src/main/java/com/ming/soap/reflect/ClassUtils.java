package com.ming.soap.reflect;

import java.lang.reflect.Field;
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
	 * 返回指定对象上此 Field 表示的字段的值。如果该值是一个基本类型值，则自动将其包装在一个对象中。
	 * @author jianggm
	 * @param  t 指定对象
	 * @param  field Field 
	 * @throws ClassFieldException 
	 * */
	public static Object getFieldValue (Object object, Field field) throws ClassFieldException {
		try {
			return field.get(object);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			throw new ClassFieldException("[Class Getter Field Exception]", e);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			throw new ClassFieldException("[Class Getter Field Exception]", e);
		}
	}
	/**
	 * 返回指定对象上此 Field 表示的字段的值。如果该值是一个基本类型值，则自动将其包装在一个对象中。
	 * @author jianggm
	 * @param  t 指定对象
	 * @param  field Field 
	 * @throws ClassFieldException 
	 * */
	public static void setFieldValue (Object object, Object value, Field field) throws ClassFieldException {
		try {
			field.set(object, value);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			throw new ClassFieldException("[Class Setter Field Exception]", e);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			throw new ClassFieldException("[Class Setter Field Exception]", e);
		}
	}
	/**
	 * @param obj 操作的对象
	 * @param field 操作的属性
	 * @throws ClassSetterOrGetterException 
	 * */
	public static Object getter(Object object, String field) throws ClassSetterOrGetterException {
		Object obj = null;
		Class<?> clzz = object.getClass();
		try {
			Method method = clzz.getMethod("get" + StringUtils.firstCharToUpperCase(field));
			obj = method.invoke(object);
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
	public static <T> void setter(Object object, String field, String value) throws ClassSetterOrGetterException {
		Class<?> clzz = object.getClass();
		Class<?> fieldClass;
		try {
			fieldClass = clzz.getDeclaredField(field).getType();
			Method method = clzz.getMethod("set" + StringUtils.firstCharToUpperCase(field), fieldClass);
			method.invoke(object, StringUtils.parseValueForClass(value, fieldClass));
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
