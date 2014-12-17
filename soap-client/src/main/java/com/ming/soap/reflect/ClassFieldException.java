package com.ming.soap.reflect;


/** 
 * @author jianggm 
 * @version 0.0.1
 * 创建时间：2014年9月23日 下午4:55:27 
 */
public class ClassFieldException extends Exception {
	private static final long serialVersionUID = 7100714597672227546L;
	
	public ClassFieldException(String msg) {
		super(msg);
	}
	/**
	 * @param msg the detail message
	 * @param cause the nested exception
	 */
	public ClassFieldException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
