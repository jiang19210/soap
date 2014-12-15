package com.ming.soap.pojo;  

import java.io.Serializable;

public class RetInfo implements Serializable
{  
	private static final long serialVersionUID = 1L;
	private String name;  
    private int age;  
    public RetInfo() {
	}
    public RetInfo(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}
	public String getName()  
    {  
        return name;  
    }  
    public void setName(String name)  
    {  
        this.name = name;  
    }  
    public int getAge()  
    {  
        return age;  
    }  
    public void setAge(int age)  
    {  
        this.age = age;  
    }
    
}  