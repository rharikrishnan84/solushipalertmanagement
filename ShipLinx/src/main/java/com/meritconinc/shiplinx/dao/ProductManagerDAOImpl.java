package com.meritconinc.shiplinx.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.meritconinc.shiplinx.model.PackageTypes;
import com.meritconinc.shiplinx.model.ProductLine;
import com.meritconinc.shiplinx.model.Products;
import java.sql.SQLException;
import org.apache.poi.hssf.record.formula.Ptg;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.meritconinc.mmr.utilities.MmrBeanLocator;
import com.meritconinc.shiplinx.model.ProductPackageMap;

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
	
	

 @Override
 public Products getProductBySKUorRef1(String skuId, String reference1,Long customerId) {
   // TODO Auto-generated method stub
   Map<String, Object> paramObj = new HashMap<String, Object>();
   paramObj.put("skuId",skuId);
   paramObj.put("reference1", reference1);
   paramObj.put("customerId", customerId);
   Products products=(Products)getSqlMapClientTemplate().queryForObject("getProductBySKUorRef1",paramObj);
   return products;
 }

  
  
 @Override
 public void sycnProductsInBatch(List<Products> ptsListtoAdd,
     List<Products> ptsListtoUpdate, List<Long> productIdsd) throws SQLException {
   // TODO Auto-generated method stub
   SqlMapClient sqlmapClinet=getSqlMapClient();
   try {
     sqlmapClinet.startBatch();
     sqlmapClinet.startTransaction();
     //delete product
     if(productIdsd!=null && productIdsd.size()>0){
       for(Long productId:productIdsd){
         sqlmapClinet.insert("deleteProductInBatch", productId);
       }
     }
     //add new products
     if(ptsListtoAdd!=null && ptsListtoAdd.size()>0){
       for(Products pt:ptsListtoAdd){
         sqlmapClinet.insert("addProductsInBatch", pt);
       }
     }
     
     if(ptsListtoUpdate!=null && ptsListtoUpdate.size()>0){
       for(Products pt:ptsListtoUpdate){
         sqlmapClinet.update("updateProductsInBatch",pt);
       }
     }
   sqlmapClinet.executeBatch();
     sqlmapClinet.commitTransaction();
   } catch (SQLException e) {
   // TODO Auto-generated catch block
     
 e.printStackTrace();
   } finally {
      
     sqlmapClinet.endTransaction();
      
   }
   
}

 @SuppressWarnings("unchecked")
 @Override
 public List<ProductPackageMap> searchProductPackageMap(
     ProductPackageMap packagemap) {
   // TODO Auto-generated method stub
   List<ProductPackageMap> prducPackList=(List<ProductPackageMap>)getSqlMapClientTemplate().queryForList("searchProductPackageMap",packagemap);
   return prducPackList;
 }

 @Override
 public void addPackageMap(ProductPackageMap productPackageMap) {
   // TODO Auto-generated method stub
   getSqlMapClientTemplate().insert("addPackageMap",productPackageMap);
 }

 
 @Override
 public Long getMaximumQunaityOfPackageType(long packageTypeId,long  productId, Long customerId) {
   // TODO Auto-generated method stub
   Map<String, Object> paramObj = new HashMap<String, Object>();
   paramObj.put("packageTypeId", packageTypeId);
   paramObj.put("productId", productId);
   paramObj.put("customerId", customerId);
   Long maxquantity=(Long)getSqlMapClientTemplate().queryForObject("getMaximumQunaityOfPackageType",paramObj);
   return maxquantity;
 }

 @Override
 public List<ProductPackageMap> getPackageMapListByPackageId(
     Long packageTypesId, Long customerId) {
   // TODO Auto-generated method stub
   Map<String, Object> paramObj = new HashMap<String, Object>();
   paramObj.put("packageTypeId", packageTypesId);
     paramObj.put("customerId", customerId);
     List<ProductPackageMap> ppmap=getSqlMapClientTemplate().queryForList("getPackageMapListByPackageId",paramObj);
   return ppmap;
 }

 @Override
 public PackageTypes findPackageTypesByProductId(long productId,
     Long customerId) {
   // TODO Auto-generated method stub
   Map<String, Object> paramObj = new HashMap<String, Object>();
     paramObj.put("productId1", productId);
   paramObj.put("customerId", customerId);
   Long packageTypeId=(Long)getSqlMapClientTemplate().queryForObject("findPackageTypesByProductIdandQuntity",paramObj);
   List<PackageTypes> ptypes=(List<PackageTypes> ) getSqlMapClientTemplate().queryForList("fetchAPackageById",packageTypeId);
   if(ptypes!=null && ptypes.size()>0){
     return ptypes.get(0);
   }
   return null;
   }

 @Override
 public Products searchUniuqeProduct(Products p) {
   // TODO Auto-generated method stub
   Products pr=(Products)getSqlMapClientTemplate().queryForObject("searchUniuqeProduct",p);
   return pr;
 }

 @Override
   // TODO Auto-generated method stub
 public ProductPackageMap getProductPackageMapById(long packageMapId) {
   ProductPackageMap ppm=(ProductPackageMap)getSqlMapClientTemplate().queryForObject("getProductPackageMapById",packageMapId);
   return ppm;
 }

 @Override
 public void updateProductPackageMap(ProductPackageMap productPackageMap) {
   // TODO Auto-generated method stub
   getSqlMapClientTemplate().update("updateProductPackageMap",productPackageMap);
 }

  @Override
 public void deleteProductPackageMap(long productPackageMapId) {
   // TODO Auto-generated method stub
   getSqlMapClientTemplate().update("deleteProductPackageMap",productPackageMapId);
 }

}