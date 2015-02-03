package com.meritconinc.shiplinx.model;

import java.util.Date;

import com.meritconinc.mmr.utilities.StringUtil;
import com.meritconinc.shiplinx.utils.ShiplinxConstants;

public class EdiItem implements Comparable<EdiItem>{
	private Long id;
	private Long carrierId;
	private Long businessId;
	private String accountNumber;
	private String invoiceNumber;
	private Date invoiceDate;
	private Date processedDate;
	private Double totInvoiceAmount;
	private Integer detailSeqNumber;
	private String ediFileName;
	private Integer status;
	private String message;
	private Long elapsedTime;
	private Date startTime;
	private String type;
	private String fromDate;
	private String toDate;	
	private String statusText; 
	
	private String accountCountry = "CA"; //hard-coding this for now, but it should be requested at time of upload. Being used only for DHL currently
	
	public static final String [] ediStatusList = {"", ShiplinxConstants.UPLOADED, ShiplinxConstants.INPROGRESS, 
			ShiplinxConstants.PROCESSED, ShiplinxConstants.RELEASED};
	
	public static final int FILE_ALREADY_RELEASED = 1;
	public static final int FILE_NOT_READY_FOR_RELEASE = 2;
	public static final int FILE_RELEASED_SUCCESSFULLY = 3;

	private String exactMatch = "N";
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getCarrierId() {
		return carrierId;
	}
	public void setCarrierId(Long carrierId) {
		this.carrierId = carrierId;
	}
	public Long getBusinessId() {
		return businessId;
	}
	public void setBusinessId(Long businessId) {
		this.businessId = businessId;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getInvoiceNumber() {
		return invoiceNumber;
	}
	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}
	public Date getInvoiceDate() {
		return invoiceDate;
	}
	public void setInvoiceDate(Date invoiceDate) {
		this.invoiceDate = invoiceDate;
	}
	public Date getProcessedDate() {
		return processedDate;
	}
	public void setProcessedDate(Date processedDate) {
		this.processedDate = processedDate;
	}
	public Double getTotInvoiceAmount() {
		return totInvoiceAmount;
	}
	public void setTotInvoiceAmount(Double totInvoiceAmount) {
		this.totInvoiceAmount = totInvoiceAmount;
	}
	public Integer getDetailSeqNumber() {
		return detailSeqNumber;
	}
	public void setDetailSeqNumber(Integer detailSeqNumber) {
		this.detailSeqNumber = detailSeqNumber;
	}
	public String getEdiFileName() {
		return ediFileName;
	}
	public void setEdiFileName(String ediFileName) {
		this.ediFileName = ediFileName;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Long getElapsedTime() {
		return elapsedTime;
	}
	public void setElapsedTime(Long elapsedTime) {
		this.elapsedTime = elapsedTime;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getFromDate() {
		return fromDate;
	}
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	public String getToDate() {
		return toDate;
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	public String getStatusText() {
		return statusText;
	}
	public void setStatusText(String statusText) {
		this.statusText = statusText;
		if (!StringUtil.isEmpty(statusText)) {
			for (int i=1; i<this.ediStatusList.length; i++) {
				String s = this.ediStatusList[i];
				if (statusText.equals(s)) {
					this.status = i;
					break;
				}
			}
		} else {
			this.status = null;
		}
	}
	
	public String getAccountCountry() {
		return accountCountry;
	}
	public void setAccountCountry(String accountCountry) {
		this.accountCountry = accountCountry;
	}
	
	public String getExactMatch() {
		return exactMatch;
	}
	public void setExactMatch(String exactMatch) {
		this.exactMatch = exactMatch;
	}
	@Override
	public int compareTo(EdiItem o) {
		// TODO Auto-generated method stub
		return (int)(o.id - this.id);
	}
	
	
}
