
package com.meritconinc.shiplinx.action;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Random;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.image4j.codec.ico.ICODecoder;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.meritconinc.mmr.constants.Constants;
import com.meritconinc.mmr.dao.BusinessFilterDAO;
import com.meritconinc.mmr.dao.MenusDAO;
import com.meritconinc.mmr.dao.UserDAO;
import com.meritconinc.shiplinx.model.BusinessFilter;
import com.meritconinc.shiplinx.model.UserBusiness;
import com.meritconinc.mmr.model.common.MenuItemVO;
import com.meritconinc.mmr.model.security.User;
import com.meritconinc.mmr.utilities.MessageUtil;
import com.meritconinc.mmr.utilities.MmrBeanLocator;
import com.meritconinc.mmr.utilities.security.UserUtil;
import com.meritconinc.shiplinx.dao.AddressDAO;
import com.meritconinc.shiplinx.dao.BusinessDAO;
import com.meritconinc.shiplinx.model.Address;
import com.meritconinc.shiplinx.model.Business;
import com.meritconinc.shiplinx.model.Country;
import com.meritconinc.shiplinx.model.CountryPartner;
import com.meritconinc.shiplinx.model.EdiItem;
import com.meritconinc.shiplinx.model.Partner;
import com.meritconinc.shiplinx.model.UserFilter;
import com.meritconinc.shiplinx.utils.BusinessDefaultLoaderDaoImpl;
import com.meritconinc.shiplinx.utils.ShiplinxConstants;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.Preparable;
import com.soluship.businessfilter.util.BusinessFilterUtil;

import it.businesslogic.ireport.undo.ITransformation;

import java.awt.image.BufferedImage;
import java.util.HashSet;
import java.util.Iterator; 
import java.util.Set;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.meritconinc.mmr.dao.MessageDAO;
import com.meritconinc.shiplinx.model.BusinessEmail;
import com.meritconinc.shiplinx.model.BusinessFilter;
import com.meritconinc.shiplinx.model.CSSVO;
import com.meritconinc.shiplinx.model.UserBusiness;
import com.soluship.businessfilter.util.CSSClass;
import com.soluship.businessfilter.util.CSSFileGenerator;


public class BusinessAction extends BaseAction implements Preparable,ServletRequestAware,ServletResponseAware{

	private static final Logger log = LogManager.getLogger(BusinessAction.class);
	
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	private BusinessDAO businessDAO;
	
	private Business business;
	
	private List<Business> businessList = new ArrayList<Business>();
	
	private UserDAO userDAO;
	
	private List<MenuItemVO> menuVo = new ArrayList<MenuItemVO>();
	
	private MenusDAO menuItemDAO;
	
	private String businessId;
	
	private int[] selectedMenuIds;
	
	private AddressDAO addressDAO;
	
	private BusinessFilterDAO businessFilterDAO;
	
	
	private  List<String> defaultLoaders;
	
	  private List<Boolean> select;
	
	private String[] selectedLoaders;
	
	private File fileUpload;
	private String fileUploadContentType;
	private String fileUploadFileName;
	
	private List<Country> allCountryList;
	
	private UserBusiness userBusiness;
	private List<BusinessEmail> businessEmailList;
	
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request ; 
	}

	public String ajaxLoadChildBusiness(){
	       
	       String rootBusId=request.getParameter("rootBusId");
	       String partnerBusId=request.getParameter("partnerBusId");
	       String nationBusId=request.getParameter("nationBusId");
	       Long parentBusId;
	       if(rootBusId!=null && !rootBusId.equals("")){
	           parentBusId=Long.parseLong(rootBusId);
	           businessList=businessDAO.getBusinessListByLevel(parentBusId);
	           return "partner";
	       }else if(partnerBusId!=null && !partnerBusId.equals("")){
	           parentBusId=Long.parseLong(partnerBusId);
	           businessList=businessDAO.getBusinessListByLevel(parentBusId);
	           return "nation";
	       }else if(nationBusId!=null && !nationBusId.equals("")){
	           parentBusId=Long.parseLong(nationBusId);
	           businessList=businessDAO.getBusinessListByLevel(parentBusId);
	       return "branch";
	       }
	       return SUCCESS;
	   }
	   public String newUserBus(){
	       long count=0;
	       String rv=request.getParameter("root");
	       String pv=request.getParameter("partner");
	       String nv=request.getParameter("nation");
	       String bv=request.getParameter("branch");
	       userBusiness=new UserBusiness();
	       Long rid,pid,nid,bid;
	        BusinessFilter bf=new BusinessFilter();
	        
	        
	       if(rv!=null  ){
	           rid=Long.parseLong(rv);
	           if(rid!=-1){
	       bf.setParentBus(businessDAO.getBusiessById(rid));
	           }else{
	               Business b=new Business();
	               b.setId(-1);
	               b.setName("ANY");
	           
	           }
	           
	       }
	       if(pv!=null){
	           pid=Long.parseLong(pv);
	           if(pid!=-1){
	           bf.setPartnerBus(businessDAO.getBusiessById(pid));
	           }else{
	               Business b=new Business();
	               b.setId(-1);
	               b.setName("ANY");
	               bf.setPartnerBus(b);
	           }
	           
	       }
	       if(nv!=null){
	           nid=Long.parseLong(nv);
	           if(nid!=-1){
	           bf.setNationBus(businessDAO.getBusiessById(nid));
	           }else {
	               Business b=new Business();
	               b.setId(-1);
	               b.setName("ANY");
	               bf.setNationBus(b);
	           }
	       }
	       if(bv!=null){
	           bid=Long.parseLong(bv);
	           if(bid!=-1){
	           bf.setBranchBus(businessDAO.getBusiessById(bid));
	           }else{
	               Business b=new Business();
	               b.setId(-1);
	               b.setName("ANY");
	               bf.setBranchBus(b);
	           }
	       }
	       
	       
	       
	         count = (int)(Math.random()*9000)+1000;
	        
	       count++;
	        
	       bf.setId(count);
	       userBusiness.getBusinessFilterList().add(bf);
	       return SUCCESS;
	   }
	   
	public String newBusiness(){
		try{
			log.debug(" CHECK METHOD IN newBusiness " );
			loadEmailSettings(1L);
			getSession().remove("emailRList");
			getSession().remove("redudant");
			loadNewBussinessDepencies();
		}catch(Exception e){
			log.error("Error in new business" +e.getMessage());
		}
		return SUCCESS;
	}
	
	/**
	 * to load the default Value of 
	 * CSSVO for new businesss
	 * @param businessId
	 */
	@SuppressWarnings("unchecked")
		private void loadEmailSettings(long businessId) {
			// TODO Auto-generated method stub
		 
				business=new Business();
				business.setCssVO(new CSSVO());
				business.getCssVO().setEmailPickup(MessageUtil.getMessage(ShiplinxConstants.MSGID_EMAIL_SHIP_PICKUP));
				business.getCssVO().setEmailCusNotify(MessageUtil.getMessage(ShiplinxConstants.MSGID_EMAIL_CUS_NOTIFY));
				business.getCssVO().setEmailShipConfim(MessageUtil.getMessage(ShiplinxConstants.MSGID_EMAIL_SHIP_NOTIFY));
				business.getCssVO().setEmailShipCancel(MessageUtil.getMessage(ShiplinxConstants.MSGID_EMAIL_SHIP_CANCEL_NOTIFY));
				business.getCssVO().setEmailNewCustomer(MessageUtil.getMessage(ShiplinxConstants.MSGID_EMAIL_NEW_CUSTOMER));
				business.getCssVO().setEmailRateNotify(MessageUtil.getMessage(ShiplinxConstants.MSGID_EMAIL_SHIP_RATE));
				business.getCssVO().setEmailInvoiceNotify(MessageUtil.getMessage(ShiplinxConstants.MSGID_EMAIL_INVOICE_NOTIFY));
				business.getCssVO().setEmailSalesRepNewCust(MessageUtil.getMessage(ShiplinxConstants.MSGID_EMAIL_SALESREP_NEWCUS));
				business.getCssVO().setEmailWareHouse(MessageUtil.getMessage(ShiplinxConstants.MSGID_EMAIL_WAREHOUSE_ORDER));
				business.getCssVO().setEmailForgotpwd(MessageUtil.getMessage(ShiplinxConstants.MSGID_EMAIL_FORGOT_PWD));
				business.getCssVO().setBusinessEmailList(businessDAO.getBusinessEmails(businessId));
				getSession().put("businessEmailList",new ArrayList<BusinessEmail>());
				getSession().put("localeList", MessageUtil.getLocales());
			 
			
			
			
		}
	
	
	
	private void loadNewBussinessDepencies() {
		// TODO Auto-generated method stub
		
		menuVo = menuItemDAO.getAllMenu();
		getSession().put("CountryList", MessageUtil.getCountriesList());
		getSession().put("editBusiness","false");
		defaultLoaders=new ArrayList<String>();
		defaultLoaders.add("BUSINESS CARRIERS");
		defaultLoaders.add("BUSINESS MENUS");
		defaultLoaders.add("CUSTOMER MARKUP");
		defaultLoaders.add("EDI INFO");
		defaultLoaders.add("LTL POUND RATE");
		defaultLoaders.add("LTL SKID RATE");
		defaultLoaders.add("MERCHANT ACCOUNT");
		//setPartnerList(businessFilterDAO.getAllPartnerList());
		
		getSession().put("defualLoaders",defaultLoaders);
	}
    
	
	/**
	 * This is ajax action method
	 * to add Dynamic Email contents on 
	 * Add Business UI
	 * 
	 */
	@SuppressWarnings("unchecked")
	public String ajaxAddEmailBusiness(){
		Long businessId=(Long)getSession().get("editBusinessId");
		String update=request.getParameter("update1");
		if(update==null){
			
			String delete=request.getParameter("delete1");
			if(delete==null){
			
				List<Long>  emaillist1=new ArrayList<Long>();
				
				long busEmailId=Long.parseLong(request.getParameter("emailType"));
				String htmlcontString=request.getParameter("htmlCont");
				String locale=request.getParameter("locale");
		        setBusinessEmailList(new ArrayList<BusinessEmail>());
		        BusinessEmail bem=new BusinessEmail();
		        bem.setHtmlContent(htmlcontString);
		        bem.setLocale(locale);
		        bem.setHtmlContent(htmlcontString);
		        bem.setEmailType(businessDAO.getBusinessEmailById(busEmailId).getEmailType());
		        bem.setBusinessEmailId(busEmailId);
				getBusinessEmailList().add(bem);
				emaillist1.add(busEmailId);	 
				getSession().put("redudant","false");
				//return SUCCESS;
				if(getSession().get("emailRList")==null){
					getSession().put("emailRList",emaillist1);	
				
			}else{
					
					List<Long> emaillist=(List<Long>) getSession().get("emailRList");
					emaillist.addAll(emaillist1);
					if(emaillist!=null && emaillist.size()>0){
						Set<Long> set = new HashSet<Long>(emaillist);
						if(set.size() < emaillist.size()){
					    /* There are duplicates */
							getSession().put("redudant","true");
							getSession().put("emailRList",emaillist1);
							return SUCCESS;
						}else{
							getSession().put("emailRList",emaillist1);
							getSession().put("redudant","false");
							 
						}
							
					}
				}
			
			}else if(delete.equals("true")){
				long deleteid=Long.parseLong(request.getParameter("deleteid"));
				List<Long> emaillist=(List<Long>) getSession().get("emailRList");
				if(emaillist!=null && emaillist.size()>0){
					emaillist.remove(deleteid);
				}
				getSession().put("emailRList",emaillist);
				
			}
		}else if(update.equals("true") && businessId!=null){

			String delete=request.getParameter("delete1");
			if(delete==null){
			
				List<Long>  emaillist1=new ArrayList<Long>();
				emaillist1=(List<Long>)getSession().get("emailRList");
				long busEmailId=Long.parseLong(request.getParameter("emailType"));
				String htmlcontString=request.getParameter("htmlCont");
				
				String locale=request.getParameter("locale");
		        setBusinessEmailList(businessDAO.getBusinessEmails(businessId));
		        setBusinessEmailList(removeListWithBusEmailId(getBusinessEmailList(),busEmailId));
		        List<BusinessEmail> updatedBelist=new ArrayList<BusinessEmail>();
		        emaillist1=BusinessFilterUtil.getvalidatedBusIds(emaillist1);
		        if(emaillist1!=null && emaillist1.size()>0){
			        for(Long beid:emaillist1 ){
			        
			        	Iterator<BusinessEmail> itrb=getBusinessEmailList().iterator();
			        	while(itrb.hasNext()){
			        		BusinessEmail bb=itrb.next();
			        		if(bb.getBusinessEmailId()==beid){
			        		    itrb.remove();
			        		}
			        		
			        	}
			        }
		        }
		        setBusinessEmailList(updatedBelist);
		        BusinessEmail bem=new BusinessEmail();
		        bem.setHtmlContent(htmlcontString);
		        bem.setLocale(locale);
		        bem.setHtmlContent(htmlcontString);
		        bem.setEmailType(businessDAO.getBusinessEmailById(busEmailId).getEmailType());
		        bem.setBusinessEmailId(busEmailId);
				getBusinessEmailList().add(bem);
				emaillist1.add(busEmailId);	 
				getSession().put("redudant","false");
				//return SUCCESS;
				if(getSession().get("emailRList")==null){
					getSession().put("emailRList",emaillist1);	
					
				}
			}
		}
		return SUCCESS;
	}
	
 

	

	/**
	 * remove Duplicate List while adding Business Email in 
	 * new business creation
	 * @param businessEmailList2
	 * @param busEmailId
	 * @return
	 */
