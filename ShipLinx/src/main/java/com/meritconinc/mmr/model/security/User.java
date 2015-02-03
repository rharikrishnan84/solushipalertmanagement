package com.meritconinc.mmr.model.security;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.meritconinc.mmr.action.admin.UserListAction;
import com.meritconinc.mmr.constants.Constants;
import com.meritconinc.mmr.model.common.AuthorizedActionVO;
import com.meritconinc.mmr.model.common.LocaleVO;
import com.meritconinc.mmr.utilities.MessageUtil;
import com.meritconinc.mmr.utilities.StringUtil;
import com.meritconinc.mmr.utilities.WebUtil;
import com.meritconinc.shiplinx.model.Business;

public class User implements Serializable {
  private static final long serialVersionUID = 1L;
  private static final Logger log = LogManager.getLogger(UserListAction.class);

  private String username;
  private String password;
  private String retypePassword;
  private String firstName;
  private String lastName;
  private String email;
  private String status = Constants.STATUS_ACTIVE;
  private Timestamp lastLogin;
  private Timestamp createdAt;
  private String createdBy;
  private String lastModifiedBy;
  private Timestamp lastModifiedAt;
  private Timestamp passwordChangedAt;
  private String passwordChangeBy;
  private int accessTimes = 0;
  private String userComments;
  private String company;
  private String phoneNumber;
  private String phoneNumberExt;
  private String occupation;
  private int sessionTimeout;
  private int loginFailedCount;
  private String language;
  private boolean enabled;
  private String fax;
  private String subType;
  private String userRole;
  private String branch;
  private String userCode;
  private String logoURL;

  private Address address = new Address();
  private Collection<String> roles = null;
  private Collection<AuthorizedActionVO> authorizedActions = null;
  private long lastRefreshTime;
  private boolean acceptedTermsOfUse = false;
  private Date expDate;
  private String locale;
  private UserExtendedAttributes extendedAttributes;
  private long customerId;
  private long businessId;
  private Business business;
  private int defaultMenuId;

  private String timeZone;
  /**
   * Added a new Property for storing Commission Percentage
   * 
   * @return
   */
  private double commissionPercentage = 0;
  private double commissionPercentagePP = 0;
  private double commissionPercentagePS = 0;
  private double commissionPercentageCHB = 0;
  
  private double commissionPercentageFPA = 0;
  private double commissionPercentageFWD = 0;

  /**
   * Added properties for print configuration fpr a User
   */
  private int printNoOfLabels;
  private int printNoOfCI;
  private String preferredLabelSize;
  private boolean autoPrint = false;

  /**
   * Setting Default from and to address at User level.
   */
  private long defaultToAddressId;
  private long defaultFromAddressId;

  private String userGLOrRefNumber;

  // web only
  private String defaultToAddressText;
  private String defaultFromAddressText;
  private int unitmeasure;
  private String unitOfMeasure;

  public String getUnitOfMeasure() {
    return unitOfMeasure;
  }

  public void setUnitOfMeasure(String unitOfMeasure) {
    this.unitOfMeasure = unitOfMeasure;
  }

  public UserExtendedAttributes getExtendedAttributes() {
    return extendedAttributes;
  }

  public void setExtendedAttributes(UserExtendedAttributes extendedAttributes) {
    this.extendedAttributes = extendedAttributes;
  }

  public User() {
    accessTimes = 0;
    loginFailedCount = 0;
    sessionTimeout = 30;
    commissionPercentage = 0.0f;
  }

  public String getLocale() {
    return locale;
  }

  public void setLocale(String locale) {
    this.locale = locale;
  }

  public Date getExpDate() {
    return expDate;
  }

  public void setExpDate(Date expDate) {
    this.expDate = expDate;
  }

  public String getUserComments() {
    return userComments;
  }

