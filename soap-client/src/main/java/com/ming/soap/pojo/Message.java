package com.ming.soap.pojo;

import java.io.Serializable;
/**
 * soap message消息
 * @author jianggm
 * @version 0.0.1-SNAPSHOT
 * */
public class Message implements Serializable {

	private static final long serialVersionUID = -3689180207266138655L;
	
	private String result;    //返回名称
	private String namespace; // 命名空间
	private String name;	//方法名称
	private String prefix;	//命名空间前缀
	private Element[] argument; 	//方法参数
	
	public Message(String result, String namespace, String name, String prefix,
			Element[] argument) {
		super();
		this.result = result;
		this.namespace = namespace;
		this.name = name;
		this.prefix = prefix;
		this.argument = argument;
	}

	public Message() {
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getNamespace() {
		return namespace;
	}

	public void setNamespace(String namespace) {
		this.namespace = namespace;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public Element[] getArgument() {
		return argument;
	}

	public void setArgument(Element[] argument) {
		this.argument = argument;
	}

	
}
