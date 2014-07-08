package com.meritconinc.mmr.dao.ibatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.meritconinc.mmr.dao.RolesDAO;
import com.meritconinc.mmr.model.common.RoleVO;

public class RolesDAOImpl extends SqlMapClientDaoSupport implements RolesDAO {

  public List<RoleVO> getRolesByUser(String locale, String user) {
    Map<String, String> map = new HashMap<String, String>(2);
    map.put("user", user);
    map.put("locale", locale);
    return getSqlMapClientTemplate().queryForList("getRolesByUser", map);
  }

  public List<RoleVO> getRolesByType(String locale, int type) {
    Map<String, Object> map = new HashMap<String, Object>(2);
    map.put("type", type);
    map.put("locale", locale);
    return getSqlMapClientTemplate().queryForList("getRolesByType", map);
  }

}