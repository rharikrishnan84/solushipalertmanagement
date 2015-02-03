package com.meritconinc.shiplinx.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.meritconinc.mmr.utilities.security.UserUtil;
import com.meritconinc.shiplinx.carrier.ups.dao.UPSCanadaTariffDAO;
import com.meritconinc.shiplinx.dao.AddressDAO;
import com.meritconinc.shiplinx.dao.CarrierServiceDAO;
import com.meritconinc.shiplinx.dao.CustomerDAO;
import com.meritconinc.shiplinx.dao.EdiDAO;
import com.meritconinc.shiplinx.model.Carrier;
import com.meritconinc.shiplinx.model.EdiInfo;
import com.meritconinc.shiplinx.model.EdiItem;
import com.meritconinc.shiplinx.service.EdiManager;
import com.meritconinc.shiplinx.service.LoggedEventService;
import com.meritconinc.shiplinx.service.MarkupManager;
import com.meritconinc.shiplinx.service.ShippingService;
import com.meritconinc.shiplinx.utils.ShiplinxConstants;
import com.opensymphony.xwork2.ActionContext;

public class EdiManagerImpl implements EdiManager {
	private static final Logger log = LogManager.getLogger(EdiManagerImpl.class); 
	private EdiDAO ediDAO;
	private CarrierServiceDAO carrierServiceDAO;
	private CustomerDAO customerDAO;
	private AddressDAO addressDAO;
	private MarkupManager markupManagerService;
	private ShippingService shippingService;
	private long userBusinessId;
	private UPSCanadaTariffDAO upsCanadaTariffDAO;
	private LoggedEventService loggedEventService;
	
	public ShippingService getShippingService() {
		return shippingService;
	}

	public void setShippingService(ShippingService shippingService) {
		this.shippingService = shippingService;
	}

	public MarkupManager getMarkupManagerService() {
		return markupManagerService;
	}

	public void setMarkupManagerService(MarkupManager markupManagerService) {
		this.markupManagerService = markupManagerService;
	}

	public AddressDAO getAddressDAO() {
		return addressDAO;
	}

	public void setAddressDAO(AddressDAO addressDAO) {
		this.addressDAO = addressDAO;
	}

	public CarrierServiceDAO getCarrierServiceDAO() {
		return carrierServiceDAO;
	}

	public void setCarrierServiceDAO(CarrierServiceDAO carrierServiceDAO) {
		this.carrierServiceDAO = carrierServiceDAO;
	}

	public EdiDAO getEdiDAO() {
		return ediDAO;
	}

	public void setEdiDAO(EdiDAO ediDAO) {
		this.ediDAO = ediDAO;
	}
	

	public CustomerDAO getCustomerDAO() {
		return customerDAO;
	}

	public void setCustomerDAO(CustomerDAO customerDAO) {
		this.customerDAO = customerDAO;
	}
	
	public UPSCanadaTariffDAO getUpsCanadaTariffDAO() {
		return upsCanadaTariffDAO;
	}

	public void setUpsCanadaTariffDAO(UPSCanadaTariffDAO upsCanadaTariffDAO) {
		this.upsCanadaTariffDAO = upsCanadaTariffDAO;
	}

	public LoggedEventService getLoggedEventService() {
		return loggedEventService;
	}

	public void setLoggedEventService(LoggedEventService loggedEventService) {
		this.loggedEventService = loggedEventService;
	}

	@Override
	public EdiInfo getEdiInfo(Long carrierId, long busId) {
		// TODO Auto-generated method stub
		return ediDAO.getEdiInfo(carrierId, busId);
	}

	@Override
	public List<EdiInfo> getEdiInfoList(long busId) {
		// TODO Auto-generated method stub
		return ediDAO.getEdiInfoList(busId);
	}

