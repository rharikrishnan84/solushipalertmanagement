
package com.meritconinc.shiplinx.carrier.purolator.ws.shippingdocuments.proxy;

import javax.xml.ws.WebFault;


/**
 * This class was generated by Apache CXF 2.5.2
 * 2012-07-16T20:28:49.595-04:00
 * Generated source version: 2.5.2
 */

@WebFault(name = "ValidationFault", targetNamespace = "http://www.microsoft.com/practices/EnterpriseLibrary/2007/01/wcf/validation")
public class ShippingDocumentsServiceContractGetDocumentsValidationFaultFaultFaultMessage extends Exception {
    
    private com.meritconinc.shiplinx.carrier.purolator.ws.shippingdocuments.proxy.ValidationFault validationFault;

    public ShippingDocumentsServiceContractGetDocumentsValidationFaultFaultFaultMessage() {
        super();
    }
    
    public ShippingDocumentsServiceContractGetDocumentsValidationFaultFaultFaultMessage(String message) {
        super(message);
    }
    
    public ShippingDocumentsServiceContractGetDocumentsValidationFaultFaultFaultMessage(String message, Throwable cause) {
        super(message, cause);
    }

    public ShippingDocumentsServiceContractGetDocumentsValidationFaultFaultFaultMessage(String message, com.meritconinc.shiplinx.carrier.purolator.ws.shippingdocuments.proxy.ValidationFault validationFault) {
        super(message);
        this.validationFault = validationFault;
    }

    public ShippingDocumentsServiceContractGetDocumentsValidationFaultFaultFaultMessage(String message, com.meritconinc.shiplinx.carrier.purolator.ws.shippingdocuments.proxy.ValidationFault validationFault, Throwable cause) {
        super(message, cause);
        this.validationFault = validationFault;
    }

    public com.meritconinc.shiplinx.carrier.purolator.ws.shippingdocuments.proxy.ValidationFault getFaultInfo() {
        return this.validationFault;
    }
}
