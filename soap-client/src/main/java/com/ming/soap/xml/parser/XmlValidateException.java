package com.ming.soap.xml.parser;


/** 
 * @author jianggm 
 * @version 创建时间：2014年9月23日 下午4:55:27 
 */
public class XmlValidateException extends Exception {


    /**
     * Create a new XmlValidateException.
     */
    public XmlValidateException ()
    {
	super();
	this.exception = null;
    }
    
    
    /**
     * Create a new XmlValidateException.
     *
     * @param message The error or warning message.
     */
    public XmlValidateException (String message) {
	super(message);
	this.exception = null;
    }
    
    
    /**
     * Create a new XmlValidateException wrapping an existing exception.
     *
     * <p>The existing exception will be embedded in the new
     * one, and its message will become the default message for
     * the XmlValidateException.</p>
     *
     * @param e The exception to be wrapped in a XmlValidateException.
     */
    public XmlValidateException (Exception e)
    {
	super();
	this.exception = e;
    }
    
    
    /**
     * Create a new XmlValidateException from an existing exception.
     *
     * <p>The existing exception will be embedded in the new
     * one, but the new exception will have its own message.</p>
     *
     * @param message The detail message.
     * @param e The exception to be wrapped in a XmlValidateException.
     */
    public XmlValidateException (String message, Exception e)
    {
	super(message);
	this.exception = e;
    }
    
    
    /**
     * Return a detail message for this exception.
     *
     * <p>If there is an embedded exception, and if the XmlValidateException
     * has no detail message of its own, this method will return
     * the detail message from the embedded exception.</p>
     *
     * @return The error or warning message.
     */
    public String getMessage ()
    {
	String message = super.getMessage();
	
	if (message == null && exception != null) {
	    return exception.getMessage();
	} else {
	    return message;
	}
    }
    
    
    /**
     * Return the embedded exception, if any.
     *
     * @return The embedded exception, or null if there is none.
     */
    public Exception getException ()
    {
	return exception;
    }

    /**
    * Return the cause of the exception
    *
    * @return Return the cause of the exception
    */
    public Throwable getCause() {
        return exception;
    }

    /**
     * Override toString to pick up any embedded exception.
     *
     * @return A string representation of this exception.
     */
    public String toString ()
    {
	if (exception != null) {
	    return exception.toString();
	} else {
	    return super.toString();
	}
    }
    
    
    
    //////////////////////////////////////////////////////////////////////
    // Internal state.
    //////////////////////////////////////////////////////////////////////


    /**
     * @serial The embedded exception if tunnelling, or null.
     */    
    private Exception exception;
    
    // Added serialVersionUID to preserve binary compatibility 
    static final long serialVersionUID = 12345675433421213l;

}
