package com.ming.soap.wsdl.support;


/** 
 * @author jianggm 
 * @version 创建时间：2014年9月23日 下午4:55:27 
 */
public class SoapClientException extends Exception {
	private static final long serialVersionUID = 7100714597672227546L;
	
	public SoapClientException(String msg) {
		super(msg);
	}
	/**
	 * @param msg the detail message
	 * @param cause the nested exception
	 */
	public SoapClientException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
