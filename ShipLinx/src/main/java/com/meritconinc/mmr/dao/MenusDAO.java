package com.meritconinc.mmr.dao;

import java.util.Collection;
import java.util.List;

import com.meritconinc.mmr.model.common.LocaleVO;
import com.meritconinc.mmr.model.common.MenuItemVO;

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
}