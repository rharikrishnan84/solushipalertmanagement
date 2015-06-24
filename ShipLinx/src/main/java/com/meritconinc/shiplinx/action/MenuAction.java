package com.meritconinc.shiplinx.action;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.meritconinc.mmr.dao.MenusDAO;
import com.meritconinc.mmr.dao.PropertyDAO;
import com.meritconinc.mmr.model.common.LocaleVO;
import com.meritconinc.mmr.model.common.PropertyVO;
import com.meritconinc.mmr.utilities.security.UserUtil;
import com.opensymphony.xwork2.Preparable;
import com.soluship.businessfilter.util.BusinessFilterUtil;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

import com.meritconinc.mmr.dao.MessageDAO;
import com.meritconinc.mmr.dao.RolesDAO;
import com.meritconinc.mmr.model.common.MenuItemVO;
import com.meritconinc.mmr.model.common.RoleVO;
import com.meritconinc.shiplinx.dao.BusinessDAO;
import com.meritconinc.shiplinx.model.Business;
import com.meritconinc.shiplinx.utils.ShiplinxConstants;
import com.meritconinc.mmr.model.common.MenuScreenVO;


/**
 * <code>Set welcome message.</code>
 */
public class MenuAction extends BaseAction implements Preparable,ServletRequestAware{
	private static final long serialVersionUID	= 25092007;
	private MenusDAO menuItemDAO;
	private PropertyDAO propertyDAO;
	private static final Logger log = LogManager.getLogger(MenuAction.class);
	private HttpServletRequest request;
	private Map<String, Long> menuId = new HashMap<String, Long>();
	private String url;

	
	private List<MenuItemVO> menuVo = new ArrayList<MenuItemVO>();
					 
						private BusinessDAO businessDAO;
						
						private List<Business> businessList = new ArrayList<Business>();
						
						private List<MenuItemVO> topMenuVo = new ArrayList<MenuItemVO>();
						
						private List<MenuItemVO> firstMenuVo = new ArrayList<MenuItemVO>();
						
						private List<LocaleVO> localeList = new ArrayList<LocaleVO>();
						
						private MessageDAO messageDAO;
						
						private RolesDAO rolesDAO;
						
						private List<RoleVO> roleList = new ArrayList<RoleVO>();
						
						private MenuScreenVO menuScreenVO;
						
						private String editMenuId;
						
						private long[] selectedBusinessIds;
						private long topLevelId;
					    private long firstlevelId;
						
						
						public String listMenu(){
									
									try
								{
										menuVo = menuItemDAO.getAllMenu();
							}catch(Exception e){
										return ERROR;
								}
									return SUCCESS;
								}
						
								
								public String addMenu(){
									businessList = businessDAO.getAllBusiness();
									localeList= messageDAO.getLocales();
									roleList = rolesDAO.getAllRoles();
									return SUCCESS;
								}
								
								public String loadMenuLevel(){
									
									System.out.println("fsdfsdfsd" + request.getParameter("levelValue"));
									
									String levelValue = request.getParameter("levelValue");
									if(levelValue!=null){
										String businessId = request.getParameter("businessId");
										
										String locale= request.getParameter("locale");
										topMenuVo = menuItemDAO.getTopMenuByBusiness(businessId,locale);
										String level = request.getParameter("level");
										if(level.equals("second") &&levelValue.equals("30")){
											String topLevelId = request.getParameter("topLevelId");
											firstMenuVo = menuItemDAO.getFirstMenuByTop(businessId, topLevelId);
											return "firstlevel";
										}else if(levelValue.equals("-1") || levelValue.equals("10")){
											return "emptylevel";
										} 
										
										return SUCCESS;
									}else{
										return "emptylevel";
									}
								}
							
