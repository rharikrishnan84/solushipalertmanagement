/**
 * 
 */
package com.meritconinc.mmr.action.common;

import java.util.Locale;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.meritconinc.mmr.action.BaseAction;
import com.meritconinc.shiplinx.utils.ShiplinxConstants;
import com.opensymphony.xwork2.ActionContext;



/**
 * @author brinzf2
 *
 */
public class SetLanguage extends BaseAction {
	private static final long serialVersionUID	= 18052011;
	private String language;
	private HttpServletResponse response;

	private static final Logger log = Logger.getLogger(SetLanguage.class); 
	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public String execute() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		Cookie cookie = new Cookie("locale", language);
		cookie.setMaxAge(ShiplinxConstants.COOKIE_EXPIRED_TIME); //2 months
		Locale locale = new Locale(language.substring(0,2), language.substring(3));
		ActionContext.getContext().setLocale(locale);
		ActionContext.getContext().getSession().put("locale", language);
		response.addCookie(cookie);
		
		return SUCCESS;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}
}
