package com.ming.soap.pojo;

import java.io.Serializable;
/**
 * @author jianggm
 * @version 0.0.1
 * wsdl:message 元素类型，相当于java类中的方法参数
 * name  消息类型元素名称,相当于java方法参数名称
 * value 消息类型元素值,相当于java方法参数值
 * */
public class Element implements Serializable {

	private static final long serialVersionUID = -260658272518176060L;
	
	private String name;	//参数名称
	private Object value;	//参数值
	private Class<?> clzz;  //参数类型
	public Element() {
	}
	
	public Element(String name, Object value, Class<?> clzz) {
		super();
		this.name = name;
		this.value = value;
		this.clzz = clzz;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Object getValue() {
		return value;
	}
	public void setValue(Object value) {
		this.value = value;
	}
	public Class<?> getClzz() {
		return clzz;
	}
	public void setClzz(Class<?> clzz) {
		this.clzz = clzz;
	}
	
	
}
