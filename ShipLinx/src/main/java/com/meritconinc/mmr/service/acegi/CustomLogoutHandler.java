package com.meritconinc.mmr.service.acegi;
// just placeholder by now, not used
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.acegisecurity.Authentication;
import org.acegisecurity.context.SecurityContextHolder;
import org.acegisecurity.ui.logout.LogoutHandler;
import org.apache.log4j.Logger;

public class CustomLogoutHandler implements LogoutHandler {
	private static final Logger log = Logger.getLogger(CustomLogoutHandler.class);

	public void logout(HttpServletRequest request,
            HttpServletResponse response, Authentication authentication) {
    	HttpSession session = request.getSession(false);
		if (session != null) {
			session.invalidate();
		}
    	SecurityContextHolder.clearContext(); //invalidate authentication
		//SecurityContextHolder.getContext().setAuthentication(null);
    }
} 