private List<BusinessEmail> removeListWithBusEmailId(
			List<BusinessEmail> businessEmailList2, long busEmailId) {
		// TODO Auto-generated method stub
		
		if(businessEmailList2!=null && businessEmailList2.size()>0){
		
			Iterator<BusinessEmail> itb=businessEmailList2.iterator();
			while(itb.hasNext()){
				BusinessEmail bem=itb.next();
				if(bem.getBusinessEmailId()==busEmailId){
					itb.remove();
				}
			}
		  return businessEmailList2;
		}
		return new ArrayList<BusinessEmail>();
	}

	@Override
	public void prepare() throws Exception {
		// TODO Auto-generated method stub
		
	}

	public String listBusiness(){
		try{
			if((getSession().get("saveBus"))!=null && ((String)getSession().get("saveBus")).equals("success")){
				addActionMessage(getText("business.save.success"));
			}else if((getSession().get("updateBus"))!=null && ((String)getSession().get("updateBus")).equals("success")){
				addActionMessage(getText("business.updated.successfully"));
			}
			getSession().put(ShiplinxConstants.SESSION_BUSINESSFILTER_CUSTOMERID,BusinessFilterUtil.getSysadminLevelCustomers());
			businessList = businessDAO.getAllBusiness();
			getSession().remove("editBusiness");
		}catch(Exception e){
			log.error("Error in listing the business :" + e.getMessage());
		}
		getSession().remove("saveBus");
		getSession().remove("updateBus");
		return SUCCESS;
	}
	
	
	public String saveBusiness(){
		//System.out.println(business.getName());
		String paramBusinessId = request.getParameter("businessid");
		  
		//updating the business
		if(paramBusinessId!=null && !paramBusinessId.equals("") && !paramBusinessId.equals("0")){
			 log.debug(" CHECK METHOD IN SAVE BUSINESS ************** UPDATE BUSINESS");
			try{
				
				
				String paramAddressId = request.getParameter("addressid");
				System.out.println(paramAddressId);
				Address addressUpdate = business.getAddress();
				addressUpdate.setAddressId(Long.valueOf(paramAddressId));
				//updating the address
				addressDAO.edit(addressUpdate);
				business.setId(Long.parseLong(paramBusinessId));
				business.setAddressId(Long.valueOf(paramAddressId));
				business.setUsAddressId(Long.valueOf(paramAddressId));
				business.setDefaultNetTerms(15);
				String staus=updateCSS();
								
								if(staus.equals("fail")){
									
									return "fail";
								}
								updateEmailconf(business.getId());
				businessDAO.updateBusiness(business);
				//inserting the business menu
				List<MenuItemVO> menuItems = menuItemDAO.getMenuByBusiness(paramBusinessId);
				String[] uiMenuSelected = business.getMenuIds();
				
				for(int uiCount=0;uiCount < uiMenuSelected.length ;uiCount++){
					boolean isOccured = false;
					for(MenuItemVO itemVO : menuItems){
						if(itemVO.getId() == Integer.valueOf(uiMenuSelected[uiCount])){
							isOccured = true;
						}
					}
					if(!isOccured){
						menuItemDAO.saveBusinessMenu(new Long(paramBusinessId).toString(), uiMenuSelected[uiCount]);
					}
				}
				
				
				//deleting the business menu
				
				for(MenuItemVO itemVO : menuItems){
					boolean isOccured = false;
					for(int uiCount=0;uiCount < uiMenuSelected.length ;uiCount++){
						if(itemVO.getId() == Integer.valueOf(uiMenuSelected[uiCount])){
							isOccured = true;
						}
					}
					
					if(!isOccured){
						menuItemDAO.deleteBusinessMenuById(paramBusinessId, String.valueOf(itemVO.getId()));
					}
				}
				getSession().put(ShiplinxConstants.SESSION_BUSINESSFILTER_CUSTOMERID,BusinessFilterUtil.getSysadminLevelCustomers());
				businessList = businessDAO.getAllBusiness();
				getSession().remove("editBusiness");
				
				
				addActionMessage(getText("business.update.success"));
				  getSession().put("updateBus", "success");
				return SUCCESS;
			}catch(Exception e){
				addActionError(getText("business.save.error"));
				return INPUT;
			}
			
		}else{
			//inserting the new business
			log.debug(" CHECK METHOD IN SAVE BUSINESS ************** ADD NEW BUSINESS");
			try{
				     if(validateBusiness()|| validateBusinessName()){
				    		 
				    		
				    			
				    		
				    	 loadNewBussinessDepencies();
				    	 loadEmailSettings(1L);
				    	 getSession().remove("emailRList");
				    	 getSession().remove("redudant");
				    	 return "fail";
				     }else{
				        business.setDefaultNetTerms(15);
		    			long addressId = addressDAO.add(business.getAddress());
						business.setAddressId(addressId);
						business.setUsAddressId(addressId);
						String cssText=getCssTextForBusiness(business.getCssVO());
						//business.setCssText(cssText);
						long businessId = businessDAO.addBusiness(business);
						//customized css to new business
						addCSS(cssText,businessId);
						//customized email contents to business
						addEmailContents(businessId);
						User user = setuserDetails(business.getName(), businessId);
				       	userDAO.create(user, "");
					 	    BusinessFilterDAO businessFilterDAO = (BusinessFilterDAO) MmrBeanLocator.getInstance().findBean("businessFilterDAO");
						    user.setUserFilter(new UserFilter());
							user.getUserFilter().setBusinessId(businessId);
							user.getUserFilter().setUserName(user.getUsername());
						 	businessFilterDAO.addUserFilters(user.getUserFilter());
							
							//add default busiess admin as parter
			                String businessName=business.getName();
			                String sC=business.getShortCode();
			                business.setShortCode(sC+"Part");
				       	    business.setName(businessName+"Partner");
							business.setPartnerLevel(true);
							business.setParentBusinessId(businessId);
							long partnerBusienssId=businessDAO.addParterLevelBusienss(business);
							//customized css to new business
							addCSS(cssText,partnerBusienssId);
							//customized email contents to business
							 addEmailContents(partnerBusienssId);
				    	    User userPatner = setuserDetails(business.getName(), partnerBusienssId);
				    	    userPatner.setUsername(business.getName());
				    	    userPatner.setBusinessLevel(false);
				    	    userPatner.setPartnerLevel(true);
				    	    userDAO.create(userPatner, "");
				    	    
				    	    userPatner.setUserFilter(new UserFilter());
				    	    userPatner.getUserFilter().setBusinessId(partnerBusienssId);
				    	    userPatner.getUserFilter().setUserName(userPatner.getUsername());
				    	    userPatner.getUserFilter().setBusinessLevel(true);
						 	businessFilterDAO.addUserFilters(userPatner.getUserFilter());
				    	    
				    	    
							// add default country_admin 
				    	     business.setShortCode(sC+"Nat");
							 business.setName(businessName+"Nation");
							 business.setPartnerLevel(false);
							 business.setNationLevel(true);
							 
							 business.setPartnerId(partnerBusienssId);
							 business.setParentBusinessId(partnerBusienssId);
							 long countryBusinessId=businessDAO.addCountryLevelBusienss(business);
							 User usercountry = setuserDetails(business.getName(), countryBusinessId);
							 usercountry.setUsername(business.getName());
							 usercountry.setNationLevel(true);
							 usercountry.setBusinessLevel(false);
							 userDAO.create(usercountry, "");
							 
							 usercountry.setUserFilter(new UserFilter());
							 usercountry.getUserFilter().setBusinessId(countryBusinessId);
							 usercountry.getUserFilter().setUserName(usercountry.getUsername());
							 usercountry.getUserFilter().setPartnerLevel(true);
							 businessFilterDAO.addUserFilters(usercountry.getUserFilter());
					    	    
							 
							 
						//userDAO.insertRole(business.getName(), "busadmin");
						for(String menuId:business.getMenuIds()){
							menuItemDAO.saveBusinessMenu(new Long(businessId).toString(), menuId);
						}
					//	String msg="Your BUSINESS SUCCESSFULLY ADDED TO SOLUSHIP\n USERNAME  :"+user.getUsername()+"Password :"+user.getPassword();
					  
						//adding the default default loaders
						if(businessId>0 && partnerBusienssId>0&& countryBusinessId>0){
							 //loadDefaultPropertyForBusiness(businessId);
							List<Long> busids=new ArrayList<Long>();
							busids.add(businessId);
							busids.add(partnerBusienssId);
							busids.add(countryBusinessId);
						    BusinessFilterUtil bfu1=new BusinessFilterUtil(busids, this.select);
							Thread t1=new Thread(bfu1);
							t1.start();
							
							
						}
					    // default loader for partner level default business
				/*		if(partnerBusienssId>0){
							  loadDefaultPropertyForBusiness(partnerBusienssId);
						   BusinessFilterUtil bfu1=new BusinessFilterUtil(partnerBusienssId, this.select);
							Thread t1=new Thread(bfu1);
							t1.start();
						}*/
						/*
						if(countryBusinessId>0){
					        // default loader for partner level default business
							  loadDefaultPropertyForBusiness(countryBusinessId);
						  	BusinessFilterUtil bfu1=new BusinessFilterUtil(countryBusinessId, this.select);
							Thread t1=new Thread(bfu1);
							t1.start();
						}*/
					    
					    getSession().put(ShiplinxConstants.SESSION_BUSINESSFILTER_CUSTOMERID,BusinessFilterUtil.getSysadminLevelCustomers());
						businessList = businessDAO.getAllBusiness();
						addHelpMenuDirecotry();
						addContactDictionary();
						addFeedbackDictionary();
						getSession().remove("editBusiness");
					    getSession().put("saveBus", "success");
					    getSession().remove("emailRList");
					    getSession().remove("redudant");
					    return SUCCESS;
						
				
				     }
			}catch(Exception e){
				log.error("Error in creating the business :"+e.getMessage());
				business.setId(0);
				
				menuVo = menuItemDAO.getAllMenu();
				addActionError(getText("business.save.error"));
				getSession().put("editBusiness","false");
				e.printStackTrace();
				return INPUT;
			}
			
		}
		
	}
	
	
	/**
	  * vivek help menu changes for new business
	  */
	 void addHelpMenuDirecotry(){
	  /*String staticpath=business.getReportHelpPath();*/
	  businessList = businessDAO.getAllBusiness();
	  String staticpath=null;
	  if(staticpath==null){
	   staticpath=ShiplinxConstants.BUSINESS_HELP_DIR;
	   
	  }
	  File f=new File(staticpath);
	  if(!f.isDirectory()){
	   f.mkdir();
	  }
	  Business bus=BusinessFilterUtil.getSuperParentBusiness(business.getId());
	  if(bus!=null)
	  {
	   long busId=bus.getId();
	  String path=staticpath+"help"+"_"+busId;
	  System.out.println(path+"    path is ");
	  File file = new File(path);
	  /*if (!file.exists()) {*/
	   if (file.mkdir()) {
	    System.out.println("Directory is created!");
	   } else {
	    System.out.println("Failed to create directory!");
	   }
	  }
	 /* }*/
	 }
	 
	 void addFeedbackDictionary(){
		 		  /*String staticpath=business.getReportHelpPath();*/
		 		  businessList = businessDAO.getAllBusiness();
		 		  boolean flag = true;
		 		  String staticpath=null;
		 		  staticpath = business.getFeedbackPath();
		 		  if(staticpath==null){
		 			  staticpath=ShiplinxConstants.BUSINESS_HELP_DIR;
		 		  }else{
		 			  //String contact1 = staticpath+"/contact_"+bus
		 			  flag = false;
		 		  }
		 		  File f=new File(staticpath);
		 		  if(!f.isDirectory()){
		 		   f.mkdir();
		 		  }
		 		  Business bus=BusinessFilterUtil.getSuperParentBusiness(business.getId());
		 		  if(bus!=null)
		 		  {
		 		   long busId=bus.getId();
		 		   String feedback = null;
		 		   if(flag){
		 			   feedback=staticpath+"feedback"+"_"+busId;
		 		   }else{
		 			   feedback=staticpath+"/feedback"+"_"+busId; 
		 		   }
		 		  
		 		  System.out.println(feedback+"    feedback is ");
		 		  File file = new File(feedback);
		 		  /*if (!file.exists()) {*/
		 		   if (file.mkdir()) {
		 		    System.out.println("Directory is created!");
		 		   } else {
		 		    System.out.println("Failed to create directory!");
		 		   }
		 		  }
		 		 /* }*/
		 		 }
		 	 
		 	 void addContactDictionary(){
		 		  /*String staticpath=business.getReportHelpPath();*/
		 		  businessList = businessDAO.getAllBusiness();
		 		  boolean flag = true;
		 		  String staticpath=null;
		 		  staticpath = business.getContactPath();
		 		  if(staticpath==null){
		 			  staticpath=ShiplinxConstants.BUSINESS_HELP_DIR;
		 		  }else{
		 			  //String contact1 = staticpath+"/contact_"+bus
		 			  flag = false;
		 		  }
		 		  File f=new File(staticpath);
		 		  if(!f.isDirectory()){
		 		   f.mkdir();
		 		  }
		 		  Business bus=BusinessFilterUtil.getSuperParentBusiness(business.getId());
		 		  if(bus!=null)
		 		  {
		 		   long busId=bus.getId();
		 		   String contact = null;
		 		   if(flag){
		 			   contact=staticpath+"contact"+"_"+busId;
		 		   }else{
		 			   contact=staticpath+"/contact"+"_"+busId; 
		 		   }
		 		  
		 		  System.out.println(contact+"    contact is ");
		 		  File file = new File(contact);
		 		  /*if (!file.exists()) {*/
		 		   if (file.mkdir()) {
		 		    System.out.println("Directory is created!");
		 		   } else {
		 		    System.out.println("Failed to create directory!");
		 		   }
		 		  }
		 		 /* }*/
		 		 }
	
	/**
	 * update the Email Configuration of the
	 * business
	 * @param businessID
	 */
	@SuppressWarnings("unchecked")
		private void updateEmailconf(long businessID ) {
			// TODO Auto-generated method stub
			try{
			List<BusinessEmail> oldBmList=(List<BusinessEmail>)getSession().get("businessEmailList");
			List<BusinessEmail> newBmList=new ArrayList<BusinessEmail>();
			
			MessageDAO messageDAO=(MessageDAO)MmrBeanLocator.getInstance().findBean("messageDAOTarget");
			String[] emailCIds=request.getParameterValues("businessEmailIds");
			String[] htmlConts=request.getParameterValues("htmlContents");
			String[] locales=request.getParameterValues("locales");
			
			
			if(emailCIds!=null && htmlConts!=null && locales!=null){
				
				for(int i=0;i<emailCIds.length;i++){
	
					Long emailCId=Long.parseLong(emailCIds[i]);
					String htmlCont=htmlConts[i];
					String locale=locales[i];
					
					if(emailCId!=null && htmlCont!=null && locale!=null){
	
						BusinessEmail bme=businessDAO.getBusinessEmailById(emailCId);
						
						if(bme!=null){
							
							BusinessEmail newBe=new BusinessEmail();
							newBe.setBusinessEmailId(emailCId);
							newBe.setBusinessId(businessID);
							newBe.setHtmlContent(htmlCont);
							newBe.setLocale(locale);
							newBe.setEmailType(bme.getEmailType());
							if(bme.getBusinessId()==1){
								newBe.setMsgId(bme.getMsgId()+businessID);
							}else{
								newBe.setMsgId(bme.getMsgId());
						}
						/*businessDAO.addBusinessEmail(newBe);
							messageDAO.saveResource(newBe.getMsgId(), newBe.getHtmlContent(),newBe.getLocale());
							*/
							newBmList.add(newBe);
							
				}
						
					}
					
				}
				
			}
			
			List<BusinessEmail> oldBackup=new ArrayList<BusinessEmail>();
			oldBackup.addAll(oldBmList);
			List<BusinessEmail> newBackup=new ArrayList<BusinessEmail>();
			newBackup.addAll(newBmList);
			oldBmList.removeAll(newBmList);
			newBackup.removeAll(oldBackup);
			newBmList.removeAll(newBackup);
	        //to add new business_email
			if(oldBmList!=null && oldBmList.size()>0){
				for(BusinessEmail be1:oldBmList){
					if(be1.getBusinessId()!=1)
					businessDAO.deleteBusinessEmailByBMId(be1.getBusinessEmailId());
				}
			}
			//to add new business_email
			if(newBackup!=null && newBackup.size()>0){
				for(BusinessEmail be2:newBackup){
					BusinessEmail bme=businessDAO.getBusinessEmailById(be2.getBusinessEmailId());
					if(bme!=null){
						
						BusinessEmail newBe=new BusinessEmail();
						newBe.setBusinessId(businessID);
						newBe.setHtmlContent(be2.getHtmlContent());
						newBe.setLocale(be2.getLocale());
						newBe.setEmailType(bme.getEmailType());
						newBe.setMsgId(bme.getMsgId()+businessID);
						businessDAO.addBusinessEmail(newBe);
						messageDAO.saveResource(newBe.getMsgId(), newBe.getHtmlContent(),newBe.getLocale());
					}
				}
			}
			
			//to update old
			if(newBmList!=null && newBmList.size()>0){
				for(BusinessEmail be3:newBmList){
					businessDAO.updateBusinessEmail(be3);
					
				}
			}
			}catch(Exception e){
				e.printStackTrace();
			}
			
		}

	
	/**
	 * add new Email configuration of Business
	 * @param businessID
	 */
		private void addEmailContents(long businessID) {
			// TODO Auto-generated method stub
			MessageDAO messageDAO=(MessageDAO)MmrBeanLocator.getInstance().findBean("messageDAOTarget");
			String[] emailCIds=request.getParameterValues("businessEmailIds");
			String[] htmlConts=request.getParameterValues("htmlContents");
			String[] locales=request.getParameterValues("locales");
			
			
		if(emailCIds!=null && htmlConts!=null && locales!=null){
				
				for(int i=0;i<emailCIds.length;i++){
	
					Long emailCId=Long.parseLong(emailCIds[i]);
					String htmlCont=htmlConts[i];
					String locale=locales[i];
					
					if(emailCId!=null && htmlCont!=null && locale!=null){
	
						BusinessEmail bme=businessDAO.getBusinessEmailById(emailCId);
						if(bme!=null){
							
							BusinessEmail newBe=new BusinessEmail();
							newBe.setBusinessId(businessID);
							newBe.setHtmlContent(htmlCont);
							newBe.setLocale(locale);
							newBe.setEmailType(bme.getEmailType());
							newBe.setMsgId(bme.getMsgId()+businessID);
							businessDAO.addBusinessEmail(newBe);
							String iscont=messageDAO.getMessage(newBe.getMsgId(), UserUtil.getMmrUser().getLocale());
							if(iscont==null && iscont.length()==0){
							messageDAO.saveResource(newBe.getMsgId(), newBe.getHtmlContent(),newBe.getLocale());
							}else{
								messageDAO.updateResource(newBe.getMsgId(),newBe.getHtmlContent(),newBe.getLocale());
							}
						}
						
					}
					
				}
				
			}
			
			
	
			
		}
	
		
		
		/**
		 * add Customized CSS Text for New Business from 
		 * the defualt CSS Text from Default Business 1L(IC)
		 * @param cssText
		 * @param businessId
		 */
		public void addCSS(String cssText, long businessId) {
	
			// Adding css details for business -start
			if (business.getCssVO() != null && cssText != null && businessId != 0) {
				CSSVO cssVo = business.getCssVO();
				cssVo.setCssText(cssText);
				String fileName = cssVo.getLogoImage();
				if (getByteImg(fileName) != null) {
					System.out.println(fileName + "\n"
							+ (getByteImg(fileName)).length);
					cssVo.setLogoImgByte(getByteImg(fileName));
				}
				fileName = cssVo.getBackImg();
				if (getByteImg(fileName) != null) {
					System.out.println(fileName + "\n"
							+ (getByteImg(fileName)).length);
					cssVo.setBackGroudImgByte((getByteImg(fileName)));
				}
				fileName = cssVo.getEmailHeader();
				if (getByteImg(fileName) != null) {
					System.out.println(fileName + "\n"
							+ (getByteImg(fileName)).length);
					cssVo.setEmailHeaderByte((getByteImg(fileName)));
				}
				
				/*fileName = cssVo.getFavIcon().getAbsolutePath();
								if (getByteImg(fileName) != null) {
									System.out.println(fileName + "\n"
											+ (getByteImg(fileName)).length);
									cssVo.setFavIconByte((getByteImg(fileName)));
								}*/
				
				if(cssVo.getFavIcon() != null){
									fileName = cssVo.getFavIcon().getAbsolutePath();
									if (getByteImg1(fileName, cssVo.getFavIcon()) != null) {
										System.out.println(fileName + "\n"
												+ (getByteImg1(fileName, cssVo.getFavIcon())).length);
										cssVo.setFavIconByte((getByteImg1(fileName, cssVo.getFavIcon())));
									}
									}
				fileName = cssVo.getPackageImage();
								if (getByteImg(fileName) != null) {
									System.out.println(fileName + "\n"
											+ (getByteImg(fileName)).length);
									cssVo.setPackageImageByte(getByteImg(fileName));
								}
				
				if (cssVo != null) {
					cssVo.setBusinessId(businessId);
					businessDAO.addCSSDetailsForBusiness(cssVo);
				}
				// Adding css details for business -end
			}
		}

		/**
		 * Update Customized CSS Text for New Business  
		 * @param cssText
		 * @param businessId
		 */
		public String updateCSS() {
			// Updating the css details for business - start
		Long	businessId1=(Long)getSession().get("editBusinessId");
		businessId=businessId1.toString();
		CSSVO cssVo = businessDAO.getCSSDetailsForBusiness(businessId1);
			if (cssVo  != null) {
				try {
					  cssVo = business.getCssVO();
					String cssText=getCssTextForBusiness(cssVo);
					String fileName = cssVo.getLogoImage();
					if (fileName != null) {
						File file = new File(fileName);
						BufferedImage bi = ImageIO.read(file);
						int width = bi.getWidth();
						int height = bi.getHeight();
	
						if ( (width >= 180 && width <=186)  && height == 60) {
							byte b[] = getByteImg(fileName);
							cssVo.setLogoImgByte(b);
						} else {
							editBusiness();
							addActionError("Logo Image Width and Height should be 186 and 60");
							return "fail";
						}
	
					}
				
					fileName=cssVo.getBackImg();
					if (fileName != null) {
						File file = new File(fileName);
						BufferedImage bi = ImageIO.read(file);
						int width = bi.getWidth();
						int height = bi.getHeight();
	
						if (width == 974 && height == 57) {
							byte b[] = getByteImg(fileName);
							cssVo.setBackGroudImgByte(b);
						} else {
	                       editBusiness();
	                       addActionError("Backgroud Image Width and Height should be 974 and 57");
							return "fail";
						}
	
					}
					
					fileName=cssVo.getPackageImage();
										if (fileName != null) {
											File file = new File(fileName);
											BufferedImage bi = ImageIO.read(file);
											int width = bi.getWidth();
											int height = bi.getHeight();
						
											if (width == 1100 && height == 45) {
												byte b[] = getByteImg(fileName);
											
												cssVo.setPackageImageByte(b);
											} else {
						                       editBusiness();
						                       addActionError("Package Image Width and Height should be 1100 and 45");
					 							return "fail";
					 						}
										}

					
					/*fileName=cssVo.getFavIcon();
															if (fileName != null) {
																File file = new File(fileName);
																BufferedImage bi = ImageIO.read(file);
																int width = bi.getWidth();
																int height = bi.getHeight();
											
																if (width == 16 && height == 16) {
																	byte b[] = getByteImg(fileName);
																	cssVo.setFavIconByte(b);
																} else {
											                       editBusiness();
											                       addActionError("FavIcon Width and Height should be 16 and 16");
										 							return "fail";
										 						}
															}*/
					if(cssVo.getFavIcon() != null){
					fileName=cssVo.getFavIcon().getAbsolutePath();
															if (fileName != null) {
																File file = new File(fileName);
																List<BufferedImage> image = ICODecoder.read(cssVo.getFavIcon());
																BufferedImage bi = image.get(0);
																int width = bi.getWidth();
															int height = bi.getHeight();
											
																if (width == 16 && height == 16) {
																	byte b[] = getByteImg1(fileName, cssVo.getFavIcon());
																	cssVo.setFavIconByte(b);
																} else {
											                       editBusiness();
											                       addActionError("FavIcon Width and Height should be 16 and 16");
																	return "fail";
																}
											
															}
					}
					if (cssVo != null && business.getId() != 0) {
						cssVo.setCssText(cssText);
						cssVo.setBusinessId(Long.valueOf(business.getId()));
						businessDAO.updateCSSDetailsForBusiness(cssVo);
					}
					
				} catch (Exception e) {
	                e.printStackTrace();
					return "fail";
				}
				// Updating the css details for business - end
			}else{
				//Add new css for old business
				try {
					  cssVo = business.getCssVO();
					String cssText=getCssTextForBusiness(cssVo);
					String fileName = cssVo.getLogoImage();
					if (fileName != null) {
						File file = new File(fileName);
						BufferedImage bi = ImageIO.read(file);
						int width = bi.getWidth();
						int height = bi.getHeight();
	
						if ( (width >= 180 && width <=186)  && height == 60) {
							byte b[] = getByteImg(fileName);
							cssVo.setLogoImgByte(b);
						} else {
							editBusiness();
							addActionError("Logo Image Width and Height should be 186 and 60");
							return "fail";
						}
	
					}
					
					fileName=cssVo.getBackImg();
					if (fileName != null) {
						File file = new File(fileName);
						BufferedImage bi = ImageIO.read(file);
						int width = bi.getWidth();
						int height = bi.getHeight();
	
						if (width == 974 && height == 57) {
							byte b[] = getByteImg(fileName);
							cssVo.setBackGroudImgByte(b);
						} else {
	                     editBusiness();
	                     addActionError("Backgroud Image Width and Height should be 974 and 57");
							return "fail";
						}
	
					}
					
					fileName=cssVo.getPackageImage();
										if (fileName != null) {
											File file = new File(fileName);
											BufferedImage bi = ImageIO.read(file);
											int width = bi.getWidth();
											int height = bi.getHeight();
						
											if (width == 1100 && height == 45) {
												byte b[] = getByteImg(fileName);
											
												cssVo.setPackageImageByte(b);
											} else {
						                       editBusiness();
						                       addActionError("Package Image Width and Height should be 1100 and 45");
					 							return "fail";
					 						}
										}

					
					/*fileName=cssVo.getFavIcon();
															if (fileName != null) {
																File file = new File(fileName);
																BufferedImage bi = ImageIO.read(file);
																int width = bi.getWidth();
																int height = bi.getHeight();
											
																if (width == 16 && height == 16) {
																	byte b[] = getByteImg(fileName);
																	cssVo.setFavIconByte(b);
																} else {
											                       editBusiness();
											                       addActionError("FavIcon Width and Height should be 16 and 16");
										 							return "fail";
										 						}
															}*/
					if(cssVo.getFavIcon() != null){
					fileName=cssVo.getFavIcon().getAbsolutePath();
															if (fileName != null) {
																File file = new File(fileName);
																List<BufferedImage> image = ICODecoder.read(cssVo.getFavIcon());
																BufferedImage bi = image.get(0);
																int width = bi.getWidth();
																int height = bi.getHeight();
											
																if (width == 16 && height == 16) {
																	byte b[] = getByteImg1(fileName, cssVo.getFavIcon());
																	cssVo.setFavIconByte(b);
																} else {
											                       editBusiness();
											                       addActionError("Backgroud Image Width and Height should be 16 and 16");
																	return "fail";
																}
											
															}
					}
					if (cssVo != null && business.getId() != 0) {
						cssVo.setCssText(cssText);
						cssVo.setBusinessId(Long.valueOf(business.getId()));
						 addCSS(cssVo.getCssText(),cssVo.getBusinessId());
					}
					
				} catch (Exception e) {
	              e.printStackTrace();
					return "fail";
				}
			}
			return "success";
		}
	
		private byte[] getByteImg(String fileName) {
			// TODO Auto-generated method stub
	
			if (fileName != null) {
	
				try {
					File file = new File(fileName);
					BufferedImage bi = ImageIO.read(file);
					if (bi != null) {
						InputStream is = new FileInputStream(file);
						byte b[] = new byte[(int) file.length()];
						is.read(b);
					return b;
				}
				} catch (Exception e) {
					return null;
				}
			}
			return null;
		}
		
		private byte[] getByteImg1(String fileName, File favIcon) {
			// TODO Auto-generated method stub
	
			if (fileName != null) {
	
				try {
					File file = new File(fileName);
					List<BufferedImage> image = ICODecoder.read(favIcon);
					BufferedImage bi = image.get(0);
					if (bi != null) {
						InputStream is = new FileInputStream(file);
						byte b[] = new byte[(int) file.length()];
						is.read(b);
					return b;
				}
				} catch (Exception e) {
					return null;
				}
			}
			return null;
		}
	
		/**
		 * Get CSSText as a String from Default Busienss 1L
		 * Stored in db 
		 * @param cssVO
		 * @return
		 */
		@SuppressWarnings({ "deprecation", "unused" })
		private String getCssTextForBusiness(CSSVO cssVO) {
			// TODO Auto-generated method stub
			String content = "";
			List<CSSClass> cssClassList = null;
			CSSFileGenerator css = new CSSFileGenerator();
			// get defaul css loads from business 1 IC.
			CSSVO cs = businessDAO.getCSSDetailsForBusiness(1L);
			if (cs != null) {
	
				cssVO.setCssText(cs.getCssText());
				cssClassList = css.getCSSList(new File(""), cssVO);
			content += css.getCSSText(cssClassList);
				if (content != null) {
					return content;
				}
			} else { // If css is not available in the db it will get it from
						// labtop.css
				File f = new File(
						request.getRealPath("/webcontent/mmr/styles/labtop.css"));
				File f1 = new File(
						request.getRealPath("/webcontent/mmr/styles/demo_table.css"));
				/* List<File> CSSFileList = BusinessFilterUtil.getCSSFileList(f); */
				List<File> CSSFileList = new ArrayList<File>();
				if (f1 != null && f != null) {
					CSSFileList.add(f1);
					CSSFileList.add(f);
				}
	
				for (File cssFile : CSSFileList) {
					CSSVO cssVo = business.getCssVO();
					// CSSFileGenerator css = new CSSFileGenerator();
					/*
					 * CSSVO cs=businessDAO.getCSSDetailsForBusiness(1L);
					 */
					cssClassList = css.getCSSList(cssFile, cssVo);
					content += css.getCSSText(cssClassList);
				}
				return content;
			}
			return content;
		}
	
		/**
		 * add css for business
		 * @param businessId2
		 */
		private void addCssForBusiness(long businessId2) {
			// TODO Auto-generated method stub
	
			// BusinessFilterUtil.addCSSForBusiness(businessId2);
			List<File> CSSFileList = BusinessFilterUtil.getCSSFileList(new File(
					"/home/soluship/WebContent/mmr/styles/"));
			for (File cssFile : CSSFileList) {
				CSSVO cssVo = business.getCssVO();
				CSSFileGenerator css = new CSSFileGenerator();
				css.GenerateCSS(cssFile, businessId2, cssVo);
			}
		}
		 
	
	private boolean validateBusinessName() {
		// TODO Auto-generated method stub
		int i=0;
		  
		if(userDAO.findUserByUsername(business.getName())!=null){
			addActionError(getText("Business UserName Already Exist "));
			i++;
		}
		if(businessDAO.getBusinessByName(business.getName()+"Partner")!=null){
			addActionError(getText("Business Parnter Name Already Exist "));
			i++;
		} 
		if(userDAO.findUserByUsername(business.getName()+"Partner")!=null){
			addActionError(getText("Business Partner UserName Already Exist "));
			i++;
		}
		if(businessDAO.getBusinessByName(business.getName()+"Nation")!=null){
			addActionError(getText(" Country Business  Name Already Exist "));
			i++;
		} 
		if(userDAO.findUserByUsername(business.getName()+"Nation")!=null){
			addActionError(getText("  Country Business   UserName Already Exist "));
			i++;
		}
		 if(i>0){
			 return true;
		 }
		return false;
	}

	public void loadDefaultPropertyForBusiness(Long businessId){
		addDefaultBusinessLoaders(businessId);
	    businessDAO.addDefaultCustomerCarrier(businessId);
	    businessDAO.addDefaultPinsToBusiness(businessId);
	}
	
	private boolean validateBusiness() {
		// TODO Auto-generated method stub
		int i=0;
	     Long  partnerId=(Long)getSession().get(Constants.PARTNER_ID_SESSION);

      if(businessDAO.getBusinessByName(business.getName())!=null){
    	   addActionError("Business Name Already Exist");
			i++;
     }
	 
		 if(business.getName().equals("")){
			addActionError("Name is Requried");
			i++;
		 }else if(business.getName().length()>30 && business.getName().length()<3){
			 addActionError(getText("Business Name length must be with 3 to 30 "));
			 i++;
		 }
		 
		 if(business.getShortCode().equals("")){
			 addActionError(getText("Short Code is Requried"));
			 i++;
			
		 }else if(business.getShortCode().length()>10 || business.getShortCode().length()<3){
			 addActionError(getText("Short Code length must be with 3 to 9 "));
			 i++;
		 }
		 
		 if(business.getSystemName().equals("")){
			 addActionError(getText("System Name is Required"));
			 i++;
		 }else if(business.getSystemName().length()>20 || business.getSystemName().length()<3){
			 addActionError(getText("System Name length must be with 3"
			 		+ " to 20 "));
			 i++;
		 }
		 
		 if(business.getSubdomain().equals("")){
			 addActionError(getText("Sub Domail  is Required"));
			 i++;
		 }else if(business.getSubdomain().length()>200){
			 addActionError(getText("subdomain  length is too high must be less than  200 "));
			 i++;
			 
		 }
		 
		 if(business.getAddress().getEmailAddress().equals("")){
			 addActionError(getText("Email address is Required"));
			 i++;
		 }else {
			 if(!business.getAddress().getEmailAddress().matches(ShiplinxConstants.EMAIL_REGEX)){
				 addActionError(getText("Email address is not a valid one"));
				 i++; 
			 }
		 }
		 
		 if(business.getAddress().getCountryCode().equals("") || business.getAddress().getCountryCode().equals("-1")){
			 addActionError(getText("Country is Required"));
			 i++;
		 } 
		 if(partnerId==null){
		 if (business.getCssVO() != null
				 				&& business.getCssVO().getLogoImage() != null) {
				 			String fileName = business.getCssVO().getLogoImage();
				 
				 			if (fileName != null) {
				 				try {
				 					File file = new File(fileName);
				 					BufferedImage bi = ImageIO.read(file);
				 					int width = bi.getWidth();
				 					int height = bi.getHeight();
				 					if (!((width == 186 || width == 181) && height == 60)) {
				 						addActionError(getText("Logo Image Resolustion is 186X60 pixels"));
				 						i++;
				 					}
				 				} catch (Exception e) {
				 					addActionError(getText("Error in the Image File"));
				 					i++;
				 				}
				 
				 			}
				 		} else {
				 			addActionError(getText("Please Upload Logo Image Image "));
				 		}
				 
				 		if (business.getCssVO() != null
				 				&& business.getCssVO().getBackImg() != null) {
				 			String fileName = business.getCssVO().getBackImg();
				 
				 			if (fileName != null) {
				 				try {
				 					File file = new File(fileName);
				 					BufferedImage bi = ImageIO.read(file);
				 					int width = bi.getWidth();
				 					int height = bi.getHeight();
				 					if (!(width == 974 && height == 57)) {
				 						addActionError(getText("BackGround Image Resolustion is 974X57 pixels"));
				 						i++;
				 					}
				 				} catch (Exception e) {
				 					addActionError(getText("Error in the Image File"));
				 					i++;
				 				}
				 
				 			}
				 		} else {
				 			addActionError(getText("Please Upload BackGround Image "));
				 		}
				 		
				 		if (business.getCssVO() != null
					 				&& business.getCssVO().getFavIcon() != null) {
					 			String fileName = business.getCssVO().getFavIcon().getAbsolutePath();
					 
					 			if (fileName != null) {
					 				try {
					 					File file = new File(fileName);
					 					List<BufferedImage> image = ICODecoder.read(business.getCssVO().getFavIcon());
					 					BufferedImage bi = image.get(0);
					 					int width = bi.getWidth();
					 					int height = bi.getHeight();
					 					if (!(width == 16 && height == 16)) {
					 						addActionError(getText("FavIcon Resolustion is 16x16 pixels"));
					 						i++;
					 					}
					 				} catch (Exception e) {
					 					addActionError(getText("Error in the Image File"));
					 					i++;
					 				}
					 
					 			}
					 		} else {
					 			addActionError(getText("Please Upload FavIcon "));
		 								 		}
 }
		 
				 		/*if (business.getCssVO() != null
				 			&& business.getCssVO().getEmailHeader() != null) {
				 			String fileName = business.getCssVO().getEmailHeader();
				 
				 			if (fileName != null) {
				 				try {
				 					File file = new File(fileName);
				 					BufferedImage bi = ImageIO.read(file);
				 					int width = bi.getWidth();
				 					int height = bi.getHeight();
				 					if (!(width == 550 && height == 75)) {
				 						addActionError(getText("Email Header Image Resolustion is 550X75 pixels"));
				 						i++;
				 					}
				 				} catch (Exception e) {
				 					addActionError(getText("Error in the Image File"));
				 					i++;
				 				}
				 
				 			}
				 		} else {
				 			addActionError(getText("Please Upload Email Header Image "));
				 		}*/
				 		
/*	     if(business.getPartnerId()==-1){
	    	 addActionError(getText("Select partner"));
			 i++;
	     }
	     if(brID==null ||brID==-1 ){
	    	 addActionError(getText("Select Branch"));
			 i++;
	     }
	     if(cpId==null || cpId==-1 ){
	    	 addActionError(getText("Select Country"));
			 i++;
	     }*/
	    	 
		 
		 
		 if(i==0){
			 return false;
		 }else if(i>0){
		return true;
		 }
		 return false;
	}
    public String listPartnerBusiness(){
    	Long businessId = (Long)getSession().get(Constants.BUSINESS_ID_SESSION);
		// ** code for adding partner list of selected business**//
		if(businessId!=null ){
			System.out.println("SUPER PARENT----->"+BusinessFilterUtil.getSuperParentBusiness(businessId).getName());
			getSession().put(ShiplinxConstants.SESSION_BUSINESSFILTER_CUSTOMERID,BusinessFilterUtil.getBusinessLevelCustomers(businessId));
            businessList=businessDAO.getPartnerBusiness(businessId);
		return SUCCESS;
		}else if(UserUtil.getMmrUser().getUserRole().equals("busadmin")){
			getSession().put(ShiplinxConstants.SESSION_BUSINESSFILTER_CUSTOMERID,BusinessFilterUtil.getBusinessLevelCustomers(UserUtil.getMmrUser().getBusinessId()));
			businessList=businessDAO.getPartnerBusiness(UserUtil.getMmrUser().getBusinessId());
			return SUCCESS;
		}
		
		else  
		{
			addActionMessage(getText("Please Select the business"));
			return "fail";
			
		}
    }
    
    @SuppressWarnings("unchecked")
	public String listCountryPartner(){
  	    Long partnerId = (Long) getSession().get(Constants.PARTNER_ID_SESSION);
  	    Long businessId=(Long) getSession().get(Constants.BUSINESS_ID_SESSION);
  	  
  	   if(partnerId !=null && getSession().get(Constants.BUSINESS_ID_SESSION)!=null && UserUtil.getMmrUser().getUserRole().equals(ShiplinxConstants.ROLE_SYSADMIN)){
  		 System.out.println("SUPER PARENT----->"+BusinessFilterUtil.getSuperParentBusiness(partnerId).getName());
  			getSession().put(ShiplinxConstants.SESSION_BUSINESSFILTER_CUSTOMERID,BusinessFilterUtil.getPartnerLevelCustomers(businessId, partnerId));
            businessList=businessDAO.getCountryBusiness(partnerId);
   		   return SUCCESS;  
  	   }else if(!UserUtil.getMmrUser().getUserRole().equals(Constants.SYS_ADMIN_ROLE_CODE)){
  		   
  		   businessId=UserUtil.getMmrUser().getBusinessId();
  		   Business bs=businessDAO.getBusiessById(UserUtil.getMmrUser().getBusinessId());
  		   //partnerId=bs.getParentBusinessId();
   		   getSession().put(ShiplinxConstants.SESSION_BUSINESSFILTER_CUSTOMERID,BusinessFilterUtil.getPartnerLevelCustomers(partnerId,UserUtil.getMmrUser().getBusinessId()));
   		 businessList=businessDAO.getCountryBusiness(UserUtil.getMmrUser().getBusinessId());
   		   if(UserUtil.getMmrUser().isBusinessLevel()&& partnerId!=null){
   			 getSession().put(ShiplinxConstants.SESSION_BUSINESSFILTER_CUSTOMERID,BusinessFilterUtil.getPartnerLevelCustomers(businessId, partnerId));
             businessList=businessDAO.getCountryBusiness(partnerId);
             return SUCCESS;
   		   }/*else if(UserUtil.getMmrUser().isPartnerLevel()){
  			   
  			 getSession().put(ShiplinxConstants.SESSION_BUSINESSFILTER_CUSTOMERID,BusinessFilterUtil.getPartnerLevelCustomers(businessId, partnerId));
             //businessList=businessDAO.getCountryBusiness(businessId);
    		   return SUCCESS;
  		  
  	       }*/  
  	   
  	   }
  	   return SUCCESS;
     }
    
    @SuppressWarnings("unchecked")
	public String listBranchBusiness(){
    	
    	Long countryPartnerId=(Long)getSession().get(Constants.NATION_ID_SESSION);
		Long businessId=(Long) getSession().get(Constants.BUSINESS_ID_SESSION);
		Long partnerId=(Long) getSession().get(Constants.PARTNER_ID_SESSION);
		
		if(countryPartnerId !=null && businessId !=null && partnerId !=null){
			getSession().put(ShiplinxConstants.SESSION_BUSINESSFILTER_CUSTOMERID,BusinessFilterUtil.getCountryLevelCustomers(businessId, partnerId, countryPartnerId));
			businessList=businessDAO.getBranchBuisness(partnerId,countryPartnerId);
			getSession().remove("edit");
			return SUCCESS;
		}else if(!UserUtil.getMmrUser().getUserRole().equals(Constants.SYS_ADMIN_ROLE_CODE)){
			Business bs=businessDAO.getBusiessById(UserUtil.getMmrUser().getBusinessId());
			if(UserUtil.getMmrUser().isBusinessLevel() && partnerId!=null && countryPartnerId!=null){
				businessList=businessDAO.getBranchBuisness(partnerId,countryPartnerId);
			}else if(UserUtil.getMmrUser().isPartnerLevel()&& countryPartnerId!=null){
				businessList=businessDAO.getBranchBuisness(UserUtil.getMmrUser().getBusinessId(),countryPartnerId);
			}else if(UserUtil.getMmrUser().isNationLevel()){
				businessList=businessDAO.getBranchBuisness(bs.getParentBusinessId(),bs.getId());
			}
			getSession().remove("edit");
		    return SUCCESS;
		}
		
		addActionMessage(getText("please select login as Partner to access Branch"));
	    return "fail";
    }
    
	public String newPartnerBusiness(){
		
		log.debug(" CHECK METHOD IN newPartner" );
		getSession().remove("edit");
		getSession().put("CountryList", MessageUtil.getCountriesList());
		setAllCountryList((List<Country>) getSession().get("CountryList"));
		loadNewBussinessDepencies();
		Long businessId=(Long)getSession().get(Constants.BUSINESS_ID_SESSION);
				if(businessId > 0){
					Business business1 = businessDAO.getBusiessById(businessId);
					this.setBusiness(business1);
				}
		return SUCCESS;
	}
	public String savePartnerBusiness(){
		String paramBusinessId = request.getParameter("businessid");
		  
		business =this.getBusiness();
		 log.debug(" CHECK METHOD IN SAVE PARTNER Business***********   " + request.getParameter("method"));
		 
		if (request.getParameter("method").equals("add")) {
		 	Long businessId=(Long)getSession().get(Constants.BUSINESS_ID_SESSION);
				    if(validateBusiness() || validateBusinessUser("Partner")){
				    	  			    	  
						 
				    loadNewBussinessDepencies();
				    return INPUT;
				    }else{
					
					    
					    if(businessId==null && !UserUtil.getMmrUser().getUserRole().equals(Constants.SYS_ADMIN_ROLE_CODE)){
					    	
					    	businessId=UserUtil.getMmrUser().getBusinessId();
					    }
					    if(businessId!=null){
						    Business parentbus=businessDAO.getBusiessById(businessId);
						    business.setDefaultNetTerms(15);
						    business.setPartnerLevel(true);
						    business.setParentBusinessId(businessId);
						    long addressId=addressDAO.add(business.getAddress());
						    business.setUsAddressId(addressId);
						    business.setAddressId(addressId);
						 // method to add default fields of business
						    						    business = addDefaultBusiness(parentbus, business);
						    long partnerBusinessId=businessDAO.addParterLevelBusienss(business);
						    String cssText=getCssTextForBusiness(business.getCssVO());
						     addCSS(cssText, partnerBusinessId);
						     BusinessFilterUtil.loadDefaultProperty(partnerBusinessId);
						    /*if(partnerBusinessId>0){
						    	List<Long> busids=new ArrayList<Long>();
						    	busids.add(partnerBusinessId);
						    	BusinessFilterUtil bfu=new BusinessFilterUtil(busids,this.select);
						    	Thread t1=new Thread(bfu);
						    	t1.start();
						    	
						    	//loadDefaultPropertyForBusiness(partnerBusinessId);
						    }*/
						    User userPatner = setuserDetails(business.getName(), partnerBusinessId);
				    	    userPatner.setUsername(business.getName());
				    	    userPatner.setPartnerLevel(true);
				    	    userPatner.setBusinessLevel(false);
				    	    userDAO.create(userPatner, "");
				    	    BusinessFilterDAO businessFilterDAO = (BusinessFilterDAO) MmrBeanLocator.getInstance().findBean("businessFilterDAO");
				    	    userPatner.setUserFilter(new UserFilter());
				    	    userPatner.getUserFilter().setBusinessId(partnerBusinessId);
				    	    userPatner.getUserFilter().setUserName(userPatner.getUsername());
				    	    userPatner.getUserFilter().setBusinessLevel(true);
						 	businessFilterDAO.addUserFilters(userPatner.getUserFilter());
							
							Business b=businessDAO.getBusiessById(businessId);
							addActionMessage("Partner Success Fully Added Under Business :"+b.getName());
					    }
				    }	
		} else if (request.getParameter("method").equals("update")) {
			// update
			
			
			
			log.debug(" CHECK METHOD IN SAVE BUSINESS ************** UPDATE BUSINESS");
			try{
				 Long businessId=(Long)getSession().get(Constants.BUSINESS_ID_SESSION);
				
				String paramAddressId = request.getParameter("addressid");
				System.out.println(paramAddressId);
				Address addressUpdate = business.getAddress();
				addressUpdate.setAddressId(Long.valueOf(paramAddressId));
				//updating the address
				addressDAO.edit(addressUpdate);
				business.setId(Long.parseLong(paramBusinessId));
				business.setAddressId(Long.valueOf(paramAddressId));
				business.setUsAddressId(Long.valueOf(paramAddressId));
				business.setDefaultNetTerms(15);
				updateCSS();
				businessDAO.updateBusiness(business);
				//inserting the business menu
				List<MenuItemVO> menuItems = menuItemDAO.getMenuByBusiness(paramBusinessId);
				String[] uiMenuSelected = business.getMenuIds();
				
				for(int uiCount=0;uiCount < uiMenuSelected.length ;uiCount++){
					boolean isOccured = false;
					for(MenuItemVO itemVO : menuItems){
						if(itemVO.getId() == Integer.valueOf(uiMenuSelected[uiCount])){
							isOccured = true;
						}
					}
					if(!isOccured){
						menuItemDAO.saveBusinessMenu(new Long(paramBusinessId).toString(), uiMenuSelected[uiCount]);
					}
				}
				
				
				//deleting the business menu
				
				for(MenuItemVO itemVO : menuItems){
					boolean isOccured = false;
					for(int uiCount=0;uiCount < uiMenuSelected.length ;uiCount++){
						if(itemVO.getId() == Integer.valueOf(uiMenuSelected[uiCount])){
							isOccured = true;
						}
					}
					
					if(!isOccured){
						menuItemDAO.deleteBusinessMenuById(paramBusinessId, String.valueOf(itemVO.getId()));
					}
				}
				//getSession().put(ShiplinxConstants.SESSION_BUSINESSFILTER_CUSTOMERID,BusinessFilterUtil.getSysadminLevelCustomers());
				Business b=businessDAO.getBusiessById(businessId);
				getSession().remove("editBusiness");
				
				
				addActionMessage(getText("business.update.success"));
				return SUCCESS;
			}catch(Exception e){
				addActionError(getText("business.save.error"));
				return INPUT;
			}
			
		
		}
		return SUCCESS;
		 
		
	}
	
	
	public String newCountryBusiness(){
		log.debug(" CHECK METHOD IN newCountry" );
		  loadNewBussinessDepencies();
		   getSession().put("CountryList", MessageUtil.getCountriesList()); 
		   Long businessId=(Long)getSession().get(Constants.PARTNER_ID_SESSION);
		   			if(businessId > 0){
		   				Business business1 = businessDAO.getBusiessById(businessId);
		   				this.setBusiness(business1);
		   			}
		   return SUCCESS;
	 
	}
	
	public String saveCountryBusiness(){
		String paramBusinessId = request.getParameter("businessid");
		  

	   	business =this.getBusiness();
		 log.debug(" CHECK METHOD IN SAVE Country Business***********   " + request.getParameter("method"));
		if (request.getParameter("method").equals("add")) {
			Long businessId=(Long)getSession().get(Constants.BUSINESS_ID_SESSION);
		     Long  partnerId=(Long)getSession().get(Constants.PARTNER_ID_SESSION);
					if(validateBusiness() || validateBusinessUser("Nation")){
						loadNewBussinessDepencies();
						return INPUT;
					}else{
					     
                         if(businessId==null && !UserUtil.getMmrUser().getUserRole().equals(Constants.SYS_ADMIN_ROLE_CODE)){
					    	businessId=UserUtil.getMmrUser().getBusinessId();
					    }
					    if(businessId!=null && partnerId!=null){
						    Business parentbus=businessDAO.getBusiessById(businessId);
						    Business parentbus2=businessDAO.getBusiessById(partnerId);
						    business.setDefaultNetTerms(15);
						    business.setNationLevel(true);
						    business.setPartnerId(partnerId);
						    business.setParentBusinessId(partnerId);
						    long addressId=addressDAO.add(business.getAddress());
						    business.setUsAddressId(addressId);
						    business.setAddressId(addressId);
						    // method to add default fields of business
						    						    business = addDefaultBusiness(parentbus2, business);
						    long countryBusinessId=businessDAO.addCountryLevelBusienss(business);
						    BusinessFilterUtil.loadDefaultProperty(countryBusinessId);
						   /* if(countryBusinessId>0){
//						    	loadDefaultPropertyForBusiness(countryBusinessId);

						    	List<Long> busids=new ArrayList<Long>();
						    	busids.add(countryBusinessId);
						    	BusinessFilterUtil bfu=new BusinessFilterUtil(busids,this.select);
						    	Thread t1=new Thread(bfu);
						    	t1.start();
						    }*/
						     User usercountry = setuserDetails(business.getName(), countryBusinessId);
							 usercountry.setUsername(business.getName());
							 usercountry.setNationLevel(true);
							 usercountry.setBusinessLevel(false);
							 userDAO.create(usercountry, "");
							 
							    BusinessFilterDAO businessFilterDAO = (BusinessFilterDAO) MmrBeanLocator.getInstance().findBean("businessFilterDAO");
							    usercountry.setUserFilter(new UserFilter());
							    usercountry.getUserFilter().setBusinessId(countryBusinessId);
							    usercountry.getUserFilter().setUserName(usercountry.getUsername());
					    	    usercountry.getUserFilter().setPartnerLevel(true);
							 	businessFilterDAO.addUserFilters(usercountry.getUserFilter());
								
							 
							Business b=businessDAO.getBusiessById(businessId);
							addActionMessage("Nation Success Fully Added Under Business :"+b.getName());
							businessList=businessDAO.getCountryBusiness(partnerId);
					    }
					}
			 			
		} else if (request.getParameter("method").equals("update")) {
			// update
			log.debug(" CHECK METHOD IN SAVE BUSINESS ************** UPDATE BUSINESS");
			try{
				String paramAddressId = request.getParameter("addressid");
				System.out.println(paramAddressId);
				Address addressUpdate = business.getAddress();
				addressUpdate.setAddressId(Long.valueOf(paramAddressId));
				//updating the address
				addressDAO.edit(addressUpdate);
				business.setId(Long.parseLong(paramBusinessId));
				business.setAddressId(Long.valueOf(paramAddressId));
				business.setUsAddressId(Long.valueOf(paramAddressId));
				business.setDefaultNetTerms(15);
				businessDAO.updateBusiness(business);
				//inserting the business menu
				List<MenuItemVO> menuItems = menuItemDAO.getMenuByBusiness(paramBusinessId);
				String[] uiMenuSelected = business.getMenuIds();
				
				for(int uiCount=0;uiCount < uiMenuSelected.length ;uiCount++){
					boolean isOccured = false;
					for(MenuItemVO itemVO : menuItems){
						if(itemVO.getId() == Integer.valueOf(uiMenuSelected[uiCount])){
							isOccured = true;
						}
					}
					if(!isOccured){
						menuItemDAO.saveBusinessMenu(new Long(paramBusinessId).toString(), uiMenuSelected[uiCount]);
					}
				}
				
				//deleting the business menu
				
				for(MenuItemVO itemVO : menuItems){
					boolean isOccured = false;
					for(int uiCount=0;uiCount < uiMenuSelected.length ;uiCount++){
						if(itemVO.getId() == Integer.valueOf(uiMenuSelected[uiCount])){
							isOccured = true;
						}
					}
					
					if(!isOccured){
						menuItemDAO.deleteBusinessMenuById(paramBusinessId, String.valueOf(itemVO.getId()));
					}
				}
				//getSession().put(ShiplinxConstants.SESSION_BUSINESSFILTER_CUSTOMERID,BusinessFilterUtil.getSysadminLevelCustomers());
				Business bus=businessDAO.getBusiessById(business.getId());
				businessList=businessDAO.getCountryBusiness(bus.getParentBusinessId());
				getSession().remove("editBusiness");
				
				
				addActionMessage(getText("business.update.success"));
				return SUCCESS;
			}catch(Exception e){
				addActionError(getText("business.save.error"));
				return INPUT;
			}
		}
		
		return SUCCESS;
	}
	
	public String saveBranchBusienss(){
		
		String paramBusinessId = request.getParameter("businessid");
		  
		business =this.getBusiness();
		 log.debug(" CHECK METHOD IN SAVE Country Business***********   " + request.getParameter("method"));
		if (request.getParameter("method").equals("add")) {
			   Long businessId=(Long)getSession().get(Constants.BUSINESS_ID_SESSION);
			    Long partnerId=(Long)getSession().get(Constants.PARTNER_ID_SESSION);
			    Long countryParterId=(Long)getSession().get(Constants.NATION_ID_SESSION);
			  
					if(validateBusiness()  || validateBusinessUser("Branch")){
						loadNewBussinessDepencies();
						return INPUT;
					}else{
					   
                        if(businessId==null && !UserUtil.getMmrUser().getUserRole().equals(Constants.SYS_ADMIN_ROLE_CODE)){
					    	
					    	businessId=UserUtil.getMmrUser().getBusinessId();
					    }
					    if(businessId!=null && partnerId!=null && countryParterId !=null){
						    Business parentbus=businessDAO.getBusiessById(businessId);
						    Business parentbus2=businessDAO.getBusiessById(countryParterId);
						    business.setDefaultNetTerms(15);
						    business.setBranchLevel(true);
						    business.setPartnerId(partnerId);
						    business.setCountryPartnerId(countryParterId);
						    business.setParentBusinessId(countryParterId);
						    long addressId=addressDAO.add(business.getAddress());
						    business.setUsAddressId(addressId);
						    business.setAddressId(addressId);
						 // method to add default fields of business
						    						    business = addDefaultBusiness(parentbus2, business);
						    long BranchBusId=businessDAO.addBranchLevelBusiness(business);
						    BusinessFilterUtil.loadDefaultProperty(BranchBusId);
						    /*if(BranchBusId>0){
						    	List<Long> busids=new ArrayList<Long>();
						    	busids.add(BranchBusId);
						    	BusinessFilterUtil bfu=new BusinessFilterUtil(busids,this.select);
						    	Thread t1=new Thread(bfu);
						    	t1.start();
						    	//loadDefaultPropertyForBusiness(BranchBusId);
						    }*/
						     User userBranch = setuserDetails(business.getName(), BranchBusId);
						     userBranch.setUsername(business.getName() );
						     userBranch.setBranchLevel(true);
						     userBranch.setBusinessLevel(false);
						     
							 userDAO.create(userBranch, "");
							 
							 
							 BusinessFilterDAO businessFilterDAO = (BusinessFilterDAO) MmrBeanLocator.getInstance().findBean("businessFilterDAO");
							 userBranch.setUserFilter(new UserFilter());
							 userBranch.getUserFilter().setBusinessId(BranchBusId);
							 userBranch.getUserFilter().setUserName(userBranch.getUsername());
							 userBranch.getUserFilter().setNationLevel(true);
							 businessFilterDAO.addUserFilters(userBranch.getUserFilter());
							 
						    		
							 
							Business b=businessDAO.getBusiessById(businessId);
							addActionMessage("Branch Success Fully Added Under Business :"+b.getName());
							businessList=businessDAO.getBranchBuisness(partnerId, countryParterId);
					    }
					}
		} else if (request.getParameter("method").equals("update")) {
			// update
			
			log.debug(" CHECK METHOD IN SAVE BUSINESS ************** UPDATE BUSINESS");
			try{
				Long businessId=(Long)getSession().get(Constants.BUSINESS_ID_SESSION);
			    Long partnerId=(Long)getSession().get(Constants.PARTNER_ID_SESSION);
			    Long countryParterId=(Long)getSession().get(Constants.NATION_ID_SESSION);
				
				String paramAddressId = request.getParameter("addressid");
				System.out.println(paramAddressId);
				Address addressUpdate = business.getAddress();
				addressUpdate.setAddressId(Long.valueOf(paramAddressId));
				//updating the address
				addressDAO.edit(addressUpdate);
				business.setId(Long.parseLong(paramBusinessId));
				business.setAddressId(Long.valueOf(paramAddressId));
				business.setUsAddressId(Long.valueOf(paramAddressId));
				business.setDefaultNetTerms(15);
				businessDAO.updateBusiness(business);
				//inserting the business menu
				List<MenuItemVO> menuItems = menuItemDAO.getMenuByBusiness(paramBusinessId);
				String[] uiMenuSelected = business.getMenuIds();
				
				for(int uiCount=0;uiCount < uiMenuSelected.length ;uiCount++){
					boolean isOccured = false;
					for(MenuItemVO itemVO : menuItems){
						if(itemVO.getId() == Integer.valueOf(uiMenuSelected[uiCount])){
							isOccured = true;
						}
					}
					if(!isOccured){
						menuItemDAO.saveBusinessMenu(new Long(paramBusinessId).toString(), uiMenuSelected[uiCount]);
					}
				}
				
				
				//deleting the business menu
				
				for(MenuItemVO itemVO : menuItems){
					boolean isOccured = false;
					for(int uiCount=0;uiCount < uiMenuSelected.length ;uiCount++){
						if(itemVO.getId() == Integer.valueOf(uiMenuSelected[uiCount])){
							isOccured = true;
						}
					}
					
					if(!isOccured){
						menuItemDAO.deleteBusinessMenuById(paramBusinessId, String.valueOf(itemVO.getId()));
					}
				}
				//getSession().put(ShiplinxConstants.SESSION_BUSINESSFILTER_CUSTOMERID,BusinessFilterUtil.getSysadminLevelCustomers());
				businessList=businessDAO.getBranchBuisness(partnerId, countryParterId);
				getSession().remove("editBusiness");
				
				
				addActionMessage(getText("business.update.success"));
				return SUCCESS;
			}catch(Exception e){
				addActionError(getText("business.save.error"));
				return INPUT;
			}
			
		

		}
		return SUCCESS;
	}
    private boolean validateBusinessUser(String level) {
		// TODO Auto-generated method stub
    	  if(userDAO.findUserByUsername(business.getName())!=null){
				addActionError(getText(level+" Business User Name Already Exist "));
				return true;
			}
		return false;
	}

	public String newBranchBusiness(){
    	log.debug(" CHECK METHOD IN newBranchBusiness" );
		  loadNewBussinessDepencies();
		   getSession().put("CountryList", MessageUtil.getCountriesList()); 
		   Long businessId=(Long)getSession().get(Constants.NATION_ID_SESSION);
		   			if(businessId > 0){
		   				Business business1 = businessDAO.getBusiessById(businessId);
		   				this.setBusiness(business1);
		   			}
		   return SUCCESS;
    	
    	
     
    }
	 
    public String logInAsBusiness(){
 	   if(request.getParameter("id")!=null || getSession().get(Constants.BUSINESS_ID_SESSION)!=null ){
 	        long businessId;
 	        businessId=Long.parseLong(request.getParameter("id"));
 	        getSession().put(Constants.BUSINESS_ID_SESSION, businessId);
 	        Business bus=businessDAO.getBusiessById(businessId);
 	        log.info("INTO TO THE  BUSINESS LEVEL FOR BUSINESS :"+bus.getSystemName());
 	        log.debug("Business Id+ :"+bus.getId());
 	        
 	        //getSession().remove(Constants.BUSINESS_ID_SESSION);
 	        return SUCCESS;
 			}else if(UserUtil.getMmrUser().getUserRole().equals("busadmin")){
 				return SUCCESS;
 			}else{
 				addActionMessage(getText("Please Select the business"));
 				return "fail";
 			}
    }
    
    public String logInAsPartner(){
		   if(request.getParameter("partnerParam")!=null && getSession().get(Constants.BUSINESS_ID_SESSION)!=null){
			   long partnerId=Long.parseLong(request.getParameter("partnerParam"));
			   getSession().put(Constants.PARTNER_ID_SESSION, partnerId);
			   return SUCCESS;
		   }else if(UserUtil.getMmrUser().isBusinessLevel() ){
			   long partnerId=Long.parseLong(request.getParameter("partnerParam"));
			   getSession().put(Constants.PARTNER_ID_SESSION, partnerId);
			   
			   return SUCCESS;
		   }else if(UserUtil.getMmrUser().isPartnerLevel()){
			   long partnerId=Long.parseLong(request.getParameter("partnerParam"));
			   getSession().put(Constants.PARTNER_ID_SESSION, partnerId);
			   return SUCCESS;
	   		}else {
			
			return "fail";
		}
	   }
	
    public String logInAsCountry(){
 	   if(request.getParameter("countryparam")!=null && getSession().get(Constants.BUSINESS_ID_SESSION)!=null && getSession().get("partnerId")!=null){
 			long countryPartnerId=Long.parseLong(request.getParameter("countryparam"));
 			getSession().put(Constants.NATION_ID_SESSION, countryPartnerId);
 			return SUCCESS;
 		}else if(UserUtil.getMmrUser().isBusinessLevel() || UserUtil.getMmrUser().isPartnerLevel()){
 			long countryPartnerId=Long.parseLong(request.getParameter("countryparam"));
 			getSession().put(Constants.NATION_ID_SESSION, countryPartnerId);
 			return SUCCESS;	
 		}
 			addActionMessage(getText("please select login as Partner to access Branch"));
 			//branchList = businessFilterDAO.getBranchList();
 		    return "fail";
    }
    
    
    public String loginBranch(){
		Long countryPartnerId=(Long)getSession().get("countryPartnerId");
		Long businessId=(Long) getSession().get(Constants.BUSINESS_ID_SESSION);
		Long partnerId=(Long) getSession().get("partnerId");
		if(request.getParameter("branchParam")!=null && getSession().get(Constants.BUSINESS_ID_SESSION)!=null && getSession().get("partnerId")!=null && getSession().get("countryPartnerId")!=null){
			long branchId=Long.parseLong(request.getParameter("branchParam"));
			getSession().put(ShiplinxConstants.SESSION_BUSINESSFILTER_CUSTOMERID,BusinessFilterUtil.getBranchLevelCustomers(businessId, partnerId, countryPartnerId,branchId));
			getSession().put(Constants.BRANCH_ID_SESSION, branchId);
			 
		}else if(UserUtil.getMmrUser().isBusinessLevel() || UserUtil.getMmrUser().isPartnerLevel() || UserUtil.getMmrUser().isNationLevel()){
			
			long branchId=Long.parseLong(request.getParameter("branchParam"));
			getSession().put(ShiplinxConstants.SESSION_BUSINESSFILTER_CUSTOMERID,BusinessFilterUtil.getBranchLevelCustomers(businessId, partnerId, countryPartnerId,branchId));
			getSession().put(Constants.BRANCH_ID_SESSION, branchId);
			   
			   return SUCCESS;
		   }
		   
		return SUCCESS;
	}
	private void uploadCss() {
		// TODO Auto-generated method stub
		
		InputStream uploadStream=null;
		/*System.out.println(request.getPathInfo()+"--- "+request.getServletPath()+"---"+request.getContextPath());
		*/String filePath = request.getSession().getServletContext().getRealPath("/");
		OutputStream bos = null;
		File outFile = new File(request.getContextPath()+"../mmr/images/business");
	    try{
	    	uploadStream=new DataInputStream(new FileInputStream(this.getFileUpload()));
			int bytesRead = 0;
			byte[] buffer = new byte[8192];
	
			boolean bFirstTime = true;
			while ((bytesRead = uploadStream.read(buffer, 0, 8192)) != -1) {
				if (bFirstTime) {
					bFirstTime = false;
					bos = new FileOutputStream(outFile);
				}
				bos.write(buffer, 0, bytesRead);
			}
	    }catch(Exception e){
	    	
	    }finally{
	    	
	    	try {
				bos.close();
				uploadStream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	
	    }
 
		
	}

	private void addDefaultBusinessLoaders(long businessId) {
		// TODO Auto-generated method stub
		BusinessDefaultLoaderDaoImpl defLoader=new BusinessDefaultLoaderDaoImpl(businessId, 1);
        List<Integer> toUpdateLoader =new ArrayList<Integer>();
		 if(select!=null && select.size()>0){
			
			   
					 for(int i=0;i<select.size();i++){
						 if(select.get(i)){
						 toUpdateLoader.add(i+1);
						 }
						 					 
					 }
			
		}
		if(toUpdateLoader.size()==7){
			defLoader.loadAll();
		}else if(toUpdateLoader.size()>0){
			
			for(Integer i:toUpdateLoader){
				if(i==1){
					defLoader.loadBusinessCarrier();
				}else if(i==2){
					defLoader.loadBusinessMenu();
				}else if(i==3){
					defLoader.loadCustomerMarkUp();
				}else if(i==4){
					defLoader.loadEdiInfo();
				}else if(i==5){
					defLoader.loadPoundRate();
				}else if(i==6){
					defLoader.loadSkidRate();
				}else if(i==7){
					defLoader.loadMerchantAccount();
				}
			}
			
		}
	}
	
	@SuppressWarnings("unchecked")
	public String editBusiness(){
		log.debug(" CHECK METHOD IN editBusiness" );
		System.out.println(businessId);
		try{
			long selectedBusinessId = Long.parseLong(businessId);
			getSession().put("editBusinessId",selectedBusinessId);
			business = businessDAO.getBusiessById(selectedBusinessId);
			CSSVO cssVo = businessDAO.getCSSDetailsForBusiness(selectedBusinessId);
									if(cssVo != null){
									business.setCssVO(cssVo);
									}else{
										business.setCssVO(new CSSVO());
										business.getCssVO().setMenuColor("#000000");
										business.getCssVO().setBarSecondColor("#000000");
										business.getCssVO().setFooterColor("#000000");
										business.getCssVO().setMenuHoverColor("#990000");
										business.getCssVO().setBarFirstColor("#990000");
										business.getCssVO().setButtonColor("#990000");
										
									}
			Address address = addressDAO.findAddressById(String.valueOf(business.getAddressId()));
			business.setAddress(address);
			menuVo = menuItemDAO.getAllMenu();
			List<MenuItemVO> menuItems = menuItemDAO.getMenuByBusiness(String.valueOf(selectedBusinessId));
			int count = 0;
			selectedMenuIds = new int[menuItems.size()];
			for(MenuItemVO menuItemVO : menuItems){
				selectedMenuIds[count] = menuItemVO.getId();
				count++;
			}
			getSession().put("CountryList", MessageUtil.getCountriesList());
			getSession().put("editBusiness","true");
			if(business.getCssVO()!=null){
							business.getCssVO().setBusinessEmailList(businessDAO.getBusinessEmails(1L));
							}else{
								business.setCssVO(new CSSVO());
								business.getCssVO().setBusinessEmailList(businessDAO.getBusinessEmails(1L));
							}
							getSession().put("businessEmailList",businessDAO.getBusinessEmails(selectedBusinessId));
							if(getSession().get("businessEmailList")!=null){
								List<Long> busEIds=new ArrayList<Long>();
							for(BusinessEmail bb:(List<BusinessEmail>)getSession().get("businessEmailList")){
							busEIds.add(bb.getBusinessEmailId());	
							}
							getSession().put("emailRList", busEIds);
							}
							
							business.getCssVO().setBusinessEmailList(setBusSychedBusEmail(businessDAO.getBusinessEmails(selectedBusinessId),business.getCssVO().getBusinessEmailList()));
							if(!(business.getCssVO().getBusinessEmailList()!=null &&business.getCssVO().getBusinessEmailList().size()>0)){
								business.getCssVO().setBusinessEmailList(businessDAO.getBusinessEmails(1L));
							}
							getSession().put("localeList", MessageUtil.getLocales());
							//getSession().remove("emailRList");
							getSession().remove("redudant");
			return SUCCESS;
			
		}catch(Exception e){
			addActionError(getText("role.update.error"));
			return INPUT;
		}
	}
	
	
	/**
	 *  
	 * @param newBusinessEmails
	 * @param defBusinessEmails
	 * @return
	 */
	private List<BusinessEmail> setBusSychedBusEmail(
						List<BusinessEmail> newBusinessEmails,
						List<BusinessEmail> defBusinessEmails) {
					// TODO Auto-generated method stub
					if(newBusinessEmails!=null && defBusinessEmails!=null && newBusinessEmails.size()>0 && defBusinessEmails.size()>0){
						
						
						for(BusinessEmail defb:defBusinessEmails){
							
							for(BusinessEmail newIb:newBusinessEmails){
								
									if(newIb.getEmailType().equals(defb.getEmailType())){
											defb.setBusinessEmailId(newIb.getBusinessEmailId());
										}
									
								
							}
						}
						
						return defBusinessEmails;
					}
					return new ArrayList<BusinessEmail>();
				}
	
	public String viewBusiness(){
		log.debug(" CHECK METHOD IN view business" );
		System.out.println(businessId);
		try{
			long selectedBusinessId = Long.parseLong(businessId);
			business = businessDAO.getBusiessById(selectedBusinessId);
			Address address = addressDAO.findAddressById(String.valueOf(business.getAddressId()));
			business.setAddress(address);
			menuVo = menuItemDAO.getAllMenu();
			List<MenuItemVO> menuItems = menuItemDAO.getMenuByBusiness(String.valueOf(selectedBusinessId));
			int count = 0;
			selectedMenuIds = new int[menuItems.size()];
			for(MenuItemVO menuItemVO : menuItems){
				selectedMenuIds[count] = menuItemVO.getId();
				count++;
			}
			getSession().put("CountryList", MessageUtil.getCountriesList());
		 
			return SUCCESS;
			
		}catch(Exception e){
			addActionError(getText("role.update.error"));
			return INPUT;
		}
	}
	public String editPartnerBusiness(){
	 return editBusiness();
	}
	
public String editNationBusiness(){
	
	return editBusiness();
}

public String editBranchBusiness(){
	return editBusiness();
}

	public String deleteBusiness(){
		try{
			log.debug(" CHECK METHOD IN DELETE BUSINESS  ");
			businessDAO.deleteBusiness(businessId);
			addActionMessage(getText("business.delete.success"));
		}catch(Exception e){
			log.debug("Error in Deleting the business : "+ e.getMessage());
			addActionError(getText("business.delete.error"));
		}
		return SUCCESS;
	}
	
	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public BusinessDAO getBusinessDAO() {
		return businessDAO;
	}

	public void setBusinessDAO(BusinessDAO businessDAO) {
		this.businessDAO = businessDAO;
	}

	public Business getBusiness() {
		return business;
	}

	public void setBusiness(Business business) {
		this.business = business;
	}

	public List<Business> getBusinessList() {
		return businessList;
	}

	public void setBusinessList(List<Business> businessList) {
		this.businessList = businessList;
	}

	
	public User setuserDetails(String userName,long businessId){
		User user = new User();
		user.setUsername(userName);
		user.setPassword("admin@123");
		user.setFirstName(userName);
		user.setLastName("admin");
		user.setEmail("admin@ic.com");
		user.setPhoneNumber("555-555-5555");
		user.setStatus("A");
		user.setCustomerId(0);
		user.setBusinessId(businessId);
		user.setPrintNoOfLabels(5);
		user.setPrintNoOfCI(5);
		user.setAutoPrint(true);
		user.setUserRole("busadmin");
		user.setLocale("en_CA");
		user.setBusinessLevel(true);
		
		return user;
	}

	public UserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	public List<MenuItemVO> getMenuVo() {
		return menuVo;
	}

	public void setMenuVo(List<MenuItemVO> menuVo) {
		this.menuVo = menuVo;
	}

	public MenusDAO getMenuItemDAO() {
		return menuItemDAO;
	}

	public void setMenuItemDAO(MenusDAO menuItemDAO) {
		this.menuItemDAO = menuItemDAO;
	}

	public String getBusinessId() {
		return businessId;
	}

	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}

	public int[] getSelectedMenuIds() {
		return selectedMenuIds;
	}

	public void setSelectedMenuIds(int[] selectedMenuIds) {
		this.selectedMenuIds = selectedMenuIds;
	}

	public AddressDAO getAddressDAO() {
		return addressDAO;
	}

	public void setAddressDAO(AddressDAO addressDAO) {
		this.addressDAO = addressDAO;
	}

	public BusinessFilterDAO getBusinessFilterDAO() {
		return businessFilterDAO;
	}

	public void setBusinessFilterDAO(BusinessFilterDAO businessFilterDAO) {
		this.businessFilterDAO = businessFilterDAO;
	}

	
	public List<String> getDefaultLoaders() {
		return defaultLoaders;
	}

	public void setDefaultLoaders(List<String> defaultLoaders) {
		this.defaultLoaders = defaultLoaders;
	}

	public String[] getSelectedLoaders() {
		return selectedLoaders;
	}

	public void setSelectedLoaders(String[] selectedLoaders) {
		this.selectedLoaders = selectedLoaders;
	}

	public List<Boolean> getSelect() {
		return select;
	}

	public void setSelect(List<Boolean> select) {
		this.select = select;
	}

	public String getFileUploadContentType() {
		return fileUploadContentType;
	}
 
	public void setFileUploadContentType(String fileUploadContentType) {
		this.fileUploadContentType = fileUploadContentType;
	}
 
	public String getFileUploadFileName() {
		return fileUploadFileName;
	}
 
	public void setFileUploadFileName(String fileUploadFileName) {
		this.fileUploadFileName = fileUploadFileName;
	}
 
	public File getFileUpload() {
		return fileUpload;
	}
 
	public void setFileUpload(File fileUpload) {
		this.fileUpload = fileUpload;
	}

 
	public List<Country> getAllCountryList() {
		return allCountryList;
	}

	public void setAllCountryList(List<Country> allCountryList) {
		this.allCountryList = allCountryList;
	}
	

