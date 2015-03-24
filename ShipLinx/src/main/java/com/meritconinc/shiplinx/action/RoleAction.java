package com.meritconinc.shiplinx.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.meritconinc.mmr.dao.ActionsDAO;
import com.meritconinc.mmr.dao.MenusDAO;
import com.meritconinc.mmr.dao.RolesDAO;
import com.meritconinc.mmr.model.common.AuthorizedActionVO;
import com.meritconinc.mmr.model.common.MenuItemVO;
import com.meritconinc.mmr.model.common.RoleVO;
import com.meritconinc.shiplinx.dao.InvoiceDAO;
import com.opensymphony.xwork2.Preparable;

public class RoleAction extends BaseAction implements Preparable,ServletRequestAware{

	private static final Logger log = LogManager.getLogger(RoleAction.class);
	
	private HttpServletRequest request;
	
	private RolesDAO rolesDAO;
	
	
	List<AuthorizedActionVO> actionVO = new ArrayList<AuthorizedActionVO>();
	
	private List<RoleVO> roleList = new ArrayList<RoleVO>();
	
	private List<MenuItemVO> menuVo = new ArrayList<MenuItemVO>();
	
	private RoleVO roleVO = new RoleVO();
	
	private MenusDAO menuItemDAO;
	
	private ActionsDAO actionsDAO;
	
	private String editRoleId;
	
	private int[] selectedActionIds;
	
