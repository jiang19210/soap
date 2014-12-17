package com.ming.soap.xml.parser;

import java.io.InputStream;


/**
 * XML格式验证(DTD,XSD)
 * @author jianggm
 * */
public interface XmlValidate {
	/**
	 * 根据xsd文件验证xml
	 * @author jianggm
	 * @param  xml    待验证的xml
	 * @param  xsd    验证xml的xsd架构
	 * */
	public void XSDValidate (InputStream inputStreamXml, InputStream inputStreamXxsd) throws XmlValidateException;
	/**
	 * 根据xsd文件验证xml
	 * @author jianggm
	 * @param  xml    待验证的xml
	 * @param  xsd    验证xml的dtd架构
	 * */
	public void DTDValidate (InputStream inputStreamXml, InputStream inputStreamXdtd) throws XmlValidateException;
}
