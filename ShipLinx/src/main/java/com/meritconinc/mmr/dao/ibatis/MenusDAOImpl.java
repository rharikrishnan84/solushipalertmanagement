package com.meritconinc.mmr.dao.ibatis;

import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.meritconinc.mmr.dao.MenusDAO;
import com.meritconinc.mmr.model.common.LocaleVO;
import com.meritconinc.mmr.model.common.MenuItemVO;
import com.meritconinc.mmr.utilities.Common;
import com.meritconinc.mmr.utilities.MenuItemVOComparator;
import com.meritconinc.shiplinx.model.Address;
import com.meritconinc.shiplinx.model.Business;
import com.meritconinc.shiplinx.utils.ShiplinxConstants;
import com.opensymphony.xwork2.ActionContext;
import java.util.ArrayList;
import com.meritconinc.mmr.model.common.MenuScreenVO;

public class MenusDAOImpl extends SqlMapClientDaoSupport implements MenusDAO {

  /**
   * Returns the top menus for a group of 'roles'.
   * 
   * @param roles
   * @return
   */
  public List<MenuItemVO> getTopMenus(Collection<String> roles, String locale) {
    Map map = new HashMap();
    map.put("locale", locale);
    map.put("roles", roles);
    map.put("businessId", getBusinessIdForMenuItems());
    List<MenuItemVO> topMenus = getSqlMapClientTemplate().queryForList("getTopMenus", map);
    topMenus = Common.removeDuplicate(topMenus, (Comparator) new MenuItemVOComparator(), true);
    return topMenus;
  }

  /**
   * Returns the menu items that have the same parent as 'action' and belong to a group of 'roles'.
   * 
   * @param action
   * @param roles
   * @return
   */
  public List<MenuItemVO> getGroupMenusForAction(String action, Collection<String> roles,
      String locale) {
    Map map = new HashMap();
    map.put("action", action);
    map.put("roles", roles);
    map.put("locale", locale);
    List<MenuItemVO> groupMenus = getSqlMapClientTemplate().queryForList("getGroupMenusForAction",
        map);
    groupMenus = Common.removeDuplicate(groupMenus, (Comparator) new MenuItemVOComparator(), true);
    return groupMenus;
  }

  /**
   * Returns the menu items that have the same parent as 'menuId' and belong to a group of 'roles'.
   * 
   * @param menuId
   * @param roles
   * @return
   */
  public List<MenuItemVO> getGroupMenusForId(int menuId, Collection<String> roles, String locale) {
    Map map = new HashMap();
    map.put("menuId", menuId);
    map.put("roles", roles);
    map.put("locale", locale);
    List<MenuItemVO> groupMenus = getSqlMapClientTemplate().queryForList("getGroupMenusForId", map);
    groupMenus = Common.removeDuplicate(groupMenus, (Comparator) new MenuItemVOComparator(), true);
    return groupMenus;
  }

  /**
   * Returns the first child in the list for a node � 'menuId'and and belonging to a group of
   * 'roles'.
   * 
   * @param menuId
   * @param roles
   * @return
   */
  public MenuItemVO getFirstChild(int menuId, Collection<String> roles, String locale) {
    Map map = new HashMap();
    map.put("menuId", menuId);
    map.put("roles", roles);
    map.put("locale", locale);
    map.put("businessId", getBusinessIdForMenuItems());
    return (MenuItemVO) getSqlMapClientTemplate().queryForObject("getFirstChild", map);
  }

  public MenuItemVO getMenuById(int menuId, Collection<String> roles, String locale) {
    // TODO Auto-generated method stub
    Map map = new HashMap();
    map.put("menuId", menuId);
    map.put("roles", roles);
    map.put("locale", locale);

    return (MenuItemVO) getSqlMapClientTemplate().queryForObject("getMenuById", map);
  }

  public List<MenuItemVO> getGroupMenusForIdByLevel(int menuId, String level,
      Collection<String> roles, String locale) {
    // TODO Auto-generated method stub
    Map map = new HashMap();
    map.put("menuId", menuId);
    map.put("level", level);
    map.put("roles", roles);
    map.put("locale", locale);
    map.put("businessId", getBusinessIdForMenuItems());
    List<MenuItemVO> groupMenus = getSqlMapClientTemplate().queryForList(
        "getGroupMenusForIdByLevel", map);
    groupMenus = Common.removeDuplicate(groupMenus, (Comparator) new MenuItemVOComparator(), true);
    return groupMenus;
  }

