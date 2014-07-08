package com.meritconinc.shiplinx.carrier.ups.ws.ship.proxy;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 2.5.0
 * 2011-12-08T22:11:53.395-05:00
 * Generated source version: 2.5.0
 * 
 */
@WebServiceClient(name = "ShipService", 
                  wsdlLocation = "Ship.wsdl",
                  targetNamespace = "http://www.ups.com/WSDL/XOLTWS/Ship/v1.0") 
public class ShipService extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://www.ups.com/WSDL/XOLTWS/Ship/v1.0", "ShipService");
    public final static QName ShipPort = new QName("http://www.ups.com/WSDL/XOLTWS/Ship/v1.0", "ShipPort");
    static {
        URL url = null;
        try {
            url = new URL("Ship.wsdl");
        } catch (MalformedURLException e) {
            java.util.logging.Logger.getLogger(ShipService.class.getName())
                .log(java.util.logging.Level.INFO, 
                     "Can not initialize the default wsdl from {0}", "Ship.wsdl");
        }
        WSDL_LOCATION = url;
    }

    public ShipService(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public ShipService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public ShipService() {
        super(WSDL_LOCATION, SERVICE);
    }
    

    /**
     *
     * @return
     *     returns ShipPortType
     */
    @WebEndpoint(name = "ShipPort")
    public ShipPortType getShipPort() {
        return super.getPort(ShipPort, ShipPortType.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns ShipPortType
     */
    @WebEndpoint(name = "ShipPort")
    public ShipPortType getShipPort(WebServiceFeature... features) {
        return super.getPort(ShipPort, ShipPortType.class, features);
    }

}
