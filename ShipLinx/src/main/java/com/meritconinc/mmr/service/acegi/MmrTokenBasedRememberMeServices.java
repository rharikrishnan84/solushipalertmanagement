package com.meritconinc.mmr.service.acegi;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.acegisecurity.ui.rememberme.TokenBasedRememberMeServices;
import org.acegisecurity.userdetails.UserDetails;
import org.apache.log4j.Logger;

import com.meritconinc.mmr.model.security.User;

public class MmrTokenBasedRememberMeServices extends
		TokenBasedRememberMeServices {

	private static final Logger log = Logger.getLogger(MmrTokenBasedRememberMeServices.class);
	
	protected boolean isValidUserDetails(HttpServletRequest request, HttpServletResponse response,
			UserDetails userDetails, String[] cookieTokens) {
		// Immediately reject if the user is not allowed to
		// login
		if (!userDetails.isAccountNonExpired() || !userDetails.isCredentialsNonExpired() || !userDetails.isEnabled()) {
			cancelCookie(request, response, "Cookie token[0] contained username '" + cookieTokens[0]
					+ "' but account has expired, credentials have expired, or user is disabled");

			return false;
		} 
		/** FMK User Status Checking **/
		User mmrUser = (User)((CustomUser) userDetails).getUserInfo();
		if (mmrUser.isAccessExpired() || mmrUser.isInactiveUser() || mmrUser.isUnapprovedUser() || mmrUser.isUserRejected() || mmrUser.isUserLocked()) {
			log.debug("access expired");
			cancelCookie(request, response, "Cookie token[0] contained username '" + cookieTokens[0]
                    + "' but account has expired, credentials have expired, or user is disabled");
  			return false;
		}
		
		return true;
	}
	

}
