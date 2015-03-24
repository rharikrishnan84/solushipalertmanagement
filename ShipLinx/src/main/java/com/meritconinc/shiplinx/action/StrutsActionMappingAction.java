package com.meritconinc.shiplinx.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.views.velocity.components.ActionDirective;

import com.meritconinc.mmr.dao.ActionsDAO;
import com.meritconinc.mmr.dao.MenusDAO;
import com.meritconinc.mmr.dao.RolesDAO;
import com.meritconinc.mmr.model.common.AuthorizedActionVO;
import com.meritconinc.mmr.model.common.MenuItemVO;
import com.meritconinc.mmr.model.common.RoleVO;
import com.opensymphony.xwork2.Preparable;

public class StrutsActionMappingAction extends BaseAction implements Preparable,ServletRequestAware{

	private static final Logger log = LogManager.getLogger(StrutsActionMappingAction.class); 
	
	private HttpServletRequest request;
	
	private MenusDAO menuItemDAO;
	
	private ActionsDAO actionsDAO;
	
	private List<AuthorizedActionVO> actionsVO = new ArrayList<AuthorizedActionVO>();
	
	AuthorizedActionVO actionVO = new AuthorizedActionVO();
	
	private List<RoleVO> roleList = new ArrayList<RoleVO>();
	
	private List<MenuItemVO> menuVo = new ArrayList<MenuItemVO>();
	
	private RolesDAO rolesDAO;
	
	private String editActionId;
	
	private String[] roleNames;
	
	public String listAction(){
		actionsVO = actionsDAO.readAllAction();
		return SUCCESS;
	}
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
		
	}
	
	
	public String newAction(){
		try
		{   log.debug(" CHECK METHOD IN NEW ACTION");
			menuVo = menuItemDAO.getAllMenu();
			roleList = rolesDAO.getAllRoles();
			return SUCCESS;
		}catch(Exception e){
			log.error("Error in loading the new Action :"+ e.getMessage());
			addActionError(getText("action.all.error"));
			return ERROR;
		}
	}

	
	public String addAction(){
		try{
			String paramaActionId = request.getParameter("actionid");
			if(paramaActionId!=null && !paramaActionId.equals("") && !paramaActionId.equals("0")){
				//updating the action
				 log.debug(" CHECK METHOD IN addAction ***********   update" );
				AuthorizedActionVO actionVOOld  =actionsDAO.getActionById(paramaActionId);
				
				List<String> OldRlist=new ArrayList<String>(Arrays.asList(rolesDAO.getRolesByActions(paramaActionId)));
				List<String> OldRlist1=new ArrayList<String>(OldRlist);
				List<String> newRlist=new ArrayList<String>(Arrays.asList(actionVO.getRoleNames()));
				OldRlist.removeAll(newRlist);
				newRlist.removeAll(OldRlist1);
                				
				actionVO.setId(Integer.parseInt(paramaActionId));
				actionsDAO.updateAction(actionVO);
				//add new updated roles
				for(String role:newRlist){
					actionsDAO.addRoleAction(String.valueOf(paramaActionId),role);
				}
				//delete updated roles
				for(String role:OldRlist){
					actionsDAO.deleteRoleAction(role, String.valueOf(paramaActionId));
				}
				/*if(actionVO.getRoleNames()!=null && actionVO.getRoleNames().length >0){
					for(int i=0; i<actionVO.getRoleNames().length; i++){
						actionsDAO.addRoleAction(String.valueOf(paramaActionId), actionVO.getRoleNames()[i]);
					}
				}*/
				addActionMessage(getText("action.update.success"));
				return SUCCESS;
			}else{
				//inserting the action
				if(validateAction()){
					menuVo = menuItemDAO.getAllMenu();
					roleList = rolesDAO.getAllRoles();
					return INPUT;
				}else{
					log.debug(" CHECK METHOD IN addAction ***********   add" );
					
					
					int actionId = actionsDAO.addAction(actionVO);
					if(actionVO.getRoleNames()!=null && actionVO.getRoleNames().length >0){
						for(int i=0; i<actionVO.getRoleNames().length; i++){
							actionsDAO.addRoleAction(String.valueOf(actionId), actionVO.getRoleNames()[i]);
						}
					}
					addActionMessage(getText("action.save.success"));
				}
				return SUCCESS;
				}
		}catch(Exception e){
			log.error("Error in Inserting or Updating the Action :"+ e.getMessage());
			addActionError(getText("action.save.error"));
			return INPUT;
		}
	}
	
	
	private boolean validateAction() {
		// TODO Auto-generated method stubac
		int i=0;
		 if(actionVO.getActionKey().equals("")){
			 addActionError("Action Name Required");
				i++;
		 }
		 if(actionVO.getMenuId()==-1){
			 addActionError("Select Menu");
				i++;
		 }
		 if(actionVO.getRoleNames()==null ||actionVO.getRoleNames().length==0){
			 addActionError("Select Any Roles");
				i++;
		 }
		 
		if(i==0){
			return false;
		}else if(i>0){
			return true;
		}
		return false;
	}
	public String deleteAction(){
		try{
			log.debug(" CHECK METHOD IN deleteAction" );
			actionsDAO.deleteAction(editActionId);
			addActionMessage(getText("action.delete.success"));
			return SUCCESS;
		}catch(Exception e){
			log.error("Error in deleting the Action :"+ e.getMessage());
			addActionError(getText("action.delete.error"));
			return INPUT;
		}
	}
	
	public String editAction(){
		try{
			log.debug(" CHECK METHOD IN editAction" );
			actionVO = actionsDAO.getActionById(editActionId);
			menuVo = menuItemDAO.getAllMenu();
			roleList = rolesDAO.getAllRoles();
			
			roleNames = rolesDAO.getRolesByActions(editActionId);
			return SUCCESS;
			
		}catch(Exception e){
			log.error("Error in editing the action :"+e.getMessage());
			addActionError(getText("action.all.error"));
			return INPUT;
		}
	}
	@Override
	public void prepare() throws Exception {
		// TODO Auto-generated method stub
		
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
	public List<AuthorizedActionVO> getActionsVO() {
		return actionsVO;
	}
	public void setActionsVO(List<AuthorizedActionVO> actionsVO) {
		this.actionsVO = actionsVO;
	}
	public AuthorizedActionVO getActionVO() {
		return actionVO;
	}
	public void setActionVO(AuthorizedActionVO actionVO) {
		this.actionVO = actionVO;
	}
	public List<RoleVO> getRoleList() {
		return roleList;
	}
	public void setRoleList(List<RoleVO> roleList) {
		this.roleList = roleList;
	}
	public List<MenuItemVO> getMenuVo() {
		return menuVo;
	}
	public void setMenuVo(List<MenuItemVO> menuVo) {
		this.menuVo = menuVo;
	}
	public RolesDAO getRolesDAO() {
		return rolesDAO;
	}
	public void setRolesDAO(RolesDAO rolesDAO) {
		this.rolesDAO = rolesDAO;
	}
	public String getEditActionId() {
		return editActionId;
	}
	public void setEditActionId(String editActionId) {
		this.editActionId = editActionId;
	}
	public String[] getRoleNames() {
		return roleNames;
	}
	public void setRoleNames(String[] roleNames) {
		this.roleNames = roleNames;
	}
	
	
	

}
