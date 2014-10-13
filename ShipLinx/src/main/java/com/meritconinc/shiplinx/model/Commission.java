package com.meritconinc.shiplinx.model;

import java.sql.Timestamp;
import java.util.Date;

public class Commission {
		private Long customerId;
		private Long userId;
		private String invoiceNum;
		private Long invoiceId;
		private double invoiceTotal;
		private double costTotal;
		private double commissionPayable;
		private int customerPaid;
		private int repPaid;
		private String notes;
		private String salesUser;
		private Timestamp dateCreated;
		private String customerName;
		private Date fromDate;
		private Date toDate;
		private String fromDate_web;
		private String toDate_web;
		private double totalSPD;
		private double totalLTL;
		private double totalCHB;
		public double getTotalSPD() {
			return totalSPD;
		}
		public void setTotalSPD(double totalSPD) {
			this.totalSPD = totalSPD;
		}
		public double getTotalLTL() {
			return totalLTL;
		}
		public void setTotalLTL(double totalLTL) {
			this.totalLTL = totalLTL;
		}
		public double getTotalCHB() {
			return totalCHB;
		}
		public void setTotalCHB(double totalCHB) {
			this.totalCHB = totalCHB;
		}
		public Long getCustomerId() {
			return customerId;
		}
		public void setCustomerId(Long customerId) {
			this.customerId = customerId;
		}
		public Long getUserId() {
			return userId;
		}
		public void setUserId(Long userId) {
			this.userId = userId;
		}
		public String getInvoiceNum() {
			return invoiceNum;
		}
		public void setInvoiceNum(String invoiceNum) {
			this.invoiceNum = invoiceNum;
		}
		public Long getInvoiceId() {
			return invoiceId;
		}
		public void setInvoiceId(Long invoiceId) {
			this.invoiceId = invoiceId;
		}
		public double getInvoiceTotal() {
			return invoiceTotal;
		}
		public void setInvoiceTotal(double invoiceTotal) {
			this.invoiceTotal = invoiceTotal;
		}
		public double getCostTotal() {
			return costTotal;
		}
		public void setCostTotal(double costTotal) {
			this.costTotal = costTotal;
		}
		public double getCommissionPayable() {
			return commissionPayable;
		}
		public void setCommissionPayable(double commissionPayable) {
			this.commissionPayable = commissionPayable;
		}
		public int getCustomerPaid() {
			return customerPaid;
		}
		public void setCustomerPaid(int customerPaid) {
			this.customerPaid = customerPaid;
		}
		public int getRepPaid() {
			return repPaid;
		}
		public void setRepPaid(int repPaid) {
			this.repPaid = repPaid;
		}
		public String getNotes() {
			return notes;
		}
		public void setNotes(String notes) {
			this.notes = notes;
		}
		public String getSalesUser() {
			return salesUser;
		}
		public void setSalesUser(String salesUser) {
			this.salesUser = salesUser;
		}
		public Timestamp getDateCreated() {
			return dateCreated;
		}
		public void setDateCreated(Timestamp dateCreated) {
			this.dateCreated = dateCreated;
		}
		public String getCustomerName() {
			return customerName;
		}
		public void setCustomerName(String customerName) {
			this.customerName = customerName;
		}
		public Date getFromDate() {
			return fromDate;
		}
		public void setFromDate(Date fromDate) {
			this.fromDate = fromDate;
		}
		public Date getToDate() {
			return toDate;
		}
		public void setToDate(Date toDate) {
			this.toDate = toDate;
		}
		public String getFromDate_web() {
			return fromDate_web;
		}
		public void setFromDate_web(String fromDate_web) {
			this.fromDate_web = fromDate_web;
		}
		public String getToDate_web() {
			return toDate_web;
		}
		public void setToDate_web(String toDate_web) {
			this.toDate_web = toDate_web;
		}
		
}
