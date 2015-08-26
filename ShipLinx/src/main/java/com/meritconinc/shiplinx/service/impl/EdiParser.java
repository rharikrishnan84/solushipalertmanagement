package com.meritconinc.shiplinx.service.impl;

import java.io.File;
import java.io.FileReader;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.meritconinc.mmr.utilities.StringUtil;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import au.com.bytecode.opencsv.CSVReader;

import com.meritconinc.mmr.constants.Constants;
import com.meritconinc.mmr.utilities.MessageUtil;
import com.meritconinc.mmr.utilities.WebUtil;
import com.meritconinc.shiplinx.model.Customer;
import com.meritconinc.shiplinx.carrier.purolator.PurolatorAPI;
import com.meritconinc.shiplinx.carrier.ups.dao.UPSCanadaTariffDAO;
import com.meritconinc.shiplinx.dao.AddressDAO;
import com.meritconinc.shiplinx.dao.CustomerDAO;
import com.meritconinc.shiplinx.dao.EdiDAO;
import com.meritconinc.shiplinx.model.Address;
import com.meritconinc.shiplinx.model.Charge;
import com.meritconinc.shiplinx.model.CustomerCarrier;
import com.meritconinc.shiplinx.model.EdiInfo;
import com.meritconinc.shiplinx.model.EdiItem;
import com.meritconinc.shiplinx.model.LoggedEvent;
import com.meritconinc.shiplinx.model.Package;
import com.meritconinc.shiplinx.model.ShippingOrder;
import com.meritconinc.shiplinx.service.LoggedEventService;
import com.meritconinc.shiplinx.service.MarkupManager;
import com.meritconinc.shiplinx.service.ShippingService;
import com.meritconinc.shiplinx.utils.FormattingUtil;
import com.meritconinc.shiplinx.utils.ShiplinxConstants;

public abstract class EdiParser {
	private static final Logger log = LogManager.getLogger(EdiParser.class);

	protected abstract List<String> performOperation() throws Exception;

	protected abstract EdiItem populateEdiItem(String fileName)
			throws Exception;

	protected abstract ShippingOrder populateShipment(EdiItem item)
			throws Exception;
	protected abstract boolean applyCustomExceptionRules(ShippingOrder ediShipment, 
			ShippingOrder dbShipment, List<LoggedEvent> events);
	
	protected EdiDAO ediDAO;
	protected EdiInfo ediInfo;
	protected CustomerDAO customerDAO;
	protected AddressDAO addressDAO;
	protected MarkupManager markupManagerService;
	protected ShippingService shippingService;
	protected LoggedEventService loggedEventService;

	protected String[] fields = null;
	protected String[] rowData = null;
	protected ShippingOrder currentDBShipment = null;
	private List<EdiItem> ediItems = null;
	private static Boolean isApplyEdiExceptions = null;
	private static Boolean isApplyEdiConstrains = true;
	private List<String>pins=new ArrayList<String>();
	private boolean status;
	public EdiParser(EdiInfo ediInfo, EdiDAO ediDAO, ShippingService shippingService,
			CustomerDAO customerDAO, AddressDAO addressDAO, MarkupManager markupService, 
			LoggedEventService loggedService) {
		// TODO Auto-generated constructor stub
		this.ediDAO = ediDAO;
		this.ediInfo = ediInfo;
		this.shippingService = shippingService;
		this.customerDAO = customerDAO;
		this.addressDAO = addressDAO;
		this.markupManagerService = markupService;
		this.loggedEventService = loggedService;
	}

	public EdiDAO getEdiDAO() {
		return ediDAO;
	}

	public void setEdiDAO(EdiDAO ediDAO) {
		this.ediDAO = ediDAO;
	}

	public EdiInfo getEdiInfo() {
		return ediInfo;
	}

	public void setEdiInfo(EdiInfo ediInfo) {
		this.ediInfo = ediInfo;
	}

	public List<String> parse() throws Exception {
		// TODO Auto-generated method stub
		return performOperation();
	}

	protected List<EdiItem> getEdiItems() {
		return ediItems;
	}

	protected ArrayList<File> getFiles(String path) {
		// TODO Auto-generated method stub
		if (path != null) {
			File f = new File(path);
			if (f.isDirectory()) {
				ArrayList<File> files = new ArrayList<File>();
				for (int i=0; i<f.listFiles().length; i++) {
					File file = f.listFiles()[i];
					if (file.isFile() && !file.getName().endsWith(ShiplinxConstants.WIP_FILE_EXT))
						files.add(file);
				}
				return files;
			} else {
				f.mkdir();
			}
		}
		return null;
	}

