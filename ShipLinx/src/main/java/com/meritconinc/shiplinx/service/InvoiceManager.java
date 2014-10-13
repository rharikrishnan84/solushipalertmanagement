package com.meritconinc.shiplinx.service;

import java.io.OutputStream;
import java.util.List;

import com.meritconinc.mmr.model.security.User;
import com.meritconinc.shiplinx.model.ARTransaction;
import com.meritconinc.shiplinx.model.Commission;
import com.meritconinc.shiplinx.model.CreditCard;
import com.meritconinc.shiplinx.model.Invoice;
import com.meritconinc.shiplinx.model.InvoiceStatus;
import com.meritconinc.shiplinx.model.SalesRecord;
import com.meritconinc.shiplinx.model.ShippingOrder;

public interface InvoiceManager {
	
	public List<Invoice> createInvoices(List<Long> orders, Invoice invoice);
	public List<Invoice> searchInvoices(Invoice invoice);
	public Invoice getInvoiceById(long invoiceId);
	public List<ShippingOrder> getShippingOrders(long invoiceId);
	
	public List<Invoice> autoGenInvoicesForBusiness(Long businessId, Invoice invoice, String branch);
	public List<InvoiceStatus> getInvoiceStatusList();
	
	public List<Invoice> processPayment(List<Long> invoiceIds, CreditCard creditCard, boolean sendAdminNotification);
	public void processAR(List<Invoice> invoicesToUpdate);
	public List<ARTransaction> searchARTransaction(ARTransaction arTransaction);
	public void getInvoicePdf(Long id, String url, OutputStream outStream, boolean detailed) throws Exception;
	public void getSalesInvoicePdf(Long id, String url, OutputStream outStream, boolean detailed,String salesUser) throws Exception;
	public boolean cancelInvoice(long invoiceId);
	 
	public List<Invoice> generateCommReport(Invoice invoice);
	public List<SalesRecord> generateSalesReport(SalesRecord sales);
	
	public void calculateInvoiceCharges(Invoice i);
	public Invoice updateInvoice(Invoice invoice, String[] ids, String[] userCharges, String[] userCosts, 
			String[] userNames, String[] trackNos) throws Exception;

	public boolean sendInvoiceEmailNotification(User user, List<Invoice> invoice);
	
	public boolean downloadInvoiceCSV(long invoiceId, OutputStream ostream);
	
	public Invoice createInvoice(List<ShippingOrder> orders, Invoice invoice, long customerId, String currency);

	public void updateInvoiceStatus(List<Invoice> invoicesToUpdate);
	
	public ARTransaction getinvoicebyinvoiceid(long invoiceid);
	public void updateInvoiceStatusCommission(List<Invoice> invoicesToUpdate);
			
			public List<Invoice> searchInvoicesAr(Invoice invoice);
				public List<Invoice> searchInvoicesArSearch1(Invoice invoice);
			public List<Commission> searchCommissions(Commission commission);
			public void deleteCommission(long invoiceId);	
}
