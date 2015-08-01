package com.meritconinc.shiplinx.model;

import com.meritconinc.shiplinx.utils.ShiplinxConstants;
import java.util.List;

public class BusinessMarkup {
	private Long customerId;
	private Long carrierId;
	private Long serviceId;
	private String serviceName;
	private Long businessId;
	private Long businessToId;
	private String fromCountryCode;
	private String toCountryCode;
	public String getFromCountryProvince() {
		return fromCountryProvince;
	}

	public void setFromCountryProvince(String fromCountryProvince) {
		this.fromCountryProvince = fromCountryProvince;
	}

	public String getToCountryProvince() {
		return toCountryProvince;
	}

	public void setToCountryProvince(String toCountryProvince) {
		this.toCountryProvince = toCountryProvince;
	}

	public Double getFromCost() {
		return fromCost;
	}

	public void setFromCost(Double fromCost) {
		this.fromCost = fromCost;
	}

	public Double getToCost() {
		return toCost;
	}

	public void setToCost(Double toCost) {
		this.toCost = toCost;
	}

	private String fromCountryProvince;
	private String toCountryProvince;
	private Integer markupPercentage;
	private Double markupFlat;
	private String customerBusName;
	private String fromCountryName;
	private String toCountryName;
	private Integer disabled;
	private Integer type;
	private Double fromCost;
	private Double toCost;
	private String carrierName;
	private Long sourceCustomerId;
	// private String typeText;
	private int serviceType;
	private List<Long> customerIds;
	private List<Long> businessIds;

	private List<String> fromCountries;
	private List<String> toCountries;
	private int variable;

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
		else if (typeText
				.equalsIgnoreCase(ShiplinxConstants.TYPE_MARKDOWN_TEXT))
			this.type = ShiplinxConstants.TYPE_MARKDOWN;
		else if (typeText.equalsIgnoreCase(ShiplinxConstants.TYPE_MARKUP_TEXT))
			this.type = ShiplinxConstants.TYPE_MARKUP;
		else
			this.type = 0;
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
		sb.append(getKeyValue("From Cost", getFromCost()));
		sb.append(getKeyValue("To Cost", getToCost()));
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

	public List<Long> getBusinessIds() {
		return businessIds;
	}

	public void setBusinessIds(List<Long> businessIds) {
		this.businessIds = businessIds;
	}

	public List<Long> getCustomerIds() {
		return customerIds;
	}

	public void setCustomerIds(List<Long> customerIds) {
		this.customerIds = customerIds;
	}

	public List<String> getFromCountries() {
		return fromCountries;
	}

	public void setFromCountries(List<String> fromCountries) {
		this.fromCountries = fromCountries;
	}

	public List<String> getToCountries() {
		return toCountries;
	}

	public void setToCountries(List<String> toCountries) {
		this.toCountries = toCountries;
	}

	public Long getBusinessToId() {
		return businessToId;
	}

	public void setBusinessToId(Long businessToId) {
		this.businessToId = businessToId;
	}
}
