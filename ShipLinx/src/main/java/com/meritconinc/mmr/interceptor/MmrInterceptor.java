/**
 * 
 */
package com.meritconinc.mmr.interceptor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.acegisecurity.Authentication;
import org.acegisecurity.GrantedAuthority;
import org.acegisecurity.context.SecurityContextHolder;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.mapper.ActionMapping;
import org.apache.struts2.views.util.UrlHelper;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.meritconinc.mmr.action.BaseAction;
import com.meritconinc.mmr.constants.Constants;
import com.meritconinc.mmr.constants.NavConsts;
import com.meritconinc.mmr.dao.ActionsDAO;
import com.meritconinc.mmr.dao.MenusDAO;
import com.meritconinc.mmr.model.common.AuthorizedActionVO;
import com.meritconinc.mmr.model.security.User;
import com.meritconinc.mmr.utilities.Common;
import com.meritconinc.mmr.utilities.MessageUtil;
import com.meritconinc.mmr.utilities.MmrBeanLocator;
import com.meritconinc.mmr.utilities.UserSessionListener;
import com.meritconinc.mmr.utilities.security.UserUtil;
import com.meritconinc.shiplinx.carrier.CarrierService;
import com.meritconinc.shiplinx.model.Business;
import com.meritconinc.shiplinx.service.BusinessManager;
import com.meritconinc.shiplinx.utils.ShiplinxConstants;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.ExceptionHolder;
import com.meritconinc.shiplinx.dao.BusinessDAO;
import com.soluship.businessfilter.util.BusinessFilterUtil;
/**
 * @author brinzf2
 *
 */
public class MmrInterceptor extends MmrBaseInterceptor {
	private static final long serialVersionUID	= 10092007;
	private static final Logger log = Logger.getLogger(MmrInterceptor.class);
	private String cssText;
	/**
	 * default constructor
	 *
	 */
	public MmrInterceptor(){
		super();
	} 

	/**
	 * 
	 * @return
	 */
	protected boolean isAjaxCall(){
		return false;
	}

	/**
	 * Override to handle interception 
	 * 
	 * @param invocation
	 * @return
	 * @throws Exception
	 */
	public String intercept(ActionInvocation invocation) throws Exception {
		log.debug("> intercept");
		HttpServletRequest request = ServletActionContext.getRequest();		
		 // Initialize requestedActionVO. It may be null if requestedActionName is not in fmk_aciton table 
		ActionsDAO actionsDAO = (ActionsDAO) MmrBeanLocator.getInstance().findBean("actionsDAO");
		String requestedActionName = invocation.getInvocationContext().getName();
		AuthorizedActionVO requestedActionVO = actionsDAO.readAction(requestedActionName);
	
		
		log.debug("> setLocalesInContext");
		setLocalesInContext();
		setBusiness(request);
		
		//Setting the language if the parameter is present
		log.debug("language = " + request.getParameter("language"));
		if (request.getParameter("language") != null)
			setLanguage(request.getParameter("language"));
		
		log.debug("> UserUtil.getMmrUser");
		User user = UserUtil.getMmrUser();
		if (user != null) {
			// getting userSessionListener		
			UserSessionListener userSessionListener = 
						(UserSessionListener) ActionContext
									.getContext().getSession().get(Constants.USER_SESSION_LISTENER);
			
			// instantiating userSessionListener if its null
			if (userSessionListener == null) {

				// instantiating and setting user session listener
				userSessionListener = new UserSessionListener();
				ActionContext.getContext().getSession().put(
						Constants.USER_SESSION_LISTENER, userSessionListener);
			}
		}
		
        String result;
        try {
			log.debug("> genMenu");
			genMenu(invocation, requestedActionName, requestedActionVO);
			log.debug("> checkAccess");
			String nextPage = checkAccess(invocation);
			log.debug("> updateLastSafeAction");
			updateLastSafeAction(invocation, requestedActionName, requestedActionVO);
			
			log.debug("> nextPage"+nextPage);
			if(nextPage == null){
				log.debug(invocation.getAction().getClass() +  " > " + invocation.getProxy().getMethod());
				result = invocation.invoke();
				log.debug(invocation.getAction().getClass() +  " < " + invocation.getProxy().getMethod());
			}
			else{
				log.debug(" result =" + nextPage);
				result = nextPage;
			}
			log.debug("> result"+result);
        }
        catch(Exception e){
        	e.printStackTrace();
        	String excID = Long.toString(System.currentTimeMillis());
        	BaseAction baseAction = (BaseAction)invocation.getAction();
        	baseAction.addFieldError("errorID", "Error ID: "+excID);
        	publishException(invocation, new ExceptionHolder(e));
    		return NavConsts.GLOBAL_ERROR;
        }
        			
		log.debug("< intercept");
		loadCSS(user);
        return result;        
	}

