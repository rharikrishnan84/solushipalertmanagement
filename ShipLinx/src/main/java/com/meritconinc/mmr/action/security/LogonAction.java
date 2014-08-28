package com.meritconinc.mmr.action.security;

import java.net.InetAddress;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.acegisecurity.Authentication;
import org.acegisecurity.context.SecurityContextHolder;
import org.acegisecurity.userdetails.UserDetails;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.meritconinc.mmr.action.BaseAction;
import com.meritconinc.mmr.constants.Constants;
import com.meritconinc.mmr.constants.NavConsts;
import com.meritconinc.mmr.dao.MenusDAO;
import com.meritconinc.mmr.dao.PropertyDAO;
import com.meritconinc.mmr.model.admin.UserSearchCriteria;
import com.meritconinc.mmr.model.common.LoginStatusVO;
import com.meritconinc.mmr.model.common.MenuItemVO;
import com.meritconinc.mmr.model.security.User;
import com.meritconinc.mmr.service.UserService;
import com.meritconinc.mmr.service.acegi.CustomUser;
import com.meritconinc.mmr.utilities.Common;
import com.meritconinc.mmr.utilities.MessageUtil;
import com.meritconinc.mmr.utilities.MmrBeanLocator;
import com.meritconinc.mmr.utilities.StringUtil;
import com.meritconinc.mmr.utilities.WebUtil;
import com.meritconinc.mmr.utilities.security.UserUtil;
import com.meritconinc.shiplinx.model.Business;
import com.meritconinc.shiplinx.model.Customer;
import com.meritconinc.shiplinx.service.BusinessManager;
import com.meritconinc.shiplinx.service.CaptchaServiceSingleton;
import com.meritconinc.shiplinx.service.CustomerManager;
import com.meritconinc.shiplinx.utils.ShiplinxConstants;
import com.octo.captcha.service.CaptchaServiceException;
import com.opensymphony.xwork2.ActionContext;


public class LogonAction extends BaseAction implements ServletRequestAware {
	private static final long serialVersionUID	= 5062007;
	protected Logger log = Logger.getLogger(this.getClass());

	private static final Logger logger = Logger.getLogger(LogonAction.class);
	private final String LOGON_FAILED 		= "FAILURE";
	private final String LOGON_SUCCESSFUL 	= "SUCCESS";

	private HttpServletRequest request;    
	
	private String nextAction;
	
	private Long userCustomerId; 

	private UserService service;
	private BusinessManager businessService;
	
	private CustomerManager customerService; 
	
	private boolean displayMessage = false;
	private String username;
	private String password;
	private boolean acegiLogin = false;
	
	private User user;

	/**
	 * 
	 * @param service
	 * @param loginTrackingDAO
	 */
	public LogonAction(UserService service) {
		this.service = service;
	}
		
    public void setBusinessService(BusinessManager businessService) {
		this.businessService = businessService;
	}

