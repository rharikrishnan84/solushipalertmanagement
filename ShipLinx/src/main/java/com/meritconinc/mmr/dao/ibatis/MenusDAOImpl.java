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


}