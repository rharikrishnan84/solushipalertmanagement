package com.meritconinc.shiplinx.carrier.purolator.stub;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;

import org.apache.cxf.configuration.security.AuthorizationPolicy;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.headers.Header;
import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxb.JAXBDataBinding;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.cxf.transport.http.HTTPConduit;
import org.apache.cxf.transports.http.configuration.HTTPClientPolicy;
import org.apache.log4j.Logger;

import com.meritconinc.mmr.model.security.User;
import com.meritconinc.mmr.utilities.security.UserUtil;
import com.meritconinc.shiplinx.carrier.purolator.PurolatorAPI;
import com.meritconinc.shiplinx.carrier.purolator.ws.shippingdocuments.proxy.ArrayOfDocument;
import com.meritconinc.shiplinx.carrier.purolator.ws.shippingdocuments.proxy.ArrayOfDocumentCriteria;
import com.meritconinc.shiplinx.carrier.purolator.ws.shippingdocuments.proxy.Document;
import com.meritconinc.shiplinx.carrier.purolator.ws.shippingdocuments.proxy.DocumentCriteria;
import com.meritconinc.shiplinx.carrier.purolator.ws.shippingdocuments.proxy.DocumentDetail;
import com.meritconinc.shiplinx.carrier.purolator.ws.shippingdocuments.proxy.DocumentTypes;
import com.meritconinc.shiplinx.carrier.purolator.ws.shippingdocuments.proxy.Error;
import com.meritconinc.shiplinx.carrier.purolator.ws.shippingdocuments.proxy.GetDocumentsRequestContainer;
import com.meritconinc.shiplinx.carrier.purolator.ws.shippingdocuments.proxy.GetDocumentsResponseContainer;
import com.meritconinc.shiplinx.carrier.purolator.ws.shippingdocuments.proxy.InformationalMessage;
import com.meritconinc.shiplinx.carrier.purolator.ws.shippingdocuments.proxy.ObjectFactory;
import com.meritconinc.shiplinx.carrier.purolator.ws.shippingdocuments.proxy.PIN;
import com.meritconinc.shiplinx.carrier.purolator.ws.shippingdocuments.proxy.RequestContext;
import com.meritconinc.shiplinx.carrier.purolator.ws.shippingdocuments.proxy.ResponseInformation;
import com.meritconinc.shiplinx.carrier.purolator.ws.shippingdocuments.proxy.ShippingDocumentsServiceContract;
import com.meritconinc.shiplinx.carrier.purolator.ws.shippingdocuments.proxy.ShippingDocumentsServiceContractGetDocumentsValidationFaultFaultFaultMessage;
import com.meritconinc.shiplinx.carrier.utils.PurolatorException;
import com.meritconinc.shiplinx.model.CustomerCarrier;
import com.meritconinc.shiplinx.model.ShippingOrder;
import com.meritconinc.shiplinx.utils.ShiplinxConstants;

public class ShippingDocumentsServiceClient{

	private static Logger logger = Logger.getLogger(ShippingDocumentsServiceClient.class);

	private ShippingOrder order;
	private CustomerCarrier customerCarrier;
	
	public ShippingDocumentsServiceClient(ShippingOrder order, CustomerCarrier customerCarrier){
		this.order = order;
		this.customerCarrier = customerCarrier;
	}

	public ArrayList<byte[]> shippingDocumentsService(ShippingOrder shippingOrder){
		try{

			return callGetDocuments();

		} catch(Exception e){
			//e.printStackTrace();
			logger.error("Exception "+e);
		}
		return null;
	}

