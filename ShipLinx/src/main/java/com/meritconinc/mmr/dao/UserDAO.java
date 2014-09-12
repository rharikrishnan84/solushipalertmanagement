package com.meritconinc.mmr.dao;

import java.util.Collection;
import java.util.List;

import com.meritconinc.mmr.model.admin.UserSearchCriteria;
import com.meritconinc.mmr.model.common.AuthorizedActionVO;
import com.meritconinc.mmr.model.common.LocaleVO;
import com.meritconinc.mmr.model.security.User;
import com.meritconinc.shiplinx.model.Customer;
import com.meritconinc.shiplinx.model.UnitOfMeasure;

public interface UserDAO {
  User login(String username, String password);

  void save(User user, String changedBy);

  void saveProfile(User user, String changedBy);

  void create(User user, String changedBy);

  User findUserByUsername(String username);

  List<User> findUsers(UserSearchCriteria criteria, int startIndex, int endIndex);

  void updateLastLoginInfo(String username);

  User findUserByEmail(String email);

  boolean isUsernameRegistered(String username);

  boolean isEmailRegistered(String username, String email);

  boolean isEmailRegistered(String email);

  boolean isPasswordValid(String username, String password);

  boolean isPasswordRepeated(String username, String newPassword, int pass_hist_count);

  boolean isAccountLockedByRetries(String username, boolean loginFailed);

  void changePassword(String username, String newPassword, String changedBy, boolean isAdmin);

  int findDataRowsCount(UserSearchCriteria critiera);

  Collection<AuthorizedActionVO> readAuthorizedActions(String userName);

  void copyUserToHistory(String username);

  public void updateLoginFailedCountZero(String username);

  List<User> findUserByCustomer(UserSearchCriteria criteria);

  // void add(User user);
  void delete(String username);

  // void edit(User user);
  User findUserByUserCode(String code);

  List<UnitOfMeasure> unitOfMeasure();

  User findunitofmeasure(String username);

  public List<LocaleVO> findLocale();
  
  public Customer getCustomerReference(long id);
  public List<User> getUserEmailById(long id,String role,String userName);
}
