package com.meritconinc.shiplinx.model;

import java.io.Serializable;

import com.meritconinc.shiplinx.utils.ShiplinxConstants;

public class CarrierChargeCode implements Serializable {
	private Long id;
	private long carrierId;
	private String chargeCode;
	private String chargeCodeLevel2;
	private String chargeName;
	private String chargeDesc;
	private long groupId;
	private String groupCode;
	private String groupName;
	private String groupDesc;
	private boolean tax; 
	private Long taxRate;
	private int displayOrder;
	private String carrierName;
	
	private Double carrierCharge;
	private Double carrierCost;
	
	private ChargeGroup chargeGroup;
	private long customerId;
	
	public long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}

	public CarrierChargeCode() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public String getGroupCode() {
		return groupCode;
	}

	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getGroupDesc() {
		return groupDesc;
	}

	public void setGroupDesc(String groupDesc) {
		this.groupDesc = groupDesc;
	}

	public boolean isTax() {
		return tax;
	}

	public void setTax(boolean isTax) {
		this.tax = isTax;
	}

	public Long getTaxRate() {
		return taxRate;
	}

	public void setTaxRate(Long taxRate) {
		this.taxRate = taxRate;
	}

	public long getCarrierId() {
		return carrierId;
	}

	public void setCarrierId(long carrierId) {
		this.carrierId = carrierId;
		setCarrierName(ShiplinxConstants.getCarrierName(new Long(carrierId).intValue()));
	}

	public String getChargeCode() {
		return chargeCode;
	}

	public void setChargeCode(String chargeCode) {
		this.chargeCode = chargeCode;
	}

	public String getChargeCodeLevel2() {
		return chargeCodeLevel2;
	}

	public void setChargeCodeLevel2(String chargeCodeLevel2) {
		this.chargeCodeLevel2 = chargeCodeLevel2;
	}

	public String getChargeName() {
		return chargeName;
	}

	public void setChargeName(String chargeName) {
		this.chargeName = chargeName;
	}

	public String getChargeDesc() {
		return chargeDesc;
	}

	public void setChargeDesc(String chargeDesc) {
		this.chargeDesc = chargeDesc;
	}

	public long getGroupId() {
		return groupId;
	}

	public void setGroupId(long groupId) {
		this.groupId = groupId;
	}
	
	public String toString(){
		return this.getId() + " : " + this.getChargeName();
	}

	public int getDisplayOrder() {
		return displayOrder;
	}

	public void setDisplayOrder(int displayOrder) {
		this.displayOrder = displayOrder;
	}

	public Double getCarrierCharge() {
		return carrierCharge;
	}

	public void setCarrierCharge(Double carrierCharge) {
		this.carrierCharge = carrierCharge;
	}

	public Double getCarrierCost() {
		return carrierCost;
	}

	public void setCarrierCost(Double carrierCost) {
		this.carrierCost = carrierCost;
	}

	public ChargeGroup getChargeGroup() {
		return chargeGroup;
	}

	public void setChargeGroup(ChargeGroup chargeGroup) {
		this.chargeGroup = chargeGroup;
	}

	public String getCarrierName() {
		return carrierName;
	}

	public void setCarrierName(String carrierName) {
		this.carrierName = carrierName;
	}

	public String getIsTaxable() {
		return isTaxable;
	}

	public void setIsTaxable(String isTaxable) {
		this.isTaxable = isTaxable;
	}

	private String isTaxable;

	
}
