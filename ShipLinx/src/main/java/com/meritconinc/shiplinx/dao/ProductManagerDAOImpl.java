package com.meritconinc.shiplinx.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.meritconinc.shiplinx.model.PackageTypes;
import com.meritconinc.shiplinx.model.ProductLine;
import com.meritconinc.shiplinx.model.Products;

public class ProductManagerDAOImpl extends SqlMapClientDaoSupport implements ProductManagerDAO 
{
	public List<Products> searchProductList(Products prod, boolean bool)
	{
		//implementation to get list from DB through ibatis
		List<Products> plist = new ArrayList<Products>();
		try {
			if(!bool) 
				plist= (List)getSqlMapClientTemplate().queryForList("getProductsList", prod);
			else
				plist= (List)getSqlMapClientTemplate().queryForList("getProductsListByCustIdAndProdId", prod);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return plist;
	} 
	
	public List<Products> getListOfProducts(Products product)
	{
		List<Products> plist = new ArrayList<Products>();
		try {
			plist=(List)getSqlMapClientTemplate().queryForList("getProductsList", product);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return plist;
	}
	
	public void deleteProducts(Products products)
	{
		getSqlMapClientTemplate().delete("deleteProduct", products);	
	}
	
	public void deleteProductLine(ProductLine productLine)
	{
		getSqlMapClientTemplate().delete("deleteProductLine", productLine);
	}
	
	public void addOrUpdate(Products product, boolean add)
	{
		if(!add) //update
			getSqlMapClientTemplate().update("updateProduct", product);
		else	//add
			getSqlMapClientTemplate().insert("createProduct", product);
	}
	
	public List<ProductLine> getProductLineList(ProductLine productLine)
	{
		List<ProductLine> plList = new ArrayList<ProductLine>();
		try {
			plList = (List)getSqlMapClientTemplate().queryForList("getProductLineList", productLine);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return plList;
	}
	
	public void addOrUpdateProductLine(ProductLine productLine, boolean add)
	{
		if(add)
			getSqlMapClientTemplate().update("updateProductLine", productLine);
		else
			getSqlMapClientTemplate().insert("createProductLine", productLine);
	}
	
	public List<ProductLine> getProductLineById(ProductLine productLine)
	{
		List<ProductLine> plList = new ArrayList<ProductLine>();
		try {
			plList = (List)getSqlMapClientTemplate().queryForList("getProductLineListById", productLine);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return plList;
	}
	
	public List<Products> getProductsByProductLineId(Products products)
	{
		List<Products> plList = new ArrayList<Products>();
		try {
			plList = (List)getSqlMapClientTemplate().queryForList("getProductsByProductLineId", products);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return plList;
		
	}
	
	public List<Products> getProductsByOrderAndCustomer(long orderId, long customerId)
	{
		List<Products> plList = new ArrayList<Products>();
		try 
		{
			Map<String, Object> paramObj = new HashMap<String, Object>(1);
			paramObj.put("orderId", orderId);
			paramObj.put("customerId", customerId);
			plList = (List)getSqlMapClientTemplate().queryForList("getProductsByOrderAndCustomer", paramObj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return plList;
		
	}
	
	public Products getProductByProductIdAndCustomerId (long productId, long customerId)
	{
		Products p = new Products();
		Map<String, Object> paramObj = new HashMap<String, Object>(1);
		try 
		{
			paramObj.put("productId", productId);
			paramObj.put("customerId", customerId);
			p = (Products)getSqlMapClientTemplate().queryForObject("getProductByProductIdAndCustomerId", paramObj);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return p;
	}
	
	public List<PackageTypes> searchPackageTypes(PackageTypes packageTypes)
	{
		List<PackageTypes> plList = new ArrayList<PackageTypes>();
		try 
		{
			plList = (List)getSqlMapClientTemplate().queryForList("getPackageTypesByCustomerId", packageTypes);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return plList;
		
	}
	
	public void addOrUpdatePackageTypes(PackageTypes packageTypes, boolean add)
	{
		try {
			if(!add) //update
				getSqlMapClientTemplate().update("editPackageTypes", packageTypes);
			else	//add
				getSqlMapClientTemplate().insert("addPackageTypes", packageTypes);
				
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<PackageTypes> fetchAPackageById(long packageTypeId)
	{
		List<PackageTypes> plList = new ArrayList<PackageTypes>();
		Map<String, Object> paramObj = new HashMap<String, Object>(1);
		try 
		{
			paramObj.put("packageTypeId", packageTypeId);
			plList = (List)getSqlMapClientTemplate().queryForList("fetchAPackageById", paramObj);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return plList;
		
	}
	
	public boolean deletePackageType(long packageTypeId)
	{
		boolean retval = true;
		Map<String, Object> paramObj = new HashMap<String, Object>(1);
		try {
			paramObj.put("packageTypeId", packageTypeId);
			getSqlMapClientTemplate().delete("deletePackageTypes",paramObj);
		} catch (Exception e) {
			e.printStackTrace();
			retval = false;
		}
		return retval;
	}
}