private Business addDefaultBusiness(Business parentbus,Business business){
		
		// adding default business
		if(parentbus.getLogoFileName() != null && !(parentbus.getLogoFileName().isEmpty())){
	    	business.setLogoFileName(parentbus.getLogoFileName());
	    }
	    if(parentbus.getLogoHiResFileName() != null && !(parentbus.getLogoHiResFileName().isEmpty())){
	    	business.setLogoHiResFileName(parentbus.getLogoHiResFileName());
	    }
	    if(parentbus.getTaxInfo() != null && !(parentbus.getTaxInfo().isEmpty())){
	    	business.setTaxInfo(parentbus.getTaxInfo());
	    }
	    if(parentbus.getFinanceEmail() != null && !(parentbus.getFinanceEmail().isEmpty())){
	    	business.setFinanceEmail(parentbus.getFinanceEmail());
	    }
	    if(parentbus.getWarehouseEmail() != null && !(parentbus.getWarehouseEmail().isEmpty())){
	    	business.setWarehouseEmail(parentbus.getWarehouseEmail());
	    }
	    if(parentbus.getInvoiceNotificationBody() != null && !(parentbus.getInvoiceNotificationBody().isEmpty())){
	    	business.setInvoiceNotificationBody(parentbus.getInvoiceNotificationBody());
	    }
	    if(parentbus.getCustomerNotificationBody() != null && !(parentbus.getCustomerNotificationBody().isEmpty())){
	    	business.setCustomerNotificationBody(parentbus.getCustomerNotificationBody());
	    }
	    if(parentbus.getCustomerNotificationSubject() != null && !(parentbus.getCustomerNotificationSubject().isEmpty())){
	    	business.setCustomerNotificationSubject(parentbus.getCustomerNotificationSubject());
	    }
	    if(parentbus.getRatesNotificationBody() != null && !(parentbus.getRatesNotificationBody().isEmpty())){
	    	business.setRatesNotificationBody(parentbus.getRatesNotificationBody());
	    }
	    if(parentbus.getAddCustomerNotificationBody() != null && !(parentbus.getAddCustomerNotificationBody().isEmpty())){
	    	business.setAddCustomerNotificationBody(parentbus.getCustomerNotificationBody());
	    }
	    if(parentbus.getAddCustomerNotificationSubject() != null && !(parentbus.getAddCustomerNotificationSubject().isEmpty())){
	    	business.setAddCustomerNotificationSubject(parentbus.getAddCustomerNotificationSubject());
	    }
	    if(parentbus.getLtlEmail() != null && !(parentbus.getLtlEmail().isEmpty())){
	    	business.setLtlEmail(parentbus.getLtlEmail());
	    }
	    if(parentbus.getReportPath() != null && !(parentbus.getReportPath().isEmpty())){
	    	business.setReportPath(parentbus.getReportPath());
	    }
	    if(parentbus.getReportPathInvoice() != null && !(parentbus.getReportPathInvoice().isEmpty())){
	    	business.setReportPathInvoice(parentbus.getReportPathInvoice()); 
	    }
	    if(parentbus.getOrderNotificationBody() != null && !(parentbus.getOrderNotificationBody().isEmpty())){
	    	business.setOrderNotificationBody(parentbus.getOrderNotificationBody());
	    }
	    if(parentbus.getShipOrderNotificationBody() != null && !(parentbus.getShipOrderNotificationBody().isEmpty())){
	    	business.setShipOrderNotificationBody(parentbus.getShipOrderNotificationBody());
	    }
	    if(parentbus.getShipOrderNotificationSubject() != null && !(parentbus.getShipOrderNotificationSubject().isEmpty())){
	    	business.setShipOrderNotificationSubject(parentbus.getShipOrderNotificationSubject());
	    }
	    return business;
	}
	
public UserBusiness getUserBusiness() {
       return userBusiness;
   }

   public void setUserBusiness(UserBusiness userBusiness) {
       this.userBusiness = userBusiness;
   }

@Override
   public void setServletResponse(HttpServletResponse arg0) {
   	// TODO Auto-generated method stub
   	this.response=arg0;
   }

public List<BusinessEmail> getBusinessEmailList() {
	return businessEmailList;
}

public void setBusinessEmailList(List<BusinessEmail> businessEmailList) {
	this.businessEmailList = businessEmailList;
}
    
	
	
}
