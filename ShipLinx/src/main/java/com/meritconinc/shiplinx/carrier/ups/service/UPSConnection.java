package com.meritconinc.shiplinx.carrier.ups.service;

import java.io.IOException;
import java.io.StringWriter;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.HttpVersion;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.log4j.Logger;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;

import com.meritconinc.shiplinx.carrier.utils.UPSException;
import com.meritconinc.shiplinx.model.CustomerCarrier;

public class UPSConnection {

	private Logger logger = Logger.getLogger(UPSConnection.class);
	private CustomerCarrier customerCarrier;
	
	private String url;
	
	public void setUrl(String url)  {
		logger.debug("--URL-----"+url);
		this.url = url;
	} 
	
	
		
	public String send(XmlObject request, int type) throws UPSException {
		long start=0, end = 0;
		
		start = System.currentTimeMillis();
		/* prepare request XML */
		XmlOptions opts = new XmlOptions();
		opts.setSavePrettyPrint();
		opts.setUseDefaultNamespace();
		StringWriter writer = new StringWriter();
		try {
			request.save(writer,opts);
		}catch(IOException e) {
			throw new UPSException("Unable to write XML from request object: " + e.getMessage());
		}
		String requestStr = this.getAccessRequest().toString() + writer.toString();
		
		/* strip namespace decl added by xmlbeans */
		requestStr = requestStr.replaceAll("xmlns=\\\".*\\\"","");
		
		if(type == UPSAPI.REQUEST_TYPE_TRACK){
			requestStr = requestStr.replaceAll("xml-fragment", "TrackRequest");
		}
		if(type == UPSAPI.REQUEST_TYPE_VOID){
			requestStr = requestStr.replaceAll("xml-fragment", "VoidShipmentRequest");
		}
		
        logger.debug(requestStr);

		
		end = System.currentTimeMillis();		
		logger.debug("Time to convert request object to xml string " + (end-start) + "ms");
		start =end;
		
		//logger.debug(requestStr);

		/* setup http client */
		HttpClient client = new HttpClient();
		client.getParams().setVersion(HttpVersion.HTTP_1_0);
        PostMethod method = getMethod(requestStr);
        
        /* execute */
        try {
        	int statusCode = client.executeMethod(method);

        	if (statusCode != HttpStatus.SC_OK) {
        		logger.error("Method failed: " + method.getStatusLine());
        	}

            // Read the response body.
//        	InputStream is = method.getResponseBodyAsStream();
//        	String response = is.toString();
            String response = method.getResponseBodyAsString();        	        
        	
        	end = System.currentTimeMillis();		
    		logger.debug("Time to get response string from server " + (end-start) + "ms");
    		start =end;
    		
        	response = response.replaceFirst("<?.*?>","");
            if(type==UPSAPI.REQUEST_TYPE_RATE)
            	response = response.replaceFirst(">"," xmlns=\"http://www.cwsi.com/eshipper/carrier/ups/rate\">");
            else if(type==UPSAPI.REQUEST_TYPE_SHIP)
            	response = response.replaceFirst(">"," xmlns=\"http://www.cwsi.com/eshipper/carrier/ups/ship\">");
            else if(type==UPSAPI.REQUEST_TYPE_TRACK)
            	response = response.replaceFirst(">"," xmlns=\"http://www.cwsi.com/eshipper/carrier/ups/track\">");
            else if(type==UPSAPI.REQUEST_TYPE_VOID)
            	response = response.replaceFirst(">"," xmlns=\"http://www.cwsi.com/eshipper/carrier/ups/ship\">");
            response = response.replaceAll("res:", "");

            logger.debug(response);
                       
             
            //responseDocument.save(System.out,opts);
            
            end = System.currentTimeMillis();		
    		logger.debug("Time to convert response xml to object  " + (end-start) + "ms");
    		start =end;
            return response;
            
        } catch (HttpException e) {
          logger.error("Fatal protocol violation: " + e.getMessage());
          throw new UPSException("Error connecting to UPS Server: " + e.getMessage());
        } catch (IOException e) {
            logger.error("Fatal transport error: " + e.getMessage());
            throw new UPSException("Error connecting to UPS Server: " + e.getMessage());            
        } 
//            catch(XmlException e) {
//        	logger.error("Error parsing returned XML from UPS: " + e.getMessage());
//            throw new UPSException("Error reading response from UPS Server: " + e.getMessage());            
//		}
        finally {
            method.releaseConnection();
        }		
	}


	/* helper functions */
	
	private PostMethod getMethod(String request) {
		logger.debug("Sending UPS request to " + url);
		PostMethod method = new PostMethod(url);
        method.setRequestEntity(new StringRequestEntity(request));
        method.setRequestHeader("Referer","Canada Worldwide Services");
        method.setRequestHeader("Content-type","text/xml");
        
        return method;
	}
	
	private StringBuilder getAccessRequest(){
		StringBuilder stb = new StringBuilder();

		stb.append("<?xml version=\"1.0\" ?>\n<AccessRequest>\n<AccessLicenseNumber>" + customerCarrier.getProperty1() 
				+ "</AccessLicenseNumber>\n<UserId>" + 	customerCarrier.getProperty2() 
				+ "</UserId>\n<Password>" + customerCarrier.getProperty3() 
				+ "</Password>\n</AccessRequest>\n<?xml version=\"1.0\" ?>\n");
		
		logger.debug(stb);
		return stb;
	}



	public void setCustomerCarrier(CustomerCarrier customerCarrier) {
		this.customerCarrier = customerCarrier;
	}
}
