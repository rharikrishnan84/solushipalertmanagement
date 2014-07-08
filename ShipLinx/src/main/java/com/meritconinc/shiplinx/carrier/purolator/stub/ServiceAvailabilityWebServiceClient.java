package com.meritconinc.shiplinx.carrier.purolator.stub;

import java.util.ArrayList;
import java.util.List;

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
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.meritconinc.shiplinx.carrier.purolator.PurolatorAPI;
import com.meritconinc.shiplinx.carrier.purolator.ws.serviceavailability.proxy.ArrayOfService;
import com.meritconinc.shiplinx.carrier.purolator.ws.serviceavailability.proxy.ArrayOfShortAddress;
import com.meritconinc.shiplinx.carrier.purolator.ws.serviceavailability.proxy.ArrayOfSuggestedAddress;
import com.meritconinc.shiplinx.carrier.purolator.ws.serviceavailability.proxy.GetServicesOptionsRequestContainer;
import com.meritconinc.shiplinx.carrier.purolator.ws.serviceavailability.proxy.GetServicesOptionsResponseContainer;
import com.meritconinc.shiplinx.carrier.purolator.ws.serviceavailability.proxy.InformationalMessage;
import com.meritconinc.shiplinx.carrier.purolator.ws.serviceavailability.proxy.Language;
import com.meritconinc.shiplinx.carrier.purolator.ws.serviceavailability.proxy.RequestContext;
import com.meritconinc.shiplinx.carrier.purolator.ws.serviceavailability.proxy.ResponseInformation;
import com.meritconinc.shiplinx.carrier.purolator.ws.serviceavailability.proxy.ServiceAvailabilityServiceContract;
import com.meritconinc.shiplinx.carrier.purolator.ws.serviceavailability.proxy.ServiceAvailabilityServiceContractGetServicesOptionsValidationFaultFaultFaultMessage;
import com.meritconinc.shiplinx.carrier.purolator.ws.serviceavailability.proxy.ServiceAvailabilityServiceContractValidateCityPostalCodeZipValidationFaultFaultFaultMessage;
import com.meritconinc.shiplinx.carrier.purolator.ws.serviceavailability.proxy.ShortAddress;
import com.meritconinc.shiplinx.carrier.purolator.ws.serviceavailability.proxy.SuggestedAddress;
import com.meritconinc.shiplinx.carrier.purolator.ws.serviceavailability.proxy.ValidateCityPostalCodeZipRequestContainer;
import com.meritconinc.shiplinx.carrier.purolator.ws.serviceavailability.proxy.ValidateCityPostalCodeZipResponseContainer;
import com.meritconinc.shiplinx.carrier.utils.UPSException;
import com.meritconinc.shiplinx.model.Address;
import com.meritconinc.shiplinx.model.CustomerCarrier;
import com.meritconinc.shiplinx.model.ShippingOrder;
import com.meritconinc.shiplinx.utils.ShiplinxConstants;

public class ServiceAvailabilityWebServiceClient {
	private static final Logger log = LogManager.getLogger(ServiceAvailabilityWebServiceClient.class);
	
	public static final String TEST_URL_ZIPVALIDATION = "https://devwebservices.purolator.com/PWS/V1/ServiceAvailability/ServiceAvailabilityService.asmx";
	public static final String PROD_URL_ZIPVALIDATION = "https://webservices.purolator.com/PWS/V1/ServiceAvailability/ServiceAvailabilityService.asmx";
	
		public ServiceAvailabilityWebServiceClient(){
		}
	

