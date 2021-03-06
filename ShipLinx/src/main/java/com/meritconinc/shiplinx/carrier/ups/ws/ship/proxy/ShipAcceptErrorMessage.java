
package com.meritconinc.shiplinx.carrier.ups.ws.ship.proxy;

import javax.xml.ws.WebFault;


/**
 * This class was generated by Apache CXF 2.5.0
 * 2011-12-08T22:11:53.351-05:00
 * Generated source version: 2.5.0
 */

@WebFault(name = "Errors", targetNamespace = "http://www.ups.com/XMLSchema/XOLTWS/Error/v1.1")
public class ShipAcceptErrorMessage extends Exception {
    
    private com.meritconinc.shiplinx.carrier.ups.ws.ship.proxy.Errors errors;

    public ShipAcceptErrorMessage() {
        super();
    }
    
    public ShipAcceptErrorMessage(String message) {
        super(message);
    }
    
    public ShipAcceptErrorMessage(String message, Throwable cause) {
        super(message, cause);
    }

    public ShipAcceptErrorMessage(String message, com.meritconinc.shiplinx.carrier.ups.ws.ship.proxy.Errors errors) {
        super(message);
        this.errors = errors;
    }

    public ShipAcceptErrorMessage(String message, com.meritconinc.shiplinx.carrier.ups.ws.ship.proxy.Errors errors, Throwable cause) {
        super(message, cause);
        this.errors = errors;
    }

    public com.meritconinc.shiplinx.carrier.ups.ws.ship.proxy.Errors getFaultInfo() {
        return this.errors;
    }
}
