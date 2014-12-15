package com.ming.soap.xml.parser;

import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.ming.soap.pojo.WsdlEntity;
import com.ming.soap.pojo.WsdlEntitys;

public class WsdlEntityParser {
	
	private final static WsdlEntityParser obj = new WsdlEntityParser () ;
	/**
	 * 存储 WsdlEntity 对象的Map，key=WsdlEntity.serviceName , value=WsdlEntity
	 * */
	public final static Map<String, WsdlEntity> WSDL_ENTITY = new HashMap<String, WsdlEntity>();
	/**
	 * 构造私有化
	 * */
	private WsdlEntityParser() {
	}
	
	public static WsdlEntityParser newInstance () {
		return obj;
	}
	
	public WsdlEntity getWsdlEntity (String name) {
		return getWsdlEntitys().get(name);
	}
	/**
	 * 首次调用判断缓存是否是空，如果是空，则加载wsdl配置到缓存中；如果不是空，直接返回 缓存Map
	 * */
	protected Map<String, WsdlEntity> getWsdlEntitys () {
		if (WSDL_ENTITY.isEmpty()) {
			this.loadWsdlEntitys();
		}
		return WSDL_ENTITY;
	}
	/**
	 * 加载配置wsdl配置到缓存中
	 * */
	protected void loadWsdlEntitys () {
		WSDL_ENTITY.clear();
		WsdlEntitys wsdls = this.parser();
		for (WsdlEntity wsdl : wsdls.getWsdlEntity()) {
			WSDL_ENTITY.put(wsdl.getId(), wsdl);
		}
	}
	/**
	 * 解析wsdl配置文件，并存储在WsdlEntitys对象中
	 * */
	protected WsdlEntitys parser() {
		JAXBContext ctx = null;
		Unmarshaller um = null;
		WsdlEntitys wsdls = null;
		try {
			ctx = JAXBContext.newInstance(WsdlEntitys.class);
			um = ctx.createUnmarshaller();
			wsdls = (WsdlEntitys)um.unmarshal(new InputStreamReader(WsdlEntityParser.class.getClassLoader().getResourceAsStream("wsdlEntity.xml")));
		} catch (JAXBException e) {
			throw new XmlParserException("[WsdlEntitys parser failed, the Exception is JAXBException;]", e);
		}
		return wsdls;
	}
}
