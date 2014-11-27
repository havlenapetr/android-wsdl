package org.jinouts.transport;

import java.io.IOException;

/**
 * Created by petr on 11/27/14.
 */
public interface HttpTransport {
    public String sendRequestAndGetRespXML(String soapAction, String reqXMLString, String url) throws IOException;
}
