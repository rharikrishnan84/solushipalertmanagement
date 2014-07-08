package com.meritconinc.shiplinx.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import au.com.bytecode.opencsv.CSVReader;

import com.meritconinc.mmr.constants.Constants;
import com.meritconinc.mmr.utilities.DateUtil;
import com.meritconinc.mmr.utilities.StringUtil;
import com.meritconinc.mmr.utilities.WebUtil;
import com.meritconinc.mmr.utilities.security.UserUtil;
import com.meritconinc.shiplinx.dao.CustomerDAO;
import com.meritconinc.shiplinx.dao.ShippingDAO;
import com.meritconinc.shiplinx.exception.ShiplinxException;
import com.meritconinc.shiplinx.model.Address;
import com.meritconinc.shiplinx.model.BatchShipmentInfo;
import com.meritconinc.shiplinx.model.Customer;
import com.meritconinc.shiplinx.model.Package;
import com.meritconinc.shiplinx.model.PackageType;
import com.meritconinc.shiplinx.model.ShippingOrder;
import com.meritconinc.shiplinx.utils.ShiplinxConstants;

public class BatchShipmentHelper {
	private static final Logger log = LogManager.getLogger(BatchShipmentHelper.class); 

	private static final int Batch_ID 					= 0;
	private static final int From_Company_Name 			= 1;
	private static final int From_Address1 				= 2;
	private static final int From_Address2 				= 3;
	private static final int From_Zip_Postal 			= 4;
	private static final int From_City 					= 5;
	private static final int From_Country_Code 			= 6;
	private static final int From_Province_Code 		= 7;
	private static final int From_Phone 				= 8;
	private static final int From_Attention 			= 9;
	private static final int From_Email 				= 10;
	private static final int From_Instructions 			= 11;
	private static final int From_Is_Residential 		= 12;
	private static final int To_Company_Name 			= 13;
	private static final int To_Address1 				= 14;
	private static final int To_Address2 				= 15;
	private static final int To_Zip_Postal 				= 16;
	private static final int To_City 					= 17;
	private static final int To_Country_Code 			= 18;
	private static final int To_Province_Code 			= 19;
	private static final int To_Phone 					= 20;
	private static final int To_Attention 				= 21;
	private static final int To_Email 					= 22;
	private static final int To_Instructions 			= 23;
	private static final int To_Is_Residential 			= 24;
	private static final int Package_Type 				= 25;
	private static final int ReferenceCode 				= 26;
	private static final int Documents_Only 			= 27;
	private static final int Saturday_Delivery 			= 28;
	private static final int Ship_Date 					= 29;
	private static final int Dutiable_amount 			= 30;
	private static final int Package_Length 			= 31;
	private static final int Package_Width 				= 32;
	private static final int Package_Height 			= 33;
	private static final int Package_Weight 			= 34;
	private static final int Package_Insurance_Amount 	= 35;
	private static final int Package_Description 		= 36;
	private static final int Package_COD_Amount 		= 37;

	public static final String FAILED_TO_PARSE_BATCH_SHIPMENT_RECORD = "Failed to parse batch shipment record.";
	public static final String FAILED_TO_CREATE_BATCH_SHIPMENT_RECORD = "Failed to create batch shipment record.";
	public static final String FAILED_TO_PROCESS_BATCH_SHIPMENT_RECORD = "Failed to process batch shipment record.";
	public static final String MESSAGE_DETAIL = "For more details, please see exception logs.";
	
	private List<PackageType> packageTypes = null;
	private String batchId = null;
	private ShippingDAO shippingDAO = null;
	private CustomerDAO customerDAO = null;
	private Customer customer = null;
	
	public BatchShipmentHelper(List<PackageType> pTypes, ShippingDAO shipDao, CustomerDAO custDAO) {
		packageTypes = pTypes;
		shippingDAO = shipDao;
		customerDAO = custDAO;
	}
	

	public List<ShippingOrder> uploadParseFile(BatchShipmentInfo batchInfo, InputStream is) throws IOException {
		// TODO Auto-generated method stub
		if (batchInfo != null && !StringUtil.isEmpty(batchInfo.getFileName()) && is != null) {
			String fileName = WebUtil.getProperty(Constants.SYSTEM_SCOPE, "BASE_PATH") + File.separator + "BatchUpload" + 
					File.separator + ShiplinxConstants.IN_FOLDER + File.separator + batchInfo.getFileName();
			
			File outFile = new File(fileName);
			OutputStream bos = null;
			int bytesRead = 0;
			byte[] buffer = new byte[8192];

			boolean bFirstTime = true;
			while ((bytesRead = is.read(buffer, 0, 8192)) != -1) {
				if (bFirstTime) {
					bFirstTime = false;
					bos = new FileOutputStream(outFile);
				}
				bos.write(buffer, 0, bytesRead);
			}
			bos.close();
			is.close();
			
			List<ShippingOrder> shipments = parseFile(fileName);
			
			// Move file to the out folder
			String outFileName = WebUtil.getProperty(Constants.SYSTEM_SCOPE, "BASE_PATH") + File.separator + "BatchUpload" + 
				File.separator + ShiplinxConstants.OUT_FOLDER + File.separator + batchInfo.getFileName();
			moveFile(fileName, outFileName);
			
			return shipments;
		}
		return null;
	}

