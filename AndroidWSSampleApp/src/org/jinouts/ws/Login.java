package org.jinouts.ws;

import org.jinouts.jws.WebMethod;
import org.jinouts.jws.WebParam;
import org.jinouts.jws.WebResult;
import org.jinouts.jws.WebService;
import org.jinouts.xml.bind.annotation.XmlSeeAlso;
import org.jinouts.xml.ws.RequestWrapper;
import org.jinouts.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 2.5.1
 * 2012-06-19T17:07:52.452+06:00
 * Generated source version: 2.5.1
 * 
 */
@WebService(targetNamespace = "http://ws.jinouts.org/", name = "login")
@XmlSeeAlso({ObjectFactory.class})
public interface Login {

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "enter", targetNamespace = "http://ws.jinouts.org/", className = "org.jinouts.ws.Enter")
    @WebMethod
    @ResponseWrapper(localName = "enterResponse", targetNamespace = "http://ws.jinouts.org/", className = "org.jinouts.ws.EnterResponse")
    public java.lang.String enter(
        @WebParam(name = "user", targetNamespace = "")
        java.lang.String user,
        @WebParam(name = "pass", targetNamespace = "")
        java.lang.String pass
    );
}
