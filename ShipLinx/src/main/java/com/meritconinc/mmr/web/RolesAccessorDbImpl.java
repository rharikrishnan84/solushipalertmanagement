package com.meritconinc.mmr.web;


import java.util.List;

import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.opensymphony.xwork2.ActionContext;
import com.meritconinc.mmr.dao.RolesTagDAO;


public class RolesAccessorDbImpl implements RolesAccessor{
	public List<String> getRoles(String section) throws Exception{
		List<String> ret = null;
		WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext((ServletContext)ActionContext.getContext().get(ServletActionContext.SERVLET_CONTEXT));
		RolesTagDAO rolesTagDAO = (RolesTagDAO)context.getBean("rolesTagDAO");
		ret = rolesTagDAO.getRoles(section);
	    return ret;
	}
} 
