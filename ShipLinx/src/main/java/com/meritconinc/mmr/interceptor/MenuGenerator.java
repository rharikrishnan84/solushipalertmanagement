package com.meritconinc.mmr.interceptor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.meritconinc.mmr.dao.MenusDAO;
import com.meritconinc.mmr.model.common.MenuItemVO;
import com.opensymphony.xwork2.ActionContext;

public class MenuGenerator {
	private Collection<String> roles = null;
	private String locale = null;
	private MenusDAO menusDAO = null;
	private static final Logger log = Logger.getLogger(MmrInterceptor.class);
	
	public MenuGenerator(Collection<String> r, String l, MenusDAO mDAO) {
		// TODO Auto-generated constructor stub
		roles = r;
		locale = l;
		menusDAO = mDAO;		
	} 

	public void generateMenus(int selectedId) {
		// TODO Auto-generated method stub
        List<MenuItemVO> topMenu = menusDAO.getTopMenus(roles, locale);
		checkForUrlUpdate(topMenu, roles, menusDAO, locale);
		List<MenuItemVO> levelOneMenus = null;
		List<MenuItemVO> levelTwoMenus = null;
				
		MenuItemVO selectedMenu = getSelectedMenu(selectedId, topMenu, roles, locale, menusDAO);
		if (selectedMenu != null) 
		{
			levelOneMenus = getLevelOneMenus(selectedMenu, topMenu, roles, locale, menusDAO);
			if (levelOneMenus != null && levelOneMenus.size() > 0) {
				levelTwoMenus = getLevelTwoMenus(selectedMenu, levelOneMenus, roles, locale, menusDAO);	
				updateURLforLevelOne(levelOneMenus,roles,menusDAO,locale);
			}
			updateURLforTopMenu(topMenu,roles, menusDAO, locale);
		}
		else
		{
			updateURLforTopMenu(topMenu,roles, menusDAO, locale);
		}
		ActionContext.getContext().getSession().put("ROLE", roles);
		ActionContext.getContext().getSession().put("ContextPath",ServletActionContext.getRequest().getContextPath());
		ActionContext.getContext().getSession().put("TOP_LEVEL_MENUS", topMenu);
				
		if (levelOneMenus != null)
			ActionContext.getContext().getSession().put("LEVEL_ONE_MENUS", levelOneMenus);
		else
			ActionContext.getContext().getSession().remove("LEVEL_ONE_MENUS");
		
		if (levelTwoMenus != null)
		{
			ActionContext.getContext().getSession().put("LEVEL_TWO_MENUS", levelTwoMenus);			
		}			
		else
		{
			ActionContext.getContext().getSession().remove("LEVEL_TWO_MENUS");			
		}
	}

	private List<MenuItemVO> getLevelTwoMenus(MenuItemVO selectedMenu, 	List<MenuItemVO> levelOneMenus, Collection<String> roles,
						String locale, MenusDAO menusDAO) {
		// TODO Auto-generated method stub
		if (selectedMenu != null) {
			List<MenuItemVO> levelTwoMenus = null;
			MenuItemVO menu = null;
			int id = -1;
			if (selectedMenu.getLevel().equalsIgnoreCase(MenuItemVO.TOP_LEVEL)) {
				menu = findMenuByAction(selectedMenu.getUrl(), levelOneMenus);
				if (menu != null) {
					id = menu.getId();
				}
			} else if (selectedMenu.getLevel().equalsIgnoreCase(MenuItemVO.LEVEL_ONE)) {
				id = selectedMenu.getId();
			} else if (selectedMenu.getLevel().equalsIgnoreCase(MenuItemVO.LEVEL_TWO)) {
				id = selectedMenu.getParentId();				
			}
			levelTwoMenus = menusDAO.getGroupMenusForIdByLevel(id,MenuItemVO.LEVEL_TWO, roles, locale);
			if (levelTwoMenus != null && levelTwoMenus.size() > 0) {
				menu = findMenuById(selectedMenu.getId(), levelTwoMenus);
				if (menu == null)
					menu = findMenuByAction(selectedMenu.getUrl(), levelTwoMenus);
				if (menu != null) {
					menu.setSelected(true);
				}
				return levelTwoMenus;
			}
		}
		return null;
	}

	private List<MenuItemVO> getLevelOneMenus(MenuItemVO selectedMenu, List<MenuItemVO> topMenu, Collection<String> roles, String locale,
						MenusDAO menusDAO) {
		// TODO Auto-generated method stub
		if (selectedMenu != null) {
			List<MenuItemVO> levelOneMenus = null;
			int id = -1;
			if (selectedMenu.getLevel().equalsIgnoreCase(MenuItemVO.TOP_LEVEL)) {
				id = selectedMenu.getId();
			} else if (selectedMenu.getLevel().equalsIgnoreCase(MenuItemVO.LEVEL_ONE)) {
				id = selectedMenu.getParentId();
			} else if (selectedMenu.getLevel().equalsIgnoreCase(MenuItemVO.LEVEL_TWO)) {
//				id = selectedMenu.getParentId();
				MenuItemVO parentMenu = menusDAO.getMenuById(selectedMenu.getParentId(), roles, locale);
				if (parentMenu != null) {
					id = parentMenu.getParentId();
				} else {
					return null;
				}
			}
			levelOneMenus = menusDAO.getGroupMenusForIdByLevel(id, MenuItemVO.LEVEL_ONE, roles, locale);
			if (levelOneMenus != null && levelOneMenus.size() > 0) {
				MenuItemVO menu = findMenuById(selectedMenu.getId(), levelOneMenus);
				if (menu == null)
					menu = findMenuByAction(selectedMenu.getUrl(),
							levelOneMenus);
				if (menu != null) {
					menu.setSelected(true);
				}
				return levelOneMenus;
			}
		}
		return null;
	}

