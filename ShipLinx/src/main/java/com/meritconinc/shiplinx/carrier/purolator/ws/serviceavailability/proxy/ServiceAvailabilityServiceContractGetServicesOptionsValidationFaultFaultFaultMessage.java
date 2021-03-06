
package com.meritconinc.shiplinx.carrier.purolator.ws.serviceavailability.proxy;

import javax.xml.ws.WebFault;


/**
 * This class was generated by Apache CXF 2.5.0
 * 2011-12-27T20:30:28.576-05:00
 * Generated source version: 2.5.0
 */

@WebFault(name = "ValidationFault", targetNamespace = "http://www.microsoft.com/practices/EnterpriseLibrary/2007/01/wcf/validation")
public class ServiceAvailabilityServiceContractGetServicesOptionsValidationFaultFaultFaultMessage extends Exception {
    
    private com.meritconinc.shiplinx.carrier.purolator.ws.serviceavailability.proxy.ValidationFault validationFault;

    public ServiceAvailabilityServiceContractGetServicesOptionsValidationFaultFaultFaultMessage() {
        super();
    }
    
    public ServiceAvailabilityServiceContractGetServicesOptionsValidationFaultFaultFaultMessage(String message) {
        super(message);
    }
    
    public ServiceAvailabilityServiceContractGetServicesOptionsValidationFaultFaultFaultMessage(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceAvailabilityServiceContractGetServicesOptionsValidationFaultFaultFaultMessage(String message, com.meritconinc.shiplinx.carrier.purolator.ws.serviceavailability.proxy.ValidationFault validationFault) {
        super(message);
        this.validationFault = validationFault;
    }

    public ServiceAvailabilityServiceContractGetServicesOptionsValidationFaultFaultFaultMessage(String message, com.meritconinc.shiplinx.carrier.purolator.ws.serviceavailability.proxy.ValidationFault validationFault, Throwable cause) {
        super(message, cause);
        this.validationFault = validationFault;
    }

    public com.meritconinc.shiplinx.carrier.purolator.ws.serviceavailability.proxy.ValidationFault getFaultInfo() {
        return this.validationFault;
    }
}
