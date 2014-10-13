package com.meritconinc.shiplinx.model;

import java.sql.Timestamp;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import com.meritconinc.shiplinx.utils.FormattingUtil;

public class Invoice {

	private Long invoiceId;
	private String invoiceNum;
	private Long customerId;
	private Long businessId;
	private Timestamp dateCreated;
	private Timestamp invoiceDate;
	private Double invoiceAmount=0.0;
	private Double invoiceCost=0.0;
	private Double invoiceTax=0.0;
	private Double invoiceTaxCost=0.0;
	private Integer paymentStatus;
	private String paymentStatusString;
	private Double paidAmount;
	private Integer payableDays;
	private String currency;
	private Customer customer;
	private Timestamp invoiceDueDate;
	 
	private List<ShippingOrder> orders;

	public static final int INVOICE_STATUS_UNPAID = 10;
	public static final int INVOICE_STATUS_PARTIAL_PAID = 20;
	public static final int INVOICE_STATUS_PAID = 30;
	public static final int INVOICE_STATUS_CANCELLED = 40;
	public static final int INVOICE_STATUS_PAID_TO_REP = 50;
	
	//search related, for web only
	private String fromInvoiceDate_web;
	private String toInvoiceDate_web;
	private Date fromInvoiceDate;
	private Date toInvoiceDate;
	private String paymentResult;
	private CCTransaction transaction;
	private String invoiceNumberString;
	private ARTransaction arTransaction = new ARTransaction();
	private String salesUsername;
	private double commissionAmount;
	private String branch;
	
	private boolean cancelled;
	private Double balanceDue;
	public void setBalanceDue(Double balanceDue) {
		this.balanceDue = balanceDue;
	}

	private int[] paymentStatusList = new int[10];
	private String salesRep;
	private int repPaid;
	private int[] repPaidList = new int[10];
	
	public int getRepPaid() {
		return repPaid;
	}

	public void setRepPaid(int repPaid) {
		this.repPaid = repPaid;
	}

	public int[] getRepPaidList() {
		return repPaidList;
	}

	public void setRepPaidList(int[] repPaidList) {
		this.repPaidList = repPaidList;
	}

	public String getSalesRep() {
		return salesRep;
	}

	public void setSalesRep(String salesRep) {
		this.salesRep = salesRep;
	}

	public Invoice(){
		this.invoiceId = new Long(0);
	}
	
