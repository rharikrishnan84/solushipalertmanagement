package com.soluship.businessfilter.util;
 

/**
  * It is the Static class for
  * all the business filter related 
  * methods
  * @author SELVA
  */


import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.meritconinc.mmr.constants.Constants;
import com.meritconinc.mmr.dao.BusinessFilterDAO;
import com.meritconinc.mmr.model.admin.UserSearchCriteria;
import com.meritconinc.mmr.model.security.User;
import com.meritconinc.mmr.service.UserService;
import com.meritconinc.mmr.utilities.MmrBeanLocator;
import com.meritconinc.mmr.utilities.StringUtil;
import com.meritconinc.mmr.utilities.security.UserUtil;
import com.meritconinc.shiplinx.dao.BusinessDAO;
import com.meritconinc.shiplinx.dao.CarrierServiceDAO;
import com.meritconinc.shiplinx.dao.MarkupManagerDAO;
import com.meritconinc.shiplinx.model.Address;
import com.meritconinc.shiplinx.model.Business;

import org.acegisecurity.userdetails.UserDetails;

import com.meritconinc.mmr.service.acegi.CustomUser;
import com.meritconinc.shiplinx.model.BusinessFilter;
import com.meritconinc.shiplinx.model.Carrier;
import com.meritconinc.shiplinx.model.CountryPartner;
import com.meritconinc.shiplinx.model.Customer;
import com.meritconinc.shiplinx.model.LtlPoundRate;
import com.meritconinc.shiplinx.model.LtlSkidRate;
import com.meritconinc.shiplinx.model.Markup;
import com.meritconinc.shiplinx.model.Partner;
import com.meritconinc.shiplinx.model.UserBusiness;
import com.meritconinc.shiplinx.service.CustomerManager;
import com.meritconinc.shiplinx.service.MarkupManager;
import com.meritconinc.shiplinx.utils.BusinessDefaultLoaderDaoImpl;
import com.meritconinc.shiplinx.utils.ShiplinxConstants;
import com.opensymphony.xwork2.ActionContext;

import java.awt.image.BufferedImage;
import java.io.File;

import org.apache.axis2.databinding.types.soapencoding.Array;

import javax.imageio.ImageIO;

