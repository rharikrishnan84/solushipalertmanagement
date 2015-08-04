
package com.meritconinc.shiplinx.api.model;

import java.util.List;

public class Client_details{
   	private String accept_language;
   	private Number browser_height;
   	private String browser_ip;
   	private Number browser_width;
   	private String session_hash;
   	private String user_agent;

 	public String getAccept_language(){
		return this.accept_language;
	}
	public void setAccept_language(String accept_language){
		this.accept_language = accept_language;
	}
 	public Number getBrowser_height(){
		return this.browser_height;
	}
	public void setBrowser_height(Number browser_height){
		this.browser_height = browser_height;
	}
 	public String getBrowser_ip(){
		return this.browser_ip;
	}
	public void setBrowser_ip(String browser_ip){
		this.browser_ip = browser_ip;
	}
 	public Number getBrowser_width(){
		return this.browser_width;
	}
	public void setBrowser_width(Number browser_width){
		this.browser_width = browser_width;
	}
 	public String getSession_hash(){
		return this.session_hash;
	}
	public void setSession_hash(String session_hash){
		this.session_hash = session_hash;
	}
 	public String getUser_agent(){
		return this.user_agent;
	}
	public void setUser_agent(String user_agent){
		this.user_agent = user_agent;
	}
}
