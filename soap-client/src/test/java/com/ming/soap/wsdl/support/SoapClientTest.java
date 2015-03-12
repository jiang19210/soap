package com.ming.soap.wsdl.support;

import org.junit.Test;
import static org.junit.Assert.*;
import com.ming.soap.SoapClient;
import com.ming.soap.SoapClientException;
import com.ming.soap.pojo.Element;
import com.ming.soap.pojo.Message;
import com.ming.soap.pojo.RetInfo;
import com.ming.soap.pojo.WsdlEntity;
import com.ming.soap.pojo.WsdlEntitys;
import com.ming.soap.xml.parser.WsdlEntityParser;

public class SoapClientTest {
	private  SoapClient client = SoapClient.newInstance();
	private WsdlEntityParser parser = WsdlEntityParser.newInstance();
	
	@Test
	public void test01 () throws SoapClientException {
		WsdlEntity  wsdl = parser.getWsdlEntity("GetInfoServiceImplService");
		RetInfo ri = new RetInfo("name", 1);
		Element[] argument = {new Element("retInfo", ri, RetInfo.class)};
		RetInfo retInfo = (RetInfo)client.invoke(wsdl, new Message("result", "http://inf.service.app.demo.sysware.com/", "getRetInfoObj", "nn", argument), RetInfo.class);
		assertEquals(retInfo.getAge(), ri.getAge());
		assertEquals(retInfo.getName(), ri.getName());
	}
	
	@Test
	public void test02 () throws SoapClientException {
		WsdlEntity  wsdl = WsdlEntitys.newInstance().createWsdlEntity("http://localhost:8989/soap-server/ws/getInfoService?wsdl",
				"http://impl.service.app.demo.sysware.com/", "GetInfoServiceImplService", "GetInfoServiceImplPort");
		RetInfo ri = new RetInfo("name", 1);
		Element[] argument = {new Element("retInfo", ri, RetInfo.class)};
		RetInfo retInfo = (RetInfo)client.invoke(wsdl, new Message("result", "http://inf.service.app.demo.sysware.com/", "getRetInfoObj", "nn", argument), RetInfo.class);
		assertEquals(retInfo.getAge(), ri.getAge());
		assertEquals(retInfo.getName(), ri.getName());
	}
}
