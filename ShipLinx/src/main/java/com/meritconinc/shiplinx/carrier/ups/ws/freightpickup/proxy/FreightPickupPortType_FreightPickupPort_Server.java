
package com.meritconinc.shiplinx.carrier.ups.ws.freightpickup.proxy;

import javax.xml.ws.Endpoint;

/**
 * This class was generated by Apache CXF 2.5.0
 * 2011-12-09T23:07:23.632-05:00
 * Generated source version: 2.5.0
 * 
 */
 
public class FreightPickupPortType_FreightPickupPort_Server{

    protected FreightPickupPortType_FreightPickupPort_Server() throws java.lang.Exception {
        System.out.println("Starting Server");
        Object implementor = new FreightPickupPortTypeImpl();
        String address = "https://wwwcie.ups.com/webservices/FreightPickup";
        Endpoint.publish(address, implementor);
    }
    
    public static void main(String args[]) throws java.lang.Exception { 
        new FreightPickupPortType_FreightPickupPort_Server();
        System.out.println("Server ready..."); 
        
        Thread.sleep(5 * 60 * 1000); 
        System.out.println("Server exiting");
        System.exit(0);
    }
}
