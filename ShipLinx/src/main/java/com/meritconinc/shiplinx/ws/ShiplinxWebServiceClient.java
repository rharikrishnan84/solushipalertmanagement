package com.meritconinc.shiplinx.ws;

/**
 * Please modify this class to meet your needs
 * This class is not complete
 */

import java.io.File;
import java.io.StringWriter;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.namespace.QName;

import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.meritconinc.shiplinx.carrier.dhl.DHLXmlWSClient;
import com.meritconinc.shiplinx.carrier.utils.DHLException;
import com.meritconinc.shiplinx.model.Rating;
import com.meritconinc.shiplinx.model.ShippingOrder;
import com.meritconinc.shiplinx.ws.proxy.RatingException;
import com.meritconinc.shiplinx.ws.proxy.RatingPortType;
import com.meritconinc.shiplinx.ws.proxy.RatingService;
import com.meritconinc.shiplinx.ws.proxy.ShippingPortType;
import com.meritconinc.shiplinx.ws.proxy.ShippingService;
import com.meritconinc.shiplinx.ws.proxy.datatypes.SecurityWSType;
import com.meritconinc.shiplinx.ws.proxy.ratingrequest.RatingRequestWSType;
import com.meritconinc.shiplinx.ws.proxy.ratingresponse.RatingResponseWSType;
import com.meritconinc.shiplinx.ws.proxy.shippingrequest.ShippingRequestWSType;
import com.meritconinc.shiplinx.ws.proxy.shippingresponse.ShippingResponseWSType;
import com.sun.xml.bind.marshaller.NamespacePrefixMapper;

//@XmlRootElement(name = "ShippingRequestWSType", namespace = "http://www.meritcon.com")
//@XmlRootElement(name = "ShippingResponseWSType", namespace = "http://www.meritcon.com")
public final class ShiplinxWebServiceClient {
	private static final Logger log = LogManager.getLogger(ShiplinxWebServiceClient.class);
    private static final QName RATING_SERVICE_NAME = new QName("http://www.proxy.ws.shiplinx.meritconinc.com/", "RatingService");
//    private static final String RATING_WSDL_LOCATION ="https://localhost:8443/shiplinx/services/RatingService?wsdl";
    private static final String RATING_WSDL_LOCATION ="https://www.soluship.com/services/RatingService?wsdl";
    private static final QName SHIPPING_SERVICE_NAME = new QName("http://www.proxy.ws.shiplinx.meritconinc.com/", "ShippingService");
    private static final String SHIPPING_WSDL_LOCATION ="https://localhost:8443/shiplinx/services/ShippingService?wsdl";
//    private static final String SHIPPING_WSDL_LOCATION ="https://www.soluship.com/services/ShippingService?wsdl";
    private StringWriter stringWriter = null;
    private static final String ROOT_OUT_FOLDER = "E:\\temp\\Shiplinx\\";
    
    private ShiplinxWebServiceClient() {
    	
    } 
    
    public static ShippingResponseWSType testShipOrder(ShippingOrder o, Rating r) {
        try {
	        URL wsdlURL = new URL(SHIPPING_WSDL_LOCATION);
	        
	        JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
	        factory.getInInterceptors().add(new LoggingInInterceptor());
			factory.getOutInterceptors().add(new LoggingOutInterceptor());	 
			
	        ShippingService ss = new ShippingService(wsdlURL, SHIPPING_SERVICE_NAME);
	        ShippingPortType port = ss.getShippingServiceImplPort();  
	        
	        log.debug("Invoking shipOrder...");
	        ShippingRequestWSType req = new ShippingRequestWSType();
	        req.setOrder(ShiplinxWebServiceModelHelper.convertOrder(o, null));
//	        req.setRating(ShiplinxWebServiceModelHelper.convertRating(r));
	        
	        // ------------ Following code will create XML file for the request, uncomment following code
	        ShiplinxWebServiceClient wsClient = new ShiplinxWebServiceClient();
	        String packageName = ShippingRequestWSType.class.getPackage().getName();
	        String sReq = wsClient.transform(req, packageName, 
	        								"http://www.proxy.ws.shiplinx.meritconinc.com/ShippingRequest ShippingRequest.xsd");
	        String fileName = getUniqueFileName("Shipping_Request");
	        DHLXmlWSClient.outputToFSFile(sReq.getBytes(), fileName);
	        log.debug(sReq);	
	        log.debug(fileName);
	        // ----------------------------------------------------------
	        
	        SecurityWSType sec = new SecurityWSType();
	        sec.setAPIUserName("bus_admin");
	        sec.setAPIUserPassword("passw0rd");

            ShippingResponseWSType res = port.shipOrder(req, sec);
            log.debug("rateShipment.result=" + res);
            return res;
        } catch (Exception e) { 
        	log.debug("Expected exception: ShippingException has occurred." + e.getMessage());
            e.printStackTrace();
        	log.error(e);
        }
        
        return null;
    }    
    