	public static void execute(EdiInfo edi, EdiDAO dao,
			ShippingService shippingService, CustomerDAO customerDAO,
			AddressDAO addressDAO, MarkupManager markupManagerService,
			UPSCanadaTariffDAO upsCanadaTariffDAO, 
			LoggedEventService loggedService) throws Exception {
		EdiParser parser = null;
		if (edi != null) {
			if (edi.getCarrierId().intValue() == ShiplinxConstants.CARRIER_UPS || edi.getCarrierId().intValue() == ShiplinxConstants.CARRIER_UPS_USA) {
				 parser = new EdiUPSParser(edi, dao, shippingService, customerDAO, addressDAO, 
						 markupManagerService, upsCanadaTariffDAO, loggedService);
			} else if (edi.getCarrierId().intValue() == ShiplinxConstants.CARRIER_FEDEX) {
				parser = new EdiFedexParser(edi, dao, shippingService, customerDAO, addressDAO,
						markupManagerService, loggedService);
			} else if (edi.getCarrierId().intValue() == ShiplinxConstants.CARRIER_DHL) {
				parser = new EdiDHLParser(edi, dao, shippingService, customerDAO, addressDAO,
						markupManagerService, loggedService);
			} else if (edi.getCarrierId().intValue() == ShiplinxConstants.CARRIER_LOOMIS) {
				parser = new EdiLoomisParser(edi, dao, shippingService, customerDAO, addressDAO,
						markupManagerService, loggedService);
			} else if (edi.getCarrierId().intValue() == ShiplinxConstants.CARRIER_PUROLATOR) {
				parser = new EdiPurolatorParser(edi, dao, shippingService, customerDAO, addressDAO,
						markupManagerService, loggedService);
			}
			if (parser != null) {
				List<String> parsedFiles = parser.parse();
				if (parsedFiles != null) {
					log.debug(parsedFiles);
				}
			}
		}
	}

	protected List<String> processEdiFiles() {
		// TODO Auto-generated method stub
		try {
			String inFolder = ediInfo.getEdiFolder() + File.separator + ShiplinxConstants.IN_FOLDER;
			ArrayList<File> filesToBeParsed = getFiles(inFolder);
			if (filesToBeParsed != null && filesToBeParsed.size() > 0) {
				ArrayList<String> fileList = new ArrayList<String>();
				String fileName = "";
				for (File f : filesToBeParsed) {
					int ediInvoiceStatus = ShiplinxConstants.STATUS_PROCESSED;
					if (f.isFile()) {
						try {						
							ediItems = getEdiItems(f.getName());
	
							fileName = f.getName().trim().toUpperCase();
							if (fileName.endsWith(ediInfo.getFileType())) {
								// ---- 16 Dec. 11 - Rename file to .WIP
								String currentFileName = f.getName();
								f = renameFileToWIP(f);
								if (f != null) {
									CSVReader csvReader = new CSVReader(new FileReader(f));
									if (csvReader != null) {
										int i = 0;
										while ((rowData = csvReader.readNext()) != null) {
											i++;
											log.debug("Processing Line: " + i);
											// if
											// (rowData[0].equals(ediInfo.getVersion())
											// && rowData.length == fields.length) {
											if(rowData.length==1){
												continue; //this is to accommodate the Loomis file which is being sent with a -> character at the last line, need to know how to identify that character.
											}
											if (rowData.length == fields.length) {
												// Properties pRecord =
												// getRowData(nextLine);
												try {
													processEdiRecord(currentFileName);
												} catch (Exception e) {
													log.error(fileName + ": " + rowData, e);
													csvReader.close();
													throw e;
												}
											}
											else{
												log.debug("Skipped processing Line: " + i);	
												csvReader.close();
												throw new Exception();
											}
										}
									}
									fileList.add(currentFileName);
									csvReader.close();
								}
							}
							updateEdiItems(ediInvoiceStatus);
							
							moveFileToOutFolder(f, ShiplinxConstants.OUT_FOLDER);
							if(ediInfo.getCarrierId().intValue()==ShiplinxConstants.CARRIER_PUROLATOR && pins.size()>0){
															//track the shipment using master tacking number
																PurolatorAPI purolatorAPI= new PurolatorAPI();
																purolatorAPI.trackShipmentByPins(pins);
															}
						} catch (Exception e) {
							ediInvoiceStatus = ShiplinxConstants.STATUS_FAILED;
							updateEdiItems(ediInvoiceStatus);
							log.error(fileName, e);
							moveFileToOutFolder(f, ShiplinxConstants.ERROR_FOLDER);
						}						
					}
				}
				return fileList;
			}
		} catch (Exception e) {
//			e.printStackTrace();
			log.error(e);
		}
		return null;
	}

	private File renameFileToWIP(File f) {
		// TODO Auto-generated method stub
		File f1 = new File(f.getAbsolutePath() + ShiplinxConstants.WIP_FILE_EXT);
		if (f.renameTo(f1))
			return new File(f1.getAbsolutePath());
		return null;
	}

