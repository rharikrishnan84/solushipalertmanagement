package com.meritconinc.mmr.dao.ibatis;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.meritconinc.mmr.constants.Constants;
import com.meritconinc.mmr.dao.UserDAO;
import com.meritconinc.mmr.dao.UserExtensionDAO;
import com.meritconinc.mmr.model.admin.UserSearchCriteria;
import com.meritconinc.mmr.model.common.AuthorizedActionVO;
import com.meritconinc.mmr.model.common.LocaleVO;
import com.meritconinc.mmr.model.security.Address;
import com.meritconinc.mmr.model.security.User;
import com.meritconinc.mmr.model.security.UserExtendedAttributes;
import com.meritconinc.mmr.utilities.MmrBeanLocator;
import com.meritconinc.mmr.utilities.StringUtil;
import com.meritconinc.mmr.utilities.WebUtil;
import com.meritconinc.mmr.utilities.security.UserUtil;
import com.meritconinc.shiplinx.model.Customer;
import com.meritconinc.shiplinx.model.UnitOfMeasure;
import com.meritconinc.shiplinx.utils.ShiplinxConstants;
import com.meritconinc.mmr.dao.MenusDAO;
import com.meritconinc.mmr.model.common.RoleVO;

public class UserDAOImpl extends SqlMapClientDaoSupport implements UserDAO {
  private UserExtensionDAO userExtensionDAO;
  private static final Logger log = LogManager.getLogger(UserDAOImpl.class);
  private MenusDAO menuItemDAO;
  public void changePassword(String username, String newPassword, String changedBy, boolean isAdmin) {
    Date passwordChangedDate = (isAdmin && !username.equals(changedBy)) ? null : new Date();
    updatePassword(username, newPassword, changedBy, passwordChangedDate);
    insertUserPasswordChangeHistory(username, newPassword);
  }

  public void copyUserToHistory(String username) {
    Map<String, Object> paramObj = new HashMap<String, Object>();
    paramObj.put("username", username);
    getSqlMapClientTemplate().insert("insertUserHistory", paramObj);
    // userExtensionDAO.copyExtendedAttributesToHistory(username, userHistoryId);
  }

