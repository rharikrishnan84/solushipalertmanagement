package com.meritconinc.shiplinx.carrier.dhl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.apache.log4j.Logger;

import com.meritconinc.mmr.constants.Constants;
import com.meritconinc.mmr.utilities.WebUtil;
import com.meritconinc.shiplinx.carrier.dhl.xml.DCTResponse;
import com.meritconinc.shiplinx.carrier.dhl.xml.ErrorResponse;
import com.meritconinc.shiplinx.carrier.dhl.xml.PickupErrorResponse;
import com.meritconinc.shiplinx.carrier.dhl.xml.PickupResponse;
import com.meritconinc.shiplinx.carrier.dhl.xml.Request;
import com.meritconinc.shiplinx.carrier.dhl.xml.Response;
import com.meritconinc.shiplinx.carrier.dhl.xml.ShipmentRatingErrorResponse;
import com.meritconinc.shiplinx.carrier.dhl.xml.ShipmentRatingResponse;
import com.meritconinc.shiplinx.carrier.dhl.xml.ShipmentValidateErrorResponse;
import com.meritconinc.shiplinx.carrier.dhl.xml.ShipmentValidateResponse;
import com.meritconinc.shiplinx.carrier.dhl.xml.shipvalidateglobal.response.ShipmentResponse;
import com.meritconinc.shiplinx.carrier.utils.DHLException;
import com.meritconinc.shiplinx.utils.ShiplinxConstants;
import com.sun.xml.bind.marshaller.NamespacePrefixMapper;

public class DHLXmlWSClient {
	private static final Logger log = Logger.getLogger(DHLXmlWSClient.class);
	public static final String HTTP_URL_TEST = "https://xmlpitest-ea.dhl.com/XMLShippingServlet";
	public static final String HTTP_URL = "https://xmlpi-ea.dhl.com/XMLShippingServlet";
	private StringWriter stringWriter = null;
	private static Map<String, JAXBContext> contextMap = new HashMap<String, JAXBContext>();

	public String transform(Object req) {
		// TODO Auto-generated method stub
		try {
			Marshaller marshaller = getMarshaller(Request.class.getPackage().getName());
			marshaller.marshal( req, getStringWriter() );
			String sReq = stringWriter.toString();
			return sReq;
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new DHLException(e.getMessage());
		}
	}	

	public String globalTransform(Object req) {
		try {
			Marshaller marshaller = getGlobalMarshaller(com.meritconinc.shiplinx.carrier.dhl.xml.shipvalidateglobal.request.Request.class.getPackage().getName());
			marshaller.marshal( req, getStringWriter() );
			String sReq = stringWriter.toString();
			return sReq;
		 	
		} catch (Exception e) {
			e.printStackTrace();
			throw new DHLException(e.getMessage());
		}
	}	
	



	protected StringWriter getStringWriter() {
		if (stringWriter == null) {
			stringWriter = new StringWriter();
		}
		return stringWriter;
	}	
	
	private Marshaller getGlobalMarshaller(String packageName) throws JAXBException {
		String schemaLoc = "http://www.dhl.com ship-val-global-res.xsd";
		JAXBContext context = contextMap.get(packageName);
		if(context==null){
			context = JAXBContext.newInstance(packageName);
			contextMap.put(packageName, context);
		}
		Marshaller marshaller = context.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, new Boolean(true));

		marshaller.setProperty(Marshaller.JAXB_SCHEMA_LOCATION, schemaLoc);
		//marshaller.setProperty("com.sun.xml.bind.namespacePrefixMapper", new MyNamespacePrefixMapperImpl());
		marshaller.setProperty("com.sun.xml.bind.namespacePrefixMapper", new MyGlobalNamespacePrefixMapperImpl());
//		marshaller.setEventHandler(new MyValidationEventHandler());
		return marshaller;
	}
	class MyGlobalNamespacePrefixMapperImpl extends NamespacePrefixMapper {
		public String getPreferredPrefix(String namespaceUri,
				String suggestion, boolean requirePrefix) {
			if ("xsd".equals(namespaceUri)) {
				return "";
			}
			return "req";
		}
	}	
	

	private Marshaller getMarshaller(String packageName) throws JAXBException {
		String schemaLoc = "http://www.dhl.com ship-val-req.xsd";
		JAXBContext context = contextMap.get(packageName);
		if(context==null){
			context = JAXBContext.newInstance(packageName);
			contextMap.put(packageName, context);
		}
		Marshaller marshaller = context.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, new Boolean(true));

		marshaller.setProperty(Marshaller.JAXB_SCHEMA_LOCATION, schemaLoc);
		marshaller.setProperty("com.sun.xml.bind.namespacePrefixMapper", new MyNamespacePrefixMapperImpl());
