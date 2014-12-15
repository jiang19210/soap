package com.ming.soap.pojo;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;

import com.ming.soap.util.StringUtils;
@XmlRootElement
public class WsdlEntitys implements Serializable {

	private static final long serialVersionUID = -5915591960316241036L;
	
	private WsdlEntity[] wsdlEntity;

	public WsdlEntitys() {
	}

	public WsdlEntitys(WsdlEntity[] wsdlEntity) {
		super();
		this.wsdlEntity = wsdlEntity;
	}

	public WsdlEntity[] getWsdlEntity() {
		return wsdlEntity;
	}

	public void setWsdlEntity(WsdlEntity[] wsdlEntity) {
		this.wsdlEntity = wsdlEntity;
	}
	
	public static WsdlEntity createWsdlEntity (String wsdlUrl, String serviceNamespace,
			String serviceName, String servicePort) {
		Map<String, WsdlEntity> wsdlEntitys = WSDL_ENTITY;
		String key = StringUtils.append(wsdlUrl, serviceName, serviceNamespace, servicePort);
		WsdlEntity wsdlEntity = null;
		if (wsdlEntitys.containsKey(key)) {
			wsdlEntity = wsdlEntitys.get(key);
		} else {
			synchronized (WSDL_ENTITY) {
				wsdlEntity = new WsdlEntity(wsdlUrl, serviceNamespace, serviceName, servicePort);
				WSDL_ENTITY.put(key, wsdlEntity);
			}
		}
		return wsdlEntity;
	}
	
	public static WsdlEntitys newInstance () {
		return obj;
	}
	/**
	 * 存储 WsdlEntity 对象的Map，key=WsdlEntity.serviceName , value=WsdlEntity
	 * */
	private final static Map<String, WsdlEntity> WSDL_ENTITY = new HashMap<String, WsdlEntity>();
	
	private final static WsdlEntitys obj = new WsdlEntitys();
	
}
