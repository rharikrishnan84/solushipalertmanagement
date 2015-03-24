package com.meritconinc.mmr.dao.ibatis;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.meritconinc.mmr.dao.ActionsDAO;
import com.meritconinc.mmr.model.common.AuthorizedActionVO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ActionsDAOImpl extends SqlMapClientDaoSupport implements ActionsDAO {

  /**
   * Reads an action by action name.
   * 
   * @param action
   * @return
   */
  public AuthorizedActionVO readAction(String action) {
    return (AuthorizedActionVO) getSqlMapClientTemplate().queryForObject("readAction", action);
  }

  @Override
  public List<AuthorizedActionVO> readAllAction() {
  	List<AuthorizedActionVO> actionList = null;
  	try{
  		actionList = getSqlMapClientTemplate().queryForList(
  	        "readAllAction");
  	}catch(Exception e){
  		return new ArrayList<AuthorizedActionVO>();
  	}
  	return actionList;
  }
  
  @Override
  public int addAction(AuthorizedActionVO actionVO) {
  	int key = ((Integer) getSqlMapClientTemplate().insert("addAction", actionVO)).intValue();
  	return key;
  }
  
  @Override
  public void addRoleAction(String actionId, String roleName) {
  	Map map = new HashMap();
      map.put("actionId", actionId);
      map.put("roleName", roleName);
      try {
          getSqlMapClientTemplate().insert("addRoleAction", map);
        } catch (Exception e) {
          // log.debug("-----Exception-----"+e);
          e.printStackTrace();
        }
  	
  }
  
  @Override
  public List<Integer> getActionIdByRole(String roleName) {
  	List<Integer> actionIds = (List<Integer>)getSqlMapClientTemplate().queryForList("roleActionIds",roleName);
  	return actionIds;
  }
  
  @Override
  public void deleteRoleAction(String role, String actionId) {
  	Map map = new HashMap();
      map.put("actionId", actionId);
      map.put("role", role);
      
      getSqlMapClientTemplate().delete("deleteRoleAction", map);
  	
  }
  
  @Override
  public void deleteActionByRole(String role) {
  	Map map = new HashMap();
      map.put("role", role);
      
      getSqlMapClientTemplate().delete("deleteActionByRole", map);
  	
  	
  }
  
  @Override
  public AuthorizedActionVO getActionById(String actionId) {
  	AuthorizedActionVO action = null;
  	Map map = new HashMap();
      map.put("actionId", actionId);
  try{
  	action =(AuthorizedActionVO) getSqlMapClientTemplate().queryForObject("readActionById", map);
  }catch(Exception e){
  	return new AuthorizedActionVO();
  }
  	return action;
  }
  
  @Override
  public void updateAction(AuthorizedActionVO actionVO) {
  	
  	getSqlMapClientTemplate().update("updateAction", actionVO);
  	
  }
  
  @Override
  public void deleteAction(String actionId) {
  	Map map = new HashMap();
  	map.put("actionId", actionId);
  
  	getSqlMapClientTemplate().delete("deleteAction", map);
  	
  }
}