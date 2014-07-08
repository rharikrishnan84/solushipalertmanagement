/**
 * 
 */
package com.meritconinc.mmr.action.welcome;

import com.meritconinc.mmr.action.BaseAction;
import com.opensymphony.xwork2.ActionContext;

/**
 * @author brinzf2
 * 
 */
public class LinksAction extends BaseAction {
  private static final long serialVersionUID = 18052007;

  /**
   * Custom implementation to handle business logic
   * 
   * @return
   * @throws Exception
   */
  public String execute() throws Exception {
    if (session == null) {
      session = ActionContext.getContext().getSession();
    }
    return SUCCESS;
  }
}
