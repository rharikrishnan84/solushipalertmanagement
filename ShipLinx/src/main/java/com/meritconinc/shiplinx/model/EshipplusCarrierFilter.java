package com.meritconinc.shiplinx.model;

public class EshipplusCarrierFilter {
	private Long customerId;
	private Long eshipCarrierId;
	private String eshipCarrierScac;
	private String eshipCarrierName;
	private Integer disabled;
	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	public Long getEshipCarrierId() {
		return eshipCarrierId;
	}
	public void setEshipCarrierId(Long eshipCarrierId) {
		this.eshipCarrierId = eshipCarrierId;
	}
	public String getEshipCarrierScac() {
		return eshipCarrierScac;
	}
	public void setEshipCarrierScac(String eshipCarrierScac) {
		this.eshipCarrierScac = eshipCarrierScac;
	}
	public String getEshipCarrierName() {
		return eshipCarrierName;
	}
	public void setEshipCarrierName(String eshipCarrierName) {
		this.eshipCarrierName = eshipCarrierName;
	}
	public Integer getDisabled() {
		return disabled;
	}
	public void setDisabled(Integer disabled) {
		this.disabled = disabled;
	}
	public Boolean isCarrierDisabledFlag() {
		if (disabled > 0)
			return true;
		return false;
	}
	public Boolean getDisabledFlag() {
		return this.isCarrierDisabledFlag();
	}
	public void setCarrierDisabledFlag(Boolean CarrierDisabledFlag) {
		if (CarrierDisabledFlag)
			this.disabled = 1;
		else
			this.disabled = 0;
	}
	
	
	
}
