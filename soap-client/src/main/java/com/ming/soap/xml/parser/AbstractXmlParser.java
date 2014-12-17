package com.ming.soap.xml.parser;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
/**
 * @author jianggm
 * xml解析及验证
 * */
public abstract class AbstractXmlParser implements XmlParser, XmlValidate {
	/**
	 * 根据xsd文件验证xml
	 * 
	 * @author jianggm
	 * @param xml
	 *            待验证的xml
	 * @param xsd
	 *            验证xml的dtd架构
	 * */
	@Override
	public void DTDValidate(InputStream inputStreamXml,
			InputStream inputStreamXdtd) {
		// TODO Auto-generated method stub

	}

	/**
	 * 根据xsd文件验证xml
	 * 
	 * @author jianggm
	 * @param xml
	 *            待验证的xml
	 * @param xsd
	 *            验证xml的xsd架构
	 * @throws SAXException
	 * @throws IOException
	 * */
	@Override
	public void XSDValidate(InputStream inputStreamXml,
			InputStream inputStreamXxsd) throws XmlValidateException {
		String schemaLanguage = XMLConstants.W3C_XML_SCHEMA_NS_URI;
		SchemaFactory schemaFactory = SchemaFactory.newInstance(schemaLanguage);
		Schema schema;
		try {
			schema = schemaFactory.newSchema(new StreamSource(inputStreamXml));
			Validator validator = schema.newValidator();
			InputSource inputSource = new InputSource(inputStreamXml);
			Source source = new SAXSource(inputSource);
			validator.validate(source);
		} catch (SAXException e) {
			e.printStackTrace();
			throw new XmlValidateException("", e);
		} catch (IOException e) {
			e.printStackTrace();
			throw new XmlValidateException("", e);
		}

	}

}
