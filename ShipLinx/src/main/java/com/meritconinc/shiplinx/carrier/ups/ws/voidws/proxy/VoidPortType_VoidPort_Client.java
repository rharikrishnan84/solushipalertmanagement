
package com.meritconinc.shiplinx.carrier.ups.ws.voidws.proxy;

/**
 * Please modify this class to meet your needs
 * This class is not complete
 */

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 * This class was generated by Apache CXF 2.5.0
 * 2011-12-10T08:24:48.635-05:00
 * Generated source version: 2.5.0
 * 
 */
public final class VoidPortType_VoidPort_Client {

    private static final QName SERVICE_NAME = new QName("http://www.ups.com/WSDL/XOLTWS/Void/v1.1", "VoidService");

    private VoidPortType_VoidPort_Client() {
    }

    public static void main(String args[]) throws java.lang.Exception {
        URL wsdlURL = VoidService.WSDL_LOCATION;
        if (args.length > 0 && args[0] != null && !"".equals(args[0])) { 
            File wsdlFile = new File(args[0]);
            try {
                if (wsdlFile.exists()) {
                    wsdlURL = wsdlFile.toURI().toURL();
                } else {
                    wsdlURL = new URL(args[0]);
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
      
        VoidService ss = new VoidService(wsdlURL, SERVICE_NAME);
        VoidPortType port = ss.getVoidPort();  
        
        {
        System.out.println("Invoking processVoid...");
        com.meritconinc.shiplinx.carrier.ups.ws.voidws.proxy.VoidShipmentRequest _processVoid_body = null;
        com.meritconinc.shiplinx.carrier.ups.ws.voidws.proxy.UPSSecurity _processVoid_upsSecurity = null;
        try {
            com.meritconinc.shiplinx.carrier.ups.ws.voidws.proxy.VoidShipmentResponse _processVoid__return = port.processVoid(_processVoid_body, _processVoid_upsSecurity);
            System.out.println("processVoid.result=" + _processVoid__return);

        } catch (VoidErrorMessage e) { 
            System.out.println("Expected exception: VoidErrorMessage has occurred.");
            System.out.println(e.toString());
        }
            }

        System.exit(0);
    }

}