	@Override
	public List<EdiItem> uploadEdiFiles(EdiInfo ediInfo, long busId) {
		// TODO Auto-generated method stub
		try {
			if (ediInfo != null) {
				if (ediInfo.getCarrierId() > 0) {
					ediInfo = this.ediDAO.getEdiInfo(ediInfo.getCarrierId(), busId);
					if (ediInfo != null) {
						EdiParser.execute(ediInfo, ediDAO, shippingService, customerDAO, 
								addressDAO, markupManagerService, upsCanadaTariffDAO, 
								loggedEventService);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public EdiItem uploadEdiFile(EdiInfo ediInfo, String uploadFileName, InputStream uploadStream, 
					boolean startNow, long busId) {
		// TODO Auto-generated method stub
		try {
			userBusinessId = busId;
			if (ediInfo != null && ediInfo.getCarrierId() > 0 && uploadFileName != null&& uploadStream != null) {
				ediInfo = this.ediDAO.getEdiInfo(ediInfo.getCarrierId(), busId);
				if (ediInfo != null && ediInfo.getFileType() != null) {
					if (uploadFileName.toUpperCase().endsWith(ediInfo.getFileType())) {
						File outFile = new File(ediInfo.getEdiFolder() + File.separator + ShiplinxConstants.IN_FOLDER + File.separator + uploadFileName);
						if (isWIPFile(outFile)) {
							// this file is currently under processing, WIP FILE
							EdiItem item = new EdiItem();
							item.setEdiFileName(outFile.getName());
							item.setBusinessId(userBusinessId);
							List<EdiItem> items = this.searchByFileName(item);
							if (items != null && items.size() > 0)
								return items.get(0);
							return null;
						}
						OutputStream bos = null;
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
						bos.close();
						uploadStream.close();
		
						EdiItem item = addEdiItem(ediInfo, uploadFileName);
						if (startNow) {
							final ActionContext currentContext = ActionContext.getContext();
					        new Thread() {
					            public void run() {		
					            	ActionContext.setContext(currentContext);
					            	beginProcessing(userBusinessId);
					            }
					        }.start();					            	
						}
						
						return item;
						
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	private boolean isWIPFile(File file) {
		// TODO Auto-generated method stub
		File f = new File (file.getAbsolutePath() + ShiplinxConstants.WIP_FILE_EXT);
		return f.exists();
	}

	private synchronized void beginProcessing(long busId) {
		// TODO Auto-generated method stub
		try {
			List<Carrier> carriers = this.carrierServiceDAO.getCarriers();
			if (carriers != null) {
				for (Carrier c:carriers) {
					EdiInfo edi = this.ediDAO.getEdiInfo(c.getId(), busId);
					if (edi != null) {
						EdiParser.execute(edi, ediDAO, shippingService, customerDAO, addressDAO, 
								markupManagerService, upsCanadaTariffDAO, loggedEventService);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private EdiItem addEdiItem(EdiInfo ediInfo, String fileName) {
		// TODO Auto-generated method stub
		EdiItem item = new EdiItem();
		item.setBusinessId(ediInfo.getBusinessId());
		item.setCarrierId(ediInfo.getCarrierId());
		item.setEdiFileName(fileName);
		item.setMessage("text.message.status.upload.successful");
		item.setStatus(ShiplinxConstants.STATUS_UPLOADED);
		
		long key = this.ediDAO.addEdiItem(item);
		if (key > 0)
			return item;
		
		return null;
	}

	@Override
	public boolean releaseEdiInvoice(long ediItemId, String invoiceNumber) {
		// TODO Auto-generated method stub
		boolean bSuccess = true;
		if (invoiceNumber != null) {
			bSuccess = this.shippingService.releaseCharges(invoiceNumber);
			if (bSuccess)
				this.ediDAO.updateEdiItemStatus(ediItemId, ShiplinxConstants.STATUS_RELEASED);
		}
		return bSuccess;
	}

	@Override
	public List<EdiItem> getEdiItemList(EdiItem ediItem, long busId) {
		// TODO Auto-generated method stub
		return ediDAO.getEdiItemList(ediItem, null, busId);
	}

	@Override
	public List<EdiItem> searchByInvoiceNumber(EdiItem item) {
		// TODO Auto-generated method stub
		return ediDAO.getMatchingEdiItemList(item);
	}

	@Override
	public List<EdiItem> searchByFileName(EdiItem item) {
		// TODO Auto-generated method stub
		return ediDAO.getMatchingEdiItemList(item);
	}

	@Override
	public List<EdiItem> findEdiItems(EdiItem item) {
		// TODO Auto-generated method stub
		return ediDAO.findEdiItems(item);
	}

	@Override
	public int releaseEdiFile(String ediFileName) throws Exception{
		// TODO Auto-generated method stub
		if (ediFileName != null) {
			EdiItem ediItem = new EdiItem();
			ediItem.setBusinessId(UserUtil.getMmrUser().getBusinessId());
			ediItem.setEdiFileName(ediFileName);
			ediItem.setExactMatch("Y");
			List<EdiItem> items = searchByFileName(ediItem);
			if (items != null && items.size() > 0) {
				if (isReleased(items)) {
					return EdiItem.FILE_ALREADY_RELEASED;
				}
				if ( !isReadyForRelease(items) ) {
					return EdiItem.FILE_NOT_READY_FOR_RELEASE;
				}
				if (isReadyForRelease(items) ) {
				// Release all items in this EDI file
				for (EdiItem item:items) {
					if ( !this.releaseEdiInvoice(item.getId(), item.getInvoiceNumber()) ) {
						throw new Exception("Failed to release EDI File:" + ediFileName + " Invoice Number:" + item.getInvoiceNumber());
					}
				}
				}
				return EdiItem.FILE_RELEASED_SUCCESSFULLY;
			}
		}
		return 0;
	}

	private boolean isReadyForRelease(List<EdiItem> items) {
		// TODO Auto-generated method stub
		if (items != null && items.size() > 0) {
			for (EdiItem i:items)
				if (i.getStatus().intValue() != ShiplinxConstants.STATUS_PROCESSED)
					return false;
			return true;
		}
		
		return false;
	}

	private boolean isReleased(List<EdiItem> items) {
		// TODO Auto-generated method stub
		if (items != null && items.size() > 0) {
			for (EdiItem i:items)
				if (i.getStatus().intValue() != ShiplinxConstants.STATUS_RELEASED)
					return false;
			return true;
		}
		
		return false;
	}

	@Override
		public List<EdiItem> searchFileInEdiItem(String uploadFileName) {
			// TODO Auto-generated method stub
			return ediDAO.searchFileInEdiItem(uploadFileName);
		}
}