  private int getBusinessIdForMenuItems() {
    Business business = (Business) ActionContext.getContext().getSession()
        .get(ShiplinxConstants.BUSINESS);
    int business_id = 1; // this should not happen, the business should have been determined based
                         // on the URL used to access the site, or through cookies already created
    if (business != null)
      business_id = (int) business.getId();

    return business_id;
  }

@Override
public LocaleVO getLanguageByUserName(String user) {
	LocaleVO localevo =null;
	try{
		localevo = (LocaleVO) getSqlMapClientTemplate().queryForObject("customerlanguage", user);
	}catch(Exception e){
		e.printStackTrace();
	}
	return localevo;
}

@Override
public MenuItemVO getWelcomeMenuBylocale(String locale) {
	return (MenuItemVO) getSqlMapClientTemplate().queryForObject("getWelcomeMenuBylocale",locale);
}

@Override
public List<MenuItemVO> getMenuByBusiness(String businessId) {
	List<MenuItemVO> menuList = null;
	try{
		Map map = new HashMap();
	    map.put("businessId", Integer.valueOf(businessId));
		menuList = getSqlMapClientTemplate().queryForList(
	        "getMenuByBusiness",map);
	}catch(Exception e){
		return new ArrayList<MenuItemVO>();
	}
	return menuList;
}
@Override
public List<MenuItemVO> getTopMenuByBusiness(String businessId,String locale) {
	Map map = new HashMap();
    map.put("businessId", businessId);
    map.put("locale", locale);
    
    List<MenuItemVO> menuList = null;
   try{
	menuList = getSqlMapClientTemplate().queryForList(
	        "getTopMenuByBusiness",map);
	}catch(Exception e){
		return new ArrayList<MenuItemVO>();
	}
	return menuList;
}

@Override
public List<MenuItemVO> getFirstMenuByTop(String businessId, String topId) {
	Map map = new HashMap();
    map.put("businessId", businessId);
    map.put("parentId", topId);
    List<MenuItemVO> menuList = null;
    try{
		menuList = getSqlMapClientTemplate().queryForList(
	        "getFirstMenuByBusiness",map);
	}catch(Exception e){
		return new ArrayList<MenuItemVO>();
	}
	return menuList;
}

@Override
public String saveMenu(MenuItemVO menuItemVO) {
	int key = ((Integer) getSqlMapClientTemplate().insert("addMenu", menuItemVO)).intValue();
	return new Integer(key).toString();
}

@Override
public void saveBusinessMenu(String businessId, String menuId) {
	Map map = new HashMap();
    map.put("businessId", businessId);
    map.put("menuId", menuId);
    try {
        getSqlMapClientTemplate().insert("addBusinessMenu", map);
      } catch (Exception e) {
        // log.debug("-----Exception-----"+e);
        e.printStackTrace();
      }
}

@Override
public List<Integer> getMenuIdByRole(String roleName) {
	List<Integer> menuIds = (List<Integer>)getSqlMapClientTemplate().queryForList("roleMenuIds",roleName);
	return menuIds;
}
@Override
public void deleteRoleMenu(String role, String menuId) {
	Map map = new HashMap();
    map.put("menuId", menuId);
    map.put("role", role);
   
    getSqlMapClientTemplate().delete("deleteRoleMenu", map);

}

@Override
public void deleteMenuByRole(String role) {
	Map map = new HashMap();
    map.put("role", role);
    getSqlMapClientTemplate().delete("deleteMenuByRole", map);
	
}

@Override
public MenuItemVO getMenuOnlyById(String menuId) {
	 Map map = new HashMap();
	    map.put("menuId", menuId);

	    return (MenuItemVO) getSqlMapClientTemplate().queryForObject("getMenuOnlyById", map);
}

@Override
public List<String> getRoleByMenuId(String menuId) {
	List<String> menuIds = (List<String>)getSqlMapClientTemplate().queryForList("getRoleIdFromMenu",menuId);
	return menuIds;
}

@Override
public List<Integer> getBusinessByMenuId(String menuId) {
	List<Integer> menuIds = (List<Integer>)getSqlMapClientTemplate().queryForList("getBusinessByMenu",menuId);
	return menuIds;
}

@Override
public void deleteMenu(String menuId) {
	Map map = new HashMap();
    map.put("menuId", menuId);
    
    getSqlMapClientTemplate().delete("deleteMenu", map);
	
}

@Override
public void deleteMenuRole(String menuId) {
	Map map = new HashMap();
    map.put("menuId", menuId);
    
   getSqlMapClientTemplate().delete("deleteMenuRole", map);
	
}
@Override
public void deleteMenuBusiness(String menuId) {
	Map map = new HashMap();
    map.put("menuId", menuId);
    
    getSqlMapClientTemplate().delete("deleteMenuBusiness", map);
	
}

@Override
public List<MenuItemVO> getAllMenu() {
List<MenuItemVO> menuList = getSqlMapClientTemplate().queryForList("getAllMenu");
	return menuList;
}

@Override
public void deleteBusinessMenuById(String businessId, String menuId) {
	Map map = new HashMap();
    map.put("menuId", menuId);
    map.put("businessId", businessId);
    
    getSqlMapClientTemplate().delete("deleteMenuBusinessById", map);
	
}


	@Override
	public int getMenuIdByUrl(String url) {
	// TODO Auto-generated method stub
	return (Integer) getSqlMapClientTemplate().queryForObject("getMenuIdByUrl", url);
	}

	@Override
	public long getParentIdByMenuId(long editMenuId) {
		// TODO Auto-generated method stub
		return (Long) getSqlMapClientTemplate().queryForObject("getParentIdByMenuId", editMenuId);
}


@Override
public void updateMenu(MenuItemVO menuItemVO, MenuScreenVO menuScreenVO) {
	// TODO Auto-generated method stub
	Map map = new HashMap();
    map.put("menuId", menuItemVO.getId());
    map.put("name", menuItemVO.getName());
    map.put("url", menuItemVO.getUrl());
    map.put("displayorder",menuItemVO.getDisplayOrder());
    map.put("menulevel",menuItemVO.getLevel());
    map.put("parentId",menuItemVO.getParentId() );
    getSqlMapClientTemplate().update("updateMenu",map);
   //resource bunndle
   Map map1 = new HashMap();
    map1.put("msgcont", menuScreenVO.getMsgContent());
    map1.put("msgkey",menuScreenVO.getMsgKey());
    map1.put("locale",menuScreenVO.getLocale());
    getSqlMapClientTemplate().update("updateResouceBunMenu",map1);
}

@Override
public void insertBusinessMenyByBusiness(long newBusinessId,
		long defaultBusinessId) {
	Map map = new HashMap();
    map.put("newBusinessId", newBusinessId);
    map.put("defaultBusinessId", defaultBusinessId);
   try {
        getSqlMapClientTemplate().insert("addBusinessMenubyBusiness", map);
      } catch (Exception e) {
        // log.debug("-----Exception-----"+e);
        e.printStackTrace();
      }
	
}
}