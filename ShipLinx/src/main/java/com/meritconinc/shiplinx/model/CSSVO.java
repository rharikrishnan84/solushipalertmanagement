package com.meritconinc.shiplinx.model;

import java.util.List;

import com.meritconinc.mmr.model.common.LocaleVO;
import com.meritconinc.mmr.utilities.MessageUtil;

/**
 * 
 * @author Dinesh
 *
 */
public class CSSVO {
	
	
	public CSSVO() {
	}
	
	private Long businessId;

	private String gridHeaderColor;
	
	private String menuColor;
	
	private String menuHoverColor;
	
	private String buttonColor;
	
	private String barFirstColor;
	
	private String barSecondColor;
	
	private String footerColor;
	
	private  byte[] logoImgByte;
	private byte[] backGroudImgByte;
	private byte[] favIconByte;
	private byte[] emailHeaderByte;
	private String emailHeader;
	private String emailHeaderFileName;
	
	private String emailShipConfim;
	private String emailPickup;
	private String emailShipCancel;
	private String emailNewCustomer;
	private String emailCusNotify;
	private String emailInvoiceNotify;
	private String emailSalesRepNewCust;
	private String emailRateNotify;
	private String emailWareHouse;
	private String emailForgotpwd;
	private String cssText;
	private String logoImageFileName;
	private BusinessEmail businessEmail;
	private List<LocaleVO> locales=MessageUtil.getLocales();
    private List<BusinessEmail> businessEmailList;
	private String logoImage;
	private String backImg;
	private String backImgFileName;
	private String footerFontColor;
	
	public String getFooterFontColor() {
			return footerFontColor;
		}


		public void setFooterFontColor(String footerFontColor) {
			this.footerFontColor = footerFontColor;
		}
		
		private String arrowColor;            // vivek

		  //vivek
			public String getArrowColor() {
				return arrowColor;
			}

			public void setArrowColor(String arrowColor) {
				this.arrowColor = arrowColor;
			}

	public String getLogoImageFileName() {
		return logoImageFileName;
	}

	public void setLogoImageFileName(String logoImageFileName) {
		this.logoImageFileName = logoImageFileName;
	}



	public String getBarFirstColor() {
		return barFirstColor;
	}

	public void setBarFirstColor(String barFirstColor) {
		this.barFirstColor = barFirstColor;
	}

	public String getBarSecondColor() {
		return barSecondColor;
	}

	public void setBarSecondColor(String barSecondColor) {
		this.barSecondColor = barSecondColor;
	}

	public Long getBusinessId() {
		return businessId;
	}

	public void setBusinessId(Long businessId) {
		this.businessId = businessId;
	}

	public String getButtonColor() {
		return buttonColor;
	}

	public void setButtonColor(String buttonColor) {
		this.buttonColor = buttonColor;
	}

	public String getMenuColor() {
		return menuColor;
	}

	public String getMenuHoverColor() {
		return menuHoverColor;
	}

	public void setMenuHoverColor(String menuHoverColor) {
		this.menuHoverColor = menuHoverColor;
	}

	public void setMenuColor(String menuColor) {
		this.menuColor = menuColor;
	}

	public String getGridHeaderColor() {
		return gridHeaderColor;
	}

	public void setGridHeaderColor(String gridHeaderColor) {
		this.gridHeaderColor = gridHeaderColor;
	}

	public String getFooterColor() {
		return footerColor;
	}

	public void setFooterColor(String footerColor) {
		this.footerColor = footerColor;
	}
	public String getCssText() {
		return cssText;
	}

	public void setCssText(String cssText) {
		this.cssText = cssText;
	}

	public String getLogoImage() {
		return logoImage;
	}

	public void setLogoImage(String logoImage) {
		this.logoImage = logoImage;
	}

	public byte[] getLogoImgByte() {
		return logoImgByte;
	}

	public void setLogoImgByte(byte[] logoImgByte) {
		this.logoImgByte = logoImgByte;
	}

