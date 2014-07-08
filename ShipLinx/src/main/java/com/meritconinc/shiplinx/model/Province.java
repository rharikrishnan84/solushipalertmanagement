package com.meritconinc.shiplinx.model;

public class Province {
	
	private Long id;
	private String provinceCode;
	private String provinceName;
	private Long countryId;
	private int taxChargeGroup;
	
	
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
	 * @return Returns the provinceCode.
	 */
	public String getProvinceCode() {
		return provinceCode;
	} 
	/**
	 * @param provinceCode The provinceCode to set.
	 */
	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}
	
	/**
	 * @return Returns the provinceName.
	 */
	public String getProvinceName() {
		return provinceName;
	}
	/**
	 * @param provinceName The provinceName to set.
	 */
	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}
	
	/**
	 * @return Returns the id.
	 */
	public Long getCountryId() {
		return countryId;
	}
	/**
	 * @param id The id to set.
	 */
	public void setCountryId(Long countryId) {
		this.countryId = countryId;
	}
	
	public int getTaxChargeGroup() {
		return taxChargeGroup;
	}
	public void setTaxChargeGroup(int taxChargeGroup) {
		this.taxChargeGroup = taxChargeGroup;
	}
	
	
}