package com.meritconinc.shiplinx.utils;

import com.meritconinc.mmr.dao.MenusDAO;
import com.meritconinc.mmr.utilities.MmrBeanLocator;
import com.meritconinc.shiplinx.dao.CarrierServiceDAO;
import com.meritconinc.shiplinx.dao.CreditCardTransactionDAO;
import com.meritconinc.shiplinx.dao.EdiDAO;
import com.meritconinc.shiplinx.dao.MarkupManagerDAO;

public class BusinessDefaultLoaderDaoImpl {

	long newBusinessId = 0;
	long defaultBusinessId = 0;
	
	
	 
	public BusinessDefaultLoaderDaoImpl(long businessId,long defaultBusinessId){
		this.newBusinessId = businessId;
		this.defaultBusinessId = defaultBusinessId;
	}
	
	public void loadAll(){
		loadBusinessCarrier();
		loadBusinessMenu();
		loadEdiInfo();
		loadMerchantAccount();
		loadZones();
		loadZoneStructure();
	}
	
	public void loadAll1(){
		loadBusinessCarrier();
		loadBusinessMenu();
		loadCustomerMarkUp();
		loadEdiInfo();
		loadMerchantAccount();
		loadPoundRate();
		loadSkidRate();
		loadZones();
		loadZoneStructure();
	}
	
	public void loadBusinessCarrier(){
		CarrierServiceDAO carrierServiceDAO= (CarrierServiceDAO) MmrBeanLocator.getInstance().findBean("carrierServiceDAO");
		carrierServiceDAO.setupNewBusinessCarrier(this.newBusinessId,this.defaultBusinessId);
	}
	
	public void loadBusinessMenu(){
		MenusDAO menusDAO = (MenusDAO) MmrBeanLocator.getInstance().findBean("menusDAO");
		menusDAO.insertBusinessMenyByBusiness(this.newBusinessId, this.defaultBusinessId);
	}
	
	public void loadCustomerMarkUp(){
		MarkupManagerDAO markupManagerDAO = (MarkupManagerDAO) MmrBeanLocator.getInstance().findBean("markupManagerDAO");
		markupManagerDAO.insertCustomerMarkupByBusiness(this.newBusinessId, this.defaultBusinessId);
	}
	
	public void loadEdiInfo(){
		EdiDAO ediDAO = (EdiDAO) MmrBeanLocator.getInstance().findBean("ediDAO");
		ediDAO.insertEdiInfoByBusiness(newBusinessId, defaultBusinessId);
		
	}
	
	public void loadPoundRate(){
		MarkupManagerDAO markupManagerDAO = (MarkupManagerDAO) MmrBeanLocator.getInstance().findBean("markupManagerDAO");
		markupManagerDAO.insertLtlPoundrateByBusiness(newBusinessId, defaultBusinessId);
	}
	
	public void loadSkidRate(){
		MarkupManagerDAO markupManagerDAO = (MarkupManagerDAO) MmrBeanLocator.getInstance().findBean("markupManagerDAO");
		markupManagerDAO.insertLtlSkidRateByBusiness(newBusinessId, defaultBusinessId);
	}
	
	public void loadMerchantAccount(){
		CreditCardTransactionDAO ccTransactionDAO=(CreditCardTransactionDAO) MmrBeanLocator.getInstance().findBean("ccTransactionDAO");
		ccTransactionDAO.insertMerchantAccountByBusiness(newBusinessId, defaultBusinessId);
	}
	
	public void loadZoneStructure(){
		
	}
	
	public void loadZones(){
		
	}
	
	
}
