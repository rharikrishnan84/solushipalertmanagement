
package com.meritconinc.shiplinx.carrier.ups.ws.ship.proxy;

import javax.xml.ws.Endpoint;

/**
 * This class was generated by Apache CXF 2.5.0
 * 2011-12-08T22:11:53.390-05:00
 * Generated source version: 2.5.0
 * 
 */
 
public class ShipPortType_ShipPort_Server{

    protected ShipPortType_ShipPort_Server() throws java.lang.Exception {
        System.out.println("Starting Server");
        Object implementor = new ShipPortTypeImpl();
        String address = "https://wwwcie.ups.com/webservices/Ship";
        Endpoint.publish(address, implementor);
    }
    
    public static void main(String args[]) throws java.lang.Exception { 
        new ShipPortType_ShipPort_Server();
        System.out.println("Server ready..."); 
        
        Thread.sleep(5 * 60 * 1000); 
        System.out.println("Server exiting");
        System.exit(0);
    }
}
