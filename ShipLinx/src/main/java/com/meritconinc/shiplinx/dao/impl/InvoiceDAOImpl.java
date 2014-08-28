package com.meritconinc.shiplinx.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.meritconinc.mmr.utilities.security.UserUtil;
import com.meritconinc.shiplinx.dao.InvoiceDAO;
import com.meritconinc.shiplinx.model.ARTransaction;
import com.meritconinc.shiplinx.model.Customer;
import com.meritconinc.shiplinx.model.Invoice;
import com.meritconinc.shiplinx.model.InvoiceStatus;
import com.meritconinc.shiplinx.model.SalesRecord;
import com.meritconinc.shiplinx.model.ShippingOrder;

public class InvoiceDAOImpl extends SqlMapClientDaoSupport implements InvoiceDAO {

	private static final Logger log = LogManager.getLogger(InvoiceDAOImpl.class);

	public List<Invoice> searchInvoices(Invoice invoice){
		return (List<Invoice>)getSqlMapClientTemplate().queryForList("searchInvoices", invoice);
	}
	public List<Invoice> searchPaidToRepInvoices(Invoice invoice){
		return (List<Invoice>)getSqlMapClientTemplate().queryForList("searchPaidToRepInvoices", invoice);
	}
	public Invoice getInvoiceById(long invoiceId){
		return (Invoice)getSqlMapClientTemplate().queryForObject("findInvoiceById", invoiceId);		
	} 
	
	public List<ShippingOrder> getShippingOrders(long invoiceId){
		List<ShippingOrder> orders = null;
		return orders;
	}
	
	public Long createInvoice(Invoice invoice) throws Exception{
		try{
			return (Long)getSqlMapClientTemplate().insert("createInvoice", invoice);
		}catch(Exception e){
			log.debug("-----Exception-----"+e);
			e.printStackTrace();
			throw e;
		}
	}
	
	
	public void addShipmentAndChargeToInvoice(long invoiceId, long orderId, long chargeId){
		Map<String, Object> paramObj = new HashMap<String, Object>();
		paramObj.put("invoiceId", invoiceId);
		paramObj.put("chargeId", chargeId);
		paramObj.put("orderId", orderId);
		getSqlMapClientTemplate().insert("addChargeToInvoice", paramObj);
		
	}

	public void addShipmentToInvoice(long invoiceId, long orderId, double totalCharge){
		Map<String, Object> paramObj = new HashMap<String, Object>();
		paramObj.put("invoiceId", invoiceId);
		paramObj.put("totalCharge", totalCharge);
		paramObj.put("orderId", orderId);
		getSqlMapClientTemplate().insert("addChargeToInvoice", paramObj);
		
	}

	//this function finds an invoice other than the one mentioned, if any, on which the order was billed
	public List<Long> findPreviousInvoiceId(long orderId, long invoiceId){
		Map<String, Object> paramObj = new HashMap<String, Object>();
		paramObj.put("invoiceId", invoiceId);
		paramObj.put("orderId", orderId);
		return getSqlMapClientTemplate().queryForList("findPreviousInvoiceId", paramObj);
		
	}

	public void updateInvoice(Invoice invoice){
		try{
			getSqlMapClientTemplate().update("updateInvoice", invoice);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public void updateInvoiceStatus(Invoice invoice){
		try{
			getSqlMapClientTemplate().update("updateInvoiceStatus", invoice);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public Long addARTransaction(ARTransaction transaction) throws Exception{
		try{
			return (Long)getSqlMapClientTemplate().insert("addARTransaction", transaction);
		}catch(Exception e){
			log.debug("-----Exception-----"+e);
			e.printStackTrace();
			throw e;
		}
	}

	public List<ARTransaction> searchARTransaction(ARTransaction arTransaction){

		List<ARTransaction>  list = (List<ARTransaction>)getSqlMapClientTemplate().queryForList("searchARTransactions", arTransaction);
		
		if(list!=null && list.size()>0){
			for(ARTransaction arTran: list){
				Map<String, Object> paramObj = new HashMap<String, Object>(1);
				paramObj.put("id", arTran.getCustomerId());
				paramObj.put("businessId", UserUtil.getMmrUser().getBusinessId());
				Customer customer=(Customer)getSqlMapClientTemplate().queryForObject("getCustomerInfoByCustomerId",paramObj);
				arTran.setCustomer(customer);
			}
		}
		return list;
	}

	
	public List<InvoiceStatus> getInvoiceStatusList(){
		return getSqlMapClientTemplate().queryForList("getInvoiceStatuses");
		
	}
	
	public void deleteShipmentAndChargeFromInvoice(long invoiceId, long orderId, long chargeId){
		Map<String, Object> paramObj = new HashMap<String, Object>();
		paramObj.put("invoiceId", invoiceId);
		paramObj.put("chargeId", chargeId);
		paramObj.put("orderId", orderId);
		getSqlMapClientTemplate().delete("deleteChargeFromInvoice", paramObj);
		
	}	
	
	public List<SalesRecord> getSalesReport(SalesRecord sales){
		return getSqlMapClientTemplate().queryForList("getSalesReport", sales);
	}
	 public ARTransaction getinvoicebyinvoiceid(long invoiceid){
		  return (ARTransaction) getSqlMapClientTemplate().queryForObject("getinvoicebyinvoiceid", invoiceid);
		 }
	 
	 @Override
	 	public void updateInvoiceCharges(Invoice invoice) {
	 		try{
	 			getSqlMapClientTemplate().update("updateInvoiceCharges", invoice);
	 		}catch(Exception e){
	 			e.printStackTrace();
	 		}
	 		
	 	}


}