	private void moveFileToOutFolder(File file, String destFolder) {
		// TODO Auto-generated method stub
		if (file != null) {
			String outFolder = ediInfo.getEdiFolder() + File.separator + destFolder;
			String outFileName = file.getName();
			if (outFileName.endsWith(ShiplinxConstants.WIP_FILE_EXT))
				outFileName = outFileName.replace(ShiplinxConstants.WIP_FILE_EXT, "");
			
			File outFile = new File(outFolder, outFileName);
			if (outFile.exists()) {
				outFile.delete();
				outFile = null;
				outFile = new File(outFolder, outFileName);
			}
			boolean success = file.renameTo(outFile);
			if (!success) {
				String msg = "Failed to moved to out Folder:" + outFolder + " File:" + file.getName();
				log.error( msg );
			}
		}

	}

	private List<EdiItem> getEdiItems(String ediFileName) {
		// TODO Auto-generated method stub
		EdiItem item = new EdiItem();
		item.setBusinessId(ediInfo.getBusinessId());
		item.setCarrierId(ediInfo.getCarrierId());
		if (ediItems != null) {
			ediItems.clear();
		}
		ediItems = this.ediDAO.getEdiItemList(item, ediFileName, this.ediInfo.getBusinessId());
		if (ediItems == null) {
			// It means this file was copied directly in "in" folder without
			// upload from the UI. This shouldn't happen,
			item.setMessage("text.message.status.upload.successful");
			item.setStatus(ShiplinxConstants.STATUS_UPLOADED);

			long key = this.ediDAO.addEdiItem(item);
			if (key > 0) {
				item.setId(key);
				ediItems = new ArrayList<EdiItem>();
				ediItems.add(item);
			}
		}

		return ediItems;
	}

	private void updateEdiItems(int status) {
		// TODO Auto-generated method stub

		for (EdiItem item : ediItems) {
//			if (item.getStatus() == ShiplinxConstants.STATUS_INPROGRESS) {
				// Update Record
				item.setMessage(ShiplinxConstants.EDI_STATUS_MESSAGES[status-1]);
				item.setStatus(status);
				if (item.getStartTime() != null)
					item.setElapsedTime((new Date()).getTime()- item.getStartTime().getTime());
				this.ediDAO.updateEdiItem(item);
//			}
		}
	}

	protected EdiItem findEdiItem(String ediInvoiceNumber) {
		// TODO Auto-generated method stub
		if (ediItems != null) {
			if (ediItems.size() == 1) {
				EdiItem item = ediItems.get(0);
				if (item.getInvoiceNumber() == null	&& item.getStatus() == ShiplinxConstants.STATUS_UPLOADED)
					return item;
			}
			for (EdiItem item : ediItems) {
				if (item.getInvoiceNumber() != null && item.getStatus() != null)
					if (item.getInvoiceNumber().equals(ediInvoiceNumber))
						return item;
			}
		}
		return null;
	}

