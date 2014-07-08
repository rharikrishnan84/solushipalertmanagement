package com.meritconinc.mmr.web;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.tagext.TagSupport;

import com.meritconinc.mmr.utilities.WebUtil;

public class MmrPropertyTag extends TagSupport{
	private String scope;
	private String name;
	public String getScope() {
		return scope;
	}
	public void setScope(String scope) {
		this.scope = scope;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	public int doStartTag() throws JspException {
        if ((null == scope) || "".equals(scope)) {
            return Tag.SKIP_BODY;
        }
        if ((null == name) || "".equals(name)) {
            return Tag.SKIP_BODY;
        } 
        String property = WebUtil.getProperty(scope, name);
        try {
        	pageContext.getOut().write(property);	
        } catch ( IOException e) {
        	throw new JspException(e);
        }        
        return Tag.SKIP_BODY;
	}
}