	private int[] selectedMenuIds;
	
	
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
		
	}

	@Override
	public void prepare() throws Exception {
		// TODO Auto-generated method stub
		
	}

	
	public String listRole(){
		roleList = rolesDAO.getAllRoles();
		getSession().remove("roleEdit");
		return SUCCESS;
	}
	
	public String newRole(){
		log.debug(" CHECK METHOD IN newRole" );
		actionVO = actionsDAO.readAllAction();
		menuVo = menuItemDAO.getAllMenu();
		getSession().put("roleEdit", "false");
		return SUCCESS;
	}
	
	
	public String addRole(){
		System.out.println(roleVO.getRole());
		String roleId = (String)request.getParameter("roleid");
		if(roleId == null || ("").equals(roleId)){
			//adding the new role.
			log.debug(" CHECK METHOD IN addRole ***********   add" );
			try{
				if(validateRole()){
					actionVO = actionsDAO.readAllAction();
					menuVo = menuItemDAO.getAllMenu();
					getSession().put("roleEdit", "false");
					return INPUT;
				}else{
				RoleVO dupRoleVo = rolesDAO.getRoleById(roleVO.getRole());
				if((dupRoleVo != null && !("").equals(dupRoleVo))){
					addActionError(getText("role.duplicate.error"));
					actionVO = actionsDAO.readAllAction();
					menuVo = menuItemDAO.getAllMenu();
					roleVO = new RoleVO();
					getSession().put("roleEdit", "false");
					return INPUT;
				}
				rolesDAO.saveRole(roleVO.getRole(), roleVO.getDescription());
				for(String menuId: roleVO.getMenuIds()){
					rolesDAO.saveRoleMenu(roleVO.getRole(), menuId);
				}
				for(String actionId : roleVO.getActionIds()){
					actionsDAO.addRoleAction(actionId, roleVO.getRole());
				}
				addActionMessage(getText("role.save.sucess"));
				}
			}catch(Exception e){
				log.error("Error in Saving the role :" + e.getMessage());
				addActionError(getText("role.save.error"));
				return INPUT;
			}
			getSession().put("roleEdit", "false");
		}else{
			try{
				
				log.debug(" CHECK METHOD IN addRole ***********   update" );
				//updating the role.
				rolesDAO.updateRole(roleId, roleVO.getDescription());
				
				//inserting the new actions
				List<Integer> actionIds = actionsDAO.getActionIdByRole(roleId);
				
				String[] uiActionIds = roleVO.getActionIds();
				//iterating the  action ids getting from the UI
				for(int i=0;i<roleVO.getActionIds().length;i++){
					//iterating the actions ids from the database
					if(!actionIds.contains(Integer.parseInt(uiActionIds[i]))){
						actionsDAO.addRoleAction(uiActionIds[i], roleId);
					}
					
				}
				//deleting the removed action ids
				for(Integer action : actionIds){
					boolean isOccured = false;
					for(int j=0;j<uiActionIds.length;j++){
						if(uiActionIds[j].equals(action.toString())){
							isOccured = true;
						}
					}
					
					if(!isOccured){
						actionsDAO.deleteRoleAction(roleId, String.valueOf(action));
					}
				}
				
				//inserting the new menu
				List<Integer> menuIds = menuItemDAO.getMenuIdByRole(roleId);
				String[] uiMenuIds = roleVO.getMenuIds();
				
				//iterating the menu ids getting from the UI
				for(int i=0;i<roleVO.getMenuIds().length;i++){
					//iterating the menu ids from the database
					if(!menuIds.contains(Integer.parseInt(uiMenuIds[i]))){
						//add the menu
						
						rolesDAO.saveRoleMenu(roleId, uiMenuIds[i]);
					}
				}
				
				//deleting the removed menu ids
				for(Integer menu : menuIds){
					boolean isOccured = false;
					for(int j=0;j<uiMenuIds.length;j++){
						if(uiMenuIds[j].equals(menu.toString())){
							isOccured = true;
						}
					}
					
					if(!isOccured){
						//delete the menu
						menuItemDAO.deleteRoleMenu(roleId, String.valueOf(menu));
					}
				}
				addActionMessage(getText("role.update.success"));
			}catch(Exception e){
				log.error("Error in updating the role :" + e.getMessage());
				addActionError(getText("role.update.error"));
				return INPUT;
			}
		}
		return SUCCESS;
	}
	
	private boolean validateRole() {
		// TODO Auto-generated method stub
		int i=0;
		if(roleVO.getRole().equals("")){
			addActionError("Role Name Required");
			i++;
		}
		if(roleVO.getActionIds()== null || roleVO.getActionIds().length==0){
			addActionError("Select Any Action");
			i++;
		}
		if(roleVO.getMenuIds()==null || roleVO.getMenuIds().length==0){
			addActionError("Select Any Menu");
			i++;
		}
		if(i==0){
			return false;
		}else if(i>0){
			return true;
		}
		return false;
	}

	public String editRole(){
		try{
			log.debug(" CHECK METHOD IN editRole" );
			roleVO = rolesDAO.getRoleById(editRoleId);
			actionVO = actionsDAO.readAllAction();
			menuVo = menuItemDAO.getAllMenu();
			
			//getting the selected Action ids for the corresponding role
			List<Integer> actionIds = actionsDAO.getActionIdByRole(editRoleId);
			selectedActionIds = new int[actionIds.size()];
			int count=0;
			for(int ids : actionIds){
				selectedActionIds[count] = ids;
				count++;
			}
			
			//getting the selected menu ids for the corresponding role
			
			List<Integer> menuIds = menuItemDAO.getMenuIdByRole(editRoleId);
			selectedMenuIds = new int[menuIds.size()];
			
			count = 0;
			for(int ids : menuIds){
				selectedMenuIds[count] = ids;
				count++;
			}
			
			getSession().put("roleEdit", "true");
			//roleVO.setActionIds(strAction);
			//addActionMessage(getText("role.update.success"));
			return SUCCESS;
			
		}catch(Exception e){
			log.error("Error in loading the role :" + e.getMessage());
			addActionError(getText("role.update.error"));
			return INPUT;
		}
	}
	
	
	public String deleteRole(){

		try{
			log.debug(" CHECK METHOD IN deleteRole" );
			menuItemDAO.deleteMenuByRole(editRoleId);
			actionsDAO.deleteActionByRole(editRoleId);
			rolesDAO.deleteRole(editRoleId);
			addActionMessage(getText("role.delete.success"));
			return SUCCESS;
		}catch(Exception e){
			log.error("Error in Deleting the role :" + e.getMessage());
			addActionError(getText("role.delete.error"));
			return INPUT;
		}
	}
	
	
	public String updateRole(){
		System.out.println("-->"+roleVO.getRole());
		return SUCCESS;
	}
	
	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public RolesDAO getRolesDAO() {
		return rolesDAO;
	}

	public void setRolesDAO(RolesDAO rolesDAO) {
		this.rolesDAO = rolesDAO;
	}

	public List<RoleVO> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<RoleVO> roleList) {
		this.roleList = roleList;
	}

	public List<AuthorizedActionVO> getActionVO() {
		return actionVO;
	}

	public void setActionVO(List<AuthorizedActionVO> actionVO) {
		this.actionVO = actionVO;
	}

	public List<MenuItemVO> getMenuVo() {
		return menuVo;
	}

	public void setMenuVo(List<MenuItemVO> menuVo) {
		this.menuVo = menuVo;
	}

	public RoleVO getRoleVO() {
		return roleVO;
	}

	public void setRoleVO(RoleVO roleVO) {
		this.roleVO = roleVO;
	}

	public MenusDAO getMenuItemDAO() {
		return menuItemDAO;
	}

	public void setMenuItemDAO(MenusDAO menuItemDAO) {
		this.menuItemDAO = menuItemDAO;
	}

	public ActionsDAO getActionsDAO() {
		return actionsDAO;
	}

	public void setActionsDAO(ActionsDAO actionsDAO) {
		this.actionsDAO = actionsDAO;
	}

	public String getEditRoleId() {
		return editRoleId;
	}

	public void setEditRoleId(String editRoleId) {
		this.editRoleId = editRoleId;
	}

	public int[] getSelectedActionIds() {
		return selectedActionIds;
	}

	public void setSelectedActionIds(int[] selectedActionIds) {
		this.selectedActionIds = selectedActionIds;
	}

	public int[] getSelectedMenuIds() {
		return selectedMenuIds;
	}

	public void setSelectedMenuIds(int[] selectedMenuIds) {
		this.selectedMenuIds = selectedMenuIds;
	}

	
	
	

}
