package com.meritconinc.mmr.dao.ibatis;
/**
 * 
 * @author selva
 * E-commerce soluship integration ( 5/06/2015)
 *
 */
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.ws.ServiceMode;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.a.a.a.g.m.p;
import com.meritconinc.mmr.dao.EcommerceDAO;
import com.meritconinc.mmr.model.admin.EcommerceConfig;
import com.meritconinc.mmr.model.admin.EcommerceLog;
import com.meritconinc.mmr.model.admin.EcommerceStore;
@ServiceMode
public class EcommerceDAOImpl extends SqlMapClientDaoSupport implements EcommerceDAO{

	@Override
	public List<EcommerceStore> listEcommerceStores(EcommerceStore ecommerceStore) {
		// TODO Auto-generated method stub
		List<EcommerceStore> stores=getSqlMapClientTemplate().queryForList("listEcommerceStores",ecommerceStore);
		return stores;
	}

	@Override
	public long addEcommerceStore(EcommerceStore ecommerceStore) {
		// TODO Auto-generated method stub
		long storeId = 0;
		storeId=(Long) getSqlMapClientTemplate().insert("addEcommerceStore", ecommerceStore);
		return storeId;
	}

	@Override
	public EcommerceStore getEcommerceStoreById(long storeId) {
		// TODO Auto-generated method stub
		EcommerceStore ecomstore=(EcommerceStore)getSqlMapClientTemplate().queryForObject("getEcommerceStoreById", storeId);
		return ecomstore;
	}

	@Override
	public void updateEcommerceStore(EcommerceStore ecommerceStore) {
		// TODO Auto-generated method stub
 		getSqlMapClientTemplate().update("updateEcommerceStore",ecommerceStore);
		
	}

	@Override
	public void deleteEcommerceStoreById(long storeId) {
		// TODO Auto-generated method stub
		getSqlMapClientTemplate().update("deleteEcommerceStoreById",storeId);
	}

	@Override
	public List<EcommerceConfig> listEcommerceStoreConfig() {
		// TODO Auto-generated method stub
		@SuppressWarnings("unchecked")
		List<EcommerceConfig> ecommStoreConfig=(List<EcommerceConfig>)getSqlMapClientTemplate().queryForList("listEcommerceStoreConfig");
		return ecommStoreConfig;
	}

	@Override
	public EcommerceStore getEcomStorebyStoreUrl(String storeUrl) {
		// TODO Auto-generated method stub
		EcommerceStore ecomStore=(EcommerceStore)getSqlMapClientTemplate().queryForObject("getEcomStorebyStoreUrl",storeUrl);
		return ecomStore;
	}

	@Override
	public void UpdateAccessKeyForStore(String shop2, String accessKey) {
		// TODO Auto-generated method stub
		Map<String, Object> paramObj = new HashMap<String, Object>();
		 paramObj.put("storeurl", shop2);
		paramObj.put("accessKey", accessKey);
		getSqlMapClientTemplate().update("UpdateAccessKeyForStore",paramObj);
	}

	@Override
	public EcommerceStore getEcommrceStoreByCustomer(long customerId) {
		// TODO Auto-generated method stub
		EcommerceStore store=(EcommerceStore)getSqlMapClientTemplate().queryForObject("getEcommrceStoreByCustomer",customerId);
		return store;
	}

	@Override
	public void insertEcommerceLog(EcommerceLog log) {
		// TODO Auto-generated method stub
		getSqlMapClientTemplate().insert("addEcommerceLog",log);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EcommerceLog> getEcommerceLogs(Long shopifyOrderId) {
		// TODO Auto-generated method stub
		List<EcommerceLog> logs=(List<EcommerceLog>) getSqlMapClientTemplate().queryForList("getEcomAccessByShopifyOrderId",shopifyOrderId);
		return logs;
	}

	@Override
	public void UpdateServices(EcommerceStore store) {
		// TODO Auto-generated method stub
		
		getSqlMapClientTemplate().update("getUpdateServicesShopify",store);
	}
	

	@Override
		public void updateFreeShiping(EcommerceStore ecommerceStore) {
			// TODO Auto-generated method stub
			getSqlMapClientTemplate().update("updateFreeShiping",ecommerceStore);
		}

}
