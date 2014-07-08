package com.meritconinc.mmr.action.shipping;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.meritconinc.mmr.action.BaseAction;
import com.meritconinc.mmr.model.common.LocaleVO;
import com.meritconinc.mmr.model.security.User;
import com.meritconinc.mmr.service.UserService;
import com.meritconinc.mmr.utilities.MessageUtil;
import com.opensymphony.xwork2.Preparable;

/**
 * <code>Set welcome message.</code>
 */
public class RatingAction extends BaseAction implements Preparable, ServletRequestAware {

  private static final long serialVersionUID = 18052007;
  private static final Logger log = Logger.getLogger(RatingAction.class);

  private HttpServletRequest request;

  private UserService service;

  private String status = null;

  private User user;

  private String selectedUsername;

  private boolean displayMessage = false;
  // indicates when forward to 'Welcome' or 'Report' is needed from 'Change Password' page

  private List<LocaleVO> availableLocales;

  public void setAvailableLocales() {
    availableLocales = MessageUtil.getLanguagesByLocale();
  }

  public List<LocaleVO> getAvailableLocales() {
    return availableLocales;
  }

  public void setAvailableLocales(List<LocaleVO> availableLocales) {
    this.availableLocales = availableLocales;
  }

  public void setSelectedUsername(String selectedUsername) {
    this.selectedUsername = selectedUsername;

  }

  public RatingAction() {
  }

  /*
   * public String execute() throws Exception { return SUCCESS; }
   */

  public UserService getService() {
    return service;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public void setServletRequest(HttpServletRequest httpServletRequest) {
    this.request = httpServletRequest;
  }

  /**
   * 
   * @throws Exception
   */
  public void prepare() throws Exception {
    if (log.isDebugEnabled()) {
      log.debug("Prepare: " + this.selectedUsername);
    }

    if (user != null) {
      log.debug("username: " + user.getUsername());
      log.debug("Created By: " + user.getCreatedBy());
    }
    request.setAttribute("countries", MessageUtil.getCountriesList());

  }

  public String execute() throws Exception {
    return SUCCESS;
  }

  public void setService(UserService service) {
    this.service = service;
  }

  public List<LocaleVO> getLocales() {
    return MessageUtil.getLocales();
  }

  public boolean isDisplayMessage() {
    return displayMessage;
  }

  public void setDisplayMessage(boolean displayMessage) {
    this.displayMessage = displayMessage;
  }
}
