package com.meritconinc.mmr.model.admin;

import java.sql.Timestamp;
import java.util.Date;

import com.meritconinc.mmr.dao.EcommerceDAO;
import com.meritconinc.mmr.dao.PropertyDAO;
import com.meritconinc.mmr.utilities.MmrBeanLocator;
import com.meritconinc.shiplinx.model.Customer;

 
/**
 * 
 * @author selva
 * E-commerce soluship integration ( 5/06/2015)
 *
 */
public class EcommerceStore {
	
	private long ecommerceStoreId;
	 
	private String url;
 
 	 private long businessId;
	 private Long customerId;
	 private String accessKey;
	 private String scopes;
	 private long ecomPlatformId;
	 private String rateServiceUrl;
	 private String createShipmentUrl;
	 private String apiKey;
	 private String sharedSceret;
	 private boolean chepest;
	 private boolean fastest;
	 private boolean bothService;
	 private String ecommerceDomain;
	 private Customer ecomCustomer;
	 private String cancelShipmentUrl;
     private String cancelShipWebhookId;
	 private String createShipWebhookId;
	 private String rateServiceId;
	 private boolean active;
	 private boolean packageMap;
	 private String installUrl;
	 private Timestamp createdAt;
	 private Timestamp updatedAt;
	 private Timestamp installedAt;
	 private String createdBy;
	 private String uninstallUrl;
	 private Long webhookUnistallId;
	 
	 private boolean autoFullFill;
	 

	 //free shipping and markup
 	 private Double flatRate;
 	 private Double weight;
 	 private String compareRate;
 	 private String compareWeight;
 	 private Long compareFreeship; //1 less than ; 2 greater than
 	 private Double markupPerc;
 	 private Double flatMarkup;
 	 private Long freeShipType; // 1 weight ; 2 cost $
 	 private Long markupType; // 1  mark up ; 2 mark down
 	 private Long markupLevel; //1 : markup by flat rate ; 2 markup by percentage;
	 private boolean freeshipRequired;	 
  	 
	 // customer settings sept-2
	 private boolean singlePack;
	 private Double maxPackWeight;
	 
	 
	 
	public Double getFlatRate() {
		return flatRate;
	}
	public void setFlatRate(Double flatRate) {
		this.flatRate = flatRate;
	}
	public Double getWeight() {
		return weight;
	}
	public void setWeight(Double weight) {
		this.weight = weight;
	}
	public String getCompareRate() {
		return compareRate;
	}
	public void setCompareRate(String compareRate) {
		this.compareRate = compareRate;
	}
	public String getCompareWeight() {
		return compareWeight;
	}
	public void setCompareWeight(String compareWeight) {
		this.compareWeight = compareWeight;
	}
	public Long getCompareFreeship() {
		return compareFreeship;
	}
	public void setCompareFreeship(Long compareFreeship) {
		this.compareFreeship = compareFreeship;
	}
	public Double getMarkupPerc() {
		return markupPerc;
	}
	public void setMarkupPerc(Double markupPerc) {
		this.markupPerc = markupPerc;
	}
	public Double getFlatMarkup() {
		return flatMarkup;
	}
	public void setFlatMarkup(Double flatMarkup) {
		this.flatMarkup = flatMarkup;
	}
	public Long getFreeShipType() {
		return freeShipType;
	}
	public void setFreeShipType(Long freeShipType) {
		this.freeShipType = freeShipType;
	}
	public Long getMarkupType() {
		return markupType;
	}
	public void setMarkupType(Long markupType) {
		this.markupType = markupType;
	}
	public Long getMarkupLevel() {
		return markupLevel;
	}
	public void setMarkupLevel(Long markupLevel) {
		this.markupLevel = markupLevel;
	}
	public boolean isFreeshipRequired() {
		return freeshipRequired;
	}
	public void setFreeshipRequired(boolean freeshipRequired) {
		this.freeshipRequired = freeshipRequired;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	 
 
	public long getEcommerceStoreId() {
		return ecommerceStoreId;
	}
	public void setEcommerceStoreId(long ecommerceStoreId) {
		this.ecommerceStoreId = ecommerceStoreId;
	}
 
	public long getBusinessId() {
		return businessId;
	}
	public void setBusinessId(long businessId) {
		this.businessId = businessId;
	}
 
	 
	public String getAccessKey() {
		return accessKey;
	}
	public void setAccessKey(String accessKey) {
		this.accessKey = accessKey;
	}
	public String getScopes() {
		return scopes;
	}
	public void setScopes(String scopes) {
		this.scopes = scopes;
	}
	public long getEcomPlatformId() {
		return ecomPlatformId;
	}
	public void setEcomPlatformId(long ecomPlatformId) {
		this.ecomPlatformId = ecomPlatformId;
	}
	public String getRateServiceUrl() {
		return rateServiceUrl;
	}
	public void setRateServiceUrl(String rateServiceUrl) {
		this.rateServiceUrl = rateServiceUrl;
	}
	public String getCreateShipmentUrl() {
		return createShipmentUrl;
	}
	public void setCreateShipmentUrl(String createShipmentUrl) {
		this.createShipmentUrl = createShipmentUrl;
	}
	public String getSharedSceret() {
		return sharedSceret;
	}
	public void setSharedSceret(String sharedSceret) {
		this.sharedSceret = sharedSceret;
	}
	public String getApiKey() {
		return apiKey;
	}
	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}
	public boolean isChepest() {
		return chepest;
	}
	public void setChepest(boolean chepest) {
		this.chepest = chepest;
	}
	public boolean isFastest() {
		return fastest;
	}
	public void setFastest(boolean fastest) {
		this.fastest = fastest;
	}
	public boolean isBothService() {
		return bothService;
	}
	public void setBothService(boolean bothService) {
		this.bothService = bothService;
	}
	public String getEcommerceDomain() {
		return ecommerceDomain;
	}
	public void setEcommerceDomain(String ecommerceDomain) {
		this.ecommerceDomain = ecommerceDomain;
	}
	public Customer getEcomCustomer() {
		return ecomCustomer;
	}
	public void setEcomCustomer(Customer ecomCustomer) {
		this.ecomCustomer = ecomCustomer;
	}
	public String getCancelShipmentUrl() {
		return cancelShipmentUrl;
	}
	public void setCancelShipmentUrl(String cancelShipmentUrl) {
		this.cancelShipmentUrl = cancelShipmentUrl;
	}
	 