  public void create(User user, String changedBy) {
    String username = user.getUsername().trim();
    Map<String, Object> paramObj = new HashMap<String, Object>();
    paramObj.put("username", username);
    paramObj.put("password", digest(user.getPassword()));

    if (user.getUsername().equalsIgnoreCase(changedBy)) {
      paramObj.put("password_changed", new Date());
    } else {
      paramObj.put("password_changed", null); // it is going to be changed by user later
    }
    paramObj.put("first_name", user.getFirstName());
    paramObj.put("last_name", user.getLastName());
    paramObj.put("email", user.getEmail());
    paramObj.put("account_status", user.getStatus());
    paramObj.put("user_comments", user.getUserComments());
    paramObj.put("created_by", changedBy);
    if (user != null && user.getTimeZone() != null && !user.getTimeZone().isEmpty()) {
      Calendar cal = Calendar.getInstance(TimeZone.getTimeZone(user.getTimeZone()));
      SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
      SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
      dateFormat.setTimeZone(cal.getTimeZone());
      timeFormat.setTimeZone(cal.getTimeZone());
      paramObj.put("created", dateFormat.format(cal.getTime()));
    } else {
      paramObj.put("created", Calendar.getInstance().getTime());
    }
    paramObj.put("company", user.getCompany());
    paramObj.put("phone_number", user.getPhoneNumber());
    paramObj.put("phone_number_ext", user.getPhoneNumberExt());
    paramObj.put("occupation", user.getOccupation());
    paramObj.put("address_line", user.getAddress().getAddressLine());
    paramObj.put("city", user.getAddress().getCity());
    paramObj.put("state_province", user.getAddress().getStateProvince());
    paramObj.put("country_code", user.getAddress().getCountryCode());
    paramObj.put("postal_zip", user.getAddress().getPostalZip());
    paramObj.put("session_timeout", user.getSessionTimeout());
    paramObj.put("locale", user.getLocale());
    paramObj.put("expiration_date", user.getExpDate());
    paramObj.put("password_changed", new Date());// should remove after password change
                                                 // functionality
    if(user.getBusinessId()> 0) {
    	    	    	    	    	  // functionality
    	    	    	    	    	  paramObj.put("businessId", user.getBusinessId());
    	    	    	    	      }else{
    	    	    	    	    	  paramObj.put("businessId", UserUtil.getMmrUser().getBusinessId());
    	    	    	    	      }
    
    
    paramObj.put("accesstimes", user.getAccessTimes());
    paramObj.put("loginfailedcount", user.getLoginFailedCount());
    paramObj.put("enabled", user.getEnabled());
    paramObj.put("customerId", user.getCustomerId());
    paramObj.put("commisionPercentagePerCHB", user.getCommissionPercentageCHB());
    paramObj.put("commissionPercentagePerPalletService", user.getCommissionPercentagePP());
    paramObj.put("commissionPercentagePerSkidService", user.getCommissionPercentagePS());

   /* if (user.getCustomerId() > 0)
      paramObj.put("default_menuId", ShiplinxConstants.MENU_ID_NEW_SHIPMENT_PAGE); // for customer
    else
      paramObj.put("default_menuId", ShiplinxConstants.MENU_ID_CUSTOMER_SEARCH); // for bus admin
*/
    paramObj.put("printNoOfLabels", user.getPrintNoOfLabels());
    paramObj.put("printNoOfCI", user.getPrintNoOfCI());
    paramObj.put("preferredLabelSize", user.getPreferredLabelSize());
    paramObj.put("autoPrint", user.isAutoPrint());
    paramObj.put("fromAddressId", user.getDefaultFromAddressId());
    paramObj.put("toAddressId", user.getDefaultToAddressId());
    paramObj.put("userGLOrRefNumber", user.getUserGLOrRefNumber());
    paramObj.put("userCode", user.getUserCode());
    paramObj.put("logoURL", user.getLogoURL());
    paramObj.put("unitmeasure", user.getUnitmeasure());
    paramObj.put("timeZone", user.getTimeZone());
    paramObj.put("partnerLevel", user.isPartnerLevel());
    paramObj.put("nationLevel", user.isNationLevel());
    paramObj.put("businessLevel",user.isBusinessLevel());
    paramObj.put("branchLevel",user.isBranchLevel());
    paramObj.put("divitionLevel", user.isDivitionLevel());
    
    //Division level filter
    paramObj.put("spdEnabled", user.isSpdEnabled());
    paramObj.put("ltlEnabled", user.isLtlEnabled());
    paramObj.put("fpaEnabled", user.isFpaEnabled());
    paramObj.put("chbEnabled", user.isChbEnabled());
    paramObj.put("fwdEnabled", user.isFwdEnabled());
    
  //For Summary Label
        paramObj.put("summaryLabel",user.isSummaryLabel());
    
    if (user.getCustomerId() > 0)
        paramObj.put("default_menuId", ShiplinxConstants.MENU_ID_NEW_SHIPMENT_PAGE); // for customer
      else{
               /* paramObj.put("default_menuId", ShiplinxConstants.MENU_ID_CUSTOMER_SEARCH); // for bus admin
*/		
           if(user.getUserRole()!=null && user.getUserRole().equals(ShiplinxConstants.ROLE_BUSINESSADMIN) && user.isBusinessLevel()){
        	 paramObj.put("default_menuId",menuItemDAO.getMenuIdByUrl("/admin/list.partner.action"));
            	
            }else if(user.getUserRole()!=null && user.getUserRole().equals(ShiplinxConstants.ROLE_SYSADMIN)){
       	   paramObj.put("default_menuId",menuItemDAO.getMenuIdByUrl("/admin/list.business.action"));
          }else if(user.isPartnerLevel()){
        	   paramObj.put("default_menuId",menuItemDAO.getMenuIdByUrl("/admin/list.countrypartner.action"));
          }else if(user.isNationLevel()){
        	   paramObj.put("default_menuId",menuItemDAO.getMenuIdByUrl("/admin/list.branch.action"));
           }else if(user.isBranchLevel()){
        	           	   paramObj.put("default_menuId",menuItemDAO.getMenuIdByUrl("/admin/searchcustomer.action"));
           }
      }
    getSqlMapClientTemplate().insert("createUser", paramObj);
    userExtensionDAO.insertExtendedAttributes(username, user.getExtendedAttributes());
    copyUserToHistory(username);
    insertRole(username, user.getUserRole());
    // insertUserRolesHistory(username, userHistoryId);
  }

