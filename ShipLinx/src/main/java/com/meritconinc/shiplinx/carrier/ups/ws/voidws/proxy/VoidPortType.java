package com.meritconinc.shiplinx.carrier.ups.ws.voidws.proxy;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 * This class was generated by Apache CXF 2.5.0
 * 2011-12-10T08:24:48.692-05:00
 * Generated source version: 2.5.0
 * 
 */
@WebService(targetNamespace = "http://www.ups.com/WSDL/XOLTWS/Void/v1.1", name = "VoidPortType")
@XmlSeeAlso({ObjectFactory.class})
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public interface VoidPortType {

    @WebResult(name = "VoidShipmentResponse", targetNamespace = "http://www.ups.com/XMLSchema/XOLTWS/Void/v1.1", partName = "Body")
    @WebMethod(operationName = "ProcessVoid", action = "http://onlinetools.ups.com/webservices/VoidBinding/v1.1")
    public VoidShipmentResponse processVoid(
        @WebParam(partName = "Body", name = "VoidShipmentRequest", targetNamespace = "http://www.ups.com/XMLSchema/XOLTWS/Void/v1.1")
        VoidShipmentRequest body,
        @WebParam(partName = "UPSSecurity", name = "UPSSecurity", targetNamespace = "http://www.ups.com/XMLSchema/XOLTWS/UPSS/v1.0", header = true)
        UPSSecurity upsSecurity
    ) throws VoidErrorMessage;
}
