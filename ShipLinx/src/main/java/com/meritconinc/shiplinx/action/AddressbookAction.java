package com.meritconinc.shiplinx.action;


import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringBufferInputStream;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.meritconinc.mmr.utilities.MessageUtil;
import com.meritconinc.mmr.utilities.security.UserUtil;
import com.meritconinc.shiplinx.carrier.purolator.stub.ServiceAvailabilityWebServiceClient;
import com.meritconinc.shiplinx.exception.ShiplinxException;
import com.meritconinc.shiplinx.model.Address;
import com.meritconinc.shiplinx.model.AddressType;
import com.meritconinc.shiplinx.model.Country;
import com.meritconinc.shiplinx.model.Customer;
import com.meritconinc.shiplinx.model.Province;
import com.meritconinc.shiplinx.service.AddressManager;
import com.meritconinc.shiplinx.service.CustomerManager;
import com.opensymphony.xwork2.Preparable;

 

/**
 * <code>Set welcome message.</code>
 */
public class AddressbookAction extends BaseAction implements Preparable,ServletRequestAware{
	private static final long serialVersionUID	= 25092007;

	private static final Logger log = LogManager.getLogger(AddressbookAction.class);
	
	private List<Address> addressList;
	private List<String> distributionList;
	private AddressManager addressService;
	private List<Country> countries;
	private List<String> canadianprovinces;
	private List<String> usstates;
	public HttpServletRequest request;
	private File upload;
	private String name;
	private List<Province> provinces;
	private List<AddressType> brokers;
	private String distributionListSize;
	private CustomerManager customerService;
	
	private InputStream inputStream;
	public InputStream getInputStream() {
	return inputStream;
	}
	public void setInputStream(InputStream inputStream) {
	this.inputStream = inputStream;
	}
	
	public List<Country> getCountryList(){
		log.debug("CHECK COUNTRY LIST "+countries);
		return countries;
	}
	public List<String> getProvinceList(){
		return canadianprovinces;
	}
	public List<AddressType> getBrokerList(){
		return brokers;
	}
	public List<String> getStateList(){
		return usstates;
	}
	
	public String execute() throws Exception {
		getSession().remove("address");
		/**
		 * Start Modification: Remove Edit from Session
		 * Modified by: Sumanth Kulkarni S
		 */
		getSession().remove("edit");
		/**
		 * Start Modification: Remove Edit from Session
		 * Modified by: Sumanth Kulkarni S
		 */
		
		long customerId = getLoginUser().getCustomerId();
		Customer customer = customerService.getCustomerInfoByCustomerId(customerId);
		this.setCustomer(customer);
		Address address = this.getAddress();
		address.setCountryCode(UserUtil.getMmrUser().getBusiness().getAddress().getCountryCode());
		getSession().put("CountryList", MessageUtil.getCountriesList());
		getSession().put("provinces", addressService.getProvinces(address.getCountryCode()));
		getSession().put("brokers", addressService.getCustomBrokers());
		return SUCCESS;
	}

	public AddressManager getAddressService() {
		return addressService;
	}
	
	public void setAddressService(AddressManager addressService) {
		this.addressService = addressService;
	}
	
	public List<Address> getAddressList(){
		return addressList;
	}
		
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
	
	private LinkedHashMap<String, Long> addressSearchResult = new LinkedHashMap<String, Long>(); ;  
	   
	public LinkedHashMap<String, Long> getAddressSearchResult() {  
		return addressSearchResult;  
	}  
	   
	public void setAddressSearchResult(LinkedHashMap<String, Long> customerSearchResult) {  
		this.addressSearchResult = customerSearchResult;  
	}

	public String list() throws Exception {
		
		Address address = getAddress();
		getSession().put("CountryList", MessageUtil.getCountriesList());
		getSession().put("provinces", addressService.getProvinces(address.getCountryCode()));
        addressList = null;
		return searchAddress();
	}
	public String listBroker()throws Exception{
		brokers=addressService.getCustomBrokers();
		getSession().put("brokers", brokers);
		return SUCCESS;
	}

	public String listProvience() throws Exception {
		String country;
		country = request.getParameter("value");
		if(country == null || "".equals(country))
			country = "US";
		provinces=addressService.getProvinces(country);
		
		getSession().put("provinces", provinces);
		return SUCCESS;
	}
	
	public String search() throws Exception {
		 
		addressList = addressService.findAddresses(this.getAddress());
		return SUCCESS;
		
	}