	/**
     * This method gets called only if logon process was successful.
     * 
     * @return
     * @throws Exception
     */
	public String execute() throws Exception {
		
		String requestedPage = (String) ActionContext.getContext().getSession().get(Constants.CURRENT_PAGE);
//		HttpServletRequest httpServletRequest = 
//			(HttpServletRequest)ActionContext.getContext().getActionInvocation().getInvocationContext()
//			.get(ServletActionContext.HTTP_REQUEST);
//		String userAgent = httpServletRequest.getHeader("user-agent");
//		if(userAgent.contains("Firefox"))
//			getSession().put("cssToLoad", "/mmr/styles/shiplinx_closeWindow_styles.css");
//		else if(userAgent.contains("MSIE"))
//			getSession().put("cssToLoad", "/mmr/styles/shiplinx_closeWindow_styles_IE.css");
//		else if(userAgent.contains("Chrome"))
//			getSession().put("cssToLoad", "/mmr/styles/shiplinx_closeWindow_styles_Chrome.css");
//		else //default to Chrome CSS
//			getSession().put("cssToLoad", "/mmr/styles/shiplinx_closeWindow_styles_Chrome.css");
		
		String result = "next";
		boolean hasError = false;
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		CustomUser customUser = (CustomUser)auth.getPrincipal();
		user = (User)customUser.getUserInfo();	
		username = user.getUsername();

		if (user == null) {
			addActionError(getText("error.invalid.user"));
			return INPUT;
		}
		
		
		if (user.isActiveUser()){
			service.updateUserInfo(username);
		} else {
			service.updateLoginFailedCountZero(username);
		}
		saveLoginAttempt(username, LOGON_SUCCESSFUL);

		if (user.isInactiveUser()) {
			addActionError(getText("error.inactive.user"));
			hasError = true;
		} else if (user.isAccessExpired()) {
			addActionError(getText("error.expired.user"));
			hasError = true;
		} else if (user.isUserRejected()) {
			addActionError(getText("error.rejected.user"));
			hasError = true;
		} else if (user.isUnapprovedUser()) {
			addActionError(getText("error.unapproved.user"));
			hasError = true;
		} 
		
		if (!hasError) {
			boolean isLockedByRetries = service.isAccountLockedByRetries(username, false);
			if(isLockedByRetries) {
				addActionError(getText("error.account.locked.retries"));
				hasError = true;
			}
		}

		if (logger.isDebugEnabled()) {
			logger.debug("username: " + user.getUsername());
		}
		
		if (!hasError) {
			MessageUtil.getPreferredLocale();
			// check if password expired or Admin resets password
			if(user.isPasswordExpired()) {
				displayMessage = true;
				return "passwordExpired";
			}  
	
	        // set session timeout for the user
			service.setSessionTimeout(user, request.getSession());			
	
			PropertyDAO propertyDAO = (PropertyDAO)MmrBeanLocator.getInstance().findBean("propertyDAO");
			boolean checkTermsOfUse = Boolean.valueOf( propertyDAO.readProperty(Constants.SYSTEM_SCOPE, "CHECK_TERMS_OF_USE") ).booleanValue();
			if(checkTermsOfUse){
				nextAction = NavConsts.TERMS_OF_USE_ACTION;
			}
			else if (requestedPage != null) {
				nextAction = requestedPage;
			}
			else if(StringUtil.isEmpty(nextAction)) {
				MenusDAO menusDAO = (MenusDAO) MmrBeanLocator.getInstance().findBean("menusDAO");
				MenuItemVO menuItem = menusDAO.getMenuById(user.getDefaultMenuId(), user.getRoles(), MessageUtil.getLocale());
				if(menuItem!=null){
					//Check if the Customer is a Warehouse Customer, if Yes, then redirect to the corresponding url.
					if(UserUtil.getMmrUser().getCustomerId() > 0)
					{
						Customer customer = customerService.getCustomerInfoByCustomerId(UserUtil.getMmrUser().getCustomerId());
						if(customer.isWarehouseCustomer())
						{
							//nextAction = NavConsts.SHIPPING_WAREHOUSE_CUSTOMER;
							menuItem.setUrl(NavConsts.SHIPPING_WAREHOUSE_CUSTOMER);
						}
					}
					nextAction = menuItem.getUrl();
					//determine the top menu to highlight
					while(!menuItem.getLevel().equalsIgnoreCase(MenuItemVO.TOP_LEVEL)){
						menuItem = menusDAO.getMenuById(menuItem.getParentId(), user.getRoles(), MessageUtil.getLocale());
						if(menuItem.getName().equalsIgnoreCase(NavConsts.TOP_LEVEL_SHIPPING))
						{
							menuItem.setUrl(NavConsts.SHIPPING_WAREHOUSE_CUSTOMER);
						}
					}
					if(menuItem!=null)
						getSession().put("HighLightMenu", menuItem.getName());

				}
				else
					nextAction = NavConsts.SEARCH_SHIPMENT_ACTION;
			}
		}
		
		if (hasError) {
			SecurityContextHolder.clearContext();
			result = "input";
		}
		if (logger.isDebugEnabled()) {
			logger.debug("Result: " + result);
			logger.debug("Next Action: " + nextAction);
		}
		
		//remove the highLighted menu from the session.
//		getSession().remove("HighLightMenu");
		
		//add the business to the session
		ActionContext.getContext().getSession().put(ShiplinxConstants.BUSINESS, user.getBusiness());
		ActionContext.getContext().getSession().put(ShiplinxConstants.USERNAME, user.getFullName());
		ActionContext.getContext().getSession().put("customerId", user.getCustomerId());
		Common.setCookie("businessId", String.valueOf(user.getBusiness().getId()), ShiplinxConstants.COOKIE_EXPIRED_TIME);

		
		return result;
	}

