package com.meritconinc.shiplinx.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Comparator;

public class LtlPoundRate {
	private Long id;
	private Long businessId;
	private Long customerId;
	private Long serviceId;
	private String fromZone;
	private String toZone;
	private String equipment;
	private String currency;
	private Double fscPercent;
	private Double minimum;
	private Integer rangeFrom;
	private Integer rangeTo;
	private Double rate;
	private Integer ttm1;
	private Integer ttm2;
	private Timestamp effectiveDate;
	private Timestamp expiryDate;
	private Boolean p1;
	private Boolean p2;
	private Boolean p3;
	private BigDecimal dimFactor;
	private String freightClass;
 
	public String getFreightClass() {
		return freightClass;
	}
	public void setFreightClass(String freightClass) {
		this.freightClass = freightClass;
	}
	public BigDecimal getDimFactor() {
		return dimFactor;
	}
	public void setDimFactor(BigDecimal dimFactor) {
		this.dimFactor = dimFactor;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getBusinessId() {
		return businessId;
	}
	public void setBusinessId(Long businessId) {
		this.businessId = businessId;
	}
	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	public Long getServiceId() {
		return serviceId;
	}
	public void setServiceId(Long serviceId) {
		this.serviceId = serviceId;
	}
	public String getFromZone() {
		return fromZone;
	}
	public void setFromZone(String fromZone) {
		this.fromZone = fromZone;
	}
	public String getToZone() {
		return toZone;
	}
	public void setToZone(String toZone) {
		this.toZone = toZone;
	}	
	public String getEquipment() {
		return equipment;
	}
	public void setEquipment(String equipment) {
		this.equipment = equipment;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public Double getFscPercent() {
		return fscPercent;
	}
	public void setFscPercent(Double fscPercent) {
		this.fscPercent = fscPercent;
	}
	public Double getMinimum() {
		return minimum;
	}
	public void setMinimum(Double minimum) {
		this.minimum = minimum;
	}
	public Integer getRangeFrom() {
		return rangeFrom;
	}
	public void setRangeFrom(Integer rangeFrom) {
		this.rangeFrom = rangeFrom;
	}
	public Integer getRangeTo() {
		return rangeTo;
	}
	public void setRangeTo(Integer rangeTo) {
		this.rangeTo = rangeTo;
	}
	public Double getRate() {
		return rate;
	}
	public void setRate(Double rate) {
		this.rate = rate;
	}
	public Integer getTtm1() {
		return ttm1;
	}
	public void setTtm1(Integer ttm1) {
		this.ttm1 = ttm1;
	}
	public Integer getTtm2() {
		return ttm2;
	}
	public void setTtm2(Integer ttm2) {
		this.ttm2 = ttm2;
	}
	public Timestamp getEffectiveDate() {
		return effectiveDate;
	}
	public void setEffectiveDate(Timestamp effectiveDate) {
		this.effectiveDate = effectiveDate;
	}
	public Timestamp getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(Timestamp expiryDate) {
		this.expiryDate = expiryDate;
	}
	public Boolean getP1() {
		return p1;
	}
	public void setP1(Boolean p1) {
		this.p1 = p1;
	}
	public Boolean getP2() {
		return p2;
	}
	public void setP2(Boolean p2) {
		this.p2 = p2;
	}
	public Boolean getP3() {
		return p3;
	}
	public void setP3(Boolean p3) {
		this.p3 = p3;
	}
	public static LtlPoundRate getObject(Long cusId, long busId,
			Long servId, String fromZone, String toZone,String fClass) {
		LtlPoundRate r = new LtlPoundRate();
		r.setCustomerId(cusId);
		r.setBusinessId(busId);
		r.setServiceId(servId);
		r.setFromZone(fromZone);
		r.setToZone(toZone);
		r.setFreightClass(fClass);
		return r;
	}
	public static Comparator LtlPoundRateComparator = new Comparator() {
		public int compare(Object arg0, Object arg1) {
			double cus1 = ((LtlPoundRate) arg0).getRate();
			double cus2 = ((LtlPoundRate) arg1).getRate();
			
			if(cus1>cus2)
				return 1;
			else if(cus1<cus2)
				return -1;
			else return 0;
		
		}
	};
	
}
