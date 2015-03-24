package com.meritconinc.shiplinx.action;

import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.meritconinc.mmr.model.admin.UserSearchCriteria;
import com.meritconinc.mmr.model.common.CountryVO;
import com.meritconinc.mmr.model.security.User;
import com.meritconinc.mmr.service.UserService;
import com.meritconinc.mmr.utilities.MessageUtil;
import com.meritconinc.mmr.utilities.security.UserUtil;
import com.meritconinc.shiplinx.model.Carrier;
import com.meritconinc.shiplinx.model.CreditCard;
import com.meritconinc.shiplinx.model.Customer;
import com.meritconinc.shiplinx.model.CustomerCarrier;
import com.meritconinc.shiplinx.model.Province;
import com.meritconinc.shiplinx.service.AddressManager;
import com.meritconinc.shiplinx.service.BusinessManager;
import com.meritconinc.shiplinx.service.CarrierServiceManager;
import com.meritconinc.shiplinx.service.CustomerManager;
import com.meritconinc.shiplinx.utils.ShiplinxConstants;
import com.meritconinc.shiplinx.utils.TimeZoneBean;
import com.meritconinc.shiplinx.utils.TimeZonesFactory;
import com.opensymphony.xwork2.Preparable;
import com.soluship.businessfilter.util.BusinessFilterUtil;

public class CustomerManagerBaseAction extends BaseAction implements Preparable,ServletRequestAware {
	private static final long serialVersionUID	= 2509200786001L;

	private static final Logger log = LogManager.getLogger(CustomerManagerBaseAction.class);
	//private Customer customer;
	private CustomerManager customerService;
	protected CarrierServiceManager carrierServiceManager;
	public HttpServletRequest request;
	private List<TimeZoneBean> timeZoneList;
	private List<CountryVO> countries;
	private List<Province> provinces;
	protected List<User> salesUsers;
	protected AddressManager addressService;
	private CustomerCarrier customerCarrier;
	protected UserService userService;
//	private Customer customer = null;
	private List<Carrier> carriers;
	protected BusinessManager businessService;
	

	@Override
	public void setServletRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		this.request = request;
	} 

	@Override
	public void prepare() throws Exception {
		// TODO Auto-generated method stub
		
	}
	public CustomerManager getService() {
		return customerService;
	}
	
	public void setService(CustomerManager customerService) {
		this.customerService = customerService;
	}

	
	public void setBusinessService(BusinessManager businessService) {
		this.businessService = businessService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public List<TimeZoneBean> getTimeZoneList() {
		return timeZoneList;
	}

	public void setTimeZoneList(List<TimeZoneBean> timeZoneList) {
		this.timeZoneList = timeZoneList;
	}
	public List<Province> getProvinces() {
		return provinces;
	}

	public void setProvinces(List<Province> provinces) {
		this.provinces = provinces;
	}

	public void setCountries(List<CountryVO> countries) {
		this.countries = countries;
	}	

	public List<CountryVO> getCountries() {
		return countries;
	}

	public CustomerCarrier getCustomerCarrier() {
		return customerCarrier;
	}

	public CarrierServiceManager getCarrierServiceManager() {
		return carrierServiceManager;
	}

	public void setCarrierServiceManager(CarrierServiceManager carrierServiceManager) {
		this.carrierServiceManager = carrierServiceManager;
	}
	public AddressManager getAddressService() {
		return addressService;
	}

	public void setAddressService(AddressManager addressService) {
		this.addressService = addressService;
	}	
	public Customer getCustomer() {
		return (Customer) getSession().get("customer");
	}

	public void setCustomer(Customer customer) {
		getSession().put("customer", customer);
	}

	public void setCreditCard(CreditCard creditCard) {
		getSession().put("creditCard", creditCard);
	}
	
	public CreditCard getCreditCard(){
		return (CreditCard) getSession().get("creditCard");
		
	}

	public List<Carrier> getCarriers() {
		return carriers;
	}

	public void setCarriers(List<Carrier> carriers) {
		this.carriers = carriers;
	}

	@SuppressWarnings("unchecked")
	protected void initialize() {
		if (this.getCustomer() == null) {
			Customer customer = new Customer();
			customer.setBusinessId(UserUtil.getMmrUser().getBusinessId());
			customer.getAddress().setCountryCode(UserUtil.getMmrUser().getBusiness().getAddress().getCountryCode());
			customer.getAddress().setProvinceCode(UserUtil.getMmrUser().getBusiness().getAddress().getProvinceCode());
			customer.setTimeZone(UserUtil.getMmrUser().getBusiness().getTimeZone());
			if(customer.getCreditLimit().doubleValue()== 0){
				customer.setCreditLimit(new BigDecimal(UserUtil.getMmrUser().getBusiness().getDefaultCreditLimit()).setScale(2));
				customer.getCreditLimit().setScale(2,BigDecimal.ROUND_HALF_UP);
			}
			if(customer.getHoldTerms() == 0){
				customer.setHoldTerms(UserUtil.getMmrUser().getBusiness().getDefaultHoldTerms());
			}
			
			if(customer.getPayableDays() == 0)
				customer.setPayableDays(UserUtil.getMmrUser().getBusiness().getDefaultNetTerms());

			this.setCustomer(customer);
		} else if(request!=null){
			String method = this.request.getParameter("method");
			if (method != null && method.equals("reset")) {
				Customer customer = new Customer();
				customer.getAddress().setCountryCode(UserUtil.getMmrUser().getBusiness().getAddress().getCountryCode());
				customer.getAddress().setProvinceCode(UserUtil.getMmrUser().getBusiness().getAddress().getProvinceCode());
				customer.setBusinessId(UserUtil.getMmrUser().getBusinessId());
				customer.setTimeZone(UserUtil.getMmrUser().getBusiness().getTimeZone());
				this.setCustomer(customer);				
			}
		}
		countries = (List<CountryVO>) getSession().get("CountryList");
		if (countries == null) {
			countries = MessageUtil.getCountriesList();
			getSession().put("CountryList", countries);
		}

		provinces = this.addressService.getProvinces(getCustomer().getAddress().getCountryCode());
		getSession().put("provinces", provinces);
		
		timeZoneList = (List<TimeZoneBean>) getSession().get("timeZones"); 
		if (timeZoneList == null) {
			timeZoneList =(List<TimeZoneBean>) TimeZonesFactory.getSupportedTimeZones();
			getSession().put("timeZones", timeZoneList);
		}
		carriers = this.carrierServiceManager.getCarriersForBusiness(UserUtil.getMmrUser().getBusinessId());
		getSession().put("CARRIERS", carriers);
		
		//get the sales users for this business
		UserSearchCriteria criteria = new UserSearchCriteria();
		criteria.setBusinessId(null);
			    criteria.setRoleCode(ShiplinxConstants.ROLE_SALES);
			    criteria.setBusinessIds(BusinessFilterUtil.getBusIdParentId(BusinessFilterUtil.setBusinessIdbyUserLevel()));
		salesUsers = userService.findUsers(criteria, 0, 0);
		
	}
	
	public String listProvince() throws Exception {
		String country;
		country = request.getParameter("value");
		if(country == null || "".equals(country))
			country = ShiplinxConstants.CANADA;
		provinces=addressService.getProvinces(country);
		getSession().put("provinces", provinces);
		return SUCCESS;
	}

	public List<User> getSalesUsers() {
		return salesUsers;
	}

	public void setSalesUsers(List<User> salesUsers) {
		this.salesUsers = salesUsers;
	}	
	
	
	
}
