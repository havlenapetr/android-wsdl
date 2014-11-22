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
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public final class HttpTransportUtil {

    private HttpTransportUtil() {
    }

    public static String sendRequestAndGetRespXML(String reqXMLString, String url) throws IOException {
        HttpPost post = new HttpPost(url);
        StringEntity body = new StringEntity(reqXMLString, "UTF-8");
        body.setChunked(true);
        post.setEntity(body);
        post.setHeader("Content-type", "text/xml; charset=UTF-8");
        post.setHeader("Accept", "text/xml; charset=UTF-8");
        //post.setHeader("SOAPAction", operation);

        HttpParams httpParameters = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(httpParameters, 5000);
        HttpConnectionParams.setSoTimeout(httpParameters, 6000);

        HttpResponse response = new DefaultHttpClient(httpParameters).execute(post);
        int status = response.getStatusLine().getStatusCode();
        if (status != 200) {
            throw new HttpResponseException(status, "Invalid response");
        }
        return EntityUtils.toString(response.getEntity());
    }
}
