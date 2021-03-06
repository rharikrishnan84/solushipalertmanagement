
package com.meritconinc.shiplinx.carrier.ups.ws.freightrate.proxy;

import javax.xml.ws.Endpoint;

/**
 * This class was generated by Apache CXF 2.5.0
 * 2011-12-08T21:34:09.005-05:00
 * Generated source version: 2.5.0
 * 
 */
 
public class FreightRatePortType_FreightRatePort_Server{

    protected FreightRatePortType_FreightRatePort_Server() throws java.lang.Exception {
        System.out.println("Starting Server");
        Object implementor = new FreightRatePortTypeImpl();
        String address = "https://wwwcie.ups.com/webservices/FreightRate";
        Endpoint.publish(address, implementor);
    }
    
    public static void main(String args[]) throws java.lang.Exception { 
        new FreightRatePortType_FreightRatePort_Server();
        System.out.println("Server ready..."); 
        
        Thread.sleep(5 * 60 * 1000); 
        System.out.println("Server exiting");
        System.exit(0);
    }
}
