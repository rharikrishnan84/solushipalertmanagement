package com.meritconinc.shiplinx.dao;

import java.util.List;

import com.meritconinc.shiplinx.model.Address;
import com.meritconinc.shiplinx.model.Pickup;
import com.meritconinc.shiplinx.model.RecordList;

public interface PickupDAO {

  public Long add(Pickup pickup);

  List<Pickup> findAddresses(Pickup pickup);

  public Pickup getPickupById(long pickupId);

  public void updatePickup(Pickup pickup);

  public Pickup getPickupByOrderId(long orderId);

  public List<Pickup> getPickups(Pickup pickup);

  public List<RecordList> getPickupList();

  public List<RecordList> getPickupList(Address fromAddress, Pickup pickUp, String carrierId);

List<RecordList> getPickupList(
		com.cwsi.eshipper.carrier.ups.rate.AddressDocument.Address fromAddress,
		Pickup pickUp, String carrierId);

}