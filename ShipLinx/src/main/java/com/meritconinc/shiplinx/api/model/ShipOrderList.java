package com.meritconinc.shiplinx.api.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ShipOrderList implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8475010962885555252L;
	
	private List<ODBCShipOrder> orders = new ArrayList<ODBCShipOrder>();
	private List<ODBCShipPackage> packages= new ArrayList<ODBCShipPackage>();
	public List<ODBCShipOrder> getOrders() {
		return orders;
	}

	public void setOrders(List<ODBCShipOrder> orders) {
		this.orders = orders;
	}

	public List<ODBCShipPackage> getPackages() {
		return packages;
	}

	public void setPackages(List<ODBCShipPackage> packages) {
		this.packages = packages;
	}
	
	

}
