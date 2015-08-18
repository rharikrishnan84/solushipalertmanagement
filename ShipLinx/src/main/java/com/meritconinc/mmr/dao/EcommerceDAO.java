package com.meritconinc.mmr.dao;

import java.util.List;

import com.meritconinc.mmr.model.admin.EcommerceConfig;
import com.meritconinc.mmr.model.admin.EcommerceLog;
import com.meritconinc.mmr.model.admin.EcommerceStore;

/**
 * 
 * @author selva
 * E-commerce soluship integration ( 5/06/2015)
 *
 */

public interface EcommerceDAO {

	List<EcommerceStore> listEcommerceStores(EcommerceStore st);

	long addEcommerceStore(EcommerceStore ecommerceStore);

	EcommerceStore getEcommerceStoreById(long parseLong);

	void updateEcommerceStore(EcommerceStore ecommerceStore);

	void deleteEcommerceStoreById(long parseLong);

	List<EcommerceConfig> listEcommerceStoreConfig();

 
	void UpdateAccessKeyForStore(String shop2, String accessKey);

	EcommerceStore getEcomStorebyStoreUrl(String ecomRediectUrl2);

	EcommerceStore getEcommrceStoreByCustomer(long customerId);
    void insertEcommerceLog(EcommerceLog log);
    List<EcommerceLog> getEcommerceLogs(Long shopifyOrderId);

	void UpdateServices(EcommerceStore store);
	
	void updateFreeShiping(EcommerceStore ecommerceStore);
}
