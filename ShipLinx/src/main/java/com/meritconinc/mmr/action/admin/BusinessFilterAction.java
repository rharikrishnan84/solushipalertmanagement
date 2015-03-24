 package com.meritconinc.mmr.action.admin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.meritconinc.mmr.action.BaseAction;
import com.meritconinc.mmr.constants.Constants;
import com.meritconinc.mmr.dao.BusinessFilterDAO;
import com.meritconinc.mmr.utilities.MessageUtil;
import com.meritconinc.mmr.utilities.security.UserUtil;
import com.meritconinc.shiplinx.dao.AddressDAOImpl;
import com.meritconinc.shiplinx.dao.BusinessDAO;
import com.meritconinc.shiplinx.model.Address;
import com.meritconinc.shiplinx.model.Branch;
import com.meritconinc.shiplinx.model.Business;
import com.meritconinc.shiplinx.model.Country;
import com.meritconinc.shiplinx.model.CountryPartner;
import com.meritconinc.shiplinx.model.Customer;
import com.meritconinc.shiplinx.model.Filter;
import com.meritconinc.shiplinx.model.Partner;
import com.meritconinc.shiplinx.model.RelPartnerFilter;
import com.meritconinc.shiplinx.model.UserFilter;
import com.meritconinc.shiplinx.service.CustomerManager;
import com.meritconinc.shiplinx.utils.ShiplinxConstants;
import com.opensymphony.xwork2.Preparable;
import com.soluship.businessfilter.util.BusinessFilterUtil;
/***
 * @author selva
 * 
 */
