package com.meritconinc.mmr.service.impl;

import java.util.List;

import javax.mail.MessagingException;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.meritconinc.mmr.constants.Constants;
import com.meritconinc.mmr.dao.LoginTrackingDAO;
import com.meritconinc.mmr.dao.UserDAO;
import com.meritconinc.mmr.exception.EmailAlreadyRegisteredException;
import com.meritconinc.mmr.exception.InvalidPasswordException;
import com.meritconinc.mmr.exception.RepeatedPasswordException;
import com.meritconinc.mmr.exception.SameOldPasswordException;
import com.meritconinc.mmr.exception.UsernameAlreadyTakenException;
import com.meritconinc.mmr.model.admin.UserSearchCriteria;
import com.meritconinc.mmr.model.common.LocaleVO;
import com.meritconinc.mmr.model.common.LoginStatusVO;
import com.meritconinc.mmr.model.security.User;
import com.meritconinc.mmr.service.UserService;
import com.meritconinc.mmr.utilities.MessageUtil;
import com.meritconinc.mmr.utilities.StringUtil;
import com.meritconinc.mmr.utilities.WebUtil;
import com.meritconinc.mmr.utilities.mail.MailHelper;
import com.meritconinc.shiplinx.model.Customer;

public class UserServiceImpl implements UserService {

  private static final Logger log = Logger.getLogger(UserServiceImpl.class);

  UserDAO siteUserDAO;
  LoginTrackingDAO loginTrackingDao;

  public User login(String username, String password) {

    User user = siteUserDAO.login(username, password);

    if (user != null && user.isActiveUser()) {
      siteUserDAO.updateLastLoginInfo(username);
    }
    return user;
  }

  public void updateUserInfo(String userName) {
    siteUserDAO.updateLastLoginInfo(userName);
  }

  public User findUserByUsername(String username) {
    User user = siteUserDAO.findUserByUsername(username);
    return user;
  }

  public void changePassword(String username, String existingPassword, String newPassword,
      String changedBy, boolean isAdmin) throws InvalidPasswordException, SameOldPasswordException,
      RepeatedPasswordException {

    if (!siteUserDAO.isPasswordValid(username, existingPassword)) {
      throw new InvalidPasswordException("Invalid Password.");
    }

    if (existingPassword.equals(newPassword)) {
      throw new SameOldPasswordException("New Password is same as Old Password");
    }

    int pass_hist_count = Integer.valueOf(
        WebUtil.getProperty(Constants.SYSTEM_SCOPE, Constants.PASSWORD_HISTORY_COUNT)).intValue();
    if (siteUserDAO.isPasswordRepeated(username, newPassword, pass_hist_count)) {
      throw new RepeatedPasswordException("Repeated Password");
    }
    siteUserDAO.changePassword(username, newPassword, changedBy, isAdmin);
  }

  public String resetPassword(User user) {

    String newPassword = StringUtil.generateRandomString(8);
    siteUserDAO.changePassword(user.getUsername(), newPassword, Constants.SYSTEM_SCOPE, true);

    sendPasswordReminderEmail(user, newPassword);

    return newPassword;
  }

  public User findUserByEmail(String email) {
    return siteUserDAO.findUserByEmail(email);
  }

  /**
   * Ruchita
   * 
   * @param criteria
   * @return
   */

  public List<User> findUserByCustomer(UserSearchCriteria criteria) {
    return siteUserDAO.findUserByCustomer(criteria);
  }

  public void addRequestAccess(final User user) throws EmailAlreadyRegisteredException,
      UsernameAlreadyTakenException {

    boolean usernameRegistered = siteUserDAO.isUsernameRegistered(user.getUsername());
    if (usernameRegistered) {
      throw new UsernameAlreadyTakenException("Username already taken.");
    }

    boolean emailRegistered = siteUserDAO.isEmailRegistered(user.getEmail());
    if (emailRegistered) {
      throw new EmailAlreadyRegisteredException("Email already registered.");
    }

    siteUserDAO.create(user, user.getUsername());
    notifyAccessRequest(user);
  }