	/**
	 * View Login Page
	 * 
	 * @return
	 * @throws Exception
	 */
	public String viewLogon() throws Exception {
		
		String code = (String)request.getParameter("sales");		
		logger.info("Sales code: " + code);
		getSession().put(ShiplinxConstants.USER_CODE,
				(request.getParameter("sales")));
		Business business = (Business)request.getSession().getAttribute("business");
		logger.info("Business Id"+business);
		setSalesCode(code, business);
		
		return INPUT;
	}

	private void setSalesCode(String code, Business business){
		//if not code, check if the code is set in cookie, i.e user visited before
		if(StringUtil.isEmpty(code)){
			logger.info("Enter Code is Empty");
			code = Common.getCookie(ShiplinxConstants.USER_CODE);
		}
		else //store cookie in code
			Common.setCookie(ShiplinxConstants.USER_CODE, code, ShiplinxConstants.COOKIE_EXPIRED_TIME);
		
		//if there is a code, then remember this in the session
		//Also get the sales person associated with this code
		if(business == null){
						Business newBusiness = new Business();		
						newBusiness.setId(1);
						business = newBusiness;
					}
		if(!StringUtil.isEmpty(code) && business!=null){
			logger.info("Sales Person");
			logger.info(code);
			logger.info(business);
			//logger.info(business.getId());
			request.getSession().setAttribute(ShiplinxConstants.USER_CODE, code);
			UserSearchCriteria criteria = new UserSearchCriteria();
			criteria.setBusinessId(business.getId());
			criteria.setRoleCode(ShiplinxConstants.ROLE_SALES);
			criteria.setUserCode(code);
			List<User> salesUsers = service.findUsers(criteria, 0, 0);
			if(salesUsers!=null && salesUsers.size()==1){ //only if exact match
				logger.info("Sales Person and Sales User");
				request.getSession().setAttribute("logoURL", salesUsers.get(0).getLogoURL());
			}//this else block to reset the IC logo when there are no sales user in bussiness id 2;
						else{
							request.getSession().removeAttribute("logoURL");
							

			}
		}
		
	}
	/**
	 * View Forgot Password Page
	 * 
	 * @return
	 * @throws Exception
	 */
	public String viewForgotPassword() throws Exception {
		return INPUT;
	}

