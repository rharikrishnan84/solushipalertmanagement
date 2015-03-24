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
  
  @Override
  public List<RoleVO> getAllRoles() {
  	return getSqlMapClientTemplate().queryForList("getRoles");
  }
  
  
  @Override
  public void saveRoleMenu(String roleId, String menuId) {
  	Map map = new HashMap();
      map.put("role", roleId);
   map.put("menuId", menuId);
      try {
          getSqlMapClientTemplate().insert("addRoleMenu", map);
        } catch (Exception e) {
          // log.debug("-----Exception-----"+e);
          e.printStackTrace();
        }
  	
  }
  
  @Override
  public void saveRole(String role, String description) throws Exception {
  	Map map = new HashMap();
  	
  	map.put("role", role);
      map.put("description", description);
      try {
          getSqlMapClientTemplate().insert("addRole", map);
        } catch (Exception e) {
          // log.debug("-----Exception-----"+e);
          e.printStackTrace();
          throw new Exception();
        }
  }
  
 @Override
  public RoleVO getRoleById(String roleName) {
  	return (RoleVO)getSqlMapClientTemplate().queryForObject("getRoleById", roleName);		
  }
  
 @Override
 public void updateRole(String roleId, String description) {
  	Map<String, Object> paramObj = new HashMap<String, Object>();
      paramObj.put("role", roleId);
      paramObj.put("description", description);
      // paramObj.put("businessId", UserUtil.getMmrUser().getBusinessId());
      getSqlMapClientTemplate().update("updateRoleDesc", paramObj);
  	
  }
  
  @Override
  public void deleteRole(String roleId) {
  	// TODO Auto-generated method stub
  	Map<String, Object> paramObj = new HashMap<String, Object>();
      paramObj.put("role", roleId);
      getSqlMapClientTemplate().delete("deleteRoleDesc",paramObj);
  }
  
  @Override
  public String[] getRolesByActions(String actionId) {
  	 Map<String, Object> map = new HashMap<String, Object>(2);
  	    map.put("actionid", actionId);
  	    List<String> roleNames =  getSqlMapClientTemplate().queryForList("getRoleByActions", map);
  	    String[] arrRoleName = new String[roleNames.size()];
  	    int count = 0;
  	    for(String roleName : roleNames){
  	    	arrRoleName[count] = roleName;
  	    	count++;
  	    }
  	    
  	return arrRoleName;
  }

 @Override
 public RoleVO getSystemAdminRole() { 
  	return (RoleVO)getSqlMapClientTemplate().queryForObject("getSysadminRole");
  }

  @Override
	public void deleteRoleByMenuId(String menuid, String role) {
		// TODO Auto-generated method stub
		 Map<String, Object> map = new HashMap<String, Object>(2);
	    map.put("menuId",menuid);
	    map.put("role",role);
	    getSqlMapClientTemplate().update("deleteRoleByMenuId",map);
	}

}