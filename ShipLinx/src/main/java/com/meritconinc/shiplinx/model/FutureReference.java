package com.meritconinc.shiplinx.model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FutureReference {

	private Timestamp dateCreated = new Timestamp(new Date().getTime());

	private String createdBy;
	private Long customerId;
	private Long futureReferenceId;
	private String name;
	private String fromCity;
	private String fromState;
	private String fromCountry;
	private String toCity;
	private String toState;
	private String toCountry;
	private String shipFromAddress;
	private String shipToAddress;
	private Integer quantity;
	private String serviceType;
	private String shipScheduleDate;
	private String shipFromEmail;
	private String shipToEmail;
	private String shipFromPhone;
	private String shipToPhone;
	private String fromCompany;
	private String toCompany;
	private boolean fromResidential;
	private boolean toresidential;
	
	private String readyTime;
	private String closeTime;
	private String pickupInstruction;
	private String pickupLocation;
	
	private String fromContactName;
	
	private String toContactName;
	private String fromPostalCode;
	private String toPostalCode;
	
	private boolean documentsOnly;
	private boolean appointmentPickup;
	private boolean tradeShowPickup;
	private boolean taligateDelivery;
	
	private boolean tradeShowDelivery;
	private boolean appointmentDelivery;
	private boolean insidePickup;
	private boolean taligatePickup;
	
	private String dangerousGoods;
	private String refCode;
	private boolean holdForPickup;
	
	private boolean saturdayDelivery;
	private String signatureRequired;
	private String dutiableCode;
	
	private boolean notifyShipper;
	private boolean notifyConsignee;
	
	private long businessId;
	private List<Long> businessIds;
	
	
	
	
	
	
	

	private List<Package> packageList=new ArrayList<Package>();

	public long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public String getToCountry() {
		return toCountry;
	}

	public void setToCountry(String toCountry) {
		this.toCountry = toCountry;
	}

	public String getFromState() {
		return fromState;
	}

	public void setFromState(String fromState) {
		this.fromState = fromState;
	}

	public String getFromCountry() {
		return fromCountry;
	}

	public void setFromCountry(String fromCountry) {
		this.fromCountry = fromCountry;
	}

	public String getToCity() {
		return toCity;
	}

	public void setToCity(String toCity) {
		this.toCity = toCity;
	}

	public String getToState() {
		return toState;
	}

	public void setToState(String toState) {
		this.toState = toState;
	}

	public String getFromCity() {
		return fromCity;
	}

	public void setFromCity(String fromCity) {
		this.fromCity = fromCity;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Timestamp getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Timestamp date) {
		this.dateCreated = (Timestamp) date;
	}

	

	public Long getFutureReferenceId() {
		return futureReferenceId;
	}

	public void setFutureReferenceId(Long futureReferenceId) {
		this.futureReferenceId = futureReferenceId;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getShipFromAddress() {
		return shipFromAddress;
	}

	public void setShipFromAddress(String shipFromAddress) {
		this.shipFromAddress = shipFromAddress;
	}

	public String getShipToAddress() {
		return shipToAddress;
	}

	public void setShipToAddress(String shipToAddress) {
		this.shipToAddress = shipToAddress;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getServiceType() {
		return serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

	public String getShipScheduleDate() {
		return shipScheduleDate;
	}

	public void setShipScheduleDate(String shipScheduleDate) {
		this.shipScheduleDate = shipScheduleDate;
	}

	public String getShipFromEmail() {
		return shipFromEmail;
	}

	public void setShipFromEmail(String shipFromEmail) {
		this.shipFromEmail = shipFromEmail;
	}

	public String getShipToEmail() {
		return shipToEmail;
	}

	public void setShipToEmail(String shipToEmail) {
		this.shipToEmail = shipToEmail;
	}

	public String getShipFromPhone() {
		return shipFromPhone;
	}

	public void setShipFromPhone(String shipFromPhone) {
		this.shipFromPhone = shipFromPhone;
	}

	public String getShipToPhone() {
		return shipToPhone;
	}

	public void setShipToPhone(String shipToPhone) {
		this.shipToPhone = shipToPhone;
	}

	public String getFromCompany() {
		return fromCompany;
	}

	public void setFromCompany(String fromCompany) {
		this.fromCompany = fromCompany;
	}

	public String getToCompany() {
		return toCompany;
	}

	public void setToCompany(String toCompany) {
		this.toCompany = toCompany;
	}

	public List<Package> getPackageList() {
		return packageList;
	}

	public void setPackageList(List<Package> packageList) {
		this.packageList = packageList;
	}

	public boolean isToresidential() {
		return toresidential;
	}

	public void setToresidential(boolean toresidential) {
		this.toresidential = toresidential;
	}

	public boolean isFromResidential() {
		return fromResidential;
	}

	public void setFromResidential(boolean fromResidential) {
		this.fromResidential = fromResidential;
	}

	public String getReadyTime() {
		return readyTime;
	}

	public void setReadyTime(String readyTime) {
		this.readyTime = readyTime;
	}

	public String getCloseTime() {
		return closeTime;
	}

	public void setCloseTime(String closeTime) {
		this.closeTime = closeTime;
	}

	public String getPickupInstruction() {
		return pickupInstruction;
	}

	public void setPickupInstruction(String pickupInstruction) {
		this.pickupInstruction = pickupInstruction;
	}

	public String getPickupLocation() {
		return pickupLocation;
	}

	public void setPickupLocation(String pickupLocation) {
		this.pickupLocation = pickupLocation;
	}

	public String getFromContactName() {
		return fromContactName;
	}

	public void setFromContactName(String fromContactName) {
		this.fromContactName = fromContactName;
	}

	public String getToPostalCode() {
		return toPostalCode;
	}

	public void setToPostalCode(String toPostalCode) {
		this.toPostalCode = toPostalCode;
	}

	public String getFromPostalCode() {
		return fromPostalCode;
	}

	public void setFromPostalCode(String fromPostalCode) {
		this.fromPostalCode = fromPostalCode;
	}

	public String getToContactName() {
		return toContactName;
	}

	public void setToContactName(String toContactName) {
		this.toContactName = toContactName;
	}

	public boolean isDocumentsOnly() {
		return documentsOnly;
	}

	public void setDocumentsOnly(boolean documentsOnly) {
		this.documentsOnly = documentsOnly;
	}

	public boolean isAppointmentPickup() {
		return appointmentPickup;
	}

	public void setAppointmentPickup(boolean appointmentPickup) {
		this.appointmentPickup = appointmentPickup;
	}

	public boolean isTradeShowPickup() {
		return tradeShowPickup;
	}

	public void setTradeShowPickup(boolean tradeShowPickup) {
		this.tradeShowPickup = tradeShowPickup;
	}

	public boolean isTaligateDelivery() {
		return taligateDelivery;
	}

	public void setTaligateDelivery(boolean taligateDelivery) {
		this.taligateDelivery = taligateDelivery;
	}

	public boolean isTradeShowDelivery() {
		return tradeShowDelivery;
	}

	public void setTradeShowDelivery(boolean tradeShowDelivery) {
		this.tradeShowDelivery = tradeShowDelivery;
	}

	public boolean isAppointmentDelivery() {
		return appointmentDelivery;
	}

	public void setAppointmentDelivery(boolean appointmentDelivery) {
		this.appointmentDelivery = appointmentDelivery;
	}

	public boolean isInsidePickup() {
		return insidePickup;
	}

	public void setInsidePickup(boolean insidePickup) {
		this.insidePickup = insidePickup;
	}

	public boolean isTaligatePickup() {
		return taligatePickup;
	}

	public void setTaligatePickup(boolean taligatePickup) {
		this.taligatePickup = taligatePickup;
	}

	public String getDangerousGoods() {
		return dangerousGoods;
	}

	public void setDangerousGoods(String dangerousGoods) {
		this.dangerousGoods = dangerousGoods;
	}

	public String getRefCode() {
		return refCode;
	}

	public void setRefCode(String refCode) {
		this.refCode = refCode;
	}

	public boolean isHoldForPickup() {
		return holdForPickup;
	}

	public void setHoldForPickup(boolean holdForPickup) {
		this.holdForPickup = holdForPickup;
	}

	public boolean isSaturdayDelivery() {
		return saturdayDelivery;
	}

	public void setSaturdayDelivery(boolean saturdayDelivery) {
		this.saturdayDelivery = saturdayDelivery;
	}

	public String getSignatureRequired() {
		return signatureRequired;
	}

	public void setSignatureRequired(String signatureRequired) {
		this.signatureRequired = signatureRequired;
	}

	public String getDutiableCode() {
		return dutiableCode;
	}

	public void setDutiableCode(String dutiableCode) {
		this.dutiableCode = dutiableCode;
	}

	public boolean isNotifyShipper() {
		return notifyShipper;
	}

	public void setNotifyShipper(boolean notifyShipper) {
		this.notifyShipper = notifyShipper;
	}

	public boolean isNotifyConsignee() {
		return notifyConsignee;
	}

	public void setNotifyConsignee(boolean notifyConsignee) {
		this.notifyConsignee = notifyConsignee;
	}

	public long getBusinessId() {
		return businessId;
	}

	public void setBusinessId(long businessId) {
		this.businessId = businessId;
	}

	public List<Long> getBusinessIds() {
		return businessIds;
	}

	public void setBusinessIds(List<Long> businessIds) {
		this.businessIds = businessIds;
	}

	

	

}
