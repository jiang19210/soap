package com.ming.soap.wsdl.support;

import org.junit.Test;

import com.ming.soap.pojo.Element;
import com.ming.soap.pojo.Message;
import com.ming.soap.pojo.RetInfo;
import com.ming.soap.pojo.WsdlEntity;
import com.ming.soap.xml.parser.WsdlEntityParser;

public class SoapClientTest {
	
	private  SoapClient client = SoapClient.newInstance();
	private WsdlEntityParser parser = WsdlEntityParser.newInstance();
	@Test
	public void test01 () throws SoapClientException {
		WsdlEntity  wsdl = parser.getWsdlEntity("GetInfoServiceImplService");
		Element[] argument = {new Element("retInfo", new RetInfo("name", 1), RetInfo.class)};
		RetInfo retInfo = (RetInfo)client.invoke(wsdl, new Message("result", "http://inf.service.app.demo.sysware.com/", "getRetInfoObj", "nn", argument), RetInfo.class);
		System.out.println(retInfo.getAge() + retInfo.getName());
	}
}
