package com.ming.soap.xml.parser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.Source;
import javax.xml.transform.sax.SAXSource;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import com.ming.soap.pojo.WsdlEntity;
import com.ming.soap.pojo.WsdlEntitys;

public class WsdlEntityParser extends AbstractXmlParser implements XmlParser {
	/**
	 * wsdlEntity SCHEMA
	 * */
	private final static String SCHEMA_XSD = "wsdlEntity.xsd";
	/**
	 * wsdlEntity XML  
	 * */
	private final static String WSDLENTITY_XML = "wsdlEntity.xml";

	private final static WsdlEntityParser obj = new WsdlEntityParser();
	/**
	 * 存储 WsdlEntity 对象的Map，key=WsdlEntity.serviceName , value=WsdlEntity
	 * */
	public final static Map<String, WsdlEntity> WSDL_ENTITY = new HashMap<String, WsdlEntity>();

	/**
	 * 构造私有化
	 * */
	private WsdlEntityParser() {
	}

	public static WsdlEntityParser newInstance() {
		return obj;
	}

	public WsdlEntity getWsdlEntity(String name) {
		return getWsdlEntitys().get(name);
	}

	/**
	 * 首次调用判断缓存是否是空，如果是空，则加载wsdl配置到缓存中；如果不是空，直接返回 缓存Map
	 * */
	protected Map<String, WsdlEntity> getWsdlEntitys() {
		if (WSDL_ENTITY.isEmpty()) {
			this.loadWsdlEntitys();
		}
		return WSDL_ENTITY;
	}

	/**
	 * 加载配置wsdl配置到缓存中
	 * */
	protected void loadWsdlEntitys() {
		WSDL_ENTITY.clear();
		WsdlEntitys wsdls = this.parser(new File(WsdlEntityParser.class.getResource("/" + WSDLENTITY_XML).getPath()));
		for (WsdlEntity wsdl : wsdls.getWsdlEntity()) {
			WSDL_ENTITY.put(wsdl.getId(), wsdl);
		}
	}

	/**
	 * 解析wsdl配置文件，并存储在WsdlEntitys对象中
	 * @author jianggm
	 * @param file 为了进行读取而打开的文件。 
	 * */
	public WsdlEntitys parser(File file) {
		JAXBContext ctx = null;
		Unmarshaller um = null;
		WsdlEntitys wsdls = null;
		try {
			super.XSDValidate(new FileInputStream(file),
					WsdlEntityParser.class.getResourceAsStream(SCHEMA_XSD));
			ctx = JAXBContext.newInstance(WsdlEntitys.class);
			um = ctx.createUnmarshaller();

			SAXParserFactory sax = SAXParserFactory.newInstance();
			sax.setNamespaceAware(false);
			XMLReader xmlReader = sax.newSAXParser().getXMLReader();
			Source source = new SAXSource(xmlReader, new InputSource(
					new FileInputStream(file)));
			wsdls = (WsdlEntitys) um.unmarshal(source);
		} catch (JAXBException e) {
			e.printStackTrace();
			throw new XmlParserException(
					"[WsdlEntitys parser failed, the Exception is JAXBException;]",
					e);
		} catch (XmlValidateException e) {
			throw new XmlParserException(
					"[WsdlEntitys parser failed, the Exception is JAXBException;]",
					e);
		} catch (SAXException e) {
			throw new XmlParserException(
					"[WsdlEntitys parser failed, the Exception is JAXBException;]",
					e);
		} catch (ParserConfigurationException e) {
			throw new XmlParserException(
					"[WsdlEntitys parser failed, the Exception is JAXBException;]",
					e);
		} catch (FileNotFoundException e) {
			throw new XmlParserException(
					"[WsdlEntitys parser failed, the Exception is JAXBException;]",
					e);
		}
		return wsdls;
	}

}