								public String saveMenu(){
									 log.debug(" CHECK METHOD IN SAVE MENU ***********   " + request.getParameter("method"));
									try{
										   if (request.getParameter("method").equals("add")) {
											     if(validateMenu()){
											    	 
											     }else{
														System.out.println(menuScreenVO.getMenuName());
														MenuItemVO menuItemVO = convertMenuScreenToMenuItem(menuScreenVO);
														messageDAO.saveResource(menuScreenVO.getMsgKey(), menuScreenVO.getMsgContent(), menuScreenVO.getLocale());
														String menuId = menuItemDAO.saveMenu(menuItemVO);
														for(Integer businessName : menuScreenVO.getBusiness()){
															menuItemDAO.saveBusinessMenu(businessName.toString(), menuId);
														}
														for(String menuRole : menuScreenVO.getMenuRole()){
															rolesDAO.saveRoleMenu(menuRole, menuId);
														}
														addActionMessage(getText("menu.save.success"));
														return SUCCESS;
											     }
										   }
										 else if(request.getParameter("method").equals("update")){
											  int count = 0;
											  String menuid=(String)getSession().get("menuId");
											  
											  MenuItemVO menuItemVO =  convertMenuScreenToMenuItem(menuScreenVO);
											  menuScreenVO.setMenulevel(request.getParameter("Menulevel"));
											  menuItemVO.setLevel(menuScreenVO.getMenulevel());
											  if(menuScreenVO.getMenulevel().equals("LEVEL_2")){
												  menuItemVO.setParentId(Integer.parseInt(request.getParameter("Firstlevel")));  
											  }else if(menuScreenVO.getMenulevel().equals("LEVEL_1")){
												  menuItemVO.setParentId(Integer.parseInt(request.getParameter("Toplevel")));
											  }
											  else{
												  menuItemVO.setParentId(0);  
											  }
											 
											  menuItemVO.setId(Integer.parseInt(menuid));
											  menuItemDAO.updateMenu(menuItemVO,menuScreenVO);
											  String newRoles[]=request.getParameterValues("menuScreenVO.menuRole");
											List<String> listRoleName = menuItemDAO.getRoleByMenuId(menuid);
											List<String> newRlist=new ArrayList<String>(Arrays.asList(newRoles));
										List<String> newRole= menuItemDAO.getRoleByMenuId(menuid);
											listRoleName.removeAll(newRlist);
											newRlist.removeAll(newRole);
											 //add new roles
											for(String role:newRlist){
			
												rolesDAO.saveRoleMenu(role,menuid);
											}
											
												//update roles
											 for(String role:listRoleName){
												 rolesDAO.deleteRoleByMenuId(menuid,role);
											 }
											 
											 //business
											 String[] updbus=request.getParameterValues("menuScreenVO.business");
											 if(updbus!=null){
											 List<Integer> listBusinessName = menuItemDAO.getBusinessByMenuId(menuid);
											 List<Integer> newbuslist=new ArrayList<Integer>();
											 for(String b:updbus){
											 newbuslist.add(Integer.parseInt(b));
											 }
											 List<Integer>  oldbuslist = menuItemDAO.getBusinessByMenuId(menuid);
									 listBusinessName.removeAll(newbuslist);
											 newbuslist.removeAll(oldbuslist);
									 //add new business
									 for(Integer id:newbuslist){
										 menuItemDAO.saveBusinessMenu(id.toString(), menuid);
										 }
											 //update business
										 for(Integer id:listBusinessName){
												 menuItemDAO.deleteBusinessMenuById (id.toString(), menuid);
									 }
									 }
											 else{
											 List<Integer>  oldbuslist = menuItemDAO.getBusinessByMenuId(menuid);
												 for(Integer id:oldbuslist){
													 menuItemDAO.deleteBusinessMenuById (id.toString(), menuid);
												 }
											 }
											  log.debug("EDITTED SUCCESSFULLY ");
										      addActionMessage(getText("Menu.info.update.successfully"));
										      getSession().remove("edit");
										      getSession().remove("menuId");
							      
									  return SUCCESS;
									   }
									}catch(Exception e){
										log.error("Error In saving the menu :"+ e.getMessage());
									addActionError(getText("menu.save.error"));
									 
										return INPUT;
									}
									businessList = businessDAO.getAllBusiness();
								localeList= messageDAO.getLocales();
								roleList = rolesDAO.getAllRoles();
									return INPUT;
							}
								
