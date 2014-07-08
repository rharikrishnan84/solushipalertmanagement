package com.meritconinc.shiplinx.service;

import java.util.List;

import com.meritconinc.shiplinx.model.Pickup;
import com.meritconinc.shiplinx.model.RecordList;
import com.meritconinc.shiplinx.model.Address;

public interface PickupManager{
	
	public Long add(Pickup pickup);	
	List<Pickup> findAddresses(Pickup pickup);
	public Pickup getPickupById(long pickupId);
	public void updatePickup(Pickup pickup);
	public Pickup getPickupByOrderId(long orderId);
	public List<Pickup> getPickups(Pickup pickup);
	public List<RecordList> getPickupList(); 
	public List<RecordList> getPickupList(Address fromAddress, Pickup pickUp, String carrierId);
} 