  protected String createNewAccessEmailBody(User user) {
    String address = user.getAddress().getAddressLine();
    String city = user.getAddress().getCity();
    String postalZip = user.getAddress().getPostalZip();
    String country = user.getAddress().getCountryCode();
    String province = user.getAddress().getStateProvince();
    String phoneExt = user.getPhoneNumberExt();

    String body = MessageUtil.getMessage("message.request.body", user.getLocale()) + " "
        + MessageUtil.getMessage("project.title", user.getLocale()) + " site.\n\n";
    body += MessageUtil.getMessage("label.user.name", user.getLocale()) + ": " + user.getUsername()
        + "\n";
    body += MessageUtil.getMessage("label.first.name", user.getLocale()) + ": "
        + user.getFirstName() + "\n";
    body += MessageUtil.getMessage("label.last.name", user.getLocale()) + ": " + user.getLastName()
        + "\n";
    body += MessageUtil.getMessage("label.company", user.getLocale()) + " " + user.getCompany()
        + "\n";
    body += MessageUtil.getMessage("label.occupation", user.getLocale()) + ": "
        + user.getOccupation() + "\n";
    body += MessageUtil.getMessage("label.email", user.getLocale()) + ": " + user.getEmail() + "\n";
    body += MessageUtil.getMessage("label.phone.number", user.getLocale()) + ": "
        + user.getPhoneNumber() + "\n";
    if (!StringUtil.isEmpty(phoneExt)) {
      body += MessageUtil.getMessage("label.phone.ext", user.getLocale()) + ": "
          + user.getPhoneNumberExt() + "\n";
    }

    body += MessageUtil.getMessage("label.user.comments", user.getLocale()) + ": "
        + user.getUserComments() + "\n";
    body += MessageUtil.getMessage("label.preferred.language", user.getLocale()) + ": "
        + user.getLanguage() + "\n";

    if (!StringUtil.isEmpty(country)) {
      body += MessageUtil.getMessage("label.country.code", user.getLocale()) + ": " + country
          + "\n";
    }
    if (!StringUtil.isEmpty(province)) {
      body += MessageUtil.getMessage("label.state.province", user.getLocale()) + ": " + province
          + "\n";
    }

    if (!StringUtil.isEmpty(address)) {
      body += MessageUtil.getMessage("label.address.line", user.getLocale()) + ": " + address
          + "\n";
    }
    if (!StringUtil.isEmpty(city)) {
      body += MessageUtil.getMessage("label.city", user.getLocale()) + ": " + city + "\n";
    }
    if (!StringUtil.isEmpty(postalZip)) {
      body += MessageUtil.getMessage("label.postal.zip", user.getLocale()) + ": " + postalZip
          + "\n";
    }
    body += "\n";
    return body;
  }

  /**
   * Notify the admin that there is a new access request
   * 
   * @param user
   */
  public void notifyAccessRequest(User user) {
    String sendFlag = WebUtil.getProperty("MAIL", "MAIL_SEND_ACCESS_FLAG");
    if (sendFlag == null || !sendFlag.equalsIgnoreCase("true")) {
      log.info("E-mail has been switched off.");
      return;
    }
    try {
      String host = WebUtil.getProperty("MAIL", "MAIL_SMTP_HOST");
      String fromAddress = WebUtil.getProperty("MAIL", "MAIL_FROM_ADDRESS");
      String locale = user.getLocale();
      String propertyName = "TO_ADDRESS_" + locale;
      String toAddress = WebUtil.getProperty("MAIL", propertyName);
      String subject = MessageUtil.getMessage("message.request.subject", user.getLocale());
      String body = createNewAccessEmailBody(user);
      MailHelper.sendEmailNow(host, fromAddress, toAddress, subject, body, null, null, null);
    } catch (MessagingException me) {
      log.info("Exception while sending e-mail: " + me, me);
    }
  }

  public void setSiteUserDAO(UserDAO siteUserDAO) {
    this.siteUserDAO = siteUserDAO;
  }

  public void create(final User user, final String changedBy)
      throws EmailAlreadyRegisteredException, UsernameAlreadyTakenException {

    boolean usernameRegistered = siteUserDAO.isUsernameRegistered(user.getUsername());
    if (usernameRegistered) {
      throw new UsernameAlreadyTakenException("Username already taken.");
    }

    // boolean emailRegistered = siteUserDAO.isEmailRegistered(user.getEmail());
    // if (emailRegistered) {
    // throw new EmailAlreadyRegisteredException("Email already registered.");
    // }

    siteUserDAO.create(user, changedBy);
  }

  // public void add(final User user)throws EmailAlreadyRegisteredException,
  // UsernameAlreadyTakenException {
  // log.debug("::::::::::::::::::::::getUsername:::"+user.getUsername());
  // boolean usernameRegistered = siteUserDAO.isUsernameRegistered(user.getUsername());
  // log.debug("::::::::::::::::::::::usernameRegistered:::"+usernameRegistered);
  // /*if(siteUserDAO.isUsernameRegistered(user.getUsername())) {
  // log.debug("::::::::::::::::::::::CustomerNameAlreadyTakenException:::"+user.getUsername());
  // throw new UsernameAlreadyTakenException(user.getUsername());
  // }*/
  // if (usernameRegistered) {
  // throw new UsernameAlreadyTakenException("Username already taken.");
  // }
  // siteUserDAO.add(user);
  // }

  public void save(final User user, final String changedBy) {
    // if (siteUserDAO.isEmailRegistered(user.getUsername(), user.getEmail())) {
    // throw new EmailAlreadyRegisteredException("Email already taken.");
    // }
    siteUserDAO.save(user, changedBy);
  }

