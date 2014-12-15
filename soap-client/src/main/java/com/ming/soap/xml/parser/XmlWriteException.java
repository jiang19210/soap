package com.ming.soap.xml.parser;


/** 
 * @author jianggm 
 * @version 创建时间：2014年9月23日 下午4:55:27 
 */
public class XmlWriteException extends RuntimeException {
	private static final long serialVersionUID = 7100714597672227546L;
	
	public XmlWriteException(String msg) {
		super(msg);
	}
	/**
	 * @param msg the detail message
	 * @param cause the nested exception
	 */
	public XmlWriteException(String msg, Throwable cause) {
		super(msg, cause);
	}

}