								private boolean validateMenu() {
									// TODO Auto-generated method stub
									int i=0;
									 String[] updbus=request.getParameterValues("menuScreenVO.business");
									 String newRoles[]=request.getParameterValues("menuScreenVO.menuRole");
								if(menuScreenVO.getMenuName().equals("")){
									addActionError("Menu Name Required");
									i++;
									}
									if(menuScreenVO.getMenuUrl().equals("")){
										addActionError("Menu Url Required");
										i++;
									}
									if(menuScreenVO.getMenulevel().equals("-1")){
										addActionError("Select Any Menu Level");
										i++;
									}
									if(menuScreenVO.getTopLevel()!=null){
										if((!menuScreenVO.getMenulevel().equals("20")) && menuScreenVO.getTopLevel().equals("-1")){
											addActionError("Select Any Top Level Menu");
											i++;
								 		}
							     	}
									if(menuScreenVO.getTopLevel()!=null && menuScreenVO.getFirstLevel()!=null){
										if((!menuScreenVO.getMenulevel().equals("20") || !menuScreenVO.getMenulevel().equals("30")) && (!menuScreenVO.getTopLevel().equals("-1")) && menuScreenVO.getFirstLevel().equals("-1")){
											addActionError("Select Any First Level Menu");
											i++;
										}
									}
									if(newRoles==null){
										addActionError("Select Menu Role");
										i++;
									}
									if(menuScreenVO.getLocale().equals("-1")|| menuScreenVO.getLocale().equals("")){
										addActionError("Select Any Locale");
										i++;
									}
									if(menuScreenVO.getMsgContent().equals("")){
										addActionError("Message Content is Required");
										i++;
									}
									if(menuScreenVO.getMsgKey().equals("")){
										addActionError("Message Key is Required");
										i++;
									}
								if(updbus==null){
									addActionError("Select Any Business");
										i++;
									}
									if(i==0){
										return false;
									}else if(i>0){
										return true;
									}
									return false;
								}
		
								public String editMenu(){
									try{
										
										System.out.println(editMenuId);
										getSession().put("menuId",editMenuId);
										menuScreenVO = convertMenuItemToMenuScreen(menuItemDAO.getMenuOnlyById(editMenuId));
									businessList = businessDAO.getAllBusiness();
										localeList= messageDAO.getLocales();
									roleList = rolesDAO.getAllRoles();
										int count = 0;
							       if(menuScreenVO.getMenulevel().equals("LEVEL_2")){
							    	   setFirstlevelId(Long.parseLong(editMenuId));
								    	   setTopLevelId(getFirstlevelId());
								       }else if(menuScreenVO.getMenulevel().equals("LEVEL_1")){
								    	   setTopLevelId(Long.parseLong(editMenuId));
								       }
								        
								      
										getSession().put("edit", "true");
										//setting the role for the corresponding menu
									List<String> listRoleName = menuItemDAO.getRoleByMenuId(editMenuId);
									 	String[] arrRoleName = new String[listRoleName.size()];
									for(String roleName : listRoleName){
										arrRoleName[count] = roleName;
											count++;
										}
										
										menuScreenVO.setMenuRole(arrRoleName);
										
									//setting the business for the corresponding menu
										List<Integer> listBusinessName = menuItemDAO.getBusinessByMenuId(editMenuId);
										selectedBusinessIds = new long[listBusinessName.size()];
										int countBusiness = 0;
										for(Integer businessName : listBusinessName){
											selectedBusinessIds[countBusiness] = businessName;
											countBusiness++;
										}
										
										//setting the resource bundle to the screen
										LocaleVO localeVO = messageDAO.getLocaleByMsgId(menuScreenVO.getMsgKey());
										
										menuScreenVO.setMsgContent(localeVO.getDescription());
										menuScreenVO.setLocale(localeVO.getLocale());
										menuScreenVO.setMenuName(localeVO.getDescription());
										return SUCCESS;
									}catch(Exception e){
										log.error("Error in editing the Menu :"+ e.getMessage());
										return INPUT;
										
									}
								}
								
						public MenuItemVO convertMenuScreenToMenuItem(MenuScreenVO menuScreenVO){
									MenuItemVO menuItemVO = new MenuItemVO();
								menuItemVO.setName(menuScreenVO.getMenuName());
									menuItemVO.setUrl(menuScreenVO.getMenuUrl());
									menuItemVO.setDisplayOrder(new Integer(menuScreenVO.getDisplayOrder()));
									String parentId = "0";
									String menuLevel="";
									if(menuScreenVO.getMenulevel()!=null){
										if(menuScreenVO.getMenulevel().equals("-1") || menuScreenVO.getMenulevel().equals("10")){
											parentId = "0";
										menuLevel="TOP";
										}else if(menuScreenVO.getMenulevel().equals("20")){
											parentId = menuScreenVO.getTopLevel();
											menuLevel="LEVEL_1";
										}else if(menuScreenVO.getMenulevel().equals("30")){
											parentId = menuScreenVO.getFirstLevel();
											menuLevel="LEVEL_2";
									}
									}
									menuItemVO.setLevel(menuLevel);
									menuItemVO.setParentId(new Integer(parentId));
									menuItemVO.setLabelId(menuScreenVO.getMsgKey());
									return menuItemVO;
								}
								
