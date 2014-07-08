package com.meritconinc.mmr.service.acegi;

import org.acegisecurity.AccountExpiredException;
import org.acegisecurity.Authentication;
import org.acegisecurity.AuthenticationException;
import org.acegisecurity.BadCredentialsException;
import org.acegisecurity.DisabledException;
import org.acegisecurity.LockedException;
import org.acegisecurity.providers.AuthenticationProvider;
import org.acegisecurity.providers.UsernamePasswordAuthenticationToken;
import org.acegisecurity.providers.dao.DaoAuthenticationProvider;
import org.acegisecurity.userdetails.UserDetails;
import org.acegisecurity.userdetails.UsernameNotFoundException;
import org.springframework.util.Assert;

public class MmrDaoAuthenticationProvider extends DaoAuthenticationProvider {
	public Authentication authenticate(Authentication authentication)
			throws AuthenticationException {
		Assert
				.isInstanceOf(
						UsernamePasswordAuthenticationToken.class,
						authentication,
						messages
								.getMessage(
										"AbstractUserDetailsAuthenticationProvider.onlySupports",
										"Only UsernamePasswordAuthenticationToken is supported"));

		// Determine username
		String username = (authentication.getPrincipal() == null) ? "NONE_PROVIDED"
				: authentication.getName();

		boolean cacheWasUsed = true;
		UserDetails user = super.getUserCache().getUserFromCache(username);

		if (user == null) { 
			cacheWasUsed = false;

			try {
				user = retrieveUser(username,
						(UsernamePasswordAuthenticationToken) authentication);
			} catch (UsernameNotFoundException notFound) {
				if (hideUserNotFoundExceptions) {
					throw new BadCredentialsException(
							messages
									.getMessage(
											"AbstractUserDetailsAuthenticationProvider.badCredentials",
											"Bad credentials"));
				} else {
					throw notFound;
				}
			}

			Assert
					.notNull(user,
							"retrieveUser returned null - a violation of the interface contract");
		}

		if (!user.isAccountNonLocked()) {
			throw new LockedException(messages.getMessage(
					"AbstractUserDetailsAuthenticationProvider.locked",
					"User account is locked"));
		}

		if (!user.isEnabled()) {
			throw new DisabledException(messages.getMessage(
					"AbstractUserDetailsAuthenticationProvider.disabled",
					"User is disabled"));
		}

		if (!user.isAccountNonExpired()) {
			throw new AccountExpiredException(messages.getMessage(
					"AbstractUserDetailsAuthenticationProvider.expired",
					"User account has expired"));
		}

		// This check must come here, as we don't want to tell users
		// about account status unless they presented the correct credentials
		try {
			additionalAuthenticationChecks(user,
					(UsernamePasswordAuthenticationToken) authentication);
		} catch (AuthenticationException exception) {
			if (cacheWasUsed) {
				// There was a problem, so try again after checking
				// we're using latest data (ie not from the cache)
				cacheWasUsed = false;
				user = retrieveUser(username,
						(UsernamePasswordAuthenticationToken) authentication);
				additionalAuthenticationChecks(user,
						(UsernamePasswordAuthenticationToken) authentication);
			} else {
				throw exception;
			}
		}
		if (!cacheWasUsed) {
			super.getUserCache().putUserInCache(user);

		}

		Object principalToReturn = user;

		if (super.isForcePrincipalAsString()) {
			principalToReturn = user.getUsername();
		}

		return createSuccessAuthentication(principalToReturn, authentication,
				user);
	}

}
