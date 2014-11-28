/**
 * Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.html * 
 *
 */
package org.jinouts.transport;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.jinouts.ws.JinosService;

import java.io.IOException;

class HttpTransportImpl implements HttpTransport {

    static {
        JinosService.registerHttpTransport(new HttpTransportImpl());
    }

    private HttpTransportImpl() {}

    public String sendRequestAndGetRespXML(String soapAction, String reqXMLString, String url) throws IOException {
        HttpPost post = new HttpPost(url);
        StringEntity body = new StringEntity(reqXMLString, HTTP.UTF_8);
        body.setChunked(true);
        post.setEntity(body);
        post.setHeader("Content-Type", "text/xml;charset=" + HTTP.UTF_8);
        post.setHeader("Accept-Charset", HTTP.UTF_8);
        post.setHeader("SOAPAction", soapAction);

        HttpParams httpParameters = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(httpParameters, 5000);
        HttpConnectionParams.setSoTimeout(httpParameters, 6000);

        HttpResponse response = new DefaultHttpClient(httpParameters).execute(post);
        int status = response.getStatusLine().getStatusCode();
        if (status != 200 || response.getEntity() == null) {
            throw new HttpResponseException(status, "Invalid response");
        }
        return EntityUtils.toString(response.getEntity(), HTTP.UTF_8);
    }

    @Override
    public String getDescription() {
        return "default HttpTransport implementation";
    }
}