	public Long getInvoiceId() {
		return invoiceId;
	}
	public void setInvoiceId(Long invoiceId) {
		this.invoiceId = invoiceId;
	}
	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	public Long getBusinessId() {
		return businessId;
	}
	public void setBusinessId(Long businessId) {
		this.businessId = businessId;
	}
	public Timestamp getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(Timestamp dateCreated) {
		this.dateCreated = dateCreated;
	}
	public Timestamp getInvoiceDate() {
		return invoiceDate;
	}
	public void setInvoiceDate(Timestamp invoiceDate) {
		this.invoiceDate = invoiceDate;
	}
	public Double getInvoiceAmount() {
		return invoiceAmount;
	}
	public void setInvoiceAmount(Double invoiceAmount) {
		this.invoiceAmount = invoiceAmount;
	}
	public Double getInvoiceCost() {
		return invoiceCost;
	}
	public void setInvoiceCost(Double invoiceCost) {
		this.invoiceCost = invoiceCost;
	}
	public Double getInvoiceTax() {
		return invoiceTax;
	}
	public void setInvoiceTax(Double invoiceTax) {
		this.invoiceTax = invoiceTax;
	}
	public Double getInvoiceTaxCost() {
		return invoiceTaxCost;
	}
	public void setInvoiceTaxCost(Double invoiceTaxCost) {
		this.invoiceTaxCost = invoiceTaxCost;
	}
	public Integer getPaymentStatus() {
		return paymentStatus;
	}
	public void setPaymentStatus(Integer paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	public Double getPaidAmount() {
		return paidAmount;
	}
	public void setPaidAmount(Double paidAmount) {
		this.paidAmount = paidAmount;
	}
	public Integer getPayableDays() {
		return payableDays;
	}
	public void setPayableDays(Integer payableDays) {
		this.payableDays = payableDays;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public List<ShippingOrder> getOrders() {
		return orders;
	}
	public void setOrders(List<ShippingOrder> orders) {
		this.orders = orders;
	}

	public int[] getPaymentStatusList() {
		return paymentStatusList;
	}
	public void setPaymentStatusList(int[] paymentStatusList) {
		this.paymentStatusList = paymentStatusList;
	}

	public String getFromInvoiceDate_web() {
		return fromInvoiceDate_web;
	}

	public void setFromInvoiceDate_web(String fromInvoiceDate_web) {
		this.fromInvoiceDate_web = fromInvoiceDate_web;
	}

	public String getToInvoiceDate_web() {
		return toInvoiceDate_web;
	}

	public void setToInvoiceDate_web(String toInvoiceDate_web) {
		this.toInvoiceDate_web = toInvoiceDate_web;
	}

	public Date getFromInvoiceDate() {
		return fromInvoiceDate;
	}

	public void setFromInvoiceDate(Date fromInvoiceDate) {
		this.fromInvoiceDate = fromInvoiceDate;
	}

	public Date getToInvoiceDate() {
		return toInvoiceDate;
	}

	public void setToInvoiceDate(Date toInvoiceDate) {
		this.toInvoiceDate = toInvoiceDate;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	public Double getBalanceDue(){
		
		double balance = FormattingUtil.add(getTotalInvoiceCharge(), -1*paidAmount).doubleValue();
		return FormattingUtil.formatDecimalTo2PlacesDouble(balance) ;
	}
	
	public double getTotalInvoiceCharge(){
		double total = FormattingUtil.add(invoiceAmount,invoiceTax).doubleValue();
		return FormattingUtil.formatDecimalTo2PlacesDouble(total);
	}

	public CCTransaction getTransaction() {
		return transaction;
	}
	public void setTransaction(CCTransaction transaction) {
		this.transaction = transaction;
	}

	public String getInvoiceNumberString() {
		return invoiceNumberString;
	}
	public void setInvoiceNumberString(String invoiceNumberString) {
		this.invoiceNumberString = invoiceNumberString;
	}

	public ARTransaction getArTransaction() {
		return arTransaction;
	}
	public void setArTransaction(ARTransaction arTransaction) {
		this.arTransaction = arTransaction;
	}

	public Timestamp getInvoiceDueDate() {
		return invoiceDueDate;
	}

	public void setInvoiceDueDate(Timestamp invoiceDueDate) {
		this.invoiceDueDate = invoiceDueDate;
	}

	public String getInvoiceNum() {
		return invoiceNum;
	}

	public void setInvoiceNum(String invoiceNum) {
		this.invoiceNum = invoiceNum;
	}

	public String getPaymentStatusString() {
		return paymentStatusString;
	}
	public void setPaymentStatusString(String paymentStatusString) {
		this.paymentStatusString = paymentStatusString;
	}

	public String getSalesUsername() {
		return salesUsername;
	}
	public void setSalesUsername(String salesUsername) {
		this.salesUsername = salesUsername;
	}

	public double getCommissionAmount() {
		return commissionAmount;
	}

	public void setCommissionAmount(double commissionAmount) {
		this.commissionAmount = commissionAmount;
	}
	
	public static Comparator CustomerComparator = new Comparator() {
		public int compare(Object arg0, Object arg1) {
			long cus1 = ((Invoice) arg0).getCustomerId();
			long cus2 = ((Invoice) arg1).getCustomerId();
			
			if(cus1>cus2)
				return 1;
			else if(cus1<cus2)
				return -1;
			else return 0;
		
		}
	};

	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}

	public boolean isCancelled() {
				return cancelled;
			}
		
			public void setCancelled(boolean cancelled) {
				this.cancelled = cancelled;
			}
}
