package com.meritconinc.mmr.action.admin;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Iterator;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;


import com.meritconinc.mmr.action.common.DataGridAction;
import com.meritconinc.mmr.constants.Constants;
import com.meritconinc.mmr.dao.RolesDAO;
import com.meritconinc.mmr.exception.UsernameAlreadyTakenException;
import com.meritconinc.mmr.model.admin.UserSearchCriteria;
import com.meritconinc.mmr.model.common.LocaleVO;
import com.meritconinc.mmr.model.common.RoleVO;
import com.meritconinc.mmr.model.datagrid.DataGrid;
import com.meritconinc.mmr.model.security.User;
import com.meritconinc.mmr.service.UserService;
import com.meritconinc.mmr.utilities.MessageUtil;
import com.meritconinc.mmr.utilities.StringUtil;
import com.meritconinc.mmr.utilities.WebUtil;
import com.meritconinc.mmr.utilities.security.UserUtil;
import com.meritconinc.shiplinx.model.Customer;
import com.meritconinc.mmr.dao.BusinessFilterDAO;
import com.meritconinc.mmr.utilities.MmrBeanLocator;
import com.meritconinc.shiplinx.dao.BusinessDAO;
import com.meritconinc.shiplinx.model.Branch;
import com.meritconinc.shiplinx.model.Business;
import com.meritconinc.shiplinx.model.BusinessFilter;
import com.meritconinc.shiplinx.model.CountryPartner;

import com.meritconinc.shiplinx.model.Partner;
import com.meritconinc.shiplinx.model.UserBusiness;
import com.meritconinc.shiplinx.model.UserFilter;
import com.meritconinc.shiplinx.service.AddressManager;
import com.meritconinc.shiplinx.service.CustomerManager;
import com.meritconinc.shiplinx.utils.ShiplinxConstants;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.Preparable;
import com.opensymphony.xwork2.ValidationAware;
import com.ibatis.sqlmap.engine.mapping.sql.dynamic.elements.IsParameterPresentTagHandler;
import com.meritconinc.mmr.dao.BusinessFilterDAO;
import com.meritconinc.mmr.utilities.MmrBeanLocator;
import com.meritconinc.shiplinx.dao.BusinessDAO;
import com.meritconinc.shiplinx.dao.ShippingDAO;
import com.meritconinc.shiplinx.model.Branch;
import com.meritconinc.shiplinx.model.Business;
import com.meritconinc.shiplinx.model.CountryPartner;
import com.meritconinc.shiplinx.model.Partner;
import com.meritconinc.shiplinx.model.UserFilter;
import com.soluship.businessfilter.util.BusinessFilterUtil;
import java.util.Iterator;

/**
 * <code>Set welcome message.</code>
 */
