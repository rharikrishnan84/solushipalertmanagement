package com.meritconinc.mmr.dao;

import com.meritconinc.mmr.model.common.AuthorizedActionVO;

public interface ActionsDAO {

  /**
   * Reads an action by action name.
   * 
   * @param action
   * @return
   */
  public AuthorizedActionVO readAction(String action);

}