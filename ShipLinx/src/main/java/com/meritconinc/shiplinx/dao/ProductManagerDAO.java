package com.meritconinc.shiplinx.dao;

import java.util.List;

import com.meritconinc.shiplinx.model.PackageTypes;
import com.meritconinc.shiplinx.model.ProductLine;
import com.meritconinc.shiplinx.model.Products;
import java.sql.SQLException;

import com.meritconinc.shiplinx.model.ProductPackageMap;


public interface ProductManagerDAO {
	
	public List<Products> searchProductList(Products p,boolean CheckByBoth); //Check by both productId and CustomerId
	
	public List<Products> getListOfProducts(Products product);
	
	public void deleteProducts(Products product);
	
	public void addOrUpdate(Products product, boolean add); // true if we want to add
	
	public List<ProductLine> getProductLineList(ProductLine productLine);
	
	public void addOrUpdateProductLine(ProductLine productLine, boolean add); // true if we want to add.
	
	public List<ProductLine> getProductLineById(ProductLine productLine);
	
	public void deleteProductLine(ProductLine productLine);  
	
	public List<Products> getProductsByProductLineId(Products products);
	
	public List<Products> getProductsByOrderAndCustomer(long orderId, long customerId);
	
	public Products getProductByProductIdAndCustomerId(long productId, long customerId);
	
	public List<PackageTypes> searchPackageTypes(PackageTypes packageTypes);
	
	public void addOrUpdatePackageTypes(PackageTypes packageTypes, boolean add);
	
	public List<PackageTypes> fetchAPackageById(long packageTypeId);
	
	public boolean deletePackageType(long packageTypeId);
	 public Products getProductBySKUorRef1(String skuId, String reference1, Long long1);
	 
	   
	  public void sycnProductsInBatch(List<Products> ptsListtoAdd,
	      List<Products> ptsListtoUpdate, List<Long> productIdsd) throws SQLException;
	 
	  public List<ProductPackageMap> searchProductPackageMap(
	      ProductPackageMap packagemap);
	 
	  public void addPackageMap(ProductPackageMap productPackageMap);
	 
	  
	  public Long getMaximumQunaityOfPackageType(long packageTypeId, long l, Long customerId);
	 
	  public List<ProductPackageMap> getPackageMapListByPackageId(
	      Long packageTypesId, Long customerId);
	 
	  public PackageTypes findPackageTypesByProductId(long productId,
	    Long customerId);
	  
	  public Products searchUniuqeProduct(Products p);
	 
	  public ProductPackageMap getProductPackageMapById(long parseLong);
	 
	  public void updateProductPackageMap(ProductPackageMap productPackageMap);
	  
	 public void deleteProductPackageMap(long parseLong);
}
