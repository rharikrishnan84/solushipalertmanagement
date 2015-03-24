package com.meritconinc.mmr.dao;

import java.util.Collection;
import java.util.List;

import com.meritconinc.mmr.model.common.LocaleVO;
import com.meritconinc.mmr.model.common.MenuItemVO;
import com.meritconinc.mmr.model.common.MenuScreenVO;

public interface MenusDAO {

  /**
   * Returns the top menus for a group of 'roles'.
   * 
   * @param roles
   * @return
   */
  public List<MenuItemVO> getTopMenus(Collection<String> roles, String locale);

  /**
   * Returns the menu items that have the same parent as 'action' and belong to a group of 'roles'.
   * 
   * @param action
   * @param roles
   * @return
   */
  public List<MenuItemVO> getGroupMenusForAction(String action, Collection<String> roles,
      String locale);

  /**
   * Returns the menu items that have the same parent as 'menuId' and belong to a group of 'roles'.
   * 
   * @param menuId
   * @param roles
   * @return
   */
  public List<MenuItemVO> getGroupMenusForId(int menuId, Collection<String> roles, String locale);

  /**
   * Returns the first child in the list for a node � 'menuId'and and belonging to a group of
   * 'roles'.
   * 
   * @param menuId
   * @param roles
   * @return
   */
  public MenuItemVO getFirstChild(int menuId, Collection<String> roles, String locale);

  public MenuItemVO getMenuById(int menuId, Collection<String> roles, String locale);

  public List<MenuItemVO> getGroupMenusForIdByLevel(int menuId, String level,
      Collection<String> roles, String locale);

  public LocaleVO getLanguageByUserName(String user);
  public MenuItemVO getWelcomeMenuBylocale(String locale);
  
  public List<MenuItemVO> getMenuByBusiness(String businessId);
  
  public List<MenuItemVO> getTopMenuByBusiness(String businessId, String locale);
  
  public List<MenuItemVO> getFirstMenuByTop(String businessId,String topId);
  
  public String saveMenu(MenuItemVO menuItemVO);
  
  public void saveBusinessMenu(String businessId,String menuId);
  
  public List<Integer> getMenuIdByRole(String roleName);
  
 public void deleteRoleMenu(String role, String menuId);
  
  public void deleteMenuByRole(String role);
  
  public MenuItemVO getMenuOnlyById(String menuId);
  
  public List<String> getRoleByMenuId(String menuId);
  
  public List<Integer> getBusinessByMenuId(String menuId);
  
  public void deleteMenu(String menuId);
  
  public void deleteMenuRole(String menuId);
  
  public void deleteMenuBusiness(String menuId);
  
  public List<MenuItemVO> getAllMenu();
  
  public void deleteBusinessMenuById(String businessId,String menuId);

    public int getMenuIdByUrl(String string);

 public long getParentIdByMenuId(long menuId);

 public void updateMenu(MenuItemVO menuItemVO, MenuScreenVO menuScreenVO);

 public void insertBusinessMenyByBusiness(long newBusinessId,long defaultBusinessId);
}