package com.meritconinc.shiplinx.carrier.ups.ws.tnt.proxy;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 * This class was generated by Apache CXF 2.5.0
 * 2011-12-10T08:32:49.370-05:00
 * Generated source version: 2.5.0
 * 
 */
@WebService(targetNamespace = "http://www.ups.com/WSDL/XOLTWS/TNT/v1.0", name = "TimeInTransitPortType")
@XmlSeeAlso({ObjectFactory.class})
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public interface TimeInTransitPortType {

    @WebResult(name = "TimeInTransitResponse", targetNamespace = "http://www.ups.com/XMLSchema/XOLTWS/tnt/v1.0", partName = "Body")
    @WebMethod(operationName = "ProcessTimeInTransit", action = "http://onlinetools.ups.com/webservices/TimeInTransitBinding/v1.0")
    public TimeInTransitResponse processTimeInTransit(
        @WebParam(partName = "Body", name = "TimeInTransitRequest", targetNamespace = "http://www.ups.com/XMLSchema/XOLTWS/tnt/v1.0")
        TimeInTransitRequest body,
        @WebParam(partName = "UPSSecurity", name = "UPSSecurity", targetNamespace = "http://www.ups.com/XMLSchema/XOLTWS/UPSS/v1.0", header = true)
        UPSSecurity upsSecurity
    ) throws TimeInTransitErrorMessage;
}
