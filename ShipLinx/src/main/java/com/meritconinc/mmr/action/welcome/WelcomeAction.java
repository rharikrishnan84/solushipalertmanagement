/**
 * 
 */
package com.meritconinc.mmr.action.welcome;

import org.acegisecurity.Authentication;
import org.acegisecurity.AuthenticationTrustResolver;
import org.acegisecurity.AuthenticationTrustResolverImpl;
import org.acegisecurity.context.SecurityContext;
import org.acegisecurity.context.SecurityContextHolder;

import com.meritconinc.mmr.action.BaseAction;
import com.opensymphony.xwork2.ActionContext;

/**
 * @author brinzf2
 * 
 */
public class WelcomeAction extends BaseAction {
  private static final long serialVersionUID = 18052007;

  /**
   * Custom implementation to handle business logic
   * 
   * @return
   * @throws Exception
   */
  public String execute() throws Exception {
    AuthenticationTrustResolver resolver = new AuthenticationTrustResolverImpl();
    // System.out.println(" WELCOME ACTION BEFORE SEND REQUEST ");

    // RateAvailableWebServiceClient.sendRequest();

    // System.out.println(" WELCOME ACTION AFTER SEND REQUEST ");

    SecurityContext ctx = SecurityContextHolder.getContext();
    if (session == null) {
      session = ActionContext.getContext().getSession();
    }

    if (ctx != null) {
      // by now it is just placeholders
      Authentication auth = ctx.getAuthentication();
      if (resolver.isRememberMe(auth)) {
        session.put("cookieLogin", "true");
      } else if (resolver.isAnonymous(auth)) {
      }
    }
    return SUCCESS;
  }
}
