
/**
 * Please modify this class to meet your needs
 * This class is not complete
 */

package com.meritconinc.shiplinx.ws.proxy;

import java.util.logging.Logger;

import com.meritconinc.shiplinx.ws.ShiplinxWebServiceAdaptor;

/**
 * This class was generated by Apache CXF 2.5.2
 * 2013-03-06T21:55:32.971-05:00
 * Generated source version: 2.5.2
 * 
 */

@javax.jws.WebService(
                      serviceName = "ShippingService",
                      portName = "ShippingServiceImplPort",
                      targetNamespace = "http://www.proxy.ws.shiplinx.meritconinc.com/",
                      wsdlLocation = "ws/ShippingService.wsdl",
                      endpointInterface = "com.meritconinc.shiplinx.ws.proxy.ShippingPortType")
                      
public class ShippingPortTypeImpl implements ShippingPortType {

    private static final Logger LOG = Logger.getLogger(ShippingPortTypeImpl.class.getName());

    /* (non-Javadoc)
     * @see com.meritconinc.shiplinx.ws.proxy.ShippingPortType#getOrderInfo(long  body ,)com.meritconinc.shiplinx.ws.proxy.datatypes.SecurityWSType  security )*
     */
    public com.meritconinc.shiplinx.ws.proxy.shippingresponse.ShippingResponseWSType getOrderInfo(long body,com.meritconinc.shiplinx.ws.proxy.datatypes.SecurityWSType security) throws GetOrderInfoException    { 
        LOG.info("Executing operation getOrderInfo");
        System.out.println(body);
        System.out.println(security);
        try { 
        	ShiplinxWebServiceAdaptor adaptor = new ShiplinxWebServiceAdaptor();
        	return adaptor.getOrderInfo(body, security);
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        //throw new GetOrderInfoException("GetOrderInfoException...");
    }

    /* (non-Javadoc)
     * @see com.meritconinc.shiplinx.ws.proxy.ShippingPortType#shipOrder(com.meritconinc.shiplinx.ws.proxy.shippingrequest.ShippingRequestWSType  body ,)com.meritconinc.shiplinx.ws.proxy.datatypes.SecurityWSType  security )*
     */
    public com.meritconinc.shiplinx.ws.proxy.shippingresponse.ShippingResponseWSType shipOrder(com.meritconinc.shiplinx.ws.proxy.shippingrequest.ShippingRequestWSType body,com.meritconinc.shiplinx.ws.proxy.datatypes.SecurityWSType security) throws ShippingException    { 
        LOG.info("Executing operation shipOrder");
        System.out.println(body);
        System.out.println(security);
        try {
        	ShiplinxWebServiceAdaptor adaptor = new ShiplinxWebServiceAdaptor();
        	return adaptor.shipOrder(body, security);
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        //throw new ShippingException("ShippingException...");
    }

}
