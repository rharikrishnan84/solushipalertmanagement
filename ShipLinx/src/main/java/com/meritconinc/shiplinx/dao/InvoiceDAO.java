package com.meritconinc.shiplinx.dao;

import java.util.List;

import com.meritconinc.shiplinx.model.ARTransaction;
import com.meritconinc.shiplinx.model.Charge;
import com.meritconinc.shiplinx.model.Commission;
import com.meritconinc.shiplinx.model.Invoice;
import com.meritconinc.shiplinx.model.InvoiceStatus;
import com.meritconinc.shiplinx.model.SalesRecord;
import com.meritconinc.shiplinx.model.ShippingOrder;

public interface InvoiceDAO {

	public Long createInvoice(Invoice invoice) throws Exception;

	public List<Invoice> searchInvoices(Invoice invoice);
	public List<Invoice> searchPaidToRepInvoices(Invoice invoice);
	public Invoice getInvoiceById(long invoiceId);
	public List<ShippingOrder> getShippingOrders(long invoiceId);
	
	public void addShipmentAndChargeToInvoice(long invoiceId, long order_id, long charge_id);
	public void addShipmentToInvoice(long invoiceId, long orderId, double totalCharge);
	public List<Long> findPreviousInvoiceId(long orderId, long invoiceId);
	
	public List<InvoiceStatus> getInvoiceStatusList();
	public void updateInvoice(Invoice invoice); 

	public Long addARTransaction(ARTransaction transaction) throws Exception;
	public List<ARTransaction> searchARTransaction(ARTransaction arTransaction);
	
	public void deleteShipmentAndChargeFromInvoice(long invoiceId, long orderId, long chargeId);

	public List<SalesRecord> getSalesReport(SalesRecord sales);
	
	public void updateInvoiceStatus(Invoice invoice);
	
	 public ARTransaction getinvoicebyinvoiceid(long invoiceid);
	 
	 public void updateInvoiceCharges(Invoice invoice);
	 public void updateInvoiceStatusCommission(Invoice invoice);
	 public void updateInvoiceCommission(Invoice invoice); 
	 public void updateInvoiceCommissionAmount(Commission commission);
	 public void createcommission(Commission commission);
	 public String getinvoicestatusbyId(long id);
	  
	 public List<Invoice> searchInvoicesAr(Invoice invoice);
	 public List<Invoice> searchInvoicesCommission(Invoice invoice);
	 public List<Invoice> searchInvoicesArSearch(Invoice invoice);
	 public List<Commission> searchCommissions(Commission commission);
	 public Commission getcommissionbyId(Long invoiceId, String salesUser);
	 public void deleteCommission(long invoiceId);
	 public List<Commission> getCommissionsByInvoiceId(long invoiceId);
	 public List<Invoice> searchInvoicesBreakdown(Commission commission);
     public List<Invoice> getInvoiceChargeDetails(Long invoiceId);
     public List<Charge> getChargeExchangeRateByInvoiceId(long invoiceId);
     public List<Invoice> searchInvoicesBreakdownIncludeCanceledInvoice(Commission commission);
}
