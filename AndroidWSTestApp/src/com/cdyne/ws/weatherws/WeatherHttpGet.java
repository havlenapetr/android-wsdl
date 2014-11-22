package com.cdyne.ws.weatherws;

import org.jinouts.jws.WebMethod;
import org.jinouts.jws.WebParam;
import org.jinouts.jws.WebResult;
import org.jinouts.jws.WebService;
import org.jinouts.jws.soap.SOAPBinding;
import org.jinouts.xml.bind.annotation.XmlSeeAlso;

/**
 * This class was generated by Apache CXF 2.6.1
 * 2014-11-22T16:10:14.099+01:00
 * Generated source version: 2.6.1
 * 
 */
@WebService(targetNamespace = "http://ws.cdyne.com/WeatherWS/", name = "WeatherHttpGet")
@XmlSeeAlso({ObjectFactory.class})
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public interface WeatherHttpGet {

    /**
     * Allows you to get your City's Weather, which is updated hourly. U.S. Only
     */
    @WebResult(name = "WeatherReturn", targetNamespace = "http://ws.cdyne.com/WeatherWS/", partName = "Body")
    @WebMethod(operationName = "GetCityWeatherByZIP")
    public WeatherReturn getCityWeatherByZIP(
        @WebParam(partName = "ZIP", name = "ZIP", targetNamespace = "")
        java.lang.String zip
    );

    /**
     * Gets Information for each WeatherID
     */
    @WebResult(name = "ArrayOfWeatherDescription", targetNamespace = "http://ws.cdyne.com/WeatherWS/", partName = "Body")
    @WebMethod(operationName = "GetWeatherInformation")
    public ArrayOfWeatherDescription getWeatherInformation();

    /**
     * Allows you to get your City Forecast Over the Next 7 Days, which is updated hourly. U.S. Only
     */
    @WebResult(name = "ForecastReturn", targetNamespace = "http://ws.cdyne.com/WeatherWS/", partName = "Body")
    @WebMethod(operationName = "GetCityForecastByZIP")
    public ForecastReturn getCityForecastByZIP(
        @WebParam(partName = "ZIP", name = "ZIP", targetNamespace = "")
        java.lang.String zip
    );
}