package com.meritconinc.shiplinx.carrier.dhl.model;

import java.math.BigDecimal;

public class DhlShipValidatePiece {
	private Long id;
	private Long shipValResponseId;
	private Long number;
	private BigDecimal weight;
	private String pieceIdentifier;
	private String licensePlate;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getShipValResponseId() {
		return shipValResponseId;
	}
	public void setShipValResponseId(Long shipValResponseId) {
		this.shipValResponseId = shipValResponseId;
	} 
	public Long getNumber() {
		return number;
	}
	public void setNumber(Long number) {
		this.number = number;
	}
	public BigDecimal getWeight() {
		return weight;
	}
	public void setWeight(BigDecimal weight) {
		this.weight = weight;
	}
	public String getPieceIdentifier() {
		return pieceIdentifier;
	}
	public void setPieceIdentifier(String pieceIdentifier) {
		this.pieceIdentifier = pieceIdentifier;
	}
	public String getLicensePlate() {
		return licensePlate;
	}
	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
	}
	
	
}