    public static RatingResponseWSType testRateShipment(ShippingOrder o) {
        try {
	        URL wsdlURL = new URL(RATING_WSDL_LOCATION);
	        
	        JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
	        factory.getInInterceptors().add(new LoggingInInterceptor());
			factory.getOutInterceptors().add(new LoggingOutInterceptor());	 
			
	        RatingService ss = new RatingService(wsdlURL, RATING_SERVICE_NAME);
	        RatingPortType port = ss.getRatingServiceImplPort();  
	        
	        log.debug("Invoking rateShipment...");
	        RatingRequestWSType req = new RatingRequestWSType();
	        req.setOrder(ShiplinxWebServiceModelHelper.convertOrder(o, null));
	        
	        // ------------ Following code will create XML file for the request, uncomment following code
	        ShiplinxWebServiceClient wsClient = new ShiplinxWebServiceClient();
	        String packageName = RatingRequestWSType.class.getPackage().getName();
	        String sReq = wsClient.transform(req, packageName, 
	        								"http://www.proxy.ws.shiplinx.meritconinc.com/RatingRequest RatingRequest.xsd");
	        String fileName = getUniqueFileName("Rating_Request");
	        DHLXmlWSClient.outputToFSFile(sReq.getBytes(), fileName);
	        System.out.println(sReq);	
	        log.debug(fileName);
	        // ----------------------------------------------------------
	        
//	        RatingRequestWSType xmlReq = (RatingRequestWSType) getXmlRequest(packageName, fileName);
	        
	        
	        SecurityWSType sec = new SecurityWSType();
	        sec.setAPIUserName("bus_admin");
	        sec.setAPIUserPassword("passw0rd");

            RatingResponseWSType res = port.rateShipment(req, sec);
            log.debug("rateShipment.result=" + res);
            return res;
        } catch (Exception e) { 
        	log.debug("Expected exception: RatingException has occurred." + e.getMessage());
        	e.printStackTrace();
        	log.debug(e);
        }
        
        return null;
    }
    
//	private static Object getXmlRequest(String packageName, String fileName) {
//		// TODO Auto-generated method stub
//		try {
//			String xmlString = inputFromFSFile(fileName);
//			Unmarshaller unmarshaller = getUnmarshaller(packageName);
//			return unmarshaller.unmarshal(new StringReader( xmlString ) );
//		} catch (Exception e) {
//			e.printStackTrace();
//			log.error(e);
//		}		
//		return null;
//	}

//	private static Unmarshaller getUnmarshaller(String packageName) {
//		// TODO Auto-generated method stub
//		try {
//			JAXBContext context = JAXBContext.newInstance(packageName);
//			Unmarshaller unmarshaller = context.createUnmarshaller();
//			return unmarshaller;
//		} catch (Exception e) {
//			e.printStackTrace();
//			log.error(e);
//		}
//		return null;
//	}

//	private static String inputFromFSFile(String fileName) {
//		// TODO Auto-generated method stub
//		try {
//		    BufferedReader reader = new BufferedReader( new FileReader (fileName));
//		    String line  = null;
//		    StringBuilder stringBuilder = new StringBuilder();
//		    String ls = System.getProperty("line.separator");
//		    while( ( line = reader.readLine() ) != null ) {
//		        stringBuilder.append( line );
//		        stringBuilder.append( ls );
//		    }
//		    return stringBuilder.toString();
//		} catch (Exception e) {
//			e.printStackTrace();
//			log.error(e);
//		}
//		return null;
//
//	}

	private static String getUniqueFileName(String filePrefix) {
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder();
//		sb.append(WebUtil.getProperty(Constants.SYSTEM_SCOPE, "BASE_PATH"));
		sb.append(ROOT_OUT_FOLDER);
//		sb.append(File.separator);
		sb.append(filePrefix);
		java.util.Date dt = new java.util.Date();
		sb.append("_");
		sb.append(dt.getTime());
		sb.append(".xml");
		
		return sb.toString();
	}

	public String transform(Object req, String packageName, String schemaLocation) {
		// TODO Auto-generated method stub
		try {
//			Marshaller marshaller = getMarshaller(Request.class.getPackage().getName());
			Marshaller marshaller = getMarshaller(packageName, schemaLocation);
			marshaller.marshal( req, getStringWriter() );
			String sReq = stringWriter.toString();
			return sReq;
			
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
			throw new DHLException(e.getMessage());
		}
	}	
	

	protected StringWriter getStringWriter() {
		if (stringWriter == null) {
			stringWriter = new StringWriter();
		}
		return stringWriter;
	}	
	
	private Marshaller getMarshaller(String packageName, String schemaLoc) throws JAXBException {
		JAXBContext context = JAXBContext.newInstance(packageName);
		Marshaller marshaller = context.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, new Boolean(true));

		marshaller.setProperty(Marshaller.JAXB_SCHEMA_LOCATION, schemaLoc);
		marshaller.setProperty("com.sun.xml.bind.namespacePrefixMapper", new MyNamespacePrefixMapperImpl());
		return marshaller;
	}
	
	class MyNamespacePrefixMapperImpl extends NamespacePrefixMapper {
		public String getPreferredPrefix(String namespaceUri,
				String suggestion, boolean requirePrefix) {
			if ("xsd".equals(namespaceUri)) {
				return "";
			}
			return "req";
		}
	}	
	
    

    public static void main(String args[]) throws java.lang.Exception {
        URL wsdlURL = RatingService.WSDL_LOCATION;
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
      
        RatingService ss = new RatingService(wsdlURL, RATING_SERVICE_NAME);
        RatingPortType port = ss.getRatingServiceImplPort();  
        
        {
        System.out.println("Invoking rateShipment...");
        RatingRequestWSType req = new RatingRequestWSType();
        SecurityWSType sec = new SecurityWSType();
        try {
            RatingResponseWSType res = port.rateShipment(req, sec);
            System.out.println("rateShipment.result=" + res);

        } catch (RatingException e) { 
            System.out.println("Expected exception: RatingException has occurred.");
            System.out.println(e.toString());
        }
            }

        System.exit(0);
    }
    


}