  // public void add(User user) {
  // Map<String, Object> paramObj = new HashMap<String, Object>();
  // paramObj.put("username", user.getUsername());
  // paramObj.put("password", digest(user.getPassword()));
  // paramObj.put("fax", user.getFax());
  // paramObj.put("phone_number", user.getPhoneNumber());
  // paramObj.put("email", user.getEmail());
  // paramObj.put("enabled", user.getEnabled());
  // paramObj.put("subtype", user.getSubType());
  // paramObj.put("accesstimes", user.getAccessTimes());
  // paramObj.put("loginfailedcount", user.getLoginFailedCount());
  // paramObj.put("sessiontimeout", user.getSessionTimeout());
  // paramObj.put("customerId", user.getCustomerId());
  // paramObj.put("created", Calendar.getInstance().getTime());
  // paramObj.put("locale", user.getLocale());
  // paramObj.put("account_status", user.getStatus());
  // paramObj.put("businessId", UserUtil.getMmrUser().getBusinessId());
  // if(user.getSubType().equals("true"))
  // paramObj.put("role", "admin");
  // else
  // paramObj.put("role", "public"); //paramObj.put("role", "user"); //currently public role as user
  // role
  //
  // try{
  // getSqlMapClientTemplate().insert("addUser", paramObj);
  // getSqlMapClientTemplate().insert("insertRole",paramObj);
  // }catch (Exception e) {
  // log.error("Error while adding the user.");
  // }
  //
  // }

  public int findDataRowsCount(UserSearchCriteria criteria) {
    Map<String, Object> paramObj = new HashMap<String, Object>(6);
    paramObj.put("username", removeQuote(criteria.getUsername()));
    paramObj.put("businessId", criteria.getBusinessId());
    Long userCount = (Long) getSqlMapClientTemplate().queryForObject("findDataRowsCount", paramObj);
    return userCount.intValue();
  }

  /**
   * Ruchita
   * 
   * @param criteria
   * @return
   */
  public List<User> findUserByCustomer(UserSearchCriteria criteria) {
    Long customerid = criteria.getCustomerId();
    Map<String, Object> paramObj = new HashMap<String, Object>(1);
    if(criteria.getCustomerIds()!=null && criteria.getCustomerIds().size()>0){
    	    	    	    	paramObj.put("customerIds", criteria.getCustomerIds());
    	    	    	    }else{
    	    	    	    	paramObj.put("customerId", customerid);
    	    	    	    }
    paramObj.put("businessId", criteria.getBusinessId());
    paramObj.put("branch", criteria.getBranch());
    List<User> user = (List<User>) getSqlMapClientTemplate().queryForList("findUserByCustomerId",
        paramObj);
    return user;

  }

  public void delete(String username) {
    Map<String, Object> paramObj = new HashMap<String, Object>(2);
    paramObj.put("username", username);
    paramObj.put("businessId", UserUtil.getMmrUser().getBusinessId());
    getSqlMapClientTemplate().delete("deleteRole", paramObj);
    getSqlMapClientTemplate().delete("deleteUser", paramObj);
  }

  // public void edit(User user) {
  // Map<String, Object> paramObj = new HashMap<String, Object>(1);
  // paramObj.put("username", user.getUsername());
  // paramObj.put("email", user.getEmail());
  // paramObj.put("phoneNumber", user.getPhoneNumber());
  // paramObj.put("password",digest(user.getPassword()));
  // paramObj.put("fax", user.getFax());
  // paramObj.put("enabled", user.getEnabled());
  // paramObj.put("subtype", user.getSubType());
  //
  // if(user.getSubType().equals("true"))
  // paramObj.put("role", "admin");
  // else
  // paramObj.put("role", "public"); //paramObj.put("role", "user"); //currently public role as user
  // role
  //
  // paramObj.put("account_status", user.getEnabled()?Constants.STATUS_ACTIVE :
  // Constants.STATUS_INACTIVE);
  //
  //
  // getSqlMapClientTemplate().update("userupdate", paramObj);
  // getSqlMapClientTemplate().insert("updateRole",paramObj);
  // }