import com.meritconinc.mmr.dao.MessageDAO;
import com.meritconinc.mmr.utilities.MessageUtil;
import com.meritconinc.shiplinx.model.BusinessEmail;







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
		if(sysadminCustomerIds!=null && sysadminCustomerIds.size()>0){ 
		return sysadminCustomerIds;
		}
		        return new ArrayList<Customer>();
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
        if(UserUtil.getMmrUser() !=null && businessLevelCustomers!=null && businessLevelCustomers.size()>0){
        	        businessLevelCustomers.addAll(getUserBusinessCustomers(UserUtil.getMmrUser().getUsername()));
        	        }
        	        if(businessLevelCustomers!=null && businessLevelCustomers.size()>0){
        	        HashSet<Customer> hs = new HashSet<Customer>();
        	        hs.addAll(businessLevelCustomers);
        	       businessLevelCustomers.clear();
        	        businessLevelCustomers.addAll(hs);
        	
		return businessLevelCustomers;
        	        }
        	                return new ArrayList<Customer>();
	}

   public static List<Customer> getCustomersByBusiness(List<Business> businessList1){
	   List<Customer>  businessLevelCustomers=new ArrayList<Customer>();
	   CustomerManager customerService=(CustomerManager)MmrBeanLocator.getInstance().findBean("customerService");
	   if(businessList1!=null && businessList1.size()>0){
    	   List<Customer> customers=null;
    	   for(Business bus:businessList1){
    		  /* Customer c=new Customer();
    		   c.setBusinessId(bus.getId());
    		   customers=customerService.search(c);
    		   
    		   if(customers!=null && customers.size()>0){*/
    		   customers=getCustomerByBusiness(bus.getId());
    		   if(customers!=null && customers.size()>0 ){
    			   businessLevelCustomers.addAll(customers);
    		   }
    	   }
    	   return businessLevelCustomers;
       }
	   
	   
	   return new ArrayList<Customer>();
   }

   public static List<Customer> getCustomerByBusiness(Long id){
	        CustomerManager customerService=(CustomerManager)MmrBeanLocator.getInstance().findBean("customerService");
	        List<Customer> customers=null;
	        Customer c=new Customer();
	        if(id!=null && id>0){
	         c.setBusinessId(id);
	         customers=customerService.search(c);
	          return customers;
	        }
	        return new ArrayList<Customer>();
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
		if(UserUtil.getMmrUser() !=null){
			          partnerLevelCustomers.addAll(getUserBusinessCustomers(UserUtil.getMmrUser().getUsername()));
			        }
			        if(partnerLevelCustomers!=null && partnerLevelCustomers.size()>0){
			      HashSet<Customer> hs = new HashSet<Customer>();
			      hs.addAll(partnerLevelCustomers);
			      partnerLevelCustomers.clear();
			      partnerLevelCustomers.addAll(hs);
		return partnerLevelCustomers;
			        }
			              return new ArrayList<Customer>();
		 
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
	 		 
		
		
	     if(UserUtil.getMmrUser() !=null && nationLevelCustomers!=null && nationLevelCustomers.size()>0){
	    	         nationLevelCustomers.addAll(getUserBusinessCustomers(UserUtil.getMmrUser().getUsername()));
	    	         }
	    	         if(nationLevelCustomers!=null && nationLevelCustomers.size()>0){
	    	         HashSet<Customer> hs = new HashSet<Customer>();
	    	         hs.addAll(nationLevelCustomers);
	    	         nationLevelCustomers.clear();
	    	         nationLevelCustomers.addAll(hs);
	    	         return nationLevelCustomers;
	    	         }
	    	         return new ArrayList<Customer>();
		 
	}
 
 public static List<Customer> getUserBusinessCustomers(String username){
	    List<Customer> ubsCustomers=new ArrayList<Customer>();
	     BusinessDAO businessDAO=(BusinessDAO)MmrBeanLocator.getInstance().findBean("businessDAO");
	     List<UserBusiness> ubs=businessDAO.getUserBusinessListByUser(username);
	     if(ubs!=null && ubs.size()>0){
	     for(UserBusiness ub:ubs){
	       if(ub.getParentId()!=null && ub.getParentId()!=UserUtil.getMmrUser().getBusinessId()){
	           List<Customer>   customers=null;
	           if(ub.getParentId()>0){
	                 customers=getCustomerByBusiness(ub.getParentId());
	               if(customers!=null){
	                   ubsCustomers.addAll(customers);
	               }
	               
	           }else if(ub.getParentId()==-1){
	           /*  List<Business> buslist=BusinessFilterUtil.getBusinessListByBusinessId(ub.getPartnerId());
	               if(buslist!=null && buslist.size()>0){
	               customers=getCustomersByBusiness(buslist);
	               ubsCustomers.addAll(customers);
	               }*/
	               continue;
	           }
	       }
	       
	       if(ub.getPartnerId()!=null && ub.getPartnerId()!=UserUtil.getMmrUser().getBusinessId()){
	           
	           List<Customer> customers=null;
	           if(ub.getPartnerId()>0){
	                      customers=getCustomerByBusiness(ub.getPartnerId());
	               if(customers!=null){
	                   ubsCustomers.addAll(customers);
	               }
	           }else if(ub.getPartnerId()==-1){
	               List<Business> buslist=BusinessFilterUtil.getBusinessListByBusinessId(ub.getParentId());
	               if(buslist!=null && buslist.size()>0){
	                   Iterator itr= buslist.iterator();
	                   while(itr.hasNext()){
	                       Business b=(Business) itr.next();
	                          if(b.getId()==ub.getParentId()){
	                           itr.remove();
	                       }
	                   }
	               customers=getCustomersByBusiness(buslist);
	               ubsCustomers.addAll(customers);
	               }
	               continue;   
	           }
	       }
	       
	       if(ub.getNationId()!=null && ub.getNationId()!=UserUtil.getMmrUser().getBusinessId()){
	           List<Customer>   customers=null;
	           if(ub.getNationId()>0){
	                 customers=getCustomerByBusiness(ub.getNationId());
	               if(customers!=null){
	                   ubsCustomers.addAll(customers);
	               }
	           }else if(ub.getNationId() ==-1){
	               List<Business> buslist=BusinessFilterUtil.getBusinessListByBusinessId(ub.getPartnerId());
	               if(buslist!=null && buslist.size()>0){
	                   Iterator itr= buslist.iterator();
	                   while(itr.hasNext()){
	                       Business b=(Business) itr.next();
	                          if(b.getId()==ub.getPartnerId()){
	                           itr.remove();
	                       }
	                   }
	               customers=getCustomersByBusiness(buslist);
	               ubsCustomers.addAll(customers);
	               }
	               continue;   
	               
	           }
	       }
	       if(ub.getBranchId()!=null && ub.getBranchId()!=UserUtil.getMmrUser().getBusinessId()){
	           List<Customer>   customers=null;
	           if(ub.getBranchId()>0){
	               customers=getCustomerByBusiness(ub.getBranchId());
	               if(customers!=null){
	                   ubsCustomers.addAll(customers);
	               }
	           }else if(ub.getBranchId()==-1){
	               List<Business> buslist=BusinessFilterUtil.getBusinessListByBusinessId(ub.getNationId());
	               if(buslist!=null && buslist.size()>0){
	               Iterator itr= buslist.iterator();
	               while(itr.hasNext()){
	                   Business b=(Business) itr.next();
	                      if(b.getId()==ub.getNationId()){
	                       itr.remove();
	                   }
	               }
	               customers=getCustomersByBusiness(buslist);
	               ubsCustomers.addAll(customers);
	               }
	               continue;   
	               
	           }
	       }

	     }
	     }
	     return ubsCustomers;
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
	   if(UserUtil.getMmrUser() !=null  && salesCustomers!=null && salesCustomers.size()>0){
		         salesCustomers.addAll(getUserBusinessCustomers(UserUtil.getMmrUser().getUsername()));
		         }
		         HashSet<Customer> hs = new HashSet<Customer>();
		         if(salesCustomers !=null && salesCustomers.size()>0){
		         hs.addAll(salesCustomers);
		         salesCustomers.clear();
		         salesCustomers.addAll(hs);
		         return salesCustomers;
		         }
		         return new ArrayList<Customer>();
}

 public static List<Customer> getBranchLevelCustomers(Long businessId, Long partnerId,
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
     if(UserUtil.getMmrUser() !=null && branchLevelCustomers!=null && branchLevelCustomers.size()>0){
    	        branchLevelCustomers.addAll(getUserBusinessCustomers(UserUtil.getMmrUser().getUsername()));
    	      }
    	      if(branchLevelCustomers!=null && branchLevelCustomers.size()>0){
    	        HashSet<Customer> hs = new HashSet<Customer>();
    	           hs.addAll(branchLevelCustomers);
    	          branchLevelCustomers.clear();
    	         branchLevelCustomers.addAll(hs);
	return branchLevelCustomers;
    	      }
    	           return new ArrayList<Customer>();
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
				}else if(logInuser.getUserRole().equalsIgnoreCase(ShiplinxConstants.ROLE_SOLUTIONMANAGER) || logInuser.getUserRole().equalsIgnoreCase(ShiplinxConstants.ROLE_CUSTOMER_SHIPPER)){
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
         if(c!=null){
         Customers.add(c);
         }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		   
		   
	   }
	   
	   System.out.println("customer admin....."+logInuser.getUsername()+"...... into the get customers..."+Customers.size());
	   if(Customers!=null && Customers.size()>0){ 
	return  Customers;
	   }
	        return new ArrayList<Customer>();
}
public static List<Business> getBusinessListByBusinessId(Long businessId){
	   businessList =new ArrayList<Business>();
	   BusinessDAO businessDAO=(BusinessDAO)MmrBeanLocator.getInstance().findBean("businessDAO");
	  
	   
	   if(businessId!=null && businessId>0){
	   List<Business> businessListTemp = getBusinessByParentBusiness(businessId);
	   businessListTemp.add(businessDAO.getBusiessById(businessId));
	   
	   
	   return businessListTemp;
	   }
	         return new ArrayList<Business>();
   }
   
   //fetching the child business list based on the business id
   public static List<Business> getBusinessByParentBusiness(Long businessId){
	   
	   BusinessDAO businessDAO=(BusinessDAO)MmrBeanLocator.getInstance().findBean("businessDAO");
	   if(businessId!=null && businessId>0){
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
	   /*}else{
		   return new ArrayList<Business>();*/
	   }
	   }
	         return new ArrayList<Business>();
	   
   }
   
   public static List<User> getUsersByBusiness(Long businessId) throws Exception{
	 
	   UserService userService=(UserService)MmrBeanLocator.getInstance().findBean("userService");
	   Long cid=(Long) ActionContext.getContext().getSession().get("cid");
	   if(businessId!=null && businessId>0){
	   if(cid!=null){
 	   return getUserListByBusinessLevel(businessId,cid);
	   }else{
		   return getUserListByBusinessLevel(businessId,cid);
	   }
	   }
	         return new ArrayList<User>();
		   
   }
   
   
   public static List<User> getUserListByBusinessLevel(Long businessId ,Long cid) throws Exception{
	   BusinessDAO businessDAO=(BusinessDAO)MmrBeanLocator.getInstance().findBean("businessDAO");
	   if(businessId !=null && businessId>0){ 
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
	           return new ArrayList<User>();
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
	
	if(UserUtil.getMmrUser()!=null){
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
 				 
 	  }else if(UserUtil.getMmrUser()!=null) {
 		   return UserUtil.getMmrUser().getBusinessId();
 	 }else{
 				   return 1L;
 	   }
	}else{
		 		   return 1L;
 	   }
}

