package com.meritconinc.mmr.utilities;

import java.io.Serializable;


import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

import org.apache.log4j.Logger;

import com.meritconinc.mmr.model.security.User;
import com.meritconinc.mmr.service.UserService;
import com.meritconinc.mmr.utilities.security.UserUtil;

public class UserSessionListener implements HttpSessionBindingListener, Serializable {
	private static final long serialVersionUID = 3022008;
	private static Logger _logger = Logger.getLogger(UserSessionListener.class);	
	
	private UserService userService;

	/**
	 * 
	 * @param userName
	 */
	public UserSessionListener() {
		super();
		MmrBeanLocator beanLocator = MmrBeanLocator.getInstance();
		userService = (UserService)beanLocator.findBean("userService");
	}

	

	/**
	 * Notifies the object that it is being bound to a session and identifies
	 * the session
	 * 
	 * @param e
	 */
	public void valueBound(HttpSessionBindingEvent e) {
		HttpSession s = e.getSession();
		_logger.info("Session [" + s.getId() + "] bound ");
		User user = UserUtil.getMmrUser();
		if (user != null) {
			userService.setSessionTimeout(user, s);
		}	 	
	}

	/**
	 * Notifies the object that it is being unbound from a session and
	 * identifies the session.
	 * 
	 * @param e
	 */
	public void valueUnbound(HttpSessionBindingEvent e) {
		HttpSession s = e.getSession();
		_logger.info("Session [" + s.getId() + "] unbound ");
	}
}