  public List<String> readUserRoles(String username) {
    Map<String, Object> paramObj = new HashMap<String, Object>(1);
    paramObj.put("username", username);
    // paramObj.put("businessId", UserUtil.getMmrUser().getBusinessId());
    List<String> users = (List<String>) getSqlMapClientTemplate().queryForList("readUserRoles",
        paramObj);
    return users;
  }

  public User findUserByEmail(String email) {
    email = this.removeQuote(email);
    Map<String, Object> paramObj = new HashMap<String, Object>(1);
    paramObj.put("email", email);
    paramObj.put("businessId", UserUtil.getMmrUser().getBusinessId());
    User user = (User) getSqlMapClientTemplate().queryForObject("findUserByEmail", paramObj);
    if (user != null) {
      user.setRoles(readUserRoles(user.getUsername()));
      user.setAuthorizedActions(readAuthorizedActions(user.getUsername()));
      user.setLastRefreshTime(Calendar.getInstance().getTimeInMillis());
      UserExtendedAttributes ea = userExtensionDAO.getExtendedAttributesByUsername(user
          .getUsername());
      user.setExtendedAttributes(ea);
    }
    return user;
  }

  public User findUserByUsername(String username) {
    username = this.removeQuote(username);
    Map<String, Object> paramObj = new HashMap<String, Object>(1);
    paramObj.put("username", username);
    UserDAO userDAO = (UserDAO) MmrBeanLocator.getInstance().findBean("userDAO");
    User unitofmeasure = userDAO.findunitofmeasure(username);
    // paramObj.put("businessId", UserUtil.getMmrUser().getBusinessId());
    User user = (User) getSqlMapClientTemplate().queryForObject("findUserByUserName", paramObj);
    Address address = (Address) getSqlMapClientTemplate().queryForObject("address", paramObj);
    UserExtendedAttributes ea = userExtensionDAO.getExtendedAttributesByUsername(username);
    if (user != null) {
      user.setRoles(readUserRoles(username));
      // Our application does not support multiple user roles for a user, but due to code we have to
      // handle the roles as a list (of size 1)
      user.setUserRole(user.getRoles().iterator().next());
      user.setAuthorizedActions(readAuthorizedActions(username));
      user.setLastRefreshTime(Calendar.getInstance().getTimeInMillis());
      user.setAddress(address);
      user.setExtendedAttributes(ea);
      user.setUnitmeasure(unitofmeasure.getUnitmeasure());
    }
    return user;
  }

  public List<User> findUsers(UserSearchCriteria criteria, int start, int end) {
    int skip = start - 1;
    log.debug("Test findUsers");
    log.debug(skip);
    Map<String, Object> paramObj = new HashMap<String, Object>(9);
    String orderBy = criteria.getOrderBy();
    log.debug(orderBy);
    if (orderBy.equals(UserSearchCriteria.SORT_BY_USERNAME)) {
      criteria.setOrderedBy("u.username");
    } else if (orderBy.equals(UserSearchCriteria.SORT_BY_FIRSTNAME)) {
      criteria.setOrderedBy("u.first_name");
    }

    criteria.setOrderedBy(orderBy);
    // paramObj.put("current_page_range", currentPageRange);
    List<User> users = (List<User>) getSqlMapClientTemplate().queryForList("findUsersPaginated",
        criteria);
    return users;
  }

  public boolean isAccountLockedByRetries(String username, boolean loginFailed) {
    // Login Account
    boolean isLocked = true;
    String accountStatus = accountStatus(username);

    if (accountStatus != null && !accountStatus.trim().equals("")) {
      if (loginFailed) {
        updateLoginFailedCount(username);
      }

      if (accountStatus.equalsIgnoreCase(Constants.STATUS_LOCKED)) {
        isLocked = true;
      } else {
        int loginFailedCount = loginFailedCount(username);
        int loginRetry = Integer.parseInt(WebUtil.getProperty(Constants.SYSTEM_SCOPE,
            Constants.LOGIN_RETRY));
        if (loginFailedCount >= loginRetry) {
          // set account_status to Locked (L) ONLY if it is Active (A)
          if (accountStatus.equalsIgnoreCase(Constants.STATUS_ACTIVE)) {
            updateAccountStatus(username, Constants.STATUS_LOCKED);
            isLocked = true;
          } else {
            isLocked = false;
          }
        } else {
          isLocked = false;
        }
      }
    } else {
      isLocked = false;
    }

    return isLocked;
  }

