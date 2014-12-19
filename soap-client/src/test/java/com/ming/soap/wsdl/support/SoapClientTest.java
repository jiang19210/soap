package com.ming.soap.wsdl.support;

import org.junit.Test;


import com.ming.soap.pojo.Element;
import com.ming.soap.pojo.Message;
import com.ming.soap.pojo.RetInfo;
import com.ming.soap.pojo.WsdlEntity;
import com.ming.soap.pojo.WsdlEntitys;
import com.ming.soap.xml.parser.WsdlEntityParser;

/**
 * SoapClient 的例子，两种不同的调用方式
 * 1. 依赖wsdlEntity.xml 获取WsdlEntity实例的调用
 * 2. 不依赖配置获得WsdlEntity实例的调用
 * */
public class SoapClientTest {
	private  SoapClient client = SoapClient.newInstance();
	private WsdlEntityParser parser = WsdlEntityParser.newInstance();
	/**
	 * 依赖wsdlEntity.xml 获取WsdlEntity实例的调用
	 * 可以再xml中灵活的配置 soap-client请求的各种参数
	 * */
	@Test
	public void test01 () throws SoapClientException {
		WsdlEntity  wsdl = parser.getWsdlEntity("GetInfoServiceImplService");
		Element[] argument = {new Element("retInfo", new RetInfo("name", 1), RetInfo.class)};
		RetInfo retInfo = (RetInfo)client.invoke(wsdl, new Message("result", "http://inf.service.app.demo.sysware.com/", "getRetInfoObj", "nn", argument), RetInfo.class);
		System.out.println(retInfo.getAge() + retInfo.getName());
	}
	/**
	 * 不依赖配置获得WsdlEntity实例的调用
	 * */
	@Test
	public void test02 () throws SoapClientException {
		WsdlEntity  wsdl = WsdlEntitys.newInstance().createWsdlEntity("http://localhost:8080/soap-server/ws/getInfoService?wsdl",
				"http://impl.service.app.demo.sysware.com/", "GetInfoServiceImplService", "GetInfoServiceImplPort");
		Element[] argument = {new Element("retInfo", new RetInfo("name", 1), RetInfo.class)};
		RetInfo retInfo = (RetInfo)client.invoke(wsdl, new Message("result", "http://inf.service.app.demo.sysware.com/", "getRetInfoObj", "nn", argument), RetInfo.class);
		System.out.println(retInfo.getAge() + retInfo.getName());
	}
}
