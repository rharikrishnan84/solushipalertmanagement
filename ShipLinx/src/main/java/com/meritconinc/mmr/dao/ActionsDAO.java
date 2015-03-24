package com.meritconinc.mmr.dao;

import com.meritconinc.mmr.model.common.AuthorizedActionVO;
import java.util.List;

public interface ActionsDAO {

  /**
   * Reads an action by action name.
   * 
   * @param action
   * @return
   */
  public AuthorizedActionVO readAction(String action);
  
  public List<AuthorizedActionVO> readAllAction();
            
            public int addAction(AuthorizedActionVO actionVO);
            
            public void addRoleAction(String actionId,String roleName);
            
            public List<Integer> getActionIdByRole(String roleName);
            
            public void deleteRoleAction(String role, String action);
            
            public void deleteActionByRole(String role);
            
            public AuthorizedActionVO getActionById(String actionId);
            
            public void updateAction(AuthorizedActionVO actionVO);
           
            public void deleteAction(String actionId);

}