	public ArrayList<byte[]> callGetDocuments(){
		
		ObjectFactory objectFactory = new ObjectFactory();
		ArrayList<byte[]> labels_raw = new ArrayList<byte[]>();
		try{

			GetDocumentsRequestContainer reqContainer = new GetDocumentsRequestContainer();

			try{
				JAXBElement<GetDocumentsRequestContainer> jaxbElement =  new JAXBElement(new QName(GetDocumentsRequestContainer.class.getSimpleName()), GetDocumentsRequestContainer.class,reqContainer);
		         StringWriter writer = new StringWriter();
		         JAXBContext context = JAXBContext.newInstance(GetDocumentsRequestContainer.class);
		         context.createMarshaller().marshal(jaxbElement, writer);
				 logger.debug("Purolator File Request :" +writer.toString());
			}catch(Exception e){
				logger.error("Error occured in printing the puralotor Request : " + e.getMessage());
			}
			int c = 0;
			int numOfCriteria = 1;
			
			PIN pin = new PIN();
			User user = UserUtil.getMmrUser();
			pin.setValue(order.getMasterTrackingNum());
			
			//logger.debug("--packages-pin.getValue()-"+pin.getValue());
			DocumentCriteria documentCriteria = new DocumentCriteria();
			documentCriteria.setPIN(pin);
			
			DocumentTypes documenttypes = new DocumentTypes();
			boolean codSelected =false;
			if(isCODRequested() && PurolatorAPI.isDomesticShipment(order)
					&& !"None".equalsIgnoreCase(order.getCODPayment())){
				logger.debug("-----order.getCodPaymentType()----ss-----"+order.getCODPayment());
				codSelected = true;
			}
			if(!PurolatorAPI.isDomesticShipment(order))
			{
				if(user.getPreferredLabelSize()!=null && user.getPreferredLabelSize().equals("8 x 11")){	
					documenttypes.getDocumentType().add(PurolatorAPI.Intl_Bill_of_Lading);
				}else{
				documenttypes.getDocumentType().add(PurolatorAPI.Intl_Bill_of_Lading_Thermal);
				}
			}
			else
			{
				if(user.getPreferredLabelSize()!=null && user.getPreferredLabelSize().equals("8 x 11")){
					documenttypes.getDocumentType().add(PurolatorAPI.Domestic_Bill_of_Lading);
				}else{
					documenttypes.getDocumentType().add(PurolatorAPI.Domestic_Bill_of_Lading_Thermal);
				}
			}
			if(codSelected)
			{
				if(user.getPreferredLabelSize()!=null && user.getPreferredLabelSize().equals("8 x 11")){
					documenttypes.getDocumentType().add(PurolatorAPI.Express_Cheque_Receipt);	
				}else{
					documenttypes.getDocumentType().add(PurolatorAPI.Express_Cheque_Receipt_Thermal);
				}
			}

			documentCriteria.setDocumentTypes(objectFactory.createDocumentCriteriaDocumentTypes(documenttypes));

			DocumentCriteria documentCriteria2 = new DocumentCriteria();
			if(codSelected){
				PIN pin2 = new PIN();
				pin2.setValue(order.getCODPin());
				documentCriteria2.setPIN(pin2);
				DocumentTypes documenttypes2 = new DocumentTypes();
				if(user.getPreferredLabelSize()!=null && user.getPreferredLabelSize().equals("8 x 11")){	
					documenttypes.getDocumentType().add(PurolatorAPI.Domestic_Bill_of_Lading);
				}else{
					documenttypes.getDocumentType().add(PurolatorAPI.Domestic_Bill_of_Lading_Thermal);
				}
				documentCriteria2.setDocumentTypes(objectFactory.createDocumentCriteriaDocumentTypes(documenttypes2));
				numOfCriteria=2;
			}

			ArrayOfDocumentCriteria arr = new ArrayOfDocumentCriteria();

			arr.getDocumentCriteria().add(documentCriteria);
			
			if(numOfCriteria>1)
				arr.getDocumentCriteria().add(documentCriteria2);
			
			reqContainer.setDocumentCriterium(arr);

			// Call the service
			GetDocumentsResponseContainer response = sendDocumentRequest(reqContainer);
			
			try{
				JAXBElement<GetDocumentsResponseContainer> jaxbElement4 =  new JAXBElement<GetDocumentsResponseContainer>( 
								            new QName(GetDocumentsResponseContainer.class.getSimpleName()), GetDocumentsResponseContainer.class,response);
		         StringWriter writer4 = new StringWriter();
		         // create JAXBContext which will be used to update writer 		
		         JAXBContext context4 = JAXBContext.newInstance(GetDocumentsResponseContainer.class);
		         // marshall or convert jaxbElement containing student to xml format
		         context4.createMarshaller().marshal(jaxbElement4, writer4);
		         //print XML string representation of Student object			
		         logger.debug("Purolator Response :"+writer4.toString());
			}catch(Exception e){
				logger.error("Error occured while printing the puralotor response :"+e.getMessage());
			}
			// Display the response
			
			if(response!=null && response.getResponseInformation()!=null){
				getResponseError(response.getResponseInformation());
			}

			ArrayOfDocument documents = response.getDocuments().getValue(); 

			
			if (documents != null && documents.getDocument().size() > 0)
			{
				
				for (Document document : documents.getDocument())
				{
					if (document.getDocumentDetails() != null && document.getDocumentDetails().getDocumentDetail().size() > 0)
					{
						List<DocumentDetail> d = document.getDocumentDetails().getDocumentDetail();
						for(DocumentDetail documentDetail: d){
							logger.debug("Getting document type: " + documentDetail.getDocumentType());
							URL url1 = null;
							URLConnection connection = null;
							try{
								url1 =new java.net.URL(documentDetail.getURL());
								logger.debug("sample1");
								connection = url1.openConnection();
								logger.debug("sample2");
							}catch(Exception e){
								url1 =new java.net.URL(documentDetail.getURL());
								logger.debug("sample3");
								connection = url1.openConnection();
								logger.debug("sample4");
							}
							connection.setUseCaches( false );
							connection.setDoOutput( true );
							connection.setDoInput( true );
							InputStream inputStream = connection.getInputStream();
							InputStreamReader isr = new InputStreamReader(inputStream, java.nio.charset.Charset.forName("iso-8859-1"));

							ArrayList<Integer> builder = new ArrayList<Integer>();
							int r ;

							try{
								while((r=isr.read())!= -1){
									builder.add(r);
								}
							}catch (Exception e) {
								//e.printStackTrace();
								logger.error("Exception "+e);
							}
							byte[] bArray  = new byte[builder.size()];;
							for(int k= 0; k<builder.size();k++){
								bArray[k]=builder.get(k).byteValue();
							}
							labels_raw.add(bArray);
						}
					}
					else
					{
						logger.debug("Document Details not available");
					}
				}
				
			}
			else
			{
				logger.debug("Documents not available");
			}

			logger.debug("done");
			c = c+1;
		
			
			//logger.debug("---shippingOrder-getPackages after label generation---"+order.getPackages());

		} catch(Exception e){
			logger.error("Exception "+e, e);
		}
		return labels_raw;
	}