  public boolean isEmailRegistered(String username, String email) {
    Map<String, Object> paramObj = new HashMap<String, Object>(2);
    paramObj.put("username", username);
    paramObj.put("email", email);
    Long isUserRegistered = (Long) getSqlMapClientTemplate().queryForObject("isEmailRegistered",
        paramObj);
    return (isUserRegistered.intValue() == 0) ? false : true;
  }

  public boolean isEmailRegistered(String email) {
    Map<String, Object> paramObj = new HashMap<String, Object>(1);
    paramObj.put("email", email);
    Integer count = (Integer) getSqlMapClientTemplate().queryForObject("isEmailRegistered2",
        paramObj);
    return (count > 0);
  }

  public boolean isUsernameRegistered(String username) {
    Map<String, Object> paramObj = new HashMap<String, Object>(1);
    paramObj.put("username", username);
    Integer count = (Integer) getSqlMapClientTemplate().queryForObject("isUsernameRegistered",
        paramObj);
    return (count > 0);
  }

  public User login(String username, String password) {
    Map<String, Object> paramObj = new HashMap<String, Object>(2);
    paramObj.put("username", username);
    paramObj.put("password", digest(password));
    User user = (User) getSqlMapClientTemplate().queryForObject("login", paramObj);
    if (user != null) {
      user = findUserByUsername(user.getUsername());
    }
    return user;
  }

  public Collection<AuthorizedActionVO> readAuthorizedActions(String username) {
    Map<String, Object> paramObj = new HashMap<String, Object>(1);
    paramObj.put("username", username);
    // paramObj.put("businessId", UserUtil.getMmrUser().getBusinessId());
    Collection<AuthorizedActionVO> actions = (Collection<AuthorizedActionVO>) getSqlMapClientTemplate()
        .queryForList("readAuthorizedActions", paramObj);
    return actions;
  }

  public void save(User user, String changedBy) {
    // reset 'login_failed_count' if status was changed from 'Locked' to
    // 'Active'
    String username = user.getUsername();
    String statusNow = accountStatus(username);
    /*
     * if(statusNow.equals(Constants.STATUS_LOCKED) && (user.isActiveUser() ||
     * user.isInactiveUser())) { updateLoginFailedCountZero(user.getUsername()); }
     */

    if (!statusNow.equals(Constants.STATUS_ACTIVE) && (user.isActiveUser())) {
      updateLoginFailedCountZero(user.getUsername());
    }

    updateUser(user, changedBy);

    // String sendApprovalFlag = WebUtil.getProperty("MAIL", "MAIL_SEND_APPROVAL_FLAG");
    // if (sendApprovalFlag == null
    // || !sendApprovalFlag.equalsIgnoreCase("true")) {
    // // do nothing
    //
    // } else {
    // // if status changes from Unapproved to Approved
    // // send email to user
    // if (statusNow.equals(Constants.STATUS_UNAPPROVED)) {
    // String userStatus = user.getStatus();
    // if (userStatus.equals(Constants.STATUS_ACTIVE)) {
    // MmrBeanLocator beanLocator = MmrBeanLocator.getInstance();
    // UserService userService = (UserService)beanLocator.findBean("userService");
    //
    // userService.sendApprovedEmail(user);
    // }
    // }
    // }

    if (!StringUtil.isEmpty(user.getPassword())) {
      // this is Administrator changing password, set password_changed to
      // NULL
      changePassword(username, user.getPassword(), changedBy, true);
    }
    // copy updated user data to USER_HISTORY
    copyUserToHistory(username);

    // if user roles have changed
    // if ( !ArrayUtil.areSameElements(user.getRoles(), readUserRoles(user.getUsername()))) {
    // Only 1 role is allowed per user, but the roles are read into a collection
    if (!(readUserRoles(user.getUsername()).contains(user.getUserRole()))) {
      // updating user roles in USER_ROLE
      deleteUserRole(username);
      insertRole(username, user.getUserRole());
      // insertUserRolesHistory(username, userHistoryId);
    }
  }

