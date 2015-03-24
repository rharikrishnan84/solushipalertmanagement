package com.soluship.businessfilter.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.text.StyledEditorKit.BoldAction;

import com.ibatis.sqlmap.engine.mapping.sql.dynamic.elements.IsParameterPresentTagHandler;
import com.meritconinc.mmr.constants.Constants;
import com.meritconinc.mmr.dao.BusinessFilterDAO;
import com.meritconinc.mmr.model.admin.UserSearchCriteria;
import com.meritconinc.mmr.model.security.User;
import com.meritconinc.mmr.service.UserService;
import com.meritconinc.mmr.utilities.MmrBeanLocator;
import com.meritconinc.mmr.utilities.StringUtil;
import com.meritconinc.mmr.utilities.security.UserUtil;
import com.meritconinc.shiplinx.dao.BusinessDAO;
import com.meritconinc.shiplinx.model.Address;
import com.meritconinc.shiplinx.model.Business;
import com.meritconinc.shiplinx.model.CountryPartner;
import com.meritconinc.shiplinx.model.Customer;
import com.meritconinc.shiplinx.model.Partner;
import com.meritconinc.shiplinx.model.UserFilter;
import com.meritconinc.shiplinx.service.CustomerManager;
import com.meritconinc.shiplinx.utils.BusinessDefaultLoaderDaoImpl;
import com.meritconinc.shiplinx.utils.ShiplinxConstants;
import com.opensymphony.xwork2.ActionContext;

public class BusinessFilterUtil implements Runnable {
	
	private List<Long> customerIds;
	private List<Long> businessIds;
	private static List<Business> businessList;
    
	  private List<Boolean> select;
	  private Long businessId;
	  public BusinessFilterUtil(){
		  
	  }
	   public BusinessFilterUtil(List<Long> businessIds, List<Boolean> select){
		  this.businessIds=businessIds;
		  this.select=select;
	   }
	//private static 
	public static Partner setPartner(Business business) {
		// TODO Auto-generated method stub
		Partner  partner=new Partner();
		partner.setAddressId(business.getAddressId());
		partner.setBusinessId(business.getId());
		partner.setShortCode(business.getShortCode());
		partner.setEmail(business.getAddress().getEmailAddress());
		partner.setBusiness(business.getName());
		partner.setPhoneNumber(business.getAddress().getPhoneNo());
		partner.setFax(business.getAddress().getFaxNo());
		partner.setPartnerName(business.getName());
		partner.setFirstName(business.getName());
		partner.setLastName("Partner");
		//partner.setCountryCode(business.getAddress().getCountryId());
		partner.setCompany(business.getSystemName());
		return partner;
	}

	public static Address setAddress(Address countryAddress, Address address) {
		// TODO Auto-generated method stub
	     countryAddress.setAddressId(address.getAddressId());
		 return countryAddress;
	}
	 
	public static void sendUserCriedentialMail( String msg, String to){
		

	      // Sender's email ID needs to be mentioned
	      String from = "selvaganesh26193@gmail.com";//change accordingly
	      final String username = "selvaganesh26193";//change accordingly
	      final String password = "Seltech__1239";//change accordingly

	      // Assuming you are sending email through relay.jangosmtp.net
	     String  host = "smtp.gmail.com";

	      Properties props = new Properties();
	      props.put("mail.smtp.auth", "true");
	      props.put("mail.smtp.starttls.enable", "true");
	      props.put("mail.smtp.host", host);
	      props.put("mail.smtp.port", "587");

	      // Get the Session object.
	      Session session = Session.getInstance(props,
	      new javax.mail.Authenticator() {
	         protected PasswordAuthentication getPasswordAuthentication() {
	            return new PasswordAuthentication(username, password);
	         }
	      });

	      try {
	         // Create a default MimeMessage object.
	         Message message = new MimeMessage(session);

	         // Set From: header field of the header.
	         message.setFrom(new InternetAddress(from));

	         // Set To: header field of the header.
	         message.setRecipients(Message.RecipientType.TO,
	         InternetAddress.parse(to));

	         // Set Subject: header field
	         message.setSubject("YOUR BUSINESS IS ADDED SUCCESFULLY");

	         // Now set the actual message
	         message.setText(msg);

	         // Send message
	         Transport.send(message);

	         System.out.println("Sent message successfully....");

	      } catch (MessagingException e) {
	            throw new RuntimeException(e);
	      }
   
		
		
	}
	
