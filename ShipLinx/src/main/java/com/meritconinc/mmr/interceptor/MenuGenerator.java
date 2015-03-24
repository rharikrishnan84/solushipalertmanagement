package com.meritconinc.mmr.interceptor;

import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.meritconinc.mmr.dao.MenusDAO;
import com.meritconinc.mmr.dao.UserDAO;
import com.meritconinc.mmr.model.common.MenuItemVO;
import com.meritconinc.mmr.utilities.MmrBeanLocator;
import com.opensymphony.xwork2.ActionContext;
import java.util.Iterator;
import com.meritconinc.mmr.constants.Constants;
import com.meritconinc.mmr.model.common.RoleVO;
import com.meritconinc.mmr.model.security.User;
import com.meritconinc.mmr.utilities.security.UserUtil;
import com.meritconinc.shiplinx.utils.ShiplinxConstants;
import com.soluship.businessfilter.util.BusinessFilterUtil;

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
		UserDAO userDAO = (UserDAO)MmrBeanLocator.getInstance().findBean("userDAO");
		String displayTextLocale = null;
		String tempLocale = locale;
		if(locale != null && !locale.isEmpty()){
			displayTextLocale = userDAO.getDisplayTextByLocale(locale).getDisplayText();
			if(displayTextLocale !=null && !displayTextLocale.isEmpty()){
				locale = displayTextLocale;
			}
		}
		
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
		MenuItemVO menuItem = menusDAO.getWelcomeMenuBylocale(locale);
				ActionContext.getContext().getSession().put("getWelcomeMenu", menuItem.getMsgContent());
			  locale =tempLocale;
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
				User user = null;
				if(UserUtil.getMmrUser()!=null){
					user = UserUtil.getMmrUser();
					}
			//removing the menu according to the user level
				if(user!=null){
					Long countryPartnerId =(Long) ActionContext.getContext().getSession().get("countryPartnerId");
					Long branchId =(Long) ActionContext.getContext().getSession().get("branchId");
			Iterator menuTwoLevelItr=levelTwoMenus.iterator();
					while(menuTwoLevelItr.hasNext()){
						MenuItemVO menuitemVO=(MenuItemVO) menuTwoLevelItr.next();
						if(user.getUserRole()!=null && user.getUserRole().equals(RoleVO.ROLE_BUSINESSADMIN) &&
								menuitemVO.getUrl().equals(MenuItemVO.BUSINESSFILTER_URL_BUSINESSLEVEL)){
						menuTwoLevelItr.remove();
						}else if(user.isBusinessLevel() && menuitemVO.getUrl()!=null
							&& (menuitemVO.getUrl().equals(MenuItemVO.BUSINESSFILTER_URL_BUSINESSLEVEL))){
						menuTwoLevelItr.remove();
						}else if(user.isPartnerLevel() && menuitemVO.getUrl()!=null
							&& (menuitemVO.getUrl().equals(MenuItemVO.BUSINESSFILTER_URL_BUSINESSLEVEL) || 
										menuitemVO.getUrl().equals(MenuItemVO.BUSINESSFILTER_URL_PARTNERLEVEL))){
							menuTwoLevelItr.remove();
						}else if((user.isNationLevel() || countryPartnerId!=null) && menuitemVO.getUrl()!=null
								&& (menuitemVO.getUrl().equals(MenuItemVO.BUSINESSFILTER_URL_BUSINESSLEVEL) || 
										menuitemVO.getUrl().equals(MenuItemVO.BUSINESSFILTER_URL_PARTNERLEVEL) ||
								menuitemVO.getUrl().equals(MenuItemVO.BUSINESSFILTER_URL_NATIONALLEVEL))){
							menuTwoLevelItr.remove();
						}else if((user.isBranchLevel()||branchId!=null) && menuitemVO.getUrl()!=null
								&& (menuitemVO.getUrl().equals(MenuItemVO.BUSINESSFILTER_URL_BUSINESSLEVEL) || 
									menuitemVO.getUrl().equals(MenuItemVO.BUSINESSFILTER_URL_PARTNERLEVEL) ||
										menuitemVO.getUrl().equals(MenuItemVO.BUSINESSFILTER_URL_NATIONALLEVEL) ||
										menuitemVO.getUrl().equals(MenuItemVO.BUSINESSFILTER_URL_BRANCHLEVEL))){
							menuTwoLevelItr.remove();
						}/*else if(){
							
						}
						*/	
					}
				
				}
			
				//removing the user according to the sysadmin user
		if(user!=null && user.getUserRole()!=null){
					Long businessId=(Long) ActionContext.getContext().getSession().get(Constants.BUSINESS_ID_SESSION);
					Long loginUserBusId = UserUtil.getMmrUser().getBusinessId();
					Long partnerId=(Long) ActionContext.getContext().getSession().get(Constants.PARTNER_ID_SESSION);
					Long countryPartnerId=(Long) ActionContext.getContext().getSession().get(Constants.NATION_ID_SESSION);
					Long branchId=(Long) ActionContext.getContext().getSession().get(Constants.BRANCH_ID_SESSION);
					
					Iterator menuTwoLevelItr=levelTwoMenus.iterator();
					while(menuTwoLevelItr.hasNext()){
						MenuItemVO menuitemVO=(MenuItemVO) menuTwoLevelItr.next();
						if(	(businessId!=null && partnerId ==null && countryPartnerId==null && branchId==null) && menuitemVO.getUrl().equals(MenuItemVO.BUSINESSFILTER_URL_BUSINESSLEVEL)){
							menuTwoLevelItr.remove();
						}else if(((businessId!=null || loginUserBusId!=null) && partnerId!=null && countryPartnerId==null && branchId==null) && menuitemVO.getUrl()!=null
								&& (menuitemVO.getUrl().equals(MenuItemVO.BUSINESSFILTER_URL_PARTNERLEVEL)  || 
										menuitemVO.getUrl().equals(MenuItemVO.BUSINESSFILTER_URL_BUSINESSLEVEL)) ){
							menuTwoLevelItr.remove();
						}else if(((businessId!=null||loginUserBusId!=null) && partnerId!=null && countryPartnerId!=null && branchId==null)  && menuitemVO.getUrl()!=null
								&& (menuitemVO.getUrl().equals(MenuItemVO.BUSINESSFILTER_URL_BUSINESSLEVEL) || 
										menuitemVO.getUrl().equals(MenuItemVO.BUSINESSFILTER_URL_PARTNERLEVEL) ||
										menuitemVO.getUrl().equals(MenuItemVO.BUSINESSFILTER_URL_NATIONALLEVEL))){
							menuTwoLevelItr.remove();
						}else if((businessId!=null ||loginUserBusId!=null) && partnerId!=null && countryPartnerId!=null && branchId!=null && menuitemVO.getUrl()!=null
							&& (menuitemVO.getUrl().equals(MenuItemVO.BUSINESSFILTER_URL_BUSINESSLEVEL) || 
										menuitemVO.getUrl().equals(MenuItemVO.BUSINESSFILTER_URL_PARTNERLEVEL) ||
										menuitemVO.getUrl().equals(MenuItemVO.BUSINESSFILTER_URL_NATIONALLEVEL) ||
										menuitemVO.getUrl().equals(MenuItemVO.BUSINESSFILTER_URL_BRANCHLEVEL))){
							menuTwoLevelItr.remove();
					}
							
				}
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
				User user = null;
				if(UserUtil.getMmrUser()!=null){
					user = UserUtil.getMmrUser();
					}
				//updating the level one url according to the user level
				if(user!=null){
					Iterator menuOneLevelItr=levelOneMenus.iterator();
					Long branchId=(Long) ActionContext.getContext().getSession().get(Constants.BRANCH_ID_SESSION);
				while(menuOneLevelItr.hasNext()){
						MenuItemVO menuitemVO=(MenuItemVO) menuOneLevelItr.next();
					if(menuitemVO.getUrl()!=null && user.getUserRole().equals(RoleVO.ROLE_BUSINESSADMIN)
								&& menuitemVO.getUrl().equals(MenuItemVO.BUSINESSFILTER_URL_BUSINESSLEVEL) && branchId ==null){
						menuitemVO.setUrl(MenuItemVO.BUSINESSFILTER_URL_PARTNERLEVEL);
						}else if(user.isPartnerLevel() && menuitemVO.getUrl()!=null
								&& (menuitemVO.getUrl().equals(MenuItemVO.BUSINESSFILTER_URL_BUSINESSLEVEL))){
							menuitemVO.setUrl(MenuItemVO.BUSINESSFILTER_URL_NATIONALLEVEL);
							
						}else if(user.isNationLevel() && menuitemVO.getUrl()!=null
								&& (menuitemVO.getUrl().equals(MenuItemVO.BUSINESSFILTER_URL_BUSINESSLEVEL))){
							
						menuitemVO.setUrl(MenuItemVO.BUSINESSFILTER_URL_BRANCHLEVEL);
						}
							
							
					}
					
				}
				
				//updating the level one url to sysadmin
				if(user!=null && user.getUserRole()!=null  ){
					Long businessId=(Long) ActionContext.getContext().getSession().get(Constants.BUSINESS_ID_SESSION);
					Long loginUserBusId = UserUtil.getMmrUser().getBusinessId();
					Long partnerId=(Long) ActionContext.getContext().getSession().get(Constants.PARTNER_ID_SESSION);
					Long countryPartnerId=(Long) ActionContext.getContext().getSession().get(Constants.NATION_ID_SESSION);
					Long branchId=(Long) ActionContext.getContext().getSession().get(Constants.BRANCH_ID_SESSION);
					Iterator menuOneLevelItr=levelOneMenus.iterator();
					while(menuOneLevelItr.hasNext()){
						MenuItemVO menuitemVO=(MenuItemVO) menuOneLevelItr.next();
						if((businessId!=null && partnerId ==null && countryPartnerId==null && branchId==null)  && menuitemVO.getUrl()!=null && user.getUserRole().equals(ShiplinxConstants.ROLE_SYSADMIN)
							&& menuitemVO.getUrl().equals(MenuItemVO.BUSINESSFILTER_URL_BUSINESSLEVEL)){
							menuitemVO.setUrl(MenuItemVO.BUSINESSFILTER_URL_PARTNERLEVEL);
						}else if(((businessId!=null || loginUserBusId!=null) && partnerId!=null && countryPartnerId==null && branchId==null) && menuitemVO.getUrl()!=null
								&& (menuitemVO.getUrl().equals(MenuItemVO.BUSINESSFILTER_URL_BUSINESSLEVEL))){
							menuitemVO.setUrl(MenuItemVO.BUSINESSFILTER_URL_NATIONALLEVEL);
							
						}else if(((businessId!=null ||loginUserBusId!=null) && partnerId!=null && countryPartnerId!=null && branchId==null) && menuitemVO.getUrl()!=null
								&& (menuitemVO.getUrl().equals(MenuItemVO.BUSINESSFILTER_URL_BUSINESSLEVEL))){
							
							menuitemVO.setUrl(MenuItemVO.BUSINESSFILTER_URL_BRANCHLEVEL);
						}else if((businessId!=null || loginUserBusId!=null) && partnerId!=null && countryPartnerId!=null && branchId!=null&& menuitemVO.getUrl()!=null
								&& (menuitemVO.getUrl().equals(MenuItemVO.BUSINESSFILTER_URL_BUSINESSLEVEL))  ){
							menuOneLevelItr.remove();
						}else if((businessId!=null || loginUserBusId!=null) && branchId!=null && user.isPartnerLevel() && (menuitemVO.getUrl().equals(MenuItemVO.BUSINESSFILTER_URL_NATIONALLEVEL))){
							 menuOneLevelItr.remove();
						}else if((businessId!=null || loginUserBusId!=null) && branchId==null && user.isBranchLevel() && (menuitemVO.getUrl().equals(MenuItemVO.BUSINESSFILTER_URL_PARTNERLEVEL))){
							 menuOneLevelItr.remove();
						}
					
							
							
					}
					
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
