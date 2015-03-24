package com.meritconinc.shiplinx.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.meritconinc.mmr.utilities.security.UserUtil;
import com.meritconinc.shiplinx.dao.EdiDAO;
import com.meritconinc.shiplinx.model.EdiInfo;
import com.meritconinc.shiplinx.model.EdiItem;
import com.meritconinc.shiplinx.service.impl.EdiParser;

public class EdiDAOImpl extends SqlMapClientDaoSupport implements EdiDAO {

	private static final Logger log = LogManager.getLogger(EdiDAOImpl.class);

	@Override
	public EdiInfo getEdiInfo(Long carrierId, long busId) {
		// TODO Auto-generated method stub
		Map<String, Object> paramObj = new HashMap<String, Object>(2);
		paramObj.put("businessId", busId);
		paramObj.put("carrierId", carrierId);
		EdiInfo ediInfo = (EdiInfo) getSqlMapClientTemplate().queryForObject(
				"getEdiInfo", paramObj);
		return ediInfo;
	} 

	@SuppressWarnings("unchecked")
	@Override
	public List<EdiInfo> getEdiInfoList(long busId) {
		// TODO Auto-generated method stub
		Map<String, Object> paramObj = new HashMap<String, Object>(1);
		paramObj.put("businessId", busId);

		List<EdiInfo> ediInfoList = (List<EdiInfo>) getSqlMapClientTemplate()
				.queryForList("getEdiInfoList", paramObj);
		return ediInfoList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EdiItem> getEdiItemList(EdiItem item, String ediFileName, long busId) {
		// TODO Auto-generated method stub
		Map<String, Object> paramObj = new HashMap<String, Object>(5);
		paramObj.put("businessId", busId);
		if (item != null) {
			if (item.getCarrierId() != null)
				paramObj.put("carrierId", item.getCarrierId());
			if (ediFileName != null)
				paramObj.put("ediFileName", ediFileName);
			if (item.getInvoiceDate() != null)
				paramObj.put("invoiceDate", item.getInvoiceDate());
			if (item.getInvoiceNumber() != null)
				paramObj.put("invoiceNumber", item.getInvoiceNumber());
		}

		List<EdiItem> ediItemList = (List<EdiItem>) getSqlMapClientTemplate()
				.queryForList("getEdiItemList", paramObj);
		return ediItemList;
	}

	@Override
	public long addEdiItem(EdiItem item) {
	
		// TODO Auto-generated method stub
		if (item != null) {
			log.info("Adding edi item for file " + item.getEdiFileName());
			// Map<String, Object> paramObj = new HashMap<String, Object>(8);
			// paramObj.put("businessId", item.getBusinessId());
			// paramObj.put("carrierId", item.getCarrierId());
			// paramObj.put("ediFileName", item.getEdiFileName());
			// paramObj.put("message", item.getMessage());
			// paramObj.put("status", item.getStatus());
			//	
			// getSqlMapClientTemplate().insert("addEdiItem", paramObj);
			long key = ((Long) getSqlMapClientTemplate().insert("addEdiItem",
					item)).longValue();

			return key;
		}

		return -1;
	}

	@Override
	public void updateEdiItem(EdiItem item) {
		// TODO Auto-generated method stub
		if (item != null) {
			log.info("Updating edi item for file " + item.getEdiFileName() + " and ID " + item.getId());
			Map<String, Object> paramObj = new HashMap<String, Object>(11);
			paramObj.put("id", item.getId());
			paramObj.put("message", item.getMessage());
			paramObj.put("status", item.getStatus());
			paramObj.put("invoiceDate", item.getInvoiceDate());
			paramObj.put("invoiceNumber", item.getInvoiceNumber());
			paramObj.put("processedDate", item.getProcessedDate());
			paramObj.put("processedDate", item.getProcessedDate());
			paramObj.put("elapsedTime", item.getElapsedTime());
			paramObj.put("accountNumber", item.getAccountNumber());
			paramObj.put("totInvoiceAmount", item.getTotInvoiceAmount());
			paramObj.put("type", item.getType());

			getSqlMapClientTemplate().insert("updateEdiItem", paramObj);
		}
	}

	@Override
	public void updateEdiItemStatus(long ediItemId, int status) {
		// TODO Auto-generated method stub
		if (ediItemId > 0) {
			Map<String, Object> paramObj = new HashMap<String, Object>(3);
			paramObj.put("id", ediItemId);
			paramObj.put("status", status);

			getSqlMapClientTemplate().update("updateEdiItemStatus", paramObj);
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<EdiItem> getMatchingEdiItemList(EdiItem item) {
		// TODO Auto-generated method stub
		if (item != null) {
			List<EdiItem> ediItemList = (List<EdiItem>) getSqlMapClientTemplate().queryForList("getMatchingEdiItemList", item);
			return ediItemList;
		}
		return null;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<EdiItem> findEdiItems(EdiItem item) {
		// TODO Auto-generated method stub
		if (item != null) {
			if (item.getCarrierId() != null && item.getCarrierId().longValue() <= 0)
				item.setCarrierId(null);
			if (item.getFromDate() != null && item.getFromDate().trim().isEmpty())
				item.setFromDate(null);
			if (item.getEdiFileName() != null && item.getEdiFileName().trim().isEmpty())
				item.setEdiFileName(null);		
			if (item.getInvoiceNumber() != null && item.getInvoiceNumber().trim().isEmpty())
				item.setInvoiceNumber(null);
			List<EdiItem> ediItemList = (List<EdiItem>) getSqlMapClientTemplate().queryForList("findEdiItems", item);
			List<EdiItem> ediItemList1=new ArrayList<EdiItem>();
						for(EdiItem e:ediItemList){
							if(e.getTotInvoiceAmount()!=null){
								log.error(e.getId()+"is having null amount and its status is"+e.getStatus()+"(ie)"+e.getStatusText());
								ediItemList1.add(e);
							}
						}
			return ediItemList1;
		}
		return null;
	}	

	// @Override
	// public EdiItem getEdiItem(Long carrierId, Date invoiceDate, String
	// invoiceNumber) {
	// // TODO Auto-generated method stub
	// Map<String, Object> paramObj = new HashMap<String, Object>(4);
	// paramObj.put("businessId", UserUtil.getMmrUser().getBusinessId());
	// paramObj.put("carrierId", carrierId);
	// paramObj.put("invoiceDate", invoiceDate);
	// paramObj.put("invoiceNumber", invoiceNumber);
	//		
	//		
	// List<EdiItem> ediItemList = (List<EdiItem>)
	// getSqlMapClientTemplate().queryForList("getEdiItemList", paramObj);
	//		
	// return ediItemList;
	// return null;
	// }
	
	@Override
		public List<EdiItem> searchFileInEdiItem(String uploadFileName) {
			// TODO Auto-generated method stub
			List<EdiItem> i=getSqlMapClientTemplate().queryForList("searchFileInEdiItem", uploadFileName);
			return i;
		}
	
	
		@Override
				public void insertEdiInfoByBusiness(long newBusinessId,
						long defaultBusinessId) {
					Map map = new HashMap();
				    map.put("newBusinessId", newBusinessId);
				    map.put("defaultBusinessId", defaultBusinessId);
				    try {
				        getSqlMapClientTemplate().insert("addEDIInfobyBusiness", map);
			      } catch (Exception e) {
	        // log.debug("-----Exception-----"+e);
				        e.printStackTrace();
				      }
					
				}


}
