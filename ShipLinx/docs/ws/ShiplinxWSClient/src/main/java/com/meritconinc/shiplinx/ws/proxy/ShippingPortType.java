package com.meritconinc.shiplinx.ws.proxy;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 * This class was generated by Apache CXF 2.5.2
 * 2012-04-29T13:56:13.695-04:00
 * Generated source version: 2.5.2
 * 
 */
@WebService(targetNamespace = "http://www.proxy.ws.shiplinx.meritconinc.com/", name = "ShippingPortType")
@XmlSeeAlso({com.meritconinc.shiplinx.ws.proxy.shippingresponse.ObjectFactory.class, com.meritconinc.shiplinx.ws.proxy.shippingrequest.ObjectFactory.class, com.meritconinc.shiplinx.ws.proxy.datatypes.ObjectFactory.class})
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public interface ShippingPortType {

    @WebResult(name = "ShippingResponse", targetNamespace = "http://www.proxy.ws.shiplinx.meritconinc.com/ShippingResponse", partName = "Body")
    @WebMethod(operationName = "ShipOrder", action = "http://onlinetools.meritcon.com/webservices/ShippingBinding/v1.1")
    public com.meritconinc.shiplinx.ws.proxy.shippingresponse.ShippingResponseWSType shipOrder(
        @WebParam(partName = "Body", name = "ShippingRequest", targetNamespace = "http://www.proxy.ws.shiplinx.meritconinc.com/ShippingRequest")
        com.meritconinc.shiplinx.ws.proxy.shippingrequest.ShippingRequestWSType body,
        @WebParam(partName = "Security", name = "Security", targetNamespace = "http://www.proxy.ws.shiplinx.meritconinc.com/ShippingRequest", header = true)
        com.meritconinc.shiplinx.ws.proxy.datatypes.SecurityWSType security
    ) throws ShippingException;
}