	public Address getSuggestedAddress(Address address){
		long start = System.currentTimeMillis();

		Address suggestedAddress = new Address();

		ValidateCityPostalCodeZipRequestContainer request = buildRequest(address);
		GetServicesOptionsRequestContainer optionsRequest = getOptionsRequest();
		ValidateCityPostalCodeZipResponseContainer respContainer = sendRequest(request, optionsRequest);

		long end = System.currentTimeMillis();
		log.debug("Time to look up zip code: " + (end-start));
		
		// Display the response
		ResponseInformation respInf = respContainer.getResponseInformation();
		StringBuilder messages = new StringBuilder();
		if (respInf != null &&  respInf.getErrors() != null && respInf.getErrors().getError() != null){

			for (com.meritconinc.shiplinx.carrier.purolator.ws.serviceavailability.proxy.Error error : respInf.getErrors().getError()) {
				log.debug("Error code :"+ error.getCode());
				log.debug("Error description :"+ error.getDescription());
				log.debug("Additional Information :"+ error.getAdditionalInformation());
				messages.append(error.getDescription() + "\n");
			}
		}

		if (respInf.getInformationalMessages() != null && respInf.getInformationalMessages().getInformationalMessage().size() > 0) {
			for (InformationalMessage msg : respInf.getInformationalMessages().getInformationalMessage()){
				log.debug("InformationalMessage ");
				log.debug(msg.getCode());
				log.debug("message "+ msg.getMessage());
				messages.append(msg.getMessage() + "\n");
			}
		}
		//log.debug("messages.toString():"+messages.toString());
		//if (messages.toString().length() > 0)
		//	throw new  EshipperLTLException(messages.toString());
			
		JAXBElement<ArrayOfSuggestedAddress> suggestedAddresses = respContainer.getSuggestedAddresses();
		if (suggestedAddresses != null && suggestedAddresses.getValue().getSuggestedAddress().size() > 0){
			log.debug("Suggested Addresses:");

			for (SuggestedAddress addr : suggestedAddresses.getValue().getSuggestedAddress()){

				log.debug("Address ");
				if (addr.getAddress() != null) {
					log.debug("City :"+ address.getCity() + " / " + addr.getAddress().getCity());
					log.debug("Province :" + address.getProvinceCode() + " / " + addr.getAddress().getProvince());
					log.debug("Country :"+ address.getCountryCode() + " / " + addr.getAddress().getCountry());
					log.debug("PostalCode :"+ address.getPostalCode() + " / " + addr.getAddress().getPostalCode());
					suggestedAddress.setCity(addr.getAddress().getCity());
					suggestedAddress.setPostalCode((addr.getAddress().getPostalCode()));
					suggestedAddress.setCountryCode((addr.getAddress().getCountry()));
					if(addr.getAddress().getProvince().equalsIgnoreCase("QC"))
						suggestedAddress.setProvinceCode("PQ");
					else if(addr.getAddress().getProvince().equalsIgnoreCase("NL"))
						suggestedAddress.setProvinceCode("NF");						
					else
						suggestedAddress.setProvinceCode((addr.getAddress().getProvince()));
				}
			}
		}
		else {
			log.debug("Suggested Addresses not available");
			return null;
		}
		
		return suggestedAddress;

		
	}
	
	public ValidateCityPostalCodeZipResponseContainer sendRequest(ValidateCityPostalCodeZipRequestContainer request,
			GetServicesOptionsRequestContainer optionsRequest) throws UPSException{

		ValidateCityPostalCodeZipResponseContainer response = null;
		try {
			JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();

			factory.getInInterceptors().add(new LoggingInInterceptor());
			factory.getOutInterceptors().add(new LoggingOutInterceptor());
			factory.setServiceClass(ServiceAvailabilityServiceContract.class);
			AuthorizationPolicy authorization = new AuthorizationPolicy();
			if(ShiplinxConstants.isTestMode()){
				authorization.setUserName("2b702cc9-0496-4a84-b8ea-ab9e92c513ca");
				authorization.setPassword("(/Ryb%0}");
				factory.setAddress(TEST_URL_ZIPVALIDATION);
			}
			else{
				authorization.setUserName("57e6f3e8d9c44bc788358c2728b09ae6");
				authorization.setPassword("Nqf%XaZ^");
				factory.setAddress(PROD_URL_ZIPVALIDATION);
			}

			ServiceAvailabilityServiceContract client = (ServiceAvailabilityServiceContract) factory.create();
			
			Client clientProxy = ClientProxy.getClient(client);
			HTTPConduit http = (HTTPConduit) clientProxy.getConduit();

			HTTPClientPolicy httpClientPolicy = new HTTPClientPolicy();

			httpClientPolicy.setConnectionTimeout(36000);
			httpClientPolicy.setAllowChunking(false);
			httpClientPolicy.setReceiveTimeout(32000);
			
			
			http.setAuthorization(authorization);
		
			// ----------- RequestContext
			RequestContext reqContext = getRequestContext();
			
			List<Header> headers = new ArrayList<Header>();
			Header dummyHeader = new Header(new QName("http://purolator.com/pws/datatypes/v1", "RequestContext"), reqContext,
			                                new JAXBDataBinding(RequestContext.class));
			headers.add(dummyHeader);

			
			((BindingProvider)client).getRequestContext().put(Header.HEADER_LIST, headers);

			response = client.validateCityPostalCodeZip(request);
			
			log.debug("Response:" + response);
		} 
		catch(ServiceAvailabilityServiceContractValidateCityPostalCodeZipValidationFaultFaultFaultMessage sem){
			sem.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}

		return response;
	}	
	
	private RequestContext getRequestContext() {
		// TODO Auto-generated method stub
		RequestContext reqContext = new RequestContext();
		reqContext.setVersion("1.0");
		reqContext.setLanguage(Language.EN);
		reqContext.setGroupID("1");
		reqContext.setRequestReference("RequestReference");
		return reqContext;
	}

	private GetServicesOptionsRequestContainer getOptionsRequest() {
		GetServicesOptionsRequestContainer reqOptions = new GetServicesOptionsRequestContainer();
		reqOptions.setBillingAccountNumber("9999999999");
		
		return reqOptions;
	}