	public String doForgotPassword() throws Exception {

		Boolean isResponseCorrect = Boolean.FALSE;
		//remenber that we need an id to validate!
		String captchaId = request.getSession().getId();
		//retrieve the response
		String turing = request.getParameter("turing");
		// Call the Service method
		try {
			isResponseCorrect = CaptchaServiceSingleton.getInstance()
					.validateResponseForID(captchaId, turing);
		} catch (CaptchaServiceException e) {
			//should not happen, may be thrown if the id is not valid 
			e.printStackTrace();
		}
		
		if(!isResponseCorrect){
			addActionError(getText("error.captcha.noMatch"));
			return INPUT;
			
		}

		String username = request.getParameter("j_username");
		String email = request.getParameter("j_email");
		
		if(username==null || email==null || username.length()==0 || email.length()==0){
			addActionError(getText("error.forgotPassword.details"));
			return INPUT;
		}
		
		User user = this.service.findUserByUsername(username.trim());
		
		if(user==null || !user.getEmail().equalsIgnoreCase(email.trim())){
			addActionError(getText("error.forgotPassword.notMatched"));
			return INPUT;
			
		}
		
		service.resetPassword(user);
		//user exists and has been matched to email address
		addActionMessage(getText("success.forgotPassword.matched"));

		return SUCCESS;
	}

	
	/**
	 * View Login Page if the session is expired
	 * 
	 * @return
	 * @throws Exception
	 */
	public String viewLogonSessionExp() throws Exception {
        nextAction = (String) ActionContext.getContext().getSession().get(Constants.CURRENT_PAGE);

		String customLogoutURL = determineCustomLogout();
		if(customLogoutURL!=null){
			nextAction = new String(customLogoutURL);
			return "login";
		}
		addActionError(getText("login.request.notes1"));
		addActionError(getText("login.request.notes2"));
		
		return INPUT;
	}
	
	
	
	public String logout() throws Exception {
		
		String adminUser = (String) getSession().get(ShiplinxConstants.ADMIN_USER);
		String strUserLogin = (String) getSession().get("userlogin");
		if(adminUser==null){	
			//check if there is a URL defined int he business record to direct the customer to.
			String customLogoutURL = determineCustomLogout();
			if(customLogoutURL!=null)
				nextAction = new String(customLogoutURL);
			
			getSession().clear();	
			
			if(nextAction != null)
				return "next";
			
			return SUCCESS;
		}
		
		getSession().remove(ShiplinxConstants.ADMIN_USER);
		getSession().remove("userlogin");
		UserDetails userDetails = UserUtil.reloadUserContext(adminUser);  
		UserUtil.setMmrUser((User)((CustomUser)userDetails).getUserInfo()); 
		ActionContext.getContext().getSession().put("username", adminUser);
		
		//returns to search User page if LoginAs is directed from that page.
		if(strUserLogin!=null)
		{
			//setting the customerId that is passed as a param to the redirected action
			userCustomerId = Long.valueOf(String.valueOf(getSession().get("uCustomerId")));
			getSession().remove("uCustomerId");
			return "return2";
		}
		String menu = "Admin";
		getSession().remove("HighLightMenu");
		getSession().put("HighLightMenu", menu);
		return "return";
		
	}

	public String loginAsUser() throws Exception {
		
		String currentUser = UserUtil.getMmrUser().getUsername();
		String customerId = request.getParameter("id");
		
		UserSearchCriteria criteria = new UserSearchCriteria();
		criteria.setBusinessId(UserUtil.getMmrUser().getBusinessId());
		if(customerId!=null)
			criteria.setCustomerId(Long.valueOf(customerId));
		else
		{
			criteria.setCustomerId(0L);
			criteria.setUsername(request.getParameter("username"));
			//to determine the flow of logout
			getSession().put("userlogin", "yes");
		}
		
		List<User> users = service.findUsers(criteria,0,0);
		
		if(users==null || users.size()==0){
		     addActionError(getText("login.request.notes1"));
   	         return INPUT;
   	    }
		
		User firstUserFound = users.get(0);
		for(User user: users){
			if(user.getUserRole().equalsIgnoreCase(ShiplinxConstants.ROLE_CUSTOMER_ADMIN)){
				firstUserFound = user;
				break;
			}
		}
		UserDetails userDetails = UserUtil.reloadUserContext(firstUserFound.getUsername());  
		UserUtil.setMmrUser((User)((CustomUser)userDetails).getUserInfo()); 
		
		ActionContext.getContext().getSession().put("username", firstUserFound.getUsername()+"*");
		getSession().put(ShiplinxConstants.ADMIN_USER, currentUser);
		//to get the customerId from the user logged in.
		getSession().put("uCustomerId", firstUserFound.getCustomerId());
		if(request.getParameter("username") != null && !request.getParameter("username").isEmpty()){
			   return "SUCCESS1";
			  }
		
		return SUCCESS;
	}
	
