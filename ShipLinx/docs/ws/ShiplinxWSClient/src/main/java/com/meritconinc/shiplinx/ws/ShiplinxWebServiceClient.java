
package com.meritconinc.shiplinx.ws;

/**
 * Please modify this class to meet your needs
 * This class is not complete
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.URL;
import java.util.UUID;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.namespace.QName;

import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

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
    private static final String RATING_WSDL_LOCATION ="https://localhost:8443/shiplinx/services/RatingService?wsdl";
    private static final String SHIPPING_WSDL_LOCATION ="https://localhost:8443/shiplinx/services/ShippingService?wsdl";
//    private static final String RATING_WSDL_LOCATION ="https://www.soluship.com/services/RatingService?wsdl";
//    private static final String SHIPPING_WSDL_LOCATION ="https://www.soluship.com/services/ShippingService?wsdl";
    private static final QName SHIPPING_SERVICE_NAME = new QName("http://www.proxy.ws.shiplinx.meritconinc.com/", "ShippingService");
    private StringWriter stringWriter = null;
    private static final String ROOT_OUT_FOLDER = "E:\\temp\\Shiplinx\\";
    
    private ShiplinxWebServiceClient() {
    	
    }
    
    public static ShippingResponseWSType testShipOrder(String requestFileName) {
        try {
	        URL wsdlURL = new URL(SHIPPING_WSDL_LOCATION);
	        
	        JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
	        factory.getInInterceptors().add(new LoggingInInterceptor());
			factory.getOutInterceptors().add(new LoggingOutInterceptor());	 
			
	        ShippingService ss = new ShippingService(wsdlURL, SHIPPING_SERVICE_NAME);
	        ShippingPortType port = ss.getShippingServiceImplPort();  
	        
	        log.debug("Invoking shipOrder...");
	        String packageName = ShippingRequestWSType.class.getPackage().getName();
	        
	        String fullReqFileName = ROOT_OUT_FOLDER + requestFileName;
	        ShippingRequestWSType xmlReq = (ShippingRequestWSType) getXmlRequest(packageName, fullReqFileName);
	        
	        SecurityWSType sec = new SecurityWSType();
	        sec.setAPIUserName("bus_admin");
	        sec.setAPIUserPassword("passw0rd");
//	        sec.setAPIUserName("icapi");
//	        sec.setAPIUserPassword("icapipass");	        
	        sec.setTransactionID(getUniqueID());

            ShippingResponseWSType res = port.shipOrder(xmlReq, sec);
            log.debug("rateShipment.result=" + res);
            
	        ShiplinxWebServiceClient wsClient = new ShiplinxWebServiceClient();
	        String resPackageName = ShippingResponseWSType.class.getPackage().getName();
	        String sReq = wsClient.transform(res, resPackageName, 
	        								"http://www.proxy.ws.shiplinx.meritconinc.com/ShippingResponse ShippingResponse.xsd");
            
	        String filePrefix = requestFileName.replace("Request", "Response");
	        filePrefix = filePrefix.replace(".xml", "");
	        
            String responseFileName = getUniqueFileName(filePrefix);
            outputToFSFile(sReq.getBytes(), responseFileName);            
            
            log.debug("Completed Successfully - Response File Name:" + responseFileName);
            return res;
        } catch (Exception e) { 
        	log.debug("Expected exception: ShippingException has occurred." + e.getMessage());
            log.error(e);
        }
        log.debug("Failed....");
        return null;
    }    
    
    public static RatingResponseWSType testRateShipment(String requestFileName) {
        try {
	        URL wsdlURL = new URL(RATING_WSDL_LOCATION);
	        
	        JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
	        factory.getInInterceptors().add(new LoggingInInterceptor());
			factory.getOutInterceptors().add(new LoggingOutInterceptor());	 
			
	        RatingService ss = new RatingService(wsdlURL, RATING_SERVICE_NAME);
	        RatingPortType port = ss.getRatingServiceImplPort();  
	        
	        log.debug("Invoking rateShipment...");
	        String packageName = RatingRequestWSType.class.getPackage().getName();
	        
	        String fullReqFileName = ROOT_OUT_FOLDER + requestFileName;
	        RatingRequestWSType xmlReq = (RatingRequestWSType) getXmlRequest(packageName, fullReqFileName);
	        
	        SecurityWSType sec = new SecurityWSType();
	        sec.setAPIUserName("bus_admin");
	        sec.setAPIUserPassword("passw0rd");
	        sec.setTransactionID(getUniqueID());

            RatingResponseWSType res = port.rateShipment(xmlReq, sec); //port.rateShipment(req, sec);
            log.debug("rateShipment.result=" + res);
            
	        ShiplinxWebServiceClient wsClient = new ShiplinxWebServiceClient();
	        String resPackageName = RatingResponseWSType.class.getPackage().getName();
	        String sReq = wsClient.transform(res, resPackageName, 
	        								"http://www.proxy.ws.shiplinx.meritconinc.com/RatingResponse RatingResponse.xsd");
            
	        String filePrefix = requestFileName.replace("Request", "Response");
	        filePrefix = filePrefix.replace(".xml", "");
	        
            String responseFileName = getUniqueFileName(filePrefix);
            outputToFSFile(sReq.getBytes(), responseFileName);
            log.debug("Completed Successfully - Response File Name:" + responseFileName);
            return res;
        } catch (Exception e) { 
        	log.debug("Expected exception: RatingException has occurred." + e.getMessage());
        	e.printStackTrace();
            log.error(e);
        }
        log.debug("Failed....");
        return null;
    }
    
	private static Object getXmlRequest(String packageName, String fileName) {
		// TODO Auto-generated method stub
		try {
			String xmlString = inputFromFSFile(fileName);
			Unmarshaller unmarshaller = getUnmarshaller(packageName);
			return unmarshaller.unmarshal(new StringReader( xmlString ) );
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}		
		return null;
	}

	private static Unmarshaller getUnmarshaller(String packageName) {
		// TODO Auto-generated method stub
		try {
			JAXBContext context = JAXBContext.newInstance(packageName);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			return unmarshaller;
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
		return null;
	}
	
	public static boolean outputToFSFile(byte [] responseData, String outputFile) {
		// TODO Auto-generated method stub
		try {
			File f = new File(outputFile);
			FileOutputStream fop = new FileOutputStream(f, false);

			fop.write(responseData);
			fop.flush();
			fop.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
		return false;
	}	

	private static String inputFromFSFile(String fileName) {
		// TODO Auto-generated method stub
		try {
		    BufferedReader reader = new BufferedReader( new FileReader (fileName));
		    String line  = null;
		    StringBuilder stringBuilder = new StringBuilder();
		    String ls = System.getProperty("line.separator");
		    while( ( line = reader.readLine() ) != null ) {
		        stringBuilder.append( line );
		        stringBuilder.append( ls );
		    }
		    return stringBuilder.toString();
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
		return null;

	}

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
			Marshaller marshaller = getMarshaller(packageName, schemaLocation);
			marshaller.marshal( req, getStringWriter() );
			String sReq = stringWriter.toString();
			return sReq;
			
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
		return null;
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
			return "res";
		}
	}	
	
	private static String getUniqueID() {
		// TODO Auto-generated method stub
		String id = UUID.randomUUID().toString();
//		id = id.replace("-", "");
		return id;
	}  

    public static void main(String args[]) throws java.lang.Exception {
//    	testRateShipment("Rating_Request_1335728383670.xml");
    	testShipOrder("Shipping_Request_4_Packages.xml");
//    	testShipOrder("Shipping_Request_4_PackagesSOLUSHIP.xml");
    }
    


}