  public void saveProfile(User user, String changedBy) {
    // only update fields rendered by Update Profile
    updateProfile(user, changedBy);

    // copy updated user data to USER_HISTORY
    copyUserToHistory(user.getUsername());
  }

  public void updateLastLoginInfo(String username) {
    Map<String, Object> paramObj = new HashMap<String, Object>(1);
    paramObj.put("username", username);
    paramObj.put("businessId", UserUtil.getMmrUser().getBusinessId());
    paramObj.put("last_login", new Date());
    getSqlMapClientTemplate().update("updateLastLoginInfo", paramObj);
  }

  public void updateUser(User user, String changedBy) {
    Map<String, Object> paramObj = new HashMap<String, Object>();
    paramObj.put("first_name", user.getFirstName());
    paramObj.put("last_name", user.getLastName());
    paramObj.put("email", user.getEmail());
    paramObj.put("account_status", user.getStatus());
    paramObj.put("user_comments", user.getUserComments());
    paramObj.put("last_modified_by", changedBy);
    paramObj.put("last_modified", new Date());
    paramObj.put("company", user.getCompany());
    paramObj.put("phone_number", user.getPhoneNumber());
    paramObj.put("occupation", user.getOccupation());
    paramObj.put("address_line", user.getAddress().getAddressLine());
    paramObj.put("city", user.getAddress().getCity());
    paramObj.put("state_province", user.getAddress().getStateProvince());
    paramObj.put("country_code", user.getAddress().getCountryCode());
    paramObj.put("postal_zip", user.getAddress().getPostalZip());
    paramObj.put("session_timeout", user.getSessionTimeout());
    paramObj.put("phone_number_ext", user.getPhoneNumberExt());
    paramObj.put("username", user.getUsername());
    paramObj.put("locale", user.getLocale());
    paramObj.put("expDate", user.getExpDate());
    paramObj.put("commisionPercentagePerCHB", user.getCommissionPercentageCHB());
    paramObj.put("commissionPercentagePerPalletService", user.getCommissionPercentagePP());
    paramObj.put("commissionPercentagePerSkidService", user.getCommissionPercentagePS());
    // print setup configuration
    paramObj.put("printNoOfLabels", user.getPrintNoOfLabels());
    paramObj.put("printNoOfCI", user.getPrintNoOfCI());
    paramObj.put("preferredLabelSize", user.getPreferredLabelSize());
    paramObj.put("autoPrint", user.isAutoPrint());
    paramObj.put("fromAddressId", user.getDefaultFromAddressId());
    paramObj.put("toAddressId", user.getDefaultToAddressId());
    paramObj.put("userGLOrRefNumber", user.getUserGLOrRefNumber());
    paramObj.put("userCode", user.getUserCode());
    paramObj.put("logoURL", user.getLogoURL());
    paramObj.put("unitmeasure", user.getUnitmeasure());
    paramObj.put("timeZone", user.getTimeZone());
  //division level user update
        paramObj.put("spdEnabled", user.isSpdEnabled());
        paramObj.put("ltlEnabled", user.isLtlEnabled());
        paramObj.put("fpaEnabled", user.isFpaEnabled());
        paramObj.put("chbEnabled", user.isChbEnabled());
        paramObj.put("fwdEnabled", user.isFwdEnabled());
      //for summary label
            paramObj.put("summaryLabel", user.isSummaryLabel()); 
    getSqlMapClientTemplate().update("updateUser", paramObj);
    UserExtendedAttributes ea = userExtensionDAO
        .getExtendedAttributesByUsername(user.getUsername());
    if (ea == null) {
      userExtensionDAO.insertExtendedAttributes(user.getUsername(), user.getExtendedAttributes());
    } else {
      userExtensionDAO.updateExtendedAttributes(user.getUsername(), user.getExtendedAttributes());
    }
  }

