package com.meritconinc.mmr.dao;

import com.meritconinc.mmr.model.system.DatabaseInfoVO;

public interface UtilDAO {
  DatabaseInfoVO getDatabaseInfoVO();

  void clearCache();
}
