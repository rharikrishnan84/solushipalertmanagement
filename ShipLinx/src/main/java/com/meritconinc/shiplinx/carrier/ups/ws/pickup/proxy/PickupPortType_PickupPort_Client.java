
package com.meritconinc.shiplinx.carrier.ups.ws.pickup.proxy;

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
 * 2011-12-08T21:31:27.805-05:00
 * Generated source version: 2.5.0
 * 
 */
public final class PickupPortType_PickupPort_Client {

    private static final QName SERVICE_NAME = new QName("http://www.ups.com/WSDL/XOLTWS/Pickup/v1.1", "PickupService");

    private PickupPortType_PickupPort_Client() {
    }

    public static void main(String args[]) throws java.lang.Exception {
        URL wsdlURL = PickupService.WSDL_LOCATION;
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
      
        PickupService ss = new PickupService(wsdlURL, SERVICE_NAME);
        PickupPortType port = ss.getPickupPort();  
        
        {
        System.out.println("Invoking processPickupCreation...");
        com.meritconinc.shiplinx.carrier.ups.ws.pickup.proxy.PickupCreationRequest _processPickupCreation_body = null;
        com.meritconinc.shiplinx.carrier.ups.ws.pickup.proxy.UPSSecurity _processPickupCreation_upsSecurity = null;
        try {
            com.meritconinc.shiplinx.carrier.ups.ws.pickup.proxy.PickupCreationResponse _processPickupCreation__return = port.processPickupCreation(_processPickupCreation_body, _processPickupCreation_upsSecurity);
            System.out.println("processPickupCreation.result=" + _processPickupCreation__return);

        } catch (PickupCreationErrorMessage e) { 
            System.out.println("Expected exception: PickupCreationErrorMessage has occurred.");
            System.out.println(e.toString());
        }
            }
        {
        System.out.println("Invoking processPickupPendingStatus...");
        com.meritconinc.shiplinx.carrier.ups.ws.pickup.proxy.PickupPendingStatusRequest _processPickupPendingStatus_body = null;
        com.meritconinc.shiplinx.carrier.ups.ws.pickup.proxy.UPSSecurity _processPickupPendingStatus_upsSecurity = null;
        try {
            com.meritconinc.shiplinx.carrier.ups.ws.pickup.proxy.PickupPendingStatusResponse _processPickupPendingStatus__return = port.processPickupPendingStatus(_processPickupPendingStatus_body, _processPickupPendingStatus_upsSecurity);
            System.out.println("processPickupPendingStatus.result=" + _processPickupPendingStatus__return);

        } catch (PickupPendingStatusErrorMessage e) { 
            System.out.println("Expected exception: PickupPendingStatusErrorMessage has occurred.");
            System.out.println(e.toString());
        }
            }
        {
        System.out.println("Invoking processPickupCancel...");
        com.meritconinc.shiplinx.carrier.ups.ws.pickup.proxy.PickupCancelRequest _processPickupCancel_body = null;
        com.meritconinc.shiplinx.carrier.ups.ws.pickup.proxy.UPSSecurity _processPickupCancel_upsSecurity = null;
        try {
            com.meritconinc.shiplinx.carrier.ups.ws.pickup.proxy.PickupCancelResponse _processPickupCancel__return = port.processPickupCancel(_processPickupCancel_body, _processPickupCancel_upsSecurity);
            System.out.println("processPickupCancel.result=" + _processPickupCancel__return);

        } catch (PickupCancelErrorMessage e) { 
            System.out.println("Expected exception: PickupCancelErrorMessage has occurred.");
            System.out.println(e.toString());
        }
            }
        {
        System.out.println("Invoking processPickupRate...");
        com.meritconinc.shiplinx.carrier.ups.ws.pickup.proxy.PickupRateRequest _processPickupRate_body = null;
        com.meritconinc.shiplinx.carrier.ups.ws.pickup.proxy.UPSSecurity _processPickupRate_upsSecurity = null;
        try {
            com.meritconinc.shiplinx.carrier.ups.ws.pickup.proxy.PickupRateResponse _processPickupRate__return = port.processPickupRate(_processPickupRate_body, _processPickupRate_upsSecurity);
            System.out.println("processPickupRate.result=" + _processPickupRate__return);

        } catch (PickupRateErrorMessage e) { 
            System.out.println("Expected exception: PickupRateErrorMessage has occurred.");
            System.out.println(e.toString());
        }
            }

        System.exit(0);
    }

}
