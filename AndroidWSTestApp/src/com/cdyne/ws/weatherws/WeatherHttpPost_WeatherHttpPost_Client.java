
package com.cdyne.ws.weatherws;

/**
 * Please modify this class to meet your needs
 * This class is not complete
 */

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import org.jinouts.xml.namespace.QName;
import org.jinouts.jws.WebMethod;
import org.jinouts.jws.WebParam;
import org.jinouts.jws.WebResult;
import org.jinouts.jws.WebService;
import org.jinouts.jws.soap.SOAPBinding;
import org.jinouts.xml.bind.annotation.XmlSeeAlso;

/**
 * This class was generated by Apache CXF 2.6.1
 * 2014-11-22T16:10:13.975+01:00
 * Generated source version: 2.6.1
 * 
 */
public final class WeatherHttpPost_WeatherHttpPost_Client {

    private static final QName SERVICE_NAME = new QName("http://ws.cdyne.com/WeatherWS/", "Weather");

    private WeatherHttpPost_WeatherHttpPost_Client() {
    }

    public static void main(String args[]) throws java.lang.Exception {
        URL wsdlURL = Weather.WSDL_LOCATION;
        if (args.length > 0 && args[0] != null && !"".equals(args[0])) { 
            File wsdlFile = new File(args[0]);
            try {
                if (wsdlFile.exists()) {
                    wsdlURL = wsdlFile.toURI().toURL();
                } else {
                    wsdlURL = new URL(args[0]);
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
      
        Weather ss = new Weather(wsdlURL, SERVICE_NAME);
        WeatherHttpPost port = ss.getWeatherHttpPost();  
        
        {
        System.out.println("Invoking getCityWeatherByZIP...");
        java.lang.String _getCityWeatherByZIP_zip = "";
        com.cdyne.ws.weatherws.WeatherReturn _getCityWeatherByZIP__return = port.getCityWeatherByZIP(_getCityWeatherByZIP_zip);
        System.out.println("getCityWeatherByZIP.result=" + _getCityWeatherByZIP__return);


        }
        {
        System.out.println("Invoking getWeatherInformation...");
        com.cdyne.ws.weatherws.ArrayOfWeatherDescription _getWeatherInformation__return = port.getWeatherInformation();
        System.out.println("getWeatherInformation.result=" + _getWeatherInformation__return);


        }
        {
        System.out.println("Invoking getCityForecastByZIP...");
        java.lang.String _getCityForecastByZIP_zip = "";
        com.cdyne.ws.weatherws.ForecastReturn _getCityForecastByZIP__return = port.getCityForecastByZIP(_getCityForecastByZIP_zip);
        System.out.println("getCityForecastByZIP.result=" + _getCityForecastByZIP__return);


        }

        System.exit(0);
    }

}
