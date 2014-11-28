/**
 * Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.html * 
 *
 */
package org.jinouts.ws;

import org.jinouts.transport.HttpTransport;
import org.jinouts.xml.namespace.QName;
import org.jinouts.xml.ws.WebServiceFeature;

import java.lang.reflect.Proxy;
import java.net.URL;

/**
 * @author asraf
 *         asraf344@gmail.com
 */
public class JinosService {

    private URL wsdlLocation;
    private QName serviceName;

    private static volatile HttpTransport sHttpTransport;

    public static void registerHttpTransport(HttpTransport httpTransport) {
        if (httpTransport == null) {
            throw new IllegalArgumentException();
        }

        sHttpTransport = httpTransport;
    }

    public static String getHttpTransportDescription() {
        HttpTransport httpTransport = sHttpTransport;
        return httpTransport.getDescription();
    }

    public JinosService(URL wsdlLocation, QName serviceName) {
        this.wsdlLocation = wsdlLocation;
        this.serviceName = serviceName;
    }

    public <T> T getPort(QName portName, Class<T> serviceEndpointInterface) {
        return this.getPort(serviceEndpointInterface.getClassLoader(),
                serviceEndpointInterface);
    }

    public <T> T getPort(QName portName, Class<T> serviceEndpointInterface,
                         WebServiceFeature... features) {
        return this.getPort(serviceEndpointInterface.getClassLoader(),
                serviceEndpointInterface);
    }

    public <T> T getPort(Class<T> serviceEndpointInterface) {
        return this.getPort(serviceEndpointInterface.getClassLoader(),
                serviceEndpointInterface);
    }

    public <T> T getPort(Class<T> serviceEndpointInterface,
                         WebServiceFeature... features) {
        return this.getPort(serviceEndpointInterface.getClassLoader(),
                serviceEndpointInterface);
    }

    public <T> T getPort(ClassLoader cl, Class<T> serviceInterface) {
        HttpTransport httpTransport = sHttpTransport;
        if (httpTransport == null) {
            throw new IllegalStateException("Http transport undefined");
        }
        WSInvocationHandler handler = new WSInvocationHandler(wsdlLocation, serviceName, httpTransport);
        return (T) Proxy.newProxyInstance(cl, new Class[]{serviceInterface}, handler);
    }

    public URL getWsdlLocation() {
        return wsdlLocation;
    }

    public void setWsdlLocation(URL wsdlLocation) {
        this.wsdlLocation = wsdlLocation;
    }

    public QName getServiceName() {
        return serviceName;
    }

    public void setServiceName(QName serviceName) {
        this.serviceName = serviceName;
    }


}
