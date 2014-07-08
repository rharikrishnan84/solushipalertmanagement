package com.meritconinc.shiplinx.ws.proxy;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 2.5.2
 * 2013-03-06T21:55:33.003-05:00
 * Generated source version: 2.5.2
 * 
 */
@WebServiceClient(name = "ShippingService", 
                  wsdlLocation = "ShippingService.wsdl",
                  targetNamespace = "http://www.proxy.ws.shiplinx.meritconinc.com/") 
public class ShippingService extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://www.proxy.ws.shiplinx.meritconinc.com/", "ShippingService");
    public final static QName ShippingServiceImplPort = new QName("http://www.proxy.ws.shiplinx.meritconinc.com/", "ShippingServiceImplPort");
    static {
        URL url = ShippingService.class.getResource("ShippingService.wsdl");
        if (url == null) {
            java.util.logging.Logger.getLogger(ShippingService.class.getName())
                .log(java.util.logging.Level.INFO, 
                     "Can not initialize the default wsdl from {0}", "ShippingService.wsdl");
        }        
        WSDL_LOCATION = url;
    }

    public ShippingService(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public ShippingService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public ShippingService() {
        super(WSDL_LOCATION, SERVICE);
    }
    

    /**
     *
     * @return
     *     returns ShippingPortType
     */
    @WebEndpoint(name = "ShippingServiceImplPort")
    public ShippingPortType getShippingServiceImplPort() {
        return super.getPort(ShippingServiceImplPort, ShippingPortType.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns ShippingPortType
     */
    @WebEndpoint(name = "ShippingServiceImplPort")
    public ShippingPortType getShippingServiceImplPort(WebServiceFeature... features) {
        return super.getPort(ShippingServiceImplPort, ShippingPortType.class, features);
    }

}