								public MenuScreenVO convertMenuItemToMenuScreen(MenuItemVO menuItemVO){
									MenuScreenVO menuScreenVO = new MenuScreenVO();
									//menuScreenVO.setBusiness(business);
									menuScreenVO.setDisplayOrder(String.valueOf(menuItemVO.getDisplayOrder()));
									menuScreenVO.setMenuName(menuItemVO.getName());
									menuScreenVO.setMenuUrl(menuItemVO.getUrl());
									menuScreenVO.setMenulevel(menuItemVO.getLevel());
									//menuScreenVO.setLocale(menuItemVO.get);
								menuScreenVO.setParentId(String.valueOf(menuItemVO.getParentId()));
								menuScreenVO.setMsgKey(menuItemVO.getName());
									return menuScreenVO;
								}
								
								
								public String deleteMenu(){
									System.out.println(editMenuId);
									 log.debug(" CHECK METHOD IN DELETE MENU ");
									try{
									menuItemDAO.deleteMenuBusiness(editMenuId);
										menuItemDAO.deleteMenuRole(editMenuId);
										menuItemDAO.deleteMenu(editMenuId);
										addActionMessage(getText("menu.delete.success"));
										return SUCCESS;
									}catch(Exception e){
										log.error("Error in deleting the Menu :"+ e.getMessage());
										addActionError(getText("menu.delete.error"));
										return INPUT;
									}
								} 
	
	
	
	
	
	public String getUrl() {
		return url;
	}
	private InputStream inputStream;
		
	