	private void moveFile(String inFileName, String outFileName) {
		// TODO Auto-generated method stub
		if (inFileName != null && outFileName != null) {
			File inFile = new File(inFileName);
			File outFile = new File(outFileName);
			if (outFile.exists()) {
				outFile.delete();
				outFile = null;
				outFile = new File(outFileName);
			}
			boolean success = inFile.renameTo(outFile);
			if (!success) {
				String msg = "Failed to moved to out Folder:" + outFile.getPath() + " File:" + inFile.getName();
				log.error( msg );
			}
		}		
	}


	private List<ShippingOrder> parseFile(String fileName) {
		// TODO Auto-generated method stub
		String[] fields = null;
		String[] rowData = null;
		List<ShippingOrder> shipments = null;
		try {
			File f = new File(fileName);
			if (f != null) {
				CSVReader csvReader = new CSVReader(new FileReader(f));
				if (csvReader != null) {
					int i = 0;
					fields = csvReader.readNext();
					if (fields != null) {
						shipments = new ArrayList<ShippingOrder>();
						while ((rowData = csvReader.readNext()) != null) {
							i++;
							log.debug("Processing Line: " + i);
							if (rowData.length == fields.length) {
								try {
									parseRecord(fields, rowData, shipments);
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
				}
				csvReader.close();
			}			
		} catch (ShiplinxException e) {
			throw e;
		} catch (Exception ex) {
			ex.printStackTrace();
			log.error(ex);
			throw new ShiplinxException(ex.getMessage());
		}
		return shipments;
	}

	private void parseRecord(String[] fields, String[] rowData, List<ShippingOrder> shipments) {
		// TODO Auto-generated method stub
		try {
			if (batchId == null) {
				batchId = rowData[Batch_ID];
				ShippingOrder oldShippingOrder = this.shippingDAO.findByBatchId(batchId);
				if (oldShippingOrder != null) {
					String msg = "Error: Shipment Already Exists - Batch ID:" + batchId + 
									" From Company Name:" + oldShippingOrder.getFromAddress().getAbbreviationName();
					log.error(msg);
					throw new ShiplinxException(msg);
				} 
			} else if ( !batchId.equals(rowData[Batch_ID]) ) {
				String msg = "Error: Multiple batches are not allowed in one file - Previous Batch ID:" + batchId + 
													" New Batch ID:" + rowData[Batch_ID];
				log.error(msg);
				throw new ShiplinxException(msg);
			}
			ShippingOrder so = new ShippingOrder();
			so.setBatchId(batchId);
			
			// ShippingOrder.From Address
			so.setFromAddress(new Address());
			so.getFromAddress().setAbbreviationName(rowData[From_Company_Name]);
			so.getFromAddress().setAddress1(rowData[From_Address1]);
			so.getFromAddress().setAddress2(rowData[From_Address2]);
			so.getFromAddress().setPostalCode(rowData[From_Zip_Postal]); 	
			so.getFromAddress().setCity(rowData[From_City]); 			
			so.getFromAddress().setCountryCode(rowData[From_Country_Code]); 	
			so.getFromAddress().setProvinceCode(rowData[From_Province_Code]); 
			so.getFromAddress().setPhoneNo(rowData[From_Phone]); 		
			so.getFromAddress().setContactName(rowData[From_Attention]); 	
			so.getFromAddress().setEmailAddress(rowData[From_Email]); 		
			so.setFromInstructions(rowData[From_Instructions]); 	
			so.getFromAddress().setResidential(StringUtil.toBoolean(rowData[From_Is_Residential]));
			
			// ShippingOrder.To Address
			so.setToAddress(new Address());
			so.getToAddress().setAbbreviationName(rowData[To_Company_Name]);
			so.getToAddress().setAddress1(rowData[To_Address1]);
			so.getToAddress().setAddress2(rowData[To_Address2]);
			so.getToAddress().setPostalCode(rowData[To_Zip_Postal]); 	
			so.getToAddress().setCity(rowData[To_City]); 			
			so.getToAddress().setCountryCode(rowData[To_Country_Code]); 	
			so.getToAddress().setProvinceCode(rowData[To_Province_Code]); 
			so.getToAddress().setPhoneNo(rowData[To_Phone]); 		
			so.getToAddress().setContactName(rowData[To_Attention]); 	
			so.getToAddress().setEmailAddress(rowData[To_Email]); 		
			so.setToInstructions(rowData[To_Instructions]); 	
			so.getToAddress().setResidential(StringUtil.toBoolean(rowData[To_Is_Residential]));

			// ShippingOrder
			so.setPackageTypeId(getPackageType(rowData[Package_Type])); 
			so.setReferenceCode(rowData[ReferenceCode]); 
			if(StringUtil.isEmpty(rowData[Ship_Date]))
				so.setScheduledShipDate(new Timestamp(Calendar.getInstance().getTimeInMillis()));
			else
				so.setScheduledShipDate(DateUtil.convert(DateUtil.convertStringToDate(rowData[Ship_Date], "yyyy-MM-dd")));
			so.setDutiableAmount(StringUtil.getBigDecimal(rowData[Dutiable_amount]));
			so.setSatDelivery(StringUtil.toBoolean(rowData[Saturday_Delivery]));
			
			// ShippingOrder.Package
			Package p = new Package();
			p.setDoc(StringUtil.toBoolean(rowData[Documents_Only])); 			
			p.setLength(StringUtil.getBigDecimal(rowData[Package_Length]));  			
			p.setWidth(StringUtil.getBigDecimal(rowData[Package_Width])); 				
			p.setHeight(StringUtil.getBigDecimal(rowData[Package_Height])); 			
			p.setWeight(new BigDecimal(rowData[Package_Weight])); 			
			p.setInsuranceAmount(new BigDecimal(rowData[Package_Insurance_Amount])); 	
			p.setDescription(rowData[Package_Description]);
			p.setCodAmount(new BigDecimal(rowData[Package_COD_Amount]));
			
			p.setLength(p.getLength().setScale(1, BigDecimal.ROUND_UNNECESSARY));
			p.setHeight(p.getHeight().setScale(1, BigDecimal.ROUND_UNNECESSARY));
			p.setWidth(p.getWidth().setScale(1, BigDecimal.ROUND_UNNECESSARY));
//			p.setCodAmount(p.getCodAmount().setScale(1, BigDecimal.ROUND_UNNECESSARY));	
//			p.setInsuranceAmount(p.getInsuranceAmount().setScale(1, BigDecimal.ROUND_UNNECESSARY));
			
			ShippingOrder soFromList = findShipmentInList(so, shipments);
			if (soFromList == null) {
				// New Shipment
				so.setBusinessId(UserUtil.getMmrUser().getBusinessId());
				so.setCustomerId(UserUtil.getMmrUser().getCustomerId());
				if (customer == null)
					customer = customerDAO.getCustomerInfoByCustomerId(so.getCustomerId(), so.getBusinessId());
				so.setCustomer(customer);
				so.getPackages().add(p);
				shipments.add(so);
			} else {
				// Old Shipment, simply add a package in the shipment
				soFromList.getPackages().add(p);
			}
			so.setQuantity(so.getPackages().size());
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
			String msg = getExceptionMessage(e, FAILED_TO_PARSE_BATCH_SHIPMENT_RECORD);
			throw new ShiplinxException(msg);
		}
	}
	public static String getExceptionMessage(Exception e, String msg) {
		// TODO Auto-generated method stub
		StringBuffer sbMsg = new StringBuffer();
		sbMsg.append(FAILED_TO_PARSE_BATCH_SHIPMENT_RECORD);
		sbMsg.append(MESSAGE_DETAIL);
		if (e.getMessage() != null) {
			sbMsg.append(e.getMessage());
			sbMsg.append(" ");
		}
		if (e.getCause() != null) {
			sbMsg.append(e.getCause().getMessage());
		}
		
		return sbMsg.toString();
	}


	private ShippingOrder findShipmentInList(ShippingOrder so, List<ShippingOrder> shipments) {
		// TODO Auto-generated method stub
//		Business Rules
//	    2. System will consolidate the rows into shipments based on the    following rules:
//	    	- Address1 of ship from and ship to match, and
//	    	- Postal/Zip code of ship from and ship to match (blank/null is also    a match)
//	    	- Package type is the same

		if (so != null && shipments != null) {
			for (ShippingOrder s1:shipments) {
				if (s1.getFromAddress() != null && s1.getToAddress() != null && s1.getPackageTypeId() != null &&
					so.getFromAddress() != null	&& so.getToAddress() != null && so.getPackageTypeId() != null) {
					if (so.getFromAddress().getAddress1().equalsIgnoreCase(s1.getFromAddress().getAddress1()) &&
						so.getToAddress().getAddress1().equalsIgnoreCase(s1.getToAddress().getAddress1()) &&
						so.getFromAddress().getPostalCode().equalsIgnoreCase(s1.getFromAddress().getPostalCode()) &&
						so.getToAddress().getPostalCode().equalsIgnoreCase(s1.getToAddress().getPostalCode()) &&
						so.getPackageTypeId().getName().equalsIgnoreCase(s1.getPackageTypeId().getName())) {
						
						return s1;
					}
				}
			}
		}
		return null;
	}


	private PackageType getPackageType(String batchPkgType) {
		// TODO Auto-generated method stub
		for(PackageType pt:packageTypes)
			if (pt.getName().equals(batchPkgType)) 
				return pt;

		return null;
	}
	
}
