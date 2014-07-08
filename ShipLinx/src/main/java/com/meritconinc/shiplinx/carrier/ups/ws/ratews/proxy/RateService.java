package com.meritconinc.shiplinx.carrier.ups.ws.ratews.proxy;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 2.5.0
 * 2011-12-10T07:51:11.497-05:00
 * Generated source version: 2.5.0
 * 
 */
@WebServiceClient(name = "RateService", 
                  wsdlLocation = "RateWS.wsdl",
                  targetNamespace = "http://www.ups.com/WSDL/XOLTWS/Rate/v1.1") 
public class RateService extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://www.ups.com/WSDL/XOLTWS/Rate/v1.1", "RateService");
    public final static QName RatePort = new QName("http://www.ups.com/WSDL/XOLTWS/Rate/v1.1", "RatePort");
    static {
        URL url = null;
        try {
            url = new URL("RateWS.wsdl");
        } catch (MalformedURLException e) {
            java.util.logging.Logger.getLogger(RateService.class.getName())
                .log(java.util.logging.Level.INFO, 
                     "Can not initialize the default wsdl from {0}", "RateWS.wsdl");
        }
        WSDL_LOCATION = url;
    }

    public RateService(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public RateService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public RateService() {
        super(WSDL_LOCATION, SERVICE);
    }
    

    /**
     *
     * @return
     *     returns RatePortType
     */
    @WebEndpoint(name = "RatePort")
    public RatePortType getRatePort() {
        return super.getPort(RatePort, RatePortType.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns RatePortType
     */
    @WebEndpoint(name = "RatePort")
    public RatePortType getRatePort(WebServiceFeature... features) {
        return super.getPort(RatePort, RatePortType.class, features);
    }

}