	public String searchAddress() throws Exception {
		
		log.debug("-------searchToAddress---------");
		
		Address addressSearchBean = this.getAddress();
		addressSearchBean.setCustomerId(getLoginUser().getCustomerId());
		addressList = addressService.findAddresses(addressSearchBean);
				
		if(!(addressList.size()!=0))
			addActionError(getText("error.no.address.found"));
		return SUCCESS;
		
	}
	
	
	public String save() throws Exception {
		Address address = this.getAddress();
		address.setCustomerId(getLoginUser().getCustomerId());
		if(address.getAddressId()>0){
			addressService.edit(address);
			addActionMessage(getText("address.info.update.successfully"));
			//session.remove("method");
		}else{
			try {
				addressService.add(address);
				addActionMessage(getText("address.info.save.successfully"));
			} 
			catch (Exception ex) {
				addActionError(getText("error.username.taken"));
				return INPUT;
			}
		}
		getSession().remove("address");
		return SUCCESS;
	}
	
	
	public String delete(){
		Long address_ret;
		String id=request.getParameter("addressid");
		/*addressService.delete(id);
		addActionMessage(getText("address.info.delete.successfully"));*/
		long address_id=Long.parseLong(id);
				
		address_ret=addressService.findAddressId(address_id);
		if(address_ret==0){
			try{
				addressService.delete(id);
				addActionMessage(getText("address.info.delete.successfully"));
			}catch(Exception e){
				e.printStackTrace();
			}
				
		}else{
				addActionMessage(getText("address.info.update.unsuccessfully"));
		}
		return SUCCESS;
	}
	
	public String edit() throws Exception{
		String id=request.getParameter("addressid");
		Address address=addressService.findAddressById(id);
		/**
		 * Start Modification: Code to handle Edit or Add
		 * Modified by: Sumanth Kulkarni S
		 */
		getSession().put("edit", "true");
		/**
		 * End Modification: Code to handle Edit or Add
		 * Modified by: Sumanth Kulkarni S
		 */
		long customerId = getLoginUser().getCustomerId();
		Customer customer = customerService.getCustomerInfoByCustomerId(customerId);
		this.setCustomer(customer);
		getSession().put("CountryList", MessageUtil.getCountriesList());
		getSession().put("provinces", addressService.getProvinces(address.getCountryCode()));
		//To get Broker information 
		getSession().put("brokers", addressService.getCustomBrokers());
		this.setAddress(address);
		return SUCCESS;
	}
	
	
	public String upload(){
		try{
							if(null == (Boolean) getSession().get("FirstTime")){
								getSession().put("FirstTime", true);
								
							}else if(null != (Boolean) getSession().get("FirstTime") && (Boolean) getSession().get("FirstTime")==true){
								addActionMessage((String)getSession().get("AddressUploadActionMessage"));
								getSession().put("FirstTime", null);
								return SUCCESS;
							}
					}catch(Exception e){
					e.printStackTrace();
					}
		
		try{
			String strmethod = request.getParameter("method");
			String strdelete = request.getParameter("delete");
			
			if(strmethod==null){
				InputStream dis = new DataInputStream(new FileInputStream(upload));
				
				if(upload.length()>new Long(distributionListSize)*1024){
					addActionError(getText("error.upload.distribution.size"));
					return INPUT;
				}
				if(strdelete!=null)
					addressService.parseFile(getLoginUser().getCustomerId()+"", dis,name, true);
				else
					addressService.parseFile(getLoginUser().getCustomerId()+"", dis,name, false);					
				
				addActionMessage("Address Book Uploading");
				getSession().put("AddressUploadActionMessage", "Address Book Uploading");
			}
		}
		catch (ShiplinxException e) {
			getSession().put("AddressUploadActionMessage", getText("error.upload.distribution.format") + " " + e.getMessage());
				return INPUT;
			}catch (Exception e) {
				addActionError(getText("error.upload.distribution"));
				getSession().put("AddressUploadActionMessage", getText("error.upload.distribution"));
				return INPUT;
			}
			
			
			return SUCCESS;
	}
	
	
	public String uploadInit(){
		return SUCCESS;
	}
	
	public String distributionList(){
		
		distributionList = addressService.findDistributionListForCustomer(getLoginUser().getCustomerId());
		if(!(distributionList.size()!=0))
			addActionError(getText("error.no.address.found"));
		return SUCCESS;
		
	}
	
	public String deleteDistributionList(){
		
		String distributionListId=request.getParameter("distributionListId");
		addressService.deleteDistributionList(distributionListId, getLoginUser().getCustomerId());
		addActionMessage(getText("distributionList.info.delete.successfully"));
		return SUCCESS;
	}
	
/*	public String getEdit(){
		return edit;
	}
	public void setEdit(String edit){
		this.edit=edit;
	}*/

