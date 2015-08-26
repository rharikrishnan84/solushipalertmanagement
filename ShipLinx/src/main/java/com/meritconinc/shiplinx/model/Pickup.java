package com.meritconinc.shiplinx.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.meritconinc.mmr.utilities.MessageUtil;
import com.meritconinc.mmr.utilities.StringUtil;
import com.meritconinc.shiplinx.utils.FormattingUtil;
import com.meritconinc.shiplinx.utils.ShiplinxConstants;

public class Pickup implements Serializable {
	
	public Pickup() {
	}

	private long pickupId;
	private long carrierId;
	private long customerId;
	private long businessId;
	private String readyHour = "09";
	private String readyMin = "00";
	private String closeHour = "17";
	private String closeMin = "00";
	private String pickupLocation;
	private String pickupReference;
	private String confirmationNum;
	private long addressId;
	private Address address;
	private Timestamp pickupDate;
	private long serviceId;
	private int quantity = 1; 
	private int oversizeQuantity;
	private String destinationCountryCode;
	private boolean isOverweight = false;
	private String instructions;
	private long packageTypeId;
	private Double totalWeight = 1.0;
	private String weightUnit = ShiplinxConstants.WEIGHT_UNITS_LBS;
	private long orderId;
	private String carrierReference; //used to store carrier specific information if any
	private int status;
	private Customer customer;
	private Carrier carrier;
	
	//web only
	private boolean pickupRequired = false;
	private CustomerCarrier carrierAccount;
	private String serviceCode;
	private String fromDate;
	private String toDate;
	private String pickupDate_web;
	private double totalActualWeight=1.0;
	
	private String masterTrackingNum;
	
	public double getTotalActualWeight() {
		return totalActualWeight;
	}
	public void setTotalActualWeight(double totalActualWeight) {
		this.totalActualWeight = totalActualWeight;
	}
	public long getCarrierId() {
		return carrierId;
	}
	public void setCarrierId(long carrierId) {
		this.carrierId = carrierId;
	}
	public String getReadyHour() {
		return readyHour;
	}
	public void setReadyHour(String readyHour) {
		this.readyHour = readyHour;
	}
	public String getReadyMin() {
		return readyMin;
	}
	public void setReadyMin(String readyMin) {
		this.readyMin = readyMin;
	}
	public String getCloseHour() {
		return closeHour;
	}
	public void setCloseHour(String closeHour) {
		this.closeHour = closeHour;
	}
	public String getCloseMin() {
		return closeMin;
	}
	public void setCloseMin(String closeMin) {
		this.closeMin = closeMin;
	}
	public String getPickupLocation() {
		return pickupLocation;
	}
	public void setPickupLocation(String pickupLocation) {
		this.pickupLocation = pickupLocation;
	}
	public String getPickupReference() {
		return pickupReference;
	}
	public void setPickupReference(String pickupReference) {
		this.pickupReference = pickupReference;
	}
	public String getConfirmationNum() {
		return confirmationNum;
	}
	public void setConfirmationNum(String confirmationNum) {
		this.confirmationNum = confirmationNum;
	}
	public long getAddressId() {
		return addressId;
	}
	public void setAddressId(long addressId) {
		this.addressId = addressId;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public Timestamp getPickupDate() {
		return pickupDate;
	}
	public void setPickupDate(Timestamp pickupDate) {
		this.pickupDate = pickupDate;
	}
	public String getServiceCode() {
		return serviceCode;
	}
	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getDestinationCountryCode() {
		return destinationCountryCode;
	}
	public void setDestinationCountryCode(String destinationCountryCode) {
		this.destinationCountryCode = destinationCountryCode;
	}
	public boolean isOverweight() {
		return isOverweight;
	}
	public void setOverweight(boolean isOverweight) {
		this.isOverweight = isOverweight;
	}
	public String getInstructions() {
		return instructions;
	}
	public void setInstructions(String instructions) {
		this.instructions = instructions;
	}
	public long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}
	public long getBusinessId() {
		return businessId;
	}
	public void setBusinessId(long businessId) {
		this.businessId = businessId;
	}
	public boolean isPickupRequired() {
		return pickupRequired;
	}
	public void setPickupRequired(boolean pickupRequired) {
		this.pickupRequired = pickupRequired;
	}
	public CustomerCarrier getCarrierAccount() {
		return carrierAccount;
	}
	public void setCarrierAccount(CustomerCarrier carrierAccount) {
		this.carrierAccount = carrierAccount;
	}
	public long getPackageTypeId() {
		return packageTypeId;
	}
	public void setPackageTypeId(long packageTypeId) {
		this.packageTypeId = packageTypeId;
	}
	public Double getTotalWeight() {
		return totalWeight;
	}
	public void setTotalWeight(Double totalWeight) {
		this.totalWeight = totalWeight;
	}
	public long getOrderId() {
		return orderId;
	}
	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}
	public long getPickupId() {
		return pickupId;
	}
	public void setPickupId(long pickupId) {
		this.pickupId = pickupId;
	}
	public long getServiceId() {
		return serviceId;
	}
	public void setServiceId(long serviceId) {
		this.serviceId = serviceId;
	}
	public String getCarrierReference() {
		return carrierReference;
	}
	public void setCarrierReference(String carrierReference) {
		this.carrierReference = carrierReference;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
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
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public Carrier getCarrier() {
		return carrier;
	}
	public void setCarrier(Carrier carrier) {
		this.carrier = carrier;
	}
	public String getPickupDate_web() {
		return pickupDate_web;
	}
	public void setPickupDate_web(String pickupDate_web) {
		this.pickupDate_web = pickupDate_web;
	}
	public String getWeightUnit() {
		return weightUnit;
	}
	public void setWeightUnit(String weightUnit) {
		this.weightUnit = weightUnit;
	}
	public int getOversizeQuantity() {
		return oversizeQuantity;
	}
	public void setOversizeQuantity(int oversizeQuantity) {
		this.oversizeQuantity = oversizeQuantity;
	}
	public String getMasterTrackingNum() {
		return masterTrackingNum;
	}
	public void setMasterTrackingNum(String masterTrackingNum) {
		this.masterTrackingNum = masterTrackingNum;
	}
	
	
}