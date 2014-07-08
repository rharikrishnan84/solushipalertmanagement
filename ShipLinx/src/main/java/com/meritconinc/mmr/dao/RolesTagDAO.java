package com.meritconinc.mmr.dao;

import java.util.List;

public interface RolesTagDAO {

  /**
   * read all roles
   * 
   * @return
   */
  public List<String> getRoles(String section);

}