package com.meritconinc.shiplinx.model;


public class PinNumberBlock {
	private Long id;
	
	private String type;
	private long fromPin;
	private long toPin;
	private long nextPin;
	private String prefix;
	
	private long businessId;
	
	/**
	 * @return Returns the fromPin.
	 */
	public long getFromPin() {
		return fromPin;
	}
	/**
	 * @param fromPin The fromPin to set.
	 */
	public void setFromPin(long fromPin) {
		this.fromPin = fromPin;
	} 
	/**
	 * @return Returns the id.
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @param id The id to set.
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * @return Returns the nextPin.
	 */
	public long getNextPin() {
		return nextPin;
	}
	/**
	 * @param nextPin The nextPin to set.
	 */
	public void setNextPin(long nextPin) {
		this.nextPin = nextPin;
	}
	/**
	 * @return Returns the toPin.
	 */
	public long getToPin() {
		return toPin;
	}
	/**
	 * @param toPin The toPin to set.
	 */
	public void setToPin(long toPin) {
		this.toPin = toPin;
	}
	/**
	 * @return Returns the type.
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type The type to set.
	 */
	public void setType(String type) {
		this.type = type;
	}

	public long getBusinessId() {
		return businessId;
	}
	public void setBusinessId(long businessId) {
		this.businessId = businessId;
	}
	public String getPrefix() {
		return prefix;
	}
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
	
	

}