  private void updateProfile(User user, String changedBy) {
    Map<String, Object> paramObj = new HashMap<String, Object>();
    paramObj.put("first_name", user.getFirstName());
    paramObj.put("last_name", user.getLastName());
    paramObj.put("email", user.getEmail());
    paramObj.put("user_comments", user.getUserComments());
    paramObj.put("last_modified_by", changedBy);
    paramObj.put("last_modified", new Date());
    paramObj.put("company", user.getCompany());
    paramObj.put("phone_number", user.getPhoneNumber());
    paramObj.put("occupation", user.getOccupation());
    paramObj.put("address_line", user.getAddress().getAddressLine());
    paramObj.put("city", user.getAddress().getCity());
    paramObj.put("state_province", user.getAddress().getStateProvince());
    paramObj.put("country_code", user.getAddress().getCountryCode());
    paramObj.put("postal_zip", user.getAddress().getPostalZip());
    paramObj.put("phone_number_ext", user.getPhoneNumberExt());
    paramObj.put("username", user.getUsername());
    getSqlMapClientTemplate().update("updateProfile", paramObj);
    UserExtendedAttributes ea = userExtensionDAO
        .getExtendedAttributesByUsername(user.getUsername());
    if (ea == null) {
      userExtensionDAO.insertExtendedAttributes(user.getUsername(), user.getExtendedAttributes());
    } else {
      userExtensionDAO.updateExtendedAttributes(user.getUsername(), user.getExtendedAttributes());
    }
  }

  public void deleteUserRole(String username) {
    Map<String, Object> paramObj = new HashMap<String, Object>();
    paramObj.put("username", username);
    getSqlMapClientTemplate().delete("deleteRole", paramObj);
  }

  public void insertRole(String username, String role) {
    Map<String, Object> paramObj = new HashMap<String, Object>();
    paramObj.put("username", username);
    paramObj.put("role", role);
    getSqlMapClientTemplate().insert("insertRole", paramObj);
  }

  public String digest(String original) {
    String digested = StringUtil.isEmpty(original) ? "" : DigestUtils.md5Hex(original);
    return digested;
  }

  public void insertUserRolesHistory(String username, int id) {
    Map<String, Object> paramObj = new HashMap<String, Object>();
    paramObj.put("username", username);
    paramObj.put("user_history_id", new Integer(id));
    getSqlMapClientTemplate().insert("insertUserRolesHistory", paramObj);
  }

  public void updatePassword(String username, String password, String changedBy,
      Date passwordChanged) {
    Map<String, Object> paramObj = new HashMap<String, Object>();
    paramObj.put("username", username);
    paramObj.put("password", digest(password));
    paramObj.put("password_changed", passwordChanged);
    paramObj.put("changed_by", changedBy);
    getSqlMapClientTemplate().update("updatePassword", paramObj);
  }

  public void insertUserPasswordChangeHistory(String username, String password) {
    Map<String, Object> paramObj = new HashMap<String, Object>();
    paramObj.put("username", username);
    paramObj.put("password", digest(password));
    paramObj.put("created", new Date());
    getSqlMapClientTemplate().insert("passwordChangeHistory", paramObj);
  }

  public boolean isPasswordValid(String username, String password) {
    Map<String, Object> paramObj = new HashMap<String, Object>();
    paramObj.put("username", username);
    paramObj.put("password", digest(password));
    Integer b = (Integer) getSqlMapClientTemplate().queryForObject("isPasswordValid", paramObj);
    return (b.intValue() == 0) ? false : true;
  }

  public boolean isPasswordRepeated(String username, String password, int pass_hist_count) {
    Map<String, Object> paramObj = new HashMap<String, Object>();
    paramObj.put("username", username);
    paramObj.put("password", digest(password));
    paramObj.put("password_count", pass_hist_count);
    Integer b = (Integer) getSqlMapClientTemplate().queryForObject("isPasswordRepeated", paramObj);
    return (b.intValue() == 0) ? false : true;
  }

  public String accountStatus(String username) {
    Map<String, Object> paramObj = new HashMap<String, Object>();
    paramObj.put("username", username);
    // paramObj.put("businessId", UserUtil.getMmrUser().getBusinessId());
    User user = (User) getSqlMapClientTemplate().queryForObject("accountStatus", paramObj);
    if (user == null) {
      return null;
    }
    return user.getStatus();
  }

