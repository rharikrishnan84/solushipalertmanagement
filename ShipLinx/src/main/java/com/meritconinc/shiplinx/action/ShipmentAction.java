package com.meritconinc.shiplinx.action;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringBufferInputStream;
import java.math.BigDecimal;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URL;
import java.net.UnknownHostException;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import java.util.Map;
import java.util.StringTokenizer;

import com.meritconinc.mmr.model.common.KeyValueVO;

import java.util.TimeZone;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.meritconinc.mmr.dao.MenusDAO;
import com.meritconinc.mmr.dao.UserDAO;
import com.meritconinc.mmr.exception.CardProcessingException;
import com.meritconinc.mmr.model.security.User;
import com.meritconinc.mmr.service.UserService;
import com.meritconinc.mmr.utilities.DateUtil;
import com.meritconinc.mmr.utilities.MessageUtil;
import com.meritconinc.mmr.utilities.MmrBeanLocator;
import com.meritconinc.mmr.utilities.StringUtil;
import com.meritconinc.mmr.utilities.security.UserUtil;
import com.meritconinc.shiplinx.carrier.CarrierService;
import com.meritconinc.shiplinx.carrier.dhl.model.DhlShipValidateResponse;
import com.meritconinc.shiplinx.carrier.purolator.stub.ServiceAvailabilityWebServiceClient;
import com.meritconinc.shiplinx.dao.CarrierServiceDAO;
import com.meritconinc.shiplinx.dao.CustomerDAO;
import com.meritconinc.shiplinx.dao.InvoiceDAO;
import com.meritconinc.shiplinx.dao.ShippingDAO;
import com.meritconinc.shiplinx.model.Address;
import com.meritconinc.shiplinx.model.BatchShipmentInfo;
import com.meritconinc.shiplinx.model.Billduty;
import com.meritconinc.shiplinx.model.BillingStatus;
import com.meritconinc.shiplinx.model.CCTransaction;
import com.meritconinc.shiplinx.model.Carrier;
import com.meritconinc.shiplinx.model.CarrierChargeCode;
import com.meritconinc.shiplinx.model.Charge;
import com.meritconinc.shiplinx.model.ChargeGroup;
import com.meritconinc.shiplinx.model.Country;
import com.meritconinc.shiplinx.model.CreditCard;
import com.meritconinc.shiplinx.model.CurrencySymbol;
import com.meritconinc.shiplinx.model.Customer;
import com.meritconinc.shiplinx.model.CustomsInvoice;
import com.meritconinc.shiplinx.model.CustomsInvoiceProduct;
import com.meritconinc.shiplinx.model.DangerousGoods;
import com.meritconinc.shiplinx.model.Invoice;
import com.meritconinc.shiplinx.model.LoggedEvent;
import com.meritconinc.shiplinx.model.ManifestBean;
import com.meritconinc.shiplinx.model.Markup;
import com.meritconinc.shiplinx.model.OrderProduct;
import com.meritconinc.shiplinx.model.OrderStatus;
import com.meritconinc.shiplinx.model.Package;
import com.meritconinc.shiplinx.model.PackageType;
import com.meritconinc.shiplinx.model.PackageTypes;
import com.meritconinc.shiplinx.model.Pickup;
import com.meritconinc.shiplinx.model.Products;
import com.meritconinc.shiplinx.model.Province;
import com.meritconinc.shiplinx.model.Rating;
import com.meritconinc.shiplinx.model.RecordList;
import com.meritconinc.shiplinx.model.Service;
import com.meritconinc.shiplinx.model.ShippingOrder;
import com.meritconinc.shiplinx.model.UnitOfMeasure;
import com.meritconinc.shiplinx.service.AddressManager;
import com.meritconinc.shiplinx.model.FutureReference;
import com.meritconinc.shiplinx.service.CarrierServiceManager;
import com.meritconinc.shiplinx.service.CustomerManager;
import com.meritconinc.shiplinx.service.InvoiceManager;
import com.meritconinc.shiplinx.service.LoggedEventService;
import com.meritconinc.shiplinx.service.MarkupManager;
import com.meritconinc.shiplinx.service.PickupManager;
import com.meritconinc.shiplinx.service.ProductManager;
import com.meritconinc.shiplinx.service.ShippingService;
import com.meritconinc.shiplinx.utils.CarrierErrorMessage;
import com.meritconinc.shiplinx.utils.EODManifestCreator;
import com.meritconinc.shiplinx.utils.FormattingUtil;
import com.meritconinc.shiplinx.utils.ShiplinxConstants;
import com.meritconinc.shiplinx.model.FutureReferencePackages;
import com.meritconinc.mmr.constants.Constants;
import com.meritconinc.mmr.model.common.KeyValueVO;
import com.meritconinc.shiplinx.dao.BusinessDAO;
import com.meritconinc.shiplinx.model.Business;
import com.opensymphony.xwork2.ActionContext;
import com.soluship.businessfilter.util.BusinessFilterUtil;
/**
 * @author Rahul
 *
 */
public class ShipmentAction extends BaseAction implements ServletRequestAware, ServletResponseAware{
	private static final Logger log = LogManager.getLogger(ShipmentAction.class);
	private static final long serialVersionUID	= 18052007;
	private HttpServletRequest request;
//	private ShippingOrder shippingOrder;
	private AddressManager addressService;
	private ShippingService shippingService;
	private String fromDate, toDate;
	private ProductManager productManagerService;
	private UserService userService;
	private List<Country> countries;

	private String contact;
	private String consigneeName;
	private String id;
	private String city;
	private String postalCode;
	private String state;
	
	private List packageTypeList;
	private List<Province> provinces;
	private List<CurrencySymbol> currencyList;
	private CarrierServiceManager carrierServiceManager;
	//private List<Rating> ratingList;
	private List<String> orderList;
	private HttpServletResponse response;
	private CustomerManager customerService;
	private Address addressBook;
	private List<Address> addressList;
	private File upload;
	private String uploadFileName;
//	private List<OrderStatus> orderStatusList;
	private MarkupManager markupManagerService;
	
	private FutureReference fc;
	private ShippingOrder so;
	private FutureReferencePackages frp;
	private List<FutureReferencePackages>futureRefPackList=new ArrayList<FutureReferencePackages>();
	
	private List<KeyValueVO> cachedList;
	public List<CurrencySymbol> getCurrencyList() {
		return currencyList;
	}


	public void setCurrencyList(List<CurrencySymbol> currencyList) {
		this.currencyList = currencyList;
	}
	public MarkupManager getMarkupManagerService() {
		return markupManagerService;
	}


	public void setMarkupManagerService(MarkupManager markupManagerService) {
		this.markupManagerService = markupManagerService;
	}

	private String nextAction;
	private String referenceone=" ";
		public String getReferenceone() {
		return referenceone;
	}


	public void setReferenceone(String referenceone) {
		this.referenceone = referenceone;
	}


	public String getReferencetwo() {
		return referencetwo;
	}


	public void setReferencetwo(String referencetwo) {
		this.referencetwo = referencetwo;
	}

		private String referencetwo=" ";
	private List<Products> warehouseProductsList;
	List<Products> warehouseAllProdList = new ArrayList<Products>();
	
	private Map<String, Long> importsSearchResult= new HashMap<String, Long>();
 	
		public Map<String, Long> getImportsSearchResult() {
			return importsSearchResult;
		}
	
	
		public void setImportsSearchResult(Map<String, Long> importsSearchResult) {
			this.importsSearchResult = importsSearchResult;
		}
	
	private List<Address> addresses = null;
	
//	private List<Carrier> carriers;
//	private List<Service> services;
	
//	private List<ShippingOrder> shipments;
	private List<Boolean> select;
	private List<ShippingOrder> selectedShipments;
//	private Charge newCharge;
	private Map<String, Long> carrierChargesSearchResult = new HashMap<String, Long>();
	private Map<String, String> carrierChargeCodeSearchResult = new HashMap<String, String>();
	private Map<String, String> carrierChargeNameSearchResult = new HashMap<String, String>();
    private List<Billduty> billduty;
    
	public List<Billduty> getBillduty() {
		return billduty;
	}


	public void setBillduty(List<Billduty> billduty) {
		this.billduty = billduty;
	}

	private static List<CarrierChargeCode> carrierChargesList = null;
	private List<CarrierChargeCode> ajaxCarreierChargeList;
	private Map<String, Long> customerSearchResult = new HashMap<String, Long>();
	
	public Map<String, Long> getCustomerSearchResult() {
				return customerSearchResult;
			}
		
		
			public void setCustomerSearchResult(Map<String, Long> customerSearchResult) {
				this.customerSearchResult = customerSearchResult;
			}
		
	private Map<String, Long> customerSearchResults = new HashMap<String, Long>();
	// City, Zipcode_Start List
	private List<String> citySuggestList;
	// Zipcode_Start, City List
	private List<String> zipSuggestList;
	private ShippingDAO shippingDAO;
	private List<LoggedEvent> loggedList = new ArrayList<LoggedEvent>();
	
	private LoggedEventService loggedEventService;
	private PickupManager pickupService;
	public List<Pickup> listPickups; 
	
	public InvoiceManager invoiceManagerService;
	public InvoiceDAO invoiceDAO;
	private boolean pickupRequired;

	 private UserDAO userDAO;
     private List<UnitOfMeasure> uom;
     private Customer customer;
          public Customer getCustomer() {
     		return customer;
     	}
     
     	public void setCustomer(Customer customer) {
     		this.customer = customer;
     	}
     public List<UnitOfMeasure> getUom() {
     return uom;
    }

  
  public void setUom(List<UnitOfMeasure> uom) {
 	this.uom = uom;
  }

	  private String newChargeType;

	  private CarrierServiceDAO carrierServiceDAO;

	  public boolean isPickupRequired() {
	    return pickupRequired;
	  }

	  public void setPickupRequired(boolean pickupRequired) {
	    this.pickupRequired = pickupRequired;
	  }
	private InputStream inputStream;
	
	private List<String> shipmentIdList = new ArrayList<String>();
	
	public InputStream getInputStream() {
	return inputStream;
	}
	
	public void setInputStream(InputStream inputStream) {
	this.inputStream = inputStream;
	}
	
	

	
	/**
	 * 
	 * @return
	 * @throws Exception
	 */
//	public String executebackup() throws Exception {
//		try{
//			log.debug("-----execute------");
////			getSession().remove("shippingOrder");
//			getSession().remove("PackageType");
//			String method=request.getParameter("method");
//			if(method==null || !(method.equals("update"))){
//				getSession().remove("EDIT_ORDER_ID");
//			}
//			String orderId=(String)getSession().get("EDIT_ORDER_ID");
//			
//			log.debug("-----orderId------"+orderId);			
//			Long customerId = getLoginUser().getCustomerId();
//			log.debug("-----customerId------"+customerId);
//			String fromCountry = "";
//			String toCountry = "";
//			
//			List<Province> toProvinces;
//			List<Province> fromProvinces;
//			Address addressbookFrom;
//			Address addressbookTo;
//			if(orderId==null){
//				addressbookFrom = addressService.findDefaultFromAddressForCustomer(customerId);
//				addressbookTo = addressService.findDefaultToAddressForCustomer(customerId);
//			}
//			else{				
//				ShippingOrder order = shippingService.getShippingOrder(Long.valueOf(orderId));
//				addressbookFrom = order.getFromAddress();
//				addressbookTo = order.getToAddress();
//			}
//			ShippingOrder shippingOrder = getShippingOrder();
//			shippingOrder.setToAddress(addressbookTo);
//			shippingOrder.setFromAddress(addressbookFrom);
//			shippingOrder.setCustomerId(getLoginUser().getCustomerId());
//			
//			if(orderId!=null){
//				shippingOrder.setPackageTypeId(shippingService.findOrderPackageType(Long.parseLong(orderId)));
//				ShippingOrder shippingOrder1=shippingService.getShippingOrder(Long.parseLong(orderId));
//
//				getSession().put("PackageType",shippingOrder.getPackageTypeId().getType());
//				shippingOrder.setReferenceCode(shippingOrder1.getReferenceCode());
//				shippingOrder.setSatDelivery(shippingOrder1.getSatDelivery());
//				shippingOrder.setHoldForPickupRequired(shippingOrder1.getHoldForPickupRequired());
//				shippingOrder.setInsideDelivery(shippingOrder1.getInsideDelivery());
//				shippingOrder.setPickupTime(shippingOrder1.getPickupTime());
//				shippingOrder.setSpecialEquipment(shippingOrder1.getSpecialEquipment());
//				shippingOrder.setCODPayment(shippingOrder1.getCODPayment());
//				shippingOrder.setCODValue(shippingOrder1.getCODValue());
//				shippingOrder.setCODCurrency(shippingOrder1.getCODCurrency());
//				shippingOrder.setCODPayableTO(shippingOrder1.getCODPayableTO());
//				shippingOrder.setDangerousGoods(shippingOrder1.getDangerousGoods());
//				shippingOrder.setReqDeliveryDate(shippingOrder1.getReqDeliveryDate());
//				shippingOrder.setScheduledShipDate(shippingOrder1.getScheduledShipDate());
//				shippingOrder.setPickUpNum(shippingOrder1.getPickUpNum());
//				shippingOrder.setReferenceOne(shippingOrder1.getReferenceOne());
//				shippingOrder.setReferenceTwo(shippingOrder1.getReferenceTwo());
//				shippingOrder.setMasterTrackingNum(shippingOrder1.getMasterTrackingNum());
//				shippingOrder.setRes(shippingOrder1.getRes());
//				shippingOrder.setFromTailgate(shippingOrder1.getFromTailgate());
//				shippingOrder.setToTailgate(shippingOrder1.getToTailgate());
//				shippingOrder.setFromAttention(shippingOrder1.getFromAttention());
//				shippingOrder.setToAttention(shippingOrder1.getToAttention());
//				shippingOrder.setNotifyRecipient(shippingOrder1.getNotifyRecipient());
//				shippingOrder.setConfirmDelivery(shippingOrder1.getConfirmDelivery());
//			}else{
//				shippingOrder.setPackageTypeId(shippingService.findPackageType(ShiplinxConstants.PACKAGE_TYPE_ENVELOPE_STRING));
//			}
//			//set the default package
//
//			
//			if(addressbookFrom != null)
//				fromCountry = addressbookFrom.getCountryCode();
//			if(addressbookTo != null)
//				toCountry = addressbookTo.getCountryCode();
//			
//			//if not set set as default
//			if(fromCountry==null ||"".equals(fromCountry))
//				fromCountry = ShiplinxConstants.CANADA;
//			if(toCountry==null ||"".equals(toCountry))
//				toCountry = ShiplinxConstants.CANADA;
//			
//			toProvinces=addressService.getProvinces(toCountry);
//			fromProvinces=addressService.getProvinces(fromCountry);
//			
//			getSession().put("Fromprovinces", fromProvinces);
//			getSession().put("Toprovinces", toProvinces);
//			getSession().put("ToCountry", toCountry);
//			getSession().put("FromCountry", fromCountry);
//		
//			countries=addressService.getCountries();
//			getSession().put("CountryList", countries);
////			getSession().put("shippingOrder",shippingOrder);
//			
//			packageTypeList = shippingService.getPackages();
//			List<PackageType> listPackages = shippingService.getPackages();
//			
//			List<HashMap<String, String>> packageOption = new ArrayList<HashMap<String, String>>();
//			
//			// To generate packages radio buttons on jsp
//			//short packeCounter=0;
//			for(PackageType pType :listPackages){
//				HashMap<String, String> packages = new HashMap<String, String>();
//				packages.put(pType.getType(),pType.getName());
//				packageOption.add(packages);
//			}
//			
//			getSession().put("packageOptions", packageOption);
//			
//			
//			}catch (Exception e) {
//				log.debug("-----------------Exception----------------"+e);
//				e.printStackTrace();
//			}
//			
//			//set customer info for shipment
////			ShiplinxConstants.setCustomer(customerService.getCustomerInfoByCustomerId(getLoginUser().getCustomerId()));
//			if(getSession().get("WINDOW_STATUS")==null){
//				getSession().put("WINDOW_STATUS","show");
//			}
////			getSession().put("shippingOrder",shippingOrder);
////			setShippingOrder(shippingOrder);
//			
//			//getSession().put("SHIPMENT_WINDOW_STATUS","hide");
//			return SUCCESS;
//		}

	public String execute() throws Exception {
		MenusDAO menusDAO = (MenusDAO) MmrBeanLocator.getInstance().findBean("menusDAO");
		try{
			log.debug("-----execute------");
			getSession().remove("shippingOrder");
			getSession().remove("PackageType");
			String method=request.getParameter("method");
			if(method==null || !(method.equals("update"))){
				getSession().remove("EDIT_ORDER_ID");
			}
			String orderId=(String)getSession().get("EDIT_ORDER_ID");
			
			log.debug("-----orderId------"+orderId);			
			Long customerId = getLoginUser().getCustomerId();
			log.debug("-----customerId------"+customerId);
			customer = userService.getCustomerReference(customerId);
			String fromCountry = "";
			String toCountry = "";
			
			List<Province> toProvinces;
			List<Province> fromProvinces;
			Address addressbookFrom =null;
			Address addressbookTo=null;
			List<PackageTypes> packagetypes;
			long l_default_from_add = 0;
			long l_default_to_add = 0;
			
			//setting the role in req attribute to enable or disable address fields based on role.
			if(getLoginUser().getUserRole().equalsIgnoreCase(ShiplinxConstants.ROLE_CUSTOMER_SHIPPER))
				request.setAttribute("USERROLE", "shipper");
			if(orderId==null){
				l_default_from_add = getLoginUser().getDefaultFromAddressId();
				l_default_to_add = getLoginUser().getDefaultToAddressId();
				//set default addresses for the user if set
					if (l_default_from_add > 0) {
			          addressbookFrom = addressService.findAddressById(l_default_from_add + "");
			        } else {
			          addressbookFrom = addressService.findDefaultFromAddressForCustomer(customerId);
			        }
			        if (l_default_to_add > 0) {
			          addressbookTo = addressService.findAddressById(l_default_to_add + "");
			        } else {
			          addressbookTo = addressService.findDefaultToAddressForCustomer(customerId);
				}

				if(addressbookFrom!=null)
					addressbookFrom.setCustomerId(0);
				if(addressbookTo!=null)
					addressbookTo.setCustomerId(0);
			}
			else{				
				ShippingOrder order = shippingService.getShippingOrder(Long.valueOf(orderId));
				addressbookFrom = order.getFromAddress();
				addressbookTo = order.getToAddress();
			}
			
			if(addressbookFrom==null)
				addressbookFrom = new Address();
			if(addressbookTo==null)
				addressbookTo = new Address();
			
			
			ShippingOrder shippingOrder = getShippingOrder();
			shippingOrder.setToAddress(addressbookTo);
			shippingOrder.setFromAddress(addressbookFrom);
			shippingOrder.setCustomerId(getLoginUser().getCustomerId());
			
			getSession().put("SHIP_MODE", "SHIP");
			this.populateCustomersList();
			
			if(addressbookFrom != null)
				fromCountry = addressbookFrom.getCountryCode();
			if(addressbookTo != null)
				toCountry = addressbookTo.getCountryCode();
			
			//if not set set as default
			if(fromCountry==null ||"".equals(fromCountry)){
				fromCountry = UserUtil.getMmrUser().getBusiness().getAddress().getCountryCode();
				addressbookFrom.setCountryCode(fromCountry);
			}
			if(toCountry==null ||"".equals(toCountry)){
				toCountry = UserUtil.getMmrUser().getBusiness().getAddress().getCountryCode();
				addressbookTo.setCountryCode(toCountry);
			}
			
			toProvinces=addressService.getProvinces(toCountry);
			fromProvinces=addressService.getProvinces(fromCountry);
			
			PackageTypes pt = new PackageTypes();
			pt.setCustomerId(Long.valueOf(customerId));
			packagetypes = productManagerService.searchPackageTypes(pt);
			
			List<DangerousGoods> dangerousGoodsList = new ArrayList<DangerousGoods>();
			
			dangerousGoodsList = shippingService.getDangerousGoodsAll();
			getSession().put("Fromprovinces", fromProvinces);
			getSession().put("Toprovinces", toProvinces);
			getSession().put("ToCountry", toCountry);
			getSession().put("FromCountry", fromCountry);
			getSession().put("PackageTypes",packagetypes);
			getSession().put("DGList", dangerousGoodsList);
		
			countries=MessageUtil.getCountriesList();

			getSession().put("CountryList", countries);
//			getSession().put("shippingOrder",shippingOrder);
			
			packageTypeList = shippingService.getPackages();
			List<PackageType> listPackages = shippingService.getPackages();
			
			List<HashMap<String, String>> packageOption = new ArrayList<HashMap<String, String>>();
			
			// To generate packages radio buttons on jsp
			//short packeCounter=0;
			for(PackageType pType :listPackages){
				HashMap<String, String> packages = new HashMap<String, String>();
				packages.put(pType.getType(),pType.getName());
				packageOption.add(packages);
			}

			PackageType pType = new PackageType();
			pType.setType(ShiplinxConstants.PACKAGE_TYPE_PACKAGE_STRING);
			shippingOrder.setPackageTypeId(pType);
			
			getSession().put("PackageType",shippingOrder.getPackageTypeId().getType());
			getSession().put("packageOptions", packageOption);
			getSession().put("listPackages", listPackages);
			//default quantity is 1
			ArrayList<Package> packages = new ArrayList<Package>();
			if(shippingOrder.getQuantity()==null)
				shippingOrder.setQuantity(1);
			

		      for (int i = 0; i < (int) shippingOrder.getQuantity(); i++) {
		        Package pack = new Package();
		        pack.setLength(new BigDecimal(1.00).setScale(2));
		        pack.setWeight(new BigDecimal(1.00).setScale(2));
		        pack.setHeight(new BigDecimal(1.0).setScale(2));
		        pack.setWidth(new BigDecimal(1.0).setScale(2));
		        pack.setCodAmount(new BigDecimal(0.00).setScale(2));
		        pack.setInsuranceAmount(new BigDecimal(0.00).setScale(2));
		        packages.add(pack);
		      }

			shippingOrder.setPackages(packages);
			//Package packageArray[] = new Package[1];
			//shippingOrder.setPackageArray(packageArray);
			
			/*----------Address fetching for autocomplete----------*/
			
			addressList = addressService.findAddressesByCustomer(Long.valueOf(customerId));
			            for(Address add: addressList){
			            	String withoutQuotesCustomer = add.getAbbreviationName().replace("\"", "");
			            	String address = add.getAddress1();
			            				            	customerSearchResults.put(withoutQuotesCustomer+",  "+address, add.getAddressId());  
			            }
			            getSession().put("usersList", customerSearchResults);
			
			//this.populateUserList();
			
			}catch (Exception e) {
				log.debug("-----------------Exception----------------"+e);
				e.printStackTrace();
			}
			
			//set customer info for shipment
//			ShiplinxConstants.setCustomer(customerService.getCustomerInfoByCustomerId(getLoginUser().getCustomerId()));
			if(getSession().get("WINDOW_STATUS")==null){
				getSession().put("WINDOW_STATUS","show");
			}
//			getSession().put("shippingOrder",shippingOrder);
			//for Quick Ship option
			initCarrierListANY();
			
			if (getSession().get("SERVICES") == null) {
				List<Service>services = getCarrierServices(-1L);
				getSession().put("SERVICES", services);
			}
			 User user = UserUtil.getMmrUser();
    userDAO = (UserDAO) MmrBeanLocator.getInstance().findBean("userDAO");
    uom = userDAO.unitOfMeasure();
    User unitofmeasure = userDAO.findunitofmeasure(user.getUsername());
    if (user != null && unitofmeasure.getUnitmeasure() == 2) {
      for (int i = 0; i < uom.size(); i++) {
        if (unitofmeasure != null
            && unitofmeasure.getUnitmeasure() == uom.get(i).getUnitOfMeasureId()) {
          Collections.swap(uom, 0, i);
   	
        }
      }
    }
    getSession().put("UOM", uom);


			//getSession().put("SHIPMENT_WINDOW_STATUS","hide");
			if(UserUtil.getMmrUser().getCustomerId()> 0 && customerService.isWarehouseCustomer(UserUtil.getMmrUser().getCustomerId()) && request.getParameter("shipment")==null)
			{
				return "success2";
			}
			else
			{
				return SUCCESS;
			}
		}
	
	public String processShipment() throws Exception {
	    try {
	        log.debug("Inside processShipment of ShipmentAction");
	        getSession().remove("shippingOrder");
	        getSession().remove("PackageType");
	        User user1 = UserUtil.getMmrUser();
	        userDAO = (UserDAO) MmrBeanLocator.getInstance().findBean("userDAO");
	        uom = userDAO.unitOfMeasure();
	        User unitofmeasure = userDAO.findunitofmeasure(user1.getUsername());
	        if (user1 != null && unitofmeasure.getUnitmeasure() == 2) {
	          for (int i = 0; i < uom.size(); i++) {
	            if (unitofmeasure != null
	                && unitofmeasure.getUnitmeasure() == uom.get(i).getUnitOfMeasureId()) {
	              Collections.swap(uom, 0, i);
	            }
	          }
	        }
	        getSession().put("UOM", uom);
	        ShippingOrder order = null;
	        String orderId = request.getParameter("order_id");
	        if (!StringUtil.isEmpty(orderId)) {
	          order = shippingService.getShippingOrder(Long.valueOf(orderId));
	        } else {
	          order = this.getShippingOrder();
	        }
	        if (order != null) {

	          setOrderAddress(order);

	          setOrderPackages(order);

	          Package packageArray[] = new Package[1];
	          order.setPackageArray(packageArray);
	        }
	        
	        if (getLoginUser().getCustomerId() > 0)
	          order.setCustomerId(getLoginUser().getCustomerId());
	        this.setShippingOrder(order);
	        this.populateCustomersList();
	        // }
	        // }
	      } catch (Exception e) {
	        e.printStackTrace();
	        log.error(e);
	      }
	      if (getSession().get("WINDOW_STATUS") == null) {
	        getSession().put("WINDOW_STATUS", "show");
	      }

	      if (getSession().get("DGList") == null) {
	        List<DangerousGoods> dangerousGoodsList = new ArrayList<DangerousGoods>();

	        dangerousGoodsList = shippingService.getDangerousGoodsAll();
	        getSession().put("DGList", dangerousGoodsList);
	      }
	      return SUCCESS;
	    }
	
	public String repeatOrder()
	{
		log.debug("Inside repeat of ShipmentAction");
		
		getSession().remove("shippingOrder");
		getSession().remove("PackageType");
		putUomToSeession();
		List<DangerousGoods> dangerousGoodsList = new ArrayList<DangerousGoods>();
		dangerousGoodsList = shippingService.getDangerousGoodsAll();
		getSession().put("DGList", dangerousGoodsList);
		ShippingOrder newShippingOrder = new ShippingOrder();
		ShippingOrder order = null;
		String orderId=request.getParameter("order_id");
		String customs = request.getParameter("customsinvoice");
		if(orderId == null)
			orderId = (String)request.getAttribute("order_id");
		
		if (!StringUtil.isEmpty(orderId)) 
		{
			//Implement in Service layer
			newShippingOrder = shippingService.repeatOrder(orderId,customs);
			
		    // setting customer
			try {
				newShippingOrder.setCustomer(this.customerService.getCustomerInfoByCustomerId(Long
				    .valueOf(newShippingOrder.getCustomerId())));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    // shippingOrder.setCustomer(this.customerService.getCustomerInfoByCustomerId(shippingOrder.getCustomerId()));

			
			//Set packages
			setOrderPackages(newShippingOrder);
			//clear the previous errors
			clearActionErrors();
			//Setting address
			setOrderAddress(newShippingOrder);
			//set shippingorder
			this.setShippingOrder(newShippingOrder);
		}
		
		return SUCCESS;
	}
	
	private void setOrderPackages(ShippingOrder order)
	{
		packageTypeList = shippingService.getPackages();
		List<PackageType> listPackages = shippingService.getPackages();
		
		List<HashMap<String, String>> packageOption = new ArrayList<HashMap<String, String>>();
		
		// To generate packages radio buttons on jsp
		//short packeCounter=0;
		for(PackageType pType :listPackages){
			HashMap<String, String> packages = new HashMap<String, String>();
			packages.put(pType.getType(),pType.getName());
			packageOption.add(packages);
		}
		if (order.getPackageTypeId() == null) 
		{
			PackageType pType = new PackageType();
			pType.setType(ShiplinxConstants.PACKAGE_TYPE_PACKAGE_STRING);
			order.setPackageTypeId(pType);
		}
		getSession().put("PackageType", order.getPackageTypeId().getType());
		getSession().put("packageOptions", packageOption);
		getSession().put("listPackages", listPackages);
		if (order.getPackages() == null || (order.getPackages()!=null && order.getPackages().size()==0)) 
		{
			//default quantity is 1
			ArrayList<Package> packages = new ArrayList<Package>();
			if(order.getQuantity()==null)
				order.setQuantity(1);
			
		      for (int i = 0; i < (int) order.getQuantity(); i++) {
		          Package pack = new Package();
		          pack.setLength(new BigDecimal(1.00).setScale(2,BigDecimal.ROUND_HALF_UP));
		          pack.setWeight(new BigDecimal(1.00).setScale(2,BigDecimal.ROUND_HALF_UP));
		          pack.setHeight(new BigDecimal(1.00).setScale(2,BigDecimal.ROUND_HALF_UP));
		          pack.setWidth(new BigDecimal(1.00).setScale(2,BigDecimal.ROUND_HALF_UP));
		          pack.setCodAmount(new BigDecimal(0.00).setScale(2,BigDecimal.ROUND_HALF_UP));
		          pack.setInsuranceAmount(new BigDecimal(0.00).setScale(2,BigDecimal.ROUND_HALF_UP));
		          packages.add(pack);
		        }

			order.setPackages(packages);
		}
		/*else 
		{
			ArrayList<Package> newPackages = new ArrayList<Package>();
			order.setQuantity(order.getPackages().size());
			
			for(Package p: order.getPackages())
			{
				Package pack = new Package();
				pack.setLength(p.getLength());
				pack.setWeight(p.getWeight());
				pack.setHeight(p.getHeight());
				pack.setWidth(p.getWidth());
				pack.setCodAmount(p.getCodAmount());
				pack.setInsuranceAmount(p.getInsuranceAmount());
				newPackages.add(pack);
			}
			
			order.setPackages(newPackages);
		}*/
	}
	
	public String printShipment(){
		try {

//			if (shipments != null)
//				shipments.clear();
			ShippingOrder so = this.getShippingOrder();	
			String fromDate=this.request.getParameter("shippingOrder.fromDate");
			String toDate=this.request.getParameter("shippingOrder.toDate");
			String carrierId=this.request.getParameter("shippingOrder.carrierId");		
			String clickableButtonId=this.request.getParameter("d-16544-e");
			String type = request.getParameter("type");	
			if("5".equals(clickableButtonId) && "80".equals(carrierId)){
				//new EODManifestCreator(fromDate,toDate);				
				getSession().put("MIDLND_SHIPPING_ORDER_FROM_DATE", fromDate);
				getSession().put("MIDLND_SHIPPING_ORDER_TO_DATE", toDate);
				 getSession().put("CARRIER_ID", carrierId);
      }
			if (carrierId != null && !"".equalsIgnoreCase(carrierId) && "5".equals(clickableButtonId)
          && !("80".equals(carrierId))) {


        getSession().put("CARRIER_ID", carrierId);
        printManifest();
			 } else if ((carrierId == null || "".equalsIgnoreCase(carrierId) || fromDate == null
          || "".equalsIgnoreCase(fromDate) || toDate == null || "".equalsIgnoreCase(toDate))
          && ("5".equals(clickableButtonId) && !("80".equals(carrierId)) && carrierId
              .equalsIgnoreCase(""))) {
        addActionError("EOD : Select FromDate,ToDate and Carrier");
        return SUCCESS;


			}
			if (so != null) {
				// Ajax calls goes to to listService.jsp and serviceId gets updated in markup.serviceId
				// therefore it needs to be updated back in shippingOrder
				String s = this.request.getParameter("markup.serviceId");
				if ( !StringUtil.isEmpty(s) )
					so.setServiceId(Long.parseLong(s));
				
				if((so.getCustomerId()==null || so.getCustomerId()==0) 
						&& getLoginUser().getCustomerId()>0)
					so.setCustomerId(getLoginUser().getCustomerId());
				
				//so.setBillingStatus(null);
			}
			if(UserUtil.getMmrUser().getUserRole().equals(ShiplinxConstants.ROLE_SALES))
			{
				so.setSalesAgentUsername(UserUtil.getMmrUser().getUsername());
			}
			else if(UserUtil.getMmrUser().getUserRole().equals(ShiplinxConstants.ROLE_CUSTOMER_SHIPPER))
			{
				so.setCreatedBy(UserUtil.getMmrUser().getUsername());
			} 
			
			//if the user belongs to a branch, then search only those shipments that belong to customers of that branch
			if(!StringUtil.isEmpty(UserUtil.getMmrUser().getBranch()))
				so.setBranch(UserUtil.getMmrUser().getBranch());
			
			if (this.shippingService != null) {
				String orderIds[]=request.getParameter("orderList").split(",");
				List<ShippingOrder> order = new ArrayList<ShippingOrder>();
				
				for(int i=0;i<orderIds.length;i++){
					ShippingOrder or=this.shippingService.getShippingOrder(Long.parseLong(orderIds[i]));
					order.add(or);
				}
				
				if("xml".equalsIgnoreCase(type)){
					String shippingLabelFileName = getUniqueTempxmlFileName("shipment");
					write_XML_File(order,shippingLabelFileName);
					response.setContentType("application/xml");
					response.setHeader("Content-Disposition",
							"attachment;filename=shipment.xml");
					File xmlFile = new File(shippingLabelFileName);
					FileInputStream fileInputStream = new FileInputStream(xmlFile);
					/*ServletContext ctx =ServletActionContext.getServletContext();
					InputStream is = ctx.getResourceAsStream(shippingLabelFileName)*/
					int read=0;
					byte[] bytes = new byte[1024];
					OutputStream os = response.getOutputStream();
				 
					while((read = fileInputStream.read(bytes))!= -1){
						os.write(bytes, 0, read);
					}
					os.flush();
					os.close();	
					
					}else if("csv".equalsIgnoreCase(type)){
						String shippingLabelFileName = getUniqueTempcsvFileName("shipment");
						
						FileWriter writer = new FileWriter(shippingLabelFileName);
						generateCsvFile(order,writer);
						response.setContentType("application/csv");
						response.setHeader("Content-Disposition",
								"attachment;filename=shipment.csv");
						File csvFile = new File(shippingLabelFileName);
						FileInputStream fileInputStream = new FileInputStream(csvFile);
						/*ServletContext ctx =ServletActionContext.getServletContext();
						InputStream is = ctx.getResourceAsStream(shippingLabelFileName)*/
						int read=0;
						byte[] bytes = new byte[1024];
						OutputStream os = response.getOutputStream();
					 
						while((read = fileInputStream.read(bytes))!= -1){
							os.write(bytes, 0, read);
						}
						os.flush();
						os.close();	
						
					}else if("xl".equalsIgnoreCase(type)){
						String shippingLabelFileName = getUniqueTempxlFileName("shipment");
						
						createxlfile(order,shippingLabelFileName);
						response.setContentType("application/msexcel");
						response.setHeader("Content-Disposition",
								"attachment;filename=shipment.xls");
						File xlFile = new File(shippingLabelFileName);
						FileInputStream fileInputStream = new FileInputStream(xlFile);
						/*ServletContext ctx =ServletActionContext.getServletContext();
						InputStream is = ctx.getResourceAsStream(shippingLabelFileName)*/
						int read=0;
						byte[] bytes = new byte[1024];
						OutputStream os = response.getOutputStream();
					 
						while((read = fileInputStream.read(bytes))!= -1){
							os.write(bytes, 0, read);
						}
						os.flush();
						os.close();	
						
					}
				
				
				
				}
			
	    } catch (Exception e) {
	    	e.printStackTrace();
	    	addActionError(getText("content.error.unexpected"));
		}
		
		return SUCCESS;
	}
	
	
	
	 public  void write_XML_File(List<ShippingOrder> shippingOrderList,String shippingLabelFileName){
		 
		
		  
		 DocumentBuilderFactory docBuilder = DocumentBuilderFactory.newInstance();
		 DocumentBuilder builder=null;
		try {
			builder = docBuilder.newDocumentBuilder();
		} catch (ParserConfigurationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		 
		 
		 Document doc = builder.newDocument();
		 Element shipingList = doc.createElement("shippings");
		 doc.appendChild(shipingList);
		 SimpleDateFormat formatDateJava = new SimpleDateFormat("dd/MM/yyyy");
		 		
		 		 for(ShippingOrder sOrder :shippingOrderList){
		  
	
		         Element log1 = doc.createElement("shipping");
		         shipingList.appendChild(log1);
	
		         /*Attr attr = doc.createAttribute("id");
		         attr.setValue(removeNull(Long.toString(sOrder.getId())));*/
		         
		         //.appendChild(log1);
		         
		         Element id = doc.createElement("OrderId");
		         		         id.appendChild(doc.createTextNode(removeNull(Long.toString(sOrder.getId()))));
		         		         log1.appendChild(id);
		         		        Element Tracking = doc.createElement("Tracking");
		         		       		         Tracking.appendChild(doc.createTextNode(removeNull(sOrder.getMasterTrackingNum())));
		         		       		         log1.appendChild(Tracking);
	
		         		       		     Element ShipDate = doc.createElement("ShipDate");
		         		       		 		         ShipDate.appendChild(doc.createTextNode(formatDateJava.format(sOrder.getScheduledShipDate())));
		         		       		 		         log1.appendChild(ShipDate);
	
		         		       		 		    Element Carrier = doc.createElement("Carrier");
		         		       		 		    			if(sOrder.getCarrier()!=null){
		         		       		 				         Carrier.appendChild(doc.createTextNode(removeNull(sOrder.getCarrier().getName())));
		         		       		 		    			}else{
		         		       		 		    			Carrier.appendChild(doc.createTextNode(""));
		         		       		 		    			}
		         		       		 				         log1.appendChild(Carrier);
		         
		         		       		 				    Element Service = doc.createElement("Service");
		         		       		 				    			if(sOrder.getService()!=null){
		         		       		 						         Service.appendChild(doc.createTextNode(removeNull(sOrder.getService().getName())));
		 		 														}else{
		 		 															Service.appendChild(doc.createTextNode(""));
		 		 														}
		         		       		 						         log1.appendChild(Service);
		         
		         		       		 						    Element qb = doc.createElement("QB");
		         		       		 								         qb.appendChild(doc.createTextNode(removeNull(String.valueOf(sOrder.getQuotedWeight()))));
		         		       		 								         log1.appendChild(qb);
		         		       		 								    Element Weight = doc.createElement("Weight");
		         		       		 										         Weight.appendChild(doc.createTextNode(removeNull(String.valueOf(sOrder.getBilledWeight()))));
		         		       		 										         log1.appendChild(Weight);
		         
		         		       		 										    Element QuotedCharge = doc.createElement("QuotedCharge");
		         		       		 												         QuotedCharge.appendChild(doc.createTextNode("$"+removeNull(String.valueOf(sOrder.getQuoteTotalCharge()))));
		         		       		 												         log1.appendChild(QuotedCharge);
		         		       		 												         
		         		       		 												    if(UserUtil.getMmrUser().getUserRole().equalsIgnoreCase("busadmin")){
		         		       		 												   Element QuotedCost = doc.createElement("QuotedCost");
	         		       		 												         QuotedCost.appendChild(doc.createTextNode("$"+removeNull(String.valueOf(sOrder.getQuoteTotalCost()))));
	         		       		 												         log1.appendChild(QuotedCost);
		         		       		 										
		         		       		 												         Element BilledCharge = doc.createElement("BilledCharge");
		         		       		 												         BilledCharge.appendChild(doc.createTextNode("$"+removeNull(String.valueOf(sOrder.getActualTotalCharge()))));
		         		       		 												         log1.appendChild(BilledCharge);
		         		       		 												         
		         		       		 												    Element BilledCost = doc.createElement("BilledCost");
	         		       		 												         BilledCost.appendChild(doc.createTextNode("$"+removeNull(String.valueOf(sOrder.getActualTotalCost()))));
	         		       		 												         log1.appendChild(BilledCost);
		         		       		 												         		 
		         		       		 												         if(sOrder.getFromAddress() != null){
		         		       		 												          Element fromAddress = doc.createElement("FromAddress");
		         		       		 												          fromAddress.appendChild(doc.createTextNode(removeNullComma(removeNull(String.valueOf(sOrder.getFromAddress().getAbbreviationName())))+" "+removeNullComma(removeNull(String.valueOf(sOrder.getFromAddress().getAddress1())))+" "+removeNullComma(removeNull(String.valueOf(sOrder.getFromAddress().getAddress2())))+" "+removeNullComma(removeNull(String.valueOf(sOrder.getFromAddress().getCity())))+" "+removeNullComma(removeNull(String.valueOf(sOrder.getFromAddress().getPostalCode())))+" "+removeNullComma(removeNull(String.valueOf(sOrder.getFromAddress().getProvinceCode())))+" "+removeNull(removeNull(String.valueOf(sOrder.getFromAddress().getCountryName())))+"."));
		         		       		 												          log1.appendChild(fromAddress);
		         		       		 												         }
		         		       		 												         	
		         		       		 												         if(sOrder.getToAddress() != null){
		         		       		 												         Element toAddress = doc.createElement("ToAddress");
		         		       		 												         toAddress.appendChild(doc.createTextNode(removeNullComma(removeNull(String.valueOf(sOrder.getToAddress().getAbbreviationName())))+" "+removeNullComma(removeNull(String.valueOf(sOrder.getToAddress().getAddress1())))+" "+removeNullComma(removeNull(String.valueOf(sOrder.getToAddress().getAddress2())))+" "+removeNullComma(removeNull(String.valueOf(sOrder.getToAddress().getCity())))+" "+removeNullComma(removeNull(String.valueOf(sOrder.getToAddress().getPostalCode())))+" "+removeNullComma(removeNull(String.valueOf(sOrder.getToAddress().getProvinceCode())))+" "+removeNull(removeNull(String.valueOf(sOrder.getToAddress().getCountryName())))+"."));
		         		       		 												         log1.appendChild(toAddress);
		         		       		 												         }
		         
		         Element Status = doc.createElement("Status");
		         		         Status.appendChild(doc.createTextNode(removeNull(sOrder.getStatusName())));
		         		         log1.appendChild(Status);
		         		         
		         
		         		         Element billingstatus = doc.createElement("BillingStatus");
		         		         billingstatus.appendChild(doc.createTextNode(removeNull(String.valueOf(sOrder.getBillingStatusText()))));
		         		         log1.appendChild(billingstatus);
		         		        Element Referencecode  = doc.createElement("ReferenceOne");
		         		       		         		         Referencecode.appendChild(doc.createTextNode(removeNull(String.valueOf(sOrder.getReferenceCode()))));
		         		       		         		         log1.appendChild(Referencecode);
		         		       		         		         Element Reference2 = doc.createElement("ReferenceTwo");
		         		       		         		         if(sOrder.getReferenceOne() != null && sOrder.getReferenceOne() != ""){
		         		       	         		        	 referenceone = String.valueOf(sOrder.getReferenceOne());
		         		       		         		         }
		         		       		         		         if(sOrder.getReferenceTwo() != null && sOrder.getReferenceTwo() != ""){
		         		       		         		        	 referencetwo = String.valueOf(sOrder.getReferenceTwo());
		         		       		         		         }
		         		       		         		         Reference2.appendChild(doc.createTextNode(removeNull(referenceone+" "+referencetwo)));
		         		       		         		         log1.appendChild(Reference2);
		         		       		 												    }else{
		         		       		 												         Element BilledCharge = doc.createElement("BilledCharge");
		         		       		 												         BilledCharge.appendChild(doc.createTextNode("$"+removeNull(String.valueOf(sOrder.getActualTotalCharge()))));
		         		       		 												         log1.appendChild(BilledCharge);
		         		       		 												         		 
		         		       		 												         if(sOrder.getFromAddress() != null){
		         		       		 												          Element fromAddress = doc.createElement("FromAddress");
		         		       		 												          fromAddress.appendChild(doc.createTextNode(removeNullComma(removeNull(String.valueOf(sOrder.getFromAddress().getAbbreviationName())))+" "+removeNullComma(removeNull(String.valueOf(sOrder.getFromAddress().getAddress1())))+" "+removeNullComma(removeNull(String.valueOf(sOrder.getFromAddress().getAddress2())))+" "+removeNullComma(removeNull(String.valueOf(sOrder.getFromAddress().getCity())))+" "+removeNullComma(removeNull(String.valueOf(sOrder.getFromAddress().getPostalCode())))+" "+removeNullComma(removeNull(String.valueOf(sOrder.getFromAddress().getProvinceCode())))+" "+removeNull(removeNull(String.valueOf(sOrder.getFromAddress().getCountryName())))+"."));
		         		       		 												          log1.appendChild(fromAddress);
		         		       		 												         }
		         		       		 												         	
		         		       		 												         if(sOrder.getToAddress() != null){
		         		       		 												         Element toAddress = doc.createElement("ToAddress");
		         		       		 												         toAddress.appendChild(doc.createTextNode(removeNullComma(removeNull(String.valueOf(sOrder.getToAddress().getAbbreviationName())))+" "+removeNullComma(removeNull(String.valueOf(sOrder.getToAddress().getAddress1())))+" "+removeNullComma(removeNull(String.valueOf(sOrder.getToAddress().getAddress2())))+" "+removeNullComma(removeNull(String.valueOf(sOrder.getToAddress().getCity())))+" "+removeNullComma(removeNull(String.valueOf(sOrder.getToAddress().getPostalCode())))+" "+removeNullComma(removeNull(String.valueOf(sOrder.getToAddress().getProvinceCode())))+" "+removeNull(removeNull(String.valueOf(sOrder.getToAddress().getCountryName())))+"."));
		         		       		 												         log1.appendChild(toAddress);
		         		       		 												         }
		         
		         Element Status = doc.createElement("Status");
		         		         Status.appendChild(doc.createTextNode(removeNull(sOrder.getStatusName())));
		         		         log1.appendChild(Status);
		         		         
		         
		         		         Element billingstatus = doc.createElement("BillingStatus");
		         		         billingstatus.appendChild(doc.createTextNode(removeNull(String.valueOf(sOrder.getBillingStatusText()))));
		         		         log1.appendChild(billingstatus);
		         		        Element Referencecode  = doc.createElement("ReferenceOne");
		         		       		         		         Referencecode.appendChild(doc.createTextNode(removeNull(String.valueOf(sOrder.getReferenceCode()))));
		         		       		         		         log1.appendChild(Referencecode);
		         		       		         		         Element Reference2 = doc.createElement("ReferenceTwo");
		         		       		         		         if(sOrder.getReferenceOne() != null && sOrder.getReferenceOne() != ""){
		         		       	         		        	 referenceone = String.valueOf(sOrder.getReferenceOne());
		         		       		         		         }
		         		       		         		         if(sOrder.getReferenceTwo() != null && sOrder.getReferenceTwo() != ""){
		         		       		         		        	 referencetwo = String.valueOf(sOrder.getReferenceTwo());
		         		       		         		         }
		         		       		         		         Reference2.appendChild(doc.createTextNode(removeNull(referenceone+" "+referencetwo)));
		         		       		         		         log1.appendChild(Reference2);
		         		       		 												    }
		         		       		 												    
		        /* if(sOrder.getCarrierName()!=null){
		         Carrier findCarrier = carrierServiceManager.getCarrier(sOrder.getCarrierId());
		         Element carrier = doc.createElement("carrier");
		         carrier.appendChild(doc.createTextNode(removeNull(sOrder.getCarrierName())));
		         log1.appendChild(carrier);
		         }
		         else{
		        	 Element carrier = doc.createElement("carrier");
			         carrier.appendChild(doc.createTextNode(removeNull(sOrder.getCarrierName())));
			         log1.appendChild(carrier);
		         }*/
		         
		         
		         
		         
		         
		         
		         
		         
		        
		          
		           
	           
		}
			try{
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
	          Transformer transformer = transformerFactory.newTransformer();
	           transformer.setOutputProperty(OutputKeys.INDENT, "yes");
	           DOMSource source = new DOMSource(doc);
	           StreamResult result = new StreamResult(new File(shippingLabelFileName));
	           transformer.transform(source, result);
			}catch(Exception e){
				e.printStackTrace();
			}
			
			System.out.println("File saved!");
	 }

	
	private void setOrderAddress(ShippingOrder order)
	{
		String fromCountry = "";
		String toCountry = "";
		
		List<Province> toProvinces;
		List<Province> fromProvinces;

		getSession().put("SHIP_MODE", "SHIP");

		if (order.getFromAddress() != null && order.getFromAddress().getCountryCode() != null)
			fromCountry = order.getFromAddress().getCountryCode();
		if (order.getToAddress() != null && order.getToAddress().getCountryCode() != null)
			toCountry = order.getToAddress().getCountryCode();

		//if not set set as default
		if(fromCountry==null ||"".equals(fromCountry)){
			fromCountry = UserUtil.getMmrUser().getBusiness().getAddress().getCountryCode();
		}
		if(toCountry==null ||"".equals(toCountry)){
			toCountry = UserUtil.getMmrUser().getBusiness().getAddress().getCountryCode();
		}

		toProvinces=addressService.getProvinces(toCountry);
		fromProvinces=addressService.getProvinces(fromCountry);

		getSession().put("Fromprovinces", fromProvinces);
		getSession().put("Toprovinces", toProvinces);
		getSession().put("ToCountry", toCountry);
		getSession().put("FromCountry", fromCountry);

		countries=MessageUtil.getCountriesList();

		getSession().put("CountryList", countries);
	}
	
	 public String setCustomer() throws Exception {

		    String customerId = request.getParameter("customerId");
		    ShippingOrder shippingOrder = getShippingOrder();
		    shippingOrder.setWebCustomerId(Long.valueOf(customerId));
		    // setting customer
		    shippingOrder.setCustomer(this.customerService.getCustomerInfoByCustomerId(Long
		        .valueOf(customerId)));
		    // shippingOrder.setCustomer(this.customerService.getCustomerInfoByCustomerId(shippingOrder.getCustomerId()));

		    List<Province> toProvinces;
		    List<Province> fromProvinces;
		    Address addressbookFrom;
		    Address addressbookTo;

		    if (shippingOrder.getId() == null || shippingOrder.getId() == 0) {

		      /*
		       * addressbookFrom =
		       * addressService.findDefaultFromAddressForCustomer(shippingOrder.getCustomerId());
		       * addressbookTo =
		       * addressService.findDefaultToAddressForCustomer(shippingOrder.getCustomerId());

		       */
		      addressbookFrom = addressService.findDefaultFromAddressForCustomer(Long.valueOf(customerId));
		      addressbookTo = addressService.findDefaultToAddressForCustomer(Long.valueOf(customerId));
		      shippingOrder.setToAddress(addressbookTo);
		      shippingOrder.setFromAddress(addressbookFrom);
		    } else {
		      addressbookFrom = shippingOrder.getFromAddress();
		      addressbookTo = shippingOrder.getToAddress();

		    }

		    if (addressbookFrom == null)
		      addressbookFrom = new Address();
		    if (addressbookTo == null)
		      addressbookTo = new Address();


		    String fromCountry = "";
		    String toCountry = "";

		    if (addressbookFrom != null)
		      fromCountry = addressbookFrom.getCountryCode();
		    if (addressbookTo != null)
		      toCountry = addressbookTo.getCountryCode();

		    // if not set set as default
		    if (fromCountry == null || "".equals(fromCountry)) {
		      fromCountry = UserUtil.getMmrUser().getBusiness().getAddress().getCountryCode();
		      addressbookFrom.setCountryCode(fromCountry);

		    }
		    if (toCountry == null || "".equals(toCountry)) {
		      toCountry = UserUtil.getMmrUser().getBusiness().getAddress().getCountryCode();
		      addressbookTo.setCountryCode(toCountry);

		    }

		    if (fromCountry == null || "".equals(fromCountry)) {
		      fromCountry = UserUtil.getMmrUser().getBusiness().getAddress().getCountryCode();
		      addressbookFrom.setCountryCode(fromCountry);

		    }
		    if (toCountry == null || "".equals(toCountry)) {
		      toCountry = UserUtil.getMmrUser().getBusiness().getAddress().getCountryCode();
		      addressbookTo.setCountryCode(toCountry);

		    }

		    toProvinces = addressService.getProvinces(toCountry);
		    fromProvinces = addressService.getProvinces(fromCountry);
		    this.populateUserList();
		    getSession().put("Fromprovinces", fromProvinces);
		    getSession().put("Toprovinces", toProvinces);
		    getSession().put("ToCountry", toCountry);
		    getSession().put("FromCountry", fromCountry);
		    if(shippingOrder.getFromAddress() != null){
		    	addressService.updateCustomerId(Long.valueOf(customerId),
		    				    shippingOrder.getFromAddress().getAddressId()); 
		    }
		    if(shippingOrder.getToAddress() != null){
		    	addressService.updateCustomerId(Long.valueOf(customerId),
		    				    shippingOrder.getToAddress().getAddressId());
		    }
		    return SUCCESS;

		  }

	 public String searchfrom() throws Exception {
		   
		   String customerId = request.getParameter("customerId");
		   String addressId = request.getParameter("addressId");
		      ShippingOrder shippingOrder = getShippingOrder();
		      // setting customer
		      
		      // shippingOrder.setCustomer(this.customerService.getCustomerInfoByCustomerId(shippingOrder.getCustomerId()));

		     
			    List<Province> toProvinces;
			    List<Province> fromProvinces;
			    Address addressbookFrom;
			    Address addressbookTo;
		     

		      if (shippingOrder.getId() == null || shippingOrder.getId() == 0) {
		        /*
		         * addressbookFrom =
		         * addressService.findDefaultFromAddressForCustomer(shippingOrder.getCustomerId());
		         * addressbookTo =
		         * addressService.findDefaultToAddressForCustomer(shippingOrder.getCustomerId());
		         */

			        addressbookFrom =addressService.findDefaultFromAddressForAddress(Long.valueOf(addressId));
				      shippingOrder.setFromAddress(addressbookFrom);	    	 
		     

		      } else {
		        addressbookFrom = shippingOrder.getFromAddress();
		       
		      }

		      if (addressbookFrom == null)
		        addressbookFrom = new Address();
		      

		      String fromCountry = "";
		     

		      if (addressbookFrom != null)
		        fromCountry = addressbookFrom.getCountryCode();
		     

		      // if not set set as default
		      if (fromCountry == null || "".equals(fromCountry)) {
		        fromCountry = UserUtil.getMmrUser().getBusiness().getAddress().getCountryCode();
		        addressbookFrom.setCountryCode(fromCountry);
		      }
		     

		      if (fromCountry == null || "".equals(fromCountry)) {
		        fromCountry = UserUtil.getMmrUser().getBusiness().getAddress().getCountryCode();
		        addressbookFrom.setCountryCode(fromCountry);
		      }
		     

		  
		      fromProvinces = addressService.getProvinces(fromCountry);

		      getSession().put("Fromprovinces", fromProvinces);
		     
		     
		      getSession().put("FromCountry", fromCountry);

		      return SUCCESS;
		  }
	 
	 public String searchto() throws Exception { 
		 String customerId = request.getParameter("customerId");
	    String addressId = request.getParameter("addressId");
	    ShippingOrder shippingOrder = getShippingOrder();
	    // setting customer
	    // shippingOrder.setCustomer(this.customerService.getCustomerInfoByCustomerId(shippingOrder.getCustomerId()));

	    List<Province> toProvinces;
	    List<Province> fromProvinces;
	    Address addressbookFrom;
	    Address addressbookTo;

	    if (shippingOrder.getId() == null || shippingOrder.getId() == 0) {

	      /*
	       * addressbookFrom =
	       * addressService.findDefaultFromAddressForCustomer(shippingOrder.getCustomerId());
	       * addressbookTo =
	       * addressService.findDefaultToAddressForCustomer(shippingOrder.getCustomerId());

	       */
	    		addressbookTo =  addressService.findDefaultFromAddressForAddress(Long.valueOf(addressId));
			      shippingOrder.setToAddress(addressbookTo);		    		
	    	
	    } else {
	      addressbookTo = shippingOrder.getToAddress();

	    }

	    if (addressbookTo == null)
	      addressbookTo = new Address();


	    String toCountry = "";

	    if (addressbookTo != null)
	      toCountry = addressbookTo.getCountryCode();

	    // if not set set as default

	    if (toCountry == null || "".equals(toCountry)) {
	      toCountry = UserUtil.getMmrUser().getBusiness().getAddress().getCountryCode();
	      addressbookTo.setCountryCode(toCountry);

	    }

	    if (toCountry == null || "".equals(toCountry)) {
	      toCountry = UserUtil.getMmrUser().getBusiness().getAddress().getCountryCode();
	      addressbookTo.setCountryCode(toCountry);

	    }

	    toProvinces = addressService.getProvinces(toCountry);

	    getSession().put("Toprovinces", toProvinces);
	    getSession().put("ToCountry", toCountry);
	    return SUCCESS;

	  }

	
	public String listFromProvience() throws Exception {
		String country;
		country = request.getParameter("value");
		if(country == null || "".equals(country))
			country = ShiplinxConstants.CANADA;
		if(getShippingOrder().getFromAddress() != null){
		getShippingOrder().getFromAddress().setCountryCode(country);
		provinces=addressService.getProvinces(country);
		getSession().put("Fromprovinces", provinces);
		}
		if(getShippingOrder().getFromAddress() == null){
			Address toAddress= new Address();
			toAddress.setCountryCode(country);
			getShippingOrder().setFromAddress(toAddress);
			}
		return SUCCESS;
	}
	
	public String listPickupProvience() throws Exception {
		String country;
		country = request.getParameter("value");
		if(country == null || "".equals(country))
			country = ShiplinxConstants.CANADA;
		//getShippingOrder().getFromAddress().setCountryCode(country);
		provinces=addressService.getProvinces(country);
		getSession().put("provinces", provinces);
		return SUCCESS;
	}
	
	public String listToProvience() throws Exception {
		String country;
		country = request.getParameter("value");
		if(country == null || "".equals(country))
			country = ShiplinxConstants.CANADA;
		
		String type = request.getParameter("type");		
		if(type!=null && type.equalsIgnoreCase("broker")){
			getShippingOrder().getCustomsInvoice().getBrokerAddress().setCountryCode(country);
			getSession().put("brokerProvinces", addressService.getProvinces(country));
			return "success2";
		}
		if(type!=null && type.equalsIgnoreCase("billTo")){
			getShippingOrder().getCustomsInvoice().getBillToAddress().setCountryCode(country);
			getSession().put("billToProvinces", addressService.getProvinces(country));
			return "success3";
		}
		if(type!=null && type.equalsIgnoreCase("buyer")){
			getShippingOrder().getCustomsInvoice().getBuyerAddress().setCountryCode(country);	
			getSession().put("buyerProvinces", addressService.getProvinces(country));
			return "success4";
		}
		if(getShippingOrder().getToAddress() != null){
		getShippingOrder().getToAddress().setCountryCode(country);
		getShippingOrder().getToAddress().setZipCodeRequired(false);
		}
		if(getShippingOrder().getToAddress() == null){
		Address toAddress= new Address();
		toAddress.setCountryCode(type);
		getShippingOrder().setToAddress(toAddress);
		}
		provinces=addressService.getProvinces(country);
		getSession().put("Toprovinces", provinces);
		return SUCCESS;
	}

	public String getAddressSuggest() throws Exception {

		String postalCode = (String)request.getParameter("postalCode");
		String country = (String)request.getParameter("countryCode");
		String type = (String)request.getParameter("type");
		
		Address address = new Address();
		address.setPostalCode(postalCode);
		address.setCountryCode(country);
		
		ServiceAvailabilityWebServiceClient zipCodeValidator = new ServiceAvailabilityWebServiceClient();
		address = zipCodeValidator.getSuggestedAddress(address);
		
		if(address!=null){
			inputStream = new StringBufferInputStream(address.getCity());	
			if(type.equalsIgnoreCase("from"))
				this.getShippingOrder().getFromAddress().setProvinceCode(address.getProvinceCode());
			else if (type.equalsIgnoreCase("to")) {
		        if (this.getShippingOrder() != null && this.getShippingOrder().getToAddress() != null) {
		          this.getShippingOrder().getToAddress().setProvinceCode(address.getProvinceCode());
		        } 
			}else if (type.equalsIgnoreCase("broker")) {
							        if (this.getShippingOrder() != null && this.getShippingOrder().getCustomsInvoice().getBrokerAddress() != null) {
								          this.getShippingOrder().getCustomsInvoice().getBrokerAddress().setProvinceCode(address.getProvinceCode());
								        } 
								      }
			else if (type.equalsIgnoreCase("clear")) {
		        if (this.getShippingOrder() != null && this.getShippingOrder().getCustomsInvoice().getBrokerAddress() != null) {
			          this.getShippingOrder().getCustomsInvoice().getBrokerAddress().setProvinceCode(address.getProvinceCode());
			        } 
			      }
			else if (type.equalsIgnoreCase("buyer")) {
							        if (this.getShippingOrder() != null && this.getShippingOrder().getToAddress() != null) {
								          this.getShippingOrder().getCustomsInvoice().getBuyerAddress().setProvinceCode(address.getProvinceCode());
								        } 
								      }
			else if(type.equalsIgnoreCase("pickup"))
				this.getPickup().getAddress().setProvinceCode(address.getProvinceCode());
		}
		return SUCCESS;
	}

	public String stageOne() throws Exception {
		ShippingOrder shippingOrder = getShippingOrder();
//		if(getSession().containsKey("shippingOrder"))
//			shippingOrder = (ShippingOrder) getSession().get("shippingOrder");
		
		getSession().put("PackageType",shippingOrder.getPackageTypeId().getType());
		return SUCCESS;
	}
	
	public String backToShipment() throws Exception {
	    log.debug("-----backToDimension--SSK------");
	    ShippingOrder shippingOrder = getShippingOrder();
	    
	    String fcountrycode = request.getParameter("shippingOrder.fromAddress.countryCode");
		String fpostalcode = request.getParameter("shippingOrder.fromAddress.postalCode");
		String fstate = request.getParameter("shippingOrder.fromAddress.provinceCode");
		String fcity = request.getParameter("shippingOrder.fromAddress.city");
		
		String tcountrycode = request.getParameter("shippingOrder.toAddress.countryCode");
		String tpostalcode = request.getParameter("shippingOrder.toAddress.postalCode");
		String tstate = request.getParameter("shippingOrder.toAddress.provinceCode");
		String tcity = request.getParameter("shippingOrder.toAddress.city");

	    // Start Sumanth Kulkarni 14 Oct 2011
	    // Removed Comments for the lines to get the ShippingOrder.
	    log.debug("-----backToDimension--SSK----1--");
	    if (getSession().containsKey("shippingOrder"))
	      shippingOrder = (ShippingOrder) getSession().get("shippingOrder");
	    // log.debug("package type::::::::::::"+shippingOrder.getPackageTypeId().getType());
	    log.debug("-----backToDimension--SSK----2--");
	    /*
	     * List<Package> packageList = shippingOrder.getPackages(); if (shippingOrder.getUnitmeasure()
	     * == 2) { for (Package pack : packageList) { float length = pack.getLength().floatValue() /
	     * 0.39370f; float width = pack.getWidth().floatValue() / 0.39370f; float height =
	     * pack.getHeight().floatValue() / 0.39370f;
	     * 
	     * BigDecimal length = new BigDecimal(lengthDouble); BigDecimal width = new
	     * BigDecimal(widthDouble); BigDecimal height = new BigDecimal(heightDouble);
	     * 
	     * pack.setWeight((pack.getWeight() / 2.2f)); pack.setLength(new BigDecimal(Math.round(length +
	     * 0.4)).setScale(2, BigDecimal.ROUND_HALF_UP)); pack.setWidth(new BigDecimal(Math.round(width +
	     * 0.4)).setScale(2, BigDecimal.ROUND_HALF_UP)); pack.setHeight(new BigDecimal(Math.round(height
	     * + 0.4)).setScale(2, BigDecimal.ROUND_HALF_UP)); } }
	     */

	    // End Sumanth Kulkarni

	    // Start Sumanth Kulkarni 14 Oct 2011
	    // commented code
	    /*
	     * if(shippingOrder.getQuantity()==null || shippingOrder.getPackages().size() == 0){ //Set the
	     * default dimension to the package ArrayList<Package> packages = new ArrayList<Package>();
	     * //default quantity is 1 if(shippingOrder.getQuantity()==null) shippingOrder.setQuantity(1);
	     * 
	     * for(int i=0;i<(int)shippingOrder.getQuantity(); i++){ Package pack = new Package();
	     * pack.setLength( new BigDecimal(1.0)); pack.setWeight( new Float(1.0)); pack.setHeight( new
	     * BigDecimal(1.0)); pack.setWidth( new BigDecimal(1.0)); pack.setCodAmount( new
	     * BigDecimal(0.0)); pack.setInsuranceAmount( new BigDecimal(0.0)); packages.add(pack); }
	     * 
	     * shippingOrder.setPackages(packages);
	     * 
	     * }
	     */
	    // End Sumanth Kulkarni

	    // Code to display additional fields when package type is 'Pallet'
	    if ("type_pallet".equals(shippingOrder.getPackageTypeId().getType())) {
	      request.setAttribute("pallet", true);
	    }
	    String mode = (String) getSession().get("SHIP_MODE");
	    String switchFlag = (String) request.getParameter("switch");
	    if (switchFlag != null) {
	      if (mode == null || mode.equalsIgnoreCase("SHIP")) {
	        getSession().put("SHIP_MODE", "QUOTE");
	        mode = new String("QUOTE");
	        shippingOrder.getFromAddress().setCountryCode(fcountrycode);
	        shippingOrder.getFromAddress().setPostalCode(fpostalcode);
	         shippingOrder.getFromAddress().setProvinceCode(fstate);
	          shippingOrder.getFromAddress().setCity(fcity);
	         
	          shippingOrder.getToAddress().setCountryCode(tcountrycode);
		        shippingOrder.getToAddress().setPostalCode(tpostalcode);
		        shippingOrder.getToAddress().setProvinceCode(tstate);
		        shippingOrder.getToAddress().setCity(tcity);
	      } else {
	        getSession().put("SHIP_MODE", "SHIP");
	        mode = new String("SHIP");
	        shippingOrder.getFromAddress().setCountryCode(fcountrycode);
	        shippingOrder.getFromAddress().setPostalCode(fpostalcode);
	         shippingOrder.getFromAddress().setProvinceCode(fstate);
	          shippingOrder.getFromAddress().setCity(fcity);
	         
	          shippingOrder.getToAddress().setCountryCode(tcountrycode);
		        shippingOrder.getToAddress().setPostalCode(tpostalcode);
		        shippingOrder.getToAddress().setProvinceCode(tstate);
		        shippingOrder.getToAddress().setCity(tcity);
	      }
	    } else {
	      List<Package> packageList = shippingOrder.getPackages();
	      if (shippingOrder.getUnitmeasure() == 2) {
	        for (Package pack : packageList) {
	          float length = pack.getLength().floatValue() / 0.39370f;
	          float width = pack.getWidth().floatValue() / 0.39370f;
	          float height = pack.getHeight().floatValue() / 0.39370f;
	          /*
	           * BigDecimal length = new BigDecimal(lengthDouble); BigDecimal width = new
	           * BigDecimal(widthDouble); BigDecimal height = new BigDecimal(heightDouble);
	           */
	          float weight= pack.getWeight().floatValue()/2.2f;
	            pack.setWeight(new BigDecimal(weight).setScale(2,
	                BigDecimal.ROUND_HALF_UP));
	          pack.setLength(new BigDecimal(Math.round(length + 0.4)).setScale(2,
	              BigDecimal.ROUND_HALF_UP));
	          pack.setWidth(new BigDecimal(Math.round(width + 0.4)).setScale(2,
	              BigDecimal.ROUND_HALF_UP));
	          pack.setHeight(new BigDecimal(Math.round(height + 0.4)).setScale(2,
	              BigDecimal.ROUND_HALF_UP));
	        }
	      }
	    }

	    // for quick shipment: to populate the service if the carrier was selected before.
	    List<Service> services = null;
	    services = new ArrayList();
	    Service ser = new Service();
	    // if(shippingOrder.isQuickShipRequired())
	    // {
	    //
	    // if(shippingOrder.getCarrierId_web()>0) //check if carrier selected is 'ANY', then dont
	    // include 'ANY' option in the service dropdown.
	    // {
	    // ser.setName("ANY");
	    // ser.setId(-1L);
	    // }
	    // else
	    // {
	    // ser.setName("");
	    // ser.setId(0L);
	    // }
	    // }
	    // else
	    // {
	    // ser.setName("------Select------");
	    // ser.setId(0l);
	    // }
	    // services.add(ser);
	    // setting the role in req attribute to enable or disable address fields based on role.

	    User user = UserUtil.getMmrUser();
        userDAO = (UserDAO)MmrBeanLocator.getInstance().findBean("userDAO");
        uom=userDAO.unitOfMeasure();
        User unitofmeasure = userDAO.findunitofmeasure(user.getUsername());
        if(user != null && unitofmeasure.getUnitmeasure()==2){
        	for(int i=0;i<uom.size();i++){
        		if(unitofmeasure != null && unitofmeasure.getUnitmeasure()==uom.get(i).getUnitOfMeasureId()){
        			Collections.swap(uom, 0, i);
        		}
        	}    	
        }


	    if (getLoginUser().getUserRole().equalsIgnoreCase(ShiplinxConstants.ROLE_CUSTOMER_SHIPPER))
	      request.setAttribute("USERROLE", "shipper");
	    if (shippingOrder.getCarrierId_web() != null && shippingOrder.getCarrierId_web() > 0) {
	      services
	          .addAll(carrierServiceManager.getServicesForCarrier(shippingOrder.getCarrierId_web()));
	    }
	    getSession().put("SERVICES", services);
	    if (mode == null || mode.equalsIgnoreCase("SHIP"))
	      return SUCCESS;
	    return "successQuote";
	  }
	
	public String backToPackageInformation() throws Exception {
		ShippingOrder shippingOrder = getShippingOrder();
//		if(getSession().containsKey("shippingOrder"))
//			shippingOrder = (ShippingOrder) getSession().get("shippingOrder");
		
		getSession().put("PackageType",shippingOrder.getPackageTypeId().getType());
		
		return SUCCESS;
	}
	/**
	 * Package information
	 * @return
	 * @throws Exception
	 */
//	public String packageInformation() throws Exception {
//		String packageType=ShiplinxConstants.PACKAGE_TYPE_ENVELOPE_STRING; 
//
//		try{
//			List<PackageType> listPackages = shippingService.getPackages();
//			List<HashMap<String, String>> packageOption = new ArrayList<HashMap<String, String>>();
//			
//			// To generate packages radio buttons on jsp
//			for(PackageType pType :listPackages){
//				HashMap<String, String> packages = new HashMap<String, String>();
//				packages.put(pType.getType(),pType.getName());
//				packageOption.add(packages);
//			}
//			getSession().put("packageOptions", packageOption);
//			
//			//if("".equals(request.getParameter("method")) && getSession().containsKey("shippingOrder"))
//			//	shippingOrder = (ShippingOrder) getSession().get("shippingOrder");
//		
//			ShippingOrder shippingOrder = getShippingOrder();
//				//set the default package
//				if(shippingOrder != null && shippingOrder.getPackageTypeId() != null){
//
//					if(shippingOrder.getPackageTypeId().getType() == null || "".equalsIgnoreCase(shippingOrder.getPackageTypeId().getType())){	
//						shippingOrder.setPackageTypeId(shippingService.findPackageType(packageType));
//					}
//					else{	
//						packageType = shippingOrder.getPackageTypeId().getType();	
//					}
//				}
//			
//				Long fromId = null;
//				Long toId = null;
////			if(shippingOrder.isSaveFromAddress() != null && shippingOrder.isSaveFromAddress()){
////				log.debug("------------------Save address as from address----------------");
////				Address address = shippingOrder.getFromAddress();
////				address.setDefaultFromAddress(true);
////				address.setCustomerId(getLoginUser().getCustomerId());
////				address.setIsCustomerOwnInfo("N");
////				
////				fromId  = addressBookService.add(addressbook);
////				log.debug("------addressbook.getId()------"+fromId);
////				shippingOrder.setShipFromId(addressBookService.findAddressById(fromId+""));
////			}
////		
////			if(shippingOrder.isSaveToAddress() != null && shippingOrder.isSaveToAddress()){
////				log.debug("------------------Save address as to address----------------");
////				Address addressbook = shippingOrder.getShipToId();
////				addressbook.setDefaultToAddress(true);
////				addressbook.setCustomerId(getLoginUser().getCustomerId());
////				addressbook.setIsCustomerOwnInfo("N");
////				
////				toId = addressBookService.add(addressbook);
////				log.debug("------addressbook.getId()------"+toId);
////				shippingOrder.setShipToId(addressBookService.findAddressById(toId+""));
////			}
//		
//			//Set the default dimension to the package
//			ArrayList<Package> packages = new ArrayList<Package>();
//			Package packageArray[] = new Package[1];
//			
//			
//			// *FKhan* - Oct. 4 -2011 - There is no need for the following code
////			if(((ShippingOrder)getSession().get("shippingOrder")) != null){
////				
////				ShippingOrder shippingOrder1=(ShippingOrder)getSession().get("shippingOrder");
////				
////				shippingOrder.setQuantity(shippingOrder1.getQuantity());
////				shippingOrder.setPackageTypeId(shippingOrder1.getPackageTypeId());
////				shippingOrder.setReferenceCode(shippingOrder1.getReferenceCode());
////				shippingOrder.setSatDelivery(shippingOrder1.getSatDelivery());
////				shippingOrder.setHoldForPickupRequired(shippingOrder1.getHoldForPickupRequired());
////				shippingOrder.setInsideDelivery(shippingOrder1.getInsideDelivery());
////				shippingOrder.setPickupTime(shippingOrder1.getPickupTime());
////				shippingOrder.setSpecialEquipment(shippingOrder1.getSpecialEquipment());
////				shippingOrder.setCODPayment(shippingOrder1.getCODPayment());
////				shippingOrder.setCODValue(shippingOrder1.getCODValue());
////				shippingOrder.setCODCurrency(shippingOrder1.getCODCurrency());
////				shippingOrder.setCODPayableTO(shippingOrder1.getCODPayableTO());
////				shippingOrder.setDangerousGoods(shippingOrder1.getDangerousGoods());
////				shippingOrder.setReqDeliveryDate(shippingOrder1.getReqDeliveryDate());
////				shippingOrder.setScheduledShipDate(shippingOrder1.getScheduledShipDate());
////				shippingOrder.setPickUpNum(shippingOrder1.getPickUpNum());
////				shippingOrder.setReferenceOne(shippingOrder1.getReferenceOne());
////			}
//				
//			//default quantity is 1
//			if(shippingOrder.getQuantity()==null)
//				shippingOrder.setQuantity(1);
//			
//			packageArray = new Package[(int)shippingOrder.getQuantity()];
//			
//			for(int i=0;i<(int)shippingOrder.getQuantity(); i++){
//				List<Package> packages1=shippingOrder.getPackages();
//				if(i<packages1.size()){
//					log.debug("--getWeight--"+packages1.get(i).getWeight());
//					packages.add(packages1.get(i));
//				}else{
//					Package pack = new Package();
//					pack.setLength( new BigDecimal(1.0));
//					pack.setWeight( new Float(1.0));
//					pack.setHeight( new BigDecimal(1.0));
//					pack.setWidth( new BigDecimal(1.0));
//					pack.setCodAmount( new BigDecimal(0.0));
//					pack.setInsuranceAmount( new BigDecimal(0.0));
//					packages.add(pack);
//					packageArray[i] = pack;
//				}
//			}
//	
////			RIZM - COMMENTING OUT IN ORDER TO REFACTOR ADDRESS OBJECT
////			if(((ShippingOrder)getSession().get("shippingOrder")).getShipFromId() != null)
////			shippingorder.getFromAddress().setId(((ShippingOrder)getSession().get("shippingOrder")).getShipFromId().getId());
////			if(((ShippingOrder)getSession().get("shippingOrder")).getShipToId() != null)
////			shippingOrder.getShipToId().setId(((ShippingOrder)getSession().get("shippingOrder")).getShipToId().getId());
////	
////			if(fromId != null)
////					shippingorder.getFromAddress().setId(fromId);
////			if(toId != null)
////				shippingOrder.getShipToId().setId(toId);
//			
//			shippingOrder.setPackageArray(packageArray);
//			shippingOrder.setPackages(packages);
//				
//			String orderId=(String)getSession().get("EDIT_ORDER_ID");
//			if(orderId!=null){
//			
//				ShippingOrder shippingOrder1=shippingService.getShippingOrder(Long.parseLong(orderId));
//								shippingOrder.setReferenceCode(shippingOrder1.getReferenceCode());
//								shippingOrder.setSatDelivery(shippingOrder1.getSatDelivery());
//								shippingOrder.setHoldForPickupRequired(shippingOrder1.getHoldForPickupRequired());
//								shippingOrder.setInsideDelivery(shippingOrder1.getInsideDelivery());
//								shippingOrder.setPickupTime(shippingOrder1.getPickupTime());
//								shippingOrder.setSpecialEquipment(shippingOrder1.getSpecialEquipment());
//								shippingOrder.setCODPayment(shippingOrder1.getCODPayment());
//								shippingOrder.setCODValue(shippingOrder1.getCODValue());
//								shippingOrder.setCODCurrency(shippingOrder1.getCODCurrency());
//								shippingOrder.setCODPayableTO(shippingOrder1.getCODPayableTO());
//								shippingOrder.setDangerousGoods(shippingOrder1.getDangerousGoods());
//								shippingOrder.setReqDeliveryDate(shippingOrder1.getReqDeliveryDate());
//								shippingOrder.setScheduledShipDate(shippingOrder1.getScheduledShipDate());
//								shippingOrder.setPickUpNum(shippingOrder1.getPickUpNum());
//								shippingOrder.setReferenceOne(shippingOrder1.getReferenceOne());
//								shippingOrder.setReferenceTwo(shippingOrder1.getReferenceTwo());
//								shippingOrder.setMasterTrackingNum(shippingOrder1.getMasterTrackingNum());
//								shippingOrder.setRes(shippingOrder1.getRes());
//								shippingOrder.setFromTailgate(shippingOrder1.getFromTailgate());
//								shippingOrder.setToTailgate(shippingOrder1.getToTailgate());
//								shippingOrder.setFromAttention(shippingOrder1.getFromAttention());
//								shippingOrder.setToAttention(shippingOrder1.getToAttention());
//								shippingOrder.setNotifyRecipient(shippingOrder1.getNotifyRecipient());
//								shippingOrder.setConfirmDelivery(shippingOrder1.getConfirmDelivery());
//			}
////			getSession().put("shippingOrder",shippingOrder);
//			if("dimensionInformation".equals(request.getParameter("method"))){
//				return "DIMENSSION_PAGE";
//			}
//		
//		}catch (Exception e) {
//			log.debug("----Exception-----"+e);
//			e.printStackTrace();
//		}
//			return SUCCESS; //default is type_env
//		
//	}
	
	/**
	 * Dimension information
	 * @return
	 * @throws Exception
	 */
	public String dimensionInformation() throws Exception {
		ShippingOrder shippingOrder = getShippingOrder();
//		shippingOrder = (ShippingOrder)getSession().get("shippingOrder");
		//Set the default dimension to the package
		ArrayList<Package> packages = new ArrayList<Package>();
		Package packageArray[] = new Package[1];

		String quantity = request.getParameter("quantity");
		String type = request.getParameter("type");
		
		try{
		
		//default quantity is 1
		if(quantity!=null){
			shippingOrder.setQuantity(Integer.valueOf(quantity));
		}
		if(type!=null){
			if(type.equalsIgnoreCase(ShiplinxConstants.PACKAGE_TYPE_ENVELOPE_STRING) || type.equalsIgnoreCase(ShiplinxConstants.PACKAGE_TYPE_PAK_STRING))
				shippingOrder.setQuantity(1);

			PackageType packageType  = new PackageType();		
			packageType  = shippingService.findPackageType(type);			
			shippingOrder.setPackageTypeId(packageType);

		}
		
		packageArray = new Package[(int)shippingOrder.getQuantity()];
		
		for(int i=0;i<(int)shippingOrder.getQuantity(); i++){
			List<Package> packages1=((ShippingOrder)getSession().get("shippingOrder")).getPackages();
			if(i<packages1.size()){
				log.debug("--getWeight--"+request.getParameter("shippingOrder.packageArray["+ String.valueOf(i) + "].weight"));
				packages.add(packages1.get(i));
			}else{
				Package pack = new Package();
		          pack.setLength(new BigDecimal(1.00).setScale(2,BigDecimal.ROUND_HALF_UP));
		          pack.setWeight(new BigDecimal(1.00).setScale(2,BigDecimal.ROUND_HALF_UP));
		          pack.setHeight(new BigDecimal(1.00).setScale(2,BigDecimal.ROUND_HALF_UP));
		          pack.setWidth(new BigDecimal(1.00).setScale(2,BigDecimal.ROUND_HALF_UP));
		          pack.setCodAmount(new BigDecimal(0.00).setScale(2,BigDecimal.ROUND_HALF_UP));
		          pack.setInsuranceAmount(new BigDecimal(0.00).setScale(2,BigDecimal.ROUND_HALF_UP));
		          packages.add(pack);
		          packageArray[i] = pack;
			}
		}

		
		shippingOrder.setPackageArray(packageArray);
		shippingOrder.setPackages(packages);
		
		
		}catch (Exception e) {
			log.debug("exception-------------"+e);
			e.printStackTrace();
		}
		
		getSession().put("shippingOrder",shippingOrder);
		
		return SUCCESS;
	}
	
	
	
//	public String stageTwo() throws Exception {
//		String packageType=""; 
//		ShippingOrder shippingOrder = getShippingOrder();
//		try{
//			//set the default package type
//			if(shippingOrder != null && shippingOrder.getPackageTypeId() != null){
//				
//				if(shippingOrder.getPackageTypeId().getType() == null || "".equalsIgnoreCase(shippingOrder.getPackageTypeId().getType())){	
//					packageType= "type_env"; //default
//				}
//				else
//					packageType = shippingOrder.getPackageTypeId().getType();
//			}
//			
//			PackageType packType =  shippingService.findPackageType(packageType);
//			shippingOrder.setPackageTypeId(packType);
//			
//			//find package by name selected
//			shippingOrder.setPackageTypeId(shippingOrder.getPackageTypeId());
//			
//			//Set the default dimension to the package
//			ArrayList<Package> packages = new ArrayList<Package>();
//			
//			//default quantity is 1
//			if(shippingOrder.getQuantity()==null)
//				shippingOrder.setQuantity(1);
//
//			String orderId=(String)getSession().get("EDIT_ORDER_ID");
//		
//			if(orderId==null){
//			
//			for(int i=0;i<(int)shippingOrder.getQuantity(); i++){
//				
//				List<Package> packages1=((ShippingOrder)getSession().get("shippingOrder")).getPackages();
//				if(i<packages1.size()){
//					log.debug("--getWeight--"+packages1.get(i).getWeight());
//					packages.add(packages1.get(i));
//				}else{
//					Package pack = new Package();
//					pack.setLength( new BigDecimal(1.0));
//					pack.setWeight( new Float(1.0));
//					pack.setHeight( new BigDecimal(1.0));
//					pack.setWidth( new BigDecimal(1.0));
//					pack.setCodAmount( new BigDecimal(0.0));
//					pack.setInsuranceAmount( new BigDecimal(0.0));
//					packages.add(pack);
//				}
//			}
//			
//			}else{
//				ArrayList<Package> packages1=(ArrayList<Package>)shippingService.getShippingPackages(Long.parseLong(orderId));
//				for(int i=0;i<(int)shippingOrder.getQuantity(); i++){
//					if(i<packages1.size()){
//						packages.add(packages1.get(i));
//					}else{
//						Package pack = new Package();
//						pack.setLength( new BigDecimal(1.0));
//						pack.setWeight( new Float(1.0));
//						pack.setHeight( new BigDecimal(1.0));
//						pack.setWidth( new BigDecimal(1.0));
//						pack.setCodAmount( new BigDecimal(0.0));
//						pack.setInsuranceAmount( new BigDecimal(0.0));
//						packages.add(pack);
//					}
//				}
//			}
//			
//			shippingOrder.setShipFromId(((ShippingOrder)getSession().get("shippingOrder")).getShipFromId());
//			shippingOrder.setShipToId(((ShippingOrder)getSession().get("shippingOrder")).getShipToId());
//			shippingOrder.setRecidential(((ShippingOrder)getSession().get("shippingOrder")).getRecidential());
//			shippingOrder.setNotifyRecipient(((ShippingOrder)getSession().get("shippingOrder")).getNotifyRecipient());
//			shippingOrder.setConfirmDelivery(((ShippingOrder)getSession().get("shippingOrder")).getConfirmDelivery());
//			shippingOrder.setToTailgate(((ShippingOrder)getSession().get("shippingOrder")).getToTailgate());
//			shippingOrder.setFromTailgate(((ShippingOrder)getSession().get("shippingOrder")).getFromTailgate());
//			
//			shippingOrder.setPackages(packages);
//			getSession().put("shippingOrder",shippingOrder);
//			
//			//to maintain the values while click on tabs
//			if("stageOne".equals(request.getParameter("method"))){
//				return "STAGE_ONE";
//			}
//			if("backToDimension".equals(request.getParameter("method"))){
//				return "DIMENSSION_PAGE";
//			}
//			if(packageType.equalsIgnoreCase("type_pallet") || packageType.equalsIgnoreCase("type_package"))
//				return "DIMENSSION_PAGE";
//		
//		}catch (Exception e) {
//			e.printStackTrace();
//			log.debug("----Exception-----"+e);
//			addActionError(getText("error.input"));
//			return INPUT;
//		}
//			return SUCCESS; //default is type_env
//		
//	}
	
	
	public String setShippingAddress() throws Exception {
		String fromCountry = "";
		String toCountry = "";
		String countryCode="";
		List<Province> fromProvinces;
		List<Province> toProvinces;
		String abbrevationBroker = request.getParameter("abbrbroker");
		String type = request.getParameter("type");
		String addressId = request.getParameter("addressid");
		String strReturn = null;
		if(type==null || addressId==null)
			return SUCCESS;
		
//		if(getSession().containsKey("shippingOrder"))
//			shippingOrder = (ShippingOrder) getSession().get("shippingOrder");
		ShippingOrder shippingOrder = getShippingOrder();
		if("fromAddress".equals(type)){
			Address address = addressService.findAddressById(addressId);
			
			fromCountry = address.getCountryCode();
			shippingOrder.setFromAddress(address);
			shippingOrder.getFromAddress().setCustomerId(0);
			getSession().put("shippingOrder",shippingOrder);
			//request.setAttribute("shippingOrder",shippingOrder);
			getSession().put("FromCountry", fromCountry);
			fromProvinces=addressService.getProvinces(fromCountry);
			getSession().put("Fromprovinces", fromProvinces);
			strReturn = "success1";
			}
		else if("toAddress".equals(type)){
			Address address = addressService.findAddressById(request.getParameter("addressid"));
			
			toCountry = address.getCountryCode();
			shippingOrder.setToAddress(address);
			shippingOrder.getToAddress().setCustomerId(0);
			getSession().put("shippingOrder",shippingOrder);
			//request.setAttribute("shippingOrder",shippingOrder);
			getSession().put("ToCountry", toCountry);
			toProvinces=addressService.getProvinces(toCountry);
			getSession().put("Toprovinces", toProvinces);
			strReturn = "success2";
		}
		else if("brokerAddress".equals(type)){
			Address address = addressService.findAddressById(request.getParameter("addressid"));			
			shippingOrder.getCustomsInvoice().getBrokerAddress().copyAddress(address);	
			//shippingOrder.getCustomsInvoice().setBrokerAddress(address);
			getSession().put("shippingOrder",shippingOrder);
			getSession().put("br_abbreviationName",address.getAbbreviationName());
			getSession().put("br_address1",address.getAddress1());
			getSession().put("br_address2",address.getAddress2());
			getSession().put("br_postalCode",address.getPostalCode());
			getSession().put("br_city",address.getCity());
			getSession().put("br_countryCode",address.getCountryCode());
			getSession().put("br_phoneNo",address.getPhoneNo());
			getSession().put("br_faxNo",address.getFaxNo());
			getSession().put("br_emailAddress",address.getEmailAddress());
			getSession().put("br_contactName",address.getContactName());	
			strReturn = "success4";
		}
		
		else if("buyerAddress".equals(type)){
			Address address = addressService.findAddressById(request.getParameter("addressid"));			
			shippingOrder.getCustomsInvoice().getBuyerAddress().copyAddress(address);
			//shippingOrder.getCustomsInvoice().setBrokerAddress(address);
			getSession().put("shippingOrder",shippingOrder);
			getSession().put("buyer_abbreviationName",address.getAbbreviationName());
			getSession().put("buyer_address1",address.getAddress1());
			getSession().put("buyer_address2",address.getAddress2());
			getSession().put("buyer_postalCode",address.getPostalCode());
			getSession().put("buyer_city",address.getCity());
			getSession().put("buyer_countryCode",address.getCountryCode());
			getSession().put("buyer_taxId",address.getTaxId());
			strReturn = "success5";
		}
		else if(type.equalsIgnoreCase("pickupNew"))	//pickup address
					{	
						Pickup pickup = this.getPickup();
						Address address = addressService.findAddressById(request.getParameter("addressid"));
						
						toCountry = address.getCountryCode();
						//shippingOrder.setToAddress(address);
						//shippingOrder.getToAddress().setCustomerId(0);
						pickup.setAddress(address);
						//getSession().put("shippingOrder",shippingOrder);
						//request.setAttribute("shippingOrder",shippingOrder);
						getSession().put("ToCountry", toCountry);
						toProvinces=addressService.getProvinces(toCountry);
						getSession().put("Toprovinces", toProvinces);
						getSession().put("provinces", toProvinces);
						getSession().put("pickup", pickup);
						strReturn = "success6";
						
					}
		else if("brokerAddressNew".equals(type)){
						   Address address = addressService.findAddressById(request.getParameter("addressid"));   
						   shippingOrder.getCustomsInvoice().getBrokerAddress().copyAddress(address); 
						   shippingOrder.getCustomsInvoice().getBrokerAddress().setAbbreviationName(abbrevationBroker);
						   
						   
						   fromCountry = address.getCountryCode();
						   fromProvinces = addressService.getProvinces(fromCountry);
			
						        getSession().put("brokerProvinces", fromProvinces);
						   //shippingOrder.getCustomsInvoice().setBrokerAddress(address);
						   getSession().put("shippingOrder",shippingOrder);
						   getSession().put("br_abbreviationName",address.getAbbreviationName());
						   getSession().put("br_address1",address.getAddress1());
						   getSession().put("br_address2",address.getAddress2());
						   getSession().put("br_postalCode",address.getPostalCode());
						   getSession().put("br_city",address.getCity());
						   getSession().put("br_countryCode",address.getCountryCode());
						   getSession().put("br_phoneNo",address.getPhoneNo());
						   getSession().put("br_faxNo",address.getFaxNo());
						   getSession().put("br_emailAddress",address.getEmailAddress());
						   getSession().put("br_contactName",address.getContactName()); 
						   strReturn = "success9";
						  }
		else 	//pickup address
		{	
			Pickup pickup = this.getPickup();
			Address address = addressService.findAddressById(request.getParameter("addressid"));
			
			toCountry = address.getCountryCode();
			//shippingOrder.setToAddress(address);
			//shippingOrder.getToAddress().setCustomerId(0);
			pickup.setAddress(address);
			//getSession().put("shippingOrder",shippingOrder);
			//request.setAttribute("shippingOrder",shippingOrder);
			getSession().put("ToCountry", toCountry);
			toProvinces=addressService.getProvinces(toCountry);
			getSession().put("Toprovinces", toProvinces);
			strReturn = "success3";
			
		}
		getSession().remove("type");
		return strReturn; 
	}
	
	
	
	/**
	 * Rating
	 * @return
	 * @throws Exception
	 */
	public String stageThree() throws Exception {
	    log.debug("-----------stageThree-------------");
	    String customs = request.getParameter("customs");
	    String stagethree = request.getParameter("getrates");
	    String emailNotification = request.getParameter("emailNotify");
	    String emailNotification2 = request.getParameter("emailNotify2");
	    ShippingOrder shippingOrder = getShippingOrder();
	   /* if(shippingOrder.getFromAddress().getCountryCode().equals("US")&&(shippingOrder.getToAddress().getCountryCode().equals("US"))){
			shippingOrder.setCurrency("USD");
		}*/
	    shippingOrder.setMarkPercent(null);
	    List<RecordList> pickuplist = pickupService.getPickupList();
	    session.put("pickuplist", pickuplist);
	    List<Package> packageList = shippingOrder.getPackages();
	    if (shippingOrder.getUnitmeasure() == 2) {
	      for (Package pack : packageList) {
	        double lengthDouble = pack.getLength().doubleValue() * 0.39370;
	        double widthDouble = pack.getWidth().doubleValue() * 0.39370;
	        double heightDouble = pack.getHeight().doubleValue() * 0.39370;
	        BigDecimal length = new BigDecimal(lengthDouble);
	        BigDecimal width = new BigDecimal(widthDouble);
	        BigDecimal height = new BigDecimal(heightDouble);
	        //pack.setWeight(pack.getWeight().multiply(new BigDecimal(2.2)));
	        BigDecimal weight = pack.getWeight().multiply(new BigDecimal(2.2));
	        pack.setWeight(weight.setScale(2, BigDecimal.ROUND_HALF_UP));
	        pack.setLength(length.setScale(2, BigDecimal.ROUND_HALF_UP));
	        pack.setWidth(width.setScale(2, BigDecimal.ROUND_HALF_UP));
	        pack.setHeight(height.setScale(2, BigDecimal.ROUND_HALF_UP));
	      }
	    }
	    shippingOrder=applyCOD(shippingOrder);
	    String displayTextLocale = null;
	    userDAO = (UserDAO) MmrBeanLocator.getInstance().findBean("userDAO");
	    displayTextLocale = userDAO.getDisplayTextByLocale(UserUtil.getMmrUser().getLocale()).getDisplayText();
	    if(displayTextLocale != null && !displayTextLocale.isEmpty()){
	    	billduty = customerService.getBilldutyList(displayTextLocale);
	    	getSession().put("billduty", billduty);
	    }else{
	    	billduty = customerService.getBilldutyList(UserUtil.getMmrUser().getLocale());
	    	getSession().put("billduty", billduty);
	    }
	    Package packageArray[] = shippingOrder.getPackageArray();
	    log.debug("packageArray.length::::" + packageArray.length);
	    if (shippingOrder.getCustomsInvoice() == null)
	      shippingOrder.setCustomsInvoice(new CustomsInvoice());

	    CustomsInvoice customsInvoice = shippingOrder.getCustomsInvoice();
	    CustomsInvoiceProduct customsInvoiceProduct = null;
	    if (customsInvoice != null && customsInvoice.getId() >= 0
	        && customsInvoice.getProducts() != null && customsInvoice.getProducts().size() > 0) {
	      customsInvoiceProduct = customsInvoice.getProducts().get(0);
	    } else {
	      customsInvoiceProduct = new CustomsInvoiceProduct();
	    }

	    // set the total value of the customs invoice, it should be done in business layer, but this is
	    // a fix for now
	    if (shippingOrder.getCustomsInvoice().getTotalValue() != null
	        && shippingOrder.getCustomsInvoice().getTotalValue() == 0
	        && shippingOrder.getCustomsInvoice().getProducts() != null
	        && shippingOrder.getCustomsInvoice().getProducts().size() > 0) {
	      for (CustomsInvoiceProduct cip : shippingOrder.getCustomsInvoice().getProducts()) {
	        shippingOrder.getCustomsInvoice().setTotalValue(
	            FormattingUtil.add(shippingOrder.getCustomsInvoice().getTotalValue(),
	                cip.getProductTotalPrice()).doubleValue());
	      }
	    }

	    if (customsInvoice.getTotalWeight() == null
	        || customsInvoice.getTotalWeight().doubleValue() == 0) {
	      customsInvoice.setTotalWeight(shippingOrder.getTotalWeight().longValue());
	    }

	    if (shippingOrder.getCustomsInvoice().getBrokerAddress() == null) {
	      shippingOrder.getCustomsInvoice().setBrokerAddress(new Address());
	      shippingOrder.getCustomsInvoice().getBrokerAddress()
	          .setCountryCode(shippingOrder.getToAddress().getCountryCode());

	    }

	    if (shippingOrder.getCustomsInvoice().getBillToAddress() == null) {
	      shippingOrder.getCustomsInvoice().setBillToAddress(new Address());
	    }
	    if (shippingOrder.getCustomsInvoice().getBuyerAddress() == null) {
	      shippingOrder.getCustomsInvoice().setBuyerAddress(new Address());
	    }
	    if (shippingOrder.getCustomsInvoice().getBillToAddress().getCountryCode() == null
	        || shippingOrder.getCustomsInvoice().getBillToAddress().getCountryCode().length() == 0)
	      shippingOrder.getCustomsInvoice().getBillToAddress()
	          .setCountryCode(shippingOrder.getToAddress().getCountryCode());
	    if (shippingOrder.getCustomsInvoice().getBuyerAddress().getCountryCode() == null
	        || shippingOrder.getCustomsInvoice().getBuyerAddress().getCountryCode().length() == 0)
	      shippingOrder.getCustomsInvoice().getBuyerAddress()
	          .setCountryCode(shippingOrder.getToAddress().getCountryCode());
	    getSession().put(
	        "brokerProvinces",
	        addressService.getProvinces(shippingOrder.getCustomsInvoice().getBrokerAddress()
	            .getCountryCode()));
	    getSession().put(
	        "billToProvinces",
	        addressService.getProvinces(shippingOrder.getCustomsInvoice().getBillToAddress()
	            .getCountryCode()));
	    getSession().put(
	        "buyerProvinces",
	        addressService.getProvinces(shippingOrder.getCustomsInvoice().getBuyerAddress()
	            .getCountryCode()));

	    if (packageList.size() != 0)
	      shippingOrder.setPackages(packageList);

	    // todo : accept from UI
	    shippingOrder.setCODValue(0d);
	    // shippingOrder.setCustomerId(getLoginUser().getCustomerId());

	    PackageType packageType = new PackageType();
	    packageType = shippingService.findPackageType(shippingOrder.getPackageTypeId().getType());
	    shippingOrder.setPackageTypeId(packageType);

	    // set default quantity
	    if (shippingOrder.getQuantity() == null) {
	      shippingOrder.setQuantity(1);
	    }

	    for (int i = 0; i < (int) shippingOrder.getQuantity(); i++) {

	      List<Package> packages1 = shippingOrder.getPackages();

	    }
	    // Broker Information code added by Mohan on 26-11-2013
	    long customerId = 0l;
	    if (shippingOrder != null && shippingOrder.getCustomerId() != null) {
	      customerId = shippingOrder.getCustomerId();
	    } else {
	      customerId = ((shippingOrder.getWebCustomerId() != null && !shippingOrder.getWebCustomerId()
	          .equals("")) ? shippingOrder.getWebCustomerId() : customerId);
	      shippingOrder.setCustomerId(customerId);
	    }
	    // long customerId = shippingOrder.getCustomerId();
	    String tocountryCode = shippingOrder.getToAddress().getCountryCode();
	    String fromcountryCode = shippingOrder.getFromAddress().getCountryCode();
	    if (!fromcountryCode.equals(tocountryCode)) {
	      Address customsBrokerAddress;
	      Customer customer = customerService.getCustomerInfoByCustomerId(customerId);
	      if(customer!=null){
	    	  String customerName = customer.getName();
	      }
	      if(shippingOrder.getCustomsInvoice().getBrokerAddress().getAbbreviationName()==null){
	      customsBrokerAddress = addressService.findDefaultCustomsBrokerAddress(customerId,
	          tocountryCode);
	      }else{
	    	  	    	  customsBrokerAddress=shippingOrder.getCustomsInvoice().getBrokerAddress();
	    	  	      }
	      if (customsBrokerAddress != null) {
	        customsInvoice.getBrokerAddress().copyAddress(customsBrokerAddress);
	      }
	    }

	    // customsInvoice.setBillToAddress(shippingOrder.getToAddress());
	    if(customs!=null && customs.equalsIgnoreCase("true")){
	    		    	customsInvoice.setBillToAddress(shippingOrder.getCustomsInvoice().getBillToAddress());
	    		    }else{
	    		    	customsInvoice.getBillToAddress().copyAddress(shippingOrder.getToAddress());
	    		    }
	    // customsInvoice.getBuyerAddress().copyAddress(shippingOrder.getToAddress());
	    customsInvoiceProduct.setCustomsInvoiceId(customsInvoice.getId());

	    // to maintain the values while click on tabs
	    if ("stageOne".equals(request.getParameter("method"))) {
	      getSession().put("shippingOrder", shippingOrder);
	      return "STAGE_ONE";
	    }
	    if ("packageInformation".equals(request.getParameter("method"))) {
	      getSession().put("shippingOrder", shippingOrder);
	      return "PACKAGE_INFO";
	    }

	    // reset the service if it has been set
	    shippingOrder.setService(null);

	    // set the User GL if set
	    if (!StringUtil.isEmpty(getLoginUser().getUserGLOrRefNumber())) {
	      shippingOrder.setReferenceTwo(getLoginUser().getUserGLOrRefNumber()); // reference2
	      shippingOrder.setReferenceTwoName(ShiplinxConstants.USER_GL); // reference2_name
	    }

	    try {
	      // clear the previous errors
	      clearActionErrors();
	      System.out.println("Starting Time Mohan ==== "+System.currentTimeMillis());
	      List<Rating> ratingList = carrierServiceManager.rateShipment(shippingOrder);
	      System.out.println("End Time Mohan ==== "+System.currentTimeMillis());
	      Boolean isTransitDaysZero = false;
	      for (Rating ratingListTRD : ratingList) {
	        if (ratingListTRD != null && ratingListTRD.getTransitDays() <= 0
	            && ratingListTRD.getCarrierId() == ShiplinxConstants.CARRIER_GENERIC) {
	          isTransitDaysZero = true;
	        }
	      }
	      if (isTransitDaysZero) {
	        String errorLog = new String(
	            "For accurate transit times please contact solutions at 416-603-0103 press 1 or email to saveltl@integratedcarriers.com");
	        this.addActionMessage(errorLog);
	      }
	      // Remove Requet Quote Link except IC services
	      List<Rating> removeRatings = new ArrayList<Rating>();
	      for (Rating ratings : ratingList) {
	        if (ratings.getCarrierId() != ((long) ShiplinxConstants.CARRIER_GENERIC)
	            && ratings.getCharges().size() == 0) {
	          removeRatings.add(ratings);
	        }
	      }
	      if (removeRatings.size() > 0) {
	        ratingList.removeAll(removeRatings);
	      }
	      //
	      // Get the error messages returned from the carriers
	      for (CarrierErrorMessage carrierErrorMessage : carrierServiceManager.getErrorMessages()) {
	        addActionError(carrierErrorMessage.getMessage());
	      }
	      // setting temp id for rate list
	      int counter = 1;
	      for (Rating r : ratingList) {
	        r.setId(counter++);
	      }
	      
	      CustomerDAO customermanagerDAO=(CustomerDAO) MmrBeanLocator.getInstance().findBean("customermanagerDAO");

	      			//CustomerDAO customermanagerDAO = (CustomerDAO) MmrBeanLocator.getInstance().findBean("customermanagerDAO");
	      		//	List<Rating> ratinglist1 = shippingOrder.getRateList();
	      			//if (shippingOrder.getId() != null) {
	      				String shipCustomerCurrency1 = customermanagerDAO.getCustomerCurrencyById(shippingOrder.getCustomerId());
	      				for (Rating r : ratingList) {
	      					String accCountry = r.getAccountCountry();
	      					int costConId = shippingService.getCountryIdByCountryCode(accCountry, "");
	      					int chargeConId = shippingService.getCountryIdByCountryCode("",shipCustomerCurrency1);
	      					for (Charge c : r.getCharges()) {
	      						if ((c.getCostcurrency() == 0)
	      								&& (c.getChargecurrency() == 0)) {
	      							c.setCostcurrency(costConId);
	      							c.setChargecurrency(chargeConId);
	      							shippingService.updateCharge(c);
	      						}
	      					}
	      				}
	      			//}
	      	      shippingOrder.setRateList(ratingList);

	      List<HashMap<String, String>> rateOption = new ArrayList<HashMap<String, String>>();

	      // to select the rate (radio option)
	      Iterator iterator = ratingList.iterator();
	      for (int i = 0; i < ratingList.size(); i++) {
	        HashMap rate = new HashMap();
	        rate.put("rateIndex" + i, "");
	        rateOption.add(rate);
	      }

	      shippingOrder.setCustomsInvoice(customsInvoice);

	      getSession().put("RateOption", rateOption);
	      getSession().put("shippingOrder", shippingOrder);

	      // setting autoprint value for the login user
	      User user = userService.findUserByUsername(UserUtil.getMmrUser().getUsername());
	      // setting the print config values for the logged user
	      // request.setAttribute("no_of_lbls", user.getPrintNoOfLabels());
	      // request.setAttribute("no_of_ci", user.getPrintNoOfCI());
	      request.setAttribute("autoprint", user.isAutoPrint());
	      
	      
     ////Markup Minimum Calculation
	      	      if(shippingOrder.getPackageTypeId().getName()
	    		  	    	        .equalsIgnoreCase(ShiplinxConstants.PACKAGE_PALLET_STRING.trim())){
	    		        if(ratingList != null && ratingList.size() > 0){
	    		  	    	  
	    		  	      for(Rating rating: ratingList){
	    		      	  double total=0.0;
	    		  	    	  double freightCharge=0.0;
	    		  	    	  boolean baseMarkupFlat=false;
	    		  	    	  boolean baseMarkupCharge=false;
	    		  	    	  long serviceId = rating.getServiceId();
	    		  	          Service service = carrierServiceManager.getService(serviceId);
	    		            if (service != null && service.getServiceType() ==ShiplinxConstants.SERVICE_TYPE_LTL_POUND) {
	    		  	        	  markupManagerService = (MarkupManager)MmrBeanLocator.getInstance().findBean("markupManagerService");
	    		  	        	  Markup searchMarkup = markupManagerService.getMarkupObj(shippingOrder);
	    		  	        	searchMarkup.setServiceId(serviceId);
	    		  	        	  if(searchMarkup!=null){
	    		  	        		  Markup baseMarkup=markupManagerService.findBaseMarkup(searchMarkup);
	    		  	        		  double baseAmount=0;
	    		  	        		    if(baseMarkup==null){
	    		          		            Markup mark=searchMarkup;
	    		  	        		            mark.setCustomerId(0l);
	    		  	        		            baseMarkup=markupManagerService.findBaseMarkup(mark);
	    		  	        		   }
	    		  	        		    if (baseMarkup!=null){
	    		  	        		    	if(baseMarkup.getMarkupPercentage() == 0
	    		  	        		    		                                  && baseMarkup.getMarkupFlat() != 0) {        		    		                        	  
	    		  	        		    		                        	  baseAmount=baseMarkup.getMarkupFlat()+rating.getTotalCost();
	    		          		    		                        	  baseMarkupFlat=true;	        		    		                        	  
	    		  	        		    		                              }else{	        		    		                            	  
	    		  	        		    		                            	  baseAmount = rating.getTotalCost()+(rating.getTotalCost()*baseMarkup.getMarkupPercentage());
	    		  	        		    		                            	  }
	    		  	        		    		                              }
	    		  	        		    if(baseMarkup!=null && rating.getTotal()<baseAmount){
	    		  	        		    	baseMarkupCharge=true;
	    		          		    	if(baseMarkupFlat){
	    		  	        		    		rating.setMarkupFlat(baseMarkup.getMarkupFlat());
	    		  	                        	  rating.setMarkupTypeText("Flat");
	    		  	        		    	for(Charge charge:rating.getCharges()){	        		    		
	    		  	        		    		 if(charge.getChargeCode()!=null&&charge.getChargeCode().equalsIgnoreCase("FRT")){
	    		  	        		    			 
	    		  	        		    			 if(rating.getFuel_perc()!=0){
	    		          		    				 freightCharge = charge.getCost()+((100-rating.getFuel_perc())*rating.getMarkupFlat()/100);
	    		  	        		    			 }else{
	    		  	        		    				 freightCharge = charge.getCost()+rating.getMarkupFlat();
	    		  	        		    			 }
	    		  	        		    			 if(freightCharge>0){
	    		  	        		    				 charge.setCharge(freightCharge);
	    		          		    			 }
	    		  	        		    		 }else if(charge.getChargeCode()!=null&&charge.getChargeCode().equalsIgnoreCase("FUE"))
	    		  	        		    		{
	    		  	        		    			if(rating.getFuel_perc()!=0 && freightCharge>0){
	    		  	        		    				double fuelCharge=freightCharge*rating.getFuel_perc()/100;
	    		  	        		    				charge.setCharge(fuelCharge);
	    		  	        		    			}	        		    			
	    		  	        		    		}
	    		  	        		    		 total+=charge.getCharge();
	    		  	        		    	}
	    		  	        		    	}else{
	    		  	        		    		rating.setMarkupPercentage(baseMarkup.getMarkupPercentage());
	    		                            	  rating.setMarkupTypeText(baseMarkup.getTypeText());
	    		  	        		    		for(Charge charge:rating.getCharges()){
	    		  	        		    			if(charge.getChargeCode()!=null&&(charge.getChargeCode().equalsIgnoreCase("FRT")||charge.getChargeCode().equalsIgnoreCase("FUE")))
	    		  		        		    		charge.setCharge(charge.getCost()+(charge.getCost()*rating.getMarkupPercentage()/100));
	    		  	        		    			total+=charge.getCharge();
	    		  		        		    	}
	    		  	        		    	}
	    		  	        		    }
	    		  	        	  }
	    		             }
	    		  	          if(baseMarkupCharge){
	    		  	          rating.setTotal(total);
	    		  	          }
	    		  	      }
	    		  	      }
	    		  	      }
	      ////End Markup Minimum Calculation
	      
	      
	      	    for (Rating r : ratingList){
    		    	  if((r.getCarrierId()==ShiplinxConstants.CARRIER_GENERIC)&&(r.getBillWeight()==0.0)&&(r.getTotalCost()==0.0)){
    		    		 //flag=true;
    		    		  createFutureReference(shippingOrder);
    		    	  }
		      }
	      	    
	      if (ratingList == null || ratingList.size() == 0) {
	        if (shippingOrder.getCarrierId_web() != null && shippingOrder.getCarrierId_web() > 0)
	          getSession().put("SERVICES",
	              carrierServiceManager.getServicesForCarrier(shippingOrder.getCarrierId_web()));
	        addActionError(getText("shippingOrder.rate.empty"));
	        createFutureReference(shippingOrder);
	        if (shippingOrder.getUnitmeasure() == 2) {
	          for (Package pack : packageList) {
	            float length = pack.getLength().floatValue() / 0.39370f;
	            float width = pack.getWidth().floatValue() / 0.39370f;
	            float height = pack.getHeight().floatValue() / 0.39370f;
	            /*
	             * BigDecimal length = new BigDecimal(lengthDouble); BigDecimal width = new
	             * BigDecimal(widthDouble); BigDecimal height = new BigDecimal(heightDouble);
	             */
	            pack.setWeight((pack.getWeight().divide(new BigDecimal(2.2))));
	            pack.setLength(new BigDecimal(Math.round(length + 0.4)).setScale(2,
	                BigDecimal.ROUND_HALF_UP));
	            pack.setWidth(new BigDecimal(Math.round(width + 0.4)).setScale(2,
	                BigDecimal.ROUND_HALF_UP));
	            pack.setHeight(new BigDecimal(Math.round(height + 0.4)).setScale(2,
	                BigDecimal.ROUND_HALF_UP));
	          }
	        }
	        return INPUT;
	      }

	      CurrencySymbol symbol=new CurrencySymbol();
	      CurrencySymbol symbol1=new CurrencySymbol();
	      List<CurrencySymbol> symbolList=new ArrayList<CurrencySymbol>();

	      int fromCurrencysymbol1=1,toCurrencysymbol1=1;
	      symbolList=shippingService.getallCurrencySymbol();
	      //// ==================== Exchanage Rate ===================
	      if(shippingOrder.getCustomer() != null && shippingOrder.getCustomer().getDefaultCurrency()!=null && !shippingOrder.getCustomer().getDefaultCurrency().isEmpty()){
	    	  	    	  symbol=shippingService.getSymbolByCurrencycode(shippingOrder.getCustomer().getDefaultCurrency());
	    	  	      }/*else{//EUCG
	    	  	    	  symbol=shippingService.getCurrencyCodeByCountryName(user.getLocale().substring(3, 5));
	    	  	      	  if(symbol==null){
	    	  	      		  for(int i=0;i<ShiplinxConstants.EURO_UNION_LIST.length;i++){
	    	  	      			  if(user.getLocale().substring(3, 5).equalsIgnoreCase(ShiplinxConstants.EURO_UNION_LIST[i])){
	    	  	      				symbol=shippingService.getCurrencyCodeByCountryName("EUCG");
	    	  	      				break;
	    	  	      			  }
	    	  	      		  }
	    	  	      	  }
	    	  	      */  
	 //// ==================== End ===================
	      Double exchRate;
	      for(Rating rate:ratingList){
	    	  if(symbol==null || ("").equals(symbol.getCurrencyCode()) || symbol.getCurrencyCode() ==null){
		    	  String accCountry = rate.getAccountCountry();
		    	  String accCurrency = shippingService.getCurrencyByCountry(accCountry);
		    	  symbol=shippingService.getSymbolByCurrencycode(accCurrency);
	    	  }
	    	  String toCurrency;
	    	  if(symbol!=null){
	    		  session.put("LocalCurrencySymbol", symbol.getCurrencySymbol());
	    		  toCurrency=symbol.getCurrencyCode();
	    		  rate.setLocalCurrencySymbol(symbol.getCurrencySymbol());
	    	  }else{
	    		  session.put("LocalCurrencySymbol", "$");
	    		  toCurrency = "CAD";
	    		  rate.setLocalCurrencySymbol("$");
	    	  }
	    	  for(Charge charge:rate.getCharges()){

	    		  //String fromCountry=rate.getCurrency();
	    		  String fromCurrency = rate.getCurrency();
	    		  /*if(fromCurrency==null){
	    			  fromCurrency=rate.getCurrency();
	    		  }*/
	    		  if(fromCurrency ==null){
	    			  fromCurrency=shippingOrder.getCurrency();
	    		  }
	    		  if(fromCurrency==null){
	    			  fromCurrency="CAD";
	    		  }
	    		  for(CurrencySymbol currencySymbol:symbolList){
	    			  if(currencySymbol.getCurrencyCode().equalsIgnoreCase(fromCurrency)){
	    				  fromCurrencysymbol1=currencySymbol.getId();
	    			  }
	    			  if(currencySymbol.getCurrencyCode().equalsIgnoreCase(toCurrency)){
	    				  toCurrencysymbol1=currencySymbol.getId();
	    			  }
	    		  }
	    		  
	    		  exchRate=shippingService.getExchangeRate(fromCurrency,toCurrency);
	    		  if(fromCurrency.equalsIgnoreCase(toCurrency))
	    			  exchRate=1d;
	    		  if(exchRate==null){
	    			  exchRate=1d;
	    			  rate.setLocalCurrencySymbol("$");
	    			  session.put("LocalCurrencySymbol", "$");
	    			  addActionError("There is no exchange rate in database for the currency");
	    			  addActionError("Currently showing in default currency");
	    		  }
	    		  if(exchRate!=null){
	    			  BigDecimal exchRate1=new BigDecimal(exchRate);
	    			  charge.setExchangerate(exchRate1);
	    			  if(charge.getCost()!=null){
	    				  charge.setCostInLocalCurrency(charge.getCost());
	    			  }
	    			  if(charge.getCharge()!=null){
	    				  double tempCharge = new BigDecimal(charge.getCharge()*exchRate).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	    				  log.debug("Before Currency Converting =======================>"+charge.getCharge());
	    				  charge.setChargeInLocalCurrency(charge.getCharge()*exchRate);
	    				  charge.setCharge(tempCharge);
	    				  log.debug("After Currency Converting =======================>"+charge.getCharge());
	    				  log.debug("Exchage Rate for Currency Convertion =======================>"+exchRate);
	    			  }
	    			  if(charge.getTariffRate()!=null){
	    				  charge.setTariffInLocalCurrency(charge.getTariffRate()*exchRate);
	    				  charge.setCustomerTarrifRate(charge.getTariffRate()*exchRate);
	    			  }
	    			  charge.setChargecurrency(toCurrencysymbol1);
	    			  charge.setCostcurrency(fromCurrencysymbol1);
	    			  if(charge.getCostInLocalCurrency()==null)
	    				  charge.setCostInLocalCurrency(0.00);
	    			  if(charge.getChargeInLocalCurrency()==null)
	    				  charge.setChargeInLocalCurrency(0.00);
	    			  if(charge.getTariffInLocalCurrency()==null)
	    				  charge.setTariffInLocalCurrency(0.00);
	    		  }
	    	  }
	    	  if(toCurrency!=null){
	    			 // shippingOrder.setCurrency(toCurrency);
	    			  rate.setCurrency(toCurrency);
	    		  }
	    	  if(rate.getCharges().get(0).getExchangerate()!=null){
	    		  rate.setTotalChargeLocalCurrency(rate.getTotal()*rate.getCharges().get(0).getExchangerate().doubleValue());
	    		  rate.setTotalCostLocalCurrency(rate.getTotalCost());
	    		  if(rate.getTotalChargeLocalCurrency()==null){
	    			  rate.setTotalChargeLocalCurrency(0.00);
	    		  }
	    		  if(rate.getTotalCostLocalCurrency()==null){
	    			  rate.setTotalCostLocalCurrency(0.00);
	    		  }
	    	  }
	      }
	    } catch (Exception e) {
	      e.printStackTrace();
	      for (CarrierErrorMessage carrierErrorMessage : carrierServiceManager.getErrorMessages()) {
	        addActionError(carrierErrorMessage.getMessage());
	      }
	      addActionError(getText("shippingOrder.rate.error"));
	    }

	    /*
	     * if (shippingOrder.getUnitmeasure() == 2) { for (Package pack : packageList) { double
	     * lengthDouble = pack.getLength().doubleValue() * 0.39370; double widthDouble =
	     * pack.getWidth().doubleValue() * 0.39370; double heightDouble = pack.getHeight().doubleValue()
	     * * 0.39370; BigDecimal length = new BigDecimal(lengthDouble); BigDecimal width = new
	     * BigDecimal(widthDouble); BigDecimal height = new BigDecimal(heightDouble);
	     * pack.setWeight(pack.getWeight() * 2.2f); pack.setLength(length.setScale(2,
	     * BigDecimal.ROUND_HALF_UP)); pack.setWidth(width.setScale(2, BigDecimal.ROUND_HALF_UP));
	     * pack.setHeight(height.setScale(2, BigDecimal.ROUND_HALF_UP)); } }
	     */
	    // if quick ship option is chosen, then redirect to ship
	    getSession().put("shippingOrder", shippingOrder);
	    if (((shippingOrder.getServiceId_web() != null && shippingOrder.getServiceId_web() > 0)
	    	|| shippingOrder.isCheapestMethod() || shippingOrder.isFastestMethod())&&(stagethree!=null && stagethree.equalsIgnoreCase("false")))
	      if (shippingOrder.getRateList().size() == 1) {
	        return "ship";
	      }

	    if (shippingOrder.getBillToType() != null
	        && (shippingOrder.getBillToType().equalsIgnoreCase(ShiplinxConstants.BILL_TO_THIRD_PARTY) || shippingOrder
	            .getBillToType().equalsIgnoreCase(ShiplinxConstants.BILL_TO_COLLECT)))
	      // getSession().put("BillToType", shippingOrder.getBillToType());
	      request.setAttribute("BillToType",
	          shippingOrder.getBillToType() + " - " + shippingOrder.getBillToAccountNum());
	    
	    	    this.addresslist();
	    	    if(emailNotification!=null && emailNotification.equalsIgnoreCase("true")){
	    	    				addressService.setSendNotification(shippingOrder.getFromAddress().getAddressId());
	    	    			}
	    	    		    if(emailNotification2!=null && emailNotification2.equalsIgnoreCase("true")){
	    	    				addressService.setSendNotification(shippingOrder.getToAddress().getAddressId());
	    	    			}
	    return SUCCESS;

	  }
	
	private void createFutureReference(ShippingOrder shippingOrder) {
				User user = UserUtil.getMmrUser();
			    userDAO = (UserDAO) MmrBeanLocator.getInstance().findBean("userDAO");
			    String[] sertype;
			    String shipdate;
			    frp=new FutureReferencePackages();
				fc=new FutureReference();
				fc.setCustomerId(shippingOrder.getCustomerId());        		 
				fc.setCreatedBy(user.getUsername());
				fc.setDateCreated((Timestamp)shippingOrder.getDateCreated());
				sertype=(shippingOrder.getPackageTypeId().getType()).split("_");
				shipdate=shippingOrder.getScheduledShipDate_web();
				String readyTime,closeTime;
				readyTime=shippingOrder.getPickup().getReadyHour()+":"+shippingOrder.getPickup().getReadyMin();
				closeTime=shippingOrder.getPickup().getCloseHour()+":"+shippingOrder.getPickup().getCloseMin();
			    String pickupInstruction;
			    pickupInstruction=shippingOrder.getPickup().getInstructions();
			    fc.setDocumentsOnly(shippingOrder.getDocsOnly());
			    fc.setAppointmentPickup(shippingOrder.isAppointmentPickup());
			    fc.setTradeShowPickup(shippingOrder.isTradeShowPickup());
			    fc.setTaligateDelivery(shippingOrder.isToTailgate());
			    fc.setTradeShowDelivery(shippingOrder.isTradeShowDelivery());
			    fc.setAppointmentDelivery(shippingOrder.isAppointmentDelivery());
			    fc.setInsidePickup(shippingOrder.isInsidePickup());
			    fc.setTaligatePickup(shippingOrder.isFromTailgate());
			    fc.setDangerousGoods(shippingOrder.getDangerousGoodsName());
			    fc.setRefCode(shippingOrder.getReferenceCode());
			    fc.setHoldForPickup(shippingOrder.getHoldForPickupRequired());
			    fc.setSaturdayDelivery(shippingOrder.getSatDelivery());
			    int sig_req;
			    sig_req=shippingOrder.getSignatureRequired();
			    if(sig_req==1)
			    {
			    fc.setSignatureRequired("NO");
			    }
			    else if(sig_req==2)
			    {
			    	fc.setSignatureRequired("Del.Confirmation");
			    }
			    else if(sig_req==3)
			    {
			    	fc.setSignatureRequired("Signature");
			    }
			    else if(sig_req==4)
			    {
			    	fc.setSignatureRequired("Adult Signature");
			    }
			    fc.setDutiableCode((shippingOrder.getDutiableAmount()).toString());
			    fc.setNotifyShipper(shippingOrder.getFromAddress().isSendNotification());
			    fc.setNotifyConsignee(shippingOrder.getToAddress().isSendNotification());
			        
			    	
				if(shippingOrder.getPickup().isPickupRequired()==true)
				{
				fc.setReadyTime(readyTime);
				fc.setCloseTime(closeTime);
				fc.setPickupInstruction(shippingOrder.getPickup().getInstructions());
				fc.setPickupLocation(shippingOrder.getPickup().getPickupLocation());
				}
				else
				{
					fc.setReadyTime(null);
					fc.setCloseTime(null);
					fc.setPickupInstruction(null);
					fc.setPickupLocation(null);
				}
				fc.setFromContactName(shippingOrder.getFromAddress().getContactName());
				fc.setToContactName(shippingOrder.getToAddress().getContactName());
				fc.setFromPostalCode(shippingOrder.getFromAddress().getPostalCode());
				fc.setToPostalCode(shippingOrder.getToAddress().getPostalCode());
						
				
				fc.setName(shippingOrder.getCustomer().getName());
				fc.setFromCity(shippingOrder.getFromAddress().getCity());
				fc.setFromState(shippingOrder.getFromAddress().getProvinceCode());
				fc.setFromCountry(shippingOrder.getFromAddress().getCountryName());
				fc.setToCity(shippingOrder.getToAddress().getCity());
				fc.setToState(shippingOrder.getToAddress().getProvinceCode());
				fc.setToCountry(shippingOrder.getToAddress().getCountryName());
				fc.setShipFromAddress(shippingOrder.getFromAddress().getAddress1());
				fc.setShipToAddress(shippingOrder.getToAddress().getAddress1());
				fc.setQuantity(shippingOrder.getQuantity());
				fc.setServiceType(sertype[1]);
				fc.setShipScheduleDate(shippingOrder.getScheduledShipDate_web());
				fc.setShipFromEmail(shippingOrder.getFromAddress().getEmailAddress());
				fc.setShipToEmail(shippingOrder.getToAddress().getEmailAddress());
				fc.setShipFromPhone(shippingOrder.getFromAddress().getPhoneNo());
				fc.setShipToPhone(shippingOrder.getToAddress().getPhoneNo());
				fc.setFromCompany(shippingOrder.getFromAddress().getAbbreviationName());
				fc.setToCompany(shippingOrder.getToAddress().getAbbreviationName());
				/*if((shippingOrder.getFromAddress().getResidential())==true)
				fc.setFromResidential(1);
				else
					fc.setFromResidential(0);
				
				if((shippingOrder.getToAddress().getResidential())==true)
				fc.setToresidential(1);
				else
					fc.setToresidential(0);*/
				//fc.setPackageList(shippingOrder.getPackages());
				fc.setFromResidential(shippingOrder.getFromAddress().getResidential());
				fc.setToresidential(shippingOrder.getToAddress().getResidential());
				fc.setBusinessId(shippingOrder.getBusinessId());
		        long frId=shippingService.insertFutureReference(fc);
		        int len=shippingOrder.getPackages().size();
		        int i;
		        for(i=0;i<len;i++)
		        {
				        frp.setFutureReferenceId(frId);
				        frp.setCustomerId(shippingOrder.getCustomerId());
				        frp.setPackLength((shippingOrder.getPackages().get(i).getLength().doubleValue()));
				        frp.setPackWidth((shippingOrder.getPackages().get(i).getWidth().doubleValue()));
				        frp.setPackHeight((shippingOrder.getPackages().get(i).getHeight()).doubleValue());
				        frp.setPackWeight((shippingOrder.getPackages().get(i).getWeight()).doubleValue());
				        frp.setPackCodAmount((shippingOrder.getPackages().get(i).getCodAmount()).doubleValue());
				        frp.setPackInsuranceAmount((shippingOrder.getPackages().get(i).getInsuranceAmount()).doubleValue());
				        frp.setPackDescription(shippingOrder.getPackages().get(i).getDescription());
				        
				        shippingService.insertFuturePackages(frp);
		        }
		       
			}

	
	public ShippingOrder applyCOD(ShippingOrder order){
		Double codTotal=0.0;
		Boolean codAmtAvl=false;
		for(Package pack:order.getPackages()){
			if(pack.getCodAmount()!=null && pack.getCodAmount().doubleValue()>0.0){
				codTotal=codTotal+pack.getCodAmount().doubleValue();
				codAmtAvl=true;
			}
		}
		order.setCODValue(codTotal);
		if(codAmtAvl){
			order.setCODPayment("Check");
			order.setCODValue(codTotal);
		}
		return order;
	}

	public String getCurrencyCode(int i){
		if(i==1){
			return "CAD";
		}
		if(i==2){
			return "USD";
		}
		return null;
	}
	public String performRating(){
		
		return SUCCESS;
		
	}
	
	public String additionalServices(){
		String packageType= request.getParameter("type");
		//get the id from package type table
		if(packageType.equalsIgnoreCase(ShiplinxConstants.PACKAGE_TYPE_PAK_STRING)){
			return "SUCCESS_PAK";
		}else if(packageType.equalsIgnoreCase(ShiplinxConstants.PACKAGE_TYPE_PALLET_STRING)){
			return "SUCCESS_PALLATE";
		}else if(packageType.equalsIgnoreCase(ShiplinxConstants.PACKAGE_TYPE_PACKAGE_STRING)){
			return "SUCCESS_PACKAGE";
		}else
			return SUCCESS; //default is type_env
			
			

	}
	
	public String quickShip(){
				try{
				ShippingOrder shippingOrder = getShippingOrder();
				shippingOrder.setCustomerId(shippingOrder.getWebCustomerId());
				shippingOrder.setCustomer(customerService.getCustomerInfoByCustomerId(shippingOrder.getCustomerId()));
				shippingOrder.setCarrierId(shippingOrder.getCarrierId_web());
				shippingOrder.setServiceId(shippingOrder.getServiceId_web());
				shippingOrder.setStatusId((long)ShiplinxConstants.STATUS_DISPATCHED);
				shippingOrder.setScheduledShipDate(DateUtil.convert(DateUtil.convertStringToDate(shippingOrder.getScheduledShipDate_web(), "yyyy-MM-dd")));
				shippingOrder.setService(carrierServiceManager.getService(shippingOrder.getServiceId()));
				if(shippingOrder.getFromAddress().getCountryCode().equals("US")||(shippingOrder.getToAddress().getCountryCode().equals("US"))){
					shippingOrder.setCurrency("USD");
				}else{
					shippingOrder.setCurrency("CAD");
				}
				shippingOrder.setBillingStatus(ShiplinxConstants.BILLING_STATUS_NOT_INVOICED);
				shippingOrder.setStatusName("READY FOR SHIPPING");
				//save the order in shipping_order table
				shippingService.save(shippingOrder);
				if(shippingOrder.getCustomer().getPaymentType()==ShiplinxConstants.PAYMENT_TYPE_CREDIT_CARD && shippingOrder.getCustomer().getCreditCardActive()==null){ //This is a credit card customer and we do not have profile stored on file (i.e with processor)
					if(shippingOrder.getCreditCard()==null)
						shippingOrder.setCreditCard(new CreditCard());
					if(shippingOrder.getCreditCard().getCcNumber()==null || shippingOrder.getCreditCard().getCcNumber().length()==0){ //credit card information not entered
						shippingOrder.setPaymentRequired(true);
						return viewShipment();
					}
				}
				}
				catch(Exception e){
					e.printStackTrace();
				}
				//return "success2";
				return viewShipment();
			}
	
	public String save(){
		log.debug("-------------Save Shipment -------------");
		ShippingOrder shippingOrder = getShippingOrder();
		String pickupRequired=request.getParameter("pickupRequired");
		  shippingOrder.getPickup().setPickupRequired(Boolean.parseBoolean(pickupRequired));
		  String brokerName = request.getParameter("broker");
		  if(brokerName!=null){
		  shippingOrder.getCustomsInvoice().getBrokerAddress().setAbbreviationName(brokerName);
		  }
		  if (shippingOrder.isSaveFromAddress()){
		      shippingOrder.getFromAddress().setCustomerId(shippingOrder.getCustomerId());
		  }
		  if (shippingOrder.isSaveToAddress()){
		      shippingOrder.getToAddress().setCustomerId(shippingOrder.getCustomerId());
		  }
		// shippingOrder.setBillToAccountNum(null);
		//shippingOrder.getCustomsInvoice().setBillTo("1");
				String currency=request.getParameter("cur");
						String shipper=request.getParameter("billTo");
						if(shippingOrder.isCustomsInvoiceRequired()){
							shippingOrder.getCustomsInvoice().setBillTo(shipper.toString());
							shippingOrder.getCustomsInvoice().setCurrency(currency.toString());
						}
		boolean saveCI = true;
		  User user = UserUtil.getMmrUser();
    userDAO = (UserDAO) MmrBeanLocator.getInstance().findBean("userDAO");
    uom = userDAO.unitOfMeasure();
    User unitofmeasure = userDAO.findunitofmeasure(user.getUsername());
    if (user != null && unitofmeasure.getUnitmeasure() == 2) {
      for (int i = 0; i < uom.size(); i++) {
        if (unitofmeasure != null
            && unitofmeasure.getUnitmeasure() == uom.get(i).getUnitOfMeasureId()) {
          Collections.swap(uom, 0, i);
        }
      }
    }

		//if in "QUOTE" mode, then need to capture detailed shipping information
		String mode = (String)getSession().get("SHIP_MODE");
		if(mode!=null && mode.equalsIgnoreCase("QUOTE")){
			getSession().put("SHIP_MODE", "SHIP");
			this.addActionMessage(getText("shippingOrder.ship.info"));
			
			 List<Package> packageList = shippingOrder.getPackages();
			    if (shippingOrder.getUnitmeasure() == 2) {
			    for (Package pack : packageList) {
	           float length = pack.getLength().floatValue() / 0.39370f;
	           float width = pack.getWidth().floatValue() / 0.39370f;
	           float height = pack.getHeight().floatValue() / 0.39370f;
	          /*
	           * BigDecimal length = new BigDecimal(lengthDouble); BigDecimal width = new
	           * BigDecimal(widthDouble); BigDecimal height = new BigDecimal(heightDouble);
	           */
	           float weight= pack.getWeight().floatValue()/2.2f;
	           pack.setWeight(new BigDecimal(weight).setScale(2,
	               BigDecimal.ROUND_HALF_UP));
	          pack.setLength(new BigDecimal(Math.round(length + 0.4)).setScale(2,
	              BigDecimal.ROUND_HALF_UP));
	          pack.setWidth(new BigDecimal(Math.round(width + 0.4)).setScale(2,
	              BigDecimal.ROUND_HALF_UP));
	          pack.setHeight(new BigDecimal(Math.round(height + 0.4)).setScale(2,
	              BigDecimal.ROUND_HALF_UP));
	        }
	      }
			
			return ERROR;
		}
						
		boolean CIreqd = shippingOrder.isCustomsInvoiceRequired();
		if(CIreqd)
		{
			if(shippingOrder.getCustomsInvoice().getBillTo()!=null && shippingOrder.getCustomsInvoice().getBillTo().equals("1"))
				shippingOrder.getCustomsInvoice().setBillTo("Shipper");
			else if(shippingOrder.getCustomsInvoice().getBillTo()!=null && shippingOrder.getCustomsInvoice().getBillTo().equals("2"))
				shippingOrder.getCustomsInvoice().setBillTo("Consignee");
			else if(shippingOrder.getCustomsInvoice().getBillTo()!=null && shippingOrder.getCustomsInvoice().getBillTo().equals("3"))
				shippingOrder.getCustomsInvoice().setBillTo("Third Party");
		}
				
		try{	
		
			String rateIndex = shippingOrder.getRateIndex();
			
			//set the default rate index if not selected, as first rate.
			if(rateIndex == null || "".equals(rateIndex))
				rateIndex = "0";
			
	//		shippingOrder = (ShippingOrder) getSession().get("shippingOrder");
			
			//get the rating/surcharges/charges for selected index from the list
			Rating r = shippingOrder.getRateList().get(Integer.parseInt(rateIndex));
			
			
			//Charge shippingCharge = r.getCharge();
			/*if(shippingOrder.getFromAddress().getCountryCode().equals("US")&&(shippingOrder.getToAddress().getCountryCode().equals("US"))){
				r.setCurrency("USD");
			}*/
			 carrierServiceDAO = (CarrierServiceDAO) MmrBeanLocator.getInstance().findBean(
				       "carrierServiceDAO");
				     Carrier chargeCarrier = carrierServiceDAO.getCarrier(r.getCarrierId());
				     Double quoteTotalCost = 0.0;
				      Double quoteTotalCharge = 0.0;
				      Double actualTotalCost = 0.0;
				      Double actualTotalCharge = 0.0;
				      if (r != null) {
				    	  if(r.getCharges()!=null){
				    		  if(r.getCarrierId() == 10){
				    			  Iterator<Charge> iteratorCharge = r.getCharges().iterator();
				    			  while(iteratorCharge.hasNext()){
				    				  Charge charge = iteratorCharge.next();
				    				  if(charge.getChargeCode()!=null && charge.getChargeCode().equals(ShiplinxConstants.GROUP_FUEL_CHARGE) && charge.getCharge() == 0 ){
				    					  iteratorCharge.remove();
				    				  }
				    			  }
				    		  }
				    	  }
				        for (int i = 0; i < r.getCharges().size(); i++) {
				        	if(r.getSlaveCarrierName()!=null){
				        					        		r.getCharges().get(i).setCarrierId(r.getSlaveCarrierId());
				        	    				        		r.getCharges().get(i).setCarrierName(r.getSlaveCarrierName());
				        						           	}  	else  	{
				        						        		r.getCharges().get(i).setCarrierId(chargeCarrier.getId());
				        						        		r.getCharges().get(i).setCarrierName(chargeCarrier.getName());
				        						           	}
				        						        		if(r.getCharges().get(i).getName().equals(ShiplinxConstants.TAX_GST)){
				        						        	  	r.getCharges().get(i).setChargeCode(ShiplinxConstants.TAX_TAX);
				        			     			        }
				          if (r.getCharges().get(i).getType() == 0) {
				            quoteTotalCost += r.getCharges().get(i).getCost();
				            quoteTotalCharge += r.getCharges().get(i).getCharge();
				          } else if (r.getCharges().get(i).getType() == 1) {
				            actualTotalCost += r.getCharges().get(i).getCost();
				            actualTotalCharge += r.getCharges().get(i).getCharge();
				          }
				          if(r.getCharges().get(i).getStatus()==null){
				        	  r.getCharges().get(i).setStatus(0);
				          }
				        }

				        //shippingOrder.setQuoteTotalCost(quoteTotalCost);
				        shippingOrder.setQuoteTotalCharge(quoteTotalCharge);
				       // shippingOrder.setActualTotalCost(actualTotalCost);
				        shippingOrder.setActualTotalCharge(actualTotalCharge);

				     }
				     
			if(getLoginUser().getCustomerId()>0)
				shippingOrder.setCustomerId(getLoginUser().getCustomerId());
			shippingOrder.setCarrierId(r.getCarrierId());
			shippingOrder.setServiceId(r.getServiceId());
			shippingOrder.setSlaveServiceId(r.getSlaveServiceId());
			shippingOrder.setRateIndex(rateIndex);
			shippingOrder.setAccountNum(r.getAccountNum());
			shippingOrder.setService(this.shippingService.getServiceById(shippingOrder.getServiceId()));
			shippingOrder.setCharges(r.getCharges());
			log.debug("---r.getFuelSurcharge()---"+r.getFuelSurcharge());
			
			//shippingOrder.setFuelCharges(r.getFuelSurcharge());
			shippingOrder.setCarrierName(r.getCarrierName());
			//shippingOrder.setTotalCharge(r.getTotal());
			
			List<Rating> rateList = new ArrayList<Rating>();
			rateList.add(r);
			shippingOrder.setRateList(rateList);
			
			clearActionErrors();
	
			if(shippingOrder.getCustomer().getPaymentType()==ShiplinxConstants.PAYMENT_TYPE_CREDIT_CARD && shippingOrder.getCustomer().getCreditCardActive()==null){ //This is a credit card customer and we do not have profile stored on file (i.e with processor)
				if(shippingOrder.getCreditCard()==null)
					shippingOrder.setCreditCard(new CreditCard());
				if(shippingOrder.getCreditCard().getCcNumber()==null || shippingOrder.getCreditCard().getCcNumber().length()==0){ //credit card information not entered
					shippingOrder.setPaymentRequired(true);
					return viewShipment();
				}
			}
			 List<Package> packageList = shippingOrder.getPackages();
			 if (shippingOrder.getUnitmeasure() == 2) {
				for (Package pack : packageList) {
				if (pack.getWidth().intValue() == 0) {
					BigDecimal decWidth = new BigDecimal("0.79");
            decWidth.setScale(2, BigDecimal.ROUND_HALF_UP);
            if (pack.getWidth().equals(decWidth)) {
              pack.setWidthTwo(true);
            } else {
              pack.setWidthTwo(false);
            }

            pack.setWidth(new BigDecimal(1.0));
	          }
	          if (pack.getHeight().intValue() == 0) {
	        	  BigDecimal decLength = new BigDecimal("0.79");
            decLength.setScale(2, BigDecimal.ROUND_HALF_UP);
            if (pack.getLength().equals(decLength)) {
              pack.setLengthTwo(true);
            } else {
              pack.setLengthTwo(false);
            }

	        	  BigDecimal decHeight = new BigDecimal("0.79");
            decHeight.setScale(2, BigDecimal.ROUND_HALF_UP);
            if (pack.getHeight().equals(decHeight)) {
              pack.setHeightTwo(true);
            } else {
              pack.setHeightTwo(false);
            }

	           pack.setHeight(new BigDecimal(1.0));
	          }
	          if (pack.getLength().intValue() == 0) {
	            pack.setLength(new BigDecimal(1.0));
	          }
	        }
	      }

			String orderId=(String)getSession().get("EDIT_ORDER_ID");
			if(orderId!=null){
				ShippingOrder shippingOrderDelete=shippingService.getShippingOrder(Long.parseLong(orderId));
				shippingOrderDelete.setId(Long.parseLong(orderId));
				boolean isAdmin = UserUtil.getMmrUser().getUserRole().equals("busadmin");
				if(carrierServiceManager.cancelOrder(Long.parseLong(orderId), isAdmin)){
//					shippingService.updateShippingOrder(Long.parseLong(orderId));
					//save shipping order
					CarrierService CarrierService = carrierServiceManager.shipOrder(shippingOrder,  r);

				}else{
					throw new Exception(getText("shippingOrder.save.error"));
				}
			}else{
				if(shippingOrder.getBillToType().equalsIgnoreCase(ShiplinxConstants.BILL_TO_THIRD_PARTY) || shippingOrder.getBillToType().equalsIgnoreCase(ShiplinxConstants.BILL_TO_COLLECT))
					shippingOrder.getCharges().removeAll(shippingOrder.getCharges());
				carrierServiceManager.shipOrder(shippingOrder,  r);
				if(shippingOrder.getToAddress().isSendNotification() || shippingOrder.getFromAddress().isSendNotification()){
					if(shippingService.sendShipmentNotificationMail(shippingOrder,UserUtil.getMmrUser().getBusiness()))
						addActionMessage(MessageUtil.getMessage("shipment.notification.mail.success"));
					else
						addActionError(MessageUtil.getMessage("shipment.notification.mail.failure"));
				}
				addressService.setSendNotification(shippingOrder.getFromAddress().getAddressId());
				addressService.setSendNotification(shippingOrder.getToAddress().getAddressId());
			}
		}
		catch (CardProcessingException cpe) { //this is if card could not be authorized
			addActionError(getText("creditCard.payment.notProcessed") + " " + cpe.getMessage());
			//go back to pay/details page
		    List<Package> packageList = shippingOrder.getPackages();
		    if (shippingOrder.getUnitmeasure() == 2) {
		    for (Package pack : packageList) {
           float length = pack.getLength().floatValue() / 0.39370f;
           float width = pack.getWidth().floatValue() / 0.39370f;
           float height = pack.getHeight().floatValue() / 0.39370f;
          /*
           * BigDecimal length = new BigDecimal(lengthDouble); BigDecimal width = new
           * BigDecimal(widthDouble); BigDecimal height = new BigDecimal(heightDouble);
           */
           float weight= pack.getWeight().floatValue()/2.2f;
           pack.setWeight(new BigDecimal(weight).setScale(2,
               BigDecimal.ROUND_HALF_UP));
          pack.setLength(new BigDecimal(Math.round(length + 0.4)).setScale(2,
              BigDecimal.ROUND_HALF_UP));
          pack.setWidth(new BigDecimal(Math.round(width + 0.4)).setScale(2,
              BigDecimal.ROUND_HALF_UP));
          pack.setHeight(new BigDecimal(Math.round(height + 0.4)).setScale(2,
              BigDecimal.ROUND_HALF_UP));
        }
      }

			return ERROR;
		}
		catch (Exception e) {
			log.debug("Shipping Error!", e);
			addActionError(getText("shippingOrder.save.error"));
			//Get the error messages returned from the carriers
			for(CarrierErrorMessage carrierErrorMessage  :carrierServiceManager.getErrorMessages()){
				addActionError(carrierErrorMessage.getMessage());
			}
				if (shippingOrder != null && shippingOrder.getId() != null) {
        getSession().put("EDIT_ORDER_ID", shippingOrder.getId().toString());
        this.getShippingService().deleteChargesByOrderId(shippingOrder.getId());
        this.getShippingService().deleteLabel(shippingOrder.getId());
      }
				if (shippingOrder != null) {
        shippingOrder.setBillToAccountNum(null);
        getSession().put("shippingOrder", shippingOrder);
      }

      List<Package> packageList = shippingOrder.getPackages();
      if (shippingOrder.getUnitmeasure() == 2) {
        for (Package pack : packageList) {
          float length = pack.getLength().floatValue() / 0.39370f;
          float width = pack.getWidth().floatValue() / 0.39370f;
          float height = pack.getHeight().floatValue() / 0.39370f;
          /*
           * BigDecimal length = new BigDecimal(lengthDouble); BigDecimal width = new
           * BigDecimal(widthDouble); BigDecimal height = new BigDecimal(heightDouble);
           */
          float weight= pack.getWeight().floatValue()/2.2f;
          pack.setWeight(new BigDecimal(weight).setScale(2,
              BigDecimal.ROUND_HALF_UP));
          if (pack.getLength().floatValue() != 1) {
              pack.setLength(new BigDecimal(Math.round(length + 0.4)).setScale(2,
                  BigDecimal.ROUND_HALF_UP));
            }
            if (pack.getWidth().floatValue() != 1) {
              pack.setWidth(new BigDecimal(Math.round(width + 0.4)).setScale(2,
                  BigDecimal.ROUND_HALF_UP));
            }
            if (pack.getHeight().floatValue() != 1) {
              pack.setHeight(new BigDecimal(Math.round(height + 0.4)).setScale(2,
                  BigDecimal.ROUND_HALF_UP));
            }
            if (pack.getWidth().intValue() == 1) {
              if (pack.isWidthTwo()) {
                pack.setWidth(new BigDecimal(2.0));
              } else {
                pack.setWidth(new BigDecimal(1.0));
              }

            }
            if (pack.getHeight().intValue() == 1) {
              if (pack.isHeightTwo()) {
                pack.setHeight(new BigDecimal(2.0));
              } else {
                pack.setHeight(new BigDecimal(1.0));
              }

            }
            if (pack.getLength().intValue() == 1) {
              if (pack.isLengthTwo()) {
                pack.setLength(new BigDecimal(2.0));
              } else {
                pack.setLength(new BigDecimal(1.0));
              }

            }
          }
        }
			return ERROR;
		}
		
		//if order created successfully
		if(carrierServiceManager.getErrorMessages().size() == 0)
			addActionMessage(getText("shippingOrder.save.successfully"));

		return viewShipment();
	}
	
	
	public Pickup getPickup() {
		Pickup pickup = (Pickup)getSession().get("pickup");
		if (pickup == null) {
			pickup = new Pickup();
			pickup.setBusinessId(UserUtil.getMmrUser().getBusinessId());
			pickup.setCustomerId(UserUtil.getMmrUser().getCustomerId());
			setPickup(pickup);
		}
		return pickup;
	}
	public void setPickup(Pickup pickup) {

		getSession().put("pickup", pickup);
	}
	
	public ShippingOrder getShippingOrder() {
		ShippingOrder order = (ShippingOrder)getSession().get("shippingOrder");
		if (order == null) {
			order = new ShippingOrder();
			order.setBusinessId(UserUtil.getMmrUser().getBusinessId());
			
			setShippingOrder(order);
		}
		return order;
	}
	public void setShippingOrder(ShippingOrder shippingOrder) {

		getSession().put("shippingOrder", shippingOrder);
	}
	

	public List<OrderStatus> getOrderStatusList() {
		return (List<OrderStatus>) getSession().get("orderStatusList");

	}
	public void setOrderStatusList(List<OrderStatus> orderStatusList) {
		if (orderStatusList != null) {
			orderStatusList.add(0, new OrderStatus(-1, ""));
		}
		getSession().put("orderStatusList", orderStatusList);
	}
	
	public List<OrderStatus> getOrderStatusOptionsList() {
		return (List<OrderStatus>) getSession().get("orderStatusOptionsList");

	}
	public void setOrderStatusOptionsList(List<OrderStatus> orderStatusOptionsList) {
		if (orderStatusOptionsList != null) {
			orderStatusOptionsList.add(0, new OrderStatus(-1, ""));
		}
		getSession().put("orderStatusOptionsList", orderStatusOptionsList);
	}
	
	public Charge getNewQuotedCharge() {
		Charge newCharge = (Charge) getSession().get("newQuotedCharge");
		if (newCharge == null) {
			newCharge = new Charge();
			setNewQuotedCharge(newCharge);
		}
		return newCharge;
	}

	public void setNewQuotedCharge(Charge newCharge) {
		getSession().put("newQuotedCharge", newCharge);
	}
	
	public Charge getNewActualCharge() {
		Charge newCharge = (Charge) getSession().get("newActualCharge");
		if (newCharge == null) {
			newCharge = new Charge();
			setNewActualCharge(newCharge);
		}
		return newCharge;
	}

	public void setNewActualCharge(Charge newCharge) {
		getSession().put("newActualCharge", newCharge);
	}	
	
	public ShippingOrder getSelectedOrder() {
		ShippingOrder order = (ShippingOrder)getSession().get("selectedOrder");
		return order;
	}
	public void setSelectedOrder(ShippingOrder selectedOrder) {
		getSession().put("selectedOrder", selectedOrder);
	}	

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public AddressManager getAddressService() {
		return addressService;
	}

	public void setAddressService(AddressManager addressService) {
		this.addressService = addressService;
	}

	public ShippingService getShippingService() {
		return shippingService;
	}

	public void setShippingService(ShippingService shippingService) {
		this.shippingService = shippingService;
	}
	
	public List<Country> getCountries() {
		return countries;
	}
	public void setCountries(List<Country> countries) {
		this.countries = countries;
	}

	public List getPackageTypeList() {
		return packageTypeList;
	}
	public void setPackageTypeList(List packageType) {
		this.packageTypeList = packageType;
	}
	public List<Province> getProvinces() {
		return provinces;
	}
	public void setProvinces(List<Province> provinces) {
		this.provinces = provinces;
	}

	public CarrierServiceManager getCarrierServiceManager() {
		return carrierServiceManager;
	}

	public void setCarrierServiceManager(CarrierServiceManager carrierServiceManager) {
		this.carrierServiceManager = carrierServiceManager;
	}

	
	public String trackShipment(){
			String strReturn = null;
		long orderId = 0;
		if(request.getParameter("id") != null)
			orderId = new Long(request.getParameter("id"));
		String trackingURL = this.shippingService.getTrackingURL(orderId);
		if(trackingURL!=null && trackingURL.length()>0){
			nextAction = new String(trackingURL);
			strReturn = "success";
		}
		else
		{
			nextAction = request.getContextPath()+"/admin/view.shipment.action?notrackurl='true'&viewShipmentId="+orderId;
			strReturn = "success";
		}
		return strReturn;
	}
	
	//TODO : Incomplete , need to complete to display for order detail from tracking page
	public String trackShipmentDetail(){
		Long orderId = 0L;
		
		if(request.getParameter("id") != null)
			orderId = new Long(request.getParameter("id"));
		
		log.debug("-----trackShipmentDetail----"+orderId);
		ShippingOrder shippingOrder = shippingService.getShippingOrder(orderId);
		
		Service s = shippingService.getServiceById(shippingOrder.getServiceId());
		
		PackageType packageType  = shippingService.findOrderPackageType(orderId);
		
		List<Package> packagesList = shippingService.getShippingPackages(orderId);
		
		shippingOrder.setServiceName(s.getName());
		shippingOrder.setPackageTypeId(packageType);
		shippingOrder.setQuantity(packagesList.size());
		shippingOrder.setPackages(packagesList);
		shippingOrder.setCarrierName(shippingOrder.getCarrierId()==ShiplinxConstants.CARRIER_FEDEX ?
					ShiplinxConstants.CARRIER_FEDEX_STRING : ((shippingOrder.getCarrierId()==ShiplinxConstants.CARRIER_UPS ? ShiplinxConstants.CARRIER_UPS_STRING :"")));
		shippingOrder.setScheduledShipDate(shippingOrder.getScheduledShipDate());
		shippingOrder.setCharges(shippingService.getShippingOrderCharges(orderId));
		
		List<Charge> chargesList = shippingService.getShippingOrderCharges(orderId);
		List<Rating> ratingList = new ArrayList<Rating>();
		
		List<Charge> chargesList2 = new ArrayList<Charge>(); 
		
		Rating rating = new Rating();
		for(Charge c :chargesList){
			Charge charge = new Charge();
			double amount = c.getCost();
			String chargeName = c.getName();
			
			log.debug("-----c.getName()::"+c.getName()+"-----c.getAmount()::"+c.getCost());
			
			if(ShiplinxConstants.Charge_Total.equalsIgnoreCase(chargeName))
				rating.setTotalCost(amount);
			else if(ShiplinxConstants.Charge_BaseCharge.equalsIgnoreCase(chargeName))
				rating.setBaseCharge(amount);
			else{
				charge.setCost(amount);
				charge.setName(chargeName);
				chargesList2.add(charge);
			}
		}
		
		rating.setCharges(chargesList2);
		ratingList.add(rating);
		shippingOrder.setRateList(ratingList);
		setShippingOrder(shippingOrder);
				
		return SUCCESS;
	}
	
	public String listCarrierServices(){
		String carrierId;
		carrierId = request.getParameter("value");
		List<Service> services=null;
		if(carrierId!=null && carrierId.length()>0){
			services=new ArrayList();
			Service ser=new Service();
			if(request.getParameter("quickship")!=null)
			{
				if(Long.parseLong(carrierId)>0)	//check if carrier selected is 'ANY', then dont include 'ANY' option in the service dropdown.
				{
					ser.setName("ANY");
					ser.setId(-1L);
				}
				else 
				{
					ser.setName("");
					ser.setId(0L);
				}
			}
			else
			{
				ser.setName("------Select------");
				ser.setId(0l);
			}
			services.add(ser);
			
			if(!carrierId.equals("0")){
				services.addAll(carrierServiceManager.getServicesForCarrier(Long.parseLong(carrierId)));
			}
			getSession().put("services", services);
			
		}
		
		if(request.getParameter("quickship")!=null)
			return "success2";
		
		return SUCCESS;
		
	}
	
	public String listSearchedShipment(){		
		ShippingOrder shippingOrder=this.getShippingOrder();
		
		if(shippingOrder.getCarrierId()==0){
			shippingOrder.setCarrierId(null);
		}
		if(shippingOrder.getServiceId()==0){
			shippingOrder.setServiceId(null);
		}
		if(shippingOrder.getStatusId()==0){
			shippingOrder.setStatusId(null);
		}
		if(shippingOrder.getMasterTrackingNum()==null || shippingOrder.getMasterTrackingNum().trim().equals("")){
			shippingOrder.setMasterTrackingNum(null);
		}

		orderList=shippingService.getSearchOrderResult(shippingOrder);
		log.debug("Order List");
		getSession().put("ORDERLISTSIZE",orderList.size());
		

	
		return SUCCESS;
	}	
	
	
	public String listReferenceShipments()
	{
		log.debug("Inside listReferenceShipments of Shipment Action");
		ShippingOrder order = this.getShippingOrder();
		try {
			if(UserUtil.getMmrUser().getUserRole().equals("customer_admin"))
				order.setCustomerId(UserUtil.getMmrUser().getCustomerId());
			else
			{
				order.setCustomerId(0L);
			}
			
			List<ShippingOrder> listSO = new ArrayList<ShippingOrder>();
			listSO = shippingService.searchReferenceShipments(order);
			
			if(listSO.size()==0)
			{
				addActionError(getText("ref.search.mismatch"));
				ShippingOrder shippingOrder = getShippingOrder();
				this.setShippingOrder(shippingOrder);
			}
			else
			{
				//if there are any that are ready to process, then load the first one we find that is ready to process, else perform a repeat of the last one created
				ShippingOrder foundRTP = null;
				for(ShippingOrder o: listSO){
					if(o.getStatusId()==ShiplinxConstants.STATUS_READYTOPROCESS){
						foundRTP = o;
						//persist the input value after reloading the page
						//setOrderPackages(listSO.get(0));
						setOrderAddress(shippingService.getShippingNewOrder(foundRTP.getId()));
						this.setShippingOrder(shippingService.getShippingNewOrder(foundRTP.getId()));
						getShippingOrder().setReferenceValue(order.getReferenceValue());
						break;
					}
				}
				
				if(foundRTP == null){ //repeat the last one in the list
					request.setAttribute("order_id", listSO.get(listSO.size()-1).getId().toString());
					repeatOrder();
				}
				
			}
			log.debug("Shipping Order Ref Val: "+ order.getReferenceValue());
			
		} catch (Exception e) {
			log.error("Error occured while fetching reference lookup results",e);
		}
		return SUCCESS;
	}
	
	
	public List<String> getOrderList(){
		return orderList;
	}
	
	public String getShippingLabel(){
		
	
		//shippingOrder = (ShippingOrder)getSession().get("shippingOrder");
		log.debug("----getShippingLabel-----");
		String id = request.getParameter("id");
		ShippingOrder shippingOrder = null;
	
		try{
			getSession().put("AutoPrintAgain",false);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			String logMessage = "";
			if(id!=null)
			{
				shippingOrder = shippingService.getShippingOrder(new Long(id));
				carrierServiceManager.getShippingLabel(shippingOrder, baos);
				logUpdateAction(shippingOrder.getId(),logMessage);
			}
			else
			{
				int sl_copies = Integer.parseInt(request.getParameter("slcopies"));
				int ci_copies = Integer.parseInt(request.getParameter("cicopies"));
				if(sl_copies > 0)
					logMessage = ShiplinxConstants.TEXT_SHIPPING_LABEL + sl_copies + ShiplinxConstants.SPACE + ShiplinxConstants.TEXT_COPIES;
				if(ci_copies > 0)
					logMessage = logMessage + ShiplinxConstants.COMMA + ShiplinxConstants.SPACE + ShiplinxConstants.TEXT_CUSTOMS_INVOICE + ci_copies + ShiplinxConstants.SPACE + ShiplinxConstants.TEXT_COPIES;
				String strOrdersSelected = request.getParameter("arrayOrders");
				
				List<String> listOrders = new ArrayList<String>(Arrays.asList(strOrdersSelected.split(",")));
				
				//shippingOrder = shippingService.getShippingOrder(new Long(listOrders.get(i)));
				carrierServiceManager.getShippingLabels(listOrders, baos, sl_copies, ci_copies);
				
				for(int i=0;i<listOrders.size();i++)
				{
					if(!StringUtil.isEmpty(listOrders.get(i)))
						logUpdateAction(new Long(listOrders.get(i)),logMessage);
				}
			}
			
			response.setHeader("Cache-Control", "no-cache"); 
//			response.setHeader("Content-Disposition","attachment; filename=label.pdf"); 
			response.setHeader("Expires", "0"); 
			response.setHeader("Cache-Control", 
			"must-revalidate, post-check=0, pre-check=0"); 
			response.setHeader("Pragma", "public"); 
			response.setContentLength(baos.size());
			ByteArrayInputStream bis=new ByteArrayInputStream(baos.toByteArray());
			inputStream = bis;
			
		} catch (Exception e) {			
			log.error("-------------",e);
		}
		
		return SUCCESS;
		
	}

	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	public CustomerManager getCustomerService() {
		return customerService;
	}

	public void setCustomerService(CustomerManager customerService) {
		this.customerService = customerService;
	}
	
	public String listCancelShipment(){
		long customerId=getLoginUser().getCustomerId();
		orderList=shippingService.getTodaysOrderResult(customerId);
		getSession().put("TODAYSORDERLISTSIZE",orderList.size());
		return SUCCESS;
	}
	
	public String editShipment(){
		String orderId = request.getParameter("order_id");
		if(orderId!=null && orderId.length()>0){
			getSession().put("EDIT_ORDER_ID",orderId);				
		}
		
		return SUCCESS;
	}
	
	public String cancelShipment() throws Exception {
		if(UserUtil.getMmrUser()!=null){
				 Customer customer = customerService.getCustomerInfoByCustomerId(UserUtil.getMmrUser().getCustomerId());
				 if(customer!=null && customer.getPaymentType() == 2 && UserUtil.getMmrUser().getUserRole().equals("customer_admin")){
					  addActionError("This shipment was paid with a credit card, Please email save@integratedcarriers.com to cancel this shipment");
					 return ERROR;
				 }
		}
		    String orderId = request.getParameter("orderId");
		    if (orderId != null && orderId.length() > 0) {
		      long order_id = Long.parseLong(orderId);

		      if (order_id == 0) {
		        // shipment has not been created
		        addActionMessage(getText("shippingOrder.cancel.successful"));
		        return INPUT;
		      }

		      boolean isAdmin = UserUtil.getMmrUser().getUserRole().equals("busadmin");
		      if (carrierServiceManager.cancelOrder(order_id, isAdmin)) {
		        if (this.getShippingOrder().getWarehouseProducts() != null
		            && this.getShippingOrder().getWarehouseProducts().size() > 0) {
		          // fetch the products assigned to the order and inject it to the ShippingOrder object
		          // shippingOrder.setWarehouseProducts(productManagerService.getProductsByOrderAndCustomer(order_id,
		          // shippingOrder.getCustomerId()));
		          // update the product counts when the shipment is getting canceled
		          productManagerService.updateProductsCounts(this.getShippingOrder(),
		              ShiplinxConstants.STATUS_CANCELLED);
		        }
		        getShippingOrder().setStatusId((long) ShiplinxConstants.STATUS_CANCELLED);
		        if(order_id > 0){
		        			        this.getShippingOrder().setId(order_id);
		        			        }
		        			        if (shippingService.sendCancelShipmentNotificationMail(this.getShippingOrder(), UserUtil
		        			            .getMmrUser().getBusiness())) {
		        			        	if(this.getShippingOrder().getService() !=null){
		        	 		        	if (this.getShippingOrder().getService().getEmailType().equals("LTL")) {
		        	                         addActionMessage(MessageUtil.getMessage("cancel.ltlshipment.notification.mail.success"));
		        	                       } else {
		        	                        addActionMessage(MessageUtil.getMessage("cancel.spdshipment.notification.mail.success"));
		        	                       }
		        			        	}else{
		        			        		shippingDAO = (ShippingDAO) MmrBeanLocator.getInstance().findBean("shippingDAO");
		        			        		ShippingOrder shippingOrder = shippingDAO.getShippingOrder(order_id);
		        			        		if(shippingOrder.getService() !=null){
		        					        	if (shippingOrder.getService().getEmailType().equals("LTL")) {
		        			                        addActionMessage(MessageUtil.getMessage("cancel.ltlshipment.notification.mail.success"));
		        			                      } else {
		        			                       addActionMessage(MessageUtil.getMessage("cancel.spdshipment.notification.mail.success"));
		        			                      }
		        					        	}
		        			        	}

		        } else {
		          addActionError(MessageUtil.getMessage("cancel.shipment.notification.mail.failure"));
		        }
		        addActionMessage(getText("shippingOrder.cancel.successful"));
		      } else
		        addActionMessage(getText("shippingOrder.cancel.error"));

		      setSelectedOrder(shippingService.getShippingOrder(order_id));
		    }
		    shippingService.deleteLabel(Long.parseLong(orderId));
		    String clientIP = request.getParameter("ip");
		    if(clientIP!=null&&!clientIP.equalsIgnoreCase("error"))
		    {
		    	addLoggedEvent(clientIP);
		    }
		    else
		    {
			String cIP = null;
			try {
				List<InetAddress> ipAddresses = new ArrayList<InetAddress>();
				Enumeration e;
				e = NetworkInterface.getNetworkInterfaces();
				while (e.hasMoreElements()) {
					NetworkInterface ni = (NetworkInterface) e.nextElement();
					if (ni.isLoopback() || !ni.isUp())
						continue;

					for (Enumeration e2 = ni.getInetAddresses(); e2
							.hasMoreElements();) {
						InetAddress ip = (InetAddress) e2.nextElement();
						ipAddresses.add(ip);
					}
				}
				cIP = ipAddresses.get(1).toString().replaceFirst("/", "");
				System.out.println(cIP);
			} catch (Exception e) {
				System.out.println("Catch block DDDDDDDDDDDDDDDD");
				cIP=null;

			}
			addLoggedEvent(cIP);
		    }
		    // to populate the updated logged events on cancellation of the shipment order
		    LoggedEvent loggedEvent = new LoggedEvent();
		    loggedEvent.setEntityId(Long.valueOf(this.getSelectedOrder().getId()));
		    loggedEvent.setEntityType(Long.valueOf(ShiplinxConstants.ENTITY_TYPE_ORDER_VALUE));
		    if (!UserUtil.getMmrUser().getUserRole().equals("busadmin")||!UserUtil.getMmrUser().getUserRole().equals("solutions_manager")) {
		      loggedEvent.setPrivateMessage(false);
		      loggedEvent.setDeletedMessage(false);
		      loggedList = loggedEventService.getLoggedEventInfo(loggedEvent, false);
		    } else {
		      loggedList = loggedEventService.getLoggedEventInfo(loggedEvent, true);
		    }
		    try {
		      boolean boolresult = false;
		      Pickup pickup = pickupService.getPickupByOrderId(Long.valueOf(orderId));
		      if (pickup != null && pickup.getPickupId() > 0) {
		        boolresult = carrierServiceManager.cancelPickup(pickup);
		      }
		      if (boolresult) {
		        addActionMessage(getText("cancel.pickup.success"));

		      }
		      currencyList=shippingService.getallCurrencySymbol();
		    } catch (Exception e) {
		      log.error("Error occured in cancelling a pickup", e);
		      addActionError(getText("error.cancel.pickup.fail"));
		    }
		    
		    return SUCCESS;
		  }


	public String getConsigneeName() {
		return consigneeName;
	}

	public String getId() {
		return id;
	}

	public String getCity() {
		return city;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public String getState() {
		return state;
	}

	public String newQuote() throws Exception {
		
		log.debug("-----newQuote------");
		List<Province> toProvinces;
		List<Province> fromProvinces;
		String fromCountry = "";
		String toCountry = "";
		Address addressbookFrom;
		Address addressbookTo;
		Long customerId = getLoginUser().getCustomerId();
		
		getSession().remove("shippingOrder");
		getSession().remove("PackageType");
		ShippingOrder shippingOrder = getShippingOrder();
		getSession().put("SHIP_MODE", "QUOTE");

		shippingOrder.setCustomerId(getLoginUser().getCustomerId());
		shippingOrder.setPackageTypeId(shippingService.findPackageType(ShiplinxConstants.PACKAGE_TYPE_ENVELOPE_STRING));
		
		addressbookFrom = addressService.findDefaultFromAddressForCustomer(customerId);
		addressbookTo = addressService.findDefaultToAddressForCustomer(customerId);
		
		if(addressbookFrom==null)
			addressbookFrom = new Address();
		if(addressbookTo==null)
			addressbookTo = new Address();

		if(addressbookFrom != null)
			fromCountry = addressbookFrom.getCountryCode();
		if(addressbookTo != null)
			toCountry = addressbookTo.getCountryCode();
		
		//if not set set as default
		if(fromCountry==null ||"".equals(fromCountry)){
			fromCountry = UserUtil.getMmrUser().getBusiness().getAddress().getCountryCode();
			addressbookFrom.setCountryCode(fromCountry);
		}
		if(toCountry==null ||"".equals(toCountry)){
			toCountry = UserUtil.getMmrUser().getBusiness().getAddress().getCountryCode();
			addressbookTo.setCountryCode(toCountry);
		}
		
		
		shippingOrder.setToAddress(addressbookTo);
		shippingOrder.setFromAddress(addressbookFrom);
		
		
		toProvinces=addressService.getProvinces(toCountry);
		fromProvinces=addressService.getProvinces(fromCountry);
		
		getSession().put("Fromprovinces", fromProvinces);
		getSession().put("Toprovinces", toProvinces);
		getSession().put("ToCountry", toCountry);
		getSession().put("FromCountry", fromCountry);
	
		countries=MessageUtil.getCountriesList();

		getSession().put("CountryList", countries);
		getSession().put("shippingOrder",shippingOrder);
		
		packageTypeList = shippingService.getPackages();
		List<PackageType> listPackages = shippingService.getPackages();
		
		List<HashMap<String, String>> packageOption = new ArrayList<HashMap<String, String>>();
		
		// To generate packages radio buttons on jsp
		//short packeCounter=0;
		for(PackageType pType :listPackages){
			HashMap<String, String> packages = new HashMap<String, String>();
			packages.put(pType.getType(),pType.getName());
			packageOption.add(packages);
		}
		
		getSession().put("packageOptions", packageOption);
		
		PackageType pType = new PackageType();
		pType.setType(ShiplinxConstants.PACKAGE_TYPE_PACKAGE_STRING);
		shippingOrder.setPackageTypeId(pType);
		
		getSession().put("PackageType",shippingOrder.getPackageTypeId().getType());
		getSession().put("packageOptions", packageOption);
		getSession().put("listPackages", listPackages);
		//default quantity is 1
		ArrayList<Package> packages = new ArrayList<Package>();
		if(shippingOrder.getQuantity()==null)
			shippingOrder.setQuantity(1);
		
		for(int i=0;i<(int)shippingOrder.getQuantity(); i++){
			Package pack = new Package();
	          pack.setLength(new BigDecimal(1.00).setScale(2,BigDecimal.ROUND_HALF_UP));
	          pack.setWeight(new BigDecimal(1.00).setScale(2,BigDecimal.ROUND_HALF_UP));
	          pack.setHeight(new BigDecimal(1.00).setScale(2,BigDecimal.ROUND_HALF_UP));
	          pack.setWidth(new BigDecimal(1.00).setScale(2,BigDecimal.ROUND_HALF_UP));
	          pack.setCodAmount(new BigDecimal(0.00).setScale(2,BigDecimal.ROUND_HALF_UP));
	          pack.setInsuranceAmount(new BigDecimal(0.00).setScale(2,BigDecimal.ROUND_HALF_UP));
	          packages.add(pack);
		}
		 User user = UserUtil.getMmrUser();
         userDAO = (UserDAO) MmrBeanLocator.getInstance().findBean("userDAO");
		    uom = userDAO.unitOfMeasure();
		    User unitofmeasure = userDAO.findunitofmeasure(user.getUsername());
		    if (user != null && unitofmeasure.getUnitmeasure() == 2) {
		    for (int i = 0; i < uom.size(); i++) {
		    if (unitofmeasure != null
            && unitofmeasure.getUnitmeasure() == uom.get(i).getUnitOfMeasureId()) {
             Collections.swap(uom, 0, i);
		         }
      }
    }


		shippingOrder.setPackages(packages);
		Package packageArray[] = new Package[1];
		shippingOrder.setPackageArray(packageArray);
		
		if(getLoginUser().getUserRole().equalsIgnoreCase(ShiplinxConstants.ROLE_CUSTOMER_SHIPPER))
			request.setAttribute("USERROLE", "shipper");

		return SUCCESS;
	}
	
	public void addLoggedEvent(String clientIP) throws IOException{
						
	    Date currentDate = new Date();
	    String userName = UserUtil.getMmrUser().getUsername();
	    SimpleDateFormat ft= new SimpleDateFormat("EEE MMM dd HH:mm:ss yyyy");
	    String cd=ft.format(currentDate);
	   	LoggedEvent loggedEvent = new LoggedEvent();
		loggedEvent.setEntityId(Long.valueOf(this.getSelectedOrder().getId()));
		loggedEvent.setEventDateTime(currentDate);
		loggedEvent.setEntityType(Long.valueOf(ShiplinxConstants.STATUS_CANCELLED));	
		loggedEvent.setEventUsername(userName); 
		loggedEvent.setMessage(ShiplinxConstants.SHIPMENT_CANCELLED+" "+"on"+" "+cd+" "+"by"+" "+userName+" "+"used IP "+" "+clientIP);
		loggedEvent.setPrivateMessage(false);
		loggedEvent.setDeletedMessage(false);
	
		loggedEvent.setSystemLog("The Order "+ Long.valueOf(this.getSelectedOrder().getId())+" has been Cancelled");
		loggedEventService.addLoggedEventInfo(loggedEvent);
	}
	
//	public String packageInformationQuote() throws Exception {
//
//		try{
////			ShiplinxConstants.setCustomer(customerService.getCustomerInfoByCustomerId(getLoginUser().getCustomerId()));
//			
//			String packageType=ShiplinxConstants.PACKAGE_TYPE_ENVELOPE_STRING; 
//
//			//Set the default dimension to the package
//			ArrayList<Package> packages = new ArrayList<Package>();
//			Package packageArray[];
//			ShippingOrder shippingOrder = getShippingOrder();
//			//default quantity is 1
//			if(shippingOrder.getQuantity()==null)
//				shippingOrder.setQuantity(1);
//			
//			//set the default package
//			if(shippingOrder != null && shippingOrder.getPackageTypeId() != null){
//
//				
//				if(shippingOrder.getPackageTypeId().getType() == null || "".equalsIgnoreCase(shippingOrder.getPackageTypeId().getType())){
//					
//					shippingOrder.setPackageTypeId(shippingService.findPackageType(packageType));
//				}
//				else{	
//					
//					packageType = shippingOrder.getPackageTypeId().getType();	
//					
//					shippingOrder.setPackageTypeId(shippingService.findPackageType(packageType));
//				}
//			}
//			
//			
//			packageArray = new Package[(int)shippingOrder.getQuantity()];
//			
//			for(int i=0;i<(int)shippingOrder.getQuantity(); i++){
//				List<Package> packages1=((ShippingOrder)getSession().get("shippingOrder")).getPackages();
//				if(i<packages1.size()){
//					log.debug("--getWeight--"+packages1.get(i).getWeight());
//					packages.add(packages1.get(i));
//				}else{
//					Package pack = new Package();
//					pack.setLength( new BigDecimal(1.0));
//					pack.setWeight( new Float(1.0));
//					pack.setHeight( new BigDecimal(1.0));
//					pack.setWidth( new BigDecimal(1.0));
//					pack.setCodAmount( new BigDecimal(0.0));
//					pack.setInsuranceAmount( new BigDecimal(0.0));
//					packages.add(pack);
//					packageArray[i] = pack;
//				}
//			}
//			
//			shippingOrder.getFromAddress().setAddress1("");
//			shippingOrder.getToAddress().setAddress1("");
//			
////			RIZM COMENTING OUT TO REFACTOR ADDRESS OBJECT
////			if(((ShippingOrder)getSession().get("shippingOrder")).getShipFromId() != null)
////				shippingorder.getFromAddress().setId(((ShippingOrder)getSession().get("shippingOrder")).getShipFromId().getId());
////			else{
////				shippingOrder.setShipFromId(shippingorder.getFromAddress());
////			}
////				
////			if(((ShippingOrder)getSession().get("shippingOrder")).getShipToId() != null)
////				shippingOrder.getShipToId().setId(((ShippingOrder)getSession().get("shippingOrder")).getShipToId().getId());
////			else{
////				shippingOrder.setShipToId(shippingOrder.getShipToId());
////			}
//	
//			shippingOrder.setPackageArray(packageArray);
//			shippingOrder.setPackages(packages);
//			shippingOrder.setPackageTypeId(shippingService.findPackageType(packageType));
//			
//			getSession().put("shippingOrder",shippingOrder);
//			
//			if(packageType.equalsIgnoreCase(ShiplinxConstants.PACKAGE_TYPE_PACKAGE_STRING)
//					|| packageType.equalsIgnoreCase(ShiplinxConstants.PACKAGE_TYPE_PALLET_STRING)){
//				return "DIMENSSION_PAGE";
//			}
//		
//		}catch (Exception e) {
//			log.debug("----Exception-----"+e);
//			e.printStackTrace();
//	
//		}
//			return SUCCESS; //default is type_env
//	}

	public List<Address> getAddresses() {
		return addressService.findAddressesByCustomer(new Long(4134));
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}
	
//	public List<Carrier> getCarriers() {
//		return carriers;
//	}
//
//	public void setCarriers(List<Carrier> carriers) {
//		this.carriers = carriers;
//	}

	public List<ShippingOrder> getShipments() {
//		return shipments;
		return (List<ShippingOrder>) getSession().get("shipments");
	}

	public void setShipments(List<ShippingOrder> shipments) {
//		this.shipments = shipments;
		getSession().put("shipments", shipments);
	}
	
	public List<Pickup> getPickups() {
//		return shipments;
		return (List<Pickup>) getSession().get("listpickups");
	}

	public void setPickups(List<Pickup> lstpickups) {
//		this.shipments = shipments;
		getSession().put("listpickups", lstpickups);
	}

	public String searchShipment(){
		try {			
			getSession().remove("shipments");
			getSession().remove("shippingOrder");
			ShippingOrder so = this.getShippingOrder();			
			//default the date range to a week ago from the current date
			int month = Calendar.getInstance().get(Calendar.MONTH);
			int year = Calendar.getInstance().get(Calendar.YEAR);
			int day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);	
			
			  /*
		       * Calendar calendar = new GregorianCalendar(year, month, day);
		       * so.setToDate(FormattingUtil.getFormattedDate(calendar.getTime(),
		       * FormattingUtil.DATE_FORMAT_WEB)); day = day - 1; calendar.set(Calendar.DAY_OF_MONTH, day);
		       * so.setFromDate(FormattingUtil.getFormattedDate(calendar.getTime(),
		       * FormattingUtil.DATE_FORMAT_WEB));
		       */
			User user = UserUtil.getMmrUser();
		      try {
		        if (user != null && user.getTimeZone() != null && !user.getTimeZone().isEmpty()) {

		          /*
		           * scheduledShipDate_web = FormattingUtil.getFormattedDate(timestamp.getTime(),
		           * FormattingUtil.DATE_FORMAT_WEB);
		           */
		          Calendar cal = Calendar.getInstance(TimeZone.getTimeZone(user.getTimeZone()));
		          SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		          SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
		          dateFormat.setTimeZone(cal.getTimeZone());
		          timeFormat.setTimeZone(cal.getTimeZone());
		          toDate = dateFormat.format(cal.getTime());
		          so.setToDate(toDate);
		          int day1 = cal.get(Calendar.DAY_OF_MONTH);
		          day1 = day1 - 1;
		          cal.set(Calendar.DAY_OF_MONTH, day1);
		          fromDate = dateFormat.format(cal.getTime());
		          so.setFromDate(fromDate);
		          System.out.println("From Date " + fromDate + " Time " + timeFormat.format(cal.getTime()));
		        } else {
		          Calendar calendar = new GregorianCalendar(year, month, day);
		          so.setToDate(FormattingUtil.getFormattedDate(calendar.getTime(),
		              FormattingUtil.DATE_FORMAT_WEB));
		          day = day - 1;
		          calendar.set(Calendar.DAY_OF_MONTH, day);
		          so.setFromDate(FormattingUtil.getFormattedDate(calendar.getTime(),
		              FormattingUtil.DATE_FORMAT_WEB));
		        }
		      } catch (Exception e) {
		        // TODO Auto-generated catch block
		        e.printStackTrace();
		      }
	
			
		   
				if (getSession().get("CARRIERS") == null) {
					initCarrierList();
					so.setCarrierId(-1L);
				}
				if (getSession().get("SERVICES") == null) {
					List<Service>services = getCarrierServices(so.getCarrierId());
					getSession().put("SERVICES", services);
				} 
			if (this.getOrderStatusList() == null) {
				this.setOrderStatusList(this.shippingService.getShippingOrdersAllStatus());
			}
			
			if (getSession().get("customersList") == null) {
				this.populateCustomersList();
			}
			
			if(this.getBillingStatusList() == null){
				this.setBillingStatusList(this.shippingService.getShippingBillingAllStatus());
			}
			//Calls the list Shipments that gets the shipments on the load event
//			if(!UserUtil.getMmrUser().getUserRole().equals(ShiplinxConstants.ROLE_ADMIN))
//				listShipment();
			
	    } catch (Exception e) {
	    	e.printStackTrace();
	    	addActionError(getText("content.error.unexpected"));
		}
		
		return SUCCESS;
	}	

	public String listShipment(){
		String manifestResult = "";
		try {

//			if (shipments != null)
//				shipments.clear();
			ShippingOrder so = this.getShippingOrder();	
			String fromDate=this.request.getParameter("shippingOrder.fromDate");
			String toDate=this.request.getParameter("shippingOrder.toDate");
			String carrierId=this.request.getParameter("shippingOrder.carrierId");
			String clickableButtonId=this.request.getParameter("d-16544-e");
			if("5".equals(clickableButtonId) && "80".equals(carrierId)){
				//new EODManifestCreator(fromDate,toDate);				
				getSession().put("MIDLND_SHIPPING_ORDER_FROM_DATE", fromDate);
				getSession().put("MIDLND_SHIPPING_ORDER_TO_DATE", toDate);
				getSession().put("CARRIER_ID", carrierId);
				String midlandEODFileName = getUniqueTempPdfFileName("MidlandEOD");
								log.debug("Mohan");
								log.debug(midlandEODFileName);				
							response.setContentType("application/pdf");
								response.setHeader("Content-Disposition",
										"attachment;filename=MidlandEOD.pdf");							
								new EODManifestCreator().midlandEODPdf(midlandEODFileName);
								File pdfFile = new File(midlandEODFileName);
								if(!pdfFile.exists()){
									pdfFile.createNewFile();
								}
								FileInputStream fileInputStream = new FileInputStream(pdfFile);
								OutputStream outputStream = response.getOutputStream();	
						        int read=0;
								byte[] bytes = new byte[1024];
						        while((read = fileInputStream.read(bytes))!= -1){
						        	outputStream.write(bytes, 0, read);
								}
						        outputStream.flush();
						        outputStream.close();
			}			if (carrierId != null && !"".equalsIgnoreCase(carrierId) && "5".equals(clickableButtonId)
			          && !("80".equals(carrierId))) {
			        getSession().put("CARRIER_ID", carrierId);
			        response.setContentType("application/pdf");
			        					response.setHeader("Content-Disposition",
			        							"attachment;filename=EODManifest.pdf");	
			        printManifest();
			      } else if ((carrierId == null || "".equalsIgnoreCase(carrierId) || fromDate == null
			          || "".equalsIgnoreCase(fromDate) || toDate == null || "".equalsIgnoreCase(toDate))
			          && ("5".equals(clickableButtonId) && !("80".equals(carrierId)) && carrierId
			              .equalsIgnoreCase(""))) {
			        addActionError("EOD : Select FromDate,ToDate and Carrier");

			        return SUCCESS;
			      }
			if (so != null) {
				// Ajax calls goes to to listService.jsp and serviceId gets updated in markup.serviceId
				// therefore it needs to be updated back in shippingOrder
				String s = this.request.getParameter("markup.serviceId");
				if ( !StringUtil.isEmpty(s) )
					so.setServiceId(Long.parseLong(s));
				
				if((so.getCustomerId()==null || so.getCustomerId()==0) 
						&& getLoginUser().getCustomerId()>0)
					so.setCustomerId(getLoginUser().getCustomerId());
				
				//so.setBillingStatus(null);
			}
			if(UserUtil.getMmrUser().getUserRole().equals(ShiplinxConstants.ROLE_SALES))
			{
				so.setSalesAgentUsername(UserUtil.getMmrUser().getUsername());
			}
			else if(UserUtil.getMmrUser().getUserRole().equals(ShiplinxConstants.ROLE_CUSTOMER_SHIPPER))
			{
				so.setCreatedBy(UserUtil.getMmrUser().getUsername());
			} 
			/*		Long businessId=(Long) ActionContext.getContext().getSession().get(Constants.BUSINESS_ID_SESSION);
												if(businessId!=null && !businessId.equals("")){
												so.setBusinessId(businessId);
												}else{
													so.setBusinessId(UserUtil.getMmrUser().getBusinessId());
												}*/
									   BusinessDAO businessDAO=(BusinessDAO)MmrBeanLocator.getInstance().findBean("businessDAO");
									  
												so.setBusinessId(BusinessFilterUtil.setBusinessIdbyUserLevel());
												List<Business>	allbusList=businessDAO.getHoleBusinessList();
												List<Long> busids=new ArrayList<Long>();
												if(so.getBusinessId()==0 && UserUtil.getMmrUser().getUserRole().equals(Constants.SYS_ADMIN_ROLE_CODE)){
												
													if(allbusList!=null && allbusList.size()>0){
													 
														for(Business bs:allbusList){
															 busids.add(bs.getId());
														}
														so.setBusinessIds(busids);
										}
												}else if(!UserUtil.getMmrUser().getUserRole().equals(ShiplinxConstants.ROLE_CUSTOMER_ADMIN)){
												so.setBusinessIds(BusinessFilterUtil.getBusIdParentId(so.getBusinessId()));
												}else if(UserUtil.getMmrUser().getUserRole().equals(ShiplinxConstants.ROLE_CUSTOMER_ADMIN)){
													busids.add(so.getBusinessId());
											     	so.setBusinessIds(busids);
											     	so.setCustomerId(UserUtil.getMmrUser().getCustomerId());
											     	
												}
			//if the user belongs to a branch, then search only those shipments that belong to customers of that branch
			if(!StringUtil.isEmpty(UserUtil.getMmrUser().getBranch()))
				so.setBranch(UserUtil.getMmrUser().getBranch());
			
			String reqCacheId = this.request.getParameter("cacheId");
			boolean cacheNotFound = false;
			Object cacheObject = null;
			if (reqCacheId == null || "".equals(reqCacheId)){
				cacheNotFound = true;
			}else{
				cacheObject = getSession().get(reqCacheId);
				if (cacheObject == null || "".equals(cacheObject)){
					cacheNotFound = true;
				}
			}
			if (this.shippingService != null && cacheNotFound) {
				so.setPurpose("SEARCH_SHIPMENTS");
				List<ShippingOrder> shipments = this.shippingService.getShipments(so);
				int rows_per_page = 500;
				int temp = 1;
				String cacheId = "";
				cachedList = new ArrayList<KeyValueVO>();
				for (int i = 0; i < shipments.size(); i=i+rows_per_page) {
					String uuid = UUID.randomUUID().toString();
					temp = i + rows_per_page; 
					if (temp > shipments.size()){
						temp = shipments.size();
					}
					KeyValueVO keyValue = new KeyValueVO();
					List<ShippingOrder> subList = shipments.subList(i, temp);
					getSession().put(uuid, subList);
					if (i==0){
						cacheId = uuid;
					}
					keyValue.setKey(uuid);
					keyValue.setValue(i+" - "+temp);
					cachedList.add(keyValue);
				}
				request.setAttribute("current_page", cacheId);
				getSession().put("cachedList", cachedList);
				this.setShipments((List<ShippingOrder>)getSession().get(cacheId));
			}else{
				cachedList = (List<KeyValueVO>) getSession().get("cachedList");
				request.setAttribute("current_page", reqCacheId);
				this.setShipments((List<ShippingOrder>)cacheObject);
			}
			
			User user = userService.findUserByUsername(UserUtil.getMmrUser().getUsername());
			//setting the print config values for the logged user
			request.setAttribute("no_of_lbls", user.getPrintNoOfLabels());
			request.setAttribute("no_of_ci", user.getPrintNoOfCI());
			request.setAttribute("autoprint", user.isAutoPrint());
			//Setting the Attribute for Carts
			request.setAttribute("fromCart", "false");
			
			/// ============= Exchnage Rate ===============
			
			if(UserUtil.getMmrUser()!=null && UserUtil.getMmrUser().getCustomerId()>0){
								List<ShippingOrder> orderTemp= new ArrayList<ShippingOrder>();
								orderTemp=this.getShipments();
								if(orderTemp!=null){
									for(ShippingOrder orderCheck:orderTemp){
										if(orderCheck!=null && orderCheck.getCharges()!=null && orderCheck.getCharges().size()>0){
											Double exchRate=orderCheck.getCharges().get(0).getExchangerate().doubleValue();
											/*if(exchRate>0 && orderCheck.getQuoteTotalCost()!=null && orderCheck.getQuoteTotalCost()>0){
												orderCheck.setQuoteTotalCost(orderCheck.getQuoteTotalCost()*exchRate);
											}*/
											if(exchRate>0 && orderCheck.getQuoteTotalCharge()!=null && orderCheck.getQuoteTotalCharge()>0){
												orderCheck.setQuoteTotalCharge(orderCheck.getQuoteTotalCharge());
											}
											/*if(exchRate>0 && orderCheck.getActualTotalCost()!=null && orderCheck.getActualTotalCost()>0){
												orderCheck.setActualTotalCost(orderCheck.getActualTotalCost()*exchRate);
											}*/
											if(exchRate>0 && orderCheck.getActualTotalCharge()!=null && orderCheck.getActualTotalCharge()>0){
												orderCheck.setActualTotalCharge(orderCheck.getActualTotalCharge());
											}
										}
									}
								}
								Customer customer = customerService.getCustomerInfoByCustomerId(UserUtil.getMmrUser().getCustomerId());
								CurrencySymbol currencySymbol = new CurrencySymbol();
								if(customer.getDefaultCurrency() != null && customer.getDefaultCurrency().isEmpty()){
								   currencySymbol = shippingService.getSymbolByCurrencycode(customer.getDefaultCurrency());
						    	  if(currencySymbol!=null){
						    	  getSession().put("DefaultCurrencySymbol", currencySymbol.getCurrencySymbol());
						    	  }else{
						    		  getSession().put("DefaultCurrencySymbol", "$");
						    	   }
								}else{
									currencySymbol = shippingService.getCurrencyCodeByCountryName(user.getLocale().substring(3,5));
						   	      	  if(currencySymbol==null){
						   	      		  for(int i=0;i<ShiplinxConstants.EURO_UNION_LIST.length;i++){
						   	      			  if(user.getLocale().substring(3, 5).equalsIgnoreCase(ShiplinxConstants.EURO_UNION_LIST[i])){
						   	      				currencySymbol=shippingService.getCurrencyCodeByCountryName("EUCG");
						   	      				break;
				   	      			  }
						   	      		  }
						   	      	  }
						   	       getSession().put("DefaultCurrencySymbol", currencySymbol.getCurrencySymbol());
								}
							}else{
								getSession().put("DefaultCurrencySymbol", "$");
							}
			/// ========== End ===========================
			
	    } catch (Exception e) {
	    	e.printStackTrace();
	    	addActionError(getText("content.error.unexpected"));
	    	
		}
		if(this.getShipments()!=null){
					this.setShipments(filterShipments(this.getShipments()));
					}
		return SUCCESS;
	}	

	private List<ShippingOrder> filterShipments(List<ShippingOrder> shipments) {
						// TODO Auto-generated method stub
						List<Customer> filCus=(List<Customer>) getSession().get(ShiplinxConstants.SESSION_BUSINESSFILTER_CUSTOMERID);
					    BusinessDAO businessDAO=(BusinessDAO)MmrBeanLocator.getInstance().findBean("businessDAO");
						List<ShippingOrder> filteredShippments=new ArrayList<ShippingOrder>();
						/*Iterator<ShippingOrder> ships=shipments.iterator();
						Iterator<Customer> customers=filCus.iterator();*/
						if(filCus!=null && filCus.size()>0 && shipments.size()>0 && shipments!=null){
						Long businessId=BusinessFilterUtil.setBusinessIdbyUserLevel(); 
						 List<Long> businessIds=new ArrayList<Long>();
						if(businessId>0){
							businessIds=BusinessFilterUtil.getBusIdParentId(businessId);
						}else if(businessId==0){
							List<Business>	allbusList=businessDAO.getHoleBusinessList();
							if(allbusList!=null && allbusList.size()>0){
								for(Business bs:allbusList){
									businessIds.add(bs.getId());
								}
							}
						
						}
								for(ShippingOrder so:shipments){
									
									for(Customer c:filCus){
										if(so.getCustomerId()!=null){
					                       if(so.getCustomerId()==c.getId()){
											filteredShippments.add(so);
										    }
					                       
										  }
									}
									
									if(so.getCustomerId()==null ){
								    	
								    	for(Long busid:businessIds){
								    		if(so.getBusinessId()>0){
								    			if(so.getBusinessId()==busid){
								    				filteredShippments.add(so);
								    			}
								    		}
								    	}
								    }
							 }
							 			
						}
						
						return filteredShippments;
					}
				
			
		public String searchPickups()
	{
		log.debug("Inside searchPickups method of ShipmentAction");
		
		getSession().remove("pickup");
		getSession().remove("listpickups");
		Pickup pickup = this.getPickup();
		//Provide ALL option to Carriers list.
		initCarrierListAll();
		
		//setting default date to fromDate of pickup
		int month = Calendar.getInstance().get(Calendar.MONTH);
		int year = Calendar.getInstance().get(Calendar.YEAR);
		int day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);	
		/*
	     * Calendar calendar = new GregorianCalendar(year, month, day);
	     * pickup.setFromDate(FormattingUtil.getFormattedDate(calendar.getTime(),
	     * FormattingUtil.DATE_FORMAT_WEB));
	     */
	    try {
	      User user = UserUtil.getMmrUser();
	      if (user != null && user.getTimeZone() != null && !user.getTimeZone().isEmpty()) {

	        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone(user.getTimeZone()));
	        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
	        dateFormat.setTimeZone(cal.getTimeZone());
	        timeFormat.setTimeZone(cal.getTimeZone());
	        fromDate = dateFormat.format(cal.getTime());
	        pickup.setFromDate(fromDate);
	      } else {
	        Calendar calendar = new GregorianCalendar(year, month, day);
	        pickup.setFromDate(FormattingUtil.getFormattedDate(calendar.getTime(),
	            FormattingUtil.DATE_FORMAT_WEB));
	      }
	    } catch (Exception e) {
	      // TODO Auto-generated catch block
	      e.printStackTrace();
	    }

		
		if (getSession().get("customersList") == null) {
			this.populateCustomersList();
		}
		
		if (getSession().get("SERVICES") == null) {
			List<Service>services = getCarrierServices(-1L);
			getSession().put("SERVICES", services);
		}
		//set pickup
		this.setPickup(pickup);
		return SUCCESS;
	}
	
	public String createPickup()
	{

		long pickupresId = 0;
		try 
		{
			Pickup pickup = this.getPickup();
			
			if(pickup.getPickupDate_web()!=null && pickup.getPickupDate_web().length()>0){
				Date date = FormattingUtil.getDate(pickup.getPickupDate_web(), FormattingUtil.DATE_FORMAT_WEB);
				pickup.setPickupDate(new Timestamp(date.getTime()));
			}
			
			pickupresId = carrierServiceManager.createPickup(pickup);
			if(pickupresId > 0){
				StringBuilder stb = new StringBuilder(getText("pickup.success"));
				if(!StringUtil.isEmpty(pickup.getConfirmationNum()))
					stb.append(" Conf #: " + pickup.getConfirmationNum());
				addActionMessage(stb.toString());
			}

		} 
		catch (Exception e) 
		{
			log.error("Error occured in creating a pickup",e);
			addActionError(getText("error.pickup.fail"));
			for(CarrierErrorMessage carrierErrorMessage  :carrierServiceManager.getErrorMessages()){
				addActionError(carrierErrorMessage.getMessage());
			}
			return INPUT;
		}
		//to redirect to search page, first calling searchPickups() to reset the pickup.
		searchPickups();
		//show the list with the new pickup created.
		return listPickups();
	}
	
	public String cancelPickup()
	{
		log.debug("Inside cancelPickup method of ShipmentAction");
		long pickupId =0L;
		boolean boolresult = false;
		try 
		{
			if(request.getParameter("pickupid")!=null)
			{
				pickupId = Long.parseLong(request.getParameter("pickupid"));
				
			
			Pickup pickup = pickupService.getPickupById(pickupId);
			boolresult = carrierServiceManager.cancelPickup(pickup);
			}
			
			if(boolresult)
				addActionMessage(getText("cancel.pickup.success"));
			
		} 
		catch (Exception e) {
			log.error("Error occured in cancelling a pickup",e);
			addActionError(getText("error.cancel.pickup.fail"));
		}
		return listPickups();
	}
	
	public String listPickups()
	{
		log.debug("Inside listPickups method of ShipmentAction");
		
		getSession().remove("listpickups");
		Pickup pickup = this.getPickup();
		
		log.debug("From date: "+pickup.getFromDate());
		log.debug("To Date: "+pickup.getToDate());
		log.debug("Carrier Id: "+pickup.getCarrierId());
		log.debug("Service Id: "+pickup.getServiceId());
		log.debug("Carrier conf num:"+pickup.getConfirmationNum()+"::");
		log.debug("Status: "+pickup.getStatus());
		
		try 
		{
			if(pickup.getConfirmationNum()!=null && "".equals(pickup.getConfirmationNum()))
				pickup.setConfirmationNum(null);
			if("".equals(pickup.getToDate()))
				pickup.setToDate(null);
			else if(pickup.getToDate()!=null && !pickup.getToDate().contains(ShiplinxConstants.HH_MM_SS_END))
				pickup.setToDate(pickup.getToDate()+ShiplinxConstants.HH_MM_SS_END);
						
			this.setPickups(pickupService.getPickups(pickup));
			
		} catch (Exception e) {
			log.error("Error occured in listPickups()",e);
		}
		
		return SUCCESS;
	}
	
	public String goToCreatePickup()
	{
		log.debug("Inside goToCreatePickup.........");
		
		getSession().remove("pickup");
		getSession().remove("listpickups");
		Pickup pickup = this.getPickup();
		//Setting default customer Address if any
		Address fromAddress;
		
		fromAddress = addressService.findDefaultFromAddressForCustomer(pickup.getCustomerId());
		if(fromAddress!=null)
			pickup.setAddress(fromAddress);
		//setting the destination country
		pickup.setDestinationCountryCode(UserUtil.getMmrUser().getBusiness().getAddress().getCountryCode());
		//setting current date to pickup date as default.
		int month = Calendar.getInstance().get(Calendar.MONTH);
		int year = Calendar.getInstance().get(Calendar.YEAR);
		int day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);	
		
		 /*
	     * Calendar calendar = new GregorianCalendar(year, month, day);
	     * pickup.setPickupDate_web(FormattingUtil.getFormattedDate(calendar.getTime(),
	     * FormattingUtil.DATE_FORMAT_WEB));
	     */
	    try {
	      User user = UserUtil.getMmrUser();
	      if (user != null && user.getTimeZone() != null && !user.getTimeZone().isEmpty()) {
	        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone(user.getTimeZone()));
	        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
	        dateFormat.setTimeZone(cal.getTimeZone());
	        timeFormat.setTimeZone(cal.getTimeZone());
	        fromDate = dateFormat.format(cal.getTime());
	        pickup.setPickupDate_web(fromDate);
	      } else {
	        Calendar calendar = new GregorianCalendar(year, month, day);
	        pickup.setPickupDate_web(FormattingUtil.getFormattedDate(calendar.getTime(),
	            FormattingUtil.DATE_FORMAT_WEB));
	      }
	      this.populateUserList();
	    } catch (Exception e) {
	      // TODO Auto-generated catch block
	      e.printStackTrace();
	    }

		//Date date = FormattingUtil.getDate(FormattingUtil.getFormattedDate(calendar.getTime(),FormattingUtil.DATE_FORMAT_WEB));
		//pickup.setPickupDate(new Timestamp(date.getTime()));
		//populate the list of carriers - exclude ALL
		initCarrierList();
		//populate provinces
		provinces=addressService.getProvinces(UserUtil.getMmrUser().getBusiness().getAddress().getCountryCode());
		getSession().put("provinces", provinces);
		
		return SUCCESS;
	}
	
	private void initCarrierList() {
		// TODO Auto-generated method stub
		Long businessId=(Long) ActionContext.getContext().getSession().get(Constants.BUSINESS_ID_SESSION);
								List<Carrier> cList = new ArrayList<Carrier>();
								if(businessId!=null && !businessId.equals("")){
									cList = this.carrierServiceManager.getCarriersForBusiness(businessId);
							}else{
									cList = this.carrierServiceManager.getCarriersForBusiness(UserUtil.getMmrUser().getBusinessId());
								}
		Carrier c = new Carrier();
		c.setId(-1L);
		c.setName("");
		cList.add(0, c);
		getSession().put("CARRIERS", cList);
	}
	private void initCustomerCarrierList(long customerId) {
    // TODO Auto-generated method stub
    List<Carrier> cList = this.carrierServiceManager.getCustomerCarriersForBusiness(customerId);
    /*

         * Carrier c = new Carrier(); c.setId(-1L); c.setName(""); cList.add(0, c);

         */

    getSession().put("CARRIERS", cList);
  }

	
	private void initCarrierListAll() {
		// TODO Auto-generated method stub
		List<Carrier> cList = this.carrierServiceManager.getCarriersForBusiness(UserUtil.getMmrUser().getBusinessId());
		Carrier c = new Carrier();
		c.setId(-1L);
		c.setName("");
		cList.add(0, c);
		Carrier cAll = new Carrier();
		cAll.setId(-2L);
		cAll.setName("ALL");
		cList.add(1, cAll);
		getSession().put("CARRIERS", cList);
	}
	
	private void initCarrierListANY() {
		// TODO Auto-generated method stub
		List<Carrier> cList = this.carrierServiceManager.getCarriersForBusiness(UserUtil.getMmrUser().getBusinessId());
//		Carrier c = new Carrier();
//		c.setId(-1L);
//		c.setName("ANY");
//		cList.add(0, c);
		getSession().put("CARRIERS", cList);
	}
	
	private List<Service> getCarrierServices(Long carrierCode) {
		// TODO Auto-generated method stub
		ShippingOrder so = this.getShippingOrder();
		List<Service> sList = this.carrierServiceManager.getServicesForCarrier(carrierCode);
		Service s = new Service();
		s.setId(-1L);
		s.setName("");
		s.setCarrierId(carrierCode);
		sList.add(0, s);
		getSession().put("SERVICES", sList);

		return sList;
	}
	private List<Service> getCustomerCarrierServices(Long carrierCode, long customerId) {
    // TODO Auto-generated method stub
    ShippingOrder so = this.getShippingOrder();
    List<Service> sList = this.carrierServiceManager.getCustomerServicesForCarrier(carrierCode,
        customerId);
    Service s = new Service();
    s.setId(-1L);
    s.setName("");
    s.setCarrierId(carrierCode);
    sList.add(0, s);
    getSession().put("SERVICES", sList);
    return sList;
  }

	public String saveShipment(){
		try {
			ShippingOrder so = this.getShippingOrder();			
			if (so != null) {
				// Ajax calls goes to to listService.jsp and serviceId gets updated in markup.serviceId
				// therefore it needs to be updated back in shippingOrder
				String s = this.request.getParameter("markup.serviceId");
				if ( !StringUtil.isEmpty(s) )
					so.setServiceId(Long.parseLong(s));
				
				if(getLoginUser().getCustomerId()>0)
					so.setCustomerId(getLoginUser().getCustomerId());
			}
//			if (this.shippingService != null) {

//			}
	    } catch (Exception e) {
	    	e.printStackTrace();
	    	addActionError(getText("content.error.unexpected"));
		}
		
		return SUCCESS;
	}

	public String autolinkedShipment(){
		try {
//			if (shipments != null)
//				shipments.clear();
			getSession().remove("shippingOrder");
			ShippingOrder so = this.getShippingOrder();
			if (so != null) {
				so.setEdiInvoiceNumber(request.getParameter("ediInvoiceNumber"));
				so.setBillingStatus(ShiplinxConstants.BILLING_STATUS_AWAITING_CONFIRMATION);
				so.setCustomerId(getLoginUser().getCustomerId());
				Long businessId=(Long) ActionContext.getContext().getSession().get(Constants.BUSINESS_ID_SESSION);
											    
											    if(businessId!=null && !businessId.equals("")){
											    	so.setBusinessId(businessId);
											    }else{
											    	so.setBusinessId(UserUtil.getMmrUser().getBusinessId());
											    }
											    
											    
											    BusinessDAO businessDAO=(BusinessDAO)MmrBeanLocator.getInstance().findBean("businessDAO");
												  
												so.setBusinessId(BusinessFilterUtil.setBusinessIdbyUserLevel());
												List<Business>	allbusList=businessDAO.getHoleBusinessList();
												List<Long> busids=new ArrayList<Long>();
												if(so.getBusinessId()==0 && UserUtil.getMmrUser().getUserRole().equals(Constants.SYS_ADMIN_ROLE_CODE)){
												
													if(allbusList!=null && allbusList.size()>0){
														 
														for(Business bs:allbusList){
														 busids.add(bs.getId());
														}
														so.setBusinessIds(busids);
													}
												}else if(!UserUtil.getMmrUser().getUserRole().equals(ShiplinxConstants.ROLE_CUSTOMER_ADMIN)){
											so.setBusinessIds(BusinessFilterUtil.getBusIdParentId(so.getBusinessId()));
												}else if(UserUtil.getMmrUser().getUserRole().equals(ShiplinxConstants.ROLE_CUSTOMER_ADMIN)){
													
													busids.add(so.getBusinessId());
													so.setBusinessIds(busids);
												}
			}
			if (this.shippingService != null) {
				this.setShipments( this.shippingService.getShipments(so) );
			}
			
			if (getSession().get("customersList") == null) {
				this.populateCustomersList();
			}
			
			
	    } catch (Exception e) {
	    	e.printStackTrace();
	    	addActionError(getText("content.error.unexpected"));
		}
		
		return SUCCESS;
	}	
	
	public String unlinkedShipment(){
		try {
//			if (shipments != null)
//				shipments.clear();
			getSession().remove("shippingOrder");
			ShippingOrder so = this.getShippingOrder();
			if (so != null) {
				so.setEdiInvoiceNumber(request.getParameter("ediInvoiceNumber"));
				so.setBillingStatus(ShiplinxConstants.BILLING_STATUS_ORPHAN);
				so.setCustomerId(getLoginUser().getCustomerId());
				 Long businessId=(Long) ActionContext.getContext().getSession().get(Constants.BUSINESS_ID_SESSION);
				 				 				    
				 				 				 if(businessId!=null && !businessId.equals("")){
				 			 				   so.setBusinessId(businessId);
				 				 				 }else{
				 				 				   so.setBusinessId(UserUtil.getMmrUser().getBusinessId());
				 				 			    }
				 				 				 
				 				 				 BusinessDAO businessDAO=(BusinessDAO)MmrBeanLocator.getInstance().findBean("businessDAO");
				 				 				  
				 				 					so.setBusinessId(BusinessFilterUtil.setBusinessIdbyUserLevel());
				 				 					List<Business>	allbusList=businessDAO.getHoleBusinessList();
				 				 					List<Long> busids=new ArrayList<Long>();
				 				 					if(so.getBusinessId()==0 && UserUtil.getMmrUser().getUserRole().equals(Constants.SYS_ADMIN_ROLE_CODE)){
				 				 					
				 				 						if(allbusList!=null && allbusList.size()>0){
				 				 							 
				 				 							for(Business bs:allbusList){
				 				 								 busids.add(bs.getId());
				 				 							}
				 				 							so.setBusinessIds(busids);
				 				 						}
				 				 					}else if(!UserUtil.getMmrUser().getUserRole().equals(ShiplinxConstants.ROLE_CUSTOMER_ADMIN)){
				 				 					so.setBusinessIds(BusinessFilterUtil.getBusIdParentId(so.getBusinessId()));
				 				 					}else if(UserUtil.getMmrUser().getUserRole().equals(ShiplinxConstants.ROLE_CUSTOMER_ADMIN)){
				 				 						
				 				 						busids.add(so.getBusinessId());
				 			 						so.setBusinessIds(busids);
				 				 					}
			}
			if (this.shippingService != null) {
				this.setShipments( this.shippingService.getShipments(so) );
			}
			
			
				this.populateCustomersList();
			 
			
	    } catch (Exception e) {
	    	e.printStackTrace();
	    	addActionError(getText("content.error.unexpected"));
		}
		
		return SUCCESS;
	}
	
	private void assignShipments(){
		List<Long> shipmentIds = new ArrayList<Long>();
		String[] ids = null;
		if(shipmentIdList!=null && shipmentIdList.size()>0){
			String firstValue = shipmentIdList.get(0);
			ids = firstValue.split(",");
		}
		for(int i=0;i<ids.length;i++){
			long shipmentIdLong = 0l;
			try{
			shipmentIdLong = new Long(ids[i]);
			shipmentIds.add(shipmentIdLong);
			}catch(Exception e){
				log.error("Error in converting the shipment id :"+ids[i]);
			}
		}
		/*for(int i=0; i< ids.length; i++){
			for(ShippingOrder shippingOrderList : this.getSelectedShipments()){
				if(ids!=null && !ids.equals("") && shippingOrderList.getId().equals(new Long(ids[i]))){
					shipmentIds.add(shippingOrderList.getId());
				}
			}
		}*/
		/*for ( int i = 0; i < select.size(); i++ ) {
		    // If this checkbox was selected:
		    if ( select.get(i) != null && select.get(i) ) {
		    	// Get the matching test scenario:
		    	ShippingOrder so = this.getSelectedShipments().get(i);
		    	// ...and launch it:
		    	shipmentIds.add(so.getId());
		    } 		
		}	*/		
//		this.shippingService.assignCustomerToShipments(shipmentIds, this.getShippingOrder().getCustomerId());
				long customerId = Long.parseLong(request.getParameter("customerId"));
			//	this.getShippingOrder().setCustomerId(customerId);
				this.shippingService.assignCustomerToShipments(shipmentIds, customerId);
				addActionMessage(getText("shippingOrder.assign.successfully"));
	}

	public String assignCustomerToUnlinkedShipments(){
		try {
			assignShipments();
	    } catch (Exception e) {
	    	e.printStackTrace();
	    	addActionError(getText("content.error.unexpected"));
		}

		return unlinkedShipment();
	}
	
	public String reassignCustomerToAutolinkedShipments(){
		try {
			assignShipments();
	    } catch (Exception e) {
	    	e.printStackTrace();
	    	addActionError(getText("content.error.unexpected"));
		}
		
		return autolinkedShipment();
	}	

	public String acceptShipments(){
		try {
			List<Long> shipmentIds = new ArrayList<Long>();
			
			  String[] ids = null;
			   if(shipmentIdList!=null && shipmentIdList.size()>0){
			    String firstValue = shipmentIdList.get(0);
			    ids = firstValue.split(","); 
			   }
			   for(int i=0;i<ids.length;i++){
			    long shipmentIdLong = 0l;
			    try{
			    shipmentIdLong = new Long(ids[i]);
			    shipmentIds.add(shipmentIdLong);
			    }catch(Exception e){
			     log.error("Error in converting the shipment id :"+ids[i]);
			    }
			   }
			
			/*for ( int i = 0; i < select.size(); i++ ) {
			    // If this checkbox was selected:
			    if ( select.get(i) != null && select.get(i) ) {
			    	// Get the matching test scenario:
			    	ShippingOrder so = this.getSelectedShipments().get(i);
			    	// ...and launch it:
			    	shipmentIds.add(so.getId());
			    } 		
			}		*/	
			this.shippingService.setShipmentsReadyForInvoice(shipmentIds);

	    } catch (Exception e) {
	    	e.printStackTrace();
	    	addActionError(getText("content.error.unexpected"));
		}
		
		return autolinkedShipment();
	}
	
	public List<ShippingOrder> getSelectedShipments() {
		return selectedShipments;
	}

	public void setSelectedShipments(List<ShippingOrder> selectedShipments) {
		this.selectedShipments = selectedShipments;
	}

	public List<Boolean> getSelect() {
		return select;
	}

	public void setSelect(List<Boolean> select) {
		this.select = select;
	}
	
	public String viewShipment() {
	    try {
	      log.debug("Inside----------------------viewShipment()---------------------");
	      String shipmentId = request.getParameter("viewShipmentId");
	      String ltLState = request.getParameter("ltLState");
	      	      if(ltLState != null){
	      	      ltLState = ltLState.replace("'", "");
	      	      }
	      ShippingOrder selectedOrder;

	      if (ltLState != null && !ltLState.isEmpty()) {
	    	  	    	  boolean isLTL = Boolean.parseBoolean(ltLState);
	    	  	    	  if(isLTL)
	    	  	    	  {
	    	  	    		  request.setAttribute("isLTL", "1");
	    	  //	    		  getSession().put("isLTL", "1");
	    	  	    	  }
	    	  	      }
	      if (shipmentId != null && !shipmentId.isEmpty()) {
	        long id = Long.valueOf(shipmentId);
	        selectedOrder = this.shippingService.getShippingOrder(id);
	        if (selectedOrder != null) {
	          List<Package> packages = shippingService.getShippingPackages(id);
	          float insuranceValue = 0.0f;
	          if (packages != null) {
	            for (Package pack : packages) {
	              if (pack.getInsuranceAmount() != null) {
	               insuranceValue +=pack.getInsuranceAmount().floatValue();
	              }
	            }
	            selectedOrder.setInsuranceValue(insuranceValue);
	          }
	          this.setSelectedOrder(selectedOrder);
	        }
	        if (this.getSelectedOrder() != null && this.getSelectedOrder().getCustomer() != null
	            && this.getSelectedOrder().getCustomer().getPrimaryWarehouse() != null) {
	          request.setAttribute("def_wh", this.getSelectedOrder().getCustomer()
	              .getPrimaryWarehouse().getWarehouseName());
	        }
	        // populate the warehouse products for the order if its a warehouse order
	        long customerId = 0l;
	        if (this.getSelectedOrder() != null && this.getSelectedOrder().getCustomerId() != null) {
	          customerId = this.getSelectedOrder().getCustomerId();
	        }
	        this.getSelectedOrder().setWarehouseProducts(
	            populateOrderWarehouseProducts(Long.valueOf(shipmentId), customerId));

	        if (request.getParameter("notrackurl") != null)
	          request.setAttribute("notrackurl", "true");
	      }
	      // else is executed for a new shipment.
	      else {
	        this.setSelectedOrder(this.getShippingOrder());
	        if (this.getSelectedOrder().getCustomer()!=null && this.getSelectedOrder().getCustomer().getPrimaryWarehouse() != null)
	          request.setAttribute("def_wh", this.getSelectedOrder().getCustomer()
	              .getPrimaryWarehouse().getWarehouseName());
	        // set an attribute to avoid loading of the label popup.
	        request.setAttribute("nopopup", "true");
	        session.put("AutoPrintAgain",true);
	      }
	      // set provinces:
	      String countryCode = "";
	      if (this.getSelectedOrder() != null && this.getSelectedOrder().getCustomer() != null
	          && this.getSelectedOrder().getCustomer().getAddress() != null) {
	        countryCode = this.getSelectedOrder().getCustomer().getAddress().getCountryCode();
	      }
	      provinces = this.addressService.getProvinces(countryCode);
	      getSession().put("provinces", provinces);
	      if (this.getSelectedOrder() != null
	          && this.getSelectedOrder().getStatusId() != null
	          && this.getSelectedOrder().getStatusId().longValue() == ShiplinxConstants.STATUS_EXCEPTION
	          && !StringUtil.isEmpty(this.getSelectedOrder().getMessage())) {
	        addActionError(getText("shipment.failed.following.error"));
	        StringTokenizer st = new StringTokenizer(this.getSelectedOrder().getMessage(), "|");
	        while (st.hasMoreTokens()) {
	          addActionError(st.nextToken().trim());
	        }

	      }
	      // setting all the shipping status in the list
	      // if (this.getOrderStatusList() == null) {
	      this.setOrderStatusList(this.shippingService.getShippingOrdersAllStatus());
	      // }
	      if (this.getSelectedOrder().getStatusId() != null)
	        this.setOrderStatusOptionsList(this.shippingService.getShippingOrdersStatusOptions(this
	            .getSelectedOrder().getStatusId()));

	      /*
	       * //Get the Products added to the order and inject
	       * this.getSelectedOrder().setWarehouseProducts(getShippingOrder().getWarehouseProducts());
	       * List<Products> prodlist1 = this.getSelectedOrder().getWarehouseProducts();
	       * this.getSelectedOrder
	       * ().setWarehouseProducts(this.getSelectedOrder().getWarehouseProducts()); List<Products>
	       * prodlist2 = this.getSelectedOrder().getWarehouseProducts();
	       */

	      if (this.getSelectedOrder().getId() != null) {
	        LoggedEvent loggedEvent = new LoggedEvent();
	        loggedEvent.setEntityId(Long.valueOf(this.getSelectedOrder().getId()));
	        loggedEvent.setEntityType(Long.valueOf(ShiplinxConstants.ENTITY_TYPE_ORDER_VALUE));
	        if (UserUtil.getMmrUser().getUserRole().equals("busadmin")||UserUtil.getMmrUser().getUserRole().equals("solutions_manager")) {
	        	loggedList = loggedEventService.getLoggedEventInfo(loggedEvent, true);
	        } else {
	          
	          loggedEvent.setPrivateMessage(false);
	          loggedEvent.setDeletedMessage(false);
	          loggedList = loggedEventService.getLoggedEventInfo(loggedEvent, false);
	        }
	      }

	      User user = userService.findUserByUsername(UserUtil.getMmrUser().getUsername());
	      // setting the print config values for the logged user
	      request.setAttribute("no_of_lbls", user.getPrintNoOfLabels());
	      request.setAttribute("no_of_ci", user.getPrintNoOfCI());
	      request.setAttribute("autoprint", user.isAutoPrint());
	      currencyList=shippingService.getallCurrencySymbol();
	      
	      ///=============== Exchange Rate ==================
	      ShippingOrder orderTemp=new ShippingOrder();
	      	      // Apply Default Currency Excluded admin
	      	      CurrencySymbol currencySymbol = new CurrencySymbol();
	      	      if(UserUtil.getMmrUser()!=null && UserUtil.getMmrUser().getCustomerId()>0){
	      	    	  orderTemp=applyDefaultCurrencyValue(this.getSelectedOrder());
	      	    	  this.setSelectedOrder(orderTemp);
	      	    	  if(this.getSelectedOrder().getCustomer().getDefaultCurrency()!=null && !this.getSelectedOrder().getCustomer().getDefaultCurrency().isEmpty()){
	      	    	   currencySymbol = shippingService.getSymbolByCurrencycode(this.getSelectedOrder().getCustomer().getDefaultCurrency());
	      	    	  if(currencySymbol!=null){
	      	    	  getSession().put("DefaultCurrencySymbol", currencySymbol.getCurrencySymbol());
	      	    	  }else{
	      	    		  getSession().put("DefaultCurrencySymbol", "$");
	      	    	   }
	      	    	 }else{
	      	    		 currencySymbol = shippingService.getCurrencyCodeByCountryName(user.getLocale().substring(3,5));
	      	   	      	  if(currencySymbol==null){
	      	   	      		  for(int i=0;i<ShiplinxConstants.EURO_UNION_LIST.length;i++){
	      	   	      			  if(user.getLocale().substring(3, 5).equalsIgnoreCase(ShiplinxConstants.EURO_UNION_LIST[i])){
	      	   	      				currencySymbol=shippingService.getCurrencyCodeByCountryName("EUCG");
	      	   	      				break;
	      	   	      			  }
	      	   	      		  }
	      	   	      	  }
	      	    		 if(currencySymbol!=null){
	      	   	    	  getSession().put("DefaultCurrencySymbol", currencySymbol.getCurrencySymbol());
	      	    		 }
	      	    	 }
	      	      }else{
	      	    	  getSession().put("DefaultCurrencySymbol", "$");
	      	      }
	      	      /// ============== End =============================
	    } catch (Exception e) {
	      e.printStackTrace();
	      addActionError(getText("content.error.unexpected"));
	    }
	    initCarrierList();
	    return SUCCESS;
	  }


		  public String processPayment() {
		    ShippingOrder selectedOrder = this.getSelectedOrder();
		    CCTransaction transaction = null;

		    if (selectedOrder.getId() != null && selectedOrder.getId() > 0) { // this is a post-payment
		      transaction = shippingService.processPayment(selectedOrder, null);
		      selectedOrder.getCcTransactions().add(transaction);

		    } else { // payment is part of the shipping process
		      try {
		        carrierServiceManager.shipOrder(selectedOrder, selectedOrder.getRateList().get(0));
		        transaction = selectedOrder.getCcTransactions().get(0);
		      } catch (CardProcessingException cpe) { // this is if card could not be authorized
		        addActionError(getText("creditCard.payment.notProcessed") + " " + cpe.getMessage());
		        // go back to pay/details page
		        return SUCCESS;
		      } catch (Exception e) {
		        log.debug("Shipping Error!", e);
		        addActionError(getText("shippingOrder.save.error"));
		        // Get the error messages returned from the carriers
		        for (CarrierErrorMessage carrierErrorMessage : carrierServiceManager.getErrorMessages()) {
		          addActionError(carrierErrorMessage.getMessage());
		        }
		        return ERROR;
		      }

		      if (carrierServiceManager.getErrorMessages().size() == 0)
		        addActionMessage(getText("shippingOrder.save.successfully"));

		    }

		    if (transaction == null || transaction.getStatus() == CCTransaction.VOID_STATUS)
		      addActionError(getText("creditCard.payment.notProcessed"));
		    else if (transaction.getStatus() == CCTransaction.PROCESSED_STATUS)
		      addActionMessage(getText("creditCard.payment.processed"));
		    else
		      addActionError(getText("creditCard.payment.notProcessed") + ". Message: "
		          + transaction.getProcMessage());

		    return SUCCESS;
		  }

		  public String addActualCharge() {
			    try {
			      Double totalActualCharge = 0.0;
			      Double totalActualCost = 0.0;
			      shippingDAO = (ShippingDAO) MmrBeanLocator.getInstance().findBean("shippingDAO");
			      long carrierChargeCodeId=Long.valueOf(request.getParameter("carrierChargeCodeId").toString());
			      Charge newCharge = this.getNewActualCharge();
			      CarrierChargeCode carrierChargeCode = shippingDAO.getChargeCodeById(carrierChargeCodeId);
			      CurrencySymbol costCurrency=new CurrencySymbol();
			      CurrencySymbol chargeCurrency=new CurrencySymbol();
			      List<CurrencySymbol> allCurrency =new ArrayList<CurrencySymbol>();
			      currencyList=shippingDAO.getallCurrencySymbol();
			      for(CurrencySymbol currencySymbol:currencyList){
			    	  if(newCharge.getCostcurrency()>0 && newCharge.getCostcurrency()==currencySymbol.getId()){
			    		  costCurrency=currencySymbol;
			    	  }
			    	  if(newCharge.getChargecurrency()>0 && newCharge.getChargecurrency()==currencySymbol.getId()){
			    		  chargeCurrency=currencySymbol;
			    	  }
			      }
			      Double exchangeRate=null;
			      if(costCurrency.getCurrencyCode()!=null && chargeCurrency.getCurrencyCode()!=null){
			    	  if(!costCurrency.getCurrencyCode().equalsIgnoreCase(chargeCurrency.getCurrencyCode())){
			    		  			    		  exchangeRate=shippingDAO.getExchangeRate(costCurrency.getCurrencyCode(), chargeCurrency.getCurrencyCode());
			    		  			    	  }else{
			    		  			    		  exchangeRate=1d;
			    		  			    	  }
			      }
			      if(exchangeRate!=null){
			    	  BigDecimal exchRate=new BigDecimal(exchangeRate);
			    	  newCharge.setExchangerate(exchRate);
			      }
			      if (newCharge != null) {
			        carrierChargesList = this.getShippingService().getChargeListByCarrierAndCodes(
			            newCharge.getCarrierId(), null, null);
			        CarrierChargeCode carrierCharge = getCarrierCharge(newCharge.getChargeId());
			        if (carrierCharge != null) {
			          carrierServiceDAO = (CarrierServiceDAO) MmrBeanLocator.getInstance().findBean(
			              "carrierServiceDAO");
			          carrierServiceDAO = (CarrierServiceDAO) MmrBeanLocator.getInstance().findBean(
			              "carrierServiceDAO");
			          Carrier chargeCarrier = carrierServiceDAO.getCarrier(newCharge.getCarrierId());
			          newCharge.setCarrierName(chargeCarrier.getName());
			          newCharge.setChargeCode(carrierCharge.getChargeCode());
			          newCharge.setOrderId(this.getSelectedOrder().getId());
			          newCharge.setChargeCodeLevel2(carrierCharge.getChargeCodeLevel2());
			          newCharge.setName(carrierCharge.getChargeName());
			          newCharge.setCurrency(this.getSelectedOrder().getCurrency());
			          newCharge.setType(ShiplinxConstants.CHARGE_TYPE_ACTUAL);
			          newCharge.setTariffRate(new Double(0.0));
			          newCharge.setChargeGroupId((int)carrierChargeCode.getGroupId());
			          // newCharge.setStatus(ShiplinxConstants.CHARGE_READY_TO_INVOICE);
			          newCharge.setDiscountAmount(new Double(0.0));
			          newCharge.setId(this.getShippingService().saveCharge(newCharge));
			          this.getSelectedOrder().getCharges().add(newCharge);
			          ShippingOrder order = new ShippingOrder();
			          if(this.getSelectedOrder().getCharges()!=null ){
			          for (int i = 0; i < this.getSelectedOrder().getCharges().size(); i++) {
			            if (this.getSelectedOrder().getCharges().get(i).getType() == 1) {
			              totalActualCharge += this.getSelectedOrder().getCharges().get(i).getCharge();
			              totalActualCost += this.getSelectedOrder().getCharges().get(i).getCost();
			            }
			          }
			          }
			          DecimalFormat round = new DecimalFormat("###.##");
			          order.setId(this.getSelectedOrder().getId());
			         // order.setActualTotalCost(Double.valueOf(round.format(totalActualCost)));
			          order.setActualTotalCharge(Double.valueOf(round.format(totalActualCharge)));
			          shippingDAO.updateTotalCharges(order);
			          getSession().remove("newActualCharge");
			          // this.setNewQuotedCharge(newCharge);
			          log.debug("Add Actual Charge");
			          log.debug("Charge");
			          log.debug(newCharge.getCharge());
			          log.debug("Charge Id");
			          log.debug(newCharge.getId());
			          this.getNewActualCharge();

			          addActionMessage(getText("charge.added.successfully"));
			        }
			      }
			    } catch (Exception e) {
			      e.printStackTrace();
			      addActionError(getText("content.error.unexpected"));
			    }

			    return SUCCESS;
			  }

			  public String copyToActualCharges() {
			    Double totalActualCharge = 0.0;
			    Double totalActualCost = 0.0;
			    Double totalQuoteCharge = 0.0;
			    Double totalQuoteCost = 0.0;
			    shippingDAO = (ShippingDAO) MmrBeanLocator.getInstance().findBean("shippingDAO");
			    log.debug("Inside copyToActualCharges method...");
			    ShippingOrder so = this.getSelectedOrder();
			    String chargeStatusText = this.request.getParameter("quotedChargeStatusText");
			    String actualStatusText = this.request.getParameter("actualChargeStatusText");
			    List<Charge> quotedToActualChargesList = new ArrayList<Charge>();
			    List<Charge> quotedChargesList = new ArrayList<Charge>();
			    String[] actualChargeIds = this.request.getParameterValues("actualChargeIds");
			    String[] ids = this.request.getParameterValues("quotedChargeIds");

			    if (chargeStatusText != null && !chargeStatusText.equals("")) {
			      try {
			        quotedToActualChargesList = so.getQuotedCharges();

			        for (Charge c : so.getQuotedCharges()) {
			          Charge newCharge = this.getNewActualCharge();
			          if (newCharge != null) {
			        	  //if(c.getChargeCode()==null){
			        		  			            	Charge charge = shippingDAO.getChargeById(c.getId());
			        		  			            	if(charge!=null && charge.getChargeGroupId()>0){
			        		  			            	ChargeGroup chargeGroup=shippingDAO.getChargeGroup(charge.getChargeGroupId());
			        		  			            	if(chargeGroup!=null && chargeGroup.isTax()){
			        		  			            		c.setChargeCode("TAX");
			        		  			            		c.setChargeGroupId((int)chargeGroup.getGroupId());
			        		  			            	}
			        		  			            }
			        		  			            	//}
			            newCharge.setChargeCode(c.getChargeCode());
			            newCharge.setChargeCodeLevel2(c.getChargeCodeLevel2());
			            newCharge.setName(c.getName());
			            newCharge.setCurrency(this.getSelectedOrder().getCurrency());
			            newCharge.setOrderId(this.getSelectedOrder().getId());
			            newCharge.setCostcurrency(c.getCostcurrency());
			            newCharge.setChargecurrency(c.getChargecurrency());
			            newCharge.setExchangerate(c.getExchangerate());
			            // set the status to "Ready to Invoice" if the selected status is "Quick Invoice"
			            if (chargeStatusText.equalsIgnoreCase(ShiplinxConstants.CHARGE_QUICK_INVOICE))
			              newCharge.setStatusText(ShiplinxConstants.CHARGE_STATUS_TEXT[2]);
			            else
			              newCharge.setStatusText(chargeStatusText);
			            newCharge.setCost(c.getCost());
			            newCharge.setCharge(c.getCharge());
			            newCharge.setDiscountAmount(c.getDiscountAmount());
			            newCharge.setQuoteChargeId(c.getId());
			            newCharge.setCarrierName(c.getCarrierName());
			            newCharge.setCarrierId(c.getCarrierId());
			            newCharge.setTariffRate(c.getTariffRate());
			            newCharge.setType(ShiplinxConstants.CHARGE_TYPE_ACTUAL);
			            newCharge.setCostcurrency(c.getCostcurrency());
			            newCharge.setChargecurrency(c.getChargecurrency());
			            newCharge.setExchangerate(c.getExchangerate());
			            newCharge.setChargeGroupId(c.getChargeGroupId());
			            newCharge.setId(this.getShippingService().saveCharge(newCharge));
			            // this.getShippingService().saveCharge(newCharge);
			            this.getSelectedOrder().getCharges().add(newCharge);
			            getSession().remove("newActualCharge");
			            this.getNewActualCharge();
			          }
			        }

			        if (chargeStatusText.equalsIgnoreCase(ShiplinxConstants.CHARGE_QUICK_INVOICE)) {
			          // create Invoice logic
			          List<ShippingOrder> shippingOrderList = new ArrayList<ShippingOrder>();
			          shippingOrderList.add(this.getSelectedOrder());
			          Invoice i = invoiceManagerService.createInvoice(shippingOrderList, new Invoice(),
			              so.getCustomerId(), so.getCurrency());
			          if (i != null) {
			            // set the invoice num
			            for (Charge c : so.getCharges()) {
			              c.setInvoiceNum(i.getInvoiceNum());
			            }
			            addActionMessage(MessageUtil.getMessage("invoice.order.generated") + " "
			                + String.valueOf(this.getSelectedOrder().getId()));
			          } else
			            addActionError(MessageUtil.getMessage("invoice.order.generation.fail"));
			        } else
			          addActionMessage(MessageUtil.getMessage("charges.copied.actual"));

			      } catch (Exception e) {
			        log.error("Error Occured while copying to Actual Charges", e);
			        addActionError(getText("content.error.unexpected"));
			      }
			    } else if (actualStatusText != null) {
			      List<Charge> actualChargesList = so.getActualCharges();
			      List<Charge> chargesList = so.getQuotedCharges();
			      int statusCode = 0;
			      if (actualStatusText != null && !actualStatusText.isEmpty()) {
			        if (actualStatusText.equalsIgnoreCase(ShiplinxConstants.CHARGE_STATUS_TEXT[1]))
			          statusCode = ShiplinxConstants.CHARGE_PENDING_RELEASE;
			        else if (actualStatusText.equalsIgnoreCase(ShiplinxConstants.CHARGE_STATUS_TEXT[2]))
			          statusCode = ShiplinxConstants.CHARGE_READY_TO_INVOICE;
			        else if (actualStatusText.equalsIgnoreCase(ShiplinxConstants.CHARGE_STATUS_TEXT[3]))
			          statusCode = ShiplinxConstants.CHARGE_INVOICED;
			        else if (actualStatusText.equalsIgnoreCase(ShiplinxConstants.CHARGE_STATUS_TEXT[4]))
			          statusCode = ShiplinxConstants.CHARGE_CANCELLED;
			        else
			          statusCode = ShiplinxConstants.CHARGE_QUOTED;
			      } else
			        statusCode = ShiplinxConstants.CHARGE_QUOTED;
			      for (int quoteCharge = 0; quoteCharge < chargesList.size(); quoteCharge++) {
			        try {
			          Charge quotedCharge = chargesList.get(quoteCharge);
			          int count = 0;
			          for (int actualCharge = 0; actualCharge < actualChargesList.size(); actualCharge++) {
			            Charge actualCharges = actualChargesList.get(actualCharge);
			            if ((statusCode == quotedCharge.getStatus())
			                && !(quotedCharge.getChargeCode().equalsIgnoreCase(actualCharges.getChargeCode()) && (quotedCharge
			                    .getCarrierName() != null && quotedCharge.getCarrierName().equalsIgnoreCase(
			                    actualCharges.getCarrierName())))
			                && !(quotedCharge.getName().equalsIgnoreCase(actualCharges.getName()) && (quotedCharge
			                    .getCarrierName() != null && quotedCharge.getCarrierName().equalsIgnoreCase(
			                    actualCharges.getCarrierName())))
			                && (quotedCharge.getCost() != actualCharges.getCost())
			                && (quotedCharge.getCharge() != actualCharges.getCharge())) {
			              count++;
			            } else if (statusCode == quotedCharge.getStatus()
		                && statusCode == ShiplinxConstants.CHARGE_CANCELLED) {
		                count++;
			            }
			            
			          }
			          if (count == actualChargesList.size()) {
			            Charge newCharge = new Charge();
			            if(quotedCharge.getChargeCode()==null){
			            				            	Charge charge = shippingDAO.getChargeById(quotedCharge.getId());
			            				            	ChargeGroup chargeGroup=shippingDAO.getChargeGroup(charge.getChargeGroupId());
			            				            	if(chargeGroup!=null && chargeGroup.isTax()){
			            				            		quotedCharge.setChargeCode("TAX");
			            				            		quotedCharge.setChargeGroupId((int)chargeGroup.getGroupId());
			            				            	}
			            				            }
			            newCharge.setChargeCode(quotedCharge.getChargeCode());
			            newCharge.setChargeCodeLevel2(quotedCharge.getChargeCodeLevel2());
			            newCharge.setName(quotedCharge.getName());
			            newCharge.setCurrency(this.getSelectedOrder().getCurrency());
			            newCharge.setOrderId(this.getSelectedOrder().getId());
			            
			            // set the status to "Ready to Invoice" if the selected status is "Quick Invoice"
			            newCharge.setQuoteChargeId(quotedCharge.getId());
			            newCharge.setStatusText(actualStatusText);
			            newCharge.setCost(quotedCharge.getCost());
			            newCharge.setCharge(quotedCharge.getCharge());
			            newCharge.setCarrierId(quotedCharge.getCarrierId());
			            newCharge.setCarrierName(quotedCharge.getCarrierName());
			            newCharge.setQuoteChargeId(quotedCharge.getId());
			            newCharge.setDiscountAmount(quotedCharge.getDiscountAmount());
			            newCharge.setCostcurrency(quotedCharge.getCostcurrency());
				        newCharge.setChargecurrency(quotedCharge.getChargecurrency());
				        newCharge.setExchangerate(quotedCharge.getExchangerate());
				        newCharge.setChargeGroupId(quotedCharge.getChargeGroupId());

			            newCharge.setType(ShiplinxConstants.CHARGE_TYPE_ACTUAL);
			            newCharge.setStatus(ShiplinxConstants.CHARGE_READY_TO_INVOICE);
			            newCharge.setId(this.getShippingService().saveCharge(newCharge));

			            this.getSelectedOrder().getCharges().add(newCharge);
			          }
			        } catch (Exception e) {
			          log.error("Error in Copying the charge : " + e.getMessage());
			        }

			      }
			      int chargeUpdateCount = 0;
			      int status = 20;
			      if (actualStatusText != null && !actualStatusText.isEmpty()) {
			        if (actualStatusText.equalsIgnoreCase(ShiplinxConstants.CHARGE_STATUS_TEXT[2]))
			          status = ShiplinxConstants.CHARGE_READY_TO_INVOICE;
			        else if (actualStatusText.equalsIgnoreCase(ShiplinxConstants.CHARGE_STATUS_TEXT[4]))
			          status = ShiplinxConstants.CHARGE_CANCELLED;
			        else
			          status = ShiplinxConstants.CHARGE_PENDING_RELEASE;
			      }
			      if (actualChargeIds != null) {
			    	  for (int chargeIdIterate = 0; chargeIdIterate < actualChargeIds.length; chargeIdIterate++) {
			    		  Charge actualCharge = shippingService.getChargeById(Long
			    				  .valueOf(actualChargeIds[chargeIdIterate]));

			    		  if (actualCharge.getQuoteChargeId() > 0) {
			    			  for (int quoteChargeIterate = 0; quoteChargeIterate < ids.length; quoteChargeIterate++) {
			    				  Long quoteChargeId = Long.parseLong(ids[quoteChargeIterate]);
			    				  if (actualCharge.getQuoteChargeId() == quoteChargeId) {
					                Charge quote = shippingService.getChargeById(Long.valueOf(quoteChargeId));
					                actualCharge.setName(quote.getName());
					                actualCharge.setCharge(quote.getCharge());
					                actualCharge.setCost(quote.getCost());
					                actualCharge.setTariffRate(quote.getTariffRate());
					                actualCharge.setStatus(status);
					                actualCharge.setStatusText(actualStatusText);
					                actualCharge.setDiscountAmount(quote.getDiscountAmount());
					                actualCharge.setCarrierId(quote.getCarrierId());
					                actualCharge.setCarrierName(quote.getCarrierName());
					                actualCharge.setCostcurrency(quote.getCostcurrency());
					                actualCharge.setChargecurrency(quote.getChargecurrency());
					                actualCharge.setExchangerate(quote.getExchangerate());

					                this.getShippingService().updateCharge(actualCharge);
					                chargeUpdateCount++;
					              }

			            }
			          }
			    		  ShippingOrder curOrder = new ShippingOrder();
			    		  for (int i = 0; i < this.getSelectedOrder().getCharges().size(); i++) {
			    			  if (this.getSelectedOrder().getCharges().get(i).getType() == 1) {
			    				  totalQuoteCharge += this.getSelectedOrder().getCharges().get(i).getCharge();
			    				  totalQuoteCost += this.getSelectedOrder().getCharges().get(i).getCost();
			    			  }

			          }
			    		  DecimalFormat round = new DecimalFormat("###.##");
				          curOrder.setId(this.getSelectedOrder().getId());
				         // curOrder.setActualTotalCost(Double.valueOf(round.format(totalQuoteCost)));
				          curOrder.setActualTotalCharge(Double.valueOf(round.format(totalQuoteCharge)));
				          shippingDAO.updateTotalCharges(curOrder);

			        }
			      }
			      for (int i = 0; i < chargeUpdateCount; i++) {
			        addActionMessage(getText("ActualCharges.Updated.Successfully"));
			      }

			      addActionMessage("Copied the " + actualStatusText + " charges");
			    }
			    ShippingOrder order = new ShippingOrder();
			    for (int i = 0; i < this.getSelectedOrder().getCharges().size(); i++) {
			      if (this.getSelectedOrder().getCharges().get(i).getType() == 1) {
			        totalActualCharge += this.getSelectedOrder().getCharges().get(i).getCharge();
			        totalActualCost += this.getSelectedOrder().getCharges().get(i).getCost();
			      } else if (this.getSelectedOrder().getCharges().get(i).getType() == 0) {
			        totalQuoteCharge += this.getSelectedOrder().getCharges().get(i).getCharge();
			        totalQuoteCost += this.getSelectedOrder().getCharges().get(i).getCost();
			      }
			    }
			    DecimalFormat round = new DecimalFormat("###.##");
			    order.setId(this.getSelectedOrder().getId());
			   // order.setActualTotalCost(Double.valueOf(round.format(totalActualCost)));
			    order.setActualTotalCharge(Double.valueOf(round.format(totalActualCharge)));
			   // order.setQuoteTotalCost(Double.valueOf(round.format(totalQuoteCost)));
			    order.setQuoteTotalCharge(Double.valueOf(round.format(totalQuoteCharge)));
			    shippingDAO.updateTotalCharges(order);
			    return SUCCESS;
			  }

	
	public String clearExceptionStatus() {
		log.debug("Inside clearExceptionStatus method...");
		try {
			String statusId = request.getParameter("status_id");
			ShippingOrder so = this.getSelectedOrder();
			if (!StringUtil.isEmpty(statusId) && !statusId.equals("-1") && 
					so != null && so.getId() != null && so.getId().longValue() > 0) {
				so.setStatusIdFromString(statusId);
				shippingService.updateOrderStatus(so.getId(), so.getStatusId());
				getSession().remove("selectedOrder");
				ShippingOrder order = shippingService.getShippingOrder(so.getId());
				this.setSelectedOrder(order);
				addActionMessage(getText("shippingOrder.updated.successfully"));
			}
		} catch (Exception e) {
			log.error("Exception occured while clearing exception of the order: "+e);
			e.printStackTrace();
			addActionError(getText("shippingOrder.update.error"));
		}
		return SUCCESS;
	}	
	
	 public String addQuotedCharge() {
		    try {
		      Double totalQuoteCharge = 0.0;
		      Double totalQuoteCost = 0.0;
		      shippingDAO = (ShippingDAO) MmrBeanLocator.getInstance().findBean("shippingDAO");
		      long carrierChargeCodeId=Long.valueOf(request.getParameter("carrierChargeCodeId").toString());
		      Charge newCharge = this.getNewQuotedCharge();
		      CurrencySymbol costCurrency=new CurrencySymbol();
		      CurrencySymbol chargeCurrency=new CurrencySymbol();
		      List<CurrencySymbol> allCurrency =new ArrayList<CurrencySymbol>();
		      currencyList=shippingDAO.getallCurrencySymbol();
		      for(CurrencySymbol currencySymbol:currencyList){
		    	  if(newCharge.getCostcurrency()!=0 && newCharge.getCostcurrency()==currencySymbol.getId()){
		    		  costCurrency=currencySymbol;
		    	  }
		    	  if(newCharge.getChargecurrency()!=0 && newCharge.getChargecurrency()==currencySymbol.getId()){
		    		  chargeCurrency=currencySymbol;
		    	  }
		      }
		      Double exchangeRate=null;
		      if(costCurrency.getCurrencyCode()!=null && chargeCurrency.getCurrencyCode()!=null){
		    	  if(!costCurrency.getCurrencyCode().equalsIgnoreCase(chargeCurrency.getCurrencyCode())){
		    		  		    		  exchangeRate=shippingDAO.getExchangeRate(costCurrency.getCurrencyCode(), chargeCurrency.getCurrencyCode());
		    		  		    	  }else{
		    		  		    		  exchangeRate=1d;
		    		  		    	  }
		      }
		      if(exchangeRate!=null){
		    	  BigDecimal exchRate=new BigDecimal(exchangeRate);
		    	  newCharge.setExchangerate(exchRate);
		      }
		      CarrierChargeCode carrierChargeCode = shippingDAO.getChargeCodeById(carrierChargeCodeId);
		      if (newCharge != null) {
		        carrierChargesList = this.getShippingService().getChargeListByCarrierAndCodes(
		            newCharge.getCarrierId(), null, null);
		        CarrierChargeCode carrierCharge = getCarrierCharge(newCharge.getChargeId());
		        if (carrierCharge != null) {
		          carrierServiceDAO = (CarrierServiceDAO) MmrBeanLocator.getInstance().findBean(
		              "carrierServiceDAO");
		          shippingDAO = (ShippingDAO) MmrBeanLocator.getInstance().findBean("shippingDAO");
		          carrierServiceDAO = (CarrierServiceDAO) MmrBeanLocator.getInstance().findBean(
		              "carrierServiceDAO");
		          Carrier chargeCarrier = carrierServiceDAO.getCarrier(newCharge.getCarrierId());
		          newCharge.setCarrierName(chargeCarrier.getName());
		          newCharge.setChargeCode(carrierCharge.getChargeCode());
		          newCharge.setChargeCodeLevel2(carrierCharge.getChargeCodeLevel2());
		          newCharge.setName(carrierCharge.getChargeName());
		          newCharge.setCurrency(this.getSelectedOrder().getCurrency());
		          newCharge.setOrderId(this.getSelectedOrder().getId());
		          // newCharge.setStatus(ShiplinxConstants.CHARGE_READY_TO_INVOICE);
		          newCharge.setDiscountAmount(new Double(0.0));
		          newCharge.setTariffRate(new Double(0.0));
		          newCharge.setChargeGroupId((int)carrierChargeCode.getGroupId());
		          newCharge.setType(ShiplinxConstants.CHARGE_TYPE_QUOTED);
		          newCharge.setId(this.getShippingService().saveCharge(newCharge));
		          ShippingOrder order = new ShippingOrder();
		          this.getSelectedOrder().getCharges().add(newCharge);
		          // this.setNewQuotedCharge(newCharge);
		          for (int i = 0; i < this.getSelectedOrder().getCharges().size(); i++) {
		            if (this.getSelectedOrder().getCharges().get(i).getType() == 0) {
		              totalQuoteCharge += this.getSelectedOrder().getCharges().get(i).getCharge();
		              //totalQuoteCost += this.getSelectedOrder().getCharges().get(i).getCost();
		            }
		          }
		          DecimalFormat round = new DecimalFormat("###.##");
		          order.setCarrierId(this.getSelectedOrder().getCarrierId());
		          order.setId(this.getSelectedOrder().getId());
		         // order.setQuoteTotalCost(Double.valueOf(round.format(totalQuoteCost)));
		          order.setQuoteTotalCharge(Double.valueOf(round.format(totalQuoteCharge)));
		          shippingDAO.updateTotalCharges(order);
		          log.debug("Add Quoted Charge");
		          log.debug("Charge");
		          log.debug(newCharge.getCharge());
		          log.debug("Charge Id");
		          log.debug(newCharge.getId());
		          this.getNewQuotedCharge();
		          getSession().remove("newQuotedCharge");

		          addActionMessage(getText("charge.added.successfully"));
		        }
		      }
		    } catch (Exception e) {
		      e.printStackTrace();
		      addActionError(getText("content.error.unexpected"));
		    }

		    return SUCCESS;
		  }
	  public String listChargeCode() {
		    long carrierId = Long.parseLong(request.getParameter("carrierId"));
		    newChargeType = request.getParameter("newChargeType");
		    // long chargeCode=Long.parseLong(request.getParameter("id"));
		    log.debug("Search String is : " + carrierId);
		    ajaxCarreierChargeList = this.getShippingService().getChargeCodeListByCarrierId(carrierId);

		    return SUCCESS;
		  }

		  public String listChargeName() {
		    // String searchParameter = request.getParameter("id");
		    long carrierId = Long.parseLong(request.getParameter("carrierId"));
		    newChargeType = request.getParameter("newChargeType");
		    String chargeCode = request.getParameter("chargeCode");
		    log.debug("Search String is : " + carrierId);
		    ajaxCarreierChargeList = this.getShippingService().getChargeNameListByCarrierIdAndChargeCode(
		        carrierId, chargeCode);
		    for (CarrierChargeCode c : ajaxCarreierChargeList) {
		      carrierChargesSearchResult.put(c.getChargeName(), c.getId());
		    }
		    return SUCCESS;
		  }


	public String updateActualCharge(){
		try {
			ShippingOrder order = this.getSelectedOrder();
			shippingDAO = (ShippingDAO) MmrBeanLocator.getInstance().findBean("shippingDAO");
		      Double totalActualCharge = 0.0;
		      Double totalActualCost = 0.0;
			System.out.println(order);
			String newCurrency = this.request.getParameter("ordCurrency");
			if(newCurrency ==null || ("").equals(newCurrency)){
				newCurrency = order.getCurrency();
			}
			if (order != null) {
				 if (!order.getCurrency().equalsIgnoreCase(newCurrency)){
			           shippingDAO.updateShippingOrderCurrency(order.getId(), newCurrency);
			          }
				String [] ids = this.request.getParameterValues("actualChargeIds");
				String [] userCharges = this.request.getParameterValues("actualCharge");
				String [] userCosts = this.request.getParameterValues("actualChargeCost");
				String [] userNames = this.request.getParameterValues("actualChargeName");
				String [] userStatusTexts = this.request.getParameterValues("actualChargeStatusText");				
				String[] userEdiInvoiceNumber=this.request.getParameterValues("actualEdiInvoiceNumber");
				String[] costCurrency = this.request.getParameterValues("actualcostcurrency");
			    String[] chargeCurrency = this.request.getParameterValues("actualchargecurrency");
			    String[] exchangeRate = this.request.getParameterValues("actualexchangerate");
               
			           ///////////// =================== Exchange Rate ===============
			    CurrencySymbol costCurrencytmp=new CurrencySymbol();
			    			    CurrencySymbol chargeCurrencytmp=new CurrencySymbol();
			    			      List<CurrencySymbol> allCurrency =new ArrayList<CurrencySymbol>();
			    			      currencyList=shippingDAO.getallCurrencySymbol();
			    			      if (ids != null) {
			    			    	  for (int i = 0; i < ids.length; i++) {
			    			    		  costCurrencytmp=currencyList.get(Integer.parseInt(costCurrency[i])-1);
			    			    		  chargeCurrencytmp=currencyList.get(Integer.parseInt(chargeCurrency[i])-1);
			    			    		  Double exchangeRatetmp=null;
			    			    		  if(costCurrencytmp.getCurrencyCode()!=null && chargeCurrencytmp.getCurrencyCode()!=null){
			    			    			  if(!costCurrencytmp.getCurrencyCode().equalsIgnoreCase(chargeCurrencytmp.getCurrencyCode())){
			    			    				  exchangeRatetmp=shippingDAO.getExchangeRate(costCurrencytmp.getCurrencyCode(), chargeCurrencytmp.getCurrencyCode());
			    			    			  }
			    		    			  if(exchangeRatetmp==null){
			    			    				  exchangeRatetmp=1d;
			    			    			  }
			    			    			 // exchangeRate[i]=exchangeRatetmp.toString();
			    			    		  }
			    
			    //// ============== End =================
			            Long id = Long.parseLong(ids[i]);
			            Charge soCharge = getCharge(this.getSelectedOrder(), id);
			            if (soCharge != null) {
			              Double ch = 0.0;
			              if (userCharges[i] != null) {
			                ch = StringUtil.getDouble(userCharges[i]); // Double.parseDouble(userCharges[i]);
			              }
			              Double cost = 0.0;
			              if (userCosts[i] != null) {
			                cost = StringUtil.getDouble(userCosts[i]); // Double.parseDouble(userCosts[i]);
			              }
			              
			              String name = (userNames[i] == null ? "" : userNames[i]);
			              String statusText = (userStatusTexts[i + 1] == null ? "" : userStatusTexts[i + 1]);		 
			              String ediNumber="";
							 int costcurr = StringUtil.getInteger(costCurrency[i]);
				             int chargecurr = StringUtil.getInteger(chargeCurrency[i]);
				              BigDecimal exchangerate = StringUtil.getBigDecimal(exchangeRate[i]);
				              if (isChargeDirty(soCharge, ch, cost, name, statusText, ediNumber, costcurr,
				                  chargecurr, exchangerate)) {

			                this.getShippingService().updateCharge(soCharge);
			                addActionMessage(getText("charges.save.successfully"));
			              }

						}
					}
			          ShippingOrder curOrder = new ShippingOrder();
			          int count =0;
			          for (int i = 0; i < this.getSelectedOrder().getCharges().size(); i++) {
			            if (this.getSelectedOrder().getCharges().get(i).getType() == 1) {
			              totalActualCharge += this.getSelectedOrder().getCharges().get(i).getCharge();
			              totalActualCost += this.getSelectedOrder().getCharges().get(i).getCost();
			              if( userEdiInvoiceNumber[count]!=null && this.getSelectedOrder().getCharges().get(i).getEdiInvoiceNumber() !=null && !userEdiInvoiceNumber[count].isEmpty() && !this.getSelectedOrder().getCharges().get(i).getEdiInvoiceNumber().equals(userEdiInvoiceNumber[count])){
			            	  			            	  shippingDAO.updateEDI(userEdiInvoiceNumber[count],this.getSelectedOrder().getCharges().get(i).getId());
			            	  			              }else if(this.getSelectedOrder().getCharges().get(i).getEdiInvoiceNumber() == null && userEdiInvoiceNumber[count]!=null){
			            	  			            	  shippingDAO.updateEDI(userEdiInvoiceNumber[count],this.getSelectedOrder().getCharges().get(i).getId());
			            	  			              }
			            	  			              count++;
			            }
			          }
			          DecimalFormat round = new DecimalFormat("###.##");
			          curOrder.setId(this.getSelectedOrder().getId());
			         // curOrder.setActualTotalCost(Double.valueOf(round.format(totalActualCost)));
			          curOrder.setActualTotalCharge(Double.valueOf(round.format(totalActualCharge)));
			          shippingDAO.updateTotalCharges(curOrder);

				}else{
					int count =0;
					  for (int i = 0; i < this.getSelectedOrder().getCharges().size(); i++) {
				            if (this.getSelectedOrder().getCharges().get(i).getType() == 1) {
				              if( userEdiInvoiceNumber[count]!=null && this.getSelectedOrder().getCharges().get(i).getEdiInvoiceNumber() !=null && !userEdiInvoiceNumber[count].isEmpty() && !this.getSelectedOrder().getCharges().get(i).getEdiInvoiceNumber().equals(userEdiInvoiceNumber[count])){
				            	  			            	  shippingDAO.updateEDI(userEdiInvoiceNumber[count],this.getSelectedOrder().getCharges().get(i).getId());
				            	  			              }else if(this.getSelectedOrder().getCharges().get(i).getEdiInvoiceNumber() == null && userEdiInvoiceNumber[count]!=null){
				            	  			            	  shippingDAO.updateEDI(userEdiInvoiceNumber[count],this.getSelectedOrder().getCharges().get(i).getId());
				            	  			              }
				            	  			              count++;
				            }
				          }
				}
			}
	    } catch (Exception e) {
	    	e.printStackTrace();
	    	addActionError(getText("content.error.unexpected"));
		}
		
		return SUCCESS;
	}	
	
	public String updateQuotedCharge() {
	    try {
	        Double totalQuoteCharge = 0.0;
	        Double totalQuoteCost = 0.0;
	        ShippingOrder order = this.getSelectedOrder();
	        shippingDAO = (ShippingDAO) MmrBeanLocator.getInstance().findBean("shippingDAO");
	        System.out.println(order);
	        String newCurrency = this.request.getParameter("ordCurrency");
	        if (order != null) {
	        	 if (!order.getCurrency().equalsIgnoreCase(newCurrency)){
	                 shippingDAO.updateShippingOrderCurrency(order.getId(), newCurrency);
	           }
	          String[] ids = this.request.getParameterValues("quotedChargeIds");
	          String[] userCharges = this.request.getParameterValues("quotedCharge");
	          String[] userCosts = this.request.getParameterValues("quotedChargeCost");
	          String[] userNames = this.request.getParameterValues("quotedChargeName");
	          String[] userStatusTexts = this.request.getParameterValues("quotedChargeStatusText");
	          String[] costCurrency = this.request.getParameterValues("quotedcostcurrency");
	          String[] chargeCurrency = this.request.getParameterValues("quotedchargecurrency");
	          String[] exchangeRate = this.request.getParameterValues("quotedexchangerate");
	          // String[] actualChargeIds = this.request.getParameterValues("actualChargeIds");
	        /*  Map<String, Object> paramObj = new HashMap<String, Object>(5);
	          
	           for(int i=0;i<exchangeRate.length;i++){
	        	   paramObj.put(exchangeRate[i], userNames[i]);
	           }
	          */
	          List<String> list = new ArrayList<String>(Arrays.asList(userStatusTexts));
	          list.removeAll(Arrays.asList("", null));
	          CurrencySymbol costCurrencytmp=new CurrencySymbol();
	          	          CurrencySymbol chargeCurrencytmp=new CurrencySymbol();
	          	          List<CurrencySymbol> allCurrency =new ArrayList<CurrencySymbol>();
	          	          currencyList=shippingDAO.getallCurrencySymbol();
	          for (int i = 0; i < ids.length; i++) {
	            Long id = Long.parseLong(ids[i]);
	            Charge soCharge = getCharge(this.getSelectedOrder(), id);
	            /////// ================ Exchange Rate ==============================
	            costCurrencytmp=currencyList.get(Integer.parseInt(costCurrency[i])-1);
	            	        	  chargeCurrencytmp=currencyList.get(Integer.parseInt(chargeCurrency[i])-1);
	            	        	  Double exchangeRatetmp=null;
	            	        	  if(costCurrencytmp.getCurrencyCode()!=null && chargeCurrencytmp.getCurrencyCode()!=null){
	            	        		  if(!costCurrencytmp.getCurrencyCode().equalsIgnoreCase(chargeCurrencytmp.getCurrencyCode())){
	            	        			  exchangeRatetmp=shippingDAO.getExchangeRate(costCurrencytmp.getCurrencyCode(), chargeCurrencytmp.getCurrencyCode());
	            	        		  }
	            	        		  if(exchangeRatetmp==null){
	            	        			  exchangeRatetmp=1d;
	            	        		  }
	            	        		 // exchangeRate[i]=exchangeRatetmp.toString();
	            	        	  }
	           ///////// ===================== End ====================
	            if (soCharge != null) {
	              Double ch = StringUtil.getDouble(userCharges[i]); // Double.parseDouble(userCharges[i]);
	              Double cost = StringUtil.getDouble(userCosts[i]); // Double.parseDouble(userCosts[i]);
	              String name = userNames[i];
	              String statusText = list.get(i);
	              int costcurr = Integer.parseInt(costCurrency[i]);
	              int chargecurr = Integer.parseInt(chargeCurrency[i]);
	              BigDecimal exchangerate = StringUtil.getBigDecimal(exchangeRate[i]);
		            if (isChargeDirty(soCharge, ch, cost, name, statusText, null, costcurr, chargecurr,
		                exchangerate)) {
	                this.getShippingService().updateCharge(soCharge);
	                addActionMessage(getText("charges.save.successfully"));
	              }
	            }
	          }
	          /*
	           * if (actualChargeIds != null) { for (int chargeIdIterate = 0; chargeIdIterate <
	           * actualChargeIds.length; chargeIdIterate++) { Charge actualCharge =
	           * shippingService.getChargeById(Long .valueOf(actualChargeIds[chargeIdIterate])); if
	           * (actualCharge.getQuoteChargeId() > 0) { for (int quoteChargeIterate = 0;
	           * quoteChargeIterate < ids.length; quoteChargeIterate++) { Long quoteChargeId =
	           * Long.parseLong(ids[quoteChargeIterate]); if (actualCharge.getQuoteChargeId() ==
	           * quoteChargeId) { Charge quoteCharge =
	           * shippingService.getChargeById(Long.valueOf(quoteChargeId));
	           * actualCharge.setName(quoteCharge.getName());
	           * actualCharge.setCharge(quoteCharge.getCharge());
	           * actualCharge.setCost(quoteCharge.getCost());
	           * actualCharge.setTariffRate(quoteCharge.getTariffRate());
	           * actualCharge.setStatus(quoteCharge.getStatus());
	           * actualCharge.setDiscountAmount(quoteCharge.getDiscountAmount());
	           * actualCharge.setCarrierId(quoteCharge.getCarrierId());
	           * actualCharge.setCarrierName(quoteCharge.getCarrierName());
	           * this.getShippingService().updateCharge(actualCharge);
	           * addActionMessage(getText("ActualCharges.Updated.Successfully")); } } } } } ShippingOrder
	           * curOrder = new ShippingOrder();
	           */
	          /*
	           * for (int i = 0; i < this.getSelectedOrder().getCharges().size(); i++) { if
	           * (this.getSelectedOrder().getCharges().get(i).getType() == 1) { totalQuoteCharge +=
	           * this.getSelectedOrder().getCharges().get(i).getCharge(); totalQuoteCost +=
	           * this.getSelectedOrder().getCharges().get(i).getCost(); } }
	           */
	          /*
	           * DecimalFormat round = new DecimalFormat("###.##");
	           * curOrder.setId(this.getSelectedOrder().getId());
	           * curOrder.setActualTotalCost(Double.valueOf(round.format(totalQuoteCost)));
	           * curOrder.setActualTotalCharge(Double.valueOf(round.format(totalQuoteCharge)));
	           * shippingDAO.updateTotalCharges(curOrder);
	           */
	        }
	      } catch (Exception e) {
	        e.printStackTrace();
	        addActionError(getText("content.error.unexpected"));
	      }

	      return SUCCESS;
	    }

		  private Charge getCharge(ShippingOrder so, Long id) {
		    // TODO Auto-generated method stub
		    for (Charge c : so.getCharges()) {
		      if (c.getId().longValue() == id.longValue())
		        return c;
		    }
		    return null;
		  }


		  private boolean isChargeDirty(Charge soCharge, Double charge, Double cost, String name,
				  String statusText, String ediNumber, int costcurr, int chargecurr, BigDecimal exchangerate) {
		// TODO Auto-generated method stub
		boolean isDirty = false;
		if (!soCharge.getName().equalsIgnoreCase(name)) {
			isDirty = true;
			soCharge.setName(name);
		}
		if ( !soCharge.getStatusText().equals(statusText) ) {
			isDirty = true;
			soCharge.setStatusText(statusText);
		}
		if ((soCharge.getCharge() == null && charge != null) ||
			(charge != null && soCharge.getCharge().doubleValue() != charge.doubleValue())	) {
			isDirty = true;
			soCharge.setCharge(charge);			
		}
//		if (soCharge.getCharge() != null && charge != null && 
//				soCharge.getCharge().doubleValue() != charge.doubleValue()) {
//			isDirty = true;
//			soCharge.setCharge(charge);
//		}
		if ((soCharge.getCost() == null && cost != null) || 
			(cost != null && soCharge.getCost().doubleValue() != cost.doubleValue()) ) {
			isDirty = true;
			soCharge.setCost(cost);
		}	
		 if (costcurr != 0) {
		      isDirty = true;
		      soCharge.setCostcurrency(costcurr);
		    }
		    if (costcurr != 0) {
		      isDirty = true;
		      soCharge.setCostcurrency(costcurr);
		    }
		    if (chargecurr != 0) {
		      isDirty = true;
		      soCharge.setChargecurrency(chargecurr);
		    }

		    if (exchangerate != null && !exchangerate.equals(BigDecimal.ZERO)) {
		      isDirty = true;
		      soCharge.setExchangerate(exchangerate);
		    }

		 if(soCharge.getEdiInvoiceNumber()==null){
			     	soCharge.setEdiInvoiceNumber("");
			     }
			     boolean ediNumberChanged=false;
			     if ( ediNumber!=null && !soCharge.getEdiInvoiceNumber().equals(ediNumber)) {
			         isDirty = true;
			         soCharge.setEdiInvoiceNumber(ediNumber);
			         ediNumberChanged=true;
			       }
			     if(ediNumberChanged==false &&soCharge.getEdiInvoiceNumber().equals("")){
			     	soCharge.setEdiInvoiceNumber(null);
			     }
			    
//		if (soCharge.getCost() != null && cost != null &&
//				soCharge.getCost().doubleValue() != cost.doubleValue()) {
//			isDirty = true;
//			soCharge.setCost(cost);
//		}
			     return isDirty;
	}

	private CarrierChargeCode getCarrierCharge(Long chargeId) {
		// TODO Auto-generated method stub
		if (carrierChargesList != null) {
			for (CarrierChargeCode c:carrierChargesList) {
				if (c.getId().longValue() == chargeId.longValue())
					return c;
			}
		}
		return null;
	}

	public Map<String, Long> getCarrierChargesSearchResult() {
		return carrierChargesSearchResult;
	}

	public void setCarrierChargesSearchResult(
			Map<String, Long> carrierChargesSearchResult) {
		this.carrierChargesSearchResult = carrierChargesSearchResult;
	}

	public String listCarrierCharges(){
		String searchParameter = request.getParameter("searchString");
		log.debug("Search string is : " + searchParameter);
		
		if ((carrierChargesList == null && this.getSelectedOrder().getCarrierId() != null) ||
				(carrierChargesList != null && carrierChargesList.size()>0 && this.getSelectedOrder().getCarrierId() != null &&
						carrierChargesList.get(0).getCarrierId() != this.getSelectedOrder().getCarrierId().longValue())) {
			carrierChargesList = this.getShippingService().getChargeListByCarrierAndCodes(
																	this.getSelectedOrder().getCarrierId(), null, null);
		}
		if (carrierChargesList != null) {
			for(CarrierChargeCode c: carrierChargesList){
				carrierChargesSearchResult.put(c.getChargeName(), c.getId());
			}
		}
		
		return SUCCESS;
	}	
	
	public String deleteCharge() throws Exception {
		try {
			  Double totalActualCharge = 0.0;
		      Double totalActualCost = 0.0;
		      Double totalQuoteCharge = 0.0;
		      Double totalQuoteCost = 0.0;

			String s=request.getParameter("id");
			shippingDAO = (ShippingDAO) MmrBeanLocator.getInstance().findBean("shippingDAO");
			Long id = 0L;
			if (s != null)
				id = Long.parseLong(s);
			if (id.longValue() > 0) {
				this.getShippingService().deleteCharge(id);
				for (int i=0; i<this.getSelectedOrder().getCharges().size(); i++)
					if (this.getSelectedOrder().getCharges().get(i).getId().longValue() == id.longValue()) {
						this.getSelectedOrder().getCharges().remove(i);
						break;
					}
			    ShippingOrder order = new ShippingOrder();
		        for (int i = 0; i < this.getSelectedOrder().getCharges().size(); i++) {
		          if (this.getSelectedOrder().getCharges().get(i).getType() == 1) {
		            totalActualCharge += this.getSelectedOrder().getCharges().get(i).getCharge();
		            totalActualCost += this.getSelectedOrder().getCharges().get(i).getCost();
		          } else if (this.getSelectedOrder().getCharges().get(i).getType() == 0) {
		            totalQuoteCharge += this.getSelectedOrder().getCharges().get(i).getCharge();
		            totalQuoteCost += this.getSelectedOrder().getCharges().get(i).getCost();
		          }
		        }
		        DecimalFormat round = new DecimalFormat("###.##");
		        order.setId(this.getSelectedOrder().getId());
		       // order.setActualTotalCost(Double.valueOf(round.format(totalActualCost)));
		        order.setActualTotalCharge(Double.valueOf(round.format(totalActualCharge)));
		       // order.setQuoteTotalCost(Double.valueOf(round.format(totalQuoteCost)));
		        order.setQuoteTotalCharge(Double.valueOf(round.format(totalQuoteCharge)));
		        shippingDAO.updateTotalCharges(order);
		        currencyList=shippingDAO.getallCurrencySymbol();
				addActionMessage(getText("charge.deleted.successfully"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			addActionError(getText("content.error.unexpected"));
		}
		return SUCCESS;
	}
	
	public String sendEmailNotificationOfRates(){
		try {
			log.debug("In sendEmailNotificationOfRates method.");		
			int selected = Integer.parseInt(String.valueOf(request.getParameter("selected")));
			boolean emailsent = false;			
			ShippingOrder shippingorder = (ShippingOrder) getSession().get(
					"shippingOrder");
			if (shippingorder.getRateList().get(selected).getTotalCost() == 0.0) {
				emailsent = shippingService
						.sendCustomerEmailNotificationRequest(
								UserUtil.getMmrUser(), shippingorder, selected);
				if (emailsent)
					this.addActionMessage(getText("email.customer.rates.notification.sent")
							+ " "
							+ UserUtil.getMmrUser().getBusiness().getLtlEmail());
				else
					this.addActionError(getText("email.customer.rates.notification.not.sent")
							+ " "
							+ UserUtil.getMmrUser().getBusiness().getLtlEmail());
			} else {
				emailsent = shippingService.sendCustomerEmailNotification(
						UserUtil.getMmrUser(), shippingorder, selected);
				if (emailsent)
					this.addActionMessage(getText("email.customer.rates.notification.sent")
							+ " " + UserUtil.getMmrUser().getEmail());
				else
					this.addActionError(getText("email.customer.rates.notification.not.sent")
							+ " " + UserUtil.getMmrUser().getEmail());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}		
		
		return SUCCESS; 	
	}
	
	public String getNextAction() {
		return nextAction;
	}
	public void setNextAction(String nextAction) {
		this.nextAction = nextAction;
	}	
	
	public String addProductDetails() throws Exception {
		log.debug("---------------------In addProductDetails method.------------------------------");		
		
		
		ShippingOrder shippingOrder = getShippingOrder();
		
		String prod_desc = request.getParameter("desc");
		String prod_hcode = request.getParameter("hcode");
		String prod_origin = request.getParameter("origin_country");
		String prod_quantity = request.getParameter("quantity");
		String prod_unit_price = request.getParameter("unit_price");
		//String prod_weight = request.getParameter("weight");
		String decode = new String(prod_desc.getBytes("UTF-8"), "ISO-8859-1");
		CustomsInvoice customsInvoice = shippingOrder.getCustomsInvoice();
		CustomsInvoiceProduct customsInvoiceProduct = new CustomsInvoiceProduct();		
		
		try{
			customsInvoiceProduct.setCustomsInvoiceId(customsInvoice.getId());
			customsInvoiceProduct.setProductDesc(decode);
			customsInvoiceProduct.setProductHC(prod_hcode);
			customsInvoiceProduct.setProductOrigin(prod_origin);
			customsInvoiceProduct.setProductQuantity(Integer.parseInt(prod_quantity));
			customsInvoiceProduct.setProductUnitPrice(Double.parseDouble(prod_unit_price));
//			customsInvoiceProduct.setProductWeight(Long.valueOf(prod_weight));
			customsInvoiceProduct.setUnitPrice(new BigDecimal(prod_unit_price).setScale(2));
			List<CustomsInvoiceProduct> listproducts = new ArrayList<CustomsInvoiceProduct>();
			
			if(customsInvoice.getProducts()!=null)
				customsInvoice.getProducts().add(customsInvoiceProduct);
			else
			{	
				listproducts.add(customsInvoiceProduct);
				customsInvoice.setProducts(listproducts);				
			}
			
			shippingOrder.setCustomsInvoice(customsInvoice);
			getSession().put("shippingOrder",shippingOrder);
			
			
			//reset all the values
			/*customsInvoiceProduct.setCustomsInvoiceId(0);
			customsInvoiceProduct.setProductDesc("");
			customsInvoiceProduct.setProductHC("");
			customsInvoiceProduct.setProductOrigin("");
			customsInvoiceProduct.setProductQuantity(Integer.parseInt("0"));
			customsInvoiceProduct.setProductUnitPrice(Double.parseDouble("0"));
			customsInvoiceProduct.setProductWeight(Long.valueOf("0"));*/
		
		}catch (Exception e) {
			log.debug("exception-------------"+e);
			e.printStackTrace();
		}
		
		
		
		return SUCCESS;
	}
	
	public String addProductToShipment()
	{
		log.debug("---------------------In addProductToShipment method.------------------------------");
		
		warehouseProductsList = new ArrayList<Products>();
		ShippingOrder shippingOrder = this.getShippingOrder();
		long lProductId = Long.valueOf(request.getParameter("prodid"));
		
		long lQuantity = Long.valueOf(request.getParameter("quantity"));
		
		int iret = 0;
		
		try 
		{
			
			Products products = new Products();
			products.setProductId(lProductId);
			shippingOrder.setCustomerId(getLoginUser().getCustomerId());
			products.setCustomerId(shippingOrder.getCustomerId());
			warehouseProductsList = productManagerService.searchProducts(products, true);
			if(warehouseProductsList.size()>0)
			{
				shippingOrder.setWarehouseProductEach(warehouseProductsList.get(0));
				shippingOrder.getWarehouseProductEach().setOrderedQuantity(lQuantity);
			}
			if(!isProductAdded(shippingOrder,lProductId))
			{
				if(shippingOrder.getWarehouseProducts()!=null)
				{
					shippingOrder.getWarehouseProducts().add(shippingOrder.getWarehouseProductEach());
				}
				else
				{
					this.warehouseAllProdList.add(shippingOrder.getWarehouseProductEach());
					shippingOrder.setWarehouseProducts(warehouseAllProdList);
				}
				
			}
			else
				return "success2";
			
			getSession().put("shippingOrder",shippingOrder);
			
		} catch (Exception e) {
			log.error("Error occured in adding a product to Order:"+e);
		}
		return SUCCESS;	
	}
	
	private boolean isProductAdded(ShippingOrder so, long productId)
	{
		boolean boolret= false;
		if(so.getWarehouseProducts()!=null)
		{
			for(Products p: so.getWarehouseProducts())
			{
				if(p.getProductId()==productId)
					boolret=true;
			}
		}
		return boolret;
	}
	
	public String setBillToAdd()
	{
		String selected = request.getParameter("selected");
		ShippingOrder shippingOrder = getShippingOrder();
		
		if(selected.equals("Shipper"))
		{
			shippingOrder.getCustomsInvoice().getBillToAddress().copyAddress(shippingOrder.getFromAddress());
			request.setAttribute("address", "From");
		}
		else if(selected.equals("Consignee"))
		{
			shippingOrder.getCustomsInvoice().getBillToAddress().copyAddress(shippingOrder.getToAddress());
			request.setAttribute("address", "To");
		}
		getSession().put("billToProvinces", addressService.getProvinces(shippingOrder.getCustomsInvoice().getBillToAddress().getCountryCode()));
		
		return SUCCESS;
	}	
	
	
	public File getUpload() {
		return upload;
	}
	public void setUpload(File upload) {
		this.upload = upload;
	}
	
	public String getUploadFileName() {
		return uploadFileName;
	}
	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}
	public BatchShipmentInfo getBatchShipmentInfo() {
		return (BatchShipmentInfo) getSession().get("batchShipmentInfo");
	}

	@SuppressWarnings("unchecked")
	public void setBatchShipmentInfo(BatchShipmentInfo batchInfo) {
		getSession().put("batchShipmentInfo", batchInfo);
	}	
	
	public String batchShipment(){
		try {
//			getSession().remove("batchShipmentInfo");
			
			BatchShipmentInfo batchShipmentInfo = this.getBatchShipmentInfo();
			if (batchShipmentInfo == null) {
				batchShipmentInfo = new BatchShipmentInfo();
				batchShipmentInfo.setBusinessId(UserUtil.getMmrUser().getBusinessId());
				this.setBatchShipmentInfo(batchShipmentInfo);
			}
			
			if (this.carrierServiceManager != null) {
				if (getSession().get("CARRIERS") == null) {
					initCarrierList();
					batchShipmentInfo.setCarrierId(-1L);
				}
				if (getSession().get("SERVICES") == null) {
					List<Service>services = getCarrierServices(batchShipmentInfo.getCarrierId());
					getSession().put("SERVICES", services);
				}
			}
			//Calls the list Shipments that gets the shipments on the load event
			//listShipment();
			
			
			//Setting the Attribute for Carts
			request.setAttribute("fromCart", "false");
			
	    } catch (Exception e) {
	    	e.printStackTrace();
	    	addActionError(getText("content.error.unexpected"));
	    	addActionError(e.getMessage());
		}
		
		return SUCCESS;
	}	
	
	public String batchShipmentFileUpload() throws Exception {
		try {
			BatchShipmentInfo batchShipmentInfo = this.getBatchShipmentInfo();
			if (batchShipmentInfo != null && this.getUpload() != null && this.getUploadFileName() != null) {
				batchShipmentInfo.setFileName(this.getUploadFileName());
				InputStream is = new DataInputStream(new FileInputStream(this.getUpload()));
				if (is != null) {
					List<ShippingOrder> batchShipments = this.shippingService.uploadBatchShipmentFile(
																batchShipmentInfo, is);
					if (batchShipments == null) {
						addActionError(getText("content.error.unexpected"));
					} else {
						setShipments(batchShipments);
//						getSession().put("BATCH_SHIPMENTS", batchShipments);
					}
				}
			} else {
				addActionError(getText("select.valid.batch.shipment.file"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			addActionError(getText("content.error.unexpected"));
			addActionError(getText(e.getMessage()));
		}
		return batchShipment();
	}	
	
	public String createBatchShipments() throws Exception {
		try {
			if (select != null && select.size() > 0) {
				List<ShippingOrder> saveShipments = new ArrayList<ShippingOrder>();
				List<ShippingOrder> shipments = this.getShipments();
				for ( int i = 0; i < select.size(); i++ ) {
				    // If this checkbox was selected:
				    if ( select.get(i) != null && select.get(i) ) {
				    	ShippingOrder so = shipments.get(i);
				    	saveShipments.add(so);
				    } 		
				}	
				if (saveShipments.size() > 0) {
					shipments = this.shippingService.createBatchShipments(saveShipments);
					if (shipments != null) {
						getSession().remove("shipments");
						this.setShipments(shipments);
					} else {
						addActionError(getText("select.valid.shipments.for.creation"));
					}
				} else {
					addActionError(getText("select.valid.shipments.for.creation"));
				}
			} else {
				addActionError(getText("select.valid.shipments.for.creation"));
			}
	    } catch (Exception e) {
	    	e.printStackTrace();
	    	addActionError(getText("content.error.unexpected"));
	    	addActionError(getText(e.getMessage()));
		}		
		return batchShipment();
	}	
	
	public String processBatchShipments() throws Exception {
		try {
			BatchShipmentInfo batchInfo = this.getBatchShipmentInfo();
			String s = this.request.getParameter("markup.serviceId");
			if ( !StringUtil.isEmpty(s) )
				batchInfo.setServiceId(Long.parseLong(s));
			if (batchInfo != null && batchInfo.getCarrierId() != null && batchInfo.getServiceId() != null &&
					batchInfo.getCarrierId().longValue() > 0 && batchInfo.getServiceId().longValue() > 0) {
				List<ShippingOrder> processShipments = new ArrayList<ShippingOrder>();
				List<ShippingOrder> shipments = this.getShipments();
				if (select != null && select.size() > 0) {
					for ( int i = 0; i < select.size(); i++ ) {
					    // If this checkbox was selected:
					    if ( select.get(i) != null && select.get(i) ) {
					    	ShippingOrder so = shipments.get(i);
					    	processShipments.add(so);
					    } 		
					}			
					if (processShipments.size() > 0) {
						shipments = this.shippingService.processBatchShipments(processShipments, batchInfo);
						if (shipments != null) {
							addActionMessage(getText("shipment.processing.started.check.track.search"));
							this.setShipments(shipments);
						}
					} else {
						addActionError(getText("no.valid.shipment.found.for.batch.shipment.processing"));
					}
				} else {
					addActionError(getText("select.valid.shipments.for.processing"));
				}
			} else {
				addActionError(getText("select.carrier.service.for.batch.shipment.processing"));
			}
	    } catch (Exception e) {
	    	e.printStackTrace();
	    	addActionError(getText("content.error.unexpected"));
	    	addActionError(getText(e.getMessage()));
		}		
		return batchShipment();
	}	
	
	public String updateShipment() throws Exception {
		try {
			ShippingOrder so = this.getShippingOrder();
			if (so != null && this.shippingService != null) {
				// Check if Customer needs to be reassigned
				if (so.getWebCustomerId() != null && so.getWebCustomerId().longValue() > 0 && 
						so.getCustomerId() !=null && so.getCustomerId().longValue() != so.getWebCustomerId().longValue()) {
					// Check if charges are not invoiced yet, customer can only be changed if charges are not invoiced yet
					for (Charge c:so.getCharges()) {
						if (c.getStatus().intValue() == ShiplinxConstants.CHARGE_INVOICED) {
							addActionError(getText("failed.to.reassign.shipment.to.another.customer"));
							so.setWebCustomerId(so.getCustomerId());
							return INPUT;
						}
					}
				} else if (so.getCustomerId() ==null && so.getWebCustomerId() != null && so.getWebCustomerId().longValue() > 0) {
					// Check if charges are not invoiced yet, customer can only be changed if charges are not invoiced yet
					for (Charge c:so.getCharges()) {
						if (c.getStatus().intValue() == ShiplinxConstants.CHARGE_INVOICED) {
							addActionError(getText("failed.to.reassign.shipment.to.another.customer"));
							so.setWebCustomerId(so.getCustomerId());
							return INPUT;
						}
					}
				}else if (so.getWebCustomerId() != null && so.getWebCustomerId().longValue() == 0L) {
					// User want to make it to orphan shipment
					so.setCustomer(null);
					so.setCustomerId(0L);
					so.setBillingStatus(ShiplinxConstants.BILLING_STATUS_ORPHAN);
				}
				
		        if(so.getScheduledShipDate_web()!=null && so.getScheduledShipDate_web().length()>0){
					Date date = FormattingUtil.getDate(so.getScheduledShipDate_web(), FormattingUtil.DATE_FORMAT_WEB);
					so.setScheduledShipDate(new Timestamp(date.getTime()));
				}				

					        if (so != null && so.getId() != null) {
			          this.shippingService.update(so);
			        } else {
			          this.shippingService.save(so);
			        }

				getSession().remove("shippingOrder");
				ShippingOrder order = shippingService.getShippingOrder(so.getId());
				this.setShippingOrder(order);
				addActionMessage(getText("shippingOrder.updated.successfully"));
			}
	    } catch (Exception e) {
	    	e.printStackTrace();
	    	addActionError(getText("content.error.unexpected"));
	    	return INPUT;
		}
		
		return SUCCESS;
	}	
	
	public void populateCustomersList() {
	    String searchParameter = "";

	    Customer c = new Customer();
	    c.setName(searchParameter);
	    	    Long businessId=(Long) ActionContext.getContext().getSession().get(Constants.BUSINESS_ID_SESSION);
	    	    	    if(businessId!=null && !businessId.equals("")){
	    	    	    	c.setBusinessId(businessId);
	    	    	    }else{
	    	    	    	c.setBusinessId(UserUtil.getMmrUser().getBusinessId());
	    	    	    	
	    	    	    }
	    	             
	    	    	    List<Customer> customers =(List<Customer>)getSession().get(ShiplinxConstants.SESSION_BUSINESSFILTER_CUSTOMERID);
	    	     
	    // First record is empty
	    customerSearchResult.put("", 0L);

	    for (Customer cust : customers) {
	    	String withoutQuotesCustomer = cust.getName().replace("\"", "");
	    		      customerSearchResult.put(withoutQuotesCustomer, cust.getId());
	    }

	    getSession().put("customersList", customerSearchResult);
	  }

	public void populateUserList(){
		String searchParameter = ""; 
		  String customerId = request.getParameter("customerId");
		  if(customerId==null){
			  			 customerId = String.valueOf(UserUtil.getMmrUser().getCustomerId());
			  		  }
		  List<Address> customer = addressService.findaddressbyid(Long.valueOf(customerId));
		  for(Address add: customer){
			  String withoutQuotesCustomer = add.getAbbreviationName().replace("\"", "");
			  String address = add.getAddress1();
			  			  		  customerSearchResults.put(withoutQuotesCustomer+",  "+address, add.getAddressId());
		
	}
		  getSession().put("usersList", customerSearchResults);
		  }
	

	public String deleteProductDetails() throws Exception {
		log.debug("---------------------In deleteProductDetails method.------------------------------");		
		
		ShippingOrder shippingOrder = getShippingOrder();
		
		int indexSelected = Integer.parseInt(String.valueOf(request.getParameter("index")));
		
		CustomsInvoice customsInvoice = shippingOrder.getCustomsInvoice();
		
		List<CustomsInvoiceProduct> listproducts = customsInvoice.getProducts();
		
		listproducts.remove(indexSelected);
		
		customsInvoice.setProducts(listproducts);
		shippingOrder.setCustomsInvoice(customsInvoice);
		getSession().put("shippingOrder",shippingOrder);
		
		return SUCCESS;
	}
	
	public String deleteProductFromShipment()
	{
		log.debug("---------------------In deleteProductFromShipment method.------------------------------");
		
		int iIndex = Integer.parseInt(request.getParameter("index"));
		
		ShippingOrder shippingOrder = getShippingOrder();
		
		List<Products> productslist = shippingOrder.getWarehouseProducts();
		
		productslist.remove(iIndex);
		
		shippingOrder.setWarehouseProducts(productslist);
		this.setShippingOrder(shippingOrder);
		
		getSession().put("shippingOrder",shippingOrder);
		
		return SUCCESS;
	}
	
	public String showProductSummary()
	{
		log.debug("Inside----------showProductSummary() method----------");
		
		long prodid = Long.valueOf(request.getParameter("prodid"));
		ShippingOrder shippingOrder = getShippingOrder();
		Products products = shippingOrder.getWarehouseProduct();
		
		products.setProductId(prodid);
		products.setCustomerId(UserUtil.getMmrUser().getCustomerId());
		List<Products> prodslist = productManagerService.searchProducts(products, true);
		shippingOrder.setWarehouseProduct(prodslist.get(0));
		getSession().put("shippingOrder",shippingOrder);
		return SUCCESS;
	}
	
	  public String relevantPickupHistory() throws Exception {
		    HttpSession httpSession = request.getSession();
		    SimpleDateFormat formatDateJava = new SimpleDateFormat("yyyy-MM-dd");
		    com.meritconinc.shiplinx.model.ShippingOrder shippingOrder = (com.meritconinc.shiplinx.model.ShippingOrder) httpSession
		        .getAttribute("shippingOrder");
		    int flag = 0;
		    if (shippingOrder != null && shippingOrder.getPickup().isPickupRequired()) {
		      Address fromAddress = shippingOrder.getFromAddress();
		      Pickup pickUp = shippingOrder.getPickup();
		      pickUp.setPickupDate(shippingOrder.getScheduledShipDate());
		      String pickupDate = formatDateJava.format(pickUp.getPickupDate());
		      String carrierId = request.getParameter("carrierId");
		      List<RecordList> pickuplist = pickupService.getPickupList(fromAddress, pickUp, carrierId);
		      for (RecordList pickUpRecord : pickuplist) {
		    	  String recordPikupDate = "";
		    	  		        if (pickUpRecord.getPickupDate() != null && !pickUpRecord.getPickupDate().equals("")) {
		    	  		          try {
		    	  		            recordPikupDate = pickUpRecord.getPickupDate().substring(0, 10);
		    	  		          } catch (Exception e) {
		    	  		            log.error("Error in getting the pick up date " + e.getMessage() + "-value-"
		    	  		                + pickUpRecord.getPickupDate());
		    	  		          }
		    	  		        }
		        if (pickUpRecord != null && pickUpRecord.getStatusId() != null
		        		&& pickUpRecord.getStatusId().equals("10")
		        				            && pickUpRecord.getCarrierId().equals(carrierId)
		        				            && fromAddress.getAbbreviationName().equals(pickUpRecord.getAbbreviation())
		        				            && fromAddress.getAddress1().equals(pickUpRecord.getAddress1())
		        				            && fromAddress.getCity().equals(pickUpRecord.getCity())
		        				            && pickUp.getPickupLocation().equals(pickUpRecord.getPickupLocation())
		        				            && pickupDate.equals(recordPikupDate)) {
		          flag = 1;
		        }
		      }
		      if (pickuplist.size() > 0 && flag > 0) {
		        return SUCCESS;
		      } else {
		        return ERROR;
		      }
		    } else {
		      return ERROR;
		    }
		  }

	
	public String submitToWarehouse()
	{
		log.debug("Inside----------submitToWarehouse() method----------");
		ShippingOrder shippingOrder = this.getShippingOrder();
		int ierror = 0;
		try 
		{
			
			shippingOrder.setCustomerId(getLoginUser().getCustomerId());
			//setting customer info to the shipment
			shippingOrder.setCustomer(this.customerService.getCustomerInfoByCustomerId(shippingOrder.getCustomerId()));
			shippingOrder.setStatusId(Long.valueOf(ShiplinxConstants.STATUS_SENT_TOWAREHOUSE));
			
			this.setSelectedOrder(shippingOrder);
			this.setShippingOrder(shippingOrder);
			
			//SubmitWarehouse Logic Implementation + email + Logging Event details
			shippingService.submitToWarehouse(shippingOrder);
			/*//update product count
			if(productManagerService.updateProductsCounts(shippingOrder, ShiplinxConstants.STATUS_SENT_TOWAREHOUSE))
			{
			//update a field "fulfilled" in shipping_order table to 1, default is 0. This means the shipment order has been fulfilled.
			shippingOrder.setFulfilled(1); // active
			//update the Shipping order only if everything is ok
			shippingService.updateShippingOrder(shippingOrder);
			}*/
		}
		catch (Exception e) 
		{
			log.debug("Shipping Error!", e);
			addActionError(getText("shippingOrder.save.error"));
			ierror++;
		}
		if(ierror==0)
		addActionMessage(getText("shippingOrder.save.successfully"));
		
		getSession().put("shippingOrder",shippingOrder);
		
		if(ierror>0)
			return "failure";
		
		return viewShipment();
	}
	
	public String updateOrderStatus()
	{
		log.debug("Inside----------updateOrderStatus() method----------");
		String strOrderId = request.getParameter("order_id");
		ShippingOrder shippingOrder = this.shippingService.getShippingOrder(Long.valueOf(strOrderId));
		String strComment = request.getParameter("comment");
		boolean boolPrivate = Boolean.valueOf(request.getParameter("pvt"));
		String strStatusId = request.getParameter("statusId");
		Long lstatusId = 0L;
		LoggedEvent loggedEvent = new LoggedEvent();
		try 
		{
			loggedEvent.setEntityId(Long.valueOf(strOrderId));
			loggedEvent.setEntityType(Long.valueOf(strStatusId));
			if(strComment==null)
			{
				lstatusId = Long.valueOf(ShiplinxConstants.STATUS_RECEIVED_BYWAREHOUSE);
				shippingService.updateOrderStatus(Long.valueOf(strOrderId), lstatusId);
			}
			else
			{
				lstatusId = Long.valueOf(strStatusId);
				shippingService.updateOrderStatus(Long.valueOf(strOrderId), lstatusId, strComment, boolPrivate);
			}
			//populate warehouse products for the orders if they are warehouse orders
			if(shippingOrder.getCustomer()!=null && shippingOrder.getCustomer().isWarehouseCustomer())
			{
				List<Products> listproducts = populateOrderWarehouseProducts(shippingOrder.getId(), shippingOrder.getCustomerId());
				if(listproducts.size()>0)
				{
					shippingOrder.setWarehouseProducts(listproducts);
					productManagerService.updateProductsCounts(shippingOrder, Integer.parseInt(String.valueOf(lstatusId)));
				}
			}
			//populate the updated logs.
			if(!UserUtil.getMmrUser().getUserRole().equals("busadmin")||!UserUtil.getMmrUser().getUserRole().equalsIgnoreCase("solutions_manager")){
				loggedList = loggedEventService.getLoggedEventInfo(loggedEvent,true);
			}else{
				loggedEvent.setPrivateMessage(false);
				loggedEvent.setDeletedMessage(false);
				loggedList = loggedEventService.getLoggedEventInfo(loggedEvent,false);
			}
			addActionMessage(getText("shippingOrder.updated.successfully"));
			
		} 
		catch (Exception e) 
		{
			log.error("Exception occured while updating the status of the order: "+e);
			e.printStackTrace();
			addActionError(getText("shippingOrder.update.error"));
		}
		return SUCCESS;
	}
	
	public List<LoggedEvent> getLoggedList() {
		return loggedList;
	}
	public void setLoggedList(List<LoggedEvent> loggedList) {
		this.loggedList = loggedList;
	}
	public LoggedEventService getLoggedEventService() {
		return loggedEventService;
	}
	public void setLoggedEventService(LoggedEventService loggedEventService) {
		this.loggedEventService = loggedEventService;
	}
	public ProductManager getProductManagerService() {
		return productManagerService;
	}
	public void setProductManagerService(ProductManager productManagerService) {
		this.productManagerService = productManagerService;
	}
	public List<Products> getWarehouseProductsList() {
		return warehouseProductsList;
	}
	public void setWarehouseProductsList(List<Products> warehouseProductsList) {
		this.warehouseProductsList = warehouseProductsList;
	}
	public String saveCurrentShipment() throws Exception {
		try {
			ShippingOrder so = this.getShippingOrder();
			log.error("saveCurrentShipment");
			if (so != null && this.shippingService != null) {
				so.setSaveShipmet(1);
//				// Check if Customer needs to be reassigned
//				if (so.getWebCustomerId() != null && so.getWebCustomerId().longValue() > 0 && 
//						so.getCustomerId().longValue() != so.getWebCustomerId().longValue()) {
//					// Check if charges are not invoiced yet, customer can only be changed if charges are not invoiced yet
//					for (Charge c:so.getCharges()) {
//						if (c.getStatus().intValue() == ShiplinxConstants.CHARGE_INVOICED) {
//							addActionError(getText("failed.to.reassign.shipment.to.another.customer"));
//							so.setWebCustomerId(so.getCustomerId());
//							return INPUT;
//						}
//					}
//				} else if (so.getWebCustomerId() != null && so.getWebCustomerId().longValue() == 0L) {
//					// User want to make it to orphan shipment
//					so.setCustomer(null);
//					so.setCustomerId(0L);
//					so.setBillingStatus(ShiplinxConstants.BILLING_STATUS_ORPHAN);
//				}
		        if(so.getScheduledShipDate_web()!=null && so.getScheduledShipDate_web().length()>0){
					Date date = FormattingUtil.getDate(so.getScheduledShipDate_web(), FormattingUtil.DATE_FORMAT_WEB);
					so.setScheduledShipDate(new Timestamp(date.getTime()));
				}				
				if (so.getId() != null && so.getId().longValue() > 0) {
					this.shippingService.update(so);
				} else {
					PackageType packageType  = new PackageType();		
					packageType  = shippingService.findPackageType(so.getPackageTypeId().getType());		
					so.setPackageTypeId(packageType);
					so.setStatusId(new Long(ShiplinxConstants.STATUS_READYTOPROCESS));
					//remove the charges if the bill to type is 'Third Party' or 'Collect'
					if(so.getBillToType().equalsIgnoreCase(ShiplinxConstants.BILL_TO_THIRD_PARTY) || so.getBillToType().equalsIgnoreCase(ShiplinxConstants.BILL_TO_COLLECT))
						so.getCharges().removeAll(so.getCharges());
					this.shippingService.save(so);
				}
				getSession().remove("shippingOrder");
				ShippingOrder order = shippingService.getShippingOrder(so.getId());
				this.setShippingOrder(order);
				addActionMessage(getText("shippingOrder.updated.successfully"));
			}
	    } catch (Exception e) {
	    	e.printStackTrace();
	    	addActionError(getText("content.error.unexpected"));
	    	return INPUT;
		}
		
		return SUCCESS;
	}	
	
	private List<Products> populateOrderWarehouseProducts(long orderId, long customerId)
	{
		List<Products> productslist = new ArrayList<Products>();
		List<OrderProduct> orderProducts = this.shippingService.getOrderProducts(orderId);
		if(orderProducts.size()>0)
		{
			for(OrderProduct op: orderProducts)
			{
				Products products = new Products();
				products=productManagerService.getProductByProductIdAndCustomerId(op.getProductId(), customerId);
				products.setOrderedQuantity(op.getOrderedQuantity());
				productslist.add(products);
			}
		}
		return productslist;
	}
	public PickupManager getPickupService() {
		return pickupService;
	}
	public void setPickupService(PickupManager pickupService) {
		this.pickupService = pickupService;
	}
	public List<Pickup> getListPickups() {
		return listPickups;
	}
	public void setListPickups(List<Pickup> listPickups) {
		this.listPickups = listPickups;
	}
	
	private void logUpdateAction(long OrderId, String logMsg)
	{
		//Logged Event Initialization
		LoggedEvent loggedEvent = new LoggedEvent();
		// Set the loggedEvent Details
		loggedEvent.setEntityType(ShiplinxConstants.ENTITY_TYPE_ORDER_VALUE); //Entity - Warehouse Order
		loggedEvent.setEntityId(OrderId);//Order ID
		loggedEvent.setEventDateTime(new Date()); //Current Date
		loggedEvent.setEventUsername(UserUtil.getMmrUser().getUsername()); //Current User
		String systemLog = MessageUtil.getMessage("label.system.log.docs.accessed");
		loggedEvent.setSystemLog(systemLog); //System generated Message Log
		if(UserUtil.getMmrUser().getUserRole().equals("busadmin")||UserUtil.getMmrUser().getUserRole().equals("solutions_manager"))
			loggedEvent.setPrivateMessage(true);
		else
			loggedEvent.setPrivateMessage(false);
		loggedEvent.setMessage(logMsg);
		//Log the Event into the DB
		loggedEventService.addLoggedEventInfo(loggedEvent); //Added Event Log into DB
		
	}

	/*
	 * This method retrieves the list of [cities, zipcode] beginning with queried letters for city and selected country by user.
	 * And write the list into response.
	 * @param Nothing.
	 * @return String
	 * @Exception IOException, Exception
	 */

	public String getCitySuggest(){
		String cityString = request.getParameter("q");
		if(cityString != null && cityString.trim().length() > 0)
		{
			ShippingOrder order = getShippingOrder();
			
			String addressType = request.getParameter("addressType");
			if(addressType.equalsIgnoreCase("from")){
				if(order.getFromAddress().getCountryCode().equalsIgnoreCase(ShiplinxConstants.US) || order.getFromAddress().getCountryCode().equalsIgnoreCase(ShiplinxConstants.CANADA))
					return SUCCESS;
				citySuggestList = addressService.getCitySuggest(order.getFromAddress().getCountryCode(), cityString);
			}
			else{
				if(order.getToAddress().getCountryCode().equalsIgnoreCase(ShiplinxConstants.US) || order.getToAddress().getCountryCode().equalsIgnoreCase(ShiplinxConstants.CANADA))
					return SUCCESS;
				citySuggestList = addressService.getCitySuggest(order.getToAddress().getCountryCode(), cityString);
			}
				
			if(citySuggestList == null || citySuggestList.size() == 0)
				return SUCCESS;

			try {
				HttpServletResponse response = ServletActionContext.getResponse();
				response.setContentType("text/html");

				PrintWriter out = response.getWriter();
				for(String strFilteredCity:citySuggestList)
					out.println(strFilteredCity+"|"+strFilteredCity);
			} 
			catch (IOException e) {
				log.error("Exception occured while retrieving details for getCitySuggest  "+e);
				e.printStackTrace();
				addActionError(getText("shippingOrder.autocompleter.city.error"));
			}  
			catch (Exception e) {
				log.error("Exception occured while retrieving details for getCitySuggest  "+e);
				e.printStackTrace();
				addActionError(getText("shippingOrder.autocompleter.city.error"));
			}
		}
		return SUCCESS;
	}
	
	/*
	 * This method retrieves the list of [zipcode, cities] beginning with queried letters for zipcode and selected country by user.
	 * And write the list into response.
	 * @param Nothing.
	 * @return String
	 * @Exception IOException, Exception
	 */

	public String getZipSuggest(){
	    String zipString = request.getParameter("q");

	    if (zipString != null && zipString.trim().length() > 0) {
	      ShippingOrder order = getShippingOrder();

	      String addressType = request.getParameter("addressType");
	      if (addressType.equalsIgnoreCase("from")) {
	        if (order.getFromAddress() != null
	            && order.getFromAddress().getCountryCode() != null
	            && (order.getFromAddress().getCountryCode().equalsIgnoreCase(ShiplinxConstants.US) || order
	                .getFromAddress().getCountryCode().equalsIgnoreCase(ShiplinxConstants.CANADA)))
	          return SUCCESS;
	        zipSuggestList = addressService.getZipSuggest(order.getFromAddress().getCountryCode(),
	            zipString);
	      } else {
	        if (order.getToAddress().getCountryCode().equalsIgnoreCase(ShiplinxConstants.US)
	            || order.getToAddress().getCountryCode().equalsIgnoreCase(ShiplinxConstants.CANADA))
	          return SUCCESS;

	        zipSuggestList = addressService.getZipSuggest(order.getToAddress().getCountryCode(),
	            zipString);
	      }

	      if (zipSuggestList == null || zipSuggestList.size() == 0)
	        return SUCCESS;

	      try {
	        HttpServletResponse response = ServletActionContext.getResponse();
	        response.setContentType("text/html");

	        PrintWriter out = response.getWriter();
	        for (String strFilteredZip : zipSuggestList)
	          out.println(strFilteredZip + "|" + strFilteredZip);

	      } catch (IOException e) {
	        log.error("Exception occured while retrieving details for getZipSuggest  " + e);
	        e.printStackTrace();
	        addActionError(getText("shippingOrder.autocompleter.zip.error"));
	      } catch (Exception e) {
	        log.error("Exception occured while retrieving details for getZipSuggest  " + e);
	        e.printStackTrace();
	        addActionError(getText("shippingOrder.autocompleter.zip.error"));
	      }
	    }

	    return SUCCESS;
	  }

	public List<String> getCitySuggestList() {
		return citySuggestList;
	}
	public void setCitySuggestList(List<String> citySuggestList) {
		this.citySuggestList = citySuggestList;
	}
	
	public UserService getUserService() {
		return userService;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public List<String> getZipSuggestList() {
		return zipSuggestList;
	}
	public void setZipSuggestList(List<String> zipSuggestList) {
		this.zipSuggestList = zipSuggestList;
	}
	
	public List<BillingStatus> getBillingStatusList() {
		return (List<BillingStatus>) getSession().get("billingStatusList");

	}
	
	
	public List<String> getShipmentIdList() {
		return shipmentIdList;
	}
	public void setShipmentIdList(List<String> shipmentIdList) {
		this.shipmentIdList = shipmentIdList;
	}
	public void setBillingStatusList(List<BillingStatus> billingStatusList) {
		if (billingStatusList != null) {
			billingStatusList.add(0, new BillingStatus(-1, ""));
		}
		getSession().put("billingStatusList", billingStatusList);
	}
	public InvoiceManager getInvoiceManagerService() {
		return invoiceManagerService;
	}
	public void setInvoiceManagerService(InvoiceManager invoiceManagerService) {
		this.invoiceManagerService = invoiceManagerService;
	}
	public InvoiceDAO getInvoiceDAO() {
				return invoiceDAO;
			}
		
			public void setInvoiceDAO(InvoiceDAO invoiceDAO) {
				this.invoiceDAO = invoiceDAO;
			}
	private String removeNull(String text){
		if(null==text || text.equals("null")){
			return "";
		}
		return text;
	}
	
	
	 private  void generateCsvFile(List<ShippingOrder> shippingOrderList,FileWriter writer)
	   {
		try
		{
		   
			writer.append("Order Id");
		    writer.append(',');
		    writer.append("Tracking");
		    writer.append(',');
		    writer.append("Ship Date");
		    writer.append(',');
		    writer.append("Carrier");
		    writer.append(',');
		    writer.append("Service");
		    writer.append(',');
		    writer.append("Q/B");
		    		    writer.append(',');
		    		    writer.append("Weight");
		    		    writer.append(',');		    
		    		    writer.append("Quoted Charge");		    
		    		    writer.append(',');
		    		    if(UserUtil.getMmrUser().getUserRole().equalsIgnoreCase("busadmin")){
		    		    	writer.append("Quoted Cost");		    
			    		    writer.append(',');
			    		    writer.append("Billed Charge");		    
			    		    writer.append(',');
			    		    writer.append("Billed Cost");		    
			    		    writer.append(',');
			    		    writer.append("From Address");		    
			    		    writer.append(',');
			    		    writer.append("To Address");		    
			    		    writer.append(',');
			    		    writer.append("Status");		    
			    		    writer.append(',');
			    		    writer.append("Billing Status");
			    		    writer.append(',');
			    		    		    		    writer.append("Reference 1");
			    		    		    		    writer.append(',');
			    		    		    		    writer.append("Reference 2");
		    		    }else{
			    		    writer.append("Billed Charge");		    
			    		    writer.append(',');
			    		    writer.append("From Address");		    
			    		    writer.append(',');
			    		    writer.append("To Address");		    
			    		    writer.append(',');
			    		    writer.append("Status");		    
			    		    writer.append(',');
			    		    writer.append("Billing Status");
			    		    writer.append(',');
			    		    		    		    writer.append("Reference 1");
			    		    		    		    writer.append(',');
			    		    		    		    writer.append("Reference 2");
		    		    }
		    		    
		    writer.append('\n');
		   /* writer.append("carrier");
		    writer.append(',');*/
		    SimpleDateFormat formatDateJava = new SimpleDateFormat("dd/MM/yyyy");
		    for(ShippingOrder sOrder :shippingOrderList){
		 
		    
	 
		    writer.append(removeNull(String.valueOf(sOrder.getId())));
		    writer.append(',');
		    writer.append(removeNull(sOrder.getMasterTrackingNum()));
		    writer.append(',');
		    writer.append(formatDateJava.format(sOrder.getScheduledShipDate()));
		    		    writer.append(',');
		    		    if(sOrder.getCarrier()!=null){
		    		    writer.append(removeNull(sOrder.getCarrier().getName()));
		    		    writer.append(',');
		    		    }else{
		    		    	writer.append("");
			    		    writer.append(',');
		    		    }
		    		    if(sOrder.getService()!=null){
		    		    writer.append(removeNull(sOrder.getService().getName()));
		    		    writer.append(',');
		    		    }else{
		    		    	writer.append("");
			    		    writer.append(',');
		    		    }
		    	        writer.append(removeNull(String.valueOf(sOrder.getQuotedWeight())));
	        writer.append(',');
	        writer.append(removeNull(String.valueOf(sOrder.getBilledWeight())));
	        writer.append(',');
	        writer.append("$"+removeNull(String.valueOf(sOrder.getQuoteTotalCharge())));
	        	        writer.append(',');
	        	        if(UserUtil.getMmrUser().getUserRole().equalsIgnoreCase("busadmin")){
	        	        writer.append("$"+removeNull(String.valueOf(sOrder.getQuoteTotalCost())));
	        	        writer.append(',');
	        	        writer.append("$"+removeNull(String.valueOf(sOrder.getActualTotalCharge())));
	        	        writer.append(',');
	        	        writer.append("$"+removeNull(String.valueOf(sOrder.getActualTotalCost())));
	        	        writer.append(',');
	        	        String abbrevfrom,add1from,add2from,cityfrom,poscodefrom,procodefrom,counnamefrom,addrfrom,addressfrom="",abbrev,add1,add2,city,poscode,procode,counname,toaddr,toaddress="";
 	        	        if(sOrder.getFromAddress()!=null){
	        	        abbrevfrom=removeNullComma(removeNull(String.valueOf(sOrder.getFromAddress().getAbbreviationName())));
	        	        add1from=removeNullComma(removeNull(String.valueOf(sOrder.getFromAddress().getAddress1())));
	        	        add2from=removeNullComma(removeNull(String.valueOf(sOrder.getFromAddress().getAddress2())));
	        	        cityfrom=removeNullComma(removeNull(String.valueOf(sOrder.getFromAddress().getCity())));
	        	        poscodefrom=removeNullComma(removeNull(String.valueOf(sOrder.getFromAddress().getPostalCode())));
	        	        procodefrom=removeNullComma(removeNull(String.valueOf(sOrder.getFromAddress().getProvinceCode())));
	        	        counnamefrom=removeNull(removeNull(String.valueOf(sOrder.getFromAddress().getCountryName())));
					addrfrom = abbrevfrom + add1from + add2from + cityfrom
							+ poscodefrom + procodefrom + counnamefrom;
					if (addrfrom != null && !addrfrom.isEmpty()) {
						addrfrom = addrfrom + ".";
						addressfrom = "\"" + addrfrom + "\"";
					}	        	        
	        	        writer.append(addressfrom);
	        	        writer.append(',');
				} else {
					writer.append("");
					writer.append(',');
				}if(sOrder.getToAddress()!=null){
	        	        abbrev=removeNullComma(removeNull(String.valueOf(sOrder.getToAddress().getAbbreviationName())));
	        	        add1=removeNullComma(removeNull(String.valueOf(sOrder.getToAddress().getAddress1())));
	        	        add2=removeNullComma(removeNull(String.valueOf(sOrder.getToAddress().getAddress2())));
	        	        city=removeNullComma(removeNull(String.valueOf(sOrder.getToAddress().getCity())));
	        	        poscode=removeNullComma(removeNull(String.valueOf(sOrder.getToAddress().getPostalCode())));
	        	        procode=removeNullComma(removeNull(String.valueOf(sOrder.getToAddress().getProvinceCode())));
	        	        counname=removeNull(String.valueOf(sOrder.getToAddress().getCountryName()));
					toaddr = abbrev + add1 + add2 + city + poscode + procode
							+ counname;
					if (toaddr != null && !toaddr.isEmpty()) {
						toaddr = toaddr + ".";
						toaddress = "\"" + toaddr + "\"";
					}
	        	        writer.append(toaddress);
	        	        writer.append(',');
				} else {
					writer.append("");
					writer.append(',');
				}
	        	        				        writer.append(removeNull(sOrder.getStatusName()));
	        	        				        writer.append(',');
	        	        				        writer.append(removeNull(String.valueOf(sOrder.getBillingStatusText())));
	        	        				        writer.append(',');
	        	        				        writer.append(removeNull(String.valueOf(sOrder.getReferenceCode())));
	        	        				        writer.append(',');
	        	        				        if(sOrder.getReferenceOne() != null && sOrder.getReferenceOne() != ""){
	        	                		        	 referenceone = String.valueOf(sOrder.getReferenceOne());
	        	                		         }
	        	                		         if(sOrder.getReferenceTwo() != null && sOrder.getReferenceTwo() != ""){
	        	                		        	 referencetwo = String.valueOf(sOrder.getReferenceTwo());
	        	                		         }
	        	        				        writer.append(removeNull(referenceone+" "+referencetwo));
	        	        }else{
	        	        	writer.append("$"+removeNull(String.valueOf(sOrder.getActualTotalCharge())));
		        	        writer.append(',');
		        	        String abbrevfrom,add1from,add2from,cityfrom,poscodefrom,procodefrom,counnamefrom,addrfrom,addressfrom="",abbrev,add1,add2,city,poscode,procode,counname,toaddr,toaddress="";
	 	        	        if(sOrder.getFromAddress()!=null){
		        	        abbrevfrom=removeNullComma(removeNull(String.valueOf(sOrder.getFromAddress().getAbbreviationName())));
		        	        add1from=removeNullComma(removeNull(String.valueOf(sOrder.getFromAddress().getAddress1())));
		        	        add2from=removeNullComma(removeNull(String.valueOf(sOrder.getFromAddress().getAddress2())));
		        	        cityfrom=removeNullComma(removeNull(String.valueOf(sOrder.getFromAddress().getCity())));
		        	        poscodefrom=removeNullComma(removeNull(String.valueOf(sOrder.getFromAddress().getPostalCode())));
		        	        procodefrom=removeNullComma(removeNull(String.valueOf(sOrder.getFromAddress().getProvinceCode())));
		        	        counnamefrom=removeNull(removeNull(String.valueOf(sOrder.getFromAddress().getCountryName())));
						addrfrom = abbrevfrom + add1from + add2from + cityfrom
								+ poscodefrom + procodefrom + counnamefrom;
						if (addrfrom != null && !addrfrom.isEmpty()) {
							addrfrom = addrfrom + ".";
							addressfrom = "\"" + addrfrom + "\"";
						}	        	        
		        	        writer.append(addressfrom);
		        	        writer.append(',');
					} else {
						writer.append("");
						writer.append(',');
					}if(sOrder.getToAddress()!=null){
		        	        abbrev=removeNullComma(removeNull(String.valueOf(sOrder.getToAddress().getAbbreviationName())));
		        	        add1=removeNullComma(removeNull(String.valueOf(sOrder.getToAddress().getAddress1())));
		        	        add2=removeNullComma(removeNull(String.valueOf(sOrder.getToAddress().getAddress2())));
		        	        city=removeNullComma(removeNull(String.valueOf(sOrder.getToAddress().getCity())));
		        	        poscode=removeNullComma(removeNull(String.valueOf(sOrder.getToAddress().getPostalCode())));
		        	        procode=removeNullComma(removeNull(String.valueOf(sOrder.getToAddress().getProvinceCode())));
		        	        counname=removeNull(String.valueOf(sOrder.getToAddress().getCountryName()));
						toaddr = abbrev + add1 + add2 + city + poscode + procode
								+ counname;
						if (toaddr != null && !toaddr.isEmpty()) {
							toaddr = toaddr + ".";
							toaddress = "\"" + toaddr + "\"";
						}
		        	        writer.append(toaddress);
		        	        writer.append(',');
					} else {
						writer.append("");
						writer.append(',');
					}
		        	        				        writer.append(removeNull(sOrder.getStatusName()));
		        	        				        writer.append(',');
		        	        				        writer.append(removeNull(String.valueOf(sOrder.getBillingStatusText())));
		        	        				        writer.append(',');
		        	        				        writer.append(removeNull(String.valueOf(sOrder.getReferenceCode())));
		        	        				        writer.append(',');
		        	        				        if(sOrder.getReferenceOne() != null && sOrder.getReferenceOne() != ""){
		        	                		        	 referenceone = String.valueOf(sOrder.getReferenceOne());
		        	                		         }
		        	                		         if(sOrder.getReferenceTwo() != null && sOrder.getReferenceTwo() != ""){
		        	                		        	 referencetwo = String.valueOf(sOrder.getReferenceTwo());
		        	                		         }
		        	        				        writer.append(removeNull(referenceone+" "+referencetwo));
	        	        }
	        	        
	        	        				        writer.append('\n');
		    //generate whatever data you want
	 
		    
		}
		
		writer.flush();
	    writer.close();
	    System.out.println("csv generated successfully");
		}
		catch(IOException e)
		{
		     e.printStackTrace();
		} 
	    }
	
	 private String removeNullComma(String text)
	 	 	 	 {
		 if(null==text||text.equalsIgnoreCase("null")|| text.equalsIgnoreCase("")){
	 	 	 			 
	 	 	 			 return "";
	 	 	 		 }
	 	 	 		 else
	 	 	 			 return text+",";
	 	 	 }	
	 
	 public void createxlfile(List<ShippingOrder> shippingOrderList,String shippingLabelFileName) throws IOException{
			
	        HSSFWorkbook workbook=new HSSFWorkbook();
	        HSSFSheet sheet =  workbook.createSheet("FirstSheet");  
	        HSSFRow rowhead=   sheet.createRow((short)0);
	        rowhead.createCell((short) 0).setCellValue("Order Id ");
	        	        rowhead.createCell((short) 1).setCellValue("Tracking Id");
	        	        rowhead.createCell((short) 2).setCellValue("Ship Date");
	        	        rowhead.createCell((short) 3).setCellValue("Carrier");
	        	        rowhead.createCell((short) 4).setCellValue("Service");
	        	        rowhead.createCell((short) 5).setCellValue("Q/B");
	        	        rowhead.createCell((short) 6).setCellValue("Weight");
	        	        rowhead.createCell((short) 7).setCellValue("Quoted Charge");
	        	        if(UserUtil.getMmrUser().getUserRole().equalsIgnoreCase("busadmin")){
	        	        	rowhead.createCell((short) 8).setCellValue("Quoted Cost");
		        	        rowhead.createCell((short) 9).setCellValue("Billed Charge");
		        	        rowhead.createCell((short) 10).setCellValue("Billed Cost");
		        	        rowhead.createCell((short) 11).setCellValue("From Address");
		        	        rowhead.createCell((short) 12).setCellValue("To Address");
		        	        rowhead.createCell((short) 13).setCellValue("Status");
		        	        rowhead.createCell((short) 14).setCellValue("Billing Status");
		        	        rowhead.createCell((short) 15).setCellValue("Reference 1");
		        	        rowhead.createCell((short) 16).setCellValue("Reference 2");
	        	        }else{
	        	        	 rowhead.createCell((short) 8).setCellValue("Billed Charge");
			        	        rowhead.createCell((short) 9).setCellValue("From Address");
			        	        rowhead.createCell((short) 10).setCellValue("To Address");
			        	        rowhead.createCell((short) 11).setCellValue("Status");
			        	        rowhead.createCell((short) 12).setCellValue("Billing Status");
			        	        rowhead.createCell((short) 13).setCellValue("Reference 1");
			        	        rowhead.createCell((short) 14).setCellValue("Reference 2");
	        	        }
	        	        
	        	        SimpleDateFormat formatDateJava = new SimpleDateFormat("dd/MM/yyyy");
	       
	        int i=1;
	        for(ShippingOrder sorder :shippingOrderList){
	        	
	        HSSFRow row=   sheet.createRow((short)i);
	        row.createCell((short) 0).setCellValue(removeNull(String.valueOf(sorder.getId())));
	        row.createCell((short) 1).setCellValue(removeNull(sorder.getMasterTrackingNum()));
	        	        row.createCell((short) 2).setCellValue(formatDateJava.format(sorder.getScheduledShipDate()));
	        	        if(sorder.getCarrier()!=null){
	        	        row.createCell((short) 3).setCellValue(removeNull(sorder.getCarrier().getName()));
	        	        }else{
	        	        	row.createCell((short) 3).setCellValue("");
	        	        }
	        	        if(sorder.getService()!=null){
	        	        row.createCell((short) 4).setCellValue(removeNull(sorder.getService().getName()));
	        	        }else{
	        	        	row.createCell((short) 4).setCellValue("");
	        	        }
	        	        row.createCell((short) 5).setCellValue(removeNull(String.valueOf(sorder.getQuotedWeight())));
	        	        row.createCell((short) 6).setCellValue(removeNull(String.valueOf(sorder.getBilledWeight())));
	        	        row.createCell((short) 7).setCellValue("$"+removeNull(String.valueOf(sorder.getQuoteTotalCharge())));
	        	        if(UserUtil.getMmrUser().getUserRole().equalsIgnoreCase("busadmin")){
	        	        	  row.createCell((short) 8).setCellValue("$"+removeNull(String.valueOf(sorder.getQuoteTotalCost())));
	  	        	        row.createCell((short) 9).setCellValue("$"+removeNull(String.valueOf(sorder.getActualTotalCharge())));
	  	        	        row.createCell((short) 10).setCellValue("$"+removeNull(String.valueOf(sorder.getActualTotalCost())));
	  	        	        if(sorder.getFromAddress() != null){
	  	        	        row.createCell((short) 11).setCellValue(removeNullComma(removeNull(String.valueOf(sorder.getFromAddress().getAbbreviationName())))+" "+removeNullComma(removeNull(String.valueOf(sorder.getFromAddress().getAddress1())))+" "+removeNullComma(removeNull(String.valueOf(sorder.getFromAddress().getAddress2())))+" "+removeNullComma(removeNull(String.valueOf(sorder.getFromAddress().getCity())))+" "+removeNullComma(removeNull(String.valueOf(sorder.getFromAddress().getPostalCode())))+" "+removeNullComma(removeNull(String.valueOf(sorder.getFromAddress().getProvinceCode())))+" "+removeNull(removeNull(String.valueOf(sorder.getFromAddress().getCountryName())))+".");
	  	        	        }
	  	        	        if(sorder.getToAddress() != null){
	  	        	        row.createCell((short) 12).setCellValue(removeNullComma(removeNull(String.valueOf(sorder.getToAddress().getAbbreviationName())))+" "+removeNullComma(removeNull(String.valueOf(sorder.getToAddress().getAddress1())))+" "+removeNullComma(removeNull(String.valueOf(sorder.getToAddress().getAddress2())))+" "+removeNullComma(removeNull(String.valueOf(sorder.getToAddress().getCity())))+" "+removeNullComma(removeNull(String.valueOf(sorder.getToAddress().getPostalCode())))+" "+removeNullComma(removeNull(String.valueOf(sorder.getToAddress().getProvinceCode())))+" "+removeNull(removeNull(String.valueOf(sorder.getToAddress().getCountryName())))+".");
	  	        	        }
	  	        	        row.createCell((short) 13).setCellValue(removeNull(sorder.getStatusName()));
	  	        	        row.createCell((short) 14).setCellValue(removeNull(String.valueOf(sorder.getBillingStatusText())));
	  	        	        row.createCell((short) 15).setCellValue(removeNull(String.valueOf(sorder.getReferenceCode())));
	  	        	        if(sorder.getReferenceOne() != null && sorder.getReferenceOne() != ""){
	  	        	         	 referenceone = String.valueOf(sorder.getReferenceOne());
	  	        	             	}
	  	        	         if(sorder.getReferenceTwo() != null && sorder.getReferenceTwo() != ""){
	  	        	               referencetwo = String.valueOf(sorder.getReferenceTwo());
	  	        	               	}
	  	        	         row.createCell((short) 16).setCellValue(removeNull(referenceone+" "+ referencetwo));
	        	        }else{
		  	        	        row.createCell((short) 8).setCellValue("$"+removeNull(String.valueOf(sorder.getActualTotalCharge())));
		  	        	        if(sorder.getFromAddress() != null){
		  	        	        row.createCell((short) 9).setCellValue(removeNullComma(removeNull(String.valueOf(sorder.getFromAddress().getAbbreviationName())))+" "+removeNullComma(removeNull(String.valueOf(sorder.getFromAddress().getAddress1())))+" "+removeNullComma(removeNull(String.valueOf(sorder.getFromAddress().getAddress2())))+" "+removeNullComma(removeNull(String.valueOf(sorder.getFromAddress().getCity())))+" "+removeNullComma(removeNull(String.valueOf(sorder.getFromAddress().getPostalCode())))+" "+removeNullComma(removeNull(String.valueOf(sorder.getFromAddress().getProvinceCode())))+" "+removeNull(removeNull(String.valueOf(sorder.getFromAddress().getCountryName())))+".");
		  	        	        }
		  	        	        if(sorder.getToAddress() != null){
		  	        	        row.createCell((short) 10).setCellValue(removeNullComma(removeNull(String.valueOf(sorder.getToAddress().getAbbreviationName())))+" "+removeNullComma(removeNull(String.valueOf(sorder.getToAddress().getAddress1())))+" "+removeNullComma(removeNull(String.valueOf(sorder.getToAddress().getAddress2())))+" "+removeNullComma(removeNull(String.valueOf(sorder.getToAddress().getCity())))+" "+removeNullComma(removeNull(String.valueOf(sorder.getToAddress().getPostalCode())))+" "+removeNullComma(removeNull(String.valueOf(sorder.getToAddress().getProvinceCode())))+" "+removeNull(removeNull(String.valueOf(sorder.getToAddress().getCountryName())))+".");
		  	        	        }
		  	        	        row.createCell((short) 11).setCellValue(removeNull(sorder.getStatusName()));
		  	        	        row.createCell((short) 12).setCellValue(removeNull(String.valueOf(sorder.getBillingStatusText())));
		  	        	        row.createCell((short) 13).setCellValue(removeNull(String.valueOf(sorder.getReferenceCode())));
		  	        	        if(sorder.getReferenceOne() != null && sorder.getReferenceOne() != ""){
		  	        	         	 referenceone = String.valueOf(sorder.getReferenceOne());
		  	        	             	}
		  	        	         if(sorder.getReferenceTwo() != null && sorder.getReferenceTwo() != ""){
		  	        	               referencetwo = String.valueOf(sorder.getReferenceTwo());
		  	        	               	}
		  	        	         row.createCell((short) 14).setCellValue(removeNull(referenceone+" "+ referencetwo));
	        	        }
	        	      
	        /*if(sorder.getCarrierId()!=null){
	        Carrier findCarrier = carrierServiceManager.getCarrier(sorder.getCarrierId());
	        row.createCell((short) 6).setCellValue(removeNull(String.valueOf(findCarrier.getName())));
	        
	        }*/
	        i++;
			 }
	        
	        FileOutputStream fileOut =  new FileOutputStream(shippingLabelFileName);
	        workbook.write(fileOut);
	        fileOut.close();
	        System.out.println("Your excel file has been generated!");
	        

	    }

	 
	 
	 
	
	 public String getUniqueTempcsvFileName(String fName)
		{
			Date curDateTime = new Date();
			String tempPath = System.getProperty("java.io.tmpdir");
			File path = new File( tempPath );
			if ( !path.exists() )
				path.mkdirs();
			
			return tempPath + File.separator + fName + curDateTime.getTime() + ".csv";
		}	 
		
		public String getUniqueTempxmlFileName(String fName)
		{
			Date curDateTime = new Date();
			String tempPath = System.getProperty("java.io.tmpdir");
			File path = new File( tempPath );
			if ( !path.exists() )
				path.mkdirs();
			
			return tempPath + File.separator + fName + curDateTime.getTime() + ".xml";
		}	 
		
		public String getUniqueTempxlFileName(String fName)
		{
			Date curDateTime = new Date();
			String tempPath = System.getProperty("java.io.tmpdir");
			File path = new File( tempPath );
			if ( !path.exists() )
				path.mkdirs();
			
			return tempPath + File.separator + fName + curDateTime.getTime() + ".xls";
		}	 
		 private  void generateCsvFile_view(List<LoggedEvent> event,FileWriter writer)
		   {
			try
			{
			   
			    writer.append("id");
			    writer.append(',');
			    writer.append("orderNum");
			    writer.append(',');
			    writer.append("billingStatus");
			   
			   
			    writer.append('\n');
			   /* writer.append("carrier");
			    writer.append(',');*/
			    
			    for(LoggedEvent levent :event){
			    writer.append(removeNull(levent.getMessage()));
			    writer.append(',');
		        writer.append(removeNull(levent.getSystemLog()));   
		        writer.append(',');
		        writer.append(removeNull(String.valueOf(levent.getEventDateTime())));
		       
		        
		       
		        writer.append('\n');
			    //generate whatever data you want
		 
			    
			}
			
			writer.flush();
		    writer.close();
		    System.out.println("csv generated successfully");
			}
			catch(IOException e)
			{
			     e.printStackTrace();
			} 
		    }
		
		 public void createxlfile_view(List<LoggedEvent> event,String shippingLabelFileName) throws IOException{
				
		        HSSFWorkbook workbook=new HSSFWorkbook();
		        HSSFSheet sheet =  workbook.createSheet("FirstSheet");  
		        HSSFRow rowhead=   sheet.createRow((short)0);
		        rowhead.createCell((short) 0).setCellValue("Message ");
		        rowhead.createCell((short) 1).setCellValue("System Log");
		        rowhead.createCell((short) 2).setCellValue("Event Date");
		       
		       /*rowhead.createCell((short) 7).setCellValue("carrier");*/
		       
		        int i=1;
		        for(LoggedEvent levent :event){
		        	
		        	
		        	
		        HSSFRow row=   sheet.createRow((short)i);
		        row.createCell((short) 0).setCellValue(removeNull(String.valueOf(levent.getMessage())));
		        row.createCell((short) 1).setCellValue(removeNull(String.valueOf(levent.getSystemLog())));
		        row.createCell((short) 2).setCellValue(removeNull(String.valueOf(levent.getEventDateTime())));
		       
		        /*if(sorder.getCarrierId()!=null){
		        Carrier findCarrier = carrierServiceManager.getCarrier(sorder.getCarrierId());
		        row.createCell((short) 6).setCellValue(removeNull(String.valueOf(findCarrier.getName())));
		        
		        }*/
		        i++;
				 }
		        
		        FileOutputStream fileOut =  new FileOutputStream(shippingLabelFileName);
		        workbook.write(fileOut);
		        fileOut.close();
		        System.out.println("Your excel file has been generated!");
		        

		    }
		 
		 public  void write_XML_File_view(List<LoggedEvent> event,String shippingLabelFileName){
			 
				
			  
			 DocumentBuilderFactory docBuilder = DocumentBuilderFactory.newInstance();
			 DocumentBuilder builder=null;
			try {
				builder = docBuilder.newDocumentBuilder();
			} catch (ParserConfigurationException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			 
			 
			 Document doc = builder.newDocument();
			 Element shipingList = doc.createElement("shippings");
			 doc.appendChild(shipingList);
				for(LoggedEvent levent :event){
			  
		
			         Element log1 = doc.createElement("shipping");
			         shipingList.appendChild(log1);
		
			         /*Attr attr = doc.createAttribute("id");
			         attr.setValue(removeNull(Long.toString(sOrder.getId())));*/
			         
			         //.appendChild(log1);
			         
			         
			         
				        
			         
			         
			         Element orderId = doc.createElement("Message");
			         
			         orderId.appendChild(doc.createTextNode(removeNull(levent.getMessage())));
			         log1.appendChild(orderId);
			         
			         
		
			         Element billingStatus = doc.createElement("SystemLog");
			         billingStatus.appendChild(doc.createTextNode(removeNull(String.valueOf(levent.getSystemLog()))));
			         log1.appendChild(billingStatus);
		
			         Element billedWeight = doc.createElement("EventDate");
			         billedWeight.appendChild(doc.createTextNode(String.valueOf(levent.getEventDateTime())));
			         log1.appendChild(billedWeight);
			         
			        
			         
			        
			         
			        /* if(sOrder.getCarrierName()!=null){
			         Carrier findCarrier = carrierServiceManager.getCarrier(sOrder.getCarrierId());
			         Element carrier = doc.createElement("carrier");
			         carrier.appendChild(doc.createTextNode(removeNull(sOrder.getCarrierName())));
			         log1.appendChild(carrier);
			         }
			         else{
			        	 Element carrier = doc.createElement("carrier");
				         carrier.appendChild(doc.createTextNode(removeNull(sOrder.getCarrierName())));
				         log1.appendChild(carrier);
			         }*/
			         
			         
			         
			         
			         
			         
			         
			         
			        
			          
			           
		           
			}
				try{
				TransformerFactory transformerFactory = TransformerFactory.newInstance();
		          Transformer transformer = transformerFactory.newTransformer();
		           transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		           DOMSource source = new DOMSource(doc);
		           StreamResult result = new StreamResult(new File(shippingLabelFileName));
		           transformer.transform(source, result);
				}catch(Exception e){
					e.printStackTrace();
				}
				
				System.out.println("File saved!");
		 }
		 
		 private  void generateCsvFile_view_adm(List<LoggedEvent> event,FileWriter writer)
		   {
			try
			{
			   
			    writer.append("Message");
			    writer.append(',');
			    writer.append("SystemLog");
			    writer.append(',');
			    writer.append("EventDate");
			    writer.append(',');
			    writer.append("UserName");
			    
			    writer.append('\n');
			   
			  
			   /* writer.append("carrier");
			    writer.append(',');*/
			    
			    for(LoggedEvent levent :event){
			 
			    
		 
			    
			    writer.append(removeNull(levent.getMessage()));
			    writer.append(',');
		        writer.append(removeNull(String.valueOf(levent.getSystemLog())));   
		        writer.append(',');
		        writer.append(removeNull(String.valueOf(levent.getEventDateTime())));
		        writer.append(',');
		        writer.append(removeNull(String.valueOf(levent.getEventUsername())));
		        
		       
		        writer.append('\n');
			    //generate whatever data you want
		 
			    
			}
			
			writer.flush();
		    writer.close();
		    System.out.println("csv generated successfully");
			}
			catch(IOException e)
			{
			     e.printStackTrace();
			} 
		    }
		
		 public void createxlfile_view_adm(List<LoggedEvent> event,String shippingLabelFileName) throws IOException{
				
		        HSSFWorkbook workbook=new HSSFWorkbook();
		        HSSFSheet sheet =  workbook.createSheet("FirstSheet");  
		        HSSFRow rowhead=   sheet.createRow((short)0);
		        rowhead.createCell((short) 0).setCellValue("Message ");
		        rowhead.createCell((short) 1).setCellValue("System Log");
		        rowhead.createCell((short) 2).setCellValue("Event Date");
		        rowhead.createCell((short) 3).setCellValue("User Name");
		       
		       /*rowhead.createCell((short) 7).setCellValue("carrier");*/
		       
		        int i=1;
		        for(LoggedEvent levent :event){
		        	
		        HSSFRow row=   sheet.createRow((short)i);
		        row.createCell((short) 0).setCellValue(removeNull(String.valueOf(levent.getMessage())));
		        row.createCell((short) 1).setCellValue(removeNull(String.valueOf(levent.getSystemLog())));
		        row.createCell((short) 2).setCellValue(removeNull(String.valueOf(levent.getEventDateTime())));
		        row.createCell((short) 3).setCellValue(removeNull(String.valueOf(levent.getEventUsername())));
		        
		        /*if(sorder.getCarrierId()!=null){
		        Carrier findCarrier = carrierServiceManager.getCarrier(sorder.getCarrierId());
		        row.createCell((short) 6).setCellValue(removeNull(String.valueOf(findCarrier.getName())));
		        
		        }*/
		        i++;
				 }
		        
		        FileOutputStream fileOut =  new FileOutputStream(shippingLabelFileName);
		        workbook.write(fileOut);
		        fileOut.close();
		        System.out.println("Your excel file has been generated!");
		        

		    }
		 
		 public  void write_XML_File_view_adm(List<LoggedEvent> event,String shippingLabelFileName){
			 
				
			  
			 DocumentBuilderFactory docBuilder = DocumentBuilderFactory.newInstance();
			 DocumentBuilder builder=null;
			try {
				builder = docBuilder.newDocumentBuilder();
			} catch (ParserConfigurationException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			 
			 
			 Document doc = builder.newDocument();
			 Element shipingList = doc.createElement("shippings");
			 doc.appendChild(shipingList);
				for(LoggedEvent levent :event){
			  
		
			         Element log1 = doc.createElement("shipping");
			         shipingList.appendChild(log1);
		
			         /*Attr attr = doc.createAttribute("id");
			         attr.setValue(removeNull(Long.toString(sOrder.getId())));*/
			         
			         //.appendChild(log1);
			         
			         
			         Element orderId = doc.createElement("Message");
			         
			         orderId.appendChild(doc.createTextNode(removeNull(String.valueOf(levent.getMessage()))));
			         log1.appendChild(orderId);
			         
			         Element orderNum = doc.createElement("SystemLog");
			         orderNum.appendChild(doc.createTextNode(removeNull(String.valueOf(levent.getSystemLog()))));
			         log1.appendChild(orderNum);
		
			         Element billingStatus = doc.createElement("EventDate");
			         billingStatus.appendChild(doc.createTextNode(removeNull(String.valueOf(levent.getEventDateTime()))));
			         log1.appendChild(billingStatus);
		
			         Element billedWeight = doc.createElement("UserName");
			         billedWeight.appendChild(doc.createTextNode(removeNull(String.valueOf(levent.getEventUsername()))));
			         log1.appendChild(billedWeight);
			         
			         
			         
			        /* if(sOrder.getCarrierName()!=null){
			         Carrier findCarrier = carrierServiceManager.getCarrier(sOrder.getCarrierId());
			         Element carrier = doc.createElement("carrier");
			         carrier.appendChild(doc.createTextNode(removeNull(sOrder.getCarrierName())));
			         log1.appendChild(carrier);
			         }
			         else{
			        	 Element carrier = doc.createElement("carrier");
				         carrier.appendChild(doc.createTextNode(removeNull(sOrder.getCarrierName())));
				         log1.appendChild(carrier);
			         }*/
			         	           
		           
			}
				try{
				TransformerFactory transformerFactory = TransformerFactory.newInstance();
		          Transformer transformer = transformerFactory.newTransformer();
		           transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		           DOMSource source = new DOMSource(doc);
		           StreamResult result = new StreamResult(new File(shippingLabelFileName));
		           transformer.transform(source, result);
				}catch(Exception e){
					e.printStackTrace();
				}
				
				System.out.println("File saved!");
		 }
		  public List<CarrierChargeCode> getAjaxCarreierChargeList() {
			    return ajaxCarreierChargeList;
			  }

			  public void setAjaxCarreierChargeList(List<CarrierChargeCode> ajaxCarreierChargeList) {
			    this.ajaxCarreierChargeList = ajaxCarreierChargeList;
			  }

			  public String getNewChargeType() {
			    return newChargeType;
			  }

			  public void setNewChargeType(String newChargeType) {
			    this.newChargeType = newChargeType;
			  }
			  public void printManifest() {
				    ShippingOrder so = this.getShippingOrder();
				    User user = UserUtil.getMmrUser();
				    File file;
				    so.setCustomerId(user.getCustomerId());
				    Date date = new Date();
				    String fromDate = this.request.getParameter("shippingOrder.fromDate");
				    String toDate = this.request.getParameter("shippingOrder.toDate");
				    String carrierId = this.request.getParameter("shippingOrder.carrierId");
				    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
				    SimpleDateFormat sdf1 = new SimpleDateFormat("MMddyyyy");
				    DecimalFormat twoDForm = new DecimalFormat("#.##");
				    String serviceDate = sdf.format(date);
				    String manifestNo = sdf1.format(date);
				    String manifestFileName = fromDate + "_" + toDate + "_" + carrierId;

				    String shipperAddress;
				    String directory = (request.getSession().getServletContext().getRealPath("/") + "WEB-INF/classes/com/meritconinc/shiplinx/carrier/purolator/pdf/");
				    Boolean folderNew = (new File((request.getSession().getServletContext().getRealPath("/") + "WEB-INF/classes/com/meritconinc/shiplinx/carrier/purolator/pdf")).mkdir()); 
				    				    if(folderNew){
				    				    	log.debug("----->New Folder Created for EOD----> "+directory);
				    				    }
				     File f = new File(request.getSession().getServletContext().getRealPath("/")

				        + "WEB-INF/classes/com/meritconinc/shiplinx/carrier/purolator/pdf/"
				        + manifestFileName + ".pdf");
					  // FileUtils.cleanDirectory(directory);
					    try {
					     // FileUtils.cleanDirectory(directory);
					     file = new File(directory);
					      String[] myFiles;
					      if (file.isDirectory()) {
					        myFiles = file.list();
					        for (int i = 0; i < myFiles.length; i++) {
					          File myFile = new File(file, myFiles[i]);
					          myFile.delete();
					       }
					      }
					    } catch (Exception e) {
					      e.printStackTrace();
					    }


				    try {

				      // if (f.exists()) {
				      JasperDesign jasperDesign = JRXmlLoader
				          .load(request.getSession().getServletContext().getRealPath("/")
				              + "WEB-INF/classes/com/meritconinc/shiplinx/carrier/purolator/jasperreports/Manifest.jrxml");
				      // JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperDesign);
				      JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);

				      Map<String, Object> parameters = new HashMap<String, Object>();
				      List<ShippingOrder> order = this.shippingService.getShipments(so);
				      List<ManifestBean> manifestBean = new ArrayList<ManifestBean>();
				      DhlShipValidateResponse dhlObj = new DhlShipValidateResponse();
				      if (!user.getUserRole().equalsIgnoreCase("busadmin")||!UserUtil.getMmrUser().getUserRole().equals("solutions_manager")) {
				        Customer customer = this.customerService.getCustomerInfoByCustomerId(user.getCustomerId());
				        String userName = user.getUsername();
				        customer.getAddress().getAbbreviationName();
				        List<ShippingOrder> filteredList = new ArrayList<ShippingOrder>();
				        if (!user.getUserRole().equalsIgnoreCase("busadmin")||!UserUtil.getMmrUser().getUserRole().equals("solutions_manager")) {
          for (ShippingOrder filterObj : order) {
            if (filterObj.getStatusId() != ShiplinxConstants.STATUS_CANCELLED
                && filterObj.getStatusId() != ShiplinxConstants.STATUS_READYTOPROCESS
                && filterObj.getCustomerId() == user.getCustomerId()) {
              if (customer.getId() == filterObj.getCustomerId()
                  && customer.getAddress().getAddress2()
                     .equalsIgnoreCase(filterObj.getFromAddress().getAddress2())
                  && customer.getAddress().getCity()
                      .equalsIgnoreCase(filterObj.getFromAddress().getCity())) {
                filteredList.add(filterObj);
              }
            }

				          }
          order = filteredList;
				        }

				      }
				      // ManifestBean dummyManifest=new ManifestBean();
				      carrierId = order.get(0).getCarrierName() + " inc";
				      Double totalWeight = 0.0;
				      Double charge_HST_GST = 0.0;
				      Double charge_QST = 0.0;
				      Double charge_HST_GST_temp;
				      Double value_SH_DG = 0.0;
				      Double value_COD = 0.0;
				      Double total = 0.0;
				      int totalPieces = 0;
				      Double totalCharge = 0.0;
				      Double totalChargeTemp = 0.0;
				      for (int i = 0; i < order.size(); i++) {
				        ManifestBean dummyManifest = new ManifestBean();
				        charge_HST_GST_temp = 0.0;
				        totalChargeTemp = 0.0;
				        value_SH_DG = 0.0;
				        totalWeight += order.get(i).getTotalWeight();
				        totalPieces += order.get(i).getQuantity();
				        if (order != null && order.get(i).getCharges() != null
				            && !order.get(i).getCharges().isEmpty()) {
				          for (int j = 0; j < order.get(i).getCharges().size(); j++) {
				            if (order.get(i).getCharges().get(j).getStatus() != ShiplinxConstants.CHARGE_CANCELLED) {
				            	if (order.get(i).getCharges().get(j).getName() != null
                  && order.get(i).getCharges().get(j).getName().length() > 2
                 && (order.get(i).getCharges().get(j).getName().substring(0, 3).equals("HST") || (order
                      .get(i).getCharges().get(j).getChargeCodeLevel2() != null && order.get(i)
                     .getCharges().get(j).getChargeCodeLevel2().substring(0, 3).equals("HST")))) {

				                charge_HST_GST += order.get(i).getCharges().get(j).getCharge();
				                charge_HST_GST_temp += order.get(i).getCharges().get(j).getCharge();
				            	} else if (order.get(i).getCharges().get(j).getName() != null
                  && order.get(i).getCharges().get(j).getName().length() > 2
                  && order.get(i).getCharges().get(j).getName().substring(0, 3).equals("GST")) {

				                charge_HST_GST += order.get(i).getCharges().get(j).getCharge();
				                charge_HST_GST_temp += order.get(i).getCharges().get(j).getCharge();
				            	 } else if (order.get(i).getCharges().get(j).getName() != null
                  && order.get(i).getCharges().get(j).getName().length() > 2
                  && order.get(i).getCharges().get(j).getName().substring(0, 3).equals("QST")) {

				                charge_QST += order.get(i).getCharges().get(j).getCharge();
				                totalChargeTemp += order.get(i).getCharges().get(j).getCharge();
				              } else if ((order.get(i).getCharges().get(j).getName().length() > 15 && order.get(i)
				                  .getCharges().get(j).getName().toLowerCase().contains("handling"))
				                  || (order.get(i).getCharges().get(j).getName().length() > 5 && order.get(i)
				                      .getCharges().get(j).getName().toLowerCase().contains("danger"))) {
				                value_SH_DG += order.get(i).getCharges().get(j).getCharge();
				              } else {
				                totalCharge += order.get(i).getCharges().get(j).getCharge();
				                totalChargeTemp += order.get(i).getCharges().get(j).getCharge();
				              }
				              total += order.get(i).getCharges().get(j).getCharge();
				            }
				          }
				        }
				        // dhlObj.setOrderId(order.get(i).getId());
				        dhlObj.setOrderId(order.get(i).getId());
				        dummyManifest.setS_No(i + 1);
				        dummyManifest.setMasterTrackingNum(order.get(i).getMasterTrackingNum());
				        dummyManifest.setCosigneeAddress(order.get(i).getToAddress().getAbbreviationName() + " "
				            + order.get(i).getToAddress().getCity() + " "
				            + order.get(i).getToAddress().getProvinceCode() + " "
				            + order.get(i).getToAddress().getPostalCode());

				        dummyManifest.setProductType(order.get(i).getPackageTypeId().getName());
				        dummyManifest.setProductPieces(order.get(i).getQuantity());
				        dummyManifest.setProductWeight(order.get(i).getTotalWeight().toString());
				        DhlShipValidateResponse validateObj = this.shippingService.getDHLvalidateResponce(dhlObj);
				        if (validateObj != null && validateObj.getOrderId() != null) {
				          dummyManifest.setDeclaredValue(Double.parseDouble(twoDForm.format(validateObj
				              .getDutyDeclaredValue().doubleValue())));
				        } else {
				          dummyManifest.setDeclaredValue(0.0);
				        }
				        dummyManifest.setCharge_HST_GST_temp(Double.parseDouble(twoDForm
				            .format(charge_HST_GST_temp)));
				        dummyManifest.setSH_DG(Double.parseDouble(twoDForm.format(value_SH_DG)));
				        dummyManifest.setCOD_value(Double.parseDouble(twoDForm.format(order.get(i).getCODValue())));
				        dummyManifest.setTotalChargeTemp(Double.parseDouble(twoDForm.format(totalChargeTemp
            + value_SH_DG + charge_HST_GST_temp)));

				        manifestBean.add(dummyManifest);

				      }
				      shipperAddress = order.get(0).getFromAddress().getAbbreviationName() + " "
				          + order.get(0).getFromAddress().getCity() + " "
				          + order.get(0).getFromAddress().getProvinceCode() + " "
				          + order.get(0).getFromAddress().getPostalCode();
				      parameters.put("carrierInc", order.get(0).getCarrier().getName() + " inc");
				      parameters.put("serviceDate", serviceDate);
				      parameters.put("manifestNo", manifestNo);
				      parameters.put("shipperAddress", shipperAddress);
				      parameters.put("shipments", order.size());
				      parameters.put("weight", twoDForm.format(totalWeight));
				      parameters.put("pieces", totalPieces);
				      parameters.put("charge", twoDForm.format(totalCharge));
				      parameters.put("GST_HST", twoDForm.format(charge_HST_GST));
				      parameters.put("QST", twoDForm.format(charge_QST));
				      parameters.put("total", twoDForm.format(total));

				      JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(manifestBean);
				      JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, ds);
				      JasperExportManager.exportReportToPdfFile(jasperPrint, request.getSession()
				          .getServletContext().getRealPath("/")
				         + "WEB-INF/classes/com/meritconinc/shiplinx/carrier/purolator/pdf/"
				          + manifestFileName + ".pdf");
				      // JasperExportManager.exportReportToPdfFile(jasperPrint,
				      // "C:/Users/Mitosis/Desktop/Manifest/Manifest-"+manifestNo+".pdf");
				      // }
				      try{
				    	  				      file = new File(request.getSession().getServletContext().getRealPath("/")
				    		 + "WEB-INF/classes/com/meritconinc/shiplinx/carrier/purolator/pdf/"
				          + manifestFileName + ".pdf");
				      // checkPdf = null;
				      /*try {

				        inputStream = new DataInputStream(new FileInputStream(file));

				      } catch (FileNotFoundException e) {
				        e.printStackTrace();*/
				    	  				    //                                     ----->  Code for download Manifest PDF <-----
				    	  				    				      FileInputStream fileInputStream = new FileInputStream(file);
				    	  				    						OutputStream outputStream = response.getOutputStream();	
				    	  				    				        int read=0;
				    	  				    						byte[] bytes = new byte[1024];
				    	  				    				        while((read = fileInputStream.read(bytes))!= -1){
				    	  				    				        	outputStream.write(bytes, 0, read);
				    	  				    						}
				    	  				    				        outputStream.flush();
				    	  				    				        outputStream.close();
				    	  				    				      }
				    	  				    				      catch(FileNotFoundException e){
				    	  				    				    	  e.printStackTrace();
				      }
				      
				    } catch (Exception e) {
				      e.printStackTrace();
				    }

				  }


			public Map<String, Long> getCustomerSearchResults() {
				return customerSearchResults;
			}


			public void setCustomerSearchResults(Map<String, Long> customerSearchResults) {
				this.customerSearchResults = customerSearchResults;
			}
			 
			public String getUniqueTempPdfFileName(String fName)
					{
						Date curDateTime = new Date();
						String tempPath = System.getProperty("java.io.tmpdir");
						File path = new File( tempPath );
						if ( !path.exists() )
							path.mkdirs();
						
						return tempPath + File.separator + fName + curDateTime.getTime() + ".pdf";
					}
			
			public void addresslist(){
							     String searchParameter = "";
				
							     ShippingOrder shippingorder = this.getShippingOrder();
							     							     String countryCode = shippingorder.getToAddress().getCountryCode();
							     							     String customerId = shippingorder.getCustomerId().toString();
							     							     
							     							     List<Address> address = this.addressService.searchCustomsBrokerAddress(customerId, countryCode);
				
							        // First record is empty
							        importsSearchResult.put("", 0L);
				
							        for (Address cust : address) {
							        									         importsSearchResult.put(cust.getAbbreviationName(), cust.getAddressId());
							        									        }
				
							        session.put("importslist", importsSearchResult);    
							   }
			
			public String deleteInvoiceCharge(){
						String s=request.getParameter("id");
						Long id = 0L;
						if (s != null)
							id = Long.parseLong(s);
						try{
							if (id.longValue() > 0){ 
								Charge charge = shippingService.getChargeById(id);
																 long invoiceId= Long.valueOf(request.getParameter("invoiceId").toString());
																 Invoice invoice = invoiceManagerService.getInvoiceById(invoiceId);
																 shippingDAO = (ShippingDAO) MmrBeanLocator.getInstance().findBean("shippingDAO");
																 ChargeGroup chargeGroup = new ChargeGroup();
																 boolean chargeGroupFlag=false;
																 if(charge.getChargeGroupId()>0){
																  chargeGroup=shippingDAO.getChargeGroup(charge.getChargeGroupId());
																  chargeGroupFlag=true;
																	}
																 if(chargeGroupFlag){
																 if(chargeGroup!=null && !chargeGroup.isTax()){
																 invoice.setInvoiceCost(invoice.getInvoiceCost()-charge.getCost());
																 invoice.setInvoiceAmount(invoice.getInvoiceAmount()-charge.getCharge());
																 }
																 }else if(charge.getChargeGroupId()==0){
																	 invoice.setInvoiceCost(invoice.getInvoiceCost()-charge.getCost());
																	 invoice.setInvoiceAmount(invoice.getInvoiceAmount()-charge.getCharge());
																 }
																 if(charge!=null && charge.getChargeGroupId()>0){
																 if(chargeGroup!=null && chargeGroup.isTax()){
																      		invoice.setInvoiceTax(invoice.getInvoiceTax()-charge.getCharge());
																      	}
																 
							}
							invoiceDAO = (InvoiceDAO)MmrBeanLocator.getInstance().findBean("invoiceDAO");
															           this.getShippingService().deleteCharge(id);
																		 invoiceDAO.updateInvoiceCharges(invoice);
																		addActionMessage("invoice charge deleted successfuly..");
														//getInvoiceManagerService().getInvoiceById(invoiceId);
						}
						}catch(Exception e){
							e.printStackTrace();
							addActionError(getText("content.error.unexpected"));
						}
				
						return SUCCESS;
					}
			
			 ////////// ================= Exchange Rate =======================
			public ShippingOrder applyDefaultCurrencyValue(ShippingOrder order){
				Double exchRate=null;
				        if(order !=null && order.getCharges()!=null && order.getCharges().size()>0 && order.getCharges().get(0).getExchangerate()!=null){
					 	 exchRate=order.getCharges().get(0).getExchangerate().doubleValue();
				        }
					 /*if(exchRate!=null){
					
						if( order.getCODValue()!=null && order.getCODValue()>0){
							order.setCODValue(order.getCODValue()*exchRate);
						}
						if(order.getQuotedCharges()!=null && order.getQuotedCharges().size()>0){
							exchangeChargeRate(order.getQuotedCharges());
						}
						if(order.getActualCharges()!=null && order.getActualCharges().size()>0){
							exchangeChargeRate(order.getActualCharges());
						}
						if(order.getQuoteTotalCost()!=null && order.getQuoteTotalCost()>0){
							order.setQuoteTotalCost(multiplyWithExchangeRate(order.getQuoteTotalCost(),exchRate));
						}
						if(order.getQuoteTotalCharge()!=null && order.getQuoteTotalCharge()!=0){
							order.setQuoteTotalCharge(multiplyWithExchangeRate(order.getQuoteTotalCharge(),exchRate));
						}
						if(order.getActualTotalCharge()!=null && order.getActualTotalCharge()>0){
							order.setQuoteTotalCost(multiplyWithExchangeRate(order.getQuoteTotalCost(),exchRate));
						}
						if(order.getActualTotalCost()!=null && order.getActualTotalCost()>0){
							order.setQuoteTotalCharge(multiplyWithExchangeRate(order.getQuoteTotalCharge(),exchRate));
						}
						//order.setQuoteTotalCharge(order.getq);
					 }*/
						return order;
					}
					
					public Double multiplyWithExchangeRate(Double origCharge,Double exchRate){
						Double product=0.0;
						if(origCharge!=null && origCharge!=0){
						 product = new BigDecimal(origCharge*exchRate).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
						}else{
							product=null;
						}
						return product;
					}
					
					public void exchangeChargeRate(List<Charge> charges){
						Double exchRate=null;
				        if(charges !=null && charges.size()>0 && charges.get(0).getExchangerate()!=null){
					 	 exchRate=charges.get(0).getExchangerate().doubleValue();
				        }
				        if(exchRate!=null && !exchRate.equals(0.0)){
						for(Charge charge:charges){
								
								if((multiplyWithExchangeRate(charge.getCharge(),exchRate))!=null)
									charge.setCharge(multiplyWithExchangeRate(charge.getCharge(),exchRate));
								if((multiplyWithExchangeRate(charge.getCost(),exchRate))!=null)
									charge.setCost(multiplyWithExchangeRate(charge.getCost(),exchRate));
								if((multiplyWithExchangeRate(charge.getTariffRate(),exchRate))!=null)
									charge.setTariffRate(multiplyWithExchangeRate(charge.getTariffRate(),exchRate));
						}
					}
					}
					public List<KeyValueVO> getCachedList() {
						return cachedList;
					}

					public void setCachedList(List<KeyValueVO> cachedList) {
						this.cachedList = cachedList;
					}
					
					public void putUomToSeession() {
						User user1 = UserUtil.getMmrUser();
						userDAO = (UserDAO) MmrBeanLocator.getInstance().findBean("userDAO");
						uom = userDAO.unitOfMeasure();
						User unitofmeasure = userDAO.findunitofmeasure(user1.getUsername());
						if (user1 != null && unitofmeasure.getUnitmeasure() == 2) {
							for (int i = 0; i < uom.size(); i++) {
								if (unitofmeasure != null
										&& unitofmeasure.getUnitmeasure() == uom.get(i)
												.getUnitOfMeasureId()) {
									Collections.swap(uom, 0, i);
								}
							}
					}
						getSession().put("UOM", uom);
					}	
			/// ============== End =======================				
}
