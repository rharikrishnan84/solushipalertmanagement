package com.meritconinc.shiplinx.carrier.purolator.ws.shipping.proxy;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.FaultAction;

/**
 * Service Contract Class - ShippingServiceContract
 *
 * This class was generated by Apache CXF 2.5.2
 * 2012-07-13T18:50:30.751-04:00
 * Generated source version: 2.5.2
 * 
 */
@WebService(targetNamespace = "http://purolator.com/pws/service/v1", name = "ShippingServiceContract")
@XmlSeeAlso({ObjectFactory.class})
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public interface ShippingServiceContract {

    /**
     * ValidateShipment
     * @param request ValidateShipmentRequest
     * @return ValidateShipmentResponse
     *             
     */
    @WebResult(name = "ValidateShipmentResponse", targetNamespace = "http://purolator.com/pws/datatypes/v1", partName = "ValidateShipmentResponse")
    @Action(input = "http://purolator.com/pws/service/v1/ValidateShipment", output = "http://purolator.com/pws/service/v1/ShippingServiceContract/ValidateShipmentResponse", fault = {@FaultAction(className = ShippingServiceContractValidateShipmentValidationFaultFaultFaultMessage.class, value = "http://purolator.com/pws/service/v1/ShippingServiceContract/ValidateShipmentValidationFaultFault")})
    @WebMethod(operationName = "ValidateShipment", action = "http://purolator.com/pws/service/v1/ValidateShipment")
    public ValidateShipmentResponseContainer validateShipment(
        @WebParam(partName = "ValidateShipmentRequest", name = "ValidateShipmentRequest", targetNamespace = "http://purolator.com/pws/datatypes/v1")
        ValidateShipmentRequestContainer validateShipmentRequest
    ) throws ShippingServiceContractValidateShipmentValidationFaultFaultFaultMessage;

    /**
     * VoidShipment
     * @param request VoidShipmentRequest
     * @return VoidShipmentResponse
     *             
     */
    @WebResult(name = "VoidShipmentResponse", targetNamespace = "http://purolator.com/pws/datatypes/v1", partName = "VoidShipmentResponse")
    @Action(input = "http://purolator.com/pws/service/v1/VoidShipment", output = "http://purolator.com/pws/service/v1/ShippingServiceContract/VoidShipmentResponse", fault = {@FaultAction(className = ShippingServiceContractVoidShipmentValidationFaultFaultFaultMessage.class, value = "http://purolator.com/pws/service/v1/ShippingServiceContract/VoidShipmentValidationFaultFault")})
    @WebMethod(operationName = "VoidShipment", action = "http://purolator.com/pws/service/v1/VoidShipment")
    public VoidShipmentResponseContainer voidShipment(
        @WebParam(partName = "VoidShipmentRequest", name = "VoidShipmentRequest", targetNamespace = "http://purolator.com/pws/datatypes/v1")
        VoidShipmentRequestContainer voidShipmentRequest
    ) throws ShippingServiceContractVoidShipmentValidationFaultFaultFaultMessage;

    /**
     * Consolidate
     * @param request ConsolidateRequest
     * @return ConsolidateResponse
     *             
     */
    @WebResult(name = "ConsolidateResponse", targetNamespace = "http://purolator.com/pws/datatypes/v1", partName = "ConsolidateResponse")
    @Action(input = "http://purolator.com/pws/service/v1/Consolidate", output = "http://purolator.com/pws/service/v1/ShippingServiceContract/ConsolidateResponse", fault = {@FaultAction(className = ShippingServiceContractConsolidateValidationFaultFaultFaultMessage.class, value = "http://purolator.com/pws/service/v1/ShippingServiceContract/ConsolidateValidationFaultFault")})
    @WebMethod(operationName = "Consolidate", action = "http://purolator.com/pws/service/v1/Consolidate")
    public ConsolidateResponseContainer consolidate(
        @WebParam(partName = "ConsolidateRequest", name = "ConsolidateRequest", targetNamespace = "http://purolator.com/pws/datatypes/v1")
        ConsolidateRequestContainer consolidateRequest
    ) throws ShippingServiceContractConsolidateValidationFaultFaultFaultMessage;

    /**
     * CreateShipment
     * @param request CreateShipmentRequest
     * @return CreateShipmentResponse
     *             
     */
    @WebResult(name = "CreateShipmentResponse", targetNamespace = "http://purolator.com/pws/datatypes/v1", partName = "CreateShipmentResponse")
    @Action(input = "http://purolator.com/pws/service/v1/CreateShipment", output = "http://purolator.com/pws/service/v1/ShippingServiceContract/CreateShipmentResponse", fault = {@FaultAction(className = ShippingServiceContractCreateShipmentValidationFaultFaultFaultMessage.class, value = "http://purolator.com/pws/service/v1/ShippingServiceContract/CreateShipmentValidationFaultFault")})
    @WebMethod(operationName = "CreateShipment", action = "http://purolator.com/pws/service/v1/CreateShipment")
    public CreateShipmentResponseContainer createShipment(
        @WebParam(partName = "CreateShipmentRequest", name = "CreateShipmentRequest", targetNamespace = "http://purolator.com/pws/datatypes/v1")
        CreateShipmentRequestContainer createShipmentRequest
    ) throws ShippingServiceContractCreateShipmentValidationFaultFaultFaultMessage;
}
