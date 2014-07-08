
package com.meritconinc.shiplinx.carrier.purolator.ws.shipping.proxy;

import javax.xml.ws.WebFault;


/**
 * This class was generated by Apache CXF 2.5.2
 * 2012-07-13T18:50:30.730-04:00
 * Generated source version: 2.5.2
 */

@WebFault(name = "ValidationFault", targetNamespace = "http://www.microsoft.com/practices/EnterpriseLibrary/2007/01/wcf/validation")
public class ShippingServiceContractCreateShipmentValidationFaultFaultFaultMessage extends Exception {
    
    private com.meritconinc.shiplinx.carrier.purolator.ws.shipping.proxy.ValidationFault validationFault;

    public ShippingServiceContractCreateShipmentValidationFaultFaultFaultMessage() {
        super();
    }
    
    public ShippingServiceContractCreateShipmentValidationFaultFaultFaultMessage(String message) {
        super(message);
    }
    
    public ShippingServiceContractCreateShipmentValidationFaultFaultFaultMessage(String message, Throwable cause) {
        super(message, cause);
    }

    public ShippingServiceContractCreateShipmentValidationFaultFaultFaultMessage(String message, com.meritconinc.shiplinx.carrier.purolator.ws.shipping.proxy.ValidationFault validationFault) {
        super(message);
        this.validationFault = validationFault;
    }

    public ShippingServiceContractCreateShipmentValidationFaultFaultFaultMessage(String message, com.meritconinc.shiplinx.carrier.purolator.ws.shipping.proxy.ValidationFault validationFault, Throwable cause) {
        super(message, cause);
        this.validationFault = validationFault;
    }

    public com.meritconinc.shiplinx.carrier.purolator.ws.shipping.proxy.ValidationFault getFaultInfo() {
        return this.validationFault;
    }
}
