package com.meritconinc.mmr.action;

import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.meritconinc.mmr.model.security.User;
import com.meritconinc.mmr.utilities.MessageUtil;
import com.meritconinc.mmr.utilities.security.UserUtil;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * Base Action class for the Tutorial package.
 */
public abstract class BaseAction extends ActionSupport {
  private static final Logger log = LogManager.getLogger(BaseAction.class);

  protected Map session;
  protected String reloadActionURL;
  protected boolean freezeReloadActionURL; // see MmrInterceptor.updateLastSafeAction() for more
                                           // info
  protected String errorId;
  private String username;
  private String password;
  private String phone;
  private boolean enabled;
  private String fax;
  private String email;
  private String subType;

  public String getErrorId() {
    return errorId;
  }

  public void setErrorId(String errorId) {
    this.errorId = errorId;
  }

  /**
   * Sets the Session map.
   * 
   * @param newSession
   */
  public void setSession(Map newSession) {
    this.session = newSession;
  }

  /**
   * Returns the Session map.
   * 
   * @return
   */
  public Map getSession() {
    if (session == null) {
      session = ActionContext.getContext().getSession();
    }
    return session;
  }

  /**
   * Returns the logged in user.
   * 
   * @return
   */
  public User getLoginUser() {
    return UserUtil.getMmrUser();
  }

  protected String getCurrentLocale() {
    return MessageUtil.getLocale();
  }

  public String getReloadActionURL() {
    return reloadActionURL;
  }

  public void setReloadActionURL(String reloadActionURL) {
    if (!freezeReloadActionURL)
      this.reloadActionURL = reloadActionURL;
  }

  public void freezeReloadActionURL() {
    freezeReloadActionURL = true;
  }

  public String getMessage(String msgId) {
    return MessageUtil.getMessage(msgId, this.getCurrentLocale());
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {

    this.username = username;
    log.debug("CHECK USER NAME in THE BASE AcTION " + username);
  }

  public String getPassword() {
    log.debug("GETTTTTTTT CHECK USER NAME ");
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getFax() {
    return fax;
  }

  public void setFax(String fax) {
    this.fax = fax;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public boolean getEnabled() {
    return enabled;
  }

  public void setEnabled(boolean enabled) {
    this.enabled = enabled;
  }

  public String getSubType() {
    return subType;
  }

  public void setSubType(String subType) {
    this.subType = subType;
  }

  public User getUser() {
    User user = (User) getSession().get("user");
    if (user == null) {
      user = new User();
      user.setBusinessId(UserUtil.getMmrUser().getBusinessId());
      setUser(user);
    }
    return user;
  }

  public void setUser(User user) {
    getSession().put("user", user);
  }

}