	/**
	 * load the css of the user's Business
	 * @param user
	 */
	private void loadCSS(User us) {
				// TODO Auto-generated method stub
				
				  BusinessDAO businessDAO=(BusinessDAO)MmrBeanLocator.getInstance().findBean("businessDAO");
				  Long businessId=BusinessFilterUtil.setBusinessIdbyUserLevel();
				  if(businessId==null){
					  businessId=us.getBusinessId();
				  } 
				  
				   Business business=businessDAO.getBusiessById(businessId);
				   if(business!=null &&
						   						   business.getCssVO()!=null && 
						   						   business.getCssVO().getBarFirstColor()!=null){
						   					   ActionContext.getContext().getSession().put("hRefColor",business.getCssVO().getBarFirstColor());
						   				   }
				   if(business!=null && business.isNationLevel()){
					   business=businessDAO.getBusiessById(business.getParentBusinessId());
					   
				   }else if( business!=null && business.isBranchLevel() && business.getPartnerId()!=0){

					   business=businessDAO.getBusiessById(business.getPartnerId());
					   
				   }else if(business.isPartnerLevel() && business.getCssVO()==null){
					   business=businessDAO.getBusiessById(business.getParentBusinessId());
				   }
				   if(business!=null && business.getCssVO()!=null){
							 setCssText(business.getCssVO().getCssText());
							 if(getCssText()!=null){
								 ActionContext.getContext().getSession().put("cssText",getCssText());
								 ActionContext.getContext().getSession().put("buttonColor",business.getCssVO().getButtonColor());
								 ActionContext.getContext().getSession().put("barSecondColor",business.getCssVO().getBarSecondColor());
								 /*ActionContext.getContext().getSession().put("footer_1", business.getCssVO().getFooter1());
								  ActionContext.getContext().getSession().put("footer_2", business.getCssVO().getFooter2());*/
							 }
							 if(business.getCssVO().getFooter1() != null && !business.getCssVO().getFooter1().isEmpty()){
								 ActionContext.getContext().getSession().put("footer_1", business.getCssVO().getFooter1());
							 }
							 if(business.getCssVO().getFooter2() != null && !business.getCssVO().getFooter2().isEmpty()){
								 ActionContext.getContext().getSession().put("footer_2", business.getCssVO().getFooter2());
							 }
				   } 
			}
	/**
	 * Generates the menu 
	 * 
	 * @param invocation
	 */
//	private void genMenuOld(ActionInvocation invocation, String requestedActionName, AuthorizedActionVO requestedActionVO){
//
//		String locale = MessageUtil.getLocale();
//		MenusDAO menusDAO = (MenusDAO) MmrBeanLocator.getInstance().findBean("menusDAO");
//        Collection<String> roles = null;
//        
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        if (auth==null || auth.getName().equals("anonymousUser") || auth.getAuthorities().length == 1	
//        		&& auth.getAuthorities()[0].equals(Constants.PUBLIC_ROLE_CODE)) {
//			roles = publicRoles;
//		}
//		else {
//			roles = new ArrayList<String>();
//			GrantedAuthority[] grantedAuthorities = auth.getAuthorities();
//			for (int i = 0; i < grantedAuthorities.length; i++) {
//				roles.add(grantedAuthorities[i].getAuthority());
//			}
//		}
//		List topMenu = menusDAO.getTopMenus(roles, locale);
//		checkForUrlUpdate(topMenu, roles, menusDAO, locale);
//
//		List parentMenuList = menusDAO.getGroupMenusForAction(requestedActionName, roles, locale);
//		checkForUrlUpdate(parentMenuList, roles, menusDAO, locale);
//		List childMenuList = null;
//		if(!parentMenuList.isEmpty() && ((MenuItemVO)parentMenuList.get(0)).getId()!=0 ){
//			MenuItemVO menuItem = (MenuItemVO)parentMenuList.get(0);
//			int selectedId = requestedActionVO.getMenuId();
//			while(menuItem.getParentId()>0){
//				Iterator it = parentMenuList.iterator();
//				while(it.hasNext()){
//					menuItem = (MenuItemVO)it.next();
//					if(menuItem.getId()==selectedId){
//						if(menuItem.getId()!=requestedActionVO.getMenuId() || requestedActionVO.isHighlight())
//							menuItem.setSelected(true);
//						menuItem.setSubMenuItems(childMenuList);
//						selectedId = menuItem.getParentId();
//						break;
//					}
//				}
//				childMenuList = parentMenuList;
//				parentMenuList = menusDAO.getGroupMenusForId(menuItem.getParentId(), roles, locale);
//				checkForUrlUpdate(parentMenuList, roles, menusDAO, locale);
//				menuItem = (MenuItemVO)parentMenuList.get(0);
//			}
//			try{
//			Iterator it = topMenu.iterator();
//			while(it.hasNext()){
//				menuItem = (MenuItemVO)it.next();
//				if(menuItem.getId()==selectedId){
//					menuItem.setSelected(true);
//					menuItem.setSubMenuItems(childMenuList);
//					selectedId = menuItem.getParentId();
//					break;
//				}
//			}
//			}catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//
//		ActionContext.getContext().getSession().put("MENU", topMenu);
//		ActionContext.getContext().getSession().put("PARENT_MENU", parentMenuList);
//		ActionContext.getContext().getSession().put("CHILD_MENU", childMenuList);
//		ActionContext.getContext().getSession().put("ROLE", roles);
//		ActionContext.getContext().getSession().put("ContextPath",ServletActionContext.getRequest().getContextPath());
//	}

//  My Method
//	private void genMenu(ActionInvocation invocation, String requestedActionName, AuthorizedActionVO requestedActionVO){
//
//		String locale = MessageUtil.getLocale();
//		MenusDAO menusDAO = (MenusDAO) MmrBeanLocator.getInstance().findBean("menusDAO");
//        Collection<String> roles = null;
//        
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        if (auth==null || auth.getName().equals("anonymousUser") || auth.getAuthorities().length == 1	
//        		&& auth.getAuthorities()[0].equals(Constants.PUBLIC_ROLE_CODE)) {
//			roles = publicRoles;
//		}
//		else {
//			roles = new ArrayList<String>();
//			GrantedAuthority[] grantedAuthorities = auth.getAuthorities();
//			for (int i = 0; i < grantedAuthorities.length; i++) {
//				roles.add(grantedAuthorities[i].getAuthority());
//			}
//		}
//        List<MenuItemVO> topMenu = menusDAO.getTopMenus(roles, locale);
//		checkForUrlUpdate(topMenu, roles, menusDAO, locale);
//
//		List<MenuItemVO> parentMenuList = menusDAO.getGroupMenusForAction(requestedActionName, roles, locale);
//		checkForUrlUpdate(parentMenuList, roles, menusDAO, locale);
//		List<MenuItemVO> childMenuList = null;
//		if( parentMenuList != null && parentMenuList.size() > 0 && parentMenuList.get(0) != null) {
//			MenuItemVO menuItem = parentMenuList.get(0);
//			int selectedId = requestedActionVO.getMenuId();
//			while(menuItem.getParentId()>0){
////				Iterator it = parentMenuList.iterator();
//				for(MenuItemVO mItem:parentMenuList) {
////					menuItem = (MenuItemVO)it.next();
//					if(mItem.getId()==selectedId) {
//						if(mItem.getId()!=requestedActionVO.getMenuId() || requestedActionVO.isHighlight())
//							mItem.setSelected(true);
//						mItem.setSubMenuItems(childMenuList);
//						selectedId = mItem.getParentId();
//						break;
//					}
//				}
//				childMenuList = parentMenuList;
//				parentMenuList = menusDAO.getGroupMenusForId(menuItem.getParentId(), roles, locale);
//				checkForUrlUpdate(parentMenuList, roles, menusDAO, locale);
//				menuItem = parentMenuList.get(0);
//			}
//			try {
////				Iterator it = topMenu.iterator();
////				while(it.hasNext()){
//				for (MenuItemVO mItem:topMenu) {
////					menuItem = (MenuItemVO)it.next();
//					if(mItem.getId()==selectedId) {
//						mItem.setSelected(true);
//						mItem.setSubMenuItems(childMenuList);
//						selectedId = mItem.getParentId();
//						break;
//					}
//				}
//			}catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//		
//		// *FKhan* - 10 Sep. 11 
//		// I do not fully understand the above code, following I am doing with little knowledge.
//		// It need to be tested throughly.
//		if (childMenuList != null) {
//			parentMenuList = childMenuList;
//			childMenuList = null;
//			for (MenuItemVO mItem:parentMenuList) {
//				if (mItem != null && mItem.getSubMenuItems() != null) {
//					childMenuList = mItem.getSubMenuItems();
//					break;
//				}
//			}
//		}
//
//		ActionContext.getContext().getSession().put("MENU", topMenu);
//		ActionContext.getContext().getSession().put("PARENT_MENU", parentMenuList);
//		if (childMenuList != null)
//			ActionContext.getContext().getSession().put("CHILD_MENU", childMenuList);
//		else 
//			ActionContext.getContext().getSession().remove("CHILD_MENU");
//		ActionContext.getContext().getSession().put("ROLE", roles);
//		ActionContext.getContext().getSession().put("ContextPath",ServletActionContext.getRequest().getContextPath());
//	}

	private void genMenu(ActionInvocation invocation, String requestedActionName, AuthorizedActionVO requestedActionVO){

		String locale = MessageUtil.getLocale();
		MenusDAO menusDAO = (MenusDAO) MmrBeanLocator.getInstance().findBean("menusDAO");
        Collection<String> roles = null;
        
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth==null || auth.getName().equals("anonymousUser") || auth.getAuthorities().length == 1	
        		&& auth.getAuthorities()[0].equals(Constants.PUBLIC_ROLE_CODE)) {
			roles = publicRoles;
		}
		else {
			roles = new ArrayList<String>();
			GrantedAuthority[] grantedAuthorities = auth.getAuthorities();
			for (int i = 0; i < grantedAuthorities.length; i++) {
				roles.add(grantedAuthorities[i].getAuthority());
			}
		}
        MenuGenerator menuGenerator = new MenuGenerator(roles, locale, menusDAO);
        int selectedId = -1;
        if (requestedActionVO != null)
        	selectedId = requestedActionVO.getMenuId();
        menuGenerator.generateMenus(selectedId);
        
	}
	
	
	/*
	 * This function will set the locales in the context.
	 * If locales are set, then the language bar is generated dynamically using this list of locales (in header.jsp).
	 */
	private void setLocalesInContext(){
		ActionContext.getContext().put("LOCALES", MessageUtil.getLocales());	
	}

	private void setBusiness(HttpServletRequest request){
		//first we need to determine the business 
		//check the session to see if the business is set, if already set then do nothing
		if(request.getSession().getAttribute(ShiplinxConstants.BUSINESS) != null)
			return;
		
		
		//first check if the information is available in the cookie, if not then try to determine by domain URL being used
		Business b = null;
		WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext((ServletContext)ActionContext.getContext().get(ServletActionContext.SERVLET_CONTEXT));
		BusinessManager businessService = (BusinessManager)context.getBean("businessService");

		String businessId = Common.getCookie("businessId");
    	if(businessId!=null){
    		b = businessService.getBusinessById(Long.valueOf(businessId));
    	}

    	//finding by domain URL
    	if(b==null){
	    	String url = request.getRequestURL().toString();
			
			//find a better way to query business table 
			List<Business> businesses = businessService.search(b);	 	
			for(Business bus: businesses){
				if(bus.getSubdomain()!=null && url.contains(bus.getSubdomain())){
					b = bus;
					break;
				}
			}
    	}
		
		if(b==null)
			return;
		ActionContext.getContext().getSession().put(ShiplinxConstants.BUSINESS, b);
		Common.setCookie("businessId", String.valueOf(b.getId()), ShiplinxConstants.COOKIE_EXPIRED_TIME);

	}
	
	private void setLanguage(String language) {

		Cookie cookie = new Cookie("locale", language);
		cookie.setMaxAge(ShiplinxConstants.COOKIE_EXPIRED_TIME); //2 months
		Locale locale = new Locale(language.substring(0,2), language.substring(3));
		ActionContext.getContext().setLocale(locale);
		ActionContext.getContext().getSession().put("locale", language);
		ServletActionContext.getResponse().addCookie(cookie);
	}


	private void updateLastSafeAction(ActionInvocation invocation, String requestedActionName, AuthorizedActionVO requestedActionVO) {

		/* Dear Developer, it's a long comment but if you want to know what's going on 
		 * you must read it in full and without skipping lines!

		 * In most cases request URL corresponds to the currently executed action.
		 * Except for then actions are chained. See below an excerpt from mmr.xml: 
		 * 		<action name="save.text.property" method="saveTextProperty" class="propertyListAction">
		 *	   		<result type="chain" name="success">text.property.list</result>
		 * 		</action>
		 * When a request for save.text.property action comes from the browser
		 * first that same action, save.text.property, is invoked.
		 * During this invocation ServletActionContext.getActionMapping() and invocation.getProxy()
		 * refer to the same action and isOriginalAction will be true.
		 * Then, following the chain, Struts invokes text.property.list action.
		 * In this second invocation the request URL refers to save.text.property action but
		 * the action being invoked is text.property.list, in this case isOriginalAction will be false.

		 * Why do we need to be able to tell between these two cases?
		 * During the original action invocation the first action object is created
		 * (com.meritconinc.mmr.action.admin.PropertyListAction in the example above)
		 * and its ReloadActionURL is set according to whether the original action is safe.
		 * During the chained action invocation another action object is created, 
		 * (of the same class or different doesn't matter, both classes extend BaseAction)
		 * and this second object's ReloadActionURL is set according to whether the chained action is safe.
		 * BUT! Later on, according to the rules of action chaining, Struts will copy values from
		 * the first action object to the second action object, and ReloadActionURL is one of these values.
		 * Essentially, the ReloadActionURL of the second action object will be overwritten with
		 * the value of ReloadActionURL from the first action object which may not be the correct value any more!
		 * For example, if the second action is safe ReloadActionURL of the second action object is null
		 * and it must remain null regardless of the value of ReloadActionURL in the first action object.
		 * That's why during the chained action invokation we have to prevent ReloadActionURL from
		 * being modified, hence freezeReloadActionURL() method is called.
		 */
		//log.debug("> CHECK 11111111 ");
		ActionMapping originalAction = ServletActionContext.getActionMapping();
		//log.debug("> CHECK 2222222");
		boolean isOriginalAction = invocation.getProxy().getActionName().equals(originalAction.getName()) && invocation.getProxy().getNamespace().equals(originalAction.getNamespace());
		//log.debug("> CHECK 33333333");
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		//log.debug("> CHECK 44444444444");
		if (requestedActionVO == null || requestedActionVO.isReloadSafe()) {
			//log.debug("> CHECK 55555555555");
			// Why do we build URL from the action rather than taking from request.getRequestURL() ?
			// Because for chained action (when isOriginalAction is false) the request URL does not
			// correspond to the currently executed action. And if the current action is safe but
			// the action in the request URL is not then we may end up storing an unsafe action on
			// the session for reload.
			String url = UrlHelper.buildUrl(requestedActionName, request, response, null, request.getScheme(), true , true , true , false);
			url += ".action";  // SHOULD FIND A WAY TO READ IT FROM THE STRUTS PROPERTIES

			// Wy do we discard query string for a chained action?
			// Because the query string belongs to the original action, whereas chained action
			// may not need the same URL parameters or, worse, may interpret them differently.
			if (isOriginalAction && request.getQueryString() != null)
				url += "?" + request.getQueryString();
			ActionContext.getContext().getSession().put("LastSafeActionURL", url);
		} else {
			((BaseAction) invocation.getAction()).setReloadActionURL( (String) ActionContext.getContext().getSession().get("LastSafeActionURL") );
		}
		//log.debug("updateLastSafeAction:   LastSafeActionURL on session: " + ActionContext.getContext().getSession().get("LastSafeActionURL"));

		// Why do we freeze reload action URL? See comment above  
		if (!isOriginalAction)  // if the current invocation is for a chained action
			((BaseAction) invocation.getAction()).freezeReloadActionURL();

	}
	       public String getCssText() {
				return cssText;
			}
		
			public void setCssText(String cssText) {
				this.cssText = cssText;
			}
}
