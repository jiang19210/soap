package com.ming.soap.wsdl.support;

import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPBodyElement;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.Dispatch;
import javax.xml.ws.Service;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.ming.soap.pojo.Element;
import com.ming.soap.pojo.Message;
import com.ming.soap.pojo.WsdlEntity;
import com.ming.soap.reflect.ClassSetterOrGetterException;
import com.ming.soap.reflect.ClassUtils;
import com.ming.soap.util.ObjectUtils;
import com.ming.soap.util.StringUtils;

/**
 * webservice 客户端简单封装
 * 
 * @author jianggm
 * @version 0.0.1
 * */
public class SoapClient {
	/**
	 * SoapClient 单例
	 * */
	private static SoapClient client = new SoapClient();
	/**
	 * 获取SoapClient 的实例，是单例模式
	 * */
	public static SoapClient newInstance() {
		return client;
	}
	/**
	 * 受保护的构造方法，外部无法实例化。可以通过类的 newInstance()实例化
	 * */
	private SoapClient() {
	}
	/**
	 * webservice调用的核心方法
	 * @author jianggm
	 * @param wsdl wsdlEntity.xml配置信息
	 * @param method  要调用的webservice方法
	 * @param clzz 返回值类型
	 * */
	public Object invoke(WsdlEntity wsdl, Message method, Class<?> clzz) throws SoapClientException   {
		try {
			// 1、创建服务(Service)
			URL url = new URL(wsdl.getWsdlUrl().toString());
			QName sname = new QName(wsdl.getServiceNamespace(),
					wsdl.getServiceName());
			Service service = Service.create(url, sname);

			// 2、创建Dispatch
			Dispatch<SOAPMessage> dispatch = service.createDispatch(new QName(
					wsdl.getServiceNamespace(), wsdl.getServicePort()),
					SOAPMessage.class, Service.Mode.MESSAGE);

			// 3、创建SOAPMessage
			SOAPMessage msg = MessageFactory.newInstance().createMessage();
			SOAPEnvelope envelope = msg.getSOAPPart().getEnvelope();
			SOAPBody body = envelope.getBody();
			setSOAPBody(body, method);
			// 5、通过Dispatch传递消息,会返回响应消息
			SOAPMessage response = dispatch.invoke(msg);
			// 将响应的消息转换为dom对象
			Document doc = response.getSOAPPart().getEnvelope().getBody()
					.extractContentAsDocument();
			
			Node node = doc.getElementsByTagName(method.getResult())
					.item(0);
			
			return nodeToObject(node, clzz);
		} catch (SOAPException e) {
			throw new SoapClientException(
					"[SoapClient invoke failed, the Exception is SOAPException;]",
					e);
		} catch (IOException e) {
			throw new SoapClientException(
					"[SoapClient invoke failed, the Exception is IOException;]",
					e);
		} catch (DOMException e) {
			throw new SoapClientException(
					"[SoapClient invoke failed, the Exception is DOMException;]",
					e);
		} catch (InstantiationException e) {
			e.printStackTrace();
			throw new SoapClientException(
					"[SoapClient invoke failed, the Exception is InstantiationException;]" ,
					e);
		} catch (IllegalAccessException e) {
			throw new SoapClientException(
					"[SoapClient invoke failed, the Exception is IllegalAccessException;]",
					e);
		} catch (ClassSetterOrGetterException e) {
			throw new SoapClientException(
					"[SoapClient invoke failed, the Exception is ClassSetterOrGetterException;]",
					e);
		} 
	}
	
	protected void setSOAPBody (SOAPBody body, Message method) throws SOAPException, ClassSetterOrGetterException {
		// 4、创建QName来指定消息中传递数据
		QName ename = new QName(method.getNamespace(), method.getName(), method.getPrefix());// <nn:add xmlns="xx"/>
		SOAPBodyElement ele = body.addBodyElement(ename);
		for (Element arg : method.getArgument()) {
			if (arg.getValue() == null) {
				ele.addChildElement(arg.getName());
				continue;
			}
			if (ObjectUtils.isBaseType(arg.getValue())) {
				ele.addChildElement(arg.getName()).setValue(arg.getValue().toString());
			} else {
				Class<?> clzz = arg.getClzz();
				SOAPElement soapObj = ele.addChildElement(arg.getName());
				Field[] fields = clzz.getDeclaredFields();
				for (Field field : fields) {
					if (ClassUtils.CONSTANT_SERIALVERSIONUID.equals(field.getName())) {
						continue;
					}
					String value = ClassUtils.getter(arg.getValue(), field.getName()).toString();
					soapObj.addChildElement(field.getName()).setValue(value);
				}
			}
		}
		
	}
	
	/**
	 * 根据Node 转换成简单对象和基本对象类型，不支持有嵌套对象类型
	 * @throws ClassSetterOrGetterException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws DOMException 
	 * */
	protected Object nodeToObject (Node node, Class<?> clzz) throws DOMException,
	ClassSetterOrGetterException, InstantiationException, IllegalAccessException {
		NodeList nodeList = node.getChildNodes();
		Node n = null;
		Object val = StringUtils.parseValueForClass(node.getTextContent(), clzz);
		Object t = null;
		if (val == null) {
			t = clzz.newInstance();
			for (int i = 0; i < nodeList.getLength(); i++) {
				n = nodeList.item(i);
				ClassUtils.setter(t, n.getNodeName(), n.getTextContent());
			}
		}
		return t;
	}
	
}