public static List<Long> getBusIdParentId(long businessId) {
	//get child businessid by parent business_id
	if(businessId >0){
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
	 return new ArrayList<Long>();
}

@Override
public void run() {
	// TODO Auto-generated method stub
	loadDefaultPropertyForBusiness(this.businessIds);
	
	
}
   
public void loadDefaultPropertyForBusiness(List<Long> businessIds){
	
	int count = 0;
	BusinessDAO businessDAO=(BusinessDAO)MmrBeanLocator.getInstance().findBean("businessDAO");
	if(businessIds!=null && businessIds.size()>0){
	for(Long businessId:businessIds){
		count=count+1;
						if(count > 1){
							addDefaultBusinessLoaders(businessId);
						}else{
							addDefaultBusinessLoaders1(businessId);
						}
	
    businessDAO.addDefaultCustomerCarrier(businessId);
	System.out.println("......  customer carrier added for business......."+businessId);
    businessDAO.addDefaultPinsToBusiness(businessId);
	System.out.println("......  pins carrier added for business......."+businessId);
	 }
	}
}

public static void loadDefaultProperty(long businessId){
	
	BusinessDAO businessDAO=(BusinessDAO)MmrBeanLocator.getInstance().findBean("businessDAO");
	if(businessId > 0){
	addDefaultLoaders(businessId);
    businessDAO.addDefaultCustomerCarrier(businessId);
	System.out.println("......  customer carrier added for business......."+businessId);
    businessDAO.addDefaultPinsToBusiness(businessId);
	System.out.println("......  pins carrier added for business......."+businessId);
	 
	}
}

private static void addDefaultLoaders(long businessId) {
	// TODO Auto-generated method stub
	 BusinessDefaultLoaderDaoImpl defLoader=new BusinessDefaultLoaderDaoImpl(businessId, 1);
	 BusinessDAO businessDAO=(BusinessDAO)MmrBeanLocator.getInstance().findBean("businessDAO");
	 if(businessId>0){
		 defLoader.loadAll();
		 businessDAO.setCopyMarkupFlag(businessId);
	 }
	
}
private void addDefaultBusinessLoaders1(long businessId) {
	// TODO Auto-generated method stub
	 BusinessDefaultLoaderDaoImpl defLoader=new BusinessDefaultLoaderDaoImpl(businessId, 1);
	 if(businessId>0){
    List<Integer> toUpdateLoader =new ArrayList<Integer>();
	 if(getSelect()!=null && getSelect().size()>0){
		
		   
				 for(int i=0;i<getSelect().size();i++){
					 if(getSelect().get(i)){
					 toUpdateLoader.add(i+1);
					 }
					 					 
				 }
		
	}
	if(toUpdateLoader.size()==7){
		defLoader.loadAll1();
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
	
}


private void addDefaultBusinessLoaders(long businessId) {
//TODO Auto-generated method stub
	 BusinessDefaultLoaderDaoImpl defLoader=new BusinessDefaultLoaderDaoImpl(businessId, 1);
	 BusinessDAO businessDAO=(BusinessDAO)MmrBeanLocator.getInstance().findBean("businessDAO");
	 if(businessId>0){
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
			}/*else if(i==3){
				defLoader.loadCustomerMarkUp();
				System.out.println("......customer_markups added for business ......."+businessId);
			}*/else if(i==4){
				defLoader.loadEdiInfo();
				System.out.println("...... edi info added for business ......."+businessId);
			}/*else if(i==5){
				defLoader.loadPoundRate();
				System.out.println("...... pound rate added for business ......."+businessId);
			}else if(i==6){
				defLoader.loadSkidRate();
				System.out.println("...... skid rate added for business ......."+businessId);
			}*/else if(i==7){
				defLoader.loadMerchantAccount();
				System.out.println("...... loadMerchantAccount added for business ......."+businessId);
			}
		}
		
	}
	businessDAO.setCopyMarkupFlag(businessId);
	
	
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
	if(businessId !=null && businessId >0) {
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
	}
	return null;
}

/*
 * get the business Ids of user if user_business table is affected
 * by the sysadmin of any user
 */
public static List<Long> getUserBusinessIds(String username, List<UserBusiness> ubs) {
	// TODO Auto-generated method stub
	List<Long> userBusIds=new ArrayList<Long>();
	if(username!=null && ubs !=null && ubs.size()>0){
		for(UserBusiness ub:ubs){
			//Root Busienss
			if(ub.getParentId()!=null && ub.getParentId()>0){
				userBusIds.add(ub.getParentId());
			}
			//Partner business
			if(ub.getPartnerId()!=null && ub.getPartnerId()>0){
				userBusIds.add(ub.getPartnerId());
			}else if(ub.getParentId()!=null && ub.getPartnerId()==-1){
				userBusIds.addAll(getBusIdParentId(ub.getParentId()));
				continue;
			}
			//NAtion business
			if(ub.getNationId()!=null && ub.getNationId()>0){
				userBusIds.add(ub.getNationId());
			}else if(ub.getPartnerId()!=null && ub.getNationId()==-1){
				userBusIds.addAll(getBusIdParentId(ub.getPartnerId()));
				continue;
			}
			//Branch business
			if(ub.getBranchId()!=null && ub.getBranchId()>0){
				userBusIds.add(ub.getBranchId());
			}else if(ub.getNationId()!=null && ub.getBranchId()==-1){
				userBusIds.addAll(getBusIdParentId(ub.getNationId()));
				continue;
			}
			
		}
		return getvalidatedBusIds(userBusIds);
	}
	return null;
}


/*
 * get the redancy less business list
 */
public static List<Long> getvalidatedBusIds(List<Long> userBusIds) {
	// TODO Auto-generated method stub
	if(userBusIds!=null && userBusIds.size()>0){
	HashSet<Long> hs = new HashSet<Long>();
    hs.addAll(userBusIds);
    userBusIds.clear();
    userBusIds.addAll(hs);
	return userBusIds;
	}
	return new ArrayList<Long>();
}
   
/*
 * get the userBusiness of the 
 * any user 
 */
public static List<UserBusiness> getUserBusinessByUserName(String username){
	 BusinessDAO businessDAO=(BusinessDAO)MmrBeanLocator.getInstance().findBean("businessDAO");
	  List<UserBusiness> ubs=new ArrayList<UserBusiness>();
	  if(UserUtil.getMmrUser()!=null){
	  ubs=businessDAO.getUserBusinessListByUser(UserUtil.getMmrUser().getUsername());
	  }
	  if(ubs!=null && ubs.size()>0){
		  return ubs;
	  }
		return new ArrayList<UserBusiness>(); 
				
	
}

/**
 * reoload user withour
 * restarting the server
 */
public static void reloadUser(String adminUser){
	ActionContext.getContext().getSession().remove(ShiplinxConstants.ADMIN_USER);
	ActionContext.getContext().getSession().remove("userlogin");
	UserDetails userDetails = UserUtil.reloadUserContext(adminUser);  
    UserUtil.setMmrUser((User)((CustomUser)userDetails).getUserInfo()); 
	ActionContext.getContext().getSession().put("username", adminUser);
	String menu = "Admin";
	ActionContext.getContext().getSession().remove("HighLightMenu");
	ActionContext.getContext().getSession().put("HighLightMenu", menu);
 }


/**
 * Get the List CSS files of
 * given File directory
 * @param dir
 * @return
 */
public static List<File> getCSSFileList(File dir) {
		List<File> fileList = new ArrayList<File>();
		File[] files = dir.listFiles();
		if (files != null) {
			for (File file : files) {
				if (file.isDirectory()) {
					getCSSFileList(file);
				} else {
					if (getExtention(file).equalsIgnoreCase("css")
							&& (file.getName().equalsIgnoreCase(
									ShiplinxConstants.LAPTOP_CSS) || file
									.getName().equalsIgnoreCase(
											ShiplinxConstants.DEMO_TABLE_CSS))) {
						fileList.add(file);
					}
				}
			}
		}

		return fileList;

	}

   /**
    * Get the File Extention of thee file
    * @param f
    * @return
    */
	public static String getExtention(File f) {
		String name = f.getName();
		int dot = name.lastIndexOf('.');
		String base = (dot == -1) ? name : name.substring(0, dot);
		String extension = (dot == -1) ? "" : name.substring(dot + 1);
		return extension;
	}
	
	
	/**
	 * static method get the Business email body of 
	 * of businesss using its msgid
	 * @param businessId
	 * @param msgidEmail
	 * @return
	 */
	public static String getEmailBody(long businessId,
			String msgidEmail ) {
		// TODO Auto-generated method stub
		MessageDAO messageDAO=(MessageDAO)MmrBeanLocator.getInstance().findBean("messageDAOTarget");
		BusinessDAO businessDAO=(BusinessDAO)MmrBeanLocator.getInstance().findBean("businessDAO");
		String locale=null;
	      List<BusinessEmail> businessEmail=businessDAO.getBusinessEmails(businessId);
	      BusinessEmail busEmail=null;
	      if(businessEmail!=null && businessEmail.size()>0){
	       for(BusinessEmail be:businessEmail){
	    	   if(be.getEmailType().equalsIgnoreCase(msgidEmail)){
	    		   busEmail=be;
	    		  locale=be.getLocale();
	    	   }
	       }
	      }
	     
	      
	      if(locale==null){
	    	  locale=MessageUtil.getLocale();
	      }
	      String body =null;
	      if(busEmail!=null){
	    	  //body=MessageUtil.getMessage(busEmail.getMsgId(),UserUtil.getMmrUser().getLocale());
	    	body=  messageDAO.getMessage(busEmail.getMsgId(),locale);
	    	
	      }else{
	      return null;
	      }
	      
		return body;
	}
	
	/*Function get the business_carrier
	 * from their parent_level business 
	 * if not found at parent_level
	 * it will get that from super parent business
	 * @param carrierId,businessId
	 */
	
	public static Carrier getCarrierByBusinessFromSuperBusiness(Long carrierId, Long businessId){
		 BusinessDAO businessDAO=(BusinessDAO)MmrBeanLocator.getInstance().findBean("businessDAO");
		 CarrierServiceDAO carrierServiceDAO=(CarrierServiceDAO) MmrBeanLocator.getInstance().findBean("carrierServiceDAO");
		 Business curBus=businessDAO.getBusiessById(businessId);
		 Carrier c=new Carrier();
	   BusinessFilter bf=new BusinessFilter();
		 if(curBus!=null && !curBus.isPartnerLevel() && !curBus.isNationLevel() && !curBus.isBranchLevel() && curBus.getPartnerId()==0 
					&& curBus.getParentBusinessId()==0 && curBus.getBranchId()==0 && curBus.getCountryPartnerId()==0){
			 
				   c=carrierServiceDAO.getCarrierByBusiness(carrierId,businessId);
				return c;
			}else if(curBus!=null&&curBus.isPartnerLevel()){  //partner level business  to get the business objects  of child business under the tree
			
				bf= getBusinessFilterAtPartnerLevel(curBus);
				c=carrierServiceDAO.getCarrierByBusiness(carrierId,businessId);
				if(c==null && bf!=null){
					c=carrierServiceDAO.getCarrierByBusiness(carrierId,bf.getParentBus().getId());
				}
				return c;
				
			}else if(curBus!=null&&curBus.isNationLevel()){   //nation level business  to get the business objects  of child business under the tree
				
				bf= getBusinessFilterAtNationLevel(curBus);
				c=carrierServiceDAO.getCarrierByBusiness(carrierId,businessId);
				if(c==null && bf!=null){
					c=carrierServiceDAO.getCarrierByBusiness(carrierId,bf.getPartnerBus().getId());
			} 
				if(c==null && bf!=null){
					c=carrierServiceDAO.getCarrierByBusiness(carrierId,bf.getParentBus().getId());
				}
				return c;
				
			}else if(curBus!=null&&curBus.isBranchLevel()){    //branch level business  to get the business objects  of child business under the tree
				
				bf= getBusinessFilterAtBranchLevel(curBus);
				c=carrierServiceDAO.getCarrierByBusiness(carrierId,businessId);
				if(c==null && bf!=null){
					c=carrierServiceDAO.getCarrierByBusiness(carrierId,bf.getNationBus().getId());
				} 
				if(c==null && bf!=null){
					c=carrierServiceDAO.getCarrierByBusiness(carrierId,bf.getPartnerBus().getId());
				}
				if(c==null && bf!=null){
					c=carrierServiceDAO.getCarrierByBusiness(carrierId,bf.getParentBus().getId());
				}
				return c;
			}
		return null;
	}
	
	
	
	
	
	 
	 /*
	  * parent level business  to get the business objects  of child business under the tree
	  * @param curBus
	  * @return
	  */
	public static BusinessFilter getBusinessFilterAtBranchLevel(Business curBus) {
		// TODO Auto-generated method stub
		
		BusinessDAO businessDAO=(BusinessDAO)MmrBeanLocator.getInstance().findBean("businessDAO");
		BusinessFilter bf=new BusinessFilter();
		
		if(curBus!=null){
			bf.setBranchBus(curBus);
			Business nationBus=businessDAO.getBusiessById(curBus.getParentBusinessId());
			Business partnerBus=businessDAO.getBusiessById(nationBus.getParentBusinessId());
			Business parentBus=businessDAO.getBusiessById(partnerBus.getParentBusinessId());
			if(nationBus!=null){
				bf.setNationBus(nationBus);
			}
			if(partnerBus!=null){
				bf.setPartnerBus(partnerBus);
			}
			if(parentBus!=null){
				bf.setParentBus(parentBus);
			}
			return bf;
		}
		
		
		return null;
	}
	
	public static BusinessFilter getBusinessFilterAtNationLevel(Business curBus) {
		// TODO Auto-generated method stub
		BusinessDAO businessDAO=(BusinessDAO)MmrBeanLocator.getInstance().findBean("businessDAO");
		if(curBus!=null){
		BusinessFilter bf=new BusinessFilter();
		bf.setNationBus(curBus);
		Business partnerBus=businessDAO.getBusiessById(curBus.getParentBusinessId());
		Business parentBus=businessDAO.getBusiessById(partnerBus.getParentBusinessId());
		if(parentBus!=null){
			bf.setParentBus(parentBus);
		}
	    if(partnerBus!=null){
	    	bf.setPartnerBus(partnerBus);
	    }
	     
			List<Business> branchLevBusList=new ArrayList<Business>();
			  
					List<Business> busList=businessDAO.getBranchBuisness(curBus.getPartnerId(),curBus.getCountryPartnerId());
		    		if(busList!=null  && busList.size()>0){
		    			branchLevBusList.addAll(branchLevBusList);
		    		}
				
		 
			if(branchLevBusList!=null && branchLevBusList.size()>0){
				bf.setBranchBusList(branchLevBusList);
			}
		
			return bf;
		}
		return null;
	}
	
	
	public static BusinessFilter getBusinessFilterAtPartnerLevel(Business curBus) {
		// TODO Auto-generated method stub
		
		BusinessDAO businessDAO=(BusinessDAO)MmrBeanLocator.getInstance().findBean("businessDAO");
		BusinessFilter bf=new BusinessFilter();
		if(curBus!=null){
		Business parentBus=businessDAO.getBusiessById(curBus.getParentBusinessId());
		Business partnerBus=businessDAO.getBusiessById(curBus.getId());
		if(parentBus!=null){
			bf.setParentBus(parentBus);	
		}
		if(partnerBus!=null){
			bf.setPartnerBus(partnerBus);
		}
	     
		 List<Business> nationBusList=new ArrayList<Business>();
		    if(partnerBus!=null){
	    		List<Business> busList=businessDAO.getCountryBusiness(partnerBus.getId());
	    		if(busList!=null  && busList.size()>0){
	   		
	    			nationBusList.addAll(busList);
	    		}
		    	if(nationBusList!=null && nationBusList.size()>0){
		    		List<Business> branchLevBusList=new ArrayList<Business>();
		    		for(Business nation:nationBusList){
		    			if(nation!=null){
		    				 busList=businessDAO.getBranchBuisness(nation.getPartnerId(),nation.getCountryPartnerId());
		    	    		if(busList!=null  && busList.size()>0){
		    	    			branchLevBusList.addAll(branchLevBusList);
		    	    		}
	    			}
	    		}
		    		bf.setNationBusList(nationBusList);
		    		if(branchLevBusList!=null && branchLevBusList.size()>0){
		    			bf.setBranchBusList(branchLevBusList);
		    		}
		    	}
		    	
		    }
		return bf;
		}
		
		return null;
	}
	
	private static BusinessFilter getBusinessFilterAtParentLevel(Business curBus) {
		// TODO Auto-generated method stub
		 BusinessDAO businessDAO=(BusinessDAO)MmrBeanLocator.getInstance().findBean("businessDAO");
		if(curBus!=null){ 
			BusinessFilter bf=new BusinessFilter();
		    bf.setParentBus(curBus);
		    List<Business> partnerBusiness=businessDAO.getPartnerBusiness(curBus.getId());
		    List<Business> nationBusList=new ArrayList<Business>();
		    if(partnerBusiness!=null && partnerBusiness.size()>0){
		    	bf.setParentBusList(partnerBusiness);
		    	for(Business partner :partnerBusiness){
		    		if(partner!=null){
			    		List<Business> busList=businessDAO.getCountryBusiness(partner.getId());
			    		if(busList!=null  && busList.size()>0){
			    		
			    			nationBusList.addAll(busList);
			    		}
		    		}
		    	}
		    	bf.setParentBusList(partnerBusiness);
		    	if(nationBusList!=null && nationBusList.size()>0){
		    		
		    		List<Business> branchLevBusList=new ArrayList<Business>();
		    		
		    		for(Business nation:nationBusList){
		    		
		    			if(nation!=null){
		    				List<Business> busList=businessDAO.getBranchBuisness(nation.getPartnerId(),nation.getCountryPartnerId());
		    	    		if(busList!=null  && busList.size()>0){
		    	    			branchLevBusList.addAll(branchLevBusList);
		    	    		}
		    			}
		    		}
		    		bf.setNationBusList(nationBusList);
		    	
		    		
		    		if(branchLevBusList!=null && branchLevBusList.size()>0){
		    			bf.setBranchBusList(branchLevBusList);
		    		}
	    	}
	    	
		    	
	    }
		    
		    return bf;
		}
	    
	    
	    
		return null;
	}
	public static List<Carrier> getCarriersForBusinessByBusinessLevel(
			long businessId) {
		// TODO Auto-generated method stub
		BusinessDAO businessDAO=(BusinessDAO)MmrBeanLocator.getInstance().findBean("businessDAO");
		 CarrierServiceDAO carrierServiceDAO=(CarrierServiceDAO) MmrBeanLocator.getInstance().findBean("carrierServiceDAO");
		 Business curBus=businessDAO.getBusiessById(businessId);
		 List<Carrier> carriers=carrierServiceDAO.getCarriersForBusiness(businessId);
	   BusinessFilter bf=new BusinessFilter();
		 if(curBus!=null && !curBus.isPartnerLevel() && !curBus.isNationLevel() && !curBus.isBranchLevel() && curBus.getPartnerId()==0 
					&& curBus.getParentBusinessId()==0 && curBus.getBranchId()==0 && curBus.getCountryPartnerId()==0){
			 
			 carriers=carrierServiceDAO.getCarriersForBusiness(businessId);
				return carriers;
			}else if(curBus!=null&&curBus.isPartnerLevel()){  //partner level business  to get the business objects  of child business under the tree
				
				bf= getBusinessFilterAtPartnerLevel(curBus);
				if((carriers==null||carriers.size()==0)&& bf!=null){
					 carriers=carrierServiceDAO.getCarriersForBusiness(bf.getParentBus().getId());
				}
				return carriers;
				
		}else if(curBus!=null&& curBus.isNationLevel()){   //nation level business  to get the business objects  of child business under the tree
				
	
				bf= getBusinessFilterAtNationLevel(curBus);
				
				if((carriers==null||carriers.size()==0)&& bf!=null){
					 carriers=carrierServiceDAO.getCarriersForBusiness(bf.getPartnerBus().getId());
				}
				
				if((carriers==null||carriers.size()==0)&& bf!=null){
					 carriers=carrierServiceDAO.getCarriersForBusiness(bf.getParentBus().getId());
				}
				return carriers;
				
				
			}else if(curBus!=null&&curBus.isBranchLevel()){    //branch level business  to get the business objects  of child business under the tree
	
				bf= getBusinessFilterAtBranchLevel(curBus);
				if((carriers==null||carriers.size()==0)&& bf!=null){
					 carriers=carrierServiceDAO.getCarriersForBusiness(bf.getNationBus().getId());
				}
				if((carriers==null||carriers.size()==0)&& bf!=null){
					 carriers=carrierServiceDAO.getCarriersForBusiness(bf.getPartnerBus().getId());
				}
				
				if((carriers==null||carriers.size()==0)&& bf!=null){
					 carriers=carrierServiceDAO.getCarriersForBusiness(bf.getParentBus().getId());
				}
				
			
				return carriers;
				
			}
	
		
		return null;
	}
	public static Markup getMarkupByBusinessLevel(Markup markup) {
		// TODO Auto-generated method stub
		
		return markup;
	}
	public static List<Markup> getMarkupListForCustomer(MarkupManagerDAO markupDAO,
			Markup markup) {
		// TODO Auto-generated method stub
		BusinessDAO businessDAO=(BusinessDAO)MmrBeanLocator.getInstance().findBean("businessDAO");
		long oldbus = markup.getBusinessId();
		long busId;
		 if(markup!=null){
			 Business curBus=businessDAO.getBusiessById(markup.getBusinessId());
			 BusinessFilter bf=new BusinessFilter();
			 List<Markup> mList=new ArrayList<Markup>();
			 
			 if(curBus!=null && !curBus.isPartnerLevel() && !curBus.isNationLevel() && !curBus.isBranchLevel() && curBus.getPartnerId()==0 
					&& curBus.getParentBusinessId()==0 && curBus.getBranchId()==0 && curBus.getCountryPartnerId()==0){
				 
				 mList= markupDAO.getMarkupListForCustomer(markup);
				 if(mList == null || mList.size() == 0 || mList.isEmpty()){
					 busId = 1;
					 markup.setBusinessId(busId);
					 mList= markupDAO.getMarkupListForCustomer(markup);
				 }
				 markup.setBusinessId(oldbus);
				 return mList;
				 
				}else if(curBus!=null && curBus.isPartnerLevel()  ){  //partner level business  to get the business objects  of child business under the tree
					
					bf= getBusinessFilterAtPartnerLevel(curBus);
					if(bf!=null){
					 mList= markupDAO.getMarkupListForCustomer(markup);
					 if((mList==null || mList.size()==0)&& bf.getParentBus() !=null){
						 markup.setBusinessId(bf.getParentBus().getId());
						 mList= markupDAO.getMarkupListForCustomer(markup);
					 }
					 
					}
					markup.setBusinessId(oldbus);
					return mList;
					
			}else if(curBus!=null&&curBus.isNationLevel()){   //nation level business  to get the business objects  of child business under the tree
					
	
				bf= getBusinessFilterAtNationLevel(curBus);
					
					if(bf!=null){
						 mList= markupDAO.getMarkupListForCustomer(markup);
						 if((mList==null || mList.size()==0)&& bf.getPartnerBus() !=null){
							 markup.setBusinessId(bf.getPartnerBus().getId());
							 mList= markupDAO.getMarkupListForCustomer(markup);
						 }
						 if((mList==null || mList.size()==0)&& bf.getParentBus() !=null){
							 markup.setBusinessId(bf.getParentBus().getId());
						     mList= markupDAO.getMarkupListForCustomer(markup);
						 }
						 
						}
					markup.setBusinessId(oldbus);
						return mList;
					 
					
				}else if(curBus!=null&&curBus.isBranchLevel() ){    //branch level business  to get the business objects  of child business under the tree
	
					bf= getBusinessFilterAtBranchLevel(curBus);
					 
		   
					
					mList= markupDAO.getMarkupListForCustomer(markup);
					if(bf!=null){
						 if((mList==null || mList.size()==0)&& bf.getNationBus() !=null){
						 markup.setBusinessId(bf.getNationBus().getId());
							 mList= markupDAO.getMarkupListForCustomer(markup);
						 }
						 if((mList==null || mList.size()==0)&& bf.getPartnerBus() !=null){
							 markup.setBusinessId(bf.getPartnerBus().getId());
							 mList= markupDAO.getMarkupListForCustomer(markup);
						 }
						 if((mList==null || mList.size()==0)&& bf.getParentBus() !=null){
							 markup.setBusinessId(bf.getParentBus().getId());
						     mList= markupDAO.getMarkupListForCustomer(markup);
						 }
						 
						}
					markup.setBusinessId(oldbus);
					 return mList;
				}
					
			
		 }
	
		return null;
	}
	public static List<Markup> getMarkupList(MarkupManagerDAO markupDAO,
			Markup markup) {
		// TODO Auto-generated method stub
		
	BusinessDAO businessDAO=(BusinessDAO)MmrBeanLocator.getInstance().findBean("businessDAO");
	long busId = markup.getBusinessId();
		 if(markup!=null){
			 Business curBus=businessDAO.getBusiessById(markup.getBusinessId());
			 BusinessFilter bf=new BusinessFilter();
			 List<Markup> mList=new ArrayList<Markup>();
			 
			 if(curBus!=null && !curBus.isPartnerLevel() && !curBus.isNationLevel() && !curBus.isBranchLevel() && curBus.getPartnerId()==0 
						&& curBus.getParentBusinessId()==0 && curBus.getBranchId()==0 && curBus.getCountryPartnerId()==0){
				 
				 mList= markupDAO.getMarkupList(markup);
				 markup.setBusinessId(busId);
				 return mList;
				 
				}else if(curBus!=null && curBus.isPartnerLevel()  ){  //partner level business  to get the business objects  of child business under the tree
					
					bf= getBusinessFilterAtPartnerLevel(curBus);
					if(bf!=null){
					 mList= markupDAO.getMarkupList(markup);
					 if((mList==null || mList.size()==0)&& bf.getParentBus() !=null){
						 markup.setBusinessId(bf.getParentBus().getId());
						 mList= markupDAO.getMarkupList(markup);
					 }
					 
					}
					 markup.setBusinessId(busId);
					return mList;
					
				}else if(curBus!=null&&curBus.isNationLevel()){   //nation level business  to get the business objects  of child business under the tree
					
	
					bf= getBusinessFilterAtNationLevel(curBus);
					
					if(bf!=null){
						 mList= markupDAO.getMarkupList(markup);
						 if((mList==null || mList.size()==0)&& bf.getPartnerBus() !=null){
							 markup.setBusinessId(bf.getPartnerBus().getId());
							 mList= markupDAO.getMarkupList(markup);
						 }
						 if((mList==null || mList.size()==0)&& bf.getParentBus() !=null){
							 markup.setBusinessId(bf.getParentBus().getId());
						     mList= markupDAO.getMarkupList(markup);
						 }
						 
						}
					 markup.setBusinessId(busId);
						return mList;
					 
					
				}else if(curBus!=null&&curBus.isBranchLevel() ){    //branch level business  to get the business objects  of child business under the tree
	
					bf= getBusinessFilterAtBranchLevel(curBus);
					 
		   
					
					mList= markupDAO.getMarkupList(markup);
					if(bf!=null){
						 if((mList==null || mList.size()==0)&& bf.getNationBus() !=null){
							 markup.setBusinessId(bf.getNationBus().getId());
							 mList= markupDAO.getMarkupList(markup);
						 }
						 if((mList==null || mList.size()==0)&& bf.getPartnerBus() !=null){
							 markup.setBusinessId(bf.getPartnerBus().getId());
							 mList= markupDAO.getMarkupList(markup);
						 }
						 if((mList==null || mList.size()==0)&& bf.getParentBus() !=null){
							 markup.setBusinessId(bf.getParentBus().getId());
						     mList= markupDAO.getMarkupList(markup);
						 }
						 
						}
					 markup.setBusinessId(busId);
					 return mList;
				}
					
			
		 }
		return null;
	}
	public static List<Markup> getMarkupListForUniqueMarkup(
			MarkupManagerDAO markupDAO, Markup markup) {
		// TODO Auto-generated method stub
		BusinessDAO businessDAO=(BusinessDAO)MmrBeanLocator.getInstance().findBean("businessDAO");
		long busId = markup.getBusinessId();
		
		 if(markup!=null){
			 Business curBus=businessDAO.getBusiessById(markup.getBusinessId());
			 BusinessFilter bf=new BusinessFilter();
			 List<Markup> mList=new ArrayList<Markup>();
			 
			 if(curBus!=null && !curBus.isPartnerLevel() && !curBus.isNationLevel() && !curBus.isBranchLevel() && curBus.getPartnerId()==0 
						&& curBus.getParentBusinessId()==0 && curBus.getBranchId()==0 && curBus.getCountryPartnerId()==0){
				 
				 mList= markupDAO.getMarkupListForUniqueMarkup(markup);
				 
				 markup.setBusinessId(busId);
				 return mList;
				 
				}else if(curBus!=null && curBus.isPartnerLevel()  ){  //partner level business  to get the business objects  of child business under the tree
					
					bf= getBusinessFilterAtPartnerLevel(curBus);
					if(bf!=null){
					 mList= markupDAO.getMarkupListForUniqueMarkup(markup);
					 if((mList==null || mList.size()==0)&& bf.getParentBus() !=null){
						 markup.setBusinessId(bf.getParentBus().getId());
						 mList= markupDAO.getMarkupListForUniqueMarkup(markup);
					 }
					 
					}
					markup.setBusinessId(busId);
					return mList;
					
				}else if(curBus!=null&&curBus.isNationLevel()){   //nation level business  to get the business objects  of child business under the tree
					
	
					bf= getBusinessFilterAtNationLevel(curBus);
					
					if(bf!=null){
						 mList= markupDAO.getMarkupListForUniqueMarkup(markup);
						 if((mList==null || mList.size()==0)&& bf.getPartnerBus() !=null){
							 markup.setBusinessId(bf.getPartnerBus().getId());
							 mList= markupDAO.getMarkupListForUniqueMarkup(markup);
						 }
						 if((mList==null || mList.size()==0)&& bf.getParentBus() !=null){
							 markup.setBusinessId(bf.getParentBus().getId());
						     mList= markupDAO.getMarkupListForUniqueMarkup(markup);
						 }
						 
						}
					markup.setBusinessId(busId);
						return mList;
					 
					
				}else if(curBus!=null&&curBus.isBranchLevel() ){    //branch level business  to get the business objects  of child business under the tree
	
					bf= getBusinessFilterAtBranchLevel(curBus);
					 
		   
					
					mList= markupDAO.getMarkupListForUniqueMarkup(markup);
					if(bf!=null){
						 if((mList==null || mList.size()==0)&& bf.getNationBus() !=null){
							 markup.setBusinessId(bf.getNationBus().getId());
							 mList= markupDAO.getMarkupListForUniqueMarkup(markup);
						 }
						 if((mList==null || mList.size()==0)&& bf.getPartnerBus() !=null){
							 markup.setBusinessId(bf.getPartnerBus().getId());
							 mList= markupDAO.getMarkupListForUniqueMarkup(markup);
						 }
						 if((mList==null || mList.size()==0)&& bf.getParentBus() !=null){
							 markup.setBusinessId(bf.getParentBus().getId());
							 mList= markupDAO.getMarkupListForUniqueMarkup(markup);
						 }
						 
						}
					markup.setBusinessId(busId);
					 return mList;
				}
				
			
		 }
		return null;
	}
	public static Markup findBaseMarkup(MarkupManagerDAO markupDAO, Markup markup) {
		// TODO Auto-generated method stub
		BusinessDAO businessDAO=(BusinessDAO)MmrBeanLocator.getInstance().findBean("businessDAO");
		long busId = markup.getBusinessId();
		 if(markup!=null){
			 Business curBus=businessDAO.getBusiessById(markup.getBusinessId());
			 BusinessFilter bf=new BusinessFilter();
			Markup m=new  Markup();
			 
			 if(curBus!=null && !curBus.isPartnerLevel() && !curBus.isNationLevel() && !curBus.isBranchLevel() && curBus.getPartnerId()==0 
						&& curBus.getParentBusinessId()==0 && curBus.getBranchId()==0 && curBus.getCountryPartnerId()==0){
				 
				 m= markupDAO.findBaseMarkup(markup);
				 markup.setBusinessId(busId);
				 return m;
				 
				}else if(curBus!=null && curBus.isPartnerLevel()  ){  //partner level business  to get the business objects  of child business under the tree
					
					bf= getBusinessFilterAtPartnerLevel(curBus);
					if(bf!=null){
					 m= markupDAO.findBaseMarkup(markup);
					 if((m==null)&& bf.getParentBus() !=null){
						 markup.setBusinessId(bf.getParentBus().getId());
						 m= markupDAO.findBaseMarkup(markup);
					 }
					 
					}
					markup.setBusinessId(busId);
					return m;
					
				}else if(curBus!=null&&curBus.isNationLevel()){   //nation level business  to get the business objects  of child business under the tree
					
	
					bf= getBusinessFilterAtNationLevel(curBus);
					
					if(bf!=null){
						 m= markupDAO.findBaseMarkup(markup);
						 if((m==null)&& bf.getPartnerBus() !=null){
							 markup.setBusinessId(bf.getPartnerBus().getId());
							 m= markupDAO.findBaseMarkup(markup);
						 }
						 if((m==null)&& bf.getParentBus() !=null){
							 markup.setBusinessId(bf.getParentBus().getId());
						     m= markupDAO.findBaseMarkup(markup);
						 }
						 
						}
					markup.setBusinessId(busId);
						return m;
					 
					
				}else if(curBus!=null&&curBus.isBranchLevel() ){    //branch level business  to get the business objects  of child business under the tree
	
					bf= getBusinessFilterAtBranchLevel(curBus);
					 
		   
					
					m= markupDAO.findBaseMarkup(markup);
					if(bf!=null){
						 if((m==null)&& bf.getNationBus() !=null){
							 markup.setBusinessId(bf.getNationBus().getId());
							 m= markupDAO.findBaseMarkup(markup);
						 }
						 if((m==null)&& bf.getPartnerBus() !=null){
							 markup.setBusinessId(bf.getPartnerBus().getId());
							 m= markupDAO.findBaseMarkup(markup);
						 }
						 if((m==null)&& bf.getParentBus() !=null){
							 markup.setBusinessId(bf.getParentBus().getId());
						     m= markupDAO.findBaseMarkup(markup);
						 }
						 
						}
					markup.setBusinessId(busId);
					 return m;
				}
					
			
		 }
		 
		return null;
	}
	
	
	public static List<Markup> getAllMarkupsForCustomer(
			MarkupManager markupManagerService, Long customerId, long businessId2) {
		// TODO Auto-generated method stub
		BusinessDAO businessDAO=(BusinessDAO)MmrBeanLocator.getInstance().findBean("businessDAO");
		long busId = businessId2;
		 
			 Business curBus=businessDAO.getBusiessById(businessId2);
			 BusinessFilter bf=new BusinessFilter();
			 List<Markup> mList=new ArrayList<Markup>();
			 
			 if(curBus!=null && !curBus.isPartnerLevel() && !curBus.isNationLevel() && !curBus.isBranchLevel() && curBus.getPartnerId()==0 
						&& curBus.getParentBusinessId()==0 && curBus.getBranchId()==0 && curBus.getCountryPartnerId()==0){
				 
				 mList= markupManagerService.getAllMarkupsForCustomer(customerId,businessId2);
				 businessId2 = busId;
				 return mList;
				 
				}else if(curBus!=null && curBus.isPartnerLevel()  ){  //partner level business  to get the business objects  of child business under the tree
					
					bf= getBusinessFilterAtPartnerLevel(curBus);
					if(bf!=null){
						 mList= markupManagerService.getAllMarkupsForCustomer(customerId,businessId2);
					 if((mList==null || mList.size()==0)&& bf.getParentBus() !=null){
						 mList= markupManagerService.getAllMarkupsForCustomer(customerId,bf.getParentBus().getId());
					 }
					 
					}
					 businessId2 = busId;

					return mList;
					
				}else if(curBus!=null&&curBus.isNationLevel()){   //nation level business  to get the business objects  of child business under the tree
					
	
					bf= getBusinessFilterAtNationLevel(curBus);
					
					if(bf!=null){
						 mList= markupManagerService.getAllMarkupsForCustomer(customerId,businessId2);
						 if((mList==null || mList.size()==0)&& bf.getPartnerBus() !=null){
							 mList= markupManagerService.getAllMarkupsForCustomer(customerId,bf.getPartnerBus().getId());
						 }
						 if((mList==null || mList.size()==0)&& bf.getParentBus() !=null){
							 mList= markupManagerService.getAllMarkupsForCustomer(customerId,bf.getParentBus().getId());
						 }
						 
						}
					 businessId2 = busId;

						return mList;
					 
					
				}else if(curBus!=null&&curBus.isBranchLevel() ){    //branch level business  to get the business objects  of child business under the tree
	
					bf= getBusinessFilterAtBranchLevel(curBus);
					 
		   
					
					 mList= markupManagerService.getAllMarkupsForCustomer(customerId,businessId2);
					if(bf!=null){
						 if((mList==null || mList.size()==0)&& bf.getNationBus() !=null){
							 mList= markupManagerService.getAllMarkupsForCustomer(customerId,bf.getNationBus().getId());
						 }
						 if((mList==null || mList.size()==0)&& bf.getPartnerBus() !=null){
							 mList= markupManagerService.getAllMarkupsForCustomer(customerId,bf.getPartnerBus().getId());
						 }
						 if((mList==null || mList.size()==0)&& bf.getParentBus() !=null){
							 mList= markupManagerService.getAllMarkupsForCustomer(customerId,bf.getParentBus().getId());
						 }
						 
						}
					 businessId2 = busId;

					 return mList;
				}
					
			
		 
		return null;
	}
	public static LtlPoundRate getPoundRate(MarkupManagerDAO markupDAO,
			LtlPoundRate poundRateTobeSearched, Double totWeight) {
		// TODO Auto-generated method stub
		
		
		
		BusinessDAO businessDAO=(BusinessDAO)MmrBeanLocator.getInstance().findBean("businessDAO");
		if(poundRateTobeSearched!=null){
		 long busId = poundRateTobeSearched.getBusinessId();
		 Business curBus=businessDAO.getBusiessById(poundRateTobeSearched.getBusinessId());
		 BusinessFilter bf=new BusinessFilter();
		 LtlPoundRate ltpr=null;
		 if(curBus!=null && !curBus.isPartnerLevel() && !curBus.isNationLevel() && !curBus.isBranchLevel() && curBus.getPartnerId()==0 
					&& curBus.getParentBusinessId()==0 && curBus.getBranchId()==0 && curBus.getCountryPartnerId()==0){
			 
			 ltpr= markupDAO.getPoundRate(poundRateTobeSearched,totWeight);
			 poundRateTobeSearched.setBusinessId(busId);
			 return ltpr;
			 
			}else if(curBus!=null && curBus.isPartnerLevel()  ){  //partner level business  to get the business objects  of child business under the tree
				
				bf= getBusinessFilterAtPartnerLevel(curBus);
				if(bf!=null){
					 ltpr= markupDAO.getPoundRate(poundRateTobeSearched,totWeight);
					 if((ltpr==null )&& bf.getParentBus() !=null){
						 poundRateTobeSearched.setBusinessId(bf.getParentBus().getId());
						 ltpr= markupDAO.getPoundRate(poundRateTobeSearched,totWeight);
					 }
				}
				poundRateTobeSearched.setBusinessId(busId);
				return ltpr;
				
			}else if(curBus!=null&&curBus.isNationLevel()){   //nation level business  to get the business objects  of child business under the tree
				
	
				bf= getBusinessFilterAtNationLevel(curBus);
				
				if(bf!=null){
					ltpr= markupDAO.getPoundRate(poundRateTobeSearched,totWeight);
					 if((ltpr==null )&& bf.getPartnerBus() !=null){
						 poundRateTobeSearched.setBusinessId(bf.getPartnerBus().getId());
						 ltpr= markupDAO.getPoundRate(poundRateTobeSearched,totWeight);
					 }
					}
				poundRateTobeSearched.setBusinessId(busId);
					return ltpr;
				 
				
			}else if(curBus!=null&&curBus.isBranchLevel() ){    //branch level business  to get the business objects  of child business under the tree
	
				bf= getBusinessFilterAtBranchLevel(curBus);
				 
	  
				
				ltpr= markupDAO.getPoundRate(poundRateTobeSearched,totWeight);
				if(bf!=null){
					 if((ltpr==null )&& bf.getNationBus() !=null){
						 poundRateTobeSearched.setBusinessId(bf.getNationBus().getId());
						 ltpr= markupDAO.getPoundRate(poundRateTobeSearched,totWeight);
					 }
					 if((ltpr==null )&& bf.getPartnerBus() !=null){
						 poundRateTobeSearched.setBusinessId(bf.getPartnerBus().getId());
						 ltpr= markupDAO.getPoundRate(poundRateTobeSearched,totWeight);
					 }
					 if((ltpr==null )&& bf.getParentBus() !=null){
						 poundRateTobeSearched.setBusinessId(bf.getParentBus().getId());
						 ltpr= markupDAO.getPoundRate(poundRateTobeSearched,totWeight);
					 }
					 
				}
				poundRateTobeSearched.setBusinessId(busId);
				 return ltpr;
			}
				
		
	}
		
		
		
		return null;
	}
	public static LtlSkidRate getSkidRate(MarkupManagerDAO markupDAO,
			LtlSkidRate skidRateTobeSearched) {
		// TODO Auto-generated method stub
		if(skidRateTobeSearched!=null){
		BusinessDAO businessDAO=(BusinessDAO)MmrBeanLocator.getInstance().findBean("businessDAO");
		long busId = skidRateTobeSearched.getBusinessId();
		Business curBus=businessDAO.getBusiessById(skidRateTobeSearched.getBusinessId());
		
		
		LtlSkidRate skidRate=new LtlSkidRate();
		 
		 BusinessFilter bf=new BusinessFilter();
	 
		 if(curBus!=null && !curBus.isPartnerLevel() && !curBus.isNationLevel() && !curBus.isBranchLevel() && curBus.getPartnerId()==0 
					&& curBus.getParentBusinessId()==0 && curBus.getBranchId()==0 && curBus.getCountryPartnerId()==0){
			 
			 skidRate =markupDAO.getSkidRate(skidRateTobeSearched);
				    
			 return skidRate;
			 
			}else if(curBus!=null && curBus.isPartnerLevel()  ){  //partner level business  to get the business objects  of child business under the tree
				
				bf= BusinessFilterUtil.getBusinessFilterAtPartnerLevel(curBus);
				if(bf!=null){
					skidRateTobeSearched.setBusinessId(bf.getParentBus().getId());
					 skidRate =markupDAO.getSkidRate(skidRateTobeSearched);
	   				    
			 if((skidRate==null )&& bf.getParentBus() !=null){
					 skidRate =markupDAO.getSkidRate(skidRateTobeSearched);
					 
				 }
			 	skidRateTobeSearched.setBusinessId(busId);
				 return skidRate;
				}
				 
			}else if(curBus!=null&&curBus.isNationLevel()){   //nation level business  to get the business objects  of child business under the tree
				
	
				bf= BusinessFilterUtil.getBusinessFilterAtNationLevel(curBus);
				 skidRate =markupDAO.getSkidRate(skidRateTobeSearched);
				if(bf!=null){
			 
					 if( (skidRate==null )&& bf.getPartnerBus() !=null){
						 skidRateTobeSearched.setBusinessId(bf.getPartnerBus().getId());
						 skidRate =markupDAO.getSkidRate(skidRateTobeSearched);
					 }
					 if( (skidRate==null )&& bf.getParentBus() !=null){
						 skidRateTobeSearched.setBusinessId(bf.getParentBus().getId());
						 skidRate =markupDAO.getSkidRate(skidRateTobeSearched);
					 }
					 	skidRateTobeSearched.setBusinessId(busId);
					 return skidRate;
					}
				 
				 
				
			}else if(curBus!=null&&curBus.isBranchLevel() ){    //branch level business  to get the business objects  of child business under the tree
	
				bf= BusinessFilterUtil.getBusinessFilterAtBranchLevel(curBus);
				 
				 skidRate =markupDAO.getSkidRate(skidRateTobeSearched);
				if(bf!=null){
					 if((skidRate==null )&& bf.getNationBus() !=null){
						 skidRate =markupDAO.getSkidRate(skidRateTobeSearched);
					 }
					 if((skidRate==null  )&& bf.getPartnerBus() !=null){
						 skidRate =markupDAO.getSkidRate(skidRateTobeSearched);
					 }
					 if((skidRate==null  )&& bf.getParentBus() !=null){
						 skidRate =markupDAO.getSkidRate(skidRateTobeSearched);
					 }
					 	skidRateTobeSearched.setBusinessId(busId);
					 return skidRate;
					}
			}
		}
		return null;
	}
	
	public static boolean isAllLevelMarkupDisabled(long businessId){
		BusinessDAO businessDAO=(BusinessDAO)MmrBeanLocator.getInstance().findBean("businessDAO");
		MarkupManagerDAO markupDAO=(MarkupManagerDAO)MmrBeanLocator.getInstance().findBean("markupManagerDAO");
		 long busId = businessId;
		 Business curBus=businessDAO.getBusiessById(businessId);
		 BusinessFilter bf=new BusinessFilter();
		 boolean flagrate = false;
		 if(curBus!=null && !curBus.isPartnerLevel() && !curBus.isNationLevel() && !curBus.isBranchLevel() && curBus.getPartnerId()==0 
					&& curBus.getParentBusinessId()==0 && curBus.getBranchId()==0 && curBus.getCountryPartnerId()==0){
			 
			 flagrate = markupDAO.isMarkupDisabled(busId);
			 
			 return flagrate;
			 
			}else if(curBus!=null && curBus.isPartnerLevel()  ){  //partner level business  to get the business objects  of child business under the tree
				
				bf= getBusinessFilterAtPartnerLevel(curBus);
				if(bf!=null){
					flagrate = markupDAO.isMarkupDisabled(busId);
					 if(!flagrate){
						 busId = bf.getParentBus().getId();
						 flagrate = markupDAO.isMarkupDisabled(busId);
					 }
				}
				
				return flagrate;
				
			}else if(curBus!=null&&curBus.isNationLevel()){   //nation level business  to get the business objects  of child business under the tree
				
	
				bf= getBusinessFilterAtNationLevel(curBus);
				
				if(bf!=null){
					flagrate = markupDAO.isMarkupDisabled(busId);
					 if(!flagrate){
						 busId = bf.getPartnerBus().getId();
						 flagrate = markupDAO.isMarkupDisabled(busId);
					 }
					 if(!flagrate){
						 busId = bf.getParentBus().getId();
						 flagrate = markupDAO.isMarkupDisabled(busId);
					 }
					}
				
					return flagrate;
				 
				
			}else if(curBus!=null&&curBus.isBranchLevel() ){    //branch level business  to get the business objects  of child business under the tree
	
				bf= getBusinessFilterAtBranchLevel(curBus);
				 
	  
				
				flagrate = markupDAO.isMarkupDisabled(busId);
				if(bf!=null){
					 if(!flagrate){
						 busId = bf.getNationBus().getId();
						 flagrate = markupDAO.isMarkupDisabled(busId);
					 }
					 if(!flagrate){
						 busId = bf.getPartnerBus().getId();
						 flagrate = markupDAO.isMarkupDisabled(busId);
					 }
					 if(!flagrate){
						 busId = bf.getParentBus().getId();
						 flagrate = markupDAO.isMarkupDisabled(busId);
					 }
					 
				}
				
				 return flagrate;
			}
		return flagrate;
	}
	
	
	public static  boolean isSystemLevel(){
						
				 		  Long businessId=(Long) ActionContext.getContext().getSession().get(Constants.BUSINESS_ID_SESSION);
						  if(businessId==null && UserUtil.getMmrUser()!=null
								  && UserUtil.getMmrUser().getUserRole().equals(ShiplinxConstants.ROLE_SYSADMIN)){
							  return true;
						  }
						return false;
					}
				    
					
					
					 public static List<Long> getAllSuperBusinessIds(Long businessId){
							BusinessDAO businessDAO=(BusinessDAO)MmrBeanLocator.getInstance().findBean("businessDAO");
							Business bus=businessDAO.getBusiessById(businessId);
							
							List<Long> businessIds=new ArrayList<Long>();
							businessIds.add(businessId);
							if(bus!=null){
								if(bus.isPartnerLevel()){
									businessIds.add(bus.getParentBusinessId());
								}else if(bus.isNationLevel()){
		 			 				businessIds.add(bus.getPartnerId());
									Business b1=businessDAO.getBusiessById(bus.getPartnerId());
									businessIds.add(b1.getParentBusinessId());
								}else if(bus.isBranchLevel()){
									businessIds.add(bus.getPartnerId());
									businessIds.add(bus.getCountryPartnerId());
								Business b1=businessDAO.getBusiessById(bus.getPartnerId());
									businessIds.add(b1.getParentBusinessId());
								}
							}
							businessIds=getvalidatedBusIds(businessIds);
							return businessIds;
					}
     
}