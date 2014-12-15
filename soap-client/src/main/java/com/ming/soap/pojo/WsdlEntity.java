package com.ming.soap.pojo;

import java.io.Serializable;
/**
 * 访问
 * */
public class WsdlEntity implements Serializable {

	private static final long serialVersionUID = -6483931466179236361L;
	
	private String id;
	private String wsdlUrl;
	private String serviceNamespace;
	private String serviceName;
	private String servicePort;
	
	protected WsdlEntity() {
	}

	protected WsdlEntity(String wsdlUrl, String serviceNamespace,
			String serviceName, String servicePort) {
		super();
		this.wsdlUrl = wsdlUrl;
		this.serviceNamespace = serviceNamespace;
		this.serviceName = serviceName;
		this.servicePort = servicePort;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getWsdlUrl() {
		return wsdlUrl;
	}

	public void setWsdlUrl(String wsdlUrl) {
		this.wsdlUrl = wsdlUrl;
	}

	public String getServiceNamespace() {
		return serviceNamespace;
	}

	public void setServiceNamespace(String serviceNamespace) {
		this.serviceNamespace = serviceNamespace;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getServicePort() {
		return servicePort;
	}

	public void setServicePort(String servicePort) {
		this.servicePort = servicePort;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((serviceName == null) ? 0 : serviceName.hashCode());
		result = prime
				* result
				+ ((serviceNamespace == null) ? 0 : serviceNamespace.hashCode());
		result = prime * result
				+ ((servicePort == null) ? 0 : servicePort.hashCode());
		result = prime * result + ((wsdlUrl == null) ? 0 : wsdlUrl.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		WsdlEntity other = (WsdlEntity) obj;
		if (serviceName == null) {
			if (other.serviceName != null)
				return false;
		} else if (!serviceName.equals(other.serviceName))
			return false;
		if (serviceNamespace == null) {
			if (other.serviceNamespace != null)
				return false;
		} else if (!serviceNamespace.equals(other.serviceNamespace))
			return false;
		if (servicePort == null) {
			if (other.servicePort != null)
				return false;
		} else if (!servicePort.equals(other.servicePort))
			return false;
		if (wsdlUrl == null) {
			if (other.wsdlUrl != null)
				return false;
		} else if (!wsdlUrl.equals(other.wsdlUrl))
			return false;
		return true;
	}
	
}
