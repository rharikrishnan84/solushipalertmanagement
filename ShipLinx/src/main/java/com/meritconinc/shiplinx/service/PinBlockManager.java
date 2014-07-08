package com.meritconinc.shiplinx.service;


public interface PinBlockManager {
	
	long[] getNewPinNumbers(String type, int count, long businessId);
	public String getPropertyValue(String propertyName);
	String[] getNewPrefixedPinNumbers(String type, int count, long businessId);

}
 