package com.sysware.demo.app.service.impl;  
  
import javax.jws.WebService;  
  
import com.sysware.demo.app.model.RetInfo;  
import com.sysware.demo.app.service.inf.IGetInfoService;  
  
@WebService(endpointInterface = "com.sysware.demo.app.service.inf.IGetInfoService")  
public class GetInfoServiceImpl implements IGetInfoService  
{  
  
    @Override  
    public int add(int num1, int num2)  
    {  
        return num1 + num2;  
    }  
  
    @Override  
    public RetInfo getRetInfo(String name, int age)  
    {  
        RetInfo retInfo = new RetInfo();  
        retInfo.setAge(age);  
        retInfo.setName(name);  
        return retInfo;  
    }  
    
    @Override
    public RetInfo getRetInfoObj(RetInfo retInfo) {
    	return retInfo;
    }
  
}  