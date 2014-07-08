package com.meritconinc.mmr.service;

import java.util.List;

import javax.servlet.http.HttpSession;

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
import com.meritconinc.shiplinx.model.Customer;

public interface UserService {
  public void setSiteUserDAO(UserDAO siteUserDAO);

  public User login(String username, String password);

  public void addRequestAccess(final User user) throws EmailAlreadyRegisteredException,
      UsernameAlreadyTakenException;

  public User findUserByUsername(String username);

  public void create(User user, String changedBy) throws EmailAlreadyRegisteredException,
      UsernameAlreadyTakenException;

  // public void add(User user) throws
  // EmailAlreadyRegisteredException,UsernameAlreadyTakenException;
  public void delete(String username);

  // public void edit(User user);
  public void save(User user, String changedBy) throws EmailAlreadyRegisteredException;

  public void saveProfile(User user, String changedBy) throws EmailAlreadyRegisteredException;

  public List<User> findUsers(UserSearchCriteria criteria, int startIndex, int endIndex);

  public void changePassword(String username, String existingPassword, String newPassword,
      String changedBy, boolean isAdmin) throws InvalidPasswordException, SameOldPasswordException,
      RepeatedPasswordException;

  public int findDataRowsCount(UserSearchCriteria criteria);

  public boolean isAccountLockedByRetries(String username, boolean loginFailed);

  public void setSessionTimeout(User user, HttpSession session);

  // public int copyUserToHistory(final String username);
  public void updateUserInfo(String userName);

  public void sendApprovedEmail(User user);

  public void updateLoginFailedCountZero(String userName);

  public void saveLoginAttempt(LoginStatusVO loginStatusVO);

  public List<User> findUserByCustomer(UserSearchCriteria criteria);

  public String resetPassword(User user);

  public User findUserByCode(String userCode);

  public List<LocaleVO> findLocale();
  public Customer getCustomerReference(long id);
}
