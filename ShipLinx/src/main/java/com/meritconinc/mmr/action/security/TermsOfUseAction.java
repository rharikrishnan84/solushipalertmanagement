/**
 * 
 */
package com.meritconinc.mmr.action.security;

import org.acegisecurity.Authentication;
import org.acegisecurity.context.SecurityContextHolder;

import com.meritconinc.mmr.action.BaseAction;
import com.meritconinc.mmr.constants.Constants;
import com.meritconinc.mmr.constants.NavConsts;
import com.meritconinc.mmr.model.security.User;
import com.meritconinc.mmr.service.acegi.CustomUser;

import com.opensymphony.xwork2.ActionContext;

/**
 * @author brinzf2
 *
 */
public class TermsOfUseAction extends BaseAction {
	private static final long serialVersionUID	= 1115007;

	private String termsOfUse;
	private String nextAction;
	private boolean userAgreed;
	
	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public String viewTermsOfUse() throws Exception{ 	
		return INPUT;
	}
	
	
    /**
     * 
     * @return
     * @throws Exception
     */
	public String execute() throws Exception {
		String nextPage = "next";
		
		if(userAgreed){
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			Object obj = auth.getPrincipal();
			// user should be validated
			if (obj instanceof CustomUser) {
				CustomUser customUser = (CustomUser) obj;
				User user = (User) customUser.getUserInfo();
				user.setAcceptedTermsOfUse(true);
				String requestedPage = (String) ActionContext.getContext()
						.getSession().get(Constants.CURRENT_PAGE);
				if (requestedPage == null)
					nextAction = NavConsts.WELCOME_ACTION;
				else
					nextAction = requestedPage;
			}
			else {
				nextAction = NavConsts.LOGOUT_ACTION;
			}
		}
		else{
			nextAction = NavConsts.LOGOUT_ACTION;
		}
		
		return nextPage;
	}


	/**
	 * @return the termsOfUse
	 */
	public String getTermsOfUse() {
		return termsOfUse;
	}


	/**
	 * @param termsOfUse the termsOfUse to set
	 */
	public void setTermsOfUse(String termsOfUse) {
		this.termsOfUse = termsOfUse;
	}


	/**
	 * @return the nextAction
	 */
	public String getNextAction() {
		return nextAction;
	}


	/**
	 * @param nextAction the nextAction to set
	 */
	public void setNextAction(String nextAction) {
		this.nextAction = nextAction;
	}


	/**
	 * @return the userAgreed
	 */
	public boolean isUserAgreed() {
		return userAgreed;
	}


	/**
	 * @param userAgreed the userAgreed to set
	 */
	public void setUserAgreed(boolean userAgreed) {
		this.userAgreed = userAgreed;
	}
	
}
