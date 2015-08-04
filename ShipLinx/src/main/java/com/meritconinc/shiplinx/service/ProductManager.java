package com.meritconinc.shiplinx.service;

import java.util.List;

import com.meritconinc.shiplinx.model.PackageTypes;
import com.meritconinc.shiplinx.model.ProductLine;
import com.meritconinc.shiplinx.model.Products;
import com.meritconinc.shiplinx.model.ShippingOrder;
import com.sun.xml.bind.v2.runtime.reflect.Lister.Pack;
import com.meritconinc.shiplinx.api.model.ShopifyProduct;
import com.meritconinc.shiplinx.model.ProductPackageMap;

public interface ProductManager {
	
	public List<Products> searchProducts(Products product, boolean CheckByBoth); //both productId and Customer Id
	
	public List<Products> getProductsList(Products product);  
	
	public void deleteProducts(Products product);
	
	public void addOrUpdate(Products product, boolean add); // true if we want to add.
	
	public List<ProductLine> getProductLineList(ProductLine productLine);
	
	public void addOrUpdateProductLine(ProductLine productLine, boolean add); // true if we want to add.
	
	public List<ProductLine> getProductLineListById(ProductLine productLine);
	
	public void deleteProductLine(ProductLine productLine);  
	
	public List<Products> getProductsByProductLineId(Products product);

	public List<Products> getProductsByOrderAndCustomer(long orderId, long customerId);
	
	public boolean updateProductsCounts(ShippingOrder shippingOrder, int status);
	
	public Products getProductByProductIdAndCustomerId(long productId, long customerId);
	
	public List<PackageTypes> searchPackageTypes(PackageTypes packageTypes);
	
	public void addOrUpdatePackageTypes(PackageTypes packageTypes, boolean add);
	
	public List<PackageTypes> fetchAPackageById(long packageTypeId);
	
	public boolean deletePackageType(long packageTypeId);
	
	
	public void sycnShopifyProducts(ShopifyProduct products);
	 
	   public List<ProductPackageMap> searchProductPackageMap(
	       ProductPackageMap packagemap);
	
	   public void addPackageMap(ProductPackageMap productPackageMap);
	 
	   public ProductPackageMap getProductPackageMapById(long parseLong);
	 
	   public void updateProductPackageMap(ProductPackageMap productPackageMap);
	   public void deleteProductPackageMap(long parseLong);
	
}
