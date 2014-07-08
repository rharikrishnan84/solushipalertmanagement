
package com.meritconinc.shiplinx.carrier.ups.ws.track.proxy;

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
 * 2011-12-08T22:13:47.810-05:00
 * Generated source version: 2.5.0
 * 
 */
public final class TrackPortType_TrackPort_Client {

    private static final QName SERVICE_NAME = new QName("http://www.ups.com/WSDL/XOLTWS/Track/v2.0", "TrackService");

    private TrackPortType_TrackPort_Client() {
    }

    public static void main(String args[]) throws java.lang.Exception {
        URL wsdlURL = TrackService.WSDL_LOCATION;
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
      
        TrackService ss = new TrackService(wsdlURL, SERVICE_NAME);
        TrackPortType port = ss.getTrackPort();  
        
        {
        System.out.println("Invoking processTrack...");
        com.meritconinc.shiplinx.carrier.ups.ws.track.proxy.TrackRequest _processTrack_body = null;
        com.meritconinc.shiplinx.carrier.ups.ws.track.proxy.UPSSecurity _processTrack_upsSecurity = null;
        try {
            com.meritconinc.shiplinx.carrier.ups.ws.track.proxy.TrackResponse _processTrack__return = port.processTrack(_processTrack_body, _processTrack_upsSecurity);
            System.out.println("processTrack.result=" + _processTrack__return);

        } catch (TrackErrorMessage e) { 
            System.out.println("Expected exception: TrackErrorMessage has occurred.");
            System.out.println(e.toString());
        }
            }

        System.exit(0);
    }

}
