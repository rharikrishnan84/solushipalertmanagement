package com.meritconinc.shiplinx.ccprocessing;

import java.util.Date;

public class CCResponseObject {

	private String orderId;
	private double amount;
	private String authNumber;
	private String referenceNumber;
	private String responseCode;
	private String responseString;
	private String processorTranId;
	private Date dateAuthorized = null;
	private int result;
	private String procMessage;
	private String cvdResponse = null;
	 
	/**
	 * @return Returns the procMessage.
	 */
	public String getProcMessage() {
		return procMessage;
	}
	/**
	 * @param procMessage The procMessage to set.
	 */
	public void setProcMessage(String procMessage) {
		this.procMessage = procMessage;
	}
	/**
	 * @return Returns the result.
	 */
	public int getResult() {
		return result;
	}
	/**
	 * @param result The result to set.
	 */
	public void setResult(int result) {
		this.result = result;
	}
	/**
	 * @return Returns the processorTranId.
	 */
	public String getProcessorTranId() {
		return processorTranId;
	}
	/**
	 * @param processorTranId The processorTranId to set.
	 */
	public void setProcessorTranId(String processorTranId) {
		this.processorTranId = processorTranId;
	}
	/**
	 * @return Returns the responseCode.
	 */
	public String getResponseCode() {
		return responseCode;
	}
	/**
	 * @param responseCode The responseCode to set.
	 */
	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}
	/**
	 * @return Returns the responseString.
	 */
	public String getResponseString() {
		return responseString;
	}
	/**
	 * @param responseString The responseString to set.
	 */
	public void setResponseString(String responseString) {
		this.responseString = responseString;
	}
	/**
	 * @return Returns the referenceNumber.
	 */
	public String getReferenceNumber() {
		return referenceNumber;
	}
	/**
	 * @param referenceNumber The referenceNumber to set.
	 */
	public void setReferenceNumber(String referenceNumber) {
		this.referenceNumber = referenceNumber;
	}
	/**
	 * @return Returns the amount.
	 */
	public double getAmount() {
		return amount;
	}
	/**
	 * @param amount The amount to set.
	 */
	public void setAmount(double amount) {
		this.amount = amount;
	}
	/**
	 * @return Returns the authNumber.
	 */
	public String getAuthNumber() {
		return authNumber;
	}
	/**
	 * @param authNumber The authNumber to set.
	 */
	public void setAuthNumber(String authNumber) {
		this.authNumber = authNumber;
	}
	/**
	 * @return Returns the dateAuthorized.
	 */
	public Date getDateAuthorized() {
		return dateAuthorized;
	}
	/**
	 * @param dateAuthorized The dateAuthorized to set.
	 */
	public void setDateAuthorized(Date dateAuthorized) {
		this.dateAuthorized = dateAuthorized;
	}
	/**
	 * @return Returns the orderId.
	 */
	public String getOrderId() {
		return orderId;
	}
	/**
	 * @param orderId The orderId to set.
	 */
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getCvdResponse() {
		return cvdResponse;
	}
	public void setCvdResponse(String cvdResponse) {
		this.cvdResponse = cvdResponse;
	}
	
	
}
