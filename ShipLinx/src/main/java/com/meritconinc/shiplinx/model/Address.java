package com.meritconinc.shiplinx.model;

import java.io.Serializable; 
import java.util.Date;

import com.meritconinc.mmr.utilities.MessageUtil;
import com.meritconinc.mmr.utilities.StringUtil;

public class Address implements Serializable, Cloneable {
	static final long serialVersionUID = 17092007;
	
	private long addressId;
	private String abbreviationName;
	private String consigneeId;
	private String address1;
	private String address2;
	private String city;	
	private String brokerCode;
	private String phoneNo;
	private String phoneExt;
	private String faxNo;
	private String mobilePhoneNo;
	private String emailAddress;
	private String contactName;
	private int countryId;
	private String postalCode;
	private String provinceId;
	private String url;
	private boolean residential;
	private String countryCode; 
	private String countryName;
	private String provinceCode;
	private long customerId;
	private Boolean defaultFromAddress = false;
	private Boolean defaultToAddress = false;
	private String distributionName;
	private Date dateCreated;
	private boolean sendNotification;
	private String taxId;
	private String addressLong;
	
	//Web only
	
	public String getTaxId() {
		return taxId;
	}
	public void setTaxId(String taxId) {
		this.taxId = taxId;
	}
	private boolean zipCodeRequired;
	
	
		
