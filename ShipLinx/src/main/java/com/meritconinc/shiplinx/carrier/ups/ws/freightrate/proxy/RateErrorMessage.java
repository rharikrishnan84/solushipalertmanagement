
package com.meritconinc.shiplinx.carrier.ups.ws.freightrate.proxy;

import javax.xml.ws.WebFault;


/**
 * This class was generated by Apache CXF 2.5.0
 * 2011-12-08T21:34:08.973-05:00
 * Generated source version: 2.5.0
 */

@WebFault(name = "Errors", targetNamespace = "http://www.ups.com/XMLSchema/XOLTWS/Error/v1.1")
public class RateErrorMessage extends Exception {
    
    private com.meritconinc.shiplinx.carrier.ups.ws.freightrate.proxy.Errors errors;

    public RateErrorMessage() {
        super();
    }
    
    public RateErrorMessage(String message) {
        super(message);
    }
    
    public RateErrorMessage(String message, Throwable cause) {
        super(message, cause);
    }

    public RateErrorMessage(String message, com.meritconinc.shiplinx.carrier.ups.ws.freightrate.proxy.Errors errors) {
        super(message);
        this.errors = errors;
    }

    public RateErrorMessage(String message, com.meritconinc.shiplinx.carrier.ups.ws.freightrate.proxy.Errors errors, Throwable cause) {
        super(message, cause);
        this.errors = errors;
    }

    public com.meritconinc.shiplinx.carrier.ups.ws.freightrate.proxy.Errors getFaultInfo() {
        return this.errors;
    }
}
