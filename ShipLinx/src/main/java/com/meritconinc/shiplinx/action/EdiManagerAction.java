package com.meritconinc.shiplinx.action;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.meritconinc.mmr.utilities.StringUtil;
import com.meritconinc.mmr.utilities.security.UserUtil;
import com.meritconinc.shiplinx.carrier.purolator.stub.ServiceAvailabilityWebServiceClient;
import com.meritconinc.shiplinx.model.Carrier;
import com.meritconinc.shiplinx.model.EdiInfo;
import com.meritconinc.shiplinx.model.EdiItem;
import com.meritconinc.shiplinx.service.CarrierServiceManager;
import com.meritconinc.shiplinx.service.EdiManager;
import com.meritconinc.shiplinx.utils.ShiplinxConstants;
import com.opensymphony.xwork2.Preparable;

public class EdiManagerAction extends BaseAction implements Preparable,
		ServletRequestAware {
	private static final long serialVersionUID = 250927861;
	private static final Logger log = LogManager
			.getLogger(EdiManagerAction.class);
	public HttpServletRequest request;
	private EdiManager ediManagerService;
	private CarrierServiceManager carrierServiceManager;
	private List<EdiItem> ediItemList;
	private List<Carrier> carriers;
	private File upload;
	public String getEdiFileNameId() {
		return ediFileNameId;
	}

	public void setEdiFileNameId(String ediFileNameId) {
		this.ediFileNameId = ediFileNameId;
	}
	private String ediFileNameId;
	private String ediFileName;
	public String getEdiFileName() {
		return ediFileName;
	}

	public void setEdiFileName(String ediFileName) {
		this.ediFileName = ediFileName;
	}
	private String uploadFileName;
	private Map<String, Long> ediInvoiceSearchResult = new HashMap<String, Long>();
	private Map<String, Long> ediFileNameSearchResult = new HashMap<String, Long>();
//	private String [] ediStatusList = {"", ShiplinxConstants.UPLOADED, ShiplinxConstants.INPROGRESS, 
//											ShiplinxConstants.PROCESSED, ShiplinxConstants.RELEASED, 
//											ShiplinxConstants.FAILED};
	
	@Override
	public void prepare() throws Exception {
		// TODO Auto-generated method stub

	} 

	@Override
	public void setServletRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		this.request = request;
	}

	public EdiManager getEdiManagerService() {
		return ediManagerService;
	}

	public void setEdiManagerService(EdiManager ediManagerService) {
		this.ediManagerService = ediManagerService;
	}

	public CarrierServiceManager getCarrierServiceManager() {
		return carrierServiceManager;
	}

	public void setCarrierServiceManager(
			CarrierServiceManager carrierServiceManager) {
		this.carrierServiceManager = carrierServiceManager;
	}

	public List<EdiItem> getEdiItemList() {
		return ediItemList;
	}

	public void setEdiItemList(List<EdiItem> ediItemList) {
		this.ediItemList = ediItemList;
	}

	public List<Carrier> getCarriers() {
		if (carriers == null) {
			carriers = this.carrierServiceManager.getCarriersForBusiness(UserUtil.getMmrUser().getBusinessId());
			Carrier c = new Carrier();
			c.setId(-1L);
			c.setName("");
			carriers.add(0, c);
		}
		return carriers;
	}

	public void setCarriers(List<Carrier> carriers) {
		this.carriers = carriers;
	}

	public EdiInfo getEdiInfo() {
		return (EdiInfo) getSession().get("ediInfo");
	}

	@SuppressWarnings("unchecked")
	public void setEdiInfo(EdiInfo edi) {
		getSession().put("ediInfo", edi);
	}
	
	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}
	
	public EdiItem getEdiItem() {
		return (EdiItem) getSession().get("ediItem");
	}

	public Map<String, Long> getEdiInvoiceSearchResult() {
		return ediInvoiceSearchResult;
	}

	public void setEdiInvoiceSearchResult(Map<String, Long> ediInvoiceSearchResult) {
		this.ediInvoiceSearchResult = ediInvoiceSearchResult;
	}

	public Map<String, Long> getEdiFileNameSearchResult() {
		return ediFileNameSearchResult;
	}

	public void setEdiFileNameSearchResult(Map<String, Long> ediFileNameSearchResult) {
		this.ediFileNameSearchResult = ediFileNameSearchResult;
	}

	@SuppressWarnings("unchecked")
	public void setEdiItem(EdiItem item) {
		getSession().put("ediItem", item);
	}
	
	public EdiItem init() throws Exception {
		EdiItem item = this.getEdiItem();
		try {
			if (item == null) {
				item = new EdiItem();
				item.setBusinessId(UserUtil.getMmrUser().getBusinessId());
//				item.setCarrierId(1L);
				this.setEdiItem(item);
			}
			if (this.carrierServiceManager != null) {
				carriers = this.carrierServiceManager.getCarriersForBusiness(UserUtil.getMmrUser().getBusinessId());
				getSession().put("CARRIERS", carriers);
			}
			if (getSession().get("EDI_STATUS_LIST") == null) {
				getSession().put("EDI_STATUS_LIST", EdiItem.ediStatusList);
			}
	    } catch (Exception e) {
	    	e.printStackTrace();
	    	addActionError(getText("content.error.unexpected"));
		}
	    return item;
	}	

	public String searchEdi() throws Exception {
		try {
			// Test Code
//			ServiceAvailabilityWebServiceClient.Main();
			// ----------------------------------------
			
			String method = this.request.getParameter("method");
			if (method != null && method.equals("reset")) {
				getSession().remove("ediItem");	
			}
			EdiItem item = this.getEdiItem();

			if (item == null)
				item = init();
			
			if (this.ediManagerService != null && method==null) {
				ediItemList = this.ediManagerService.findEdiItems(item);
			}
		} catch (Exception e) {
			e.printStackTrace();
			// addActionError(getText("content.error.unexpected"));
			addActionError(e.getMessage());
		}
		return SUCCESS;
	}
	

	@SuppressWarnings("unchecked")
	public String uploadEdi() throws Exception {
		try {
			EdiInfo ediInfo = this.getEdiInfo();
			if (ediInfo == null) {
				ediInfo = new EdiInfo();
				ediInfo.setBusinessId(UserUtil.getMmrUser().getBusinessId());
				this.setEdiInfo(ediInfo);
			}
			if (getSession().get("CARRIERS") == null) {
				carriers = this.carrierServiceManager.getCarriersForBusiness(UserUtil.getMmrUser().getBusinessId());
				getSession().put("CARRIERS", carriers);
			}
		} catch (Exception e) {
			e.printStackTrace();
			// addActionError(getText("content.error.unexpected"));
			addActionError(e.getMessage());
		}
		return SUCCESS;
	}
	
	public String uploadAndProcess() throws Exception {
		try {
			EdiInfo ediInfo = this.getEdiInfo();
			if (ediInfo != null && ediInfo.getCarrierId() > 0 && this.getUpload() != null && this.getUploadFileName() != null) {
				InputStream is = new DataInputStream(new FileInputStream(this.getUpload()));
				if (is != null) {
					EdiItem ediItem = this.ediManagerService.uploadEdiFile(ediInfo, 
													this.getUploadFileName(), is, true, 
													UserUtil.getMmrUser().getBusinessId());
					if (ediItem == null) {
						addActionError(getText("content.error.unexpected"));
					} else {
						addActionMessage(getText("edifile.uploaded.successfully.process.started"));
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			// addActionError(getText("content.error.unexpected"));
			addActionError(e.getMessage());
		}
		return uploadEdi();
	}	

	public String uploadAndProcessLater() throws Exception {
		try {
			EdiInfo ediInfo = this.getEdiInfo();
			if (ediInfo != null && ediInfo.getCarrierId() > 0 && this.getUpload() != null && this.getUploadFileName() != null) {
				InputStream is = new DataInputStream(new FileInputStream(this.getUpload()));
				if (is != null) {
					EdiItem ediItem = this.ediManagerService.uploadEdiFile(ediInfo, 
											this.getUploadFileName(), is, false, 
											UserUtil.getMmrUser().getBusinessId());
					if (ediItem == null) {
						addActionError(getText("content.error.unexpected"));
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			// addActionError(getText("content.error.unexpected"));
			addActionError(e.getMessage());
		}
		return uploadEdi();
	}	
	
	public String getResourceMessage(String s) {
		String msg = getMessage(s);
		return msg;
	}
	
	public String getEdiItemStatus(Integer s) {
		if (s != null) {
			if (s.intValue()==ShiplinxConstants.STATUS_UPLOADED) 
				return ShiplinxConstants.UPLOADED;
			if (s.intValue()==ShiplinxConstants.STATUS_INPROGRESS) 
				return ShiplinxConstants.INPROGRESS;
			if (s.intValue()==ShiplinxConstants.STATUS_PROCESSED) 
				return ShiplinxConstants.PROCESSED;
			if (s.intValue()==ShiplinxConstants.STATUS_RELEASED) 
				return ShiplinxConstants.RELEASED;
			if (s.intValue()==ShiplinxConstants.STATUS_FAILED) 
				return ShiplinxConstants.FAILED;			
		}	
		return "";
	}
	
	public String getCarrierName(Integer s) {
		return ShiplinxConstants.getCarrierName(s);
	}

	public String releaseEdiFile() throws Exception {
		try {
			String ediFileName = request.getParameter("ediFileName");
			if ( !StringUtil.isEmpty(ediFileName) ) {
				int bRet = this.ediManagerService.releaseEdiFile(ediFileName);
				if (bRet == EdiItem.FILE_RELEASED_SUCCESSFULLY)
					addActionMessage(getText("edifile.released.successfully"));
				else if (bRet == EdiItem.FILE_ALREADY_RELEASED)
					addActionMessage(getText("edifile.already.released"));
				else if (bRet == EdiItem.FILE_NOT_READY_FOR_RELEASE)
					addActionError(getText("edifile.not.ready.for.release"));				
			}
			EdiItem item = this.getEdiItem();

			if (item == null)
				item = init();
			
			if (this.ediManagerService != null) {
				ediItemList = this.ediManagerService.findEdiItems(item);
			}
		} catch (Exception e) {
			e.printStackTrace();
			// addActionError(getText("content.error.unexpected"));
			addActionError(e.getMessage());
		}
		return this.searchEdi();
	}	
	
	public String releaseEdiInvoice() throws Exception {
		try {
			String id = request.getParameter("ediItemId");
			if ( !StringUtil.isEmpty(id) ) {
				long ediItemId = Long.parseLong(id);
				String invoiceNumber = request.getParameter("invoiceNumber");
				if ( !StringUtil.isEmpty(invoiceNumber) ) {
					boolean bSuccess = this.ediManagerService.releaseEdiInvoice(ediItemId, invoiceNumber);
					if (bSuccess)
						addActionMessage(getText("invoice.released.successfully"));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			// addActionError(getText("content.error.unexpected"));
			addActionError(e.getMessage());
		}
		return this.searchEdi();
	}
	
	public String listEdiInvoice(){
		String searchParameter = request.getParameter("searchInvoice");
		log.debug("Search string is : " + searchParameter);
		
		EdiItem item = this.getEdiItem();
		item.setInvoiceNumber(searchParameter);

		List<EdiItem> items = this.ediManagerService.searchByInvoiceNumber(item);
		
		for(EdiItem i: items){
			ediInvoiceSearchResult.put(i.getInvoiceNumber(), i.getId());
		}
		
		return SUCCESS;
	}	
	public String listEdiFileName(){
		String searchParameter = request.getParameter("searchFileName");
		log.debug("Search string is : " + searchParameter);
		
		EdiItem item = this.getEdiItem();
		item.setInvoiceNumber(searchParameter);

		List<EdiItem> items = this.ediManagerService.searchByFileName(item);
		
		for(EdiItem i: items){
			ediFileNameSearchResult.put(i.getEdiFileName(), i.getId());
		}
		
		return SUCCESS;
	}	
}
