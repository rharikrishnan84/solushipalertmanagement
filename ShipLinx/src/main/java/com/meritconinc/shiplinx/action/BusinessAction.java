
package com.meritconinc.shiplinx.action;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.meritconinc.mmr.constants.Constants;
import com.meritconinc.mmr.dao.BusinessFilterDAO;
import com.meritconinc.mmr.dao.MenusDAO;
import com.meritconinc.mmr.dao.UserDAO;
import com.meritconinc.mmr.model.common.MenuItemVO;
import com.meritconinc.mmr.model.security.User;
import com.meritconinc.mmr.utilities.MessageUtil;
import com.meritconinc.mmr.utilities.MmrBeanLocator;
import com.meritconinc.mmr.utilities.security.UserUtil;
import com.meritconinc.shiplinx.dao.AddressDAO;
import com.meritconinc.shiplinx.dao.BusinessDAO;
import com.meritconinc.shiplinx.model.Address;
import com.meritconinc.shiplinx.model.Business;
import com.meritconinc.shiplinx.model.Country;
import com.meritconinc.shiplinx.model.CountryPartner;
import com.meritconinc.shiplinx.model.EdiItem;
import com.meritconinc.shiplinx.model.Partner;
import com.meritconinc.shiplinx.model.UserFilter;
import com.meritconinc.shiplinx.utils.BusinessDefaultLoaderDaoImpl;
import com.meritconinc.shiplinx.utils.ShiplinxConstants;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.Preparable;
import com.soluship.businessfilter.util.BusinessFilterUtil;

 

public class BusinessAction extends BaseAction implements Preparable,ServletRequestAware{

	private static final Logger log = LogManager.getLogger(BusinessAction.class);
	
	private HttpServletRequest request;
	
	private BusinessDAO businessDAO;
	
	private Business business;
	
	private List<Business> businessList = new ArrayList<Business>();
	
	private UserDAO userDAO;
	
	private List<MenuItemVO> menuVo = new ArrayList<MenuItemVO>();
	
	private MenusDAO menuItemDAO;
	
	private String businessId;
	
	private int[] selectedMenuIds;
	
	private AddressDAO addressDAO;
	
	private BusinessFilterDAO businessFilterDAO;
	
	
	private  List<String> defaultLoaders;
	
	  private List<Boolean> select;
	
	private String[] selectedLoaders;
	
	private File fileUpload;
	private String fileUploadContentType;
	private String fileUploadFileName;
	