//		marshaller.setEventHandler(new MyValidationEventHandler());
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
	
	
	/*
	 * This constructor is used to do the following important operations 1) Read
	 * a request XML 2) Connect to Server 3) Send the request XML 4) Receive
	 * response XML message 5) Calls a private method to write the response XML
	 * message
	 * 
	 * @param requestMessagePath The path of the request XML message to be send
	 * to server @param httpURL The http URL to connect ot the server (e.g.
	 * http://<ip address>:<port>/application name/Servlet name) @param
	 * responseMessagePath The path where the response XML message is to be
	 * stored
	 */
	public String submitRequest(String request, String urlString) throws DHLException {
		try {
			DHLXmlWSClient.outputToFSFile(request.getBytes(), WebUtil.getProperty(Constants.SYSTEM_SCOPE, "BASE_PATH") + File.separator + "request.xml");
			log.debug("DHL Request:\n" + request);
			
			URL servletURL = new URL(urlString);

			HttpURLConnection servletConnection = null;
			servletConnection = (HttpURLConnection) servletURL.openConnection();
			servletConnection.setDoOutput(true); // to allow

			servletConnection.setDoInput(true);
			servletConnection.setUseCaches(false);
			servletConnection.setRequestMethod("POST"); 
			servletConnection.setReadTimeout(ShiplinxConstants.READ_TIME_OUT);

			servletConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			String len = Integer.toString(request.getBytes().length);
			servletConnection.setRequestProperty("Content-Length", len);

			servletConnection.connect();
			OutputStreamWriter wr = new OutputStreamWriter(servletConnection.getOutputStream());
			wr.write(request);
			wr.flush();
			wr.close();

			InputStream inputStream = null;
			inputStream = servletConnection.getInputStream();
			StringBuffer response = new StringBuffer();
			int printResponse;

			// Reading the response into StringBuffer
			while ((printResponse = inputStream.read()) != -1) {
				response.append((char) printResponse);
			}
			inputStream.close();

			String sResponse = response.toString();
			DHLXmlWSClient.outputToFSFile(sResponse.getBytes(), WebUtil.getProperty(Constants.SYSTEM_SCOPE, "BASE_PATH") + File.separator + "response.xml");
			log.debug("DHL Response:\n" + sResponse);
			return sResponse;
		} catch (MalformedURLException mfURLex) {
			log.error("MalformedURLException " + mfURLex.getMessage());
			throw new DHLException(mfURLex.getMessage());
		} catch (IOException e) {
			log.error("IOException " + e.getMessage());
			// e.printStackTrace();
			throw new DHLException(e.getMessage());
		}
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
		}
		return false;
	}


	public ShipmentValidateResponse processResponse(String res) throws DHLException {
		// TODO Auto-generated method stub
		try {
			Unmarshaller unmarshaller = getUnmarshaller(ShipmentValidateResponse.class.getPackage().getName());
//			unmarshaller.setValidating(true);
//			unmarshaller.setSchema(null);
			
			ShipmentValidateResponse svRes = (ShipmentValidateResponse) 
										unmarshaller.unmarshal(new StringReader( res ) );

			if (svRes != null) {
				Response response = svRes.getResponse();
				log.error("Response-MessageReference: " + response.getServiceHeader().getMessageReference());
			}
			return svRes;
		} catch (Exception e) {
			e.printStackTrace();
			throw new DHLException(e.getMessage());
		}		
	}
	
	public ShipmentValidateErrorResponse processShipValidateErrorResponse(String res) {
		// TODO Auto-generated method stub
		try {
			Unmarshaller unmarshaller = getUnmarshaller(ShipmentValidateErrorResponse.class.getPackage().getName());
			ShipmentValidateErrorResponse errRes = (ShipmentValidateErrorResponse) 
												unmarshaller.unmarshal(new StringReader( res ) );
//			unmarshaller.setValidating(false);
//			unmarshaller.setSchema(null);
//			if (errRes != null && errRes.getResponse() != null && errRes.getResponse().getStatus()!= null && 
//					errRes.getResponse().getStatus().getCondition() != null) {
//				for(int i=0; i<errRes.getResponse().getStatus().getCondition().size(); i++) {
//					Condition c = (Condition) errRes.getResponse().getStatus().getCondition().get(i);
//					log.error("Codition Code: " + c.getConditionCode() + " Data:" + c.getConditionData());
//				}
//				return errRes;
//			}
			return errRes;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new DHLException(ex.getMessage());
		}
//		return null;
	}


	protected Unmarshaller getUnmarshaller(String packageName) throws JAXBException {
		JAXBContext context = contextMap.get(packageName);
		if(context==null){
			context = JAXBContext.newInstance(packageName);
			contextMap.put(packageName, context);
		}
		Unmarshaller unmarshaller = context.createUnmarshaller();
		return unmarshaller;
	}	
	
    public static void main(String[] args) {
    	DHLXmlWSClient xmlWsClient = new DHLXmlWSClient();
//    	String xmlFileRes = readFileContents("D:\\ShipLinx\\Docs\\DHL\\Toolkit\\XML_PI_Toolkit\\documents\\UnitTestPlan\\ShipmentValidation\\Response\\AM\\ShipmentValidateResponse_AM_to_AM_PieceEnabled_With2Pcs_PcsSeg.xml");
    	String xmlFileRes = readFileContents("D:\\temp\\response.xml");
//    	xmlFileRes = DHLXmlDataConverter.customizeResponse(xmlFileRes);
//    	ShipmentValidateResponse res = xmlWsClient.processResponse(xmlFileRes);
    	
		if (xmlFileRes.contains("ShipmentValidateErrorResponse")) {
			ShipmentValidateErrorResponse errRes = xmlWsClient.processShipValidateErrorResponse(xmlFileRes);
			DHLXmlDataConverter.myDebug("ErrorResponse:" + errRes);
		} else {
			// Valid Response
			ShipmentValidateResponse res = xmlWsClient.processResponse(xmlFileRes);
			// Generate Label
			DHLXmlDataConverter.myDebug("ShipmentValidateResponse:" + res);
		}    	
    	
		// Generate Label
//		DHLXmlDataConverter.myDebug("ShipmentValidateResponse:" + res);
    }


	public static String readFileContents(String fullFilePath) {
		// TODO Auto-generated method stub
		try {
			//Preparing file inputstream from a file        
			FileInputStream fis = new FileInputStream(fullFilePath);

			//Getting size of the stream
			int fisSize = fis.available();
			byte[] buffer = new byte[fisSize];

			//Reading file into buffer                                                                      
			fis.read(buffer);
			fis.close();

			String clientRequestXml = new String(buffer);
			
			return clientRequestXml;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}


	public ShipmentRatingErrorResponse processShipmentRatingErrorResponse(String res) {
		// TODO Auto-generated method stub
		try {
			Unmarshaller unmarshaller = getUnmarshaller(ShipmentRatingErrorResponse.class.getPackage().getName());
			ShipmentRatingErrorResponse errRes = (ShipmentRatingErrorResponse) 
												unmarshaller.unmarshal(new StringReader( res ) );

			return errRes;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new DHLException(ex.getMessage());
		}
	}


	public ShipmentRatingResponse processShipmentRatingResponse(String res) {
		// TODO Auto-generated method stub
		try {
			Unmarshaller unmarshaller = getUnmarshaller(ShipmentRatingResponse.class.getPackage().getName());
			
			ShipmentRatingResponse svRes = (ShipmentRatingResponse) 
										unmarshaller.unmarshal(new StringReader( res ) );

			if (svRes != null) {
				Response response = svRes.getResponse();
				log.error("Response-MessageReference: " + response.getServiceHeader().getMessageReference());
			}
			return svRes;
		} catch (Exception e) {
			e.printStackTrace();
			throw new DHLException(e.getMessage());
		}
	}


	public ErrorResponse processErrorResponse(String res) {
		// TODO Auto-generated method stub
		try {
			Unmarshaller unmarshaller = getUnmarshaller(ErrorResponse.class.getPackage().getName());
			ErrorResponse errRes = (ErrorResponse) unmarshaller.unmarshal(new StringReader( res ) );
			return errRes;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new DHLException(ex.getMessage());
		}
	}


	public DCTResponse processDCTErrorResponse(String res) {
		// TODO Auto-generated method stub
		try {
			Unmarshaller unmarshaller = getUnmarshaller(DCTResponse.class.getPackage().getName());
			DCTResponse errRes = (DCTResponse) unmarshaller.unmarshal(new StringReader( res ) );
			return errRes;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new DHLException(ex.getMessage());
		}
	}

	public DCTResponse processDCTResponse(String res) {
		// TODO Auto-generated method stub
		try {
			Unmarshaller unmarshaller = getUnmarshaller(DCTResponse.class.getPackage().getName());
			DCTResponse dctRes = (DCTResponse) unmarshaller.unmarshal(new StringReader( res ) );
			if (dctRes != null) {
				if (dctRes.getGetCapabilityResponse() != null) {
					DCTResponse.GetCapabilityResponse.Response cRes = dctRes.getGetCapabilityResponse().getResponse();
					log.debug("DCTResponse.GetCapabilityResponse.Response: " + cRes.getServiceHeader().getMessageReference());
				}
				if (dctRes.getGetQuoteResponse() != null) {
					DCTResponse.GetQuoteResponse.Response qRes = dctRes.getGetQuoteResponse().getResponse();
					log.debug("DCTResponse.GetQuoteResponse.Response: " + qRes.getServiceHeader().getMessageReference());
				}
				
			}
			return dctRes;
		} catch (Exception e) {
			e.printStackTrace();
			throw new DHLException(e.getMessage());
		}
	}
	
	public PickupResponse processBookPickupResponse(String res) {
		// TODO Auto-generated method stub
		try {
			Unmarshaller unmarshaller = getUnmarshaller(PickupResponse.class.getPackage().getName());
			
			PickupResponse svRes = (PickupResponse) 
										unmarshaller.unmarshal(new StringReader( res ) );

			if (svRes != null) {
				Response response = svRes.getResponse();
				log.error("Response-MessageReference: " + response.getServiceHeader().getMessageReference());
			}
			return svRes;
		} catch (Exception e) {
			e.printStackTrace();
			throw new DHLException(e.getMessage());
		}
	}
	
	public PickupErrorResponse processPickupErrorResponse(String res) {
		// TODO Auto-generated method stub
		try {
			Unmarshaller unmarshaller = getUnmarshaller(PickupErrorResponse.class.getPackage().getName());
			
			PickupErrorResponse svRes = (PickupErrorResponse) 
										unmarshaller.unmarshal(new StringReader( res ) );

			if (svRes != null) {
				ErrorResponse response = svRes.getResponse();
				log.error("Response-MessageReference: " + response.getServiceHeader().getMessageReference());
			}
			return svRes;
		} catch (Exception e) {
			e.printStackTrace();
			throw new DHLException(e.getMessage());
		}
	}

	public com.meritconinc.shiplinx.carrier.dhl.xml.shipvalidateglobal.response.ShipmentResponse processGlobalResponse(String res) throws DHLException {
		try {
			Unmarshaller unmarshaller = getUnmarshaller(ShipmentResponse.class.getPackage().getName());
			
			ShipmentResponse svRes = (ShipmentResponse) 
										unmarshaller.unmarshal(new StringReader( res ) );

			if (svRes != null) {
				com.meritconinc.shiplinx.carrier.dhl.xml.shipvalidateglobal.response.Response response = svRes.getResponse();
				log.error("Response-MessageReference: " + response.getServiceHeader().getMessageReference());
			}
			return svRes;
		} catch (Exception e) {
			e.printStackTrace();
			throw new DHLException(e.getMessage());
		}		
	}
	
	public com.meritconinc.shiplinx.carrier.dhl.xml.ShipmentValidateErrorResponse processGlobalShipValidateErrorResponse(String res) {
		// TODO Auto-generated method stub
		try {
			if (res != null && !res.trim().isEmpty()) {
				if(!res.contains("ShipmentValidateErrorResponse"))
					res = res.replaceAll("ErrorResponse", "ShipmentValidateErrorResponse");
				Unmarshaller unmarshaller = getUnmarshaller(ShipmentValidateErrorResponse.class.getPackage().getName());
				ShipmentValidateErrorResponse errRes = (ShipmentValidateErrorResponse) unmarshaller.unmarshal(new StringReader( res ) );
				return errRes;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public static String statusConditionToString(List<com.meritconinc.shiplinx.carrier.dhl.xml.Condition> condition) {
		StringBuilder sb = new StringBuilder();
		if (condition != null) {
			for(int i=0; i<condition.size(); i++) {
				com.meritconinc.shiplinx.carrier.dhl.xml.Condition c = (com.meritconinc.shiplinx.carrier.dhl.xml.Condition) condition.get(i);
				sb.append("[Codition Code:" + c.getConditionCode() );
				sb.append("][Data:" + c.getConditionData() + "]");
			}
		}
		return sb.toString();
	}


}
