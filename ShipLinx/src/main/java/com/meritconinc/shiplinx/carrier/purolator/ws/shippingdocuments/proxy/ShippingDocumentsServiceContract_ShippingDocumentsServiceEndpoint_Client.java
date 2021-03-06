
package com.meritconinc.shiplinx.carrier.purolator.ws.shippingdocuments.proxy;

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
import javax.xml.ws.Action;
import javax.xml.ws.FaultAction;

/**
 * This class was generated by Apache CXF 2.5.2
 * 2012-07-16T20:28:49.562-04:00
 * Generated source version: 2.5.2
 * 
 */
public final class ShippingDocumentsServiceContract_ShippingDocumentsServiceEndpoint_Client {

    private static final QName SERVICE_NAME = new QName("http://purolator.com/pws/service/v1", "ShippingDocumentsService");

    private ShippingDocumentsServiceContract_ShippingDocumentsServiceEndpoint_Client() {
    }

    public static void main(String args[]) throws java.lang.Exception {
        URL wsdlURL = ShippingDocumentsService.WSDL_LOCATION;
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
      
        ShippingDocumentsService ss = new ShippingDocumentsService(wsdlURL, SERVICE_NAME);
        ShippingDocumentsServiceContract port = ss.getShippingDocumentsServiceEndpoint();  
        
        {
        System.out.println("Invoking getShipmentManifestDocument...");
        com.meritconinc.shiplinx.carrier.purolator.ws.shippingdocuments.proxy.GetShipmentManifestDocumentRequestContainer _getShipmentManifestDocument_getShipmentManifestDocumentRequest = null;
        try {
            com.meritconinc.shiplinx.carrier.purolator.ws.shippingdocuments.proxy.GetShipmentManifestDocumentResponseContainer _getShipmentManifestDocument__return = port.getShipmentManifestDocument(_getShipmentManifestDocument_getShipmentManifestDocumentRequest);
            System.out.println("getShipmentManifestDocument.result=" + _getShipmentManifestDocument__return);

        } catch (ShippingDocumentsServiceContractGetShipmentManifestDocumentValidationFaultFaultFaultMessage e) { 
            System.out.println("Expected exception: ShippingDocumentsServiceContract_GetShipmentManifestDocument_ValidationFaultFault_FaultMessage has occurred.");
            System.out.println(e.toString());
        }
            }
        {
        System.out.println("Invoking getDocuments...");
        com.meritconinc.shiplinx.carrier.purolator.ws.shippingdocuments.proxy.GetDocumentsRequestContainer _getDocuments_getDocumentsRequest = null;
        try {
            com.meritconinc.shiplinx.carrier.purolator.ws.shippingdocuments.proxy.GetDocumentsResponseContainer _getDocuments__return = port.getDocuments(_getDocuments_getDocumentsRequest);
            System.out.println("getDocuments.result=" + _getDocuments__return);

        } catch (ShippingDocumentsServiceContractGetDocumentsValidationFaultFaultFaultMessage e) { 
            System.out.println("Expected exception: ShippingDocumentsServiceContract_GetDocuments_ValidationFaultFault_FaultMessage has occurred.");
            System.out.println(e.toString());
        }
            }

        System.exit(0);
    }

}
