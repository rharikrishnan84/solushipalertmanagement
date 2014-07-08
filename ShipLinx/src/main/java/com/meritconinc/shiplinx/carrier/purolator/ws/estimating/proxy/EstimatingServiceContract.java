package com.meritconinc.shiplinx.carrier.purolator.ws.estimating.proxy;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.FaultAction;

/**
 * Service Contract Class - EstimatingServiceContract
 *
 * This class was generated by Apache CXF 2.5.2
 * 2012-07-12T21:59:51.159-04:00
 * Generated source version: 2.5.2
 * 
 */
@WebService(targetNamespace = "http://purolator.com/pws/service/v1", name = "EstimatingServiceContract")
@XmlSeeAlso({ObjectFactory.class})
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public interface EstimatingServiceContract {

    /**
     * GetQuickEstimate
     * @param request GetQuickEstimateRequest
     * @return GetQuickEstimateResponse
     */
    @WebResult(name = "GetQuickEstimateResponse", targetNamespace = "http://purolator.com/pws/datatypes/v1", partName = "GetQuickEstimateResponse")
    @Action(input = "http://purolator.com/pws/service/v1/GetQuickEstimate", output = "http://purolator.com/pws/service/v1/EstimatingServiceContract/GetQuickEstimateResponse", fault = {@FaultAction(className = EstimatingServiceContractGetQuickEstimateValidationFaultFaultFaultMessage.class, value = "http://purolator.com/pws/service/v1/EstimatingServiceContract/GetQuickEstimateValidationFaultFault")})
    @WebMethod(operationName = "GetQuickEstimate", action = "http://purolator.com/pws/service/v1/GetQuickEstimate")
    public GetQuickEstimateResponseContainer getQuickEstimate(
        @WebParam(partName = "GetQuickEstimateRequest", name = "GetQuickEstimateRequest", targetNamespace = "http://purolator.com/pws/datatypes/v1")
        GetQuickEstimateRequestContainer getQuickEstimateRequest
    ) throws EstimatingServiceContractGetQuickEstimateValidationFaultFaultFaultMessage;

    /**
     * GetFullEstimate
     * @param request GetFullEstimateRequest
     * @return GetFullEstimateResponse
     */
    @WebResult(name = "GetFullEstimateResponse", targetNamespace = "http://purolator.com/pws/datatypes/v1", partName = "GetFullEstimateResponse")
    @Action(input = "http://purolator.com/pws/service/v1/GetFullEstimate", output = "http://purolator.com/pws/service/v1/EstimatingServiceContract/GetFullEstimateResponse", fault = {@FaultAction(className = EstimatingServiceContractGetFullEstimateValidationFaultFaultFaultMessage.class, value = "http://purolator.com/pws/service/v1/EstimatingServiceContract/GetFullEstimateValidationFaultFault")})
    @WebMethod(operationName = "GetFullEstimate", action = "http://purolator.com/pws/service/v1/GetFullEstimate")
    public GetFullEstimateResponseContainer getFullEstimate(
        @WebParam(partName = "GetFullEstimateRequest", name = "GetFullEstimateRequest", targetNamespace = "http://purolator.com/pws/datatypes/v1")
        GetFullEstimateRequestContainer getFullEstimateRequest
    ) throws EstimatingServiceContractGetFullEstimateValidationFaultFaultFaultMessage;
}
