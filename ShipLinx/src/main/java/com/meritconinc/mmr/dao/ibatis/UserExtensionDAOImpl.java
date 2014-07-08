package com.meritconinc.mmr.dao.ibatis;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.meritconinc.mmr.dao.UserExtensionDAO;
import com.meritconinc.mmr.model.security.UserExtendedAttributes;

public class UserExtensionDAOImpl extends SqlMapClientDaoSupport implements UserExtensionDAO {
  private static Logger logger = Logger.getLogger(UserExtensionDAOImpl.class);

  public void copyExtendedAttributesToHistory(String username, int historyId) {
    logger.debug("> copyExtendedAttributesToHistory");
    Map params = new HashMap(2);
    params.put("historyId", historyId);
    params.put("username", username);
    getSqlMapClientTemplate().insert("copyExtendedAttributesToHistory", params);
  }

  public UserExtendedAttributes getExtendedAttributesByUsername(String username) {
    logger.debug("> getExtendedAttributes");
    Map params = new HashMap(1);
    params.put("username", username);
    return (UserExtendedAttributes) getSqlMapClientTemplate().queryForObject(
        "getExtendedAttributesByUsername", params);
  }

  public static class AttributeWrapper {
    private String username;
    private UserExtendedAttributes attributes;

    public String getUsername() {
      return username;
    }

    public void setUsername(String username) {
      this.username = username;
    }

    public UserExtendedAttributes getAttributes() {
      return attributes;
    }

    public void setAttributes(UserExtendedAttributes attributes) {
      this.attributes = attributes;
    }
  }

  public void insertExtendedAttributes(String username, UserExtendedAttributes attributes) {
    logger.debug("> insertExtendedAttributes");
    AttributeWrapper proxy = new AttributeWrapper();
    proxy.setUsername(username);
    proxy.setAttributes(attributes);
    getSqlMapClientTemplate().insert("insertExtendedAttributes", proxy);
  }

  public void updateExtendedAttributes(String username, UserExtendedAttributes attributes) {
    logger.debug("> updateExtendedAttributes");
    AttributeWrapper proxy = new AttributeWrapper();
    proxy.setUsername(username);
    proxy.setAttributes(attributes);
    getSqlMapClientTemplate().update("updateExtendedAttributes", proxy);
  }

}