	public String getBrokerCode() {
		return brokerCode;
	}
	public void setBrokerCode(String brokerCode) {
		this.brokerCode = brokerCode;
	}
	/**
	 * @return the addressId
	 */
	public long getAddressId() {
		return addressId;
	}
	/**
	 * @param addressId the addressId to set
	 */
	public void setAddressId(long addressId) {
		this.addressId = addressId;
	}
	/**
	 * @return the abbreviationName
	 */
	public String getAbbreviationName() {
		return abbreviationName;
	}
	/**
	 * @param abbreviationName the abbreviationName to set
	 */
	public void setAbbreviationName(String abbreviationName) {
		this.abbreviationName = abbreviationName;
	}
	/**
	 * @return the consigneeId
	 */
	public String getConsigneeId() {
		return consigneeId;
	}
	/**
	 * @param consigneeId the consigneeId to set
	 */
	public void setConsigneeId(String consigneeId) {
		this.consigneeId = consigneeId;
	}
	/**
	 * @return the address1
	 */
	public String getAddress1() {
		return address1;
	}
	/**
	 * @param address1 the address1 to set
	 */
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	/**
	 * @return the address2
	 */
	public String getAddress2() {
		return address2;
	}
	/**
	 * @param address2 the address2 to set
	 */
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}
	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}
	/**
	 * @return the phoneNo
	 */
	public String getPhoneNo() {
		return phoneNo;
	}
	/**
	 * @param phoneNo the phoneNo to set
	 */
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public String getPhoneExt() {
		return phoneExt;
	}
	public void setPhoneExt(String phoneExt) {
		this.phoneExt = phoneExt;
	}
	/**
	 * @return the faxNo
	 */
	public String getFaxNo() {
		return faxNo;
	}
	/**
	 * @param faxNo the faxNo to set
	 */
	public void setFaxNo(String faxNo) {
		this.faxNo = faxNo;
	}
	/**
	 * @return the mobilePhoneNo
	 */
	public String getMobilePhoneNo() {
		return mobilePhoneNo;
	}
	/**
	 * @param mobilePhoneNo the mobilePhoneNo to set
	 */
	public void setMobilePhoneNo(String mobilePhoneNo) {
		this.mobilePhoneNo = mobilePhoneNo;
	}
	/**
	 * @return the emailAddress
	 */
	public String getEmailAddress() {
		return emailAddress;
	}
	/**
	 * @param emailAddress the emailAddress to set
	 */
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	/**
	 * @return the contactName
	 */
	public String getContactName() {
		return contactName;
	}
	/**
	 * @param contactName the contactName to set
	 */
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}
	/**
	 * @return the countryId
	 */
	public int getCountryId() {
		return countryId;
	}
	/**
	 * @param countryId the countryId to set
	 */
	public void setCountryId(int countryId) {
		this.countryId = countryId;
	}
	/**
	 * @return the postalCode
	 */
	public String getPostalCode() {
		return postalCode;
	}
	/**
	 * @param postalCode the postalCode to set
	 */
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	/**
	 * @return the provinceId
	 */
	public String getProvinceId() {
		return provinceId;
	}
	/**
	 * @param provinceId the provinceId to set
	 */
	public void setProvinceId(String provinceId) {
		this.provinceId = provinceId;
	}
	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}
	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	/**
	 * @return the residential
	 */
	public boolean isResidential() {
		return residential;
	}
	public boolean getResidential() {
		return residential;
	}
	/**
	 * @param residential the residential to set
	 */
	public void setResidential(boolean residential) {
		this.residential = residential;
	}
	/**
	 * @return the serialVersionUID
	 */
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}
	public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	public String getProvinceCode() {
		return provinceCode;
	}
	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}
	public long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}
	public Boolean isDefaultFromAddress() {
		return defaultFromAddress;
	}
	public Boolean getDefaultFromAddress() {
		return defaultFromAddress;
	}
	public void setDefaultFromAddress(Boolean defaultFromAddress) {
		this.defaultFromAddress = defaultFromAddress;
	}
	public Boolean isDefaultToAddress() {
		return defaultToAddress;
	}
	public Boolean getDefaultToAddress() {
		return defaultToAddress;
	}
	public void setDefaultToAddress(Boolean defaultToAddress) {
		this.defaultToAddress = defaultToAddress;
	}
	public Date getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
	public String getDistributionName() {
		return distributionName;
	}
	public void setDistributionName(String distributionName) {
		this.distributionName = distributionName;
	}
	
	public String getLongAddress(){
		
		StringBuilder stb = new StringBuilder();
		StringUtil.buildString(stb, abbreviationName, true);
		StringUtil.buildString(stb, address1, true);
		StringUtil.buildString(stb, address2, true);
		StringUtil.buildString(stb, city, true);
		StringUtil.buildString(stb, postalCode, true);
		StringUtil.buildString(stb, provinceCode, true);
		StringUtil.buildString(stb, getCountryName(), false);
		
		return stb.toString();
	}
	
	public String getLongAddress2(){ //no company name
		StringBuilder stb = new StringBuilder();
		StringUtil.buildString(stb, address1, true);
		StringUtil.buildString(stb, address2, true);
		StringUtil.buildString(stb, city, true);
		StringUtil.buildString(stb, postalCode, true);
		StringUtil.buildString(stb, provinceCode, true);
		StringUtil.buildString(stb, getCountryName(), false);
		
		return stb.toString();
	}
	
	public String getCountryName() {
		return MessageUtil.getCountryName(countryCode);
	}
	public void copyAddress(Address sourceAddress)
	{
		if(sourceAddress!=null){
			this.setAbbreviationName(sourceAddress.getAbbreviationName());
			this.setAddress1(sourceAddress.getAddress1());
			this.setAddress2(sourceAddress.getAddress2());
			this.setCity(sourceAddress.getCity());
			this.setPostalCode(sourceAddress.getPostalCode());
			this.setCountryCode(sourceAddress.getCountryCode());
			this.setProvinceCode(sourceAddress.getProvinceCode());
			this.setProvinceId(sourceAddress.getProvinceId());
			this.setPhoneNo(sourceAddress.getPhoneNo());
			this.setEmailAddress(sourceAddress.getEmailAddress());
			this.setContactName(sourceAddress.getContactName());
			this.setFaxNo(sourceAddress.getFaxNo());
			this.setTaxId(sourceAddress.getTaxId());
			this.setResidential(sourceAddress.getResidential());
			this.setSendNotification(sourceAddress.sendNotification);
		}
	}
	public boolean isZipCodeRequired() {
		return zipCodeRequired;
	}
	public void setZipCodeRequired(boolean zipCodeRequired) {
		this.zipCodeRequired = zipCodeRequired;
	}
	public boolean isSendNotification() {
		return sendNotification;
	}
	public void setSendNotification(boolean sendNotification) {
		this.sendNotification = sendNotification;
	}
	public boolean getSendNotification() {
		return sendNotification;
	}
	public String getAddressLong() {
		return addressLong;
	}
	public void setAddressLong(String addressLong) {
		this.addressLong = addressLong;
	}
	
}
