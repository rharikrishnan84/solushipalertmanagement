package com.meritconinc.shiplinx.model;

import java.io.Serializable;

public class Products implements Serializable, Cloneable
{
	static final long serialVersionUID = 21052012;
	
	private long productId;
	private String productName;
	private String productDescription;
	private String productHarmonizedCode;
	private double unitPrice;
	private String originCountry;
	private long customerId;
	
	
	private String productCode;
	private double unitCost;
	private float weight;
	private String weightUnit;
	private long productLineId;
	private int locationCount;
	private int wipCount;
	private int quarantineCount;
	private int inqueueCount;
	private int backOrderCount;
	private int totalCount;
	private long orderedQuantity;
	 
	private long primaryLocationId;
	
	private ProductLine productLine;
	
	private long productCustomerId;
	
	  //SHOPIFY SHOPIFY
	     private String skuId;
	     private String reference1;
	     private String reference1Name;
	     private Float length;
	     private Float width;
	     private Float height;
	     private long unitmeasureId;
	
	public Products()
	{
		//productLine = new ProductLine();
	}
	
	public ProductLine getProductLine() {
		return productLine;
	}
	public void setProductLine(ProductLine productLine) {
		this.productLine = productLine;
	}
	public long getProductId() {
		return productId;
	}
	public void setProductId(long productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductDescription() {
		return productDescription;
	}
	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}
	public String getProductHarmonizedCode() {
		return productHarmonizedCode;
	}
	public void setProductHarmonizedCode(String productHarmonizedCode) {
		this.productHarmonizedCode = productHarmonizedCode;
	}
	public double getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}
	public long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}
	public String getOriginCountry() {
		return originCountry;
	}
	public void setOriginCountry(String originCountry) {
		this.originCountry = originCountry;
	}
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public double getUnitCost() {
		return unitCost;
	}
	public void setUnitCost(double unitCost) {
		this.unitCost = unitCost;
	}
	public float getWeight() {
		return weight;
	}
	public void setWeight(float weight) {
		this.weight = weight;
	}
	public String getWeightUnit() {
		return weightUnit;
	}
	public void setWeightUnit(String weightUnit) {
		this.weightUnit = weightUnit;
	}
	
	public long getProductLineId() {
		return productLineId;
	}
	public void setProductLineId(long productLineId) {
		this.productLineId = productLineId;
	}
	public int getLocationCount() {
		return locationCount;
	}
	public void setLocationCount(int locationCount) {
		this.locationCount = locationCount;
	}
	public int getWipCount() {
		return wipCount;
	}
	public void setWipCount(int wipCount) {
		this.wipCount = wipCount;
	}
	public int getQuarantineCount() {
		return quarantineCount;
	}
	public void setQuarantineCount(int quarantineCount) {
		this.quarantineCount = quarantineCount;
	}
	public int getInqueueCount() {
		return inqueueCount;
	}
	public void setInqueueCount(int inqueueCount) {
		this.inqueueCount = inqueueCount;
	}
	public int getBackOrderCount() {
		return backOrderCount;
	}
	public void setBackOrderCount(int backOrderCount) {
		this.backOrderCount = backOrderCount;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public long getOrderedQuantity() {
		return orderedQuantity;
	}

	public void setOrderedQuantity(long orderedQuantity) {
		this.orderedQuantity = orderedQuantity;
	}

	public long getPrimaryLocationId() {
		return primaryLocationId;
	}

	public void setPrimaryLocationId(long primaryLocationId) {
		this.primaryLocationId = primaryLocationId;
	}

	public long getProductCustomerId() {
		return productCustomerId;
	}

	public void setProductCustomerId(long productCustomerId) {
		this.productCustomerId = productCustomerId;
	}

	public String getSkuId() {
		return skuId;
	}

	public void setSkuId(String skuId) {
		this.skuId = skuId;
	}

	public String getReference1() {
		return reference1;
	}

	public void setReference1(String reference1) {
		this.reference1 = reference1;
	}

	public String getReference1Name() {
		return reference1Name;
	}

	public void setReference1Name(String reference1Name) {
		this.reference1Name = reference1Name;
	}

	public Float getLength() {
		return length;
	}

	public void setLength(Float length) {
		this.length = length;
	}

	public Float getWidth() {
		return width;
	}

	public void setWidth(Float width) {
		this.width = width;
	}

	public Float getHeight() {
		return height;
	}

	public void setHeight(Float height) {
		this.height = height;
	}

	public long getUnitmeasureId() {
		return unitmeasureId;
	}

	public void setUnitmeasureId(long unitmeasureId) {
		this.unitmeasureId = unitmeasureId;
	}
	

	
}
