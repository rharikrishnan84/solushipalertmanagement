package com.meritconinc.mmr.action.common;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.Preparable;
import com.meritconinc.mmr.action.BaseAction;
import com.meritconinc.mmr.constants.Constants;
import com.meritconinc.mmr.exception.EmailAlreadyRegisteredException;
import com.meritconinc.mmr.exception.UsernameAlreadyTakenException;
import com.meritconinc.mmr.model.common.LocaleVO;
import com.meritconinc.mmr.model.security.User;
import com.meritconinc.mmr.service.UserService;
import com.meritconinc.mmr.utilities.MessageUtil;
import com.meritconinc.mmr.utilities.StringUtil;
import com.meritconinc.mmr.utilities.WebUtil;

public class RequestAccessAction extends BaseAction implements Preparable, ServletRequestAware{
	private static final long serialVersionUID 		= 6272007;
	
	private static final Logger log = Logger.getLogger(RequestAccessAction.class);	
	private UserService service;
	private List<LocaleVO> availableLocales;
	private HttpServletRequest request;    

	
    public void setServletRequest(HttpServletRequest httpServletRequest) {
    	this.request = httpServletRequest;     
    }
	
    public void setAvailableLocales() {
		availableLocales = MessageUtil.getLanguagesByLocale();	
	}
	 
	public void setAvailableLocales(List<LocaleVO> availableLocales) {
		this.availableLocales = availableLocales;
	}
	
	public List<LocaleVO> getAvailableLocales() {
		availableLocales  = new ArrayList<LocaleVO>();
		String userLocale = MessageUtil.getLocale();
		List<LocaleVO> locales = MessageUtil.getLanguagesByLocale();
		LocaleVO displayFirstLocaleVO = null;
		for (int i = 0; i < locales.size(); i++) {
			LocaleVO localeVO = (LocaleVO)locales.get(i);
			if (localeVO.getLocale().equals(userLocale)) {
				displayFirstLocaleVO = localeVO;
			}
			else {
				availableLocales.add(localeVO);
			}
		}	
		availableLocales.add(0,displayFirstLocaleVO);
		return availableLocales;
	}
		
	public List<LocaleVO> getLocales() {
		return MessageUtil.getLocales();
	}
	
	
	private User user;

    public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public void prepare() throws Exception {
		request.setAttribute("countries", MessageUtil.getCountriesList());
	}

	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public String execute() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("username: " + user.getUsername());
		}

		user.setStatus(Constants.STATUS_UNAPPROVED);
		user.setCreatedBy(user.getUsername());
		
		Collection<String> rolesList = new ArrayList<String>();
		//rolesList.add(Constants.PUBLIC_ROLE_CODE);
		rolesList.add(Constants.BASE_USER_ROLE);
		// calculate role from properties here
		String defaultRoles = WebUtil.getProperty(Constants.SYSTEM_SCOPE, Constants.REQUEST_ACCESS_DEFAULT_ROLE);
		if (defaultRoles != null) {
			String[] ar = defaultRoles.split(",");
			for (int i = 0; i < ar.length; i++) {
				String defaultRole = ar[i];
				if(!defaultRole.equalsIgnoreCase("none")) {
					rolesList.add(defaultRole);
				}
			}
		}
		user.setRoles(rolesList);
		user.setSessionTimeout(Integer.parseInt(WebUtil.getProperty(Constants.SYSTEM_SCOPE, Constants.TIMEOUT)));

		if (!StringUtil.isValidPhoneNumber(user.getPhoneNumber())) {
        	addFieldError("phoneNumber", getText("error.phone.number.format"));
	       	return INPUT;
		} else {
			user.setPhoneNumber(StringUtil.stripChars(user.getPhoneNumber()));
			if (StringUtil.isValidPhoneNumber(user.getPhoneNumberExt())) {
				user.setPhoneNumberExt(StringUtil.stripChars(user.getPhoneNumberExt()));
			} else {
	        	addFieldError("phoneNumber", getText("error.phone.number.format"));
		       	return INPUT;
			}
		}

		if (isValidPasswords()) {
			try {
		       	service.addRequestAccess(user);
		       	addActionMessage(getText("info.request.save.success"));
		       	//user = new User();
		       	return SUCCESS;
			} catch (EmailAlreadyRegisteredException ex) {
	        	addActionError(getText("error.email.taken"));
		       	return INPUT;
			} catch (UsernameAlreadyTakenException ex) {
	        	addActionError(getText("error.username.taken"));				
		       	return INPUT;
			}
		} else {
			addActionError(getText("error.invalid.passwords"));
	       	return INPUT;
		}
		
       	//return SUCCESS;
    }

	private boolean isValidPasswords() {
		if (user.getPassword().equals(user.getRetypePassword())) {
			return true;
		} else {
			return false;
		}
	}

	public UserService getService() {
		return service;
	}
	 

	public void setService(UserService service) {
		this.service = service;
	}

	public String input() {
		return INPUT;
	}	
	
	public String getCountryName(String countryCode){
		return MessageUtil.getCountryName(countryCode);
	}
}
