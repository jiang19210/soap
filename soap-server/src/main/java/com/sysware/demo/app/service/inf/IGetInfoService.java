package com.sysware.demo.app.service.inf;  
  
import javax.jws.WebMethod;  
import javax.jws.WebParam;  
import javax.jws.WebResult;  
import javax.jws.WebService;  
  
import com.sysware.demo.app.model.RetInfo;  
  
@WebService  
public interface IGetInfoService  
{  
    @WebMethod(operationName = "add")  
    @WebResult(name = "result")  
    public int add(@WebParam(name = "num1") int num1,  
            @WebParam(name = "num2") int num2);  
      
    @WebMethod(operationName = "getRetInfo")  
    @WebResult(name = "result")  
    public RetInfo getRetInfo(@WebParam(name = "name") String name, @WebParam(name = "age") int age); 
    
    @WebMethod(operationName = "getRetInfoObj")  
    @WebResult(name = "result")  
    public RetInfo getRetInfoObj(@WebParam(name = "retInfo") RetInfo retInfo);  
}  