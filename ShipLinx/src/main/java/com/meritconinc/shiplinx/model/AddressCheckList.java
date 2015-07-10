package com.meritconinc.shiplinx.model;

public class AddressCheckList {
	private long id;
	private long addressId;
	private boolean commercialBusiness;
	private String description;
	private boolean pickupOrDeliver;
	private boolean priorToPickup;
	private boolean dockLevel;
	private boolean palletJack;
	private boolean powerTailgate;
    private boolean insidePickup;
	private long floorNo;
	private boolean CheckListActivated;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getAddressId() {
		return addressId;
	}
	public void setAddressId(long addressId) {
		this.addressId = addressId;
	}
	public boolean isCommercialBusiness() {
		return commercialBusiness;
	}
	public void setCommercialBusiness(boolean commercialBusiness) {
		this.commercialBusiness = commercialBusiness;
	}
	
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public boolean isPickupOrDeliver() {
		return pickupOrDeliver;
	}
	public void setPickupOrDeliver(boolean pickupOrDeliver) {
		this.pickupOrDeliver = pickupOrDeliver;
	}
	public boolean isPriorToPickup() {
		return priorToPickup;
	}
	public void setPriorToPickup(boolean priorToPickup) {
		this.priorToPickup = priorToPickup;
	}
	public boolean isDockLevel() {
		return dockLevel;
	}
	public void setDockLevel(boolean dockLevel) {
		this.dockLevel = dockLevel;
	}
	public boolean isPalletJack() {
		return palletJack;
	}
	public void setPalletJack(boolean palletJack) {
		this.palletJack = palletJack;
	}
	public boolean isPowerTailgate() {
		return powerTailgate;
	}
	public void setPowerTailgate(boolean powerTailgate) {
		this.powerTailgate = powerTailgate;
	}
	public boolean isInsidePickup() {
		return insidePickup;
	}
	public void setInsidePickup(boolean insidePickup) {
		this.insidePickup = insidePickup;
	}
	public long getFloorNo() {
		return floorNo;
	}
	public void setFloorNo(long floorNo) {
		this.floorNo = floorNo;
	}
	public boolean isCheckListActivated() {
		return CheckListActivated;
	}
	public void setCheckListActivated(boolean checkListActivated) {
		CheckListActivated = checkListActivated;
	}
	
	
	

}