	public static List<Customer> getSysadminLevelCustomers(){
		System.out.println("...INTO THE METHOD getSysadminLevelCustomers");
		CustomerManager customerService=(CustomerManager)MmrBeanLocator.getInstance().findBean("customerService");
		List<Customer> sysadminCustomerIds= customerService.getAllCustomers();
		 
		return sysadminCustomerIds;
	}

	public static List<Customer> getBusinessLevelCustomers(Long businessId){
		
		System.out.println("...INTO THE METHOD getBusinessLevelCustomers FOR BUSINESS "+businessId);
        List<Business> businessList1=null;
		 
		List<Customer> businessLevelCustomers=new ArrayList<Customer>();
		if(businessId!=null){
		businessList1=BusinessFilterUtil.getBusinessListByBusinessId(businessId);
		}
		//businessList1.add(businessDAO.getBusiessById(businessId));
        if(businessList1!=null && businessList1.size()>0){
        	businessLevelCustomers= getCustomersByBusiness(businessList1);
        	
        }
		return businessLevelCustomers;
	}

   public static List<Customer> getCustomersByBusiness(List<Business> businessList1){
	   List<Customer>  businessLevelCustomers=new ArrayList<Customer>();
	   CustomerManager customerService=(CustomerManager)MmrBeanLocator.getInstance().findBean("customerService");
	   if(businessList1!=null && businessList1.size()>0){
    	   List<Customer> customers=null;
    	   for(Business bus:businessList1){
    		   Customer c=new Customer();
    		   c.setBusinessId(bus.getId());
    		   customers=customerService.search(c);
    		   
    		   if(customers!=null && customers.size()>0){
    			   businessLevelCustomers.addAll(customers);
    		   }
    	   }
       }
	   
	   
   return businessLevelCustomers;
   }

	public static List<Customer> getPartnerLevelCustomers(Long businessId,
			Long partnerId) {
		// TODO Auto-generated method stub
		List<Customer>  partnerLevelCustomers=new ArrayList<Customer>();
		List<Business> businessList1=null;
		if(partnerId!=null){
			businessList1=BusinessFilterUtil.getBusinessListByBusinessId(partnerId);
			}
 
	     if(businessList1!=null && businessList1.size()>0){
	    	 partnerLevelCustomers=getCustomersByBusiness(businessList1);
	     }
	 
		return partnerLevelCustomers;
		 
	}
	
 public static List<Customer> getCountryLevelCustomers(Long businessId,
			Long partnerId,Long countryPartnerId) {
		// TODO Auto-generated method stub
 		List<Customer>  nationLevelCustomers=new ArrayList<Customer>();
		List<Business> businessList1=null;
		if(businessId!=null && partnerId!=null && countryPartnerId!=null){
			businessList1=BusinessFilterUtil.getBusinessListByBusinessId(countryPartnerId);
			}
	     if(businessList1!=null && businessList1.size()>0){
	    	 nationLevelCustomers=getCustomersByBusiness(businessList1);
	     }
	 		 
		
		
		return nationLevelCustomers;
		 
	}
 
 public static List<Customer> getSalesUserCustomers(User logInuser) {
	// TODO Auto-generated method stub
	 System.out.println("...INTO THE METHOD getSalesUserCustomers FOR sales user "+logInuser.getUsername());
	 List<Customer> salesCustomers=new ArrayList<Customer>();
	   if(logInuser!=null && logInuser.getUserRole().equalsIgnoreCase(ShiplinxConstants.ROLE_SALES)){
		   CustomerManager customerService=(CustomerManager)MmrBeanLocator.getInstance().findBean("customerService");
		   
		   salesCustomers=  customerService.getCustomersBySalesUser(logInuser.getUsername());
		   
		   
	   }
	   
	   System.out.println("Sales User....."+logInuser.getUsername()+"...... into the get customers..."+salesCustomers.size());
	return salesCustomers;
}

public static Object getBranchLevelCustomers(Long businessId, Long partnerId,
		Long countryPartnerId, Long branchId) {
	// TODO Auto-generated method stub
	List<Customer>  branchLevelCustomers=new ArrayList<Customer>();
	List<Business> businessList1=null;
	if(businessId!=null && partnerId!=null && countryPartnerId!=null){
		businessList1=BusinessFilterUtil.getBusinessListByBusinessId(branchId);
		}
     if(businessList1!=null && businessList1.size()>0){
    	 branchLevelCustomers=getCustomersByBusiness(businessList1);
     }
 		
	return branchLevelCustomers;
}
	
// public static  List<Customer> get (Long businessId){
	public List<Long> getCustomerIds() {
		return customerIds;
	}

