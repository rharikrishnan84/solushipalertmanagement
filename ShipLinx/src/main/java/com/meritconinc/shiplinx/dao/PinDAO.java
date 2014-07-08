package com.meritconinc.shiplinx.dao;

import com.meritconinc.shiplinx.model.PinNumberBlock;

public interface PinDAO {

	PinNumberBlock getBlock(String type, long franchise_id);
	void saveBlock(PinNumberBlock pin_block);
	
	public String getPropertyValue(String propertyName);
	
} 
