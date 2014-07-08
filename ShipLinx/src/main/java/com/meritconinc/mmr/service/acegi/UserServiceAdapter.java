package com.meritconinc.mmr.service.acegi;

import java.util.Collection;
import java.util.Iterator;

import org.acegisecurity.GrantedAuthority;
import org.acegisecurity.GrantedAuthorityImpl;
import org.acegisecurity.userdetails.UserDetails;
import org.acegisecurity.userdetails.UserDetailsService;
import org.acegisecurity.userdetails.UsernameNotFoundException;
import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;

import com.meritconinc.mmr.model.security.User;
import com.meritconinc.mmr.service.UserService;
import com.meritconinc.mmr.utilities.MmrBeanLocator;

/**
 * 
 * @author rmerchant
 * Adapter between ACEGI and ShipLinx
 */
public class UserServiceAdapter implements UserDetailsService {
	private static final Logger logger = Logger.getLogger(UserServiceAdapter.class);

	/**
	 * 
	 * @param userName
	 * @return
	 * @throws UsernameNotFoundException
	 * @throws DataAccessException
	 * Called internally by ACEGI DAO authentication filter, based on provided URL
	 * ACEGI will create SecurityContext after succefull authentication
	 *    
	 */ 
	public UserDetails loadUserByUsername(String userName)
			throws UsernameNotFoundException, DataAccessException {
		
		if(logger.isDebugEnabled())
			logger.debug("In UserService Adapter");
	
		User user = null;

		if (userName == null || userName.equals("")) {
			throw new UsernameNotFoundException("User not in request");
		}
		UserService userService = (UserService) MmrBeanLocator.getInstance().findBean("userService");
		try {
			user = userService.findUserByUsername(userName);
			if (user == null) {
				throw new UsernameNotFoundException("User not found");
			}
		} catch (Exception e) {
			throw new UsernameNotFoundException("Invalid User");
		}
		
		Collection<String> roles = null;
		
		roles = user.getRoles();
		
		GrantedAuthority[] grantedAuthorities = new GrantedAuthority[roles.size()];
		int i = 0;
		for (Iterator<String> iter = roles.iterator(); iter.hasNext(); i++) {
			String role = (String) iter.next();
			GrantedAuthority authority = new GrantedAuthorityImpl(role);
            grantedAuthorities[i] = authority;
		}
		
		UserDetails userDetails = new CustomUser(
				user.getUsername(), 
				user.getPassword(), 
				true, //enabled, terms of use by now, should be reviewed later
				true, //accountNonExpired
				true, //credentialsNonExpired
				true, //accountNonLocked
				grantedAuthorities,
				(Object)user);
		return userDetails;
	}
}
