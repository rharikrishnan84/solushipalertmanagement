package com.meritconinc.mmr.dao.ibatis;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.meritconinc.mmr.dao.PropertyDAO;
import com.meritconinc.mmr.model.common.PropertyVO;

public class PropertyDAOImpl extends SqlMapClientDaoSupport implements PropertyDAO {

  /**
   * Reads an action by action name.
   * 
   * @param action
   * @return
   */
  public String readProperty(String scope, String name) {
    Map map = new HashMap();
    map.put("scope", scope);
    map.put("name", name);
    return (String) getSqlMapClientTemplate().queryForObject("readProperty", map);
  }

  public void updateProperty(String scope, String name, String value) {
    Map<String, Object> params = new HashMap<String, Object>();
    params.put("scope", scope);
    params.put("name", name);
    params.put("value", value);
    getSqlMapClientTemplate().update("updateProperty", params);
  }

  public List<PropertyVO> readProperties() {
    List<PropertyVO> properties = getSqlMapClientTemplate().queryForList("getProperties");
    // Issue#400: retrieve from cache as well
    for (Iterator iterator = properties.iterator(); iterator.hasNext();) {
      PropertyVO propertyVO = (PropertyVO) iterator.next();
      propertyVO.setValue(readProperty(propertyVO.getScope(), propertyVO.getName()));
    }
    return properties;
  }

  public String readTextProperty(String scope, String name) {
    Map map = new HashMap();
    map.put("scope", scope);
    map.put("name", name);
    return (String) getSqlMapClientTemplate().queryForObject("readTextProperty", map);
  }

  public List<PropertyVO> readTextProperties() {
    List<PropertyVO> textProps = getSqlMapClientTemplate().queryForList("getTextProperties");
    // Issue#400: retrieve from cache as well
    for (PropertyVO propertyVO : textProps)
      propertyVO.setValue(readTextProperty(propertyVO.getScope(), propertyVO.getName()));
    return textProps;
  }

  public void updateTextProperty(String scope, String name, String value) {
    Map<String, Object> params = new HashMap<String, Object>();
    params.put("scope", scope);
    params.put("name", name);
    params.put("value", value);
    getSqlMapClientTemplate().update("updateTextProperty", params);
  }
}