	public ValidateCityPostalCodeZipRequestContainer buildRequest(Address address) {
		// TODO Auto-generated method stub
		ValidateCityPostalCodeZipRequestContainer req =
			new ValidateCityPostalCodeZipRequestContainer();
		req.setAddresses(getAddresses(address));
		return req;
	}

	private ArrayOfShortAddress getAddresses(Address address) {
		// TODO Auto-generated method stub
		ArrayOfShortAddress addresses = new ArrayOfShortAddress();
		addresses.getShortAddress().add(getAddress(address));
		
		return addresses;
	}

	private ShortAddress getAddress(Address sourceAddress) {
		// TODO Auto-generated method stub
		ShortAddress address = new ShortAddress();
		address.setCity(sourceAddress.getCity());
		address.setCountry(sourceAddress.getCountryCode());
		address.setPostalCode(sourceAddress.getPostalCode());
		address.setProvince(sourceAddress.getProvinceCode());
		return address;
	}
	
	public JAXBElement<ArrayOfService> getServiceOptions(ShippingOrder order, CustomerCarrier customerCarrier){
		GetServicesOptionsRequestContainer request = new GetServicesOptionsRequestContainer();

		request.setReceiverAddress(getAddress(order.getToAddress()));
		request.setSenderAddress(getAddress(order.getFromAddress()));
		request.setBillingAccountNumber(customerCarrier.getAccountNumber1());
//		request.setShipmentDate(value)
		if(request.getSenderAddress().getProvince().equalsIgnoreCase("NF"))
			request.getSenderAddress().setProvince("NL");
		if(request.getReceiverAddress().getProvince().equalsIgnoreCase("PQ"))
			request.getReceiverAddress().setProvince("QC");
		if(request.getReceiverAddress().getProvince().equalsIgnoreCase("NF"))
			request.getReceiverAddress().setProvince("NL");
		if(request.getSenderAddress().getProvince().equalsIgnoreCase("PQ"))
			request.getSenderAddress().setProvince("QC");

		
		GetServicesOptionsResponseContainer respContainer = sendOptionsRequest(request, customerCarrier);

		// Display the response
		ResponseInformation respInf = respContainer.getResponseInformation();
		StringBuilder messages = new StringBuilder();
		if (respInf != null &&  respInf.getErrors() != null && respInf.getErrors().getError() != null){

			for (com.meritconinc.shiplinx.carrier.purolator.ws.serviceavailability.proxy.Error error : respInf.getErrors().getError()) {
				log.debug("Error code :"+ error.getCode());
				log.debug("Error description :"+ error.getDescription());
				log.debug("Additional Information :"+ error.getAdditionalInformation());
				messages.append(error.getDescription() + "\n");
			}
		}

		if (respInf.getInformationalMessages() != null && respInf.getInformationalMessages().getInformationalMessage().size() > 0) {
			for (InformationalMessage msg : respInf.getInformationalMessages().getInformationalMessage()){
				log.debug("InformationalMessage ");
				log.debug(msg.getCode());
				log.debug("message "+ msg.getMessage());
				messages.append(msg.getMessage() + "\n");
			}
		}
		
		return respContainer.getServices();	
		
	}

	private GetServicesOptionsResponseContainer sendOptionsRequest(	GetServicesOptionsRequestContainer optionsRequest, CustomerCarrier customerCarrier) throws UPSException{

		GetServicesOptionsResponseContainer response = null;
		try {
			JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();

			factory.getInInterceptors().add(new LoggingInInterceptor());
			factory.getOutInterceptors().add(new LoggingOutInterceptor());
			factory.setServiceClass(ServiceAvailabilityServiceContract.class);
//			factory.setAddress(TEST_URL_ZIPVALIDATION);
			if(ShiplinxConstants.isTestMode())
				factory.setAddress(PurolatorAPI.TEST_URL_AVAIL);
			else
				factory.setAddress(PurolatorAPI.LIVE_URL_AVAIL);
			
			ServiceAvailabilityServiceContract client = (ServiceAvailabilityServiceContract) factory.create();
			
			Client clientProxy = ClientProxy.getClient(client);
			HTTPConduit http = (HTTPConduit) clientProxy.getConduit();

			HTTPClientPolicy httpClientPolicy = new HTTPClientPolicy();

			httpClientPolicy.setConnectionTimeout(36000);
			httpClientPolicy.setAllowChunking(false);
			httpClientPolicy.setReceiveTimeout(32000);
			
			AuthorizationPolicy authorization = new AuthorizationPolicy();

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

			response = client.getServicesOptions(optionsRequest);
			
			log.debug("Response:" + response);
		} 
		catch(ServiceAvailabilityServiceContractGetServicesOptionsValidationFaultFaultFaultMessage sem){
			sem.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}

		return response;
	}	

}
