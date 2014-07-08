package com.meritconinc.mmr.dao.ibatis;

import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.meritconinc.mmr.dao.RolesTagDAO;
import com.meritconinc.mmr.model.common.RoleTagVO;

public class RolesTagDAOImpl extends SqlMapClientDaoSupport implements RolesTagDAO {

  /**
   * read all roles
   * 
   * @return
   */
  public List<String> getRoles(String section) {
    List<String> ret = new ArrayList<String>();
    List<RoleTagVO> roleTagVOs = getSqlMapClientTemplate().queryForList("getRolesTag", section);
    for (RoleTagVO roleTagVO : roleTagVOs) {
      ret.add(roleTagVO.getRole());
    }
    return ret;
  }
}