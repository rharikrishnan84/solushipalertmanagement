package com.meritconinc.shiplinx.service.impl;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.meritconinc.shiplinx.dao.PickupDAO;
import com.meritconinc.shiplinx.model.Pickup;
import com.meritconinc.shiplinx.model.RecordList;
import com.meritconinc.shiplinx.service.PickupManager;
import com.meritconinc.shiplinx.model.Address;

public class PickupManagerImpl implements PickupManager{ 
	
	private static final Logger log = LogManager.getLogger(PickupManagerImpl.class);
	PickupDAO pickupDAO;
	/**
	 * @param pickupDAO the pickupDAO to set
	 */
	public void setPickupDAO(PickupDAO pickupDAO) {
		this.pickupDAO = pickupDAO;
	}

	public Long add(Pickup pickup){
		return pickupDAO.add(pickup);	
	}
	
	public List<Pickup> findAddresses(Pickup pickup){
		return pickupDAO.findAddresses(pickup);
	}

	public Pickup getPickupById(long pickupId){
		return pickupDAO.getPickupById(pickupId);
	}
	
	public void updatePickup(Pickup pickup){
		pickupDAO.updatePickup(pickup);
	}
	
	public Pickup getPickupByOrderId(long orderId){
		return pickupDAO.getPickupByOrderId(orderId);
	}
	
	public List<Pickup> getPickups(Pickup pickup){
		return pickupDAO.getPickups(pickup);
	}
	
	public List<RecordList> getPickupList(){
		return pickupDAO.getPickupList();
	}
	
	@Override
		  public List<RecordList> getPickupList(Address fromAddress, Pickup pickUp, String carrierId) {
		    return pickupDAO.getPickupList(fromAddress, pickUp, carrierId);
		  }
}