	public void setCustomerIds(List<Long> customerIds) {
		this.customerIds = customerIds;
	}

	public static  CountryPartner setCountryPartner(Business business, long partnerId) {
		// TODO Auto-generated method stub
		CountryPartner cp=new CountryPartner();
		cp.setAddressId(business.getAddressId());
		cp.setCountryName(business.getAddress().getCountryName());
		cp.setCountryCode(business.getAddress().getCountryCode());
		cp.setPartnerId(partnerId);
		cp.setBusinessId(business.getId());
		return cp;
		
	}
	
 	
   public static void setCustomersByUserLevel(User logInuser){
	   System.out.println("...INTO THE METHOD setCustomersByUserLevel FOR USER "+logInuser.getUsername());
	   BusinessFilterDAO businessFilterDAO=(BusinessFilterDAO)MmrBeanLocator.getInstance().findBean("businessFilterDAO");
	   if(logInuser!=null){
		   	//UserFilter uf=businessFilterDAO.getUserFilterByUsername(logInuser.getUsername());
		   BusinessDAO businessDAO=(BusinessDAO)MmrBeanLocator.getInstance().findBean("businessDAO");
			  
		   
 
			if(logInuser!=null){
				Business bs=businessDAO.getBusiessById(logInuser.getBusinessId());
				if(logInuser.getUserRole().equalsIgnoreCase(ShiplinxConstants.ROLE_BUSINESSADMIN)||logInuser.getUserRole().equalsIgnoreCase(ShiplinxConstants.ROLE_SYSADMIN)){
				
					if(logInuser.isBusinessLevel()){
						
						ActionContext.getContext().getSession().put(ShiplinxConstants.SESSION_BUSINESSFILTER_CUSTOMERID,BusinessFilterUtil.getBusinessLevelCustomers(logInuser.getBusinessId()));
						
					}else if(logInuser.isPartnerLevel()){
						 
						ActionContext.getContext().getSession().put(ShiplinxConstants.SESSION_BUSINESSFILTER_CUSTOMERID,BusinessFilterUtil.getPartnerLevelCustomers(bs.getParentBusinessId(),logInuser.getBusinessId()));
						
					}else if(logInuser.isNationLevel()){
						
						ActionContext.getContext().getSession().put(ShiplinxConstants.SESSION_BUSINESSFILTER_CUSTOMERID,BusinessFilterUtil.getCountryLevelCustomers(bs.getParentBusinessId(),bs.getPartnerId(),logInuser.getBusinessId()));
						
					}else if(logInuser.isBranchLevel()){
						
						ActionContext.getContext().getSession().put(ShiplinxConstants.SESSION_BUSINESSFILTER_CUSTOMERID,BusinessFilterUtil.getBranchLevelCustomers(bs.getParentBusinessId(),bs.getPartnerId(),bs.getCountryPartnerId(),logInuser.getBusinessId()));
						
					}else if(logInuser.getUserRole().equalsIgnoreCase(ShiplinxConstants.ROLE_CUSTOMER_ADMIN)){
						
						ActionContext.getContext().getSession().put(ShiplinxConstants.SESSION_BUSINESSFILTER_CUSTOMERID,BusinessFilterUtil.getBusinessLevelCustomers(logInuser.getBusinessId()));
						
					}else if(logInuser.getUserRole().equalsIgnoreCase(ShiplinxConstants.ROLE_BUSINESSADMIN)){
						ActionContext.getContext().getSession().put(ShiplinxConstants.SESSION_BUSINESSFILTER_CUSTOMERID,BusinessFilterUtil.getBusinessLevelCustomers(logInuser.getBusinessId()));

					}
					
				}else if(logInuser.getUserRole().equalsIgnoreCase(ShiplinxConstants.ROLE_SALES)){
					ActionContext.getContext().getSession().put(ShiplinxConstants.SESSION_BUSINESSFILTER_CUSTOMERID,BusinessFilterUtil.getSalesUserCustomers(logInuser));
				}else if(logInuser.getUserRole().equalsIgnoreCase(ShiplinxConstants.ROLE_CUSTOMER_ADMIN)){
					ActionContext.getContext().getSession().put(ShiplinxConstants.SESSION_BUSINESSFILTER_CUSTOMERID,BusinessFilterUtil.getCustomersForCustomerAdmin(logInuser));
				}else if(logInuser.getUserRole().equalsIgnoreCase(ShiplinxConstants.ROLE_CUSTOMER_BASE) || logInuser.getUserRole().equalsIgnoreCase(ShiplinxConstants.ROLE_CUSTOMER_SHIPPER)){
					ActionContext.getContext().getSession().put(ShiplinxConstants.SESSION_BUSINESSFILTER_CUSTOMERID,BusinessFilterUtil.getBusinessLevelCustomers(logInuser.getBusinessId()));
				}
			}	
	   }
		
	}
	
   
   
  
public static List<Customer> getCustomersForCustomerAdmin(User logInuser) {
	// TODO Auto-generated method stub
	 System.out.println("...INTO THE METHOD getCustomersForCustomerAdmin FOR customer Admin "+logInuser.getUsername());
	 List<Customer> Customers=new ArrayList<Customer>();
	   if(logInuser!=null && logInuser.getUserRole().equalsIgnoreCase(ShiplinxConstants.ROLE_CUSTOMER_ADMIN)){
		   CustomerManager customerService=(CustomerManager)MmrBeanLocator.getInstance().findBean("customerService");
		   
		 try {
		Customer c=new  Customer();
		c.setId(logInuser.getCustomerId());
		c.setBusinessId(logInuser.getBusinessId());
         c=customerService.getCustomerInfoByCustomerId(c.getId());
         Customers.add(c);
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		   
		   
	   }
	   
	   System.out.println("customer admin....."+logInuser.getUsername()+"...... into the get customers..."+Customers.size());
	return  Customers;
}
public static List<Business> getBusinessListByBusinessId(Long businessId){
	   businessList =new ArrayList<Business>();
	   BusinessDAO businessDAO=(BusinessDAO)MmrBeanLocator.getInstance().findBean("businessDAO");
	  
	   
	   
	   List<Business> businessListTemp = getBusinessByParentBusiness(businessId);
	   businessListTemp.add(businessDAO.getBusiessById(businessId));
	   
	   
	   return businessListTemp;
   }
   
   //fetching the child business list based on the business id
   public static List<Business> getBusinessByParentBusiness(Long businessId){
	   
	   BusinessDAO businessDAO=(BusinessDAO)MmrBeanLocator.getInstance().findBean("businessDAO");
	   List<Business> businessListLoc=businessDAO.getBusinessListByLevel(businessId);
	   boolean isBranchLevel = false;
	   for(Business business :businessListLoc){
		   businessList.add(business);
		   if(business.isBranchLevel()){
			   isBranchLevel = true;
		   }
	   }
	   
	   if(isBranchLevel){
		    
		    return businessList;
	   }else{
		   for(Business business : businessListLoc){
			   getBusinessByParentBusiness(business.getId());
		   }
	   }
	   
	   if(businessList!=null && businessList.size()>0){
		   
		   return businessList;
	   }else{
		   return new ArrayList<Business>();
	   }
	   
   }
   
   public static List<User> getUsersByBusiness(Long businessId) throws Exception{
	 
	   UserService userService=(UserService)MmrBeanLocator.getInstance().findBean("userService");
	   Long cid=(Long) ActionContext.getContext().getSession().get("cid");
	   if(cid!=null){
 	   return getUserListByBusinessLevel(businessId,cid);
	   }else{
		   return getUserListByBusinessLevel(businessId,cid);
	   }
		   
   }
   
   
   public static List<User> getUserListByBusinessLevel(Long businessId ,Long cid) throws Exception{
	   BusinessDAO businessDAO=(BusinessDAO)MmrBeanLocator.getInstance().findBean("businessDAO");
		  
	   if(cid>0L && businessId==0){
		   businessId=UserUtil.getMmrUser().getBusinessId();
		   
	   }
	   boolean isBranchLevel = false;
	   UserService userService=(UserService)MmrBeanLocator.getInstance().findBean("userService");
	   List<Business> busList=getBusinessListByBusinessId(businessId);
	    List<User> userList=new ArrayList<User>();
	       if(cid>0L){
	    	   Business b=businessDAO.getBusiessById(businessId);
			   userList.addAll((getUserListByBusId(b.getId(),cid)));
			   return userList;
		   }
	   if(busList!=null &&  busList.size()>0){
    
		   for(Business bus:busList){
			  
			   if(bus!=null){
			  userList.addAll(getUserListByBusId(bus.getId(),cid));
			   }
		   }
	   }
	   
	   return userList;
   }
   
   
   
   public static User getUser(){
   User user = (User)  ActionContext.getContext().getSession().get("user");
   if (user == null) {
     user = new User();
     user.setBusinessId(UserUtil.getMmrUser().getBusinessId());
     return user;
   }
   return user;
   }
   
   
   	
   
   public static List<User> getUserListByBusId(Long busId,Long cid) throws Exception{
	    Long lCustomerId = 0L;

	    CustomerManager customerService=(CustomerManager)MmrBeanLocator.getInstance().findBean("customerService");
	    UserService userService=(UserService)MmrBeanLocator.getInstance().findBean("userService");
	    Collection<String> listCustomerIds = new ArrayList<String>();
	    User user = UserUtil.getMmrUser();
	      UserSearchCriteria criteria=new UserSearchCriteria();
	     Long businessId= busId;
	/*     Long partnerId=(Long) ActionContext.getContext().getSession().get(Constants.PARTNER_ID_SESSION);
	 	 Long countryPartnerId=(Long) ActionContext.getContext().getSession().get(Constants.NATION_ID_SESSION);
	 	 Long branchId=(Long) ActionContext.getContext().getSession().get(Constants.BRANCH_ID_SESSION)*/;
	 	 User user1=new User();
	 	 if(cid==0L){
	 		 cid=null;
	 	 }
	    if (cid != null) {
	      lCustomerId =cid;
	      Customer c = customerService.getCustomerInfoByCustomerId(lCustomerId);
	      ActionContext.getContext().getSession().put("customerName", c.getName());
	      user1.setCustomerId(lCustomerId);
	    }else if(user!=null && user.getUserRole()!=null && user.getUserRole().equals(ShiplinxConstants.ROLE_SYSADMIN) ){ 
	    	    	/*List<Customer> customers =  (List<Customer>)  ActionContext.getContext().getSession().get(ShiplinxConstants.SESSION_BUSINESSFILTER_CUSTOMERID);
	    	   	if(customers!=null && customers.size()>0){
	    	    		for(Customer customer : customers){
	    	    			listCustomerIds.add(String.valueOf(customer.getId()));
	    	    		}*/
	    	    		//get the user created by busadmin
	    	    		listCustomerIds.add(String.valueOf(0));
	    	    		criteria.setCustomerIds(listCustomerIds);
	    	    		if(businessId!=null ){
	    	    			criteria.setBusinessId(businessId);
	    	    		}else{
	    	    			criteria.setBusinessId(null);
	    	    		}
	    	     
	    	    		if(businessId!=null ){
	    	    			criteria.setBusinessId(businessId);
	    	    		}else{
	    	    			criteria.setBusinessId(null);
	    	    		}
	    	    	 
	    	    }else if(user!=null && user.getUserRole()!=null && user.getUserRole().equals("busadmin")){
	    	    	/*List<Customer> customers =  (List<Customer>)ActionContext.getContext().getSession().get(ShiplinxConstants.SESSION_BUSINESSFILTER_CUSTOMERID);
	    	    	if(customers!=null && customers.size()>0){
	    	    		for(Customer customer : customers){
	    	    			listCustomerIds.add(String.valueOf(customer.getId()));
	    	    		}
	    	    		
	    	    	}*/
	    	    	listCustomerIds.add(String.valueOf(0));
	    	    	criteria.setCustomerIds(listCustomerIds);
	    	    	    if(businessId!=null && businessId>0){
	    	    	    	criteria.setBusinessId(businessId);  			
	    	    	    }else if(UserUtil.getMmrUser().getUserRole().equals(ShiplinxConstants.ROLE_SYSADMIN) && businessId ==null){
	    	    	    	criteria.setBusinessId(null);
	    	    	    }else{
	    	    	    	criteria.setBusinessId(UserUtil.getMmrUser().getBusinessId());
	    	    	    }
	    	    }else {
	      lCustomerId = UserUtil.getMmrUser().getCustomerId();
	      getUser().setCustomerId(lCustomerId);
	    }
	    criteria.setCustomerId(lCustomerId);
	    // if current user belongs to a branch, then show only those users within the branch
	    if (!StringUtil.isEmpty(UserUtil.getMmrUser().getBranch()))
	      criteria.setBranch(UserUtil.getMmrUser().getBranch());
	    ActionContext.getContext().getSession().remove("edit");
	    List<User> uslist=userService.findUserByCustomer(criteria);
	   return uslist;
   }

public static long setBusinessIdbyUserLevel() {
	// TODO Auto-generated method stub
	
	
	 Long businessId=(Long) ActionContext.getContext().getSession().get(Constants.BUSINESS_ID_SESSION);
     Long partnerId=(Long) ActionContext.getContext().getSession().get(Constants.PARTNER_ID_SESSION);
     Long countryPartnerId=(Long) ActionContext.getContext().getSession().get(Constants.NATION_ID_SESSION);
     Long branchId=(Long) ActionContext.getContext().getSession().get(Constants.BRANCH_ID_SESSION);
 
 	if(businessId!=null && partnerId==null && countryPartnerId ==null && branchId==null){
 		 

 		return businessId;            		
 		
 	}else if(businessId!=null && partnerId!=null && countryPartnerId ==null && branchId==null){
 		return partnerId;
 		 
 	}else if(businessId!=null && partnerId!=null && countryPartnerId !=null && branchId==null ){
 		

 		return countryPartnerId; 	
  
 			
 	}else if(businessId!=null && partnerId!=null && countryPartnerId !=null && branchId!=null){
 		
 		return branchId;
 		
 		 

 	}else if((businessId==null && partnerId==null && branchId==null && countryPartnerId ==null) && UserUtil.getMmrUser().isPartnerLevel()){
    
 	 

 		return UserUtil.getMmrUser().getBusinessId();
 		
 	}else if((businessId==null && partnerId==null && branchId==null && countryPartnerId!=null) && UserUtil.getMmrUser().isPartnerLevel()){

 		return countryPartnerId;
  
 
 	   }else if((businessId==null && partnerId==null && branchId!=null && countryPartnerId!=null) && UserUtil.getMmrUser().isPartnerLevel()){
 		   	 

 	 		return branchId;
 	   }
 	
 	 else if((businessId==null && partnerId==null && branchId==null && countryPartnerId ==null) && UserUtil.getMmrUser().isNationLevel()){
 		 

  		return UserUtil.getMmrUser().getBusinessId();
 		 
 	}else if((businessId==null && partnerId==null && branchId==null && countryPartnerId ==null) && UserUtil.getMmrUser().isBranchLevel()){
 	 

 		return UserUtil.getMmrUser().getBusinessId();
 	 
 	}else if((businessId==null && partnerId==null && branchId==null && countryPartnerId ==null) && UserUtil.getMmrUser().isBusinessLevel()){
 		 

 	
 		return UserUtil.getMmrUser().getBusinessId();
 	}else if((businessId==null && partnerId!=null && branchId==null && countryPartnerId ==null) && UserUtil.getMmrUser().isBusinessLevel()){
 		
 		 
 	 

 		return partnerId;
 	}else if((businessId==null && partnerId!=null && branchId==null && countryPartnerId !=null) && UserUtil.getMmrUser().isBusinessLevel()){

 		 

 		return countryPartnerId;
 		 
 	   }else if((businessId==null && partnerId!=null && branchId!=null && countryPartnerId !=null) && UserUtil.getMmrUser().isBusinessLevel()){
 
 			 
 		 

 	 		return branchId;
 			 
 	   }else if((businessId==null && partnerId==null && branchId!=null && countryPartnerId ==null) && UserUtil.getMmrUser().isNationLevel()){
 		        	 
 				 
 		return branchId;
 				 
 	   }else {
 		   return UserUtil.getMmrUser().getBusinessId();
 	   }
}

public static List<Long> getBusIdParentId(long businessId) {
	//get child businessid by parent business_id
	
   List<Long> businessIds=new ArrayList<Long>();
   List<Business> businessList=getBusinessListByBusinessId(businessId);
   if(businessList!=null && businessList.size()>0){
	   for(Business bus:businessList){
		   if(bus!=null){
		   businessIds.add(bus.getId());
		   }
	   }
   }
   HashSet hs = new HashSet();
   hs.addAll(businessIds);
   businessIds.clear();
   businessIds.addAll(hs);
	
	return businessIds;
}

@Override
public void run() {
	// TODO Auto-generated method stub
	loadDefaultPropertyForBusiness(this.businessIds);
	
	
}
   
public void loadDefaultPropertyForBusiness(List<Long> businessIds){
	
	
	BusinessDAO businessDAO=(BusinessDAO)MmrBeanLocator.getInstance().findBean("businessDAO");
	for(Long businessId:businessIds){
	addDefaultBusinessLoaders(businessId);
	
    businessDAO.addDefaultCustomerCarrier(businessId);
	System.out.println("......  customer carrier added for business......."+businessId);
    businessDAO.addDefaultPinsToBusiness(businessId);
	System.out.println("......  pins carrier added for business......."+businessId);
	}
}


private void addDefaultBusinessLoaders(long businessId) {
	// TODO Auto-generated method stub
	 BusinessDefaultLoaderDaoImpl defLoader=new BusinessDefaultLoaderDaoImpl(businessId, 1);
    List<Integer> toUpdateLoader =new ArrayList<Integer>();
	 if(getSelect()!=null && getSelect().size()>0){
		
		   
				 for(int i=0;i<getSelect().size();i++){
					 if(getSelect().get(i)){
					 toUpdateLoader.add(i+1);
					 }
					 					 
				 }
		
	}
	if(toUpdateLoader.size()==7){
		defLoader.loadAll();
		System.out.println("...... All defualt loaders added for business ......."+businessId);
	}else if(toUpdateLoader.size()>0){
		
		for(Integer i:toUpdateLoader){
			if(i==1){
				defLoader.loadBusinessCarrier();
				System.out.println("...... business carrier added for business ......."+businessId);
			}else if(i==2){
				defLoader.loadBusinessMenu();
				System.out.println("...... menus added for business ......."+businessId);
			}else if(i==3){
				defLoader.loadCustomerMarkUp();
				System.out.println("......customer_markups added for business ......."+businessId);
			}else if(i==4){
				defLoader.loadEdiInfo();
				System.out.println("...... edi info added for business ......."+businessId);
			}else if(i==5){
				defLoader.loadPoundRate();
				System.out.println("...... pound rate added for business ......."+businessId);
			}else if(i==6){
				defLoader.loadSkidRate();
				System.out.println("...... skid rate added for business ......."+businessId);
			}else if(i==7){
				defLoader.loadMerchantAccount();
				System.out.println("...... loadMerchantAccount added for business ......."+businessId);
			}
		}
		
	}
	
	 
	
}

public List<Boolean> getSelect() {
	return select;
}

public void setSelect(List<Boolean> select) {
	this.select = select;
}
public Long getBusinessId() {
	return businessId;
}
public void setBusinessId(Long businessId) {
	this.businessId = businessId;
}
public List<Long> getBusinessIds() {
	return businessIds;
}
public void setBusinessIds(List<Long> businessIds) {
	this.businessIds = businessIds;
}

public static Business getSuperParentBusiness(Long businessId){
	 BusinessDAO businessDAO=(BusinessDAO)MmrBeanLocator.getInstance().findBean("businessDAO");
	 Business inBusiness=businessDAO.getBusiessById(businessId);
	 Business parentBusiness=null;
	 if(inBusiness!=null){
		 if(inBusiness.isBranchLevel()||inBusiness.isNationLevel() ||
				 inBusiness.isPartnerLevel()){
			  parentBusiness=businessDAO.getSuperParentBusiness(businessId);
			  if(parentBusiness!=null){
				  return parentBusiness;
			  }
				  
		 }else{
			 return inBusiness;
		 }
	 }
	return null;
}
   
}
