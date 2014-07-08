package com.meritconinc.shiplinx.carrier.ups.model;


public class UPSDASZipsUS  {
	
	private String zipCode;
	private boolean das;
	private boolean dasExtended;
	private boolean remoteHI;
	private boolean remoteAK;
	
	
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public boolean isDas() {
		return das;
	}
	public void setDas(boolean das) {
		this.das = das;
	} 
	public boolean isDasExtended() {
		return dasExtended;
	}
	public void setDasExtended(boolean dasExtended) {
		this.dasExtended = dasExtended;
	}
	public boolean isRemoteHI() {
		return remoteHI;
	}
	public void setRemoteHI(boolean remoteHI) {
		this.remoteHI = remoteHI;
	}
	public boolean isRemoteAK() {
		return remoteAK;
	}
	public void setRemoteAK(boolean remoteAK) {
		this.remoteAK = remoteAK;
	}
}