	private MenuItemVO getSelectedMenu(int selectedId, List<MenuItemVO> topMenu, Collection<String> roles, String locale, MenusDAO menusDAO) {
		// TODO Auto-generated method stub
		if (selectedId > 0) {
			MenuItemVO selectedMenu = null;
			if (topMenu != null && topMenu.size() > 0) {
				selectedMenu = findMenuById(selectedId, topMenu);
				if (selectedMenu != null) {
					selectedMenu.setSelected(true);
					return selectedMenu;
				}
			}
			selectedMenu = menusDAO.getMenuById(selectedId, roles, locale);
			if (selectedMenu != null) {
				selectedMenu.setSelected(true);
				return selectedMenu;
			}
		}
		return null;
	}

	private MenuItemVO findMenuById(int selectedId, List<MenuItemVO> menuList) {
		// TODO Auto-generated method stub
		if (menuList != null && menuList.size() > 0) {
			for (MenuItemVO m : menuList) {
				if (m != null)
					if (m.getId() == selectedId)
						return m;
			}
		}
		return null;
	}

	private MenuItemVO findMenuByAction(String url, List<MenuItemVO> menuList) {
		// TODO Auto-generated method stub
		if (url != null && menuList != null && menuList.size() > 0) {
			for (MenuItemVO m : menuList) {
				if (m != null && m.getUrl() != null)
					if (m.getUrl().equals(url))
						return m;
			}
		}
		return null;
	}

	/**
	 * Checks if we have to update the URL for any menu item.
	 * 
	 * @param menuItems
	 * @param roles
	 * @param menusDAO
	 */
	private void checkForUrlUpdate(List<MenuItemVO> menuItems, Collection<String> roles, MenusDAO menusDAO, String locale) {
		if (menuItems != null) {
			for(MenuItemVO mitVO:menuItems) {
				if (mitVO.getUrl().equals("TBA")) {
					MenuItemVO miChildVO = menusDAO.getFirstChild(mitVO.getId(), roles, locale);
					if (miChildVO != null) {
						if (miChildVO.getUrl().equals("TBA")) {
							miChildVO = menusDAO.getFirstChild(miChildVO.getId(), roles, locale);
							if (miChildVO != null)
								mitVO.setUrl(miChildVO.getUrl());
						} else {
							mitVO.setUrl(miChildVO.getUrl());
						}
					}
				}				
			}
		}
	}
	
	/**
	 * Updates the url of each of the top menu to the url of the 1st level2 item if exists or 1st level item.
	 * 
	 * @param menuItems
	 * @param roles
	 * @param menusDAO
	 * @param locale
	 */
	private void updateURLforTopMenu(List<MenuItemVO> menuItems, Collection<String> roles, MenusDAO menusDAO, String locale)
	{
		List<MenuItemVO> levelOneMenus = null;
		List<MenuItemVO> levelTwoMenus = null;
		for(int i=0;i< menuItems.size();i++)
		{
			levelOneMenus = getLevelOneMenus(menuItems.get(i), menuItems, roles, locale, menusDAO);
			if (levelOneMenus != null && levelOneMenus.size() > 0) 
			{
				levelTwoMenus = getLevelTwoMenus(menuItems.get(i), levelOneMenus, roles, locale, menusDAO);	
				if(levelTwoMenus != null && levelTwoMenus.size() > 0)
				{
					menuItems.get(i).setUrl(levelTwoMenus.get(0).getUrl().toString().trim()); // Assigns the url of the 1st level2 menu to the respective topmenu if its not null
				}
				else
				{
					menuItems.get(i).setUrl(levelOneMenus.get(0).getUrl().toString().trim()); // if level2 is null, assign the url of 1st level1 menu to the respective topmenu.
				}
			}
		}		
	}

	/**
	 * Updates the url of each of the top menu to the url of the 1st level2 item if exists or 1st level item.
	 * 
	 * @param menuItems
	 * @param roles
	 * @param menusDAO
	 * @param locale
	 */
	private void updateURLforLevelOne(List<MenuItemVO> menuItems, Collection<String> roles, MenusDAO menusDAO, String locale)
	{
		List<MenuItemVO> levelTwoMenus = null;
		List<MenuItemVO> levelOneMenus = null;
		for(int i=0;i< menuItems.size();i++)
		{
			levelTwoMenus = getLevelTwoMenus(menuItems.get(i), levelOneMenus, roles, locale, menusDAO);	
			
			if(levelTwoMenus != null && levelTwoMenus.size() > 0)
			{
				menuItems.get(i).setUrl(levelTwoMenus.get(0).getUrl().toString().trim()); //assigns the url of the 1st Level2 menu item to the level1 menu items
			}
		}
	}
	
}