	private String getResponseError(ResponseInformation respInf)
	{
		StringBuilder stringBuilder = new StringBuilder();
		try{
			if (respInf == null)
				return "";

			int i = 0;
			if (respInf.getErrors() != null && respInf.getErrors().getError()!=null &&  respInf.getErrors().getError().size() > 0)
			{
				for (Error error : respInf.getErrors().getError())
				{
					i++;
					stringBuilder.append(error.getCode());
					stringBuilder.append(":");
					stringBuilder.append(error.getDescription());
					stringBuilder.append("\n");
				}
			}

			i = 0;
			if (respInf.getInformationalMessages() != null && respInf.getInformationalMessages().getInformationalMessage().size() > 0)
			{
				for (InformationalMessage msg : respInf.getInformationalMessages().getInformationalMessage())
				{
					i++;
					stringBuilder.append(msg.getCode());
					stringBuilder.append(":");
					stringBuilder.append(msg.getMessage());
					stringBuilder.append("\n");
				}
			}
		}catch (Exception e) {
			//e.printStackTrace();
			logger.error("Exception "+e);
		}

		return stringBuilder.toString();
	}

	protected boolean isCODRequested(){
		
		for(com.meritconinc.shiplinx.model.Package p: order.getPackages()){
			if(p.getCodAmount()!=null && p.getCodAmount().floatValue() > 0)
				return true;
		}
		return false;
	
	}
	
	public GetDocumentsResponseContainer sendDocumentRequest(GetDocumentsRequestContainer request) throws PurolatorException{

		GetDocumentsResponseContainer response = null;
		try {
			logger.debug("Request:" + request);
			JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();

			factory.getInInterceptors().add(new LoggingInInterceptor());
			factory.getOutInterceptors().add(new LoggingOutInterceptor());
			factory.setServiceClass(ShippingDocumentsServiceContract.class);
			if(ShiplinxConstants.isTestMode())
				factory.setAddress(PurolatorAPI.TEST_URL_DOC);
			else
				factory.setAddress(PurolatorAPI.LIVE_URL_DOC);
			
			ShippingDocumentsServiceContract client = (ShippingDocumentsServiceContract) factory.create();
			
			Client clientProxy = ClientProxy.getClient(client);
			HTTPConduit http = (HTTPConduit) clientProxy.getConduit();

			HTTPClientPolicy httpClientPolicy = new HTTPClientPolicy();

			httpClientPolicy.setConnectionTimeout(36000);
			httpClientPolicy.setAllowChunking(false);
			httpClientPolicy.setReceiveTimeout(32000);
			
			AuthorizationPolicy authorization = new AuthorizationPolicy();

			
			// Production URL
			authorization.setUserName(customerCarrier.getProperty1());
			authorization.setPassword(customerCarrier.getProperty2());
			
			http.setAuthorization(authorization);
		
			// ----------- RequestContext
			RequestContext reqContext = getRequestContext();
			List<Header> headers = new ArrayList<Header>();
			Header dummyHeader = new Header(new QName("http://purolator.com/pws/datatypes/v1", "RequestContext"), reqContext,
			                                new JAXBDataBinding(RequestContext.class));
			headers.add(dummyHeader);

			
			((BindingProvider)client).getRequestContext().put(Header.HEADER_LIST, headers);
			((BindingProvider)client).getRequestContext().put("set-jaxb-validation-event-handler", false);
			response = client.getDocuments(request);
			
			logger.debug("Response:" + response);
		} 
		catch(ShippingDocumentsServiceContractGetDocumentsValidationFaultFaultFaultMessage sem){
			logger.error("Error sending documents request to Purolator", sem);
		}
		catch (Exception e) {
			logger.error("Error sending documents request to Purolator", e);
		}

		return response;
	}	

	private RequestContext getRequestContext() {
		// TODO Auto-generated method stub
		RequestContext reqContext = new RequestContext();
		reqContext.setVersion("1.0");
		reqContext.setLanguage(com.meritconinc.shiplinx.carrier.purolator.ws.shippingdocuments.proxy.Language.EN);
		reqContext.setGroupID("1");
		reqContext.setRequestReference("RequestReference");
		return reqContext;
	}


}
