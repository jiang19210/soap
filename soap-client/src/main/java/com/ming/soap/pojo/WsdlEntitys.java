package com.ming.soap.pojo;

import java.io.Serializable;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.ming.soap.util.StringUtils;
/**
 * soap 访问服务描述集合，创建的WsdlEntity实例将被缓存，并且只缓存一个不同的实例对象，当缓存中存在此实例则从缓存中获取
 * @author jianggm
 * @version 0.0.1-SNAPSHOT
 * */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class WsdlEntitys implements Serializable {

	private static final long serialVersionUID = -5915591960316241036L;
	
	private Set<WsdlEntity> wsdlEntity = new HashSet<WsdlEntity>();

	private WsdlEntitys() {
	}

	public Set<WsdlEntity> getWsdlEntity() {
		return wsdlEntity;
	}

	public void setWsdlEntity(HashSet<WsdlEntity> wsdlEntity) {
		this.wsdlEntity = wsdlEntity;
	}


	public WsdlEntity createWsdlEntity (String wsdlUrl, String serviceNamespace,
			String serviceName, String servicePort) {
		Map<String, WsdlEntity> wsdlEntitys = WSDL_ENTITY;
		String key = StringUtils.append(wsdlUrl, serviceName, serviceNamespace, servicePort);
		WsdlEntity wsdl = null;
		if (wsdlEntitys.containsKey(key)) {
			wsdl = wsdlEntitys.get(key);
		} else {
			wsdl = new WsdlEntity(wsdlUrl, serviceNamespace, serviceName, servicePort);
			wsdlEntitys.put(key, wsdl);
			wsdlEntity.add(wsdl);
		}
		return wsdl;
	}
	
	public static WsdlEntitys newInstance () {
		return obj;
	}
	/**
	 * 存储 WsdlEntity 对象的Map，key=WsdlEntity.serviceName , value=WsdlEntity
	 * */
	private final static Map<String, WsdlEntity> WSDL_ENTITY = new ConcurrentHashMap<String, WsdlEntity>();
	
	private final static WsdlEntitys obj = new WsdlEntitys();
	
}
