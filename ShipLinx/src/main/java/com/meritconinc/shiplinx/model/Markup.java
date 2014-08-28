package com.meritconinc.shiplinx.model;

import com.meritconinc.shiplinx.utils.ShiplinxConstants;

public class Markup {
	
	private Long customerId;
	private Long carrierId;
	private Long serviceId;
	private String serviceName;
	private Long businessId;
	private String fromCountryCode;
	private String toCountryCode;
	private Integer markupPercentage;
	private Double markupFlat;
	private String customerBusName;
	private String fromCountryName;
	private String toCountryName;
	private Integer disabled;
	private Integer type;
	private Double fromWeight;
	private Double toWeight;
	private String carrierName;
	private Long sourceCustomerId;
//	private String typeText;
	private int serviceType;
	
	private int variable;
	private int rate;
	public int getRate() {
		return rate;
	}
	public void setRate(int rate) {
		this.rate = rate;
	}
	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	public Long getServiceId() {
		return serviceId;
	}
	public void setServiceId(Long serviceId) {
		this.serviceId = serviceId;
	}
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public Long getBusinessId() {
		return businessId;
	}
	public void setBusinessId(Long businessId) {
		this.businessId = businessId;
	}
	public String getFromCountryCode() {
		return fromCountryCode;
	}
	public void setFromCountryCode(String fromCountryCode) {
		this.fromCountryCode = fromCountryCode;
	}
	public String getToCountryCode() {
		return toCountryCode;
	}
	public void setToCountryCode(String toCountryCode) {
		this.toCountryCode = toCountryCode;
	}
	public Integer getMarkupPercentage() {
		return markupPercentage;
	}
	public void setMarkupPercentage(Integer markupPercentage) {
		this.markupPercentage = markupPercentage;
	}
	public Double getMarkupFlat() {
		return markupFlat;
	}
	public void setMarkupFlat(Double markupFlat) {
		this.markupFlat = markupFlat;
	}
	public String getCustomerBusName() {
		return customerBusName;
	}
	public void setCustomerBusName(String customerBusName) {
		this.customerBusName = customerBusName;
	}
	public String getFromCountryName() {
		return fromCountryName;
	}
	public void setFromCountryName(String fromCountryName) {
		this.fromCountryName = fromCountryName;
	}
	public String getToCountryName() {
		return toCountryName;
	}
	public void setToCountryName(String toCountryName) {
		this.toCountryName = toCountryName;
	}
	public Integer getDisabled() {
		return disabled;
	}
	public void setDisabled(Integer disabled) {
		this.disabled = disabled;
	}
		
	public String getCarrierName() {
		return carrierName;
	}
	public void setCarrierName(String carrierName) {
		this.carrierName = carrierName;
	}
	public Boolean isDisabledFlag() {
		if (disabled > 0)
			return true;
		return false;
	}
	public Boolean getDisabledFlag() {
		return this.isDisabledFlag();
	}
	public void setDisabledFlag(Boolean disabledFlag) {
		if (disabledFlag)
			this.disabled = 1;
		else
			this.disabled = 0;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	
	public String getTypeText() {
		if (this.type == null || this.type.intValue() == 0)
			return "";
		if (this.type.intValue() == ShiplinxConstants.TYPE_MARKDOWN)
			return ShiplinxConstants.TYPE_MARKDOWN_TEXT;
		if (this.type.intValue() == ShiplinxConstants.TYPE_MARKUP)
			return ShiplinxConstants.TYPE_MARKUP_TEXT;
		
		return "";
	}
	public void setTypeText(String typeText) {
		if (typeText == null || typeText.isEmpty())
			this.type = null;
		else if (typeText.equalsIgnoreCase(ShiplinxConstants.TYPE_MARKDOWN_TEXT))
			this.type = ShiplinxConstants.TYPE_MARKDOWN;
		else if (typeText.equalsIgnoreCase(ShiplinxConstants.TYPE_MARKUP_TEXT))
			this.type = ShiplinxConstants.TYPE_MARKUP;
		else
			this.type = 0;
	}
	public Double getFromWeight() {
		return fromWeight;
	}
	public void setFromWeight(Double fromWeight) {
		this.fromWeight = fromWeight;
	}
	public Double getToWeight() {
		return toWeight;
	}
	public void setToWeight(Double toWeight) {
		this.toWeight = toWeight;
	}
	
	public Long getCarrierId() {
		return carrierId;
	}
	public void setCarrierId(Long carrierId) {
		this.carrierId = carrierId;
	}
	public Long getSourceCustomerId() {
		return sourceCustomerId;
	}
	public void setSourceCustomerId(Long sourceCustomerId) {
		this.sourceCustomerId = sourceCustomerId;
	}
	public int getServiceType() {
		return serviceType;
	}
	public void setServiceType(int serviceType) {
		this.serviceType = serviceType;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer();
		sb.append(getKeyValue("Type", getType()));
		sb.append(getKeyValue("CustomerID", getCustomerId()));
		sb.append(getKeyValue("ServiceID", getServiceId()));
		sb.append(getKeyValue("From Country", getFromCountryCode()));
		sb.append(getKeyValue("To Country", getToCountryCode()));
		sb.append(getKeyValue("From Weight", getFromWeight()));
		sb.append(getKeyValue("To Weight", getToWeight()));
		return sb.toString();
	}
	private String getKeyValue(String name, Object value) {
		// TODO Auto-generated method stub
		return "[" + name + ":" + value + "] ";
	}
	
	public int getVariable() {
				return variable;
			}
			public void setVariable(int variable) {
				this.variable = variable;
			}
}
