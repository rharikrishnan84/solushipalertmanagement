package com.meritconinc.shiplinx.model;

import java.io.Serializable;
import java.util.Date;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;



public class OrderStatus implements Serializable{
	
	private static final Logger log = LogManager.getLogger(Address.class);
	private Long id;
	private String name;
	public OrderStatus() {
		//country="CA";
	}
	public OrderStatus(long i, String n) {
		id = i;
		name = n;
	}
	/**
	 * @return Returns the city.
	 */
	public Long getId() {
		return id;
	} 
	/**
	 * @param city The city to set.
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * @return Returns the consigneeId.
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param consigneeId The consigneeId to set.
	 */
	public void setName(String name) {
		this.name = name;
	}
	}