	/**
	 * View Login Page - login process failed.
	 * This method is called by ACEGI upon login failure.
	 * 
	 * @return
	 * @throws Exception
	 */
	public String viewLogonFailed() throws Exception {
		
		username = (String)request.getSession().getAttribute("failed_user");
		saveLoginAttempt(username, LOGON_FAILED);
		
		//	check if account is locked due to retries (for existing users only). 
		//	It also updates the failed attempt count.
		boolean isLockedByRetries = service.isAccountLockedByRetries(username, true);
		if(isLockedByRetries) {
			addActionError(getText("error.account.locked.retries"));
		}
		else{
			addActionError(getText("error.invalid.user"));
		}
		
		String customLogoutURL = determineCustomLogout();
		if(customLogoutURL!=null)
			nextAction = new String(customLogoutURL);

		if(nextAction!=null)
			return "next";
		
		return INPUT;
	}
	
	/**
	 * Saves the login attempt to DB
	 * 
	 * @param loginStatus
	 * @throws Exception
	 */
	private void saveLoginAttempt(String enteredUserName, String loginStatus) throws Exception{
		LoginStatusVO loginStatusVO = new LoginStatusVO();
		loginStatusVO.setUserName(enteredUserName);
		loginStatusVO.setLoginStatus( loginStatus );
		loginStatusVO.setIpAddress(request.getRemoteAddr());
		loginStatusVO.setHostName(InetAddress.getByName(request.getRemoteAddr()).getHostName());
		service.saveLoginAttempt(loginStatusVO);
	}
	
	public String otherDetail() throws Exception{
		return "success";
	}
	

	/**
	 * 
	 * @return
	 */
	public String getNextAction() {
		return nextAction;
	}

	/**
	 * 
	 * @param nextAction
	 */
	public void setNextAction(String nextAction) {
		this.nextAction = nextAction;
	}

	/**
	 * 
	 * @param httpServletRequest
	 */
    public void setServletRequest(HttpServletRequest httpServletRequest) {
    	this.request = httpServletRequest;     
    }

    /**
     * 
     * @return
     */
	public String getUsername() {
		return username;
	}

	/**
	 * 
	 * @param username
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserService getService() {
		return service;
	}


	
	public User getCurrentUser() {
		return user;
	}

	public boolean isAcegiLogin() {
		String acegiLoginStr = WebUtil.getProperty(Constants.SYSTEM_SCOPE, Constants.REMEMBER_PASSWORD);
		acegiLogin = (acegiLoginStr.equals("true")?true:false);
		return acegiLogin;
	}

	public void setAcegiLogin(boolean acegiLogin) {
		this.acegiLogin = acegiLogin;
	}

	public boolean isDisplayMessage() {
		return displayMessage;
	}

	public void setDisplayMessage(boolean displayMessage) {
		this.displayMessage = displayMessage;
	}
	
	private String determineCustomLogout(){
		
		String customLogoutURL = null;
		try{
	        //try to determine the business
	        Business business  = (Business)this.getSession().get("business");
	        if(business == null){ //try to determine from the cookie
	        
	        	String businessId = Common.getCookie("businessId");
	        	if(businessId!=null){
	        		business = businessService.getBusinessById(Long.valueOf(businessId));
	        	}
	        	
	        }
	        
	        if(business!=null)
	        	customLogoutURL = business.getLogoutURL();
		}
		catch(Exception e){
			customLogoutURL = null;
		}
		
		return customLogoutURL;

	}

	public CustomerManager getCustomerService() {
		return customerService;
	}

	public void setCustomerService(CustomerManager customerService) {
		this.customerService = customerService;
	}

	public Long getUserCustomerId() {
		return userCustomerId;
	}

	public void setUserCustomerId(Long userCustomerId) {
		this.userCustomerId = userCustomerId;
	}

		
}