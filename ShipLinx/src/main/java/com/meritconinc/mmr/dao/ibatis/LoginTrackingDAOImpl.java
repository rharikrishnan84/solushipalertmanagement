package com.meritconinc.mmr.dao.ibatis;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.meritconinc.mmr.dao.LoginTrackingDAO;
import com.meritconinc.mmr.model.common.LoginStatusVO;

public class LoginTrackingDAOImpl extends SqlMapClientDaoSupport implements LoginTrackingDAO {

  /**
   * Saves the Login status / attempt
   * 
   * @param loginStatusVO
   * @throws Exception
   */
  public void saveLoginAttempt(LoginStatusVO loginStatusVO) {
    if (loginStatusVO != null)
      getSqlMapClientTemplate().insert("insertLoginTracking", loginStatusVO);
  }

}