	public void prepare() throws Exception {
		//setAvailableRoles();
	}
	public List<String> getDistributionList() {
		return distributionList;
	}
	public File getUpload() {
		return upload;
	}
	public void setUpload(File upload) {
		this.upload = upload;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Province> getProvinces() {
		return provinces;
	}
	public void setProvinces(List<Province> provinces) {
		this.provinces = provinces;
	}	
	/*
	 * custom broker getter and setter 23/10
	 */
	public List<AddressType> getCustomBroker() {
		return brokers;
	}
	public void setCustomBroker(List<AddressType> customBroker) {
		this.brokers = customBroker;
	}
	public String getDistributionListSize() {
		return distributionListSize;
	}
	public void setDistributionListSize(String distributionListSize) {
		this.distributionListSize = distributionListSize;
	}
	
	public Address getAddress() {
		Address address = (Address)getSession().get("address");
		if (address == null) {
			address = new Address();
			setAddress(address);
		}
		return address;
	}
	public void setAddress(Address address) {

		getSession().put("address", address);
	}	
	
	public String listAddresses(){
		
		String customerId = null;
		String searchParameter = request.getParameter("searchString");
		if(searchParameter==null)
			searchParameter = request.getParameter("q");
		if(request.getParameter("shippingOrder.customerId")!=null)
			customerId = request.getParameter("shippingOrder.customerId");
		else if(request.getParameter("customerId")!= null)
			customerId = request.getParameter("customerId");
		else
			customerId = request.getParameter("pickup.customerId");
		log.debug("Search string is : " + searchParameter);
		if(customerId==null) //don't return any addresses, this should not happen
			return SUCCESS;
		//if(searchParameter==null)	
			//return SUCCESS;
		
		Address a = new Address();
		a.setContactName(searchParameter);
		a.setPostalCode(searchParameter);
		a.setCity(searchParameter);
		a.setAbbreviationName(searchParameter);
		a.setProvinceCode(searchParameter);
		a.setDistributionName(searchParameter);
		a.setCustomerId(Long.valueOf(customerId));
		List<Address> address = null;
		if ((request.getParameter("customerId")!=null) && 
			(request.getParameter("shipToCountryCode")!=null)){
			log.debug("Test searchCustomsBrokerAddress");
			customerId = request.getParameter("customerId");
			String shipToCountryCode=request.getParameter("shipToCountryCode");
			address = addressService.searchCustomsBrokerAddress(customerId,shipToCountryCode);			
		}
		else if((request.getParameter("customerId")!=null) && ("CN".equalsIgnoreCase(request.getParameter("shipperType")))){
				log.debug("Test searchShipperAndConsigneeAddress");
				customerId = request.getParameter("customerId");
				address = addressService.searchShipperAndConsigneeAddress(customerId);
				
			}else{
			log.debug("Test searchAddresses");
			address = addressService.searchAddresses(a);
		}
		if(request.getParameter("customerId")!=null)
			addressSearchResult.put("None",0L);
		for(Address add: address){
			addressSearchResult.put(add.getLongAddress(),add.getAddressId());
		}
		if(request.getParameter("q")!=null){
		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("text/html");

			PrintWriter out = response.getWriter();
			for (Map.Entry<String, Long> entry : addressSearchResult.entrySet()) {
			   out.println(entry.getKey()+"|"+entry.getValue());
			}
		} 
		catch (IOException e) {
			log.error("Exception occured while retrieving addresses in listAddresses  "+e);
			addActionError(MessageUtil.getMessage("warehouseOrder.autocompleter.locations.error"));
		}
		}
				
		return SUCCESS;
	}
	
	public String getAddressSuggest() throws Exception {

		String postalCode = (String)request.getParameter("postalCode");
		String country = (String)request.getParameter("countryCode");
		
		Address address = new Address();
		address.setPostalCode(postalCode);
		address.setCountryCode(country);
		
		ServiceAvailabilityWebServiceClient zipCodeValidator = new ServiceAvailabilityWebServiceClient();
		address = zipCodeValidator.getSuggestedAddress(address);
		
		if(address!=null){
			inputStream = new StringBufferInputStream(address.getCity());	
			this.getCustomer().getAddress().setProvinceCode(address.getProvinceCode());
			this.getAddress().setProvinceCode(address.getProvinceCode());
		}
		return SUCCESS;
	}
	public CustomerManager getCustomerService() {
		return customerService;
	}
	public void setCustomerService(CustomerManager customerService) {
		this.customerService = customerService;
	}
	
}
