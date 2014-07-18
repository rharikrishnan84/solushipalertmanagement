package com.meritconinc.shiplinx.action;


import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.meritconinc.mmr.dao.MenusDAO;
import com.meritconinc.mmr.dao.PropertyDAO;
import com.meritconinc.mmr.model.common.LocaleVO;
import com.meritconinc.mmr.model.common.PropertyVO;
import com.meritconinc.mmr.utilities.security.UserUtil;
import com.opensymphony.xwork2.Preparable;



/**
 * <code>Set welcome message.</code>
 */
public class MenuAction extends BaseAction implements Preparable,ServletRequestAware{
	private static final long serialVersionUID	= 25092007;
	private MenusDAO menuItemDAO;
	private PropertyDAO propertyDAO;
	private static final Logger log = LogManager.getLogger(MenuAction.class);
	private HttpServletRequest request;
	private Map<String, Long> menuId = new HashMap<String, Long>();
	private String url;

	public String getUrl() {
		return url;
	}
	private InputStream inputStream;
		
	

	public InputStream getInputStream() {
		return inputStream;
	}


	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}


	public String execute() throws Exception {
		
		log.debug("--------execute----MenuAction-----"); 
		url= /*request.getContextPath() + */request.getParameter("value");
		com.meritconinc.mmr.model.security.User user = UserUtil.getMmrUser();
		String menu = request.getParameter("menu");
		getSession().remove("HighLightMenu");
		getSession().put("HighLightMenu", menu);
		return "success";
	} 
	
	
	public void setUrl(String url) {
		this.url = url;
	}

	public void prepare() throws Exception {
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
	public String  help() {
		log.debug("--------Help----MenuAction-----");
		System.out.println("Test Help Method");
		String user = UserUtil.getMmrUser().getUsername();
		String highLightMenu = getSession().get("HighLightMenu").toString();
		LocaleVO locale = menuItemDAO.getLanguageByUserName(user);
		String language = locale.getLocaleText();
		 String string="";

	        //reading   
	        try{

	        	String path = request.getHeader("Referer");
	        	 String index[]=path.split("/");
	        	 String method =index[index.length-1].substring(0,index[index.length-1].lastIndexOf("."));
	       	    url = method+"."+language;
	       	 PropertyVO propertyVO = propertyDAO.getPath(language); 
	       		            url = propertyVO.getDbValue();
	       		            String file = url+"/"+method+".html";
	       		            inputStream = new FileInputStream(file);
	        }       
	        catch (Exception e){
	            System.out.println(e.toString());
	        }
		return SUCCESS;
		
	}

	public MenusDAO getMenuItemDAO() {
		return menuItemDAO;
	}

	public void setMenuItemDAO(MenusDAO menuItemDAO) {
		this.menuItemDAO = menuItemDAO;
	}
	
	public PropertyDAO getPropertyDAO() {
				return propertyDAO;
			}
		
		
			public void setPropertyDAO(PropertyDAO propertyDAO) {
				this.propertyDAO = propertyDAO;
			}
	
}