	public String getRateServiceId() {
		return rateServiceId;
	}
	public void setRateServiceId(String rateServiceId) {
		this.rateServiceId = rateServiceId;
	}
	public String getCancelShipWebhookId() {
		return cancelShipWebhookId;
	}
	public void setCancelShipWebhookId(String cancelShipWebhookId) {
		this.cancelShipWebhookId = cancelShipWebhookId;
	}
	public String getCreateShipWebhookId() {
		return createShipWebhookId;
	}
	public void setCreateShipWebhookId(String createShipWebhookId) {
		this.createShipWebhookId = createShipWebhookId;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public String getInstallUrl() {
		return installUrl;
	}
	public void setInstallUrl(String installUrl) {
		this.installUrl = installUrl;
	}
	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	public boolean isPackageMap() {
		return packageMap;
	}
	public void setPackageMap(boolean packageMap) {
		this.packageMap = packageMap;
	}
	public Timestamp getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}
	public Timestamp getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}
	public Timestamp getInstalledAt() {
		return installedAt;
	}
	public void setInstalledAt(Timestamp installedAt) {
		this.installedAt = installedAt;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getUninstallUrl() {
		PropertyDAO propertyDAO = (PropertyDAO) MmrBeanLocator.getInstance()
				.findBean("propertyDAO");
		String url=propertyDAO.readProperty("SHOPIFY", "WEBHOOK_UNINSTALL_NOTIFY_URL");
		if(url!=null){
			uninstallUrl=url;
		}
		return uninstallUrl;
	}
	public void setUninstallUrl(String uninstallUrl) {
	 
		this.uninstallUrl = uninstallUrl;
	}
	public Long getWebhookUnistallId() {
		return webhookUnistallId;
	}
	public void setWebhookUnistallId(Long webhookUnistallId) {
		this.webhookUnistallId = webhookUnistallId;
	}
	public boolean isSinglePack() {
		return singlePack;
	}
	public void setSinglePack(boolean singlePack) {
		this.singlePack = singlePack;
	}
	public Double getMaxPackWeight() {
		return maxPackWeight;
	}
	public void setMaxPackWeight(Double maxPackWeight) {
		this.maxPackWeight = maxPackWeight;
	}
	public boolean isAutoFullFill() {
		return autoFullFill;
	}
	public void setAutoFullFill(boolean autoFullFill) {
		this.autoFullFill = autoFullFill;
	}
 

}
