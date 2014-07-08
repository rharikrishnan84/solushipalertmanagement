
package com.meritconinc.shiplinx.carrier.ups.ws.pickup.proxy;

import javax.xml.ws.WebFault;


/**
 * This class was generated by Apache CXF 2.5.0
 * 2011-12-08T21:31:27.866-05:00
 * Generated source version: 2.5.0
 */

@WebFault(name = "Errors", targetNamespace = "http://www.ups.com/XMLSchema/XOLTWS/Error/v1.1")
public class PickupRateErrorMessage extends Exception {
    
    private com.meritconinc.shiplinx.carrier.ups.ws.pickup.proxy.Errors errors;

    public PickupRateErrorMessage() {
        super();
    }
    
    public PickupRateErrorMessage(String message) {
        super(message);
    }
    
    public PickupRateErrorMessage(String message, Throwable cause) {
        super(message, cause);
    }

    public PickupRateErrorMessage(String message, com.meritconinc.shiplinx.carrier.ups.ws.pickup.proxy.Errors errors) {
        super(message);
        this.errors = errors;
    }

    public PickupRateErrorMessage(String message, com.meritconinc.shiplinx.carrier.ups.ws.pickup.proxy.Errors errors, Throwable cause) {
        super(message, cause);
        this.errors = errors;
    }

    public com.meritconinc.shiplinx.carrier.ups.ws.pickup.proxy.Errors getFaultInfo() {
        return this.errors;
    }
}