  public void saveProfile(final User user, final String changedBy)
      throws EmailAlreadyRegisteredException {
    if (siteUserDAO.isEmailRegistered(user.getUsername(), user.getEmail())) {
      throw new EmailAlreadyRegisteredException("Email already taken.");
    }
    siteUserDAO.saveProfile(user, changedBy);
  }

  public List<User> findUsers(UserSearchCriteria criteria, int startIndex, int endIndex) {
    List<User> users = siteUserDAO.findUsers(criteria, startIndex, endIndex);
    if (log.isDebugEnabled()) {
      log.debug("size: " + users.size());
    }
    return users;
  }

  public int findDataRowsCount(UserSearchCriteria criteria) {
    return siteUserDAO.findDataRowsCount(criteria);
  }

  public int findUsersByCustomer(UserSearchCriteria criteria) {
    return siteUserDAO.findDataRowsCount(criteria);
  }

  public void delete(String username) {
    siteUserDAO.delete(username);
  }

  // public void edit(User user) {
  // siteUserDAO.edit(user);
  // }
  //
  public boolean isAccountLockedByRetries(String username, boolean loginFailed) {
    return siteUserDAO.isAccountLockedByRetries(username, loginFailed);
  }

  public void setSessionTimeout(User user, HttpSession session) {
    // obtain the user's defined timeout (if one has been set)
    int timeout = user.getSessionTimeout();
    if (timeout <= 0) {
      timeout = Integer.parseInt(WebUtil.getProperty(Constants.SYSTEM_SCOPE, Constants.TIMEOUT));
    }

    if (log.isDebugEnabled()) {
      log.debug("setting session timeout to " + timeout + " (min) for user: " + user.getUsername());
    }
    // set session timeout for the user
    session.setMaxInactiveInterval(timeout * 60);
  }

  public void sendApprovedEmail(User user) {
    String host = WebUtil.getProperty("MAIL", "MAIL_SMTP_HOST");

    // GROUP_EMAIL_ADDRESS_en_CA
    String locale = user.getLocale();
    String propertyName = "GROUP_EMAIL_ADDRESS_" + locale;
    String fromAddress = WebUtil.getProperty("MAIL", propertyName);
    String toAddress = user.getEmail();

    String subject = MessageUtil.getMessage("message.approve.status.subject", locale);
    String body = MessageUtil.getMessage("message.approve.status.message", locale);
    body += "\n\n";

    try {
      MailHelper.sendEmailNow2("smtp.pathcom.com", "info@eshipper.com", "Ofnipath!@", user
          .getBusiness().getName(), 587, "riz@shaw.ca", "rizwan.merchant@meritconinc.com", null,
          subject, body, null, true);
    } catch (MessagingException e) {
      log.error("Error sending email: " + e.getMessage());
    }

  }

  private void sendPasswordReminderEmail(User user, String newPassword) {

    // GROUP_EMAIL_ADDRESS_en_CA
    String locale = user.getLocale();

    String subject = MessageUtil.getMessage("message.forgot.password.subject", locale);
    subject = new String(subject.replaceAll("%BUSINESSNAME", user.getBusiness().getName()));

    String body = MessageUtil.getMessage("message.forgot.password.body", locale);

    body = new String(body.replaceAll("%BUSINESSNAME", user.getBusiness().getName()));
    body = new String(body.replaceAll("%LOGINURL", user.getBusiness().getLogoutURL()));
    body = new String(body.replaceAll("%USERNAME", user.getUsername()));
    body = new String(body.replaceAll("%PASSWORD", newPassword));

    try {
      MailHelper.sendEmailNow2(user.getBusiness().getSmtpHost(), user.getBusiness()
          .getSmtpUsername(), user.getBusiness().getSmtpPassword(), user.getBusiness().getName(),
          user.getBusiness().getSmtpPort(), user.getBusiness().getAddress().getEmailAddress(), user
              .getEmail(), null, subject, body, null, true);
    } catch (MessagingException e) {
      log.error("Error sending email: " + e.getMessage());
    }

  }

  public void updateLoginFailedCountZero(String userName) {
    siteUserDAO.updateLoginFailedCountZero(userName);
  }

  public void saveLoginAttempt(LoginStatusVO loginStatusVO) {
    loginTrackingDao.saveLoginAttempt(loginStatusVO);
  }

  public void setLoginTrackingDao(LoginTrackingDAO loginTrackingDao) {
    this.loginTrackingDao = loginTrackingDao;
  }

  public User findUserByCode(String userCode) {
    User user = siteUserDAO.findUserByUserCode(userCode);
    return null;
  }

  public List<LocaleVO> findLocale() {
    return (List<LocaleVO>) siteUserDAO.findLocale();
  }
  
  public Customer getCustomerReference(long id){
	  	  return (Customer)siteUserDAO.getCustomerReference(id);
	    }
}