public class UserListAction extends DataGridAction implements Preparable, ServletRequestAware,
    ValidationAware {
  private static final long serialVersionUID = 25092007;

  private static final Logger log = LogManager.getLogger(UserListAction.class);
  private List<User> userList;
  private User user;
  private String edit;
  private UserService service;
  private CustomerManager customerService;
  private AddressManager addressService;
  private String selectedRoles[] = new String[0];
  public HttpServletRequest request;
  private UserSearchCriteria criteria;
  private boolean resetCurrPage = false;
  private int noOfRowsPerPage;

  private List<Partner> partnerList;
      private UserFilter userFilter;
      private List<CountryPartner> ajaxCountryPartnerList;
      private BusinessFilterDAO businessFilterDAO;
      private List<Branch> branchList;
  private Collection<RoleVO> availableRoles = new ArrayList<RoleVO>();
  private UserBusiness userBusiness;
    private List<Business> businessList;
  public void setDataGridPages() {
    this.setNoOfRowsPerPage(10);
    setDataGrid(new DataGrid(noOfRowsPerPage));
  }

  public UserSearchCriteria getCriteria() {
    return criteria;
  }

  public void setCriteria(UserSearchCriteria criteria) {
    this.criteria = criteria;
  }

  public String execute() throws Exception {
    return SUCCESS;
  }

  public UserService getService() {
    return service;
  }

  public void setService(UserService service) {
    this.service = service;
  }

  public List<User> getUserList() {
    return userList;
  }

  public void setServletRequest(HttpServletRequest request) {
    this.request = request;
  }

  public String list() throws Exception {
    Long lCustomerId = 0L;

    String cidAttribute = (String) request.getAttribute("cid");

    Collection<String> listCustomerIds = new ArrayList<String>();
         User user = UserUtil.getMmrUser();
             Long businessId=(Long) ActionContext.getContext().getSession().get(Constants.BUSINESS_ID_SESSION);
           Long partnerId=(Long) ActionContext.getContext().getSession().get(Constants.PARTNER_ID_SESSION);
         	 Long countryPartnerId=(Long) ActionContext.getContext().getSession().get(Constants.NATION_ID_SESSION);
        	 Long branchId=(Long) ActionContext.getContext().getSession().get(Constants.BRANCH_ID_SESSION);
         	
           if (request.getParameter("cid") != null) {
      lCustomerId = Long.valueOf(request.getParameter("cid"));
      Customer c = customerService.getCustomerInfoByCustomerId(lCustomerId);
      getSession().put("customerName", c.getName());
      this.getUser().setCustomerId(lCustomerId);
           }   
                  else{
               	   lCustomerId = getLoginUser().getCustomerId();
               	      getUser().setCustomerId(lCustomerId);
                  }
                  if(!UserUtil.getMmrUser().getUserRole().equals(ShiplinxConstants.ROLE_SYSADMIN) ||  businessId!=null){
    criteria.setCustomerId(lCustomerId);
    // if current user belongs to a branch, then show only those users within the branch
    if (!StringUtil.isEmpty(UserUtil.getMmrUser().getBranch()))
      criteria.setBranch(UserUtil.getMmrUser().getBranch());
    getSession().remove("edit");
  //userList = service.findUserByCustomer(criteria);
           String cid=request.getParameter("cid");
           if(cid!=null){
            getSession().put("cid", Long.valueOf(request.getParameter("cid")));
           }else{
        	   getSession().put("cid",lCustomerId);
           }
          
            userList=getuserlistbyBusId();
            if(userList!=null && userList.size()>0){
	            Iterator itr1=userList.iterator();
	                       while(itr1.hasNext()){
	                    	   User u=(User) itr1.next();
	                    	   if(u.getUserRole().equalsIgnoreCase(ShiplinxConstants.ROLE_SYSADMIN)){
	                    		itr1.remove();
	                    	   }
	                       }
            }
    if (!(userList.size() != 0))
      addActionError(getText("error.no.user.found"));
                  }else if(UserUtil.getMmrUser().getUserRole().equals(ShiplinxConstants.ROLE_SYSADMIN)&& businessId==null){
                	      	   	   userList=service.getAllUsers();
                	      	      }
    return SUCCESS;
  }

  public String checkRegiteredUser() throws Exception {
    User u = service.findUserByUsername(request.getParameter("username"));
    if (u != null)
      addActionError(getText("error.user.found"));
    return SUCCESS;
  }

  public List<User> getuserlistbyBusId() throws Exception{
  	  Long businessId=(Long) ActionContext.getContext().getSession().get(Constants.BUSINESS_ID_SESSION);
        Long partnerId=(Long) ActionContext.getContext().getSession().get(Constants.PARTNER_ID_SESSION);
        Long countryPartnerId=(Long) ActionContext.getContext().getSession().get(Constants.NATION_ID_SESSION);
        Long branchId=(Long) ActionContext.getContext().getSession().get(Constants.BRANCH_ID_SESSION);
       //update  business id to user by level
        User user1=new User();
  
     if(!UserUtil.getMmrUser().getUserRole().equals(ShiplinxConstants.ROLE_SYSADMIN)){
    	user1=UserUtil.getMmrUser();
    	}
    	//checking the user according to session values
    	 
    	if(businessId!=null && partnerId==null && countryPartnerId ==null && branchId==null){
    		 
   
    		user1.setBusinessId(businessId);             		
    		
    	}else if(businessId!=null && partnerId!=null && countryPartnerId ==null && branchId==null){
    		 
    		 
    		user1.setBusinessId(partnerId);
    		 
    	}else if(businessId!=null && partnerId!=null && countryPartnerId !=null && branchId==null ){
    		
    	
    		user1.setBusinessId(countryPartnerId);
     
    			
    	}else if(businessId!=null && partnerId!=null && countryPartnerId !=null && branchId!=null){
   
    		 
    		user1.setBusinessId(branchId);
    		 
  
    	}else if((businessId==null && partnerId==null && branchId==null && countryPartnerId ==null) && UserUtil.getMmrUser().isPartnerLevel()){
       
    	 
    		user1.setBusinessId(UserUtil.getMmrUser().getBusinessId());
    		  
    		
    	}else if((businessId==null && partnerId==null && branchId==null && countryPartnerId!=null) && UserUtil.getMmrUser().isPartnerLevel()){
    		   
    			  
   		user1.setBusinessId(countryPartnerId);
     
    
    	   }else if((businessId==null && partnerId==null && branchId!=null && countryPartnerId!=null) && UserUtil.getMmrUser().isPartnerLevel()){
    		   	 
    		 user1.setBranchLevel(true);
    		user1.setBusinessId(branchId);
    	   }
   	
    	 else if((businessId==null && partnerId==null && branchId==null && countryPartnerId ==null) && UserUtil.getMmrUser().isNationLevel()){
    		 
    		 
    		user1.setBusinessId(UserUtil.getMmrUser().getBusinessId());
    	 
    		 
   	}else if((businessId==null && partnerId==null && branchId==null && countryPartnerId ==null) && UserUtil.getMmrUser().isBranchLevel()){
    	 
    		 
    		user1.setBusinessId(UserUtil.getMmrUser().getBusinessId());
    	 
    	}else if((businessId==null && partnerId==null && branchId==null && countryPartnerId ==null) && UserUtil.getMmrUser().isBusinessLevel()){
    		 
  
    		user1.setBusinessId(UserUtil.getMmrUser().getBusinessId());
   	}else if((businessId==null && partnerId!=null && branchId==null && countryPartnerId ==null) && UserUtil.getMmrUser().isBusinessLevel()){
    		
    		 
    		user1.setBusinessId(partnerId);
    	}else if((businessId==null && partnerId!=null && branchId==null && countryPartnerId !=null) && UserUtil.getMmrUser().isBusinessLevel()){
  
    		 
    		user1.setBusinessId(countryPartnerId);
    		 
    	   }else if((businessId==null && partnerId!=null && branchId!=null && countryPartnerId !=null) && UserUtil.getMmrUser().isBusinessLevel()){
    
    			 
   		 
    		 user1.setBusinessId(branchId);
    			 
    	   }else if((businessId==null && partnerId==null && branchId!=null && countryPartnerId ==null) && UserUtil.getMmrUser().isNationLevel()){
   		        	 
    				 
   		 user1.setBusinessId(branchId);
   				 
    	   }
    	 
    	
    
    	
  	  List<User> userlist=BusinessFilterUtil.getUsersByBusiness(user1.getBusinessId());
  		  
  	  return userlist;
    }
   
  
    /*
     * filtering the user on the basis of User level.
     */
    public List<User> filterUserByLevel(List<User> user){
  	  
  	  List<User> tempUserList = new ArrayList<User>();
  	 if(user!=null && user.size() > 0){ 
  	     Long partnerId=(Long) ActionContext.getContext().getSession().get(Constants.PARTNER_ID_SESSION);
  	 	 Long countryPartnerId=(Long) ActionContext.getContext().getSession().get(Constants.NATION_ID_SESSION);
  	 	 Long branchId=(Long) ActionContext.getContext().getSession().get(Constants.BRANCH_ID_SESSION);
  		 Long businessId=(Long)ActionContext.getContext().getSession().get(Constants.BUSINESS_ID_SESSION);
  	 	 Boolean isPartnerLevel = UserUtil.getMmrUser().isPartnerLevel();
  	 	 Boolean isCountryLevel = UserUtil.getMmrUser().isNationLevel();
  	 	 Boolean isBranchLevel = UserUtil.getMmrUser().isBranchLevel();
  	 	 
   	 if((partnerId==null&& countryPartnerId!=null&& branchId==null) && businessId==null && !UserUtil.getMmrUser().getUserRole().equals(ShiplinxConstants.ROLE_SYSADMIN)){
  	 		 //removing business and partner level from user list
  	 		 Iterator<User> userIterator = user.iterator();
  	 		 while(userIterator.hasNext()){
  	 			 User userObj = userIterator.next();
  	 			if(userObj.isBusinessLevel()|| userObj.isPartnerLevel()){
  	 				 userIterator.remove();
   			 }
   		 }
   		 
  	 	 }else if((partnerId==null&& countryPartnerId!=null&& branchId!=null) && businessId==null && !UserUtil.getMmrUser().getUserRole().equals(ShiplinxConstants.ROLE_SYSADMIN)){
  	 		 Iterator<User> userIterator = user.iterator();
  	 		 while(userIterator.hasNext()){
  	 			 User userObj = userIterator.next();
  	 			if(userObj.isBusinessLevel()|| userObj.isPartnerLevel()||userObj.isNationLevel()){
  	 				 userIterator.remove();
  	 			 }
 	 		 }
  	 	 }else if((partnerId!=null && countryPartnerId==null && branchId==null) || (isPartnerLevel!=null && isPartnerLevel)){
  	 		 //removing the business level user from the list
  	 		 Iterator<User> userIterator = user.iterator();
  	 		 while(userIterator.hasNext()){
  	 			 User userObj = userIterator.next();
  	 			if(userObj.isBusinessLevel()){
  	 				 userIterator.remove();
   			 }
  	 		 }
  	 	 }else if((partnerId!=null && countryPartnerId!=null && branchId==null) || (isCountryLevel!=null && isCountryLevel) ){
  	 		 //removing the partner level user from the list
  	 		 if(partnerId!=null && countryPartnerId!=null && branchId==null){
  		 		 Iterator<User> userIterator = user.iterator();
  		 		 while(userIterator.hasNext()){
  		 			 User userObj = userIterator.next();
  		 			 if(userObj.isBusinessLevel() || userObj.isPartnerLevel()){
  		 				 userIterator.remove();
  		 			 }
  		 		 }
  	 		 }else if(partnerId==null&& countryPartnerId!=null && branchId==null){
  	 			 Iterator<User> userIterator = user.iterator();
  		 		 while(userIterator.hasNext()){
  		 			 User userObj = userIterator.next();
  		 			 if(userObj.isBusinessLevel() || userObj.isPartnerLevel()){
  		 				 userIterator.remove();
  		 			 }
  		 		 }
  	 			 
  	 		 }else if(partnerId==null&& (countryPartnerId==null||countryPartnerId!=null) && branchId!=null){
  	 			 Iterator<User> userIterator = user.iterator();
  		 		 while(userIterator.hasNext()){
  		 			 User userObj = userIterator.next();
  		 			 if(userObj.isBusinessLevel() || userObj.isPartnerLevel()||userObj.isNationLevel()){
  		 				 userIterator.remove();
  		 			 }
  		 		 }
  	 		 }else if(partnerId==null&& countryPartnerId==null && branchId==null && isCountryLevel){
  	 			Iterator<User> userIterator = user.iterator();
  		 		 while(userIterator.hasNext()){
  		 			 User userObj = userIterator.next();
  		 			 if(userObj.isBusinessLevel() || userObj.isPartnerLevel()){
  		 				 userIterator.remove();
  		 			 }
  		 		 }
  	 		 }
  	 	 }else if((partnerId!=null && countryPartnerId!=null && branchId!=null) || (isBranchLevel!=null && isBranchLevel) ){
  	 		 //removing the country level user from the list
  	 		Iterator<User> userIterator = user.iterator();
  	 		 while(userIterator.hasNext()){
  	 			 User userObj = userIterator.next();
  	 			 if(userObj.isBusinessLevel() || userObj.isPartnerLevel() || userObj.isNationLevel()){
  	 				 userIterator.remove();
  	 			 }
  	 		 }
  	 	}else if(((isPartnerLevel!=null && isPartnerLevel) || (isCountryLevel!=null&&isCountryLevel) ||(isBranchLevel!=null &&isBranchLevel) )&& 	partnerId==null && countryPartnerId==null && branchId==null){
  	 		Iterator<User> userIterator = user.iterator();
  	 		 while(userIterator.hasNext()){
  	 			 User userObj = userIterator.next();
  	 			 if(userObj.isBusinessLevel() || userObj.isPartnerLevel() || userObj.isNationLevel()){
  	 				 userIterator.remove();
  	 			 }
  	 		 }
  	 	 }
  	 }
  		 return user;
    }
  /**
   * creates a new user
   * 
   * @return
   * @throws Exception
   */
  public String save() throws Exception {
    user = getUser();
    setAvailableRoles(user.getCustomerId());
    log.debug(" CHECK METHOD IN SAVE ***********   " + request.getParameter("method"));

    User loggedInUser = UserUtil.getMmrUser();
    if (loggedInUser.getCustomerId() > 0) // this is a customer creating a user under his/her own
                                          // profile
      user.setCustomerId(loggedInUser.getCustomerId());
    // else either admin is creating business user, or admin is creating a user for a customer, both
    // cases are taken care of in the new method

    user.setStatus(user.getEnabled() ? Constants.STATUS_ACTIVE : Constants.STATUS_INACTIVE);
    if (user.getLocale() == null) {
      user.setLocale("en_CA");
    }
    user.setPasswordChangedAt(new Timestamp(new Date().getTime()));
    if (request.getParameter("method").equals("update")) {
      String username = request.getParameter("username");

      service.save(user, UserUtil.getSignedInUser().getUsername());
      if(UserUtil.getMmrUser().getUserRole().equalsIgnoreCase(ShiplinxConstants.ROLE_SYSADMIN)){
    	           
    	      	 updateUserBusiness(user);
    	      	  
    	        }
      log.debug("EDITTED SUCCESSFULLY ");
      addActionMessage(getText("user.info.update.successfully"));
      // session.remove("method");
    } else { // add user
      try {
        if (user.getPassword() == null || user.getPassword().length() < 6) {
          addActionError(getText("error.password.length"));
          return INPUT;
        }
        User u = service.findUserByUsername(user.getUsername());
        if (u != null) {
          addActionError(getText("error.username.taken"));
          addActionMessage(getText("error.username.taken"));
          return INPUT;
        }
        Long businessId=(Long) ActionContext.getContext().getSession().get(Constants.BUSINESS_ID_SESSION);
        Long partnerId=(Long) ActionContext.getContext().getSession().get(Constants.PARTNER_ID_SESSION);
        Long countryPartnerId=(Long) ActionContext.getContext().getSession().get(Constants.NATION_ID_SESSION);
        Long branchId=(Long) ActionContext.getContext().getSession().get(Constants.BRANCH_ID_SESSION);
        
       //update  business id to user by level
        
        if(UserUtil.getMmrUser().getUserRole().equals(ShiplinxConstants.ROLE_SYSADMIN) && businessId==null ){
    		
    		addActionMessage("Please Select Any business to Add User");
    		return "fail";
    	 
    	}
    	if(!UserUtil.getMmrUser().getUserRole().equals(ShiplinxConstants.ROLE_SYSADMIN)){
    	Long logedInBusId=UserUtil.getMmrUser().getBusinessId();
    	}
    	//checking the user according to session values
    	 
    	if(businessId!=null && partnerId==null && countryPartnerId ==null && branchId==null){
    		 
    		user.setBusinessLevel(true);
    		user.setPartnerLevel(false);
    		user.setNationLevel(false);
    		user.setBranchLevel(false);
    		user.setBusinessId(businessId);             		
   		
   	}else if(businessId!=null && partnerId!=null && countryPartnerId ==null && branchId==null){
    		 
    		 user.setPartnerLevel(true);
    		 user.setBusinessId(partnerId);
    		 
    	}else if(businessId!=null && partnerId!=null && countryPartnerId !=null && branchId==null ){
    		
    	 
    		user.setNationLevel(true);
             user.setBusinessId(countryPartnerId);
     
    			
    	}else if(businessId!=null && partnerId!=null && countryPartnerId !=null && branchId!=null){
   
    		 
    		 user.setBusinessId(branchId);
    		user.setBranchLevel(true);
 
   	}else if((businessId==null && partnerId==null && branchId==null && countryPartnerId ==null) && UserUtil.getMmrUser().isPartnerLevel()){
       
    		user.setPartnerLevel(true);
    	    user.setBusinessId(UserUtil.getMmrUser().getBusinessId());
    		  
    		
   	}else if((businessId==null && partnerId==null && branchId==null && countryPartnerId!=null) && UserUtil.getMmrUser().isPartnerLevel()){
    		   
    			  
            user.setBusinessId(countryPartnerId);
    		user.setNationLevel(true);
    
   	   }else if((businessId==null && partnerId==null && branchId!=null && countryPartnerId!=null) && UserUtil.getMmrUser().isPartnerLevel()){
    		   	 
    		 user.setBranchLevel(true);
    		 user.setBusinessId(branchId);
    	   }
    	
   	 else if((businessId==null && partnerId==null && branchId==null && countryPartnerId ==null) && UserUtil.getMmrUser().isNationLevel()){
    		 
    		user.setNationLevel(true);
    		user.setBusinessId(UserUtil.getMmrUser().getBusinessId());
    	 
    		 
   	}else if((businessId==null && partnerId==null && branchId==null && countryPartnerId ==null) && UserUtil.getMmrUser().isBranchLevel()){
    	 
   		user.setBranchLevel(true);
    	    user.setBusinessId(UserUtil.getMmrUser().getBusinessId());
    	 
   	}else if((businessId==null && partnerId==null && branchId==null && countryPartnerId ==null) && UserUtil.getMmrUser().isBusinessLevel()){
   		 
   		user.setBusinessLevel(true);
    		user.setPartnerLevel(false);
    	    user.setNationLevel(false);
    		user.setBranchLevel(false);
    		 user.setBusinessId(UserUtil.getMmrUser().getBusinessId());
   	}else if((businessId==null && partnerId!=null && branchId==null && countryPartnerId ==null) && UserUtil.getMmrUser().isBusinessLevel()){
    		
    		 user.setPartnerLevel(true);
   		 user.setBusinessId(partnerId);
  	}else if((businessId==null && partnerId!=null && branchId==null && countryPartnerId !=null) && UserUtil.getMmrUser().isBusinessLevel()){

    		user.setNationLevel(true);
    		 user.setBusinessId(countryPartnerId);
    		 
   	   }else if((businessId==null && partnerId!=null && branchId!=null && countryPartnerId !=null) && UserUtil.getMmrUser().isBusinessLevel()){
    
    			 
    			user.setBranchLevel(true);
    			 user.setBusinessId(branchId);
    			 
   	   }else if((businessId==null && partnerId==null && branchId!=null && countryPartnerId ==null) && UserUtil.getMmrUser().isNationLevel()){
    		        	 
    				user.setBranchLevel(true);
    				 user.setBusinessId(branchId);
    				 
    	   }
     /*   if(businessId!=null){
        	user.setBusinessId(businessId);	
        }else{
        	user.setBusinessId(UserUtil.getMmrUser().getBusinessId());
        }*/
        service.create(user, UserUtil.getSignedInUser().getUsername());
        
        if(UserUtil.getMmrUser().getUserRole().equalsIgnoreCase(ShiplinxConstants.ROLE_SYSADMIN)){
        	        addUserBusiness(user);
        	        }
        
        BusinessFilterDAO businessFilterDAO = (BusinessFilterDAO) MmrBeanLocator.getInstance().findBean("businessFilterDAO");
                                //UserFilter
                                 addUserFilter(user);
                                if(businessId !=null &&(partnerId==null)
                        				&& (countryPartnerId==null )
                        				&& (branchId==null)){
                       				   getSession().put(ShiplinxConstants.SESSION_BUSINESSFILTER_CUSTOMERID,BusinessFilterUtil.getBusinessLevelCustomers(businessId));
                        			   }

      } catch (UsernameAlreadyTakenException ue) {
        addActionMessage(getText("error.username.taken"));
        addActionError(getText("error.username.taken"));
        return INPUT;
      }
      addActionMessage(getText("user.info.save.successfully"));
    }
    getSession().remove("user");
    return SUCCESS;
  }

  /*update the user_business only for sysadmin can 
   *edit the access of multiple business 
   *for this user
   */
  private void updateUserBusiness(User user2) {
  	// TODO Auto-generated method stub
  	  BusinessDAO businessDAO=(BusinessDAO)MmrBeanLocator.getInstance().findBean("businessDAO");
  	  
  	  List<UserBusiness> userBusinessList=businessDAO.getUserBusinessListByUser(user.getUsername());
  	   String[] parentIds=request.getParameterValues("parentBusIds");
  	   String[] partnerIds=request.getParameterValues("partnerBusIds");
  	   String[] nationIds=request.getParameterValues("nationBusIds");
  	   String[] branchIds=request.getParameterValues("branchBusIds");
  	   List<Long> parentIdL=new ArrayList<Long>();
  	   List<Long> partnerIdL=new ArrayList<Long>();
  	   List<Long> nationIdL=new ArrayList<Long>();
  	   List<Long> branchIdL=new ArrayList<Long>();
  	   if(parentIds !=null){
  	   for(int i=0;i<parentIds.length;i++){
  		   
  		   parentIdL.add(Long.parseLong(parentIds[i]));
  		   partnerIdL.add(Long.parseLong(partnerIds[i]));
  		   nationIdL.add(Long.parseLong(nationIds[i]));
  		   branchIdL.add(Long.parseLong(branchIds[i]));
  	   }
  	   }
  	   
  	   List<UserBusiness> updateduserBus=new ArrayList<UserBusiness>();
  	   List<UserBusiness> toAdduserBus=new ArrayList<UserBusiness>();
  	   List<UserBusiness> tempOldubs=new ArrayList<UserBusiness>();
  	   List<UserBusiness> tempubs=new ArrayList<UserBusiness>();
  	   if(parentIdL!=null){
  	  for(int i=0;i<parentIdL.size();i++){
  		  
  		  UserBusiness ub=new UserBusiness();
  		  ub.setUsername(user2.getUsername());
  		  ub.setParentId(parentIdL.get(i));
  		  ub.setPartnerId(partnerIdL.get(i));
  		  ub.setNationId(nationIdL.get(i));
  		  ub.setBranchId(branchIdL.get(i));
  		  updateduserBus.add(ub);
  	  }
  	   }
  	   if(updateduserBus.size()>0){
  	  tempubs.addAll(updateduserBus);
  	   }
  	   if(userBusinessList.size()>0){
  	  tempOldubs.addAll(userBusinessList);
  	   }
  	  @SuppressWarnings("rawtypes")
  	Iterator old=userBusinessList.iterator();	
  	  @SuppressWarnings("rawtypes")
  	Iterator lat=updateduserBus.iterator();
  	  Iterator lat1=tempubs.iterator();
  	  Iterator old1=tempOldubs.iterator();
  	  while(lat.hasNext()){
  		  UserBusiness ubl=(UserBusiness) lat.next();
  		  while(old.hasNext()){
  			  UserBusiness ubo=(UserBusiness)old.next();
  			  if(ubo.getUsername().equals(ubl.getUsername()) && 
  					  ubo.getParentId().equals(ubl.getParentId()) && ubo.getPartnerId().equals(ubl.getPartnerId()) &&
  					  ubo.getNationId().equals(ubl.getNationId()) && ubo.getBranchId().equals(ubl.getBranchId())){
  				  lat.remove();
  			  }
  		  }
  	  }
  	  

  		 
  			  while(lat1.hasNext()){
  				  UserBusiness ubl=(UserBusiness) lat1.next();
  				  while(old1.hasNext()){
  					  UserBusiness ubo=(UserBusiness)old1.next();
  			  if(ubo.getUsername().equals(ubl.getUsername()) && 
  					  ubo.getParentId().equals(ubl.getParentId()) && ubo.getPartnerId().equals(ubl.getPartnerId()) &&
  					  ubo.getNationId().equals(ubl.getNationId()) && ubo.getBranchId().equals(ubl.getBranchId())){
  				  old1.remove();
  			  }
  		  }
  	  }
  	  
  	  //to add
  	  for(UserBusiness ub:updateduserBus){
  	    	 businessDAO.adduserBusiness(ub);
  	     }
  		
  	  //to delete
  	  for(UserBusiness ub:tempOldubs){
  		  businessDAO.deleteUserBusiness(ub.getUserBusId());
  	  }
  	   
  }
  /* 
  *  Allocate multiple business for 
  *   various business
  */
  private void addUserBusiness(User user2) {
  	// TODO Auto-generated method stub
  	  BusinessDAO businessDAO=(BusinessDAO)MmrBeanLocator.getInstance().findBean("businessDAO");
  	 String[] parentIds=request.getParameterValues("parentBusIds");
  	   String[] partnerIds=request.getParameterValues("partnerBusIds");
  	   String[] nationIds=request.getParameterValues("nationBusIds");
  	   String[] branchIds=request.getParameterValues("branchBusIds");
  	   List<Long> parentIdL=new ArrayList<Long>();
  	   List<Long> partnerIdL=new ArrayList<Long>();
  	   List<Long> nationIdL=new ArrayList<Long>();
  	   List<Long> branchIdL=new ArrayList<Long>();
  	   if(parentIds!=null){
  	   for(int i=0;i<parentIds.length;i++){
  		   
  		   parentIdL.add(Long.parseLong(parentIds[i]));
  		   partnerIdL.add(Long.parseLong(partnerIds[i]));
  		   nationIdL.add(Long.parseLong(nationIds[i]));
  		   branchIdL.add(Long.parseLong(branchIds[i]));
  	   }
  	   }
  	   
  	   List<UserBusiness> toaddubs=new ArrayList<UserBusiness>();
  	   if(parentIdL!=null){
  	  for(int i=0;i<parentIdL.size();i++){
  		  
  		  UserBusiness ub=new UserBusiness();
  		  ub.setUsername(user2.getUsername());
  		  ub.setParentId(parentIdL.get(i));
  		  ub.setPartnerId(partnerIdL.get(i));
  		  ub.setNationId(nationIdL.get(i));
  		  ub.setBranchId(branchIdL.get(i));
  		  toaddubs.add(ub);
  	  }
  	  if(toaddubs!=null && toaddubs.size()>0){
       for(UserBusiness ub:toaddubs){
      	 businessDAO.adduserBusiness(ub);
       }
       }
  	   }
  	
  }
  
  private void addUserFilter(User user2) {
	 	// TODO Auto-generated method stub
	   
	 	  userFilter=new UserFilter();
	    BusinessFilterDAO businessFilterDAO=(BusinessFilterDAO)MmrBeanLocator.getInstance().findBean("businessFilterDAO");
	  	 if(user2!=null){
	  		 if(user2.isBusinessLevel()){
	  			 userFilter.setBusinessId(user2.getBusinessId());
	  			 userFilter.setUserName(user2.getUsername());
	  			 userFilter.setBusinessLevel(true);
	  		 }else if(user2.isPartnerLevel()){
	  			 userFilter.setBusinessId(user2.getBusinessId());
	  			 userFilter.setUserName(user2.getUsername());
	  			 userFilter.setPartnerLevel(true);
	  		 }else if(user2.isNationLevel()){
	  			 userFilter.setBusinessId(user2.getBusinessId());
	  			 userFilter.setUserName(user2.getUsername());
	  			 userFilter.setNationLevel(true);
	  		 }else if(user2.isBranchLevel()){
	  			 userFilter.setBusinessId(user2.getBusinessId());
	  			 userFilter.setUserName(user2.getUsername());
	  			 userFilter.setBranchLevel(true);
	  		 }
	  		 businessFilterDAO.addUserFilters(userFilter);
	  	 }
	  }
  
  public String delete() throws Exception {

    String username = request.getParameter("name");
    log.debug("CHECK NAME " + username);
    service.delete(username);
    addActionMessage(getText("user.info.delete.successfully"));
    return SUCCESS;
  }

  public String edit() throws Exception {
	  BusinessDAO businessDAO=(BusinessDAO)MmrBeanLocator.getInstance().findBean("businessDAO");
    String username = request.getParameter("name");
    log.debug("CHECK EIDT NAME " + username);
    user = service.findUserByUsername(request.getParameter("name"));
    log.debug("User phone " + user.getPhoneNumber());
    getSession().put("user", user);
    getSession().put("edit", "true");
    // request.setAttribute("edit", "true");
    // setEdit("true");
    // getSession().put("username", username);
    if (user.getDefaultFromAddressId() > 0) {
      // request.setAttribute("f_add_id", user.getDefaultFromAddressId());
      // request.setAttribute("long_f_add",
      // addressService.findAddressById(user.getDefaultFromAddressId()+"").getLongAddress());
      user.setDefaultFromAddressText(addressService.findAddressById(
          user.getDefaultFromAddressId() + "").getLongAddress());
    }
    if (user.getDefaultToAddressId() > 0) {
      // request.setAttribute("t_add_id", user.getDefaultToAddressId());
      // request.setAttribute("long_t_add",
      // addressService.findAddressById(user.getDefaultToAddressId()+"").getLongAddress());
      user.setDefaultToAddressText(addressService
          .findAddressById(user.getDefaultToAddressId() + "").getLongAddress());
    }
    // set the available roles here based on admin/customer user
    setAvailableRoles(user.getCustomerId());
    List<LocaleVO> localeList = service.findLocale();
    getSession().put("localeList", localeList);
    if(UserUtil.getMmrUser().getUserRole().equalsIgnoreCase(ShiplinxConstants.ROLE_SYSADMIN)){
    	    userbuinessDepencies();
    	    }
    
    return SUCCESS;
  }

  
  
  private void userbuinessDepencies() {
	// TODO Auto-generated method stub
  BusinessDAO businessDAO=(BusinessDAO)MmrBeanLocator.getInstance().findBean("businessDAO");
	  
		
  	  businessList=businessDAO.getAllBusiness();
	  userBusiness=new UserBusiness();
	  userBusiness.setUsername(user.getUsername());
	  BusinessFilter bf=new BusinessFilter();
      List <Business> allRootbus=businessDAO.getAllBusiness();
	  bf.setParentBusList(allRootbus);
      userBusiness.setBusinessFilter(bf);
      
      List<UserBusiness> userBusinessList=businessDAO.getUserBusinessListByUser(user.getUsername());
      List<BusinessFilter> bfList=new ArrayList<BusinessFilter>();
 
  if(userBusinessList!=null){
		  
		  for(UserBusiness ub:userBusinessList){
			  ub.setBusinessFilter(new BusinessFilter());
			  ub.getBusinessFilter().setId(ub.getUserBusId());
			 if(ub.getParentId()!=null){
				 if(ub.getParentId()!=-1){
				 ub.getBusinessFilter().setParentBus(businessDAO.getBusiessById(ub.getParentId()));
				 }else{
					 Business b=new Business();
					 b.setName("ANY");
					 b.setId(-1);
					 ub.getBusinessFilter().setParentBus(b);
				 }
			 }
			 if(ub.getPartnerId()!=null){
				 if(ub.getPartnerId()!=-1){
				 ub.getBusinessFilter().setPartnerBus(businessDAO.getBusiessById(ub.getPartnerId()));
				 }else{
					 Business b=new Business();
					 b.setName("ANY");
					 b.setId(-1);
					 ub.getBusinessFilter().setPartnerBus(b);
				 }
			 }
			 if(ub.getNationId()!=null ){
				 if( ub.getNationId()!=-1){
				 ub.getBusinessFilter().setNationBus(businessDAO.getBusiessById(ub.getNationId()));
				 }else{
					 Business b=new Business();
					 b.setName("ANY");
					 b.setId(-1);
					 ub.getBusinessFilter().setNationBus(b);
				 }
			 }
			 if(ub.getBranchId()!=null){
				 
				 if(ub.getBranchId()!=-1){
				 ub.getBusinessFilter().setBranchBus(businessDAO.getBusiessById(ub.getBranchId()));
				 }else{
					 Business b=new Business();
					 b.setName("ANY");
					 b.setId(-1);
					 ub.getBusinessFilter().setBranchBus(b);
				 }
			 }
			  bfList.add(ub.getBusinessFilter());
		  }
		  
		
	  }
  userBusiness.setBusinessFilterList(bfList);
  
}
  
  /**
   * Prepares the data for the New User page
   * 
   * @return
   * @throws Exception
   */
  public String newUser() throws Exception {
	  BusinessDAO businessDAO=(BusinessDAO)MmrBeanLocator.getInstance().findBean("businessDAO");
    /*// check if customer id is set, it means admin user is creating customer user
    long customerId = 0;
    if (getUser() != null)
      customerId = getUser().getCustomerId();
    setAvailableRoles(customerId);
    getSession().remove("user");
    getSession().remove("edit");
    user = new User();
    user.setCustomerId(customerId);
    user.setStatus("A");
    // user.setLocale("en_CA");
    selectedRoles = new String[1];
    selectedRoles[0] = Constants.PUBLIC_ROLE_CODE;
    user.setSessionTimeout(Integer.parseInt(WebUtil.getProperty(Constants.SYSTEM_SCOPE,
        Constants.TIMEOUT)));
    List<LocaleVO> localeList = service.findLocale();
    getSession().put("localeList", localeList);
    getSession().put("user", user);
    // setEdit("false");
    return SUCCESS;*/
	// check if customer id is set, it means admin user is creating customer user
	  	  	    long customerId = 0;
	  	  	    if (getUser() != null)
	  	  	      customerId = getUser().getCustomerId();
	  	  	    setAvailableRoles(customerId);
	  	  	    getSession().remove("user");
	  	  	    getSession().remove("edit");
	  	  	    user = new User();
	  	  	    user.setCustomerId(customerId);
	  	  	    user.setStatus("A");
	  	  	    // user.setLocale("en_CA");
	  	  	    selectedRoles = new String[1];
	  	  	    selectedRoles[0] = Constants.PUBLIC_ROLE_CODE;
	  	  	    user.setSessionTimeout(Integer.parseInt(WebUtil.getProperty(Constants.SYSTEM_SCOPE,
	  	  	        Constants.TIMEOUT)));
	  	  	    List<LocaleVO> localeList = service.findLocale();
	  	  	    getSession().put("localeList", localeList);
	  	      getSession().put("user", user);
	  	  	    // setEdit("false");
	  	    if(UserUtil.getMmrUser().getUserRole().equalsIgnoreCase(ShiplinxConstants.ROLE_SYSADMIN)){
	  	    		  	    	
	  	    		  	    	businessList=businessDAO.getAllBusiness();
	  	    		  	  	  userBusiness=new UserBusiness();
	  	    		  	  	userBusiness.setUsername(user.getUsername());
	  	    		  	  	BusinessFilter bf=new BusinessFilter();
	  	    		  	  	List <Business> allRootbus=businessDAO.getAllBusiness();
	  	    		  	  	bf.setParentBusList(allRootbus);
	  	    		  	    userBusiness.setBusinessFilter(bf);  	
	  	    		  	    userBusiness.setBusinessFilterList(new ArrayList<BusinessFilter>());
	  	    		  	    
	  	    		  	    }
	  	    
	  	  	    return SUCCESS;
  }

  private void newUserDepandencies(long businessId) {
	  	  	// TODO Auto-generated method stub
	  	  	 BusinessDAO businessDAO = (BusinessDAO) MmrBeanLocator.getInstance().findBean("businessDAO");
	  	  	 BusinessFilterDAO businessFilterDAO = (BusinessFilterDAO) MmrBeanLocator.getInstance().findBean("businessFilterDAO");
	  	  	 Business bs=businessDAO.getBusiessById(businessId);
	  	  	  partnerList=new ArrayList<Partner>();
	  	  	  branchList=new ArrayList<Branch>();
	  	  	  ajaxCountryPartnerList=new ArrayList<CountryPartner>();
	  	  	  userFilter=new UserFilter();
	  	  	  if(bs!=null && (bs.getPartnerId()!=0 ||  bs.getCountryPartnerId()!=0 || bs.getBranchId()!=0)){
	  	  		  partnerList.add(businessFilterDAO.getPartnerById(bs.getPartnerId()));
	  	  		  CountryPartner cp=businessFilterDAO.getCountryPartnerById(bs.getCountryPartnerId());
	  	  		  cp.setCountryName(businessFilterDAO.getCountryByCode(cp.getCountryCode()));
	  	  		  ajaxCountryPartnerList.add(cp);
	  	  	  branchList.add(businessFilterDAO.getBranchByBranchId(bs.getBranchId()));
	  	  		  userFilter.setPartnerId(bs.getPartnerId());
	  	            userFilter.setCountryPartnerId(bs.getCountryPartnerId());
	  	            userFilter.setBranchId(bs.getBranchId());
	  	  		  
	  	  	  }
	  	    
	  	  }
  
  public String back() throws Exception {
    // addActionMessage(getText("user.info.save.successfully"));
    // addActionMessage(getActionMessages());

    return SUCCESS;
  }

  public List refreshDataRows() throws Exception {
    List userList = null;
    userList = service.findUsers(criteria, getDataGrid().getCurPageRowStartIndex(), getDataGrid()
        .getCurPageRowEndIndex());
    if (userList.size() <= 0) {
      addActionError(getText("error.no.user.found"));
    }
    return userList;
  }

  public int getNoOfRowsPerPage() {
    return noOfRowsPerPage;
  }

  public void setNoOfRowsPerPage(int noOfRowsPerPage) {
    this.noOfRowsPerPage = noOfRowsPerPage;
  }

  public boolean isResetCurrPage() {
    return resetCurrPage;
  }

  public void setResetCurrPage(boolean resetCurrPage) {
    this.resetCurrPage = resetCurrPage;
  }

  public String goToSearch() throws Exception {
    if (resetCurrPage) {
      getDataGrid().resetCurrentPage();
    }
    return INPUT;
  }

  /**
   * Sets the available roles
   * 
   */
  public void setAvailableRoles(long customerId) {
    WebApplicationContext context = WebApplicationContextUtils
        .getWebApplicationContext((ServletContext) ActionContext.getContext().get(
            ServletActionContext.SERVLET_CONTEXT));
    RolesDAO rolesDAO = (RolesDAO) context.getBean("rolesDAO");
    String locale = MessageUtil.getLocale();
    if (customerId > 0) // this is a customer admin updating/creating user
      availableRoles = rolesDAO.getRolesByType(locale, ShiplinxConstants.ROLE_TYPE_CUSTOMER);
    else
      // business admin
      availableRoles = rolesDAO.getRolesByType(locale, ShiplinxConstants.ROLE_TYPE_BUSINESS);
    if(!UserUtil.getMmrUser().getUserRole().equals("sysadmin")){
    	    	    	    	    	    	RoleVO sysadminRole = rolesDAO.getSystemAdminRole();
    	    	    	    	    	    	RoleVO removeRole = new RoleVO();
    	    	    	    	    	    	for(RoleVO roleVO : availableRoles){
    	    	    	    	    	    		if(roleVO.getRole().equals(sysadminRole.getRole())){
    	    	    	    	    	    			removeRole = roleVO;
    	    	    	    	    	    		}
    	    	    	    	    	    	}
    	    	    	    	    	    	availableRoles.remove(removeRole);
    	    	    	    	    	    }
    getSession().put("availableRoles", availableRoles);
  }

  /**
   * @return the availableRoles
   */
  public Collection<RoleVO> getAvailableRoles() {
    return availableRoles;
  }

  /**
   * @param availableRoles
   *          the availableRoles to set
   */
  public void setAvailableRoles(Collection<RoleVO> availableRoles) {
    this.availableRoles = availableRoles;
  }

  public void prepare() throws Exception {
    if (user != null)
      setAvailableRoles(user.getCustomerId());
    else {

    }
  }

  public CustomerManager getCustomerService() {
    return customerService;
  }

  public void setCustomerService(CustomerManager customerService) {
    this.customerService = customerService;
  }

  public AddressManager getAddressService() {
    return addressService;
  }

  public void setAddressService(AddressManager addressService) {
    this.addressService = addressService;
  }

  public List<Partner> getPartnerList() {
	  	return partnerList;
	  }
	  
	  public void setPartnerList(List<Partner> partnerList) {
	  	this.partnerList = partnerList;
	  }
	  
	  public UserFilter getUserFilter() {
	  	return userFilter;
	  }
	  
	  public void setUserFilter(UserFilter userFilter) {
	  	this.userFilter = userFilter;
	  }
	  
	  public List<CountryPartner> getAjaxCountryPartnerList() {
	  	return ajaxCountryPartnerList;
	  }
	  
	  public void setAjaxCountryPartnerList(
	  		List<CountryPartner> ajaxCountryPartnerList) {
	  	this.ajaxCountryPartnerList = ajaxCountryPartnerList;
	  }
	  
	  public BusinessFilterDAO getBusinessFilterDAO() {
	  	return businessFilterDAO;
	  }
	  
	  public void setBusinessFilterDAO(BusinessFilterDAO businessFilterDAO) {
	  	this.businessFilterDAO = businessFilterDAO;
	  }
	  
	  public List<Branch> getBranchList() {
	  	return branchList;
	  }
	  
	  public void setBranchList(List<Branch> branchList) {
	  	this.branchList = branchList;
	  }
	  
	  public UserBusiness getUserBusiness() {
		  		return userBusiness;
		  	}
		  
		  	public void setUserBusiness(UserBusiness userBusiness) {
		  		this.userBusiness = userBusiness;
		  	}
		  
		  	public List<Business> getBusinessList() {
		  		return businessList;
		  	}
		  
		  	public void setBusinessList(List<Business> businessList) {
		  		this.businessList = businessList;
		  	}
}
