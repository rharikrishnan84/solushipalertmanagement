package com.meritconinc.mmr.dao;

import java.util.List;

import com.meritconinc.mmr.model.common.RoleVO;

public interface RolesDAO {

  /**
   * Get all roles that are visible to the specified user
   * 
   * @param user
   * @return
   */
  public List<RoleVO> getRolesByUser(String locale, String user);

  public List<RoleVO> getRolesByType(String locale, int type);
  
  public List<RoleVO> getAllRoles();
            
            public void saveRoleMenu(String roleId,String menuId);
            
            public void saveRole(String role,String description) throws Exception;
           
           public RoleVO getRoleById(String roleName);
            
            public void updateRole(String roleId,String description);
            
            public void deleteRole(String roleId);
            
            public String[] getRolesByActions(String actionId);
            
            public RoleVO getSystemAdminRole();
      
      	public void deleteRoleByMenuId(String menuid, String role);

}