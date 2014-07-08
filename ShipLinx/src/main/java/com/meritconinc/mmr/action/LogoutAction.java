/**
 * 
 */
package com.meritconinc.mmr.action;

/**
 * @author
 * 
 */
public class LogoutAction extends BaseAction {
  private static final long serialVersionUID = 18052007;

  /**
   * Custom implementation to handle business logic
   * 
   * @return
   * @throws Exception
   */
  public String execute() throws Exception {

    // remove the highLighted menu from the session.
    getSession().remove("HighLightMenu");
    return SUCCESS;
  }
}
