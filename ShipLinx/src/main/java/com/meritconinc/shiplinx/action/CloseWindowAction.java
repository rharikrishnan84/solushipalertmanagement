package com.meritconinc.shiplinx.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.meritconinc.mmr.action.BaseAction;
import com.meritconinc.mmr.model.common.LocaleVO;
import com.meritconinc.mmr.model.common.RoleVO;
import com.meritconinc.mmr.model.security.User;
import com.meritconinc.mmr.service.UserService;
import com.meritconinc.mmr.utilities.MessageUtil;
import com.meritconinc.mmr.utilities.StringUtil;
import com.opensymphony.xwork2.Preparable;

/**
 * <code>Set welcome message.</code>
 */
public class CloseWindowAction extends BaseAction 
	implements Preparable, ServletRequestAware {
	
	private static final long serialVersionUID	= 18052007;
	private static final Logger log = Logger.getLogger(CloseWindowAction.class);

	private HttpServletRequest request;    
	
	 
	
	public CloseWindowAction() {
		
		log.debug("CloseWindowAction: CONSTRUCTOR *****************8");
	}

	/*
	 * public String execute() throws Exception { return SUCCESS; }
	 */

	public void setServletRequest(HttpServletRequest httpServletRequest) {
    	this.request = httpServletRequest;     
    }
	
	/**
	 * 
	 * @throws Exception
	 */
	public void prepare() throws Exception {
	
		
		
	}

	public String execute() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("execute: ");
		}
		String strWindowStatus=request.getParameter("name");
		log.debug("execute: CLOSE WINDOW ACTION******strWindowStatus***********"+strWindowStatus);
		if(strWindowStatus!=null && strWindowStatus.equals("hide")){
			request.getSession().setAttribute("WINDOW_STATUS",strWindowStatus);		
		}else{
			request.getSession().removeAttribute("WINDOW_STATUS");
		}
		
		return SUCCESS;
	}

}