  public void updateLoginFailedCount(String username) {
    Map<String, Object> paramObj = new HashMap<String, Object>();
    paramObj.put("username", username);
    // paramObj.put("businessId", UserUtil.getMmrUser().getBusinessId());
    getSqlMapClientTemplate().update("updateLoginFailedCount", paramObj);
  }

  public int loginFailedCount(String username) {
    Map<String, Object> paramObj = new HashMap<String, Object>();
    paramObj.put("username", username);
    // paramObj.put("businessId", UserUtil.getMmrUser().getBusinessId());
    User user = (User) getSqlMapClientTemplate().queryForObject("loginFailedCount", paramObj);
    return user.getLoginFailedCount();
  }

  public void updateAccountStatus(String username, String status) {
    Map<String, Object> paramObj = new HashMap<String, Object>();
    paramObj.put("username", username);
    // paramObj.put("businessId", UserUtil.getMmrUser().getBusinessId());
    paramObj.put("account_status", status);
    getSqlMapClientTemplate().update("updateAccountStatus", paramObj);
  }

  public void updateLoginFailedCountZero(String username) {
    Map<String, Object> paramObj = new HashMap<String, Object>();
    paramObj.put("username", username);
    // paramObj.put("businessId", UserUtil.getMmrUser().getBusinessId());
    getSqlMapClientTemplate().update("updateLoginFailedCountZero", paramObj);
  }

  protected String removeQuote(String string) {
    String ret = null;

    if (StringUtil.isEmpty(string)) {
      return null;
    } else {
      if (string.indexOf("'") != -1) {
        ret = string.replace("'", "''");
      } else {
        ret = string;
      }
    }
    return ret.trim();
  }

  public void setUserExtensionDAO(UserExtensionDAO userExtensionDAO) {
    this.userExtensionDAO = userExtensionDAO;
  }

  public User findUserByUserCode(String code) {
    Map<String, Object> paramObj = new HashMap<String, Object>(1);
    paramObj.put("userCode", code);
    User user = (User) getSqlMapClientTemplate().queryForObject("findUserByUserCode", paramObj);
    return user;
  }

  public List<UnitOfMeasure> unitOfMeasure() {
    return (List<UnitOfMeasure>) getSqlMapClientTemplate().queryForList("unitOfMeasure");
  }

  public User findunitofmeasure(String username) {
    return (User) getSqlMapClientTemplate().queryForObject("unitmeasure", username);
  }

  public List<LocaleVO> findLocale() {
    return (List<LocaleVO>) getSqlMapClientTemplate().queryForList("locale");
  }
  public Customer getCustomerReference(long id){
	  		return (Customer)getSqlMapClientTemplate().queryForObject("getCustomerReference",id);
	  	}
  
  public List<User> getUserEmailById(long id, String role, String userName) {
	  	  	List<User> user=new ArrayList<User>();
	  	  	Map <String,String> m=new HashMap<String,String>();
	  	  	
	  	  	if(role.equalsIgnoreCase("sales") || role.equalsIgnoreCase("Customer_Shipper") || role.equalsIgnoreCase("customer_base")){
	  	  		m.put("userName", userName);
	  	  		user=(List<User>) getSqlMapClientTemplate().queryForList("getUserEmailByUserName", m);	
	  	  	}
	  	  	else{
	  	  		m.put("customerId", String.valueOf(id));
	  	  		m.put("role", role);
	  	  		user=(List<User>) getSqlMapClientTemplate().queryForList("getUserEmailById", m);
	  	  	}
	  	  	return user;
	  	  }
  
  @Override
    public User getUserByUsercode(String userCode) {
  
  	return (User)getSqlMapClientTemplate().queryForObject("getUserByUsercode",userCode);
    }

@Override
public LocaleVO getDisplayTextByLocale(String locale) {
	return (LocaleVO) getSqlMapClientTemplate().queryForObject("getDisplayTextByLocale",locale);
}

public MenusDAO getMenuItemDAO() {
	return menuItemDAO;
}

public void setMenuItemDAO(MenusDAO menuItemDAO) {
	this.menuItemDAO = menuItemDAO;
}

@SuppressWarnings("unchecked")
@Override
public List<User> getAllUsers() {
	// TODO Auto-generated method stub
	return (List<User>)getSqlMapClientTemplate().queryForList("getAllUsers");
}
}
