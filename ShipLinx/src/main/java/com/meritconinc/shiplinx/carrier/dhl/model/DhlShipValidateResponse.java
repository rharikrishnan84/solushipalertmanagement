package com.meritconinc.shiplinx.carrier.dhl.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class DhlShipValidateResponse implements Serializable {
	public static final String SEPERATOR = "|";
	private Long id;
	private Long orderId;
	private String airwayBillNumber;
	private String destServiceAreaCode;
	private String productShortName;
	
	private String shipperFederalTaxId;
	private String shipperStateTaxId;
	private String consigneeStateTaxId;
	private String consigneeFederalTaxId;
	
	private String productContentCode;
	private String originServiceAreaCode;
	private String outBoundSortCode;
	private String inBoundSortCode;
	private String destFacilityCode;
	private String internalServiceCode;		// multiple values, seperated by "|"
	private String deliveryTimeCode;
	private String deliveryDateCode;
	private String shipperAccountNumber;
	private String reference; 				// multiple values, seperated by "|"
	private String contentsDescription;
	private String dhlRoutingDataId;
	private String dhlRoutingCode;
	private String weightUnit;
	private String specialServiceType;		// multiple values, seperated by "|"
	private String dutyPaymentType;
	private String dutyAccountNumber;
	private Float insuredAmount;
	private Float dutyDeclaredValue;
	private String dutyDeclaredCurrency;
	private String dutyTermsOfTrade;
	private String billingAccountNumber;
	private BigDecimal chargeableWeight;
	private String globalProductCode;
	
	private List<DhlShipValidatePiece> pieces;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public String getAirwayBillNumber() {
		return airwayBillNumber;
	}

	public void setAirwayBillNumber(String airwayBillNumber) {
		this.airwayBillNumber = airwayBillNumber;
	}

	public String getDestServiceAreaCode() {
		return destServiceAreaCode;
	}

	public void setDestServiceAreaCode(String destServiceAreaCode) {
		this.destServiceAreaCode = destServiceAreaCode;
	}

	public String getProductShortName() {
		return productShortName;
	}

	public void setProductShortName(String productShortName) {
		this.productShortName = productShortName;
	}

	public String getShipperFederalTaxId() {
		return shipperFederalTaxId;
	}

	public void setShipperFederalTaxId(String shipperFederalTaxId) {
		this.shipperFederalTaxId = shipperFederalTaxId;
	}

	public String getShipperStateTaxId() {
		return shipperStateTaxId;
	}

	public void setShipperStateTaxId(String shipperStateTaxId) {
		this.shipperStateTaxId = shipperStateTaxId;
	}

	public String getConsigneeStateTaxId() {
		return consigneeStateTaxId;
	}

	public void setConsigneeStateTaxId(String consigneeStateTaxId) {
		this.consigneeStateTaxId = consigneeStateTaxId;
	}

	public String getConsigneeFederalTaxId() {
		return consigneeFederalTaxId;
	}

	public void setConsigneeFederalTaxId(String consigneeFederalTaxId) {
		this.consigneeFederalTaxId = consigneeFederalTaxId;
	}

	public String getProductContentCode() {
		return productContentCode;
	}

	public void setProductContentCode(String productContentCode) {
		this.productContentCode = productContentCode;
	}

	public String getOriginServiceAreaCode() {
		return originServiceAreaCode;
	}

	public void setOriginServiceAreaCode(String originServiceAreaCode) {
		this.originServiceAreaCode = originServiceAreaCode;
	}

	public String getOutBoundSortCode() {
		return outBoundSortCode;
	}

	public void setOutBoundSortCode(String outBoundSortCode) {
		this.outBoundSortCode = outBoundSortCode;
	}

	public String getInBoundSortCode() {
		return inBoundSortCode;
	}

	public void setInBoundSortCode(String inBoundSortCode) {
		this.inBoundSortCode = inBoundSortCode;
	}

	public String getDestFacilityCode() {
		return destFacilityCode;
	}

	public void setDestFacilityCode(String destFacilityCode) {
		this.destFacilityCode = destFacilityCode;
	}

	public String getInternalServiceCode() {
		return internalServiceCode;
	}

	public void setInternalServiceCode(String internalServiceCode) {
		this.internalServiceCode = internalServiceCode;
	}

	public String getDeliveryTimeCode() {
		return deliveryTimeCode;
	}

	public void setDeliveryTimeCode(String deliveryTimeCode) {
		this.deliveryTimeCode = deliveryTimeCode;
	}

	public String getDeliveryDateCode() {
		return deliveryDateCode;
	}

	public void setDeliveryDateCode(String deliveryDateCode) {
		this.deliveryDateCode = deliveryDateCode;
	}

	public String getShipperAccountNumber() {
		return shipperAccountNumber;
	}

	public void setShipperAccountNumber(String shipperAccountNumber) {
		this.shipperAccountNumber = shipperAccountNumber;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public String getContentsDescription() {
		return contentsDescription;
	}

	public void setContentsDescription(String contentsDescription) {
		this.contentsDescription = contentsDescription;
	}

	public String getDhlRoutingDataId() {
		return dhlRoutingDataId;
	}

	public void setDhlRoutingDataId(String dhlRoutingDataId) {
		this.dhlRoutingDataId = dhlRoutingDataId;
	}

	public String getDhlRoutingCode() {
		return dhlRoutingCode;
	}

	public void setDhlRoutingCode(String dhlRoutingCode) {
		this.dhlRoutingCode = dhlRoutingCode;
	}

	public String getWeightUnit() {
		return weightUnit;
	}

	public void setWeightUnit(String weightUnit) {
		this.weightUnit = weightUnit;
	}

	public String getSpecialServiceType() {
		return specialServiceType;
	}

	public void setSpecialServiceType(String specialServiceType) {
		this.specialServiceType = specialServiceType;
	}

	public String getDutyPaymentType() {
		return dutyPaymentType;
	}

	public void setDutyPaymentType(String dutyPaymentType) {
		this.dutyPaymentType = dutyPaymentType;
	}

	public String getDutyAccountNumber() {
		return dutyAccountNumber;
	}

	public void setDutyAccountNumber(String dutyAccountNumber) {
		this.dutyAccountNumber = dutyAccountNumber;
	}

	public Float getInsuredAmount() {
		return insuredAmount;
	}

	public void setInsuredAmount(Float insuredAmount) {
		this.insuredAmount = insuredAmount;
	}

	public Float getDutyDeclaredValue() {
		return dutyDeclaredValue;
	}

	public void setDutyDeclaredValue(Float dutyDeclaredValue) {
		this.dutyDeclaredValue = dutyDeclaredValue;
	}

	public String getDutyDeclaredCurrency() {
		return dutyDeclaredCurrency;
	}

	public void setDutyDeclaredCurrency(String dutyDeclaredCurrency) {
		this.dutyDeclaredCurrency = dutyDeclaredCurrency;
	}

	public String getDutyTermsOfTrade() {
		return dutyTermsOfTrade;
	}

	public void setDutyTermsOfTrade(String dutyTermsOfTrade) {
		this.dutyTermsOfTrade = dutyTermsOfTrade;
	}

	public String getBillingAccountNumber() {
		return billingAccountNumber;
	}

	public void setBillingAccountNumber(String billingAccountNumber) {
		this.billingAccountNumber = billingAccountNumber;
	}

	public List<DhlShipValidatePiece> getPieces() {
		return pieces;
	}

	public void setPieces(List<DhlShipValidatePiece> pieces) {
		this.pieces = pieces;
	}

	public BigDecimal getChargeableWeight() {
		return chargeableWeight;
	}

	public void setChargeableWeight(BigDecimal chargeableWeight) {
		this.chargeableWeight = chargeableWeight;
	}

	public String getGlobalProductCode() {
		return globalProductCode;
	}

	public void setGlobalProductCode(String globalProductCode) {
		this.globalProductCode = globalProductCode;
	}


}
