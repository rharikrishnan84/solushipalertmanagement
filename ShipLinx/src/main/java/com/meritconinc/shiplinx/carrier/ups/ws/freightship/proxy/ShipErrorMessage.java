
package com.meritconinc.shiplinx.carrier.ups.ws.freightship.proxy;

import javax.xml.ws.WebFault;


/**
 * This class was generated by Apache CXF 2.5.0
 * 2011-12-10T08:17:40.030-05:00
 * Generated source version: 2.5.0
 */

@WebFault(name = "Errors", targetNamespace = "http://www.ups.com/XMLSchema/XOLTWS/Error/v1.1")
public class ShipErrorMessage extends Exception {
    
    private com.meritconinc.shiplinx.carrier.ups.ws.freightship.proxy.Errors errors;

    public ShipErrorMessage() {
        super();
    }
    
    public ShipErrorMessage(String message) {
        super(message);
    }
    
    public ShipErrorMessage(String message, Throwable cause) {
        super(message, cause);
    }

    public ShipErrorMessage(String message, com.meritconinc.shiplinx.carrier.ups.ws.freightship.proxy.Errors errors) {
        super(message);
        this.errors = errors;
    }

    public ShipErrorMessage(String message, com.meritconinc.shiplinx.carrier.ups.ws.freightship.proxy.Errors errors, Throwable cause) {
        super(message, cause);
        this.errors = errors;
    }

    public com.meritconinc.shiplinx.carrier.ups.ws.freightship.proxy.Errors getFaultInfo() {
        return this.errors;
    }
}