  public void setUserComments(String institutionalSalesPerson) {
    this.userComments = institutionalSalesPerson;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getFirstName() {
    return (firstName == null) ? "" : firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getUsername() {
    log.debug("MODEL GET USERNAME ");
    return username;
  }

  public void setUsername(String username) {
    log.debug("MODEL SET USERNAME " + username);
    this.username = username;
  }

  public String getEmail() {
    return (email == null) ? "" : email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getLastName() {
    return (lastName == null) ? "" : lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getRetypePassword() {
    return retypePassword;
  }

  public void setRetypePassword(String retypePassword) {
    this.retypePassword = retypePassword;
  }

  public String getFullName() {
    return this.getFirstName() + " " + this.getLastName();
  }

  public Timestamp getLastLogin() {
    return lastLogin;
  }

  public void setLastLogin(Timestamp lastLogin) {
    this.lastLogin = lastLogin;
  }

  public int getAccessTimes() {
    return accessTimes;
  }

  public void setAccessTimes(int accessTimes) {
    this.accessTimes = accessTimes;
  }

  public Timestamp getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Timestamp createdAt) {
    this.createdAt = createdAt;
  }

  public String getCreatedBy() {
    return (createdBy == null) ? "" : createdBy;
  }

  public void setCreatedBy(String createdBy) {
    this.createdBy = createdBy;
  }

  public Timestamp getLastModifiedAt() {
    return lastModifiedAt;
  }

  public void setLastModifiedAt(Timestamp lastModifiedAt) {
    this.lastModifiedAt = lastModifiedAt;
  }

  public String getLastModifiedBy() {
    return (lastModifiedBy == null) ? "" : lastModifiedBy;
  }

  public void setLastModifiedBy(String lastModifiedBy) {
    this.lastModifiedBy = lastModifiedBy;
  }

  public String getPasswordChangeBy() {
    return (passwordChangeBy == null) ? "" : passwordChangeBy;
  }

  public void setPasswordChangeBy(String passwordChangeBy) {
    this.passwordChangeBy = passwordChangeBy;
  }

  public Timestamp getPasswordChangedAt() {
    return passwordChangedAt;
  }

  public void setPasswordChangedAt(Timestamp passwordChangedAt) {
    this.passwordChangedAt = new Timestamp(new Date().getTime());// passwordChangedAt;
  }

  /**
   * 
   * @return
   */
  public boolean isActiveUser() {
    if (getStatus().equals(Constants.STATUS_ACTIVE)) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * 
   * @return
   */
  public boolean isInactiveUser() {
    if (getStatus().equals(Constants.STATUS_INACTIVE)) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * 
   * @return
   */
  public boolean isUnapprovedUser() {
    if (getStatus().equals(Constants.STATUS_UNAPPROVED)) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * 
   * @return
   */
  public boolean isUserLocked() {
    if (getStatus().equals(Constants.STATUS_LOCKED)) {
      return true;
    } else {
      return false;
    }
  }

  public boolean isUserRejected() {
    if (getStatus().equals(Constants.STATUS_REJECTED)) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * 
   * @return
   */
  public boolean isNewUser() {
    if (StringUtil.isEmpty(this.getCreatedBy())) {
      return true;
    } else {
      return false;
    }
  }

  public boolean isSysAdmin() {
    return roles.contains(Constants.SYS_ADMIN_ROLE_CODE);
  }

  /**
   * @return the authorizedActions
   */
  public Collection<AuthorizedActionVO> getAuthorizedActions() {
    return authorizedActions;
  }

  /**
   * @param authorizedActions
   *          the authorizedActions to set
   */
  public void setAuthorizedActions(Collection<AuthorizedActionVO> authorizedActions) {
    this.authorizedActions = authorizedActions;
  }

  /**
   * @return the roles
   */
  public Collection<String> getRoles() {
    return roles;
  }

  /**
   * @param roles
   *          the roles to set
   */
  public void setRoles(Collection<String> roles) {
    this.roles = roles;
  }

  public String getCompany() {
    return (company == null) ? "" : company;
  }

  public void setCompany(String company) {
    this.company = company;
  }

  public String getOccupation() {
    return (occupation == null) ? "" : occupation;
  }

  public void setOccupation(String occupation) {
    this.occupation = occupation;
  }

  public String getPhoneNumber() {
    return (phoneNumber == null) ? "" : phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public Address getAddress() {
    return address;
  }

  public void setAddress(Address address) {
    this.address = address;
  }

  public boolean isAnonymousUser() {
    if (getUsername().equals("anonymous")) {
      return true;
    } else {
      return false;
    }
  }

  public String getPhoneNumberExt() {
    return (phoneNumberExt == null) ? "" : phoneNumberExt;
  }

  public void setPhoneNumberExt(String phoneNumberExt) {
    this.phoneNumberExt = phoneNumberExt;
  }

  public int getSessionTimeout() {
    return sessionTimeout;
  }

  public void setSessionTimeout(int sessionTimeout) {
    this.sessionTimeout = sessionTimeout;
  }

  /**
   * @return the lastRefreshTime
   */
  public long getLastRefreshTime() {
    return lastRefreshTime;
  }

  /**
   * @param lastRefreshTime
   *          the lastRefreshTime to set
   */
  public void setLastRefreshTime(long lastRefreshTime) {
    this.lastRefreshTime = lastRefreshTime;
  }

  /**
   * @return the acceptedTermsOfUse
   */
  public boolean isAcceptedTermsOfUse() {
    return acceptedTermsOfUse;
  }

  /**
   * @param acceptedTermsOfUse
   *          the acceptedTermsOfUse to set
   */
  public void setAcceptedTermsOfUse(boolean acceptedTermsOfUse) {
    this.acceptedTermsOfUse = acceptedTermsOfUse;
  }

  public int getLoginFailedCount() {
    return loginFailedCount;
  }

  public void setLoginFailedCount(int loginFailedCount) {
    this.loginFailedCount = loginFailedCount;
  }

  public String getLanguage() {
    List<LocaleVO> locales = MessageUtil.getLocales();
    for (int i = 0; i < locales.size(); i++) {
      LocaleVO localeVO = locales.get(i);
      String locale = localeVO.getLocale();
      if (this.locale.equals(locale)) {
        this.language = localeVO.getLocaleText();
      }
    }
    return language;
  }

  public String getDisplayLanguage() {
    List<LocaleVO> locales = MessageUtil.getLanguagesByLocale();
    for (int i = 0; i < locales.size(); i++) {
      LocaleVO localeVO = locales.get(i);
      String locale = localeVO.getLocale();
      if (this.locale.equals(locale)) {
        this.language = localeVO.getLocaleText();
      }
    }
    return language;
  }

  public void setLanguage(String language) {
    this.language = language;
  }

  public boolean isAccessExpired() {
    boolean isExpired = true;
    Date now = new Date();
    // user is not set expiration date or access is not expired yet
    if (getExpDate() == null || now.before(getExpDate())) {
      isExpired = false; // false, not expired
    }
    return isExpired;
  }

  public boolean isPasswordExpired() {

    boolean isExpired = true;
    // get expiry period (days) from properties
    String prop = WebUtil.getProperty(Constants.SYSTEM_SCOPE, Constants.PASSWORD_EXPIRES_DAYS);

    Date pwdChangedDate = getPasswordChangedAt();
    // if password_changed is NULL - it was reset by Admin
    log.debug("-------------------------" + pwdChangedDate);
    if (pwdChangedDate == null) {
      isExpired = true;
      return isExpired;
    }

    log.debug("-----------------222--------");
    int passwordExpiresDays = (prop == null) ? 10 : Integer.parseInt(prop);
    // if expiry period is set to ZERO - disable this feature
    if (passwordExpiresDays == 0) {
      isExpired = false;
    } else {
      Calendar cal = Calendar.getInstance();
      cal.setTime(new Date());
      cal.set(Calendar.DATE, cal.get(Calendar.DATE) - passwordExpiresDays);
      Date startDate = cal.getTime();
      if (pwdChangedDate.before(startDate)) {
        isExpired = true;
      } else {
        isExpired = false;
      }
    }
    return isExpired;
  }

  public String getFax() {
    return fax;
  }

  public void setFax(String fax) {
    this.fax = fax;
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

  /**
   * @return Returns the customerId.
   */
  public long getCustomerId() {
    return customerId;
  }

  /**
   * @param customerId
   *          The customerId to set.
   */
  public void setCustomerId(long customerId) {
    this.customerId = customerId;
  }

  /**
   * @return the businessId
   */
  public long getBusinessId() {
    return businessId;
  }

  /**
   * @param businessId
   *          the businessId to set
   */
  public void setBusinessId(long businessId) {
    this.businessId = businessId;
  }

  /**
   * @return the business
   */
  public Business getBusiness() {
    return business;
  }

  /**
   * @param business
   *          the business to set
   */
  public void setBusiness(Business business) {
    this.business = business;
  }

  public String getUserRole() {
    return userRole;
  }

  public void setUserRole(String userRole) {
    this.userRole = userRole;
  }

  public int getDefaultMenuId() {
    return defaultMenuId;
  }

  public void setDefaultMenuId(int defaultMenuId) {
    this.defaultMenuId = defaultMenuId;
  }

  public double getCommissionPercentage() {
    return commissionPercentage;
  }

  public void setCommissionPercentage(double commissionPercentage) {
    this.commissionPercentage = commissionPercentage;
  }

  public int getPrintNoOfLabels() {
    return printNoOfLabels;
  }

  public void setPrintNoOfLabels(int printNoOfLabels) {
    this.printNoOfLabels = printNoOfLabels;
  }

  public int getPrintNoOfCI() {
    return printNoOfCI;
  }

  public void setPrintNoOfCI(int printNoOfCI) {
    this.printNoOfCI = printNoOfCI;
  }

  public String getPreferredLabelSize() {
    return preferredLabelSize;
  }

  public void setPreferredLabelSize(String preferredLabelSize) {
    this.preferredLabelSize = preferredLabelSize;
  }

  public boolean isAutoPrint() {
    return autoPrint;
  }

  public void setAutoPrint(boolean autoPrint) {
    this.autoPrint = autoPrint;
  }

  public double getCommissionPercentagePP() {
    return commissionPercentagePP;
  }

  public void setCommissionPercentagePP(double commissionPercentagePP) {
    this.commissionPercentagePP = commissionPercentagePP;
  }

  public double getCommissionPercentagePS() {
    return commissionPercentagePS;
  }

  public void setCommissionPercentagePS(double commissionPercentagePS) {
    this.commissionPercentagePS = commissionPercentagePS;
  }

  public String getBranch() {
    return branch;
  }

  public void setBranch(String branch) {
    this.branch = branch;
  }

  public long getDefaultToAddressId() {
    return defaultToAddressId;
  }

  public void setDefaultToAddressId(long defaultToAddressId) {
    this.defaultToAddressId = defaultToAddressId;
  }

  public long getDefaultFromAddressId() {
    return defaultFromAddressId;
  }

  public void setDefaultFromAddressId(long defaultFromAddressId) {
    this.defaultFromAddressId = defaultFromAddressId;
  }

  public String getDefaultToAddressText() {
    return defaultToAddressText;
  }

  public void setDefaultToAddressText(String defaultToAddressText) {
    this.defaultToAddressText = defaultToAddressText;
  }

  public String getDefaultFromAddressText() {
    return defaultFromAddressText;
  }

  public void setDefaultFromAddressText(String defaultFromAddressText) {
    this.defaultFromAddressText = defaultFromAddressText;
  }

  public String getUserGLOrRefNumber() {
    return userGLOrRefNumber;
  }

  public void setUserGLOrRefNumber(String userGLOrRefNumber) {
    this.userGLOrRefNumber = userGLOrRefNumber;
  }

  public String getUserCode() {
    return userCode;
  }

  public void setUserCode(String userCode) {
    this.userCode = userCode;
  }

  public String getLogoURL() {
    return logoURL;
  }

  public void setLogoURL(String logoURL) {
    this.logoURL = logoURL;
  }

  public int getUnitmeasure() {
    return unitmeasure;
  }

  public void setUnitmeasure(int unitmeasure) {
    this.unitmeasure = unitmeasure;
  }

  public String getTimeZone() {
    return timeZone;
  }

  public void setTimeZone(String timeZone) {
    this.timeZone = timeZone;
  }

public double getCommissionPercentageCHB() {
	return commissionPercentageCHB;
}

public void setCommissionPercentageCHB(double commissionPercentageCHB) {
	this.commissionPercentageCHB = commissionPercentageCHB;
}

public double getCommissionPercentageFPA() {
	return commissionPercentageFPA;
}

public void setCommissionPercentageFPA(double commissionPercentageFPA) {
	this.commissionPercentageFPA = commissionPercentageFPA;
}

public double getCommissionPercentageFWD() {
	return commissionPercentageFWD;
}

public void setCommissionPercentageFWD(double commissionPercentageFWD) {
	this.commissionPercentageFWD = commissionPercentageFWD;
}

}