	public byte[] getBackGroudImgByte() {
		return backGroudImgByte;
	}

	public void setBackGroudImgByte(byte[] backGroudImgByte) {
		this.backGroudImgByte = backGroudImgByte;
	}

	public byte[] getFavIconByte() {
		return favIconByte;
	}

	public void setFavIconByte(byte[] favIconByte) {
		this.favIconByte = favIconByte;
	}

	public String getBackImgFileName() {
		return backImgFileName;
	}

	public void setBackImgFileName(String backImgFileName) {
		this.backImgFileName = backImgFileName;
	}

	public String getBackImg() {
		return backImg;
	}

	public void setBackImg(String backImg) {
		this.backImg = backImg;
	}

	public String getEmailHeader() {
		return emailHeader;
	}

	public void setEmailHeader(String emailHeader) {
		this.emailHeader = emailHeader;
	}

	public String getEmailHeaderFileName() {
		return emailHeaderFileName;
	}

	public void setEmailHeaderFileName(String emailHeaderFileName) {
		this.emailHeaderFileName = emailHeaderFileName;
	}

	public String getEmailShipConfim() {
		return emailShipConfim;
	}

	public void setEmailShipConfim(String emailShipConfim) {
		this.emailShipConfim = emailShipConfim;
	}

	public String getEmailPickup() {
		return emailPickup;
	}

	public void setEmailPickup(String emailPickup) {
		this.emailPickup = emailPickup;
	}

	public String getEmailShipCancel() {
		return emailShipCancel;
	}

	public void setEmailShipCancel(String emailShipCancel) {
		this.emailShipCancel = emailShipCancel;
	}

	public String getEmailNewCustomer() {
		return emailNewCustomer;
	}

	public void setEmailNewCustomer(String emailNewCustomer) {
		this.emailNewCustomer = emailNewCustomer;
	}

	public String getEmailCusNotify() {
		return emailCusNotify;
	}

	public void setEmailCusNotify(String emailCusNotify) {
		this.emailCusNotify = emailCusNotify;
	}

	public String getEmailRateNotify() {
		return emailRateNotify;
	}

	public void setEmailRateNotify(String emailRateNotify) {
		this.emailRateNotify = emailRateNotify;
	}

	public String getEmailInvoiceNotify() {
		return emailInvoiceNotify;
	}

	public void setEmailInvoiceNotify(String emailInvoiceNotify) {
		this.emailInvoiceNotify = emailInvoiceNotify;
	}

	public String getEmailSalesRepNewCust() {
		return emailSalesRepNewCust;
	}

	public void setEmailSalesRepNewCust(String emailSalesRepNewCust) {
		this.emailSalesRepNewCust = emailSalesRepNewCust;
	}

	public String getEmailWareHouse() {
		return emailWareHouse;
	}

	public void setEmailWareHouse(String emailWareHouse) {
		this.emailWareHouse = emailWareHouse;
	}

	public String getEmailForgotpwd() {
		return emailForgotpwd;
	}

	public void setEmailForgotpwd(String emailForgotpwd) {
		this.emailForgotpwd = emailForgotpwd;
	}

	public byte[] getEmailHeaderByte() {
		return emailHeaderByte;
	}

	public void setEmailHeaderByte(byte[] emailHeaderByte) {
		this.emailHeaderByte = emailHeaderByte;
	}
	public List<BusinessEmail> getBusinessEmailList() {
		return businessEmailList;
	}

	public void setBusinessEmailList(List<BusinessEmail> businessEmailList) {
		this.businessEmailList = businessEmailList;
	}

	public BusinessEmail getBusinessEmail() {
		return businessEmail;
	}

	public void setBusinessEmail(BusinessEmail businessEmail) {
		this.businessEmail = businessEmail;
	}

	public List<LocaleVO> getLocales() {
		return locales;
	}

	public void setLocales(List<LocaleVO> locales) {
		this.locales = locales;
	}

	 
	

}