	public InputStream getInputStream() {
		return inputStream;
	}


	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}


	public String execute() throws Exception {
		
		log.debug("--------execute----MenuAction-----"); 
		url= /*request.getContextPath() + */request.getParameter("value");
		com.meritconinc.mmr.model.security.User user = UserUtil.getMmrUser();
		String menu = request.getParameter("menu");
		getSession().remove("HighLightMenu");
		getSession().put("HighLightMenu", menu);
		return "success";
	} 
	
	
	public void setUrl(String url) {
		this.url = url;
	}

	public void prepare() throws Exception {
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
	public String  help() {
		log.debug("--------Help----MenuAction-----");
		System.out.println("Test Help Method");
		String user = UserUtil.getMmrUser().getUsername();
		String highLightMenu = getSession().get("HighLightMenu").toString();
		LocaleVO locale = menuItemDAO.getLanguageByUserName(user);
		String language = locale.getLocaleText();
		 String string="";

		   FileInputStream fis = null;
		         BufferedReader reader = null;
		    long bid=BusinessFilterUtil.setBusinessIdbyUserLevel();
		    Business bus=BusinessFilterUtil.getSuperParentBusiness(bid);
		 /*long bid=UserUtil.getMmrUser().getBusinessId();*/
		    long bid1=0;
		    if(bus!=null)
		    {
		      bid1=bus.getId();
		    }
		   String dirName="help_"+bid1;
		       /* String reportPath = "/home/soluship/solushipfiles"+"/helpMenu/"+dirName;*/
		   String reportPath = ShiplinxConstants.BUSINESS_HELP_DIR+dirName;
		       String fileName = null;
		       File directory = new File(reportPath);
		   if (directory.isDirectory()) {
		   
		    File file = new File(reportPath);
		    
		    if (file.canRead()) {
		     for (File temp : file.listFiles()) {
		      if (temp.getName().toLowerCase()
		          .endsWith(".html")) {
		       fileName = temp.getAbsoluteFile().toString();
		      }
		     }
		    }
		          }
	        //reading   
	        try{

	        	String path = request.getHeader("Referer");
	        	 String index[]=path.split("/");
	        	 String method =index[index.length-1].substring(0,index[index.length-1].lastIndexOf("."));
	       	    url = method+"."+language;
	       	 PropertyVO propertyVO = propertyDAO.getPath(language); 
	       		            url = propertyVO.getDbValue();
	       		            String file = url+"/"+method+".html";
	       		            if(fileName!=null){
	       		            inputStream = new FileInputStream(fileName);
	       		            }else{
	       		             inputStream = new FileInputStream(file);
	       		            }
	        }       
	        catch (Exception e){
	            System.out.println(e.toString());
	        }
		return SUCCESS;
		
	}
	
	public String  contact() {
				log.debug("--------Contact----MenuAction-----");
				System.out.println("Test Contact Method");
				String user = UserUtil.getMmrUser().getUsername();
				String highLightMenu = getSession().get("HighLightMenu").toString();
				LocaleVO locale = menuItemDAO.getLanguageByUserName(user);
				String language = locale.getLocaleText();
				 String string="";
		
				   FileInputStream fis = null;
				         BufferedReader reader = null;
				    long bid=BusinessFilterUtil.setBusinessIdbyUserLevel();
				    Business bus=BusinessFilterUtil.getSuperParentBusiness(bid);
				 /*long bid=UserUtil.getMmrUser().getBusinessId();*/
				    long bid1=0;
				    if(bus!=null)
				    {
				      bid1=bus.getId();
				    }
				   String dirName="contact_"+bid1;
				   Business busi = businessDAO.getBusiessById(bid1);
				   String reportPath = busi.getContactPath();
				   if(reportPath == null){
					   reportPath = ShiplinxConstants.BUSINESS_HELP_DIR+dirName;
				   }else{
					   String dirName1 = "/"+"contact_"+bid1;
					   reportPath = reportPath+dirName1;
				   }
				       String fileName = null;
				       File directory = new File(reportPath);
				   if (directory.isDirectory()) {
				   
				    File file = new File(reportPath);
				    
				    if (file.canRead()) {
				     for (File temp : file.listFiles()) {
				      if (temp.getName().toLowerCase()
				          .endsWith(".html")) {
				       fileName = temp.getAbsoluteFile().toString();
				      }
				     }
				    }
				          }
			        //reading   
			        try{
		
			        	String path = request.getHeader("Referer");
			        	 String index[]=path.split("/");
			        	 String method =index[index.length-1].substring(0,index[index.length-1].lastIndexOf("."));
			       	    url = method+"."+language;
			       	 PropertyVO propertyVO = propertyDAO.getPath(language); 
			       		            url = propertyVO.getDbValue();
			       		            String file = url+"/"+method+".html";
			       		            if(fileName!=null){
			       		            inputStream = new FileInputStream(fileName);
			       		            }else{
			       		             inputStream = new FileInputStream(file);
			       		            }
			        }       
			        catch (Exception e){
			            System.out.println(e.toString());
			        }
				return SUCCESS;
				
			}
			
			public String  feedback() {
				log.debug("--------feedback----MenuAction-----");
				System.out.println("Test feedback Method");
				String user = UserUtil.getMmrUser().getUsername();
				String highLightMenu = getSession().get("HighLightMenu").toString();
				LocaleVO locale = menuItemDAO.getLanguageByUserName(user);
				String language = locale.getLocaleText();
				 String string="";
		
				   FileInputStream fis = null;
				         BufferedReader reader = null;
				    long bid=BusinessFilterUtil.setBusinessIdbyUserLevel();
				    Business bus=BusinessFilterUtil.getSuperParentBusiness(bid);
				 /*long bid=UserUtil.getMmrUser().getBusinessId();*/
				    long bid1=0;
				    if(bus!=null)
				    {
				      bid1=bus.getId();
				    }
				   String dirName="feedback"+bid1;
				   Business busi = businessDAO.getBusiessById(bid1);
				   String reportPath = busi.getFeedbackPath();
				   if(reportPath == null){
					   reportPath = ShiplinxConstants.BUSINESS_HELP_DIR+dirName;
				   }else{
					   String dirName1 = "/"+"feedback_"+bid1;
					   reportPath = reportPath+dirName1;
				   }
				       String fileName = null;
				       File directory = new File(reportPath);
				   if (directory.isDirectory()) {
				   
				    File file = new File(reportPath);
				    
				    if (file.canRead()) {
				     for (File temp : file.listFiles()) {
				      if (temp.getName().toLowerCase()
				          .endsWith(".html")) {
				       fileName = temp.getAbsoluteFile().toString();
				      }
				     }
				    }
				          }
			        //reading   
			        try{
		
			        	String path = request.getHeader("Referer");
			        	 String index[]=path.split("/");
			        	 String method =index[index.length-1].substring(0,index[index.length-1].lastIndexOf("."));
			       	    url = method+"."+language;
			       	 PropertyVO propertyVO = propertyDAO.getPath(language); 
			       		            url = propertyVO.getDbValue();
			       		            String file = url+"/"+method+".html";
			       		            if(fileName!=null){
			       		            inputStream = new FileInputStream(fileName);
			       		            }else{
			       		             inputStream = new FileInputStream(file);
			       		            }
			        }       
			        catch (Exception e){
			            System.out.println(e.toString());
			        }
				return SUCCESS;
				
			}

	public MenusDAO getMenuItemDAO() {
		return menuItemDAO;
	}

	public void setMenuItemDAO(MenusDAO menuItemDAO) {
		this.menuItemDAO = menuItemDAO;
	}
	
	public PropertyDAO getPropertyDAO() {
				return propertyDAO;
			}
		
		
			public void setPropertyDAO(PropertyDAO propertyDAO) {
				this.propertyDAO = propertyDAO;
			}
			
			
			
			public List<MenuItemVO> getMenuVo() {
																				return menuVo;
																			}
																
																
																			public void setMenuVo(List<MenuItemVO> menuVo) {
																			this.menuVo = menuVo;
																			}
															
																
																		public BusinessDAO getBusinessDAO() {
																			return businessDAO;
																			}
																
																
																			public void setBusinessDAO(BusinessDAO businessDAO) {
																				this.businessDAO = businessDAO;
																	}
																
																
																			public List<Business> getBusinessList() {
																				return businessList;
																		}
																
																
																			public void setBusinessList(List<Business> businessList) {
																			this.businessList = businessList;
																			}
																
																
																		public List<MenuItemVO> getTopMenuVo() {
																				return topMenuVo;
																			}
																
															
																			public void setTopMenuVo(List<MenuItemVO> topMenuVo) {
																				this.topMenuVo = topMenuVo;
																			}
																
																 
																		public List<MenuItemVO> getFirstMenuVo() {
																	 			return firstMenuVo;
																			}
																
																
																			public void setFirstMenuVo(List<MenuItemVO> firstMenuVo) {
																				this.firstMenuVo = firstMenuVo;
																			}
																
															
																			public MessageDAO getMessageDAO() {
																				return messageDAO;
																			}
																
															
																			public void setMessageDAO(MessageDAO messageDAO) {
																	this.messageDAO = messageDAO;
																		}
																
																
																			public List<LocaleVO> getLocaleList() {
																				return localeList;
																			}
																
																
																			public void setLocaleList(List<LocaleVO> localeList) {
																				this.localeList = localeList;
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
																
																
																			public MenuScreenVO getMenuScreenVO() {
																				return menuScreenVO;
																			}
																
																
																			public void setMenuScreenVO(MenuScreenVO menuScreenVO) {
																				this.menuScreenVO = menuScreenVO;
																			}
																
																
																			public String getEditMenuId() {
																				return editMenuId;
																		}
															
																
																			public void setEditMenuId(String editMenuId) {
																				this.editMenuId = editMenuId;
																			}
															
																
																			public long[] getSelectedBusinessIds() {
																				return selectedBusinessIds;
																			}
															
																
																		public void setSelectedBusinessIds(long[] selectedBusinessIds) {
																			this.selectedBusinessIds = selectedBusinessIds;
																		}
								
							
																		public long getTopLevelId() {
																			return topLevelId;
																			}
								
								
																			public void setTopLevelId(
																					long menuId) {
																				this.topLevelId =  menuItemDAO.getParentIdByMenuId(menuId);
																			}
							
								
																			public long getFirstlevelId() {
																				return firstlevelId;
																			}
								
								
																			public void setFirstlevelId(
																					long firstlevelId) {
																			this.firstlevelId =  menuItemDAO.getParentIdByMenuId(firstlevelId);;
																			}
							
				
}