	private List<Country> allCountryList;
	
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request ; 
	}

	public String newBusiness(){
		try{
			log.debug(" CHECK METHOD IN newBusiness " );
			loadNewBussinessDepencies();
		}catch(Exception e){
			log.error("Error in new business" +e.getMessage());
		}
		return SUCCESS;
	}
	
	private void loadNewBussinessDepencies() {
		// TODO Auto-generated method stub
		
		menuVo = menuItemDAO.getAllMenu();
		getSession().put("CountryList", MessageUtil.getCountriesList());
		getSession().put("editBusiness","false");
		defaultLoaders=new ArrayList<String>();
		defaultLoaders.add("BUSINESS CARRIERS");
		defaultLoaders.add("BUSINESS MENUS");
		defaultLoaders.add("CUSTOMER MARKUP");
		defaultLoaders.add("EDI INFO");
		defaultLoaders.add("LTL POUND RATE");
		defaultLoaders.add("LTL SKID RATE");
		defaultLoaders.add("MERCHANT ACCOUNT");
		//setPartnerList(businessFilterDAO.getAllPartnerList());
		
		getSession().put("defualLoaders",defaultLoaders);
		
	}

	@Override
	public void prepare() throws Exception {
		// TODO Auto-generated method stub
		
	}

	public String listBusiness(){
		try{
			if((getSession().get("saveBus"))!=null && ((String)getSession().get("saveBus")).equals("success")){
				addActionMessage(getText("business.save.success"));
			}else if((getSession().get("updateBus"))!=null && ((String)getSession().get("updateBus")).equals("success")){
				addActionMessage(getText("business.updated.successfully"));
			}
			getSession().put(ShiplinxConstants.SESSION_BUSINESSFILTER_CUSTOMERID,BusinessFilterUtil.getSysadminLevelCustomers());
			businessList = businessDAO.getAllBusiness();
			getSession().remove("editBusiness");
		}catch(Exception e){
			log.error("Error in listing the business :" + e.getMessage());
		}
		getSession().remove("saveBus");
		getSession().remove("updateBus");
		return SUCCESS;
	}
	
	
	public String saveBusiness(){
		//System.out.println(business.getName());
		String paramBusinessId = request.getParameter("businessid");
		  
		//updating the business
		if(paramBusinessId!=null && !paramBusinessId.equals("") && !paramBusinessId.equals("0")){
			 log.debug(" CHECK METHOD IN SAVE BUSINESS ************** UPDATE BUSINESS");
			try{
				
				
				String paramAddressId = request.getParameter("addressid");
				System.out.println(paramAddressId);
				Address addressUpdate = business.getAddress();
				addressUpdate.setAddressId(Long.valueOf(paramAddressId));
				//updating the address
				addressDAO.edit(addressUpdate);
				business.setId(Long.parseLong(paramBusinessId));
				business.setAddressId(Long.valueOf(paramAddressId));
				business.setUsAddressId(Long.valueOf(paramAddressId));
				business.setDefaultNetTerms(15);
				businessDAO.updateBusiness(business);
				//inserting the business menu
				List<MenuItemVO> menuItems = menuItemDAO.getMenuByBusiness(paramBusinessId);
				String[] uiMenuSelected = business.getMenuIds();
				
				for(int uiCount=0;uiCount < uiMenuSelected.length ;uiCount++){
					boolean isOccured = false;
					for(MenuItemVO itemVO : menuItems){
						if(itemVO.getId() == Integer.valueOf(uiMenuSelected[uiCount])){
							isOccured = true;
						}
					}
					if(!isOccured){
						menuItemDAO.saveBusinessMenu(new Long(paramBusinessId).toString(), uiMenuSelected[uiCount]);
					}
				}
				
				
				//deleting the business menu
				
				for(MenuItemVO itemVO : menuItems){
					boolean isOccured = false;
					for(int uiCount=0;uiCount < uiMenuSelected.length ;uiCount++){
						if(itemVO.getId() == Integer.valueOf(uiMenuSelected[uiCount])){
							isOccured = true;
						}
					}
					
					if(!isOccured){
						menuItemDAO.deleteBusinessMenuById(paramBusinessId, String.valueOf(itemVO.getId()));
					}
				}
				getSession().put(ShiplinxConstants.SESSION_BUSINESSFILTER_CUSTOMERID,BusinessFilterUtil.getSysadminLevelCustomers());
				businessList = businessDAO.getAllBusiness();
				getSession().remove("editBusiness");
				
				
				addActionMessage(getText("business.update.success"));
				  getSession().put("updateBus", "success");
				return SUCCESS;
			}catch(Exception e){
				addActionError(getText("business.save.error"));
				return INPUT;
			}
			
		}else{
			//inserting the new business
			log.debug(" CHECK METHOD IN SAVE BUSINESS ************** ADD NEW BUSINESS");
			try{
				     if(validateBusiness()|| validateBusinessName()){
				    		 
				    		
				    			
				    		
				    	 loadNewBussinessDepencies();
				    	 return "fail";
				     }else{
				        business.setDefaultNetTerms(15);
		    			long addressId = addressDAO.add(business.getAddress());
						business.setAddressId(addressId);
						business.setUsAddressId(addressId);
						long businessId = businessDAO.addBusiness(business);
						User user = setuserDetails(business.getName(), businessId);
				       	userDAO.create(user, "");
					 	    BusinessFilterDAO businessFilterDAO = (BusinessFilterDAO) MmrBeanLocator.getInstance().findBean("businessFilterDAO");
						    user.setUserFilter(new UserFilter());
							user.getUserFilter().setBusinessId(businessId);
							user.getUserFilter().setUserName(user.getUsername());
						 	businessFilterDAO.addUserFilters(user.getUserFilter());
							
							//add default busiess admin as parter
			                String businessName=business.getName();
			                String sC=business.getShortCode();
			                business.setShortCode(sC+"Part");
				       	    business.setName(businessName+"Partner");
							business.setPartnerLevel(true);
							business.setParentBusinessId(businessId);
							long partnerBusienssId=businessDAO.addParterLevelBusienss(business);
				    	    User userPatner = setuserDetails(business.getName(), partnerBusienssId);
				    	    userPatner.setUsername(business.getName());
				    	    userPatner.setBusinessLevel(false);
				    	    userPatner.setPartnerLevel(true);
				    	    userDAO.create(userPatner, "");
				    	    
				    	    userPatner.setUserFilter(new UserFilter());
				    	    userPatner.getUserFilter().setBusinessId(partnerBusienssId);
				    	    userPatner.getUserFilter().setUserName(userPatner.getUsername());
				    	    userPatner.getUserFilter().setBusinessLevel(true);
						 	businessFilterDAO.addUserFilters(userPatner.getUserFilter());
				    	    
				    	    
							// add default country_admin 
				    	     business.setShortCode(sC+"Nat");
							 business.setName(businessName+"Nation");
							 business.setPartnerLevel(false);
							 business.setNationLevel(true);
							 
							 business.setPartnerId(partnerBusienssId);
							 business.setParentBusinessId(partnerBusienssId);
							 long countryBusinessId=businessDAO.addCountryLevelBusienss(business);
							 User usercountry = setuserDetails(business.getName(), countryBusinessId);
							 usercountry.setUsername(business.getName());
							 usercountry.setNationLevel(true);
							 usercountry.setBusinessLevel(false);
							 userDAO.create(usercountry, "");
							 
							 usercountry.setUserFilter(new UserFilter());
							 usercountry.getUserFilter().setBusinessId(countryBusinessId);
							 usercountry.getUserFilter().setUserName(usercountry.getUsername());
							 usercountry.getUserFilter().setPartnerLevel(true);
							 businessFilterDAO.addUserFilters(usercountry.getUserFilter());
					    	    
							 
							 
						//userDAO.insertRole(business.getName(), "busadmin");
						for(String menuId:business.getMenuIds()){
							menuItemDAO.saveBusinessMenu(new Long(businessId).toString(), menuId);
						}
					//	String msg="Your BUSINESS SUCCESSFULLY ADDED TO SOLUSHIP\n USERNAME  :"+user.getUsername()+"Password :"+user.getPassword();
					  
						//adding the default default loaders
						if(businessId>0 && partnerBusienssId>0&& countryBusinessId>0){
							 //loadDefaultPropertyForBusiness(businessId);
							List<Long> busids=new ArrayList<Long>();
							busids.add(businessId);
							busids.add(partnerBusienssId);
							busids.add(countryBusinessId);
						    BusinessFilterUtil bfu1=new BusinessFilterUtil(busids, this.select);
							Thread t1=new Thread(bfu1);
							t1.start();
							
							
						}
					    // default loader for partner level default business
				/*		if(partnerBusienssId>0){
							  loadDefaultPropertyForBusiness(partnerBusienssId);
						   BusinessFilterUtil bfu1=new BusinessFilterUtil(partnerBusienssId, this.select);
							Thread t1=new Thread(bfu1);
							t1.start();
						}*/
						/*
						if(countryBusinessId>0){
					        // default loader for partner level default business
							  loadDefaultPropertyForBusiness(countryBusinessId);
						  	BusinessFilterUtil bfu1=new BusinessFilterUtil(countryBusinessId, this.select);
							Thread t1=new Thread(bfu1);
							t1.start();
						}*/
					    
					    getSession().put(ShiplinxConstants.SESSION_BUSINESSFILTER_CUSTOMERID,BusinessFilterUtil.getSysadminLevelCustomers());
						businessList = businessDAO.getAllBusiness();
						getSession().remove("editBusiness");
					    getSession().put("saveBus", "success");
						
					    return SUCCESS;
						
				
				     }
			}catch(Exception e){
				log.error("Error in creating the business :"+e.getMessage());
				business.setId(0);
				
				menuVo = menuItemDAO.getAllMenu();
				addActionError(getText("business.save.error"));
				getSession().put("editBusiness","false");
				return INPUT;
			}
			
		}
		
	}
	
	private boolean validateBusinessName() {
		// TODO Auto-generated method stub
		int i=0;
		  
		if(userDAO.findUserByUsername(business.getName())!=null){
			addActionError(getText("Business UserName Already Exist "));
			i++;
		}
		if(businessDAO.getBusinessByName(business.getName()+"Partner")!=null){
			addActionError(getText("Business Parnter Name Already Exist "));
			i++;
		} 
		if(userDAO.findUserByUsername(business.getName()+"Partner")!=null){
			addActionError(getText("Business Partner UserName Already Exist "));
			i++;
		}
		if(businessDAO.getBusinessByName(business.getName()+"Nation")!=null){
			addActionError(getText(" Country Business  Name Already Exist "));
			i++;
		} 
		if(userDAO.findUserByUsername(business.getName()+"Nation")!=null){
			addActionError(getText("  Country Business   UserName Already Exist "));
			i++;
		}
		 if(i>0){
			 return true;
		 }
		return false;
	}

	public void loadDefaultPropertyForBusiness(Long businessId){
		addDefaultBusinessLoaders(businessId);
	    businessDAO.addDefaultCustomerCarrier(businessId);
	    businessDAO.addDefaultPinsToBusiness(businessId);
	}
	
	private boolean validateBusiness() {
		// TODO Auto-generated method stub
		int i=0;
      if(businessDAO.getBusinessByName(business.getName())!=null){
    	   addActionError("Business Name Already Exist");
			i++;
     }
	 
		 if(business.getName().equals("")){
			addActionError("Name is Requried");
			i++;
		 }else if(business.getName().length()>30 && business.getName().length()<3){
			 addActionError(getText("Business Name length must be with 3 to 30 "));
			 i++;
		 }
		 
		 if(business.getShortCode().equals("")){
			 addActionError(getText("Short Code is Requried"));
			 i++;
			
		 }else if(business.getShortCode().length()>10 || business.getShortCode().length()<3){
			 addActionError(getText("Short Code length must be with 3 to 9 "));
			 i++;
		 }
		 
		 if(business.getSystemName().equals("")){
			 addActionError(getText("System Name is Required"));
			 i++;
		 }else if(business.getSystemName().length()>20 || business.getSystemName().length()<3){
			 addActionError(getText("System Name length must be with 3"
			 		+ " to 20 "));
			 i++;
		 }
		 
		 if(business.getSubdomain().equals("")){
			 addActionError(getText("Sub Domail  is Required"));
			 i++;
		 }else if(business.getSubdomain().length()>200){
			 addActionError(getText("subdomain  length is too high must be less than  200 "));
			 i++;
			 
		 }
		 
		 if(business.getAddress().getEmailAddress().equals("")){
			 addActionError(getText("Email address is Required"));
			 i++;
		 }else {
			 if(!business.getAddress().getEmailAddress().matches(ShiplinxConstants.EMAIL_REGEX)){
				 addActionError(getText("Email address is not a valid one"));
				 i++; 
			 }
		 }
		 
		 if(business.getAddress().getCountryCode().equals("") || business.getAddress().getCountryCode().equals("-1")){
			 addActionError(getText("Country is Required"));
			 i++;
		 } 
/*	     if(business.getPartnerId()==-1){
	    	 addActionError(getText("Select partner"));
			 i++;
	     }
	     if(brID==null ||brID==-1 ){
	    	 addActionError(getText("Select Branch"));
			 i++;
	     }
	     if(cpId==null || cpId==-1 ){
	    	 addActionError(getText("Select Country"));
			 i++;
	     }*/
	    	 
		 
		 
		 if(i==0){
			 return false;
		 }else if(i>0){
		return true;
		 }
		 return false;
	}
    public String listPartnerBusiness(){
    	Long businessId = (Long)getSession().get(Constants.BUSINESS_ID_SESSION);
		// ** code for adding partner list of selected business**//
		if(businessId!=null ){
			System.out.println("SUPER PARENT----->"+BusinessFilterUtil.getSuperParentBusiness(businessId).getName());
			getSession().put(ShiplinxConstants.SESSION_BUSINESSFILTER_CUSTOMERID,BusinessFilterUtil.getBusinessLevelCustomers(businessId));
            businessList=businessDAO.getPartnerBusiness(businessId);
		return SUCCESS;
		}else if(UserUtil.getMmrUser().getUserRole().equals("busadmin")){
			getSession().put(ShiplinxConstants.SESSION_BUSINESSFILTER_CUSTOMERID,BusinessFilterUtil.getBusinessLevelCustomers(UserUtil.getMmrUser().getBusinessId()));
			businessList=businessDAO.getPartnerBusiness(UserUtil.getMmrUser().getBusinessId());
			return SUCCESS;
		}
		
		else  
		{
			addActionMessage(getText("Please Select the business"));
			return "fail";
			
		}
    }
    
    @SuppressWarnings("unchecked")
	public String listCountryPartner(){
  	    Long partnerId = (Long) getSession().get(Constants.PARTNER_ID_SESSION);
  	    Long businessId=(Long) getSession().get(Constants.BUSINESS_ID_SESSION);
  	  
  	   if(partnerId !=null && getSession().get(Constants.BUSINESS_ID_SESSION)!=null && UserUtil.getMmrUser().getUserRole().equals(ShiplinxConstants.ROLE_SYSADMIN)){
  		 System.out.println("SUPER PARENT----->"+BusinessFilterUtil.getSuperParentBusiness(partnerId).getName());
  			getSession().put(ShiplinxConstants.SESSION_BUSINESSFILTER_CUSTOMERID,BusinessFilterUtil.getPartnerLevelCustomers(businessId, partnerId));
            businessList=businessDAO.getCountryBusiness(partnerId);
   		   return SUCCESS;  
  	   }else if(!UserUtil.getMmrUser().getUserRole().equals(Constants.SYS_ADMIN_ROLE_CODE)){
  		   
  		   businessId=UserUtil.getMmrUser().getBusinessId();
  		   Business bs=businessDAO.getBusiessById(UserUtil.getMmrUser().getBusinessId());
  		   //partnerId=bs.getParentBusinessId();
   		   getSession().put(ShiplinxConstants.SESSION_BUSINESSFILTER_CUSTOMERID,BusinessFilterUtil.getPartnerLevelCustomers(partnerId,UserUtil.getMmrUser().getBusinessId()));
   		 businessList=businessDAO.getCountryBusiness(UserUtil.getMmrUser().getBusinessId());
   		   if(UserUtil.getMmrUser().isBusinessLevel()&& partnerId!=null){
   			 getSession().put(ShiplinxConstants.SESSION_BUSINESSFILTER_CUSTOMERID,BusinessFilterUtil.getPartnerLevelCustomers(businessId, partnerId));
             businessList=businessDAO.getCountryBusiness(partnerId);
             return SUCCESS;
   		   }/*else if(UserUtil.getMmrUser().isPartnerLevel()){
  			   
  			 getSession().put(ShiplinxConstants.SESSION_BUSINESSFILTER_CUSTOMERID,BusinessFilterUtil.getPartnerLevelCustomers(businessId, partnerId));
             //businessList=businessDAO.getCountryBusiness(businessId);
    		   return SUCCESS;
  		  
  	       }*/  
  	   
  	   }
  	   return SUCCESS;
     }
    
    @SuppressWarnings("unchecked")
	public String listBranchBusiness(){
    	
    	Long countryPartnerId=(Long)getSession().get(Constants.NATION_ID_SESSION);
		Long businessId=(Long) getSession().get(Constants.BUSINESS_ID_SESSION);
		Long partnerId=(Long) getSession().get(Constants.PARTNER_ID_SESSION);
		
		if(countryPartnerId !=null && businessId !=null && partnerId !=null){
			getSession().put(ShiplinxConstants.SESSION_BUSINESSFILTER_CUSTOMERID,BusinessFilterUtil.getCountryLevelCustomers(businessId, partnerId, countryPartnerId));
			businessList=businessDAO.getBranchBuisness(partnerId,countryPartnerId);
			getSession().remove("edit");
			return SUCCESS;
		}else if(!UserUtil.getMmrUser().getUserRole().equals(Constants.SYS_ADMIN_ROLE_CODE)){
			Business bs=businessDAO.getBusiessById(UserUtil.getMmrUser().getBusinessId());
			if(UserUtil.getMmrUser().isBusinessLevel() && partnerId!=null && countryPartnerId!=null){
				businessList=businessDAO.getBranchBuisness(partnerId,countryPartnerId);
			}else if(UserUtil.getMmrUser().isPartnerLevel()&& countryPartnerId!=null){
				businessList=businessDAO.getBranchBuisness(UserUtil.getMmrUser().getBusinessId(),countryPartnerId);
			}else if(UserUtil.getMmrUser().isNationLevel()){
				businessList=businessDAO.getBranchBuisness(bs.getParentBusinessId(),bs.getId());
			}
			getSession().remove("edit");
		    return SUCCESS;
		}
		
		addActionMessage(getText("please select login as Partner to access Branch"));
	    return "fail";
    }
    
	public String newPartnerBusiness(){
		
		log.debug(" CHECK METHOD IN newPartner" );
		getSession().remove("edit");
		getSession().put("CountryList", MessageUtil.getCountriesList());
		setAllCountryList((List<Country>) getSession().get("CountryList"));
		loadNewBussinessDepencies();
		return SUCCESS;
	}
	public String savePartnerBusiness(){
		String paramBusinessId = request.getParameter("businessid");
		  
		business =this.getBusiness();
		 log.debug(" CHECK METHOD IN SAVE PARTNER Business***********   " + request.getParameter("method"));
		 
		if (request.getParameter("method").equals("add")) {
		 	Long businessId=(Long)getSession().get(Constants.BUSINESS_ID_SESSION);
				    if(validateBusiness() || validateBusinessUser("Partner")){
				    	  			    	  
						 
				    loadNewBussinessDepencies();
				    return INPUT;
				    }else{
					
					    
					    if(businessId==null && !UserUtil.getMmrUser().getUserRole().equals(Constants.SYS_ADMIN_ROLE_CODE)){
					    	
					    	businessId=UserUtil.getMmrUser().getBusinessId();
					    }
					    if(businessId!=null){
						    Business parentbus=businessDAO.getBusiessById(businessId);
						    business.setDefaultNetTerms(15);
						    business.setPartnerLevel(true);
						    business.setParentBusinessId(businessId);
						    long addressId=addressDAO.add(business.getAddress());
						    business.setUsAddressId(addressId);
						    business.setAddressId(addressId);
						    
						    long partnerBusinessId=businessDAO.addParterLevelBusienss(business);
						    if(partnerBusinessId>0){
						    	List<Long> busids=new ArrayList<Long>();
						    	busids.add(partnerBusinessId);
						    	BusinessFilterUtil bfu=new BusinessFilterUtil(busids,this.select);
						    	Thread t1=new Thread(bfu);
						    	t1.start();
						    	
						    	//loadDefaultPropertyForBusiness(partnerBusinessId);
						    }
						    User userPatner = setuserDetails(business.getName(), partnerBusinessId);
				    	    userPatner.setUsername(business.getName());
				    	    userPatner.setPartnerLevel(true);
				    	    userPatner.setBusinessLevel(false);
				    	    userDAO.create(userPatner, "");
				    	    BusinessFilterDAO businessFilterDAO = (BusinessFilterDAO) MmrBeanLocator.getInstance().findBean("businessFilterDAO");
				    	    userPatner.setUserFilter(new UserFilter());
				    	    userPatner.getUserFilter().setBusinessId(partnerBusinessId);
				    	    userPatner.getUserFilter().setUserName(userPatner.getUsername());
				    	    userPatner.getUserFilter().setBusinessLevel(true);
						 	businessFilterDAO.addUserFilters(userPatner.getUserFilter());
							
							Business b=businessDAO.getBusiessById(businessId);
							addActionMessage("Partner Success Fully Added Under Business :"+b.getName());
					    }
				    }	
		} else if (request.getParameter("method").equals("update")) {
			// update
			
			
			
			log.debug(" CHECK METHOD IN SAVE BUSINESS ************** UPDATE BUSINESS");
			try{
				 Long businessId=(Long)getSession().get(Constants.BUSINESS_ID_SESSION);
				
				String paramAddressId = request.getParameter("addressid");
				System.out.println(paramAddressId);
				Address addressUpdate = business.getAddress();
				addressUpdate.setAddressId(Long.valueOf(paramAddressId));
				//updating the address
				addressDAO.edit(addressUpdate);
				business.setId(Long.parseLong(paramBusinessId));
				business.setAddressId(Long.valueOf(paramAddressId));
				business.setUsAddressId(Long.valueOf(paramAddressId));
				business.setDefaultNetTerms(15);
				businessDAO.updateBusiness(business);
				//inserting the business menu
				List<MenuItemVO> menuItems = menuItemDAO.getMenuByBusiness(paramBusinessId);
				String[] uiMenuSelected = business.getMenuIds();
				
				for(int uiCount=0;uiCount < uiMenuSelected.length ;uiCount++){
					boolean isOccured = false;
					for(MenuItemVO itemVO : menuItems){
						if(itemVO.getId() == Integer.valueOf(uiMenuSelected[uiCount])){
							isOccured = true;
						}
					}
					if(!isOccured){
						menuItemDAO.saveBusinessMenu(new Long(paramBusinessId).toString(), uiMenuSelected[uiCount]);
					}
				}
				
				
				//deleting the business menu
				
				for(MenuItemVO itemVO : menuItems){
					boolean isOccured = false;
					for(int uiCount=0;uiCount < uiMenuSelected.length ;uiCount++){
						if(itemVO.getId() == Integer.valueOf(uiMenuSelected[uiCount])){
							isOccured = true;
						}
					}
					
					if(!isOccured){
						menuItemDAO.deleteBusinessMenuById(paramBusinessId, String.valueOf(itemVO.getId()));
					}
				}
				//getSession().put(ShiplinxConstants.SESSION_BUSINESSFILTER_CUSTOMERID,BusinessFilterUtil.getSysadminLevelCustomers());
				Business b=businessDAO.getBusiessById(businessId);
				getSession().remove("editBusiness");
				
				
				addActionMessage(getText("business.update.success"));
				return SUCCESS;
			}catch(Exception e){
				addActionError(getText("business.save.error"));
				return INPUT;
			}
			
		
		}
		return SUCCESS;
		 
		
	}
	
	
	public String newCountryBusiness(){
		log.debug(" CHECK METHOD IN newCountry" );
		  loadNewBussinessDepencies();
		   getSession().put("CountryList", MessageUtil.getCountriesList()); 
		   return SUCCESS;
	 
	}
	
	public String saveCountryBusiness(){
		String paramBusinessId = request.getParameter("businessid");
		  

	   	business =this.getBusiness();
		 log.debug(" CHECK METHOD IN SAVE Country Business***********   " + request.getParameter("method"));
		if (request.getParameter("method").equals("add")) {
			Long businessId=(Long)getSession().get(Constants.BUSINESS_ID_SESSION);
		     Long  partnerId=(Long)getSession().get(Constants.PARTNER_ID_SESSION);
					if(validateBusiness() || validateBusinessUser("Nation")){
						loadNewBussinessDepencies();
						return INPUT;
					}else{
					     
                         if(businessId==null && !UserUtil.getMmrUser().getUserRole().equals(Constants.SYS_ADMIN_ROLE_CODE)){
					    	businessId=UserUtil.getMmrUser().getBusinessId();
					    }
					    if(businessId!=null && partnerId!=null){
						    Business parentbus=businessDAO.getBusiessById(businessId);
						    business.setDefaultNetTerms(15);
						    business.setNationLevel(true);
						    business.setPartnerId(partnerId);
						    business.setParentBusinessId(partnerId);
						    long addressId=addressDAO.add(business.getAddress());
						    business.setUsAddressId(addressId);
						    business.setAddressId(addressId);
						    
						    long countryBusinessId=businessDAO.addCountryLevelBusienss(business);
						    if(countryBusinessId>0){
//						    	loadDefaultPropertyForBusiness(countryBusinessId);

						    	List<Long> busids=new ArrayList<Long>();
						    	busids.add(countryBusinessId);
						    	BusinessFilterUtil bfu=new BusinessFilterUtil(busids,this.select);
						    	Thread t1=new Thread(bfu);
						    	t1.start();
						    }
						     User usercountry = setuserDetails(business.getName(), countryBusinessId);
							 usercountry.setUsername(business.getName());
							 usercountry.setNationLevel(true);
							 usercountry.setBusinessLevel(false);
							 userDAO.create(usercountry, "");
							 
							    BusinessFilterDAO businessFilterDAO = (BusinessFilterDAO) MmrBeanLocator.getInstance().findBean("businessFilterDAO");
							    usercountry.setUserFilter(new UserFilter());
							    usercountry.getUserFilter().setBusinessId(countryBusinessId);
							    usercountry.getUserFilter().setUserName(usercountry.getUsername());
					    	    usercountry.getUserFilter().setPartnerLevel(true);
							 	businessFilterDAO.addUserFilters(usercountry.getUserFilter());
								
							 
							Business b=businessDAO.getBusiessById(businessId);
							addActionMessage("Partner Success Fully Added Under Business :"+b.getName());
							businessList=businessDAO.getCountryBusiness(partnerId);
					    }
					}
			 			
		} else if (request.getParameter("method").equals("update")) {
			// update
			log.debug(" CHECK METHOD IN SAVE BUSINESS ************** UPDATE BUSINESS");
			try{
				String paramAddressId = request.getParameter("addressid");
				System.out.println(paramAddressId);
				Address addressUpdate = business.getAddress();
				addressUpdate.setAddressId(Long.valueOf(paramAddressId));
				//updating the address
				addressDAO.edit(addressUpdate);
				business.setId(Long.parseLong(paramBusinessId));
				business.setAddressId(Long.valueOf(paramAddressId));
				business.setUsAddressId(Long.valueOf(paramAddressId));
				business.setDefaultNetTerms(15);
				businessDAO.updateBusiness(business);
				//inserting the business menu
				List<MenuItemVO> menuItems = menuItemDAO.getMenuByBusiness(paramBusinessId);
				String[] uiMenuSelected = business.getMenuIds();
				
				for(int uiCount=0;uiCount < uiMenuSelected.length ;uiCount++){
					boolean isOccured = false;
					for(MenuItemVO itemVO : menuItems){
						if(itemVO.getId() == Integer.valueOf(uiMenuSelected[uiCount])){
							isOccured = true;
						}
					}
					if(!isOccured){
						menuItemDAO.saveBusinessMenu(new Long(paramBusinessId).toString(), uiMenuSelected[uiCount]);
					}
				}
				
				//deleting the business menu
				
				for(MenuItemVO itemVO : menuItems){
					boolean isOccured = false;
					for(int uiCount=0;uiCount < uiMenuSelected.length ;uiCount++){
						if(itemVO.getId() == Integer.valueOf(uiMenuSelected[uiCount])){
							isOccured = true;
						}
					}
					
					if(!isOccured){
						menuItemDAO.deleteBusinessMenuById(paramBusinessId, String.valueOf(itemVO.getId()));
					}
				}
				//getSession().put(ShiplinxConstants.SESSION_BUSINESSFILTER_CUSTOMERID,BusinessFilterUtil.getSysadminLevelCustomers());
				Business bus=businessDAO.getBusiessById(business.getId());
				businessList=businessDAO.getCountryBusiness(bus.getParentBusinessId());
				getSession().remove("editBusiness");
				
				
				addActionMessage(getText("business.update.success"));
				return SUCCESS;
			}catch(Exception e){
				addActionError(getText("business.save.error"));
				return INPUT;
			}
		}
		
		return SUCCESS;
	}
	
	public String saveBranchBusienss(){
		
		String paramBusinessId = request.getParameter("businessid");
		  
		business =this.getBusiness();
		 log.debug(" CHECK METHOD IN SAVE Country Business***********   " + request.getParameter("method"));
		if (request.getParameter("method").equals("add")) {
			   Long businessId=(Long)getSession().get(Constants.BUSINESS_ID_SESSION);
			    Long partnerId=(Long)getSession().get(Constants.PARTNER_ID_SESSION);
			    Long countryParterId=(Long)getSession().get(Constants.NATION_ID_SESSION);
			  
					if(validateBusiness()  || validateBusinessUser("Branch")){
						loadNewBussinessDepencies();
						return INPUT;
					}else{
					   
                        if(businessId==null && !UserUtil.getMmrUser().getUserRole().equals(Constants.SYS_ADMIN_ROLE_CODE)){
					    	
					    	businessId=UserUtil.getMmrUser().getBusinessId();
					    }
					    if(businessId!=null && partnerId!=null && countryParterId !=null){
						    Business parentbus=businessDAO.getBusiessById(businessId);
						    business.setDefaultNetTerms(15);
						    business.setBranchLevel(true);
						    business.setPartnerId(partnerId);
						    business.setCountryPartnerId(countryParterId);
						    business.setParentBusinessId(countryParterId);
						    long addressId=addressDAO.add(business.getAddress());
						    business.setUsAddressId(addressId);
						    business.setAddressId(addressId);
						    long BranchBusId=businessDAO.addBranchLevelBusiness(business);
						    if(BranchBusId>0){
						    	List<Long> busids=new ArrayList<Long>();
						    	busids.add(BranchBusId);
						    	BusinessFilterUtil bfu=new BusinessFilterUtil(busids,this.select);
						    	Thread t1=new Thread(bfu);
						    	t1.start();
						    	//loadDefaultPropertyForBusiness(BranchBusId);
						    }
						     User userBranch = setuserDetails(business.getName(), BranchBusId);
						     userBranch.setUsername(business.getName() );
						     userBranch.setBranchLevel(true);
						     userBranch.setBusinessLevel(false);
						     
							 userDAO.create(userBranch, "");
							 
							 
							 BusinessFilterDAO businessFilterDAO = (BusinessFilterDAO) MmrBeanLocator.getInstance().findBean("businessFilterDAO");
							 userBranch.setUserFilter(new UserFilter());
							 userBranch.getUserFilter().setBusinessId(BranchBusId);
							 userBranch.getUserFilter().setUserName(userBranch.getUsername());
							 userBranch.getUserFilter().setNationLevel(true);
							 businessFilterDAO.addUserFilters(userBranch.getUserFilter());
							 
						    		
							 
							Business b=businessDAO.getBusiessById(businessId);
							addActionMessage("Partner Success Fully Added Under Business :"+b.getName());
							businessList=businessDAO.getBranchBuisness(partnerId, countryParterId);
					    }
					}
		} else if (request.getParameter("method").equals("update")) {
			// update
			
			log.debug(" CHECK METHOD IN SAVE BUSINESS ************** UPDATE BUSINESS");
			try{
				Long businessId=(Long)getSession().get(Constants.BUSINESS_ID_SESSION);
			    Long partnerId=(Long)getSession().get(Constants.PARTNER_ID_SESSION);
			    Long countryParterId=(Long)getSession().get(Constants.NATION_ID_SESSION);
				
				String paramAddressId = request.getParameter("addressid");
				System.out.println(paramAddressId);
				Address addressUpdate = business.getAddress();
				addressUpdate.setAddressId(Long.valueOf(paramAddressId));
				//updating the address
				addressDAO.edit(addressUpdate);
				business.setId(Long.parseLong(paramBusinessId));
				business.setAddressId(Long.valueOf(paramAddressId));
				business.setUsAddressId(Long.valueOf(paramAddressId));
				business.setDefaultNetTerms(15);
				businessDAO.updateBusiness(business);
				//inserting the business menu
				List<MenuItemVO> menuItems = menuItemDAO.getMenuByBusiness(paramBusinessId);
				String[] uiMenuSelected = business.getMenuIds();
				
				for(int uiCount=0;uiCount < uiMenuSelected.length ;uiCount++){
					boolean isOccured = false;
					for(MenuItemVO itemVO : menuItems){
						if(itemVO.getId() == Integer.valueOf(uiMenuSelected[uiCount])){
							isOccured = true;
						}
					}
					if(!isOccured){
						menuItemDAO.saveBusinessMenu(new Long(paramBusinessId).toString(), uiMenuSelected[uiCount]);
					}
				}
				
				
				//deleting the business menu
				
				for(MenuItemVO itemVO : menuItems){
					boolean isOccured = false;
					for(int uiCount=0;uiCount < uiMenuSelected.length ;uiCount++){
						if(itemVO.getId() == Integer.valueOf(uiMenuSelected[uiCount])){
							isOccured = true;
						}
					}
					
					if(!isOccured){
						menuItemDAO.deleteBusinessMenuById(paramBusinessId, String.valueOf(itemVO.getId()));
					}
				}
				//getSession().put(ShiplinxConstants.SESSION_BUSINESSFILTER_CUSTOMERID,BusinessFilterUtil.getSysadminLevelCustomers());
				businessList=businessDAO.getBranchBuisness(partnerId, countryParterId);
				getSession().remove("editBusiness");
				
				
				addActionMessage(getText("business.update.success"));
				return SUCCESS;
			}catch(Exception e){
				addActionError(getText("business.save.error"));
				return INPUT;
			}
			
		

		}
		return SUCCESS;
	}
    private boolean validateBusinessUser(String level) {
		// TODO Auto-generated method stub
    	  if(userDAO.findUserByUsername(business.getName())!=null){
				addActionError(getText(level+" Business User Name Already Exist "));
				return true;
			}
		return false;
	}

	public String newBranchBusiness(){
    	log.debug(" CHECK METHOD IN newBranchBusiness" );
		  loadNewBussinessDepencies();
		   getSession().put("CountryList", MessageUtil.getCountriesList()); 
		   return SUCCESS;
    	
    	
     
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
			   getSession().put(Constants.PARTNER_ID_SESSION, partnerId);
			   return SUCCESS;
		   }else if(UserUtil.getMmrUser().isBusinessLevel() ){
			   long partnerId=Long.parseLong(request.getParameter("partnerParam"));
			   getSession().put(Constants.PARTNER_ID_SESSION, partnerId);
			   
			   return SUCCESS;
		   }else if(UserUtil.getMmrUser().isPartnerLevel()){
			   long partnerId=Long.parseLong(request.getParameter("partnerParam"));
			   getSession().put(Constants.PARTNER_ID_SESSION, partnerId);
			   return SUCCESS;
	   		}else {
			
			return "fail";
		}
	   }
	
    public String logInAsCountry(){
 	   if(request.getParameter("countryparam")!=null && getSession().get(Constants.BUSINESS_ID_SESSION)!=null && getSession().get("partnerId")!=null){
 			long countryPartnerId=Long.parseLong(request.getParameter("countryparam"));
 			getSession().put(Constants.NATION_ID_SESSION, countryPartnerId);
 			return SUCCESS;
 		}else if(UserUtil.getMmrUser().isBusinessLevel() || UserUtil.getMmrUser().isPartnerLevel()){
 			long countryPartnerId=Long.parseLong(request.getParameter("countryparam"));
 			getSession().put(Constants.NATION_ID_SESSION, countryPartnerId);
 			return SUCCESS;	
 		}
 			addActionMessage(getText("please select login as Partner to access Branch"));
 			//branchList = businessFilterDAO.getBranchList();
 		    return "fail";
    }
    
    
    public String loginBranch(){
		Long countryPartnerId=(Long)getSession().get("countryPartnerId");
		Long businessId=(Long) getSession().get(Constants.BUSINESS_ID_SESSION);
		Long partnerId=(Long) getSession().get("partnerId");
		if(request.getParameter("branchParam")!=null && getSession().get(Constants.BUSINESS_ID_SESSION)!=null && getSession().get("partnerId")!=null && getSession().get("countryPartnerId")!=null){
			long branchId=Long.parseLong(request.getParameter("branchParam"));
			getSession().put(ShiplinxConstants.SESSION_BUSINESSFILTER_CUSTOMERID,BusinessFilterUtil.getBranchLevelCustomers(businessId, partnerId, countryPartnerId,branchId));
			getSession().put(Constants.BRANCH_ID_SESSION, branchId);
			 
		}else if(UserUtil.getMmrUser().isBusinessLevel() || UserUtil.getMmrUser().isPartnerLevel() || UserUtil.getMmrUser().isNationLevel()){
			
			long branchId=Long.parseLong(request.getParameter("branchParam"));
			getSession().put(ShiplinxConstants.SESSION_BUSINESSFILTER_CUSTOMERID,BusinessFilterUtil.getBranchLevelCustomers(businessId, partnerId, countryPartnerId,branchId));
			getSession().put(Constants.BRANCH_ID_SESSION, branchId);
			   
			   return SUCCESS;
		   }
		   
		return SUCCESS;
	}
	private void uploadCss() {
		// TODO Auto-generated method stub
		
		InputStream uploadStream=null;
		/*System.out.println(request.getPathInfo()+"--- "+request.getServletPath()+"---"+request.getContextPath());
		*/String filePath = request.getSession().getServletContext().getRealPath("/");
		OutputStream bos = null;
		File outFile = new File(request.getContextPath()+"../mmr/images/business");
	    try{
	    	uploadStream=new DataInputStream(new FileInputStream(this.getFileUpload()));
			int bytesRead = 0;
			byte[] buffer = new byte[8192];
	
			boolean bFirstTime = true;
			while ((bytesRead = uploadStream.read(buffer, 0, 8192)) != -1) {
				if (bFirstTime) {
					bFirstTime = false;
					bos = new FileOutputStream(outFile);
				}
				bos.write(buffer, 0, bytesRead);
			}
	    }catch(Exception e){
	    	
	    }finally{
	    	
	    	try {
				bos.close();
				uploadStream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	
	    }
 
		
	}

	private void addDefaultBusinessLoaders(long businessId) {
		// TODO Auto-generated method stub
		BusinessDefaultLoaderDaoImpl defLoader=new BusinessDefaultLoaderDaoImpl(businessId, 1);
        List<Integer> toUpdateLoader =new ArrayList<Integer>();
		 if(select!=null && select.size()>0){
			
			   
					 for(int i=0;i<select.size();i++){
						 if(select.get(i)){
						 toUpdateLoader.add(i+1);
						 }
						 					 
					 }
			
		}
		if(toUpdateLoader.size()==7){
			defLoader.loadAll();
		}else if(toUpdateLoader.size()>0){
			
			for(Integer i:toUpdateLoader){
				if(i==1){
					defLoader.loadBusinessCarrier();
				}else if(i==2){
					defLoader.loadBusinessMenu();
				}else if(i==3){
					defLoader.loadCustomerMarkUp();
				}else if(i==4){
					defLoader.loadEdiInfo();
				}else if(i==5){
					defLoader.loadPoundRate();
				}else if(i==6){
					defLoader.loadSkidRate();
				}else if(i==7){
					defLoader.loadMerchantAccount();
				}
			}
			
		}
	}

	public String editBusiness(){
		log.debug(" CHECK METHOD IN editBusiness" );
		System.out.println(businessId);
		try{
			long selectedBusinessId = Long.parseLong(businessId);
			business = businessDAO.getBusiessById(selectedBusinessId);
			Address address = addressDAO.findAddressById(String.valueOf(business.getAddressId()));
			business.setAddress(address);
			menuVo = menuItemDAO.getAllMenu();
			List<MenuItemVO> menuItems = menuItemDAO.getMenuByBusiness(String.valueOf(selectedBusinessId));
			int count = 0;
			selectedMenuIds = new int[menuItems.size()];
			for(MenuItemVO menuItemVO : menuItems){
				selectedMenuIds[count] = menuItemVO.getId();
				count++;
			}
			getSession().put("CountryList", MessageUtil.getCountriesList());
			getSession().put("editBusiness","true");
			return SUCCESS;
			
		}catch(Exception e){
			addActionError(getText("role.update.error"));
			return INPUT;
		}
	}
	 
	
	public String viewBusiness(){
		log.debug(" CHECK METHOD IN view business" );
		System.out.println(businessId);
		try{
			long selectedBusinessId = Long.parseLong(businessId);
			business = businessDAO.getBusiessById(selectedBusinessId);
			Address address = addressDAO.findAddressById(String.valueOf(business.getAddressId()));
			business.setAddress(address);
			menuVo = menuItemDAO.getAllMenu();
			List<MenuItemVO> menuItems = menuItemDAO.getMenuByBusiness(String.valueOf(selectedBusinessId));
			int count = 0;
			selectedMenuIds = new int[menuItems.size()];
			for(MenuItemVO menuItemVO : menuItems){
				selectedMenuIds[count] = menuItemVO.getId();
				count++;
			}
			getSession().put("CountryList", MessageUtil.getCountriesList());
		 
			return SUCCESS;
			
		}catch(Exception e){
			addActionError(getText("role.update.error"));
			return INPUT;
		}
	}
	public String editPartnerBusiness(){
	 return editBusiness();
	}
	
public String editNationBusiness(){
	
	return editBusiness();
}

public String editBranchBusiness(){
	return editBusiness();
}

	public String deleteBusiness(){
		try{
			log.debug(" CHECK METHOD IN DELETE BUSINESS  ");
			businessDAO.deleteBusiness(businessId);
			addActionMessage(getText("business.delete.success"));
		}catch(Exception e){
			log.debug("Error in Deleting the business : "+ e.getMessage());
			addActionError(getText("business.delete.error"));
		}
		return SUCCESS;
	}
	
	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public BusinessDAO getBusinessDAO() {
		return businessDAO;
	}

	public void setBusinessDAO(BusinessDAO businessDAO) {
		this.businessDAO = businessDAO;
	}

	public Business getBusiness() {
		return business;
	}

	public void setBusiness(Business business) {
		this.business = business;
	}

	public List<Business> getBusinessList() {
		return businessList;
	}

	public void setBusinessList(List<Business> businessList) {
		this.businessList = businessList;
	}

	
	public User setuserDetails(String userName,long businessId){
		User user = new User();
		user.setUsername(userName);
		user.setPassword("admin@123");
		user.setFirstName(userName);
		user.setLastName("admin");
		user.setEmail("admin@ic.com");
		user.setPhoneNumber("555-555-5555");
		user.setStatus("A");
		user.setCustomerId(0);
		user.setBusinessId(businessId);
		user.setPrintNoOfLabels(5);
		user.setPrintNoOfCI(5);
		user.setAutoPrint(true);
		user.setUserRole("busadmin");
		user.setLocale("en_CA");
		user.setBusinessLevel(true);
		
		return user;
	}

	public UserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	public List<MenuItemVO> getMenuVo() {
		return menuVo;
	}

	public void setMenuVo(List<MenuItemVO> menuVo) {
		this.menuVo = menuVo;
	}

	public MenusDAO getMenuItemDAO() {
		return menuItemDAO;
	}

	public void setMenuItemDAO(MenusDAO menuItemDAO) {
		this.menuItemDAO = menuItemDAO;
	}

	public String getBusinessId() {
		return businessId;
	}

	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}

	public int[] getSelectedMenuIds() {
		return selectedMenuIds;
	}

	public void setSelectedMenuIds(int[] selectedMenuIds) {
		this.selectedMenuIds = selectedMenuIds;
	}

	public AddressDAO getAddressDAO() {
		return addressDAO;
	}

	public void setAddressDAO(AddressDAO addressDAO) {
		this.addressDAO = addressDAO;
	}

	public BusinessFilterDAO getBusinessFilterDAO() {
		return businessFilterDAO;
	}

	public void setBusinessFilterDAO(BusinessFilterDAO businessFilterDAO) {
		this.businessFilterDAO = businessFilterDAO;
	}

	
	public List<String> getDefaultLoaders() {
		return defaultLoaders;
	}

	public void setDefaultLoaders(List<String> defaultLoaders) {
		this.defaultLoaders = defaultLoaders;
	}

	public String[] getSelectedLoaders() {
		return selectedLoaders;
	}

	public void setSelectedLoaders(String[] selectedLoaders) {
		this.selectedLoaders = selectedLoaders;
	}

	public List<Boolean> getSelect() {
		return select;
	}

	public void setSelect(List<Boolean> select) {
		this.select = select;
	}

	public String getFileUploadContentType() {
		return fileUploadContentType;
	}
 
	public void setFileUploadContentType(String fileUploadContentType) {
		this.fileUploadContentType = fileUploadContentType;
	}
 
	public String getFileUploadFileName() {
		return fileUploadFileName;
	}
 
	public void setFileUploadFileName(String fileUploadFileName) {
		this.fileUploadFileName = fileUploadFileName;
	}
 
	public File getFileUpload() {
		return fileUpload;
	}
 
	public void setFileUpload(File fileUpload) {
		this.fileUpload = fileUpload;
	}

 
	public List<Country> getAllCountryList() {
		return allCountryList;
	}

	public void setAllCountryList(List<Country> allCountryList) {
		this.allCountryList = allCountryList;
	}
	

	
	
	
	
}