	protected String[] populateFields() throws Exception {
		// TODO Auto-generated method stub
		// GFF Header Version 2.1.csv
		try {
			ArrayList<File> headerFiles = getFiles(ediInfo.getEdiFolder());
			if (headerFiles != null) {
				String fileName = "";
				for (File f : headerFiles) {
					if (f.isFile()) {
						fileName = f.getName().trim().toUpperCase();
						if (fileName.endsWith(ediInfo.getFileType())
								&& fileName.contains(ShiplinxConstants.HEADER)) {
							CSVReader csvReader = new CSVReader(new FileReader(
									f));
							if (csvReader != null) {
								List<String[]> allRows = csvReader.readAll();
								if (allRows != null && allRows.size() >= 2) {
									fields = new String[allRows.get(0).length];
									for (int i = 0; i < allRows.get(0).length; i++) {
										String name = allRows.get(0)[i] + "_"
												+ allRows.get(1)[i];
										fields[i] = name;
									}
								}
							}
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
			throw e;
		}
		return fields;
	}

	protected ShippingOrder processEdiRecord(String fileName) throws Exception {
		// TODO Auto-generated method stub
		EdiItem item = populateEdiItem(fileName);
		if (item != null) {
			ShippingOrder ediShipment = populateShipment(item);
			ShippingOrder dbShipment = findShipment(ediShipment);			
			if (dbShipment == null) {
				addShipment(ediShipment, item);
				if(ediShipment.getMasterTrackingNum() !=null && ediShipment.getCarrierId()!=null && ediShipment.getCarrierId().intValue()==ShiplinxConstants.CARRIER_PUROLATOR)
					pins.add(ediShipment.getMasterTrackingNum());
			} else {
				updateShipment(ediShipment, dbShipment);
				shippingService.updateShippingOrderCurrency(ediShipment);
				if(ediShipment.getMasterTrackingNum() !=null && ediShipment.getCarrierId()!=null && ediShipment.getCarrierId().intValue()==ShiplinxConstants.CARRIER_PUROLATOR)
					pins.add(ediShipment.getMasterTrackingNum());
			}
			return ediShipment;
		}

		return null;
	}

	private List<LoggedEvent> checkExceptionRules(ShippingOrder ediShipment,
													ShippingOrder dbShipment) {
		List<LoggedEvent> events = new ArrayList<LoggedEvent>();
		String logMsg = "";
//		1. If db shipment is in Cancelled status when EDI file is processed and shipment is 
//			reconciled, then set the status of the shipment to exception. 
//			Logged Event message will indicate that the shipment was cancelled but billed 
//			(lets create some generic static messages for each rule).
		
		if(status){
			if (dbShipment.getStatusId()!=null && dbShipment.getStatusId().longValue() == ShiplinxConstants.STATUS_CANCELLED) {
			logMsg = "This shipment was cancelled but billed.";
			events.add(getLoggedEvent(dbShipment.getId().longValue(), logMsg));
		}
//		2. If total actual charge is more than total quoted charge, then set the status to 
//			exception with message "Actual charges are more than quoted charges".
		if (dbShipment.getTotalChargeActual() > dbShipment.getTotalChargeQuoted()) {
			logMsg = "Actual charges are more than quoted charges.";
			events.add(getLoggedEvent(dbShipment.getId().longValue(), logMsg));
		}
		// apply generic rules
		applyCustomExceptionRules(ediShipment, dbShipment, events);
		}
		//+++++++++++++++++++AddressCorrectionStutusUpdate++++++++++++++++++++++++++++++++++++++++++++++++++		
				
		//      1.If the charge comes with address correction then such a order will be updated with following code "logMsg" in logged_event table  			
				if(ediShipment.getCharges()!=null && ediShipment.getCharges().size()>0){
					StringBuilder msg;
					for(int i=0;ediShipment.getCharges().size()>i;i++){
						 msg=new StringBuilder();
						if(ediShipment.getCharges().get(0).getName().equals("Address Correction")){
							if(dbShipment!=null){
							if(!dbShipment.getFromAddress().getAddress1().equals(ediShipment.getFromAddress().getAddress1())){
								msg.append("Address correction for address takes place in this order.The corrected 'From-address' is '");
								msg.append(ediShipment.getFromAddress().getAddress1());
								msg.append("'.");
								//logMsg="Address correction for address takes place in this order.The corrected 'From-address' is '"+ediShipment.getFromAddress().getAddress1()+"'.";
								}
							if(!dbShipment.getToAddress().getAddress1().equals(ediShipment.getToAddress().getAddress1())){
								msg.append("& 'To-Address' is '");
								msg.append(ediShipment.getToAddress().getAddress1());
								msg.append("'.");
								//logMsg=logMsg.concat("& 'To-Address' is '"+ediShipment.getToAddress().getAddress1()+"'.");
								}
							}
							if(StringUtil.isEmpty(msg.toString())){
								msg.append("Address correction takes place in this record.From & To address in file is");
								msg.append(ediShipment.getFromAddress().getAddress1());
								msg.append("&");
								msg.append(ediShipment.getToAddress().getAddress1());
							}
							logMsg=msg.toString();
								if(ediShipment.getCharges().get(0).getOrderId()!=null && !ediShipment.getCharges().get(0).getOrderId().equals("")){
									events.add(getLoggedEvent(ediShipment.getCharges().get(0).getOrderId().longValue(),logMsg));
								}
								else{
									events.add(getLoggedEvent(ediShipment.getDbShipment().getCharges().get(0).getOrderId().longValue(),logMsg));
									}
							}
						} 
					}
				
				// apply generic rules
		if (events.size() > 0)
			return events;
		return null;
	}

	protected LoggedEvent getLoggedEvent(long orderId, String logMsg) {
		LoggedEvent loggedEvent = new LoggedEvent();
		// Set the loggedEvent Details
		loggedEvent.setEntityType(ShiplinxConstants.ENTITY_TYPE_ORDER_VALUE); 
		loggedEvent.setEntityId(orderId);//Order ID
		loggedEvent.setEventDateTime(new Date()); //Current Date
		// MmrUser is not set, as it is running in a seperate thread
		// If it's needed, we probably need to persist user info in edi_item table
		// Need to confirm with Rizwan
//		loggedEvent.setEventUsername(UserUtil.getMmrUser().getUsername()); //Current User
		loggedEvent.setEventUsername(ShiplinxConstants.SYSTEM); // User SYSTEM 
		//String systemLog = MessageUtil.getMessage("label.system.log.edi.exceptions");
		String systemLog;
		try{
			systemLog = MessageUtil.getMessage("label.system.log.edi.exceptions");
			if(isApplyEdiConstrains){
				systemLog = MessageUtil.getMessage("label.system.log.edi.message");
			}
		}catch(Exception e){
				e.printStackTrace();
				log.debug("Error in getting the locale from the session :" + e.getMessage());
				systemLog="EDI Upload Processing - Address correction occured";
		}
		loggedEvent.setSystemLog(systemLog); //System generated Message Log
//		if(UserUtil.getMmrUser().getUserRole().equals("busadmin"))
			loggedEvent.setPrivateMessage(true);
//		else
//			loggedEvent.setPrivateMessage(false);
		loggedEvent.setMessage(logMsg);

		return loggedEvent;
	}

	protected ShippingOrder findShipment(ShippingOrder ediShipment) {
		// TODO Auto-generated method stub
		// if (currentDBShipment != null &&
		// currentDBShipment.getMasterTrackingNum().equals(ediShipment.getMasterTrackingNum()))
		// return currentDBShipment;
		// Debugging code
		// if (ediShipment.getMasterTrackingNum().equals("1Z5F132X2091794537"))
		// {
		// log.debug("Debugging Master Tracking Number:" +
		// ediShipment.getMasterTrackingNum());
		// }

		List<ShippingOrder> dbShipments = this.shippingService
				.getOrdersByTrackingNumber(ediShipment.getCarrierId(),
						ediShipment.getMasterTrackingNum());
		if (dbShipments == null || dbShipments.size() == 0) {
			// Try to find shipments using package tracking number
			for(Package pkg: ediShipment.getPackages()){			
				dbShipments = this.shippingService.getOrdersByTrackingNumber(
					ediShipment.getCarrierId(), pkg.getTrackingNumber());
				if(dbShipments!=null && dbShipments.size()>0) //found
					break;
			}
		}
		if (dbShipments != null && dbShipments.size() > 0) {
			currentDBShipment = dbShipments.get(0);
			return currentDBShipment;
		}

		return null;
	}
	
	protected ShippingOrder findShipmentByMasterTrackingNumber(Long carrierId, String trackingNumber) {
		List<ShippingOrder> dbShipments = this.shippingService
				.getOrdersByMasterTrackingNumber(carrierId, trackingNumber);
		if (dbShipments != null && dbShipments.size() > 0) {
			return dbShipments.get(0);
		}

		return null;
	}	

	private void addShipment(ShippingOrder ediShipment, EdiItem item)
			throws Exception {
		// TODO Auto-generated method stub
//		ediShipment.setCustomerId(getCustomerId(item.getAccountNumber()));		
		shippingService.applyAdditionalHandling(ediShipment, null, ShiplinxConstants.CHARGE_TYPE_ACTUAL);
		shippingService.save(ediShipment);
		
//		if (ediShipment.getFromAddress() != null)
//			ediShipment.setShipFromId(this.addressDAO.add(ediShipment
//					.getFromAddress()));
//		if (ediShipment.getToAddress() != null)
//			ediShipment.setShipToId(this.addressDAO.add(ediShipment
//					.getToAddress()));
//
//		//set the status of the shipment to delivered
//		ediShipment.setStatusId((long)ShiplinxConstants.STATUS_DELIVERED);
//		
//		this.shippingDAO.save(ediShipment);
//
//		if (ediShipment.getCharges() != null) {
//			for (Charge c : ediShipment.getCharges()) {
//				c.setOrderId(ediShipment.getId());
//				this.shippingDAO.saveCharges(c);
//			}
//		}
//		if (ediShipment.getPackages() != null) {
//			this.shippingDAO.addPackage(ediShipment.getPackages(), ediShipment
//					.getId());
//		}
	}
	
	protected void populateCustomer(ShippingOrder shipment, String accountNumber) {
		// TODO Auto-generated method stub
		
		if(shipment.getDbShipment()!=null && shipment.getDbShipment().getCustomerId()!=null && shipment.getDbShipment().getCustomerId()>0){
			shipment.setCustomerId(shipment.getDbShipment().getCustomerId());
			shipment.setBillingStatus(ShiplinxConstants.BILLING_STATUS_READY_TO_INVOICE);
			return;
		}else{
			// TODO check if address already exists
			long customerId = this.addressDAO.getCustomerIdByAddressMatch(shipment);  
			Customer customerInfo=this.customerDAO.getCustomerInfoByCustomerId(customerId, shipment.getBusinessId());
			if(customerId > 0 && customerInfo!=null){
				shipment.setCustomerId(customerId);
				shipment.setBillingStatus(ShiplinxConstants.BILLING_STATUS_READY_TO_INVOICE);
				return;
			}/*else {
				long fromCustomerId = this.addressDAO.getCustomerIdForFromAddress(shipment);
				customerInfo=this.customerDAO.getCustomerInfoByCustomerId(fromCustomerId, shipment.getBusinessId());
				if(fromCustomerId > 0){
					long toCustomerId = this.addressDAO.getCustomerIdForToAddress(shipment);
					if(toCustomerId > 0 && fromCustomerId == toCustomerId && customerInfo!=null){
						shipment.setCustomerId(fromCustomerId);
						shipment.setBillingStatus(ShiplinxConstants.BILLING_STATUS_READY_TO_INVOICE);
						return;
					}
				}
			}*/
		}		
		Long cusId = getCustomerIdByAccountNumber(accountNumber);
		if (cusId != null && cusId>0) {
			shipment.setCustomerId(cusId);
			shipment.setBillingStatus(ShiplinxConstants.BILLING_STATUS_READY_TO_INVOICE);
			return;
		} else {
			if (shipment.getFromAddress() != null && 
					!isNullOrEmpty(shipment.getFromAddress().getAbbreviationName())) {
				cusId = getCustomerIdByCompanyName(shipment.getFromAddress().getAbbreviationName());
				if (cusId != null) {
					shipment.setCustomerId(cusId);
					shipment.setBillingStatus(ShiplinxConstants.BILLING_STATUS_AWAITING_CONFIRMATION);
					return;
				}
			}
			if (shipment.getToAddress() != null && 
					!isNullOrEmpty(shipment.getToAddress().getAbbreviationName())) {
				cusId = getCustomerIdByCompanyName(shipment.getFromAddress().getAbbreviationName());
				if (cusId != null) {
					shipment.setCustomerId(cusId);
					shipment.setBillingStatus(ShiplinxConstants.BILLING_STATUS_AWAITING_CONFIRMATION);
					return;
				}
			}			
		}
		shipment.setCustomerId(null);
		shipment.setBillingStatus(ShiplinxConstants.BILLING_STATUS_ORPHAN);
	}
	
	private Long getCustomerIdByCompanyName(String name) {
		// TODO Auto-generated method stub
		return this.customerDAO.getCustomerIdByName(name, this.ediInfo.getBusinessId() );
	}

	private Long getCustomerIdByAccountNumber(String accountNumber) {
		// TODO Auto-generated method stub
		List<CustomerCarrier> ccList = this.customerDAO
				.getCustomerCarrierByAccountNumber(accountNumber);
		if (ccList != null && ccList.size() > 0) {
			CustomerCarrier carrier = ccList.get(0);
			if(carrier.isLive()) //if the account is a house account, then we do not link the shipment to the account.
				return null;
			return carrier.getCustomerId();
		}
		return null;
	}

	protected void updateShipment(ShippingOrder ediShipment,
			ShippingOrder dbShipment) throws Exception {
		// TODO Auto-generated method stub
		// Update Package
		Package ediPackage = null;
		if (ediShipment.getPackages() != null && ediShipment.getPackages().size() > 0) {
			ediPackage = ediShipment.getPackages().get(0);
			if (ediPackage != null) {
				Package dbPackage = findPackage(dbShipment, ediPackage
						.getTrackingNumber());
				if (dbPackage == null) {
					addPackage(dbShipment, ediShipment);
				} else {
					updatePackage(ediPackage, dbPackage);
				}
			}
		}
		
		//if service id is set in existing shipment, and not in edi record, then set it
		if(dbShipment.getServiceId() != null && dbShipment.getServiceId()>0 && 
				ediShipment.getServiceId() != null && ediShipment.getServiceId()<=0)
			ediShipment.setServiceId(dbShipment.getServiceId());

		// Update Charge
		if(ediShipment.getCharges().size()==0)
			return;
		Charge ediCharge = ediShipment.getCharges().get(0); //this makes the assumption that only 1 charge per record, not the case for DHL and Loomis, modified DHL implementation
		if (ediCharge != null) {
			
			//If the charge is generic HST, try to convert to specific HST (ON,BC,etc.). this is happening in UPS EDI file when the second/adjustment set of HST charges are coming in and address columns are blank
			if(ediCharge.getChargeCodeLevel2().trim().equalsIgnoreCase(ShiplinxConstants.TAX_HST) && dbShipment!=null){
				String taxName = ShiplinxConstants.TAX_HST + " " + dbShipment.getToAddress().getProvinceCode();
				ediCharge.setChargeCodeLevel2(taxName);				
			}
			
			Charge dbCharge = findCharge(dbShipment, ediCharge);
			if (dbCharge == null) {
				addCharge(dbShipment, ediCharge);
			} else {
				updateCharge(ediCharge, dbCharge);
			}
		}
		
		shippingService.applyAdditionalHandling(dbShipment, null, ShiplinxConstants.CHARGE_TYPE_ACTUAL);		
		applyExceptionsRules(ediShipment, dbShipment);
		this.shippingService.updateShippingOrder(ediShipment);

	}

	protected boolean applyExceptionsRules(ShippingOrder ediShipment, ShippingOrder dbShipment) {
		status=isApplyEdiExceptions();
		if (status || isApplyEdiConstrains) {
			List<LoggedEvent> loggedEvents = checkExceptionRules(ediShipment, dbShipment);
			if (loggedEvents != null) {
				if(status){
					dbShipment.setStatusId(Long.valueOf(ShiplinxConstants.STATUS_EXCEPTION));
				}
				for (LoggedEvent event:loggedEvents)
					loggedEventService.addLoggedEventInfo(event);
				return true;
			}
		}
		return false;
	}

	protected void updatePackage(Package ediPackage, Package dbPackage) {
		// TODO Auto-generated method stub
		dbPackage.setReference1(ediPackage.getReference1());
		dbPackage.setReference2(ediPackage.getReference2());
		dbPackage.setReference3(ediPackage.getReference3());
		dbPackage.setWeight(ediPackage.getWeight());
		dbPackage.setBilledWeight(ediPackage.getBilledWeight());
		dbPackage.setType(ediPackage.getType());
		dbPackage.setDimmedString(ediPackage.getDimmedString());
//		dbPackage.setPackageId(ediPackage.getPackageId());

		shippingService.updatePackage(dbPackage);

	}

	protected void updateCharge(Charge ediCharge, Charge dbCharge)
			throws Exception {
		// TODO Auto-generated method stub
		// Accumulate cost, discountAmount, tariff, charge
		if (ediCharge.getCost() != null && dbCharge.getCost() != null)
			dbCharge.setCost(FormattingUtil.add(dbCharge.getCost(), ediCharge.getCost()).doubleValue());
		if (ediCharge.getDiscountAmount() != null && dbCharge.getDiscountAmount() != null)
			dbCharge.setDiscountAmount(FormattingUtil.add(dbCharge.getDiscountAmount(), ediCharge.getDiscountAmount()).doubleValue());
		if (ediCharge.getCharge() != null && dbCharge.getCharge() != null)
			dbCharge.setCharge(FormattingUtil.add(dbCharge.getCharge(), ediCharge.getCharge()).doubleValue());
		if (ediCharge.getTariffRate() != null && dbCharge.getTariffRate() != null)
			dbCharge.setTariffRate(FormattingUtil.add(dbCharge.getTariffRate(), ediCharge.getTariffRate()).doubleValue());
		
		if(ediCharge!=null){
						if(ediCharge.getIsTax()||(ediCharge.getChargeCode()!=null&&ediCharge.getChargeCode().equalsIgnoreCase("TAX")))
						dbCharge.setisCommissonable(false);
						else
							dbCharge.setisCommissonable(true);
					}

		shippingService.updateCharge(dbCharge);
	}

	protected Charge findCharge(ShippingOrder dbShipment, Charge ediCharge) {
		// TODO Auto-generated method stub
		for (Charge c : dbShipment.getActualCharges()) {
			if (c != null && c.getEdiInvoiceNumber() != null
					&& c.getStatus() != null && c.getChargeCode() != null
					&& c.getChargeCodeLevel2() != null) {
				if (c.getChargeCode().equals(ediCharge.getChargeCode())
						&& c.getChargeCodeLevel2().equals(
								ediCharge.getChargeCodeLevel2())
						&& c.getEdiInvoiceNumber().equals(
								ediCharge.getEdiInvoiceNumber())
						&& c.getStatus().equals(ediCharge.getStatus()))
					return c;
			}
		}
		return null;
	}

	protected void addCharge(ShippingOrder dbShipment, Charge ediCharge)
			throws Exception {
		// TODO Auto-generated method stub
		ediCharge.setOrderId(dbShipment.getId());
		
		if(ediCharge!=null){
						if(ediCharge.getIsTax()||(ediCharge.getChargeCode()!=null&&ediCharge.getChargeCode().equalsIgnoreCase("TAX")))
							ediCharge.setisCommissonable(false);
						else
							ediCharge.setisCommissonable(true);
					}
					
		this.shippingService.saveCharge(ediCharge);
	}
	protected Double applyMarkup(ShippingOrder shipment, Charge charge, EdiItem item) {
		// TODO Auto-generated method stub
		if(shipment.getDbShipment()!=null)
			return this.markupManagerService.applyMarkup(shipment.getDbShipment(), charge, true);
		return this.markupManagerService.applyMarkup(shipment, charge, true);
	}
	
//	protected Double applyMarkup(ShippingOrder shipment, Charge charge, EdiItem item) {
//		// TODO Auto-generated method stub
//		double amount = new Double(0.0);
//		if (shipment != null && charge != null) {
//			if (shipment.getMarkPercent() == null) {
//				Markup searchMarkup = getMarkupObj(shipment, item);
//				Markup markup = this.markupManagerService.getUniqueMarkup(searchMarkup);
//				if (markup != null) {
//					shipment.setMarkPercent(markup.getMarkupPercentage());
//					shipment.setMarkType(markup.getType());
//				}
//			}
//			
//			if (shipment.getMarkPercent() != null) {
//				if (charge.getCost() != null) {
//					if (shipment.getMarkType() != null) {
//						if (shipment.getMarkType().intValue() == ShiplinxConstants.TYPE_MARKDOWN) {
//							float f = (charge.getCost().floatValue() + charge.getDiscountAmount().floatValue()) * 
//									shipment.getMarkPercent().intValue() / 100;
//							amount = charge.getCost() - f;
//						} else {
//							float f = charge.getCost().floatValue() * shipment.getMarkPercent().intValue() / 100;
//							amount = charge.getCost() + f;
//						}
//					}
//				}
//			}
//		}
//		String s = FormattingUtil.formatDecimalTo2Places(amount, 
//											FormattingUtil.DECIMAL_2_PLACES_PATTERN);
//
//		return Double.valueOf(s);
//	}
	
//	private Markup getMarkupObj(ShippingOrder shipment, EdiItem item) {
//		// TODO Auto-generated method stub
//		Markup m = new Markup();
//		m.setBusinessId(item.getBusinessId());
//		m.setCustomerId(shipment.getCustomerId());
//		if (shipment.getFromAddress() != null)
//			m.setFromCountryCode(shipment.getFromAddress().getCountryCode());
//		if (shipment.getToAddress() != null)
//			m.setToCountryCode(shipment.getToAddress().getCountryCode());
//		
//		Double weight = shipment.getTotalWeight();
//		if (weight != null && weight.doubleValue() > 0) {
//			m.setFromWeight(weight);
//			m.setToWeight(weight);
//		}
//		m.setServiceId(shipment.getServiceId());
//		
//		return m;
//	}

	protected void addPackage(ShippingOrder dbShipment, ShippingOrder ediShipment)
			throws Exception {
		// TODO Auto-generated method stub
		shippingService.addPackage(ediShipment.getPackages(), dbShipment.getId());
	}

	protected Package findPackage(ShippingOrder dbShipment, String trackingNumber) {
		// TODO Auto-generated method stub
		for (Package pkg : dbShipment.getPackages()) {
			if (pkg != null && pkg.getTrackingNumber() != null) {
				if (pkg.getTrackingNumber().equals(trackingNumber))
					return pkg;
			}
		}
		return null;
	}

	protected String getEdiField(String key) {
		// TODO Auto-generated method stub
		// 1. Try to find out using index within field key
		if (key != null && this.rowData != null) {
			int n = key.indexOf("_");
			if (n > 0) {
				int index = Integer.parseInt(key.substring(0, n)) - 1;
				if (this.rowData.length >= index)
					return this.rowData[index].trim();

			}
		}
		// 2. Unable to find using key index, try to search in fields
		if (this.fields != null && this.rowData != null) {
			for (int n = 0; n < this.fields.length; n++)
				if (this.fields[n].equals(key))
					return this.rowData[n].trim();
		}
		return null;
	}

	protected Timestamp getDateTime(String date, String time, String format) throws Exception{
		// TODO Auto-generated method stub
//		try {
			if (date != null && !date.isEmpty() && !date.equals("0")) {
				String s = date;
				if (time != null && !time.isEmpty() && !time.equals("0")) {
					s += time;
				}
				SimpleDateFormat dateFormat = new SimpleDateFormat(format);
				Date d = dateFormat.parse(s);
				return new Timestamp(d.getTime());
			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		return null;
	}

	protected boolean isNullOrEmpty(String s) {
		// TODO Auto-generated method stub
		if (s == null || s.trim().isEmpty() || s.trim().equals("0"))
			return true;
		return false;
	}
	
//	protected String getZoneCountry(ShippingOrder shipment) {
//		// TODO Auto-generated method stub
//		if (shipment.getFromAddress() != null && shipment.getFromAddress().getCountryCode() != null) {
//			if (shipment.getFromAddress().getCountryCode().equals(ShiplinxConstants.CANADA))
//				return shipment.getToAddress().getCountryCode();
//			else
//				return shipment.getFromAddress().getCountryCode();
//		}
//		
//		return null;
//	}	
	
	protected String getShippingType(Address address) {
		// TODO Auto-generated method stub
		if (address != null && address.getCountryCode() != null && 
				address.getCountryCode().equals(ShiplinxConstants.CANADA))
			return ShiplinxConstants.IMPORT;
		return ShiplinxConstants.EXPORT;
	}
	
	public boolean isApplyEdiExceptions() {
		if (isApplyEdiExceptions == null) {
			String str = WebUtil.getProperty(Constants.SYSTEM_SCOPE, "APPLY_EDI_EXCEPTIONS");
			isApplyEdiExceptions = (str.equals("true")?true:false);
		}
		return isApplyEdiExceptions;
	}	
//	protected Integer getNumber(String ediField) throws Exception {
//		// TODO Auto-generated method stub
////		try {
//			Integer n = Integer.parseInt(ediField);
//			return n;
////		} catch (Exception e) {
//////			e.printStackTrace();
////		}
////		return new Integer(0);
//	}	
	
//	protected Double getNumber(String ediField) throws Exception {
//		// TODO Auto-generated method stub
//		Double n = Double.parseDouble(ediField);
//		return n;
//	}	
}
