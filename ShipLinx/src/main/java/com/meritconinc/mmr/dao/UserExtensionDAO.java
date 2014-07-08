package com.meritconinc.mmr.dao;

import com.meritconinc.mmr.model.security.UserExtendedAttributes;

public interface UserExtensionDAO {
  void updateExtendedAttributes(String username, UserExtendedAttributes attributes);

  void insertExtendedAttributes(String username, UserExtendedAttributes attributes);

  UserExtendedAttributes getExtendedAttributesByUsername(String username);

  void copyExtendedAttributesToHistory(String username, int historyId);
}