public class BusinessFilterAction extends BaseAction implements Preparable,
		ServletRequestAware, ServletResponseAware {
 
	private static final long serialVersionUID = 1L;
	private static final Logger log = LogManager.getLogger(UserListAction.class);
	public HttpServletRequest request;
	BusinessFilterDAO businessFilterDAO;
	private AddressDAOImpl addressDAO;
	public HttpServletResponse response;
	private List<Partner> partnerList;
	private List<Branch> branchList;
	private List<String> countryList;
	private Partner partner;
	private List<String> ajaxcountryNameList;
	private List<CountryPartner> ajaxCountryPartnerList;
	private static List<Integer> countryidls = new ArrayList<Integer>();
	private Branch branch;
	private String countryName;
	private List<Filter> filterList;
	private List<Customer> allCustomerList;
	private List<Customer> branchCustomerList = new ArrayList<Customer>();
	private List<String> ajaxbranchNameList;
	private List<Customer> ajaxFilterCustomers;
	private CustomerManager customerService;
	private List<Integer> listint;
	private Filter filter;
	private String filterCountry;
	private List<Country> partnerCountryList = new ArrayList<Country>();
	private List<Country> allCountryList = new ArrayList<Country>();
	private List<Partner> ajaxPartnerList;
	private List<RelPartnerFilter> Relpartnerfils;
    private BusinessDAO businessDAO;
    private CountryPartner countrypartner;
    private List<CountryPartner> countryPartnerList;
    private UserFilter userFilter;
	public Partner getPartner() {
		return partner;
	}

	public void setPartner(Partner partner) {
		this.partner = partner;
	}

	public BusinessFilterDAO getBusinessFilterDAO() {
		return businessFilterDAO;
	}

	public void setBusinessFilterDAO(BusinessFilterDAO businessFilterDAO) {
		this.businessFilterDAO = businessFilterDAO;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public void setServletResponse(HttpServletResponse httpServletResponse) {
		this.response = httpServletResponse;
	}

	@Override
	public void prepare() throws Exception {
		// TODO Auto-generated method stub

	}

	public List<Partner> getPartnerList() {
		return partnerList;
	}

	public void setPartnerList(List<Partner> partnerList) {
		this.partnerList = partnerList;
	}

	public String list() {
		Long businessId = (Long)getSession().get(Constants.BUSINESS_ID_SESSION);
		// ** code for adding partner list **//
		if(businessId!=null ){
			getSession().put(ShiplinxConstants.SESSION_BUSINESSFILTER_CUSTOMERID,BusinessFilterUtil.getBusinessLevelCustomers(businessId));
        
			Business bs=businessDAO.getBusiessById(businessId);
			partnerList=new ArrayList<Partner>();
		if(bs!=null   && bs.getPartnerId()!=0){	
			partnerList.add(businessFilterDAO.getPartnerById(bs.getPartnerId()));
		}
		
		return SUCCESS;
		}else if(UserUtil.getMmrUser().getUserRole().equals("busadmin")){
			getSession().put(ShiplinxConstants.SESSION_BUSINESSFILTER_CUSTOMERID,BusinessFilterUtil.getBusinessLevelCustomers(UserUtil.getMmrUser().getBusinessId()));
			partnerList=new ArrayList<Partner>();
			Business bs=businessDAO.getBusiessById(UserUtil.getMmrUser().getBusinessId());
			if(bs!=null   && bs.getPartnerId()!=0){	
				partnerList.add(businessFilterDAO.getPartnerById(bs.getPartnerId()));
			}
			
			return SUCCESS;
		}else{
		//	partnerList=businessFilterDAO.getPartnerlist();
			addActionMessage(getText("Please Select the business"));
			return "fail";
			
		}
	}
	
	
	public String listAllPartner(){
		
		partnerList=businessFilterDAO.getAllPartnerList();
		getSession().put("allPartner","true");
		return SUCCESS;
	}
	
	public String listAllBranch(){
		branchList=businessFilterDAO.getAllBranchList();
		getSession().put("allBranch1", "true");
		return SUCCESS;
	}
	 public String listCountryPartner(){
		  Long partnerId = (Long) getSession().get("partnerId");
		  Long businessId=(Long) getSession().get(Constants.BUSINESS_ID_SESSION);
		  
		   if(partnerId !=null && getSession().get(Constants.BUSINESS_ID_SESSION)!=null && UserUtil.getMmrUser().getUserRole().equals(ShiplinxConstants.ROLE_SYSADMIN)){
				getSession().put(ShiplinxConstants.SESSION_BUSINESSFILTER_CUSTOMERID,BusinessFilterUtil.getPartnerLevelCustomers(businessId, partnerId));
	         countryPartnerList=businessFilterDAO.getCountryPartnerList(partnerId);
	         
			   for(CountryPartner cp:countryPartnerList){
				   cp.setPartner(businessFilterDAO.getPartnerById(cp.getPartnerId()));
				   //cp.setCountryAddress((addressDAO.findAddressById(String.valueOf(cp.getAddressId()))));
	   			   cp.setBusiness(businessDAO.getBusiessById(cp.getBusinessId()));
			   }
				Business bs=businessDAO.getBusiessById(businessId);
				if(bs!=null && bs.getCountryPartnerId()!=0){
					countrypartner=businessFilterDAO.getCountryPartnerById(bs.getCountryPartnerId());
					countrypartner.setPartner(businessFilterDAO.getPartnerById(countrypartner.getPartnerId()));
					countrypartner.setBusiness(bs);
				}
				countryPartnerList=new ArrayList<CountryPartner>();
				countryPartnerList.add(countrypartner);
			   Partner part=businessFilterDAO.getPartnerById(partnerId);
			   log.info("INTO THE PARTER LELVEL FOR A PARTNER : "+part.getPartnerName());
			   log.debug(" Business Id :"+ businessId+" partner Id :"+ partnerId);
			   return SUCCESS;  
		   }else if(UserUtil.getMmrUser().isBusinessLevel() || UserUtil.getMmrUser().isPartnerLevel()){
			   userFilter=businessFilterDAO.getUserFilterByUsername(UserUtil.getMmrUser().getUsername());
			   if(partnerId!=null&& partnerId>0){
				   getSession().put(ShiplinxConstants.SESSION_BUSINESSFILTER_CUSTOMERID,BusinessFilterUtil.getPartnerLevelCustomers(userFilter.getBusinessId(),  partnerId));
			   }
			   if(partnerId==null){
				  userFilter=businessFilterDAO.getUserFilterByUsername(UserUtil.getMmrUser().getUsername());
				  countryPartnerList=businessFilterDAO.getCountryPartnerList(userFilter.getPartnerId());
				  for(CountryPartner cp:countryPartnerList){
					   cp.setPartner(businessFilterDAO.getPartnerById(cp.getPartnerId()));
					   //cp.setCountryAddress((addressDAO.findAddressById(String.valueOf(cp.getAddressId()))));
		   			   cp.setBusiness(businessDAO.getBusiessById(cp.getBusinessId()));
				   }
				  countryPartnerList=new ArrayList<CountryPartner>();
					Business bs=businessDAO.getBusiessById(UserUtil.getMmrUser().getBusinessId());
					if(bs!=null && bs.getCountryPartnerId()!=0){
						countrypartner=businessFilterDAO.getCountryPartnerById(bs.getCountryPartnerId());
						countrypartner.setPartner(businessFilterDAO.getPartnerById(countrypartner.getPartnerId()));
						countrypartner.setBusiness(bs);
					}
					countryPartnerList.add(countrypartner);
	     		   }else if(partnerId!=null && partnerId>0){
				     	   countryPartnerList=businessFilterDAO.getCountryPartnerList(partnerId);
						   for(CountryPartner cp:countryPartnerList){
							   cp.setPartner(businessFilterDAO.getPartnerById(cp.getPartnerId()));
							   //cp.setCountryAddress((addressDAO.findAddressById(String.valueOf(cp.getAddressId()))));
				   			   cp.setBusiness(businessDAO.getBusiessById(cp.getBusinessId()));
						   }
	     			   
	     			  countryPartnerList=new ArrayList<CountryPartner>();
	  				Business bs=businessDAO.getBusiessById(UserUtil.getMmrUser().getBusinessId());
	  				if(bs!=null && bs.getCountryPartnerId()!=0){
	  					countrypartner=businessFilterDAO.getCountryPartnerById(bs.getCountryPartnerId());
	  					countrypartner.setPartner(businessFilterDAO.getPartnerById(countrypartner.getPartnerId()));
	  					countrypartner.setBusiness(bs);
	  				}
	  				countryPartnerList.add(countrypartner);
	     		   }
			   if(partnerId!=null){
			   Partner part=businessFilterDAO.getPartnerById(partnerId);
			   log.info("INTO THE PARTER LELVEL FOR A PARTNER : "+part.getPartnerName());
			   log.debug(" Business Id :"+ businessId+" partner Id :"+ partnerId);
			   }
			   return SUCCESS;
		   }else {
			
			return "fail";
		    }
		   
		   
	   }
	   
   
   public String logInAsBusiness(){
	   if(request.getParameter("id")!=null || getSession().get(Constants.BUSINESS_ID_SESSION)!=null ){
	        long businessId;
	        businessId=Long.parseLong(request.getParameter("id"));
	        getSession().put(Constants.BUSINESS_ID_SESSION, businessId);
	        Business bus=businessDAO.getBusiessById(businessId);
	        log.info("INTO TO THE  BUSINESS LEVEL FOR BUSINESS :"+bus.getSystemName());
	        log.debug("Business Id+ :"+bus.getId());
	        
	        //getSession().remove(Constants.BUSINESS_ID_SESSION);
			return SUCCESS;
			}else if(UserUtil.getMmrUser().getUserRole().equals("busadmin")){
				return SUCCESS;
			}else{
				addActionMessage(getText("Please Select the business"));
				return "fail";
			}
   }
   public String logInAsPartner(){
	   if(request.getParameter("partnerParam")!=null && getSession().get(Constants.BUSINESS_ID_SESSION)!=null){
		   long partnerId=Long.parseLong(request.getParameter("partnerParam"));
		   getSession().put("partnerId", partnerId);
		   
		   
		   return SUCCESS;
	   }else if(UserUtil.getMmrUser().isBusinessLevel() ){
		   long partnerId=Long.parseLong(request.getParameter("partnerParam"));
		   getSession().put("partnerId", partnerId);
		   
		   return SUCCESS;
	   }else if(UserUtil.getMmrUser().isPartnerLevel()){
		   long partnerId=Long.parseLong(request.getParameter("partnerParam"));
		   getSession().put("partnerId", partnerId);
		   return SUCCESS;
   		}else {
		
		return "fail";
	}
   }
   
   
   
   public String logInAsCountry(){
	   if(request.getParameter("countryparam")!=null && getSession().get(Constants.BUSINESS_ID_SESSION)!=null && getSession().get("partnerId")!=null){
			long countryPartnerId=Long.parseLong(request.getParameter("countryparam"));
			getSession().put("countryPartnerId", countryPartnerId);
			return SUCCESS;
		}else if(UserUtil.getMmrUser().isBusinessLevel() || UserUtil.getMmrUser().isPartnerLevel()){
			long countryPartnerId=Long.parseLong(request.getParameter("countryparam"));
			getSession().put("countryPartnerId", countryPartnerId);
			return SUCCESS;	
		}
			addActionMessage(getText("please select login as Partner to access Branch"));
			//branchList = businessFilterDAO.getBranchList();
		    return "fail";
   }
   public String logInAsBranch(){
	  if(UserUtil.getMmrUser().getUserRole().equals(ShiplinxConstants.ROLE_SYSADMIN) || UserUtil.getMmrUser().isBusinessLevel()
			  	|| UserUtil.getMmrUser().isPartnerLevel() || UserUtil.getMmrUser().isNationLevel()){ 
		  return SUCCESS;
	  }else{
		  return "fail";
	  }
   }
   public String newCountry(){
	   log.debug(" CHECK METHOD IN newCountry" );
	   partnerList=businessFilterDAO.getPartnerList((Long)getSession().get(Constants.BUSINESS_ID_SESSION));
	   partner=businessFilterDAO.getPartnerById((Long)getSession().get(Constants.PARTNER_ID_SESSION));
	   getSession().put("CountryList", MessageUtil.getCountriesList()); 
	   
	   return SUCCESS;
   }
	public String saveBranch() {
		branch = getBranch();
		 log.debug(" CHECK METHOD IN SAVE BRANCH ***********   " + request.getParameter("method"));
		if (request.getParameter("method").equals("add")) {
				    long businessId=(Long)getSession().get(Constants.BUSINESS_ID_SESSION);
				    long countryParterId=(Long)getSession().get("countryPartnerId");
				    
				    if(true){
				    	
				    	partner=businessFilterDAO.getPartnerById((Long)getSession().get("partnerId"));
						countrypartner=businessFilterDAO.getCountryPartnerById((Long)getSession().get("countryPartnerId"));
						getSession().put("CountryList", MessageUtil.getCountriesList()); 
				    	return INPUT;	
		    }
		    else {
		    	
		    	   if(getSession().get("allBranch").equals("true")){
		    		   long parterId=Long.parseLong(request.getParameter("branch.partnerName"));
			    	    String country=request.getParameter("countryName");
			    	    branch.setPartnerId(parterId);
 			    	    long countryPartnerId=businessFilterDAO.getCountryPartnerId(country,parterId);
			        	branch.setBusinessId(UserUtil.getMmrUser().getBusinessId());
			        	long addressId=addressDAO.add(branch.getBranchAddress());
			        	branch.setAddressId(addressId);
			        	branch.setCountryPartnerId(countryPartnerId);
			        	businessFilterDAO.addBranch(branch);
			        	addActionMessage(getText("Branch.info.save.successfully"));
			        	branchList=businessFilterDAO.getAllBranchList();
		    	   }else {
			    	    long addressId=addressDAO.add(branch.getBranchAddress());
					    countrypartner=businessFilterDAO.getCountryPartnerById(countryParterId);
					    branch.setPartnerId(countrypartner.getPartnerId());
					    branch.setAddressId(addressId);
					    branch.setBusinessId(businessId);
						branch.setCountryPartnerId(countryParterId);
				        businessFilterDAO.addBranch(branch);
						Business b=businessDAO.getBusiessById(businessId);
					    CountryPartner c=businessFilterDAO.getCountryPartnerById(countryParterId);
						addActionMessage("Branch Success Fully Added for Country : "+branch.getBranchAddress().getCountryName() +" Business :"+b.getName());
					    branchList=businessFilterDAO.getBranchList(countryParterId)	;
		    	   }
		    }
		    
		} else if (request.getParameter("method").equals("update")) {
			Branch branch1=(Branch) getSession().get("Branch");
			long businessId=(Long)getSession().get(Constants.BUSINESS_ID_SESSION);
			//countrypartner.setBusinessId(businessId);
			long addressId=branch1.getBranchAddress().getAddressId();
			Address address=addressDAO.findAddressById(String.valueOf(addressId));
			addressDAO.edit(BusinessFilterUtil.setAddress(branch.getBranchAddress(),address));
			branch.setBranchId(branch1.getBranchId());
			//branch.setBusinessId(businessId);
			
			businessFilterDAO.updateBranch(branch);
		    long countryParterId=(Long)getSession().get("countryPartnerId");
			branchList=businessFilterDAO.getBranchList(countryParterId);
			
    		   addActionMessage(getMessage("Nation info updated"));
			
		}
		return SUCCESS;
	}

	private boolean validateBranch1() {
		// TODO Auto-generated method stub
		int i=0;
		 String parterId= request.getParameter("branch.partnerName");
 	     String countryPartnerId= request.getParameter("user.userFilter.countryPartnerId");
 	     if(parterId==null || parterId.equals("-1")){
 	    	addActionError(getText("Select Partner"));
			i++;
 	     }
 	     if(countryPartnerId==null || countryPartnerId.equals("-1") ){
 	    	addActionError(getText("Select Country"));
			i++;
 	     }
		if(businessFilterDAO.getBranchByShortCode(branch.getShortCode())!=null){
			addActionError(getText("Branch Short Code Already in use try some other"));
			i++;
			return true;
		}else if(branch.getShortCode().equals("")){
			addActionError(getText("Branch Short Code is Required"));
			i++;
		}else if(branch.getShortCode().length()>15 || branch.getShortCode().length()<3){
			addActionError(getText("Branch Short Code length must be  with in 3 to 15"));
			i++;
		}
		
		if(branch.getBranchName().equals("")){
			addActionError(getText("Branch Name is Required"));
			i++;
		}else if(branch.getBranchName().length()<3 || branch.getBranchName().length()>20){
			addActionError(getText("Branch Name must be with in  3 to 20"));
			i++;
		}
		
		if(branch.getBranchAddress().getEmailAddress().equals("")){
			addActionError(getText("Email is Required"));
			i++;
		}else if(!branch.getBranchAddress().getEmailAddress().matches(ShiplinxConstants.EMAIL_REGEX)){
			addActionError(getText("Email Address is Not a valid one"));
			i++;
		}
		
		if(branch.getBranchAddress().getPhoneNo().equals("")){
			addActionError(getText("Phone Number is Requried"));
			i++;
		}
		
		
		if(i==0){
			return false;
		}else if(i>0){
			return true;
		}
		return false;
	}

	public String deleteBranch() {

		String partnerId = request.getParameter("name");
		businessFilterDAO.deleteBranchByPartnerId(partnerId);
	//	branchList = businessFilterDAO.getBranchList();

		return SUCCESS;
	}

	public String deleteFilter() {

		long filterId = Long.parseLong(request.getParameter("name"));
		businessFilterDAO.deleteFilter(filterId);

		filterList = businessFilterDAO.getFilterList();
		return SUCCESS;
	}

	public String editBranch() {

		String branchId = request.getParameter("partnerId");
		branch = businessFilterDAO.getBranchByBranchId(branchId);
		getSession().put("Branch", branch);
		getSession().put("edit", "true");
		getSession().put("CountryList", MessageUtil.getCountriesList());
		countrypartner=businessFilterDAO.getCountryPartnerById(branch.getCountryPartnerId());
		
		//partnerList=businessFilterDAO.getPartnerList((Long)getSession().get(Constants.BUSINESS_ID_SESSION));
		getSession().put("edit", "true");
		return SUCCESS;
	}

	public String save() {
		partner = getPartner();
		 log.debug(" CHECK METHOD IN SAVE PARTNER ***********   " + request.getParameter("method"));
		if (request.getParameter("method").equals("add")) {
				if(validatePartner()){
					
						getSession().remove("edit");
						getSession().put("CountryList", MessageUtil.getCountriesList());
						allCountryList = (List<Country>) getSession().get("CountryList");
						return INPUT;
						
					
				}else if(getSession().get(Constants.BUSINESS_ID_SESSION)!=null){
					
					    long businessId=(Long)getSession().get(Constants.BUSINESS_ID_SESSION);
					    long addressId=addressDAO.add(partner.getPartnerAddress());
					    partner.setAddressId(addressId);
					    partner.setEmail(partner.getPartnerAddress().getEmailAddress());
					    partner.setBusinessId(businessId);
						businessFilterDAO.addPartner(partner);
						Business b=businessDAO.getBusiessById(businessId);
						addActionMessage("Partner Success Fully Added Under Business :"+b.getName());
				} 
				
		} else if (request.getParameter("method").equals("update")) {
			// update
              Partner partner1=(Partner) getSession().get("partner");
              
	          addressDAO.edit(partner.getPartnerAddress());
	          partner.setBusinessId(partner1.getBusinessId());
	          partner.setPartnerId(partner1.getPartnerId());
			 businessFilterDAO.update(partner);
			 addActionMessage(getText("partner.info.update.successfully"));
			//partnerList = businessFilterDAO.getPartnerList();

		}
		return SUCCESS;
	}
	
	public String SavePartnerAll(){
        partner=this.getPartner();
        if (request.getParameter("method").equals("add") && getSession().get("allPartner").equals("true")) {
           if(validatePartner()){
        		getSession().remove("edit");
				getSession().put("CountryList", MessageUtil.getCountriesList());
				allCountryList = (List<Country>) getSession().get("CountryList");
				return INPUT;
           }else{
           
			String cpls=request.getParameter("partnerCs");
	    	  if (cpls.length() > 0 && cpls.charAt(cpls.length()-1)==',') {
	    		  cpls = cpls.substring(0, cpls.length()-1);
	    		  }
	    	  String[] countryCodes=cpls.split(",");
	    	List<String> countryCodeList=Arrays.asList(countryCodes);
	    	 
	    	List<Integer> Countryids=new ArrayList<Integer>();
	     
	    	 List<String> countryCodeLt = new ArrayList(new HashSet(countryCodeList));
	       	 partner.setBusinessId(UserUtil.getMmrUser().getBusinessId());
	       	 businessFilterDAO.addPartnerWithCountryList(partner,countryCodeList);
	    	 addActionMessage(getText("partner.info.save.successfully"));
	    	 countryidls.clear();
           }
        } else if (request.getParameter("method").equals("update")&& getSession().get("allPartner").equals("true")) {
			// update
        	 List<Country> tmp=new ArrayList<Country>();
       	  List<Country> newCountry=new ArrayList<Country>();
       	  List<Country> oldCountry=new ArrayList<Country>();
       	  String cpls=request.getParameter("partnerCs");
       	  if (cpls.length() > 0 && cpls.charAt(cpls.length()-1)==',') {
       		  cpls = cpls.substring(0, cpls.length()-1);
       		  }
       	  String[] countryCodes=cpls.split(",");
       	  long partnerId=(Long) getSession().get("partnerId");
       	  partner.setPartnerId(partnerId);
       	  List<Long> partnerCountryIds=businessFilterDAO.getpartnerCountryIds(partnerId);
       	  
       	  for(long cid:partnerCountryIds){
       			tmp.add(businessFilterDAO.getCountryById(cid));
       		}
       	  for(String code:countryCodes){
       		  partnerCountryList.add(businessFilterDAO.getCountryObjByCode(code));
       		  
       	  }
       	  for(Country c:partnerCountryList){
       		  newCountry.add(c);
       	  }
       	  for(Country c:tmp){
       		  oldCountry.add(c);
       	  }
       	  Iterator itr = partnerCountryList.iterator();
          	  Iterator itr3 = oldCountry.iterator();
       	  while(itr.hasNext()){
       		  Country c=(Country) itr.next();
       			for(int i=0;i<tmp.size();i++){
       				if(tmp.get(i).getCountryCode().equals(c.getCountryCode())){
       					itr.remove();
       				}
       			}
       	  }
       	  while(itr3.hasNext()){
       		     Country c=(Country) itr3.next();
       			for(int i=0;i<newCountry.size();i++){
       				if(newCountry.get(i).getCountryCode().equals(c.getCountryCode())){
       					itr3.remove();
       				}
       			}
       		  
       	  }
       	  List<Integer> newCountryids=new ArrayList<Integer>();
       	  for(Country c:partnerCountryList){
       		 newCountryids.add(businessFilterDAO.getCountryIdByCode(c.getCountryName())); 
       		 
       	  }
       	 // businessFilterDAO.addCountryPartnerById(partner.getPartnerId(), newCountryids);
       	  newCountryids.clear();
       	  for(Country c:oldCountry){
        		 newCountryids.add(businessFilterDAO.getCountryIdByCode(c.getCountryName())); 
        		 
        	  }
       	  businessFilterDAO.deleteCountryPartnerById(partner.getPartnerId(), newCountryids);
       	  partner.setBusinessId(UserUtil.getMmrUser().getBusinessId());
       	  businessFilterDAO.update(partner);
       	  addActionMessage(getText("partner.info.update.successfully"));
        } 
		partnerList=businessFilterDAO.getAllPartnerList();
		getSession().put("allPartner","true");
		return "success1";
	}
  public String saveBranchAll(){
	  
	  branch =getBranch();
      if(request.getParameter("method").equals("add")){
    	  //add
    	  if(validateBranch1()){
    		    partnerList = businessFilterDAO.getAllPartnerList();
				getSession().put("CountryList", MessageUtil.getCountriesList()); 
		    	return "fail";
    	  }else{
    	    long parterId=Long.parseLong(request.getParameter("branch.partnerName"));
    	    //String country=request.getParameter("user.userFilter.countryPartnerId");
    	    branch.setPartnerId(parterId);
    	    
//    	    partner=businessFilterDAO.getPartnerById(parterId);
//    	    branch.setPartnerName(partner.getPartnerName());
    	    long countryPartnerId=Long.parseLong(request.getParameter("user.userFilter.countryPartnerId"));
        	branch.setBusinessId(UserUtil.getMmrUser().getBusinessId());
        	long addressId=addressDAO.add(branch.getBranchAddress());
        	branch.setAddressId(addressId);
        	branch.setCountryPartnerId(countryPartnerId);
        	businessFilterDAO.addBranch(branch);
        	addActionMessage(getText("Branch.info.save.successfully"));
        	branchList=businessFilterDAO.getAllBranchList();
        	 return "success1";
    	  }
      }
      else if(request.getParameter("method").equals("update")){
    	  //update
    	    long parterId=Long.parseLong(request.getParameter("branch.partnerName"));
    	    String country=request.getParameter("countryName");
    	    branch.setPartnerId(parterId);
    	    branch.setBranchId(Long.parseLong((String) getSession().get("branchId")));
    	    getSession().remove("branchId");
//    	    partner=businessFilterDAO.getPartnerById(parterId);
//    	    branch.setPartnerName(partner.getPartnerName());
    	    long countryPartnerId=businessFilterDAO.getCountryPartnerId(country,parterId);
    	    branch.setCountryPartnerId(countryPartnerId);
    	    addressDAO.edit(branch.getBranchAddress());
    	    branch.setBusinessId(UserUtil.getMmrUser().getBusinessId());
    	    businessFilterDAO.updateBranch(branch);
    	    addActionMessage(getText("Branch.info.update.successfully"));
    	    branchList=businessFilterDAO.getAllBranchList();
      }
		
      
      return "success1";
	  
  }
	private boolean validatePartner() {
		// TODO Auto-generated method stub
		
		 
		int i=0;
		String[] countryCodes = null;
		String cpls=request.getParameter("partnerCs");
		if(cpls!=null){
  	  if (cpls.length() > 0 && cpls.charAt(cpls.length()-1)==',') {
  		  cpls = cpls.substring(0, cpls.length()-1);
  		  }
  	  countryCodes=cpls.split(",");
		}else{
			addActionError(getText("Select Country"));
			i++;
		}
  	   
		if(cpls!=null && cpls.equals("")){
				addActionError(getText("Select Country"));
				i++;
		 
		}
  	  
  	  
		if(partner.getShortCode().equals("")){
			addActionError(getText("Short Code Required"));
			i++;
		}else if(businessFilterDAO.getpartnerByPartnerShortCode(partner.getShortCode())!=null){
			addActionError(getText("Short Code Already Taken try some other"));
		    return true;
		}else if(partner.getShortCode().length()>15 || partner.getShortCode().length()<3){
			addActionError(getText("Short Code length must be with in 3 to 15"));
			i++;
		}
		
		if(partner.getPartnerName().equals("")){
			addActionError("Parnter Name Required");
			i++;
		}else if(partner.getPartnerName().length()>30 || partner.getPartnerName().length()<3){
			addActionError(getText("Partner Name length must to be with in 3 to 30"));
			i++;
		}
		
		if(partner.getEmail().equals("")){
			addActionError(getText("Email is Required"));
			i++;
		}else {
			if(!partner.getEmail().matches(ShiplinxConstants.EMAIL_REGEX)){
				addActionError(getText("Email is not a valid one"));
				i++;
			}
			
		}
		
		if(partner.getPhoneNumber().equals("")){
			addActionError(getText("Phone Number is Required"));
			i++;
		}
		
		if(i==0){
			return false;
		}else if(i>0) {
			return true;
		}
		
		
		return false;
	}

	public String edit() {

		long partnerId = Long.parseLong(request.getParameter("name"));
		getSession().put("CountryList", MessageUtil.getCountriesList());
		partner = businessFilterDAO.getPartnerById(partnerId);
		getSession().put("partner",partner);
		getSession().put("edit", "true");
		return SUCCESS;
	}
	
	
	public String editCountry(){
	   	long cPId=  Long.parseLong(request.getParameter("name"));
		getSession().put("CountryList", MessageUtil.getCountriesList());
		countrypartner=businessFilterDAO.getCountryPartnerById(cPId);
		countrypartner.setPartner(businessFilterDAO.getPartnerById(countrypartner.getPartnerId()));
		getSession().put("countryPartner", countrypartner);
		partnerList=businessFilterDAO.getPartnerList((Long)getSession().get(Constants.BUSINESS_ID_SESSION));
		getSession().put("edit", "true");
		return SUCCESS;
	}

	public String deletePartner() {

	//	long partnerId = Long.parseLong(request.getParameter("name"));
	//	businessFilterDAO.deletePartnerById(partnerId);
		//addActionMessage(getText("partner.info.delete.successfully"));
		//partnerList = businessFilterDAO.getPartnerList();
		return SUCCESS;
	}

	public String newPartner() {
		log.debug(" CHECK METHOD IN newPartner" );
		getSession().remove("edit");
		getSession().put("CountryList", MessageUtil.getCountriesList());
		allCountryList = (List<Country>) getSession().get("CountryList");

		return SUCCESS;
	}

	public String listCountryCode() {
		String countrycode = request.getParameter("countrycode");

		// int cid=businessFilterDAO.getCountryIdByCode(countrycode);

		countryidls.add(businessFilterDAO.getCountryIdByCode(countrycode));
		this.setCountryidls(countryidls);
		return SUCCESS;
	}

	public List<Integer> getCountryidls() {
		return countryidls;
	}

	public void setCountryidls(List<Integer> countryidls) {
		this.countryidls = countryidls;
	}

	public String addBranch() {
		log.debug(" CHECK METHOD IN addBranch" );
		partnerList = businessFilterDAO.getAllPartnerList();
		
		//partner=businessFilterDAO.getPartnerById((Long)getSession().get("partnerId"));
		//countrypartner=businessFilterDAO.getCountryPartnerById((Long)getSession().get("countryPartnerId"));
		 getSession().put("CountryList", MessageUtil.getCountriesList()); 

		return SUCCESS;
	}

	public String listCountryName() {

		long partnerId = Long.parseLong(request.getParameter("partnerId"));
		ajaxCountryPartnerList=businessFilterDAO.getCountryPartnerList(partnerId);
		  for(CountryPartner cp:ajaxCountryPartnerList){
			   cp.setPartner(businessFilterDAO.getPartnerById(cp.getPartnerId()));
			    
			   cp.setCountryName (businessFilterDAO.getCountryByCode(cp.getCountryCode()));
			   
			   //cp.setCountryAddress((addressDAO.findAddressById(String.valueOf(cp.getAddressId()))));
  			  // cp.setBusiness(businessDAO.getBusiessById(cp.getBusinessId()));
		   }
		   
		return SUCCESS;
	}

	public String listBranch() {
		Long countryPartnerId=(Long)getSession().get("countryPartnerId");
		Long businessId=(Long) getSession().get(Constants.BUSINESS_ID_SESSION);
		Long partnerId=(Long) getSession().get("partnerId");
		if(countryPartnerId !=null && businessId !=null && partnerId !=null){
			getSession().put(ShiplinxConstants.SESSION_BUSINESSFILTER_CUSTOMERID,BusinessFilterUtil.getCountryLevelCustomers(businessId, partnerId, countryPartnerId));
			branchList=businessFilterDAO.getBranchList(countryPartnerId);
			
		branchList=new ArrayList<Branch>();
		Business bs=businessDAO.getBusiessById(businessId);
		if(bs!=null && bs.getBranchId()!=0){
			branch=businessFilterDAO.getBranchByBranchId(bs.getBranchId());
			branchList.add(branch);
		}
		
			getSession().remove("edit");
			if(countryPartnerId!=null){
			   CountryPartner cp=businessFilterDAO.getCountryPartnerById(countryPartnerId);
			 //  log.info("INTO THE NATION LEVEL  FOR A COUNTRY CODE: "+cp.getCountryAddress().getCountryCode());
			   log.debug(" Business Id :"+ businessId+" partner Id :"+ partnerId +"countryPartner Id"+ countryPartnerId);
			}
			return SUCCESS;
		}else if(UserUtil.getMmrUser().isBusinessLevel() || UserUtil.getMmrUser().isPartnerLevel() 
				|| UserUtil.getMmrUser().isNationLevel() ){
			if(partnerId!=null &countryPartnerId!=null){
				getSession().put(ShiplinxConstants.SESSION_BUSINESSFILTER_CUSTOMERID,BusinessFilterUtil.getCountryLevelCustomers(UserUtil.getMmrUser().getBusinessId(), partnerId, countryPartnerId));
				branchList=businessFilterDAO.getBranchList(countryPartnerId);
				branchList=new ArrayList<Branch>();
				if(businessId==null)
				{
					businessId=UserUtil.getMmrUser().getBusinessId();
				}
				Business bs=businessDAO.getBusiessById(businessId);
				if(bs!=null && bs.getBranchId()!=0){
					branch=businessFilterDAO.getBranchByBranchId(bs.getBranchId());
					branchList.add(branch);
				}
				
			}else if(UserUtil.getMmrUser().isNationLevel() || UserUtil.getMmrUser().isPartnerLevel() || UserUtil.getMmrUser().isBranchLevel() ){
				userFilter=businessFilterDAO.getUserFilterByUsername(UserUtil.getMmrUser().getUsername());
				if(userFilter!=null){
				getSession().put(ShiplinxConstants.SESSION_BUSINESSFILTER_CUSTOMERID,BusinessFilterUtil.getCountryLevelCustomers(UserUtil.getMmrUser().getBusinessId(), userFilter.getPartnerId(),userFilter.getCountryPartnerId()));
				}
				branchList=businessFilterDAO.getBranchList(userFilter.getCountryPartnerId());
				if(businessId==null){
					businessId=UserUtil.getMmrUser().getBusinessId();
				}
				branchList=new ArrayList<Branch>();
				Business bs=businessDAO.getBusiessById(businessId);
				if(bs!=null && bs.getBranchId()!=0){
					branch=businessFilterDAO.getBranchByBranchId(bs.getBranchId());
					branchList.add(branch);
				}
				
				
			} 
			getSession().remove("edit");
			if(countryPartnerId!=null){
		   CountryPartner cp=businessFilterDAO.getCountryPartnerById(countryPartnerId);
		  // log.info("INTO THE NATION LEVEL  FOR A COUNTRY CODE: "+cp.getCountryAddress().getCountryCode());
		   log.debug(" Business Id :"+ businessId+" partner Id :"+ partnerId +"countryPartner Id"+ countryPartnerId);
			}	
      		return SUCCESS;
		}
		addActionMessage(getText("please select login as Partner to access Branch"));
	    return "fail";
	}  

	public String loginBranch(){
		Long countryPartnerId=(Long)getSession().get("countryPartnerId");
		Long businessId=(Long) getSession().get(Constants.BUSINESS_ID_SESSION);
		Long partnerId=(Long) getSession().get("partnerId");
		if(request.getParameter("branchParam")!=null && getSession().get(Constants.BUSINESS_ID_SESSION)!=null && getSession().get("partnerId")!=null && getSession().get("countryPartnerId")!=null){
			long branchId=Long.parseLong(request.getParameter("branchParam"));
			getSession().put(ShiplinxConstants.SESSION_BUSINESSFILTER_CUSTOMERID,BusinessFilterUtil.getBranchLevelCustomers(businessId, partnerId, countryPartnerId,branchId));
			getSession().put("branchId", branchId);
			 Branch b=businessFilterDAO.getBranchByBranchId((branchId));
			   
			   log.info("INTO THE BRANCH LEVEL FOR A BRANCH : "+b.getBranchName());
			   log.debug(" Business Id :"+ businessId+" partner Id :"+ partnerId +"country partner id "+countryPartnerId+" branch id "+branchId);
			   
		}else if(UserUtil.getMmrUser().isBusinessLevel() || UserUtil.getMmrUser().isPartnerLevel() || UserUtil.getMmrUser().isNationLevel()){
			
			long branchId=Long.parseLong(request.getParameter("branchParam"));
			getSession().put(ShiplinxConstants.SESSION_BUSINESSFILTER_CUSTOMERID,BusinessFilterUtil.getBranchLevelCustomers(businessId, partnerId, countryPartnerId,branchId));
			getSession().put("branchId", branchId);

			   Branch b=businessFilterDAO.getBranchByBranchId(branchId);
			   
			   log.info("INTO THE BRANCH LEVEL FOR A BRANCH : "+b.getBranchName());
			   log.debug(" Business Id :"+ businessId+" partner Id :"+ partnerId +"country partner id "+countryPartnerId+" branch id "+branchId);
			   
			   return SUCCESS;
		   }
		   
		return SUCCESS;
	}
	
	public String filterCustomer() {

		filterList = businessFilterDAO.getFilterList();
		return SUCCESS;

	}

	/*public String editFilter() throws Exception {
		String flag = request.getParameter("getcus");

		if (flag.equals("getcus")) {
			ajaxFilterCustomers = getBranchCustomerList();
		} else if (flag.equals("getting")) {

			
			 * filter=businessFilterDAO.getFilterById(Long.parseLong(filterId));
			 * 
			 * this.setCountryName(businessFilterDAO.getCountryNameByCPId(filter.
			 * getCountryPartnerId()));
			 * partnerList=businessFilterDAO.getPartnerList(); Customer
			 * customer=(Customer) getSession().get("customer");
			 * customer.setBusinessId(UserUtil.getMmrUser().getBusinessId());
			 * List<Long> filteredCusIds=null;
			 * allCustomerList=customerService.search(customer);
			 * getSession().put("filterId", filter.getFilterId());
			 * filteredCusIds
			 * =businessFilterDAO.getFilteredCustomerIdsById(filter
			 * .getFilterId()); for(long customerId:filteredCusIds){ Customer
			 * cs=customerService.getCustomerInfoByCustomerId(customerId);
			 * branchCustomerList.add(cs); }
			 * 
			 * allCustomerList.removeAll(branchCustomerList);
			 
			getSession().put("edit", "true");
			long filterId = Long.parseLong(request.getParameter("name1"));
			getSession().put("filterId", filterId);
			setRelpartnerfils(businessFilterDAO
					.getRelParterFiltersByFilter(filterId));
			filter = businessFilterDAO.getFilterById(filterId);

			for (RelPartnerFilter relpart : getRelpartnerfils()) {
				List<Customer> customers = new ArrayList<Customer>();
				for (Long cusid : businessFilterDAO.getCustomerByRelIds(relpart
						.getRelPartnerFilId())) {
					customers.add(customerService
							.getCustomerInfoByCustomerId(cusid));
				}
				relpart.setCustomers(customers);
				relpart.setPartner(businessFilterDAO.getPartnerById(relpart
						.getPartnerId()));
				relpart.setBranch(businessFilterDAO.getBranchByBranchId(relpart
						.getBranchId()));
				relpart.setCountryName(businessFilterDAO
						.getCountryNameByCPId(relpart.getCountryPatnerId()));
			}
			Customer customer = (Customer) getSession().get("customer");

			allCustomerList = customerService.search(customer);
			
			 * for(Long c:cids){
			 * branchCustomerList.add(customerService.getCustomerInfoByCustomerId
			 * (c)); }
			 
		///	partnerList = businessFilterDAO.getPartnerList();

			return SUCCESS;
		}
		return INPUT;
	}
*/
	public String addFilter() throws Exception {
		getSession().remove("edit");
	//	partnerList = businessFilterDAO.getPartnerList();
		Customer customer = (Customer) getSession().get("customer");
		customer.setBusinessId(UserUtil.getMmrUser().getBusinessId());
	//	allCustomerList = UserUtil.getCustomersByBusinessFilter();
		// branchCustomerList=
		// allCustomerList=businessFilterDAO.getCustomerListByBusinessId(UserUtil.getMmrUser().getBusinessId());
		// allCustomerList
		return SUCCESS;
	}

	/*public String saveFilter() throws NumberFormatException, Exception {

		filter = getFilter();

		if (request.getParameter("method1").equals("add")) {
			// add
			String filteredIds = request.getParameter("selectedCus");
			String allvalues = request.getParameter("selectedCus");

			if (allvalues.length() > 0
					&& allvalues.charAt(allvalues.length() - 1) == '&') {
				allvalues = allvalues.substring(0, allvalues.length() - 1);
			}
			String subfilters[] = allvalues.split("&");
			List<String[]> filterdet = new ArrayList<String[]>();
			for (String s : subfilters) {
				String[] splitdet = s.split("%");
				String[] pridet = null;
				for (String s1 : splitdet) {
					filterdet.add(s1.split(","));
				}
			}
			List<Long> pids = new ArrayList<Long>();
			List<String> countries = new ArrayList<String>();
			List<Long> bids = new ArrayList<Long>();
			List<Long[]> cids = new ArrayList<Long[]>();
			for (int i = 0; i < filterdet.size(); i++) {
				if (i % 2 == 0) {
					String s[] = filterdet.get(i);
					pids.add(Long.parseLong(s[0]));
					countries.add(s[1]);
					bids.add(Long.parseLong(s[2]));
				} else if (i % 2 != 0) {
					Long n[] = new Long[filterdet.get(i).length];
					int i1 = 0;
					for (String s : filterdet.get(i)) {
						n[i1] = Long.parseLong(s);
						i1++;

					}
					cids.add(n);
				}

			}
			filter.setBusinessId(UserUtil.getMmrUser().getBusinessId());
			long filterId = businessFilterDAO.addFilter(filter);
			for (int i = 0; i < pids.size(); i++) {

				
				 * if (filteredIds.length() > 0 &&
				 * filteredIds.charAt(filteredIds.length()-1)==',') {
				 * filteredIds = filteredIds.substring(0,
				 * filteredIds.length()-1); } String[]
				 * filteredCusId=filteredIds.split(",");
				 
				branchCustomerList = new ArrayList<Customer>();

				filter.setFilterId(filterId);
				filter.setPartnerId(pids.get(i));
				filter.setBranchId(bids.get(i));
				filter.setCountryPartnerId(businessFilterDAO
						.getCountryPartnerId(countries.get(i), pids.get(i)));

				businessFilterDAO.addFilterCustomer(filter.getBusinessId(),
						cids.get(i), filter);

			}
			filterList = businessFilterDAO.getFilterList();
		} else if (request.getParameter("method1").equals("update")) {
			filter = getFilter();

			long fid = (Long) getSession().get("filterId");
			filter.setFilterId(fid);
			filter.setBusinessId(UserUtil.getMmrUser().getBusinessId());
			// filter.setCountryPartnerId(businessFilterDAO.getCountryPartnerId(request.getParameter("countryName"),pid));
			List<Customer> updatedCus = new ArrayList<Customer>();
			List<Customer> tmp = new ArrayList<Customer>();

			String filteredIds = request.getParameter("selectedCus");
			String filterparam = request.getParameter("filterparam");
			String filparam[] = filterparam.split(",");
			long pid = Long.parseLong(filparam[0]);
			long bid = Long.parseLong(filparam[2]);
			String country = filparam[1];
			filter.setPartnerId(pid);
			filter.setBranchId(bid);

			filter.setCountryPartnerId(businessFilterDAO.getCountryPartnerId(
					country, pid));
			long RelparfilId = businessFilterDAO
					.getRelParterFilIdByFilter(filter);
			List<Long> oldCusIds = businessFilterDAO
					.getCustomerByRelIds(RelparfilId);
			if (filteredIds.length() > 0
					&& filteredIds.charAt(filteredIds.length() - 1) == ',') {
				filteredIds = filteredIds
						.substring(0, filteredIds.length() - 1);
			}
			String[] filteredCusId = filteredIds.split(",");
			List<Long> updatedCusids = new ArrayList<Long>();
			List<Long> tmpcu = new ArrayList<Long>();
			for (String id : filteredCusId) {
				updatedCusids.add(Long.parseLong(id));
				tmpcu.add(Long.parseLong(id));
			}
			updatedCusids.removeAll(oldCusIds);
			// add new customers

			businessFilterDAO
					.addRelPartFilCustomers(updatedCusids, RelparfilId);
			oldCusIds.removeAll(tmpcu);
			businessFilterDAO.delRelPartFilCustomers(oldCusIds, RelparfilId);
			
			 * long
			 * filterId=Long.parseLong(getSession().get("filterId").toString());
			 * filter.setFilterId(filterId); List<Long> filteredCusIds=null;
			 * filteredCusIds
			 * =businessFilterDAO.getFilteredCustomerIdsById(filterId); for(long
			 * customerId:filteredCusIds){ Customer
			 * cs=customerService.getCustomerInfoByCustomerId(customerId);
			 * branchCustomerList.add(cs); } for(String cs1:filteredCusId){
			 * 
			 * Customer
			 * cs=customerService.getCustomerInfoByCustomerId(Long.parseLong
			 * (cs1)); updatedCus.add(cs); } for(Customer s:updatedCus){
			 * tmp.add(s); } updatedCus.removeAll(branchCustomerList);
			 * branchCustomerList.removeAll(tmp); for(Customer
			 * addCus:updatedCus){ ///
			 * businessFilterDAO.addFilterCustomer(filterId
			 * ,filter.getBusinessId(),addCus.getId()); } for(Customer
			 * delCus:branchCustomerList){
			 * businessFilterDAO.deleteFilterCustomerById
			 * (filterId,filter.getBusinessId(),delCus.getId()); }
			 
			businessFilterDAO.updateFilter(filter);
			filterList = businessFilterDAO.getFilterList();
			getSession().remove("filterId");
			getSession().remove("edit");
			return "edit";
		}
		return SUCCESS;
	}
*/
	public String listBranchName() {
		long partnerId = Long.parseLong(request.getParameter("partnerId"));
		Long countryPartnerId = Long.parseLong(request.getParameter("countryPartnerId"));
		// String branchName=request.getParameter("branchName");
	 
		branchList = businessFilterDAO.getBranchNameListByPartnerId(partnerId,
				countryPartnerId);
		return SUCCESS;
	}

	public String listPartnerFilter() {
		Long filterId = Long.parseLong(request.getParameter("filterId"));
		ajaxPartnerList = businessFilterDAO.getpartnerListByFilter(filterId);
		return SUCCESS;
	}

	public String saveCountryPartner(){
	 
		countrypartner = getCountrypartner();
		 log.debug(" CHECK METHOD IN SAVECOUNTRYPARTNER ***********   " + request.getParameter("method"));
			if (request.getParameter("method").equals("add")) {
				
				//add countrypartner
				if(validateCountryPartner()){
					   partnerList=businessFilterDAO.getPartnerList((Long)getSession().get(Constants.BUSINESS_ID_SESSION));
					   partner=businessFilterDAO.getPartnerById((Long)getSession().get(Constants.PARTNER_ID_SESSION));
					   getSession().put("CountryList", MessageUtil.getCountriesList());
					   return INPUT;
				}else{
					    long businessId=(Long)getSession().get(Constants.BUSINESS_ID_SESSION);
					    long partnerId=(Long)getSession().get("partnerId");
					    long addressId=addressDAO.add(countrypartner.getCountryAddress());
					    countrypartner.setAddressId(addressId);
					    countrypartner.setPartnerId(partnerId);
					    countrypartner.setCountryCode(countrypartner.getCountryAddress().getCountryCode());
					    countrypartner.setBusinessId(businessId);
						businessFilterDAO.addCountryPartner(countrypartner);
						Business b=businessDAO.getBusiessById(businessId);
						Partner p=businessFilterDAO.getPartnerById(partnerId);
						addActionMessage("Country Success Fully Added for "+p.getPartnerName()+" Under Business :"+b.getName());
					     countryPartnerList=businessFilterDAO.getCountryPartnerList(partnerId);
						   for(CountryPartner cp:countryPartnerList){
							   cp.setPartner(businessFilterDAO.getPartnerById(cp.getPartnerId()));
							  // cp.setCountryAddress((addressDAO.findAddressById(String.valueOf(cp.getAddressId()))));
				   			   cp.setBusiness(businessDAO.getBusiessById(cp.getBusinessId()));
						   }
				}  			
			} else if (request.getParameter("method").equals("update")) {
				// update
				CountryPartner cp=(CountryPartner) getSession().get("countryPartner");
				long businessId=(Long)getSession().get(Constants.BUSINESS_ID_SESSION);
				//countrypartner.setBusinessId(businessId);
				long addressId=cp.getCountryAddress().getAddressId();
				Address address=addressDAO.findAddressById(String.valueOf(addressId));
				addressDAO.edit(BusinessFilterUtil.setAddress(countrypartner.getCountryAddress(),address));
				countrypartner.setCountryPartnerId((cp.getCountryPartnerId()));
				countrypartner.setCountryCode(countrypartner.getCountryAddress().getCountryCode());
				
				businessFilterDAO.updateCountryPartner(countrypartner);
				 countryPartnerList=businessFilterDAO.getCountryPartnerList(cp.getPartnerId());
				   for(CountryPartner cp1:countryPartnerList){
					   cp1.setPartner(businessFilterDAO.getPartnerById(cp1.getPartnerId()));
					   //cp.setCountryAddress((addressDAO.findAddressById(String.valueOf(cp.getAddressId()))));
		   			   cp1.setBusiness(businessDAO.getBusiessById(cp1.getBusinessId()));
				   }
				   addActionMessage(getMessage("Nation info updated"));
				   
				   
				
			}
			return SUCCESS;
		
			}
	private boolean validateCountryPartner() {
		// TODO Auto-generated method stub
		int i=0;
		
		if(countrypartner.getPartner().getPartnerId()==-1 || countrypartner.getPartner().getPartnerId()==0){
			addActionError(getText("Partner is Required"));
			i++;
		}
		if(countrypartner.getCountryAddress().getCountryCode().equals("-1")){
			addActionError(getText("Country is Required"));
			i++;
		}
		if(countrypartner.getCountryAddress().getEmailAddress().equals("")){
			addActionError(getText("Email is Required"));
			i++;
		}else if(!countrypartner.getCountryAddress().getEmailAddress().matches(ShiplinxConstants.EMAIL_REGEX)){
			addActionError(getText("Email Address is not  valid one"));
			i++;		
		}
		if(countrypartner.getCountryAddress().getPhoneNo().equals("")){
			addActionError(getText("Phone Number is Requried"));
			i++;
		}
		if(i==0){
			return false;
		}else if(i>0){
			return true;
		}
		return false;
	}

	public List<String> getCountryList() {
		return countryList;
	}

	public void setCountryList(List<String> countryList) {
		this.countryList = countryList;
	}

	public List<String> getAjaxcountryNameList() {
		return ajaxcountryNameList;
	}

	public void setAjaxcountryNameList(List<String> ajaxcountryNameList) {
		this.ajaxcountryNameList = ajaxcountryNameList;
	}

	public List<Branch> getBranchList() {
		return branchList;
	}

	public void setBranchList(List<Branch> branchList) {
		this.branchList = branchList;
	}

	public Branch getBranch() {
		return branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}

	public AddressDAOImpl getAddressDAO() {
		return addressDAO;
	}

	public void setAddressDAO(AddressDAOImpl addressDAO) {
		this.addressDAO = addressDAO;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public List<Filter> getFilterList() {
		return filterList;
	}

	public void setFilterList(List<Filter> filterList) {
		this.filterList = filterList;
	}

	public List<String> getAjaxbranchNameList() {
		return ajaxbranchNameList;
	}

	public void setAjaxbranchNameList(List<String> ajaxbranchNameList) {
		this.ajaxbranchNameList = ajaxbranchNameList;
	}

	public List<Customer> getAllCustomerList() {
		return allCustomerList;
	}

	public void setAllCustomerList(List<Customer> allCustomerList) {
		this.allCustomerList = allCustomerList;
	}

	public List<Customer> getBranchCustomerList() {
		return branchCustomerList;
	}

	public void setBranchCustomerList(List<Customer> branchCustomerList) {
		this.branchCustomerList = branchCustomerList;
	}

	public CustomerManager getCustomerService() {
		return customerService;
	}

	public void setCustomerService(CustomerManager customerService) {
		this.customerService = customerService;
	}

	public Filter getFilter() {
		return filter;
	}

	public void setFilter(Filter filter) {
		this.filter = filter;
	}

	public String getFilterCountry() {
		return filterCountry;
	}

	public void setFilterCountry(String filterCountry) {
		this.filterCountry = filterCountry;
	}

	public List<Customer> getAjaxFilterCustomers() {
		return ajaxFilterCustomers;
	}

	public void setAjaxFilterCustomers(List<Customer> ajaxFilterCustomers) {
		this.ajaxFilterCustomers = ajaxFilterCustomers;
	}

	public List<Integer> getListint() {
		return listint;
	}

	public void setListint(List<Integer> listint) {
		this.listint = listint;
	}

	public List<Country> getPartnerCountryList() {
		return partnerCountryList;
	}

	public void setPartnerCountryList(List<Country> partnerCountryList) {
		this.partnerCountryList = partnerCountryList;
	}

	public List<Country> getAllCountryList() {
		return allCountryList;
	}

	public void setAllCountryList(List<Country> allCountryList) {
		this.allCountryList = allCountryList;
	}

	public List<Partner> getAjaxPartnerList() {
		return ajaxPartnerList;
	}

	public void setAjaxPartnerList(List<Partner> ajaxPartnerList) {
		this.ajaxPartnerList = ajaxPartnerList;
	}

	public List<RelPartnerFilter> getRelpartnerfils() {
		return Relpartnerfils;
	}

	public void setRelpartnerfils(List<RelPartnerFilter> relpartnerfils) {
		Relpartnerfils = relpartnerfils;
	}

	public BusinessDAO getBusinessDAO() {
		return businessDAO;
	}

	public void setBusinessDAO(BusinessDAO businessDAO) {
		this.businessDAO = businessDAO;
	}

	public CountryPartner getCountrypartner() {
		return countrypartner;
	}

	public void setCountrypartner(CountryPartner countrypartner) {
		this.countrypartner = countrypartner;
	}

	public List<CountryPartner> getCountryPartnerList() {
		return countryPartnerList;
	}

	public void setCountryPartnerList(List<CountryPartner> countryPartnerList) {
		this.countryPartnerList = countryPartnerList;
	}

 
	public List<CountryPartner> getAjaxCountryPartnerList() {
		return ajaxCountryPartnerList;
	}

	public void setAjaxCountryPartnerList(List<CountryPartner> ajaxCountryPartnerList) {
		this.ajaxCountryPartnerList = ajaxCountryPartnerList;
	}
	public String viewPartner(){
		long partnerId = Long.parseLong(request.getParameter("name"));
		getSession().put("CountryList", MessageUtil.getCountriesList());
		partner = businessFilterDAO.getPartnerById(partnerId);
		return SUCCESS;
	}
	public String viewCountry(){
		
		long cPId=  Long.parseLong(request.getParameter("name"));
		getSession().put("CountryList", MessageUtil.getCountriesList());
		countrypartner=businessFilterDAO.getCountryPartnerById(cPId);
		getSession().put("countryPartner", countrypartner);
		if(getSession().get(Constants.BUSINESS_ID_SESSION)==null){
			partnerList=businessFilterDAO.getPartnerList(UserUtil.getMmrUser().getBusinessId());
		}else{
			partnerList=businessFilterDAO.getPartnerList((Long)getSession().get(Constants.BUSINESS_ID_SESSION));
		}
		
		return SUCCESS;
	}
	public String viewBranch(){
		String branchId = request.getParameter("partnerId");
		branch = businessFilterDAO.getBranchByBranchId(branchId);
		getSession().put("CountryList", MessageUtil.getCountriesList());
		
		return SUCCESS;
	}

	public UserFilter getUserFilter() {
		return userFilter;
	}

	public void setUserFilter(UserFilter userFilter) {
		this.userFilter = userFilter;
	}

}